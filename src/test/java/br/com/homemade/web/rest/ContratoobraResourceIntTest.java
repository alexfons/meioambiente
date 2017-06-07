package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Contratoobra;
import br.com.homemade.repository.ContratoobraRepository;
import br.com.homemade.service.dto.ContratoobraDTO;
import br.com.homemade.service.mapper.ContratoobraMapper;
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
 * Test class for the ContratoobraResource REST controller.
 *
 * @see ContratoobraResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class ContratoobraResourceIntTest {

    private static final String DEFAULT_TIPO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO = "BBBBBBBBBB";

    @Autowired
    private ContratoobraRepository contratoobraRepository;

    @Autowired
    private ContratoobraMapper contratoobraMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restContratoobraMockMvc;

    private Contratoobra contratoobra;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ContratoobraResource contratoobraResource = new ContratoobraResource(contratoobraRepository, contratoobraMapper);
        this.restContratoobraMockMvc = MockMvcBuilders.standaloneSetup(contratoobraResource)
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
    public static Contratoobra createEntity(EntityManager em) {
        Contratoobra contratoobra = new Contratoobra()
            .tipo(DEFAULT_TIPO);
        return contratoobra;
    }

    @Before
    public void initTest() {
        contratoobra = createEntity(em);
    }

    @Test
    @Transactional
    public void createContratoobra() throws Exception {
        int databaseSizeBeforeCreate = contratoobraRepository.findAll().size();

        // Create the Contratoobra
        ContratoobraDTO contratoobraDTO = contratoobraMapper.toDto(contratoobra);
        restContratoobraMockMvc.perform(post("/api/contratoobras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratoobraDTO)))
            .andExpect(status().isCreated());

        // Validate the Contratoobra in the database
        List<Contratoobra> contratoobraList = contratoobraRepository.findAll();
        assertThat(contratoobraList).hasSize(databaseSizeBeforeCreate + 1);
        Contratoobra testContratoobra = contratoobraList.get(contratoobraList.size() - 1);
        assertThat(testContratoobra.getTipo()).isEqualTo(DEFAULT_TIPO);
    }

    @Test
    @Transactional
    public void createContratoobraWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = contratoobraRepository.findAll().size();

        // Create the Contratoobra with an existing ID
        contratoobra.setId(1L);
        ContratoobraDTO contratoobraDTO = contratoobraMapper.toDto(contratoobra);

        // An entity with an existing ID cannot be created, so this API call must fail
        restContratoobraMockMvc.perform(post("/api/contratoobras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratoobraDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Contratoobra> contratoobraList = contratoobraRepository.findAll();
        assertThat(contratoobraList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllContratoobras() throws Exception {
        // Initialize the database
        contratoobraRepository.saveAndFlush(contratoobra);

        // Get all the contratoobraList
        restContratoobraMockMvc.perform(get("/api/contratoobras?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contratoobra.getId().intValue())))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO.toString())));
    }

    @Test
    @Transactional
    public void getContratoobra() throws Exception {
        // Initialize the database
        contratoobraRepository.saveAndFlush(contratoobra);

        // Get the contratoobra
        restContratoobraMockMvc.perform(get("/api/contratoobras/{id}", contratoobra.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(contratoobra.getId().intValue()))
            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingContratoobra() throws Exception {
        // Get the contratoobra
        restContratoobraMockMvc.perform(get("/api/contratoobras/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateContratoobra() throws Exception {
        // Initialize the database
        contratoobraRepository.saveAndFlush(contratoobra);
        int databaseSizeBeforeUpdate = contratoobraRepository.findAll().size();

        // Update the contratoobra
        Contratoobra updatedContratoobra = contratoobraRepository.findOne(contratoobra.getId());
        updatedContratoobra
            .tipo(UPDATED_TIPO);
        ContratoobraDTO contratoobraDTO = contratoobraMapper.toDto(updatedContratoobra);

        restContratoobraMockMvc.perform(put("/api/contratoobras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratoobraDTO)))
            .andExpect(status().isOk());

        // Validate the Contratoobra in the database
        List<Contratoobra> contratoobraList = contratoobraRepository.findAll();
        assertThat(contratoobraList).hasSize(databaseSizeBeforeUpdate);
        Contratoobra testContratoobra = contratoobraList.get(contratoobraList.size() - 1);
        assertThat(testContratoobra.getTipo()).isEqualTo(UPDATED_TIPO);
    }

    @Test
    @Transactional
    public void updateNonExistingContratoobra() throws Exception {
        int databaseSizeBeforeUpdate = contratoobraRepository.findAll().size();

        // Create the Contratoobra
        ContratoobraDTO contratoobraDTO = contratoobraMapper.toDto(contratoobra);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restContratoobraMockMvc.perform(put("/api/contratoobras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratoobraDTO)))
            .andExpect(status().isCreated());

        // Validate the Contratoobra in the database
        List<Contratoobra> contratoobraList = contratoobraRepository.findAll();
        assertThat(contratoobraList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteContratoobra() throws Exception {
        // Initialize the database
        contratoobraRepository.saveAndFlush(contratoobra);
        int databaseSizeBeforeDelete = contratoobraRepository.findAll().size();

        // Get the contratoobra
        restContratoobraMockMvc.perform(delete("/api/contratoobras/{id}", contratoobra.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Contratoobra> contratoobraList = contratoobraRepository.findAll();
        assertThat(contratoobraList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Contratoobra.class);
        Contratoobra contratoobra1 = new Contratoobra();
        contratoobra1.setId(1L);
        Contratoobra contratoobra2 = new Contratoobra();
        contratoobra2.setId(contratoobra1.getId());
        assertThat(contratoobra1).isEqualTo(contratoobra2);
        contratoobra2.setId(2L);
        assertThat(contratoobra1).isNotEqualTo(contratoobra2);
        contratoobra1.setId(null);
        assertThat(contratoobra1).isNotEqualTo(contratoobra2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContratoobraDTO.class);
        ContratoobraDTO contratoobraDTO1 = new ContratoobraDTO();
        contratoobraDTO1.setId(1L);
        ContratoobraDTO contratoobraDTO2 = new ContratoobraDTO();
        assertThat(contratoobraDTO1).isNotEqualTo(contratoobraDTO2);
        contratoobraDTO2.setId(contratoobraDTO1.getId());
        assertThat(contratoobraDTO1).isEqualTo(contratoobraDTO2);
        contratoobraDTO2.setId(2L);
        assertThat(contratoobraDTO1).isNotEqualTo(contratoobraDTO2);
        contratoobraDTO1.setId(null);
        assertThat(contratoobraDTO1).isNotEqualTo(contratoobraDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(contratoobraMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(contratoobraMapper.fromId(null)).isNull();
    }
}
