package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Referencia;
import br.com.homemade.repository.ReferenciaRepository;
import br.com.homemade.service.dto.ReferenciaDTO;
import br.com.homemade.service.mapper.ReferenciaMapper;
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
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ReferenciaResource REST controller.
 *
 * @see ReferenciaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class ReferenciaResourceIntTest {

    private static final String DEFAULT_APORTE = "AAAAAAAAAA";
    private static final String UPDATED_APORTE = "BBBBBBBBBB";

    private static final Integer DEFAULT_IDREFERENCIA = 1;
    private static final Integer UPDATED_IDREFERENCIA = 2;

    private static final String DEFAULT_MOEDA = "AAAAAAAAAA";
    private static final String UPDATED_MOEDA = "BBBBBBBBBB";

    private static final Integer DEFAULT_NREFERENCIA = 1;
    private static final Integer UPDATED_NREFERENCIA = 2;

    private static final BigDecimal DEFAULT_VALORREFERENCIA = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALORREFERENCIA = new BigDecimal(2);

    @Autowired
    private ReferenciaRepository referenciaRepository;

    @Autowired
    private ReferenciaMapper referenciaMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restReferenciaMockMvc;

    private Referencia referencia;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReferenciaResource referenciaResource = new ReferenciaResource(referenciaRepository, referenciaMapper);
        this.restReferenciaMockMvc = MockMvcBuilders.standaloneSetup(referenciaResource)
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
    public static Referencia createEntity(EntityManager em) {
        Referencia referencia = new Referencia()
            .aporte(DEFAULT_APORTE)
            .idreferencia(DEFAULT_IDREFERENCIA)
            .moeda(DEFAULT_MOEDA)
            .nreferencia(DEFAULT_NREFERENCIA)
            .valorreferencia(DEFAULT_VALORREFERENCIA);
        return referencia;
    }

    @Before
    public void initTest() {
        referencia = createEntity(em);
    }

    @Test
    @Transactional
    public void createReferencia() throws Exception {
        int databaseSizeBeforeCreate = referenciaRepository.findAll().size();

        // Create the Referencia
        ReferenciaDTO referenciaDTO = referenciaMapper.toDto(referencia);
        restReferenciaMockMvc.perform(post("/api/referencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(referenciaDTO)))
            .andExpect(status().isCreated());

        // Validate the Referencia in the database
        List<Referencia> referenciaList = referenciaRepository.findAll();
        assertThat(referenciaList).hasSize(databaseSizeBeforeCreate + 1);
        Referencia testReferencia = referenciaList.get(referenciaList.size() - 1);
        assertThat(testReferencia.getAporte()).isEqualTo(DEFAULT_APORTE);
        assertThat(testReferencia.getIdreferencia()).isEqualTo(DEFAULT_IDREFERENCIA);
        assertThat(testReferencia.getMoeda()).isEqualTo(DEFAULT_MOEDA);
        assertThat(testReferencia.getNreferencia()).isEqualTo(DEFAULT_NREFERENCIA);
        assertThat(testReferencia.getValorreferencia()).isEqualTo(DEFAULT_VALORREFERENCIA);
    }

    @Test
    @Transactional
    public void createReferenciaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = referenciaRepository.findAll().size();

        // Create the Referencia with an existing ID
        referencia.setId(1L);
        ReferenciaDTO referenciaDTO = referenciaMapper.toDto(referencia);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReferenciaMockMvc.perform(post("/api/referencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(referenciaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Referencia> referenciaList = referenciaRepository.findAll();
        assertThat(referenciaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllReferencias() throws Exception {
        // Initialize the database
        referenciaRepository.saveAndFlush(referencia);

        // Get all the referenciaList
        restReferenciaMockMvc.perform(get("/api/referencias?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(referencia.getId().intValue())))
            .andExpect(jsonPath("$.[*].aporte").value(hasItem(DEFAULT_APORTE.toString())))
            .andExpect(jsonPath("$.[*].idreferencia").value(hasItem(DEFAULT_IDREFERENCIA)))
            .andExpect(jsonPath("$.[*].moeda").value(hasItem(DEFAULT_MOEDA.toString())))
            .andExpect(jsonPath("$.[*].nreferencia").value(hasItem(DEFAULT_NREFERENCIA)))
            .andExpect(jsonPath("$.[*].valorreferencia").value(hasItem(DEFAULT_VALORREFERENCIA.intValue())));
    }

    @Test
    @Transactional
    public void getReferencia() throws Exception {
        // Initialize the database
        referenciaRepository.saveAndFlush(referencia);

        // Get the referencia
        restReferenciaMockMvc.perform(get("/api/referencias/{id}", referencia.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(referencia.getId().intValue()))
            .andExpect(jsonPath("$.aporte").value(DEFAULT_APORTE.toString()))
            .andExpect(jsonPath("$.idreferencia").value(DEFAULT_IDREFERENCIA))
            .andExpect(jsonPath("$.moeda").value(DEFAULT_MOEDA.toString()))
            .andExpect(jsonPath("$.nreferencia").value(DEFAULT_NREFERENCIA))
            .andExpect(jsonPath("$.valorreferencia").value(DEFAULT_VALORREFERENCIA.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingReferencia() throws Exception {
        // Get the referencia
        restReferenciaMockMvc.perform(get("/api/referencias/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReferencia() throws Exception {
        // Initialize the database
        referenciaRepository.saveAndFlush(referencia);
        int databaseSizeBeforeUpdate = referenciaRepository.findAll().size();

        // Update the referencia
        Referencia updatedReferencia = referenciaRepository.findOne(referencia.getId());
        updatedReferencia
            .aporte(UPDATED_APORTE)
            .idreferencia(UPDATED_IDREFERENCIA)
            .moeda(UPDATED_MOEDA)
            .nreferencia(UPDATED_NREFERENCIA)
            .valorreferencia(UPDATED_VALORREFERENCIA);
        ReferenciaDTO referenciaDTO = referenciaMapper.toDto(updatedReferencia);

        restReferenciaMockMvc.perform(put("/api/referencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(referenciaDTO)))
            .andExpect(status().isOk());

        // Validate the Referencia in the database
        List<Referencia> referenciaList = referenciaRepository.findAll();
        assertThat(referenciaList).hasSize(databaseSizeBeforeUpdate);
        Referencia testReferencia = referenciaList.get(referenciaList.size() - 1);
        assertThat(testReferencia.getAporte()).isEqualTo(UPDATED_APORTE);
        assertThat(testReferencia.getIdreferencia()).isEqualTo(UPDATED_IDREFERENCIA);
        assertThat(testReferencia.getMoeda()).isEqualTo(UPDATED_MOEDA);
        assertThat(testReferencia.getNreferencia()).isEqualTo(UPDATED_NREFERENCIA);
        assertThat(testReferencia.getValorreferencia()).isEqualTo(UPDATED_VALORREFERENCIA);
    }

    @Test
    @Transactional
    public void updateNonExistingReferencia() throws Exception {
        int databaseSizeBeforeUpdate = referenciaRepository.findAll().size();

        // Create the Referencia
        ReferenciaDTO referenciaDTO = referenciaMapper.toDto(referencia);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restReferenciaMockMvc.perform(put("/api/referencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(referenciaDTO)))
            .andExpect(status().isCreated());

        // Validate the Referencia in the database
        List<Referencia> referenciaList = referenciaRepository.findAll();
        assertThat(referenciaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteReferencia() throws Exception {
        // Initialize the database
        referenciaRepository.saveAndFlush(referencia);
        int databaseSizeBeforeDelete = referenciaRepository.findAll().size();

        // Get the referencia
        restReferenciaMockMvc.perform(delete("/api/referencias/{id}", referencia.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Referencia> referenciaList = referenciaRepository.findAll();
        assertThat(referenciaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Referencia.class);
        Referencia referencia1 = new Referencia();
        referencia1.setId(1L);
        Referencia referencia2 = new Referencia();
        referencia2.setId(referencia1.getId());
        assertThat(referencia1).isEqualTo(referencia2);
        referencia2.setId(2L);
        assertThat(referencia1).isNotEqualTo(referencia2);
        referencia1.setId(null);
        assertThat(referencia1).isNotEqualTo(referencia2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReferenciaDTO.class);
        ReferenciaDTO referenciaDTO1 = new ReferenciaDTO();
        referenciaDTO1.setId(1L);
        ReferenciaDTO referenciaDTO2 = new ReferenciaDTO();
        assertThat(referenciaDTO1).isNotEqualTo(referenciaDTO2);
        referenciaDTO2.setId(referenciaDTO1.getId());
        assertThat(referenciaDTO1).isEqualTo(referenciaDTO2);
        referenciaDTO2.setId(2L);
        assertThat(referenciaDTO1).isNotEqualTo(referenciaDTO2);
        referenciaDTO1.setId(null);
        assertThat(referenciaDTO1).isNotEqualTo(referenciaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(referenciaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(referenciaMapper.fromId(null)).isNull();
    }
}
