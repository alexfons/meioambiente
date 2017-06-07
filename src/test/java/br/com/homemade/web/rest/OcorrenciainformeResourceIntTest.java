package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Ocorrenciainforme;
import br.com.homemade.repository.OcorrenciainformeRepository;
import br.com.homemade.service.dto.OcorrenciainformeDTO;
import br.com.homemade.service.mapper.OcorrenciainformeMapper;
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
 * Test class for the OcorrenciainformeResource REST controller.
 *
 * @see OcorrenciainformeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class OcorrenciainformeResourceIntTest {

    @Autowired
    private OcorrenciainformeRepository ocorrenciainformeRepository;

    @Autowired
    private OcorrenciainformeMapper ocorrenciainformeMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOcorrenciainformeMockMvc;

    private Ocorrenciainforme ocorrenciainforme;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        OcorrenciainformeResource ocorrenciainformeResource = new OcorrenciainformeResource(ocorrenciainformeRepository, ocorrenciainformeMapper);
        this.restOcorrenciainformeMockMvc = MockMvcBuilders.standaloneSetup(ocorrenciainformeResource)
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
    public static Ocorrenciainforme createEntity(EntityManager em) {
        Ocorrenciainforme ocorrenciainforme = new Ocorrenciainforme();
        return ocorrenciainforme;
    }

    @Before
    public void initTest() {
        ocorrenciainforme = createEntity(em);
    }

    @Test
    @Transactional
    public void createOcorrenciainforme() throws Exception {
        int databaseSizeBeforeCreate = ocorrenciainformeRepository.findAll().size();

        // Create the Ocorrenciainforme
        OcorrenciainformeDTO ocorrenciainformeDTO = ocorrenciainformeMapper.toDto(ocorrenciainforme);
        restOcorrenciainformeMockMvc.perform(post("/api/ocorrenciainformes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ocorrenciainformeDTO)))
            .andExpect(status().isCreated());

        // Validate the Ocorrenciainforme in the database
        List<Ocorrenciainforme> ocorrenciainformeList = ocorrenciainformeRepository.findAll();
        assertThat(ocorrenciainformeList).hasSize(databaseSizeBeforeCreate + 1);
        Ocorrenciainforme testOcorrenciainforme = ocorrenciainformeList.get(ocorrenciainformeList.size() - 1);
    }

    @Test
    @Transactional
    public void createOcorrenciainformeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ocorrenciainformeRepository.findAll().size();

        // Create the Ocorrenciainforme with an existing ID
        ocorrenciainforme.setId(1L);
        OcorrenciainformeDTO ocorrenciainformeDTO = ocorrenciainformeMapper.toDto(ocorrenciainforme);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOcorrenciainformeMockMvc.perform(post("/api/ocorrenciainformes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ocorrenciainformeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Ocorrenciainforme> ocorrenciainformeList = ocorrenciainformeRepository.findAll();
        assertThat(ocorrenciainformeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOcorrenciainformes() throws Exception {
        // Initialize the database
        ocorrenciainformeRepository.saveAndFlush(ocorrenciainforme);

        // Get all the ocorrenciainformeList
        restOcorrenciainformeMockMvc.perform(get("/api/ocorrenciainformes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ocorrenciainforme.getId().intValue())));
    }

    @Test
    @Transactional
    public void getOcorrenciainforme() throws Exception {
        // Initialize the database
        ocorrenciainformeRepository.saveAndFlush(ocorrenciainforme);

        // Get the ocorrenciainforme
        restOcorrenciainformeMockMvc.perform(get("/api/ocorrenciainformes/{id}", ocorrenciainforme.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ocorrenciainforme.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingOcorrenciainforme() throws Exception {
        // Get the ocorrenciainforme
        restOcorrenciainformeMockMvc.perform(get("/api/ocorrenciainformes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOcorrenciainforme() throws Exception {
        // Initialize the database
        ocorrenciainformeRepository.saveAndFlush(ocorrenciainforme);
        int databaseSizeBeforeUpdate = ocorrenciainformeRepository.findAll().size();

        // Update the ocorrenciainforme
        Ocorrenciainforme updatedOcorrenciainforme = ocorrenciainformeRepository.findOne(ocorrenciainforme.getId());
        OcorrenciainformeDTO ocorrenciainformeDTO = ocorrenciainformeMapper.toDto(updatedOcorrenciainforme);

        restOcorrenciainformeMockMvc.perform(put("/api/ocorrenciainformes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ocorrenciainformeDTO)))
            .andExpect(status().isOk());

        // Validate the Ocorrenciainforme in the database
        List<Ocorrenciainforme> ocorrenciainformeList = ocorrenciainformeRepository.findAll();
        assertThat(ocorrenciainformeList).hasSize(databaseSizeBeforeUpdate);
        Ocorrenciainforme testOcorrenciainforme = ocorrenciainformeList.get(ocorrenciainformeList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingOcorrenciainforme() throws Exception {
        int databaseSizeBeforeUpdate = ocorrenciainformeRepository.findAll().size();

        // Create the Ocorrenciainforme
        OcorrenciainformeDTO ocorrenciainformeDTO = ocorrenciainformeMapper.toDto(ocorrenciainforme);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restOcorrenciainformeMockMvc.perform(put("/api/ocorrenciainformes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ocorrenciainformeDTO)))
            .andExpect(status().isCreated());

        // Validate the Ocorrenciainforme in the database
        List<Ocorrenciainforme> ocorrenciainformeList = ocorrenciainformeRepository.findAll();
        assertThat(ocorrenciainformeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteOcorrenciainforme() throws Exception {
        // Initialize the database
        ocorrenciainformeRepository.saveAndFlush(ocorrenciainforme);
        int databaseSizeBeforeDelete = ocorrenciainformeRepository.findAll().size();

        // Get the ocorrenciainforme
        restOcorrenciainformeMockMvc.perform(delete("/api/ocorrenciainformes/{id}", ocorrenciainforme.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Ocorrenciainforme> ocorrenciainformeList = ocorrenciainformeRepository.findAll();
        assertThat(ocorrenciainformeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Ocorrenciainforme.class);
        Ocorrenciainforme ocorrenciainforme1 = new Ocorrenciainforme();
        ocorrenciainforme1.setId(1L);
        Ocorrenciainforme ocorrenciainforme2 = new Ocorrenciainforme();
        ocorrenciainforme2.setId(ocorrenciainforme1.getId());
        assertThat(ocorrenciainforme1).isEqualTo(ocorrenciainforme2);
        ocorrenciainforme2.setId(2L);
        assertThat(ocorrenciainforme1).isNotEqualTo(ocorrenciainforme2);
        ocorrenciainforme1.setId(null);
        assertThat(ocorrenciainforme1).isNotEqualTo(ocorrenciainforme2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OcorrenciainformeDTO.class);
        OcorrenciainformeDTO ocorrenciainformeDTO1 = new OcorrenciainformeDTO();
        ocorrenciainformeDTO1.setId(1L);
        OcorrenciainformeDTO ocorrenciainformeDTO2 = new OcorrenciainformeDTO();
        assertThat(ocorrenciainformeDTO1).isNotEqualTo(ocorrenciainformeDTO2);
        ocorrenciainformeDTO2.setId(ocorrenciainformeDTO1.getId());
        assertThat(ocorrenciainformeDTO1).isEqualTo(ocorrenciainformeDTO2);
        ocorrenciainformeDTO2.setId(2L);
        assertThat(ocorrenciainformeDTO1).isNotEqualTo(ocorrenciainformeDTO2);
        ocorrenciainformeDTO1.setId(null);
        assertThat(ocorrenciainformeDTO1).isNotEqualTo(ocorrenciainformeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(ocorrenciainformeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(ocorrenciainformeMapper.fromId(null)).isNull();
    }
}
