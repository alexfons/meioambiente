package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Solicitacao;
import br.com.homemade.repository.SolicitacaoRepository;
import br.com.homemade.service.dto.SolicitacaoDTO;
import br.com.homemade.service.mapper.SolicitacaoMapper;
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
 * Test class for the SolicitacaoResource REST controller.
 *
 * @see SolicitacaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class SolicitacaoResourceIntTest {

    private static final Boolean DEFAULT_APRESENTAMOS = false;
    private static final Boolean UPDATED_APRESENTAMOS = true;

    private static final ZonedDateTime DEFAULT_DATA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final String DEFAULT_EXTENSO = "AAAAAAAAAA";
    private static final String UPDATED_EXTENSO = "BBBBBBBBBB";

    private static final String DEFAULT_EXTENSO_1 = "AAAAAAAAAA";
    private static final String UPDATED_EXTENSO_1 = "BBBBBBBBBB";

    private static final Integer DEFAULT_IDSOLICITACAO = 1;
    private static final Integer UPDATED_IDSOLICITACAO = 2;

    private static final String DEFAULT_MOEDA = "AAAAAAAAAA";
    private static final String UPDATED_MOEDA = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_MONTANTE = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANTE = new BigDecimal(2);

    private static final Integer DEFAULT_NSOLICITACAO = 1;
    private static final Integer UPDATED_NSOLICITACAO = 2;

    private static final String DEFAULT_OFICIO = "AAAAAAAAAA";
    private static final String UPDATED_OFICIO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_SOLICITAMOS = false;
    private static final Boolean UPDATED_SOLICITAMOS = true;

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    @Autowired
    private SolicitacaoMapper solicitacaoMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSolicitacaoMockMvc;

    private Solicitacao solicitacao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        SolicitacaoResource solicitacaoResource = new SolicitacaoResource(solicitacaoRepository, solicitacaoMapper);
        this.restSolicitacaoMockMvc = MockMvcBuilders.standaloneSetup(solicitacaoResource)
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
    public static Solicitacao createEntity(EntityManager em) {
        Solicitacao solicitacao = new Solicitacao()
            .apresentamos(DEFAULT_APRESENTAMOS)
            .data(DEFAULT_DATA)
            .descricao(DEFAULT_DESCRICAO)
            .extenso(DEFAULT_EXTENSO)
            .extenso1(DEFAULT_EXTENSO_1)
            .idsolicitacao(DEFAULT_IDSOLICITACAO)
            .moeda(DEFAULT_MOEDA)
            .montante(DEFAULT_MONTANTE)
            .nsolicitacao(DEFAULT_NSOLICITACAO)
            .oficio(DEFAULT_OFICIO)
            .solicitamos(DEFAULT_SOLICITAMOS);
        return solicitacao;
    }

    @Before
    public void initTest() {
        solicitacao = createEntity(em);
    }

    @Test
    @Transactional
    public void createSolicitacao() throws Exception {
        int databaseSizeBeforeCreate = solicitacaoRepository.findAll().size();

        // Create the Solicitacao
        SolicitacaoDTO solicitacaoDTO = solicitacaoMapper.toDto(solicitacao);
        restSolicitacaoMockMvc.perform(post("/api/solicitacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(solicitacaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Solicitacao in the database
        List<Solicitacao> solicitacaoList = solicitacaoRepository.findAll();
        assertThat(solicitacaoList).hasSize(databaseSizeBeforeCreate + 1);
        Solicitacao testSolicitacao = solicitacaoList.get(solicitacaoList.size() - 1);
        assertThat(testSolicitacao.isApresentamos()).isEqualTo(DEFAULT_APRESENTAMOS);
        assertThat(testSolicitacao.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testSolicitacao.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testSolicitacao.getExtenso()).isEqualTo(DEFAULT_EXTENSO);
        assertThat(testSolicitacao.getExtenso1()).isEqualTo(DEFAULT_EXTENSO_1);
        assertThat(testSolicitacao.getIdsolicitacao()).isEqualTo(DEFAULT_IDSOLICITACAO);
        assertThat(testSolicitacao.getMoeda()).isEqualTo(DEFAULT_MOEDA);
        assertThat(testSolicitacao.getMontante()).isEqualTo(DEFAULT_MONTANTE);
        assertThat(testSolicitacao.getNsolicitacao()).isEqualTo(DEFAULT_NSOLICITACAO);
        assertThat(testSolicitacao.getOficio()).isEqualTo(DEFAULT_OFICIO);
        assertThat(testSolicitacao.isSolicitamos()).isEqualTo(DEFAULT_SOLICITAMOS);
    }

    @Test
    @Transactional
    public void createSolicitacaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = solicitacaoRepository.findAll().size();

        // Create the Solicitacao with an existing ID
        solicitacao.setId(1L);
        SolicitacaoDTO solicitacaoDTO = solicitacaoMapper.toDto(solicitacao);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSolicitacaoMockMvc.perform(post("/api/solicitacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(solicitacaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Solicitacao> solicitacaoList = solicitacaoRepository.findAll();
        assertThat(solicitacaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSolicitacaos() throws Exception {
        // Initialize the database
        solicitacaoRepository.saveAndFlush(solicitacao);

        // Get all the solicitacaoList
        restSolicitacaoMockMvc.perform(get("/api/solicitacaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(solicitacao.getId().intValue())))
            .andExpect(jsonPath("$.[*].apresentamos").value(hasItem(DEFAULT_APRESENTAMOS.booleanValue())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(sameInstant(DEFAULT_DATA))))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].extenso").value(hasItem(DEFAULT_EXTENSO.toString())))
            .andExpect(jsonPath("$.[*].extenso1").value(hasItem(DEFAULT_EXTENSO_1.toString())))
            .andExpect(jsonPath("$.[*].idsolicitacao").value(hasItem(DEFAULT_IDSOLICITACAO)))
            .andExpect(jsonPath("$.[*].moeda").value(hasItem(DEFAULT_MOEDA.toString())))
            .andExpect(jsonPath("$.[*].montante").value(hasItem(DEFAULT_MONTANTE.intValue())))
            .andExpect(jsonPath("$.[*].nsolicitacao").value(hasItem(DEFAULT_NSOLICITACAO)))
            .andExpect(jsonPath("$.[*].oficio").value(hasItem(DEFAULT_OFICIO.toString())))
            .andExpect(jsonPath("$.[*].solicitamos").value(hasItem(DEFAULT_SOLICITAMOS.booleanValue())));
    }

    @Test
    @Transactional
    public void getSolicitacao() throws Exception {
        // Initialize the database
        solicitacaoRepository.saveAndFlush(solicitacao);

        // Get the solicitacao
        restSolicitacaoMockMvc.perform(get("/api/solicitacaos/{id}", solicitacao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(solicitacao.getId().intValue()))
            .andExpect(jsonPath("$.apresentamos").value(DEFAULT_APRESENTAMOS.booleanValue()))
            .andExpect(jsonPath("$.data").value(sameInstant(DEFAULT_DATA)))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.extenso").value(DEFAULT_EXTENSO.toString()))
            .andExpect(jsonPath("$.extenso1").value(DEFAULT_EXTENSO_1.toString()))
            .andExpect(jsonPath("$.idsolicitacao").value(DEFAULT_IDSOLICITACAO))
            .andExpect(jsonPath("$.moeda").value(DEFAULT_MOEDA.toString()))
            .andExpect(jsonPath("$.montante").value(DEFAULT_MONTANTE.intValue()))
            .andExpect(jsonPath("$.nsolicitacao").value(DEFAULT_NSOLICITACAO))
            .andExpect(jsonPath("$.oficio").value(DEFAULT_OFICIO.toString()))
            .andExpect(jsonPath("$.solicitamos").value(DEFAULT_SOLICITAMOS.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSolicitacao() throws Exception {
        // Get the solicitacao
        restSolicitacaoMockMvc.perform(get("/api/solicitacaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSolicitacao() throws Exception {
        // Initialize the database
        solicitacaoRepository.saveAndFlush(solicitacao);
        int databaseSizeBeforeUpdate = solicitacaoRepository.findAll().size();

        // Update the solicitacao
        Solicitacao updatedSolicitacao = solicitacaoRepository.findOne(solicitacao.getId());
        updatedSolicitacao
            .apresentamos(UPDATED_APRESENTAMOS)
            .data(UPDATED_DATA)
            .descricao(UPDATED_DESCRICAO)
            .extenso(UPDATED_EXTENSO)
            .extenso1(UPDATED_EXTENSO_1)
            .idsolicitacao(UPDATED_IDSOLICITACAO)
            .moeda(UPDATED_MOEDA)
            .montante(UPDATED_MONTANTE)
            .nsolicitacao(UPDATED_NSOLICITACAO)
            .oficio(UPDATED_OFICIO)
            .solicitamos(UPDATED_SOLICITAMOS);
        SolicitacaoDTO solicitacaoDTO = solicitacaoMapper.toDto(updatedSolicitacao);

        restSolicitacaoMockMvc.perform(put("/api/solicitacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(solicitacaoDTO)))
            .andExpect(status().isOk());

        // Validate the Solicitacao in the database
        List<Solicitacao> solicitacaoList = solicitacaoRepository.findAll();
        assertThat(solicitacaoList).hasSize(databaseSizeBeforeUpdate);
        Solicitacao testSolicitacao = solicitacaoList.get(solicitacaoList.size() - 1);
        assertThat(testSolicitacao.isApresentamos()).isEqualTo(UPDATED_APRESENTAMOS);
        assertThat(testSolicitacao.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testSolicitacao.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testSolicitacao.getExtenso()).isEqualTo(UPDATED_EXTENSO);
        assertThat(testSolicitacao.getExtenso1()).isEqualTo(UPDATED_EXTENSO_1);
        assertThat(testSolicitacao.getIdsolicitacao()).isEqualTo(UPDATED_IDSOLICITACAO);
        assertThat(testSolicitacao.getMoeda()).isEqualTo(UPDATED_MOEDA);
        assertThat(testSolicitacao.getMontante()).isEqualTo(UPDATED_MONTANTE);
        assertThat(testSolicitacao.getNsolicitacao()).isEqualTo(UPDATED_NSOLICITACAO);
        assertThat(testSolicitacao.getOficio()).isEqualTo(UPDATED_OFICIO);
        assertThat(testSolicitacao.isSolicitamos()).isEqualTo(UPDATED_SOLICITAMOS);
    }

    @Test
    @Transactional
    public void updateNonExistingSolicitacao() throws Exception {
        int databaseSizeBeforeUpdate = solicitacaoRepository.findAll().size();

        // Create the Solicitacao
        SolicitacaoDTO solicitacaoDTO = solicitacaoMapper.toDto(solicitacao);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSolicitacaoMockMvc.perform(put("/api/solicitacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(solicitacaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Solicitacao in the database
        List<Solicitacao> solicitacaoList = solicitacaoRepository.findAll();
        assertThat(solicitacaoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSolicitacao() throws Exception {
        // Initialize the database
        solicitacaoRepository.saveAndFlush(solicitacao);
        int databaseSizeBeforeDelete = solicitacaoRepository.findAll().size();

        // Get the solicitacao
        restSolicitacaoMockMvc.perform(delete("/api/solicitacaos/{id}", solicitacao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Solicitacao> solicitacaoList = solicitacaoRepository.findAll();
        assertThat(solicitacaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Solicitacao.class);
        Solicitacao solicitacao1 = new Solicitacao();
        solicitacao1.setId(1L);
        Solicitacao solicitacao2 = new Solicitacao();
        solicitacao2.setId(solicitacao1.getId());
        assertThat(solicitacao1).isEqualTo(solicitacao2);
        solicitacao2.setId(2L);
        assertThat(solicitacao1).isNotEqualTo(solicitacao2);
        solicitacao1.setId(null);
        assertThat(solicitacao1).isNotEqualTo(solicitacao2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SolicitacaoDTO.class);
        SolicitacaoDTO solicitacaoDTO1 = new SolicitacaoDTO();
        solicitacaoDTO1.setId(1L);
        SolicitacaoDTO solicitacaoDTO2 = new SolicitacaoDTO();
        assertThat(solicitacaoDTO1).isNotEqualTo(solicitacaoDTO2);
        solicitacaoDTO2.setId(solicitacaoDTO1.getId());
        assertThat(solicitacaoDTO1).isEqualTo(solicitacaoDTO2);
        solicitacaoDTO2.setId(2L);
        assertThat(solicitacaoDTO1).isNotEqualTo(solicitacaoDTO2);
        solicitacaoDTO1.setId(null);
        assertThat(solicitacaoDTO1).isNotEqualTo(solicitacaoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(solicitacaoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(solicitacaoMapper.fromId(null)).isNull();
    }
}
