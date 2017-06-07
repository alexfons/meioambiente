package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Fisicografico;
import br.com.homemade.repository.FisicograficoRepository;
import br.com.homemade.service.dto.FisicograficoDTO;
import br.com.homemade.service.mapper.FisicograficoMapper;
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
 * Test class for the FisicograficoResource REST controller.
 *
 * @see FisicograficoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class FisicograficoResourceIntTest {

    private static final Boolean DEFAULT_ATACADA = false;
    private static final Boolean UPDATED_ATACADA = true;

    private static final Double DEFAULT_EXTENSAO = 1D;
    private static final Double UPDATED_EXTENSAO = 2D;

    private static final Double DEFAULT_FIM = 1D;
    private static final Double UPDATED_FIM = 2D;

    private static final Double DEFAULT_INICIO = 1D;
    private static final Double UPDATED_INICIO = 2D;

    private static final Integer DEFAULT_NPONTOS = 1;
    private static final Integer UPDATED_NPONTOS = 2;

    private static final Integer DEFAULT_PONTOFIM = 1;
    private static final Integer UPDATED_PONTOFIM = 2;

    private static final Integer DEFAULT_PONTOINICIO = 1;
    private static final Integer UPDATED_PONTOINICIO = 2;

    @Autowired
    private FisicograficoRepository fisicograficoRepository;

    @Autowired
    private FisicograficoMapper fisicograficoMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFisicograficoMockMvc;

    private Fisicografico fisicografico;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        FisicograficoResource fisicograficoResource = new FisicograficoResource(fisicograficoRepository, fisicograficoMapper);
        this.restFisicograficoMockMvc = MockMvcBuilders.standaloneSetup(fisicograficoResource)
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
    public static Fisicografico createEntity(EntityManager em) {
        Fisicografico fisicografico = new Fisicografico()
            .atacada(DEFAULT_ATACADA)
            .extensao(DEFAULT_EXTENSAO)
            .fim(DEFAULT_FIM)
            .inicio(DEFAULT_INICIO)
            .npontos(DEFAULT_NPONTOS)
            .pontofim(DEFAULT_PONTOFIM)
            .pontoinicio(DEFAULT_PONTOINICIO);
        return fisicografico;
    }

    @Before
    public void initTest() {
        fisicografico = createEntity(em);
    }

    @Test
    @Transactional
    public void createFisicografico() throws Exception {
        int databaseSizeBeforeCreate = fisicograficoRepository.findAll().size();

        // Create the Fisicografico
        FisicograficoDTO fisicograficoDTO = fisicograficoMapper.toDto(fisicografico);
        restFisicograficoMockMvc.perform(post("/api/fisicograficos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fisicograficoDTO)))
            .andExpect(status().isCreated());

        // Validate the Fisicografico in the database
        List<Fisicografico> fisicograficoList = fisicograficoRepository.findAll();
        assertThat(fisicograficoList).hasSize(databaseSizeBeforeCreate + 1);
        Fisicografico testFisicografico = fisicograficoList.get(fisicograficoList.size() - 1);
        assertThat(testFisicografico.isAtacada()).isEqualTo(DEFAULT_ATACADA);
        assertThat(testFisicografico.getExtensao()).isEqualTo(DEFAULT_EXTENSAO);
        assertThat(testFisicografico.getFim()).isEqualTo(DEFAULT_FIM);
        assertThat(testFisicografico.getInicio()).isEqualTo(DEFAULT_INICIO);
        assertThat(testFisicografico.getNpontos()).isEqualTo(DEFAULT_NPONTOS);
        assertThat(testFisicografico.getPontofim()).isEqualTo(DEFAULT_PONTOFIM);
        assertThat(testFisicografico.getPontoinicio()).isEqualTo(DEFAULT_PONTOINICIO);
    }

    @Test
    @Transactional
    public void createFisicograficoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fisicograficoRepository.findAll().size();

        // Create the Fisicografico with an existing ID
        fisicografico.setId(1L);
        FisicograficoDTO fisicograficoDTO = fisicograficoMapper.toDto(fisicografico);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFisicograficoMockMvc.perform(post("/api/fisicograficos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fisicograficoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Fisicografico> fisicograficoList = fisicograficoRepository.findAll();
        assertThat(fisicograficoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFisicograficos() throws Exception {
        // Initialize the database
        fisicograficoRepository.saveAndFlush(fisicografico);

        // Get all the fisicograficoList
        restFisicograficoMockMvc.perform(get("/api/fisicograficos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fisicografico.getId().intValue())))
            .andExpect(jsonPath("$.[*].atacada").value(hasItem(DEFAULT_ATACADA.booleanValue())))
            .andExpect(jsonPath("$.[*].extensao").value(hasItem(DEFAULT_EXTENSAO.doubleValue())))
            .andExpect(jsonPath("$.[*].fim").value(hasItem(DEFAULT_FIM.doubleValue())))
            .andExpect(jsonPath("$.[*].inicio").value(hasItem(DEFAULT_INICIO.doubleValue())))
            .andExpect(jsonPath("$.[*].npontos").value(hasItem(DEFAULT_NPONTOS)))
            .andExpect(jsonPath("$.[*].pontofim").value(hasItem(DEFAULT_PONTOFIM)))
            .andExpect(jsonPath("$.[*].pontoinicio").value(hasItem(DEFAULT_PONTOINICIO)));
    }

    @Test
    @Transactional
    public void getFisicografico() throws Exception {
        // Initialize the database
        fisicograficoRepository.saveAndFlush(fisicografico);

        // Get the fisicografico
        restFisicograficoMockMvc.perform(get("/api/fisicograficos/{id}", fisicografico.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fisicografico.getId().intValue()))
            .andExpect(jsonPath("$.atacada").value(DEFAULT_ATACADA.booleanValue()))
            .andExpect(jsonPath("$.extensao").value(DEFAULT_EXTENSAO.doubleValue()))
            .andExpect(jsonPath("$.fim").value(DEFAULT_FIM.doubleValue()))
            .andExpect(jsonPath("$.inicio").value(DEFAULT_INICIO.doubleValue()))
            .andExpect(jsonPath("$.npontos").value(DEFAULT_NPONTOS))
            .andExpect(jsonPath("$.pontofim").value(DEFAULT_PONTOFIM))
            .andExpect(jsonPath("$.pontoinicio").value(DEFAULT_PONTOINICIO));
    }

    @Test
    @Transactional
    public void getNonExistingFisicografico() throws Exception {
        // Get the fisicografico
        restFisicograficoMockMvc.perform(get("/api/fisicograficos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFisicografico() throws Exception {
        // Initialize the database
        fisicograficoRepository.saveAndFlush(fisicografico);
        int databaseSizeBeforeUpdate = fisicograficoRepository.findAll().size();

        // Update the fisicografico
        Fisicografico updatedFisicografico = fisicograficoRepository.findOne(fisicografico.getId());
        updatedFisicografico
            .atacada(UPDATED_ATACADA)
            .extensao(UPDATED_EXTENSAO)
            .fim(UPDATED_FIM)
            .inicio(UPDATED_INICIO)
            .npontos(UPDATED_NPONTOS)
            .pontofim(UPDATED_PONTOFIM)
            .pontoinicio(UPDATED_PONTOINICIO);
        FisicograficoDTO fisicograficoDTO = fisicograficoMapper.toDto(updatedFisicografico);

        restFisicograficoMockMvc.perform(put("/api/fisicograficos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fisicograficoDTO)))
            .andExpect(status().isOk());

        // Validate the Fisicografico in the database
        List<Fisicografico> fisicograficoList = fisicograficoRepository.findAll();
        assertThat(fisicograficoList).hasSize(databaseSizeBeforeUpdate);
        Fisicografico testFisicografico = fisicograficoList.get(fisicograficoList.size() - 1);
        assertThat(testFisicografico.isAtacada()).isEqualTo(UPDATED_ATACADA);
        assertThat(testFisicografico.getExtensao()).isEqualTo(UPDATED_EXTENSAO);
        assertThat(testFisicografico.getFim()).isEqualTo(UPDATED_FIM);
        assertThat(testFisicografico.getInicio()).isEqualTo(UPDATED_INICIO);
        assertThat(testFisicografico.getNpontos()).isEqualTo(UPDATED_NPONTOS);
        assertThat(testFisicografico.getPontofim()).isEqualTo(UPDATED_PONTOFIM);
        assertThat(testFisicografico.getPontoinicio()).isEqualTo(UPDATED_PONTOINICIO);
    }

    @Test
    @Transactional
    public void updateNonExistingFisicografico() throws Exception {
        int databaseSizeBeforeUpdate = fisicograficoRepository.findAll().size();

        // Create the Fisicografico
        FisicograficoDTO fisicograficoDTO = fisicograficoMapper.toDto(fisicografico);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFisicograficoMockMvc.perform(put("/api/fisicograficos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fisicograficoDTO)))
            .andExpect(status().isCreated());

        // Validate the Fisicografico in the database
        List<Fisicografico> fisicograficoList = fisicograficoRepository.findAll();
        assertThat(fisicograficoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFisicografico() throws Exception {
        // Initialize the database
        fisicograficoRepository.saveAndFlush(fisicografico);
        int databaseSizeBeforeDelete = fisicograficoRepository.findAll().size();

        // Get the fisicografico
        restFisicograficoMockMvc.perform(delete("/api/fisicograficos/{id}", fisicografico.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Fisicografico> fisicograficoList = fisicograficoRepository.findAll();
        assertThat(fisicograficoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Fisicografico.class);
        Fisicografico fisicografico1 = new Fisicografico();
        fisicografico1.setId(1L);
        Fisicografico fisicografico2 = new Fisicografico();
        fisicografico2.setId(fisicografico1.getId());
        assertThat(fisicografico1).isEqualTo(fisicografico2);
        fisicografico2.setId(2L);
        assertThat(fisicografico1).isNotEqualTo(fisicografico2);
        fisicografico1.setId(null);
        assertThat(fisicografico1).isNotEqualTo(fisicografico2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FisicograficoDTO.class);
        FisicograficoDTO fisicograficoDTO1 = new FisicograficoDTO();
        fisicograficoDTO1.setId(1L);
        FisicograficoDTO fisicograficoDTO2 = new FisicograficoDTO();
        assertThat(fisicograficoDTO1).isNotEqualTo(fisicograficoDTO2);
        fisicograficoDTO2.setId(fisicograficoDTO1.getId());
        assertThat(fisicograficoDTO1).isEqualTo(fisicograficoDTO2);
        fisicograficoDTO2.setId(2L);
        assertThat(fisicograficoDTO1).isNotEqualTo(fisicograficoDTO2);
        fisicograficoDTO1.setId(null);
        assertThat(fisicograficoDTO1).isNotEqualTo(fisicograficoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(fisicograficoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(fisicograficoMapper.fromId(null)).isNull();
    }
}
