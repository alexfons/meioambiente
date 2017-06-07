package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Edital;
import br.com.homemade.repository.EditalRepository;
import br.com.homemade.service.dto.EditalDTO;
import br.com.homemade.service.mapper.EditalMapper;
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
import java.math.BigDecimal;
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
 * Test class for the EditalResource REST controller.
 *
 * @see EditalResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class EditalResourceIntTest {

    private static final String DEFAULT_COMISSAOJULGAMENTO = "AAAAAAAAAA";
    private static final String UPDATED_COMISSAOJULGAMENTO = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATAAPROVACAO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAAPROVACAO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAENVIO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAENVIO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAHORAABERTURA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAHORAABERTURA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATALICITACAO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATALICITACAO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATANUMEROMANIFESTACAO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATANUMEROMANIFESTACAO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAPUBLICACAO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAPUBLICACAO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATARELATORIO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATARELATORIO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_LICITACAO = "AAAAAAAAAA";
    private static final String UPDATED_LICITACAO = "BBBBBBBBBB";

    private static final String DEFAULT_LOCALRELATORIO = "AAAAAAAAAA";
    private static final String UPDATED_LOCALRELATORIO = "BBBBBBBBBB";

    private static final String DEFAULT_MODALIDADE = "AAAAAAAAAA";
    private static final String UPDATED_MODALIDADE = "BBBBBBBBBB";

    private static final String DEFAULT_NATUREZASERVICO = "AAAAAAAAAA";
    private static final String UPDATED_NATUREZASERVICO = "BBBBBBBBBB";

    private static final String DEFAULT_NUMEROCONVITE = "AAAAAAAAAA";
    private static final String UPDATED_NUMEROCONVITE = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMEROEDITAL = 1;
    private static final Integer UPDATED_NUMEROEDITAL = 2;

    private static final String DEFAULT_NUMEROMANIFESTACAO = "AAAAAAAAAA";
    private static final String UPDATED_NUMEROMANIFESTACAO = "BBBBBBBBBB";

    private static final String DEFAULT_NUMEROPROJETO = "AAAAAAAAAA";
    private static final String UPDATED_NUMEROPROJETO = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_PRECOGLOBAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRECOGLOBAL = new BigDecimal(2);

    private static final String DEFAULT_TIPOAQUISICAO = "AAAAAAAAAA";
    private static final String UPDATED_TIPOAQUISICAO = "BBBBBBBBBB";

    private static final String DEFAULT_TIPOLICITACAO = "AAAAAAAAAA";
    private static final String UPDATED_TIPOLICITACAO = "BBBBBBBBBB";

    @Autowired
    private EditalRepository editalRepository;

    @Autowired
    private EditalMapper editalMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEditalMockMvc;

    private Edital edital;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        EditalResource editalResource = new EditalResource(editalRepository, editalMapper);
        this.restEditalMockMvc = MockMvcBuilders.standaloneSetup(editalResource)
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
    public static Edital createEntity(EntityManager em) {
        Edital edital = new Edital()
            .comissaojulgamento(DEFAULT_COMISSAOJULGAMENTO)
            .dataaprovacao(DEFAULT_DATAAPROVACAO)
            .dataenvio(DEFAULT_DATAENVIO)
            .datahoraabertura(DEFAULT_DATAHORAABERTURA)
            .datalicitacao(DEFAULT_DATALICITACAO)
            .datanumeromanifestacao(DEFAULT_DATANUMEROMANIFESTACAO)
            .datapublicacao(DEFAULT_DATAPUBLICACAO)
            .datarelatorio(DEFAULT_DATARELATORIO)
            .licitacao(DEFAULT_LICITACAO)
            .localrelatorio(DEFAULT_LOCALRELATORIO)
            .modalidade(DEFAULT_MODALIDADE)
            .naturezaservico(DEFAULT_NATUREZASERVICO)
            .numeroconvite(DEFAULT_NUMEROCONVITE)
            .numeroedital(DEFAULT_NUMEROEDITAL)
            .numeromanifestacao(DEFAULT_NUMEROMANIFESTACAO)
            .numeroprojeto(DEFAULT_NUMEROPROJETO)
            .precoglobal(DEFAULT_PRECOGLOBAL)
            .tipoaquisicao(DEFAULT_TIPOAQUISICAO)
            .tipolicitacao(DEFAULT_TIPOLICITACAO);
        return edital;
    }

    @Before
    public void initTest() {
        edital = createEntity(em);
    }

    @Test
    @Transactional
    public void createEdital() throws Exception {
        int databaseSizeBeforeCreate = editalRepository.findAll().size();

        // Create the Edital
        EditalDTO editalDTO = editalMapper.toDto(edital);
        restEditalMockMvc.perform(post("/api/editals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(editalDTO)))
            .andExpect(status().isCreated());

        // Validate the Edital in the database
        List<Edital> editalList = editalRepository.findAll();
        assertThat(editalList).hasSize(databaseSizeBeforeCreate + 1);
        Edital testEdital = editalList.get(editalList.size() - 1);
        assertThat(testEdital.getComissaojulgamento()).isEqualTo(DEFAULT_COMISSAOJULGAMENTO);
        assertThat(testEdital.getDataaprovacao()).isEqualTo(DEFAULT_DATAAPROVACAO);
        assertThat(testEdital.getDataenvio()).isEqualTo(DEFAULT_DATAENVIO);
        assertThat(testEdital.getDatahoraabertura()).isEqualTo(DEFAULT_DATAHORAABERTURA);
        assertThat(testEdital.getDatalicitacao()).isEqualTo(DEFAULT_DATALICITACAO);
        assertThat(testEdital.getDatanumeromanifestacao()).isEqualTo(DEFAULT_DATANUMEROMANIFESTACAO);
        assertThat(testEdital.getDatapublicacao()).isEqualTo(DEFAULT_DATAPUBLICACAO);
        assertThat(testEdital.getDatarelatorio()).isEqualTo(DEFAULT_DATARELATORIO);
        assertThat(testEdital.getLicitacao()).isEqualTo(DEFAULT_LICITACAO);
        assertThat(testEdital.getLocalrelatorio()).isEqualTo(DEFAULT_LOCALRELATORIO);
        assertThat(testEdital.getModalidade()).isEqualTo(DEFAULT_MODALIDADE);
        assertThat(testEdital.getNaturezaservico()).isEqualTo(DEFAULT_NATUREZASERVICO);
        assertThat(testEdital.getNumeroconvite()).isEqualTo(DEFAULT_NUMEROCONVITE);
        assertThat(testEdital.getNumeroedital()).isEqualTo(DEFAULT_NUMEROEDITAL);
        assertThat(testEdital.getNumeromanifestacao()).isEqualTo(DEFAULT_NUMEROMANIFESTACAO);
        assertThat(testEdital.getNumeroprojeto()).isEqualTo(DEFAULT_NUMEROPROJETO);
        assertThat(testEdital.getPrecoglobal()).isEqualTo(DEFAULT_PRECOGLOBAL);
        assertThat(testEdital.getTipoaquisicao()).isEqualTo(DEFAULT_TIPOAQUISICAO);
        assertThat(testEdital.getTipolicitacao()).isEqualTo(DEFAULT_TIPOLICITACAO);
    }

    @Test
    @Transactional
    public void createEditalWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = editalRepository.findAll().size();

        // Create the Edital with an existing ID
        edital.setId(1L);
        EditalDTO editalDTO = editalMapper.toDto(edital);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEditalMockMvc.perform(post("/api/editals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(editalDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Edital> editalList = editalRepository.findAll();
        assertThat(editalList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEditals() throws Exception {
        // Initialize the database
        editalRepository.saveAndFlush(edital);

        // Get all the editalList
        restEditalMockMvc.perform(get("/api/editals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(edital.getId().intValue())))
            .andExpect(jsonPath("$.[*].comissaojulgamento").value(hasItem(DEFAULT_COMISSAOJULGAMENTO.toString())))
            .andExpect(jsonPath("$.[*].dataaprovacao").value(hasItem(sameInstant(DEFAULT_DATAAPROVACAO))))
            .andExpect(jsonPath("$.[*].dataenvio").value(hasItem(sameInstant(DEFAULT_DATAENVIO))))
            .andExpect(jsonPath("$.[*].datahoraabertura").value(hasItem(sameInstant(DEFAULT_DATAHORAABERTURA))))
            .andExpect(jsonPath("$.[*].datalicitacao").value(hasItem(sameInstant(DEFAULT_DATALICITACAO))))
            .andExpect(jsonPath("$.[*].datanumeromanifestacao").value(hasItem(sameInstant(DEFAULT_DATANUMEROMANIFESTACAO))))
            .andExpect(jsonPath("$.[*].datapublicacao").value(hasItem(sameInstant(DEFAULT_DATAPUBLICACAO))))
            .andExpect(jsonPath("$.[*].datarelatorio").value(hasItem(sameInstant(DEFAULT_DATARELATORIO))))
            .andExpect(jsonPath("$.[*].licitacao").value(hasItem(DEFAULT_LICITACAO.toString())))
            .andExpect(jsonPath("$.[*].localrelatorio").value(hasItem(DEFAULT_LOCALRELATORIO.toString())))
            .andExpect(jsonPath("$.[*].modalidade").value(hasItem(DEFAULT_MODALIDADE.toString())))
            .andExpect(jsonPath("$.[*].naturezaservico").value(hasItem(DEFAULT_NATUREZASERVICO.toString())))
            .andExpect(jsonPath("$.[*].numeroconvite").value(hasItem(DEFAULT_NUMEROCONVITE.toString())))
            .andExpect(jsonPath("$.[*].numeroedital").value(hasItem(DEFAULT_NUMEROEDITAL)))
            .andExpect(jsonPath("$.[*].numeromanifestacao").value(hasItem(DEFAULT_NUMEROMANIFESTACAO.toString())))
            .andExpect(jsonPath("$.[*].numeroprojeto").value(hasItem(DEFAULT_NUMEROPROJETO.toString())))
            .andExpect(jsonPath("$.[*].precoglobal").value(hasItem(DEFAULT_PRECOGLOBAL.intValue())))
            .andExpect(jsonPath("$.[*].tipoaquisicao").value(hasItem(DEFAULT_TIPOAQUISICAO.toString())))
            .andExpect(jsonPath("$.[*].tipolicitacao").value(hasItem(DEFAULT_TIPOLICITACAO.toString())));
    }

    @Test
    @Transactional
    public void getEdital() throws Exception {
        // Initialize the database
        editalRepository.saveAndFlush(edital);

        // Get the edital
        restEditalMockMvc.perform(get("/api/editals/{id}", edital.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(edital.getId().intValue()))
            .andExpect(jsonPath("$.comissaojulgamento").value(DEFAULT_COMISSAOJULGAMENTO.toString()))
            .andExpect(jsonPath("$.dataaprovacao").value(sameInstant(DEFAULT_DATAAPROVACAO)))
            .andExpect(jsonPath("$.dataenvio").value(sameInstant(DEFAULT_DATAENVIO)))
            .andExpect(jsonPath("$.datahoraabertura").value(sameInstant(DEFAULT_DATAHORAABERTURA)))
            .andExpect(jsonPath("$.datalicitacao").value(sameInstant(DEFAULT_DATALICITACAO)))
            .andExpect(jsonPath("$.datanumeromanifestacao").value(sameInstant(DEFAULT_DATANUMEROMANIFESTACAO)))
            .andExpect(jsonPath("$.datapublicacao").value(sameInstant(DEFAULT_DATAPUBLICACAO)))
            .andExpect(jsonPath("$.datarelatorio").value(sameInstant(DEFAULT_DATARELATORIO)))
            .andExpect(jsonPath("$.licitacao").value(DEFAULT_LICITACAO.toString()))
            .andExpect(jsonPath("$.localrelatorio").value(DEFAULT_LOCALRELATORIO.toString()))
            .andExpect(jsonPath("$.modalidade").value(DEFAULT_MODALIDADE.toString()))
            .andExpect(jsonPath("$.naturezaservico").value(DEFAULT_NATUREZASERVICO.toString()))
            .andExpect(jsonPath("$.numeroconvite").value(DEFAULT_NUMEROCONVITE.toString()))
            .andExpect(jsonPath("$.numeroedital").value(DEFAULT_NUMEROEDITAL))
            .andExpect(jsonPath("$.numeromanifestacao").value(DEFAULT_NUMEROMANIFESTACAO.toString()))
            .andExpect(jsonPath("$.numeroprojeto").value(DEFAULT_NUMEROPROJETO.toString()))
            .andExpect(jsonPath("$.precoglobal").value(DEFAULT_PRECOGLOBAL.intValue()))
            .andExpect(jsonPath("$.tipoaquisicao").value(DEFAULT_TIPOAQUISICAO.toString()))
            .andExpect(jsonPath("$.tipolicitacao").value(DEFAULT_TIPOLICITACAO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEdital() throws Exception {
        // Get the edital
        restEditalMockMvc.perform(get("/api/editals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEdital() throws Exception {
        // Initialize the database
        editalRepository.saveAndFlush(edital);
        int databaseSizeBeforeUpdate = editalRepository.findAll().size();

        // Update the edital
        Edital updatedEdital = editalRepository.findOne(edital.getId());
        updatedEdital
            .comissaojulgamento(UPDATED_COMISSAOJULGAMENTO)
            .dataaprovacao(UPDATED_DATAAPROVACAO)
            .dataenvio(UPDATED_DATAENVIO)
            .datahoraabertura(UPDATED_DATAHORAABERTURA)
            .datalicitacao(UPDATED_DATALICITACAO)
            .datanumeromanifestacao(UPDATED_DATANUMEROMANIFESTACAO)
            .datapublicacao(UPDATED_DATAPUBLICACAO)
            .datarelatorio(UPDATED_DATARELATORIO)
            .licitacao(UPDATED_LICITACAO)
            .localrelatorio(UPDATED_LOCALRELATORIO)
            .modalidade(UPDATED_MODALIDADE)
            .naturezaservico(UPDATED_NATUREZASERVICO)
            .numeroconvite(UPDATED_NUMEROCONVITE)
            .numeroedital(UPDATED_NUMEROEDITAL)
            .numeromanifestacao(UPDATED_NUMEROMANIFESTACAO)
            .numeroprojeto(UPDATED_NUMEROPROJETO)
            .precoglobal(UPDATED_PRECOGLOBAL)
            .tipoaquisicao(UPDATED_TIPOAQUISICAO)
            .tipolicitacao(UPDATED_TIPOLICITACAO);
        EditalDTO editalDTO = editalMapper.toDto(updatedEdital);

        restEditalMockMvc.perform(put("/api/editals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(editalDTO)))
            .andExpect(status().isOk());

        // Validate the Edital in the database
        List<Edital> editalList = editalRepository.findAll();
        assertThat(editalList).hasSize(databaseSizeBeforeUpdate);
        Edital testEdital = editalList.get(editalList.size() - 1);
        assertThat(testEdital.getComissaojulgamento()).isEqualTo(UPDATED_COMISSAOJULGAMENTO);
        assertThat(testEdital.getDataaprovacao()).isEqualTo(UPDATED_DATAAPROVACAO);
        assertThat(testEdital.getDataenvio()).isEqualTo(UPDATED_DATAENVIO);
        assertThat(testEdital.getDatahoraabertura()).isEqualTo(UPDATED_DATAHORAABERTURA);
        assertThat(testEdital.getDatalicitacao()).isEqualTo(UPDATED_DATALICITACAO);
        assertThat(testEdital.getDatanumeromanifestacao()).isEqualTo(UPDATED_DATANUMEROMANIFESTACAO);
        assertThat(testEdital.getDatapublicacao()).isEqualTo(UPDATED_DATAPUBLICACAO);
        assertThat(testEdital.getDatarelatorio()).isEqualTo(UPDATED_DATARELATORIO);
        assertThat(testEdital.getLicitacao()).isEqualTo(UPDATED_LICITACAO);
        assertThat(testEdital.getLocalrelatorio()).isEqualTo(UPDATED_LOCALRELATORIO);
        assertThat(testEdital.getModalidade()).isEqualTo(UPDATED_MODALIDADE);
        assertThat(testEdital.getNaturezaservico()).isEqualTo(UPDATED_NATUREZASERVICO);
        assertThat(testEdital.getNumeroconvite()).isEqualTo(UPDATED_NUMEROCONVITE);
        assertThat(testEdital.getNumeroedital()).isEqualTo(UPDATED_NUMEROEDITAL);
        assertThat(testEdital.getNumeromanifestacao()).isEqualTo(UPDATED_NUMEROMANIFESTACAO);
        assertThat(testEdital.getNumeroprojeto()).isEqualTo(UPDATED_NUMEROPROJETO);
        assertThat(testEdital.getPrecoglobal()).isEqualTo(UPDATED_PRECOGLOBAL);
        assertThat(testEdital.getTipoaquisicao()).isEqualTo(UPDATED_TIPOAQUISICAO);
        assertThat(testEdital.getTipolicitacao()).isEqualTo(UPDATED_TIPOLICITACAO);
    }

    @Test
    @Transactional
    public void updateNonExistingEdital() throws Exception {
        int databaseSizeBeforeUpdate = editalRepository.findAll().size();

        // Create the Edital
        EditalDTO editalDTO = editalMapper.toDto(edital);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEditalMockMvc.perform(put("/api/editals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(editalDTO)))
            .andExpect(status().isCreated());

        // Validate the Edital in the database
        List<Edital> editalList = editalRepository.findAll();
        assertThat(editalList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteEdital() throws Exception {
        // Initialize the database
        editalRepository.saveAndFlush(edital);
        int databaseSizeBeforeDelete = editalRepository.findAll().size();

        // Get the edital
        restEditalMockMvc.perform(delete("/api/editals/{id}", edital.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Edital> editalList = editalRepository.findAll();
        assertThat(editalList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Edital.class);
        Edital edital1 = new Edital();
        edital1.setId(1L);
        Edital edital2 = new Edital();
        edital2.setId(edital1.getId());
        assertThat(edital1).isEqualTo(edital2);
        edital2.setId(2L);
        assertThat(edital1).isNotEqualTo(edital2);
        edital1.setId(null);
        assertThat(edital1).isNotEqualTo(edital2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EditalDTO.class);
        EditalDTO editalDTO1 = new EditalDTO();
        editalDTO1.setId(1L);
        EditalDTO editalDTO2 = new EditalDTO();
        assertThat(editalDTO1).isNotEqualTo(editalDTO2);
        editalDTO2.setId(editalDTO1.getId());
        assertThat(editalDTO1).isEqualTo(editalDTO2);
        editalDTO2.setId(2L);
        assertThat(editalDTO1).isNotEqualTo(editalDTO2);
        editalDTO1.setId(null);
        assertThat(editalDTO1).isNotEqualTo(editalDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(editalMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(editalMapper.fromId(null)).isNull();
    }
}
