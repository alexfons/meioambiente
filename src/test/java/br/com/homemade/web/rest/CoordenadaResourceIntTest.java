package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Coordenada;
import br.com.homemade.repository.CoordenadaRepository;
import br.com.homemade.service.dto.CoordenadaDTO;
import br.com.homemade.service.mapper.CoordenadaMapper;
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
 * Test class for the CoordenadaResource REST controller.
 *
 * @see CoordenadaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class CoordenadaResourceIntTest {

    private static final Float DEFAULT_A = 1F;
    private static final Float UPDATED_A = 2F;

    private static final Float DEFAULT_KM = 1F;
    private static final Float UPDATED_KM = 2F;

    private static final Float DEFAULT_N = 1F;
    private static final Float UPDATED_N = 2F;

    private static final Float DEFAULT_S = 1F;
    private static final Float UPDATED_S = 2F;

    @Autowired
    private CoordenadaRepository coordenadaRepository;

    @Autowired
    private CoordenadaMapper coordenadaMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCoordenadaMockMvc;

    private Coordenada coordenada;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CoordenadaResource coordenadaResource = new CoordenadaResource(coordenadaRepository, coordenadaMapper);
        this.restCoordenadaMockMvc = MockMvcBuilders.standaloneSetup(coordenadaResource)
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
    public static Coordenada createEntity(EntityManager em) {
        Coordenada coordenada = new Coordenada()
            .a(DEFAULT_A)
            .km(DEFAULT_KM)
            .n(DEFAULT_N)
            .s(DEFAULT_S);
        return coordenada;
    }

    @Before
    public void initTest() {
        coordenada = createEntity(em);
    }

    @Test
    @Transactional
    public void createCoordenada() throws Exception {
        int databaseSizeBeforeCreate = coordenadaRepository.findAll().size();

        // Create the Coordenada
        CoordenadaDTO coordenadaDTO = coordenadaMapper.toDto(coordenada);
        restCoordenadaMockMvc.perform(post("/api/coordenadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coordenadaDTO)))
            .andExpect(status().isCreated());

        // Validate the Coordenada in the database
        List<Coordenada> coordenadaList = coordenadaRepository.findAll();
        assertThat(coordenadaList).hasSize(databaseSizeBeforeCreate + 1);
        Coordenada testCoordenada = coordenadaList.get(coordenadaList.size() - 1);
        assertThat(testCoordenada.getA()).isEqualTo(DEFAULT_A);
        assertThat(testCoordenada.getKm()).isEqualTo(DEFAULT_KM);
        assertThat(testCoordenada.getN()).isEqualTo(DEFAULT_N);
        assertThat(testCoordenada.getS()).isEqualTo(DEFAULT_S);
    }

    @Test
    @Transactional
    public void createCoordenadaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = coordenadaRepository.findAll().size();

        // Create the Coordenada with an existing ID
        coordenada.setId(1L);
        CoordenadaDTO coordenadaDTO = coordenadaMapper.toDto(coordenada);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCoordenadaMockMvc.perform(post("/api/coordenadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coordenadaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Coordenada> coordenadaList = coordenadaRepository.findAll();
        assertThat(coordenadaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCoordenadas() throws Exception {
        // Initialize the database
        coordenadaRepository.saveAndFlush(coordenada);

        // Get all the coordenadaList
        restCoordenadaMockMvc.perform(get("/api/coordenadas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(coordenada.getId().intValue())))
            .andExpect(jsonPath("$.[*].a").value(hasItem(DEFAULT_A.doubleValue())))
            .andExpect(jsonPath("$.[*].km").value(hasItem(DEFAULT_KM.doubleValue())))
            .andExpect(jsonPath("$.[*].n").value(hasItem(DEFAULT_N.doubleValue())))
            .andExpect(jsonPath("$.[*].s").value(hasItem(DEFAULT_S.doubleValue())));
    }

    @Test
    @Transactional
    public void getCoordenada() throws Exception {
        // Initialize the database
        coordenadaRepository.saveAndFlush(coordenada);

        // Get the coordenada
        restCoordenadaMockMvc.perform(get("/api/coordenadas/{id}", coordenada.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(coordenada.getId().intValue()))
            .andExpect(jsonPath("$.a").value(DEFAULT_A.doubleValue()))
            .andExpect(jsonPath("$.km").value(DEFAULT_KM.doubleValue()))
            .andExpect(jsonPath("$.n").value(DEFAULT_N.doubleValue()))
            .andExpect(jsonPath("$.s").value(DEFAULT_S.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCoordenada() throws Exception {
        // Get the coordenada
        restCoordenadaMockMvc.perform(get("/api/coordenadas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCoordenada() throws Exception {
        // Initialize the database
        coordenadaRepository.saveAndFlush(coordenada);
        int databaseSizeBeforeUpdate = coordenadaRepository.findAll().size();

        // Update the coordenada
        Coordenada updatedCoordenada = coordenadaRepository.findOne(coordenada.getId());
        updatedCoordenada
            .a(UPDATED_A)
            .km(UPDATED_KM)
            .n(UPDATED_N)
            .s(UPDATED_S);
        CoordenadaDTO coordenadaDTO = coordenadaMapper.toDto(updatedCoordenada);

        restCoordenadaMockMvc.perform(put("/api/coordenadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coordenadaDTO)))
            .andExpect(status().isOk());

        // Validate the Coordenada in the database
        List<Coordenada> coordenadaList = coordenadaRepository.findAll();
        assertThat(coordenadaList).hasSize(databaseSizeBeforeUpdate);
        Coordenada testCoordenada = coordenadaList.get(coordenadaList.size() - 1);
        assertThat(testCoordenada.getA()).isEqualTo(UPDATED_A);
        assertThat(testCoordenada.getKm()).isEqualTo(UPDATED_KM);
        assertThat(testCoordenada.getN()).isEqualTo(UPDATED_N);
        assertThat(testCoordenada.getS()).isEqualTo(UPDATED_S);
    }

    @Test
    @Transactional
    public void updateNonExistingCoordenada() throws Exception {
        int databaseSizeBeforeUpdate = coordenadaRepository.findAll().size();

        // Create the Coordenada
        CoordenadaDTO coordenadaDTO = coordenadaMapper.toDto(coordenada);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCoordenadaMockMvc.perform(put("/api/coordenadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coordenadaDTO)))
            .andExpect(status().isCreated());

        // Validate the Coordenada in the database
        List<Coordenada> coordenadaList = coordenadaRepository.findAll();
        assertThat(coordenadaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCoordenada() throws Exception {
        // Initialize the database
        coordenadaRepository.saveAndFlush(coordenada);
        int databaseSizeBeforeDelete = coordenadaRepository.findAll().size();

        // Get the coordenada
        restCoordenadaMockMvc.perform(delete("/api/coordenadas/{id}", coordenada.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Coordenada> coordenadaList = coordenadaRepository.findAll();
        assertThat(coordenadaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Coordenada.class);
        Coordenada coordenada1 = new Coordenada();
        coordenada1.setId(1L);
        Coordenada coordenada2 = new Coordenada();
        coordenada2.setId(coordenada1.getId());
        assertThat(coordenada1).isEqualTo(coordenada2);
        coordenada2.setId(2L);
        assertThat(coordenada1).isNotEqualTo(coordenada2);
        coordenada1.setId(null);
        assertThat(coordenada1).isNotEqualTo(coordenada2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CoordenadaDTO.class);
        CoordenadaDTO coordenadaDTO1 = new CoordenadaDTO();
        coordenadaDTO1.setId(1L);
        CoordenadaDTO coordenadaDTO2 = new CoordenadaDTO();
        assertThat(coordenadaDTO1).isNotEqualTo(coordenadaDTO2);
        coordenadaDTO2.setId(coordenadaDTO1.getId());
        assertThat(coordenadaDTO1).isEqualTo(coordenadaDTO2);
        coordenadaDTO2.setId(2L);
        assertThat(coordenadaDTO1).isNotEqualTo(coordenadaDTO2);
        coordenadaDTO1.setId(null);
        assertThat(coordenadaDTO1).isNotEqualTo(coordenadaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(coordenadaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(coordenadaMapper.fromId(null)).isNull();
    }
}
