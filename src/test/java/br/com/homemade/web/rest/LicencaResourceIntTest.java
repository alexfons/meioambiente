package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Licenca;
import br.com.homemade.repository.LicencaRepository;
import br.com.homemade.service.dto.LicencaDTO;
import br.com.homemade.service.mapper.LicencaMapper;
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
 * Test class for the LicencaResource REST controller.
 *
 * @see LicencaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class LicencaResourceIntTest {

    private static final String DEFAULT_ALBUM = "AAAAAAAAAA";
    private static final String UPDATED_ALBUM = "BBBBBBBBBB";

    private static final String DEFAULT_ANDAMENTO = "AAAAAAAAAA";
    private static final String UPDATED_ANDAMENTO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_COMPENSACAOAMBIENTAL = false;
    private static final Boolean UPDATED_COMPENSACAOAMBIENTAL = true;

    private static final ZonedDateTime DEFAULT_DATAEMISSAO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAEMISSAO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAENTREGADOCS = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAENTREGADOCS = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAEXPEDICAOPRORROGACAO_1 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAEXPEDICAOPRORROGACAO_1 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAEXPEDICAOPRORROGACAO_2 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAEXPEDICAOPRORROGACAO_2 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAEXPEDICAOPRORROGACAO_3 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAEXPEDICAOPRORROGACAO_3 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAOFICIOLOCALPEDIDO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAOFICIOLOCALPEDIDO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAOFICIOLOCALRECEBIMENTO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAOFICIOLOCALRECEBIMENTO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAOFICIOREOFICIALPEDIDO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAOFICIOREOFICIALPEDIDO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAOFICIOREOFICIALRECEBIMENTO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAOFICIOREOFICIALRECEBIMENTO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

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

    private static final Boolean DEFAULT_DISPENSALAI = false;
    private static final Boolean UPDATED_DISPENSALAI = true;

    private static final String DEFAULT_DOCSENTREGUES = "AAAAAAAAAA";
    private static final String UPDATED_DOCSENTREGUES = "BBBBBBBBBB";

    private static final Boolean DEFAULT_EIARIMA = false;
    private static final Boolean UPDATED_EIARIMA = true;

    private static final String DEFAULT_FCEI = "AAAAAAAAAA";
    private static final String UPDATED_FCEI = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_FCEIDATAPAGAMENTO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FCEIDATAPAGAMENTO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_FCEIDATAPROTOCOLO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FCEIDATAPROTOCOLO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final BigDecimal DEFAULT_FCEIVALOR = new BigDecimal(1);
    private static final BigDecimal UPDATED_FCEIVALOR = new BigDecimal(2);

    private static final String DEFAULT_FOLDER = "AAAAAAAAAA";
    private static final String UPDATED_FOLDER = "BBBBBBBBBB";

    private static final Boolean DEFAULT_INATIVO = false;
    private static final Boolean UPDATED_INATIVO = true;

    private static final Boolean DEFAULT_INVENTARIOFLORESTAL = false;
    private static final Boolean UPDATED_INVENTARIOFLORESTAL = true;

    private static final String DEFAULT_NUMERO = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO = "BBBBBBBBBB";

    private static final String DEFAULT_NUMEROLAP = "AAAAAAAAAA";
    private static final String UPDATED_NUMEROLAP = "BBBBBBBBBB";

    private static final String DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_1 = "AAAAAAAAAA";
    private static final String UPDATED_NUMEROOFICIOCONCESSAOPRORROGACAO_1 = "BBBBBBBBBB";

    private static final String DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_2 = "AAAAAAAAAA";
    private static final String UPDATED_NUMEROOFICIOCONCESSAOPRORROGACAO_2 = "BBBBBBBBBB";

    private static final String DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_3 = "AAAAAAAAAA";
    private static final String UPDATED_NUMEROOFICIOCONCESSAOPRORROGACAO_3 = "BBBBBBBBBB";

    private static final String DEFAULT_NUMEROOFICIOLOCALPEDIDO = "AAAAAAAAAA";
    private static final String UPDATED_NUMEROOFICIOLOCALPEDIDO = "BBBBBBBBBB";

    private static final String DEFAULT_NUMEROOFICIOLOCALRECEBIMENTO = "AAAAAAAAAA";
    private static final String UPDATED_NUMEROOFICIOLOCALRECEBIMENTO = "BBBBBBBBBB";

    private static final String DEFAULT_NUMEROOFICIOOFICIALPEDIDO = "AAAAAAAAAA";
    private static final String UPDATED_NUMEROOFICIOOFICIALPEDIDO = "BBBBBBBBBB";

    private static final String DEFAULT_NUMEROOFICIOOFICIALRECEBIMENTO = "AAAAAAAAAA";
    private static final String UPDATED_NUMEROOFICIOOFICIALRECEBIMENTO = "BBBBBBBBBB";

    private static final String DEFAULT_NUMEROOFICIOPRORROGACAO_1 = "AAAAAAAAAA";
    private static final String UPDATED_NUMEROOFICIOPRORROGACAO_1 = "BBBBBBBBBB";

    private static final String DEFAULT_NUMEROOFICIOPRORROGACAO_2 = "AAAAAAAAAA";
    private static final String UPDATED_NUMEROOFICIOPRORROGACAO_2 = "BBBBBBBBBB";

    private static final String DEFAULT_NUMEROOFICIOPRORROGACAO_3 = "AAAAAAAAAA";
    private static final String UPDATED_NUMEROOFICIOPRORROGACAO_3 = "BBBBBBBBBB";

    private static final String DEFAULT_NUMEROPARECERTECNICO = "AAAAAAAAAA";
    private static final String UPDATED_NUMEROPARECERTECNICO = "BBBBBBBBBB";

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

    private static final String DEFAULT_RECIBOENTREGADOCS = "AAAAAAAAAA";
    private static final String UPDATED_RECIBOENTREGADOCS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_USOSOLO = false;
    private static final Boolean UPDATED_USOSOLO = true;

    private static final String DEFAULT_USOSOLOOBS = "AAAAAAAAAA";
    private static final String UPDATED_USOSOLOOBS = "BBBBBBBBBB";

    @Autowired
    private LicencaRepository licencaRepository;

    @Autowired
    private LicencaMapper licencaMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLicencaMockMvc;

    private Licenca licenca;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        LicencaResource licencaResource = new LicencaResource(licencaRepository, licencaMapper);
        this.restLicencaMockMvc = MockMvcBuilders.standaloneSetup(licencaResource)
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
    public static Licenca createEntity(EntityManager em) {
        Licenca licenca = new Licenca()
            .album(DEFAULT_ALBUM)
            .andamento(DEFAULT_ANDAMENTO)
            .compensacaoambiental(DEFAULT_COMPENSACAOAMBIENTAL)
            .dataemissao(DEFAULT_DATAEMISSAO)
            .dataentregadocs(DEFAULT_DATAENTREGADOCS)
            .dataexpedicaoprorrogacao1(DEFAULT_DATAEXPEDICAOPRORROGACAO_1)
            .dataexpedicaoprorrogacao2(DEFAULT_DATAEXPEDICAOPRORROGACAO_2)
            .dataexpedicaoprorrogacao3(DEFAULT_DATAEXPEDICAOPRORROGACAO_3)
            .dataoficiolocalpedido(DEFAULT_DATAOFICIOLOCALPEDIDO)
            .dataoficiolocalrecebimento(DEFAULT_DATAOFICIOLOCALRECEBIMENTO)
            .dataoficioreoficialpedido(DEFAULT_DATAOFICIOREOFICIALPEDIDO)
            .dataoficioreoficialrecebimento(DEFAULT_DATAOFICIOREOFICIALRECEBIMENTO)
            .datapedidoprorrogacao1(DEFAULT_DATAPEDIDOPRORROGACAO_1)
            .datapedidoprorrogacao2(DEFAULT_DATAPEDIDOPRORROGACAO_2)
            .datapedidoprorrogacao3(DEFAULT_DATAPEDIDOPRORROGACAO_3)
            .datavalidadeprorrogacao1(DEFAULT_DATAVALIDADEPRORROGACAO_1)
            .datavalidadeprorrogacao2(DEFAULT_DATAVALIDADEPRORROGACAO_2)
            .datavalidadeprorrogacao3(DEFAULT_DATAVALIDADEPRORROGACAO_3)
            .datavencimento(DEFAULT_DATAVENCIMENTO)
            .datavencimentoatual(DEFAULT_DATAVENCIMENTOATUAL)
            .descricao(DEFAULT_DESCRICAO)
            .dispensalai(DEFAULT_DISPENSALAI)
            .docsentregues(DEFAULT_DOCSENTREGUES)
            .eiarima(DEFAULT_EIARIMA)
            .fcei(DEFAULT_FCEI)
            .fceidatapagamento(DEFAULT_FCEIDATAPAGAMENTO)
            .fceidataprotocolo(DEFAULT_FCEIDATAPROTOCOLO)
            .fceivalor(DEFAULT_FCEIVALOR)
            .folder(DEFAULT_FOLDER)
            .inativo(DEFAULT_INATIVO)
            .inventarioflorestal(DEFAULT_INVENTARIOFLORESTAL)
            .numero(DEFAULT_NUMERO)
            .numerolap(DEFAULT_NUMEROLAP)
            .numerooficioconcessaoprorrogacao1(DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_1)
            .numerooficioconcessaoprorrogacao2(DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_2)
            .numerooficioconcessaoprorrogacao3(DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_3)
            .numerooficiolocalpedido(DEFAULT_NUMEROOFICIOLOCALPEDIDO)
            .numerooficiolocalrecebimento(DEFAULT_NUMEROOFICIOLOCALRECEBIMENTO)
            .numerooficiooficialpedido(DEFAULT_NUMEROOFICIOOFICIALPEDIDO)
            .numerooficiooficialrecebimento(DEFAULT_NUMEROOFICIOOFICIALRECEBIMENTO)
            .numerooficioprorrogacao1(DEFAULT_NUMEROOFICIOPRORROGACAO_1)
            .numerooficioprorrogacao2(DEFAULT_NUMEROOFICIOPRORROGACAO_2)
            .numerooficioprorrogacao3(DEFAULT_NUMEROOFICIOPRORROGACAO_3)
            .numeroparecertecnico(DEFAULT_NUMEROPARECERTECNICO)
            .numeroprocesso(DEFAULT_NUMEROPROCESSO)
            .observacao(DEFAULT_OBSERVACAO)
            .pendente(DEFAULT_PENDENTE)
            .prazomes(DEFAULT_PRAZOMES)
            .prazovalidade(DEFAULT_PRAZOVALIDADE)
            .reciboentregadocs(DEFAULT_RECIBOENTREGADOCS)
            .usosolo(DEFAULT_USOSOLO)
            .usosoloobs(DEFAULT_USOSOLOOBS);
        return licenca;
    }

    @Before
    public void initTest() {
        licenca = createEntity(em);
    }

    @Test
    @Transactional
    public void createLicenca() throws Exception {
        int databaseSizeBeforeCreate = licencaRepository.findAll().size();

        // Create the Licenca
        LicencaDTO licencaDTO = licencaMapper.toDto(licenca);
        restLicencaMockMvc.perform(post("/api/licencas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(licencaDTO)))
            .andExpect(status().isCreated());

        // Validate the Licenca in the database
        List<Licenca> licencaList = licencaRepository.findAll();
        assertThat(licencaList).hasSize(databaseSizeBeforeCreate + 1);
        Licenca testLicenca = licencaList.get(licencaList.size() - 1);
        assertThat(testLicenca.getAlbum()).isEqualTo(DEFAULT_ALBUM);
        assertThat(testLicenca.getAndamento()).isEqualTo(DEFAULT_ANDAMENTO);
        assertThat(testLicenca.isCompensacaoambiental()).isEqualTo(DEFAULT_COMPENSACAOAMBIENTAL);
        assertThat(testLicenca.getDataemissao()).isEqualTo(DEFAULT_DATAEMISSAO);
        assertThat(testLicenca.getDataentregadocs()).isEqualTo(DEFAULT_DATAENTREGADOCS);
        assertThat(testLicenca.getDataexpedicaoprorrogacao1()).isEqualTo(DEFAULT_DATAEXPEDICAOPRORROGACAO_1);
        assertThat(testLicenca.getDataexpedicaoprorrogacao2()).isEqualTo(DEFAULT_DATAEXPEDICAOPRORROGACAO_2);
        assertThat(testLicenca.getDataexpedicaoprorrogacao3()).isEqualTo(DEFAULT_DATAEXPEDICAOPRORROGACAO_3);
        assertThat(testLicenca.getDataoficiolocalpedido()).isEqualTo(DEFAULT_DATAOFICIOLOCALPEDIDO);
        assertThat(testLicenca.getDataoficiolocalrecebimento()).isEqualTo(DEFAULT_DATAOFICIOLOCALRECEBIMENTO);
        assertThat(testLicenca.getDataoficioreoficialpedido()).isEqualTo(DEFAULT_DATAOFICIOREOFICIALPEDIDO);
        assertThat(testLicenca.getDataoficioreoficialrecebimento()).isEqualTo(DEFAULT_DATAOFICIOREOFICIALRECEBIMENTO);
        assertThat(testLicenca.getDatapedidoprorrogacao1()).isEqualTo(DEFAULT_DATAPEDIDOPRORROGACAO_1);
        assertThat(testLicenca.getDatapedidoprorrogacao2()).isEqualTo(DEFAULT_DATAPEDIDOPRORROGACAO_2);
        assertThat(testLicenca.getDatapedidoprorrogacao3()).isEqualTo(DEFAULT_DATAPEDIDOPRORROGACAO_3);
        assertThat(testLicenca.getDatavalidadeprorrogacao1()).isEqualTo(DEFAULT_DATAVALIDADEPRORROGACAO_1);
        assertThat(testLicenca.getDatavalidadeprorrogacao2()).isEqualTo(DEFAULT_DATAVALIDADEPRORROGACAO_2);
        assertThat(testLicenca.getDatavalidadeprorrogacao3()).isEqualTo(DEFAULT_DATAVALIDADEPRORROGACAO_3);
        assertThat(testLicenca.getDatavencimento()).isEqualTo(DEFAULT_DATAVENCIMENTO);
        assertThat(testLicenca.getDatavencimentoatual()).isEqualTo(DEFAULT_DATAVENCIMENTOATUAL);
        assertThat(testLicenca.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testLicenca.isDispensalai()).isEqualTo(DEFAULT_DISPENSALAI);
        assertThat(testLicenca.getDocsentregues()).isEqualTo(DEFAULT_DOCSENTREGUES);
        assertThat(testLicenca.isEiarima()).isEqualTo(DEFAULT_EIARIMA);
        assertThat(testLicenca.getFcei()).isEqualTo(DEFAULT_FCEI);
        assertThat(testLicenca.getFceidatapagamento()).isEqualTo(DEFAULT_FCEIDATAPAGAMENTO);
        assertThat(testLicenca.getFceidataprotocolo()).isEqualTo(DEFAULT_FCEIDATAPROTOCOLO);
        assertThat(testLicenca.getFceivalor()).isEqualTo(DEFAULT_FCEIVALOR);
        assertThat(testLicenca.getFolder()).isEqualTo(DEFAULT_FOLDER);
        assertThat(testLicenca.isInativo()).isEqualTo(DEFAULT_INATIVO);
        assertThat(testLicenca.isInventarioflorestal()).isEqualTo(DEFAULT_INVENTARIOFLORESTAL);
        assertThat(testLicenca.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testLicenca.getNumerolap()).isEqualTo(DEFAULT_NUMEROLAP);
        assertThat(testLicenca.getNumerooficioconcessaoprorrogacao1()).isEqualTo(DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_1);
        assertThat(testLicenca.getNumerooficioconcessaoprorrogacao2()).isEqualTo(DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_2);
        assertThat(testLicenca.getNumerooficioconcessaoprorrogacao3()).isEqualTo(DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_3);
        assertThat(testLicenca.getNumerooficiolocalpedido()).isEqualTo(DEFAULT_NUMEROOFICIOLOCALPEDIDO);
        assertThat(testLicenca.getNumerooficiolocalrecebimento()).isEqualTo(DEFAULT_NUMEROOFICIOLOCALRECEBIMENTO);
        assertThat(testLicenca.getNumerooficiooficialpedido()).isEqualTo(DEFAULT_NUMEROOFICIOOFICIALPEDIDO);
        assertThat(testLicenca.getNumerooficiooficialrecebimento()).isEqualTo(DEFAULT_NUMEROOFICIOOFICIALRECEBIMENTO);
        assertThat(testLicenca.getNumerooficioprorrogacao1()).isEqualTo(DEFAULT_NUMEROOFICIOPRORROGACAO_1);
        assertThat(testLicenca.getNumerooficioprorrogacao2()).isEqualTo(DEFAULT_NUMEROOFICIOPRORROGACAO_2);
        assertThat(testLicenca.getNumerooficioprorrogacao3()).isEqualTo(DEFAULT_NUMEROOFICIOPRORROGACAO_3);
        assertThat(testLicenca.getNumeroparecertecnico()).isEqualTo(DEFAULT_NUMEROPARECERTECNICO);
        assertThat(testLicenca.getNumeroprocesso()).isEqualTo(DEFAULT_NUMEROPROCESSO);
        assertThat(testLicenca.getObservacao()).isEqualTo(DEFAULT_OBSERVACAO);
        assertThat(testLicenca.getPendente()).isEqualTo(DEFAULT_PENDENTE);
        assertThat(testLicenca.isPrazomes()).isEqualTo(DEFAULT_PRAZOMES);
        assertThat(testLicenca.getPrazovalidade()).isEqualTo(DEFAULT_PRAZOVALIDADE);
        assertThat(testLicenca.getReciboentregadocs()).isEqualTo(DEFAULT_RECIBOENTREGADOCS);
        assertThat(testLicenca.isUsosolo()).isEqualTo(DEFAULT_USOSOLO);
        assertThat(testLicenca.getUsosoloobs()).isEqualTo(DEFAULT_USOSOLOOBS);
    }

    @Test
    @Transactional
    public void createLicencaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = licencaRepository.findAll().size();

        // Create the Licenca with an existing ID
        licenca.setId(1L);
        LicencaDTO licencaDTO = licencaMapper.toDto(licenca);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLicencaMockMvc.perform(post("/api/licencas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(licencaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Licenca> licencaList = licencaRepository.findAll();
        assertThat(licencaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllLicencas() throws Exception {
        // Initialize the database
        licencaRepository.saveAndFlush(licenca);

        // Get all the licencaList
        restLicencaMockMvc.perform(get("/api/licencas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(licenca.getId().intValue())))
            .andExpect(jsonPath("$.[*].album").value(hasItem(DEFAULT_ALBUM.toString())))
            .andExpect(jsonPath("$.[*].andamento").value(hasItem(DEFAULT_ANDAMENTO.toString())))
            .andExpect(jsonPath("$.[*].compensacaoambiental").value(hasItem(DEFAULT_COMPENSACAOAMBIENTAL.booleanValue())))
            .andExpect(jsonPath("$.[*].dataemissao").value(hasItem(sameInstant(DEFAULT_DATAEMISSAO))))
            .andExpect(jsonPath("$.[*].dataentregadocs").value(hasItem(sameInstant(DEFAULT_DATAENTREGADOCS))))
            .andExpect(jsonPath("$.[*].dataexpedicaoprorrogacao1").value(hasItem(sameInstant(DEFAULT_DATAEXPEDICAOPRORROGACAO_1))))
            .andExpect(jsonPath("$.[*].dataexpedicaoprorrogacao2").value(hasItem(sameInstant(DEFAULT_DATAEXPEDICAOPRORROGACAO_2))))
            .andExpect(jsonPath("$.[*].dataexpedicaoprorrogacao3").value(hasItem(sameInstant(DEFAULT_DATAEXPEDICAOPRORROGACAO_3))))
            .andExpect(jsonPath("$.[*].dataoficiolocalpedido").value(hasItem(sameInstant(DEFAULT_DATAOFICIOLOCALPEDIDO))))
            .andExpect(jsonPath("$.[*].dataoficiolocalrecebimento").value(hasItem(sameInstant(DEFAULT_DATAOFICIOLOCALRECEBIMENTO))))
            .andExpect(jsonPath("$.[*].dataoficioreoficialpedido").value(hasItem(sameInstant(DEFAULT_DATAOFICIOREOFICIALPEDIDO))))
            .andExpect(jsonPath("$.[*].dataoficioreoficialrecebimento").value(hasItem(sameInstant(DEFAULT_DATAOFICIOREOFICIALRECEBIMENTO))))
            .andExpect(jsonPath("$.[*].datapedidoprorrogacao1").value(hasItem(sameInstant(DEFAULT_DATAPEDIDOPRORROGACAO_1))))
            .andExpect(jsonPath("$.[*].datapedidoprorrogacao2").value(hasItem(sameInstant(DEFAULT_DATAPEDIDOPRORROGACAO_2))))
            .andExpect(jsonPath("$.[*].datapedidoprorrogacao3").value(hasItem(sameInstant(DEFAULT_DATAPEDIDOPRORROGACAO_3))))
            .andExpect(jsonPath("$.[*].datavalidadeprorrogacao1").value(hasItem(sameInstant(DEFAULT_DATAVALIDADEPRORROGACAO_1))))
            .andExpect(jsonPath("$.[*].datavalidadeprorrogacao2").value(hasItem(sameInstant(DEFAULT_DATAVALIDADEPRORROGACAO_2))))
            .andExpect(jsonPath("$.[*].datavalidadeprorrogacao3").value(hasItem(sameInstant(DEFAULT_DATAVALIDADEPRORROGACAO_3))))
            .andExpect(jsonPath("$.[*].datavencimento").value(hasItem(sameInstant(DEFAULT_DATAVENCIMENTO))))
            .andExpect(jsonPath("$.[*].datavencimentoatual").value(hasItem(sameInstant(DEFAULT_DATAVENCIMENTOATUAL))))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].dispensalai").value(hasItem(DEFAULT_DISPENSALAI.booleanValue())))
            .andExpect(jsonPath("$.[*].docsentregues").value(hasItem(DEFAULT_DOCSENTREGUES.toString())))
            .andExpect(jsonPath("$.[*].eiarima").value(hasItem(DEFAULT_EIARIMA.booleanValue())))
            .andExpect(jsonPath("$.[*].fcei").value(hasItem(DEFAULT_FCEI.toString())))
            .andExpect(jsonPath("$.[*].fceidatapagamento").value(hasItem(sameInstant(DEFAULT_FCEIDATAPAGAMENTO))))
            .andExpect(jsonPath("$.[*].fceidataprotocolo").value(hasItem(sameInstant(DEFAULT_FCEIDATAPROTOCOLO))))
            .andExpect(jsonPath("$.[*].fceivalor").value(hasItem(DEFAULT_FCEIVALOR.intValue())))
            .andExpect(jsonPath("$.[*].folder").value(hasItem(DEFAULT_FOLDER.toString())))
            .andExpect(jsonPath("$.[*].inativo").value(hasItem(DEFAULT_INATIVO.booleanValue())))
            .andExpect(jsonPath("$.[*].inventarioflorestal").value(hasItem(DEFAULT_INVENTARIOFLORESTAL.booleanValue())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO.toString())))
            .andExpect(jsonPath("$.[*].numerolap").value(hasItem(DEFAULT_NUMEROLAP.toString())))
            .andExpect(jsonPath("$.[*].numerooficioconcessaoprorrogacao1").value(hasItem(DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_1.toString())))
            .andExpect(jsonPath("$.[*].numerooficioconcessaoprorrogacao2").value(hasItem(DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_2.toString())))
            .andExpect(jsonPath("$.[*].numerooficioconcessaoprorrogacao3").value(hasItem(DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_3.toString())))
            .andExpect(jsonPath("$.[*].numerooficiolocalpedido").value(hasItem(DEFAULT_NUMEROOFICIOLOCALPEDIDO.toString())))
            .andExpect(jsonPath("$.[*].numerooficiolocalrecebimento").value(hasItem(DEFAULT_NUMEROOFICIOLOCALRECEBIMENTO.toString())))
            .andExpect(jsonPath("$.[*].numerooficiooficialpedido").value(hasItem(DEFAULT_NUMEROOFICIOOFICIALPEDIDO.toString())))
            .andExpect(jsonPath("$.[*].numerooficiooficialrecebimento").value(hasItem(DEFAULT_NUMEROOFICIOOFICIALRECEBIMENTO.toString())))
            .andExpect(jsonPath("$.[*].numerooficioprorrogacao1").value(hasItem(DEFAULT_NUMEROOFICIOPRORROGACAO_1.toString())))
            .andExpect(jsonPath("$.[*].numerooficioprorrogacao2").value(hasItem(DEFAULT_NUMEROOFICIOPRORROGACAO_2.toString())))
            .andExpect(jsonPath("$.[*].numerooficioprorrogacao3").value(hasItem(DEFAULT_NUMEROOFICIOPRORROGACAO_3.toString())))
            .andExpect(jsonPath("$.[*].numeroparecertecnico").value(hasItem(DEFAULT_NUMEROPARECERTECNICO.toString())))
            .andExpect(jsonPath("$.[*].numeroprocesso").value(hasItem(DEFAULT_NUMEROPROCESSO.toString())))
            .andExpect(jsonPath("$.[*].observacao").value(hasItem(DEFAULT_OBSERVACAO.toString())))
            .andExpect(jsonPath("$.[*].pendente").value(hasItem(DEFAULT_PENDENTE.toString())))
            .andExpect(jsonPath("$.[*].prazomes").value(hasItem(DEFAULT_PRAZOMES.booleanValue())))
            .andExpect(jsonPath("$.[*].prazovalidade").value(hasItem(DEFAULT_PRAZOVALIDADE)))
            .andExpect(jsonPath("$.[*].reciboentregadocs").value(hasItem(DEFAULT_RECIBOENTREGADOCS.toString())))
            .andExpect(jsonPath("$.[*].usosolo").value(hasItem(DEFAULT_USOSOLO.booleanValue())))
            .andExpect(jsonPath("$.[*].usosoloobs").value(hasItem(DEFAULT_USOSOLOOBS.toString())));
    }

    @Test
    @Transactional
    public void getLicenca() throws Exception {
        // Initialize the database
        licencaRepository.saveAndFlush(licenca);

        // Get the licenca
        restLicencaMockMvc.perform(get("/api/licencas/{id}", licenca.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(licenca.getId().intValue()))
            .andExpect(jsonPath("$.album").value(DEFAULT_ALBUM.toString()))
            .andExpect(jsonPath("$.andamento").value(DEFAULT_ANDAMENTO.toString()))
            .andExpect(jsonPath("$.compensacaoambiental").value(DEFAULT_COMPENSACAOAMBIENTAL.booleanValue()))
            .andExpect(jsonPath("$.dataemissao").value(sameInstant(DEFAULT_DATAEMISSAO)))
            .andExpect(jsonPath("$.dataentregadocs").value(sameInstant(DEFAULT_DATAENTREGADOCS)))
            .andExpect(jsonPath("$.dataexpedicaoprorrogacao1").value(sameInstant(DEFAULT_DATAEXPEDICAOPRORROGACAO_1)))
            .andExpect(jsonPath("$.dataexpedicaoprorrogacao2").value(sameInstant(DEFAULT_DATAEXPEDICAOPRORROGACAO_2)))
            .andExpect(jsonPath("$.dataexpedicaoprorrogacao3").value(sameInstant(DEFAULT_DATAEXPEDICAOPRORROGACAO_3)))
            .andExpect(jsonPath("$.dataoficiolocalpedido").value(sameInstant(DEFAULT_DATAOFICIOLOCALPEDIDO)))
            .andExpect(jsonPath("$.dataoficiolocalrecebimento").value(sameInstant(DEFAULT_DATAOFICIOLOCALRECEBIMENTO)))
            .andExpect(jsonPath("$.dataoficioreoficialpedido").value(sameInstant(DEFAULT_DATAOFICIOREOFICIALPEDIDO)))
            .andExpect(jsonPath("$.dataoficioreoficialrecebimento").value(sameInstant(DEFAULT_DATAOFICIOREOFICIALRECEBIMENTO)))
            .andExpect(jsonPath("$.datapedidoprorrogacao1").value(sameInstant(DEFAULT_DATAPEDIDOPRORROGACAO_1)))
            .andExpect(jsonPath("$.datapedidoprorrogacao2").value(sameInstant(DEFAULT_DATAPEDIDOPRORROGACAO_2)))
            .andExpect(jsonPath("$.datapedidoprorrogacao3").value(sameInstant(DEFAULT_DATAPEDIDOPRORROGACAO_3)))
            .andExpect(jsonPath("$.datavalidadeprorrogacao1").value(sameInstant(DEFAULT_DATAVALIDADEPRORROGACAO_1)))
            .andExpect(jsonPath("$.datavalidadeprorrogacao2").value(sameInstant(DEFAULT_DATAVALIDADEPRORROGACAO_2)))
            .andExpect(jsonPath("$.datavalidadeprorrogacao3").value(sameInstant(DEFAULT_DATAVALIDADEPRORROGACAO_3)))
            .andExpect(jsonPath("$.datavencimento").value(sameInstant(DEFAULT_DATAVENCIMENTO)))
            .andExpect(jsonPath("$.datavencimentoatual").value(sameInstant(DEFAULT_DATAVENCIMENTOATUAL)))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.dispensalai").value(DEFAULT_DISPENSALAI.booleanValue()))
            .andExpect(jsonPath("$.docsentregues").value(DEFAULT_DOCSENTREGUES.toString()))
            .andExpect(jsonPath("$.eiarima").value(DEFAULT_EIARIMA.booleanValue()))
            .andExpect(jsonPath("$.fcei").value(DEFAULT_FCEI.toString()))
            .andExpect(jsonPath("$.fceidatapagamento").value(sameInstant(DEFAULT_FCEIDATAPAGAMENTO)))
            .andExpect(jsonPath("$.fceidataprotocolo").value(sameInstant(DEFAULT_FCEIDATAPROTOCOLO)))
            .andExpect(jsonPath("$.fceivalor").value(DEFAULT_FCEIVALOR.intValue()))
            .andExpect(jsonPath("$.folder").value(DEFAULT_FOLDER.toString()))
            .andExpect(jsonPath("$.inativo").value(DEFAULT_INATIVO.booleanValue()))
            .andExpect(jsonPath("$.inventarioflorestal").value(DEFAULT_INVENTARIOFLORESTAL.booleanValue()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO.toString()))
            .andExpect(jsonPath("$.numerolap").value(DEFAULT_NUMEROLAP.toString()))
            .andExpect(jsonPath("$.numerooficioconcessaoprorrogacao1").value(DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_1.toString()))
            .andExpect(jsonPath("$.numerooficioconcessaoprorrogacao2").value(DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_2.toString()))
            .andExpect(jsonPath("$.numerooficioconcessaoprorrogacao3").value(DEFAULT_NUMEROOFICIOCONCESSAOPRORROGACAO_3.toString()))
            .andExpect(jsonPath("$.numerooficiolocalpedido").value(DEFAULT_NUMEROOFICIOLOCALPEDIDO.toString()))
            .andExpect(jsonPath("$.numerooficiolocalrecebimento").value(DEFAULT_NUMEROOFICIOLOCALRECEBIMENTO.toString()))
            .andExpect(jsonPath("$.numerooficiooficialpedido").value(DEFAULT_NUMEROOFICIOOFICIALPEDIDO.toString()))
            .andExpect(jsonPath("$.numerooficiooficialrecebimento").value(DEFAULT_NUMEROOFICIOOFICIALRECEBIMENTO.toString()))
            .andExpect(jsonPath("$.numerooficioprorrogacao1").value(DEFAULT_NUMEROOFICIOPRORROGACAO_1.toString()))
            .andExpect(jsonPath("$.numerooficioprorrogacao2").value(DEFAULT_NUMEROOFICIOPRORROGACAO_2.toString()))
            .andExpect(jsonPath("$.numerooficioprorrogacao3").value(DEFAULT_NUMEROOFICIOPRORROGACAO_3.toString()))
            .andExpect(jsonPath("$.numeroparecertecnico").value(DEFAULT_NUMEROPARECERTECNICO.toString()))
            .andExpect(jsonPath("$.numeroprocesso").value(DEFAULT_NUMEROPROCESSO.toString()))
            .andExpect(jsonPath("$.observacao").value(DEFAULT_OBSERVACAO.toString()))
            .andExpect(jsonPath("$.pendente").value(DEFAULT_PENDENTE.toString()))
            .andExpect(jsonPath("$.prazomes").value(DEFAULT_PRAZOMES.booleanValue()))
            .andExpect(jsonPath("$.prazovalidade").value(DEFAULT_PRAZOVALIDADE))
            .andExpect(jsonPath("$.reciboentregadocs").value(DEFAULT_RECIBOENTREGADOCS.toString()))
            .andExpect(jsonPath("$.usosolo").value(DEFAULT_USOSOLO.booleanValue()))
            .andExpect(jsonPath("$.usosoloobs").value(DEFAULT_USOSOLOOBS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLicenca() throws Exception {
        // Get the licenca
        restLicencaMockMvc.perform(get("/api/licencas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLicenca() throws Exception {
        // Initialize the database
        licencaRepository.saveAndFlush(licenca);
        int databaseSizeBeforeUpdate = licencaRepository.findAll().size();

        // Update the licenca
        Licenca updatedLicenca = licencaRepository.findOne(licenca.getId());
        updatedLicenca
            .album(UPDATED_ALBUM)
            .andamento(UPDATED_ANDAMENTO)
            .compensacaoambiental(UPDATED_COMPENSACAOAMBIENTAL)
            .dataemissao(UPDATED_DATAEMISSAO)
            .dataentregadocs(UPDATED_DATAENTREGADOCS)
            .dataexpedicaoprorrogacao1(UPDATED_DATAEXPEDICAOPRORROGACAO_1)
            .dataexpedicaoprorrogacao2(UPDATED_DATAEXPEDICAOPRORROGACAO_2)
            .dataexpedicaoprorrogacao3(UPDATED_DATAEXPEDICAOPRORROGACAO_3)
            .dataoficiolocalpedido(UPDATED_DATAOFICIOLOCALPEDIDO)
            .dataoficiolocalrecebimento(UPDATED_DATAOFICIOLOCALRECEBIMENTO)
            .dataoficioreoficialpedido(UPDATED_DATAOFICIOREOFICIALPEDIDO)
            .dataoficioreoficialrecebimento(UPDATED_DATAOFICIOREOFICIALRECEBIMENTO)
            .datapedidoprorrogacao1(UPDATED_DATAPEDIDOPRORROGACAO_1)
            .datapedidoprorrogacao2(UPDATED_DATAPEDIDOPRORROGACAO_2)
            .datapedidoprorrogacao3(UPDATED_DATAPEDIDOPRORROGACAO_3)
            .datavalidadeprorrogacao1(UPDATED_DATAVALIDADEPRORROGACAO_1)
            .datavalidadeprorrogacao2(UPDATED_DATAVALIDADEPRORROGACAO_2)
            .datavalidadeprorrogacao3(UPDATED_DATAVALIDADEPRORROGACAO_3)
            .datavencimento(UPDATED_DATAVENCIMENTO)
            .datavencimentoatual(UPDATED_DATAVENCIMENTOATUAL)
            .descricao(UPDATED_DESCRICAO)
            .dispensalai(UPDATED_DISPENSALAI)
            .docsentregues(UPDATED_DOCSENTREGUES)
            .eiarima(UPDATED_EIARIMA)
            .fcei(UPDATED_FCEI)
            .fceidatapagamento(UPDATED_FCEIDATAPAGAMENTO)
            .fceidataprotocolo(UPDATED_FCEIDATAPROTOCOLO)
            .fceivalor(UPDATED_FCEIVALOR)
            .folder(UPDATED_FOLDER)
            .inativo(UPDATED_INATIVO)
            .inventarioflorestal(UPDATED_INVENTARIOFLORESTAL)
            .numero(UPDATED_NUMERO)
            .numerolap(UPDATED_NUMEROLAP)
            .numerooficioconcessaoprorrogacao1(UPDATED_NUMEROOFICIOCONCESSAOPRORROGACAO_1)
            .numerooficioconcessaoprorrogacao2(UPDATED_NUMEROOFICIOCONCESSAOPRORROGACAO_2)
            .numerooficioconcessaoprorrogacao3(UPDATED_NUMEROOFICIOCONCESSAOPRORROGACAO_3)
            .numerooficiolocalpedido(UPDATED_NUMEROOFICIOLOCALPEDIDO)
            .numerooficiolocalrecebimento(UPDATED_NUMEROOFICIOLOCALRECEBIMENTO)
            .numerooficiooficialpedido(UPDATED_NUMEROOFICIOOFICIALPEDIDO)
            .numerooficiooficialrecebimento(UPDATED_NUMEROOFICIOOFICIALRECEBIMENTO)
            .numerooficioprorrogacao1(UPDATED_NUMEROOFICIOPRORROGACAO_1)
            .numerooficioprorrogacao2(UPDATED_NUMEROOFICIOPRORROGACAO_2)
            .numerooficioprorrogacao3(UPDATED_NUMEROOFICIOPRORROGACAO_3)
            .numeroparecertecnico(UPDATED_NUMEROPARECERTECNICO)
            .numeroprocesso(UPDATED_NUMEROPROCESSO)
            .observacao(UPDATED_OBSERVACAO)
            .pendente(UPDATED_PENDENTE)
            .prazomes(UPDATED_PRAZOMES)
            .prazovalidade(UPDATED_PRAZOVALIDADE)
            .reciboentregadocs(UPDATED_RECIBOENTREGADOCS)
            .usosolo(UPDATED_USOSOLO)
            .usosoloobs(UPDATED_USOSOLOOBS);
        LicencaDTO licencaDTO = licencaMapper.toDto(updatedLicenca);

        restLicencaMockMvc.perform(put("/api/licencas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(licencaDTO)))
            .andExpect(status().isOk());

        // Validate the Licenca in the database
        List<Licenca> licencaList = licencaRepository.findAll();
        assertThat(licencaList).hasSize(databaseSizeBeforeUpdate);
        Licenca testLicenca = licencaList.get(licencaList.size() - 1);
        assertThat(testLicenca.getAlbum()).isEqualTo(UPDATED_ALBUM);
        assertThat(testLicenca.getAndamento()).isEqualTo(UPDATED_ANDAMENTO);
        assertThat(testLicenca.isCompensacaoambiental()).isEqualTo(UPDATED_COMPENSACAOAMBIENTAL);
        assertThat(testLicenca.getDataemissao()).isEqualTo(UPDATED_DATAEMISSAO);
        assertThat(testLicenca.getDataentregadocs()).isEqualTo(UPDATED_DATAENTREGADOCS);
        assertThat(testLicenca.getDataexpedicaoprorrogacao1()).isEqualTo(UPDATED_DATAEXPEDICAOPRORROGACAO_1);
        assertThat(testLicenca.getDataexpedicaoprorrogacao2()).isEqualTo(UPDATED_DATAEXPEDICAOPRORROGACAO_2);
        assertThat(testLicenca.getDataexpedicaoprorrogacao3()).isEqualTo(UPDATED_DATAEXPEDICAOPRORROGACAO_3);
        assertThat(testLicenca.getDataoficiolocalpedido()).isEqualTo(UPDATED_DATAOFICIOLOCALPEDIDO);
        assertThat(testLicenca.getDataoficiolocalrecebimento()).isEqualTo(UPDATED_DATAOFICIOLOCALRECEBIMENTO);
        assertThat(testLicenca.getDataoficioreoficialpedido()).isEqualTo(UPDATED_DATAOFICIOREOFICIALPEDIDO);
        assertThat(testLicenca.getDataoficioreoficialrecebimento()).isEqualTo(UPDATED_DATAOFICIOREOFICIALRECEBIMENTO);
        assertThat(testLicenca.getDatapedidoprorrogacao1()).isEqualTo(UPDATED_DATAPEDIDOPRORROGACAO_1);
        assertThat(testLicenca.getDatapedidoprorrogacao2()).isEqualTo(UPDATED_DATAPEDIDOPRORROGACAO_2);
        assertThat(testLicenca.getDatapedidoprorrogacao3()).isEqualTo(UPDATED_DATAPEDIDOPRORROGACAO_3);
        assertThat(testLicenca.getDatavalidadeprorrogacao1()).isEqualTo(UPDATED_DATAVALIDADEPRORROGACAO_1);
        assertThat(testLicenca.getDatavalidadeprorrogacao2()).isEqualTo(UPDATED_DATAVALIDADEPRORROGACAO_2);
        assertThat(testLicenca.getDatavalidadeprorrogacao3()).isEqualTo(UPDATED_DATAVALIDADEPRORROGACAO_3);
        assertThat(testLicenca.getDatavencimento()).isEqualTo(UPDATED_DATAVENCIMENTO);
        assertThat(testLicenca.getDatavencimentoatual()).isEqualTo(UPDATED_DATAVENCIMENTOATUAL);
        assertThat(testLicenca.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testLicenca.isDispensalai()).isEqualTo(UPDATED_DISPENSALAI);
        assertThat(testLicenca.getDocsentregues()).isEqualTo(UPDATED_DOCSENTREGUES);
        assertThat(testLicenca.isEiarima()).isEqualTo(UPDATED_EIARIMA);
        assertThat(testLicenca.getFcei()).isEqualTo(UPDATED_FCEI);
        assertThat(testLicenca.getFceidatapagamento()).isEqualTo(UPDATED_FCEIDATAPAGAMENTO);
        assertThat(testLicenca.getFceidataprotocolo()).isEqualTo(UPDATED_FCEIDATAPROTOCOLO);
        assertThat(testLicenca.getFceivalor()).isEqualTo(UPDATED_FCEIVALOR);
        assertThat(testLicenca.getFolder()).isEqualTo(UPDATED_FOLDER);
        assertThat(testLicenca.isInativo()).isEqualTo(UPDATED_INATIVO);
        assertThat(testLicenca.isInventarioflorestal()).isEqualTo(UPDATED_INVENTARIOFLORESTAL);
        assertThat(testLicenca.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testLicenca.getNumerolap()).isEqualTo(UPDATED_NUMEROLAP);
        assertThat(testLicenca.getNumerooficioconcessaoprorrogacao1()).isEqualTo(UPDATED_NUMEROOFICIOCONCESSAOPRORROGACAO_1);
        assertThat(testLicenca.getNumerooficioconcessaoprorrogacao2()).isEqualTo(UPDATED_NUMEROOFICIOCONCESSAOPRORROGACAO_2);
        assertThat(testLicenca.getNumerooficioconcessaoprorrogacao3()).isEqualTo(UPDATED_NUMEROOFICIOCONCESSAOPRORROGACAO_3);
        assertThat(testLicenca.getNumerooficiolocalpedido()).isEqualTo(UPDATED_NUMEROOFICIOLOCALPEDIDO);
        assertThat(testLicenca.getNumerooficiolocalrecebimento()).isEqualTo(UPDATED_NUMEROOFICIOLOCALRECEBIMENTO);
        assertThat(testLicenca.getNumerooficiooficialpedido()).isEqualTo(UPDATED_NUMEROOFICIOOFICIALPEDIDO);
        assertThat(testLicenca.getNumerooficiooficialrecebimento()).isEqualTo(UPDATED_NUMEROOFICIOOFICIALRECEBIMENTO);
        assertThat(testLicenca.getNumerooficioprorrogacao1()).isEqualTo(UPDATED_NUMEROOFICIOPRORROGACAO_1);
        assertThat(testLicenca.getNumerooficioprorrogacao2()).isEqualTo(UPDATED_NUMEROOFICIOPRORROGACAO_2);
        assertThat(testLicenca.getNumerooficioprorrogacao3()).isEqualTo(UPDATED_NUMEROOFICIOPRORROGACAO_3);
        assertThat(testLicenca.getNumeroparecertecnico()).isEqualTo(UPDATED_NUMEROPARECERTECNICO);
        assertThat(testLicenca.getNumeroprocesso()).isEqualTo(UPDATED_NUMEROPROCESSO);
        assertThat(testLicenca.getObservacao()).isEqualTo(UPDATED_OBSERVACAO);
        assertThat(testLicenca.getPendente()).isEqualTo(UPDATED_PENDENTE);
        assertThat(testLicenca.isPrazomes()).isEqualTo(UPDATED_PRAZOMES);
        assertThat(testLicenca.getPrazovalidade()).isEqualTo(UPDATED_PRAZOVALIDADE);
        assertThat(testLicenca.getReciboentregadocs()).isEqualTo(UPDATED_RECIBOENTREGADOCS);
        assertThat(testLicenca.isUsosolo()).isEqualTo(UPDATED_USOSOLO);
        assertThat(testLicenca.getUsosoloobs()).isEqualTo(UPDATED_USOSOLOOBS);
    }

    @Test
    @Transactional
    public void updateNonExistingLicenca() throws Exception {
        int databaseSizeBeforeUpdate = licencaRepository.findAll().size();

        // Create the Licenca
        LicencaDTO licencaDTO = licencaMapper.toDto(licenca);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restLicencaMockMvc.perform(put("/api/licencas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(licencaDTO)))
            .andExpect(status().isCreated());

        // Validate the Licenca in the database
        List<Licenca> licencaList = licencaRepository.findAll();
        assertThat(licencaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteLicenca() throws Exception {
        // Initialize the database
        licencaRepository.saveAndFlush(licenca);
        int databaseSizeBeforeDelete = licencaRepository.findAll().size();

        // Get the licenca
        restLicencaMockMvc.perform(delete("/api/licencas/{id}", licenca.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Licenca> licencaList = licencaRepository.findAll();
        assertThat(licencaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Licenca.class);
        Licenca licenca1 = new Licenca();
        licenca1.setId(1L);
        Licenca licenca2 = new Licenca();
        licenca2.setId(licenca1.getId());
        assertThat(licenca1).isEqualTo(licenca2);
        licenca2.setId(2L);
        assertThat(licenca1).isNotEqualTo(licenca2);
        licenca1.setId(null);
        assertThat(licenca1).isNotEqualTo(licenca2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LicencaDTO.class);
        LicencaDTO licencaDTO1 = new LicencaDTO();
        licencaDTO1.setId(1L);
        LicencaDTO licencaDTO2 = new LicencaDTO();
        assertThat(licencaDTO1).isNotEqualTo(licencaDTO2);
        licencaDTO2.setId(licencaDTO1.getId());
        assertThat(licencaDTO1).isEqualTo(licencaDTO2);
        licencaDTO2.setId(2L);
        assertThat(licencaDTO1).isNotEqualTo(licencaDTO2);
        licencaDTO1.setId(null);
        assertThat(licencaDTO1).isNotEqualTo(licencaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(licencaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(licencaMapper.fromId(null)).isNull();
    }
}
