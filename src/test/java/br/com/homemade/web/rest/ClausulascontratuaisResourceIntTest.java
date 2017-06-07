package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Clausulascontratuais;
import br.com.homemade.repository.ClausulascontratuaisRepository;
import br.com.homemade.service.dto.ClausulascontratuaisDTO;
import br.com.homemade.service.mapper.ClausulascontratuaisMapper;
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
 * Test class for the ClausulascontratuaisResource REST controller.
 *
 * @see ClausulascontratuaisResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class ClausulascontratuaisResourceIntTest {

    private static final String DEFAULT_ARTIGO = "AAAAAAAAAA";
    private static final String UPDATED_ARTIGO = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final String DEFAULT_NOFICIOENVIADO = "AAAAAAAAAA";
    private static final String UPDATED_NOFICIOENVIADO = "BBBBBBBBBB";

    private static final String DEFAULT_NOFICIOAPROVADO = "AAAAAAAAAA";
    private static final String UPDATED_NOFICIOAPROVADO = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATAAPROVACAO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAAPROVACAO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAENVIO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAENVIO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAVIGENTE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAVIGENTE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_IDCLAUSULASCONTRATUAIS = 1;
    private static final Integer UPDATED_IDCLAUSULASCONTRATUAIS = 2;

    @Autowired
    private ClausulascontratuaisRepository clausulascontratuaisRepository;

    @Autowired
    private ClausulascontratuaisMapper clausulascontratuaisMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restClausulascontratuaisMockMvc;

    private Clausulascontratuais clausulascontratuais;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ClausulascontratuaisResource clausulascontratuaisResource = new ClausulascontratuaisResource(clausulascontratuaisRepository, clausulascontratuaisMapper);
        this.restClausulascontratuaisMockMvc = MockMvcBuilders.standaloneSetup(clausulascontratuaisResource)
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
    public static Clausulascontratuais createEntity(EntityManager em) {
        Clausulascontratuais clausulascontratuais = new Clausulascontratuais()
            .artigo(DEFAULT_ARTIGO)
            .descricao(DEFAULT_DESCRICAO)
            .noficioenviado(DEFAULT_NOFICIOENVIADO)
            .noficioaprovado(DEFAULT_NOFICIOAPROVADO)
            .dataaprovacao(DEFAULT_DATAAPROVACAO)
            .dataenvio(DEFAULT_DATAENVIO)
            .datavigente(DEFAULT_DATAVIGENTE)
            .idclausulascontratuais(DEFAULT_IDCLAUSULASCONTRATUAIS);
        return clausulascontratuais;
    }

    @Before
    public void initTest() {
        clausulascontratuais = createEntity(em);
    }

    @Test
    @Transactional
    public void createClausulascontratuais() throws Exception {
        int databaseSizeBeforeCreate = clausulascontratuaisRepository.findAll().size();

        // Create the Clausulascontratuais
        ClausulascontratuaisDTO clausulascontratuaisDTO = clausulascontratuaisMapper.toDto(clausulascontratuais);
        restClausulascontratuaisMockMvc.perform(post("/api/clausulascontratuais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clausulascontratuaisDTO)))
            .andExpect(status().isCreated());

        // Validate the Clausulascontratuais in the database
        List<Clausulascontratuais> clausulascontratuaisList = clausulascontratuaisRepository.findAll();
        assertThat(clausulascontratuaisList).hasSize(databaseSizeBeforeCreate + 1);
        Clausulascontratuais testClausulascontratuais = clausulascontratuaisList.get(clausulascontratuaisList.size() - 1);
        assertThat(testClausulascontratuais.getArtigo()).isEqualTo(DEFAULT_ARTIGO);
        assertThat(testClausulascontratuais.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testClausulascontratuais.getNoficioenviado()).isEqualTo(DEFAULT_NOFICIOENVIADO);
        assertThat(testClausulascontratuais.getNoficioaprovado()).isEqualTo(DEFAULT_NOFICIOAPROVADO);
        assertThat(testClausulascontratuais.getDataaprovacao()).isEqualTo(DEFAULT_DATAAPROVACAO);
        assertThat(testClausulascontratuais.getDataenvio()).isEqualTo(DEFAULT_DATAENVIO);
        assertThat(testClausulascontratuais.getDatavigente()).isEqualTo(DEFAULT_DATAVIGENTE);
        assertThat(testClausulascontratuais.getIdclausulascontratuais()).isEqualTo(DEFAULT_IDCLAUSULASCONTRATUAIS);
    }

    @Test
    @Transactional
    public void createClausulascontratuaisWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = clausulascontratuaisRepository.findAll().size();

        // Create the Clausulascontratuais with an existing ID
        clausulascontratuais.setId(1L);
        ClausulascontratuaisDTO clausulascontratuaisDTO = clausulascontratuaisMapper.toDto(clausulascontratuais);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClausulascontratuaisMockMvc.perform(post("/api/clausulascontratuais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clausulascontratuaisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Clausulascontratuais> clausulascontratuaisList = clausulascontratuaisRepository.findAll();
        assertThat(clausulascontratuaisList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllClausulascontratuais() throws Exception {
        // Initialize the database
        clausulascontratuaisRepository.saveAndFlush(clausulascontratuais);

        // Get all the clausulascontratuaisList
        restClausulascontratuaisMockMvc.perform(get("/api/clausulascontratuais?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(clausulascontratuais.getId().intValue())))
            .andExpect(jsonPath("$.[*].artigo").value(hasItem(DEFAULT_ARTIGO.toString())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].noficioenviado").value(hasItem(DEFAULT_NOFICIOENVIADO.toString())))
            .andExpect(jsonPath("$.[*].noficioaprovado").value(hasItem(DEFAULT_NOFICIOAPROVADO.toString())))
            .andExpect(jsonPath("$.[*].dataaprovacao").value(hasItem(sameInstant(DEFAULT_DATAAPROVACAO))))
            .andExpect(jsonPath("$.[*].dataenvio").value(hasItem(sameInstant(DEFAULT_DATAENVIO))))
            .andExpect(jsonPath("$.[*].datavigente").value(hasItem(sameInstant(DEFAULT_DATAVIGENTE))))
            .andExpect(jsonPath("$.[*].idclausulascontratuais").value(hasItem(DEFAULT_IDCLAUSULASCONTRATUAIS)));
    }

    @Test
    @Transactional
    public void getClausulascontratuais() throws Exception {
        // Initialize the database
        clausulascontratuaisRepository.saveAndFlush(clausulascontratuais);

        // Get the clausulascontratuais
        restClausulascontratuaisMockMvc.perform(get("/api/clausulascontratuais/{id}", clausulascontratuais.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(clausulascontratuais.getId().intValue()))
            .andExpect(jsonPath("$.artigo").value(DEFAULT_ARTIGO.toString()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.noficioenviado").value(DEFAULT_NOFICIOENVIADO.toString()))
            .andExpect(jsonPath("$.noficioaprovado").value(DEFAULT_NOFICIOAPROVADO.toString()))
            .andExpect(jsonPath("$.dataaprovacao").value(sameInstant(DEFAULT_DATAAPROVACAO)))
            .andExpect(jsonPath("$.dataenvio").value(sameInstant(DEFAULT_DATAENVIO)))
            .andExpect(jsonPath("$.datavigente").value(sameInstant(DEFAULT_DATAVIGENTE)))
            .andExpect(jsonPath("$.idclausulascontratuais").value(DEFAULT_IDCLAUSULASCONTRATUAIS));
    }

    @Test
    @Transactional
    public void getNonExistingClausulascontratuais() throws Exception {
        // Get the clausulascontratuais
        restClausulascontratuaisMockMvc.perform(get("/api/clausulascontratuais/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClausulascontratuais() throws Exception {
        // Initialize the database
        clausulascontratuaisRepository.saveAndFlush(clausulascontratuais);
        int databaseSizeBeforeUpdate = clausulascontratuaisRepository.findAll().size();

        // Update the clausulascontratuais
        Clausulascontratuais updatedClausulascontratuais = clausulascontratuaisRepository.findOne(clausulascontratuais.getId());
        updatedClausulascontratuais
            .artigo(UPDATED_ARTIGO)
            .descricao(UPDATED_DESCRICAO)
            .noficioenviado(UPDATED_NOFICIOENVIADO)
            .noficioaprovado(UPDATED_NOFICIOAPROVADO)
            .dataaprovacao(UPDATED_DATAAPROVACAO)
            .dataenvio(UPDATED_DATAENVIO)
            .datavigente(UPDATED_DATAVIGENTE)
            .idclausulascontratuais(UPDATED_IDCLAUSULASCONTRATUAIS);
        ClausulascontratuaisDTO clausulascontratuaisDTO = clausulascontratuaisMapper.toDto(updatedClausulascontratuais);

        restClausulascontratuaisMockMvc.perform(put("/api/clausulascontratuais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clausulascontratuaisDTO)))
            .andExpect(status().isOk());

        // Validate the Clausulascontratuais in the database
        List<Clausulascontratuais> clausulascontratuaisList = clausulascontratuaisRepository.findAll();
        assertThat(clausulascontratuaisList).hasSize(databaseSizeBeforeUpdate);
        Clausulascontratuais testClausulascontratuais = clausulascontratuaisList.get(clausulascontratuaisList.size() - 1);
        assertThat(testClausulascontratuais.getArtigo()).isEqualTo(UPDATED_ARTIGO);
        assertThat(testClausulascontratuais.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testClausulascontratuais.getNoficioenviado()).isEqualTo(UPDATED_NOFICIOENVIADO);
        assertThat(testClausulascontratuais.getNoficioaprovado()).isEqualTo(UPDATED_NOFICIOAPROVADO);
        assertThat(testClausulascontratuais.getDataaprovacao()).isEqualTo(UPDATED_DATAAPROVACAO);
        assertThat(testClausulascontratuais.getDataenvio()).isEqualTo(UPDATED_DATAENVIO);
        assertThat(testClausulascontratuais.getDatavigente()).isEqualTo(UPDATED_DATAVIGENTE);
        assertThat(testClausulascontratuais.getIdclausulascontratuais()).isEqualTo(UPDATED_IDCLAUSULASCONTRATUAIS);
    }

    @Test
    @Transactional
    public void updateNonExistingClausulascontratuais() throws Exception {
        int databaseSizeBeforeUpdate = clausulascontratuaisRepository.findAll().size();

        // Create the Clausulascontratuais
        ClausulascontratuaisDTO clausulascontratuaisDTO = clausulascontratuaisMapper.toDto(clausulascontratuais);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restClausulascontratuaisMockMvc.perform(put("/api/clausulascontratuais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clausulascontratuaisDTO)))
            .andExpect(status().isCreated());

        // Validate the Clausulascontratuais in the database
        List<Clausulascontratuais> clausulascontratuaisList = clausulascontratuaisRepository.findAll();
        assertThat(clausulascontratuaisList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteClausulascontratuais() throws Exception {
        // Initialize the database
        clausulascontratuaisRepository.saveAndFlush(clausulascontratuais);
        int databaseSizeBeforeDelete = clausulascontratuaisRepository.findAll().size();

        // Get the clausulascontratuais
        restClausulascontratuaisMockMvc.perform(delete("/api/clausulascontratuais/{id}", clausulascontratuais.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Clausulascontratuais> clausulascontratuaisList = clausulascontratuaisRepository.findAll();
        assertThat(clausulascontratuaisList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Clausulascontratuais.class);
        Clausulascontratuais clausulascontratuais1 = new Clausulascontratuais();
        clausulascontratuais1.setId(1L);
        Clausulascontratuais clausulascontratuais2 = new Clausulascontratuais();
        clausulascontratuais2.setId(clausulascontratuais1.getId());
        assertThat(clausulascontratuais1).isEqualTo(clausulascontratuais2);
        clausulascontratuais2.setId(2L);
        assertThat(clausulascontratuais1).isNotEqualTo(clausulascontratuais2);
        clausulascontratuais1.setId(null);
        assertThat(clausulascontratuais1).isNotEqualTo(clausulascontratuais2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClausulascontratuaisDTO.class);
        ClausulascontratuaisDTO clausulascontratuaisDTO1 = new ClausulascontratuaisDTO();
        clausulascontratuaisDTO1.setId(1L);
        ClausulascontratuaisDTO clausulascontratuaisDTO2 = new ClausulascontratuaisDTO();
        assertThat(clausulascontratuaisDTO1).isNotEqualTo(clausulascontratuaisDTO2);
        clausulascontratuaisDTO2.setId(clausulascontratuaisDTO1.getId());
        assertThat(clausulascontratuaisDTO1).isEqualTo(clausulascontratuaisDTO2);
        clausulascontratuaisDTO2.setId(2L);
        assertThat(clausulascontratuaisDTO1).isNotEqualTo(clausulascontratuaisDTO2);
        clausulascontratuaisDTO1.setId(null);
        assertThat(clausulascontratuaisDTO1).isNotEqualTo(clausulascontratuaisDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(clausulascontratuaisMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(clausulascontratuaisMapper.fromId(null)).isNull();
    }
}
