package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Informe;
import br.com.homemade.repository.InformeRepository;
import br.com.homemade.service.dto.InformeDTO;
import br.com.homemade.service.mapper.InformeMapper;
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
 * Test class for the InformeResource REST controller.
 *
 * @see InformeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class InformeResourceIntTest {

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
    private InformeRepository informeRepository;

    @Autowired
    private InformeMapper informeMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restInformeMockMvc;

    private Informe informe;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        InformeResource informeResource = new InformeResource(informeRepository, informeMapper);
        this.restInformeMockMvc = MockMvcBuilders.standaloneSetup(informeResource)
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
    public static Informe createEntity(EntityManager em) {
        Informe informe = new Informe()
            .data(DEFAULT_DATA)
            .datainspecao(DEFAULT_DATAINSPECAO)
            .notificacao(DEFAULT_NOTIFICACAO)
            .numero(DEFAULT_NUMERO)
            .obs(DEFAULT_OBS);
        return informe;
    }

    @Before
    public void initTest() {
        informe = createEntity(em);
    }

    @Test
    @Transactional
    public void createInforme() throws Exception {
        int databaseSizeBeforeCreate = informeRepository.findAll().size();

        // Create the Informe
        InformeDTO informeDTO = informeMapper.toDto(informe);
        restInformeMockMvc.perform(post("/api/informes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(informeDTO)))
            .andExpect(status().isCreated());

        // Validate the Informe in the database
        List<Informe> informeList = informeRepository.findAll();
        assertThat(informeList).hasSize(databaseSizeBeforeCreate + 1);
        Informe testInforme = informeList.get(informeList.size() - 1);
        assertThat(testInforme.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testInforme.getDatainspecao()).isEqualTo(DEFAULT_DATAINSPECAO);
        assertThat(testInforme.isNotificacao()).isEqualTo(DEFAULT_NOTIFICACAO);
        assertThat(testInforme.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testInforme.getObs()).isEqualTo(DEFAULT_OBS);
    }

    @Test
    @Transactional
    public void createInformeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = informeRepository.findAll().size();

        // Create the Informe with an existing ID
        informe.setId(1L);
        InformeDTO informeDTO = informeMapper.toDto(informe);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInformeMockMvc.perform(post("/api/informes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(informeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Informe> informeList = informeRepository.findAll();
        assertThat(informeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllInformes() throws Exception {
        // Initialize the database
        informeRepository.saveAndFlush(informe);

        // Get all the informeList
        restInformeMockMvc.perform(get("/api/informes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(informe.getId().intValue())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(sameInstant(DEFAULT_DATA))))
            .andExpect(jsonPath("$.[*].datainspecao").value(hasItem(sameInstant(DEFAULT_DATAINSPECAO))))
            .andExpect(jsonPath("$.[*].notificacao").value(hasItem(DEFAULT_NOTIFICACAO.booleanValue())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO)))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())));
    }

    @Test
    @Transactional
    public void getInforme() throws Exception {
        // Initialize the database
        informeRepository.saveAndFlush(informe);

        // Get the informe
        restInformeMockMvc.perform(get("/api/informes/{id}", informe.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(informe.getId().intValue()))
            .andExpect(jsonPath("$.data").value(sameInstant(DEFAULT_DATA)))
            .andExpect(jsonPath("$.datainspecao").value(sameInstant(DEFAULT_DATAINSPECAO)))
            .andExpect(jsonPath("$.notificacao").value(DEFAULT_NOTIFICACAO.booleanValue()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingInforme() throws Exception {
        // Get the informe
        restInformeMockMvc.perform(get("/api/informes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInforme() throws Exception {
        // Initialize the database
        informeRepository.saveAndFlush(informe);
        int databaseSizeBeforeUpdate = informeRepository.findAll().size();

        // Update the informe
        Informe updatedInforme = informeRepository.findOne(informe.getId());
        updatedInforme
            .data(UPDATED_DATA)
            .datainspecao(UPDATED_DATAINSPECAO)
            .notificacao(UPDATED_NOTIFICACAO)
            .numero(UPDATED_NUMERO)
            .obs(UPDATED_OBS);
        InformeDTO informeDTO = informeMapper.toDto(updatedInforme);

        restInformeMockMvc.perform(put("/api/informes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(informeDTO)))
            .andExpect(status().isOk());

        // Validate the Informe in the database
        List<Informe> informeList = informeRepository.findAll();
        assertThat(informeList).hasSize(databaseSizeBeforeUpdate);
        Informe testInforme = informeList.get(informeList.size() - 1);
        assertThat(testInforme.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testInforme.getDatainspecao()).isEqualTo(UPDATED_DATAINSPECAO);
        assertThat(testInforme.isNotificacao()).isEqualTo(UPDATED_NOTIFICACAO);
        assertThat(testInforme.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testInforme.getObs()).isEqualTo(UPDATED_OBS);
    }

    @Test
    @Transactional
    public void updateNonExistingInforme() throws Exception {
        int databaseSizeBeforeUpdate = informeRepository.findAll().size();

        // Create the Informe
        InformeDTO informeDTO = informeMapper.toDto(informe);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restInformeMockMvc.perform(put("/api/informes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(informeDTO)))
            .andExpect(status().isCreated());

        // Validate the Informe in the database
        List<Informe> informeList = informeRepository.findAll();
        assertThat(informeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteInforme() throws Exception {
        // Initialize the database
        informeRepository.saveAndFlush(informe);
        int databaseSizeBeforeDelete = informeRepository.findAll().size();

        // Get the informe
        restInformeMockMvc.perform(delete("/api/informes/{id}", informe.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Informe> informeList = informeRepository.findAll();
        assertThat(informeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Informe.class);
        Informe informe1 = new Informe();
        informe1.setId(1L);
        Informe informe2 = new Informe();
        informe2.setId(informe1.getId());
        assertThat(informe1).isEqualTo(informe2);
        informe2.setId(2L);
        assertThat(informe1).isNotEqualTo(informe2);
        informe1.setId(null);
        assertThat(informe1).isNotEqualTo(informe2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InformeDTO.class);
        InformeDTO informeDTO1 = new InformeDTO();
        informeDTO1.setId(1L);
        InformeDTO informeDTO2 = new InformeDTO();
        assertThat(informeDTO1).isNotEqualTo(informeDTO2);
        informeDTO2.setId(informeDTO1.getId());
        assertThat(informeDTO1).isEqualTo(informeDTO2);
        informeDTO2.setId(2L);
        assertThat(informeDTO1).isNotEqualTo(informeDTO2);
        informeDTO1.setId(null);
        assertThat(informeDTO1).isNotEqualTo(informeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(informeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(informeMapper.fromId(null)).isNull();
    }
}
