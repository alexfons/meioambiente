package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Rodovia;
import br.com.homemade.repository.RodoviaRepository;
import br.com.homemade.service.dto.RodoviaDTO;
import br.com.homemade.service.mapper.RodoviaMapper;
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
 * Test class for the RodoviaResource REST controller.
 *
 * @see RodoviaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class RodoviaResourceIntTest {

    @Autowired
    private RodoviaRepository rodoviaRepository;

    @Autowired
    private RodoviaMapper rodoviaMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRodoviaMockMvc;

    private Rodovia rodovia;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RodoviaResource rodoviaResource = new RodoviaResource(rodoviaRepository, rodoviaMapper);
        this.restRodoviaMockMvc = MockMvcBuilders.standaloneSetup(rodoviaResource)
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
    public static Rodovia createEntity(EntityManager em) {
        Rodovia rodovia = new Rodovia();
        return rodovia;
    }

    @Before
    public void initTest() {
        rodovia = createEntity(em);
    }

    @Test
    @Transactional
    public void createRodovia() throws Exception {
        int databaseSizeBeforeCreate = rodoviaRepository.findAll().size();

        // Create the Rodovia
        RodoviaDTO rodoviaDTO = rodoviaMapper.toDto(rodovia);
        restRodoviaMockMvc.perform(post("/api/rodovias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rodoviaDTO)))
            .andExpect(status().isCreated());

        // Validate the Rodovia in the database
        List<Rodovia> rodoviaList = rodoviaRepository.findAll();
        assertThat(rodoviaList).hasSize(databaseSizeBeforeCreate + 1);
        Rodovia testRodovia = rodoviaList.get(rodoviaList.size() - 1);
    }

    @Test
    @Transactional
    public void createRodoviaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rodoviaRepository.findAll().size();

        // Create the Rodovia with an existing ID
        rodovia.setId(1L);
        RodoviaDTO rodoviaDTO = rodoviaMapper.toDto(rodovia);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRodoviaMockMvc.perform(post("/api/rodovias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rodoviaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Rodovia> rodoviaList = rodoviaRepository.findAll();
        assertThat(rodoviaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRodovias() throws Exception {
        // Initialize the database
        rodoviaRepository.saveAndFlush(rodovia);

        // Get all the rodoviaList
        restRodoviaMockMvc.perform(get("/api/rodovias?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rodovia.getId().intValue())));
    }

    @Test
    @Transactional
    public void getRodovia() throws Exception {
        // Initialize the database
        rodoviaRepository.saveAndFlush(rodovia);

        // Get the rodovia
        restRodoviaMockMvc.perform(get("/api/rodovias/{id}", rodovia.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(rodovia.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingRodovia() throws Exception {
        // Get the rodovia
        restRodoviaMockMvc.perform(get("/api/rodovias/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRodovia() throws Exception {
        // Initialize the database
        rodoviaRepository.saveAndFlush(rodovia);
        int databaseSizeBeforeUpdate = rodoviaRepository.findAll().size();

        // Update the rodovia
        Rodovia updatedRodovia = rodoviaRepository.findOne(rodovia.getId());
        RodoviaDTO rodoviaDTO = rodoviaMapper.toDto(updatedRodovia);

        restRodoviaMockMvc.perform(put("/api/rodovias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rodoviaDTO)))
            .andExpect(status().isOk());

        // Validate the Rodovia in the database
        List<Rodovia> rodoviaList = rodoviaRepository.findAll();
        assertThat(rodoviaList).hasSize(databaseSizeBeforeUpdate);
        Rodovia testRodovia = rodoviaList.get(rodoviaList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingRodovia() throws Exception {
        int databaseSizeBeforeUpdate = rodoviaRepository.findAll().size();

        // Create the Rodovia
        RodoviaDTO rodoviaDTO = rodoviaMapper.toDto(rodovia);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRodoviaMockMvc.perform(put("/api/rodovias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rodoviaDTO)))
            .andExpect(status().isCreated());

        // Validate the Rodovia in the database
        List<Rodovia> rodoviaList = rodoviaRepository.findAll();
        assertThat(rodoviaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRodovia() throws Exception {
        // Initialize the database
        rodoviaRepository.saveAndFlush(rodovia);
        int databaseSizeBeforeDelete = rodoviaRepository.findAll().size();

        // Get the rodovia
        restRodoviaMockMvc.perform(delete("/api/rodovias/{id}", rodovia.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Rodovia> rodoviaList = rodoviaRepository.findAll();
        assertThat(rodoviaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Rodovia.class);
        Rodovia rodovia1 = new Rodovia();
        rodovia1.setId(1L);
        Rodovia rodovia2 = new Rodovia();
        rodovia2.setId(rodovia1.getId());
        assertThat(rodovia1).isEqualTo(rodovia2);
        rodovia2.setId(2L);
        assertThat(rodovia1).isNotEqualTo(rodovia2);
        rodovia1.setId(null);
        assertThat(rodovia1).isNotEqualTo(rodovia2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RodoviaDTO.class);
        RodoviaDTO rodoviaDTO1 = new RodoviaDTO();
        rodoviaDTO1.setId(1L);
        RodoviaDTO rodoviaDTO2 = new RodoviaDTO();
        assertThat(rodoviaDTO1).isNotEqualTo(rodoviaDTO2);
        rodoviaDTO2.setId(rodoviaDTO1.getId());
        assertThat(rodoviaDTO1).isEqualTo(rodoviaDTO2);
        rodoviaDTO2.setId(2L);
        assertThat(rodoviaDTO1).isNotEqualTo(rodoviaDTO2);
        rodoviaDTO1.setId(null);
        assertThat(rodoviaDTO1).isNotEqualTo(rodoviaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(rodoviaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(rodoviaMapper.fromId(null)).isNull();
    }
}
