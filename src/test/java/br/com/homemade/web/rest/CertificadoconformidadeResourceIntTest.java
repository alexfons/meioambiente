package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Certificadoconformidade;
import br.com.homemade.repository.CertificadoconformidadeRepository;
import br.com.homemade.service.dto.CertificadoconformidadeDTO;
import br.com.homemade.service.mapper.CertificadoconformidadeMapper;
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
 * Test class for the CertificadoconformidadeResource REST controller.
 *
 * @see CertificadoconformidadeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class CertificadoconformidadeResourceIntTest {

    private static final ZonedDateTime DEFAULT_DATA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATALIBERACAO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATALIBERACAO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAPARALISACAO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAPARALISACAO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAREINICIO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAREINICIO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_EDITAL = "AAAAAAAAAA";
    private static final String UPDATED_EDITAL = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM = "AAAAAAAAAA";
    private static final String UPDATED_ITEM = "BBBBBBBBBB";

    private static final String DEFAULT_LIBERACAO = "AAAAAAAAAA";
    private static final String UPDATED_LIBERACAO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_LIBERACAOADMINISTRATIVAMENTE = false;
    private static final Boolean UPDATED_LIBERACAOADMINISTRATIVAMENTE = true;

    private static final String DEFAULT_PERIODO = "AAAAAAAAAA";
    private static final String UPDATED_PERIODO = "BBBBBBBBBB";

    private static final String DEFAULT_TEXTO = "AAAAAAAAAA";
    private static final String UPDATED_TEXTO = "BBBBBBBBBB";

    private static final String DEFAULT_TEXTO_2 = "AAAAAAAAAA";
    private static final String UPDATED_TEXTO_2 = "BBBBBBBBBB";

    @Autowired
    private CertificadoconformidadeRepository certificadoconformidadeRepository;

    @Autowired
    private CertificadoconformidadeMapper certificadoconformidadeMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCertificadoconformidadeMockMvc;

    private Certificadoconformidade certificadoconformidade;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CertificadoconformidadeResource certificadoconformidadeResource = new CertificadoconformidadeResource(certificadoconformidadeRepository, certificadoconformidadeMapper);
        this.restCertificadoconformidadeMockMvc = MockMvcBuilders.standaloneSetup(certificadoconformidadeResource)
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
    public static Certificadoconformidade createEntity(EntityManager em) {
        Certificadoconformidade certificadoconformidade = new Certificadoconformidade()
            .data(DEFAULT_DATA)
            .dataliberacao(DEFAULT_DATALIBERACAO)
            .dataparalisacao(DEFAULT_DATAPARALISACAO)
            .datareinicio(DEFAULT_DATAREINICIO)
            .edital(DEFAULT_EDITAL)
            .item(DEFAULT_ITEM)
            .liberacao(DEFAULT_LIBERACAO)
            .liberacaoadministrativamente(DEFAULT_LIBERACAOADMINISTRATIVAMENTE)
            .periodo(DEFAULT_PERIODO)
            .texto(DEFAULT_TEXTO)
            .texto2(DEFAULT_TEXTO_2);
        return certificadoconformidade;
    }

    @Before
    public void initTest() {
        certificadoconformidade = createEntity(em);
    }

    @Test
    @Transactional
    public void createCertificadoconformidade() throws Exception {
        int databaseSizeBeforeCreate = certificadoconformidadeRepository.findAll().size();

        // Create the Certificadoconformidade
        CertificadoconformidadeDTO certificadoconformidadeDTO = certificadoconformidadeMapper.toDto(certificadoconformidade);
        restCertificadoconformidadeMockMvc.perform(post("/api/certificadoconformidades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certificadoconformidadeDTO)))
            .andExpect(status().isCreated());

        // Validate the Certificadoconformidade in the database
        List<Certificadoconformidade> certificadoconformidadeList = certificadoconformidadeRepository.findAll();
        assertThat(certificadoconformidadeList).hasSize(databaseSizeBeforeCreate + 1);
        Certificadoconformidade testCertificadoconformidade = certificadoconformidadeList.get(certificadoconformidadeList.size() - 1);
        assertThat(testCertificadoconformidade.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testCertificadoconformidade.getDataliberacao()).isEqualTo(DEFAULT_DATALIBERACAO);
        assertThat(testCertificadoconformidade.getDataparalisacao()).isEqualTo(DEFAULT_DATAPARALISACAO);
        assertThat(testCertificadoconformidade.getDatareinicio()).isEqualTo(DEFAULT_DATAREINICIO);
        assertThat(testCertificadoconformidade.getEdital()).isEqualTo(DEFAULT_EDITAL);
        assertThat(testCertificadoconformidade.getItem()).isEqualTo(DEFAULT_ITEM);
        assertThat(testCertificadoconformidade.getLiberacao()).isEqualTo(DEFAULT_LIBERACAO);
        assertThat(testCertificadoconformidade.isLiberacaoadministrativamente()).isEqualTo(DEFAULT_LIBERACAOADMINISTRATIVAMENTE);
        assertThat(testCertificadoconformidade.getPeriodo()).isEqualTo(DEFAULT_PERIODO);
        assertThat(testCertificadoconformidade.getTexto()).isEqualTo(DEFAULT_TEXTO);
        assertThat(testCertificadoconformidade.getTexto2()).isEqualTo(DEFAULT_TEXTO_2);
    }

    @Test
    @Transactional
    public void createCertificadoconformidadeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = certificadoconformidadeRepository.findAll().size();

        // Create the Certificadoconformidade with an existing ID
        certificadoconformidade.setId(1L);
        CertificadoconformidadeDTO certificadoconformidadeDTO = certificadoconformidadeMapper.toDto(certificadoconformidade);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCertificadoconformidadeMockMvc.perform(post("/api/certificadoconformidades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certificadoconformidadeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Certificadoconformidade> certificadoconformidadeList = certificadoconformidadeRepository.findAll();
        assertThat(certificadoconformidadeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCertificadoconformidades() throws Exception {
        // Initialize the database
        certificadoconformidadeRepository.saveAndFlush(certificadoconformidade);

        // Get all the certificadoconformidadeList
        restCertificadoconformidadeMockMvc.perform(get("/api/certificadoconformidades?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(certificadoconformidade.getId().intValue())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(sameInstant(DEFAULT_DATA))))
            .andExpect(jsonPath("$.[*].dataliberacao").value(hasItem(sameInstant(DEFAULT_DATALIBERACAO))))
            .andExpect(jsonPath("$.[*].dataparalisacao").value(hasItem(sameInstant(DEFAULT_DATAPARALISACAO))))
            .andExpect(jsonPath("$.[*].datareinicio").value(hasItem(sameInstant(DEFAULT_DATAREINICIO))))
            .andExpect(jsonPath("$.[*].edital").value(hasItem(DEFAULT_EDITAL.toString())))
            .andExpect(jsonPath("$.[*].item").value(hasItem(DEFAULT_ITEM.toString())))
            .andExpect(jsonPath("$.[*].liberacao").value(hasItem(DEFAULT_LIBERACAO.toString())))
            .andExpect(jsonPath("$.[*].liberacaoadministrativamente").value(hasItem(DEFAULT_LIBERACAOADMINISTRATIVAMENTE.booleanValue())))
            .andExpect(jsonPath("$.[*].periodo").value(hasItem(DEFAULT_PERIODO.toString())))
            .andExpect(jsonPath("$.[*].texto").value(hasItem(DEFAULT_TEXTO.toString())))
            .andExpect(jsonPath("$.[*].texto2").value(hasItem(DEFAULT_TEXTO_2.toString())));
    }

    @Test
    @Transactional
    public void getCertificadoconformidade() throws Exception {
        // Initialize the database
        certificadoconformidadeRepository.saveAndFlush(certificadoconformidade);

        // Get the certificadoconformidade
        restCertificadoconformidadeMockMvc.perform(get("/api/certificadoconformidades/{id}", certificadoconformidade.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(certificadoconformidade.getId().intValue()))
            .andExpect(jsonPath("$.data").value(sameInstant(DEFAULT_DATA)))
            .andExpect(jsonPath("$.dataliberacao").value(sameInstant(DEFAULT_DATALIBERACAO)))
            .andExpect(jsonPath("$.dataparalisacao").value(sameInstant(DEFAULT_DATAPARALISACAO)))
            .andExpect(jsonPath("$.datareinicio").value(sameInstant(DEFAULT_DATAREINICIO)))
            .andExpect(jsonPath("$.edital").value(DEFAULT_EDITAL.toString()))
            .andExpect(jsonPath("$.item").value(DEFAULT_ITEM.toString()))
            .andExpect(jsonPath("$.liberacao").value(DEFAULT_LIBERACAO.toString()))
            .andExpect(jsonPath("$.liberacaoadministrativamente").value(DEFAULT_LIBERACAOADMINISTRATIVAMENTE.booleanValue()))
            .andExpect(jsonPath("$.periodo").value(DEFAULT_PERIODO.toString()))
            .andExpect(jsonPath("$.texto").value(DEFAULT_TEXTO.toString()))
            .andExpect(jsonPath("$.texto2").value(DEFAULT_TEXTO_2.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCertificadoconformidade() throws Exception {
        // Get the certificadoconformidade
        restCertificadoconformidadeMockMvc.perform(get("/api/certificadoconformidades/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCertificadoconformidade() throws Exception {
        // Initialize the database
        certificadoconformidadeRepository.saveAndFlush(certificadoconformidade);
        int databaseSizeBeforeUpdate = certificadoconformidadeRepository.findAll().size();

        // Update the certificadoconformidade
        Certificadoconformidade updatedCertificadoconformidade = certificadoconformidadeRepository.findOne(certificadoconformidade.getId());
        updatedCertificadoconformidade
            .data(UPDATED_DATA)
            .dataliberacao(UPDATED_DATALIBERACAO)
            .dataparalisacao(UPDATED_DATAPARALISACAO)
            .datareinicio(UPDATED_DATAREINICIO)
            .edital(UPDATED_EDITAL)
            .item(UPDATED_ITEM)
            .liberacao(UPDATED_LIBERACAO)
            .liberacaoadministrativamente(UPDATED_LIBERACAOADMINISTRATIVAMENTE)
            .periodo(UPDATED_PERIODO)
            .texto(UPDATED_TEXTO)
            .texto2(UPDATED_TEXTO_2);
        CertificadoconformidadeDTO certificadoconformidadeDTO = certificadoconformidadeMapper.toDto(updatedCertificadoconformidade);

        restCertificadoconformidadeMockMvc.perform(put("/api/certificadoconformidades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certificadoconformidadeDTO)))
            .andExpect(status().isOk());

        // Validate the Certificadoconformidade in the database
        List<Certificadoconformidade> certificadoconformidadeList = certificadoconformidadeRepository.findAll();
        assertThat(certificadoconformidadeList).hasSize(databaseSizeBeforeUpdate);
        Certificadoconformidade testCertificadoconformidade = certificadoconformidadeList.get(certificadoconformidadeList.size() - 1);
        assertThat(testCertificadoconformidade.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testCertificadoconformidade.getDataliberacao()).isEqualTo(UPDATED_DATALIBERACAO);
        assertThat(testCertificadoconformidade.getDataparalisacao()).isEqualTo(UPDATED_DATAPARALISACAO);
        assertThat(testCertificadoconformidade.getDatareinicio()).isEqualTo(UPDATED_DATAREINICIO);
        assertThat(testCertificadoconformidade.getEdital()).isEqualTo(UPDATED_EDITAL);
        assertThat(testCertificadoconformidade.getItem()).isEqualTo(UPDATED_ITEM);
        assertThat(testCertificadoconformidade.getLiberacao()).isEqualTo(UPDATED_LIBERACAO);
        assertThat(testCertificadoconformidade.isLiberacaoadministrativamente()).isEqualTo(UPDATED_LIBERACAOADMINISTRATIVAMENTE);
        assertThat(testCertificadoconformidade.getPeriodo()).isEqualTo(UPDATED_PERIODO);
        assertThat(testCertificadoconformidade.getTexto()).isEqualTo(UPDATED_TEXTO);
        assertThat(testCertificadoconformidade.getTexto2()).isEqualTo(UPDATED_TEXTO_2);
    }

    @Test
    @Transactional
    public void updateNonExistingCertificadoconformidade() throws Exception {
        int databaseSizeBeforeUpdate = certificadoconformidadeRepository.findAll().size();

        // Create the Certificadoconformidade
        CertificadoconformidadeDTO certificadoconformidadeDTO = certificadoconformidadeMapper.toDto(certificadoconformidade);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCertificadoconformidadeMockMvc.perform(put("/api/certificadoconformidades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certificadoconformidadeDTO)))
            .andExpect(status().isCreated());

        // Validate the Certificadoconformidade in the database
        List<Certificadoconformidade> certificadoconformidadeList = certificadoconformidadeRepository.findAll();
        assertThat(certificadoconformidadeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCertificadoconformidade() throws Exception {
        // Initialize the database
        certificadoconformidadeRepository.saveAndFlush(certificadoconformidade);
        int databaseSizeBeforeDelete = certificadoconformidadeRepository.findAll().size();

        // Get the certificadoconformidade
        restCertificadoconformidadeMockMvc.perform(delete("/api/certificadoconformidades/{id}", certificadoconformidade.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Certificadoconformidade> certificadoconformidadeList = certificadoconformidadeRepository.findAll();
        assertThat(certificadoconformidadeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Certificadoconformidade.class);
        Certificadoconformidade certificadoconformidade1 = new Certificadoconformidade();
        certificadoconformidade1.setId(1L);
        Certificadoconformidade certificadoconformidade2 = new Certificadoconformidade();
        certificadoconformidade2.setId(certificadoconformidade1.getId());
        assertThat(certificadoconformidade1).isEqualTo(certificadoconformidade2);
        certificadoconformidade2.setId(2L);
        assertThat(certificadoconformidade1).isNotEqualTo(certificadoconformidade2);
        certificadoconformidade1.setId(null);
        assertThat(certificadoconformidade1).isNotEqualTo(certificadoconformidade2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CertificadoconformidadeDTO.class);
        CertificadoconformidadeDTO certificadoconformidadeDTO1 = new CertificadoconformidadeDTO();
        certificadoconformidadeDTO1.setId(1L);
        CertificadoconformidadeDTO certificadoconformidadeDTO2 = new CertificadoconformidadeDTO();
        assertThat(certificadoconformidadeDTO1).isNotEqualTo(certificadoconformidadeDTO2);
        certificadoconformidadeDTO2.setId(certificadoconformidadeDTO1.getId());
        assertThat(certificadoconformidadeDTO1).isEqualTo(certificadoconformidadeDTO2);
        certificadoconformidadeDTO2.setId(2L);
        assertThat(certificadoconformidadeDTO1).isNotEqualTo(certificadoconformidadeDTO2);
        certificadoconformidadeDTO1.setId(null);
        assertThat(certificadoconformidadeDTO1).isNotEqualTo(certificadoconformidadeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(certificadoconformidadeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(certificadoconformidadeMapper.fromId(null)).isNull();
    }
}
