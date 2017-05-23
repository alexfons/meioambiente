package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.TipoObra;
import br.com.homemade.repository.TipoObraRepository;
import br.com.homemade.service.dto.TipoObraDTO;
import br.com.homemade.service.mapper.TipoObraMapper;
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
 * Test class for the TipoObraResource REST controller.
 *
 * @see TipoObraResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class TipoObraResourceIntTest {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORIA = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORIA = "BBBBBBBBBB";

    private static final String DEFAULT_SUBCATEGORIA = "AAAAAAAAAA";
    private static final String UPDATED_SUBCATEGORIA = "BBBBBBBBBB";

    @Autowired
    private TipoObraRepository tipoObraRepository;

    @Autowired
    private TipoObraMapper tipoObraMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTipoObraMockMvc;

    private TipoObra tipoObra;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TipoObraResource tipoObraResource = new TipoObraResource(tipoObraRepository, tipoObraMapper);
        this.restTipoObraMockMvc = MockMvcBuilders.standaloneSetup(tipoObraResource)
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
    public static TipoObra createEntity(EntityManager em) {
        TipoObra tipoObra = new TipoObra()
            .descricao(DEFAULT_DESCRICAO)
            .categoria(DEFAULT_CATEGORIA)
            .subcategoria(DEFAULT_SUBCATEGORIA);
        return tipoObra;
    }

    @Before
    public void initTest() {
        tipoObra = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoObra() throws Exception {
        int databaseSizeBeforeCreate = tipoObraRepository.findAll().size();

        // Create the TipoObra
        TipoObraDTO tipoObraDTO = tipoObraMapper.toDto(tipoObra);
        restTipoObraMockMvc.perform(post("/api/tipo-obras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoObraDTO)))
            .andExpect(status().isCreated());

        // Validate the TipoObra in the database
        List<TipoObra> tipoObraList = tipoObraRepository.findAll();
        assertThat(tipoObraList).hasSize(databaseSizeBeforeCreate + 1);
        TipoObra testTipoObra = tipoObraList.get(tipoObraList.size() - 1);
        assertThat(testTipoObra.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testTipoObra.getCategoria()).isEqualTo(DEFAULT_CATEGORIA);
        assertThat(testTipoObra.getSubcategoria()).isEqualTo(DEFAULT_SUBCATEGORIA);
    }

    @Test
    @Transactional
    public void createTipoObraWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoObraRepository.findAll().size();

        // Create the TipoObra with an existing ID
        tipoObra.setId(1L);
        TipoObraDTO tipoObraDTO = tipoObraMapper.toDto(tipoObra);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoObraMockMvc.perform(post("/api/tipo-obras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoObraDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<TipoObra> tipoObraList = tipoObraRepository.findAll();
        assertThat(tipoObraList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTipoObras() throws Exception {
        // Initialize the database
        tipoObraRepository.saveAndFlush(tipoObra);

        // Get all the tipoObraList
        restTipoObraMockMvc.perform(get("/api/tipo-obras?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoObra.getId().intValue())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].categoria").value(hasItem(DEFAULT_CATEGORIA.toString())))
            .andExpect(jsonPath("$.[*].subcategoria").value(hasItem(DEFAULT_SUBCATEGORIA.toString())));
    }

    @Test
    @Transactional
    public void getTipoObra() throws Exception {
        // Initialize the database
        tipoObraRepository.saveAndFlush(tipoObra);

        // Get the tipoObra
        restTipoObraMockMvc.perform(get("/api/tipo-obras/{id}", tipoObra.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipoObra.getId().intValue()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.categoria").value(DEFAULT_CATEGORIA.toString()))
            .andExpect(jsonPath("$.subcategoria").value(DEFAULT_SUBCATEGORIA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTipoObra() throws Exception {
        // Get the tipoObra
        restTipoObraMockMvc.perform(get("/api/tipo-obras/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoObra() throws Exception {
        // Initialize the database
        tipoObraRepository.saveAndFlush(tipoObra);
        int databaseSizeBeforeUpdate = tipoObraRepository.findAll().size();

        // Update the tipoObra
        TipoObra updatedTipoObra = tipoObraRepository.findOne(tipoObra.getId());
        updatedTipoObra
            .descricao(UPDATED_DESCRICAO)
            .categoria(UPDATED_CATEGORIA)
            .subcategoria(UPDATED_SUBCATEGORIA);
        TipoObraDTO tipoObraDTO = tipoObraMapper.toDto(updatedTipoObra);

        restTipoObraMockMvc.perform(put("/api/tipo-obras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoObraDTO)))
            .andExpect(status().isOk());

        // Validate the TipoObra in the database
        List<TipoObra> tipoObraList = tipoObraRepository.findAll();
        assertThat(tipoObraList).hasSize(databaseSizeBeforeUpdate);
        TipoObra testTipoObra = tipoObraList.get(tipoObraList.size() - 1);
        assertThat(testTipoObra.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testTipoObra.getCategoria()).isEqualTo(UPDATED_CATEGORIA);
        assertThat(testTipoObra.getSubcategoria()).isEqualTo(UPDATED_SUBCATEGORIA);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoObra() throws Exception {
        int databaseSizeBeforeUpdate = tipoObraRepository.findAll().size();

        // Create the TipoObra
        TipoObraDTO tipoObraDTO = tipoObraMapper.toDto(tipoObra);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTipoObraMockMvc.perform(put("/api/tipo-obras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoObraDTO)))
            .andExpect(status().isCreated());

        // Validate the TipoObra in the database
        List<TipoObra> tipoObraList = tipoObraRepository.findAll();
        assertThat(tipoObraList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTipoObra() throws Exception {
        // Initialize the database
        tipoObraRepository.saveAndFlush(tipoObra);
        int databaseSizeBeforeDelete = tipoObraRepository.findAll().size();

        // Get the tipoObra
        restTipoObraMockMvc.perform(delete("/api/tipo-obras/{id}", tipoObra.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TipoObra> tipoObraList = tipoObraRepository.findAll();
        assertThat(tipoObraList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoObra.class);
        TipoObra tipoObra1 = new TipoObra();
        tipoObra1.setId(1L);
        TipoObra tipoObra2 = new TipoObra();
        tipoObra2.setId(tipoObra1.getId());
        assertThat(tipoObra1).isEqualTo(tipoObra2);
        tipoObra2.setId(2L);
        assertThat(tipoObra1).isNotEqualTo(tipoObra2);
        tipoObra1.setId(null);
        assertThat(tipoObra1).isNotEqualTo(tipoObra2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoObraDTO.class);
        TipoObraDTO tipoObraDTO1 = new TipoObraDTO();
        tipoObraDTO1.setId(1L);
        TipoObraDTO tipoObraDTO2 = new TipoObraDTO();
        assertThat(tipoObraDTO1).isNotEqualTo(tipoObraDTO2);
        tipoObraDTO2.setId(tipoObraDTO1.getId());
        assertThat(tipoObraDTO1).isEqualTo(tipoObraDTO2);
        tipoObraDTO2.setId(2L);
        assertThat(tipoObraDTO1).isNotEqualTo(tipoObraDTO2);
        tipoObraDTO1.setId(null);
        assertThat(tipoObraDTO1).isNotEqualTo(tipoObraDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(tipoObraMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(tipoObraMapper.fromId(null)).isNull();
    }
}
