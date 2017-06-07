package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Planocontas;
import br.com.homemade.repository.PlanocontasRepository;
import br.com.homemade.service.dto.PlanocontasDTO;
import br.com.homemade.service.mapper.PlanocontasMapper;
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
 * Test class for the PlanocontasResource REST controller.
 *
 * @see PlanocontasResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class PlanocontasResourceIntTest {

    private static final Integer DEFAULT_IDPLANOCONTAS = 1;
    private static final Integer UPDATED_IDPLANOCONTAS = 2;

    private static final String DEFAULT_NCONTABIL = "AAAAAAAAAA";
    private static final String UPDATED_NCONTABIL = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final String DEFAULT_TIPOCONTA = "AAAAAAAAAA";
    private static final String UPDATED_TIPOCONTA = "BBBBBBBBBB";

    private static final String DEFAULT_TIPOLANCAMENTO = "AAAAAAAAAA";
    private static final String UPDATED_TIPOLANCAMENTO = "BBBBBBBBBB";

    @Autowired
    private PlanocontasRepository planocontasRepository;

    @Autowired
    private PlanocontasMapper planocontasMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPlanocontasMockMvc;

    private Planocontas planocontas;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PlanocontasResource planocontasResource = new PlanocontasResource(planocontasRepository, planocontasMapper);
        this.restPlanocontasMockMvc = MockMvcBuilders.standaloneSetup(planocontasResource)
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
    public static Planocontas createEntity(EntityManager em) {
        Planocontas planocontas = new Planocontas()
            .idplanocontas(DEFAULT_IDPLANOCONTAS)
            .ncontabil(DEFAULT_NCONTABIL)
            .descricao(DEFAULT_DESCRICAO)
            .tipoconta(DEFAULT_TIPOCONTA)
            .tipolancamento(DEFAULT_TIPOLANCAMENTO);
        return planocontas;
    }

    @Before
    public void initTest() {
        planocontas = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlanocontas() throws Exception {
        int databaseSizeBeforeCreate = planocontasRepository.findAll().size();

        // Create the Planocontas
        PlanocontasDTO planocontasDTO = planocontasMapper.toDto(planocontas);
        restPlanocontasMockMvc.perform(post("/api/planocontas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planocontasDTO)))
            .andExpect(status().isCreated());

        // Validate the Planocontas in the database
        List<Planocontas> planocontasList = planocontasRepository.findAll();
        assertThat(planocontasList).hasSize(databaseSizeBeforeCreate + 1);
        Planocontas testPlanocontas = planocontasList.get(planocontasList.size() - 1);
        assertThat(testPlanocontas.getIdplanocontas()).isEqualTo(DEFAULT_IDPLANOCONTAS);
        assertThat(testPlanocontas.getNcontabil()).isEqualTo(DEFAULT_NCONTABIL);
        assertThat(testPlanocontas.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testPlanocontas.getTipoconta()).isEqualTo(DEFAULT_TIPOCONTA);
        assertThat(testPlanocontas.getTipolancamento()).isEqualTo(DEFAULT_TIPOLANCAMENTO);
    }

    @Test
    @Transactional
    public void createPlanocontasWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = planocontasRepository.findAll().size();

        // Create the Planocontas with an existing ID
        planocontas.setId(1L);
        PlanocontasDTO planocontasDTO = planocontasMapper.toDto(planocontas);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlanocontasMockMvc.perform(post("/api/planocontas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planocontasDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Planocontas> planocontasList = planocontasRepository.findAll();
        assertThat(planocontasList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPlanocontas() throws Exception {
        // Initialize the database
        planocontasRepository.saveAndFlush(planocontas);

        // Get all the planocontasList
        restPlanocontasMockMvc.perform(get("/api/planocontas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planocontas.getId().intValue())))
            .andExpect(jsonPath("$.[*].idplanocontas").value(hasItem(DEFAULT_IDPLANOCONTAS)))
            .andExpect(jsonPath("$.[*].ncontabil").value(hasItem(DEFAULT_NCONTABIL.toString())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].tipoconta").value(hasItem(DEFAULT_TIPOCONTA.toString())))
            .andExpect(jsonPath("$.[*].tipolancamento").value(hasItem(DEFAULT_TIPOLANCAMENTO.toString())));
    }

    @Test
    @Transactional
    public void getPlanocontas() throws Exception {
        // Initialize the database
        planocontasRepository.saveAndFlush(planocontas);

        // Get the planocontas
        restPlanocontasMockMvc.perform(get("/api/planocontas/{id}", planocontas.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(planocontas.getId().intValue()))
            .andExpect(jsonPath("$.idplanocontas").value(DEFAULT_IDPLANOCONTAS))
            .andExpect(jsonPath("$.ncontabil").value(DEFAULT_NCONTABIL.toString()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.tipoconta").value(DEFAULT_TIPOCONTA.toString()))
            .andExpect(jsonPath("$.tipolancamento").value(DEFAULT_TIPOLANCAMENTO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPlanocontas() throws Exception {
        // Get the planocontas
        restPlanocontasMockMvc.perform(get("/api/planocontas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlanocontas() throws Exception {
        // Initialize the database
        planocontasRepository.saveAndFlush(planocontas);
        int databaseSizeBeforeUpdate = planocontasRepository.findAll().size();

        // Update the planocontas
        Planocontas updatedPlanocontas = planocontasRepository.findOne(planocontas.getId());
        updatedPlanocontas
            .idplanocontas(UPDATED_IDPLANOCONTAS)
            .ncontabil(UPDATED_NCONTABIL)
            .descricao(UPDATED_DESCRICAO)
            .tipoconta(UPDATED_TIPOCONTA)
            .tipolancamento(UPDATED_TIPOLANCAMENTO);
        PlanocontasDTO planocontasDTO = planocontasMapper.toDto(updatedPlanocontas);

        restPlanocontasMockMvc.perform(put("/api/planocontas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planocontasDTO)))
            .andExpect(status().isOk());

        // Validate the Planocontas in the database
        List<Planocontas> planocontasList = planocontasRepository.findAll();
        assertThat(planocontasList).hasSize(databaseSizeBeforeUpdate);
        Planocontas testPlanocontas = planocontasList.get(planocontasList.size() - 1);
        assertThat(testPlanocontas.getIdplanocontas()).isEqualTo(UPDATED_IDPLANOCONTAS);
        assertThat(testPlanocontas.getNcontabil()).isEqualTo(UPDATED_NCONTABIL);
        assertThat(testPlanocontas.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testPlanocontas.getTipoconta()).isEqualTo(UPDATED_TIPOCONTA);
        assertThat(testPlanocontas.getTipolancamento()).isEqualTo(UPDATED_TIPOLANCAMENTO);
    }

    @Test
    @Transactional
    public void updateNonExistingPlanocontas() throws Exception {
        int databaseSizeBeforeUpdate = planocontasRepository.findAll().size();

        // Create the Planocontas
        PlanocontasDTO planocontasDTO = planocontasMapper.toDto(planocontas);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPlanocontasMockMvc.perform(put("/api/planocontas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planocontasDTO)))
            .andExpect(status().isCreated());

        // Validate the Planocontas in the database
        List<Planocontas> planocontasList = planocontasRepository.findAll();
        assertThat(planocontasList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePlanocontas() throws Exception {
        // Initialize the database
        planocontasRepository.saveAndFlush(planocontas);
        int databaseSizeBeforeDelete = planocontasRepository.findAll().size();

        // Get the planocontas
        restPlanocontasMockMvc.perform(delete("/api/planocontas/{id}", planocontas.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Planocontas> planocontasList = planocontasRepository.findAll();
        assertThat(planocontasList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Planocontas.class);
        Planocontas planocontas1 = new Planocontas();
        planocontas1.setId(1L);
        Planocontas planocontas2 = new Planocontas();
        planocontas2.setId(planocontas1.getId());
        assertThat(planocontas1).isEqualTo(planocontas2);
        planocontas2.setId(2L);
        assertThat(planocontas1).isNotEqualTo(planocontas2);
        planocontas1.setId(null);
        assertThat(planocontas1).isNotEqualTo(planocontas2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlanocontasDTO.class);
        PlanocontasDTO planocontasDTO1 = new PlanocontasDTO();
        planocontasDTO1.setId(1L);
        PlanocontasDTO planocontasDTO2 = new PlanocontasDTO();
        assertThat(planocontasDTO1).isNotEqualTo(planocontasDTO2);
        planocontasDTO2.setId(planocontasDTO1.getId());
        assertThat(planocontasDTO1).isEqualTo(planocontasDTO2);
        planocontasDTO2.setId(2L);
        assertThat(planocontasDTO1).isNotEqualTo(planocontasDTO2);
        planocontasDTO1.setId(null);
        assertThat(planocontasDTO1).isNotEqualTo(planocontasDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(planocontasMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(planocontasMapper.fromId(null)).isNull();
    }
}
