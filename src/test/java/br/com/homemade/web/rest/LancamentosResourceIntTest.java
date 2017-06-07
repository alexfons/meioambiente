package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Lancamentos;
import br.com.homemade.repository.LancamentosRepository;
import br.com.homemade.service.dto.LancamentosDTO;
import br.com.homemade.service.mapper.LancamentosMapper;
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
 * Test class for the LancamentosResource REST controller.
 *
 * @see LancamentosResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class LancamentosResourceIntTest {

    private static final ZonedDateTime DEFAULT_DATALAN = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATALAN = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_HISTORICO = "AAAAAAAAAA";
    private static final String UPDATED_HISTORICO = "BBBBBBBBBB";

    private static final String DEFAULT_RECURSO = "AAAAAAAAAA";
    private static final String UPDATED_RECURSO = "BBBBBBBBBB";

    private static final String DEFAULT_TIPOMEDICAO = "AAAAAAAAAA";
    private static final String UPDATED_TIPOMEDICAO = "BBBBBBBBBB";

    private static final Integer DEFAULT_IDLANCAMENTOS = 1;
    private static final Integer UPDATED_IDLANCAMENTOS = 2;

    private static final Integer DEFAULT_NUMMEDICAO = 1;
    private static final Integer UPDATED_NUMMEDICAO = 2;

    private static final BigDecimal DEFAULT_VALORRDEBITO = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALORRDEBITO = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VALORUSDEBITO = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALORUSDEBITO = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VALORRCREDITO = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALORRCREDITO = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VALORUSCREDITO = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALORUSCREDITO = new BigDecimal(2);

    @Autowired
    private LancamentosRepository lancamentosRepository;

    @Autowired
    private LancamentosMapper lancamentosMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLancamentosMockMvc;

    private Lancamentos lancamentos;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        LancamentosResource lancamentosResource = new LancamentosResource(lancamentosRepository, lancamentosMapper);
        this.restLancamentosMockMvc = MockMvcBuilders.standaloneSetup(lancamentosResource)
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
    public static Lancamentos createEntity(EntityManager em) {
        Lancamentos lancamentos = new Lancamentos()
            .datalan(DEFAULT_DATALAN)
            .historico(DEFAULT_HISTORICO)
            .recurso(DEFAULT_RECURSO)
            .tipomedicao(DEFAULT_TIPOMEDICAO)
            .idlancamentos(DEFAULT_IDLANCAMENTOS)
            .nummedicao(DEFAULT_NUMMEDICAO)
            .valorrdebito(DEFAULT_VALORRDEBITO)
            .valorusdebito(DEFAULT_VALORUSDEBITO)
            .valorrcredito(DEFAULT_VALORRCREDITO)
            .valoruscredito(DEFAULT_VALORUSCREDITO);
        return lancamentos;
    }

    @Before
    public void initTest() {
        lancamentos = createEntity(em);
    }

    @Test
    @Transactional
    public void createLancamentos() throws Exception {
        int databaseSizeBeforeCreate = lancamentosRepository.findAll().size();

        // Create the Lancamentos
        LancamentosDTO lancamentosDTO = lancamentosMapper.toDto(lancamentos);
        restLancamentosMockMvc.perform(post("/api/lancamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lancamentosDTO)))
            .andExpect(status().isCreated());

        // Validate the Lancamentos in the database
        List<Lancamentos> lancamentosList = lancamentosRepository.findAll();
        assertThat(lancamentosList).hasSize(databaseSizeBeforeCreate + 1);
        Lancamentos testLancamentos = lancamentosList.get(lancamentosList.size() - 1);
        assertThat(testLancamentos.getDatalan()).isEqualTo(DEFAULT_DATALAN);
        assertThat(testLancamentos.getHistorico()).isEqualTo(DEFAULT_HISTORICO);
        assertThat(testLancamentos.getRecurso()).isEqualTo(DEFAULT_RECURSO);
        assertThat(testLancamentos.getTipomedicao()).isEqualTo(DEFAULT_TIPOMEDICAO);
        assertThat(testLancamentos.getIdlancamentos()).isEqualTo(DEFAULT_IDLANCAMENTOS);
        assertThat(testLancamentos.getNummedicao()).isEqualTo(DEFAULT_NUMMEDICAO);
        assertThat(testLancamentos.getValorrdebito()).isEqualTo(DEFAULT_VALORRDEBITO);
        assertThat(testLancamentos.getValorusdebito()).isEqualTo(DEFAULT_VALORUSDEBITO);
        assertThat(testLancamentos.getValorrcredito()).isEqualTo(DEFAULT_VALORRCREDITO);
        assertThat(testLancamentos.getValoruscredito()).isEqualTo(DEFAULT_VALORUSCREDITO);
    }

    @Test
    @Transactional
    public void createLancamentosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = lancamentosRepository.findAll().size();

        // Create the Lancamentos with an existing ID
        lancamentos.setId(1L);
        LancamentosDTO lancamentosDTO = lancamentosMapper.toDto(lancamentos);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLancamentosMockMvc.perform(post("/api/lancamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lancamentosDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Lancamentos> lancamentosList = lancamentosRepository.findAll();
        assertThat(lancamentosList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllLancamentos() throws Exception {
        // Initialize the database
        lancamentosRepository.saveAndFlush(lancamentos);

        // Get all the lancamentosList
        restLancamentosMockMvc.perform(get("/api/lancamentos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lancamentos.getId().intValue())))
            .andExpect(jsonPath("$.[*].datalan").value(hasItem(sameInstant(DEFAULT_DATALAN))))
            .andExpect(jsonPath("$.[*].historico").value(hasItem(DEFAULT_HISTORICO.toString())))
            .andExpect(jsonPath("$.[*].recurso").value(hasItem(DEFAULT_RECURSO.toString())))
            .andExpect(jsonPath("$.[*].tipomedicao").value(hasItem(DEFAULT_TIPOMEDICAO.toString())))
            .andExpect(jsonPath("$.[*].idlancamentos").value(hasItem(DEFAULT_IDLANCAMENTOS)))
            .andExpect(jsonPath("$.[*].nummedicao").value(hasItem(DEFAULT_NUMMEDICAO)))
            .andExpect(jsonPath("$.[*].valorrdebito").value(hasItem(DEFAULT_VALORRDEBITO.intValue())))
            .andExpect(jsonPath("$.[*].valorusdebito").value(hasItem(DEFAULT_VALORUSDEBITO.intValue())))
            .andExpect(jsonPath("$.[*].valorrcredito").value(hasItem(DEFAULT_VALORRCREDITO.intValue())))
            .andExpect(jsonPath("$.[*].valoruscredito").value(hasItem(DEFAULT_VALORUSCREDITO.intValue())));
    }

    @Test
    @Transactional
    public void getLancamentos() throws Exception {
        // Initialize the database
        lancamentosRepository.saveAndFlush(lancamentos);

        // Get the lancamentos
        restLancamentosMockMvc.perform(get("/api/lancamentos/{id}", lancamentos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(lancamentos.getId().intValue()))
            .andExpect(jsonPath("$.datalan").value(sameInstant(DEFAULT_DATALAN)))
            .andExpect(jsonPath("$.historico").value(DEFAULT_HISTORICO.toString()))
            .andExpect(jsonPath("$.recurso").value(DEFAULT_RECURSO.toString()))
            .andExpect(jsonPath("$.tipomedicao").value(DEFAULT_TIPOMEDICAO.toString()))
            .andExpect(jsonPath("$.idlancamentos").value(DEFAULT_IDLANCAMENTOS))
            .andExpect(jsonPath("$.nummedicao").value(DEFAULT_NUMMEDICAO))
            .andExpect(jsonPath("$.valorrdebito").value(DEFAULT_VALORRDEBITO.intValue()))
            .andExpect(jsonPath("$.valorusdebito").value(DEFAULT_VALORUSDEBITO.intValue()))
            .andExpect(jsonPath("$.valorrcredito").value(DEFAULT_VALORRCREDITO.intValue()))
            .andExpect(jsonPath("$.valoruscredito").value(DEFAULT_VALORUSCREDITO.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingLancamentos() throws Exception {
        // Get the lancamentos
        restLancamentosMockMvc.perform(get("/api/lancamentos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLancamentos() throws Exception {
        // Initialize the database
        lancamentosRepository.saveAndFlush(lancamentos);
        int databaseSizeBeforeUpdate = lancamentosRepository.findAll().size();

        // Update the lancamentos
        Lancamentos updatedLancamentos = lancamentosRepository.findOne(lancamentos.getId());
        updatedLancamentos
            .datalan(UPDATED_DATALAN)
            .historico(UPDATED_HISTORICO)
            .recurso(UPDATED_RECURSO)
            .tipomedicao(UPDATED_TIPOMEDICAO)
            .idlancamentos(UPDATED_IDLANCAMENTOS)
            .nummedicao(UPDATED_NUMMEDICAO)
            .valorrdebito(UPDATED_VALORRDEBITO)
            .valorusdebito(UPDATED_VALORUSDEBITO)
            .valorrcredito(UPDATED_VALORRCREDITO)
            .valoruscredito(UPDATED_VALORUSCREDITO);
        LancamentosDTO lancamentosDTO = lancamentosMapper.toDto(updatedLancamentos);

        restLancamentosMockMvc.perform(put("/api/lancamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lancamentosDTO)))
            .andExpect(status().isOk());

        // Validate the Lancamentos in the database
        List<Lancamentos> lancamentosList = lancamentosRepository.findAll();
        assertThat(lancamentosList).hasSize(databaseSizeBeforeUpdate);
        Lancamentos testLancamentos = lancamentosList.get(lancamentosList.size() - 1);
        assertThat(testLancamentos.getDatalan()).isEqualTo(UPDATED_DATALAN);
        assertThat(testLancamentos.getHistorico()).isEqualTo(UPDATED_HISTORICO);
        assertThat(testLancamentos.getRecurso()).isEqualTo(UPDATED_RECURSO);
        assertThat(testLancamentos.getTipomedicao()).isEqualTo(UPDATED_TIPOMEDICAO);
        assertThat(testLancamentos.getIdlancamentos()).isEqualTo(UPDATED_IDLANCAMENTOS);
        assertThat(testLancamentos.getNummedicao()).isEqualTo(UPDATED_NUMMEDICAO);
        assertThat(testLancamentos.getValorrdebito()).isEqualTo(UPDATED_VALORRDEBITO);
        assertThat(testLancamentos.getValorusdebito()).isEqualTo(UPDATED_VALORUSDEBITO);
        assertThat(testLancamentos.getValorrcredito()).isEqualTo(UPDATED_VALORRCREDITO);
        assertThat(testLancamentos.getValoruscredito()).isEqualTo(UPDATED_VALORUSCREDITO);
    }

    @Test
    @Transactional
    public void updateNonExistingLancamentos() throws Exception {
        int databaseSizeBeforeUpdate = lancamentosRepository.findAll().size();

        // Create the Lancamentos
        LancamentosDTO lancamentosDTO = lancamentosMapper.toDto(lancamentos);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restLancamentosMockMvc.perform(put("/api/lancamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lancamentosDTO)))
            .andExpect(status().isCreated());

        // Validate the Lancamentos in the database
        List<Lancamentos> lancamentosList = lancamentosRepository.findAll();
        assertThat(lancamentosList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteLancamentos() throws Exception {
        // Initialize the database
        lancamentosRepository.saveAndFlush(lancamentos);
        int databaseSizeBeforeDelete = lancamentosRepository.findAll().size();

        // Get the lancamentos
        restLancamentosMockMvc.perform(delete("/api/lancamentos/{id}", lancamentos.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Lancamentos> lancamentosList = lancamentosRepository.findAll();
        assertThat(lancamentosList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Lancamentos.class);
        Lancamentos lancamentos1 = new Lancamentos();
        lancamentos1.setId(1L);
        Lancamentos lancamentos2 = new Lancamentos();
        lancamentos2.setId(lancamentos1.getId());
        assertThat(lancamentos1).isEqualTo(lancamentos2);
        lancamentos2.setId(2L);
        assertThat(lancamentos1).isNotEqualTo(lancamentos2);
        lancamentos1.setId(null);
        assertThat(lancamentos1).isNotEqualTo(lancamentos2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LancamentosDTO.class);
        LancamentosDTO lancamentosDTO1 = new LancamentosDTO();
        lancamentosDTO1.setId(1L);
        LancamentosDTO lancamentosDTO2 = new LancamentosDTO();
        assertThat(lancamentosDTO1).isNotEqualTo(lancamentosDTO2);
        lancamentosDTO2.setId(lancamentosDTO1.getId());
        assertThat(lancamentosDTO1).isEqualTo(lancamentosDTO2);
        lancamentosDTO2.setId(2L);
        assertThat(lancamentosDTO1).isNotEqualTo(lancamentosDTO2);
        lancamentosDTO1.setId(null);
        assertThat(lancamentosDTO1).isNotEqualTo(lancamentosDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(lancamentosMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(lancamentosMapper.fromId(null)).isNull();
    }
}
