package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Projeto;
import br.com.homemade.repository.ProjetoRepository;
import br.com.homemade.service.dto.ProjetoDTO;
import br.com.homemade.service.mapper.ProjetoMapper;
import br.com.homemade.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ProjetoResource REST controller.
 *
 * @see ProjetoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class ProjetoResourceIntTest {

    private static final String DEFAULT_ANDAMENTO = "AAAAAAAAAA";
    private static final String UPDATED_ANDAMENTO = "BBBBBBBBBB";

    private static final String DEFAULT_PENDENTE = "AAAAAAAAAA";
    private static final String UPDATED_PENDENTE = "BBBBBBBBBB";

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private ProjetoMapper projetoMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProjetoMockMvc;

    private Projeto projeto;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProjetoResource projetoResource = new ProjetoResource(projetoRepository, projetoMapper);
        this.restProjetoMockMvc = MockMvcBuilders.standaloneSetup(projetoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Projeto createEntity(EntityManager em) {
        Projeto projeto = new Projeto()
            .andamento(DEFAULT_ANDAMENTO)
            .pendente(DEFAULT_PENDENTE);
        return projeto;
    }

    @Before
    public void initTest() {
        projeto = createEntity(em);
    }

    @Test
    @Transactional
    public void createProjeto() throws Exception {
        int databaseSizeBeforeCreate = projetoRepository.findAll().size();

        // Create the Projeto
        ProjetoDTO projetoDTO = projetoMapper.toDto(projeto);
        restProjetoMockMvc.perform(post("/api/projetos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projetoDTO)))
            .andExpect(status().isCreated());

        // Validate the Projeto in the database
        List<Projeto> projetoList = projetoRepository.findAll();
        assertThat(projetoList).hasSize(databaseSizeBeforeCreate + 1);
        Projeto testProjeto = projetoList.get(projetoList.size() - 1);
        assertThat(testProjeto.getAndamento()).isEqualTo(DEFAULT_ANDAMENTO);
        assertThat(testProjeto.getPendente()).isEqualTo(DEFAULT_PENDENTE);
    }

    @Test
    @Transactional
    public void createProjetoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projetoRepository.findAll().size();

        // Create the Projeto with an existing ID
        projeto.setId(1L);
        ProjetoDTO projetoDTO = projetoMapper.toDto(projeto);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjetoMockMvc.perform(post("/api/projetos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projetoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Projeto> projetoList = projetoRepository.findAll();
        assertThat(projetoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllProjetos() throws Exception {
        // Initialize the database
        projetoRepository.saveAndFlush(projeto);

        // Get all the projetoList
        restProjetoMockMvc.perform(get("/api/projetos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projeto.getId().intValue())))
            .andExpect(jsonPath("$.[*].andamento").value(hasItem(DEFAULT_ANDAMENTO.toString())))
            .andExpect(jsonPath("$.[*].pendente").value(hasItem(DEFAULT_PENDENTE.toString())));
    }

    @Test
    @Transactional
    public void getProjeto() throws Exception {
        // Initialize the database
        projetoRepository.saveAndFlush(projeto);

        // Get the projeto
        restProjetoMockMvc.perform(get("/api/projetos/{id}", projeto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(projeto.getId().intValue()))
            .andExpect(jsonPath("$.andamento").value(DEFAULT_ANDAMENTO.toString()))
            .andExpect(jsonPath("$.pendente").value(DEFAULT_PENDENTE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingProjeto() throws Exception {
        // Get the projeto
        restProjetoMockMvc.perform(get("/api/projetos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProjeto() throws Exception {
        // Initialize the database
        projetoRepository.saveAndFlush(projeto);
        int databaseSizeBeforeUpdate = projetoRepository.findAll().size();

        // Update the projeto
        Projeto updatedProjeto = projetoRepository.findOne(projeto.getId());
        updatedProjeto
            .andamento(UPDATED_ANDAMENTO)
            .pendente(UPDATED_PENDENTE);
        ProjetoDTO projetoDTO = projetoMapper.toDto(updatedProjeto);

        restProjetoMockMvc.perform(put("/api/projetos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projetoDTO)))
            .andExpect(status().isOk());

        // Validate the Projeto in the database
        List<Projeto> projetoList = projetoRepository.findAll();
        assertThat(projetoList).hasSize(databaseSizeBeforeUpdate);
        Projeto testProjeto = projetoList.get(projetoList.size() - 1);
        assertThat(testProjeto.getAndamento()).isEqualTo(UPDATED_ANDAMENTO);
        assertThat(testProjeto.getPendente()).isEqualTo(UPDATED_PENDENTE);
    }

    @Test
    @Transactional
    public void updateNonExistingProjeto() throws Exception {
        int databaseSizeBeforeUpdate = projetoRepository.findAll().size();

        // Create the Projeto
        ProjetoDTO projetoDTO = projetoMapper.toDto(projeto);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProjetoMockMvc.perform(put("/api/projetos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projetoDTO)))
            .andExpect(status().isCreated());

        // Validate the Projeto in the database
        List<Projeto> projetoList = projetoRepository.findAll();
        assertThat(projetoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteProjeto() throws Exception {
        // Initialize the database
        projetoRepository.saveAndFlush(projeto);
        int databaseSizeBeforeDelete = projetoRepository.findAll().size();

        // Get the projeto
        restProjetoMockMvc.perform(delete("/api/projetos/{id}", projeto.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Projeto> projetoList = projetoRepository.findAll();
        assertThat(projetoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Projeto.class);
        Projeto projeto1 = new Projeto();
        projeto1.setId(1L);
        Projeto projeto2 = new Projeto();
        projeto2.setId(projeto1.getId());
        assertThat(projeto1).isEqualTo(projeto2);
        projeto2.setId(2L);
        assertThat(projeto1).isNotEqualTo(projeto2);
        projeto1.setId(null);
        assertThat(projeto1).isNotEqualTo(projeto2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjetoDTO.class);
        ProjetoDTO projetoDTO1 = new ProjetoDTO();
        projetoDTO1.setId(1L);
        ProjetoDTO projetoDTO2 = new ProjetoDTO();
        assertThat(projetoDTO1).isNotEqualTo(projetoDTO2);
        projetoDTO2.setId(projetoDTO1.getId());
        assertThat(projetoDTO1).isEqualTo(projetoDTO2);
        projetoDTO2.setId(2L);
        assertThat(projetoDTO1).isNotEqualTo(projetoDTO2);
        projetoDTO1.setId(null);
        assertThat(projetoDTO1).isNotEqualTo(projetoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(projetoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(projetoMapper.fromId(null)).isNull();
    }
}
