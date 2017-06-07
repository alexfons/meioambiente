package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Custosconcorrentes;
import br.com.homemade.repository.CustosconcorrentesRepository;
import br.com.homemade.service.dto.CustosconcorrentesDTO;
import br.com.homemade.service.mapper.CustosconcorrentesMapper;
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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static br.com.homemade.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CustosconcorrentesResource REST controller.
 *
 * @see CustosconcorrentesResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class CustosconcorrentesResourceIntTest {

    private static final ZonedDateTime DEFAULT_DATAINICIO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAINICIO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_NSOLICITACAO = 1;
    private static final Integer UPDATED_NSOLICITACAO = 2;

    private static final BigDecimal DEFAULT_VALORUS = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALORUS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VALORPAGOREAIS = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALORPAGOREAIS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TAXA = new BigDecimal(1);
    private static final BigDecimal UPDATED_TAXA = new BigDecimal(2);

    @Autowired
    private CustosconcorrentesRepository custosconcorrentesRepository;

    @Autowired
    private CustosconcorrentesMapper custosconcorrentesMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCustosconcorrentesMockMvc;

    private Custosconcorrentes custosconcorrentes;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CustosconcorrentesResource custosconcorrentesResource = new CustosconcorrentesResource(custosconcorrentesRepository, custosconcorrentesMapper);
        this.restCustosconcorrentesMockMvc = MockMvcBuilders.standaloneSetup(custosconcorrentesResource)
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
    public static Custosconcorrentes createEntity(EntityManager em) {
        Custosconcorrentes custosconcorrentes = new Custosconcorrentes()
            .datainicio(DEFAULT_DATAINICIO)
            .nsolicitacao(DEFAULT_NSOLICITACAO)
            .valorus(DEFAULT_VALORUS)
            .valorpagoreais(DEFAULT_VALORPAGOREAIS)
            .taxa(DEFAULT_TAXA);
        return custosconcorrentes;
    }

    @Before
    public void initTest() {
        custosconcorrentes = createEntity(em);
    }

    @Test
    @Transactional
    public void createCustosconcorrentes() throws Exception {
        int databaseSizeBeforeCreate = custosconcorrentesRepository.findAll().size();

        // Create the Custosconcorrentes
        CustosconcorrentesDTO custosconcorrentesDTO = custosconcorrentesMapper.toDto(custosconcorrentes);
        restCustosconcorrentesMockMvc.perform(post("/api/custosconcorrentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(custosconcorrentesDTO)))
            .andExpect(status().isCreated());

        // Validate the Custosconcorrentes in the database
        List<Custosconcorrentes> custosconcorrentesList = custosconcorrentesRepository.findAll();
        assertThat(custosconcorrentesList).hasSize(databaseSizeBeforeCreate + 1);
        Custosconcorrentes testCustosconcorrentes = custosconcorrentesList.get(custosconcorrentesList.size() - 1);
        assertThat(testCustosconcorrentes.getDatainicio()).isEqualTo(DEFAULT_DATAINICIO);
        assertThat(testCustosconcorrentes.getNsolicitacao()).isEqualTo(DEFAULT_NSOLICITACAO);
        assertThat(testCustosconcorrentes.getValorus()).isEqualTo(DEFAULT_VALORUS);
        assertThat(testCustosconcorrentes.getValorpagoreais()).isEqualTo(DEFAULT_VALORPAGOREAIS);
        assertThat(testCustosconcorrentes.getTaxa()).isEqualTo(DEFAULT_TAXA);
    }

    @Test
    @Transactional
    public void createCustosconcorrentesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = custosconcorrentesRepository.findAll().size();

        // Create the Custosconcorrentes with an existing ID
        custosconcorrentes.setId(1L);
        CustosconcorrentesDTO custosconcorrentesDTO = custosconcorrentesMapper.toDto(custosconcorrentes);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustosconcorrentesMockMvc.perform(post("/api/custosconcorrentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(custosconcorrentesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Custosconcorrentes> custosconcorrentesList = custosconcorrentesRepository.findAll();
        assertThat(custosconcorrentesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCustosconcorrentes() throws Exception {
        // Initialize the database
        custosconcorrentesRepository.saveAndFlush(custosconcorrentes);

        // Get all the custosconcorrentesList
        restCustosconcorrentesMockMvc.perform(get("/api/custosconcorrentes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(custosconcorrentes.getId().intValue())))
            .andExpect(jsonPath("$.[*].datainicio").value(hasItem(sameInstant(DEFAULT_DATAINICIO))))
            .andExpect(jsonPath("$.[*].nsolicitacao").value(hasItem(DEFAULT_NSOLICITACAO)))
            .andExpect(jsonPath("$.[*].valorus").value(hasItem(DEFAULT_VALORUS.intValue())))
            .andExpect(jsonPath("$.[*].valorpagoreais").value(hasItem(DEFAULT_VALORPAGOREAIS.intValue())))
            .andExpect(jsonPath("$.[*].taxa").value(hasItem(DEFAULT_TAXA.intValue())));
    }

    @Test
    @Transactional
    public void getCustosconcorrentes() throws Exception {
        // Initialize the database
        custosconcorrentesRepository.saveAndFlush(custosconcorrentes);

        // Get the custosconcorrentes
        restCustosconcorrentesMockMvc.perform(get("/api/custosconcorrentes/{id}", custosconcorrentes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(custosconcorrentes.getId().intValue()))
            .andExpect(jsonPath("$.datainicio").value(sameInstant(DEFAULT_DATAINICIO)))
            .andExpect(jsonPath("$.nsolicitacao").value(DEFAULT_NSOLICITACAO))
            .andExpect(jsonPath("$.valorus").value(DEFAULT_VALORUS.intValue()))
            .andExpect(jsonPath("$.valorpagoreais").value(DEFAULT_VALORPAGOREAIS.intValue()))
            .andExpect(jsonPath("$.taxa").value(DEFAULT_TAXA.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCustosconcorrentes() throws Exception {
        // Get the custosconcorrentes
        restCustosconcorrentesMockMvc.perform(get("/api/custosconcorrentes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCustosconcorrentes() throws Exception {
        // Initialize the database
        custosconcorrentesRepository.saveAndFlush(custosconcorrentes);
        int databaseSizeBeforeUpdate = custosconcorrentesRepository.findAll().size();

        // Update the custosconcorrentes
        Custosconcorrentes updatedCustosconcorrentes = custosconcorrentesRepository.findOne(custosconcorrentes.getId());
        updatedCustosconcorrentes
            .datainicio(UPDATED_DATAINICIO)
            .nsolicitacao(UPDATED_NSOLICITACAO)
            .valorus(UPDATED_VALORUS)
            .valorpagoreais(UPDATED_VALORPAGOREAIS)
            .taxa(UPDATED_TAXA);
        CustosconcorrentesDTO custosconcorrentesDTO = custosconcorrentesMapper.toDto(updatedCustosconcorrentes);

        restCustosconcorrentesMockMvc.perform(put("/api/custosconcorrentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(custosconcorrentesDTO)))
            .andExpect(status().isOk());

        // Validate the Custosconcorrentes in the database
        List<Custosconcorrentes> custosconcorrentesList = custosconcorrentesRepository.findAll();
        assertThat(custosconcorrentesList).hasSize(databaseSizeBeforeUpdate);
        Custosconcorrentes testCustosconcorrentes = custosconcorrentesList.get(custosconcorrentesList.size() - 1);
        assertThat(testCustosconcorrentes.getDatainicio()).isEqualTo(UPDATED_DATAINICIO);
        assertThat(testCustosconcorrentes.getNsolicitacao()).isEqualTo(UPDATED_NSOLICITACAO);
        assertThat(testCustosconcorrentes.getValorus()).isEqualTo(UPDATED_VALORUS);
        assertThat(testCustosconcorrentes.getValorpagoreais()).isEqualTo(UPDATED_VALORPAGOREAIS);
        assertThat(testCustosconcorrentes.getTaxa()).isEqualTo(UPDATED_TAXA);
    }

    @Test
    @Transactional
    public void updateNonExistingCustosconcorrentes() throws Exception {
        int databaseSizeBeforeUpdate = custosconcorrentesRepository.findAll().size();

        // Create the Custosconcorrentes
        CustosconcorrentesDTO custosconcorrentesDTO = custosconcorrentesMapper.toDto(custosconcorrentes);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCustosconcorrentesMockMvc.perform(put("/api/custosconcorrentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(custosconcorrentesDTO)))
            .andExpect(status().isCreated());

        // Validate the Custosconcorrentes in the database
        List<Custosconcorrentes> custosconcorrentesList = custosconcorrentesRepository.findAll();
        assertThat(custosconcorrentesList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCustosconcorrentes() throws Exception {
        // Initialize the database
        custosconcorrentesRepository.saveAndFlush(custosconcorrentes);
        int databaseSizeBeforeDelete = custosconcorrentesRepository.findAll().size();

        // Get the custosconcorrentes
        restCustosconcorrentesMockMvc.perform(delete("/api/custosconcorrentes/{id}", custosconcorrentes.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Custosconcorrentes> custosconcorrentesList = custosconcorrentesRepository.findAll();
        assertThat(custosconcorrentesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Custosconcorrentes.class);
        Custosconcorrentes custosconcorrentes1 = new Custosconcorrentes();
        custosconcorrentes1.setId(1L);
        Custosconcorrentes custosconcorrentes2 = new Custosconcorrentes();
        custosconcorrentes2.setId(custosconcorrentes1.getId());
        assertThat(custosconcorrentes1).isEqualTo(custosconcorrentes2);
        custosconcorrentes2.setId(2L);
        assertThat(custosconcorrentes1).isNotEqualTo(custosconcorrentes2);
        custosconcorrentes1.setId(null);
        assertThat(custosconcorrentes1).isNotEqualTo(custosconcorrentes2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustosconcorrentesDTO.class);
        CustosconcorrentesDTO custosconcorrentesDTO1 = new CustosconcorrentesDTO();
        custosconcorrentesDTO1.setId(1L);
        CustosconcorrentesDTO custosconcorrentesDTO2 = new CustosconcorrentesDTO();
        assertThat(custosconcorrentesDTO1).isNotEqualTo(custosconcorrentesDTO2);
        custosconcorrentesDTO2.setId(custosconcorrentesDTO1.getId());
        assertThat(custosconcorrentesDTO1).isEqualTo(custosconcorrentesDTO2);
        custosconcorrentesDTO2.setId(2L);
        assertThat(custosconcorrentesDTO1).isNotEqualTo(custosconcorrentesDTO2);
        custosconcorrentesDTO1.setId(null);
        assertThat(custosconcorrentesDTO1).isNotEqualTo(custosconcorrentesDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(custosconcorrentesMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(custosconcorrentesMapper.fromId(null)).isNull();
    }
}
