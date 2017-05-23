package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Atividadelicenca;
import br.com.homemade.repository.AtividadelicencaRepository;
import br.com.homemade.service.dto.AtividadelicencaDTO;
import br.com.homemade.service.mapper.AtividadelicencaMapper;
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
 * Test class for the AtividadelicencaResource REST controller.
 *
 * @see AtividadelicencaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class AtividadelicencaResourceIntTest {

    private static final String DEFAULT_CODIGO = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    @Autowired
    private AtividadelicencaRepository atividadelicencaRepository;

    @Autowired
    private AtividadelicencaMapper atividadelicencaMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAtividadelicencaMockMvc;

    private Atividadelicenca atividadelicenca;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AtividadelicencaResource atividadelicencaResource = new AtividadelicencaResource(atividadelicencaRepository, atividadelicencaMapper);
        this.restAtividadelicencaMockMvc = MockMvcBuilders.standaloneSetup(atividadelicencaResource)
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
    public static Atividadelicenca createEntity(EntityManager em) {
        Atividadelicenca atividadelicenca = new Atividadelicenca()
            .codigo(DEFAULT_CODIGO)
            .descricao(DEFAULT_DESCRICAO);
        return atividadelicenca;
    }

    @Before
    public void initTest() {
        atividadelicenca = createEntity(em);
    }

    @Test
    @Transactional
    public void createAtividadelicenca() throws Exception {
        int databaseSizeBeforeCreate = atividadelicencaRepository.findAll().size();

        // Create the Atividadelicenca
        AtividadelicencaDTO atividadelicencaDTO = atividadelicencaMapper.toDto(atividadelicenca);
        restAtividadelicencaMockMvc.perform(post("/api/atividadelicencas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(atividadelicencaDTO)))
            .andExpect(status().isCreated());

        // Validate the Atividadelicenca in the database
        List<Atividadelicenca> atividadelicencaList = atividadelicencaRepository.findAll();
        assertThat(atividadelicencaList).hasSize(databaseSizeBeforeCreate + 1);
        Atividadelicenca testAtividadelicenca = atividadelicencaList.get(atividadelicencaList.size() - 1);
        assertThat(testAtividadelicenca.getCodigo()).isEqualTo(DEFAULT_CODIGO);
        assertThat(testAtividadelicenca.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
    }

    @Test
    @Transactional
    public void createAtividadelicencaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = atividadelicencaRepository.findAll().size();

        // Create the Atividadelicenca with an existing ID
        atividadelicenca.setId(1L);
        AtividadelicencaDTO atividadelicencaDTO = atividadelicencaMapper.toDto(atividadelicenca);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAtividadelicencaMockMvc.perform(post("/api/atividadelicencas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(atividadelicencaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Atividadelicenca> atividadelicencaList = atividadelicencaRepository.findAll();
        assertThat(atividadelicencaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAtividadelicencas() throws Exception {
        // Initialize the database
        atividadelicencaRepository.saveAndFlush(atividadelicenca);

        // Get all the atividadelicencaList
        restAtividadelicencaMockMvc.perform(get("/api/atividadelicencas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(atividadelicenca.getId().intValue())))
            .andExpect(jsonPath("$.[*].codigo").value(hasItem(DEFAULT_CODIGO.toString())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())));
    }

    @Test
    @Transactional
    public void getAtividadelicenca() throws Exception {
        // Initialize the database
        atividadelicencaRepository.saveAndFlush(atividadelicenca);

        // Get the atividadelicenca
        restAtividadelicencaMockMvc.perform(get("/api/atividadelicencas/{id}", atividadelicenca.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(atividadelicenca.getId().intValue()))
            .andExpect(jsonPath("$.codigo").value(DEFAULT_CODIGO.toString()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAtividadelicenca() throws Exception {
        // Get the atividadelicenca
        restAtividadelicencaMockMvc.perform(get("/api/atividadelicencas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAtividadelicenca() throws Exception {
        // Initialize the database
        atividadelicencaRepository.saveAndFlush(atividadelicenca);
        int databaseSizeBeforeUpdate = atividadelicencaRepository.findAll().size();

        // Update the atividadelicenca
        Atividadelicenca updatedAtividadelicenca = atividadelicencaRepository.findOne(atividadelicenca.getId());
        updatedAtividadelicenca
            .codigo(UPDATED_CODIGO)
            .descricao(UPDATED_DESCRICAO);
        AtividadelicencaDTO atividadelicencaDTO = atividadelicencaMapper.toDto(updatedAtividadelicenca);

        restAtividadelicencaMockMvc.perform(put("/api/atividadelicencas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(atividadelicencaDTO)))
            .andExpect(status().isOk());

        // Validate the Atividadelicenca in the database
        List<Atividadelicenca> atividadelicencaList = atividadelicencaRepository.findAll();
        assertThat(atividadelicencaList).hasSize(databaseSizeBeforeUpdate);
        Atividadelicenca testAtividadelicenca = atividadelicencaList.get(atividadelicencaList.size() - 1);
        assertThat(testAtividadelicenca.getCodigo()).isEqualTo(UPDATED_CODIGO);
        assertThat(testAtividadelicenca.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
    }

    @Test
    @Transactional
    public void updateNonExistingAtividadelicenca() throws Exception {
        int databaseSizeBeforeUpdate = atividadelicencaRepository.findAll().size();

        // Create the Atividadelicenca
        AtividadelicencaDTO atividadelicencaDTO = atividadelicencaMapper.toDto(atividadelicenca);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAtividadelicencaMockMvc.perform(put("/api/atividadelicencas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(atividadelicencaDTO)))
            .andExpect(status().isCreated());

        // Validate the Atividadelicenca in the database
        List<Atividadelicenca> atividadelicencaList = atividadelicencaRepository.findAll();
        assertThat(atividadelicencaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAtividadelicenca() throws Exception {
        // Initialize the database
        atividadelicencaRepository.saveAndFlush(atividadelicenca);
        int databaseSizeBeforeDelete = atividadelicencaRepository.findAll().size();

        // Get the atividadelicenca
        restAtividadelicencaMockMvc.perform(delete("/api/atividadelicencas/{id}", atividadelicenca.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Atividadelicenca> atividadelicencaList = atividadelicencaRepository.findAll();
        assertThat(atividadelicencaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Atividadelicenca.class);
        Atividadelicenca atividadelicenca1 = new Atividadelicenca();
        atividadelicenca1.setId(1L);
        Atividadelicenca atividadelicenca2 = new Atividadelicenca();
        atividadelicenca2.setId(atividadelicenca1.getId());
        assertThat(atividadelicenca1).isEqualTo(atividadelicenca2);
        atividadelicenca2.setId(2L);
        assertThat(atividadelicenca1).isNotEqualTo(atividadelicenca2);
        atividadelicenca1.setId(null);
        assertThat(atividadelicenca1).isNotEqualTo(atividadelicenca2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AtividadelicencaDTO.class);
        AtividadelicencaDTO atividadelicencaDTO1 = new AtividadelicencaDTO();
        atividadelicencaDTO1.setId(1L);
        AtividadelicencaDTO atividadelicencaDTO2 = new AtividadelicencaDTO();
        assertThat(atividadelicencaDTO1).isNotEqualTo(atividadelicencaDTO2);
        atividadelicencaDTO2.setId(atividadelicencaDTO1.getId());
        assertThat(atividadelicencaDTO1).isEqualTo(atividadelicencaDTO2);
        atividadelicencaDTO2.setId(2L);
        assertThat(atividadelicencaDTO1).isNotEqualTo(atividadelicencaDTO2);
        atividadelicencaDTO1.setId(null);
        assertThat(atividadelicencaDTO1).isNotEqualTo(atividadelicencaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(atividadelicencaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(atividadelicencaMapper.fromId(null)).isNull();
    }
}
