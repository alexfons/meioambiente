package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Fiscal;
import br.com.homemade.repository.FiscalRepository;
import br.com.homemade.service.dto.FiscalDTO;
import br.com.homemade.service.mapper.FiscalMapper;
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
 * Test class for the FiscalResource REST controller.
 *
 * @see FiscalResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class FiscalResourceIntTest {

    @Autowired
    private FiscalRepository fiscalRepository;

    @Autowired
    private FiscalMapper fiscalMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFiscalMockMvc;

    private Fiscal fiscal;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        FiscalResource fiscalResource = new FiscalResource(fiscalRepository, fiscalMapper);
        this.restFiscalMockMvc = MockMvcBuilders.standaloneSetup(fiscalResource)
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
    public static Fiscal createEntity(EntityManager em) {
        Fiscal fiscal = new Fiscal();
        return fiscal;
    }

    @Before
    public void initTest() {
        fiscal = createEntity(em);
    }

    @Test
    @Transactional
    public void createFiscal() throws Exception {
        int databaseSizeBeforeCreate = fiscalRepository.findAll().size();

        // Create the Fiscal
        FiscalDTO fiscalDTO = fiscalMapper.toDto(fiscal);
        restFiscalMockMvc.perform(post("/api/fiscals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fiscalDTO)))
            .andExpect(status().isCreated());

        // Validate the Fiscal in the database
        List<Fiscal> fiscalList = fiscalRepository.findAll();
        assertThat(fiscalList).hasSize(databaseSizeBeforeCreate + 1);
        Fiscal testFiscal = fiscalList.get(fiscalList.size() - 1);
    }

    @Test
    @Transactional
    public void createFiscalWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fiscalRepository.findAll().size();

        // Create the Fiscal with an existing ID
        fiscal.setId(1L);
        FiscalDTO fiscalDTO = fiscalMapper.toDto(fiscal);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFiscalMockMvc.perform(post("/api/fiscals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fiscalDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Fiscal> fiscalList = fiscalRepository.findAll();
        assertThat(fiscalList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFiscals() throws Exception {
        // Initialize the database
        fiscalRepository.saveAndFlush(fiscal);

        // Get all the fiscalList
        restFiscalMockMvc.perform(get("/api/fiscals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fiscal.getId().intValue())));
    }

    @Test
    @Transactional
    public void getFiscal() throws Exception {
        // Initialize the database
        fiscalRepository.saveAndFlush(fiscal);

        // Get the fiscal
        restFiscalMockMvc.perform(get("/api/fiscals/{id}", fiscal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fiscal.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingFiscal() throws Exception {
        // Get the fiscal
        restFiscalMockMvc.perform(get("/api/fiscals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFiscal() throws Exception {
        // Initialize the database
        fiscalRepository.saveAndFlush(fiscal);
        int databaseSizeBeforeUpdate = fiscalRepository.findAll().size();

        // Update the fiscal
        Fiscal updatedFiscal = fiscalRepository.findOne(fiscal.getId());
        FiscalDTO fiscalDTO = fiscalMapper.toDto(updatedFiscal);

        restFiscalMockMvc.perform(put("/api/fiscals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fiscalDTO)))
            .andExpect(status().isOk());

        // Validate the Fiscal in the database
        List<Fiscal> fiscalList = fiscalRepository.findAll();
        assertThat(fiscalList).hasSize(databaseSizeBeforeUpdate);
        Fiscal testFiscal = fiscalList.get(fiscalList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingFiscal() throws Exception {
        int databaseSizeBeforeUpdate = fiscalRepository.findAll().size();

        // Create the Fiscal
        FiscalDTO fiscalDTO = fiscalMapper.toDto(fiscal);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFiscalMockMvc.perform(put("/api/fiscals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fiscalDTO)))
            .andExpect(status().isCreated());

        // Validate the Fiscal in the database
        List<Fiscal> fiscalList = fiscalRepository.findAll();
        assertThat(fiscalList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFiscal() throws Exception {
        // Initialize the database
        fiscalRepository.saveAndFlush(fiscal);
        int databaseSizeBeforeDelete = fiscalRepository.findAll().size();

        // Get the fiscal
        restFiscalMockMvc.perform(delete("/api/fiscals/{id}", fiscal.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Fiscal> fiscalList = fiscalRepository.findAll();
        assertThat(fiscalList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Fiscal.class);
        Fiscal fiscal1 = new Fiscal();
        fiscal1.setId(1L);
        Fiscal fiscal2 = new Fiscal();
        fiscal2.setId(fiscal1.getId());
        assertThat(fiscal1).isEqualTo(fiscal2);
        fiscal2.setId(2L);
        assertThat(fiscal1).isNotEqualTo(fiscal2);
        fiscal1.setId(null);
        assertThat(fiscal1).isNotEqualTo(fiscal2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FiscalDTO.class);
        FiscalDTO fiscalDTO1 = new FiscalDTO();
        fiscalDTO1.setId(1L);
        FiscalDTO fiscalDTO2 = new FiscalDTO();
        assertThat(fiscalDTO1).isNotEqualTo(fiscalDTO2);
        fiscalDTO2.setId(fiscalDTO1.getId());
        assertThat(fiscalDTO1).isEqualTo(fiscalDTO2);
        fiscalDTO2.setId(2L);
        assertThat(fiscalDTO1).isNotEqualTo(fiscalDTO2);
        fiscalDTO1.setId(null);
        assertThat(fiscalDTO1).isNotEqualTo(fiscalDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(fiscalMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(fiscalMapper.fromId(null)).isNull();
    }
}
