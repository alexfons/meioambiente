package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Empresacontrato;
import br.com.homemade.repository.EmpresacontratoRepository;
import br.com.homemade.service.dto.EmpresacontratoDTO;
import br.com.homemade.service.mapper.EmpresacontratoMapper;
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
 * Test class for the EmpresacontratoResource REST controller.
 *
 * @see EmpresacontratoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class EmpresacontratoResourceIntTest {

    @Autowired
    private EmpresacontratoRepository empresacontratoRepository;

    @Autowired
    private EmpresacontratoMapper empresacontratoMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEmpresacontratoMockMvc;

    private Empresacontrato empresacontrato;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        EmpresacontratoResource empresacontratoResource = new EmpresacontratoResource(empresacontratoRepository, empresacontratoMapper);
        this.restEmpresacontratoMockMvc = MockMvcBuilders.standaloneSetup(empresacontratoResource)
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
    public static Empresacontrato createEntity(EntityManager em) {
        Empresacontrato empresacontrato = new Empresacontrato();
        return empresacontrato;
    }

    @Before
    public void initTest() {
        empresacontrato = createEntity(em);
    }

    @Test
    @Transactional
    public void createEmpresacontrato() throws Exception {
        int databaseSizeBeforeCreate = empresacontratoRepository.findAll().size();

        // Create the Empresacontrato
        EmpresacontratoDTO empresacontratoDTO = empresacontratoMapper.toDto(empresacontrato);
        restEmpresacontratoMockMvc.perform(post("/api/empresacontratoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empresacontratoDTO)))
            .andExpect(status().isCreated());

        // Validate the Empresacontrato in the database
        List<Empresacontrato> empresacontratoList = empresacontratoRepository.findAll();
        assertThat(empresacontratoList).hasSize(databaseSizeBeforeCreate + 1);
        Empresacontrato testEmpresacontrato = empresacontratoList.get(empresacontratoList.size() - 1);
    }

    @Test
    @Transactional
    public void createEmpresacontratoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = empresacontratoRepository.findAll().size();

        // Create the Empresacontrato with an existing ID
        empresacontrato.setId(1L);
        EmpresacontratoDTO empresacontratoDTO = empresacontratoMapper.toDto(empresacontrato);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmpresacontratoMockMvc.perform(post("/api/empresacontratoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empresacontratoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Empresacontrato> empresacontratoList = empresacontratoRepository.findAll();
        assertThat(empresacontratoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEmpresacontratoes() throws Exception {
        // Initialize the database
        empresacontratoRepository.saveAndFlush(empresacontrato);

        // Get all the empresacontratoList
        restEmpresacontratoMockMvc.perform(get("/api/empresacontratoes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(empresacontrato.getId().intValue())));
    }

    @Test
    @Transactional
    public void getEmpresacontrato() throws Exception {
        // Initialize the database
        empresacontratoRepository.saveAndFlush(empresacontrato);

        // Get the empresacontrato
        restEmpresacontratoMockMvc.perform(get("/api/empresacontratoes/{id}", empresacontrato.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(empresacontrato.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEmpresacontrato() throws Exception {
        // Get the empresacontrato
        restEmpresacontratoMockMvc.perform(get("/api/empresacontratoes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEmpresacontrato() throws Exception {
        // Initialize the database
        empresacontratoRepository.saveAndFlush(empresacontrato);
        int databaseSizeBeforeUpdate = empresacontratoRepository.findAll().size();

        // Update the empresacontrato
        Empresacontrato updatedEmpresacontrato = empresacontratoRepository.findOne(empresacontrato.getId());
        EmpresacontratoDTO empresacontratoDTO = empresacontratoMapper.toDto(updatedEmpresacontrato);

        restEmpresacontratoMockMvc.perform(put("/api/empresacontratoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empresacontratoDTO)))
            .andExpect(status().isOk());

        // Validate the Empresacontrato in the database
        List<Empresacontrato> empresacontratoList = empresacontratoRepository.findAll();
        assertThat(empresacontratoList).hasSize(databaseSizeBeforeUpdate);
        Empresacontrato testEmpresacontrato = empresacontratoList.get(empresacontratoList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingEmpresacontrato() throws Exception {
        int databaseSizeBeforeUpdate = empresacontratoRepository.findAll().size();

        // Create the Empresacontrato
        EmpresacontratoDTO empresacontratoDTO = empresacontratoMapper.toDto(empresacontrato);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEmpresacontratoMockMvc.perform(put("/api/empresacontratoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empresacontratoDTO)))
            .andExpect(status().isCreated());

        // Validate the Empresacontrato in the database
        List<Empresacontrato> empresacontratoList = empresacontratoRepository.findAll();
        assertThat(empresacontratoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteEmpresacontrato() throws Exception {
        // Initialize the database
        empresacontratoRepository.saveAndFlush(empresacontrato);
        int databaseSizeBeforeDelete = empresacontratoRepository.findAll().size();

        // Get the empresacontrato
        restEmpresacontratoMockMvc.perform(delete("/api/empresacontratoes/{id}", empresacontrato.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Empresacontrato> empresacontratoList = empresacontratoRepository.findAll();
        assertThat(empresacontratoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Empresacontrato.class);
        Empresacontrato empresacontrato1 = new Empresacontrato();
        empresacontrato1.setId(1L);
        Empresacontrato empresacontrato2 = new Empresacontrato();
        empresacontrato2.setId(empresacontrato1.getId());
        assertThat(empresacontrato1).isEqualTo(empresacontrato2);
        empresacontrato2.setId(2L);
        assertThat(empresacontrato1).isNotEqualTo(empresacontrato2);
        empresacontrato1.setId(null);
        assertThat(empresacontrato1).isNotEqualTo(empresacontrato2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmpresacontratoDTO.class);
        EmpresacontratoDTO empresacontratoDTO1 = new EmpresacontratoDTO();
        empresacontratoDTO1.setId(1L);
        EmpresacontratoDTO empresacontratoDTO2 = new EmpresacontratoDTO();
        assertThat(empresacontratoDTO1).isNotEqualTo(empresacontratoDTO2);
        empresacontratoDTO2.setId(empresacontratoDTO1.getId());
        assertThat(empresacontratoDTO1).isEqualTo(empresacontratoDTO2);
        empresacontratoDTO2.setId(2L);
        assertThat(empresacontratoDTO1).isNotEqualTo(empresacontratoDTO2);
        empresacontratoDTO1.setId(null);
        assertThat(empresacontratoDTO1).isNotEqualTo(empresacontratoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(empresacontratoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(empresacontratoMapper.fromId(null)).isNull();
    }
}
