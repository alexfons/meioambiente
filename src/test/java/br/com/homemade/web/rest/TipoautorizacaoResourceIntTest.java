package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Tipoautorizacao;
import br.com.homemade.repository.TipoautorizacaoRepository;
import br.com.homemade.service.dto.TipoautorizacaoDTO;
import br.com.homemade.service.mapper.TipoautorizacaoMapper;
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
 * Test class for the TipoautorizacaoResource REST controller.
 *
 * @see TipoautorizacaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class TipoautorizacaoResourceIntTest {

    @Autowired
    private TipoautorizacaoRepository tipoautorizacaoRepository;

    @Autowired
    private TipoautorizacaoMapper tipoautorizacaoMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTipoautorizacaoMockMvc;

    private Tipoautorizacao tipoautorizacao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TipoautorizacaoResource tipoautorizacaoResource = new TipoautorizacaoResource(tipoautorizacaoRepository, tipoautorizacaoMapper);
        this.restTipoautorizacaoMockMvc = MockMvcBuilders.standaloneSetup(tipoautorizacaoResource)
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
    public static Tipoautorizacao createEntity(EntityManager em) {
        Tipoautorizacao tipoautorizacao = new Tipoautorizacao();
        return tipoautorizacao;
    }

    @Before
    public void initTest() {
        tipoautorizacao = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoautorizacao() throws Exception {
        int databaseSizeBeforeCreate = tipoautorizacaoRepository.findAll().size();

        // Create the Tipoautorizacao
        TipoautorizacaoDTO tipoautorizacaoDTO = tipoautorizacaoMapper.toDto(tipoautorizacao);
        restTipoautorizacaoMockMvc.perform(post("/api/tipoautorizacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoautorizacaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Tipoautorizacao in the database
        List<Tipoautorizacao> tipoautorizacaoList = tipoautorizacaoRepository.findAll();
        assertThat(tipoautorizacaoList).hasSize(databaseSizeBeforeCreate + 1);
        Tipoautorizacao testTipoautorizacao = tipoautorizacaoList.get(tipoautorizacaoList.size() - 1);
    }

    @Test
    @Transactional
    public void createTipoautorizacaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoautorizacaoRepository.findAll().size();

        // Create the Tipoautorizacao with an existing ID
        tipoautorizacao.setId(1L);
        TipoautorizacaoDTO tipoautorizacaoDTO = tipoautorizacaoMapper.toDto(tipoautorizacao);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoautorizacaoMockMvc.perform(post("/api/tipoautorizacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoautorizacaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Tipoautorizacao> tipoautorizacaoList = tipoautorizacaoRepository.findAll();
        assertThat(tipoautorizacaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTipoautorizacaos() throws Exception {
        // Initialize the database
        tipoautorizacaoRepository.saveAndFlush(tipoautorizacao);

        // Get all the tipoautorizacaoList
        restTipoautorizacaoMockMvc.perform(get("/api/tipoautorizacaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoautorizacao.getId().intValue())));
    }

    @Test
    @Transactional
    public void getTipoautorizacao() throws Exception {
        // Initialize the database
        tipoautorizacaoRepository.saveAndFlush(tipoautorizacao);

        // Get the tipoautorizacao
        restTipoautorizacaoMockMvc.perform(get("/api/tipoautorizacaos/{id}", tipoautorizacao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipoautorizacao.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTipoautorizacao() throws Exception {
        // Get the tipoautorizacao
        restTipoautorizacaoMockMvc.perform(get("/api/tipoautorizacaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoautorizacao() throws Exception {
        // Initialize the database
        tipoautorizacaoRepository.saveAndFlush(tipoautorizacao);
        int databaseSizeBeforeUpdate = tipoautorizacaoRepository.findAll().size();

        // Update the tipoautorizacao
        Tipoautorizacao updatedTipoautorizacao = tipoautorizacaoRepository.findOne(tipoautorizacao.getId());
        TipoautorizacaoDTO tipoautorizacaoDTO = tipoautorizacaoMapper.toDto(updatedTipoautorizacao);

        restTipoautorizacaoMockMvc.perform(put("/api/tipoautorizacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoautorizacaoDTO)))
            .andExpect(status().isOk());

        // Validate the Tipoautorizacao in the database
        List<Tipoautorizacao> tipoautorizacaoList = tipoautorizacaoRepository.findAll();
        assertThat(tipoautorizacaoList).hasSize(databaseSizeBeforeUpdate);
        Tipoautorizacao testTipoautorizacao = tipoautorizacaoList.get(tipoautorizacaoList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoautorizacao() throws Exception {
        int databaseSizeBeforeUpdate = tipoautorizacaoRepository.findAll().size();

        // Create the Tipoautorizacao
        TipoautorizacaoDTO tipoautorizacaoDTO = tipoautorizacaoMapper.toDto(tipoautorizacao);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTipoautorizacaoMockMvc.perform(put("/api/tipoautorizacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoautorizacaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Tipoautorizacao in the database
        List<Tipoautorizacao> tipoautorizacaoList = tipoautorizacaoRepository.findAll();
        assertThat(tipoautorizacaoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTipoautorizacao() throws Exception {
        // Initialize the database
        tipoautorizacaoRepository.saveAndFlush(tipoautorizacao);
        int databaseSizeBeforeDelete = tipoautorizacaoRepository.findAll().size();

        // Get the tipoautorizacao
        restTipoautorizacaoMockMvc.perform(delete("/api/tipoautorizacaos/{id}", tipoautorizacao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Tipoautorizacao> tipoautorizacaoList = tipoautorizacaoRepository.findAll();
        assertThat(tipoautorizacaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tipoautorizacao.class);
        Tipoautorizacao tipoautorizacao1 = new Tipoautorizacao();
        tipoautorizacao1.setId(1L);
        Tipoautorizacao tipoautorizacao2 = new Tipoautorizacao();
        tipoautorizacao2.setId(tipoautorizacao1.getId());
        assertThat(tipoautorizacao1).isEqualTo(tipoautorizacao2);
        tipoautorizacao2.setId(2L);
        assertThat(tipoautorizacao1).isNotEqualTo(tipoautorizacao2);
        tipoautorizacao1.setId(null);
        assertThat(tipoautorizacao1).isNotEqualTo(tipoautorizacao2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoautorizacaoDTO.class);
        TipoautorizacaoDTO tipoautorizacaoDTO1 = new TipoautorizacaoDTO();
        tipoautorizacaoDTO1.setId(1L);
        TipoautorizacaoDTO tipoautorizacaoDTO2 = new TipoautorizacaoDTO();
        assertThat(tipoautorizacaoDTO1).isNotEqualTo(tipoautorizacaoDTO2);
        tipoautorizacaoDTO2.setId(tipoautorizacaoDTO1.getId());
        assertThat(tipoautorizacaoDTO1).isEqualTo(tipoautorizacaoDTO2);
        tipoautorizacaoDTO2.setId(2L);
        assertThat(tipoautorizacaoDTO1).isNotEqualTo(tipoautorizacaoDTO2);
        tipoautorizacaoDTO1.setId(null);
        assertThat(tipoautorizacaoDTO1).isNotEqualTo(tipoautorizacaoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(tipoautorizacaoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(tipoautorizacaoMapper.fromId(null)).isNull();
    }
}
