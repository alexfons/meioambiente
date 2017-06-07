package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Natureza;
import br.com.homemade.repository.NaturezaRepository;
import br.com.homemade.service.dto.NaturezaDTO;
import br.com.homemade.service.mapper.NaturezaMapper;
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
 * Test class for the NaturezaResource REST controller.
 *
 * @see NaturezaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class NaturezaResourceIntTest {

    @Autowired
    private NaturezaRepository naturezaRepository;

    @Autowired
    private NaturezaMapper naturezaMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restNaturezaMockMvc;

    private Natureza natureza;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        NaturezaResource naturezaResource = new NaturezaResource(naturezaRepository, naturezaMapper);
        this.restNaturezaMockMvc = MockMvcBuilders.standaloneSetup(naturezaResource)
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
    public static Natureza createEntity(EntityManager em) {
        Natureza natureza = new Natureza();
        return natureza;
    }

    @Before
    public void initTest() {
        natureza = createEntity(em);
    }

    @Test
    @Transactional
    public void createNatureza() throws Exception {
        int databaseSizeBeforeCreate = naturezaRepository.findAll().size();

        // Create the Natureza
        NaturezaDTO naturezaDTO = naturezaMapper.toDto(natureza);
        restNaturezaMockMvc.perform(post("/api/naturezas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(naturezaDTO)))
            .andExpect(status().isCreated());

        // Validate the Natureza in the database
        List<Natureza> naturezaList = naturezaRepository.findAll();
        assertThat(naturezaList).hasSize(databaseSizeBeforeCreate + 1);
        Natureza testNatureza = naturezaList.get(naturezaList.size() - 1);
    }

    @Test
    @Transactional
    public void createNaturezaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = naturezaRepository.findAll().size();

        // Create the Natureza with an existing ID
        natureza.setId(1L);
        NaturezaDTO naturezaDTO = naturezaMapper.toDto(natureza);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNaturezaMockMvc.perform(post("/api/naturezas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(naturezaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Natureza> naturezaList = naturezaRepository.findAll();
        assertThat(naturezaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllNaturezas() throws Exception {
        // Initialize the database
        naturezaRepository.saveAndFlush(natureza);

        // Get all the naturezaList
        restNaturezaMockMvc.perform(get("/api/naturezas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(natureza.getId().intValue())));
    }

    @Test
    @Transactional
    public void getNatureza() throws Exception {
        // Initialize the database
        naturezaRepository.saveAndFlush(natureza);

        // Get the natureza
        restNaturezaMockMvc.perform(get("/api/naturezas/{id}", natureza.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(natureza.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingNatureza() throws Exception {
        // Get the natureza
        restNaturezaMockMvc.perform(get("/api/naturezas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNatureza() throws Exception {
        // Initialize the database
        naturezaRepository.saveAndFlush(natureza);
        int databaseSizeBeforeUpdate = naturezaRepository.findAll().size();

        // Update the natureza
        Natureza updatedNatureza = naturezaRepository.findOne(natureza.getId());
        NaturezaDTO naturezaDTO = naturezaMapper.toDto(updatedNatureza);

        restNaturezaMockMvc.perform(put("/api/naturezas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(naturezaDTO)))
            .andExpect(status().isOk());

        // Validate the Natureza in the database
        List<Natureza> naturezaList = naturezaRepository.findAll();
        assertThat(naturezaList).hasSize(databaseSizeBeforeUpdate);
        Natureza testNatureza = naturezaList.get(naturezaList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingNatureza() throws Exception {
        int databaseSizeBeforeUpdate = naturezaRepository.findAll().size();

        // Create the Natureza
        NaturezaDTO naturezaDTO = naturezaMapper.toDto(natureza);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restNaturezaMockMvc.perform(put("/api/naturezas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(naturezaDTO)))
            .andExpect(status().isCreated());

        // Validate the Natureza in the database
        List<Natureza> naturezaList = naturezaRepository.findAll();
        assertThat(naturezaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteNatureza() throws Exception {
        // Initialize the database
        naturezaRepository.saveAndFlush(natureza);
        int databaseSizeBeforeDelete = naturezaRepository.findAll().size();

        // Get the natureza
        restNaturezaMockMvc.perform(delete("/api/naturezas/{id}", natureza.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Natureza> naturezaList = naturezaRepository.findAll();
        assertThat(naturezaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Natureza.class);
        Natureza natureza1 = new Natureza();
        natureza1.setId(1L);
        Natureza natureza2 = new Natureza();
        natureza2.setId(natureza1.getId());
        assertThat(natureza1).isEqualTo(natureza2);
        natureza2.setId(2L);
        assertThat(natureza1).isNotEqualTo(natureza2);
        natureza1.setId(null);
        assertThat(natureza1).isNotEqualTo(natureza2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NaturezaDTO.class);
        NaturezaDTO naturezaDTO1 = new NaturezaDTO();
        naturezaDTO1.setId(1L);
        NaturezaDTO naturezaDTO2 = new NaturezaDTO();
        assertThat(naturezaDTO1).isNotEqualTo(naturezaDTO2);
        naturezaDTO2.setId(naturezaDTO1.getId());
        assertThat(naturezaDTO1).isEqualTo(naturezaDTO2);
        naturezaDTO2.setId(2L);
        assertThat(naturezaDTO1).isNotEqualTo(naturezaDTO2);
        naturezaDTO1.setId(null);
        assertThat(naturezaDTO1).isNotEqualTo(naturezaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(naturezaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(naturezaMapper.fromId(null)).isNull();
    }
}
