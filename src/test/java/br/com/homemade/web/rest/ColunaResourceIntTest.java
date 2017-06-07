package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Coluna;
import br.com.homemade.repository.ColunaRepository;
import br.com.homemade.service.dto.ColunaDTO;
import br.com.homemade.service.mapper.ColunaMapper;
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
 * Test class for the ColunaResource REST controller.
 *
 * @see ColunaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class ColunaResourceIntTest {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_LISTA = false;
    private static final Boolean UPDATED_LISTA = true;

    private static final Integer DEFAULT_SEQUENCIA = 1;
    private static final Integer UPDATED_SEQUENCIA = 2;

    @Autowired
    private ColunaRepository colunaRepository;

    @Autowired
    private ColunaMapper colunaMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restColunaMockMvc;

    private Coluna coluna;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ColunaResource colunaResource = new ColunaResource(colunaRepository, colunaMapper);
        this.restColunaMockMvc = MockMvcBuilders.standaloneSetup(colunaResource)
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
    public static Coluna createEntity(EntityManager em) {
        Coluna coluna = new Coluna()
            .descricao(DEFAULT_DESCRICAO)
            .lista(DEFAULT_LISTA)
            .sequencia(DEFAULT_SEQUENCIA);
        return coluna;
    }

    @Before
    public void initTest() {
        coluna = createEntity(em);
    }

    @Test
    @Transactional
    public void createColuna() throws Exception {
        int databaseSizeBeforeCreate = colunaRepository.findAll().size();

        // Create the Coluna
        ColunaDTO colunaDTO = colunaMapper.toDto(coluna);
        restColunaMockMvc.perform(post("/api/colunas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(colunaDTO)))
            .andExpect(status().isCreated());

        // Validate the Coluna in the database
        List<Coluna> colunaList = colunaRepository.findAll();
        assertThat(colunaList).hasSize(databaseSizeBeforeCreate + 1);
        Coluna testColuna = colunaList.get(colunaList.size() - 1);
        assertThat(testColuna.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testColuna.isLista()).isEqualTo(DEFAULT_LISTA);
        assertThat(testColuna.getSequencia()).isEqualTo(DEFAULT_SEQUENCIA);
    }

    @Test
    @Transactional
    public void createColunaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = colunaRepository.findAll().size();

        // Create the Coluna with an existing ID
        coluna.setId(1L);
        ColunaDTO colunaDTO = colunaMapper.toDto(coluna);

        // An entity with an existing ID cannot be created, so this API call must fail
        restColunaMockMvc.perform(post("/api/colunas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(colunaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Coluna> colunaList = colunaRepository.findAll();
        assertThat(colunaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllColunas() throws Exception {
        // Initialize the database
        colunaRepository.saveAndFlush(coluna);

        // Get all the colunaList
        restColunaMockMvc.perform(get("/api/colunas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(coluna.getId().intValue())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].lista").value(hasItem(DEFAULT_LISTA.booleanValue())))
            .andExpect(jsonPath("$.[*].sequencia").value(hasItem(DEFAULT_SEQUENCIA)));
    }

    @Test
    @Transactional
    public void getColuna() throws Exception {
        // Initialize the database
        colunaRepository.saveAndFlush(coluna);

        // Get the coluna
        restColunaMockMvc.perform(get("/api/colunas/{id}", coluna.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(coluna.getId().intValue()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.lista").value(DEFAULT_LISTA.booleanValue()))
            .andExpect(jsonPath("$.sequencia").value(DEFAULT_SEQUENCIA));
    }

    @Test
    @Transactional
    public void getNonExistingColuna() throws Exception {
        // Get the coluna
        restColunaMockMvc.perform(get("/api/colunas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateColuna() throws Exception {
        // Initialize the database
        colunaRepository.saveAndFlush(coluna);
        int databaseSizeBeforeUpdate = colunaRepository.findAll().size();

        // Update the coluna
        Coluna updatedColuna = colunaRepository.findOne(coluna.getId());
        updatedColuna
            .descricao(UPDATED_DESCRICAO)
            .lista(UPDATED_LISTA)
            .sequencia(UPDATED_SEQUENCIA);
        ColunaDTO colunaDTO = colunaMapper.toDto(updatedColuna);

        restColunaMockMvc.perform(put("/api/colunas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(colunaDTO)))
            .andExpect(status().isOk());

        // Validate the Coluna in the database
        List<Coluna> colunaList = colunaRepository.findAll();
        assertThat(colunaList).hasSize(databaseSizeBeforeUpdate);
        Coluna testColuna = colunaList.get(colunaList.size() - 1);
        assertThat(testColuna.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testColuna.isLista()).isEqualTo(UPDATED_LISTA);
        assertThat(testColuna.getSequencia()).isEqualTo(UPDATED_SEQUENCIA);
    }

    @Test
    @Transactional
    public void updateNonExistingColuna() throws Exception {
        int databaseSizeBeforeUpdate = colunaRepository.findAll().size();

        // Create the Coluna
        ColunaDTO colunaDTO = colunaMapper.toDto(coluna);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restColunaMockMvc.perform(put("/api/colunas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(colunaDTO)))
            .andExpect(status().isCreated());

        // Validate the Coluna in the database
        List<Coluna> colunaList = colunaRepository.findAll();
        assertThat(colunaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteColuna() throws Exception {
        // Initialize the database
        colunaRepository.saveAndFlush(coluna);
        int databaseSizeBeforeDelete = colunaRepository.findAll().size();

        // Get the coluna
        restColunaMockMvc.perform(delete("/api/colunas/{id}", coluna.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Coluna> colunaList = colunaRepository.findAll();
        assertThat(colunaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Coluna.class);
        Coluna coluna1 = new Coluna();
        coluna1.setId(1L);
        Coluna coluna2 = new Coluna();
        coluna2.setId(coluna1.getId());
        assertThat(coluna1).isEqualTo(coluna2);
        coluna2.setId(2L);
        assertThat(coluna1).isNotEqualTo(coluna2);
        coluna1.setId(null);
        assertThat(coluna1).isNotEqualTo(coluna2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ColunaDTO.class);
        ColunaDTO colunaDTO1 = new ColunaDTO();
        colunaDTO1.setId(1L);
        ColunaDTO colunaDTO2 = new ColunaDTO();
        assertThat(colunaDTO1).isNotEqualTo(colunaDTO2);
        colunaDTO2.setId(colunaDTO1.getId());
        assertThat(colunaDTO1).isEqualTo(colunaDTO2);
        colunaDTO2.setId(2L);
        assertThat(colunaDTO1).isNotEqualTo(colunaDTO2);
        colunaDTO1.setId(null);
        assertThat(colunaDTO1).isNotEqualTo(colunaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(colunaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(colunaMapper.fromId(null)).isNull();
    }
}
