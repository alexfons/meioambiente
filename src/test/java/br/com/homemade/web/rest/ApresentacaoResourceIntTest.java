package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Apresentacao;
import br.com.homemade.repository.ApresentacaoRepository;
import br.com.homemade.service.dto.ApresentacaoDTO;
import br.com.homemade.service.mapper.ApresentacaoMapper;
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
 * Test class for the ApresentacaoResource REST controller.
 *
 * @see ApresentacaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class ApresentacaoResourceIntTest {

    private static final Integer DEFAULT_NUMERO = 1;
    private static final Integer UPDATED_NUMERO = 2;

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_NOTIFICACAO = false;
    private static final Boolean UPDATED_NOTIFICACAO = true;

    private static final ZonedDateTime DEFAULT_DATA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private ApresentacaoRepository apresentacaoRepository;

    @Autowired
    private ApresentacaoMapper apresentacaoMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restApresentacaoMockMvc;

    private Apresentacao apresentacao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ApresentacaoResource apresentacaoResource = new ApresentacaoResource(apresentacaoRepository, apresentacaoMapper);
        this.restApresentacaoMockMvc = MockMvcBuilders.standaloneSetup(apresentacaoResource)
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
    public static Apresentacao createEntity(EntityManager em) {
        Apresentacao apresentacao = new Apresentacao()
            .numero(DEFAULT_NUMERO)
            .obs(DEFAULT_OBS)
            .notificacao(DEFAULT_NOTIFICACAO)
            .data(DEFAULT_DATA);
        return apresentacao;
    }

    @Before
    public void initTest() {
        apresentacao = createEntity(em);
    }

    @Test
    @Transactional
    public void createApresentacao() throws Exception {
        int databaseSizeBeforeCreate = apresentacaoRepository.findAll().size();

        // Create the Apresentacao
        ApresentacaoDTO apresentacaoDTO = apresentacaoMapper.toDto(apresentacao);
        restApresentacaoMockMvc.perform(post("/api/apresentacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apresentacaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Apresentacao in the database
        List<Apresentacao> apresentacaoList = apresentacaoRepository.findAll();
        assertThat(apresentacaoList).hasSize(databaseSizeBeforeCreate + 1);
        Apresentacao testApresentacao = apresentacaoList.get(apresentacaoList.size() - 1);
        assertThat(testApresentacao.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testApresentacao.getObs()).isEqualTo(DEFAULT_OBS);
        assertThat(testApresentacao.isNotificacao()).isEqualTo(DEFAULT_NOTIFICACAO);
        assertThat(testApresentacao.getData()).isEqualTo(DEFAULT_DATA);
    }

    @Test
    @Transactional
    public void createApresentacaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = apresentacaoRepository.findAll().size();

        // Create the Apresentacao with an existing ID
        apresentacao.setId(1L);
        ApresentacaoDTO apresentacaoDTO = apresentacaoMapper.toDto(apresentacao);

        // An entity with an existing ID cannot be created, so this API call must fail
        restApresentacaoMockMvc.perform(post("/api/apresentacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apresentacaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Apresentacao> apresentacaoList = apresentacaoRepository.findAll();
        assertThat(apresentacaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllApresentacaos() throws Exception {
        // Initialize the database
        apresentacaoRepository.saveAndFlush(apresentacao);

        // Get all the apresentacaoList
        restApresentacaoMockMvc.perform(get("/api/apresentacaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(apresentacao.getId().intValue())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO)))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())))
            .andExpect(jsonPath("$.[*].notificacao").value(hasItem(DEFAULT_NOTIFICACAO.booleanValue())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(sameInstant(DEFAULT_DATA))));
    }

    @Test
    @Transactional
    public void getApresentacao() throws Exception {
        // Initialize the database
        apresentacaoRepository.saveAndFlush(apresentacao);

        // Get the apresentacao
        restApresentacaoMockMvc.perform(get("/api/apresentacaos/{id}", apresentacao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(apresentacao.getId().intValue()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()))
            .andExpect(jsonPath("$.notificacao").value(DEFAULT_NOTIFICACAO.booleanValue()))
            .andExpect(jsonPath("$.data").value(sameInstant(DEFAULT_DATA)));
    }

    @Test
    @Transactional
    public void getNonExistingApresentacao() throws Exception {
        // Get the apresentacao
        restApresentacaoMockMvc.perform(get("/api/apresentacaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateApresentacao() throws Exception {
        // Initialize the database
        apresentacaoRepository.saveAndFlush(apresentacao);
        int databaseSizeBeforeUpdate = apresentacaoRepository.findAll().size();

        // Update the apresentacao
        Apresentacao updatedApresentacao = apresentacaoRepository.findOne(apresentacao.getId());
        updatedApresentacao
            .numero(UPDATED_NUMERO)
            .obs(UPDATED_OBS)
            .notificacao(UPDATED_NOTIFICACAO)
            .data(UPDATED_DATA);
        ApresentacaoDTO apresentacaoDTO = apresentacaoMapper.toDto(updatedApresentacao);

        restApresentacaoMockMvc.perform(put("/api/apresentacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apresentacaoDTO)))
            .andExpect(status().isOk());

        // Validate the Apresentacao in the database
        List<Apresentacao> apresentacaoList = apresentacaoRepository.findAll();
        assertThat(apresentacaoList).hasSize(databaseSizeBeforeUpdate);
        Apresentacao testApresentacao = apresentacaoList.get(apresentacaoList.size() - 1);
        assertThat(testApresentacao.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testApresentacao.getObs()).isEqualTo(UPDATED_OBS);
        assertThat(testApresentacao.isNotificacao()).isEqualTo(UPDATED_NOTIFICACAO);
        assertThat(testApresentacao.getData()).isEqualTo(UPDATED_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingApresentacao() throws Exception {
        int databaseSizeBeforeUpdate = apresentacaoRepository.findAll().size();

        // Create the Apresentacao
        ApresentacaoDTO apresentacaoDTO = apresentacaoMapper.toDto(apresentacao);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restApresentacaoMockMvc.perform(put("/api/apresentacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apresentacaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Apresentacao in the database
        List<Apresentacao> apresentacaoList = apresentacaoRepository.findAll();
        assertThat(apresentacaoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteApresentacao() throws Exception {
        // Initialize the database
        apresentacaoRepository.saveAndFlush(apresentacao);
        int databaseSizeBeforeDelete = apresentacaoRepository.findAll().size();

        // Get the apresentacao
        restApresentacaoMockMvc.perform(delete("/api/apresentacaos/{id}", apresentacao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Apresentacao> apresentacaoList = apresentacaoRepository.findAll();
        assertThat(apresentacaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Apresentacao.class);
        Apresentacao apresentacao1 = new Apresentacao();
        apresentacao1.setId(1L);
        Apresentacao apresentacao2 = new Apresentacao();
        apresentacao2.setId(apresentacao1.getId());
        assertThat(apresentacao1).isEqualTo(apresentacao2);
        apresentacao2.setId(2L);
        assertThat(apresentacao1).isNotEqualTo(apresentacao2);
        apresentacao1.setId(null);
        assertThat(apresentacao1).isNotEqualTo(apresentacao2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ApresentacaoDTO.class);
        ApresentacaoDTO apresentacaoDTO1 = new ApresentacaoDTO();
        apresentacaoDTO1.setId(1L);
        ApresentacaoDTO apresentacaoDTO2 = new ApresentacaoDTO();
        assertThat(apresentacaoDTO1).isNotEqualTo(apresentacaoDTO2);
        apresentacaoDTO2.setId(apresentacaoDTO1.getId());
        assertThat(apresentacaoDTO1).isEqualTo(apresentacaoDTO2);
        apresentacaoDTO2.setId(2L);
        assertThat(apresentacaoDTO1).isNotEqualTo(apresentacaoDTO2);
        apresentacaoDTO1.setId(null);
        assertThat(apresentacaoDTO1).isNotEqualTo(apresentacaoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(apresentacaoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(apresentacaoMapper.fromId(null)).isNull();
    }
}
