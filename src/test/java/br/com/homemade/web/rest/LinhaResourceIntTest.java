package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Linha;
import br.com.homemade.repository.LinhaRepository;
import br.com.homemade.service.dto.LinhaDTO;
import br.com.homemade.service.mapper.LinhaMapper;
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
 * Test class for the LinhaResource REST controller.
 *
 * @see LinhaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class LinhaResourceIntTest {

    private static final Integer DEFAULT_SEQUENCIA = 1;
    private static final Integer UPDATED_SEQUENCIA = 2;

    private static final String DEFAULT_VALOR = "AAAAAAAAAA";
    private static final String UPDATED_VALOR = "BBBBBBBBBB";

    @Autowired
    private LinhaRepository linhaRepository;

    @Autowired
    private LinhaMapper linhaMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLinhaMockMvc;

    private Linha linha;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        LinhaResource linhaResource = new LinhaResource(linhaRepository, linhaMapper);
        this.restLinhaMockMvc = MockMvcBuilders.standaloneSetup(linhaResource)
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
    public static Linha createEntity(EntityManager em) {
        Linha linha = new Linha()
            .sequencia(DEFAULT_SEQUENCIA)
            .valor(DEFAULT_VALOR);
        return linha;
    }

    @Before
    public void initTest() {
        linha = createEntity(em);
    }

    @Test
    @Transactional
    public void createLinha() throws Exception {
        int databaseSizeBeforeCreate = linhaRepository.findAll().size();

        // Create the Linha
        LinhaDTO linhaDTO = linhaMapper.toDto(linha);
        restLinhaMockMvc.perform(post("/api/linhas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(linhaDTO)))
            .andExpect(status().isCreated());

        // Validate the Linha in the database
        List<Linha> linhaList = linhaRepository.findAll();
        assertThat(linhaList).hasSize(databaseSizeBeforeCreate + 1);
        Linha testLinha = linhaList.get(linhaList.size() - 1);
        assertThat(testLinha.getSequencia()).isEqualTo(DEFAULT_SEQUENCIA);
        assertThat(testLinha.getValor()).isEqualTo(DEFAULT_VALOR);
    }

    @Test
    @Transactional
    public void createLinhaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = linhaRepository.findAll().size();

        // Create the Linha with an existing ID
        linha.setId(1L);
        LinhaDTO linhaDTO = linhaMapper.toDto(linha);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLinhaMockMvc.perform(post("/api/linhas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(linhaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Linha> linhaList = linhaRepository.findAll();
        assertThat(linhaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllLinhas() throws Exception {
        // Initialize the database
        linhaRepository.saveAndFlush(linha);

        // Get all the linhaList
        restLinhaMockMvc.perform(get("/api/linhas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(linha.getId().intValue())))
            .andExpect(jsonPath("$.[*].sequencia").value(hasItem(DEFAULT_SEQUENCIA)))
            .andExpect(jsonPath("$.[*].valor").value(hasItem(DEFAULT_VALOR.toString())));
    }

    @Test
    @Transactional
    public void getLinha() throws Exception {
        // Initialize the database
        linhaRepository.saveAndFlush(linha);

        // Get the linha
        restLinhaMockMvc.perform(get("/api/linhas/{id}", linha.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(linha.getId().intValue()))
            .andExpect(jsonPath("$.sequencia").value(DEFAULT_SEQUENCIA))
            .andExpect(jsonPath("$.valor").value(DEFAULT_VALOR.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLinha() throws Exception {
        // Get the linha
        restLinhaMockMvc.perform(get("/api/linhas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLinha() throws Exception {
        // Initialize the database
        linhaRepository.saveAndFlush(linha);
        int databaseSizeBeforeUpdate = linhaRepository.findAll().size();

        // Update the linha
        Linha updatedLinha = linhaRepository.findOne(linha.getId());
        updatedLinha
            .sequencia(UPDATED_SEQUENCIA)
            .valor(UPDATED_VALOR);
        LinhaDTO linhaDTO = linhaMapper.toDto(updatedLinha);

        restLinhaMockMvc.perform(put("/api/linhas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(linhaDTO)))
            .andExpect(status().isOk());

        // Validate the Linha in the database
        List<Linha> linhaList = linhaRepository.findAll();
        assertThat(linhaList).hasSize(databaseSizeBeforeUpdate);
        Linha testLinha = linhaList.get(linhaList.size() - 1);
        assertThat(testLinha.getSequencia()).isEqualTo(UPDATED_SEQUENCIA);
        assertThat(testLinha.getValor()).isEqualTo(UPDATED_VALOR);
    }

    @Test
    @Transactional
    public void updateNonExistingLinha() throws Exception {
        int databaseSizeBeforeUpdate = linhaRepository.findAll().size();

        // Create the Linha
        LinhaDTO linhaDTO = linhaMapper.toDto(linha);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restLinhaMockMvc.perform(put("/api/linhas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(linhaDTO)))
            .andExpect(status().isCreated());

        // Validate the Linha in the database
        List<Linha> linhaList = linhaRepository.findAll();
        assertThat(linhaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteLinha() throws Exception {
        // Initialize the database
        linhaRepository.saveAndFlush(linha);
        int databaseSizeBeforeDelete = linhaRepository.findAll().size();

        // Get the linha
        restLinhaMockMvc.perform(delete("/api/linhas/{id}", linha.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Linha> linhaList = linhaRepository.findAll();
        assertThat(linhaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Linha.class);
        Linha linha1 = new Linha();
        linha1.setId(1L);
        Linha linha2 = new Linha();
        linha2.setId(linha1.getId());
        assertThat(linha1).isEqualTo(linha2);
        linha2.setId(2L);
        assertThat(linha1).isNotEqualTo(linha2);
        linha1.setId(null);
        assertThat(linha1).isNotEqualTo(linha2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LinhaDTO.class);
        LinhaDTO linhaDTO1 = new LinhaDTO();
        linhaDTO1.setId(1L);
        LinhaDTO linhaDTO2 = new LinhaDTO();
        assertThat(linhaDTO1).isNotEqualTo(linhaDTO2);
        linhaDTO2.setId(linhaDTO1.getId());
        assertThat(linhaDTO1).isEqualTo(linhaDTO2);
        linhaDTO2.setId(2L);
        assertThat(linhaDTO1).isNotEqualTo(linhaDTO2);
        linhaDTO1.setId(null);
        assertThat(linhaDTO1).isNotEqualTo(linhaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(linhaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(linhaMapper.fromId(null)).isNull();
    }
}
