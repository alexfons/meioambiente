package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.CertifConfor;
import br.com.homemade.repository.CertifConforRepository;
import br.com.homemade.service.dto.CertifConforDTO;
import br.com.homemade.service.mapper.CertifConforMapper;
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
 * Test class for the CertifConforResource REST controller.
 *
 * @see CertifConforResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class CertifConforResourceIntTest {

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
    private CertifConforRepository certifConforRepository;

    @Autowired
    private CertifConforMapper certifConforMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCertifConforMockMvc;

    private CertifConfor certifConfor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CertifConforResource certifConforResource = new CertifConforResource(certifConforRepository, certifConforMapper);
        this.restCertifConforMockMvc = MockMvcBuilders.standaloneSetup(certifConforResource)
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
    public static CertifConfor createEntity(EntityManager em) {
        CertifConfor certifConfor = new CertifConfor()
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
        return certifConfor;
    }

    @Before
    public void initTest() {
        certifConfor = createEntity(em);
    }

    @Test
    @Transactional
    public void createCertifConfor() throws Exception {
        int databaseSizeBeforeCreate = certifConforRepository.findAll().size();

        // Create the CertifConfor
        CertifConforDTO certifConforDTO = certifConforMapper.toDto(certifConfor);
        restCertifConforMockMvc.perform(post("/api/certif-confors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certifConforDTO)))
            .andExpect(status().isCreated());

        // Validate the CertifConfor in the database
        List<CertifConfor> certifConforList = certifConforRepository.findAll();
        assertThat(certifConforList).hasSize(databaseSizeBeforeCreate + 1);
        CertifConfor testCertifConfor = certifConforList.get(certifConforList.size() - 1);
        assertThat(testCertifConfor.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testCertifConfor.getDataliberacao()).isEqualTo(DEFAULT_DATALIBERACAO);
        assertThat(testCertifConfor.getDataparalisacao()).isEqualTo(DEFAULT_DATAPARALISACAO);
        assertThat(testCertifConfor.getDatareinicio()).isEqualTo(DEFAULT_DATAREINICIO);
        assertThat(testCertifConfor.getEdital()).isEqualTo(DEFAULT_EDITAL);
        assertThat(testCertifConfor.getItem()).isEqualTo(DEFAULT_ITEM);
        assertThat(testCertifConfor.getLiberacao()).isEqualTo(DEFAULT_LIBERACAO);
        assertThat(testCertifConfor.isLiberacaoadministrativamente()).isEqualTo(DEFAULT_LIBERACAOADMINISTRATIVAMENTE);
        assertThat(testCertifConfor.getPeriodo()).isEqualTo(DEFAULT_PERIODO);
        assertThat(testCertifConfor.getTexto()).isEqualTo(DEFAULT_TEXTO);
        assertThat(testCertifConfor.getTexto2()).isEqualTo(DEFAULT_TEXTO_2);
    }

    @Test
    @Transactional
    public void createCertifConforWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = certifConforRepository.findAll().size();

        // Create the CertifConfor with an existing ID
        certifConfor.setId(1L);
        CertifConforDTO certifConforDTO = certifConforMapper.toDto(certifConfor);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCertifConforMockMvc.perform(post("/api/certif-confors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certifConforDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<CertifConfor> certifConforList = certifConforRepository.findAll();
        assertThat(certifConforList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCertifConfors() throws Exception {
        // Initialize the database
        certifConforRepository.saveAndFlush(certifConfor);

        // Get all the certifConforList
        restCertifConforMockMvc.perform(get("/api/certif-confors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(certifConfor.getId().intValue())))
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
    public void getCertifConfor() throws Exception {
        // Initialize the database
        certifConforRepository.saveAndFlush(certifConfor);

        // Get the certifConfor
        restCertifConforMockMvc.perform(get("/api/certif-confors/{id}", certifConfor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(certifConfor.getId().intValue()))
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
    public void getNonExistingCertifConfor() throws Exception {
        // Get the certifConfor
        restCertifConforMockMvc.perform(get("/api/certif-confors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCertifConfor() throws Exception {
        // Initialize the database
        certifConforRepository.saveAndFlush(certifConfor);
        int databaseSizeBeforeUpdate = certifConforRepository.findAll().size();

        // Update the certifConfor
        CertifConfor updatedCertifConfor = certifConforRepository.findOne(certifConfor.getId());
        updatedCertifConfor
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
        CertifConforDTO certifConforDTO = certifConforMapper.toDto(updatedCertifConfor);

        restCertifConforMockMvc.perform(put("/api/certif-confors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certifConforDTO)))
            .andExpect(status().isOk());

        // Validate the CertifConfor in the database
        List<CertifConfor> certifConforList = certifConforRepository.findAll();
        assertThat(certifConforList).hasSize(databaseSizeBeforeUpdate);
        CertifConfor testCertifConfor = certifConforList.get(certifConforList.size() - 1);
        assertThat(testCertifConfor.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testCertifConfor.getDataliberacao()).isEqualTo(UPDATED_DATALIBERACAO);
        assertThat(testCertifConfor.getDataparalisacao()).isEqualTo(UPDATED_DATAPARALISACAO);
        assertThat(testCertifConfor.getDatareinicio()).isEqualTo(UPDATED_DATAREINICIO);
        assertThat(testCertifConfor.getEdital()).isEqualTo(UPDATED_EDITAL);
        assertThat(testCertifConfor.getItem()).isEqualTo(UPDATED_ITEM);
        assertThat(testCertifConfor.getLiberacao()).isEqualTo(UPDATED_LIBERACAO);
        assertThat(testCertifConfor.isLiberacaoadministrativamente()).isEqualTo(UPDATED_LIBERACAOADMINISTRATIVAMENTE);
        assertThat(testCertifConfor.getPeriodo()).isEqualTo(UPDATED_PERIODO);
        assertThat(testCertifConfor.getTexto()).isEqualTo(UPDATED_TEXTO);
        assertThat(testCertifConfor.getTexto2()).isEqualTo(UPDATED_TEXTO_2);
    }

    @Test
    @Transactional
    public void updateNonExistingCertifConfor() throws Exception {
        int databaseSizeBeforeUpdate = certifConforRepository.findAll().size();

        // Create the CertifConfor
        CertifConforDTO certifConforDTO = certifConforMapper.toDto(certifConfor);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCertifConforMockMvc.perform(put("/api/certif-confors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certifConforDTO)))
            .andExpect(status().isCreated());

        // Validate the CertifConfor in the database
        List<CertifConfor> certifConforList = certifConforRepository.findAll();
        assertThat(certifConforList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCertifConfor() throws Exception {
        // Initialize the database
        certifConforRepository.saveAndFlush(certifConfor);
        int databaseSizeBeforeDelete = certifConforRepository.findAll().size();

        // Get the certifConfor
        restCertifConforMockMvc.perform(delete("/api/certif-confors/{id}", certifConfor.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CertifConfor> certifConforList = certifConforRepository.findAll();
        assertThat(certifConforList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CertifConfor.class);
        CertifConfor certifConfor1 = new CertifConfor();
        certifConfor1.setId(1L);
        CertifConfor certifConfor2 = new CertifConfor();
        certifConfor2.setId(certifConfor1.getId());
        assertThat(certifConfor1).isEqualTo(certifConfor2);
        certifConfor2.setId(2L);
        assertThat(certifConfor1).isNotEqualTo(certifConfor2);
        certifConfor1.setId(null);
        assertThat(certifConfor1).isNotEqualTo(certifConfor2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CertifConforDTO.class);
        CertifConforDTO certifConforDTO1 = new CertifConforDTO();
        certifConforDTO1.setId(1L);
        CertifConforDTO certifConforDTO2 = new CertifConforDTO();
        assertThat(certifConforDTO1).isNotEqualTo(certifConforDTO2);
        certifConforDTO2.setId(certifConforDTO1.getId());
        assertThat(certifConforDTO1).isEqualTo(certifConforDTO2);
        certifConforDTO2.setId(2L);
        assertThat(certifConforDTO1).isNotEqualTo(certifConforDTO2);
        certifConforDTO1.setId(null);
        assertThat(certifConforDTO1).isNotEqualTo(certifConforDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(certifConforMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(certifConforMapper.fromId(null)).isNull();
    }
}
