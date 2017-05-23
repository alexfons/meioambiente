package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Historico;
import br.com.homemade.repository.HistoricoRepository;
import br.com.homemade.service.dto.HistoricoDTO;
import br.com.homemade.service.mapper.HistoricoMapper;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the HistoricoResource REST controller.
 *
 * @see HistoricoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class HistoricoResourceIntTest {

    @Autowired
    private HistoricoRepository historicoRepository;

    @Autowired
    private HistoricoMapper historicoMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restHistoricoMockMvc;

    private Historico historico;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        HistoricoResource historicoResource = new HistoricoResource(historicoRepository, historicoMapper);
        this.restHistoricoMockMvc = MockMvcBuilders.standaloneSetup(historicoResource)
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
    public static Historico createEntity(EntityManager em) {
        Historico historico = new Historico();
        return historico;
    }

    @Before
    public void initTest() {
        historico = createEntity(em);
    }

    @Test
    @Transactional
    public void createHistorico() throws Exception {
        int databaseSizeBeforeCreate = historicoRepository.findAll().size();

        // Create the Historico
        HistoricoDTO historicoDTO = historicoMapper.toDto(historico);
        restHistoricoMockMvc.perform(post("/api/historicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(historicoDTO)))
            .andExpect(status().isCreated());

        // Validate the Historico in the database
        List<Historico> historicoList = historicoRepository.findAll();
        assertThat(historicoList).hasSize(databaseSizeBeforeCreate + 1);
        Historico testHistorico = historicoList.get(historicoList.size() - 1);
    }

    @Test
    @Transactional
    public void createHistoricoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = historicoRepository.findAll().size();

        // Create the Historico with an existing ID
        historico.setId(1L);
        HistoricoDTO historicoDTO = historicoMapper.toDto(historico);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHistoricoMockMvc.perform(post("/api/historicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(historicoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Historico> historicoList = historicoRepository.findAll();
        assertThat(historicoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllHistoricos() throws Exception {
        // Initialize the database
        historicoRepository.saveAndFlush(historico);

        // Get all the historicoList
        restHistoricoMockMvc.perform(get("/api/historicos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(historico.getId().intValue())));
    }

    @Test
    @Transactional
    public void getHistorico() throws Exception {
        // Initialize the database
        historicoRepository.saveAndFlush(historico);

        // Get the historico
        restHistoricoMockMvc.perform(get("/api/historicos/{id}", historico.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(historico.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingHistorico() throws Exception {
        // Get the historico
        restHistoricoMockMvc.perform(get("/api/historicos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHistorico() throws Exception {
        // Initialize the database
        historicoRepository.saveAndFlush(historico);
        int databaseSizeBeforeUpdate = historicoRepository.findAll().size();

        // Update the historico
        Historico updatedHistorico = historicoRepository.findOne(historico.getId());
        HistoricoDTO historicoDTO = historicoMapper.toDto(updatedHistorico);

        restHistoricoMockMvc.perform(put("/api/historicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(historicoDTO)))
            .andExpect(status().isOk());

        // Validate the Historico in the database
        List<Historico> historicoList = historicoRepository.findAll();
        assertThat(historicoList).hasSize(databaseSizeBeforeUpdate);
        Historico testHistorico = historicoList.get(historicoList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingHistorico() throws Exception {
        int databaseSizeBeforeUpdate = historicoRepository.findAll().size();

        // Create the Historico
        HistoricoDTO historicoDTO = historicoMapper.toDto(historico);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restHistoricoMockMvc.perform(put("/api/historicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(historicoDTO)))
            .andExpect(status().isCreated());

        // Validate the Historico in the database
        List<Historico> historicoList = historicoRepository.findAll();
        assertThat(historicoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteHistorico() throws Exception {
        // Initialize the database
        historicoRepository.saveAndFlush(historico);
        int databaseSizeBeforeDelete = historicoRepository.findAll().size();

        // Get the historico
        restHistoricoMockMvc.perform(delete("/api/historicos/{id}", historico.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Historico> historicoList = historicoRepository.findAll();
        assertThat(historicoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Historico.class);
        Historico historico1 = new Historico();
        historico1.setId(1L);
        Historico historico2 = new Historico();
        historico2.setId(historico1.getId());
        assertThat(historico1).isEqualTo(historico2);
        historico2.setId(2L);
        assertThat(historico1).isNotEqualTo(historico2);
        historico1.setId(null);
        assertThat(historico1).isNotEqualTo(historico2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HistoricoDTO.class);
        HistoricoDTO historicoDTO1 = new HistoricoDTO();
        historicoDTO1.setId(1L);
        HistoricoDTO historicoDTO2 = new HistoricoDTO();
        assertThat(historicoDTO1).isNotEqualTo(historicoDTO2);
        historicoDTO2.setId(historicoDTO1.getId());
        assertThat(historicoDTO1).isEqualTo(historicoDTO2);
        historicoDTO2.setId(2L);
        assertThat(historicoDTO1).isNotEqualTo(historicoDTO2);
        historicoDTO1.setId(null);
        assertThat(historicoDTO1).isNotEqualTo(historicoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(historicoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(historicoMapper.fromId(null)).isNull();
    }
}
