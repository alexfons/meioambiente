package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.TipocertifConfor;
import br.com.homemade.repository.TipocertifConforRepository;
import br.com.homemade.service.dto.TipocertifConforDTO;
import br.com.homemade.service.mapper.TipocertifConforMapper;
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
 * Test class for the TipocertifConforResource REST controller.
 *
 * @see TipocertifConforResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class TipocertifConforResourceIntTest {

    private static final String DEFAULT_CATEGORIA = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORIA = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final String DEFAULT_SUBCATEGORIA = "AAAAAAAAAA";
    private static final String UPDATED_SUBCATEGORIA = "BBBBBBBBBB";

    @Autowired
    private TipocertifConforRepository tipocertifConforRepository;

    @Autowired
    private TipocertifConforMapper tipocertifConforMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTipocertifConforMockMvc;

    private TipocertifConfor tipocertifConfor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TipocertifConforResource tipocertifConforResource = new TipocertifConforResource(tipocertifConforRepository, tipocertifConforMapper);
        this.restTipocertifConforMockMvc = MockMvcBuilders.standaloneSetup(tipocertifConforResource)
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
    public static TipocertifConfor createEntity(EntityManager em) {
        TipocertifConfor tipocertifConfor = new TipocertifConfor()
            .categoria(DEFAULT_CATEGORIA)
            .descricao(DEFAULT_DESCRICAO)
            .subcategoria(DEFAULT_SUBCATEGORIA);
        return tipocertifConfor;
    }

    @Before
    public void initTest() {
        tipocertifConfor = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipocertifConfor() throws Exception {
        int databaseSizeBeforeCreate = tipocertifConforRepository.findAll().size();

        // Create the TipocertifConfor
        TipocertifConforDTO tipocertifConforDTO = tipocertifConforMapper.toDto(tipocertifConfor);
        restTipocertifConforMockMvc.perform(post("/api/tipocertif-confors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipocertifConforDTO)))
            .andExpect(status().isCreated());

        // Validate the TipocertifConfor in the database
        List<TipocertifConfor> tipocertifConforList = tipocertifConforRepository.findAll();
        assertThat(tipocertifConforList).hasSize(databaseSizeBeforeCreate + 1);
        TipocertifConfor testTipocertifConfor = tipocertifConforList.get(tipocertifConforList.size() - 1);
        assertThat(testTipocertifConfor.getCategoria()).isEqualTo(DEFAULT_CATEGORIA);
        assertThat(testTipocertifConfor.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testTipocertifConfor.getSubcategoria()).isEqualTo(DEFAULT_SUBCATEGORIA);
    }

    @Test
    @Transactional
    public void createTipocertifConforWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipocertifConforRepository.findAll().size();

        // Create the TipocertifConfor with an existing ID
        tipocertifConfor.setId(1L);
        TipocertifConforDTO tipocertifConforDTO = tipocertifConforMapper.toDto(tipocertifConfor);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipocertifConforMockMvc.perform(post("/api/tipocertif-confors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipocertifConforDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<TipocertifConfor> tipocertifConforList = tipocertifConforRepository.findAll();
        assertThat(tipocertifConforList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTipocertifConfors() throws Exception {
        // Initialize the database
        tipocertifConforRepository.saveAndFlush(tipocertifConfor);

        // Get all the tipocertifConforList
        restTipocertifConforMockMvc.perform(get("/api/tipocertif-confors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipocertifConfor.getId().intValue())))
            .andExpect(jsonPath("$.[*].categoria").value(hasItem(DEFAULT_CATEGORIA.toString())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].subcategoria").value(hasItem(DEFAULT_SUBCATEGORIA.toString())));
    }

    @Test
    @Transactional
    public void getTipocertifConfor() throws Exception {
        // Initialize the database
        tipocertifConforRepository.saveAndFlush(tipocertifConfor);

        // Get the tipocertifConfor
        restTipocertifConforMockMvc.perform(get("/api/tipocertif-confors/{id}", tipocertifConfor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipocertifConfor.getId().intValue()))
            .andExpect(jsonPath("$.categoria").value(DEFAULT_CATEGORIA.toString()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.subcategoria").value(DEFAULT_SUBCATEGORIA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTipocertifConfor() throws Exception {
        // Get the tipocertifConfor
        restTipocertifConforMockMvc.perform(get("/api/tipocertif-confors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipocertifConfor() throws Exception {
        // Initialize the database
        tipocertifConforRepository.saveAndFlush(tipocertifConfor);
        int databaseSizeBeforeUpdate = tipocertifConforRepository.findAll().size();

        // Update the tipocertifConfor
        TipocertifConfor updatedTipocertifConfor = tipocertifConforRepository.findOne(tipocertifConfor.getId());
        updatedTipocertifConfor
            .categoria(UPDATED_CATEGORIA)
            .descricao(UPDATED_DESCRICAO)
            .subcategoria(UPDATED_SUBCATEGORIA);
        TipocertifConforDTO tipocertifConforDTO = tipocertifConforMapper.toDto(updatedTipocertifConfor);

        restTipocertifConforMockMvc.perform(put("/api/tipocertif-confors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipocertifConforDTO)))
            .andExpect(status().isOk());

        // Validate the TipocertifConfor in the database
        List<TipocertifConfor> tipocertifConforList = tipocertifConforRepository.findAll();
        assertThat(tipocertifConforList).hasSize(databaseSizeBeforeUpdate);
        TipocertifConfor testTipocertifConfor = tipocertifConforList.get(tipocertifConforList.size() - 1);
        assertThat(testTipocertifConfor.getCategoria()).isEqualTo(UPDATED_CATEGORIA);
        assertThat(testTipocertifConfor.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testTipocertifConfor.getSubcategoria()).isEqualTo(UPDATED_SUBCATEGORIA);
    }

    @Test
    @Transactional
    public void updateNonExistingTipocertifConfor() throws Exception {
        int databaseSizeBeforeUpdate = tipocertifConforRepository.findAll().size();

        // Create the TipocertifConfor
        TipocertifConforDTO tipocertifConforDTO = tipocertifConforMapper.toDto(tipocertifConfor);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTipocertifConforMockMvc.perform(put("/api/tipocertif-confors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipocertifConforDTO)))
            .andExpect(status().isCreated());

        // Validate the TipocertifConfor in the database
        List<TipocertifConfor> tipocertifConforList = tipocertifConforRepository.findAll();
        assertThat(tipocertifConforList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTipocertifConfor() throws Exception {
        // Initialize the database
        tipocertifConforRepository.saveAndFlush(tipocertifConfor);
        int databaseSizeBeforeDelete = tipocertifConforRepository.findAll().size();

        // Get the tipocertifConfor
        restTipocertifConforMockMvc.perform(delete("/api/tipocertif-confors/{id}", tipocertifConfor.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TipocertifConfor> tipocertifConforList = tipocertifConforRepository.findAll();
        assertThat(tipocertifConforList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipocertifConfor.class);
        TipocertifConfor tipocertifConfor1 = new TipocertifConfor();
        tipocertifConfor1.setId(1L);
        TipocertifConfor tipocertifConfor2 = new TipocertifConfor();
        tipocertifConfor2.setId(tipocertifConfor1.getId());
        assertThat(tipocertifConfor1).isEqualTo(tipocertifConfor2);
        tipocertifConfor2.setId(2L);
        assertThat(tipocertifConfor1).isNotEqualTo(tipocertifConfor2);
        tipocertifConfor1.setId(null);
        assertThat(tipocertifConfor1).isNotEqualTo(tipocertifConfor2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipocertifConforDTO.class);
        TipocertifConforDTO tipocertifConforDTO1 = new TipocertifConforDTO();
        tipocertifConforDTO1.setId(1L);
        TipocertifConforDTO tipocertifConforDTO2 = new TipocertifConforDTO();
        assertThat(tipocertifConforDTO1).isNotEqualTo(tipocertifConforDTO2);
        tipocertifConforDTO2.setId(tipocertifConforDTO1.getId());
        assertThat(tipocertifConforDTO1).isEqualTo(tipocertifConforDTO2);
        tipocertifConforDTO2.setId(2L);
        assertThat(tipocertifConforDTO1).isNotEqualTo(tipocertifConforDTO2);
        tipocertifConforDTO1.setId(null);
        assertThat(tipocertifConforDTO1).isNotEqualTo(tipocertifConforDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(tipocertifConforMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(tipocertifConforMapper.fromId(null)).isNull();
    }
}
