package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Auc;
import br.com.homemade.repository.AucRepository;
import br.com.homemade.service.dto.AucDTO;
import br.com.homemade.service.mapper.AucMapper;
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
 * Test class for the AucResource REST controller.
 *
 * @see AucResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class AucResourceIntTest {

    private static final BigDecimal DEFAULT_FCEIVALOR = new BigDecimal(1);
    private static final BigDecimal UPDATED_FCEIVALOR = new BigDecimal(2);

    private static final Boolean DEFAULT_INATIVO = false;
    private static final Boolean UPDATED_INATIVO = true;

    private static final Boolean DEFAULT_PRAZOMES = false;
    private static final Boolean UPDATED_PRAZOMES = true;

    private static final Boolean DEFAULT_REPOSICAOFLORESTAL = false;
    private static final Boolean UPDATED_REPOSICAOFLORESTAL = true;

    private static final Boolean DEFAULT_COMPENSACAOAMBIENTAL = false;
    private static final Boolean UPDATED_COMPENSACAOAMBIENTAL = true;

    private static final Integer DEFAULT_PRAZOVALIDADE = 1;
    private static final Integer UPDATED_PRAZOVALIDADE = 2;

    private static final String DEFAULT_FCEI = "AAAAAAAAAA";
    private static final String UPDATED_FCEI = "BBBBBBBBBB";

    private static final String DEFAULT_RECIBOENTREGADOCS = "AAAAAAAAAA";
    private static final String UPDATED_RECIBOENTREGADOCS = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO = "BBBBBBBBBB";

    private static final String DEFAULT_NUMEROPROCESSO = "AAAAAAAAAA";
    private static final String UPDATED_NUMEROPROCESSO = "BBBBBBBBBB";

    private static final String DEFAULT_NUMEROOFICIOLOCALPEDIDO = "AAAAAAAAAA";
    private static final String UPDATED_NUMEROOFICIOLOCALPEDIDO = "BBBBBBBBBB";

    private static final String DEFAULT_NUMEROOFICIOOFICIALPEDIDO = "AAAAAAAAAA";
    private static final String UPDATED_NUMEROOFICIOOFICIALPEDIDO = "BBBBBBBBBB";

    private static final String DEFAULT_NUMEROOFICIOLOCALRECEBIMENTO = "AAAAAAAAAA";
    private static final String UPDATED_NUMEROOFICIOLOCALRECEBIMENTO = "BBBBBBBBBB";

    private static final String DEFAULT_NUMEROOFICIOOFICIALRECEBIMENTO = "AAAAAAAAAA";
    private static final String UPDATED_NUMEROOFICIOOFICIALRECEBIMENTO = "BBBBBBBBBB";

    private static final String DEFAULT_NUMEROPARECERTECNICO = "AAAAAAAAAA";
    private static final String UPDATED_NUMEROPARECERTECNICO = "BBBBBBBBBB";

    private static final String DEFAULT_NUMEROOFICIOPRORROGACAO_1 = "AAAAAAAAAA";
    private static final String UPDATED_NUMEROOFICIOPRORROGACAO_1 = "BBBBBBBBBB";

    private static final String DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_1 = "AAAAAAAAAA";
    private static final String UPDATED_NUMEROOFICIOCONCESSAOPRORROGACAO_1 = "BBBBBBBBBB";

    private static final String DEFAULT_NUMEROOFICIOPRORROGACAO_2 = "AAAAAAAAAA";
    private static final String UPDATED_NUMEROOFICIOPRORROGACAO_2 = "BBBBBBBBBB";

    private static final String DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_2 = "AAAAAAAAAA";
    private static final String UPDATED_NUMEROOFICIOCONCESSAOPRORROGACAO_2 = "BBBBBBBBBB";

    private static final String DEFAULT_NUMEROOFICIOPRORROGACAO_3 = "AAAAAAAAAA";
    private static final String UPDATED_NUMEROOFICIOPRORROGACAO_3 = "BBBBBBBBBB";

    private static final String DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_3 = "AAAAAAAAAA";
    private static final String UPDATED_NUMEROOFICIOCONCESSAOPRORROGACAO_3 = "BBBBBBBBBB";

    private static final String DEFAULT_ALBUM = "AAAAAAAAAA";
    private static final String UPDATED_ALBUM = "BBBBBBBBBB";

    private static final String DEFAULT_FOLDER = "AAAAAAAAAA";
    private static final String UPDATED_FOLDER = "BBBBBBBBBB";

    private static final String DEFAULT_PENDENTE = "AAAAAAAAAA";
    private static final String UPDATED_PENDENTE = "BBBBBBBBBB";

    private static final String DEFAULT_ANDAMENTO = "AAAAAAAAAA";
    private static final String UPDATED_ANDAMENTO = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final String DEFAULT_OBSERVACAO = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVACAO = "BBBBBBBBBB";

    private static final String DEFAULT_DOCSENTREGUES = "AAAAAAAAAA";
    private static final String UPDATED_DOCSENTREGUES = "BBBBBBBBBB";

    private static final String DEFAULT_REPOSICAOFLORESTALOBS = "AAAAAAAAAA";
    private static final String UPDATED_REPOSICAOFLORESTALOBS = "BBBBBBBBBB";

    private static final String DEFAULT_COMPENSACAOAMBIENTALOBS = "AAAAAAAAAA";
    private static final String UPDATED_COMPENSACAOAMBIENTALOBS = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_FCEIDATAPROTOCOLO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FCEIDATAPROTOCOLO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_FCEIDATAPAGAMENTO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FCEIDATAPAGAMENTO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAENTREGADOCS = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAENTREGADOCS = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAOFICIOLOCALPEDIDO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAOFICIOLOCALPEDIDO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAOFICIOREOFICIALPEDIDO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAOFICIOREOFICIALPEDIDO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAOFICIOLOCALRECEBIMENTO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAOFICIOLOCALRECEBIMENTO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAOFICIOREOFICIALRECEBIMENTO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAOFICIOREOFICIALRECEBIMENTO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAEMISSAO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAEMISSAO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAPEDIDOPRORROGACAO_1 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAPEDIDOPRORROGACAO_1 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAEXPEDICAOPRORROGACAO_1 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAEXPEDICAOPRORROGACAO_1 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAVALIDADEPRORROGACAO_1 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAVALIDADEPRORROGACAO_1 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAPEDIDOPRORROGACAO_2 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAPEDIDOPRORROGACAO_2 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAEXPEDICAOPRORROGACAO_2 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAEXPEDICAOPRORROGACAO_2 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAVALIDADEPRORROGACAO_2 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAVALIDADEPRORROGACAO_2 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAPEDIDOPRORROGACAO_3 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAPEDIDOPRORROGACAO_3 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAEXPEDICAOPRORROGACAO_3 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAEXPEDICAOPRORROGACAO_3 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAVALIDADEPRORROGACAO_3 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAVALIDADEPRORROGACAO_3 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAVENCIMENTO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAVENCIMENTO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAVENCIMENTOATUAL = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAVENCIMENTOATUAL = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private AucRepository aucRepository;

    @Autowired
    private AucMapper aucMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAucMockMvc;

    private Auc auc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AucResource aucResource = new AucResource(aucRepository, aucMapper);
        this.restAucMockMvc = MockMvcBuilders.standaloneSetup(aucResource)
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
    public static Auc createEntity(EntityManager em) {
        Auc auc = new Auc()
            .fceivalor(DEFAULT_FCEIVALOR)
            .inativo(DEFAULT_INATIVO)
            .prazomes(DEFAULT_PRAZOMES)
            .reposicaoflorestal(DEFAULT_REPOSICAOFLORESTAL)
            .compensacaoambiental(DEFAULT_COMPENSACAOAMBIENTAL)
            .prazovalidade(DEFAULT_PRAZOVALIDADE)
            .fcei(DEFAULT_FCEI)
            .reciboentregadocs(DEFAULT_RECIBOENTREGADOCS)
            .numero(DEFAULT_NUMERO)
            .numeroprocesso(DEFAULT_NUMEROPROCESSO)
            .numerooficiolocalpedido(DEFAULT_NUMEROOFICIOLOCALPEDIDO)
            .numerooficiooficialpedido(DEFAULT_NUMEROOFICIOOFICIALPEDIDO)
            .numerooficiolocalrecebimento(DEFAULT_NUMEROOFICIOLOCALRECEBIMENTO)
            .numerooficiooficialrecebimento(DEFAULT_NUMEROOFICIOOFICIALRECEBIMENTO)
            .numeroparecertecnico(DEFAULT_NUMEROPARECERTECNICO)
            .numerooficioprorrogacao1(DEFAULT_NUMEROOFICIOPRORROGACAO_1)
            .numerooficioconcessaoprorrogacao1(DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_1)
            .numerooficioprorrogacao2(DEFAULT_NUMEROOFICIOPRORROGACAO_2)
            .numerooficioconcessaoprorrogacao2(DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_2)
            .numerooficioprorrogacao3(DEFAULT_NUMEROOFICIOPRORROGACAO_3)
            .numerooficioconcessaoprorrogacao3(DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_3)
            .album(DEFAULT_ALBUM)
            .folder(DEFAULT_FOLDER)
            .pendente(DEFAULT_PENDENTE)
            .andamento(DEFAULT_ANDAMENTO)
            .descricao(DEFAULT_DESCRICAO)
            .observacao(DEFAULT_OBSERVACAO)
            .docsentregues(DEFAULT_DOCSENTREGUES)
            .reposicaoflorestalobs(DEFAULT_REPOSICAOFLORESTALOBS)
            .compensacaoambientalobs(DEFAULT_COMPENSACAOAMBIENTALOBS)
            .fceidataprotocolo(DEFAULT_FCEIDATAPROTOCOLO)
            .fceidatapagamento(DEFAULT_FCEIDATAPAGAMENTO)
            .dataentregadocs(DEFAULT_DATAENTREGADOCS)
            .dataoficiolocalpedido(DEFAULT_DATAOFICIOLOCALPEDIDO)
            .dataoficioreoficialpedido(DEFAULT_DATAOFICIOREOFICIALPEDIDO)
            .dataoficiolocalrecebimento(DEFAULT_DATAOFICIOLOCALRECEBIMENTO)
            .dataoficioreoficialrecebimento(DEFAULT_DATAOFICIOREOFICIALRECEBIMENTO)
            .dataemissao(DEFAULT_DATAEMISSAO)
            .datapedidoprorrogacao1(DEFAULT_DATAPEDIDOPRORROGACAO_1)
            .dataexpedicaoprorrogacao1(DEFAULT_DATAEXPEDICAOPRORROGACAO_1)
            .datavalidadeprorrogacao1(DEFAULT_DATAVALIDADEPRORROGACAO_1)
            .datapedidoprorrogacao2(DEFAULT_DATAPEDIDOPRORROGACAO_2)
            .dataexpedicaoprorrogacao2(DEFAULT_DATAEXPEDICAOPRORROGACAO_2)
            .datavalidadeprorrogacao2(DEFAULT_DATAVALIDADEPRORROGACAO_2)
            .datapedidoprorrogacao3(DEFAULT_DATAPEDIDOPRORROGACAO_3)
            .dataexpedicaoprorrogacao3(DEFAULT_DATAEXPEDICAOPRORROGACAO_3)
            .datavalidadeprorrogacao3(DEFAULT_DATAVALIDADEPRORROGACAO_3)
            .datavencimento(DEFAULT_DATAVENCIMENTO)
            .datavencimentoatual(DEFAULT_DATAVENCIMENTOATUAL);
        return auc;
    }

    @Before
    public void initTest() {
        auc = createEntity(em);
    }

    @Test
    @Transactional
    public void createAuc() throws Exception {
        int databaseSizeBeforeCreate = aucRepository.findAll().size();

        // Create the Auc
        AucDTO aucDTO = aucMapper.toDto(auc);
        restAucMockMvc.perform(post("/api/aucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aucDTO)))
            .andExpect(status().isCreated());

        // Validate the Auc in the database
        List<Auc> aucList = aucRepository.findAll();
        assertThat(aucList).hasSize(databaseSizeBeforeCreate + 1);
        Auc testAuc = aucList.get(aucList.size() - 1);
        assertThat(testAuc.getFceivalor()).isEqualTo(DEFAULT_FCEIVALOR);
        assertThat(testAuc.isInativo()).isEqualTo(DEFAULT_INATIVO);
        assertThat(testAuc.isPrazomes()).isEqualTo(DEFAULT_PRAZOMES);
        assertThat(testAuc.isReposicaoflorestal()).isEqualTo(DEFAULT_REPOSICAOFLORESTAL);
        assertThat(testAuc.isCompensacaoambiental()).isEqualTo(DEFAULT_COMPENSACAOAMBIENTAL);
        assertThat(testAuc.getPrazovalidade()).isEqualTo(DEFAULT_PRAZOVALIDADE);
        assertThat(testAuc.getFcei()).isEqualTo(DEFAULT_FCEI);
        assertThat(testAuc.getReciboentregadocs()).isEqualTo(DEFAULT_RECIBOENTREGADOCS);
        assertThat(testAuc.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testAuc.getNumeroprocesso()).isEqualTo(DEFAULT_NUMEROPROCESSO);
        assertThat(testAuc.getNumerooficiolocalpedido()).isEqualTo(DEFAULT_NUMEROOFICIOLOCALPEDIDO);
        assertThat(testAuc.getNumerooficiooficialpedido()).isEqualTo(DEFAULT_NUMEROOFICIOOFICIALPEDIDO);
        assertThat(testAuc.getNumerooficiolocalrecebimento()).isEqualTo(DEFAULT_NUMEROOFICIOLOCALRECEBIMENTO);
        assertThat(testAuc.getNumerooficiooficialrecebimento()).isEqualTo(DEFAULT_NUMEROOFICIOOFICIALRECEBIMENTO);
        assertThat(testAuc.getNumeroparecertecnico()).isEqualTo(DEFAULT_NUMEROPARECERTECNICO);
        assertThat(testAuc.getNumerooficioprorrogacao1()).isEqualTo(DEFAULT_NUMEROOFICIOPRORROGACAO_1);
        assertThat(testAuc.getNumerooficioconcessaoprorrogacao1()).isEqualTo(DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_1);
        assertThat(testAuc.getNumerooficioprorrogacao2()).isEqualTo(DEFAULT_NUMEROOFICIOPRORROGACAO_2);
        assertThat(testAuc.getNumerooficioconcessaoprorrogacao2()).isEqualTo(DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_2);
        assertThat(testAuc.getNumerooficioprorrogacao3()).isEqualTo(DEFAULT_NUMEROOFICIOPRORROGACAO_3);
        assertThat(testAuc.getNumerooficioconcessaoprorrogacao3()).isEqualTo(DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_3);
        assertThat(testAuc.getAlbum()).isEqualTo(DEFAULT_ALBUM);
        assertThat(testAuc.getFolder()).isEqualTo(DEFAULT_FOLDER);
        assertThat(testAuc.getPendente()).isEqualTo(DEFAULT_PENDENTE);
        assertThat(testAuc.getAndamento()).isEqualTo(DEFAULT_ANDAMENTO);
        assertThat(testAuc.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testAuc.getObservacao()).isEqualTo(DEFAULT_OBSERVACAO);
        assertThat(testAuc.getDocsentregues()).isEqualTo(DEFAULT_DOCSENTREGUES);
        assertThat(testAuc.getReposicaoflorestalobs()).isEqualTo(DEFAULT_REPOSICAOFLORESTALOBS);
        assertThat(testAuc.getCompensacaoambientalobs()).isEqualTo(DEFAULT_COMPENSACAOAMBIENTALOBS);
        assertThat(testAuc.getFceidataprotocolo()).isEqualTo(DEFAULT_FCEIDATAPROTOCOLO);
        assertThat(testAuc.getFceidatapagamento()).isEqualTo(DEFAULT_FCEIDATAPAGAMENTO);
        assertThat(testAuc.getDataentregadocs()).isEqualTo(DEFAULT_DATAENTREGADOCS);
        assertThat(testAuc.getDataoficiolocalpedido()).isEqualTo(DEFAULT_DATAOFICIOLOCALPEDIDO);
        assertThat(testAuc.getDataoficioreoficialpedido()).isEqualTo(DEFAULT_DATAOFICIOREOFICIALPEDIDO);
        assertThat(testAuc.getDataoficiolocalrecebimento()).isEqualTo(DEFAULT_DATAOFICIOLOCALRECEBIMENTO);
        assertThat(testAuc.getDataoficioreoficialrecebimento()).isEqualTo(DEFAULT_DATAOFICIOREOFICIALRECEBIMENTO);
        assertThat(testAuc.getDataemissao()).isEqualTo(DEFAULT_DATAEMISSAO);
        assertThat(testAuc.getDatapedidoprorrogacao1()).isEqualTo(DEFAULT_DATAPEDIDOPRORROGACAO_1);
        assertThat(testAuc.getDataexpedicaoprorrogacao1()).isEqualTo(DEFAULT_DATAEXPEDICAOPRORROGACAO_1);
        assertThat(testAuc.getDatavalidadeprorrogacao1()).isEqualTo(DEFAULT_DATAVALIDADEPRORROGACAO_1);
        assertThat(testAuc.getDatapedidoprorrogacao2()).isEqualTo(DEFAULT_DATAPEDIDOPRORROGACAO_2);
        assertThat(testAuc.getDataexpedicaoprorrogacao2()).isEqualTo(DEFAULT_DATAEXPEDICAOPRORROGACAO_2);
        assertThat(testAuc.getDatavalidadeprorrogacao2()).isEqualTo(DEFAULT_DATAVALIDADEPRORROGACAO_2);
        assertThat(testAuc.getDatapedidoprorrogacao3()).isEqualTo(DEFAULT_DATAPEDIDOPRORROGACAO_3);
        assertThat(testAuc.getDataexpedicaoprorrogacao3()).isEqualTo(DEFAULT_DATAEXPEDICAOPRORROGACAO_3);
        assertThat(testAuc.getDatavalidadeprorrogacao3()).isEqualTo(DEFAULT_DATAVALIDADEPRORROGACAO_3);
        assertThat(testAuc.getDatavencimento()).isEqualTo(DEFAULT_DATAVENCIMENTO);
        assertThat(testAuc.getDatavencimentoatual()).isEqualTo(DEFAULT_DATAVENCIMENTOATUAL);
    }

    @Test
    @Transactional
    public void createAucWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = aucRepository.findAll().size();

        // Create the Auc with an existing ID
        auc.setId(1L);
        AucDTO aucDTO = aucMapper.toDto(auc);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAucMockMvc.perform(post("/api/aucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aucDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Auc> aucList = aucRepository.findAll();
        assertThat(aucList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAucs() throws Exception {
        // Initialize the database
        aucRepository.saveAndFlush(auc);

        // Get all the aucList
        restAucMockMvc.perform(get("/api/aucs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(auc.getId().intValue())))
            .andExpect(jsonPath("$.[*].fceivalor").value(hasItem(DEFAULT_FCEIVALOR.intValue())))
            .andExpect(jsonPath("$.[*].inativo").value(hasItem(DEFAULT_INATIVO.booleanValue())))
            .andExpect(jsonPath("$.[*].prazomes").value(hasItem(DEFAULT_PRAZOMES.booleanValue())))
            .andExpect(jsonPath("$.[*].reposicaoflorestal").value(hasItem(DEFAULT_REPOSICAOFLORESTAL.booleanValue())))
            .andExpect(jsonPath("$.[*].compensacaoambiental").value(hasItem(DEFAULT_COMPENSACAOAMBIENTAL.booleanValue())))
            .andExpect(jsonPath("$.[*].prazovalidade").value(hasItem(DEFAULT_PRAZOVALIDADE)))
            .andExpect(jsonPath("$.[*].fcei").value(hasItem(DEFAULT_FCEI.toString())))
            .andExpect(jsonPath("$.[*].reciboentregadocs").value(hasItem(DEFAULT_RECIBOENTREGADOCS.toString())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO.toString())))
            .andExpect(jsonPath("$.[*].numeroprocesso").value(hasItem(DEFAULT_NUMEROPROCESSO.toString())))
            .andExpect(jsonPath("$.[*].numerooficiolocalpedido").value(hasItem(DEFAULT_NUMEROOFICIOLOCALPEDIDO.toString())))
            .andExpect(jsonPath("$.[*].numerooficiooficialpedido").value(hasItem(DEFAULT_NUMEROOFICIOOFICIALPEDIDO.toString())))
            .andExpect(jsonPath("$.[*].numerooficiolocalrecebimento").value(hasItem(DEFAULT_NUMEROOFICIOLOCALRECEBIMENTO.toString())))
            .andExpect(jsonPath("$.[*].numerooficiooficialrecebimento").value(hasItem(DEFAULT_NUMEROOFICIOOFICIALRECEBIMENTO.toString())))
            .andExpect(jsonPath("$.[*].numeroparecertecnico").value(hasItem(DEFAULT_NUMEROPARECERTECNICO.toString())))
            .andExpect(jsonPath("$.[*].numerooficioprorrogacao1").value(hasItem(DEFAULT_NUMEROOFICIOPRORROGACAO_1.toString())))
            .andExpect(jsonPath("$.[*].numerooficioconcessaoprorrogacao1").value(hasItem(DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_1.toString())))
            .andExpect(jsonPath("$.[*].numerooficioprorrogacao2").value(hasItem(DEFAULT_NUMEROOFICIOPRORROGACAO_2.toString())))
            .andExpect(jsonPath("$.[*].numerooficioconcessaoprorrogacao2").value(hasItem(DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_2.toString())))
            .andExpect(jsonPath("$.[*].numerooficioprorrogacao3").value(hasItem(DEFAULT_NUMEROOFICIOPRORROGACAO_3.toString())))
            .andExpect(jsonPath("$.[*].numerooficioconcessaoprorrogacao3").value(hasItem(DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_3.toString())))
            .andExpect(jsonPath("$.[*].album").value(hasItem(DEFAULT_ALBUM.toString())))
            .andExpect(jsonPath("$.[*].folder").value(hasItem(DEFAULT_FOLDER.toString())))
            .andExpect(jsonPath("$.[*].pendente").value(hasItem(DEFAULT_PENDENTE.toString())))
            .andExpect(jsonPath("$.[*].andamento").value(hasItem(DEFAULT_ANDAMENTO.toString())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].observacao").value(hasItem(DEFAULT_OBSERVACAO.toString())))
            .andExpect(jsonPath("$.[*].docsentregues").value(hasItem(DEFAULT_DOCSENTREGUES.toString())))
            .andExpect(jsonPath("$.[*].reposicaoflorestalobs").value(hasItem(DEFAULT_REPOSICAOFLORESTALOBS.toString())))
            .andExpect(jsonPath("$.[*].compensacaoambientalobs").value(hasItem(DEFAULT_COMPENSACAOAMBIENTALOBS.toString())))
            .andExpect(jsonPath("$.[*].fceidataprotocolo").value(hasItem(sameInstant(DEFAULT_FCEIDATAPROTOCOLO))))
            .andExpect(jsonPath("$.[*].fceidatapagamento").value(hasItem(sameInstant(DEFAULT_FCEIDATAPAGAMENTO))))
            .andExpect(jsonPath("$.[*].dataentregadocs").value(hasItem(sameInstant(DEFAULT_DATAENTREGADOCS))))
            .andExpect(jsonPath("$.[*].dataoficiolocalpedido").value(hasItem(sameInstant(DEFAULT_DATAOFICIOLOCALPEDIDO))))
            .andExpect(jsonPath("$.[*].dataoficioreoficialpedido").value(hasItem(sameInstant(DEFAULT_DATAOFICIOREOFICIALPEDIDO))))
            .andExpect(jsonPath("$.[*].dataoficiolocalrecebimento").value(hasItem(sameInstant(DEFAULT_DATAOFICIOLOCALRECEBIMENTO))))
            .andExpect(jsonPath("$.[*].dataoficioreoficialrecebimento").value(hasItem(sameInstant(DEFAULT_DATAOFICIOREOFICIALRECEBIMENTO))))
            .andExpect(jsonPath("$.[*].dataemissao").value(hasItem(sameInstant(DEFAULT_DATAEMISSAO))))
            .andExpect(jsonPath("$.[*].datapedidoprorrogacao1").value(hasItem(sameInstant(DEFAULT_DATAPEDIDOPRORROGACAO_1))))
            .andExpect(jsonPath("$.[*].dataexpedicaoprorrogacao1").value(hasItem(sameInstant(DEFAULT_DATAEXPEDICAOPRORROGACAO_1))))
            .andExpect(jsonPath("$.[*].datavalidadeprorrogacao1").value(hasItem(sameInstant(DEFAULT_DATAVALIDADEPRORROGACAO_1))))
            .andExpect(jsonPath("$.[*].datapedidoprorrogacao2").value(hasItem(sameInstant(DEFAULT_DATAPEDIDOPRORROGACAO_2))))
            .andExpect(jsonPath("$.[*].dataexpedicaoprorrogacao2").value(hasItem(sameInstant(DEFAULT_DATAEXPEDICAOPRORROGACAO_2))))
            .andExpect(jsonPath("$.[*].datavalidadeprorrogacao2").value(hasItem(sameInstant(DEFAULT_DATAVALIDADEPRORROGACAO_2))))
            .andExpect(jsonPath("$.[*].datapedidoprorrogacao3").value(hasItem(sameInstant(DEFAULT_DATAPEDIDOPRORROGACAO_3))))
            .andExpect(jsonPath("$.[*].dataexpedicaoprorrogacao3").value(hasItem(sameInstant(DEFAULT_DATAEXPEDICAOPRORROGACAO_3))))
            .andExpect(jsonPath("$.[*].datavalidadeprorrogacao3").value(hasItem(sameInstant(DEFAULT_DATAVALIDADEPRORROGACAO_3))))
            .andExpect(jsonPath("$.[*].datavencimento").value(hasItem(sameInstant(DEFAULT_DATAVENCIMENTO))))
            .andExpect(jsonPath("$.[*].datavencimentoatual").value(hasItem(sameInstant(DEFAULT_DATAVENCIMENTOATUAL))));
    }

    @Test
    @Transactional
    public void getAuc() throws Exception {
        // Initialize the database
        aucRepository.saveAndFlush(auc);

        // Get the auc
        restAucMockMvc.perform(get("/api/aucs/{id}", auc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(auc.getId().intValue()))
            .andExpect(jsonPath("$.fceivalor").value(DEFAULT_FCEIVALOR.intValue()))
            .andExpect(jsonPath("$.inativo").value(DEFAULT_INATIVO.booleanValue()))
            .andExpect(jsonPath("$.prazomes").value(DEFAULT_PRAZOMES.booleanValue()))
            .andExpect(jsonPath("$.reposicaoflorestal").value(DEFAULT_REPOSICAOFLORESTAL.booleanValue()))
            .andExpect(jsonPath("$.compensacaoambiental").value(DEFAULT_COMPENSACAOAMBIENTAL.booleanValue()))
            .andExpect(jsonPath("$.prazovalidade").value(DEFAULT_PRAZOVALIDADE))
            .andExpect(jsonPath("$.fcei").value(DEFAULT_FCEI.toString()))
            .andExpect(jsonPath("$.reciboentregadocs").value(DEFAULT_RECIBOENTREGADOCS.toString()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO.toString()))
            .andExpect(jsonPath("$.numeroprocesso").value(DEFAULT_NUMEROPROCESSO.toString()))
            .andExpect(jsonPath("$.numerooficiolocalpedido").value(DEFAULT_NUMEROOFICIOLOCALPEDIDO.toString()))
            .andExpect(jsonPath("$.numerooficiooficialpedido").value(DEFAULT_NUMEROOFICIOOFICIALPEDIDO.toString()))
            .andExpect(jsonPath("$.numerooficiolocalrecebimento").value(DEFAULT_NUMEROOFICIOLOCALRECEBIMENTO.toString()))
            .andExpect(jsonPath("$.numerooficiooficialrecebimento").value(DEFAULT_NUMEROOFICIOOFICIALRECEBIMENTO.toString()))
            .andExpect(jsonPath("$.numeroparecertecnico").value(DEFAULT_NUMEROPARECERTECNICO.toString()))
            .andExpect(jsonPath("$.numerooficioprorrogacao1").value(DEFAULT_NUMEROOFICIOPRORROGACAO_1.toString()))
            .andExpect(jsonPath("$.numerooficioconcessaoprorrogacao1").value(DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_1.toString()))
            .andExpect(jsonPath("$.numerooficioprorrogacao2").value(DEFAULT_NUMEROOFICIOPRORROGACAO_2.toString()))
            .andExpect(jsonPath("$.numerooficioconcessaoprorrogacao2").value(DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_2.toString()))
            .andExpect(jsonPath("$.numerooficioprorrogacao3").value(DEFAULT_NUMEROOFICIOPRORROGACAO_3.toString()))
            .andExpect(jsonPath("$.numerooficioconcessaoprorrogacao3").value(DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_3.toString()))
            .andExpect(jsonPath("$.album").value(DEFAULT_ALBUM.toString()))
            .andExpect(jsonPath("$.folder").value(DEFAULT_FOLDER.toString()))
            .andExpect(jsonPath("$.pendente").value(DEFAULT_PENDENTE.toString()))
            .andExpect(jsonPath("$.andamento").value(DEFAULT_ANDAMENTO.toString()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.observacao").value(DEFAULT_OBSERVACAO.toString()))
            .andExpect(jsonPath("$.docsentregues").value(DEFAULT_DOCSENTREGUES.toString()))
            .andExpect(jsonPath("$.reposicaoflorestalobs").value(DEFAULT_REPOSICAOFLORESTALOBS.toString()))
            .andExpect(jsonPath("$.compensacaoambientalobs").value(DEFAULT_COMPENSACAOAMBIENTALOBS.toString()))
            .andExpect(jsonPath("$.fceidataprotocolo").value(sameInstant(DEFAULT_FCEIDATAPROTOCOLO)))
            .andExpect(jsonPath("$.fceidatapagamento").value(sameInstant(DEFAULT_FCEIDATAPAGAMENTO)))
            .andExpect(jsonPath("$.dataentregadocs").value(sameInstant(DEFAULT_DATAENTREGADOCS)))
            .andExpect(jsonPath("$.dataoficiolocalpedido").value(sameInstant(DEFAULT_DATAOFICIOLOCALPEDIDO)))
            .andExpect(jsonPath("$.dataoficioreoficialpedido").value(sameInstant(DEFAULT_DATAOFICIOREOFICIALPEDIDO)))
            .andExpect(jsonPath("$.dataoficiolocalrecebimento").value(sameInstant(DEFAULT_DATAOFICIOLOCALRECEBIMENTO)))
            .andExpect(jsonPath("$.dataoficioreoficialrecebimento").value(sameInstant(DEFAULT_DATAOFICIOREOFICIALRECEBIMENTO)))
            .andExpect(jsonPath("$.dataemissao").value(sameInstant(DEFAULT_DATAEMISSAO)))
            .andExpect(jsonPath("$.datapedidoprorrogacao1").value(sameInstant(DEFAULT_DATAPEDIDOPRORROGACAO_1)))
            .andExpect(jsonPath("$.dataexpedicaoprorrogacao1").value(sameInstant(DEFAULT_DATAEXPEDICAOPRORROGACAO_1)))
            .andExpect(jsonPath("$.datavalidadeprorrogacao1").value(sameInstant(DEFAULT_DATAVALIDADEPRORROGACAO_1)))
            .andExpect(jsonPath("$.datapedidoprorrogacao2").value(sameInstant(DEFAULT_DATAPEDIDOPRORROGACAO_2)))
            .andExpect(jsonPath("$.dataexpedicaoprorrogacao2").value(sameInstant(DEFAULT_DATAEXPEDICAOPRORROGACAO_2)))
            .andExpect(jsonPath("$.datavalidadeprorrogacao2").value(sameInstant(DEFAULT_DATAVALIDADEPRORROGACAO_2)))
            .andExpect(jsonPath("$.datapedidoprorrogacao3").value(sameInstant(DEFAULT_DATAPEDIDOPRORROGACAO_3)))
            .andExpect(jsonPath("$.dataexpedicaoprorrogacao3").value(sameInstant(DEFAULT_DATAEXPEDICAOPRORROGACAO_3)))
            .andExpect(jsonPath("$.datavalidadeprorrogacao3").value(sameInstant(DEFAULT_DATAVALIDADEPRORROGACAO_3)))
            .andExpect(jsonPath("$.datavencimento").value(sameInstant(DEFAULT_DATAVENCIMENTO)))
            .andExpect(jsonPath("$.datavencimentoatual").value(sameInstant(DEFAULT_DATAVENCIMENTOATUAL)));
    }

    @Test
    @Transactional
    public void getNonExistingAuc() throws Exception {
        // Get the auc
        restAucMockMvc.perform(get("/api/aucs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAuc() throws Exception {
        // Initialize the database
        aucRepository.saveAndFlush(auc);
        int databaseSizeBeforeUpdate = aucRepository.findAll().size();

        // Update the auc
        Auc updatedAuc = aucRepository.findOne(auc.getId());
        updatedAuc
            .fceivalor(UPDATED_FCEIVALOR)
            .inativo(UPDATED_INATIVO)
            .prazomes(UPDATED_PRAZOMES)
            .reposicaoflorestal(UPDATED_REPOSICAOFLORESTAL)
            .compensacaoambiental(UPDATED_COMPENSACAOAMBIENTAL)
            .prazovalidade(UPDATED_PRAZOVALIDADE)
            .fcei(UPDATED_FCEI)
            .reciboentregadocs(UPDATED_RECIBOENTREGADOCS)
            .numero(UPDATED_NUMERO)
            .numeroprocesso(UPDATED_NUMEROPROCESSO)
            .numerooficiolocalpedido(UPDATED_NUMEROOFICIOLOCALPEDIDO)
            .numerooficiooficialpedido(UPDATED_NUMEROOFICIOOFICIALPEDIDO)
            .numerooficiolocalrecebimento(UPDATED_NUMEROOFICIOLOCALRECEBIMENTO)
            .numerooficiooficialrecebimento(UPDATED_NUMEROOFICIOOFICIALRECEBIMENTO)
            .numeroparecertecnico(UPDATED_NUMEROPARECERTECNICO)
            .numerooficioprorrogacao1(UPDATED_NUMEROOFICIOPRORROGACAO_1)
            .numerooficioconcessaoprorrogacao1(UPDATED_NUMEROOFICIOCONCESSAOPRORROGACAO_1)
            .numerooficioprorrogacao2(UPDATED_NUMEROOFICIOPRORROGACAO_2)
            .numerooficioconcessaoprorrogacao2(UPDATED_NUMEROOFICIOCONCESSAOPRORROGACAO_2)
            .numerooficioprorrogacao3(UPDATED_NUMEROOFICIOPRORROGACAO_3)
            .numerooficioconcessaoprorrogacao3(UPDATED_NUMEROOFICIOCONCESSAOPRORROGACAO_3)
            .album(UPDATED_ALBUM)
            .folder(UPDATED_FOLDER)
            .pendente(UPDATED_PENDENTE)
            .andamento(UPDATED_ANDAMENTO)
            .descricao(UPDATED_DESCRICAO)
            .observacao(UPDATED_OBSERVACAO)
            .docsentregues(UPDATED_DOCSENTREGUES)
            .reposicaoflorestalobs(UPDATED_REPOSICAOFLORESTALOBS)
            .compensacaoambientalobs(UPDATED_COMPENSACAOAMBIENTALOBS)
            .fceidataprotocolo(UPDATED_FCEIDATAPROTOCOLO)
            .fceidatapagamento(UPDATED_FCEIDATAPAGAMENTO)
            .dataentregadocs(UPDATED_DATAENTREGADOCS)
            .dataoficiolocalpedido(UPDATED_DATAOFICIOLOCALPEDIDO)
            .dataoficioreoficialpedido(UPDATED_DATAOFICIOREOFICIALPEDIDO)
            .dataoficiolocalrecebimento(UPDATED_DATAOFICIOLOCALRECEBIMENTO)
            .dataoficioreoficialrecebimento(UPDATED_DATAOFICIOREOFICIALRECEBIMENTO)
            .dataemissao(UPDATED_DATAEMISSAO)
            .datapedidoprorrogacao1(UPDATED_DATAPEDIDOPRORROGACAO_1)
            .dataexpedicaoprorrogacao1(UPDATED_DATAEXPEDICAOPRORROGACAO_1)
            .datavalidadeprorrogacao1(UPDATED_DATAVALIDADEPRORROGACAO_1)
            .datapedidoprorrogacao2(UPDATED_DATAPEDIDOPRORROGACAO_2)
            .dataexpedicaoprorrogacao2(UPDATED_DATAEXPEDICAOPRORROGACAO_2)
            .datavalidadeprorrogacao2(UPDATED_DATAVALIDADEPRORROGACAO_2)
            .datapedidoprorrogacao3(UPDATED_DATAPEDIDOPRORROGACAO_3)
            .dataexpedicaoprorrogacao3(UPDATED_DATAEXPEDICAOPRORROGACAO_3)
            .datavalidadeprorrogacao3(UPDATED_DATAVALIDADEPRORROGACAO_3)
            .datavencimento(UPDATED_DATAVENCIMENTO)
            .datavencimentoatual(UPDATED_DATAVENCIMENTOATUAL);
        AucDTO aucDTO = aucMapper.toDto(updatedAuc);

        restAucMockMvc.perform(put("/api/aucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aucDTO)))
            .andExpect(status().isOk());

        // Validate the Auc in the database
        List<Auc> aucList = aucRepository.findAll();
        assertThat(aucList).hasSize(databaseSizeBeforeUpdate);
        Auc testAuc = aucList.get(aucList.size() - 1);
        assertThat(testAuc.getFceivalor()).isEqualTo(UPDATED_FCEIVALOR);
        assertThat(testAuc.isInativo()).isEqualTo(UPDATED_INATIVO);
        assertThat(testAuc.isPrazomes()).isEqualTo(UPDATED_PRAZOMES);
        assertThat(testAuc.isReposicaoflorestal()).isEqualTo(UPDATED_REPOSICAOFLORESTAL);
        assertThat(testAuc.isCompensacaoambiental()).isEqualTo(UPDATED_COMPENSACAOAMBIENTAL);
        assertThat(testAuc.getPrazovalidade()).isEqualTo(UPDATED_PRAZOVALIDADE);
        assertThat(testAuc.getFcei()).isEqualTo(UPDATED_FCEI);
        assertThat(testAuc.getReciboentregadocs()).isEqualTo(UPDATED_RECIBOENTREGADOCS);
        assertThat(testAuc.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testAuc.getNumeroprocesso()).isEqualTo(UPDATED_NUMEROPROCESSO);
        assertThat(testAuc.getNumerooficiolocalpedido()).isEqualTo(UPDATED_NUMEROOFICIOLOCALPEDIDO);
        assertThat(testAuc.getNumerooficiooficialpedido()).isEqualTo(UPDATED_NUMEROOFICIOOFICIALPEDIDO);
        assertThat(testAuc.getNumerooficiolocalrecebimento()).isEqualTo(UPDATED_NUMEROOFICIOLOCALRECEBIMENTO);
        assertThat(testAuc.getNumerooficiooficialrecebimento()).isEqualTo(UPDATED_NUMEROOFICIOOFICIALRECEBIMENTO);
        assertThat(testAuc.getNumeroparecertecnico()).isEqualTo(UPDATED_NUMEROPARECERTECNICO);
        assertThat(testAuc.getNumerooficioprorrogacao1()).isEqualTo(UPDATED_NUMEROOFICIOPRORROGACAO_1);
        assertThat(testAuc.getNumerooficioconcessaoprorrogacao1()).isEqualTo(UPDATED_NUMEROOFICIOCONCESSAOPRORROGACAO_1);
        assertThat(testAuc.getNumerooficioprorrogacao2()).isEqualTo(UPDATED_NUMEROOFICIOPRORROGACAO_2);
        assertThat(testAuc.getNumerooficioconcessaoprorrogacao2()).isEqualTo(UPDATED_NUMEROOFICIOCONCESSAOPRORROGACAO_2);
        assertThat(testAuc.getNumerooficioprorrogacao3()).isEqualTo(UPDATED_NUMEROOFICIOPRORROGACAO_3);
        assertThat(testAuc.getNumerooficioconcessaoprorrogacao3()).isEqualTo(UPDATED_NUMEROOFICIOCONCESSAOPRORROGACAO_3);
        assertThat(testAuc.getAlbum()).isEqualTo(UPDATED_ALBUM);
        assertThat(testAuc.getFolder()).isEqualTo(UPDATED_FOLDER);
        assertThat(testAuc.getPendente()).isEqualTo(UPDATED_PENDENTE);
        assertThat(testAuc.getAndamento()).isEqualTo(UPDATED_ANDAMENTO);
        assertThat(testAuc.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testAuc.getObservacao()).isEqualTo(UPDATED_OBSERVACAO);
        assertThat(testAuc.getDocsentregues()).isEqualTo(UPDATED_DOCSENTREGUES);
        assertThat(testAuc.getReposicaoflorestalobs()).isEqualTo(UPDATED_REPOSICAOFLORESTALOBS);
        assertThat(testAuc.getCompensacaoambientalobs()).isEqualTo(UPDATED_COMPENSACAOAMBIENTALOBS);
        assertThat(testAuc.getFceidataprotocolo()).isEqualTo(UPDATED_FCEIDATAPROTOCOLO);
        assertThat(testAuc.getFceidatapagamento()).isEqualTo(UPDATED_FCEIDATAPAGAMENTO);
        assertThat(testAuc.getDataentregadocs()).isEqualTo(UPDATED_DATAENTREGADOCS);
        assertThat(testAuc.getDataoficiolocalpedido()).isEqualTo(UPDATED_DATAOFICIOLOCALPEDIDO);
        assertThat(testAuc.getDataoficioreoficialpedido()).isEqualTo(UPDATED_DATAOFICIOREOFICIALPEDIDO);
        assertThat(testAuc.getDataoficiolocalrecebimento()).isEqualTo(UPDATED_DATAOFICIOLOCALRECEBIMENTO);
        assertThat(testAuc.getDataoficioreoficialrecebimento()).isEqualTo(UPDATED_DATAOFICIOREOFICIALRECEBIMENTO);
        assertThat(testAuc.getDataemissao()).isEqualTo(UPDATED_DATAEMISSAO);
        assertThat(testAuc.getDatapedidoprorrogacao1()).isEqualTo(UPDATED_DATAPEDIDOPRORROGACAO_1);
        assertThat(testAuc.getDataexpedicaoprorrogacao1()).isEqualTo(UPDATED_DATAEXPEDICAOPRORROGACAO_1);
        assertThat(testAuc.getDatavalidadeprorrogacao1()).isEqualTo(UPDATED_DATAVALIDADEPRORROGACAO_1);
        assertThat(testAuc.getDatapedidoprorrogacao2()).isEqualTo(UPDATED_DATAPEDIDOPRORROGACAO_2);
        assertThat(testAuc.getDataexpedicaoprorrogacao2()).isEqualTo(UPDATED_DATAEXPEDICAOPRORROGACAO_2);
        assertThat(testAuc.getDatavalidadeprorrogacao2()).isEqualTo(UPDATED_DATAVALIDADEPRORROGACAO_2);
        assertThat(testAuc.getDatapedidoprorrogacao3()).isEqualTo(UPDATED_DATAPEDIDOPRORROGACAO_3);
        assertThat(testAuc.getDataexpedicaoprorrogacao3()).isEqualTo(UPDATED_DATAEXPEDICAOPRORROGACAO_3);
        assertThat(testAuc.getDatavalidadeprorrogacao3()).isEqualTo(UPDATED_DATAVALIDADEPRORROGACAO_3);
        assertThat(testAuc.getDatavencimento()).isEqualTo(UPDATED_DATAVENCIMENTO);
        assertThat(testAuc.getDatavencimentoatual()).isEqualTo(UPDATED_DATAVENCIMENTOATUAL);
    }

    @Test
    @Transactional
    public void updateNonExistingAuc() throws Exception {
        int databaseSizeBeforeUpdate = aucRepository.findAll().size();

        // Create the Auc
        AucDTO aucDTO = aucMapper.toDto(auc);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAucMockMvc.perform(put("/api/aucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aucDTO)))
            .andExpect(status().isCreated());

        // Validate the Auc in the database
        List<Auc> aucList = aucRepository.findAll();
        assertThat(aucList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAuc() throws Exception {
        // Initialize the database
        aucRepository.saveAndFlush(auc);
        int databaseSizeBeforeDelete = aucRepository.findAll().size();

        // Get the auc
        restAucMockMvc.perform(delete("/api/aucs/{id}", auc.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Auc> aucList = aucRepository.findAll();
        assertThat(aucList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Auc.class);
        Auc auc1 = new Auc();
        auc1.setId(1L);
        Auc auc2 = new Auc();
        auc2.setId(auc1.getId());
        assertThat(auc1).isEqualTo(auc2);
        auc2.setId(2L);
        assertThat(auc1).isNotEqualTo(auc2);
        auc1.setId(null);
        assertThat(auc1).isNotEqualTo(auc2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AucDTO.class);
        AucDTO aucDTO1 = new AucDTO();
        aucDTO1.setId(1L);
        AucDTO aucDTO2 = new AucDTO();
        assertThat(aucDTO1).isNotEqualTo(aucDTO2);
        aucDTO2.setId(aucDTO1.getId());
        assertThat(aucDTO1).isEqualTo(aucDTO2);
        aucDTO2.setId(2L);
        assertThat(aucDTO1).isNotEqualTo(aucDTO2);
        aucDTO1.setId(null);
        assertThat(aucDTO1).isNotEqualTo(aucDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(aucMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(aucMapper.fromId(null)).isNull();
    }
}
