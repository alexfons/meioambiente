package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Faturacontrato;
import br.com.homemade.repository.FaturacontratoRepository;
import br.com.homemade.service.dto.FaturacontratoDTO;
import br.com.homemade.service.mapper.FaturacontratoMapper;
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
 * Test class for the FaturacontratoResource REST controller.
 *
 * @see FaturacontratoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class FaturacontratoResourceIntTest {

    private static final ZonedDateTime DEFAULT_DATAOP = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAOP = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_DESPESASEXEANTERIORES = "AAAAAAAAAA";
    private static final String UPDATED_DESPESASEXEANTERIORES = "BBBBBBBBBB";

    private static final Integer DEFAULT_IDFATURACONTRATO = 1;
    private static final Integer UPDATED_IDFATURACONTRATO = 2;

    private static final Integer DEFAULT_NFATURA = 1;
    private static final Integer UPDATED_NFATURA = 2;

    private static final Integer DEFAULT_NPROCESSO = 1;
    private static final Integer UPDATED_NPROCESSO = 2;

    private static final Integer DEFAULT_NSOLICITACAO = 1;
    private static final Integer UPDATED_NSOLICITACAO = 2;

    private static final Integer DEFAULT_PARCELA = 1;
    private static final Integer UPDATED_PARCELA = 2;

    private static final Integer DEFAULT_NOBANCARIA = 1;
    private static final Integer UPDATED_NOBANCARIA = 2;

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

    private static final BigDecimal DEFAULT_APORTELOCAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_APORTELOCAL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_APORTEAGENTE = new BigDecimal(1);
    private static final BigDecimal UPDATED_APORTEAGENTE = new BigDecimal(2);

    @Autowired
    private FaturacontratoRepository faturacontratoRepository;

    @Autowired
    private FaturacontratoMapper faturacontratoMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFaturacontratoMockMvc;

    private Faturacontrato faturacontrato;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        FaturacontratoResource faturacontratoResource = new FaturacontratoResource(faturacontratoRepository, faturacontratoMapper);
        this.restFaturacontratoMockMvc = MockMvcBuilders.standaloneSetup(faturacontratoResource)
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
    public static Faturacontrato createEntity(EntityManager em) {
        Faturacontrato faturacontrato = new Faturacontrato()
            .dataop(DEFAULT_DATAOP)
            .despesasexeanteriores(DEFAULT_DESPESASEXEANTERIORES)
            .idfaturacontrato(DEFAULT_IDFATURACONTRATO)
            .nfatura(DEFAULT_NFATURA)
            .nprocesso(DEFAULT_NPROCESSO)
            .nsolicitacao(DEFAULT_NSOLICITACAO)
            .parcela(DEFAULT_PARCELA)
            .nobancaria(DEFAULT_NOBANCARIA)
            .nop(DEFAULT_NOP)
            .nummedicao(DEFAULT_NUMMEDICAO)
            .restosapagar(DEFAULT_RESTOSAPAGAR)
            .tipomedicao(DEFAULT_TIPOMEDICAO)
            .valorpi(DEFAULT_VALORPI)
            .valorreajuste(DEFAULT_VALORREAJUSTE)
            .valorus(DEFAULT_VALORUS)
            .vreajuste(DEFAULT_VREAJUSTE)
            .aportelocal(DEFAULT_APORTELOCAL)
            .aporteagente(DEFAULT_APORTEAGENTE);
        return faturacontrato;
    }

    @Before
    public void initTest() {
        faturacontrato = createEntity(em);
    }

    @Test
    @Transactional
    public void createFaturacontrato() throws Exception {
        int databaseSizeBeforeCreate = faturacontratoRepository.findAll().size();

        // Create the Faturacontrato
        FaturacontratoDTO faturacontratoDTO = faturacontratoMapper.toDto(faturacontrato);
        restFaturacontratoMockMvc.perform(post("/api/faturacontratoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(faturacontratoDTO)))
            .andExpect(status().isCreated());

        // Validate the Faturacontrato in the database
        List<Faturacontrato> faturacontratoList = faturacontratoRepository.findAll();
        assertThat(faturacontratoList).hasSize(databaseSizeBeforeCreate + 1);
        Faturacontrato testFaturacontrato = faturacontratoList.get(faturacontratoList.size() - 1);
        assertThat(testFaturacontrato.getDataop()).isEqualTo(DEFAULT_DATAOP);
        assertThat(testFaturacontrato.getDespesasexeanteriores()).isEqualTo(DEFAULT_DESPESASEXEANTERIORES);
        assertThat(testFaturacontrato.getIdfaturacontrato()).isEqualTo(DEFAULT_IDFATURACONTRATO);
        assertThat(testFaturacontrato.getNfatura()).isEqualTo(DEFAULT_NFATURA);
        assertThat(testFaturacontrato.getNprocesso()).isEqualTo(DEFAULT_NPROCESSO);
        assertThat(testFaturacontrato.getNsolicitacao()).isEqualTo(DEFAULT_NSOLICITACAO);
        assertThat(testFaturacontrato.getParcela()).isEqualTo(DEFAULT_PARCELA);
        assertThat(testFaturacontrato.getNobancaria()).isEqualTo(DEFAULT_NOBANCARIA);
        assertThat(testFaturacontrato.getNop()).isEqualTo(DEFAULT_NOP);
        assertThat(testFaturacontrato.getNummedicao()).isEqualTo(DEFAULT_NUMMEDICAO);
        assertThat(testFaturacontrato.getRestosapagar()).isEqualTo(DEFAULT_RESTOSAPAGAR);
        assertThat(testFaturacontrato.getTipomedicao()).isEqualTo(DEFAULT_TIPOMEDICAO);
        assertThat(testFaturacontrato.getValorpi()).isEqualTo(DEFAULT_VALORPI);
        assertThat(testFaturacontrato.getValorreajuste()).isEqualTo(DEFAULT_VALORREAJUSTE);
        assertThat(testFaturacontrato.getValorus()).isEqualTo(DEFAULT_VALORUS);
        assertThat(testFaturacontrato.getVreajuste()).isEqualTo(DEFAULT_VREAJUSTE);
        assertThat(testFaturacontrato.getAportelocal()).isEqualTo(DEFAULT_APORTELOCAL);
        assertThat(testFaturacontrato.getAporteagente()).isEqualTo(DEFAULT_APORTEAGENTE);
    }

    @Test
    @Transactional
    public void createFaturacontratoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = faturacontratoRepository.findAll().size();

        // Create the Faturacontrato with an existing ID
        faturacontrato.setId(1L);
        FaturacontratoDTO faturacontratoDTO = faturacontratoMapper.toDto(faturacontrato);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFaturacontratoMockMvc.perform(post("/api/faturacontratoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(faturacontratoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Faturacontrato> faturacontratoList = faturacontratoRepository.findAll();
        assertThat(faturacontratoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFaturacontratoes() throws Exception {
        // Initialize the database
        faturacontratoRepository.saveAndFlush(faturacontrato);

        // Get all the faturacontratoList
        restFaturacontratoMockMvc.perform(get("/api/faturacontratoes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(faturacontrato.getId().intValue())))
            .andExpect(jsonPath("$.[*].dataop").value(hasItem(sameInstant(DEFAULT_DATAOP))))
            .andExpect(jsonPath("$.[*].despesasexeanteriores").value(hasItem(DEFAULT_DESPESASEXEANTERIORES.toString())))
            .andExpect(jsonPath("$.[*].idfaturacontrato").value(hasItem(DEFAULT_IDFATURACONTRATO)))
            .andExpect(jsonPath("$.[*].nfatura").value(hasItem(DEFAULT_NFATURA)))
            .andExpect(jsonPath("$.[*].nprocesso").value(hasItem(DEFAULT_NPROCESSO)))
            .andExpect(jsonPath("$.[*].nsolicitacao").value(hasItem(DEFAULT_NSOLICITACAO)))
            .andExpect(jsonPath("$.[*].parcela").value(hasItem(DEFAULT_PARCELA)))
            .andExpect(jsonPath("$.[*].nobancaria").value(hasItem(DEFAULT_NOBANCARIA)))
            .andExpect(jsonPath("$.[*].nop").value(hasItem(DEFAULT_NOP)))
            .andExpect(jsonPath("$.[*].nummedicao").value(hasItem(DEFAULT_NUMMEDICAO)))
            .andExpect(jsonPath("$.[*].restosapagar").value(hasItem(DEFAULT_RESTOSAPAGAR.toString())))
            .andExpect(jsonPath("$.[*].tipomedicao").value(hasItem(DEFAULT_TIPOMEDICAO.toString())))
            .andExpect(jsonPath("$.[*].valorpi").value(hasItem(DEFAULT_VALORPI.intValue())))
            .andExpect(jsonPath("$.[*].valorreajuste").value(hasItem(DEFAULT_VALORREAJUSTE.intValue())))
            .andExpect(jsonPath("$.[*].valorus").value(hasItem(DEFAULT_VALORUS.intValue())))
            .andExpect(jsonPath("$.[*].vreajuste").value(hasItem(DEFAULT_VREAJUSTE.intValue())))
            .andExpect(jsonPath("$.[*].aportelocal").value(hasItem(DEFAULT_APORTELOCAL.intValue())))
            .andExpect(jsonPath("$.[*].aporteagente").value(hasItem(DEFAULT_APORTEAGENTE.intValue())));
    }

    @Test
    @Transactional
    public void getFaturacontrato() throws Exception {
        // Initialize the database
        faturacontratoRepository.saveAndFlush(faturacontrato);

        // Get the faturacontrato
        restFaturacontratoMockMvc.perform(get("/api/faturacontratoes/{id}", faturacontrato.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(faturacontrato.getId().intValue()))
            .andExpect(jsonPath("$.dataop").value(sameInstant(DEFAULT_DATAOP)))
            .andExpect(jsonPath("$.despesasexeanteriores").value(DEFAULT_DESPESASEXEANTERIORES.toString()))
            .andExpect(jsonPath("$.idfaturacontrato").value(DEFAULT_IDFATURACONTRATO))
            .andExpect(jsonPath("$.nfatura").value(DEFAULT_NFATURA))
            .andExpect(jsonPath("$.nprocesso").value(DEFAULT_NPROCESSO))
            .andExpect(jsonPath("$.nsolicitacao").value(DEFAULT_NSOLICITACAO))
            .andExpect(jsonPath("$.parcela").value(DEFAULT_PARCELA))
            .andExpect(jsonPath("$.nobancaria").value(DEFAULT_NOBANCARIA))
            .andExpect(jsonPath("$.nop").value(DEFAULT_NOP))
            .andExpect(jsonPath("$.nummedicao").value(DEFAULT_NUMMEDICAO))
            .andExpect(jsonPath("$.restosapagar").value(DEFAULT_RESTOSAPAGAR.toString()))
            .andExpect(jsonPath("$.tipomedicao").value(DEFAULT_TIPOMEDICAO.toString()))
            .andExpect(jsonPath("$.valorpi").value(DEFAULT_VALORPI.intValue()))
            .andExpect(jsonPath("$.valorreajuste").value(DEFAULT_VALORREAJUSTE.intValue()))
            .andExpect(jsonPath("$.valorus").value(DEFAULT_VALORUS.intValue()))
            .andExpect(jsonPath("$.vreajuste").value(DEFAULT_VREAJUSTE.intValue()))
            .andExpect(jsonPath("$.aportelocal").value(DEFAULT_APORTELOCAL.intValue()))
            .andExpect(jsonPath("$.aporteagente").value(DEFAULT_APORTEAGENTE.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingFaturacontrato() throws Exception {
        // Get the faturacontrato
        restFaturacontratoMockMvc.perform(get("/api/faturacontratoes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFaturacontrato() throws Exception {
        // Initialize the database
        faturacontratoRepository.saveAndFlush(faturacontrato);
        int databaseSizeBeforeUpdate = faturacontratoRepository.findAll().size();

        // Update the faturacontrato
        Faturacontrato updatedFaturacontrato = faturacontratoRepository.findOne(faturacontrato.getId());
        updatedFaturacontrato
            .dataop(UPDATED_DATAOP)
            .despesasexeanteriores(UPDATED_DESPESASEXEANTERIORES)
            .idfaturacontrato(UPDATED_IDFATURACONTRATO)
            .nfatura(UPDATED_NFATURA)
            .nprocesso(UPDATED_NPROCESSO)
            .nsolicitacao(UPDATED_NSOLICITACAO)
            .parcela(UPDATED_PARCELA)
            .nobancaria(UPDATED_NOBANCARIA)
            .nop(UPDATED_NOP)
            .nummedicao(UPDATED_NUMMEDICAO)
            .restosapagar(UPDATED_RESTOSAPAGAR)
            .tipomedicao(UPDATED_TIPOMEDICAO)
            .valorpi(UPDATED_VALORPI)
            .valorreajuste(UPDATED_VALORREAJUSTE)
            .valorus(UPDATED_VALORUS)
            .vreajuste(UPDATED_VREAJUSTE)
            .aportelocal(UPDATED_APORTELOCAL)
            .aporteagente(UPDATED_APORTEAGENTE);
        FaturacontratoDTO faturacontratoDTO = faturacontratoMapper.toDto(updatedFaturacontrato);

        restFaturacontratoMockMvc.perform(put("/api/faturacontratoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(faturacontratoDTO)))
            .andExpect(status().isOk());

        // Validate the Faturacontrato in the database
        List<Faturacontrato> faturacontratoList = faturacontratoRepository.findAll();
        assertThat(faturacontratoList).hasSize(databaseSizeBeforeUpdate);
        Faturacontrato testFaturacontrato = faturacontratoList.get(faturacontratoList.size() - 1);
        assertThat(testFaturacontrato.getDataop()).isEqualTo(UPDATED_DATAOP);
        assertThat(testFaturacontrato.getDespesasexeanteriores()).isEqualTo(UPDATED_DESPESASEXEANTERIORES);
        assertThat(testFaturacontrato.getIdfaturacontrato()).isEqualTo(UPDATED_IDFATURACONTRATO);
        assertThat(testFaturacontrato.getNfatura()).isEqualTo(UPDATED_NFATURA);
        assertThat(testFaturacontrato.getNprocesso()).isEqualTo(UPDATED_NPROCESSO);
        assertThat(testFaturacontrato.getNsolicitacao()).isEqualTo(UPDATED_NSOLICITACAO);
        assertThat(testFaturacontrato.getParcela()).isEqualTo(UPDATED_PARCELA);
        assertThat(testFaturacontrato.getNobancaria()).isEqualTo(UPDATED_NOBANCARIA);
        assertThat(testFaturacontrato.getNop()).isEqualTo(UPDATED_NOP);
        assertThat(testFaturacontrato.getNummedicao()).isEqualTo(UPDATED_NUMMEDICAO);
        assertThat(testFaturacontrato.getRestosapagar()).isEqualTo(UPDATED_RESTOSAPAGAR);
        assertThat(testFaturacontrato.getTipomedicao()).isEqualTo(UPDATED_TIPOMEDICAO);
        assertThat(testFaturacontrato.getValorpi()).isEqualTo(UPDATED_VALORPI);
        assertThat(testFaturacontrato.getValorreajuste()).isEqualTo(UPDATED_VALORREAJUSTE);
        assertThat(testFaturacontrato.getValorus()).isEqualTo(UPDATED_VALORUS);
        assertThat(testFaturacontrato.getVreajuste()).isEqualTo(UPDATED_VREAJUSTE);
        assertThat(testFaturacontrato.getAportelocal()).isEqualTo(UPDATED_APORTELOCAL);
        assertThat(testFaturacontrato.getAporteagente()).isEqualTo(UPDATED_APORTEAGENTE);
    }

    @Test
    @Transactional
    public void updateNonExistingFaturacontrato() throws Exception {
        int databaseSizeBeforeUpdate = faturacontratoRepository.findAll().size();

        // Create the Faturacontrato
        FaturacontratoDTO faturacontratoDTO = faturacontratoMapper.toDto(faturacontrato);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFaturacontratoMockMvc.perform(put("/api/faturacontratoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(faturacontratoDTO)))
            .andExpect(status().isCreated());

        // Validate the Faturacontrato in the database
        List<Faturacontrato> faturacontratoList = faturacontratoRepository.findAll();
        assertThat(faturacontratoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFaturacontrato() throws Exception {
        // Initialize the database
        faturacontratoRepository.saveAndFlush(faturacontrato);
        int databaseSizeBeforeDelete = faturacontratoRepository.findAll().size();

        // Get the faturacontrato
        restFaturacontratoMockMvc.perform(delete("/api/faturacontratoes/{id}", faturacontrato.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Faturacontrato> faturacontratoList = faturacontratoRepository.findAll();
        assertThat(faturacontratoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Faturacontrato.class);
        Faturacontrato faturacontrato1 = new Faturacontrato();
        faturacontrato1.setId(1L);
        Faturacontrato faturacontrato2 = new Faturacontrato();
        faturacontrato2.setId(faturacontrato1.getId());
        assertThat(faturacontrato1).isEqualTo(faturacontrato2);
        faturacontrato2.setId(2L);
        assertThat(faturacontrato1).isNotEqualTo(faturacontrato2);
        faturacontrato1.setId(null);
        assertThat(faturacontrato1).isNotEqualTo(faturacontrato2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FaturacontratoDTO.class);
        FaturacontratoDTO faturacontratoDTO1 = new FaturacontratoDTO();
        faturacontratoDTO1.setId(1L);
        FaturacontratoDTO faturacontratoDTO2 = new FaturacontratoDTO();
        assertThat(faturacontratoDTO1).isNotEqualTo(faturacontratoDTO2);
        faturacontratoDTO2.setId(faturacontratoDTO1.getId());
        assertThat(faturacontratoDTO1).isEqualTo(faturacontratoDTO2);
        faturacontratoDTO2.setId(2L);
        assertThat(faturacontratoDTO1).isNotEqualTo(faturacontratoDTO2);
        faturacontratoDTO1.setId(null);
        assertThat(faturacontratoDTO1).isNotEqualTo(faturacontratoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(faturacontratoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(faturacontratoMapper.fromId(null)).isNull();
    }
}
