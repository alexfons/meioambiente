package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Ocorrenciapendencias;
import br.com.homemade.repository.OcorrenciapendenciasRepository;
import br.com.homemade.service.dto.OcorrenciapendenciasDTO;
import br.com.homemade.service.mapper.OcorrenciapendenciasMapper;
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
 * Test class for the OcorrenciapendenciasResource REST controller.
 *
 * @see OcorrenciapendenciasResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class OcorrenciapendenciasResourceIntTest {

    private static final String DEFAULT_ENQUADRAMENTO = "AAAAAAAAAA";
    private static final String UPDATED_ENQUADRAMENTO = "BBBBBBBBBB";

    @Autowired
    private OcorrenciapendenciasRepository ocorrenciapendenciasRepository;

    @Autowired
    private OcorrenciapendenciasMapper ocorrenciapendenciasMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOcorrenciapendenciasMockMvc;

    private Ocorrenciapendencias ocorrenciapendencias;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        OcorrenciapendenciasResource ocorrenciapendenciasResource = new OcorrenciapendenciasResource(ocorrenciapendenciasRepository, ocorrenciapendenciasMapper);
        this.restOcorrenciapendenciasMockMvc = MockMvcBuilders.standaloneSetup(ocorrenciapendenciasResource)
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
    public static Ocorrenciapendencias createEntity(EntityManager em) {
        Ocorrenciapendencias ocorrenciapendencias = new Ocorrenciapendencias()
            .enquadramento(DEFAULT_ENQUADRAMENTO);
        return ocorrenciapendencias;
    }

    @Before
    public void initTest() {
        ocorrenciapendencias = createEntity(em);
    }

    @Test
    @Transactional
    public void createOcorrenciapendencias() throws Exception {
        int databaseSizeBeforeCreate = ocorrenciapendenciasRepository.findAll().size();

        // Create the Ocorrenciapendencias
        OcorrenciapendenciasDTO ocorrenciapendenciasDTO = ocorrenciapendenciasMapper.toDto(ocorrenciapendencias);
        restOcorrenciapendenciasMockMvc.perform(post("/api/ocorrenciapendencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ocorrenciapendenciasDTO)))
            .andExpect(status().isCreated());

        // Validate the Ocorrenciapendencias in the database
        List<Ocorrenciapendencias> ocorrenciapendenciasList = ocorrenciapendenciasRepository.findAll();
        assertThat(ocorrenciapendenciasList).hasSize(databaseSizeBeforeCreate + 1);
        Ocorrenciapendencias testOcorrenciapendencias = ocorrenciapendenciasList.get(ocorrenciapendenciasList.size() - 1);
        assertThat(testOcorrenciapendencias.getEnquadramento()).isEqualTo(DEFAULT_ENQUADRAMENTO);
    }

    @Test
    @Transactional
    public void createOcorrenciapendenciasWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ocorrenciapendenciasRepository.findAll().size();

        // Create the Ocorrenciapendencias with an existing ID
        ocorrenciapendencias.setId(1L);
        OcorrenciapendenciasDTO ocorrenciapendenciasDTO = ocorrenciapendenciasMapper.toDto(ocorrenciapendencias);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOcorrenciapendenciasMockMvc.perform(post("/api/ocorrenciapendencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ocorrenciapendenciasDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Ocorrenciapendencias> ocorrenciapendenciasList = ocorrenciapendenciasRepository.findAll();
        assertThat(ocorrenciapendenciasList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOcorrenciapendencias() throws Exception {
        // Initialize the database
        ocorrenciapendenciasRepository.saveAndFlush(ocorrenciapendencias);

        // Get all the ocorrenciapendenciasList
        restOcorrenciapendenciasMockMvc.perform(get("/api/ocorrenciapendencias?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ocorrenciapendencias.getId().intValue())))
            .andExpect(jsonPath("$.[*].enquadramento").value(hasItem(DEFAULT_ENQUADRAMENTO.toString())));
    }

    @Test
    @Transactional
    public void getOcorrenciapendencias() throws Exception {
        // Initialize the database
        ocorrenciapendenciasRepository.saveAndFlush(ocorrenciapendencias);

        // Get the ocorrenciapendencias
        restOcorrenciapendenciasMockMvc.perform(get("/api/ocorrenciapendencias/{id}", ocorrenciapendencias.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ocorrenciapendencias.getId().intValue()))
            .andExpect(jsonPath("$.enquadramento").value(DEFAULT_ENQUADRAMENTO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOcorrenciapendencias() throws Exception {
        // Get the ocorrenciapendencias
        restOcorrenciapendenciasMockMvc.perform(get("/api/ocorrenciapendencias/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOcorrenciapendencias() throws Exception {
        // Initialize the database
        ocorrenciapendenciasRepository.saveAndFlush(ocorrenciapendencias);
        int databaseSizeBeforeUpdate = ocorrenciapendenciasRepository.findAll().size();

        // Update the ocorrenciapendencias
        Ocorrenciapendencias updatedOcorrenciapendencias = ocorrenciapendenciasRepository.findOne(ocorrenciapendencias.getId());
        updatedOcorrenciapendencias
            .enquadramento(UPDATED_ENQUADRAMENTO);
        OcorrenciapendenciasDTO ocorrenciapendenciasDTO = ocorrenciapendenciasMapper.toDto(updatedOcorrenciapendencias);

        restOcorrenciapendenciasMockMvc.perform(put("/api/ocorrenciapendencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ocorrenciapendenciasDTO)))
            .andExpect(status().isOk());

        // Validate the Ocorrenciapendencias in the database
        List<Ocorrenciapendencias> ocorrenciapendenciasList = ocorrenciapendenciasRepository.findAll();
        assertThat(ocorrenciapendenciasList).hasSize(databaseSizeBeforeUpdate);
        Ocorrenciapendencias testOcorrenciapendencias = ocorrenciapendenciasList.get(ocorrenciapendenciasList.size() - 1);
        assertThat(testOcorrenciapendencias.getEnquadramento()).isEqualTo(UPDATED_ENQUADRAMENTO);
    }

    @Test
    @Transactional
    public void updateNonExistingOcorrenciapendencias() throws Exception {
        int databaseSizeBeforeUpdate = ocorrenciapendenciasRepository.findAll().size();

        // Create the Ocorrenciapendencias
        OcorrenciapendenciasDTO ocorrenciapendenciasDTO = ocorrenciapendenciasMapper.toDto(ocorrenciapendencias);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restOcorrenciapendenciasMockMvc.perform(put("/api/ocorrenciapendencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ocorrenciapendenciasDTO)))
            .andExpect(status().isCreated());

        // Validate the Ocorrenciapendencias in the database
        List<Ocorrenciapendencias> ocorrenciapendenciasList = ocorrenciapendenciasRepository.findAll();
        assertThat(ocorrenciapendenciasList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteOcorrenciapendencias() throws Exception {
        // Initialize the database
        ocorrenciapendenciasRepository.saveAndFlush(ocorrenciapendencias);
        int databaseSizeBeforeDelete = ocorrenciapendenciasRepository.findAll().size();

        // Get the ocorrenciapendencias
        restOcorrenciapendenciasMockMvc.perform(delete("/api/ocorrenciapendencias/{id}", ocorrenciapendencias.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Ocorrenciapendencias> ocorrenciapendenciasList = ocorrenciapendenciasRepository.findAll();
        assertThat(ocorrenciapendenciasList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Ocorrenciapendencias.class);
        Ocorrenciapendencias ocorrenciapendencias1 = new Ocorrenciapendencias();
        ocorrenciapendencias1.setId(1L);
        Ocorrenciapendencias ocorrenciapendencias2 = new Ocorrenciapendencias();
        ocorrenciapendencias2.setId(ocorrenciapendencias1.getId());
        assertThat(ocorrenciapendencias1).isEqualTo(ocorrenciapendencias2);
        ocorrenciapendencias2.setId(2L);
        assertThat(ocorrenciapendencias1).isNotEqualTo(ocorrenciapendencias2);
        ocorrenciapendencias1.setId(null);
        assertThat(ocorrenciapendencias1).isNotEqualTo(ocorrenciapendencias2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OcorrenciapendenciasDTO.class);
        OcorrenciapendenciasDTO ocorrenciapendenciasDTO1 = new OcorrenciapendenciasDTO();
        ocorrenciapendenciasDTO1.setId(1L);
        OcorrenciapendenciasDTO ocorrenciapendenciasDTO2 = new OcorrenciapendenciasDTO();
        assertThat(ocorrenciapendenciasDTO1).isNotEqualTo(ocorrenciapendenciasDTO2);
        ocorrenciapendenciasDTO2.setId(ocorrenciapendenciasDTO1.getId());
        assertThat(ocorrenciapendenciasDTO1).isEqualTo(ocorrenciapendenciasDTO2);
        ocorrenciapendenciasDTO2.setId(2L);
        assertThat(ocorrenciapendenciasDTO1).isNotEqualTo(ocorrenciapendenciasDTO2);
        ocorrenciapendenciasDTO1.setId(null);
        assertThat(ocorrenciapendenciasDTO1).isNotEqualTo(ocorrenciapendenciasDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(ocorrenciapendenciasMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(ocorrenciapendenciasMapper.fromId(null)).isNull();
    }
}
