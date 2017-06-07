package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Contratoagente;
import br.com.homemade.repository.ContratoagenteRepository;
import br.com.homemade.service.dto.ContratoagenteDTO;
import br.com.homemade.service.mapper.ContratoagenteMapper;
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
 * Test class for the ContratoagenteResource REST controller.
 *
 * @see ContratoagenteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class ContratoagenteResourceIntTest {

    private static final ZonedDateTime DEFAULT_DATAAPROVACAO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAAPROVACAO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAASSINATURA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAASSINATURA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATACONCLUSAO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATACONCLUSAO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAINICIO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAINICIO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_IDCONTRATOAGENTE = 1;
    private static final Integer UPDATED_IDCONTRATOAGENTE = 2;

    private static final String DEFAULT_NOMECONTRATOAGENTE = "AAAAAAAAAA";
    private static final String UPDATED_NOMECONTRATOAGENTE = "BBBBBBBBBB";

    private static final String DEFAULT_REFERENCIABID = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCIABID = "BBBBBBBBBB";

    private static final String DEFAULT_ACORDOCREDITO = "AAAAAAAAAA";
    private static final String UPDATED_ACORDOCREDITO = "BBBBBBBBBB";

    private static final String DEFAULT_MUTUARIO = "AAAAAAAAAA";
    private static final String UPDATED_MUTUARIO = "BBBBBBBBBB";

    private static final String DEFAULT_EXECUTOR = "AAAAAAAAAA";
    private static final String UPDATED_EXECUTOR = "BBBBBBBBBB";

    private static final String DEFAULT_CLAUSULASCONTRATUAIS = "AAAAAAAAAA";
    private static final String UPDATED_CLAUSULASCONTRATUAIS = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final String DEFAULT_MOEDA = "AAAAAAAAAA";
    private static final String UPDATED_MOEDA = "BBBBBBBBBB";

    private static final Double DEFAULT_PERCENTUALLOCAL = 1D;
    private static final Double UPDATED_PERCENTUALLOCAL = 2D;

    private static final Double DEFAULT_PERCENTUALAGENTE = 1D;
    private static final Double UPDATED_PERCENTUALAGENTE = 2D;

    private static final Double DEFAULT_VALORCONTRATO = 1D;
    private static final Double UPDATED_VALORCONTRATO = 2D;

    private static final Double DEFAULT_VALOR = 1D;
    private static final Double UPDATED_VALOR = 2D;

    @Autowired
    private ContratoagenteRepository contratoagenteRepository;

    @Autowired
    private ContratoagenteMapper contratoagenteMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restContratoagenteMockMvc;

    private Contratoagente contratoagente;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ContratoagenteResource contratoagenteResource = new ContratoagenteResource(contratoagenteRepository, contratoagenteMapper);
        this.restContratoagenteMockMvc = MockMvcBuilders.standaloneSetup(contratoagenteResource)
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
    public static Contratoagente createEntity(EntityManager em) {
        Contratoagente contratoagente = new Contratoagente()
            .dataaprovacao(DEFAULT_DATAAPROVACAO)
            .dataassinatura(DEFAULT_DATAASSINATURA)
            .dataconclusao(DEFAULT_DATACONCLUSAO)
            .datainicio(DEFAULT_DATAINICIO)
            .idcontratoagente(DEFAULT_IDCONTRATOAGENTE)
            .nomecontratoagente(DEFAULT_NOMECONTRATOAGENTE)
            .referenciabid(DEFAULT_REFERENCIABID)
            .acordocredito(DEFAULT_ACORDOCREDITO)
            .mutuario(DEFAULT_MUTUARIO)
            .executor(DEFAULT_EXECUTOR)
            .clausulascontratuais(DEFAULT_CLAUSULASCONTRATUAIS)
            .descricao(DEFAULT_DESCRICAO)
            .moeda(DEFAULT_MOEDA)
            .percentuallocal(DEFAULT_PERCENTUALLOCAL)
            .percentualagente(DEFAULT_PERCENTUALAGENTE)
            .valorcontrato(DEFAULT_VALORCONTRATO)
            .valor(DEFAULT_VALOR);
        return contratoagente;
    }

    @Before
    public void initTest() {
        contratoagente = createEntity(em);
    }

    @Test
    @Transactional
    public void createContratoagente() throws Exception {
        int databaseSizeBeforeCreate = contratoagenteRepository.findAll().size();

        // Create the Contratoagente
        ContratoagenteDTO contratoagenteDTO = contratoagenteMapper.toDto(contratoagente);
        restContratoagenteMockMvc.perform(post("/api/contratoagentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratoagenteDTO)))
            .andExpect(status().isCreated());

        // Validate the Contratoagente in the database
        List<Contratoagente> contratoagenteList = contratoagenteRepository.findAll();
        assertThat(contratoagenteList).hasSize(databaseSizeBeforeCreate + 1);
        Contratoagente testContratoagente = contratoagenteList.get(contratoagenteList.size() - 1);
        assertThat(testContratoagente.getDataaprovacao()).isEqualTo(DEFAULT_DATAAPROVACAO);
        assertThat(testContratoagente.getDataassinatura()).isEqualTo(DEFAULT_DATAASSINATURA);
        assertThat(testContratoagente.getDataconclusao()).isEqualTo(DEFAULT_DATACONCLUSAO);
        assertThat(testContratoagente.getDatainicio()).isEqualTo(DEFAULT_DATAINICIO);
        assertThat(testContratoagente.getIdcontratoagente()).isEqualTo(DEFAULT_IDCONTRATOAGENTE);
        assertThat(testContratoagente.getNomecontratoagente()).isEqualTo(DEFAULT_NOMECONTRATOAGENTE);
        assertThat(testContratoagente.getReferenciabid()).isEqualTo(DEFAULT_REFERENCIABID);
        assertThat(testContratoagente.getAcordocredito()).isEqualTo(DEFAULT_ACORDOCREDITO);
        assertThat(testContratoagente.getMutuario()).isEqualTo(DEFAULT_MUTUARIO);
        assertThat(testContratoagente.getExecutor()).isEqualTo(DEFAULT_EXECUTOR);
        assertThat(testContratoagente.getClausulascontratuais()).isEqualTo(DEFAULT_CLAUSULASCONTRATUAIS);
        assertThat(testContratoagente.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testContratoagente.getMoeda()).isEqualTo(DEFAULT_MOEDA);
        assertThat(testContratoagente.getPercentuallocal()).isEqualTo(DEFAULT_PERCENTUALLOCAL);
        assertThat(testContratoagente.getPercentualagente()).isEqualTo(DEFAULT_PERCENTUALAGENTE);
        assertThat(testContratoagente.getValorcontrato()).isEqualTo(DEFAULT_VALORCONTRATO);
        assertThat(testContratoagente.getValor()).isEqualTo(DEFAULT_VALOR);
    }

    @Test
    @Transactional
    public void createContratoagenteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = contratoagenteRepository.findAll().size();

        // Create the Contratoagente with an existing ID
        contratoagente.setId(1L);
        ContratoagenteDTO contratoagenteDTO = contratoagenteMapper.toDto(contratoagente);

        // An entity with an existing ID cannot be created, so this API call must fail
        restContratoagenteMockMvc.perform(post("/api/contratoagentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratoagenteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Contratoagente> contratoagenteList = contratoagenteRepository.findAll();
        assertThat(contratoagenteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllContratoagentes() throws Exception {
        // Initialize the database
        contratoagenteRepository.saveAndFlush(contratoagente);

        // Get all the contratoagenteList
        restContratoagenteMockMvc.perform(get("/api/contratoagentes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contratoagente.getId().intValue())))
            .andExpect(jsonPath("$.[*].dataaprovacao").value(hasItem(sameInstant(DEFAULT_DATAAPROVACAO))))
            .andExpect(jsonPath("$.[*].dataassinatura").value(hasItem(sameInstant(DEFAULT_DATAASSINATURA))))
            .andExpect(jsonPath("$.[*].dataconclusao").value(hasItem(sameInstant(DEFAULT_DATACONCLUSAO))))
            .andExpect(jsonPath("$.[*].datainicio").value(hasItem(sameInstant(DEFAULT_DATAINICIO))))
            .andExpect(jsonPath("$.[*].idcontratoagente").value(hasItem(DEFAULT_IDCONTRATOAGENTE)))
            .andExpect(jsonPath("$.[*].nomecontratoagente").value(hasItem(DEFAULT_NOMECONTRATOAGENTE.toString())))
            .andExpect(jsonPath("$.[*].referenciabid").value(hasItem(DEFAULT_REFERENCIABID.toString())))
            .andExpect(jsonPath("$.[*].acordocredito").value(hasItem(DEFAULT_ACORDOCREDITO.toString())))
            .andExpect(jsonPath("$.[*].mutuario").value(hasItem(DEFAULT_MUTUARIO.toString())))
            .andExpect(jsonPath("$.[*].executor").value(hasItem(DEFAULT_EXECUTOR.toString())))
            .andExpect(jsonPath("$.[*].clausulascontratuais").value(hasItem(DEFAULT_CLAUSULASCONTRATUAIS.toString())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].moeda").value(hasItem(DEFAULT_MOEDA.toString())))
            .andExpect(jsonPath("$.[*].percentuallocal").value(hasItem(DEFAULT_PERCENTUALLOCAL.doubleValue())))
            .andExpect(jsonPath("$.[*].percentualagente").value(hasItem(DEFAULT_PERCENTUALAGENTE.doubleValue())))
            .andExpect(jsonPath("$.[*].valorcontrato").value(hasItem(DEFAULT_VALORCONTRATO.doubleValue())))
            .andExpect(jsonPath("$.[*].valor").value(hasItem(DEFAULT_VALOR.doubleValue())));
    }

    @Test
    @Transactional
    public void getContratoagente() throws Exception {
        // Initialize the database
        contratoagenteRepository.saveAndFlush(contratoagente);

        // Get the contratoagente
        restContratoagenteMockMvc.perform(get("/api/contratoagentes/{id}", contratoagente.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(contratoagente.getId().intValue()))
            .andExpect(jsonPath("$.dataaprovacao").value(sameInstant(DEFAULT_DATAAPROVACAO)))
            .andExpect(jsonPath("$.dataassinatura").value(sameInstant(DEFAULT_DATAASSINATURA)))
            .andExpect(jsonPath("$.dataconclusao").value(sameInstant(DEFAULT_DATACONCLUSAO)))
            .andExpect(jsonPath("$.datainicio").value(sameInstant(DEFAULT_DATAINICIO)))
            .andExpect(jsonPath("$.idcontratoagente").value(DEFAULT_IDCONTRATOAGENTE))
            .andExpect(jsonPath("$.nomecontratoagente").value(DEFAULT_NOMECONTRATOAGENTE.toString()))
            .andExpect(jsonPath("$.referenciabid").value(DEFAULT_REFERENCIABID.toString()))
            .andExpect(jsonPath("$.acordocredito").value(DEFAULT_ACORDOCREDITO.toString()))
            .andExpect(jsonPath("$.mutuario").value(DEFAULT_MUTUARIO.toString()))
            .andExpect(jsonPath("$.executor").value(DEFAULT_EXECUTOR.toString()))
            .andExpect(jsonPath("$.clausulascontratuais").value(DEFAULT_CLAUSULASCONTRATUAIS.toString()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.moeda").value(DEFAULT_MOEDA.toString()))
            .andExpect(jsonPath("$.percentuallocal").value(DEFAULT_PERCENTUALLOCAL.doubleValue()))
            .andExpect(jsonPath("$.percentualagente").value(DEFAULT_PERCENTUALAGENTE.doubleValue()))
            .andExpect(jsonPath("$.valorcontrato").value(DEFAULT_VALORCONTRATO.doubleValue()))
            .andExpect(jsonPath("$.valor").value(DEFAULT_VALOR.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingContratoagente() throws Exception {
        // Get the contratoagente
        restContratoagenteMockMvc.perform(get("/api/contratoagentes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateContratoagente() throws Exception {
        // Initialize the database
        contratoagenteRepository.saveAndFlush(contratoagente);
        int databaseSizeBeforeUpdate = contratoagenteRepository.findAll().size();

        // Update the contratoagente
        Contratoagente updatedContratoagente = contratoagenteRepository.findOne(contratoagente.getId());
        updatedContratoagente
            .dataaprovacao(UPDATED_DATAAPROVACAO)
            .dataassinatura(UPDATED_DATAASSINATURA)
            .dataconclusao(UPDATED_DATACONCLUSAO)
            .datainicio(UPDATED_DATAINICIO)
            .idcontratoagente(UPDATED_IDCONTRATOAGENTE)
            .nomecontratoagente(UPDATED_NOMECONTRATOAGENTE)
            .referenciabid(UPDATED_REFERENCIABID)
            .acordocredito(UPDATED_ACORDOCREDITO)
            .mutuario(UPDATED_MUTUARIO)
            .executor(UPDATED_EXECUTOR)
            .clausulascontratuais(UPDATED_CLAUSULASCONTRATUAIS)
            .descricao(UPDATED_DESCRICAO)
            .moeda(UPDATED_MOEDA)
            .percentuallocal(UPDATED_PERCENTUALLOCAL)
            .percentualagente(UPDATED_PERCENTUALAGENTE)
            .valorcontrato(UPDATED_VALORCONTRATO)
            .valor(UPDATED_VALOR);
        ContratoagenteDTO contratoagenteDTO = contratoagenteMapper.toDto(updatedContratoagente);

        restContratoagenteMockMvc.perform(put("/api/contratoagentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratoagenteDTO)))
            .andExpect(status().isOk());

        // Validate the Contratoagente in the database
        List<Contratoagente> contratoagenteList = contratoagenteRepository.findAll();
        assertThat(contratoagenteList).hasSize(databaseSizeBeforeUpdate);
        Contratoagente testContratoagente = contratoagenteList.get(contratoagenteList.size() - 1);
        assertThat(testContratoagente.getDataaprovacao()).isEqualTo(UPDATED_DATAAPROVACAO);
        assertThat(testContratoagente.getDataassinatura()).isEqualTo(UPDATED_DATAASSINATURA);
        assertThat(testContratoagente.getDataconclusao()).isEqualTo(UPDATED_DATACONCLUSAO);
        assertThat(testContratoagente.getDatainicio()).isEqualTo(UPDATED_DATAINICIO);
        assertThat(testContratoagente.getIdcontratoagente()).isEqualTo(UPDATED_IDCONTRATOAGENTE);
        assertThat(testContratoagente.getNomecontratoagente()).isEqualTo(UPDATED_NOMECONTRATOAGENTE);
        assertThat(testContratoagente.getReferenciabid()).isEqualTo(UPDATED_REFERENCIABID);
        assertThat(testContratoagente.getAcordocredito()).isEqualTo(UPDATED_ACORDOCREDITO);
        assertThat(testContratoagente.getMutuario()).isEqualTo(UPDATED_MUTUARIO);
        assertThat(testContratoagente.getExecutor()).isEqualTo(UPDATED_EXECUTOR);
        assertThat(testContratoagente.getClausulascontratuais()).isEqualTo(UPDATED_CLAUSULASCONTRATUAIS);
        assertThat(testContratoagente.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testContratoagente.getMoeda()).isEqualTo(UPDATED_MOEDA);
        assertThat(testContratoagente.getPercentuallocal()).isEqualTo(UPDATED_PERCENTUALLOCAL);
        assertThat(testContratoagente.getPercentualagente()).isEqualTo(UPDATED_PERCENTUALAGENTE);
        assertThat(testContratoagente.getValorcontrato()).isEqualTo(UPDATED_VALORCONTRATO);
        assertThat(testContratoagente.getValor()).isEqualTo(UPDATED_VALOR);
    }

    @Test
    @Transactional
    public void updateNonExistingContratoagente() throws Exception {
        int databaseSizeBeforeUpdate = contratoagenteRepository.findAll().size();

        // Create the Contratoagente
        ContratoagenteDTO contratoagenteDTO = contratoagenteMapper.toDto(contratoagente);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restContratoagenteMockMvc.perform(put("/api/contratoagentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratoagenteDTO)))
            .andExpect(status().isCreated());

        // Validate the Contratoagente in the database
        List<Contratoagente> contratoagenteList = contratoagenteRepository.findAll();
        assertThat(contratoagenteList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteContratoagente() throws Exception {
        // Initialize the database
        contratoagenteRepository.saveAndFlush(contratoagente);
        int databaseSizeBeforeDelete = contratoagenteRepository.findAll().size();

        // Get the contratoagente
        restContratoagenteMockMvc.perform(delete("/api/contratoagentes/{id}", contratoagente.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Contratoagente> contratoagenteList = contratoagenteRepository.findAll();
        assertThat(contratoagenteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Contratoagente.class);
        Contratoagente contratoagente1 = new Contratoagente();
        contratoagente1.setId(1L);
        Contratoagente contratoagente2 = new Contratoagente();
        contratoagente2.setId(contratoagente1.getId());
        assertThat(contratoagente1).isEqualTo(contratoagente2);
        contratoagente2.setId(2L);
        assertThat(contratoagente1).isNotEqualTo(contratoagente2);
        contratoagente1.setId(null);
        assertThat(contratoagente1).isNotEqualTo(contratoagente2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContratoagenteDTO.class);
        ContratoagenteDTO contratoagenteDTO1 = new ContratoagenteDTO();
        contratoagenteDTO1.setId(1L);
        ContratoagenteDTO contratoagenteDTO2 = new ContratoagenteDTO();
        assertThat(contratoagenteDTO1).isNotEqualTo(contratoagenteDTO2);
        contratoagenteDTO2.setId(contratoagenteDTO1.getId());
        assertThat(contratoagenteDTO1).isEqualTo(contratoagenteDTO2);
        contratoagenteDTO2.setId(2L);
        assertThat(contratoagenteDTO1).isNotEqualTo(contratoagenteDTO2);
        contratoagenteDTO1.setId(null);
        assertThat(contratoagenteDTO1).isNotEqualTo(contratoagenteDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(contratoagenteMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(contratoagenteMapper.fromId(null)).isNull();
    }
}
