package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Tabela;
import br.com.homemade.repository.TabelaRepository;
import br.com.homemade.service.dto.TabelaDTO;
import br.com.homemade.service.mapper.TabelaMapper;
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
 * Test class for the TabelaResource REST controller.
 *
 * @see TabelaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class TabelaResourceIntTest {

    @Autowired
    private TabelaRepository tabelaRepository;

    @Autowired
    private TabelaMapper tabelaMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTabelaMockMvc;

    private Tabela tabela;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TabelaResource tabelaResource = new TabelaResource(tabelaRepository, tabelaMapper);
        this.restTabelaMockMvc = MockMvcBuilders.standaloneSetup(tabelaResource)
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
    public static Tabela createEntity(EntityManager em) {
        Tabela tabela = new Tabela();
        return tabela;
    }

    @Before
    public void initTest() {
        tabela = createEntity(em);
    }

    @Test
    @Transactional
    public void createTabela() throws Exception {
        int databaseSizeBeforeCreate = tabelaRepository.findAll().size();

        // Create the Tabela
        TabelaDTO tabelaDTO = tabelaMapper.toDto(tabela);
        restTabelaMockMvc.perform(post("/api/tabelas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tabelaDTO)))
            .andExpect(status().isCreated());

        // Validate the Tabela in the database
        List<Tabela> tabelaList = tabelaRepository.findAll();
        assertThat(tabelaList).hasSize(databaseSizeBeforeCreate + 1);
        Tabela testTabela = tabelaList.get(tabelaList.size() - 1);
    }

    @Test
    @Transactional
    public void createTabelaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tabelaRepository.findAll().size();

        // Create the Tabela with an existing ID
        tabela.setId(1L);
        TabelaDTO tabelaDTO = tabelaMapper.toDto(tabela);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTabelaMockMvc.perform(post("/api/tabelas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tabelaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Tabela> tabelaList = tabelaRepository.findAll();
        assertThat(tabelaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTabelas() throws Exception {
        // Initialize the database
        tabelaRepository.saveAndFlush(tabela);

        // Get all the tabelaList
        restTabelaMockMvc.perform(get("/api/tabelas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tabela.getId().intValue())));
    }

    @Test
    @Transactional
    public void getTabela() throws Exception {
        // Initialize the database
        tabelaRepository.saveAndFlush(tabela);

        // Get the tabela
        restTabelaMockMvc.perform(get("/api/tabelas/{id}", tabela.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tabela.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTabela() throws Exception {
        // Get the tabela
        restTabelaMockMvc.perform(get("/api/tabelas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTabela() throws Exception {
        // Initialize the database
        tabelaRepository.saveAndFlush(tabela);
        int databaseSizeBeforeUpdate = tabelaRepository.findAll().size();

        // Update the tabela
        Tabela updatedTabela = tabelaRepository.findOne(tabela.getId());
        TabelaDTO tabelaDTO = tabelaMapper.toDto(updatedTabela);

        restTabelaMockMvc.perform(put("/api/tabelas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tabelaDTO)))
            .andExpect(status().isOk());

        // Validate the Tabela in the database
        List<Tabela> tabelaList = tabelaRepository.findAll();
        assertThat(tabelaList).hasSize(databaseSizeBeforeUpdate);
        Tabela testTabela = tabelaList.get(tabelaList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingTabela() throws Exception {
        int databaseSizeBeforeUpdate = tabelaRepository.findAll().size();

        // Create the Tabela
        TabelaDTO tabelaDTO = tabelaMapper.toDto(tabela);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTabelaMockMvc.perform(put("/api/tabelas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tabelaDTO)))
            .andExpect(status().isCreated());

        // Validate the Tabela in the database
        List<Tabela> tabelaList = tabelaRepository.findAll();
        assertThat(tabelaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTabela() throws Exception {
        // Initialize the database
        tabelaRepository.saveAndFlush(tabela);
        int databaseSizeBeforeDelete = tabelaRepository.findAll().size();

        // Get the tabela
        restTabelaMockMvc.perform(delete("/api/tabelas/{id}", tabela.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Tabela> tabelaList = tabelaRepository.findAll();
        assertThat(tabelaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tabela.class);
        Tabela tabela1 = new Tabela();
        tabela1.setId(1L);
        Tabela tabela2 = new Tabela();
        tabela2.setId(tabela1.getId());
        assertThat(tabela1).isEqualTo(tabela2);
        tabela2.setId(2L);
        assertThat(tabela1).isNotEqualTo(tabela2);
        tabela1.setId(null);
        assertThat(tabela1).isNotEqualTo(tabela2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TabelaDTO.class);
        TabelaDTO tabelaDTO1 = new TabelaDTO();
        tabelaDTO1.setId(1L);
        TabelaDTO tabelaDTO2 = new TabelaDTO();
        assertThat(tabelaDTO1).isNotEqualTo(tabelaDTO2);
        tabelaDTO2.setId(tabelaDTO1.getId());
        assertThat(tabelaDTO1).isEqualTo(tabelaDTO2);
        tabelaDTO2.setId(2L);
        assertThat(tabelaDTO1).isNotEqualTo(tabelaDTO2);
        tabelaDTO1.setId(null);
        assertThat(tabelaDTO1).isNotEqualTo(tabelaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(tabelaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(tabelaMapper.fromId(null)).isNull();
    }
}
