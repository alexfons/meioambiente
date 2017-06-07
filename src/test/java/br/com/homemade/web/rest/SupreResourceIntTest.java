package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Supre;
import br.com.homemade.repository.SupreRepository;
import br.com.homemade.service.dto.SupreDTO;
import br.com.homemade.service.mapper.SupreMapper;
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
 * Test class for the SupreResource REST controller.
 *
 * @see SupreResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class SupreResourceIntTest {

    private static final Integer DEFAULT_CDORGAOSET = 1;
    private static final Integer UPDATED_CDORGAOSET = 2;

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    @Autowired
    private SupreRepository supreRepository;

    @Autowired
    private SupreMapper supreMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSupreMockMvc;

    private Supre supre;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        SupreResource supreResource = new SupreResource(supreRepository, supreMapper);
        this.restSupreMockMvc = MockMvcBuilders.standaloneSetup(supreResource)
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
    public static Supre createEntity(EntityManager em) {
        Supre supre = new Supre()
            .cdorgaoset(DEFAULT_CDORGAOSET)
            .descricao(DEFAULT_DESCRICAO)
            .nome(DEFAULT_NOME);
        return supre;
    }

    @Before
    public void initTest() {
        supre = createEntity(em);
    }

    @Test
    @Transactional
    public void createSupre() throws Exception {
        int databaseSizeBeforeCreate = supreRepository.findAll().size();

        // Create the Supre
        SupreDTO supreDTO = supreMapper.toDto(supre);
        restSupreMockMvc.perform(post("/api/supres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(supreDTO)))
            .andExpect(status().isCreated());

        // Validate the Supre in the database
        List<Supre> supreList = supreRepository.findAll();
        assertThat(supreList).hasSize(databaseSizeBeforeCreate + 1);
        Supre testSupre = supreList.get(supreList.size() - 1);
        assertThat(testSupre.getCdorgaoset()).isEqualTo(DEFAULT_CDORGAOSET);
        assertThat(testSupre.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testSupre.getNome()).isEqualTo(DEFAULT_NOME);
    }

    @Test
    @Transactional
    public void createSupreWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = supreRepository.findAll().size();

        // Create the Supre with an existing ID
        supre.setId(1L);
        SupreDTO supreDTO = supreMapper.toDto(supre);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSupreMockMvc.perform(post("/api/supres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(supreDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Supre> supreList = supreRepository.findAll();
        assertThat(supreList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSupres() throws Exception {
        // Initialize the database
        supreRepository.saveAndFlush(supre);

        // Get all the supreList
        restSupreMockMvc.perform(get("/api/supres?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(supre.getId().intValue())))
            .andExpect(jsonPath("$.[*].cdorgaoset").value(hasItem(DEFAULT_CDORGAOSET)))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())));
    }

    @Test
    @Transactional
    public void getSupre() throws Exception {
        // Initialize the database
        supreRepository.saveAndFlush(supre);

        // Get the supre
        restSupreMockMvc.perform(get("/api/supres/{id}", supre.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(supre.getId().intValue()))
            .andExpect(jsonPath("$.cdorgaoset").value(DEFAULT_CDORGAOSET))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSupre() throws Exception {
        // Get the supre
        restSupreMockMvc.perform(get("/api/supres/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSupre() throws Exception {
        // Initialize the database
        supreRepository.saveAndFlush(supre);
        int databaseSizeBeforeUpdate = supreRepository.findAll().size();

        // Update the supre
        Supre updatedSupre = supreRepository.findOne(supre.getId());
        updatedSupre
            .cdorgaoset(UPDATED_CDORGAOSET)
            .descricao(UPDATED_DESCRICAO)
            .nome(UPDATED_NOME);
        SupreDTO supreDTO = supreMapper.toDto(updatedSupre);

        restSupreMockMvc.perform(put("/api/supres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(supreDTO)))
            .andExpect(status().isOk());

        // Validate the Supre in the database
        List<Supre> supreList = supreRepository.findAll();
        assertThat(supreList).hasSize(databaseSizeBeforeUpdate);
        Supre testSupre = supreList.get(supreList.size() - 1);
        assertThat(testSupre.getCdorgaoset()).isEqualTo(UPDATED_CDORGAOSET);
        assertThat(testSupre.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testSupre.getNome()).isEqualTo(UPDATED_NOME);
    }

    @Test
    @Transactional
    public void updateNonExistingSupre() throws Exception {
        int databaseSizeBeforeUpdate = supreRepository.findAll().size();

        // Create the Supre
        SupreDTO supreDTO = supreMapper.toDto(supre);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSupreMockMvc.perform(put("/api/supres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(supreDTO)))
            .andExpect(status().isCreated());

        // Validate the Supre in the database
        List<Supre> supreList = supreRepository.findAll();
        assertThat(supreList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSupre() throws Exception {
        // Initialize the database
        supreRepository.saveAndFlush(supre);
        int databaseSizeBeforeDelete = supreRepository.findAll().size();

        // Get the supre
        restSupreMockMvc.perform(delete("/api/supres/{id}", supre.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Supre> supreList = supreRepository.findAll();
        assertThat(supreList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Supre.class);
        Supre supre1 = new Supre();
        supre1.setId(1L);
        Supre supre2 = new Supre();
        supre2.setId(supre1.getId());
        assertThat(supre1).isEqualTo(supre2);
        supre2.setId(2L);
        assertThat(supre1).isNotEqualTo(supre2);
        supre1.setId(null);
        assertThat(supre1).isNotEqualTo(supre2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SupreDTO.class);
        SupreDTO supreDTO1 = new SupreDTO();
        supreDTO1.setId(1L);
        SupreDTO supreDTO2 = new SupreDTO();
        assertThat(supreDTO1).isNotEqualTo(supreDTO2);
        supreDTO2.setId(supreDTO1.getId());
        assertThat(supreDTO1).isEqualTo(supreDTO2);
        supreDTO2.setId(2L);
        assertThat(supreDTO1).isNotEqualTo(supreDTO2);
        supreDTO1.setId(null);
        assertThat(supreDTO1).isNotEqualTo(supreDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(supreMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(supreMapper.fromId(null)).isNull();
    }
}
