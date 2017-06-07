package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Manifestacao;
import br.com.homemade.repository.ManifestacaoRepository;
import br.com.homemade.service.dto.ManifestacaoDTO;
import br.com.homemade.service.mapper.ManifestacaoMapper;
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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static br.com.homemade.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ManifestacaoResource REST controller.
 *
 * @see ManifestacaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class ManifestacaoResourceIntTest {

    private static final ZonedDateTime DEFAULT_DATAAVISO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAAVISO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAENTREGA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAENTREGA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_NUMERO = 1;
    private static final Integer UPDATED_NUMERO = 2;

    @Autowired
    private ManifestacaoRepository manifestacaoRepository;

    @Autowired
    private ManifestacaoMapper manifestacaoMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restManifestacaoMockMvc;

    private Manifestacao manifestacao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ManifestacaoResource manifestacaoResource = new ManifestacaoResource(manifestacaoRepository, manifestacaoMapper);
        this.restManifestacaoMockMvc = MockMvcBuilders.standaloneSetup(manifestacaoResource)
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
    public static Manifestacao createEntity(EntityManager em) {
        Manifestacao manifestacao = new Manifestacao()
            .dataaviso(DEFAULT_DATAAVISO)
            .dataentrega(DEFAULT_DATAENTREGA)
            .numero(DEFAULT_NUMERO);
        return manifestacao;
    }

    @Before
    public void initTest() {
        manifestacao = createEntity(em);
    }

    @Test
    @Transactional
    public void createManifestacao() throws Exception {
        int databaseSizeBeforeCreate = manifestacaoRepository.findAll().size();

        // Create the Manifestacao
        ManifestacaoDTO manifestacaoDTO = manifestacaoMapper.toDto(manifestacao);
        restManifestacaoMockMvc.perform(post("/api/manifestacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manifestacaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Manifestacao in the database
        List<Manifestacao> manifestacaoList = manifestacaoRepository.findAll();
        assertThat(manifestacaoList).hasSize(databaseSizeBeforeCreate + 1);
        Manifestacao testManifestacao = manifestacaoList.get(manifestacaoList.size() - 1);
        assertThat(testManifestacao.getDataaviso()).isEqualTo(DEFAULT_DATAAVISO);
        assertThat(testManifestacao.getDataentrega()).isEqualTo(DEFAULT_DATAENTREGA);
        assertThat(testManifestacao.getNumero()).isEqualTo(DEFAULT_NUMERO);
    }

    @Test
    @Transactional
    public void createManifestacaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = manifestacaoRepository.findAll().size();

        // Create the Manifestacao with an existing ID
        manifestacao.setId(1L);
        ManifestacaoDTO manifestacaoDTO = manifestacaoMapper.toDto(manifestacao);

        // An entity with an existing ID cannot be created, so this API call must fail
        restManifestacaoMockMvc.perform(post("/api/manifestacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manifestacaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Manifestacao> manifestacaoList = manifestacaoRepository.findAll();
        assertThat(manifestacaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllManifestacaos() throws Exception {
        // Initialize the database
        manifestacaoRepository.saveAndFlush(manifestacao);

        // Get all the manifestacaoList
        restManifestacaoMockMvc.perform(get("/api/manifestacaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(manifestacao.getId().intValue())))
            .andExpect(jsonPath("$.[*].dataaviso").value(hasItem(sameInstant(DEFAULT_DATAAVISO))))
            .andExpect(jsonPath("$.[*].dataentrega").value(hasItem(sameInstant(DEFAULT_DATAENTREGA))))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO)));
    }

    @Test
    @Transactional
    public void getManifestacao() throws Exception {
        // Initialize the database
        manifestacaoRepository.saveAndFlush(manifestacao);

        // Get the manifestacao
        restManifestacaoMockMvc.perform(get("/api/manifestacaos/{id}", manifestacao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(manifestacao.getId().intValue()))
            .andExpect(jsonPath("$.dataaviso").value(sameInstant(DEFAULT_DATAAVISO)))
            .andExpect(jsonPath("$.dataentrega").value(sameInstant(DEFAULT_DATAENTREGA)))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO));
    }

    @Test
    @Transactional
    public void getNonExistingManifestacao() throws Exception {
        // Get the manifestacao
        restManifestacaoMockMvc.perform(get("/api/manifestacaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateManifestacao() throws Exception {
        // Initialize the database
        manifestacaoRepository.saveAndFlush(manifestacao);
        int databaseSizeBeforeUpdate = manifestacaoRepository.findAll().size();

        // Update the manifestacao
        Manifestacao updatedManifestacao = manifestacaoRepository.findOne(manifestacao.getId());
        updatedManifestacao
            .dataaviso(UPDATED_DATAAVISO)
            .dataentrega(UPDATED_DATAENTREGA)
            .numero(UPDATED_NUMERO);
        ManifestacaoDTO manifestacaoDTO = manifestacaoMapper.toDto(updatedManifestacao);

        restManifestacaoMockMvc.perform(put("/api/manifestacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manifestacaoDTO)))
            .andExpect(status().isOk());

        // Validate the Manifestacao in the database
        List<Manifestacao> manifestacaoList = manifestacaoRepository.findAll();
        assertThat(manifestacaoList).hasSize(databaseSizeBeforeUpdate);
        Manifestacao testManifestacao = manifestacaoList.get(manifestacaoList.size() - 1);
        assertThat(testManifestacao.getDataaviso()).isEqualTo(UPDATED_DATAAVISO);
        assertThat(testManifestacao.getDataentrega()).isEqualTo(UPDATED_DATAENTREGA);
        assertThat(testManifestacao.getNumero()).isEqualTo(UPDATED_NUMERO);
    }

    @Test
    @Transactional
    public void updateNonExistingManifestacao() throws Exception {
        int databaseSizeBeforeUpdate = manifestacaoRepository.findAll().size();

        // Create the Manifestacao
        ManifestacaoDTO manifestacaoDTO = manifestacaoMapper.toDto(manifestacao);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restManifestacaoMockMvc.perform(put("/api/manifestacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manifestacaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Manifestacao in the database
        List<Manifestacao> manifestacaoList = manifestacaoRepository.findAll();
        assertThat(manifestacaoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteManifestacao() throws Exception {
        // Initialize the database
        manifestacaoRepository.saveAndFlush(manifestacao);
        int databaseSizeBeforeDelete = manifestacaoRepository.findAll().size();

        // Get the manifestacao
        restManifestacaoMockMvc.perform(delete("/api/manifestacaos/{id}", manifestacao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Manifestacao> manifestacaoList = manifestacaoRepository.findAll();
        assertThat(manifestacaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Manifestacao.class);
        Manifestacao manifestacao1 = new Manifestacao();
        manifestacao1.setId(1L);
        Manifestacao manifestacao2 = new Manifestacao();
        manifestacao2.setId(manifestacao1.getId());
        assertThat(manifestacao1).isEqualTo(manifestacao2);
        manifestacao2.setId(2L);
        assertThat(manifestacao1).isNotEqualTo(manifestacao2);
        manifestacao1.setId(null);
        assertThat(manifestacao1).isNotEqualTo(manifestacao2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ManifestacaoDTO.class);
        ManifestacaoDTO manifestacaoDTO1 = new ManifestacaoDTO();
        manifestacaoDTO1.setId(1L);
        ManifestacaoDTO manifestacaoDTO2 = new ManifestacaoDTO();
        assertThat(manifestacaoDTO1).isNotEqualTo(manifestacaoDTO2);
        manifestacaoDTO2.setId(manifestacaoDTO1.getId());
        assertThat(manifestacaoDTO1).isEqualTo(manifestacaoDTO2);
        manifestacaoDTO2.setId(2L);
        assertThat(manifestacaoDTO1).isNotEqualTo(manifestacaoDTO2);
        manifestacaoDTO1.setId(null);
        assertThat(manifestacaoDTO1).isNotEqualTo(manifestacaoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(manifestacaoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(manifestacaoMapper.fromId(null)).isNull();
    }
}
