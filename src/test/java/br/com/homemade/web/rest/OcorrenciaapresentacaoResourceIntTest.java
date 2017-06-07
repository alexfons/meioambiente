package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Ocorrenciaapresentacao;
import br.com.homemade.repository.OcorrenciaapresentacaoRepository;
import br.com.homemade.service.dto.OcorrenciaapresentacaoDTO;
import br.com.homemade.service.mapper.OcorrenciaapresentacaoMapper;
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
 * Test class for the OcorrenciaapresentacaoResource REST controller.
 *
 * @see OcorrenciaapresentacaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class OcorrenciaapresentacaoResourceIntTest {

    private static final String DEFAULT_ENQUADRAMENTO = "AAAAAAAAAA";
    private static final String UPDATED_ENQUADRAMENTO = "BBBBBBBBBB";

    @Autowired
    private OcorrenciaapresentacaoRepository ocorrenciaapresentacaoRepository;

    @Autowired
    private OcorrenciaapresentacaoMapper ocorrenciaapresentacaoMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOcorrenciaapresentacaoMockMvc;

    private Ocorrenciaapresentacao ocorrenciaapresentacao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        OcorrenciaapresentacaoResource ocorrenciaapresentacaoResource = new OcorrenciaapresentacaoResource(ocorrenciaapresentacaoRepository, ocorrenciaapresentacaoMapper);
        this.restOcorrenciaapresentacaoMockMvc = MockMvcBuilders.standaloneSetup(ocorrenciaapresentacaoResource)
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
    public static Ocorrenciaapresentacao createEntity(EntityManager em) {
        Ocorrenciaapresentacao ocorrenciaapresentacao = new Ocorrenciaapresentacao()
            .enquadramento(DEFAULT_ENQUADRAMENTO);
        return ocorrenciaapresentacao;
    }

    @Before
    public void initTest() {
        ocorrenciaapresentacao = createEntity(em);
    }

    @Test
    @Transactional
    public void createOcorrenciaapresentacao() throws Exception {
        int databaseSizeBeforeCreate = ocorrenciaapresentacaoRepository.findAll().size();

        // Create the Ocorrenciaapresentacao
        OcorrenciaapresentacaoDTO ocorrenciaapresentacaoDTO = ocorrenciaapresentacaoMapper.toDto(ocorrenciaapresentacao);
        restOcorrenciaapresentacaoMockMvc.perform(post("/api/ocorrenciaapresentacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ocorrenciaapresentacaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Ocorrenciaapresentacao in the database
        List<Ocorrenciaapresentacao> ocorrenciaapresentacaoList = ocorrenciaapresentacaoRepository.findAll();
        assertThat(ocorrenciaapresentacaoList).hasSize(databaseSizeBeforeCreate + 1);
        Ocorrenciaapresentacao testOcorrenciaapresentacao = ocorrenciaapresentacaoList.get(ocorrenciaapresentacaoList.size() - 1);
        assertThat(testOcorrenciaapresentacao.getEnquadramento()).isEqualTo(DEFAULT_ENQUADRAMENTO);
    }

    @Test
    @Transactional
    public void createOcorrenciaapresentacaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ocorrenciaapresentacaoRepository.findAll().size();

        // Create the Ocorrenciaapresentacao with an existing ID
        ocorrenciaapresentacao.setId(1L);
        OcorrenciaapresentacaoDTO ocorrenciaapresentacaoDTO = ocorrenciaapresentacaoMapper.toDto(ocorrenciaapresentacao);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOcorrenciaapresentacaoMockMvc.perform(post("/api/ocorrenciaapresentacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ocorrenciaapresentacaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Ocorrenciaapresentacao> ocorrenciaapresentacaoList = ocorrenciaapresentacaoRepository.findAll();
        assertThat(ocorrenciaapresentacaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOcorrenciaapresentacaos() throws Exception {
        // Initialize the database
        ocorrenciaapresentacaoRepository.saveAndFlush(ocorrenciaapresentacao);

        // Get all the ocorrenciaapresentacaoList
        restOcorrenciaapresentacaoMockMvc.perform(get("/api/ocorrenciaapresentacaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ocorrenciaapresentacao.getId().intValue())))
            .andExpect(jsonPath("$.[*].enquadramento").value(hasItem(DEFAULT_ENQUADRAMENTO.toString())));
    }

    @Test
    @Transactional
    public void getOcorrenciaapresentacao() throws Exception {
        // Initialize the database
        ocorrenciaapresentacaoRepository.saveAndFlush(ocorrenciaapresentacao);

        // Get the ocorrenciaapresentacao
        restOcorrenciaapresentacaoMockMvc.perform(get("/api/ocorrenciaapresentacaos/{id}", ocorrenciaapresentacao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ocorrenciaapresentacao.getId().intValue()))
            .andExpect(jsonPath("$.enquadramento").value(DEFAULT_ENQUADRAMENTO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOcorrenciaapresentacao() throws Exception {
        // Get the ocorrenciaapresentacao
        restOcorrenciaapresentacaoMockMvc.perform(get("/api/ocorrenciaapresentacaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOcorrenciaapresentacao() throws Exception {
        // Initialize the database
        ocorrenciaapresentacaoRepository.saveAndFlush(ocorrenciaapresentacao);
        int databaseSizeBeforeUpdate = ocorrenciaapresentacaoRepository.findAll().size();

        // Update the ocorrenciaapresentacao
        Ocorrenciaapresentacao updatedOcorrenciaapresentacao = ocorrenciaapresentacaoRepository.findOne(ocorrenciaapresentacao.getId());
        updatedOcorrenciaapresentacao
            .enquadramento(UPDATED_ENQUADRAMENTO);
        OcorrenciaapresentacaoDTO ocorrenciaapresentacaoDTO = ocorrenciaapresentacaoMapper.toDto(updatedOcorrenciaapresentacao);

        restOcorrenciaapresentacaoMockMvc.perform(put("/api/ocorrenciaapresentacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ocorrenciaapresentacaoDTO)))
            .andExpect(status().isOk());

        // Validate the Ocorrenciaapresentacao in the database
        List<Ocorrenciaapresentacao> ocorrenciaapresentacaoList = ocorrenciaapresentacaoRepository.findAll();
        assertThat(ocorrenciaapresentacaoList).hasSize(databaseSizeBeforeUpdate);
        Ocorrenciaapresentacao testOcorrenciaapresentacao = ocorrenciaapresentacaoList.get(ocorrenciaapresentacaoList.size() - 1);
        assertThat(testOcorrenciaapresentacao.getEnquadramento()).isEqualTo(UPDATED_ENQUADRAMENTO);
    }

    @Test
    @Transactional
    public void updateNonExistingOcorrenciaapresentacao() throws Exception {
        int databaseSizeBeforeUpdate = ocorrenciaapresentacaoRepository.findAll().size();

        // Create the Ocorrenciaapresentacao
        OcorrenciaapresentacaoDTO ocorrenciaapresentacaoDTO = ocorrenciaapresentacaoMapper.toDto(ocorrenciaapresentacao);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restOcorrenciaapresentacaoMockMvc.perform(put("/api/ocorrenciaapresentacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ocorrenciaapresentacaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Ocorrenciaapresentacao in the database
        List<Ocorrenciaapresentacao> ocorrenciaapresentacaoList = ocorrenciaapresentacaoRepository.findAll();
        assertThat(ocorrenciaapresentacaoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteOcorrenciaapresentacao() throws Exception {
        // Initialize the database
        ocorrenciaapresentacaoRepository.saveAndFlush(ocorrenciaapresentacao);
        int databaseSizeBeforeDelete = ocorrenciaapresentacaoRepository.findAll().size();

        // Get the ocorrenciaapresentacao
        restOcorrenciaapresentacaoMockMvc.perform(delete("/api/ocorrenciaapresentacaos/{id}", ocorrenciaapresentacao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Ocorrenciaapresentacao> ocorrenciaapresentacaoList = ocorrenciaapresentacaoRepository.findAll();
        assertThat(ocorrenciaapresentacaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Ocorrenciaapresentacao.class);
        Ocorrenciaapresentacao ocorrenciaapresentacao1 = new Ocorrenciaapresentacao();
        ocorrenciaapresentacao1.setId(1L);
        Ocorrenciaapresentacao ocorrenciaapresentacao2 = new Ocorrenciaapresentacao();
        ocorrenciaapresentacao2.setId(ocorrenciaapresentacao1.getId());
        assertThat(ocorrenciaapresentacao1).isEqualTo(ocorrenciaapresentacao2);
        ocorrenciaapresentacao2.setId(2L);
        assertThat(ocorrenciaapresentacao1).isNotEqualTo(ocorrenciaapresentacao2);
        ocorrenciaapresentacao1.setId(null);
        assertThat(ocorrenciaapresentacao1).isNotEqualTo(ocorrenciaapresentacao2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OcorrenciaapresentacaoDTO.class);
        OcorrenciaapresentacaoDTO ocorrenciaapresentacaoDTO1 = new OcorrenciaapresentacaoDTO();
        ocorrenciaapresentacaoDTO1.setId(1L);
        OcorrenciaapresentacaoDTO ocorrenciaapresentacaoDTO2 = new OcorrenciaapresentacaoDTO();
        assertThat(ocorrenciaapresentacaoDTO1).isNotEqualTo(ocorrenciaapresentacaoDTO2);
        ocorrenciaapresentacaoDTO2.setId(ocorrenciaapresentacaoDTO1.getId());
        assertThat(ocorrenciaapresentacaoDTO1).isEqualTo(ocorrenciaapresentacaoDTO2);
        ocorrenciaapresentacaoDTO2.setId(2L);
        assertThat(ocorrenciaapresentacaoDTO1).isNotEqualTo(ocorrenciaapresentacaoDTO2);
        ocorrenciaapresentacaoDTO1.setId(null);
        assertThat(ocorrenciaapresentacaoDTO1).isNotEqualTo(ocorrenciaapresentacaoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(ocorrenciaapresentacaoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(ocorrenciaapresentacaoMapper.fromId(null)).isNull();
    }
}
