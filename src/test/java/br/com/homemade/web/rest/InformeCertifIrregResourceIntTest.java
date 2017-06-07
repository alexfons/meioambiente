package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.InformeCertifIrreg;
import br.com.homemade.repository.InformeCertifIrregRepository;
import br.com.homemade.service.dto.InformeCertifIrregDTO;
import br.com.homemade.service.mapper.InformeCertifIrregMapper;
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
 * Test class for the InformeCertifIrregResource REST controller.
 *
 * @see InformeCertifIrregResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class InformeCertifIrregResourceIntTest {

    @Autowired
    private InformeCertifIrregRepository informeCertifIrregRepository;

    @Autowired
    private InformeCertifIrregMapper informeCertifIrregMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restInformeCertifIrregMockMvc;

    private InformeCertifIrreg informeCertifIrreg;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        InformeCertifIrregResource informeCertifIrregResource = new InformeCertifIrregResource(informeCertifIrregRepository, informeCertifIrregMapper);
        this.restInformeCertifIrregMockMvc = MockMvcBuilders.standaloneSetup(informeCertifIrregResource)
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
    public static InformeCertifIrreg createEntity(EntityManager em) {
        InformeCertifIrreg informeCertifIrreg = new InformeCertifIrreg();
        return informeCertifIrreg;
    }

    @Before
    public void initTest() {
        informeCertifIrreg = createEntity(em);
    }

    @Test
    @Transactional
    public void createInformeCertifIrreg() throws Exception {
        int databaseSizeBeforeCreate = informeCertifIrregRepository.findAll().size();

        // Create the InformeCertifIrreg
        InformeCertifIrregDTO informeCertifIrregDTO = informeCertifIrregMapper.toDto(informeCertifIrreg);
        restInformeCertifIrregMockMvc.perform(post("/api/informe-certif-irregs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(informeCertifIrregDTO)))
            .andExpect(status().isCreated());

        // Validate the InformeCertifIrreg in the database
        List<InformeCertifIrreg> informeCertifIrregList = informeCertifIrregRepository.findAll();
        assertThat(informeCertifIrregList).hasSize(databaseSizeBeforeCreate + 1);
        InformeCertifIrreg testInformeCertifIrreg = informeCertifIrregList.get(informeCertifIrregList.size() - 1);
    }

    @Test
    @Transactional
    public void createInformeCertifIrregWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = informeCertifIrregRepository.findAll().size();

        // Create the InformeCertifIrreg with an existing ID
        informeCertifIrreg.setId(1L);
        InformeCertifIrregDTO informeCertifIrregDTO = informeCertifIrregMapper.toDto(informeCertifIrreg);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInformeCertifIrregMockMvc.perform(post("/api/informe-certif-irregs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(informeCertifIrregDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<InformeCertifIrreg> informeCertifIrregList = informeCertifIrregRepository.findAll();
        assertThat(informeCertifIrregList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllInformeCertifIrregs() throws Exception {
        // Initialize the database
        informeCertifIrregRepository.saveAndFlush(informeCertifIrreg);

        // Get all the informeCertifIrregList
        restInformeCertifIrregMockMvc.perform(get("/api/informe-certif-irregs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(informeCertifIrreg.getId().intValue())));
    }

    @Test
    @Transactional
    public void getInformeCertifIrreg() throws Exception {
        // Initialize the database
        informeCertifIrregRepository.saveAndFlush(informeCertifIrreg);

        // Get the informeCertifIrreg
        restInformeCertifIrregMockMvc.perform(get("/api/informe-certif-irregs/{id}", informeCertifIrreg.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(informeCertifIrreg.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingInformeCertifIrreg() throws Exception {
        // Get the informeCertifIrreg
        restInformeCertifIrregMockMvc.perform(get("/api/informe-certif-irregs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInformeCertifIrreg() throws Exception {
        // Initialize the database
        informeCertifIrregRepository.saveAndFlush(informeCertifIrreg);
        int databaseSizeBeforeUpdate = informeCertifIrregRepository.findAll().size();

        // Update the informeCertifIrreg
        InformeCertifIrreg updatedInformeCertifIrreg = informeCertifIrregRepository.findOne(informeCertifIrreg.getId());
        InformeCertifIrregDTO informeCertifIrregDTO = informeCertifIrregMapper.toDto(updatedInformeCertifIrreg);

        restInformeCertifIrregMockMvc.perform(put("/api/informe-certif-irregs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(informeCertifIrregDTO)))
            .andExpect(status().isOk());

        // Validate the InformeCertifIrreg in the database
        List<InformeCertifIrreg> informeCertifIrregList = informeCertifIrregRepository.findAll();
        assertThat(informeCertifIrregList).hasSize(databaseSizeBeforeUpdate);
        InformeCertifIrreg testInformeCertifIrreg = informeCertifIrregList.get(informeCertifIrregList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingInformeCertifIrreg() throws Exception {
        int databaseSizeBeforeUpdate = informeCertifIrregRepository.findAll().size();

        // Create the InformeCertifIrreg
        InformeCertifIrregDTO informeCertifIrregDTO = informeCertifIrregMapper.toDto(informeCertifIrreg);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restInformeCertifIrregMockMvc.perform(put("/api/informe-certif-irregs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(informeCertifIrregDTO)))
            .andExpect(status().isCreated());

        // Validate the InformeCertifIrreg in the database
        List<InformeCertifIrreg> informeCertifIrregList = informeCertifIrregRepository.findAll();
        assertThat(informeCertifIrregList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteInformeCertifIrreg() throws Exception {
        // Initialize the database
        informeCertifIrregRepository.saveAndFlush(informeCertifIrreg);
        int databaseSizeBeforeDelete = informeCertifIrregRepository.findAll().size();

        // Get the informeCertifIrreg
        restInformeCertifIrregMockMvc.perform(delete("/api/informe-certif-irregs/{id}", informeCertifIrreg.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<InformeCertifIrreg> informeCertifIrregList = informeCertifIrregRepository.findAll();
        assertThat(informeCertifIrregList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InformeCertifIrreg.class);
        InformeCertifIrreg informeCertifIrreg1 = new InformeCertifIrreg();
        informeCertifIrreg1.setId(1L);
        InformeCertifIrreg informeCertifIrreg2 = new InformeCertifIrreg();
        informeCertifIrreg2.setId(informeCertifIrreg1.getId());
        assertThat(informeCertifIrreg1).isEqualTo(informeCertifIrreg2);
        informeCertifIrreg2.setId(2L);
        assertThat(informeCertifIrreg1).isNotEqualTo(informeCertifIrreg2);
        informeCertifIrreg1.setId(null);
        assertThat(informeCertifIrreg1).isNotEqualTo(informeCertifIrreg2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InformeCertifIrregDTO.class);
        InformeCertifIrregDTO informeCertifIrregDTO1 = new InformeCertifIrregDTO();
        informeCertifIrregDTO1.setId(1L);
        InformeCertifIrregDTO informeCertifIrregDTO2 = new InformeCertifIrregDTO();
        assertThat(informeCertifIrregDTO1).isNotEqualTo(informeCertifIrregDTO2);
        informeCertifIrregDTO2.setId(informeCertifIrregDTO1.getId());
        assertThat(informeCertifIrregDTO1).isEqualTo(informeCertifIrregDTO2);
        informeCertifIrregDTO2.setId(2L);
        assertThat(informeCertifIrregDTO1).isNotEqualTo(informeCertifIrregDTO2);
        informeCertifIrregDTO1.setId(null);
        assertThat(informeCertifIrregDTO1).isNotEqualTo(informeCertifIrregDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(informeCertifIrregMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(informeCertifIrregMapper.fromId(null)).isNull();
    }
}
