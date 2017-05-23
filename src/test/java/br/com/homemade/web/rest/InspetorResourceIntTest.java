package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Inspetor;
import br.com.homemade.repository.InspetorRepository;
import br.com.homemade.service.dto.InspetorDTO;
import br.com.homemade.service.mapper.InspetorMapper;
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
 * Test class for the InspetorResource REST controller.
 *
 * @see InspetorResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class InspetorResourceIntTest {

    @Autowired
    private InspetorRepository inspetorRepository;

    @Autowired
    private InspetorMapper inspetorMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restInspetorMockMvc;

    private Inspetor inspetor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        InspetorResource inspetorResource = new InspetorResource(inspetorRepository, inspetorMapper);
        this.restInspetorMockMvc = MockMvcBuilders.standaloneSetup(inspetorResource)
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
    public static Inspetor createEntity(EntityManager em) {
        Inspetor inspetor = new Inspetor();
        return inspetor;
    }

    @Before
    public void initTest() {
        inspetor = createEntity(em);
    }

    @Test
    @Transactional
    public void createInspetor() throws Exception {
        int databaseSizeBeforeCreate = inspetorRepository.findAll().size();

        // Create the Inspetor
        InspetorDTO inspetorDTO = inspetorMapper.toDto(inspetor);
        restInspetorMockMvc.perform(post("/api/inspetors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inspetorDTO)))
            .andExpect(status().isCreated());

        // Validate the Inspetor in the database
        List<Inspetor> inspetorList = inspetorRepository.findAll();
        assertThat(inspetorList).hasSize(databaseSizeBeforeCreate + 1);
        Inspetor testInspetor = inspetorList.get(inspetorList.size() - 1);
    }

    @Test
    @Transactional
    public void createInspetorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = inspetorRepository.findAll().size();

        // Create the Inspetor with an existing ID
        inspetor.setId(1L);
        InspetorDTO inspetorDTO = inspetorMapper.toDto(inspetor);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInspetorMockMvc.perform(post("/api/inspetors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inspetorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Inspetor> inspetorList = inspetorRepository.findAll();
        assertThat(inspetorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllInspetors() throws Exception {
        // Initialize the database
        inspetorRepository.saveAndFlush(inspetor);

        // Get all the inspetorList
        restInspetorMockMvc.perform(get("/api/inspetors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(inspetor.getId().intValue())));
    }

    @Test
    @Transactional
    public void getInspetor() throws Exception {
        // Initialize the database
        inspetorRepository.saveAndFlush(inspetor);

        // Get the inspetor
        restInspetorMockMvc.perform(get("/api/inspetors/{id}", inspetor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(inspetor.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingInspetor() throws Exception {
        // Get the inspetor
        restInspetorMockMvc.perform(get("/api/inspetors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInspetor() throws Exception {
        // Initialize the database
        inspetorRepository.saveAndFlush(inspetor);
        int databaseSizeBeforeUpdate = inspetorRepository.findAll().size();

        // Update the inspetor
        Inspetor updatedInspetor = inspetorRepository.findOne(inspetor.getId());
        InspetorDTO inspetorDTO = inspetorMapper.toDto(updatedInspetor);

        restInspetorMockMvc.perform(put("/api/inspetors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inspetorDTO)))
            .andExpect(status().isOk());

        // Validate the Inspetor in the database
        List<Inspetor> inspetorList = inspetorRepository.findAll();
        assertThat(inspetorList).hasSize(databaseSizeBeforeUpdate);
        Inspetor testInspetor = inspetorList.get(inspetorList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingInspetor() throws Exception {
        int databaseSizeBeforeUpdate = inspetorRepository.findAll().size();

        // Create the Inspetor
        InspetorDTO inspetorDTO = inspetorMapper.toDto(inspetor);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restInspetorMockMvc.perform(put("/api/inspetors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inspetorDTO)))
            .andExpect(status().isCreated());

        // Validate the Inspetor in the database
        List<Inspetor> inspetorList = inspetorRepository.findAll();
        assertThat(inspetorList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteInspetor() throws Exception {
        // Initialize the database
        inspetorRepository.saveAndFlush(inspetor);
        int databaseSizeBeforeDelete = inspetorRepository.findAll().size();

        // Get the inspetor
        restInspetorMockMvc.perform(delete("/api/inspetors/{id}", inspetor.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Inspetor> inspetorList = inspetorRepository.findAll();
        assertThat(inspetorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Inspetor.class);
        Inspetor inspetor1 = new Inspetor();
        inspetor1.setId(1L);
        Inspetor inspetor2 = new Inspetor();
        inspetor2.setId(inspetor1.getId());
        assertThat(inspetor1).isEqualTo(inspetor2);
        inspetor2.setId(2L);
        assertThat(inspetor1).isNotEqualTo(inspetor2);
        inspetor1.setId(null);
        assertThat(inspetor1).isNotEqualTo(inspetor2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InspetorDTO.class);
        InspetorDTO inspetorDTO1 = new InspetorDTO();
        inspetorDTO1.setId(1L);
        InspetorDTO inspetorDTO2 = new InspetorDTO();
        assertThat(inspetorDTO1).isNotEqualTo(inspetorDTO2);
        inspetorDTO2.setId(inspetorDTO1.getId());
        assertThat(inspetorDTO1).isEqualTo(inspetorDTO2);
        inspetorDTO2.setId(2L);
        assertThat(inspetorDTO1).isNotEqualTo(inspetorDTO2);
        inspetorDTO1.setId(null);
        assertThat(inspetorDTO1).isNotEqualTo(inspetorDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(inspetorMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(inspetorMapper.fromId(null)).isNull();
    }
}
