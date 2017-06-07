package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Tipoobra;
import br.com.homemade.repository.TipoobraRepository2;
import br.com.homemade.service.dto.TipoobraDTO;
import br.com.homemade.service.mapper.TipoobraMapper;
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
 * Test class for the TipoobraResource REST controller.
 *
 * @see TipoobraResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class TipoobraResourceIntTest2 {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORIA = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORIA = "BBBBBBBBBB";

    private static final String DEFAULT_SUBCATEGORIA = "AAAAAAAAAA";
    private static final String UPDATED_SUBCATEGORIA = "BBBBBBBBBB";

    @Autowired
    private TipoobraRepository2 tipoobraRepository;

    @Autowired
    private TipoobraMapper tipoobraMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTipoobraMockMvc;

    private Tipoobra tipoobra;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TipoobraResource tipoobraResource = new TipoobraResource(tipoobraRepository, tipoobraMapper);
        this.restTipoobraMockMvc = MockMvcBuilders.standaloneSetup(tipoobraResource)
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
    public static Tipoobra createEntity(EntityManager em) {
        Tipoobra tipoobra = new Tipoobra()
            .descricao(DEFAULT_DESCRICAO)
            .categoria(DEFAULT_CATEGORIA)
            .subcategoria(DEFAULT_SUBCATEGORIA);
        return tipoobra;
    }

    @Before
    public void initTest() {
        tipoobra = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoobra() throws Exception {
        int databaseSizeBeforeCreate = tipoobraRepository.findAll().size();

        // Create the Tipoobra
        TipoobraDTO tipoobraDTO = tipoobraMapper.toDto(tipoobra);
        restTipoobraMockMvc.perform(post("/api/tipoobras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoobraDTO)))
            .andExpect(status().isCreated());

        // Validate the Tipoobra in the database
        List<Tipoobra> tipoobraList = tipoobraRepository.findAll();
        assertThat(tipoobraList).hasSize(databaseSizeBeforeCreate + 1);
        Tipoobra testTipoobra = tipoobraList.get(tipoobraList.size() - 1);
        assertThat(testTipoobra.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testTipoobra.getCategoria()).isEqualTo(DEFAULT_CATEGORIA);
        assertThat(testTipoobra.getSubcategoria()).isEqualTo(DEFAULT_SUBCATEGORIA);
    }

    @Test
    @Transactional
    public void createTipoobraWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoobraRepository.findAll().size();

        // Create the Tipoobra with an existing ID
        tipoobra.setId(1L);
        TipoobraDTO tipoobraDTO = tipoobraMapper.toDto(tipoobra);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoobraMockMvc.perform(post("/api/tipoobras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoobraDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Tipoobra> tipoobraList = tipoobraRepository.findAll();
        assertThat(tipoobraList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTipoobras() throws Exception {
        // Initialize the database
        tipoobraRepository.saveAndFlush(tipoobra);

        // Get all the tipoobraList
        restTipoobraMockMvc.perform(get("/api/tipoobras?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoobra.getId().intValue())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].categoria").value(hasItem(DEFAULT_CATEGORIA.toString())))
            .andExpect(jsonPath("$.[*].subcategoria").value(hasItem(DEFAULT_SUBCATEGORIA.toString())));
    }

    @Test
    @Transactional
    public void getTipoobra() throws Exception {
        // Initialize the database
        tipoobraRepository.saveAndFlush(tipoobra);

        // Get the tipoobra
        restTipoobraMockMvc.perform(get("/api/tipoobras/{id}", tipoobra.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipoobra.getId().intValue()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.categoria").value(DEFAULT_CATEGORIA.toString()))
            .andExpect(jsonPath("$.subcategoria").value(DEFAULT_SUBCATEGORIA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTipoobra() throws Exception {
        // Get the tipoobra
        restTipoobraMockMvc.perform(get("/api/tipoobras/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoobra() throws Exception {
        // Initialize the database
        tipoobraRepository.saveAndFlush(tipoobra);
        int databaseSizeBeforeUpdate = tipoobraRepository.findAll().size();

        // Update the tipoobra
        Tipoobra updatedTipoobra = tipoobraRepository.findOne(tipoobra.getId());
        updatedTipoobra
            .descricao(UPDATED_DESCRICAO)
            .categoria(UPDATED_CATEGORIA)
            .subcategoria(UPDATED_SUBCATEGORIA);
        TipoobraDTO tipoobraDTO = tipoobraMapper.toDto(updatedTipoobra);

        restTipoobraMockMvc.perform(put("/api/tipoobras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoobraDTO)))
            .andExpect(status().isOk());

        // Validate the Tipoobra in the database
        List<Tipoobra> tipoobraList = tipoobraRepository.findAll();
        assertThat(tipoobraList).hasSize(databaseSizeBeforeUpdate);
        Tipoobra testTipoobra = tipoobraList.get(tipoobraList.size() - 1);
        assertThat(testTipoobra.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testTipoobra.getCategoria()).isEqualTo(UPDATED_CATEGORIA);
        assertThat(testTipoobra.getSubcategoria()).isEqualTo(UPDATED_SUBCATEGORIA);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoobra() throws Exception {
        int databaseSizeBeforeUpdate = tipoobraRepository.findAll().size();

        // Create the Tipoobra
        TipoobraDTO tipoobraDTO = tipoobraMapper.toDto(tipoobra);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTipoobraMockMvc.perform(put("/api/tipoobras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoobraDTO)))
            .andExpect(status().isCreated());

        // Validate the Tipoobra in the database
        List<Tipoobra> tipoobraList = tipoobraRepository.findAll();
        assertThat(tipoobraList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTipoobra() throws Exception {
        // Initialize the database
        tipoobraRepository.saveAndFlush(tipoobra);
        int databaseSizeBeforeDelete = tipoobraRepository.findAll().size();

        // Get the tipoobra
        restTipoobraMockMvc.perform(delete("/api/tipoobras/{id}", tipoobra.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Tipoobra> tipoobraList = tipoobraRepository.findAll();
        assertThat(tipoobraList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tipoobra.class);
        Tipoobra tipoobra1 = new Tipoobra();
        tipoobra1.setId(1L);
        Tipoobra tipoobra2 = new Tipoobra();
        tipoobra2.setId(tipoobra1.getId());
        assertThat(tipoobra1).isEqualTo(tipoobra2);
        tipoobra2.setId(2L);
        assertThat(tipoobra1).isNotEqualTo(tipoobra2);
        tipoobra1.setId(null);
        assertThat(tipoobra1).isNotEqualTo(tipoobra2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoobraDTO.class);
        TipoobraDTO tipoobraDTO1 = new TipoobraDTO();
        tipoobraDTO1.setId(1L);
        TipoobraDTO tipoobraDTO2 = new TipoobraDTO();
        assertThat(tipoobraDTO1).isNotEqualTo(tipoobraDTO2);
        tipoobraDTO2.setId(tipoobraDTO1.getId());
        assertThat(tipoobraDTO1).isEqualTo(tipoobraDTO2);
        tipoobraDTO2.setId(2L);
        assertThat(tipoobraDTO1).isNotEqualTo(tipoobraDTO2);
        tipoobraDTO1.setId(null);
        assertThat(tipoobraDTO1).isNotEqualTo(tipoobraDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(tipoobraMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(tipoobraMapper.fromId(null)).isNull();
    }
}
