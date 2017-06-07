package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Log;
import br.com.homemade.repository.LogRepository;
import br.com.homemade.service.dto.LogDTO;
import br.com.homemade.service.mapper.LogMapper;
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
 * Test class for the LogResource REST controller.
 *
 * @see LogResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class LogResourceIntTest {

    private static final ZonedDateTime DEFAULT_DATA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final String DEFAULT_METODO = "AAAAAAAAAA";
    private static final String UPDATED_METODO = "BBBBBBBBBB";

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLogMockMvc;

    private Log log;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        LogResource logResource = new LogResource(logRepository, logMapper);
        this.restLogMockMvc = MockMvcBuilders.standaloneSetup(logResource)
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
    public static Log createEntity(EntityManager em) {
        Log log = new Log()
            .data(DEFAULT_DATA)
            .descricao(DEFAULT_DESCRICAO)
            .metodo(DEFAULT_METODO);
        return log;
    }

    @Before
    public void initTest() {
        log = createEntity(em);
    }

    @Test
    @Transactional
    public void createLog() throws Exception {
        int databaseSizeBeforeCreate = logRepository.findAll().size();

        // Create the Log
        LogDTO logDTO = logMapper.toDto(log);
        restLogMockMvc.perform(post("/api/logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(logDTO)))
            .andExpect(status().isCreated());

        // Validate the Log in the database
        List<Log> logList = logRepository.findAll();
        assertThat(logList).hasSize(databaseSizeBeforeCreate + 1);
        Log testLog = logList.get(logList.size() - 1);
        assertThat(testLog.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testLog.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testLog.getMetodo()).isEqualTo(DEFAULT_METODO);
    }

    @Test
    @Transactional
    public void createLogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = logRepository.findAll().size();

        // Create the Log with an existing ID
        log.setId(1L);
        LogDTO logDTO = logMapper.toDto(log);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLogMockMvc.perform(post("/api/logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(logDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Log> logList = logRepository.findAll();
        assertThat(logList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllLogs() throws Exception {
        // Initialize the database
        logRepository.saveAndFlush(log);

        // Get all the logList
        restLogMockMvc.perform(get("/api/logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(log.getId().intValue())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(sameInstant(DEFAULT_DATA))))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].metodo").value(hasItem(DEFAULT_METODO.toString())));
    }

    @Test
    @Transactional
    public void getLog() throws Exception {
        // Initialize the database
        logRepository.saveAndFlush(log);

        // Get the log
        restLogMockMvc.perform(get("/api/logs/{id}", log.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(log.getId().intValue()))
            .andExpect(jsonPath("$.data").value(sameInstant(DEFAULT_DATA)))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.metodo").value(DEFAULT_METODO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLog() throws Exception {
        // Get the log
        restLogMockMvc.perform(get("/api/logs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLog() throws Exception {
        // Initialize the database
        logRepository.saveAndFlush(log);
        int databaseSizeBeforeUpdate = logRepository.findAll().size();

        // Update the log
        Log updatedLog = logRepository.findOne(log.getId());
        updatedLog
            .data(UPDATED_DATA)
            .descricao(UPDATED_DESCRICAO)
            .metodo(UPDATED_METODO);
        LogDTO logDTO = logMapper.toDto(updatedLog);

        restLogMockMvc.perform(put("/api/logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(logDTO)))
            .andExpect(status().isOk());

        // Validate the Log in the database
        List<Log> logList = logRepository.findAll();
        assertThat(logList).hasSize(databaseSizeBeforeUpdate);
        Log testLog = logList.get(logList.size() - 1);
        assertThat(testLog.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testLog.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testLog.getMetodo()).isEqualTo(UPDATED_METODO);
    }

    @Test
    @Transactional
    public void updateNonExistingLog() throws Exception {
        int databaseSizeBeforeUpdate = logRepository.findAll().size();

        // Create the Log
        LogDTO logDTO = logMapper.toDto(log);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restLogMockMvc.perform(put("/api/logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(logDTO)))
            .andExpect(status().isCreated());

        // Validate the Log in the database
        List<Log> logList = logRepository.findAll();
        assertThat(logList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteLog() throws Exception {
        // Initialize the database
        logRepository.saveAndFlush(log);
        int databaseSizeBeforeDelete = logRepository.findAll().size();

        // Get the log
        restLogMockMvc.perform(delete("/api/logs/{id}", log.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Log> logList = logRepository.findAll();
        assertThat(logList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Log.class);
        Log log1 = new Log();
        log1.setId(1L);
        Log log2 = new Log();
        log2.setId(log1.getId());
        assertThat(log1).isEqualTo(log2);
        log2.setId(2L);
        assertThat(log1).isNotEqualTo(log2);
        log1.setId(null);
        assertThat(log1).isNotEqualTo(log2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LogDTO.class);
        LogDTO logDTO1 = new LogDTO();
        logDTO1.setId(1L);
        LogDTO logDTO2 = new LogDTO();
        assertThat(logDTO1).isNotEqualTo(logDTO2);
        logDTO2.setId(logDTO1.getId());
        assertThat(logDTO1).isEqualTo(logDTO2);
        logDTO2.setId(2L);
        assertThat(logDTO1).isNotEqualTo(logDTO2);
        logDTO1.setId(null);
        assertThat(logDTO1).isNotEqualTo(logDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(logMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(logMapper.fromId(null)).isNull();
    }
}
