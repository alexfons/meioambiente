package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Tipoadministrativo;
import br.com.homemade.repository.TipoadministrativoRepository;
import br.com.homemade.service.dto.TipoadministrativoDTO;
import br.com.homemade.service.mapper.TipoadministrativoMapper;
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
 * Test class for the TipoadministrativoResource REST controller.
 *
 * @see TipoadministrativoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class TipoadministrativoResourceIntTest {

    private static final String DEFAULT_CATEGORIA = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORIA = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final String DEFAULT_SUBCATEGORIA = "AAAAAAAAAA";
    private static final String UPDATED_SUBCATEGORIA = "BBBBBBBBBB";

    @Autowired
    private TipoadministrativoRepository tipoadministrativoRepository;

    @Autowired
    private TipoadministrativoMapper tipoadministrativoMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTipoadministrativoMockMvc;

    private Tipoadministrativo tipoadministrativo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TipoadministrativoResource tipoadministrativoResource = new TipoadministrativoResource(tipoadministrativoRepository, tipoadministrativoMapper);
        this.restTipoadministrativoMockMvc = MockMvcBuilders.standaloneSetup(tipoadministrativoResource)
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
    public static Tipoadministrativo createEntity(EntityManager em) {
        Tipoadministrativo tipoadministrativo = new Tipoadministrativo()
            .categoria(DEFAULT_CATEGORIA)
            .descricao(DEFAULT_DESCRICAO)
            .subcategoria(DEFAULT_SUBCATEGORIA);
        return tipoadministrativo;
    }

    @Before
    public void initTest() {
        tipoadministrativo = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoadministrativo() throws Exception {
        int databaseSizeBeforeCreate = tipoadministrativoRepository.findAll().size();

        // Create the Tipoadministrativo
        TipoadministrativoDTO tipoadministrativoDTO = tipoadministrativoMapper.toDto(tipoadministrativo);
        restTipoadministrativoMockMvc.perform(post("/api/tipoadministrativos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoadministrativoDTO)))
            .andExpect(status().isCreated());

        // Validate the Tipoadministrativo in the database
        List<Tipoadministrativo> tipoadministrativoList = tipoadministrativoRepository.findAll();
        assertThat(tipoadministrativoList).hasSize(databaseSizeBeforeCreate + 1);
        Tipoadministrativo testTipoadministrativo = tipoadministrativoList.get(tipoadministrativoList.size() - 1);
        assertThat(testTipoadministrativo.getCategoria()).isEqualTo(DEFAULT_CATEGORIA);
        assertThat(testTipoadministrativo.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testTipoadministrativo.getSubcategoria()).isEqualTo(DEFAULT_SUBCATEGORIA);
    }

    @Test
    @Transactional
    public void createTipoadministrativoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoadministrativoRepository.findAll().size();

        // Create the Tipoadministrativo with an existing ID
        tipoadministrativo.setId(1L);
        TipoadministrativoDTO tipoadministrativoDTO = tipoadministrativoMapper.toDto(tipoadministrativo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoadministrativoMockMvc.perform(post("/api/tipoadministrativos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoadministrativoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Tipoadministrativo> tipoadministrativoList = tipoadministrativoRepository.findAll();
        assertThat(tipoadministrativoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTipoadministrativos() throws Exception {
        // Initialize the database
        tipoadministrativoRepository.saveAndFlush(tipoadministrativo);

        // Get all the tipoadministrativoList
        restTipoadministrativoMockMvc.perform(get("/api/tipoadministrativos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoadministrativo.getId().intValue())))
            .andExpect(jsonPath("$.[*].categoria").value(hasItem(DEFAULT_CATEGORIA.toString())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].subcategoria").value(hasItem(DEFAULT_SUBCATEGORIA.toString())));
    }

    @Test
    @Transactional
    public void getTipoadministrativo() throws Exception {
        // Initialize the database
        tipoadministrativoRepository.saveAndFlush(tipoadministrativo);

        // Get the tipoadministrativo
        restTipoadministrativoMockMvc.perform(get("/api/tipoadministrativos/{id}", tipoadministrativo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipoadministrativo.getId().intValue()))
            .andExpect(jsonPath("$.categoria").value(DEFAULT_CATEGORIA.toString()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.subcategoria").value(DEFAULT_SUBCATEGORIA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTipoadministrativo() throws Exception {
        // Get the tipoadministrativo
        restTipoadministrativoMockMvc.perform(get("/api/tipoadministrativos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoadministrativo() throws Exception {
        // Initialize the database
        tipoadministrativoRepository.saveAndFlush(tipoadministrativo);
        int databaseSizeBeforeUpdate = tipoadministrativoRepository.findAll().size();

        // Update the tipoadministrativo
        Tipoadministrativo updatedTipoadministrativo = tipoadministrativoRepository.findOne(tipoadministrativo.getId());
        updatedTipoadministrativo
            .categoria(UPDATED_CATEGORIA)
            .descricao(UPDATED_DESCRICAO)
            .subcategoria(UPDATED_SUBCATEGORIA);
        TipoadministrativoDTO tipoadministrativoDTO = tipoadministrativoMapper.toDto(updatedTipoadministrativo);

        restTipoadministrativoMockMvc.perform(put("/api/tipoadministrativos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoadministrativoDTO)))
            .andExpect(status().isOk());

        // Validate the Tipoadministrativo in the database
        List<Tipoadministrativo> tipoadministrativoList = tipoadministrativoRepository.findAll();
        assertThat(tipoadministrativoList).hasSize(databaseSizeBeforeUpdate);
        Tipoadministrativo testTipoadministrativo = tipoadministrativoList.get(tipoadministrativoList.size() - 1);
        assertThat(testTipoadministrativo.getCategoria()).isEqualTo(UPDATED_CATEGORIA);
        assertThat(testTipoadministrativo.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testTipoadministrativo.getSubcategoria()).isEqualTo(UPDATED_SUBCATEGORIA);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoadministrativo() throws Exception {
        int databaseSizeBeforeUpdate = tipoadministrativoRepository.findAll().size();

        // Create the Tipoadministrativo
        TipoadministrativoDTO tipoadministrativoDTO = tipoadministrativoMapper.toDto(tipoadministrativo);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTipoadministrativoMockMvc.perform(put("/api/tipoadministrativos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoadministrativoDTO)))
            .andExpect(status().isCreated());

        // Validate the Tipoadministrativo in the database
        List<Tipoadministrativo> tipoadministrativoList = tipoadministrativoRepository.findAll();
        assertThat(tipoadministrativoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTipoadministrativo() throws Exception {
        // Initialize the database
        tipoadministrativoRepository.saveAndFlush(tipoadministrativo);
        int databaseSizeBeforeDelete = tipoadministrativoRepository.findAll().size();

        // Get the tipoadministrativo
        restTipoadministrativoMockMvc.perform(delete("/api/tipoadministrativos/{id}", tipoadministrativo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Tipoadministrativo> tipoadministrativoList = tipoadministrativoRepository.findAll();
        assertThat(tipoadministrativoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tipoadministrativo.class);
        Tipoadministrativo tipoadministrativo1 = new Tipoadministrativo();
        tipoadministrativo1.setId(1L);
        Tipoadministrativo tipoadministrativo2 = new Tipoadministrativo();
        tipoadministrativo2.setId(tipoadministrativo1.getId());
        assertThat(tipoadministrativo1).isEqualTo(tipoadministrativo2);
        tipoadministrativo2.setId(2L);
        assertThat(tipoadministrativo1).isNotEqualTo(tipoadministrativo2);
        tipoadministrativo1.setId(null);
        assertThat(tipoadministrativo1).isNotEqualTo(tipoadministrativo2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoadministrativoDTO.class);
        TipoadministrativoDTO tipoadministrativoDTO1 = new TipoadministrativoDTO();
        tipoadministrativoDTO1.setId(1L);
        TipoadministrativoDTO tipoadministrativoDTO2 = new TipoadministrativoDTO();
        assertThat(tipoadministrativoDTO1).isNotEqualTo(tipoadministrativoDTO2);
        tipoadministrativoDTO2.setId(tipoadministrativoDTO1.getId());
        assertThat(tipoadministrativoDTO1).isEqualTo(tipoadministrativoDTO2);
        tipoadministrativoDTO2.setId(2L);
        assertThat(tipoadministrativoDTO1).isNotEqualTo(tipoadministrativoDTO2);
        tipoadministrativoDTO1.setId(null);
        assertThat(tipoadministrativoDTO1).isNotEqualTo(tipoadministrativoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(tipoadministrativoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(tipoadministrativoMapper.fromId(null)).isNull();
    }
}
