package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Ocorrencianotificacao;
import br.com.homemade.repository.OcorrencianotificacaoRepository;
import br.com.homemade.service.dto.OcorrencianotificacaoDTO;
import br.com.homemade.service.mapper.OcorrencianotificacaoMapper;
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
 * Test class for the OcorrencianotificacaoResource REST controller.
 *
 * @see OcorrencianotificacaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class OcorrencianotificacaoResourceIntTest {

    private static final String DEFAULT_ENQUADRAMENTO = "AAAAAAAAAA";
    private static final String UPDATED_ENQUADRAMENTO = "BBBBBBBBBB";

    @Autowired
    private OcorrencianotificacaoRepository ocorrencianotificacaoRepository;

    @Autowired
    private OcorrencianotificacaoMapper ocorrencianotificacaoMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOcorrencianotificacaoMockMvc;

    private Ocorrencianotificacao ocorrencianotificacao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        OcorrencianotificacaoResource ocorrencianotificacaoResource = new OcorrencianotificacaoResource(ocorrencianotificacaoRepository, ocorrencianotificacaoMapper);
        this.restOcorrencianotificacaoMockMvc = MockMvcBuilders.standaloneSetup(ocorrencianotificacaoResource)
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
    public static Ocorrencianotificacao createEntity(EntityManager em) {
        Ocorrencianotificacao ocorrencianotificacao = new Ocorrencianotificacao()
            .enquadramento(DEFAULT_ENQUADRAMENTO);
        return ocorrencianotificacao;
    }

    @Before
    public void initTest() {
        ocorrencianotificacao = createEntity(em);
    }

    @Test
    @Transactional
    public void createOcorrencianotificacao() throws Exception {
        int databaseSizeBeforeCreate = ocorrencianotificacaoRepository.findAll().size();

        // Create the Ocorrencianotificacao
        OcorrencianotificacaoDTO ocorrencianotificacaoDTO = ocorrencianotificacaoMapper.toDto(ocorrencianotificacao);
        restOcorrencianotificacaoMockMvc.perform(post("/api/ocorrencianotificacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ocorrencianotificacaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Ocorrencianotificacao in the database
        List<Ocorrencianotificacao> ocorrencianotificacaoList = ocorrencianotificacaoRepository.findAll();
        assertThat(ocorrencianotificacaoList).hasSize(databaseSizeBeforeCreate + 1);
        Ocorrencianotificacao testOcorrencianotificacao = ocorrencianotificacaoList.get(ocorrencianotificacaoList.size() - 1);
        assertThat(testOcorrencianotificacao.getEnquadramento()).isEqualTo(DEFAULT_ENQUADRAMENTO);
    }

    @Test
    @Transactional
    public void createOcorrencianotificacaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ocorrencianotificacaoRepository.findAll().size();

        // Create the Ocorrencianotificacao with an existing ID
        ocorrencianotificacao.setId(1L);
        OcorrencianotificacaoDTO ocorrencianotificacaoDTO = ocorrencianotificacaoMapper.toDto(ocorrencianotificacao);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOcorrencianotificacaoMockMvc.perform(post("/api/ocorrencianotificacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ocorrencianotificacaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Ocorrencianotificacao> ocorrencianotificacaoList = ocorrencianotificacaoRepository.findAll();
        assertThat(ocorrencianotificacaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOcorrencianotificacaos() throws Exception {
        // Initialize the database
        ocorrencianotificacaoRepository.saveAndFlush(ocorrencianotificacao);

        // Get all the ocorrencianotificacaoList
        restOcorrencianotificacaoMockMvc.perform(get("/api/ocorrencianotificacaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ocorrencianotificacao.getId().intValue())))
            .andExpect(jsonPath("$.[*].enquadramento").value(hasItem(DEFAULT_ENQUADRAMENTO.toString())));
    }

    @Test
    @Transactional
    public void getOcorrencianotificacao() throws Exception {
        // Initialize the database
        ocorrencianotificacaoRepository.saveAndFlush(ocorrencianotificacao);

        // Get the ocorrencianotificacao
        restOcorrencianotificacaoMockMvc.perform(get("/api/ocorrencianotificacaos/{id}", ocorrencianotificacao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ocorrencianotificacao.getId().intValue()))
            .andExpect(jsonPath("$.enquadramento").value(DEFAULT_ENQUADRAMENTO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOcorrencianotificacao() throws Exception {
        // Get the ocorrencianotificacao
        restOcorrencianotificacaoMockMvc.perform(get("/api/ocorrencianotificacaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOcorrencianotificacao() throws Exception {
        // Initialize the database
        ocorrencianotificacaoRepository.saveAndFlush(ocorrencianotificacao);
        int databaseSizeBeforeUpdate = ocorrencianotificacaoRepository.findAll().size();

        // Update the ocorrencianotificacao
        Ocorrencianotificacao updatedOcorrencianotificacao = ocorrencianotificacaoRepository.findOne(ocorrencianotificacao.getId());
        updatedOcorrencianotificacao
            .enquadramento(UPDATED_ENQUADRAMENTO);
        OcorrencianotificacaoDTO ocorrencianotificacaoDTO = ocorrencianotificacaoMapper.toDto(updatedOcorrencianotificacao);

        restOcorrencianotificacaoMockMvc.perform(put("/api/ocorrencianotificacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ocorrencianotificacaoDTO)))
            .andExpect(status().isOk());

        // Validate the Ocorrencianotificacao in the database
        List<Ocorrencianotificacao> ocorrencianotificacaoList = ocorrencianotificacaoRepository.findAll();
        assertThat(ocorrencianotificacaoList).hasSize(databaseSizeBeforeUpdate);
        Ocorrencianotificacao testOcorrencianotificacao = ocorrencianotificacaoList.get(ocorrencianotificacaoList.size() - 1);
        assertThat(testOcorrencianotificacao.getEnquadramento()).isEqualTo(UPDATED_ENQUADRAMENTO);
    }

    @Test
    @Transactional
    public void updateNonExistingOcorrencianotificacao() throws Exception {
        int databaseSizeBeforeUpdate = ocorrencianotificacaoRepository.findAll().size();

        // Create the Ocorrencianotificacao
        OcorrencianotificacaoDTO ocorrencianotificacaoDTO = ocorrencianotificacaoMapper.toDto(ocorrencianotificacao);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restOcorrencianotificacaoMockMvc.perform(put("/api/ocorrencianotificacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ocorrencianotificacaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Ocorrencianotificacao in the database
        List<Ocorrencianotificacao> ocorrencianotificacaoList = ocorrencianotificacaoRepository.findAll();
        assertThat(ocorrencianotificacaoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteOcorrencianotificacao() throws Exception {
        // Initialize the database
        ocorrencianotificacaoRepository.saveAndFlush(ocorrencianotificacao);
        int databaseSizeBeforeDelete = ocorrencianotificacaoRepository.findAll().size();

        // Get the ocorrencianotificacao
        restOcorrencianotificacaoMockMvc.perform(delete("/api/ocorrencianotificacaos/{id}", ocorrencianotificacao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Ocorrencianotificacao> ocorrencianotificacaoList = ocorrencianotificacaoRepository.findAll();
        assertThat(ocorrencianotificacaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Ocorrencianotificacao.class);
        Ocorrencianotificacao ocorrencianotificacao1 = new Ocorrencianotificacao();
        ocorrencianotificacao1.setId(1L);
        Ocorrencianotificacao ocorrencianotificacao2 = new Ocorrencianotificacao();
        ocorrencianotificacao2.setId(ocorrencianotificacao1.getId());
        assertThat(ocorrencianotificacao1).isEqualTo(ocorrencianotificacao2);
        ocorrencianotificacao2.setId(2L);
        assertThat(ocorrencianotificacao1).isNotEqualTo(ocorrencianotificacao2);
        ocorrencianotificacao1.setId(null);
        assertThat(ocorrencianotificacao1).isNotEqualTo(ocorrencianotificacao2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OcorrencianotificacaoDTO.class);
        OcorrencianotificacaoDTO ocorrencianotificacaoDTO1 = new OcorrencianotificacaoDTO();
        ocorrencianotificacaoDTO1.setId(1L);
        OcorrencianotificacaoDTO ocorrencianotificacaoDTO2 = new OcorrencianotificacaoDTO();
        assertThat(ocorrencianotificacaoDTO1).isNotEqualTo(ocorrencianotificacaoDTO2);
        ocorrencianotificacaoDTO2.setId(ocorrencianotificacaoDTO1.getId());
        assertThat(ocorrencianotificacaoDTO1).isEqualTo(ocorrencianotificacaoDTO2);
        ocorrencianotificacaoDTO2.setId(2L);
        assertThat(ocorrencianotificacaoDTO1).isNotEqualTo(ocorrencianotificacaoDTO2);
        ocorrencianotificacaoDTO1.setId(null);
        assertThat(ocorrencianotificacaoDTO1).isNotEqualTo(ocorrencianotificacaoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(ocorrencianotificacaoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(ocorrencianotificacaoMapper.fromId(null)).isNull();
    }
}
