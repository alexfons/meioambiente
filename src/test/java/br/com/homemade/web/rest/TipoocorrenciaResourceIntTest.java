package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Tipoocorrencia;
import br.com.homemade.repository.TipoocorrenciaRepository;
import br.com.homemade.service.dto.TipoocorrenciaDTO;
import br.com.homemade.service.mapper.TipoocorrenciaMapper;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TipoocorrenciaResource REST controller.
 *
 * @see TipoocorrenciaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class TipoocorrenciaResourceIntTest {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORIA = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORIA = "BBBBBBBBBB";

    private static final String DEFAULT_SUBCATEGORIA = "AAAAAAAAAA";
    private static final String UPDATED_SUBCATEGORIA = "BBBBBBBBBB";

    @Autowired
    private TipoocorrenciaRepository tipoocorrenciaRepository;

    @Autowired
    private TipoocorrenciaMapper tipoocorrenciaMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTipoocorrenciaMockMvc;

    private Tipoocorrencia tipoocorrencia;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TipoocorrenciaResource tipoocorrenciaResource = new TipoocorrenciaResource(tipoocorrenciaRepository, tipoocorrenciaMapper);
        this.restTipoocorrenciaMockMvc = MockMvcBuilders.standaloneSetup(tipoocorrenciaResource)
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
    public static Tipoocorrencia createEntity(EntityManager em) {
        Tipoocorrencia tipoocorrencia = new Tipoocorrencia()
            .descricao(DEFAULT_DESCRICAO)
            .categoria(DEFAULT_CATEGORIA)
            .subcategoria(DEFAULT_SUBCATEGORIA);
        return tipoocorrencia;
    }

    @Before
    public void initTest() {
        tipoocorrencia = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoocorrencia() throws Exception {
        int databaseSizeBeforeCreate = tipoocorrenciaRepository.findAll().size();

        // Create the Tipoocorrencia
        TipoocorrenciaDTO tipoocorrenciaDTO = tipoocorrenciaMapper.toDto(tipoocorrencia);
        restTipoocorrenciaMockMvc.perform(post("/api/tipoocorrencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoocorrenciaDTO)))
            .andExpect(status().isCreated());

        // Validate the Tipoocorrencia in the database
        List<Tipoocorrencia> tipoocorrenciaList = tipoocorrenciaRepository.findAll();
        assertThat(tipoocorrenciaList).hasSize(databaseSizeBeforeCreate + 1);
        Tipoocorrencia testTipoocorrencia = tipoocorrenciaList.get(tipoocorrenciaList.size() - 1);
        assertThat(testTipoocorrencia.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testTipoocorrencia.getCategoria()).isEqualTo(DEFAULT_CATEGORIA);
        assertThat(testTipoocorrencia.getSubcategoria()).isEqualTo(DEFAULT_SUBCATEGORIA);
    }

    @Test
    @Transactional
    public void createTipoocorrenciaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoocorrenciaRepository.findAll().size();

        // Create the Tipoocorrencia with an existing ID
        tipoocorrencia.setId(1L);
        TipoocorrenciaDTO tipoocorrenciaDTO = tipoocorrenciaMapper.toDto(tipoocorrencia);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoocorrenciaMockMvc.perform(post("/api/tipoocorrencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoocorrenciaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Tipoocorrencia> tipoocorrenciaList = tipoocorrenciaRepository.findAll();
        assertThat(tipoocorrenciaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTipoocorrencias() throws Exception {
        // Initialize the database
        tipoocorrenciaRepository.saveAndFlush(tipoocorrencia);

        // Get all the tipoocorrenciaList
        restTipoocorrenciaMockMvc.perform(get("/api/tipoocorrencias?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoocorrencia.getId().intValue())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].categoria").value(hasItem(DEFAULT_CATEGORIA.toString())))
            .andExpect(jsonPath("$.[*].subcategoria").value(hasItem(DEFAULT_SUBCATEGORIA.toString())));
    }

    @Test
    @Transactional
    public void getTipoocorrencia() throws Exception {
        // Initialize the database
        tipoocorrenciaRepository.saveAndFlush(tipoocorrencia);

        // Get the tipoocorrencia
        restTipoocorrenciaMockMvc.perform(get("/api/tipoocorrencias/{id}", tipoocorrencia.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipoocorrencia.getId().intValue()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.categoria").value(DEFAULT_CATEGORIA.toString()))
            .andExpect(jsonPath("$.subcategoria").value(DEFAULT_SUBCATEGORIA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTipoocorrencia() throws Exception {
        // Get the tipoocorrencia
        restTipoocorrenciaMockMvc.perform(get("/api/tipoocorrencias/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoocorrencia() throws Exception {
        // Initialize the database
        tipoocorrenciaRepository.saveAndFlush(tipoocorrencia);
        int databaseSizeBeforeUpdate = tipoocorrenciaRepository.findAll().size();

        // Update the tipoocorrencia
        Tipoocorrencia updatedTipoocorrencia = tipoocorrenciaRepository.findOne(tipoocorrencia.getId());
        updatedTipoocorrencia
            .descricao(UPDATED_DESCRICAO)
            .categoria(UPDATED_CATEGORIA)
            .subcategoria(UPDATED_SUBCATEGORIA);
        TipoocorrenciaDTO tipoocorrenciaDTO = tipoocorrenciaMapper.toDto(updatedTipoocorrencia);

        restTipoocorrenciaMockMvc.perform(put("/api/tipoocorrencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoocorrenciaDTO)))
            .andExpect(status().isOk());

        // Validate the Tipoocorrencia in the database
        List<Tipoocorrencia> tipoocorrenciaList = tipoocorrenciaRepository.findAll();
        assertThat(tipoocorrenciaList).hasSize(databaseSizeBeforeUpdate);
        Tipoocorrencia testTipoocorrencia = tipoocorrenciaList.get(tipoocorrenciaList.size() - 1);
        assertThat(testTipoocorrencia.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testTipoocorrencia.getCategoria()).isEqualTo(UPDATED_CATEGORIA);
        assertThat(testTipoocorrencia.getSubcategoria()).isEqualTo(UPDATED_SUBCATEGORIA);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoocorrencia() throws Exception {
        int databaseSizeBeforeUpdate = tipoocorrenciaRepository.findAll().size();

        // Create the Tipoocorrencia
        TipoocorrenciaDTO tipoocorrenciaDTO = tipoocorrenciaMapper.toDto(tipoocorrencia);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTipoocorrenciaMockMvc.perform(put("/api/tipoocorrencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoocorrenciaDTO)))
            .andExpect(status().isCreated());

        // Validate the Tipoocorrencia in the database
        List<Tipoocorrencia> tipoocorrenciaList = tipoocorrenciaRepository.findAll();
        assertThat(tipoocorrenciaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTipoocorrencia() throws Exception {
        // Initialize the database
        tipoocorrenciaRepository.saveAndFlush(tipoocorrencia);
        int databaseSizeBeforeDelete = tipoocorrenciaRepository.findAll().size();

        // Get the tipoocorrencia
        restTipoocorrenciaMockMvc.perform(delete("/api/tipoocorrencias/{id}", tipoocorrencia.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Tipoocorrencia> tipoocorrenciaList = tipoocorrenciaRepository.findAll();
        assertThat(tipoocorrenciaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tipoocorrencia.class);
        Tipoocorrencia tipoocorrencia1 = new Tipoocorrencia();
        tipoocorrencia1.setId(1L);
        Tipoocorrencia tipoocorrencia2 = new Tipoocorrencia();
        tipoocorrencia2.setId(tipoocorrencia1.getId());
        assertThat(tipoocorrencia1).isEqualTo(tipoocorrencia2);
        tipoocorrencia2.setId(2L);
        assertThat(tipoocorrencia1).isNotEqualTo(tipoocorrencia2);
        tipoocorrencia1.setId(null);
        assertThat(tipoocorrencia1).isNotEqualTo(tipoocorrencia2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoocorrenciaDTO.class);
        TipoocorrenciaDTO tipoocorrenciaDTO1 = new TipoocorrenciaDTO();
        tipoocorrenciaDTO1.setId(1L);
        TipoocorrenciaDTO tipoocorrenciaDTO2 = new TipoocorrenciaDTO();
        assertThat(tipoocorrenciaDTO1).isNotEqualTo(tipoocorrenciaDTO2);
        tipoocorrenciaDTO2.setId(tipoocorrenciaDTO1.getId());
        assertThat(tipoocorrenciaDTO1).isEqualTo(tipoocorrenciaDTO2);
        tipoocorrenciaDTO2.setId(2L);
        assertThat(tipoocorrenciaDTO1).isNotEqualTo(tipoocorrenciaDTO2);
        tipoocorrenciaDTO1.setId(null);
        assertThat(tipoocorrenciaDTO1).isNotEqualTo(tipoocorrenciaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(tipoocorrenciaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(tipoocorrenciaMapper.fromId(null)).isNull();
    }
}
