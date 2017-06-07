package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Ocorrencia;
import br.com.homemade.repository.OcorrenciaRepository;
import br.com.homemade.service.dto.OcorrenciaDTO;
import br.com.homemade.service.mapper.OcorrenciaMapper;
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
 * Test class for the OcorrenciaResource REST controller.
 *
 * @see OcorrenciaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class OcorrenciaResourceIntTest {

    private static final String DEFAULT_ALBUM = "AAAAAAAAAA";
    private static final String UPDATED_ALBUM = "BBBBBBBBBB";

    private static final String DEFAULT_CARACTERIZACAO = "AAAAAAAAAA";
    private static final String UPDATED_CARACTERIZACAO = "BBBBBBBBBB";

    private static final String DEFAULT_COORDENADAA = "AAAAAAAAAA";
    private static final String UPDATED_COORDENADAA = "BBBBBBBBBB";

    private static final String DEFAULT_COORDENADAE = "AAAAAAAAAA";
    private static final String UPDATED_COORDENADAE = "BBBBBBBBBB";

    private static final String DEFAULT_COORDENADAN = "AAAAAAAAAA";
    private static final String UPDATED_COORDENADAN = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Float DEFAULT_DISTANCIAEIXO = 1F;
    private static final Float UPDATED_DISTANCIAEIXO = 2F;

    private static final String DEFAULT_FEATURE = "AAAAAAAAAA";
    private static final String UPDATED_FEATURE = "BBBBBBBBBB";

    private static final Integer DEFAULT_KMFIM = 1;
    private static final Integer UPDATED_KMFIM = 2;

    private static final Integer DEFAULT_KMINICIO = 1;
    private static final Integer UPDATED_KMINICIO = 2;

    private static final String DEFAULT_LADO = "AAAAAAAAAA";
    private static final String UPDATED_LADO = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMERO = 1;
    private static final Integer UPDATED_NUMERO = 2;

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    private OcorrenciaMapper ocorrenciaMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOcorrenciaMockMvc;

    private Ocorrencia ocorrencia;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        OcorrenciaResource ocorrenciaResource = new OcorrenciaResource(ocorrenciaRepository, ocorrenciaMapper);
        this.restOcorrenciaMockMvc = MockMvcBuilders.standaloneSetup(ocorrenciaResource)
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
    public static Ocorrencia createEntity(EntityManager em) {
        Ocorrencia ocorrencia = new Ocorrencia()
            .album(DEFAULT_ALBUM)
            .caracterizacao(DEFAULT_CARACTERIZACAO)
            .coordenadaa(DEFAULT_COORDENADAA)
            .coordenadae(DEFAULT_COORDENADAE)
            .coordenadan(DEFAULT_COORDENADAN)
            .data(DEFAULT_DATA)
            .distanciaeixo(DEFAULT_DISTANCIAEIXO)
            .feature(DEFAULT_FEATURE)
            .kmfim(DEFAULT_KMFIM)
            .kminicio(DEFAULT_KMINICIO)
            .lado(DEFAULT_LADO)
            .numero(DEFAULT_NUMERO);
        return ocorrencia;
    }

    @Before
    public void initTest() {
        ocorrencia = createEntity(em);
    }

    @Test
    @Transactional
    public void createOcorrencia() throws Exception {
        int databaseSizeBeforeCreate = ocorrenciaRepository.findAll().size();

        // Create the Ocorrencia
        OcorrenciaDTO ocorrenciaDTO = ocorrenciaMapper.toDto(ocorrencia);
        restOcorrenciaMockMvc.perform(post("/api/ocorrencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ocorrenciaDTO)))
            .andExpect(status().isCreated());

        // Validate the Ocorrencia in the database
        List<Ocorrencia> ocorrenciaList = ocorrenciaRepository.findAll();
        assertThat(ocorrenciaList).hasSize(databaseSizeBeforeCreate + 1);
        Ocorrencia testOcorrencia = ocorrenciaList.get(ocorrenciaList.size() - 1);
        assertThat(testOcorrencia.getAlbum()).isEqualTo(DEFAULT_ALBUM);
        assertThat(testOcorrencia.getCaracterizacao()).isEqualTo(DEFAULT_CARACTERIZACAO);
        assertThat(testOcorrencia.getCoordenadaa()).isEqualTo(DEFAULT_COORDENADAA);
        assertThat(testOcorrencia.getCoordenadae()).isEqualTo(DEFAULT_COORDENADAE);
        assertThat(testOcorrencia.getCoordenadan()).isEqualTo(DEFAULT_COORDENADAN);
        assertThat(testOcorrencia.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testOcorrencia.getDistanciaeixo()).isEqualTo(DEFAULT_DISTANCIAEIXO);
        assertThat(testOcorrencia.getFeature()).isEqualTo(DEFAULT_FEATURE);
        assertThat(testOcorrencia.getKmfim()).isEqualTo(DEFAULT_KMFIM);
        assertThat(testOcorrencia.getKminicio()).isEqualTo(DEFAULT_KMINICIO);
        assertThat(testOcorrencia.getLado()).isEqualTo(DEFAULT_LADO);
        assertThat(testOcorrencia.getNumero()).isEqualTo(DEFAULT_NUMERO);
    }

    @Test
    @Transactional
    public void createOcorrenciaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ocorrenciaRepository.findAll().size();

        // Create the Ocorrencia with an existing ID
        ocorrencia.setId(1L);
        OcorrenciaDTO ocorrenciaDTO = ocorrenciaMapper.toDto(ocorrencia);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOcorrenciaMockMvc.perform(post("/api/ocorrencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ocorrenciaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Ocorrencia> ocorrenciaList = ocorrenciaRepository.findAll();
        assertThat(ocorrenciaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOcorrencias() throws Exception {
        // Initialize the database
        ocorrenciaRepository.saveAndFlush(ocorrencia);

        // Get all the ocorrenciaList
        restOcorrenciaMockMvc.perform(get("/api/ocorrencias?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ocorrencia.getId().intValue())))
            .andExpect(jsonPath("$.[*].album").value(hasItem(DEFAULT_ALBUM.toString())))
            .andExpect(jsonPath("$.[*].caracterizacao").value(hasItem(DEFAULT_CARACTERIZACAO.toString())))
            .andExpect(jsonPath("$.[*].coordenadaa").value(hasItem(DEFAULT_COORDENADAA.toString())))
            .andExpect(jsonPath("$.[*].coordenadae").value(hasItem(DEFAULT_COORDENADAE.toString())))
            .andExpect(jsonPath("$.[*].coordenadan").value(hasItem(DEFAULT_COORDENADAN.toString())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(sameInstant(DEFAULT_DATA))))
            .andExpect(jsonPath("$.[*].distanciaeixo").value(hasItem(DEFAULT_DISTANCIAEIXO.doubleValue())))
            .andExpect(jsonPath("$.[*].feature").value(hasItem(DEFAULT_FEATURE.toString())))
            .andExpect(jsonPath("$.[*].kmfim").value(hasItem(DEFAULT_KMFIM)))
            .andExpect(jsonPath("$.[*].kminicio").value(hasItem(DEFAULT_KMINICIO)))
            .andExpect(jsonPath("$.[*].lado").value(hasItem(DEFAULT_LADO.toString())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO)));
    }

    @Test
    @Transactional
    public void getOcorrencia() throws Exception {
        // Initialize the database
        ocorrenciaRepository.saveAndFlush(ocorrencia);

        // Get the ocorrencia
        restOcorrenciaMockMvc.perform(get("/api/ocorrencias/{id}", ocorrencia.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ocorrencia.getId().intValue()))
            .andExpect(jsonPath("$.album").value(DEFAULT_ALBUM.toString()))
            .andExpect(jsonPath("$.caracterizacao").value(DEFAULT_CARACTERIZACAO.toString()))
            .andExpect(jsonPath("$.coordenadaa").value(DEFAULT_COORDENADAA.toString()))
            .andExpect(jsonPath("$.coordenadae").value(DEFAULT_COORDENADAE.toString()))
            .andExpect(jsonPath("$.coordenadan").value(DEFAULT_COORDENADAN.toString()))
            .andExpect(jsonPath("$.data").value(sameInstant(DEFAULT_DATA)))
            .andExpect(jsonPath("$.distanciaeixo").value(DEFAULT_DISTANCIAEIXO.doubleValue()))
            .andExpect(jsonPath("$.feature").value(DEFAULT_FEATURE.toString()))
            .andExpect(jsonPath("$.kmfim").value(DEFAULT_KMFIM))
            .andExpect(jsonPath("$.kminicio").value(DEFAULT_KMINICIO))
            .andExpect(jsonPath("$.lado").value(DEFAULT_LADO.toString()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO));
    }

    @Test
    @Transactional
    public void getNonExistingOcorrencia() throws Exception {
        // Get the ocorrencia
        restOcorrenciaMockMvc.perform(get("/api/ocorrencias/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOcorrencia() throws Exception {
        // Initialize the database
        ocorrenciaRepository.saveAndFlush(ocorrencia);
        int databaseSizeBeforeUpdate = ocorrenciaRepository.findAll().size();

        // Update the ocorrencia
        Ocorrencia updatedOcorrencia = ocorrenciaRepository.findOne(ocorrencia.getId());
        updatedOcorrencia
            .album(UPDATED_ALBUM)
            .caracterizacao(UPDATED_CARACTERIZACAO)
            .coordenadaa(UPDATED_COORDENADAA)
            .coordenadae(UPDATED_COORDENADAE)
            .coordenadan(UPDATED_COORDENADAN)
            .data(UPDATED_DATA)
            .distanciaeixo(UPDATED_DISTANCIAEIXO)
            .feature(UPDATED_FEATURE)
            .kmfim(UPDATED_KMFIM)
            .kminicio(UPDATED_KMINICIO)
            .lado(UPDATED_LADO)
            .numero(UPDATED_NUMERO);
        OcorrenciaDTO ocorrenciaDTO = ocorrenciaMapper.toDto(updatedOcorrencia);

        restOcorrenciaMockMvc.perform(put("/api/ocorrencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ocorrenciaDTO)))
            .andExpect(status().isOk());

        // Validate the Ocorrencia in the database
        List<Ocorrencia> ocorrenciaList = ocorrenciaRepository.findAll();
        assertThat(ocorrenciaList).hasSize(databaseSizeBeforeUpdate);
        Ocorrencia testOcorrencia = ocorrenciaList.get(ocorrenciaList.size() - 1);
        assertThat(testOcorrencia.getAlbum()).isEqualTo(UPDATED_ALBUM);
        assertThat(testOcorrencia.getCaracterizacao()).isEqualTo(UPDATED_CARACTERIZACAO);
        assertThat(testOcorrencia.getCoordenadaa()).isEqualTo(UPDATED_COORDENADAA);
        assertThat(testOcorrencia.getCoordenadae()).isEqualTo(UPDATED_COORDENADAE);
        assertThat(testOcorrencia.getCoordenadan()).isEqualTo(UPDATED_COORDENADAN);
        assertThat(testOcorrencia.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testOcorrencia.getDistanciaeixo()).isEqualTo(UPDATED_DISTANCIAEIXO);
        assertThat(testOcorrencia.getFeature()).isEqualTo(UPDATED_FEATURE);
        assertThat(testOcorrencia.getKmfim()).isEqualTo(UPDATED_KMFIM);
        assertThat(testOcorrencia.getKminicio()).isEqualTo(UPDATED_KMINICIO);
        assertThat(testOcorrencia.getLado()).isEqualTo(UPDATED_LADO);
        assertThat(testOcorrencia.getNumero()).isEqualTo(UPDATED_NUMERO);
    }

    @Test
    @Transactional
    public void updateNonExistingOcorrencia() throws Exception {
        int databaseSizeBeforeUpdate = ocorrenciaRepository.findAll().size();

        // Create the Ocorrencia
        OcorrenciaDTO ocorrenciaDTO = ocorrenciaMapper.toDto(ocorrencia);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restOcorrenciaMockMvc.perform(put("/api/ocorrencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ocorrenciaDTO)))
            .andExpect(status().isCreated());

        // Validate the Ocorrencia in the database
        List<Ocorrencia> ocorrenciaList = ocorrenciaRepository.findAll();
        assertThat(ocorrenciaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteOcorrencia() throws Exception {
        // Initialize the database
        ocorrenciaRepository.saveAndFlush(ocorrencia);
        int databaseSizeBeforeDelete = ocorrenciaRepository.findAll().size();

        // Get the ocorrencia
        restOcorrenciaMockMvc.perform(delete("/api/ocorrencias/{id}", ocorrencia.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Ocorrencia> ocorrenciaList = ocorrenciaRepository.findAll();
        assertThat(ocorrenciaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Ocorrencia.class);
        Ocorrencia ocorrencia1 = new Ocorrencia();
        ocorrencia1.setId(1L);
        Ocorrencia ocorrencia2 = new Ocorrencia();
        ocorrencia2.setId(ocorrencia1.getId());
        assertThat(ocorrencia1).isEqualTo(ocorrencia2);
        ocorrencia2.setId(2L);
        assertThat(ocorrencia1).isNotEqualTo(ocorrencia2);
        ocorrencia1.setId(null);
        assertThat(ocorrencia1).isNotEqualTo(ocorrencia2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OcorrenciaDTO.class);
        OcorrenciaDTO ocorrenciaDTO1 = new OcorrenciaDTO();
        ocorrenciaDTO1.setId(1L);
        OcorrenciaDTO ocorrenciaDTO2 = new OcorrenciaDTO();
        assertThat(ocorrenciaDTO1).isNotEqualTo(ocorrenciaDTO2);
        ocorrenciaDTO2.setId(ocorrenciaDTO1.getId());
        assertThat(ocorrenciaDTO1).isEqualTo(ocorrenciaDTO2);
        ocorrenciaDTO2.setId(2L);
        assertThat(ocorrenciaDTO1).isNotEqualTo(ocorrenciaDTO2);
        ocorrenciaDTO1.setId(null);
        assertThat(ocorrenciaDTO1).isNotEqualTo(ocorrenciaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(ocorrenciaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(ocorrenciaMapper.fromId(null)).isNull();
    }
}
