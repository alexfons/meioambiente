package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Fisico;
import br.com.homemade.repository.FisicoRepository;
import br.com.homemade.service.dto.FisicoDTO;
import br.com.homemade.service.mapper.FisicoMapper;
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
 * Test class for the FisicoResource REST controller.
 *
 * @see FisicoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class FisicoResourceIntTest {

    private static final Double DEFAULT_EXTENSAO = 1D;
    private static final Double UPDATED_EXTENSAO = 2D;

    private static final String DEFAULT_TIPO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO = "BBBBBBBBBB";

    private static final String DEFAULT_TITULO_FIM = "AAAAAAAAAA";
    private static final String UPDATED_TITULO_FIM = "BBBBBBBBBB";

    private static final String DEFAULT_TITULO_INICIO = "AAAAAAAAAA";
    private static final String UPDATED_TITULO_INICIO = "BBBBBBBBBB";

    @Autowired
    private FisicoRepository fisicoRepository;

    @Autowired
    private FisicoMapper fisicoMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFisicoMockMvc;

    private Fisico fisico;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        FisicoResource fisicoResource = new FisicoResource(fisicoRepository, fisicoMapper);
        this.restFisicoMockMvc = MockMvcBuilders.standaloneSetup(fisicoResource)
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
    public static Fisico createEntity(EntityManager em) {
        Fisico fisico = new Fisico()
            .extensao(DEFAULT_EXTENSAO)
            .tipo(DEFAULT_TIPO)
            .tituloFim(DEFAULT_TITULO_FIM)
            .tituloInicio(DEFAULT_TITULO_INICIO);
        return fisico;
    }

    @Before
    public void initTest() {
        fisico = createEntity(em);
    }

    @Test
    @Transactional
    public void createFisico() throws Exception {
        int databaseSizeBeforeCreate = fisicoRepository.findAll().size();

        // Create the Fisico
        FisicoDTO fisicoDTO = fisicoMapper.toDto(fisico);
        restFisicoMockMvc.perform(post("/api/fisicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fisicoDTO)))
            .andExpect(status().isCreated());

        // Validate the Fisico in the database
        List<Fisico> fisicoList = fisicoRepository.findAll();
        assertThat(fisicoList).hasSize(databaseSizeBeforeCreate + 1);
        Fisico testFisico = fisicoList.get(fisicoList.size() - 1);
        assertThat(testFisico.getExtensao()).isEqualTo(DEFAULT_EXTENSAO);
        assertThat(testFisico.getTipo()).isEqualTo(DEFAULT_TIPO);
        assertThat(testFisico.getTituloFim()).isEqualTo(DEFAULT_TITULO_FIM);
        assertThat(testFisico.getTituloInicio()).isEqualTo(DEFAULT_TITULO_INICIO);
    }

    @Test
    @Transactional
    public void createFisicoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fisicoRepository.findAll().size();

        // Create the Fisico with an existing ID
        fisico.setId(1L);
        FisicoDTO fisicoDTO = fisicoMapper.toDto(fisico);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFisicoMockMvc.perform(post("/api/fisicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fisicoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Fisico> fisicoList = fisicoRepository.findAll();
        assertThat(fisicoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFisicos() throws Exception {
        // Initialize the database
        fisicoRepository.saveAndFlush(fisico);

        // Get all the fisicoList
        restFisicoMockMvc.perform(get("/api/fisicos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fisico.getId().intValue())))
            .andExpect(jsonPath("$.[*].extensao").value(hasItem(DEFAULT_EXTENSAO.doubleValue())))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO.toString())))
            .andExpect(jsonPath("$.[*].tituloFim").value(hasItem(DEFAULT_TITULO_FIM.toString())))
            .andExpect(jsonPath("$.[*].tituloInicio").value(hasItem(DEFAULT_TITULO_INICIO.toString())));
    }

    @Test
    @Transactional
    public void getFisico() throws Exception {
        // Initialize the database
        fisicoRepository.saveAndFlush(fisico);

        // Get the fisico
        restFisicoMockMvc.perform(get("/api/fisicos/{id}", fisico.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fisico.getId().intValue()))
            .andExpect(jsonPath("$.extensao").value(DEFAULT_EXTENSAO.doubleValue()))
            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO.toString()))
            .andExpect(jsonPath("$.tituloFim").value(DEFAULT_TITULO_FIM.toString()))
            .andExpect(jsonPath("$.tituloInicio").value(DEFAULT_TITULO_INICIO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFisico() throws Exception {
        // Get the fisico
        restFisicoMockMvc.perform(get("/api/fisicos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFisico() throws Exception {
        // Initialize the database
        fisicoRepository.saveAndFlush(fisico);
        int databaseSizeBeforeUpdate = fisicoRepository.findAll().size();

        // Update the fisico
        Fisico updatedFisico = fisicoRepository.findOne(fisico.getId());
        updatedFisico
            .extensao(UPDATED_EXTENSAO)
            .tipo(UPDATED_TIPO)
            .tituloFim(UPDATED_TITULO_FIM)
            .tituloInicio(UPDATED_TITULO_INICIO);
        FisicoDTO fisicoDTO = fisicoMapper.toDto(updatedFisico);

        restFisicoMockMvc.perform(put("/api/fisicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fisicoDTO)))
            .andExpect(status().isOk());

        // Validate the Fisico in the database
        List<Fisico> fisicoList = fisicoRepository.findAll();
        assertThat(fisicoList).hasSize(databaseSizeBeforeUpdate);
        Fisico testFisico = fisicoList.get(fisicoList.size() - 1);
        assertThat(testFisico.getExtensao()).isEqualTo(UPDATED_EXTENSAO);
        assertThat(testFisico.getTipo()).isEqualTo(UPDATED_TIPO);
        assertThat(testFisico.getTituloFim()).isEqualTo(UPDATED_TITULO_FIM);
        assertThat(testFisico.getTituloInicio()).isEqualTo(UPDATED_TITULO_INICIO);
    }

    @Test
    @Transactional
    public void updateNonExistingFisico() throws Exception {
        int databaseSizeBeforeUpdate = fisicoRepository.findAll().size();

        // Create the Fisico
        FisicoDTO fisicoDTO = fisicoMapper.toDto(fisico);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFisicoMockMvc.perform(put("/api/fisicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fisicoDTO)))
            .andExpect(status().isCreated());

        // Validate the Fisico in the database
        List<Fisico> fisicoList = fisicoRepository.findAll();
        assertThat(fisicoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFisico() throws Exception {
        // Initialize the database
        fisicoRepository.saveAndFlush(fisico);
        int databaseSizeBeforeDelete = fisicoRepository.findAll().size();

        // Get the fisico
        restFisicoMockMvc.perform(delete("/api/fisicos/{id}", fisico.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Fisico> fisicoList = fisicoRepository.findAll();
        assertThat(fisicoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Fisico.class);
        Fisico fisico1 = new Fisico();
        fisico1.setId(1L);
        Fisico fisico2 = new Fisico();
        fisico2.setId(fisico1.getId());
        assertThat(fisico1).isEqualTo(fisico2);
        fisico2.setId(2L);
        assertThat(fisico1).isNotEqualTo(fisico2);
        fisico1.setId(null);
        assertThat(fisico1).isNotEqualTo(fisico2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FisicoDTO.class);
        FisicoDTO fisicoDTO1 = new FisicoDTO();
        fisicoDTO1.setId(1L);
        FisicoDTO fisicoDTO2 = new FisicoDTO();
        assertThat(fisicoDTO1).isNotEqualTo(fisicoDTO2);
        fisicoDTO2.setId(fisicoDTO1.getId());
        assertThat(fisicoDTO1).isEqualTo(fisicoDTO2);
        fisicoDTO2.setId(2L);
        assertThat(fisicoDTO1).isNotEqualTo(fisicoDTO2);
        fisicoDTO1.setId(null);
        assertThat(fisicoDTO1).isNotEqualTo(fisicoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(fisicoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(fisicoMapper.fromId(null)).isNull();
    }
}
