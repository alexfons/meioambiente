package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Contrato;
import br.com.homemade.repository.ContratoRepository;
import br.com.homemade.service.dto.ContratoDTO;
import br.com.homemade.service.mapper.ContratoMapper;
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
 * Test class for the ContratoResource REST controller.
 *
 * @see ContratoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class ContratoResourceIntTest {

    private static final String DEFAULT_CODIGOBID = "AAAAAAAAAA";
    private static final String UPDATED_CODIGOBID = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATAATUAL = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAATUAL = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATABASECONTRATO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATABASECONTRATO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATACONTRATACAO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATACONTRATACAO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATATERMINOCAUCAO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATATERMINOCAUCAO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final BigDecimal DEFAULT_EXTATUALCONTRATO = new BigDecimal(1);
    private static final BigDecimal UPDATED_EXTATUALCONTRATO = new BigDecimal(2);

    private static final BigDecimal DEFAULT_EXTINICIALCONTRATO = new BigDecimal(1);
    private static final BigDecimal UPDATED_EXTINICIALCONTRATO = new BigDecimal(2);

    private static final String DEFAULT_NCONTRATO = "AAAAAAAAAA";
    private static final String UPDATED_NCONTRATO = "BBBBBBBBBB";

    private static final String DEFAULT_ORDEMSERVICO = "AAAAAAAAAA";
    private static final String UPDATED_ORDEMSERVICO = "BBBBBBBBBB";

    private static final Integer DEFAULT_PRAZOATUAL = 1;
    private static final Integer UPDATED_PRAZOATUAL = 2;

    private static final Integer DEFAULT_PRAZOINICIAL = 1;
    private static final Integer UPDATED_PRAZOINICIAL = 2;

    private static final String DEFAULT_RODOVIACONTRATO = "AAAAAAAAAA";
    private static final String UPDATED_RODOVIACONTRATO = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_SALDOCONTRATUAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_SALDOCONTRATUAL = new BigDecimal(2);

    private static final String DEFAULT_SITUACAO = "AAAAAAAAAA";
    private static final String UPDATED_SITUACAO = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_TAXAATUAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_TAXAATUAL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TAXAORIGINAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_TAXAORIGINAL = new BigDecimal(2);

    private static final String DEFAULT_TIPOCONTRATO = "AAAAAAAAAA";
    private static final String UPDATED_TIPOCONTRATO = "BBBBBBBBBB";

    private static final String DEFAULT_TITULOCONTRATO = "AAAAAAAAAA";
    private static final String UPDATED_TITULOCONTRATO = "BBBBBBBBBB";

    private static final String DEFAULT_TRECHOCONTRATO = "AAAAAAAAAA";
    private static final String UPDATED_TRECHOCONTRATO = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_VALORATUAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALORATUAL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VALORCAUCAO = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALORCAUCAO = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VALORPI = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALORPI = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VALORREAJUSTE = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALORREAJUSTE = new BigDecimal(2);

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private ContratoMapper contratoMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restContratoMockMvc;

    private Contrato contrato;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ContratoResource contratoResource = new ContratoResource(contratoRepository, contratoMapper);
        this.restContratoMockMvc = MockMvcBuilders.standaloneSetup(contratoResource)
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
    public static Contrato createEntity(EntityManager em) {
        Contrato contrato = new Contrato()
            .codigobid(DEFAULT_CODIGOBID)
            .dataatual(DEFAULT_DATAATUAL)
            .databasecontrato(DEFAULT_DATABASECONTRATO)
            .datacontratacao(DEFAULT_DATACONTRATACAO)
            .dataterminocaucao(DEFAULT_DATATERMINOCAUCAO)
            .extatualcontrato(DEFAULT_EXTATUALCONTRATO)
            .extinicialcontrato(DEFAULT_EXTINICIALCONTRATO)
            .ncontrato(DEFAULT_NCONTRATO)
            .ordemservico(DEFAULT_ORDEMSERVICO)
            .prazoatual(DEFAULT_PRAZOATUAL)
            .prazoinicial(DEFAULT_PRAZOINICIAL)
            .rodoviacontrato(DEFAULT_RODOVIACONTRATO)
            .saldocontratual(DEFAULT_SALDOCONTRATUAL)
            .situacao(DEFAULT_SITUACAO)
            .taxaatual(DEFAULT_TAXAATUAL)
            .taxaoriginal(DEFAULT_TAXAORIGINAL)
            .tipocontrato(DEFAULT_TIPOCONTRATO)
            .titulocontrato(DEFAULT_TITULOCONTRATO)
            .trechocontrato(DEFAULT_TRECHOCONTRATO)
            .valoratual(DEFAULT_VALORATUAL)
            .valorcaucao(DEFAULT_VALORCAUCAO)
            .valorpi(DEFAULT_VALORPI)
            .valorreajuste(DEFAULT_VALORREAJUSTE);
        return contrato;
    }

    @Before
    public void initTest() {
        contrato = createEntity(em);
    }

    @Test
    @Transactional
    public void createContrato() throws Exception {
        int databaseSizeBeforeCreate = contratoRepository.findAll().size();

        // Create the Contrato
        ContratoDTO contratoDTO = contratoMapper.toDto(contrato);
        restContratoMockMvc.perform(post("/api/contratoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratoDTO)))
            .andExpect(status().isCreated());

        // Validate the Contrato in the database
        List<Contrato> contratoList = contratoRepository.findAll();
        assertThat(contratoList).hasSize(databaseSizeBeforeCreate + 1);
        Contrato testContrato = contratoList.get(contratoList.size() - 1);
        assertThat(testContrato.getCodigobid()).isEqualTo(DEFAULT_CODIGOBID);
        assertThat(testContrato.getDataatual()).isEqualTo(DEFAULT_DATAATUAL);
        assertThat(testContrato.getDatabasecontrato()).isEqualTo(DEFAULT_DATABASECONTRATO);
        assertThat(testContrato.getDatacontratacao()).isEqualTo(DEFAULT_DATACONTRATACAO);
        assertThat(testContrato.getDataterminocaucao()).isEqualTo(DEFAULT_DATATERMINOCAUCAO);
        assertThat(testContrato.getExtatualcontrato()).isEqualTo(DEFAULT_EXTATUALCONTRATO);
        assertThat(testContrato.getExtinicialcontrato()).isEqualTo(DEFAULT_EXTINICIALCONTRATO);
        assertThat(testContrato.getNcontrato()).isEqualTo(DEFAULT_NCONTRATO);
        assertThat(testContrato.getOrdemservico()).isEqualTo(DEFAULT_ORDEMSERVICO);
        assertThat(testContrato.getPrazoatual()).isEqualTo(DEFAULT_PRAZOATUAL);
        assertThat(testContrato.getPrazoinicial()).isEqualTo(DEFAULT_PRAZOINICIAL);
        assertThat(testContrato.getRodoviacontrato()).isEqualTo(DEFAULT_RODOVIACONTRATO);
        assertThat(testContrato.getSaldocontratual()).isEqualTo(DEFAULT_SALDOCONTRATUAL);
        assertThat(testContrato.getSituacao()).isEqualTo(DEFAULT_SITUACAO);
        assertThat(testContrato.getTaxaatual()).isEqualTo(DEFAULT_TAXAATUAL);
        assertThat(testContrato.getTaxaoriginal()).isEqualTo(DEFAULT_TAXAORIGINAL);
        assertThat(testContrato.getTipocontrato()).isEqualTo(DEFAULT_TIPOCONTRATO);
        assertThat(testContrato.getTitulocontrato()).isEqualTo(DEFAULT_TITULOCONTRATO);
        assertThat(testContrato.getTrechocontrato()).isEqualTo(DEFAULT_TRECHOCONTRATO);
        assertThat(testContrato.getValoratual()).isEqualTo(DEFAULT_VALORATUAL);
        assertThat(testContrato.getValorcaucao()).isEqualTo(DEFAULT_VALORCAUCAO);
        assertThat(testContrato.getValorpi()).isEqualTo(DEFAULT_VALORPI);
        assertThat(testContrato.getValorreajuste()).isEqualTo(DEFAULT_VALORREAJUSTE);
    }

    @Test
    @Transactional
    public void createContratoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = contratoRepository.findAll().size();

        // Create the Contrato with an existing ID
        contrato.setId(1L);
        ContratoDTO contratoDTO = contratoMapper.toDto(contrato);

        // An entity with an existing ID cannot be created, so this API call must fail
        restContratoMockMvc.perform(post("/api/contratoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Contrato> contratoList = contratoRepository.findAll();
        assertThat(contratoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllContratoes() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList
        restContratoMockMvc.perform(get("/api/contratoes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contrato.getId().intValue())))
            .andExpect(jsonPath("$.[*].codigobid").value(hasItem(DEFAULT_CODIGOBID.toString())))
            .andExpect(jsonPath("$.[*].dataatual").value(hasItem(sameInstant(DEFAULT_DATAATUAL))))
            .andExpect(jsonPath("$.[*].databasecontrato").value(hasItem(sameInstant(DEFAULT_DATABASECONTRATO))))
            .andExpect(jsonPath("$.[*].datacontratacao").value(hasItem(sameInstant(DEFAULT_DATACONTRATACAO))))
            .andExpect(jsonPath("$.[*].dataterminocaucao").value(hasItem(sameInstant(DEFAULT_DATATERMINOCAUCAO))))
            .andExpect(jsonPath("$.[*].extatualcontrato").value(hasItem(DEFAULT_EXTATUALCONTRATO.intValue())))
            .andExpect(jsonPath("$.[*].extinicialcontrato").value(hasItem(DEFAULT_EXTINICIALCONTRATO.intValue())))
            .andExpect(jsonPath("$.[*].ncontrato").value(hasItem(DEFAULT_NCONTRATO.toString())))
            .andExpect(jsonPath("$.[*].ordemservico").value(hasItem(DEFAULT_ORDEMSERVICO.toString())))
            .andExpect(jsonPath("$.[*].prazoatual").value(hasItem(DEFAULT_PRAZOATUAL)))
            .andExpect(jsonPath("$.[*].prazoinicial").value(hasItem(DEFAULT_PRAZOINICIAL)))
            .andExpect(jsonPath("$.[*].rodoviacontrato").value(hasItem(DEFAULT_RODOVIACONTRATO.toString())))
            .andExpect(jsonPath("$.[*].saldocontratual").value(hasItem(DEFAULT_SALDOCONTRATUAL.intValue())))
            .andExpect(jsonPath("$.[*].situacao").value(hasItem(DEFAULT_SITUACAO.toString())))
            .andExpect(jsonPath("$.[*].taxaatual").value(hasItem(DEFAULT_TAXAATUAL.intValue())))
            .andExpect(jsonPath("$.[*].taxaoriginal").value(hasItem(DEFAULT_TAXAORIGINAL.intValue())))
            .andExpect(jsonPath("$.[*].tipocontrato").value(hasItem(DEFAULT_TIPOCONTRATO.toString())))
            .andExpect(jsonPath("$.[*].titulocontrato").value(hasItem(DEFAULT_TITULOCONTRATO.toString())))
            .andExpect(jsonPath("$.[*].trechocontrato").value(hasItem(DEFAULT_TRECHOCONTRATO.toString())))
            .andExpect(jsonPath("$.[*].valoratual").value(hasItem(DEFAULT_VALORATUAL.intValue())))
            .andExpect(jsonPath("$.[*].valorcaucao").value(hasItem(DEFAULT_VALORCAUCAO.intValue())))
            .andExpect(jsonPath("$.[*].valorpi").value(hasItem(DEFAULT_VALORPI.intValue())))
            .andExpect(jsonPath("$.[*].valorreajuste").value(hasItem(DEFAULT_VALORREAJUSTE.intValue())));
    }

    @Test
    @Transactional
    public void getContrato() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get the contrato
        restContratoMockMvc.perform(get("/api/contratoes/{id}", contrato.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(contrato.getId().intValue()))
            .andExpect(jsonPath("$.codigobid").value(DEFAULT_CODIGOBID.toString()))
            .andExpect(jsonPath("$.dataatual").value(sameInstant(DEFAULT_DATAATUAL)))
            .andExpect(jsonPath("$.databasecontrato").value(sameInstant(DEFAULT_DATABASECONTRATO)))
            .andExpect(jsonPath("$.datacontratacao").value(sameInstant(DEFAULT_DATACONTRATACAO)))
            .andExpect(jsonPath("$.dataterminocaucao").value(sameInstant(DEFAULT_DATATERMINOCAUCAO)))
            .andExpect(jsonPath("$.extatualcontrato").value(DEFAULT_EXTATUALCONTRATO.intValue()))
            .andExpect(jsonPath("$.extinicialcontrato").value(DEFAULT_EXTINICIALCONTRATO.intValue()))
            .andExpect(jsonPath("$.ncontrato").value(DEFAULT_NCONTRATO.toString()))
            .andExpect(jsonPath("$.ordemservico").value(DEFAULT_ORDEMSERVICO.toString()))
            .andExpect(jsonPath("$.prazoatual").value(DEFAULT_PRAZOATUAL))
            .andExpect(jsonPath("$.prazoinicial").value(DEFAULT_PRAZOINICIAL))
            .andExpect(jsonPath("$.rodoviacontrato").value(DEFAULT_RODOVIACONTRATO.toString()))
            .andExpect(jsonPath("$.saldocontratual").value(DEFAULT_SALDOCONTRATUAL.intValue()))
            .andExpect(jsonPath("$.situacao").value(DEFAULT_SITUACAO.toString()))
            .andExpect(jsonPath("$.taxaatual").value(DEFAULT_TAXAATUAL.intValue()))
            .andExpect(jsonPath("$.taxaoriginal").value(DEFAULT_TAXAORIGINAL.intValue()))
            .andExpect(jsonPath("$.tipocontrato").value(DEFAULT_TIPOCONTRATO.toString()))
            .andExpect(jsonPath("$.titulocontrato").value(DEFAULT_TITULOCONTRATO.toString()))
            .andExpect(jsonPath("$.trechocontrato").value(DEFAULT_TRECHOCONTRATO.toString()))
            .andExpect(jsonPath("$.valoratual").value(DEFAULT_VALORATUAL.intValue()))
            .andExpect(jsonPath("$.valorcaucao").value(DEFAULT_VALORCAUCAO.intValue()))
            .andExpect(jsonPath("$.valorpi").value(DEFAULT_VALORPI.intValue()))
            .andExpect(jsonPath("$.valorreajuste").value(DEFAULT_VALORREAJUSTE.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingContrato() throws Exception {
        // Get the contrato
        restContratoMockMvc.perform(get("/api/contratoes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateContrato() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);
        int databaseSizeBeforeUpdate = contratoRepository.findAll().size();

        // Update the contrato
        Contrato updatedContrato = contratoRepository.findOne(contrato.getId());
        updatedContrato
            .codigobid(UPDATED_CODIGOBID)
            .dataatual(UPDATED_DATAATUAL)
            .databasecontrato(UPDATED_DATABASECONTRATO)
            .datacontratacao(UPDATED_DATACONTRATACAO)
            .dataterminocaucao(UPDATED_DATATERMINOCAUCAO)
            .extatualcontrato(UPDATED_EXTATUALCONTRATO)
            .extinicialcontrato(UPDATED_EXTINICIALCONTRATO)
            .ncontrato(UPDATED_NCONTRATO)
            .ordemservico(UPDATED_ORDEMSERVICO)
            .prazoatual(UPDATED_PRAZOATUAL)
            .prazoinicial(UPDATED_PRAZOINICIAL)
            .rodoviacontrato(UPDATED_RODOVIACONTRATO)
            .saldocontratual(UPDATED_SALDOCONTRATUAL)
            .situacao(UPDATED_SITUACAO)
            .taxaatual(UPDATED_TAXAATUAL)
            .taxaoriginal(UPDATED_TAXAORIGINAL)
            .tipocontrato(UPDATED_TIPOCONTRATO)
            .titulocontrato(UPDATED_TITULOCONTRATO)
            .trechocontrato(UPDATED_TRECHOCONTRATO)
            .valoratual(UPDATED_VALORATUAL)
            .valorcaucao(UPDATED_VALORCAUCAO)
            .valorpi(UPDATED_VALORPI)
            .valorreajuste(UPDATED_VALORREAJUSTE);
        ContratoDTO contratoDTO = contratoMapper.toDto(updatedContrato);

        restContratoMockMvc.perform(put("/api/contratoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratoDTO)))
            .andExpect(status().isOk());

        // Validate the Contrato in the database
        List<Contrato> contratoList = contratoRepository.findAll();
        assertThat(contratoList).hasSize(databaseSizeBeforeUpdate);
        Contrato testContrato = contratoList.get(contratoList.size() - 1);
        assertThat(testContrato.getCodigobid()).isEqualTo(UPDATED_CODIGOBID);
        assertThat(testContrato.getDataatual()).isEqualTo(UPDATED_DATAATUAL);
        assertThat(testContrato.getDatabasecontrato()).isEqualTo(UPDATED_DATABASECONTRATO);
        assertThat(testContrato.getDatacontratacao()).isEqualTo(UPDATED_DATACONTRATACAO);
        assertThat(testContrato.getDataterminocaucao()).isEqualTo(UPDATED_DATATERMINOCAUCAO);
        assertThat(testContrato.getExtatualcontrato()).isEqualTo(UPDATED_EXTATUALCONTRATO);
        assertThat(testContrato.getExtinicialcontrato()).isEqualTo(UPDATED_EXTINICIALCONTRATO);
        assertThat(testContrato.getNcontrato()).isEqualTo(UPDATED_NCONTRATO);
        assertThat(testContrato.getOrdemservico()).isEqualTo(UPDATED_ORDEMSERVICO);
        assertThat(testContrato.getPrazoatual()).isEqualTo(UPDATED_PRAZOATUAL);
        assertThat(testContrato.getPrazoinicial()).isEqualTo(UPDATED_PRAZOINICIAL);
        assertThat(testContrato.getRodoviacontrato()).isEqualTo(UPDATED_RODOVIACONTRATO);
        assertThat(testContrato.getSaldocontratual()).isEqualTo(UPDATED_SALDOCONTRATUAL);
        assertThat(testContrato.getSituacao()).isEqualTo(UPDATED_SITUACAO);
        assertThat(testContrato.getTaxaatual()).isEqualTo(UPDATED_TAXAATUAL);
        assertThat(testContrato.getTaxaoriginal()).isEqualTo(UPDATED_TAXAORIGINAL);
        assertThat(testContrato.getTipocontrato()).isEqualTo(UPDATED_TIPOCONTRATO);
        assertThat(testContrato.getTitulocontrato()).isEqualTo(UPDATED_TITULOCONTRATO);
        assertThat(testContrato.getTrechocontrato()).isEqualTo(UPDATED_TRECHOCONTRATO);
        assertThat(testContrato.getValoratual()).isEqualTo(UPDATED_VALORATUAL);
        assertThat(testContrato.getValorcaucao()).isEqualTo(UPDATED_VALORCAUCAO);
        assertThat(testContrato.getValorpi()).isEqualTo(UPDATED_VALORPI);
        assertThat(testContrato.getValorreajuste()).isEqualTo(UPDATED_VALORREAJUSTE);
    }

    @Test
    @Transactional
    public void updateNonExistingContrato() throws Exception {
        int databaseSizeBeforeUpdate = contratoRepository.findAll().size();

        // Create the Contrato
        ContratoDTO contratoDTO = contratoMapper.toDto(contrato);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restContratoMockMvc.perform(put("/api/contratoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratoDTO)))
            .andExpect(status().isCreated());

        // Validate the Contrato in the database
        List<Contrato> contratoList = contratoRepository.findAll();
        assertThat(contratoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteContrato() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);
        int databaseSizeBeforeDelete = contratoRepository.findAll().size();

        // Get the contrato
        restContratoMockMvc.perform(delete("/api/contratoes/{id}", contrato.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Contrato> contratoList = contratoRepository.findAll();
        assertThat(contratoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Contrato.class);
        Contrato contrato1 = new Contrato();
        contrato1.setId(1L);
        Contrato contrato2 = new Contrato();
        contrato2.setId(contrato1.getId());
        assertThat(contrato1).isEqualTo(contrato2);
        contrato2.setId(2L);
        assertThat(contrato1).isNotEqualTo(contrato2);
        contrato1.setId(null);
        assertThat(contrato1).isNotEqualTo(contrato2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContratoDTO.class);
        ContratoDTO contratoDTO1 = new ContratoDTO();
        contratoDTO1.setId(1L);
        ContratoDTO contratoDTO2 = new ContratoDTO();
        assertThat(contratoDTO1).isNotEqualTo(contratoDTO2);
        contratoDTO2.setId(contratoDTO1.getId());
        assertThat(contratoDTO1).isEqualTo(contratoDTO2);
        contratoDTO2.setId(2L);
        assertThat(contratoDTO1).isNotEqualTo(contratoDTO2);
        contratoDTO1.setId(null);
        assertThat(contratoDTO1).isNotEqualTo(contratoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(contratoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(contratoMapper.fromId(null)).isNull();
    }
}
