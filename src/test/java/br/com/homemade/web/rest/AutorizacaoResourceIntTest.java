package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Autorizacao;
import br.com.homemade.repository.AutorizacaoRepository;
import br.com.homemade.service.dto.AutorizacaoDTO;
import br.com.homemade.service.mapper.AutorizacaoMapper;
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
 * Test class for the AutorizacaoResource REST controller.
 *
 * @see AutorizacaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class AutorizacaoResourceIntTest {

    private static final String DEFAULT_ALBUM = "AAAAAAAAAA";
    private static final String UPDATED_ALBUM = "BBBBBBBBBB";

    private static final String DEFAULT_ANDAMENTO = "AAAAAAAAAA";
    private static final String UPDATED_ANDAMENTO = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAENTREGADOCS = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAENTREGADOCS = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAEXPEDICAOPRORROGACAO_1 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAEXPEDICAOPRORROGACAO_1 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAEXPEDICAOPRORROGACAO_2 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAEXPEDICAOPRORROGACAO_2 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAEXPEDICAOPRORROGACAO_3 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAEXPEDICAOPRORROGACAO_3 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAPEDIDOPRORROGACAO_1 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAPEDIDOPRORROGACAO_1 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAPEDIDOPRORROGACAO_2 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAPEDIDOPRORROGACAO_2 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAPEDIDOPRORROGACAO_3 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAPEDIDOPRORROGACAO_3 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAVALIDADEPRORROGACAO_1 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAVALIDADEPRORROGACAO_1 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAVALIDADEPRORROGACAO_2 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAVALIDADEPRORROGACAO_2 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAVALIDADEPRORROGACAO_3 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAVALIDADEPRORROGACAO_3 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAVENCIMENTO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAVENCIMENTO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAVENCIMENTOATUAL = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAVENCIMENTOATUAL = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final String DEFAULT_DOCSENTREGUES = "AAAAAAAAAA";
    private static final String UPDATED_DOCSENTREGUES = "BBBBBBBBBB";

    private static final String DEFAULT_FCEI = "AAAAAAAAAA";
    private static final String UPDATED_FCEI = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_FCEIDATAPAGAMENTO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FCEIDATAPAGAMENTO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_FCEIDATAPROTOCOLO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FCEIDATAPROTOCOLO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Double DEFAULT_FCEIVALOR = 1D;
    private static final Double UPDATED_FCEIVALOR = 2D;

    private static final String DEFAULT_FOLDER = "AAAAAAAAAA";
    private static final String UPDATED_FOLDER = "BBBBBBBBBB";

    private static final Boolean DEFAULT_INATIVO = false;
    private static final Boolean UPDATED_INATIVO = true;

    private static final Integer DEFAULT_KMFIM = 1;
    private static final Integer UPDATED_KMFIM = 2;

    private static final Integer DEFAULT_KMINICIO = 1;
    private static final Integer UPDATED_KMINICIO = 2;

    private static final String DEFAULT_LADO = "AAAAAAAAAA";
    private static final String UPDATED_LADO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_NAORIA = false;
    private static final Boolean UPDATED_NAORIA = true;

    private static final String DEFAULT_NUMERO = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO = "BBBBBBBBBB";

    private static final String DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_1 = "AAAAAAAAAA";
    private static final String UPDATED_NUMEROOFICIOCONCESSAOPRORROGACAO_1 = "BBBBBBBBBB";

    private static final String DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_2 = "AAAAAAAAAA";
    private static final String UPDATED_NUMEROOFICIOCONCESSAOPRORROGACAO_2 = "BBBBBBBBBB";

    private static final String DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_3 = "AAAAAAAAAA";
    private static final String UPDATED_NUMEROOFICIOCONCESSAOPRORROGACAO_3 = "BBBBBBBBBB";

    private static final String DEFAULT_NUMEROOFICIOPRORROGACAO_1 = "AAAAAAAAAA";
    private static final String UPDATED_NUMEROOFICIOPRORROGACAO_1 = "BBBBBBBBBB";

    private static final String DEFAULT_NUMEROOFICIOPRORROGACAO_2 = "AAAAAAAAAA";
    private static final String UPDATED_NUMEROOFICIOPRORROGACAO_2 = "BBBBBBBBBB";

    private static final String DEFAULT_NUMEROOFICIOPRORROGACAO_3 = "AAAAAAAAAA";
    private static final String UPDATED_NUMEROOFICIOPRORROGACAO_3 = "BBBBBBBBBB";

    private static final String DEFAULT_NUMEROPROCESSO = "AAAAAAAAAA";
    private static final String UPDATED_NUMEROPROCESSO = "BBBBBBBBBB";

    private static final String DEFAULT_OBSERVACAO = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVACAO = "BBBBBBBBBB";

    private static final String DEFAULT_PENDENTE = "AAAAAAAAAA";
    private static final String UPDATED_PENDENTE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_PRAZOMES = false;
    private static final Boolean UPDATED_PRAZOMES = true;

    private static final Integer DEFAULT_PRAZOVALIDADE = 1;
    private static final Integer UPDATED_PRAZOVALIDADE = 2;

    private static final String DEFAULT_PROPRIETARIO = "AAAAAAAAAA";
    private static final String UPDATED_PROPRIETARIO = "BBBBBBBBBB";

    private static final String DEFAULT_RECIBOENTREGADOCS = "AAAAAAAAAA";
    private static final String UPDATED_RECIBOENTREGADOCS = "BBBBBBBBBB";

    @Autowired
    private AutorizacaoRepository autorizacaoRepository;

    @Autowired
    private AutorizacaoMapper autorizacaoMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAutorizacaoMockMvc;

    private Autorizacao autorizacao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AutorizacaoResource autorizacaoResource = new AutorizacaoResource(autorizacaoRepository, autorizacaoMapper);
        this.restAutorizacaoMockMvc = MockMvcBuilders.standaloneSetup(autorizacaoResource)
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
    public static Autorizacao createEntity(EntityManager em) {
        Autorizacao autorizacao = new Autorizacao()
            .album(DEFAULT_ALBUM)
            .andamento(DEFAULT_ANDAMENTO)
            .data(DEFAULT_DATA)
            .dataentregadocs(DEFAULT_DATAENTREGADOCS)
            .dataexpedicaoprorrogacao1(DEFAULT_DATAEXPEDICAOPRORROGACAO_1)
            .dataexpedicaoprorrogacao2(DEFAULT_DATAEXPEDICAOPRORROGACAO_2)
            .dataexpedicaoprorrogacao3(DEFAULT_DATAEXPEDICAOPRORROGACAO_3)
            .datapedidoprorrogacao1(DEFAULT_DATAPEDIDOPRORROGACAO_1)
            .datapedidoprorrogacao2(DEFAULT_DATAPEDIDOPRORROGACAO_2)
            .datapedidoprorrogacao3(DEFAULT_DATAPEDIDOPRORROGACAO_3)
            .datavalidadeprorrogacao1(DEFAULT_DATAVALIDADEPRORROGACAO_1)
            .datavalidadeprorrogacao2(DEFAULT_DATAVALIDADEPRORROGACAO_2)
            .datavalidadeprorrogacao3(DEFAULT_DATAVALIDADEPRORROGACAO_3)
            .datavencimento(DEFAULT_DATAVENCIMENTO)
            .datavencimentoatual(DEFAULT_DATAVENCIMENTOATUAL)
            .descricao(DEFAULT_DESCRICAO)
            .docsentregues(DEFAULT_DOCSENTREGUES)
            .fcei(DEFAULT_FCEI)
            .fceidatapagamento(DEFAULT_FCEIDATAPAGAMENTO)
            .fceidataprotocolo(DEFAULT_FCEIDATAPROTOCOLO)
            .fceivalor(DEFAULT_FCEIVALOR)
            .folder(DEFAULT_FOLDER)
            .inativo(DEFAULT_INATIVO)
            .kmfim(DEFAULT_KMFIM)
            .kminicio(DEFAULT_KMINICIO)
            .lado(DEFAULT_LADO)
            .naoria(DEFAULT_NAORIA)
            .numero(DEFAULT_NUMERO)
            .numerooficioconcessaoprorrogacao1(DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_1)
            .numerooficioconcessaoprorrogacao2(DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_2)
            .numerooficioconcessaoprorrogacao3(DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_3)
            .numerooficioprorrogacao1(DEFAULT_NUMEROOFICIOPRORROGACAO_1)
            .numerooficioprorrogacao2(DEFAULT_NUMEROOFICIOPRORROGACAO_2)
            .numerooficioprorrogacao3(DEFAULT_NUMEROOFICIOPRORROGACAO_3)
            .numeroprocesso(DEFAULT_NUMEROPROCESSO)
            .observacao(DEFAULT_OBSERVACAO)
            .pendente(DEFAULT_PENDENTE)
            .prazomes(DEFAULT_PRAZOMES)
            .prazovalidade(DEFAULT_PRAZOVALIDADE)
            .proprietario(DEFAULT_PROPRIETARIO)
            .reciboentregadocs(DEFAULT_RECIBOENTREGADOCS);
        return autorizacao;
    }

    @Before
    public void initTest() {
        autorizacao = createEntity(em);
    }

    @Test
    @Transactional
    public void createAutorizacao() throws Exception {
        int databaseSizeBeforeCreate = autorizacaoRepository.findAll().size();

        // Create the Autorizacao
        AutorizacaoDTO autorizacaoDTO = autorizacaoMapper.toDto(autorizacao);
        restAutorizacaoMockMvc.perform(post("/api/autorizacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(autorizacaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Autorizacao in the database
        List<Autorizacao> autorizacaoList = autorizacaoRepository.findAll();
        assertThat(autorizacaoList).hasSize(databaseSizeBeforeCreate + 1);
        Autorizacao testAutorizacao = autorizacaoList.get(autorizacaoList.size() - 1);
        assertThat(testAutorizacao.getAlbum()).isEqualTo(DEFAULT_ALBUM);
        assertThat(testAutorizacao.getAndamento()).isEqualTo(DEFAULT_ANDAMENTO);
        assertThat(testAutorizacao.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testAutorizacao.getDataentregadocs()).isEqualTo(DEFAULT_DATAENTREGADOCS);
        assertThat(testAutorizacao.getDataexpedicaoprorrogacao1()).isEqualTo(DEFAULT_DATAEXPEDICAOPRORROGACAO_1);
        assertThat(testAutorizacao.getDataexpedicaoprorrogacao2()).isEqualTo(DEFAULT_DATAEXPEDICAOPRORROGACAO_2);
        assertThat(testAutorizacao.getDataexpedicaoprorrogacao3()).isEqualTo(DEFAULT_DATAEXPEDICAOPRORROGACAO_3);
        assertThat(testAutorizacao.getDatapedidoprorrogacao1()).isEqualTo(DEFAULT_DATAPEDIDOPRORROGACAO_1);
        assertThat(testAutorizacao.getDatapedidoprorrogacao2()).isEqualTo(DEFAULT_DATAPEDIDOPRORROGACAO_2);
        assertThat(testAutorizacao.getDatapedidoprorrogacao3()).isEqualTo(DEFAULT_DATAPEDIDOPRORROGACAO_3);
        assertThat(testAutorizacao.getDatavalidadeprorrogacao1()).isEqualTo(DEFAULT_DATAVALIDADEPRORROGACAO_1);
        assertThat(testAutorizacao.getDatavalidadeprorrogacao2()).isEqualTo(DEFAULT_DATAVALIDADEPRORROGACAO_2);
        assertThat(testAutorizacao.getDatavalidadeprorrogacao3()).isEqualTo(DEFAULT_DATAVALIDADEPRORROGACAO_3);
        assertThat(testAutorizacao.getDatavencimento()).isEqualTo(DEFAULT_DATAVENCIMENTO);
        assertThat(testAutorizacao.getDatavencimentoatual()).isEqualTo(DEFAULT_DATAVENCIMENTOATUAL);
        assertThat(testAutorizacao.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testAutorizacao.getDocsentregues()).isEqualTo(DEFAULT_DOCSENTREGUES);
        assertThat(testAutorizacao.getFcei()).isEqualTo(DEFAULT_FCEI);
        assertThat(testAutorizacao.getFceidatapagamento()).isEqualTo(DEFAULT_FCEIDATAPAGAMENTO);
        assertThat(testAutorizacao.getFceidataprotocolo()).isEqualTo(DEFAULT_FCEIDATAPROTOCOLO);
        assertThat(testAutorizacao.getFceivalor()).isEqualTo(DEFAULT_FCEIVALOR);
        assertThat(testAutorizacao.getFolder()).isEqualTo(DEFAULT_FOLDER);
        assertThat(testAutorizacao.isInativo()).isEqualTo(DEFAULT_INATIVO);
        assertThat(testAutorizacao.getKmfim()).isEqualTo(DEFAULT_KMFIM);
        assertThat(testAutorizacao.getKminicio()).isEqualTo(DEFAULT_KMINICIO);
        assertThat(testAutorizacao.getLado()).isEqualTo(DEFAULT_LADO);
        assertThat(testAutorizacao.isNaoria()).isEqualTo(DEFAULT_NAORIA);
        assertThat(testAutorizacao.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testAutorizacao.getNumerooficioconcessaoprorrogacao1()).isEqualTo(DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_1);
        assertThat(testAutorizacao.getNumerooficioconcessaoprorrogacao2()).isEqualTo(DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_2);
        assertThat(testAutorizacao.getNumerooficioconcessaoprorrogacao3()).isEqualTo(DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_3);
        assertThat(testAutorizacao.getNumerooficioprorrogacao1()).isEqualTo(DEFAULT_NUMEROOFICIOPRORROGACAO_1);
        assertThat(testAutorizacao.getNumerooficioprorrogacao2()).isEqualTo(DEFAULT_NUMEROOFICIOPRORROGACAO_2);
        assertThat(testAutorizacao.getNumerooficioprorrogacao3()).isEqualTo(DEFAULT_NUMEROOFICIOPRORROGACAO_3);
        assertThat(testAutorizacao.getNumeroprocesso()).isEqualTo(DEFAULT_NUMEROPROCESSO);
        assertThat(testAutorizacao.getObservacao()).isEqualTo(DEFAULT_OBSERVACAO);
        assertThat(testAutorizacao.getPendente()).isEqualTo(DEFAULT_PENDENTE);
        assertThat(testAutorizacao.isPrazomes()).isEqualTo(DEFAULT_PRAZOMES);
        assertThat(testAutorizacao.getPrazovalidade()).isEqualTo(DEFAULT_PRAZOVALIDADE);
        assertThat(testAutorizacao.getProprietario()).isEqualTo(DEFAULT_PROPRIETARIO);
        assertThat(testAutorizacao.getReciboentregadocs()).isEqualTo(DEFAULT_RECIBOENTREGADOCS);
    }

    @Test
    @Transactional
    public void createAutorizacaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = autorizacaoRepository.findAll().size();

        // Create the Autorizacao with an existing ID
        autorizacao.setId(1L);
        AutorizacaoDTO autorizacaoDTO = autorizacaoMapper.toDto(autorizacao);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAutorizacaoMockMvc.perform(post("/api/autorizacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(autorizacaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Autorizacao> autorizacaoList = autorizacaoRepository.findAll();
        assertThat(autorizacaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAutorizacaos() throws Exception {
        // Initialize the database
        autorizacaoRepository.saveAndFlush(autorizacao);

        // Get all the autorizacaoList
        restAutorizacaoMockMvc.perform(get("/api/autorizacaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(autorizacao.getId().intValue())))
            .andExpect(jsonPath("$.[*].album").value(hasItem(DEFAULT_ALBUM.toString())))
            .andExpect(jsonPath("$.[*].andamento").value(hasItem(DEFAULT_ANDAMENTO.toString())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(sameInstant(DEFAULT_DATA))))
            .andExpect(jsonPath("$.[*].dataentregadocs").value(hasItem(sameInstant(DEFAULT_DATAENTREGADOCS))))
            .andExpect(jsonPath("$.[*].dataexpedicaoprorrogacao1").value(hasItem(sameInstant(DEFAULT_DATAEXPEDICAOPRORROGACAO_1))))
            .andExpect(jsonPath("$.[*].dataexpedicaoprorrogacao2").value(hasItem(sameInstant(DEFAULT_DATAEXPEDICAOPRORROGACAO_2))))
            .andExpect(jsonPath("$.[*].dataexpedicaoprorrogacao3").value(hasItem(sameInstant(DEFAULT_DATAEXPEDICAOPRORROGACAO_3))))
            .andExpect(jsonPath("$.[*].datapedidoprorrogacao1").value(hasItem(sameInstant(DEFAULT_DATAPEDIDOPRORROGACAO_1))))
            .andExpect(jsonPath("$.[*].datapedidoprorrogacao2").value(hasItem(sameInstant(DEFAULT_DATAPEDIDOPRORROGACAO_2))))
            .andExpect(jsonPath("$.[*].datapedidoprorrogacao3").value(hasItem(sameInstant(DEFAULT_DATAPEDIDOPRORROGACAO_3))))
            .andExpect(jsonPath("$.[*].datavalidadeprorrogacao1").value(hasItem(sameInstant(DEFAULT_DATAVALIDADEPRORROGACAO_1))))
            .andExpect(jsonPath("$.[*].datavalidadeprorrogacao2").value(hasItem(sameInstant(DEFAULT_DATAVALIDADEPRORROGACAO_2))))
            .andExpect(jsonPath("$.[*].datavalidadeprorrogacao3").value(hasItem(sameInstant(DEFAULT_DATAVALIDADEPRORROGACAO_3))))
            .andExpect(jsonPath("$.[*].datavencimento").value(hasItem(sameInstant(DEFAULT_DATAVENCIMENTO))))
            .andExpect(jsonPath("$.[*].datavencimentoatual").value(hasItem(sameInstant(DEFAULT_DATAVENCIMENTOATUAL))))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].docsentregues").value(hasItem(DEFAULT_DOCSENTREGUES.toString())))
            .andExpect(jsonPath("$.[*].fcei").value(hasItem(DEFAULT_FCEI.toString())))
            .andExpect(jsonPath("$.[*].fceidatapagamento").value(hasItem(sameInstant(DEFAULT_FCEIDATAPAGAMENTO))))
            .andExpect(jsonPath("$.[*].fceidataprotocolo").value(hasItem(sameInstant(DEFAULT_FCEIDATAPROTOCOLO))))
            .andExpect(jsonPath("$.[*].fceivalor").value(hasItem(DEFAULT_FCEIVALOR.doubleValue())))
            .andExpect(jsonPath("$.[*].folder").value(hasItem(DEFAULT_FOLDER.toString())))
            .andExpect(jsonPath("$.[*].inativo").value(hasItem(DEFAULT_INATIVO.booleanValue())))
            .andExpect(jsonPath("$.[*].kmfim").value(hasItem(DEFAULT_KMFIM)))
            .andExpect(jsonPath("$.[*].kminicio").value(hasItem(DEFAULT_KMINICIO)))
            .andExpect(jsonPath("$.[*].lado").value(hasItem(DEFAULT_LADO.toString())))
            .andExpect(jsonPath("$.[*].naoria").value(hasItem(DEFAULT_NAORIA.booleanValue())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO.toString())))
            .andExpect(jsonPath("$.[*].numerooficioconcessaoprorrogacao1").value(hasItem(DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_1.toString())))
            .andExpect(jsonPath("$.[*].numerooficioconcessaoprorrogacao2").value(hasItem(DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_2.toString())))
            .andExpect(jsonPath("$.[*].numerooficioconcessaoprorrogacao3").value(hasItem(DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_3.toString())))
            .andExpect(jsonPath("$.[*].numerooficioprorrogacao1").value(hasItem(DEFAULT_NUMEROOFICIOPRORROGACAO_1.toString())))
            .andExpect(jsonPath("$.[*].numerooficioprorrogacao2").value(hasItem(DEFAULT_NUMEROOFICIOPRORROGACAO_2.toString())))
            .andExpect(jsonPath("$.[*].numerooficioprorrogacao3").value(hasItem(DEFAULT_NUMEROOFICIOPRORROGACAO_3.toString())))
            .andExpect(jsonPath("$.[*].numeroprocesso").value(hasItem(DEFAULT_NUMEROPROCESSO.toString())))
            .andExpect(jsonPath("$.[*].observacao").value(hasItem(DEFAULT_OBSERVACAO.toString())))
            .andExpect(jsonPath("$.[*].pendente").value(hasItem(DEFAULT_PENDENTE.toString())))
            .andExpect(jsonPath("$.[*].prazomes").value(hasItem(DEFAULT_PRAZOMES.booleanValue())))
            .andExpect(jsonPath("$.[*].prazovalidade").value(hasItem(DEFAULT_PRAZOVALIDADE)))
            .andExpect(jsonPath("$.[*].proprietario").value(hasItem(DEFAULT_PROPRIETARIO.toString())))
            .andExpect(jsonPath("$.[*].reciboentregadocs").value(hasItem(DEFAULT_RECIBOENTREGADOCS.toString())));
    }

    @Test
    @Transactional
    public void getAutorizacao() throws Exception {
        // Initialize the database
        autorizacaoRepository.saveAndFlush(autorizacao);

        // Get the autorizacao
        restAutorizacaoMockMvc.perform(get("/api/autorizacaos/{id}", autorizacao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(autorizacao.getId().intValue()))
            .andExpect(jsonPath("$.album").value(DEFAULT_ALBUM.toString()))
            .andExpect(jsonPath("$.andamento").value(DEFAULT_ANDAMENTO.toString()))
            .andExpect(jsonPath("$.data").value(sameInstant(DEFAULT_DATA)))
            .andExpect(jsonPath("$.dataentregadocs").value(sameInstant(DEFAULT_DATAENTREGADOCS)))
            .andExpect(jsonPath("$.dataexpedicaoprorrogacao1").value(sameInstant(DEFAULT_DATAEXPEDICAOPRORROGACAO_1)))
            .andExpect(jsonPath("$.dataexpedicaoprorrogacao2").value(sameInstant(DEFAULT_DATAEXPEDICAOPRORROGACAO_2)))
            .andExpect(jsonPath("$.dataexpedicaoprorrogacao3").value(sameInstant(DEFAULT_DATAEXPEDICAOPRORROGACAO_3)))
            .andExpect(jsonPath("$.datapedidoprorrogacao1").value(sameInstant(DEFAULT_DATAPEDIDOPRORROGACAO_1)))
            .andExpect(jsonPath("$.datapedidoprorrogacao2").value(sameInstant(DEFAULT_DATAPEDIDOPRORROGACAO_2)))
            .andExpect(jsonPath("$.datapedidoprorrogacao3").value(sameInstant(DEFAULT_DATAPEDIDOPRORROGACAO_3)))
            .andExpect(jsonPath("$.datavalidadeprorrogacao1").value(sameInstant(DEFAULT_DATAVALIDADEPRORROGACAO_1)))
            .andExpect(jsonPath("$.datavalidadeprorrogacao2").value(sameInstant(DEFAULT_DATAVALIDADEPRORROGACAO_2)))
            .andExpect(jsonPath("$.datavalidadeprorrogacao3").value(sameInstant(DEFAULT_DATAVALIDADEPRORROGACAO_3)))
            .andExpect(jsonPath("$.datavencimento").value(sameInstant(DEFAULT_DATAVENCIMENTO)))
            .andExpect(jsonPath("$.datavencimentoatual").value(sameInstant(DEFAULT_DATAVENCIMENTOATUAL)))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.docsentregues").value(DEFAULT_DOCSENTREGUES.toString()))
            .andExpect(jsonPath("$.fcei").value(DEFAULT_FCEI.toString()))
            .andExpect(jsonPath("$.fceidatapagamento").value(sameInstant(DEFAULT_FCEIDATAPAGAMENTO)))
            .andExpect(jsonPath("$.fceidataprotocolo").value(sameInstant(DEFAULT_FCEIDATAPROTOCOLO)))
            .andExpect(jsonPath("$.fceivalor").value(DEFAULT_FCEIVALOR.doubleValue()))
            .andExpect(jsonPath("$.folder").value(DEFAULT_FOLDER.toString()))
            .andExpect(jsonPath("$.inativo").value(DEFAULT_INATIVO.booleanValue()))
            .andExpect(jsonPath("$.kmfim").value(DEFAULT_KMFIM))
            .andExpect(jsonPath("$.kminicio").value(DEFAULT_KMINICIO))
            .andExpect(jsonPath("$.lado").value(DEFAULT_LADO.toString()))
            .andExpect(jsonPath("$.naoria").value(DEFAULT_NAORIA.booleanValue()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO.toString()))
            .andExpect(jsonPath("$.numerooficioconcessaoprorrogacao1").value(DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_1.toString()))
            .andExpect(jsonPath("$.numerooficioconcessaoprorrogacao2").value(DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_2.toString()))
            .andExpect(jsonPath("$.numerooficioconcessaoprorrogacao3").value(DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_3.toString()))
            .andExpect(jsonPath("$.numerooficioprorrogacao1").value(DEFAULT_NUMEROOFICIOPRORROGACAO_1.toString()))
            .andExpect(jsonPath("$.numerooficioprorrogacao2").value(DEFAULT_NUMEROOFICIOPRORROGACAO_2.toString()))
            .andExpect(jsonPath("$.numerooficioprorrogacao3").value(DEFAULT_NUMEROOFICIOPRORROGACAO_3.toString()))
            .andExpect(jsonPath("$.numeroprocesso").value(DEFAULT_NUMEROPROCESSO.toString()))
            .andExpect(jsonPath("$.observacao").value(DEFAULT_OBSERVACAO.toString()))
            .andExpect(jsonPath("$.pendente").value(DEFAULT_PENDENTE.toString()))
            .andExpect(jsonPath("$.prazomes").value(DEFAULT_PRAZOMES.booleanValue()))
            .andExpect(jsonPath("$.prazovalidade").value(DEFAULT_PRAZOVALIDADE))
            .andExpect(jsonPath("$.proprietario").value(DEFAULT_PROPRIETARIO.toString()))
            .andExpect(jsonPath("$.reciboentregadocs").value(DEFAULT_RECIBOENTREGADOCS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAutorizacao() throws Exception {
        // Get the autorizacao
        restAutorizacaoMockMvc.perform(get("/api/autorizacaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAutorizacao() throws Exception {
        // Initialize the database
        autorizacaoRepository.saveAndFlush(autorizacao);
        int databaseSizeBeforeUpdate = autorizacaoRepository.findAll().size();

        // Update the autorizacao
        Autorizacao updatedAutorizacao = autorizacaoRepository.findOne(autorizacao.getId());
        updatedAutorizacao
            .album(UPDATED_ALBUM)
            .andamento(UPDATED_ANDAMENTO)
            .data(UPDATED_DATA)
            .dataentregadocs(UPDATED_DATAENTREGADOCS)
            .dataexpedicaoprorrogacao1(UPDATED_DATAEXPEDICAOPRORROGACAO_1)
            .dataexpedicaoprorrogacao2(UPDATED_DATAEXPEDICAOPRORROGACAO_2)
            .dataexpedicaoprorrogacao3(UPDATED_DATAEXPEDICAOPRORROGACAO_3)
            .datapedidoprorrogacao1(UPDATED_DATAPEDIDOPRORROGACAO_1)
            .datapedidoprorrogacao2(UPDATED_DATAPEDIDOPRORROGACAO_2)
            .datapedidoprorrogacao3(UPDATED_DATAPEDIDOPRORROGACAO_3)
            .datavalidadeprorrogacao1(UPDATED_DATAVALIDADEPRORROGACAO_1)
            .datavalidadeprorrogacao2(UPDATED_DATAVALIDADEPRORROGACAO_2)
            .datavalidadeprorrogacao3(UPDATED_DATAVALIDADEPRORROGACAO_3)
            .datavencimento(UPDATED_DATAVENCIMENTO)
            .datavencimentoatual(UPDATED_DATAVENCIMENTOATUAL)
            .descricao(UPDATED_DESCRICAO)
            .docsentregues(UPDATED_DOCSENTREGUES)
            .fcei(UPDATED_FCEI)
            .fceidatapagamento(UPDATED_FCEIDATAPAGAMENTO)
            .fceidataprotocolo(UPDATED_FCEIDATAPROTOCOLO)
            .fceivalor(UPDATED_FCEIVALOR)
            .folder(UPDATED_FOLDER)
            .inativo(UPDATED_INATIVO)
            .kmfim(UPDATED_KMFIM)
            .kminicio(UPDATED_KMINICIO)
            .lado(UPDATED_LADO)
            .naoria(UPDATED_NAORIA)
            .numero(UPDATED_NUMERO)
            .numerooficioconcessaoprorrogacao1(UPDATED_NUMEROOFICIOCONCESSAOPRORROGACAO_1)
            .numerooficioconcessaoprorrogacao2(UPDATED_NUMEROOFICIOCONCESSAOPRORROGACAO_2)
            .numerooficioconcessaoprorrogacao3(UPDATED_NUMEROOFICIOCONCESSAOPRORROGACAO_3)
            .numerooficioprorrogacao1(UPDATED_NUMEROOFICIOPRORROGACAO_1)
            .numerooficioprorrogacao2(UPDATED_NUMEROOFICIOPRORROGACAO_2)
            .numerooficioprorrogacao3(UPDATED_NUMEROOFICIOPRORROGACAO_3)
            .numeroprocesso(UPDATED_NUMEROPROCESSO)
            .observacao(UPDATED_OBSERVACAO)
            .pendente(UPDATED_PENDENTE)
            .prazomes(UPDATED_PRAZOMES)
            .prazovalidade(UPDATED_PRAZOVALIDADE)
            .proprietario(UPDATED_PROPRIETARIO)
            .reciboentregadocs(UPDATED_RECIBOENTREGADOCS);
        AutorizacaoDTO autorizacaoDTO = autorizacaoMapper.toDto(updatedAutorizacao);

        restAutorizacaoMockMvc.perform(put("/api/autorizacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(autorizacaoDTO)))
            .andExpect(status().isOk());

        // Validate the Autorizacao in the database
        List<Autorizacao> autorizacaoList = autorizacaoRepository.findAll();
        assertThat(autorizacaoList).hasSize(databaseSizeBeforeUpdate);
        Autorizacao testAutorizacao = autorizacaoList.get(autorizacaoList.size() - 1);
        assertThat(testAutorizacao.getAlbum()).isEqualTo(UPDATED_ALBUM);
        assertThat(testAutorizacao.getAndamento()).isEqualTo(UPDATED_ANDAMENTO);
        assertThat(testAutorizacao.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testAutorizacao.getDataentregadocs()).isEqualTo(UPDATED_DATAENTREGADOCS);
        assertThat(testAutorizacao.getDataexpedicaoprorrogacao1()).isEqualTo(UPDATED_DATAEXPEDICAOPRORROGACAO_1);
        assertThat(testAutorizacao.getDataexpedicaoprorrogacao2()).isEqualTo(UPDATED_DATAEXPEDICAOPRORROGACAO_2);
        assertThat(testAutorizacao.getDataexpedicaoprorrogacao3()).isEqualTo(UPDATED_DATAEXPEDICAOPRORROGACAO_3);
        assertThat(testAutorizacao.getDatapedidoprorrogacao1()).isEqualTo(UPDATED_DATAPEDIDOPRORROGACAO_1);
        assertThat(testAutorizacao.getDatapedidoprorrogacao2()).isEqualTo(UPDATED_DATAPEDIDOPRORROGACAO_2);
        assertThat(testAutorizacao.getDatapedidoprorrogacao3()).isEqualTo(UPDATED_DATAPEDIDOPRORROGACAO_3);
        assertThat(testAutorizacao.getDatavalidadeprorrogacao1()).isEqualTo(UPDATED_DATAVALIDADEPRORROGACAO_1);
        assertThat(testAutorizacao.getDatavalidadeprorrogacao2()).isEqualTo(UPDATED_DATAVALIDADEPRORROGACAO_2);
        assertThat(testAutorizacao.getDatavalidadeprorrogacao3()).isEqualTo(UPDATED_DATAVALIDADEPRORROGACAO_3);
        assertThat(testAutorizacao.getDatavencimento()).isEqualTo(UPDATED_DATAVENCIMENTO);
        assertThat(testAutorizacao.getDatavencimentoatual()).isEqualTo(UPDATED_DATAVENCIMENTOATUAL);
        assertThat(testAutorizacao.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testAutorizacao.getDocsentregues()).isEqualTo(UPDATED_DOCSENTREGUES);
        assertThat(testAutorizacao.getFcei()).isEqualTo(UPDATED_FCEI);
        assertThat(testAutorizacao.getFceidatapagamento()).isEqualTo(UPDATED_FCEIDATAPAGAMENTO);
        assertThat(testAutorizacao.getFceidataprotocolo()).isEqualTo(UPDATED_FCEIDATAPROTOCOLO);
        assertThat(testAutorizacao.getFceivalor()).isEqualTo(UPDATED_FCEIVALOR);
        assertThat(testAutorizacao.getFolder()).isEqualTo(UPDATED_FOLDER);
        assertThat(testAutorizacao.isInativo()).isEqualTo(UPDATED_INATIVO);
        assertThat(testAutorizacao.getKmfim()).isEqualTo(UPDATED_KMFIM);
        assertThat(testAutorizacao.getKminicio()).isEqualTo(UPDATED_KMINICIO);
        assertThat(testAutorizacao.getLado()).isEqualTo(UPDATED_LADO);
        assertThat(testAutorizacao.isNaoria()).isEqualTo(UPDATED_NAORIA);
        assertThat(testAutorizacao.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testAutorizacao.getNumerooficioconcessaoprorrogacao1()).isEqualTo(UPDATED_NUMEROOFICIOCONCESSAOPRORROGACAO_1);
        assertThat(testAutorizacao.getNumerooficioconcessaoprorrogacao2()).isEqualTo(UPDATED_NUMEROOFICIOCONCESSAOPRORROGACAO_2);
        assertThat(testAutorizacao.getNumerooficioconcessaoprorrogacao3()).isEqualTo(UPDATED_NUMEROOFICIOCONCESSAOPRORROGACAO_3);
        assertThat(testAutorizacao.getNumerooficioprorrogacao1()).isEqualTo(UPDATED_NUMEROOFICIOPRORROGACAO_1);
        assertThat(testAutorizacao.getNumerooficioprorrogacao2()).isEqualTo(UPDATED_NUMEROOFICIOPRORROGACAO_2);
        assertThat(testAutorizacao.getNumerooficioprorrogacao3()).isEqualTo(UPDATED_NUMEROOFICIOPRORROGACAO_3);
        assertThat(testAutorizacao.getNumeroprocesso()).isEqualTo(UPDATED_NUMEROPROCESSO);
        assertThat(testAutorizacao.getObservacao()).isEqualTo(UPDATED_OBSERVACAO);
        assertThat(testAutorizacao.getPendente()).isEqualTo(UPDATED_PENDENTE);
        assertThat(testAutorizacao.isPrazomes()).isEqualTo(UPDATED_PRAZOMES);
        assertThat(testAutorizacao.getPrazovalidade()).isEqualTo(UPDATED_PRAZOVALIDADE);
        assertThat(testAutorizacao.getProprietario()).isEqualTo(UPDATED_PROPRIETARIO);
        assertThat(testAutorizacao.getReciboentregadocs()).isEqualTo(UPDATED_RECIBOENTREGADOCS);
    }

    @Test
    @Transactional
    public void updateNonExistingAutorizacao() throws Exception {
        int databaseSizeBeforeUpdate = autorizacaoRepository.findAll().size();

        // Create the Autorizacao
        AutorizacaoDTO autorizacaoDTO = autorizacaoMapper.toDto(autorizacao);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAutorizacaoMockMvc.perform(put("/api/autorizacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(autorizacaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Autorizacao in the database
        List<Autorizacao> autorizacaoList = autorizacaoRepository.findAll();
        assertThat(autorizacaoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAutorizacao() throws Exception {
        // Initialize the database
        autorizacaoRepository.saveAndFlush(autorizacao);
        int databaseSizeBeforeDelete = autorizacaoRepository.findAll().size();

        // Get the autorizacao
        restAutorizacaoMockMvc.perform(delete("/api/autorizacaos/{id}", autorizacao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Autorizacao> autorizacaoList = autorizacaoRepository.findAll();
        assertThat(autorizacaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Autorizacao.class);
        Autorizacao autorizacao1 = new Autorizacao();
        autorizacao1.setId(1L);
        Autorizacao autorizacao2 = new Autorizacao();
        autorizacao2.setId(autorizacao1.getId());
        assertThat(autorizacao1).isEqualTo(autorizacao2);
        autorizacao2.setId(2L);
        assertThat(autorizacao1).isNotEqualTo(autorizacao2);
        autorizacao1.setId(null);
        assertThat(autorizacao1).isNotEqualTo(autorizacao2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AutorizacaoDTO.class);
        AutorizacaoDTO autorizacaoDTO1 = new AutorizacaoDTO();
        autorizacaoDTO1.setId(1L);
        AutorizacaoDTO autorizacaoDTO2 = new AutorizacaoDTO();
        assertThat(autorizacaoDTO1).isNotEqualTo(autorizacaoDTO2);
        autorizacaoDTO2.setId(autorizacaoDTO1.getId());
        assertThat(autorizacaoDTO1).isEqualTo(autorizacaoDTO2);
        autorizacaoDTO2.setId(2L);
        assertThat(autorizacaoDTO1).isNotEqualTo(autorizacaoDTO2);
        autorizacaoDTO1.setId(null);
        assertThat(autorizacaoDTO1).isNotEqualTo(autorizacaoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(autorizacaoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(autorizacaoMapper.fromId(null)).isNull();
    }
}
