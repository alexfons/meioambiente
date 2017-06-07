package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Notificacao;
import br.com.homemade.repository.NotificacaoRepository;
import br.com.homemade.service.dto.NotificacaoDTO;
import br.com.homemade.service.mapper.NotificacaoMapper;
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
 * Test class for the NotificacaoResource REST controller.
 *
 * @see NotificacaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class NotificacaoResourceIntTest {

    private static final ZonedDateTime DEFAULT_DATA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAINSPECAO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAINSPECAO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_NUMERO = 1;
    private static final Integer UPDATED_NUMERO = 2;

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    @Autowired
    private NotificacaoMapper notificacaoMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restNotificacaoMockMvc;

    private Notificacao notificacao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        NotificacaoResource notificacaoResource = new NotificacaoResource(notificacaoRepository, notificacaoMapper);
        this.restNotificacaoMockMvc = MockMvcBuilders.standaloneSetup(notificacaoResource)
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
    public static Notificacao createEntity(EntityManager em) {
        Notificacao notificacao = new Notificacao()
            .data(DEFAULT_DATA)
            .datainspecao(DEFAULT_DATAINSPECAO)
            .numero(DEFAULT_NUMERO)
            .obs(DEFAULT_OBS);
        return notificacao;
    }

    @Before
    public void initTest() {
        notificacao = createEntity(em);
    }

    @Test
    @Transactional
    public void createNotificacao() throws Exception {
        int databaseSizeBeforeCreate = notificacaoRepository.findAll().size();

        // Create the Notificacao
        NotificacaoDTO notificacaoDTO = notificacaoMapper.toDto(notificacao);
        restNotificacaoMockMvc.perform(post("/api/notificacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificacaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Notificacao in the database
        List<Notificacao> notificacaoList = notificacaoRepository.findAll();
        assertThat(notificacaoList).hasSize(databaseSizeBeforeCreate + 1);
        Notificacao testNotificacao = notificacaoList.get(notificacaoList.size() - 1);
        assertThat(testNotificacao.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testNotificacao.getDatainspecao()).isEqualTo(DEFAULT_DATAINSPECAO);
        assertThat(testNotificacao.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testNotificacao.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createNotificacaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = notificacaoRepository.findAll().size();

        // Create the Notificacao with an existing ID
        notificacao.setId(1L);
        NotificacaoDTO notificacaoDTO = notificacaoMapper.toDto(notificacao);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNotificacaoMockMvc.perform(post("/api/notificacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificacaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Notificacao> notificacaoList = notificacaoRepository.findAll();
        assertThat(notificacaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllNotificacaos() throws Exception {
        // Initialize the database
        notificacaoRepository.saveAndFlush(notificacao);

        // Get all the notificacaoList
        restNotificacaoMockMvc.perform(get("/api/notificacaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notificacao.getId().intValue())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(sameInstant(DEFAULT_DATA))))
            .andExpect(jsonPath("$.[*].datainspecao").value(hasItem(sameInstant(DEFAULT_DATAINSPECAO))))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO)))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }

    @Test
    @Transactional
    public void getNotificacao() throws Exception {
        // Initialize the database
        notificacaoRepository.saveAndFlush(notificacao);

        // Get the notificacao
        restNotificacaoMockMvc.perform(get("/api/notificacaos/{id}", notificacao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(notificacao.getId().intValue()))
            .andExpect(jsonPath("$.data").value(sameInstant(DEFAULT_DATA)))
            .andExpect(jsonPath("$.datainspecao").value(sameInstant(DEFAULT_DATAINSPECAO)))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingNotificacao() throws Exception {
        // Get the notificacao
        restNotificacaoMockMvc.perform(get("/api/notificacaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNotificacao() throws Exception {
        // Initialize the database
        notificacaoRepository.saveAndFlush(notificacao);
        int databaseSizeBeforeUpdate = notificacaoRepository.findAll().size();

        // Update the notificacao
        Notificacao updatedNotificacao = notificacaoRepository.findOne(notificacao.getId());
        updatedNotificacao
            .data(UPDATED_DATA)
            .datainspecao(UPDATED_DATAINSPECAO)
            .numero(UPDATED_NUMERO)
            .obs(UPDATED_OBS);
        NotificacaoDTO notificacaoDTO = notificacaoMapper.toDto(updatedNotificacao);

        restNotificacaoMockMvc.perform(put("/api/notificacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificacaoDTO)))
            .andExpect(status().isOk());

        // Validate the Notificacao in the database
        List<Notificacao> notificacaoList = notificacaoRepository.findAll();
        assertThat(notificacaoList).hasSize(databaseSizeBeforeUpdate);
        Notificacao testNotificacao = notificacaoList.get(notificacaoList.size() - 1);
        assertThat(testNotificacao.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testNotificacao.getDatainspecao()).isEqualTo(UPDATED_DATAINSPECAO);
        assertThat(testNotificacao.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testNotificacao.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingNotificacao() throws Exception {
        int databaseSizeBeforeUpdate = notificacaoRepository.findAll().size();

        // Create the Notificacao
        NotificacaoDTO notificacaoDTO = notificacaoMapper.toDto(notificacao);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restNotificacaoMockMvc.perform(put("/api/notificacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificacaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Notificacao in the database
        List<Notificacao> notificacaoList = notificacaoRepository.findAll();
        assertThat(notificacaoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteNotificacao() throws Exception {
        // Initialize the database
        notificacaoRepository.saveAndFlush(notificacao);
        int databaseSizeBeforeDelete = notificacaoRepository.findAll().size();

        // Get the notificacao
        restNotificacaoMockMvc.perform(delete("/api/notificacaos/{id}", notificacao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Notificacao> notificacaoList = notificacaoRepository.findAll();
        assertThat(notificacaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Notificacao.class);
        Notificacao notificacao1 = new Notificacao();
        notificacao1.setId(1L);
        Notificacao notificacao2 = new Notificacao();
        notificacao2.setId(notificacao1.getId());
        assertThat(notificacao1).isEqualTo(notificacao2);
        notificacao2.setId(2L);
        assertThat(notificacao1).isNotEqualTo(notificacao2);
        notificacao1.setId(null);
        assertThat(notificacao1).isNotEqualTo(notificacao2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotificacaoDTO.class);
        NotificacaoDTO notificacaoDTO1 = new NotificacaoDTO();
        notificacaoDTO1.setId(1L);
        NotificacaoDTO notificacaoDTO2 = new NotificacaoDTO();
        assertThat(notificacaoDTO1).isNotEqualTo(notificacaoDTO2);
        notificacaoDTO2.setId(notificacaoDTO1.getId());
        assertThat(notificacaoDTO1).isEqualTo(notificacaoDTO2);
        notificacaoDTO2.setId(2L);
        assertThat(notificacaoDTO1).isNotEqualTo(notificacaoDTO2);
        notificacaoDTO1.setId(null);
        assertThat(notificacaoDTO1).isNotEqualTo(notificacaoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(notificacaoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(notificacaoMapper.fromId(null)).isNull();
    }
}
