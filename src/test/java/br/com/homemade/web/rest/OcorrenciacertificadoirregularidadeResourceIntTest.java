package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Ocorrenciacertificadoirregularidade;
import br.com.homemade.repository.OcorrenciacertificadoirregularidadeRepository;
import br.com.homemade.service.dto.OcorrenciacertificadoirregularidadeDTO;
import br.com.homemade.service.mapper.OcorrenciacertificadoirregularidadeMapper;
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
 * Test class for the OcorrenciacertificadoirregularidadeResource REST controller.
 *
 * @see OcorrenciacertificadoirregularidadeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class OcorrenciacertificadoirregularidadeResourceIntTest {

    @Autowired
    private OcorrenciacertificadoirregularidadeRepository ocorrenciacertificadoirregularidadeRepository;

    @Autowired
    private OcorrenciacertificadoirregularidadeMapper ocorrenciacertificadoirregularidadeMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOcorrenciacertificadoirregularidadeMockMvc;

    private Ocorrenciacertificadoirregularidade ocorrenciacertificadoirregularidade;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        OcorrenciacertificadoirregularidadeResource ocorrenciacertificadoirregularidadeResource = new OcorrenciacertificadoirregularidadeResource(ocorrenciacertificadoirregularidadeRepository, ocorrenciacertificadoirregularidadeMapper);
        this.restOcorrenciacertificadoirregularidadeMockMvc = MockMvcBuilders.standaloneSetup(ocorrenciacertificadoirregularidadeResource)
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
    public static Ocorrenciacertificadoirregularidade createEntity(EntityManager em) {
        Ocorrenciacertificadoirregularidade ocorrenciacertificadoirregularidade = new Ocorrenciacertificadoirregularidade();
        return ocorrenciacertificadoirregularidade;
    }

    @Before
    public void initTest() {
        ocorrenciacertificadoirregularidade = createEntity(em);
    }

    @Test
    @Transactional
    public void createOcorrenciacertificadoirregularidade() throws Exception {
        int databaseSizeBeforeCreate = ocorrenciacertificadoirregularidadeRepository.findAll().size();

        // Create the Ocorrenciacertificadoirregularidade
        OcorrenciacertificadoirregularidadeDTO ocorrenciacertificadoirregularidadeDTO = ocorrenciacertificadoirregularidadeMapper.toDto(ocorrenciacertificadoirregularidade);
        restOcorrenciacertificadoirregularidadeMockMvc.perform(post("/api/ocorrenciacertificadoirregularidades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ocorrenciacertificadoirregularidadeDTO)))
            .andExpect(status().isCreated());

        // Validate the Ocorrenciacertificadoirregularidade in the database
        List<Ocorrenciacertificadoirregularidade> ocorrenciacertificadoirregularidadeList = ocorrenciacertificadoirregularidadeRepository.findAll();
        assertThat(ocorrenciacertificadoirregularidadeList).hasSize(databaseSizeBeforeCreate + 1);
        Ocorrenciacertificadoirregularidade testOcorrenciacertificadoirregularidade = ocorrenciacertificadoirregularidadeList.get(ocorrenciacertificadoirregularidadeList.size() - 1);
    }

    @Test
    @Transactional
    public void createOcorrenciacertificadoirregularidadeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ocorrenciacertificadoirregularidadeRepository.findAll().size();

        // Create the Ocorrenciacertificadoirregularidade with an existing ID
        ocorrenciacertificadoirregularidade.setId(1L);
        OcorrenciacertificadoirregularidadeDTO ocorrenciacertificadoirregularidadeDTO = ocorrenciacertificadoirregularidadeMapper.toDto(ocorrenciacertificadoirregularidade);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOcorrenciacertificadoirregularidadeMockMvc.perform(post("/api/ocorrenciacertificadoirregularidades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ocorrenciacertificadoirregularidadeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Ocorrenciacertificadoirregularidade> ocorrenciacertificadoirregularidadeList = ocorrenciacertificadoirregularidadeRepository.findAll();
        assertThat(ocorrenciacertificadoirregularidadeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOcorrenciacertificadoirregularidades() throws Exception {
        // Initialize the database
        ocorrenciacertificadoirregularidadeRepository.saveAndFlush(ocorrenciacertificadoirregularidade);

        // Get all the ocorrenciacertificadoirregularidadeList
        restOcorrenciacertificadoirregularidadeMockMvc.perform(get("/api/ocorrenciacertificadoirregularidades?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ocorrenciacertificadoirregularidade.getId().intValue())));
    }

    @Test
    @Transactional
    public void getOcorrenciacertificadoirregularidade() throws Exception {
        // Initialize the database
        ocorrenciacertificadoirregularidadeRepository.saveAndFlush(ocorrenciacertificadoirregularidade);

        // Get the ocorrenciacertificadoirregularidade
        restOcorrenciacertificadoirregularidadeMockMvc.perform(get("/api/ocorrenciacertificadoirregularidades/{id}", ocorrenciacertificadoirregularidade.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ocorrenciacertificadoirregularidade.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingOcorrenciacertificadoirregularidade() throws Exception {
        // Get the ocorrenciacertificadoirregularidade
        restOcorrenciacertificadoirregularidadeMockMvc.perform(get("/api/ocorrenciacertificadoirregularidades/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOcorrenciacertificadoirregularidade() throws Exception {
        // Initialize the database
        ocorrenciacertificadoirregularidadeRepository.saveAndFlush(ocorrenciacertificadoirregularidade);
        int databaseSizeBeforeUpdate = ocorrenciacertificadoirregularidadeRepository.findAll().size();

        // Update the ocorrenciacertificadoirregularidade
        Ocorrenciacertificadoirregularidade updatedOcorrenciacertificadoirregularidade = ocorrenciacertificadoirregularidadeRepository.findOne(ocorrenciacertificadoirregularidade.getId());
        OcorrenciacertificadoirregularidadeDTO ocorrenciacertificadoirregularidadeDTO = ocorrenciacertificadoirregularidadeMapper.toDto(updatedOcorrenciacertificadoirregularidade);

        restOcorrenciacertificadoirregularidadeMockMvc.perform(put("/api/ocorrenciacertificadoirregularidades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ocorrenciacertificadoirregularidadeDTO)))
            .andExpect(status().isOk());

        // Validate the Ocorrenciacertificadoirregularidade in the database
        List<Ocorrenciacertificadoirregularidade> ocorrenciacertificadoirregularidadeList = ocorrenciacertificadoirregularidadeRepository.findAll();
        assertThat(ocorrenciacertificadoirregularidadeList).hasSize(databaseSizeBeforeUpdate);
        Ocorrenciacertificadoirregularidade testOcorrenciacertificadoirregularidade = ocorrenciacertificadoirregularidadeList.get(ocorrenciacertificadoirregularidadeList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingOcorrenciacertificadoirregularidade() throws Exception {
        int databaseSizeBeforeUpdate = ocorrenciacertificadoirregularidadeRepository.findAll().size();

        // Create the Ocorrenciacertificadoirregularidade
        OcorrenciacertificadoirregularidadeDTO ocorrenciacertificadoirregularidadeDTO = ocorrenciacertificadoirregularidadeMapper.toDto(ocorrenciacertificadoirregularidade);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restOcorrenciacertificadoirregularidadeMockMvc.perform(put("/api/ocorrenciacertificadoirregularidades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ocorrenciacertificadoirregularidadeDTO)))
            .andExpect(status().isCreated());

        // Validate the Ocorrenciacertificadoirregularidade in the database
        List<Ocorrenciacertificadoirregularidade> ocorrenciacertificadoirregularidadeList = ocorrenciacertificadoirregularidadeRepository.findAll();
        assertThat(ocorrenciacertificadoirregularidadeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteOcorrenciacertificadoirregularidade() throws Exception {
        // Initialize the database
        ocorrenciacertificadoirregularidadeRepository.saveAndFlush(ocorrenciacertificadoirregularidade);
        int databaseSizeBeforeDelete = ocorrenciacertificadoirregularidadeRepository.findAll().size();

        // Get the ocorrenciacertificadoirregularidade
        restOcorrenciacertificadoirregularidadeMockMvc.perform(delete("/api/ocorrenciacertificadoirregularidades/{id}", ocorrenciacertificadoirregularidade.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Ocorrenciacertificadoirregularidade> ocorrenciacertificadoirregularidadeList = ocorrenciacertificadoirregularidadeRepository.findAll();
        assertThat(ocorrenciacertificadoirregularidadeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Ocorrenciacertificadoirregularidade.class);
        Ocorrenciacertificadoirregularidade ocorrenciacertificadoirregularidade1 = new Ocorrenciacertificadoirregularidade();
        ocorrenciacertificadoirregularidade1.setId(1L);
        Ocorrenciacertificadoirregularidade ocorrenciacertificadoirregularidade2 = new Ocorrenciacertificadoirregularidade();
        ocorrenciacertificadoirregularidade2.setId(ocorrenciacertificadoirregularidade1.getId());
        assertThat(ocorrenciacertificadoirregularidade1).isEqualTo(ocorrenciacertificadoirregularidade2);
        ocorrenciacertificadoirregularidade2.setId(2L);
        assertThat(ocorrenciacertificadoirregularidade1).isNotEqualTo(ocorrenciacertificadoirregularidade2);
        ocorrenciacertificadoirregularidade1.setId(null);
        assertThat(ocorrenciacertificadoirregularidade1).isNotEqualTo(ocorrenciacertificadoirregularidade2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OcorrenciacertificadoirregularidadeDTO.class);
        OcorrenciacertificadoirregularidadeDTO ocorrenciacertificadoirregularidadeDTO1 = new OcorrenciacertificadoirregularidadeDTO();
        ocorrenciacertificadoirregularidadeDTO1.setId(1L);
        OcorrenciacertificadoirregularidadeDTO ocorrenciacertificadoirregularidadeDTO2 = new OcorrenciacertificadoirregularidadeDTO();
        assertThat(ocorrenciacertificadoirregularidadeDTO1).isNotEqualTo(ocorrenciacertificadoirregularidadeDTO2);
        ocorrenciacertificadoirregularidadeDTO2.setId(ocorrenciacertificadoirregularidadeDTO1.getId());
        assertThat(ocorrenciacertificadoirregularidadeDTO1).isEqualTo(ocorrenciacertificadoirregularidadeDTO2);
        ocorrenciacertificadoirregularidadeDTO2.setId(2L);
        assertThat(ocorrenciacertificadoirregularidadeDTO1).isNotEqualTo(ocorrenciacertificadoirregularidadeDTO2);
        ocorrenciacertificadoirregularidadeDTO1.setId(null);
        assertThat(ocorrenciacertificadoirregularidadeDTO1).isNotEqualTo(ocorrenciacertificadoirregularidadeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(ocorrenciacertificadoirregularidadeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(ocorrenciacertificadoirregularidadeMapper.fromId(null)).isNull();
    }
}
