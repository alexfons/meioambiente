package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Pendencias;
import br.com.homemade.repository.PendenciasRepository;
import br.com.homemade.service.dto.PendenciasDTO;
import br.com.homemade.service.mapper.PendenciasMapper;
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
 * Test class for the PendenciasResource REST controller.
 *
 * @see PendenciasResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class PendenciasResourceIntTest {

    private static final ZonedDateTime DEFAULT_DATA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAINSPECAO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAINSPECAO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_NOTIFICACAO = false;
    private static final Boolean UPDATED_NOTIFICACAO = true;

    private static final Integer DEFAULT_NUMERO = 1;
    private static final Integer UPDATED_NUMERO = 2;

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    @Autowired
    private PendenciasRepository pendenciasRepository;

    @Autowired
    private PendenciasMapper pendenciasMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPendenciasMockMvc;

    private Pendencias pendencias;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PendenciasResource pendenciasResource = new PendenciasResource(pendenciasRepository, pendenciasMapper);
        this.restPendenciasMockMvc = MockMvcBuilders.standaloneSetup(pendenciasResource)
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
    public static Pendencias createEntity(EntityManager em) {
        Pendencias pendencias = new Pendencias()
            .data(DEFAULT_DATA)
            .datainspecao(DEFAULT_DATAINSPECAO)
            .notificacao(DEFAULT_NOTIFICACAO)
            .numero(DEFAULT_NUMERO)
            .obs(DEFAULT_OBS);
        return pendencias;
    }

    @Before
    public void initTest() {
        pendencias = createEntity(em);
    }

    @Test
    @Transactional
    public void createPendencias() throws Exception {
        int databaseSizeBeforeCreate = pendenciasRepository.findAll().size();

        // Create the Pendencias
        PendenciasDTO pendenciasDTO = pendenciasMapper.toDto(pendencias);
        restPendenciasMockMvc.perform(post("/api/pendencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pendenciasDTO)))
            .andExpect(status().isCreated());

        // Validate the Pendencias in the database
        List<Pendencias> pendenciasList = pendenciasRepository.findAll();
        assertThat(pendenciasList).hasSize(databaseSizeBeforeCreate + 1);
        Pendencias testPendencias = pendenciasList.get(pendenciasList.size() - 1);
        assertThat(testPendencias.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testPendencias.getDatainspecao()).isEqualTo(DEFAULT_DATAINSPECAO);
        assertThat(testPendencias.isNotificacao()).isEqualTo(DEFAULT_NOTIFICACAO);
        assertThat(testPendencias.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testPendencias.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createPendenciasWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pendenciasRepository.findAll().size();

        // Create the Pendencias with an existing ID
        pendencias.setId(1L);
        PendenciasDTO pendenciasDTO = pendenciasMapper.toDto(pendencias);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPendenciasMockMvc.perform(post("/api/pendencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pendenciasDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Pendencias> pendenciasList = pendenciasRepository.findAll();
        assertThat(pendenciasList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPendencias() throws Exception {
        // Initialize the database
        pendenciasRepository.saveAndFlush(pendencias);

        // Get all the pendenciasList
        restPendenciasMockMvc.perform(get("/api/pendencias?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pendencias.getId().intValue())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(sameInstant(DEFAULT_DATA))))
            .andExpect(jsonPath("$.[*].datainspecao").value(hasItem(sameInstant(DEFAULT_DATAINSPECAO))))
            .andExpect(jsonPath("$.[*].notificacao").value(hasItem(DEFAULT_NOTIFICACAO.booleanValue())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO)))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }

    @Test
    @Transactional
    public void getPendencias() throws Exception {
        // Initialize the database
        pendenciasRepository.saveAndFlush(pendencias);

        // Get the pendencias
        restPendenciasMockMvc.perform(get("/api/pendencias/{id}", pendencias.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pendencias.getId().intValue()))
            .andExpect(jsonPath("$.data").value(sameInstant(DEFAULT_DATA)))
            .andExpect(jsonPath("$.datainspecao").value(sameInstant(DEFAULT_DATAINSPECAO)))
            .andExpect(jsonPath("$.notificacao").value(DEFAULT_NOTIFICACAO.booleanValue()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPendencias() throws Exception {
        // Get the pendencias
        restPendenciasMockMvc.perform(get("/api/pendencias/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePendencias() throws Exception {
        // Initialize the database
        pendenciasRepository.saveAndFlush(pendencias);
        int databaseSizeBeforeUpdate = pendenciasRepository.findAll().size();

        // Update the pendencias
        Pendencias updatedPendencias = pendenciasRepository.findOne(pendencias.getId());
        updatedPendencias
            .data(UPDATED_DATA)
            .datainspecao(UPDATED_DATAINSPECAO)
            .notificacao(UPDATED_NOTIFICACAO)
            .numero(UPDATED_NUMERO)
            .obs(UPDATED_OBS);
        PendenciasDTO pendenciasDTO = pendenciasMapper.toDto(updatedPendencias);

        restPendenciasMockMvc.perform(put("/api/pendencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pendenciasDTO)))
            .andExpect(status().isOk());

        // Validate the Pendencias in the database
        List<Pendencias> pendenciasList = pendenciasRepository.findAll();
        assertThat(pendenciasList).hasSize(databaseSizeBeforeUpdate);
        Pendencias testPendencias = pendenciasList.get(pendenciasList.size() - 1);
        assertThat(testPendencias.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testPendencias.getDatainspecao()).isEqualTo(UPDATED_DATAINSPECAO);
        assertThat(testPendencias.isNotificacao()).isEqualTo(UPDATED_NOTIFICACAO);
        assertThat(testPendencias.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testPendencias.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingPendencias() throws Exception {
        int databaseSizeBeforeUpdate = pendenciasRepository.findAll().size();

        // Create the Pendencias
        PendenciasDTO pendenciasDTO = pendenciasMapper.toDto(pendencias);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPendenciasMockMvc.perform(put("/api/pendencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pendenciasDTO)))
            .andExpect(status().isCreated());

        // Validate the Pendencias in the database
        List<Pendencias> pendenciasList = pendenciasRepository.findAll();
        assertThat(pendenciasList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePendencias() throws Exception {
        // Initialize the database
        pendenciasRepository.saveAndFlush(pendencias);
        int databaseSizeBeforeDelete = pendenciasRepository.findAll().size();

        // Get the pendencias
        restPendenciasMockMvc.perform(delete("/api/pendencias/{id}", pendencias.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Pendencias> pendenciasList = pendenciasRepository.findAll();
        assertThat(pendenciasList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Pendencias.class);
        Pendencias pendencias1 = new Pendencias();
        pendencias1.setId(1L);
        Pendencias pendencias2 = new Pendencias();
        pendencias2.setId(pendencias1.getId());
        assertThat(pendencias1).isEqualTo(pendencias2);
        pendencias2.setId(2L);
        assertThat(pendencias1).isNotEqualTo(pendencias2);
        pendencias1.setId(null);
        assertThat(pendencias1).isNotEqualTo(pendencias2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PendenciasDTO.class);
        PendenciasDTO pendenciasDTO1 = new PendenciasDTO();
        pendenciasDTO1.setId(1L);
        PendenciasDTO pendenciasDTO2 = new PendenciasDTO();
        assertThat(pendenciasDTO1).isNotEqualTo(pendenciasDTO2);
        pendenciasDTO2.setId(pendenciasDTO1.getId());
        assertThat(pendenciasDTO1).isEqualTo(pendenciasDTO2);
        pendenciasDTO2.setId(2L);
        assertThat(pendenciasDTO1).isNotEqualTo(pendenciasDTO2);
        pendenciasDTO1.setId(null);
        assertThat(pendenciasDTO1).isNotEqualTo(pendenciasDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(pendenciasMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(pendenciasMapper.fromId(null)).isNull();
    }
}
