package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Planoaquisicoes;
import br.com.homemade.repository.PlanoaquisicoesRepository;
import br.com.homemade.service.dto.PlanoaquisicoesDTO;
import br.com.homemade.service.mapper.PlanoaquisicoesMapper;
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
 * Test class for the PlanoaquisicoesResource REST controller.
 *
 * @see PlanoaquisicoesResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class PlanoaquisicoesResourceIntTest {

    private static final ZonedDateTime DEFAULT_AVISOLICITACAO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_AVISOLICITACAO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final BigDecimal DEFAULT_CUSTOESTIMADO = new BigDecimal(1);
    private static final BigDecimal UPDATED_CUSTOESTIMADO = new BigDecimal(2);

    private static final BigDecimal DEFAULT_APORTELOCAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_APORTELOCAL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_APORTEAGENTE = new BigDecimal(1);
    private static final BigDecimal UPDATED_APORTEAGENTE = new BigDecimal(2);

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final String DEFAULT_METODO = "AAAAAAAAAA";
    private static final String UPDATED_METODO = "BBBBBBBBBB";

    private static final String DEFAULT_REVISAO = "AAAAAAAAAA";
    private static final String UPDATED_REVISAO = "BBBBBBBBBB";

    private static final String DEFAULT_PREQUALIFICADO = "AAAAAAAAAA";
    private static final String UPDATED_PREQUALIFICADO = "BBBBBBBBBB";

    private static final String DEFAULT_SITUACAO = "AAAAAAAAAA";
    private static final String UPDATED_SITUACAO = "BBBBBBBBBB";

    private static final Integer DEFAULT_IDPLANOAQUISICOES = 1;
    private static final Integer UPDATED_IDPLANOAQUISICOES = 2;

    @Autowired
    private PlanoaquisicoesRepository planoaquisicoesRepository;

    @Autowired
    private PlanoaquisicoesMapper planoaquisicoesMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPlanoaquisicoesMockMvc;

    private Planoaquisicoes planoaquisicoes;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PlanoaquisicoesResource planoaquisicoesResource = new PlanoaquisicoesResource(planoaquisicoesRepository, planoaquisicoesMapper);
        this.restPlanoaquisicoesMockMvc = MockMvcBuilders.standaloneSetup(planoaquisicoesResource)
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
    public static Planoaquisicoes createEntity(EntityManager em) {
        Planoaquisicoes planoaquisicoes = new Planoaquisicoes()
            .avisolicitacao(DEFAULT_AVISOLICITACAO)
            .custoestimado(DEFAULT_CUSTOESTIMADO)
            .aportelocal(DEFAULT_APORTELOCAL)
            .aporteagente(DEFAULT_APORTEAGENTE)
            .descricao(DEFAULT_DESCRICAO)
            .metodo(DEFAULT_METODO)
            .revisao(DEFAULT_REVISAO)
            .prequalificado(DEFAULT_PREQUALIFICADO)
            .situacao(DEFAULT_SITUACAO)
            .idplanoaquisicoes(DEFAULT_IDPLANOAQUISICOES);
        return planoaquisicoes;
    }

    @Before
    public void initTest() {
        planoaquisicoes = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlanoaquisicoes() throws Exception {
        int databaseSizeBeforeCreate = planoaquisicoesRepository.findAll().size();

        // Create the Planoaquisicoes
        PlanoaquisicoesDTO planoaquisicoesDTO = planoaquisicoesMapper.toDto(planoaquisicoes);
        restPlanoaquisicoesMockMvc.perform(post("/api/planoaquisicoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planoaquisicoesDTO)))
            .andExpect(status().isCreated());

        // Validate the Planoaquisicoes in the database
        List<Planoaquisicoes> planoaquisicoesList = planoaquisicoesRepository.findAll();
        assertThat(planoaquisicoesList).hasSize(databaseSizeBeforeCreate + 1);
        Planoaquisicoes testPlanoaquisicoes = planoaquisicoesList.get(planoaquisicoesList.size() - 1);
        assertThat(testPlanoaquisicoes.getAvisolicitacao()).isEqualTo(DEFAULT_AVISOLICITACAO);
        assertThat(testPlanoaquisicoes.getCustoestimado()).isEqualTo(DEFAULT_CUSTOESTIMADO);
        assertThat(testPlanoaquisicoes.getAportelocal()).isEqualTo(DEFAULT_APORTELOCAL);
        assertThat(testPlanoaquisicoes.getAporteagente()).isEqualTo(DEFAULT_APORTEAGENTE);
        assertThat(testPlanoaquisicoes.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testPlanoaquisicoes.getMetodo()).isEqualTo(DEFAULT_METODO);
        assertThat(testPlanoaquisicoes.getRevisao()).isEqualTo(DEFAULT_REVISAO);
        assertThat(testPlanoaquisicoes.getPrequalificado()).isEqualTo(DEFAULT_PREQUALIFICADO);
        assertThat(testPlanoaquisicoes.getSituacao()).isEqualTo(DEFAULT_SITUACAO);
        assertThat(testPlanoaquisicoes.getIdplanoaquisicoes()).isEqualTo(DEFAULT_IDPLANOAQUISICOES);
    }

    @Test
    @Transactional
    public void createPlanoaquisicoesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = planoaquisicoesRepository.findAll().size();

        // Create the Planoaquisicoes with an existing ID
        planoaquisicoes.setId(1L);
        PlanoaquisicoesDTO planoaquisicoesDTO = planoaquisicoesMapper.toDto(planoaquisicoes);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlanoaquisicoesMockMvc.perform(post("/api/planoaquisicoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planoaquisicoesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Planoaquisicoes> planoaquisicoesList = planoaquisicoesRepository.findAll();
        assertThat(planoaquisicoesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPlanoaquisicoes() throws Exception {
        // Initialize the database
        planoaquisicoesRepository.saveAndFlush(planoaquisicoes);

        // Get all the planoaquisicoesList
        restPlanoaquisicoesMockMvc.perform(get("/api/planoaquisicoes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planoaquisicoes.getId().intValue())))
            .andExpect(jsonPath("$.[*].avisolicitacao").value(hasItem(sameInstant(DEFAULT_AVISOLICITACAO))))
            .andExpect(jsonPath("$.[*].custoestimado").value(hasItem(DEFAULT_CUSTOESTIMADO.intValue())))
            .andExpect(jsonPath("$.[*].aportelocal").value(hasItem(DEFAULT_APORTELOCAL.intValue())))
            .andExpect(jsonPath("$.[*].aporteagente").value(hasItem(DEFAULT_APORTEAGENTE.intValue())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].metodo").value(hasItem(DEFAULT_METODO.toString())))
            .andExpect(jsonPath("$.[*].revisao").value(hasItem(DEFAULT_REVISAO.toString())))
            .andExpect(jsonPath("$.[*].prequalificado").value(hasItem(DEFAULT_PREQUALIFICADO.toString())))
            .andExpect(jsonPath("$.[*].situacao").value(hasItem(DEFAULT_SITUACAO.toString())))
            .andExpect(jsonPath("$.[*].idplanoaquisicoes").value(hasItem(DEFAULT_IDPLANOAQUISICOES)));
    }

    @Test
    @Transactional
    public void getPlanoaquisicoes() throws Exception {
        // Initialize the database
        planoaquisicoesRepository.saveAndFlush(planoaquisicoes);

        // Get the planoaquisicoes
        restPlanoaquisicoesMockMvc.perform(get("/api/planoaquisicoes/{id}", planoaquisicoes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(planoaquisicoes.getId().intValue()))
            .andExpect(jsonPath("$.avisolicitacao").value(sameInstant(DEFAULT_AVISOLICITACAO)))
            .andExpect(jsonPath("$.custoestimado").value(DEFAULT_CUSTOESTIMADO.intValue()))
            .andExpect(jsonPath("$.aportelocal").value(DEFAULT_APORTELOCAL.intValue()))
            .andExpect(jsonPath("$.aporteagente").value(DEFAULT_APORTEAGENTE.intValue()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.metodo").value(DEFAULT_METODO.toString()))
            .andExpect(jsonPath("$.revisao").value(DEFAULT_REVISAO.toString()))
            .andExpect(jsonPath("$.prequalificado").value(DEFAULT_PREQUALIFICADO.toString()))
            .andExpect(jsonPath("$.situacao").value(DEFAULT_SITUACAO.toString()))
            .andExpect(jsonPath("$.idplanoaquisicoes").value(DEFAULT_IDPLANOAQUISICOES));
    }

    @Test
    @Transactional
    public void getNonExistingPlanoaquisicoes() throws Exception {
        // Get the planoaquisicoes
        restPlanoaquisicoesMockMvc.perform(get("/api/planoaquisicoes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlanoaquisicoes() throws Exception {
        // Initialize the database
        planoaquisicoesRepository.saveAndFlush(planoaquisicoes);
        int databaseSizeBeforeUpdate = planoaquisicoesRepository.findAll().size();

        // Update the planoaquisicoes
        Planoaquisicoes updatedPlanoaquisicoes = planoaquisicoesRepository.findOne(planoaquisicoes.getId());
        updatedPlanoaquisicoes
            .avisolicitacao(UPDATED_AVISOLICITACAO)
            .custoestimado(UPDATED_CUSTOESTIMADO)
            .aportelocal(UPDATED_APORTELOCAL)
            .aporteagente(UPDATED_APORTEAGENTE)
            .descricao(UPDATED_DESCRICAO)
            .metodo(UPDATED_METODO)
            .revisao(UPDATED_REVISAO)
            .prequalificado(UPDATED_PREQUALIFICADO)
            .situacao(UPDATED_SITUACAO)
            .idplanoaquisicoes(UPDATED_IDPLANOAQUISICOES);
        PlanoaquisicoesDTO planoaquisicoesDTO = planoaquisicoesMapper.toDto(updatedPlanoaquisicoes);

        restPlanoaquisicoesMockMvc.perform(put("/api/planoaquisicoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planoaquisicoesDTO)))
            .andExpect(status().isOk());

        // Validate the Planoaquisicoes in the database
        List<Planoaquisicoes> planoaquisicoesList = planoaquisicoesRepository.findAll();
        assertThat(planoaquisicoesList).hasSize(databaseSizeBeforeUpdate);
        Planoaquisicoes testPlanoaquisicoes = planoaquisicoesList.get(planoaquisicoesList.size() - 1);
        assertThat(testPlanoaquisicoes.getAvisolicitacao()).isEqualTo(UPDATED_AVISOLICITACAO);
        assertThat(testPlanoaquisicoes.getCustoestimado()).isEqualTo(UPDATED_CUSTOESTIMADO);
        assertThat(testPlanoaquisicoes.getAportelocal()).isEqualTo(UPDATED_APORTELOCAL);
        assertThat(testPlanoaquisicoes.getAporteagente()).isEqualTo(UPDATED_APORTEAGENTE);
        assertThat(testPlanoaquisicoes.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testPlanoaquisicoes.getMetodo()).isEqualTo(UPDATED_METODO);
        assertThat(testPlanoaquisicoes.getRevisao()).isEqualTo(UPDATED_REVISAO);
        assertThat(testPlanoaquisicoes.getPrequalificado()).isEqualTo(UPDATED_PREQUALIFICADO);
        assertThat(testPlanoaquisicoes.getSituacao()).isEqualTo(UPDATED_SITUACAO);
        assertThat(testPlanoaquisicoes.getIdplanoaquisicoes()).isEqualTo(UPDATED_IDPLANOAQUISICOES);
    }

    @Test
    @Transactional
    public void updateNonExistingPlanoaquisicoes() throws Exception {
        int databaseSizeBeforeUpdate = planoaquisicoesRepository.findAll().size();

        // Create the Planoaquisicoes
        PlanoaquisicoesDTO planoaquisicoesDTO = planoaquisicoesMapper.toDto(planoaquisicoes);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPlanoaquisicoesMockMvc.perform(put("/api/planoaquisicoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planoaquisicoesDTO)))
            .andExpect(status().isCreated());

        // Validate the Planoaquisicoes in the database
        List<Planoaquisicoes> planoaquisicoesList = planoaquisicoesRepository.findAll();
        assertThat(planoaquisicoesList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePlanoaquisicoes() throws Exception {
        // Initialize the database
        planoaquisicoesRepository.saveAndFlush(planoaquisicoes);
        int databaseSizeBeforeDelete = planoaquisicoesRepository.findAll().size();

        // Get the planoaquisicoes
        restPlanoaquisicoesMockMvc.perform(delete("/api/planoaquisicoes/{id}", planoaquisicoes.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Planoaquisicoes> planoaquisicoesList = planoaquisicoesRepository.findAll();
        assertThat(planoaquisicoesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Planoaquisicoes.class);
        Planoaquisicoes planoaquisicoes1 = new Planoaquisicoes();
        planoaquisicoes1.setId(1L);
        Planoaquisicoes planoaquisicoes2 = new Planoaquisicoes();
        planoaquisicoes2.setId(planoaquisicoes1.getId());
        assertThat(planoaquisicoes1).isEqualTo(planoaquisicoes2);
        planoaquisicoes2.setId(2L);
        assertThat(planoaquisicoes1).isNotEqualTo(planoaquisicoes2);
        planoaquisicoes1.setId(null);
        assertThat(planoaquisicoes1).isNotEqualTo(planoaquisicoes2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlanoaquisicoesDTO.class);
        PlanoaquisicoesDTO planoaquisicoesDTO1 = new PlanoaquisicoesDTO();
        planoaquisicoesDTO1.setId(1L);
        PlanoaquisicoesDTO planoaquisicoesDTO2 = new PlanoaquisicoesDTO();
        assertThat(planoaquisicoesDTO1).isNotEqualTo(planoaquisicoesDTO2);
        planoaquisicoesDTO2.setId(planoaquisicoesDTO1.getId());
        assertThat(planoaquisicoesDTO1).isEqualTo(planoaquisicoesDTO2);
        planoaquisicoesDTO2.setId(2L);
        assertThat(planoaquisicoesDTO1).isNotEqualTo(planoaquisicoesDTO2);
        planoaquisicoesDTO1.setId(null);
        assertThat(planoaquisicoesDTO1).isNotEqualTo(planoaquisicoesDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(planoaquisicoesMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(planoaquisicoesMapper.fromId(null)).isNull();
    }
}
