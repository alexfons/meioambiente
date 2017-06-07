package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.OcorrenciaCertifIrreg;
import br.com.homemade.repository.OcorrenciaCertifIrregRepository;
import br.com.homemade.service.dto.OcorrenciaCertifIrregDTO;
import br.com.homemade.service.mapper.OcorrenciaCertifIrregMapper;
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
 * Test class for the OcorrenciaCertifIrregResource REST controller.
 *
 * @see OcorrenciaCertifIrregResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class OcorrenciaCertifIrregResourceIntTest {

    @Autowired
    private OcorrenciaCertifIrregRepository ocorrenciaCertifIrregRepository;

    @Autowired
    private OcorrenciaCertifIrregMapper ocorrenciaCertifIrregMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOcorrenciaCertifIrregMockMvc;

    private OcorrenciaCertifIrreg ocorrenciaCertifIrreg;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        OcorrenciaCertifIrregResource ocorrenciaCertifIrregResource = new OcorrenciaCertifIrregResource(ocorrenciaCertifIrregRepository, ocorrenciaCertifIrregMapper);
        this.restOcorrenciaCertifIrregMockMvc = MockMvcBuilders.standaloneSetup(ocorrenciaCertifIrregResource)
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
    public static OcorrenciaCertifIrreg createEntity(EntityManager em) {
        OcorrenciaCertifIrreg ocorrenciaCertifIrreg = new OcorrenciaCertifIrreg();
        return ocorrenciaCertifIrreg;
    }

    @Before
    public void initTest() {
        ocorrenciaCertifIrreg = createEntity(em);
    }

    @Test
    @Transactional
    public void createOcorrenciaCertifIrreg() throws Exception {
        int databaseSizeBeforeCreate = ocorrenciaCertifIrregRepository.findAll().size();

        // Create the OcorrenciaCertifIrreg
        OcorrenciaCertifIrregDTO ocorrenciaCertifIrregDTO = ocorrenciaCertifIrregMapper.toDto(ocorrenciaCertifIrreg);
        restOcorrenciaCertifIrregMockMvc.perform(post("/api/ocorrencia-certif-irregs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ocorrenciaCertifIrregDTO)))
            .andExpect(status().isCreated());

        // Validate the OcorrenciaCertifIrreg in the database
        List<OcorrenciaCertifIrreg> ocorrenciaCertifIrregList = ocorrenciaCertifIrregRepository.findAll();
        assertThat(ocorrenciaCertifIrregList).hasSize(databaseSizeBeforeCreate + 1);
        OcorrenciaCertifIrreg testOcorrenciaCertifIrreg = ocorrenciaCertifIrregList.get(ocorrenciaCertifIrregList.size() - 1);
    }

    @Test
    @Transactional
    public void createOcorrenciaCertifIrregWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ocorrenciaCertifIrregRepository.findAll().size();

        // Create the OcorrenciaCertifIrreg with an existing ID
        ocorrenciaCertifIrreg.setId(1L);
        OcorrenciaCertifIrregDTO ocorrenciaCertifIrregDTO = ocorrenciaCertifIrregMapper.toDto(ocorrenciaCertifIrreg);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOcorrenciaCertifIrregMockMvc.perform(post("/api/ocorrencia-certif-irregs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ocorrenciaCertifIrregDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<OcorrenciaCertifIrreg> ocorrenciaCertifIrregList = ocorrenciaCertifIrregRepository.findAll();
        assertThat(ocorrenciaCertifIrregList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOcorrenciaCertifIrregs() throws Exception {
        // Initialize the database
        ocorrenciaCertifIrregRepository.saveAndFlush(ocorrenciaCertifIrreg);

        // Get all the ocorrenciaCertifIrregList
        restOcorrenciaCertifIrregMockMvc.perform(get("/api/ocorrencia-certif-irregs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ocorrenciaCertifIrreg.getId().intValue())));
    }

    @Test
    @Transactional
    public void getOcorrenciaCertifIrreg() throws Exception {
        // Initialize the database
        ocorrenciaCertifIrregRepository.saveAndFlush(ocorrenciaCertifIrreg);

        // Get the ocorrenciaCertifIrreg
        restOcorrenciaCertifIrregMockMvc.perform(get("/api/ocorrencia-certif-irregs/{id}", ocorrenciaCertifIrreg.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ocorrenciaCertifIrreg.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingOcorrenciaCertifIrreg() throws Exception {
        // Get the ocorrenciaCertifIrreg
        restOcorrenciaCertifIrregMockMvc.perform(get("/api/ocorrencia-certif-irregs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOcorrenciaCertifIrreg() throws Exception {
        // Initialize the database
        ocorrenciaCertifIrregRepository.saveAndFlush(ocorrenciaCertifIrreg);
        int databaseSizeBeforeUpdate = ocorrenciaCertifIrregRepository.findAll().size();

        // Update the ocorrenciaCertifIrreg
        OcorrenciaCertifIrreg updatedOcorrenciaCertifIrreg = ocorrenciaCertifIrregRepository.findOne(ocorrenciaCertifIrreg.getId());
        OcorrenciaCertifIrregDTO ocorrenciaCertifIrregDTO = ocorrenciaCertifIrregMapper.toDto(updatedOcorrenciaCertifIrreg);

        restOcorrenciaCertifIrregMockMvc.perform(put("/api/ocorrencia-certif-irregs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ocorrenciaCertifIrregDTO)))
            .andExpect(status().isOk());

        // Validate the OcorrenciaCertifIrreg in the database
        List<OcorrenciaCertifIrreg> ocorrenciaCertifIrregList = ocorrenciaCertifIrregRepository.findAll();
        assertThat(ocorrenciaCertifIrregList).hasSize(databaseSizeBeforeUpdate);
        OcorrenciaCertifIrreg testOcorrenciaCertifIrreg = ocorrenciaCertifIrregList.get(ocorrenciaCertifIrregList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingOcorrenciaCertifIrreg() throws Exception {
        int databaseSizeBeforeUpdate = ocorrenciaCertifIrregRepository.findAll().size();

        // Create the OcorrenciaCertifIrreg
        OcorrenciaCertifIrregDTO ocorrenciaCertifIrregDTO = ocorrenciaCertifIrregMapper.toDto(ocorrenciaCertifIrreg);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restOcorrenciaCertifIrregMockMvc.perform(put("/api/ocorrencia-certif-irregs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ocorrenciaCertifIrregDTO)))
            .andExpect(status().isCreated());

        // Validate the OcorrenciaCertifIrreg in the database
        List<OcorrenciaCertifIrreg> ocorrenciaCertifIrregList = ocorrenciaCertifIrregRepository.findAll();
        assertThat(ocorrenciaCertifIrregList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteOcorrenciaCertifIrreg() throws Exception {
        // Initialize the database
        ocorrenciaCertifIrregRepository.saveAndFlush(ocorrenciaCertifIrreg);
        int databaseSizeBeforeDelete = ocorrenciaCertifIrregRepository.findAll().size();

        // Get the ocorrenciaCertifIrreg
        restOcorrenciaCertifIrregMockMvc.perform(delete("/api/ocorrencia-certif-irregs/{id}", ocorrenciaCertifIrreg.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<OcorrenciaCertifIrreg> ocorrenciaCertifIrregList = ocorrenciaCertifIrregRepository.findAll();
        assertThat(ocorrenciaCertifIrregList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OcorrenciaCertifIrreg.class);
        OcorrenciaCertifIrreg ocorrenciaCertifIrreg1 = new OcorrenciaCertifIrreg();
        ocorrenciaCertifIrreg1.setId(1L);
        OcorrenciaCertifIrreg ocorrenciaCertifIrreg2 = new OcorrenciaCertifIrreg();
        ocorrenciaCertifIrreg2.setId(ocorrenciaCertifIrreg1.getId());
        assertThat(ocorrenciaCertifIrreg1).isEqualTo(ocorrenciaCertifIrreg2);
        ocorrenciaCertifIrreg2.setId(2L);
        assertThat(ocorrenciaCertifIrreg1).isNotEqualTo(ocorrenciaCertifIrreg2);
        ocorrenciaCertifIrreg1.setId(null);
        assertThat(ocorrenciaCertifIrreg1).isNotEqualTo(ocorrenciaCertifIrreg2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OcorrenciaCertifIrregDTO.class);
        OcorrenciaCertifIrregDTO ocorrenciaCertifIrregDTO1 = new OcorrenciaCertifIrregDTO();
        ocorrenciaCertifIrregDTO1.setId(1L);
        OcorrenciaCertifIrregDTO ocorrenciaCertifIrregDTO2 = new OcorrenciaCertifIrregDTO();
        assertThat(ocorrenciaCertifIrregDTO1).isNotEqualTo(ocorrenciaCertifIrregDTO2);
        ocorrenciaCertifIrregDTO2.setId(ocorrenciaCertifIrregDTO1.getId());
        assertThat(ocorrenciaCertifIrregDTO1).isEqualTo(ocorrenciaCertifIrregDTO2);
        ocorrenciaCertifIrregDTO2.setId(2L);
        assertThat(ocorrenciaCertifIrregDTO1).isNotEqualTo(ocorrenciaCertifIrregDTO2);
        ocorrenciaCertifIrregDTO1.setId(null);
        assertThat(ocorrenciaCertifIrregDTO1).isNotEqualTo(ocorrenciaCertifIrregDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(ocorrenciaCertifIrregMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(ocorrenciaCertifIrregMapper.fromId(null)).isNull();
    }
}
