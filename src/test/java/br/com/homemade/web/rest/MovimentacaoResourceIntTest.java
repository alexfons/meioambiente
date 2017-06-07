package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Movimentacao;
import br.com.homemade.repository.MovimentacaoRepository;
import br.com.homemade.service.dto.MovimentacaoDTO;
import br.com.homemade.service.mapper.MovimentacaoMapper;
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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static br.com.homemade.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the MovimentacaoResource REST controller.
 *
 * @see MovimentacaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class MovimentacaoResourceIntTest {

    private static final ZonedDateTime DEFAULT_DATA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_TIPOMOVIMENTACAO = "AAAAAAAAAA";
    private static final String UPDATED_TIPOMOVIMENTACAO = "BBBBBBBBBB";

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private MovimentacaoMapper movimentacaoMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMovimentacaoMockMvc;

    private Movimentacao movimentacao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MovimentacaoResource movimentacaoResource = new MovimentacaoResource(movimentacaoRepository, movimentacaoMapper);
        this.restMovimentacaoMockMvc = MockMvcBuilders.standaloneSetup(movimentacaoResource)
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
    public static Movimentacao createEntity(EntityManager em) {
        Movimentacao movimentacao = new Movimentacao()
            .data(DEFAULT_DATA)
            .tipomovimentacao(DEFAULT_TIPOMOVIMENTACAO);
        return movimentacao;
    }

    @Before
    public void initTest() {
        movimentacao = createEntity(em);
    }

    @Test
    @Transactional
    public void createMovimentacao() throws Exception {
        int databaseSizeBeforeCreate = movimentacaoRepository.findAll().size();

        // Create the Movimentacao
        MovimentacaoDTO movimentacaoDTO = movimentacaoMapper.toDto(movimentacao);
        restMovimentacaoMockMvc.perform(post("/api/movimentacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(movimentacaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Movimentacao in the database
        List<Movimentacao> movimentacaoList = movimentacaoRepository.findAll();
        assertThat(movimentacaoList).hasSize(databaseSizeBeforeCreate + 1);
        Movimentacao testMovimentacao = movimentacaoList.get(movimentacaoList.size() - 1);
        assertThat(testMovimentacao.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testMovimentacao.getTipomovimentacao()).isEqualTo(DEFAULT_TIPOMOVIMENTACAO);
    }

    @Test
    @Transactional
    public void createMovimentacaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = movimentacaoRepository.findAll().size();

        // Create the Movimentacao with an existing ID
        movimentacao.setId(1L);
        MovimentacaoDTO movimentacaoDTO = movimentacaoMapper.toDto(movimentacao);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMovimentacaoMockMvc.perform(post("/api/movimentacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(movimentacaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Movimentacao> movimentacaoList = movimentacaoRepository.findAll();
        assertThat(movimentacaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMovimentacaos() throws Exception {
        // Initialize the database
        movimentacaoRepository.saveAndFlush(movimentacao);

        // Get all the movimentacaoList
        restMovimentacaoMockMvc.perform(get("/api/movimentacaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(movimentacao.getId().intValue())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(sameInstant(DEFAULT_DATA))))
            .andExpect(jsonPath("$.[*].tipomovimentacao").value(hasItem(DEFAULT_TIPOMOVIMENTACAO.toString())));
    }

    @Test
    @Transactional
    public void getMovimentacao() throws Exception {
        // Initialize the database
        movimentacaoRepository.saveAndFlush(movimentacao);

        // Get the movimentacao
        restMovimentacaoMockMvc.perform(get("/api/movimentacaos/{id}", movimentacao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(movimentacao.getId().intValue()))
            .andExpect(jsonPath("$.data").value(sameInstant(DEFAULT_DATA)))
            .andExpect(jsonPath("$.tipomovimentacao").value(DEFAULT_TIPOMOVIMENTACAO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMovimentacao() throws Exception {
        // Get the movimentacao
        restMovimentacaoMockMvc.perform(get("/api/movimentacaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMovimentacao() throws Exception {
        // Initialize the database
        movimentacaoRepository.saveAndFlush(movimentacao);
        int databaseSizeBeforeUpdate = movimentacaoRepository.findAll().size();

        // Update the movimentacao
        Movimentacao updatedMovimentacao = movimentacaoRepository.findOne(movimentacao.getId());
        updatedMovimentacao
            .data(UPDATED_DATA)
            .tipomovimentacao(UPDATED_TIPOMOVIMENTACAO);
        MovimentacaoDTO movimentacaoDTO = movimentacaoMapper.toDto(updatedMovimentacao);

        restMovimentacaoMockMvc.perform(put("/api/movimentacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(movimentacaoDTO)))
            .andExpect(status().isOk());

        // Validate the Movimentacao in the database
        List<Movimentacao> movimentacaoList = movimentacaoRepository.findAll();
        assertThat(movimentacaoList).hasSize(databaseSizeBeforeUpdate);
        Movimentacao testMovimentacao = movimentacaoList.get(movimentacaoList.size() - 1);
        assertThat(testMovimentacao.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testMovimentacao.getTipomovimentacao()).isEqualTo(UPDATED_TIPOMOVIMENTACAO);
    }

    @Test
    @Transactional
    public void updateNonExistingMovimentacao() throws Exception {
        int databaseSizeBeforeUpdate = movimentacaoRepository.findAll().size();

        // Create the Movimentacao
        MovimentacaoDTO movimentacaoDTO = movimentacaoMapper.toDto(movimentacao);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMovimentacaoMockMvc.perform(put("/api/movimentacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(movimentacaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Movimentacao in the database
        List<Movimentacao> movimentacaoList = movimentacaoRepository.findAll();
        assertThat(movimentacaoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMovimentacao() throws Exception {
        // Initialize the database
        movimentacaoRepository.saveAndFlush(movimentacao);
        int databaseSizeBeforeDelete = movimentacaoRepository.findAll().size();

        // Get the movimentacao
        restMovimentacaoMockMvc.perform(delete("/api/movimentacaos/{id}", movimentacao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Movimentacao> movimentacaoList = movimentacaoRepository.findAll();
        assertThat(movimentacaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Movimentacao.class);
        Movimentacao movimentacao1 = new Movimentacao();
        movimentacao1.setId(1L);
        Movimentacao movimentacao2 = new Movimentacao();
        movimentacao2.setId(movimentacao1.getId());
        assertThat(movimentacao1).isEqualTo(movimentacao2);
        movimentacao2.setId(2L);
        assertThat(movimentacao1).isNotEqualTo(movimentacao2);
        movimentacao1.setId(null);
        assertThat(movimentacao1).isNotEqualTo(movimentacao2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MovimentacaoDTO.class);
        MovimentacaoDTO movimentacaoDTO1 = new MovimentacaoDTO();
        movimentacaoDTO1.setId(1L);
        MovimentacaoDTO movimentacaoDTO2 = new MovimentacaoDTO();
        assertThat(movimentacaoDTO1).isNotEqualTo(movimentacaoDTO2);
        movimentacaoDTO2.setId(movimentacaoDTO1.getId());
        assertThat(movimentacaoDTO1).isEqualTo(movimentacaoDTO2);
        movimentacaoDTO2.setId(2L);
        assertThat(movimentacaoDTO1).isNotEqualTo(movimentacaoDTO2);
        movimentacaoDTO1.setId(null);
        assertThat(movimentacaoDTO1).isNotEqualTo(movimentacaoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(movimentacaoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(movimentacaoMapper.fromId(null)).isNull();
    }
}
