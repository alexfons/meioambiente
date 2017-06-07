package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Contratoprojeto;
import br.com.homemade.repository.ContratoprojetoRepository;
import br.com.homemade.service.dto.ContratoprojetoDTO;
import br.com.homemade.service.mapper.ContratoprojetoMapper;
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
 * Test class for the ContratoprojetoResource REST controller.
 *
 * @see ContratoprojetoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class ContratoprojetoResourceIntTest {

    private static final String DEFAULT_TIPO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO = "BBBBBBBBBB";

    @Autowired
    private ContratoprojetoRepository contratoprojetoRepository;

    @Autowired
    private ContratoprojetoMapper contratoprojetoMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restContratoprojetoMockMvc;

    private Contratoprojeto contratoprojeto;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ContratoprojetoResource contratoprojetoResource = new ContratoprojetoResource(contratoprojetoRepository, contratoprojetoMapper);
        this.restContratoprojetoMockMvc = MockMvcBuilders.standaloneSetup(contratoprojetoResource)
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
    public static Contratoprojeto createEntity(EntityManager em) {
        Contratoprojeto contratoprojeto = new Contratoprojeto()
            .tipo(DEFAULT_TIPO);
        return contratoprojeto;
    }

    @Before
    public void initTest() {
        contratoprojeto = createEntity(em);
    }

    @Test
    @Transactional
    public void createContratoprojeto() throws Exception {
        int databaseSizeBeforeCreate = contratoprojetoRepository.findAll().size();

        // Create the Contratoprojeto
        ContratoprojetoDTO contratoprojetoDTO = contratoprojetoMapper.toDto(contratoprojeto);
        restContratoprojetoMockMvc.perform(post("/api/contratoprojetos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratoprojetoDTO)))
            .andExpect(status().isCreated());

        // Validate the Contratoprojeto in the database
        List<Contratoprojeto> contratoprojetoList = contratoprojetoRepository.findAll();
        assertThat(contratoprojetoList).hasSize(databaseSizeBeforeCreate + 1);
        Contratoprojeto testContratoprojeto = contratoprojetoList.get(contratoprojetoList.size() - 1);
        assertThat(testContratoprojeto.getTipo()).isEqualTo(DEFAULT_TIPO);
    }

    @Test
    @Transactional
    public void createContratoprojetoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = contratoprojetoRepository.findAll().size();

        // Create the Contratoprojeto with an existing ID
        contratoprojeto.setId(1L);
        ContratoprojetoDTO contratoprojetoDTO = contratoprojetoMapper.toDto(contratoprojeto);

        // An entity with an existing ID cannot be created, so this API call must fail
        restContratoprojetoMockMvc.perform(post("/api/contratoprojetos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratoprojetoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Contratoprojeto> contratoprojetoList = contratoprojetoRepository.findAll();
        assertThat(contratoprojetoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllContratoprojetos() throws Exception {
        // Initialize the database
        contratoprojetoRepository.saveAndFlush(contratoprojeto);

        // Get all the contratoprojetoList
        restContratoprojetoMockMvc.perform(get("/api/contratoprojetos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contratoprojeto.getId().intValue())))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO.toString())));
    }

    @Test
    @Transactional
    public void getContratoprojeto() throws Exception {
        // Initialize the database
        contratoprojetoRepository.saveAndFlush(contratoprojeto);

        // Get the contratoprojeto
        restContratoprojetoMockMvc.perform(get("/api/contratoprojetos/{id}", contratoprojeto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(contratoprojeto.getId().intValue()))
            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingContratoprojeto() throws Exception {
        // Get the contratoprojeto
        restContratoprojetoMockMvc.perform(get("/api/contratoprojetos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateContratoprojeto() throws Exception {
        // Initialize the database
        contratoprojetoRepository.saveAndFlush(contratoprojeto);
        int databaseSizeBeforeUpdate = contratoprojetoRepository.findAll().size();

        // Update the contratoprojeto
        Contratoprojeto updatedContratoprojeto = contratoprojetoRepository.findOne(contratoprojeto.getId());
        updatedContratoprojeto
            .tipo(UPDATED_TIPO);
        ContratoprojetoDTO contratoprojetoDTO = contratoprojetoMapper.toDto(updatedContratoprojeto);

        restContratoprojetoMockMvc.perform(put("/api/contratoprojetos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratoprojetoDTO)))
            .andExpect(status().isOk());

        // Validate the Contratoprojeto in the database
        List<Contratoprojeto> contratoprojetoList = contratoprojetoRepository.findAll();
        assertThat(contratoprojetoList).hasSize(databaseSizeBeforeUpdate);
        Contratoprojeto testContratoprojeto = contratoprojetoList.get(contratoprojetoList.size() - 1);
        assertThat(testContratoprojeto.getTipo()).isEqualTo(UPDATED_TIPO);
    }

    @Test
    @Transactional
    public void updateNonExistingContratoprojeto() throws Exception {
        int databaseSizeBeforeUpdate = contratoprojetoRepository.findAll().size();

        // Create the Contratoprojeto
        ContratoprojetoDTO contratoprojetoDTO = contratoprojetoMapper.toDto(contratoprojeto);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restContratoprojetoMockMvc.perform(put("/api/contratoprojetos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratoprojetoDTO)))
            .andExpect(status().isCreated());

        // Validate the Contratoprojeto in the database
        List<Contratoprojeto> contratoprojetoList = contratoprojetoRepository.findAll();
        assertThat(contratoprojetoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteContratoprojeto() throws Exception {
        // Initialize the database
        contratoprojetoRepository.saveAndFlush(contratoprojeto);
        int databaseSizeBeforeDelete = contratoprojetoRepository.findAll().size();

        // Get the contratoprojeto
        restContratoprojetoMockMvc.perform(delete("/api/contratoprojetos/{id}", contratoprojeto.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Contratoprojeto> contratoprojetoList = contratoprojetoRepository.findAll();
        assertThat(contratoprojetoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Contratoprojeto.class);
        Contratoprojeto contratoprojeto1 = new Contratoprojeto();
        contratoprojeto1.setId(1L);
        Contratoprojeto contratoprojeto2 = new Contratoprojeto();
        contratoprojeto2.setId(contratoprojeto1.getId());
        assertThat(contratoprojeto1).isEqualTo(contratoprojeto2);
        contratoprojeto2.setId(2L);
        assertThat(contratoprojeto1).isNotEqualTo(contratoprojeto2);
        contratoprojeto1.setId(null);
        assertThat(contratoprojeto1).isNotEqualTo(contratoprojeto2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContratoprojetoDTO.class);
        ContratoprojetoDTO contratoprojetoDTO1 = new ContratoprojetoDTO();
        contratoprojetoDTO1.setId(1L);
        ContratoprojetoDTO contratoprojetoDTO2 = new ContratoprojetoDTO();
        assertThat(contratoprojetoDTO1).isNotEqualTo(contratoprojetoDTO2);
        contratoprojetoDTO2.setId(contratoprojetoDTO1.getId());
        assertThat(contratoprojetoDTO1).isEqualTo(contratoprojetoDTO2);
        contratoprojetoDTO2.setId(2L);
        assertThat(contratoprojetoDTO1).isNotEqualTo(contratoprojetoDTO2);
        contratoprojetoDTO1.setId(null);
        assertThat(contratoprojetoDTO1).isNotEqualTo(contratoprojetoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(contratoprojetoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(contratoprojetoMapper.fromId(null)).isNull();
    }
}
