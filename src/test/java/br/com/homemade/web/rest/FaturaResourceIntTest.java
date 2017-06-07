package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Fatura;
import br.com.homemade.repository.FaturaRepository;
import br.com.homemade.service.dto.FaturaDTO;
import br.com.homemade.service.mapper.FaturaMapper;
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
 * Test class for the FaturaResource REST controller.
 *
 * @see FaturaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class FaturaResourceIntTest {

    private static final Boolean DEFAULT_AJUSTECAMBIO = false;
    private static final Boolean UPDATED_AJUSTECAMBIO = true;

    private static final ZonedDateTime DEFAULT_DATAOP = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAOP = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_DESPESASEXEANTERIORES = "AAAAAAAAAA";
    private static final String UPDATED_DESPESASEXEANTERIORES = "BBBBBBBBBB";

    private static final Integer DEFAULT_NFATURA = 1;
    private static final Integer UPDATED_NFATURA = 2;

    private static final Integer DEFAULT_NPROCESSO = 1;
    private static final Integer UPDATED_NPROCESSO = 2;

    private static final Integer DEFAULT_NSOLICITACAO = 1;
    private static final Integer UPDATED_NSOLICITACAO = 2;

    private static final Integer DEFAULT_PARCELA = 1;
    private static final Integer UPDATED_PARCELA = 2;

    private static final Integer DEFAULT_NOB = 1;
    private static final Integer UPDATED_NOB = 2;

    private static final Integer DEFAULT_NOP = 1;
    private static final Integer UPDATED_NOP = 2;

    private static final Integer DEFAULT_NUMMEDICAO = 1;
    private static final Integer UPDATED_NUMMEDICAO = 2;

    private static final String DEFAULT_RESTOSAPAGAR = "AAAAAAAAAA";
    private static final String UPDATED_RESTOSAPAGAR = "BBBBBBBBBB";

    private static final String DEFAULT_TIPOMEDICAO = "AAAAAAAAAA";
    private static final String UPDATED_TIPOMEDICAO = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_VALORPI = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALORPI = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VALORREAJUSTE = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALORREAJUSTE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VALORUS = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALORUS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VREAJUSTE = new BigDecimal(1);
    private static final BigDecimal UPDATED_VREAJUSTE = new BigDecimal(2);

    @Autowired
    private FaturaRepository faturaRepository;

    @Autowired
    private FaturaMapper faturaMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFaturaMockMvc;

    private Fatura fatura;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        FaturaResource faturaResource = new FaturaResource(faturaRepository, faturaMapper);
        this.restFaturaMockMvc = MockMvcBuilders.standaloneSetup(faturaResource)
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
    public static Fatura createEntity(EntityManager em) {
        Fatura fatura = new Fatura()
            .ajustecambio(DEFAULT_AJUSTECAMBIO)
            .dataop(DEFAULT_DATAOP)
            .despesasexeanteriores(DEFAULT_DESPESASEXEANTERIORES)
            .nfatura(DEFAULT_NFATURA)
            .nprocesso(DEFAULT_NPROCESSO)
            .nsolicitacao(DEFAULT_NSOLICITACAO)
            .parcela(DEFAULT_PARCELA)
            .nob(DEFAULT_NOB)
            .nop(DEFAULT_NOP)
            .nummedicao(DEFAULT_NUMMEDICAO)
            .restosapagar(DEFAULT_RESTOSAPAGAR)
            .tipomedicao(DEFAULT_TIPOMEDICAO)
            .valorpi(DEFAULT_VALORPI)
            .valorreajuste(DEFAULT_VALORREAJUSTE)
            .valorus(DEFAULT_VALORUS)
            .vreajuste(DEFAULT_VREAJUSTE);
        return fatura;
    }

    @Before
    public void initTest() {
        fatura = createEntity(em);
    }

    @Test
    @Transactional
    public void createFatura() throws Exception {
        int databaseSizeBeforeCreate = faturaRepository.findAll().size();

        // Create the Fatura
        FaturaDTO faturaDTO = faturaMapper.toDto(fatura);
        restFaturaMockMvc.perform(post("/api/faturas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(faturaDTO)))
            .andExpect(status().isCreated());

        // Validate the Fatura in the database
        List<Fatura> faturaList = faturaRepository.findAll();
        assertThat(faturaList).hasSize(databaseSizeBeforeCreate + 1);
        Fatura testFatura = faturaList.get(faturaList.size() - 1);
        assertThat(testFatura.isAjustecambio()).isEqualTo(DEFAULT_AJUSTECAMBIO);
        assertThat(testFatura.getDataop()).isEqualTo(DEFAULT_DATAOP);
        assertThat(testFatura.getDespesasexeanteriores()).isEqualTo(DEFAULT_DESPESASEXEANTERIORES);
        assertThat(testFatura.getNfatura()).isEqualTo(DEFAULT_NFATURA);
        assertThat(testFatura.getNprocesso()).isEqualTo(DEFAULT_NPROCESSO);
        assertThat(testFatura.getNsolicitacao()).isEqualTo(DEFAULT_NSOLICITACAO);
        assertThat(testFatura.getParcela()).isEqualTo(DEFAULT_PARCELA);
        assertThat(testFatura.getNob()).isEqualTo(DEFAULT_NOB);
        assertThat(testFatura.getNop()).isEqualTo(DEFAULT_NOP);
        assertThat(testFatura.getNummedicao()).isEqualTo(DEFAULT_NUMMEDICAO);
        assertThat(testFatura.getRestosapagar()).isEqualTo(DEFAULT_RESTOSAPAGAR);
        assertThat(testFatura.getTipomedicao()).isEqualTo(DEFAULT_TIPOMEDICAO);
        assertThat(testFatura.getValorpi()).isEqualTo(DEFAULT_VALORPI);
        assertThat(testFatura.getValorreajuste()).isEqualTo(DEFAULT_VALORREAJUSTE);
        assertThat(testFatura.getValorus()).isEqualTo(DEFAULT_VALORUS);
        assertThat(testFatura.getVreajuste()).isEqualTo(DEFAULT_VREAJUSTE);
    }

    @Test
    @Transactional
    public void createFaturaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = faturaRepository.findAll().size();

        // Create the Fatura with an existing ID
        fatura.setId(1L);
        FaturaDTO faturaDTO = faturaMapper.toDto(fatura);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFaturaMockMvc.perform(post("/api/faturas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(faturaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Fatura> faturaList = faturaRepository.findAll();
        assertThat(faturaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFaturas() throws Exception {
        // Initialize the database
        faturaRepository.saveAndFlush(fatura);

        // Get all the faturaList
        restFaturaMockMvc.perform(get("/api/faturas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fatura.getId().intValue())))
            .andExpect(jsonPath("$.[*].ajustecambio").value(hasItem(DEFAULT_AJUSTECAMBIO.booleanValue())))
            .andExpect(jsonPath("$.[*].dataop").value(hasItem(sameInstant(DEFAULT_DATAOP))))
            .andExpect(jsonPath("$.[*].despesasexeanteriores").value(hasItem(DEFAULT_DESPESASEXEANTERIORES.toString())))
            .andExpect(jsonPath("$.[*].nfatura").value(hasItem(DEFAULT_NFATURA)))
            .andExpect(jsonPath("$.[*].nprocesso").value(hasItem(DEFAULT_NPROCESSO)))
            .andExpect(jsonPath("$.[*].nsolicitacao").value(hasItem(DEFAULT_NSOLICITACAO)))
            .andExpect(jsonPath("$.[*].parcela").value(hasItem(DEFAULT_PARCELA)))
            .andExpect(jsonPath("$.[*].nob").value(hasItem(DEFAULT_NOB)))
            .andExpect(jsonPath("$.[*].nop").value(hasItem(DEFAULT_NOP)))
            .andExpect(jsonPath("$.[*].nummedicao").value(hasItem(DEFAULT_NUMMEDICAO)))
            .andExpect(jsonPath("$.[*].restosapagar").value(hasItem(DEFAULT_RESTOSAPAGAR.toString())))
            .andExpect(jsonPath("$.[*].tipomedicao").value(hasItem(DEFAULT_TIPOMEDICAO.toString())))
            .andExpect(jsonPath("$.[*].valorpi").value(hasItem(DEFAULT_VALORPI.intValue())))
            .andExpect(jsonPath("$.[*].valorreajuste").value(hasItem(DEFAULT_VALORREAJUSTE.intValue())))
            .andExpect(jsonPath("$.[*].valorus").value(hasItem(DEFAULT_VALORUS.intValue())))
            .andExpect(jsonPath("$.[*].vreajuste").value(hasItem(DEFAULT_VREAJUSTE.intValue())));
    }

    @Test
    @Transactional
    public void getFatura() throws Exception {
        // Initialize the database
        faturaRepository.saveAndFlush(fatura);

        // Get the fatura
        restFaturaMockMvc.perform(get("/api/faturas/{id}", fatura.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fatura.getId().intValue()))
            .andExpect(jsonPath("$.ajustecambio").value(DEFAULT_AJUSTECAMBIO.booleanValue()))
            .andExpect(jsonPath("$.dataop").value(sameInstant(DEFAULT_DATAOP)))
            .andExpect(jsonPath("$.despesasexeanteriores").value(DEFAULT_DESPESASEXEANTERIORES.toString()))
            .andExpect(jsonPath("$.nfatura").value(DEFAULT_NFATURA))
            .andExpect(jsonPath("$.nprocesso").value(DEFAULT_NPROCESSO))
            .andExpect(jsonPath("$.nsolicitacao").value(DEFAULT_NSOLICITACAO))
            .andExpect(jsonPath("$.parcela").value(DEFAULT_PARCELA))
            .andExpect(jsonPath("$.nob").value(DEFAULT_NOB))
            .andExpect(jsonPath("$.nop").value(DEFAULT_NOP))
            .andExpect(jsonPath("$.nummedicao").value(DEFAULT_NUMMEDICAO))
            .andExpect(jsonPath("$.restosapagar").value(DEFAULT_RESTOSAPAGAR.toString()))
            .andExpect(jsonPath("$.tipomedicao").value(DEFAULT_TIPOMEDICAO.toString()))
            .andExpect(jsonPath("$.valorpi").value(DEFAULT_VALORPI.intValue()))
            .andExpect(jsonPath("$.valorreajuste").value(DEFAULT_VALORREAJUSTE.intValue()))
            .andExpect(jsonPath("$.valorus").value(DEFAULT_VALORUS.intValue()))
            .andExpect(jsonPath("$.vreajuste").value(DEFAULT_VREAJUSTE.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingFatura() throws Exception {
        // Get the fatura
        restFaturaMockMvc.perform(get("/api/faturas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFatura() throws Exception {
        // Initialize the database
        faturaRepository.saveAndFlush(fatura);
        int databaseSizeBeforeUpdate = faturaRepository.findAll().size();

        // Update the fatura
        Fatura updatedFatura = faturaRepository.findOne(fatura.getId());
        updatedFatura
            .ajustecambio(UPDATED_AJUSTECAMBIO)
            .dataop(UPDATED_DATAOP)
            .despesasexeanteriores(UPDATED_DESPESASEXEANTERIORES)
            .nfatura(UPDATED_NFATURA)
            .nprocesso(UPDATED_NPROCESSO)
            .nsolicitacao(UPDATED_NSOLICITACAO)
            .parcela(UPDATED_PARCELA)
            .nob(UPDATED_NOB)
            .nop(UPDATED_NOP)
            .nummedicao(UPDATED_NUMMEDICAO)
            .restosapagar(UPDATED_RESTOSAPAGAR)
            .tipomedicao(UPDATED_TIPOMEDICAO)
            .valorpi(UPDATED_VALORPI)
            .valorreajuste(UPDATED_VALORREAJUSTE)
            .valorus(UPDATED_VALORUS)
            .vreajuste(UPDATED_VREAJUSTE);
        FaturaDTO faturaDTO = faturaMapper.toDto(updatedFatura);

        restFaturaMockMvc.perform(put("/api/faturas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(faturaDTO)))
            .andExpect(status().isOk());

        // Validate the Fatura in the database
        List<Fatura> faturaList = faturaRepository.findAll();
        assertThat(faturaList).hasSize(databaseSizeBeforeUpdate);
        Fatura testFatura = faturaList.get(faturaList.size() - 1);
        assertThat(testFatura.isAjustecambio()).isEqualTo(UPDATED_AJUSTECAMBIO);
        assertThat(testFatura.getDataop()).isEqualTo(UPDATED_DATAOP);
        assertThat(testFatura.getDespesasexeanteriores()).isEqualTo(UPDATED_DESPESASEXEANTERIORES);
        assertThat(testFatura.getNfatura()).isEqualTo(UPDATED_NFATURA);
        assertThat(testFatura.getNprocesso()).isEqualTo(UPDATED_NPROCESSO);
        assertThat(testFatura.getNsolicitacao()).isEqualTo(UPDATED_NSOLICITACAO);
        assertThat(testFatura.getParcela()).isEqualTo(UPDATED_PARCELA);
        assertThat(testFatura.getNob()).isEqualTo(UPDATED_NOB);
        assertThat(testFatura.getNop()).isEqualTo(UPDATED_NOP);
        assertThat(testFatura.getNummedicao()).isEqualTo(UPDATED_NUMMEDICAO);
        assertThat(testFatura.getRestosapagar()).isEqualTo(UPDATED_RESTOSAPAGAR);
        assertThat(testFatura.getTipomedicao()).isEqualTo(UPDATED_TIPOMEDICAO);
        assertThat(testFatura.getValorpi()).isEqualTo(UPDATED_VALORPI);
        assertThat(testFatura.getValorreajuste()).isEqualTo(UPDATED_VALORREAJUSTE);
        assertThat(testFatura.getValorus()).isEqualTo(UPDATED_VALORUS);
        assertThat(testFatura.getVreajuste()).isEqualTo(UPDATED_VREAJUSTE);
    }

    @Test
    @Transactional
    public void updateNonExistingFatura() throws Exception {
        int databaseSizeBeforeUpdate = faturaRepository.findAll().size();

        // Create the Fatura
        FaturaDTO faturaDTO = faturaMapper.toDto(fatura);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFaturaMockMvc.perform(put("/api/faturas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(faturaDTO)))
            .andExpect(status().isCreated());

        // Validate the Fatura in the database
        List<Fatura> faturaList = faturaRepository.findAll();
        assertThat(faturaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFatura() throws Exception {
        // Initialize the database
        faturaRepository.saveAndFlush(fatura);
        int databaseSizeBeforeDelete = faturaRepository.findAll().size();

        // Get the fatura
        restFaturaMockMvc.perform(delete("/api/faturas/{id}", fatura.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Fatura> faturaList = faturaRepository.findAll();
        assertThat(faturaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Fatura.class);
        Fatura fatura1 = new Fatura();
        fatura1.setId(1L);
        Fatura fatura2 = new Fatura();
        fatura2.setId(fatura1.getId());
        assertThat(fatura1).isEqualTo(fatura2);
        fatura2.setId(2L);
        assertThat(fatura1).isNotEqualTo(fatura2);
        fatura1.setId(null);
        assertThat(fatura1).isNotEqualTo(fatura2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FaturaDTO.class);
        FaturaDTO faturaDTO1 = new FaturaDTO();
        faturaDTO1.setId(1L);
        FaturaDTO faturaDTO2 = new FaturaDTO();
        assertThat(faturaDTO1).isNotEqualTo(faturaDTO2);
        faturaDTO2.setId(faturaDTO1.getId());
        assertThat(faturaDTO1).isEqualTo(faturaDTO2);
        faturaDTO2.setId(2L);
        assertThat(faturaDTO1).isNotEqualTo(faturaDTO2);
        faturaDTO1.setId(null);
        assertThat(faturaDTO1).isNotEqualTo(faturaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(faturaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(faturaMapper.fromId(null)).isNull();
    }
}
