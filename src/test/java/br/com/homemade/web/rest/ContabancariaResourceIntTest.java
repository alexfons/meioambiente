package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Contabancaria;
import br.com.homemade.repository.ContabancariaRepository;
import br.com.homemade.service.dto.ContabancariaDTO;
import br.com.homemade.service.mapper.ContabancariaMapper;
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
 * Test class for the ContabancariaResource REST controller.
 *
 * @see ContabancariaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class ContabancariaResourceIntTest {

    private static final Integer DEFAULT_IDCONTABANCARIA = 1;
    private static final Integer UPDATED_IDCONTABANCARIA = 2;

    private static final String DEFAULT_NCONTA = "AAAAAAAAAA";
    private static final String UPDATED_NCONTA = "BBBBBBBBBB";

    private static final String DEFAULT_NBANCO = "AAAAAAAAAA";
    private static final String UPDATED_NBANCO = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    @Autowired
    private ContabancariaRepository contabancariaRepository;

    @Autowired
    private ContabancariaMapper contabancariaMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restContabancariaMockMvc;

    private Contabancaria contabancaria;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ContabancariaResource contabancariaResource = new ContabancariaResource(contabancariaRepository, contabancariaMapper);
        this.restContabancariaMockMvc = MockMvcBuilders.standaloneSetup(contabancariaResource)
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
    public static Contabancaria createEntity(EntityManager em) {
        Contabancaria contabancaria = new Contabancaria()
            .idcontabancaria(DEFAULT_IDCONTABANCARIA)
            .nconta(DEFAULT_NCONTA)
            .nbanco(DEFAULT_NBANCO)
            .descricao(DEFAULT_DESCRICAO);
        return contabancaria;
    }

    @Before
    public void initTest() {
        contabancaria = createEntity(em);
    }

    @Test
    @Transactional
    public void createContabancaria() throws Exception {
        int databaseSizeBeforeCreate = contabancariaRepository.findAll().size();

        // Create the Contabancaria
        ContabancariaDTO contabancariaDTO = contabancariaMapper.toDto(contabancaria);
        restContabancariaMockMvc.perform(post("/api/contabancarias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contabancariaDTO)))
            .andExpect(status().isCreated());

        // Validate the Contabancaria in the database
        List<Contabancaria> contabancariaList = contabancariaRepository.findAll();
        assertThat(contabancariaList).hasSize(databaseSizeBeforeCreate + 1);
        Contabancaria testContabancaria = contabancariaList.get(contabancariaList.size() - 1);
        assertThat(testContabancaria.getIdcontabancaria()).isEqualTo(DEFAULT_IDCONTABANCARIA);
        assertThat(testContabancaria.getNconta()).isEqualTo(DEFAULT_NCONTA);
        assertThat(testContabancaria.getNbanco()).isEqualTo(DEFAULT_NBANCO);
        assertThat(testContabancaria.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
    }

    @Test
    @Transactional
    public void createContabancariaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = contabancariaRepository.findAll().size();

        // Create the Contabancaria with an existing ID
        contabancaria.setId(1L);
        ContabancariaDTO contabancariaDTO = contabancariaMapper.toDto(contabancaria);

        // An entity with an existing ID cannot be created, so this API call must fail
        restContabancariaMockMvc.perform(post("/api/contabancarias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contabancariaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Contabancaria> contabancariaList = contabancariaRepository.findAll();
        assertThat(contabancariaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllContabancarias() throws Exception {
        // Initialize the database
        contabancariaRepository.saveAndFlush(contabancaria);

        // Get all the contabancariaList
        restContabancariaMockMvc.perform(get("/api/contabancarias?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contabancaria.getId().intValue())))
            .andExpect(jsonPath("$.[*].idcontabancaria").value(hasItem(DEFAULT_IDCONTABANCARIA)))
            .andExpect(jsonPath("$.[*].nconta").value(hasItem(DEFAULT_NCONTA.toString())))
            .andExpect(jsonPath("$.[*].nbanco").value(hasItem(DEFAULT_NBANCO.toString())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())));
    }

    @Test
    @Transactional
    public void getContabancaria() throws Exception {
        // Initialize the database
        contabancariaRepository.saveAndFlush(contabancaria);

        // Get the contabancaria
        restContabancariaMockMvc.perform(get("/api/contabancarias/{id}", contabancaria.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(contabancaria.getId().intValue()))
            .andExpect(jsonPath("$.idcontabancaria").value(DEFAULT_IDCONTABANCARIA))
            .andExpect(jsonPath("$.nconta").value(DEFAULT_NCONTA.toString()))
            .andExpect(jsonPath("$.nbanco").value(DEFAULT_NBANCO.toString()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingContabancaria() throws Exception {
        // Get the contabancaria
        restContabancariaMockMvc.perform(get("/api/contabancarias/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateContabancaria() throws Exception {
        // Initialize the database
        contabancariaRepository.saveAndFlush(contabancaria);
        int databaseSizeBeforeUpdate = contabancariaRepository.findAll().size();

        // Update the contabancaria
        Contabancaria updatedContabancaria = contabancariaRepository.findOne(contabancaria.getId());
        updatedContabancaria
            .idcontabancaria(UPDATED_IDCONTABANCARIA)
            .nconta(UPDATED_NCONTA)
            .nbanco(UPDATED_NBANCO)
            .descricao(UPDATED_DESCRICAO);
        ContabancariaDTO contabancariaDTO = contabancariaMapper.toDto(updatedContabancaria);

        restContabancariaMockMvc.perform(put("/api/contabancarias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contabancariaDTO)))
            .andExpect(status().isOk());

        // Validate the Contabancaria in the database
        List<Contabancaria> contabancariaList = contabancariaRepository.findAll();
        assertThat(contabancariaList).hasSize(databaseSizeBeforeUpdate);
        Contabancaria testContabancaria = contabancariaList.get(contabancariaList.size() - 1);
        assertThat(testContabancaria.getIdcontabancaria()).isEqualTo(UPDATED_IDCONTABANCARIA);
        assertThat(testContabancaria.getNconta()).isEqualTo(UPDATED_NCONTA);
        assertThat(testContabancaria.getNbanco()).isEqualTo(UPDATED_NBANCO);
        assertThat(testContabancaria.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
    }

    @Test
    @Transactional
    public void updateNonExistingContabancaria() throws Exception {
        int databaseSizeBeforeUpdate = contabancariaRepository.findAll().size();

        // Create the Contabancaria
        ContabancariaDTO contabancariaDTO = contabancariaMapper.toDto(contabancaria);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restContabancariaMockMvc.perform(put("/api/contabancarias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contabancariaDTO)))
            .andExpect(status().isCreated());

        // Validate the Contabancaria in the database
        List<Contabancaria> contabancariaList = contabancariaRepository.findAll();
        assertThat(contabancariaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteContabancaria() throws Exception {
        // Initialize the database
        contabancariaRepository.saveAndFlush(contabancaria);
        int databaseSizeBeforeDelete = contabancariaRepository.findAll().size();

        // Get the contabancaria
        restContabancariaMockMvc.perform(delete("/api/contabancarias/{id}", contabancaria.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Contabancaria> contabancariaList = contabancariaRepository.findAll();
        assertThat(contabancariaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Contabancaria.class);
        Contabancaria contabancaria1 = new Contabancaria();
        contabancaria1.setId(1L);
        Contabancaria contabancaria2 = new Contabancaria();
        contabancaria2.setId(contabancaria1.getId());
        assertThat(contabancaria1).isEqualTo(contabancaria2);
        contabancaria2.setId(2L);
        assertThat(contabancaria1).isNotEqualTo(contabancaria2);
        contabancaria1.setId(null);
        assertThat(contabancaria1).isNotEqualTo(contabancaria2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContabancariaDTO.class);
        ContabancariaDTO contabancariaDTO1 = new ContabancariaDTO();
        contabancariaDTO1.setId(1L);
        ContabancariaDTO contabancariaDTO2 = new ContabancariaDTO();
        assertThat(contabancariaDTO1).isNotEqualTo(contabancariaDTO2);
        contabancariaDTO2.setId(contabancariaDTO1.getId());
        assertThat(contabancariaDTO1).isEqualTo(contabancariaDTO2);
        contabancariaDTO2.setId(2L);
        assertThat(contabancariaDTO1).isNotEqualTo(contabancariaDTO2);
        contabancariaDTO1.setId(null);
        assertThat(contabancariaDTO1).isNotEqualTo(contabancariaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(contabancariaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(contabancariaMapper.fromId(null)).isNull();
    }
}
