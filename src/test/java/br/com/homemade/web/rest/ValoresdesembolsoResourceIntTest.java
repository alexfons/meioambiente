package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Valoresdesembolso;
import br.com.homemade.repository.ValoresdesembolsoRepository;
import br.com.homemade.service.dto.ValoresdesembolsoDTO;
import br.com.homemade.service.mapper.ValoresdesembolsoMapper;
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
 * Test class for the ValoresdesembolsoResource REST controller.
 *
 * @see ValoresdesembolsoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class ValoresdesembolsoResourceIntTest {

    private static final ZonedDateTime DEFAULT_DATAINTERNALIZACAO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAINTERNALIZACAO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_IDVALORESDESEMBOLSO = 1;
    private static final Integer UPDATED_IDVALORESDESEMBOLSO = 2;

    private static final Integer DEFAULT_NSOLICITACAO = 1;
    private static final Integer UPDATED_NSOLICITACAO = 2;

    private static final String DEFAULT_TIPODESEMBOLSO = "AAAAAAAAAA";
    private static final String UPDATED_TIPODESEMBOLSO = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_VALOREAIS = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALOREAIS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VALORUS = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALORUS = new BigDecimal(2);

    private static final ZonedDateTime DEFAULT_VALUEDATA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_VALUEDATA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private ValoresdesembolsoRepository valoresdesembolsoRepository;

    @Autowired
    private ValoresdesembolsoMapper valoresdesembolsoMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restValoresdesembolsoMockMvc;

    private Valoresdesembolso valoresdesembolso;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ValoresdesembolsoResource valoresdesembolsoResource = new ValoresdesembolsoResource(valoresdesembolsoRepository, valoresdesembolsoMapper);
        this.restValoresdesembolsoMockMvc = MockMvcBuilders.standaloneSetup(valoresdesembolsoResource)
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
    public static Valoresdesembolso createEntity(EntityManager em) {
        Valoresdesembolso valoresdesembolso = new Valoresdesembolso()
            .datainternalizacao(DEFAULT_DATAINTERNALIZACAO)
            .idvaloresdesembolso(DEFAULT_IDVALORESDESEMBOLSO)
            .nsolicitacao(DEFAULT_NSOLICITACAO)
            .tipodesembolso(DEFAULT_TIPODESEMBOLSO)
            .valoreais(DEFAULT_VALOREAIS)
            .valorus(DEFAULT_VALORUS)
            .valuedata(DEFAULT_VALUEDATA);
        return valoresdesembolso;
    }

    @Before
    public void initTest() {
        valoresdesembolso = createEntity(em);
    }

    @Test
    @Transactional
    public void createValoresdesembolso() throws Exception {
        int databaseSizeBeforeCreate = valoresdesembolsoRepository.findAll().size();

        // Create the Valoresdesembolso
        ValoresdesembolsoDTO valoresdesembolsoDTO = valoresdesembolsoMapper.toDto(valoresdesembolso);
        restValoresdesembolsoMockMvc.perform(post("/api/valoresdesembolsos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(valoresdesembolsoDTO)))
            .andExpect(status().isCreated());

        // Validate the Valoresdesembolso in the database
        List<Valoresdesembolso> valoresdesembolsoList = valoresdesembolsoRepository.findAll();
        assertThat(valoresdesembolsoList).hasSize(databaseSizeBeforeCreate + 1);
        Valoresdesembolso testValoresdesembolso = valoresdesembolsoList.get(valoresdesembolsoList.size() - 1);
        assertThat(testValoresdesembolso.getDatainternalizacao()).isEqualTo(DEFAULT_DATAINTERNALIZACAO);
        assertThat(testValoresdesembolso.getIdvaloresdesembolso()).isEqualTo(DEFAULT_IDVALORESDESEMBOLSO);
        assertThat(testValoresdesembolso.getNsolicitacao()).isEqualTo(DEFAULT_NSOLICITACAO);
        assertThat(testValoresdesembolso.getTipodesembolso()).isEqualTo(DEFAULT_TIPODESEMBOLSO);
        assertThat(testValoresdesembolso.getValoreais()).isEqualTo(DEFAULT_VALOREAIS);
        assertThat(testValoresdesembolso.getValorus()).isEqualTo(DEFAULT_VALORUS);
        assertThat(testValoresdesembolso.getValuedata()).isEqualTo(DEFAULT_VALUEDATA);
    }

    @Test
    @Transactional
    public void createValoresdesembolsoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = valoresdesembolsoRepository.findAll().size();

        // Create the Valoresdesembolso with an existing ID
        valoresdesembolso.setId(1L);
        ValoresdesembolsoDTO valoresdesembolsoDTO = valoresdesembolsoMapper.toDto(valoresdesembolso);

        // An entity with an existing ID cannot be created, so this API call must fail
        restValoresdesembolsoMockMvc.perform(post("/api/valoresdesembolsos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(valoresdesembolsoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Valoresdesembolso> valoresdesembolsoList = valoresdesembolsoRepository.findAll();
        assertThat(valoresdesembolsoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllValoresdesembolsos() throws Exception {
        // Initialize the database
        valoresdesembolsoRepository.saveAndFlush(valoresdesembolso);

        // Get all the valoresdesembolsoList
        restValoresdesembolsoMockMvc.perform(get("/api/valoresdesembolsos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(valoresdesembolso.getId().intValue())))
            .andExpect(jsonPath("$.[*].datainternalizacao").value(hasItem(sameInstant(DEFAULT_DATAINTERNALIZACAO))))
            .andExpect(jsonPath("$.[*].idvaloresdesembolso").value(hasItem(DEFAULT_IDVALORESDESEMBOLSO)))
            .andExpect(jsonPath("$.[*].nsolicitacao").value(hasItem(DEFAULT_NSOLICITACAO)))
            .andExpect(jsonPath("$.[*].tipodesembolso").value(hasItem(DEFAULT_TIPODESEMBOLSO.toString())))
            .andExpect(jsonPath("$.[*].valoreais").value(hasItem(DEFAULT_VALOREAIS.intValue())))
            .andExpect(jsonPath("$.[*].valorus").value(hasItem(DEFAULT_VALORUS.intValue())))
            .andExpect(jsonPath("$.[*].valuedata").value(hasItem(sameInstant(DEFAULT_VALUEDATA))));
    }

    @Test
    @Transactional
    public void getValoresdesembolso() throws Exception {
        // Initialize the database
        valoresdesembolsoRepository.saveAndFlush(valoresdesembolso);

        // Get the valoresdesembolso
        restValoresdesembolsoMockMvc.perform(get("/api/valoresdesembolsos/{id}", valoresdesembolso.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(valoresdesembolso.getId().intValue()))
            .andExpect(jsonPath("$.datainternalizacao").value(sameInstant(DEFAULT_DATAINTERNALIZACAO)))
            .andExpect(jsonPath("$.idvaloresdesembolso").value(DEFAULT_IDVALORESDESEMBOLSO))
            .andExpect(jsonPath("$.nsolicitacao").value(DEFAULT_NSOLICITACAO))
            .andExpect(jsonPath("$.tipodesembolso").value(DEFAULT_TIPODESEMBOLSO.toString()))
            .andExpect(jsonPath("$.valoreais").value(DEFAULT_VALOREAIS.intValue()))
            .andExpect(jsonPath("$.valorus").value(DEFAULT_VALORUS.intValue()))
            .andExpect(jsonPath("$.valuedata").value(sameInstant(DEFAULT_VALUEDATA)));
    }

    @Test
    @Transactional
    public void getNonExistingValoresdesembolso() throws Exception {
        // Get the valoresdesembolso
        restValoresdesembolsoMockMvc.perform(get("/api/valoresdesembolsos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateValoresdesembolso() throws Exception {
        // Initialize the database
        valoresdesembolsoRepository.saveAndFlush(valoresdesembolso);
        int databaseSizeBeforeUpdate = valoresdesembolsoRepository.findAll().size();

        // Update the valoresdesembolso
        Valoresdesembolso updatedValoresdesembolso = valoresdesembolsoRepository.findOne(valoresdesembolso.getId());
        updatedValoresdesembolso
            .datainternalizacao(UPDATED_DATAINTERNALIZACAO)
            .idvaloresdesembolso(UPDATED_IDVALORESDESEMBOLSO)
            .nsolicitacao(UPDATED_NSOLICITACAO)
            .tipodesembolso(UPDATED_TIPODESEMBOLSO)
            .valoreais(UPDATED_VALOREAIS)
            .valorus(UPDATED_VALORUS)
            .valuedata(UPDATED_VALUEDATA);
        ValoresdesembolsoDTO valoresdesembolsoDTO = valoresdesembolsoMapper.toDto(updatedValoresdesembolso);

        restValoresdesembolsoMockMvc.perform(put("/api/valoresdesembolsos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(valoresdesembolsoDTO)))
            .andExpect(status().isOk());

        // Validate the Valoresdesembolso in the database
        List<Valoresdesembolso> valoresdesembolsoList = valoresdesembolsoRepository.findAll();
        assertThat(valoresdesembolsoList).hasSize(databaseSizeBeforeUpdate);
        Valoresdesembolso testValoresdesembolso = valoresdesembolsoList.get(valoresdesembolsoList.size() - 1);
        assertThat(testValoresdesembolso.getDatainternalizacao()).isEqualTo(UPDATED_DATAINTERNALIZACAO);
        assertThat(testValoresdesembolso.getIdvaloresdesembolso()).isEqualTo(UPDATED_IDVALORESDESEMBOLSO);
        assertThat(testValoresdesembolso.getNsolicitacao()).isEqualTo(UPDATED_NSOLICITACAO);
        assertThat(testValoresdesembolso.getTipodesembolso()).isEqualTo(UPDATED_TIPODESEMBOLSO);
        assertThat(testValoresdesembolso.getValoreais()).isEqualTo(UPDATED_VALOREAIS);
        assertThat(testValoresdesembolso.getValorus()).isEqualTo(UPDATED_VALORUS);
        assertThat(testValoresdesembolso.getValuedata()).isEqualTo(UPDATED_VALUEDATA);
    }

    @Test
    @Transactional
    public void updateNonExistingValoresdesembolso() throws Exception {
        int databaseSizeBeforeUpdate = valoresdesembolsoRepository.findAll().size();

        // Create the Valoresdesembolso
        ValoresdesembolsoDTO valoresdesembolsoDTO = valoresdesembolsoMapper.toDto(valoresdesembolso);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restValoresdesembolsoMockMvc.perform(put("/api/valoresdesembolsos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(valoresdesembolsoDTO)))
            .andExpect(status().isCreated());

        // Validate the Valoresdesembolso in the database
        List<Valoresdesembolso> valoresdesembolsoList = valoresdesembolsoRepository.findAll();
        assertThat(valoresdesembolsoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteValoresdesembolso() throws Exception {
        // Initialize the database
        valoresdesembolsoRepository.saveAndFlush(valoresdesembolso);
        int databaseSizeBeforeDelete = valoresdesembolsoRepository.findAll().size();

        // Get the valoresdesembolso
        restValoresdesembolsoMockMvc.perform(delete("/api/valoresdesembolsos/{id}", valoresdesembolso.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Valoresdesembolso> valoresdesembolsoList = valoresdesembolsoRepository.findAll();
        assertThat(valoresdesembolsoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Valoresdesembolso.class);
        Valoresdesembolso valoresdesembolso1 = new Valoresdesembolso();
        valoresdesembolso1.setId(1L);
        Valoresdesembolso valoresdesembolso2 = new Valoresdesembolso();
        valoresdesembolso2.setId(valoresdesembolso1.getId());
        assertThat(valoresdesembolso1).isEqualTo(valoresdesembolso2);
        valoresdesembolso2.setId(2L);
        assertThat(valoresdesembolso1).isNotEqualTo(valoresdesembolso2);
        valoresdesembolso1.setId(null);
        assertThat(valoresdesembolso1).isNotEqualTo(valoresdesembolso2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ValoresdesembolsoDTO.class);
        ValoresdesembolsoDTO valoresdesembolsoDTO1 = new ValoresdesembolsoDTO();
        valoresdesembolsoDTO1.setId(1L);
        ValoresdesembolsoDTO valoresdesembolsoDTO2 = new ValoresdesembolsoDTO();
        assertThat(valoresdesembolsoDTO1).isNotEqualTo(valoresdesembolsoDTO2);
        valoresdesembolsoDTO2.setId(valoresdesembolsoDTO1.getId());
        assertThat(valoresdesembolsoDTO1).isEqualTo(valoresdesembolsoDTO2);
        valoresdesembolsoDTO2.setId(2L);
        assertThat(valoresdesembolsoDTO1).isNotEqualTo(valoresdesembolsoDTO2);
        valoresdesembolsoDTO1.setId(null);
        assertThat(valoresdesembolsoDTO1).isNotEqualTo(valoresdesembolsoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(valoresdesembolsoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(valoresdesembolsoMapper.fromId(null)).isNull();
    }
}
