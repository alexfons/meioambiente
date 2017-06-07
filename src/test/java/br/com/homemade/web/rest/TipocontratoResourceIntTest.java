package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Tipocontrato;
import br.com.homemade.repository.TipocontratoRepository;
import br.com.homemade.service.dto.TipocontratoDTO;
import br.com.homemade.service.mapper.TipocontratoMapper;
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
 * Test class for the TipocontratoResource REST controller.
 *
 * @see TipocontratoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class TipocontratoResourceIntTest {

    private static final String DEFAULT_CATEGORIA = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORIA = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final String DEFAULT_SUBCATEGORIA = "AAAAAAAAAA";
    private static final String UPDATED_SUBCATEGORIA = "BBBBBBBBBB";

    @Autowired
    private TipocontratoRepository tipocontratoRepository;

    @Autowired
    private TipocontratoMapper tipocontratoMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTipocontratoMockMvc;

    private Tipocontrato tipocontrato;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TipocontratoResource tipocontratoResource = new TipocontratoResource(tipocontratoRepository, tipocontratoMapper);
        this.restTipocontratoMockMvc = MockMvcBuilders.standaloneSetup(tipocontratoResource)
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
    public static Tipocontrato createEntity(EntityManager em) {
        Tipocontrato tipocontrato = new Tipocontrato()
            .categoria(DEFAULT_CATEGORIA)
            .descricao(DEFAULT_DESCRICAO)
            .subcategoria(DEFAULT_SUBCATEGORIA);
        return tipocontrato;
    }

    @Before
    public void initTest() {
        tipocontrato = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipocontrato() throws Exception {
        int databaseSizeBeforeCreate = tipocontratoRepository.findAll().size();

        // Create the Tipocontrato
        TipocontratoDTO tipocontratoDTO = tipocontratoMapper.toDto(tipocontrato);
        restTipocontratoMockMvc.perform(post("/api/tipocontratoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipocontratoDTO)))
            .andExpect(status().isCreated());

        // Validate the Tipocontrato in the database
        List<Tipocontrato> tipocontratoList = tipocontratoRepository.findAll();
        assertThat(tipocontratoList).hasSize(databaseSizeBeforeCreate + 1);
        Tipocontrato testTipocontrato = tipocontratoList.get(tipocontratoList.size() - 1);
        assertThat(testTipocontrato.getCategoria()).isEqualTo(DEFAULT_CATEGORIA);
        assertThat(testTipocontrato.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testTipocontrato.getSubcategoria()).isEqualTo(DEFAULT_SUBCATEGORIA);
    }

    @Test
    @Transactional
    public void createTipocontratoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipocontratoRepository.findAll().size();

        // Create the Tipocontrato with an existing ID
        tipocontrato.setId(1L);
        TipocontratoDTO tipocontratoDTO = tipocontratoMapper.toDto(tipocontrato);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipocontratoMockMvc.perform(post("/api/tipocontratoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipocontratoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Tipocontrato> tipocontratoList = tipocontratoRepository.findAll();
        assertThat(tipocontratoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTipocontratoes() throws Exception {
        // Initialize the database
        tipocontratoRepository.saveAndFlush(tipocontrato);

        // Get all the tipocontratoList
        restTipocontratoMockMvc.perform(get("/api/tipocontratoes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipocontrato.getId().intValue())))
            .andExpect(jsonPath("$.[*].categoria").value(hasItem(DEFAULT_CATEGORIA.toString())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].subcategoria").value(hasItem(DEFAULT_SUBCATEGORIA.toString())));
    }

    @Test
    @Transactional
    public void getTipocontrato() throws Exception {
        // Initialize the database
        tipocontratoRepository.saveAndFlush(tipocontrato);

        // Get the tipocontrato
        restTipocontratoMockMvc.perform(get("/api/tipocontratoes/{id}", tipocontrato.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipocontrato.getId().intValue()))
            .andExpect(jsonPath("$.categoria").value(DEFAULT_CATEGORIA.toString()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.subcategoria").value(DEFAULT_SUBCATEGORIA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTipocontrato() throws Exception {
        // Get the tipocontrato
        restTipocontratoMockMvc.perform(get("/api/tipocontratoes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipocontrato() throws Exception {
        // Initialize the database
        tipocontratoRepository.saveAndFlush(tipocontrato);
        int databaseSizeBeforeUpdate = tipocontratoRepository.findAll().size();

        // Update the tipocontrato
        Tipocontrato updatedTipocontrato = tipocontratoRepository.findOne(tipocontrato.getId());
        updatedTipocontrato
            .categoria(UPDATED_CATEGORIA)
            .descricao(UPDATED_DESCRICAO)
            .subcategoria(UPDATED_SUBCATEGORIA);
        TipocontratoDTO tipocontratoDTO = tipocontratoMapper.toDto(updatedTipocontrato);

        restTipocontratoMockMvc.perform(put("/api/tipocontratoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipocontratoDTO)))
            .andExpect(status().isOk());

        // Validate the Tipocontrato in the database
        List<Tipocontrato> tipocontratoList = tipocontratoRepository.findAll();
        assertThat(tipocontratoList).hasSize(databaseSizeBeforeUpdate);
        Tipocontrato testTipocontrato = tipocontratoList.get(tipocontratoList.size() - 1);
        assertThat(testTipocontrato.getCategoria()).isEqualTo(UPDATED_CATEGORIA);
        assertThat(testTipocontrato.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testTipocontrato.getSubcategoria()).isEqualTo(UPDATED_SUBCATEGORIA);
    }

    @Test
    @Transactional
    public void updateNonExistingTipocontrato() throws Exception {
        int databaseSizeBeforeUpdate = tipocontratoRepository.findAll().size();

        // Create the Tipocontrato
        TipocontratoDTO tipocontratoDTO = tipocontratoMapper.toDto(tipocontrato);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTipocontratoMockMvc.perform(put("/api/tipocontratoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipocontratoDTO)))
            .andExpect(status().isCreated());

        // Validate the Tipocontrato in the database
        List<Tipocontrato> tipocontratoList = tipocontratoRepository.findAll();
        assertThat(tipocontratoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTipocontrato() throws Exception {
        // Initialize the database
        tipocontratoRepository.saveAndFlush(tipocontrato);
        int databaseSizeBeforeDelete = tipocontratoRepository.findAll().size();

        // Get the tipocontrato
        restTipocontratoMockMvc.perform(delete("/api/tipocontratoes/{id}", tipocontrato.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Tipocontrato> tipocontratoList = tipocontratoRepository.findAll();
        assertThat(tipocontratoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tipocontrato.class);
        Tipocontrato tipocontrato1 = new Tipocontrato();
        tipocontrato1.setId(1L);
        Tipocontrato tipocontrato2 = new Tipocontrato();
        tipocontrato2.setId(tipocontrato1.getId());
        assertThat(tipocontrato1).isEqualTo(tipocontrato2);
        tipocontrato2.setId(2L);
        assertThat(tipocontrato1).isNotEqualTo(tipocontrato2);
        tipocontrato1.setId(null);
        assertThat(tipocontrato1).isNotEqualTo(tipocontrato2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipocontratoDTO.class);
        TipocontratoDTO tipocontratoDTO1 = new TipocontratoDTO();
        tipocontratoDTO1.setId(1L);
        TipocontratoDTO tipocontratoDTO2 = new TipocontratoDTO();
        assertThat(tipocontratoDTO1).isNotEqualTo(tipocontratoDTO2);
        tipocontratoDTO2.setId(tipocontratoDTO1.getId());
        assertThat(tipocontratoDTO1).isEqualTo(tipocontratoDTO2);
        tipocontratoDTO2.setId(2L);
        assertThat(tipocontratoDTO1).isNotEqualTo(tipocontratoDTO2);
        tipocontratoDTO1.setId(null);
        assertThat(tipocontratoDTO1).isNotEqualTo(tipocontratoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(tipocontratoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(tipocontratoMapper.fromId(null)).isNull();
    }
}
