package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Trecho;
import br.com.homemade.repository.TrechoRepository;
import br.com.homemade.service.dto.TrechoDTO;
import br.com.homemade.service.mapper.TrechoMapper;
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
 * Test class for the TrechoResource REST controller.
 *
 * @see TrechoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class TrechoResourceIntTest {

    @Autowired
    private TrechoRepository trechoRepository;

    @Autowired
    private TrechoMapper trechoMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTrechoMockMvc;

    private Trecho trecho;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TrechoResource trechoResource = new TrechoResource(trechoRepository, trechoMapper);
        this.restTrechoMockMvc = MockMvcBuilders.standaloneSetup(trechoResource)
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
    public static Trecho createEntity(EntityManager em) {
        Trecho trecho = new Trecho();
        return trecho;
    }

    @Before
    public void initTest() {
        trecho = createEntity(em);
    }

    @Test
    @Transactional
    public void createTrecho() throws Exception {
        int databaseSizeBeforeCreate = trechoRepository.findAll().size();

        // Create the Trecho
        TrechoDTO trechoDTO = trechoMapper.toDto(trecho);
        restTrechoMockMvc.perform(post("/api/trechos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trechoDTO)))
            .andExpect(status().isCreated());

        // Validate the Trecho in the database
        List<Trecho> trechoList = trechoRepository.findAll();
        assertThat(trechoList).hasSize(databaseSizeBeforeCreate + 1);
        Trecho testTrecho = trechoList.get(trechoList.size() - 1);
    }

    @Test
    @Transactional
    public void createTrechoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = trechoRepository.findAll().size();

        // Create the Trecho with an existing ID
        trecho.setId(1L);
        TrechoDTO trechoDTO = trechoMapper.toDto(trecho);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTrechoMockMvc.perform(post("/api/trechos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trechoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Trecho> trechoList = trechoRepository.findAll();
        assertThat(trechoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTrechos() throws Exception {
        // Initialize the database
        trechoRepository.saveAndFlush(trecho);

        // Get all the trechoList
        restTrechoMockMvc.perform(get("/api/trechos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(trecho.getId().intValue())));
    }

    @Test
    @Transactional
    public void getTrecho() throws Exception {
        // Initialize the database
        trechoRepository.saveAndFlush(trecho);

        // Get the trecho
        restTrechoMockMvc.perform(get("/api/trechos/{id}", trecho.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(trecho.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTrecho() throws Exception {
        // Get the trecho
        restTrechoMockMvc.perform(get("/api/trechos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTrecho() throws Exception {
        // Initialize the database
        trechoRepository.saveAndFlush(trecho);
        int databaseSizeBeforeUpdate = trechoRepository.findAll().size();

        // Update the trecho
        Trecho updatedTrecho = trechoRepository.findOne(trecho.getId());
        TrechoDTO trechoDTO = trechoMapper.toDto(updatedTrecho);

        restTrechoMockMvc.perform(put("/api/trechos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trechoDTO)))
            .andExpect(status().isOk());

        // Validate the Trecho in the database
        List<Trecho> trechoList = trechoRepository.findAll();
        assertThat(trechoList).hasSize(databaseSizeBeforeUpdate);
        Trecho testTrecho = trechoList.get(trechoList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingTrecho() throws Exception {
        int databaseSizeBeforeUpdate = trechoRepository.findAll().size();

        // Create the Trecho
        TrechoDTO trechoDTO = trechoMapper.toDto(trecho);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTrechoMockMvc.perform(put("/api/trechos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trechoDTO)))
            .andExpect(status().isCreated());

        // Validate the Trecho in the database
        List<Trecho> trechoList = trechoRepository.findAll();
        assertThat(trechoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTrecho() throws Exception {
        // Initialize the database
        trechoRepository.saveAndFlush(trecho);
        int databaseSizeBeforeDelete = trechoRepository.findAll().size();

        // Get the trecho
        restTrechoMockMvc.perform(delete("/api/trechos/{id}", trecho.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Trecho> trechoList = trechoRepository.findAll();
        assertThat(trechoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Trecho.class);
        Trecho trecho1 = new Trecho();
        trecho1.setId(1L);
        Trecho trecho2 = new Trecho();
        trecho2.setId(trecho1.getId());
        assertThat(trecho1).isEqualTo(trecho2);
        trecho2.setId(2L);
        assertThat(trecho1).isNotEqualTo(trecho2);
        trecho1.setId(null);
        assertThat(trecho1).isNotEqualTo(trecho2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TrechoDTO.class);
        TrechoDTO trechoDTO1 = new TrechoDTO();
        trechoDTO1.setId(1L);
        TrechoDTO trechoDTO2 = new TrechoDTO();
        assertThat(trechoDTO1).isNotEqualTo(trechoDTO2);
        trechoDTO2.setId(trechoDTO1.getId());
        assertThat(trechoDTO1).isEqualTo(trechoDTO2);
        trechoDTO2.setId(2L);
        assertThat(trechoDTO1).isNotEqualTo(trechoDTO2);
        trechoDTO1.setId(null);
        assertThat(trechoDTO1).isNotEqualTo(trechoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(trechoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(trechoMapper.fromId(null)).isNull();
    }
}
