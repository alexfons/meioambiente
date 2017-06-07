package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Condicionante;
import br.com.homemade.repository.CondicionanteRepository;
import br.com.homemade.service.dto.CondicionanteDTO;
import br.com.homemade.service.mapper.CondicionanteMapper;
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
 * Test class for the CondicionanteResource REST controller.
 *
 * @see CondicionanteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class CondicionanteResourceIntTest {

    private static final Boolean DEFAULT_ATIVO = false;
    private static final Boolean UPDATED_ATIVO = true;

    private static final String DEFAULT_CONTEUDO = "AAAAAAAAAA";
    private static final String UPDATED_CONTEUDO = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final String DEFAULT_OBSERVACAO = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVACAO = "BBBBBBBBBB";

    private static final String DEFAULT_SITUACAO = "AAAAAAAAAA";
    private static final String UPDATED_SITUACAO = "BBBBBBBBBB";

    @Autowired
    private CondicionanteRepository condicionanteRepository;

    @Autowired
    private CondicionanteMapper condicionanteMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCondicionanteMockMvc;

    private Condicionante condicionante;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CondicionanteResource condicionanteResource = new CondicionanteResource(condicionanteRepository, condicionanteMapper);
        this.restCondicionanteMockMvc = MockMvcBuilders.standaloneSetup(condicionanteResource)
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
    public static Condicionante createEntity(EntityManager em) {
        Condicionante condicionante = new Condicionante()
            .ativo(DEFAULT_ATIVO)
            .conteudo(DEFAULT_CONTEUDO)
            .data(DEFAULT_DATA)
            .descricao(DEFAULT_DESCRICAO)
            .observacao(DEFAULT_OBSERVACAO)
            .situacao(DEFAULT_SITUACAO);
        return condicionante;
    }

    @Before
    public void initTest() {
        condicionante = createEntity(em);
    }

    @Test
    @Transactional
    public void createCondicionante() throws Exception {
        int databaseSizeBeforeCreate = condicionanteRepository.findAll().size();

        // Create the Condicionante
        CondicionanteDTO condicionanteDTO = condicionanteMapper.toDto(condicionante);
        restCondicionanteMockMvc.perform(post("/api/condicionantes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(condicionanteDTO)))
            .andExpect(status().isCreated());

        // Validate the Condicionante in the database
        List<Condicionante> condicionanteList = condicionanteRepository.findAll();
        assertThat(condicionanteList).hasSize(databaseSizeBeforeCreate + 1);
        Condicionante testCondicionante = condicionanteList.get(condicionanteList.size() - 1);
        assertThat(testCondicionante.isAtivo()).isEqualTo(DEFAULT_ATIVO);
        assertThat(testCondicionante.getConteudo()).isEqualTo(DEFAULT_CONTEUDO);
        assertThat(testCondicionante.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testCondicionante.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testCondicionante.getObservacao()).isEqualTo(DEFAULT_OBSERVACAO);
        assertThat(testCondicionante.getSituacao()).isEqualTo(DEFAULT_SITUACAO);
    }

    @Test
    @Transactional
    public void createCondicionanteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = condicionanteRepository.findAll().size();

        // Create the Condicionante with an existing ID
        condicionante.setId(1L);
        CondicionanteDTO condicionanteDTO = condicionanteMapper.toDto(condicionante);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCondicionanteMockMvc.perform(post("/api/condicionantes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(condicionanteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Condicionante> condicionanteList = condicionanteRepository.findAll();
        assertThat(condicionanteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCondicionantes() throws Exception {
        // Initialize the database
        condicionanteRepository.saveAndFlush(condicionante);

        // Get all the condicionanteList
        restCondicionanteMockMvc.perform(get("/api/condicionantes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(condicionante.getId().intValue())))
            .andExpect(jsonPath("$.[*].ativo").value(hasItem(DEFAULT_ATIVO.booleanValue())))
            .andExpect(jsonPath("$.[*].conteudo").value(hasItem(DEFAULT_CONTEUDO.toString())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(sameInstant(DEFAULT_DATA))))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].observacao").value(hasItem(DEFAULT_OBSERVACAO.toString())))
            .andExpect(jsonPath("$.[*].situacao").value(hasItem(DEFAULT_SITUACAO.toString())));
    }

    @Test
    @Transactional
    public void getCondicionante() throws Exception {
        // Initialize the database
        condicionanteRepository.saveAndFlush(condicionante);

        // Get the condicionante
        restCondicionanteMockMvc.perform(get("/api/condicionantes/{id}", condicionante.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(condicionante.getId().intValue()))
            .andExpect(jsonPath("$.ativo").value(DEFAULT_ATIVO.booleanValue()))
            .andExpect(jsonPath("$.conteudo").value(DEFAULT_CONTEUDO.toString()))
            .andExpect(jsonPath("$.data").value(sameInstant(DEFAULT_DATA)))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.observacao").value(DEFAULT_OBSERVACAO.toString()))
            .andExpect(jsonPath("$.situacao").value(DEFAULT_SITUACAO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCondicionante() throws Exception {
        // Get the condicionante
        restCondicionanteMockMvc.perform(get("/api/condicionantes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCondicionante() throws Exception {
        // Initialize the database
        condicionanteRepository.saveAndFlush(condicionante);
        int databaseSizeBeforeUpdate = condicionanteRepository.findAll().size();

        // Update the condicionante
        Condicionante updatedCondicionante = condicionanteRepository.findOne(condicionante.getId());
        updatedCondicionante
            .ativo(UPDATED_ATIVO)
            .conteudo(UPDATED_CONTEUDO)
            .data(UPDATED_DATA)
            .descricao(UPDATED_DESCRICAO)
            .observacao(UPDATED_OBSERVACAO)
            .situacao(UPDATED_SITUACAO);
        CondicionanteDTO condicionanteDTO = condicionanteMapper.toDto(updatedCondicionante);

        restCondicionanteMockMvc.perform(put("/api/condicionantes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(condicionanteDTO)))
            .andExpect(status().isOk());

        // Validate the Condicionante in the database
        List<Condicionante> condicionanteList = condicionanteRepository.findAll();
        assertThat(condicionanteList).hasSize(databaseSizeBeforeUpdate);
        Condicionante testCondicionante = condicionanteList.get(condicionanteList.size() - 1);
        assertThat(testCondicionante.isAtivo()).isEqualTo(UPDATED_ATIVO);
        assertThat(testCondicionante.getConteudo()).isEqualTo(UPDATED_CONTEUDO);
        assertThat(testCondicionante.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testCondicionante.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testCondicionante.getObservacao()).isEqualTo(UPDATED_OBSERVACAO);
        assertThat(testCondicionante.getSituacao()).isEqualTo(UPDATED_SITUACAO);
    }

    @Test
    @Transactional
    public void updateNonExistingCondicionante() throws Exception {
        int databaseSizeBeforeUpdate = condicionanteRepository.findAll().size();

        // Create the Condicionante
        CondicionanteDTO condicionanteDTO = condicionanteMapper.toDto(condicionante);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCondicionanteMockMvc.perform(put("/api/condicionantes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(condicionanteDTO)))
            .andExpect(status().isCreated());

        // Validate the Condicionante in the database
        List<Condicionante> condicionanteList = condicionanteRepository.findAll();
        assertThat(condicionanteList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCondicionante() throws Exception {
        // Initialize the database
        condicionanteRepository.saveAndFlush(condicionante);
        int databaseSizeBeforeDelete = condicionanteRepository.findAll().size();

        // Get the condicionante
        restCondicionanteMockMvc.perform(delete("/api/condicionantes/{id}", condicionante.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Condicionante> condicionanteList = condicionanteRepository.findAll();
        assertThat(condicionanteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Condicionante.class);
        Condicionante condicionante1 = new Condicionante();
        condicionante1.setId(1L);
        Condicionante condicionante2 = new Condicionante();
        condicionante2.setId(condicionante1.getId());
        assertThat(condicionante1).isEqualTo(condicionante2);
        condicionante2.setId(2L);
        assertThat(condicionante1).isNotEqualTo(condicionante2);
        condicionante1.setId(null);
        assertThat(condicionante1).isNotEqualTo(condicionante2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CondicionanteDTO.class);
        CondicionanteDTO condicionanteDTO1 = new CondicionanteDTO();
        condicionanteDTO1.setId(1L);
        CondicionanteDTO condicionanteDTO2 = new CondicionanteDTO();
        assertThat(condicionanteDTO1).isNotEqualTo(condicionanteDTO2);
        condicionanteDTO2.setId(condicionanteDTO1.getId());
        assertThat(condicionanteDTO1).isEqualTo(condicionanteDTO2);
        condicionanteDTO2.setId(2L);
        assertThat(condicionanteDTO1).isNotEqualTo(condicionanteDTO2);
        condicionanteDTO1.setId(null);
        assertThat(condicionanteDTO1).isNotEqualTo(condicionanteDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(condicionanteMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(condicionanteMapper.fromId(null)).isNull();
    }
}
