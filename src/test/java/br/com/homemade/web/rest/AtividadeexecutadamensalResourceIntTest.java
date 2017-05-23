package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Atividadeexecutadamensal;
import br.com.homemade.repository.AtividadeexecutadamensalRepository;
import br.com.homemade.service.dto.AtividadeexecutadamensalDTO;
import br.com.homemade.service.mapper.AtividadeexecutadamensalMapper;
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
 * Test class for the AtividadeexecutadamensalResource REST controller.
 *
 * @see AtividadeexecutadamensalResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class AtividadeexecutadamensalResourceIntTest {

    private static final Float DEFAULT_PERCENTUALATACADO = 1F;
    private static final Float UPDATED_PERCENTUALATACADO = 2F;

    private static final Float DEFAULT_FIM = 1F;
    private static final Float UPDATED_FIM = 2F;

    private static final Double DEFAULT_INICIO = 1D;
    private static final Double UPDATED_INICIO = 2D;

    private static final String DEFAULT_LADO = "AAAAAAAAAA";
    private static final String UPDATED_LADO = "BBBBBBBBBB";

    private static final Float DEFAULT_PERCENTUALCONCLUIDO = 1F;
    private static final Float UPDATED_PERCENTUALCONCLUIDO = 2F;

    @Autowired
    private AtividadeexecutadamensalRepository atividadeexecutadamensalRepository;

    @Autowired
    private AtividadeexecutadamensalMapper atividadeexecutadamensalMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAtividadeexecutadamensalMockMvc;

    private Atividadeexecutadamensal atividadeexecutadamensal;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AtividadeexecutadamensalResource atividadeexecutadamensalResource = new AtividadeexecutadamensalResource(atividadeexecutadamensalRepository, atividadeexecutadamensalMapper);
        this.restAtividadeexecutadamensalMockMvc = MockMvcBuilders.standaloneSetup(atividadeexecutadamensalResource)
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
    public static Atividadeexecutadamensal createEntity(EntityManager em) {
        Atividadeexecutadamensal atividadeexecutadamensal = new Atividadeexecutadamensal()
            .percentualatacado(DEFAULT_PERCENTUALATACADO)
            .fim(DEFAULT_FIM)
            .inicio(DEFAULT_INICIO)
            .lado(DEFAULT_LADO)
            .percentualconcluido(DEFAULT_PERCENTUALCONCLUIDO);
        return atividadeexecutadamensal;
    }

    @Before
    public void initTest() {
        atividadeexecutadamensal = createEntity(em);
    }

    @Test
    @Transactional
    public void createAtividadeexecutadamensal() throws Exception {
        int databaseSizeBeforeCreate = atividadeexecutadamensalRepository.findAll().size();

        // Create the Atividadeexecutadamensal
        AtividadeexecutadamensalDTO atividadeexecutadamensalDTO = atividadeexecutadamensalMapper.toDto(atividadeexecutadamensal);
        restAtividadeexecutadamensalMockMvc.perform(post("/api/atividadeexecutadamensals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(atividadeexecutadamensalDTO)))
            .andExpect(status().isCreated());

        // Validate the Atividadeexecutadamensal in the database
        List<Atividadeexecutadamensal> atividadeexecutadamensalList = atividadeexecutadamensalRepository.findAll();
        assertThat(atividadeexecutadamensalList).hasSize(databaseSizeBeforeCreate + 1);
        Atividadeexecutadamensal testAtividadeexecutadamensal = atividadeexecutadamensalList.get(atividadeexecutadamensalList.size() - 1);
        assertThat(testAtividadeexecutadamensal.getPercentualatacado()).isEqualTo(DEFAULT_PERCENTUALATACADO);
        assertThat(testAtividadeexecutadamensal.getFim()).isEqualTo(DEFAULT_FIM);
        assertThat(testAtividadeexecutadamensal.getInicio()).isEqualTo(DEFAULT_INICIO);
        assertThat(testAtividadeexecutadamensal.getLado()).isEqualTo(DEFAULT_LADO);
        assertThat(testAtividadeexecutadamensal.getPercentualconcluido()).isEqualTo(DEFAULT_PERCENTUALCONCLUIDO);
    }

    @Test
    @Transactional
    public void createAtividadeexecutadamensalWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = atividadeexecutadamensalRepository.findAll().size();

        // Create the Atividadeexecutadamensal with an existing ID
        atividadeexecutadamensal.setId(1L);
        AtividadeexecutadamensalDTO atividadeexecutadamensalDTO = atividadeexecutadamensalMapper.toDto(atividadeexecutadamensal);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAtividadeexecutadamensalMockMvc.perform(post("/api/atividadeexecutadamensals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(atividadeexecutadamensalDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Atividadeexecutadamensal> atividadeexecutadamensalList = atividadeexecutadamensalRepository.findAll();
        assertThat(atividadeexecutadamensalList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAtividadeexecutadamensals() throws Exception {
        // Initialize the database
        atividadeexecutadamensalRepository.saveAndFlush(atividadeexecutadamensal);

        // Get all the atividadeexecutadamensalList
        restAtividadeexecutadamensalMockMvc.perform(get("/api/atividadeexecutadamensals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(atividadeexecutadamensal.getId().intValue())))
            .andExpect(jsonPath("$.[*].percentualatacado").value(hasItem(DEFAULT_PERCENTUALATACADO.doubleValue())))
            .andExpect(jsonPath("$.[*].fim").value(hasItem(DEFAULT_FIM.doubleValue())))
            .andExpect(jsonPath("$.[*].inicio").value(hasItem(DEFAULT_INICIO.doubleValue())))
            .andExpect(jsonPath("$.[*].lado").value(hasItem(DEFAULT_LADO.toString())))
            .andExpect(jsonPath("$.[*].percentualconcluido").value(hasItem(DEFAULT_PERCENTUALCONCLUIDO.doubleValue())));
    }

    @Test
    @Transactional
    public void getAtividadeexecutadamensal() throws Exception {
        // Initialize the database
        atividadeexecutadamensalRepository.saveAndFlush(atividadeexecutadamensal);

        // Get the atividadeexecutadamensal
        restAtividadeexecutadamensalMockMvc.perform(get("/api/atividadeexecutadamensals/{id}", atividadeexecutadamensal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(atividadeexecutadamensal.getId().intValue()))
            .andExpect(jsonPath("$.percentualatacado").value(DEFAULT_PERCENTUALATACADO.doubleValue()))
            .andExpect(jsonPath("$.fim").value(DEFAULT_FIM.doubleValue()))
            .andExpect(jsonPath("$.inicio").value(DEFAULT_INICIO.doubleValue()))
            .andExpect(jsonPath("$.lado").value(DEFAULT_LADO.toString()))
            .andExpect(jsonPath("$.percentualconcluido").value(DEFAULT_PERCENTUALCONCLUIDO.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAtividadeexecutadamensal() throws Exception {
        // Get the atividadeexecutadamensal
        restAtividadeexecutadamensalMockMvc.perform(get("/api/atividadeexecutadamensals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAtividadeexecutadamensal() throws Exception {
        // Initialize the database
        atividadeexecutadamensalRepository.saveAndFlush(atividadeexecutadamensal);
        int databaseSizeBeforeUpdate = atividadeexecutadamensalRepository.findAll().size();

        // Update the atividadeexecutadamensal
        Atividadeexecutadamensal updatedAtividadeexecutadamensal = atividadeexecutadamensalRepository.findOne(atividadeexecutadamensal.getId());
        updatedAtividadeexecutadamensal
            .percentualatacado(UPDATED_PERCENTUALATACADO)
            .fim(UPDATED_FIM)
            .inicio(UPDATED_INICIO)
            .lado(UPDATED_LADO)
            .percentualconcluido(UPDATED_PERCENTUALCONCLUIDO);
        AtividadeexecutadamensalDTO atividadeexecutadamensalDTO = atividadeexecutadamensalMapper.toDto(updatedAtividadeexecutadamensal);

        restAtividadeexecutadamensalMockMvc.perform(put("/api/atividadeexecutadamensals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(atividadeexecutadamensalDTO)))
            .andExpect(status().isOk());

        // Validate the Atividadeexecutadamensal in the database
        List<Atividadeexecutadamensal> atividadeexecutadamensalList = atividadeexecutadamensalRepository.findAll();
        assertThat(atividadeexecutadamensalList).hasSize(databaseSizeBeforeUpdate);
        Atividadeexecutadamensal testAtividadeexecutadamensal = atividadeexecutadamensalList.get(atividadeexecutadamensalList.size() - 1);
        assertThat(testAtividadeexecutadamensal.getPercentualatacado()).isEqualTo(UPDATED_PERCENTUALATACADO);
        assertThat(testAtividadeexecutadamensal.getFim()).isEqualTo(UPDATED_FIM);
        assertThat(testAtividadeexecutadamensal.getInicio()).isEqualTo(UPDATED_INICIO);
        assertThat(testAtividadeexecutadamensal.getLado()).isEqualTo(UPDATED_LADO);
        assertThat(testAtividadeexecutadamensal.getPercentualconcluido()).isEqualTo(UPDATED_PERCENTUALCONCLUIDO);
    }

    @Test
    @Transactional
    public void updateNonExistingAtividadeexecutadamensal() throws Exception {
        int databaseSizeBeforeUpdate = atividadeexecutadamensalRepository.findAll().size();

        // Create the Atividadeexecutadamensal
        AtividadeexecutadamensalDTO atividadeexecutadamensalDTO = atividadeexecutadamensalMapper.toDto(atividadeexecutadamensal);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAtividadeexecutadamensalMockMvc.perform(put("/api/atividadeexecutadamensals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(atividadeexecutadamensalDTO)))
            .andExpect(status().isCreated());

        // Validate the Atividadeexecutadamensal in the database
        List<Atividadeexecutadamensal> atividadeexecutadamensalList = atividadeexecutadamensalRepository.findAll();
        assertThat(atividadeexecutadamensalList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAtividadeexecutadamensal() throws Exception {
        // Initialize the database
        atividadeexecutadamensalRepository.saveAndFlush(atividadeexecutadamensal);
        int databaseSizeBeforeDelete = atividadeexecutadamensalRepository.findAll().size();

        // Get the atividadeexecutadamensal
        restAtividadeexecutadamensalMockMvc.perform(delete("/api/atividadeexecutadamensals/{id}", atividadeexecutadamensal.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Atividadeexecutadamensal> atividadeexecutadamensalList = atividadeexecutadamensalRepository.findAll();
        assertThat(atividadeexecutadamensalList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Atividadeexecutadamensal.class);
        Atividadeexecutadamensal atividadeexecutadamensal1 = new Atividadeexecutadamensal();
        atividadeexecutadamensal1.setId(1L);
        Atividadeexecutadamensal atividadeexecutadamensal2 = new Atividadeexecutadamensal();
        atividadeexecutadamensal2.setId(atividadeexecutadamensal1.getId());
        assertThat(atividadeexecutadamensal1).isEqualTo(atividadeexecutadamensal2);
        atividadeexecutadamensal2.setId(2L);
        assertThat(atividadeexecutadamensal1).isNotEqualTo(atividadeexecutadamensal2);
        atividadeexecutadamensal1.setId(null);
        assertThat(atividadeexecutadamensal1).isNotEqualTo(atividadeexecutadamensal2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AtividadeexecutadamensalDTO.class);
        AtividadeexecutadamensalDTO atividadeexecutadamensalDTO1 = new AtividadeexecutadamensalDTO();
        atividadeexecutadamensalDTO1.setId(1L);
        AtividadeexecutadamensalDTO atividadeexecutadamensalDTO2 = new AtividadeexecutadamensalDTO();
        assertThat(atividadeexecutadamensalDTO1).isNotEqualTo(atividadeexecutadamensalDTO2);
        atividadeexecutadamensalDTO2.setId(atividadeexecutadamensalDTO1.getId());
        assertThat(atividadeexecutadamensalDTO1).isEqualTo(atividadeexecutadamensalDTO2);
        atividadeexecutadamensalDTO2.setId(2L);
        assertThat(atividadeexecutadamensalDTO1).isNotEqualTo(atividadeexecutadamensalDTO2);
        atividadeexecutadamensalDTO1.setId(null);
        assertThat(atividadeexecutadamensalDTO1).isNotEqualTo(atividadeexecutadamensalDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(atividadeexecutadamensalMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(atividadeexecutadamensalMapper.fromId(null)).isNull();
    }
}
