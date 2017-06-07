package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Referenciacontrato;
import br.com.homemade.repository.ReferenciacontratoRepository;
import br.com.homemade.service.dto.ReferenciacontratoDTO;
import br.com.homemade.service.mapper.ReferenciacontratoMapper;
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
 * Test class for the ReferenciacontratoResource REST controller.
 *
 * @see ReferenciacontratoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class ReferenciacontratoResourceIntTest {

    private static final String DEFAULT_APORTE = "AAAAAAAAAA";
    private static final String UPDATED_APORTE = "BBBBBBBBBB";

    private static final String DEFAULT_MOEDA = "AAAAAAAAAA";
    private static final String UPDATED_MOEDA = "BBBBBBBBBB";

    private static final Integer DEFAULT_IDREFERENCIACONTRATO = 1;
    private static final Integer UPDATED_IDREFERENCIACONTRATO = 2;

    private static final Integer DEFAULT_NREFERENCIA = 1;
    private static final Integer UPDATED_NREFERENCIA = 2;

    private static final BigDecimal DEFAULT_VALORREFERENCIA = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALORREFERENCIA = new BigDecimal(2);

    @Autowired
    private ReferenciacontratoRepository referenciacontratoRepository;

    @Autowired
    private ReferenciacontratoMapper referenciacontratoMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restReferenciacontratoMockMvc;

    private Referenciacontrato referenciacontrato;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReferenciacontratoResource referenciacontratoResource = new ReferenciacontratoResource(referenciacontratoRepository, referenciacontratoMapper);
        this.restReferenciacontratoMockMvc = MockMvcBuilders.standaloneSetup(referenciacontratoResource)
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
    public static Referenciacontrato createEntity(EntityManager em) {
        Referenciacontrato referenciacontrato = new Referenciacontrato()
            .aporte(DEFAULT_APORTE)
            .moeda(DEFAULT_MOEDA)
            .idreferenciacontrato(DEFAULT_IDREFERENCIACONTRATO)
            .nreferencia(DEFAULT_NREFERENCIA)
            .valorreferencia(DEFAULT_VALORREFERENCIA);
        return referenciacontrato;
    }

    @Before
    public void initTest() {
        referenciacontrato = createEntity(em);
    }

    @Test
    @Transactional
    public void createReferenciacontrato() throws Exception {
        int databaseSizeBeforeCreate = referenciacontratoRepository.findAll().size();

        // Create the Referenciacontrato
        ReferenciacontratoDTO referenciacontratoDTO = referenciacontratoMapper.toDto(referenciacontrato);
        restReferenciacontratoMockMvc.perform(post("/api/referenciacontratoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(referenciacontratoDTO)))
            .andExpect(status().isCreated());

        // Validate the Referenciacontrato in the database
        List<Referenciacontrato> referenciacontratoList = referenciacontratoRepository.findAll();
        assertThat(referenciacontratoList).hasSize(databaseSizeBeforeCreate + 1);
        Referenciacontrato testReferenciacontrato = referenciacontratoList.get(referenciacontratoList.size() - 1);
        assertThat(testReferenciacontrato.getAporte()).isEqualTo(DEFAULT_APORTE);
        assertThat(testReferenciacontrato.getMoeda()).isEqualTo(DEFAULT_MOEDA);
        assertThat(testReferenciacontrato.getIdreferenciacontrato()).isEqualTo(DEFAULT_IDREFERENCIACONTRATO);
        assertThat(testReferenciacontrato.getNreferencia()).isEqualTo(DEFAULT_NREFERENCIA);
        assertThat(testReferenciacontrato.getValorreferencia()).isEqualTo(DEFAULT_VALORREFERENCIA);
    }

    @Test
    @Transactional
    public void createReferenciacontratoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = referenciacontratoRepository.findAll().size();

        // Create the Referenciacontrato with an existing ID
        referenciacontrato.setId(1L);
        ReferenciacontratoDTO referenciacontratoDTO = referenciacontratoMapper.toDto(referenciacontrato);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReferenciacontratoMockMvc.perform(post("/api/referenciacontratoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(referenciacontratoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Referenciacontrato> referenciacontratoList = referenciacontratoRepository.findAll();
        assertThat(referenciacontratoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllReferenciacontratoes() throws Exception {
        // Initialize the database
        referenciacontratoRepository.saveAndFlush(referenciacontrato);

        // Get all the referenciacontratoList
        restReferenciacontratoMockMvc.perform(get("/api/referenciacontratoes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(referenciacontrato.getId().intValue())))
            .andExpect(jsonPath("$.[*].aporte").value(hasItem(DEFAULT_APORTE.toString())))
            .andExpect(jsonPath("$.[*].moeda").value(hasItem(DEFAULT_MOEDA.toString())))
            .andExpect(jsonPath("$.[*].idreferenciacontrato").value(hasItem(DEFAULT_IDREFERENCIACONTRATO)))
            .andExpect(jsonPath("$.[*].nreferencia").value(hasItem(DEFAULT_NREFERENCIA)))
            .andExpect(jsonPath("$.[*].valorreferencia").value(hasItem(DEFAULT_VALORREFERENCIA.intValue())));
    }

    @Test
    @Transactional
    public void getReferenciacontrato() throws Exception {
        // Initialize the database
        referenciacontratoRepository.saveAndFlush(referenciacontrato);

        // Get the referenciacontrato
        restReferenciacontratoMockMvc.perform(get("/api/referenciacontratoes/{id}", referenciacontrato.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(referenciacontrato.getId().intValue()))
            .andExpect(jsonPath("$.aporte").value(DEFAULT_APORTE.toString()))
            .andExpect(jsonPath("$.moeda").value(DEFAULT_MOEDA.toString()))
            .andExpect(jsonPath("$.idreferenciacontrato").value(DEFAULT_IDREFERENCIACONTRATO))
            .andExpect(jsonPath("$.nreferencia").value(DEFAULT_NREFERENCIA))
            .andExpect(jsonPath("$.valorreferencia").value(DEFAULT_VALORREFERENCIA.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingReferenciacontrato() throws Exception {
        // Get the referenciacontrato
        restReferenciacontratoMockMvc.perform(get("/api/referenciacontratoes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReferenciacontrato() throws Exception {
        // Initialize the database
        referenciacontratoRepository.saveAndFlush(referenciacontrato);
        int databaseSizeBeforeUpdate = referenciacontratoRepository.findAll().size();

        // Update the referenciacontrato
        Referenciacontrato updatedReferenciacontrato = referenciacontratoRepository.findOne(referenciacontrato.getId());
        updatedReferenciacontrato
            .aporte(UPDATED_APORTE)
            .moeda(UPDATED_MOEDA)
            .idreferenciacontrato(UPDATED_IDREFERENCIACONTRATO)
            .nreferencia(UPDATED_NREFERENCIA)
            .valorreferencia(UPDATED_VALORREFERENCIA);
        ReferenciacontratoDTO referenciacontratoDTO = referenciacontratoMapper.toDto(updatedReferenciacontrato);

        restReferenciacontratoMockMvc.perform(put("/api/referenciacontratoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(referenciacontratoDTO)))
            .andExpect(status().isOk());

        // Validate the Referenciacontrato in the database
        List<Referenciacontrato> referenciacontratoList = referenciacontratoRepository.findAll();
        assertThat(referenciacontratoList).hasSize(databaseSizeBeforeUpdate);
        Referenciacontrato testReferenciacontrato = referenciacontratoList.get(referenciacontratoList.size() - 1);
        assertThat(testReferenciacontrato.getAporte()).isEqualTo(UPDATED_APORTE);
        assertThat(testReferenciacontrato.getMoeda()).isEqualTo(UPDATED_MOEDA);
        assertThat(testReferenciacontrato.getIdreferenciacontrato()).isEqualTo(UPDATED_IDREFERENCIACONTRATO);
        assertThat(testReferenciacontrato.getNreferencia()).isEqualTo(UPDATED_NREFERENCIA);
        assertThat(testReferenciacontrato.getValorreferencia()).isEqualTo(UPDATED_VALORREFERENCIA);
    }

    @Test
    @Transactional
    public void updateNonExistingReferenciacontrato() throws Exception {
        int databaseSizeBeforeUpdate = referenciacontratoRepository.findAll().size();

        // Create the Referenciacontrato
        ReferenciacontratoDTO referenciacontratoDTO = referenciacontratoMapper.toDto(referenciacontrato);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restReferenciacontratoMockMvc.perform(put("/api/referenciacontratoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(referenciacontratoDTO)))
            .andExpect(status().isCreated());

        // Validate the Referenciacontrato in the database
        List<Referenciacontrato> referenciacontratoList = referenciacontratoRepository.findAll();
        assertThat(referenciacontratoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteReferenciacontrato() throws Exception {
        // Initialize the database
        referenciacontratoRepository.saveAndFlush(referenciacontrato);
        int databaseSizeBeforeDelete = referenciacontratoRepository.findAll().size();

        // Get the referenciacontrato
        restReferenciacontratoMockMvc.perform(delete("/api/referenciacontratoes/{id}", referenciacontrato.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Referenciacontrato> referenciacontratoList = referenciacontratoRepository.findAll();
        assertThat(referenciacontratoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Referenciacontrato.class);
        Referenciacontrato referenciacontrato1 = new Referenciacontrato();
        referenciacontrato1.setId(1L);
        Referenciacontrato referenciacontrato2 = new Referenciacontrato();
        referenciacontrato2.setId(referenciacontrato1.getId());
        assertThat(referenciacontrato1).isEqualTo(referenciacontrato2);
        referenciacontrato2.setId(2L);
        assertThat(referenciacontrato1).isNotEqualTo(referenciacontrato2);
        referenciacontrato1.setId(null);
        assertThat(referenciacontrato1).isNotEqualTo(referenciacontrato2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReferenciacontratoDTO.class);
        ReferenciacontratoDTO referenciacontratoDTO1 = new ReferenciacontratoDTO();
        referenciacontratoDTO1.setId(1L);
        ReferenciacontratoDTO referenciacontratoDTO2 = new ReferenciacontratoDTO();
        assertThat(referenciacontratoDTO1).isNotEqualTo(referenciacontratoDTO2);
        referenciacontratoDTO2.setId(referenciacontratoDTO1.getId());
        assertThat(referenciacontratoDTO1).isEqualTo(referenciacontratoDTO2);
        referenciacontratoDTO2.setId(2L);
        assertThat(referenciacontratoDTO1).isNotEqualTo(referenciacontratoDTO2);
        referenciacontratoDTO1.setId(null);
        assertThat(referenciacontratoDTO1).isNotEqualTo(referenciacontratoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(referenciacontratoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(referenciacontratoMapper.fromId(null)).isNull();
    }
}
