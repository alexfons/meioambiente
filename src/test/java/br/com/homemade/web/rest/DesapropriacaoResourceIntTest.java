package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Desapropriacao;
import br.com.homemade.repository.DesapropriacaoRepository;
import br.com.homemade.service.dto.DesapropriacaoDTO;
import br.com.homemade.service.mapper.DesapropriacaoMapper;
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
import java.math.BigDecimal;
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
 * Test class for the DesapropriacaoResource REST controller.
 *
 * @see DesapropriacaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class DesapropriacaoResourceIntTest {

    private static final ZonedDateTime DEFAULT_DATAOP = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAOP = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_IDDESAPROPRIA = 1;
    private static final Integer UPDATED_IDDESAPROPRIA = 2;

    private static final String DEFAULT_NPROCESSO = "AAAAAAAAAA";
    private static final String UPDATED_NPROCESSO = "BBBBBBBBBB";

    private static final String DEFAULT_NOMEDESAPROPRIADO = "AAAAAAAAAA";
    private static final String UPDATED_NOMEDESAPROPRIADO = "BBBBBBBBBB";

    private static final String DEFAULT_LOCAL = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL = "BBBBBBBBBB";

    private static final Integer DEFAULT_NSOLICITACAO = 1;
    private static final Integer UPDATED_NSOLICITACAO = 2;

    private static final BigDecimal DEFAULT_VALORPAGO = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALORPAGO = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VALORUS = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALORUS = new BigDecimal(2);

    @Autowired
    private DesapropriacaoRepository desapropriacaoRepository;

    @Autowired
    private DesapropriacaoMapper desapropriacaoMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDesapropriacaoMockMvc;

    private Desapropriacao desapropriacao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DesapropriacaoResource desapropriacaoResource = new DesapropriacaoResource(desapropriacaoRepository, desapropriacaoMapper);
        this.restDesapropriacaoMockMvc = MockMvcBuilders.standaloneSetup(desapropriacaoResource)
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
    public static Desapropriacao createEntity(EntityManager em) {
        Desapropriacao desapropriacao = new Desapropriacao()
            .dataop(DEFAULT_DATAOP)
            .iddesapropria(DEFAULT_IDDESAPROPRIA)
            .nprocesso(DEFAULT_NPROCESSO)
            .nomedesapropriado(DEFAULT_NOMEDESAPROPRIADO)
            .local(DEFAULT_LOCAL)
            .nsolicitacao(DEFAULT_NSOLICITACAO)
            .valorpago(DEFAULT_VALORPAGO)
            .valorus(DEFAULT_VALORUS);
        return desapropriacao;
    }

    @Before
    public void initTest() {
        desapropriacao = createEntity(em);
    }

    @Test
    @Transactional
    public void createDesapropriacao() throws Exception {
        int databaseSizeBeforeCreate = desapropriacaoRepository.findAll().size();

        // Create the Desapropriacao
        DesapropriacaoDTO desapropriacaoDTO = desapropriacaoMapper.toDto(desapropriacao);
        restDesapropriacaoMockMvc.perform(post("/api/desapropriacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(desapropriacaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Desapropriacao in the database
        List<Desapropriacao> desapropriacaoList = desapropriacaoRepository.findAll();
        assertThat(desapropriacaoList).hasSize(databaseSizeBeforeCreate + 1);
        Desapropriacao testDesapropriacao = desapropriacaoList.get(desapropriacaoList.size() - 1);
        assertThat(testDesapropriacao.getDataop()).isEqualTo(DEFAULT_DATAOP);
        assertThat(testDesapropriacao.getIddesapropria()).isEqualTo(DEFAULT_IDDESAPROPRIA);
        assertThat(testDesapropriacao.getNprocesso()).isEqualTo(DEFAULT_NPROCESSO);
        assertThat(testDesapropriacao.getNomedesapropriado()).isEqualTo(DEFAULT_NOMEDESAPROPRIADO);
        assertThat(testDesapropriacao.getLocal()).isEqualTo(DEFAULT_LOCAL);
        assertThat(testDesapropriacao.getNsolicitacao()).isEqualTo(DEFAULT_NSOLICITACAO);
        assertThat(testDesapropriacao.getValorpago()).isEqualTo(DEFAULT_VALORPAGO);
        assertThat(testDesapropriacao.getValorus()).isEqualTo(DEFAULT_VALORUS);
    }

    @Test
    @Transactional
    public void createDesapropriacaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = desapropriacaoRepository.findAll().size();

        // Create the Desapropriacao with an existing ID
        desapropriacao.setId(1L);
        DesapropriacaoDTO desapropriacaoDTO = desapropriacaoMapper.toDto(desapropriacao);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDesapropriacaoMockMvc.perform(post("/api/desapropriacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(desapropriacaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Desapropriacao> desapropriacaoList = desapropriacaoRepository.findAll();
        assertThat(desapropriacaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDesapropriacaos() throws Exception {
        // Initialize the database
        desapropriacaoRepository.saveAndFlush(desapropriacao);

        // Get all the desapropriacaoList
        restDesapropriacaoMockMvc.perform(get("/api/desapropriacaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(desapropriacao.getId().intValue())))
            .andExpect(jsonPath("$.[*].dataop").value(hasItem(sameInstant(DEFAULT_DATAOP))))
            .andExpect(jsonPath("$.[*].iddesapropria").value(hasItem(DEFAULT_IDDESAPROPRIA)))
            .andExpect(jsonPath("$.[*].nprocesso").value(hasItem(DEFAULT_NPROCESSO.toString())))
            .andExpect(jsonPath("$.[*].nomedesapropriado").value(hasItem(DEFAULT_NOMEDESAPROPRIADO.toString())))
            .andExpect(jsonPath("$.[*].local").value(hasItem(DEFAULT_LOCAL.toString())))
            .andExpect(jsonPath("$.[*].nsolicitacao").value(hasItem(DEFAULT_NSOLICITACAO)))
            .andExpect(jsonPath("$.[*].valorpago").value(hasItem(DEFAULT_VALORPAGO.intValue())))
            .andExpect(jsonPath("$.[*].valorus").value(hasItem(DEFAULT_VALORUS.intValue())));
    }

    @Test
    @Transactional
    public void getDesapropriacao() throws Exception {
        // Initialize the database
        desapropriacaoRepository.saveAndFlush(desapropriacao);

        // Get the desapropriacao
        restDesapropriacaoMockMvc.perform(get("/api/desapropriacaos/{id}", desapropriacao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(desapropriacao.getId().intValue()))
            .andExpect(jsonPath("$.dataop").value(sameInstant(DEFAULT_DATAOP)))
            .andExpect(jsonPath("$.iddesapropria").value(DEFAULT_IDDESAPROPRIA))
            .andExpect(jsonPath("$.nprocesso").value(DEFAULT_NPROCESSO.toString()))
            .andExpect(jsonPath("$.nomedesapropriado").value(DEFAULT_NOMEDESAPROPRIADO.toString()))
            .andExpect(jsonPath("$.local").value(DEFAULT_LOCAL.toString()))
            .andExpect(jsonPath("$.nsolicitacao").value(DEFAULT_NSOLICITACAO))
            .andExpect(jsonPath("$.valorpago").value(DEFAULT_VALORPAGO.intValue()))
            .andExpect(jsonPath("$.valorus").value(DEFAULT_VALORUS.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDesapropriacao() throws Exception {
        // Get the desapropriacao
        restDesapropriacaoMockMvc.perform(get("/api/desapropriacaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDesapropriacao() throws Exception {
        // Initialize the database
        desapropriacaoRepository.saveAndFlush(desapropriacao);
        int databaseSizeBeforeUpdate = desapropriacaoRepository.findAll().size();

        // Update the desapropriacao
        Desapropriacao updatedDesapropriacao = desapropriacaoRepository.findOne(desapropriacao.getId());
        updatedDesapropriacao
            .dataop(UPDATED_DATAOP)
            .iddesapropria(UPDATED_IDDESAPROPRIA)
            .nprocesso(UPDATED_NPROCESSO)
            .nomedesapropriado(UPDATED_NOMEDESAPROPRIADO)
            .local(UPDATED_LOCAL)
            .nsolicitacao(UPDATED_NSOLICITACAO)
            .valorpago(UPDATED_VALORPAGO)
            .valorus(UPDATED_VALORUS);
        DesapropriacaoDTO desapropriacaoDTO = desapropriacaoMapper.toDto(updatedDesapropriacao);

        restDesapropriacaoMockMvc.perform(put("/api/desapropriacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(desapropriacaoDTO)))
            .andExpect(status().isOk());

        // Validate the Desapropriacao in the database
        List<Desapropriacao> desapropriacaoList = desapropriacaoRepository.findAll();
        assertThat(desapropriacaoList).hasSize(databaseSizeBeforeUpdate);
        Desapropriacao testDesapropriacao = desapropriacaoList.get(desapropriacaoList.size() - 1);
        assertThat(testDesapropriacao.getDataop()).isEqualTo(UPDATED_DATAOP);
        assertThat(testDesapropriacao.getIddesapropria()).isEqualTo(UPDATED_IDDESAPROPRIA);
        assertThat(testDesapropriacao.getNprocesso()).isEqualTo(UPDATED_NPROCESSO);
        assertThat(testDesapropriacao.getNomedesapropriado()).isEqualTo(UPDATED_NOMEDESAPROPRIADO);
        assertThat(testDesapropriacao.getLocal()).isEqualTo(UPDATED_LOCAL);
        assertThat(testDesapropriacao.getNsolicitacao()).isEqualTo(UPDATED_NSOLICITACAO);
        assertThat(testDesapropriacao.getValorpago()).isEqualTo(UPDATED_VALORPAGO);
        assertThat(testDesapropriacao.getValorus()).isEqualTo(UPDATED_VALORUS);
    }

    @Test
    @Transactional
    public void updateNonExistingDesapropriacao() throws Exception {
        int databaseSizeBeforeUpdate = desapropriacaoRepository.findAll().size();

        // Create the Desapropriacao
        DesapropriacaoDTO desapropriacaoDTO = desapropriacaoMapper.toDto(desapropriacao);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDesapropriacaoMockMvc.perform(put("/api/desapropriacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(desapropriacaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Desapropriacao in the database
        List<Desapropriacao> desapropriacaoList = desapropriacaoRepository.findAll();
        assertThat(desapropriacaoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDesapropriacao() throws Exception {
        // Initialize the database
        desapropriacaoRepository.saveAndFlush(desapropriacao);
        int databaseSizeBeforeDelete = desapropriacaoRepository.findAll().size();

        // Get the desapropriacao
        restDesapropriacaoMockMvc.perform(delete("/api/desapropriacaos/{id}", desapropriacao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Desapropriacao> desapropriacaoList = desapropriacaoRepository.findAll();
        assertThat(desapropriacaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Desapropriacao.class);
        Desapropriacao desapropriacao1 = new Desapropriacao();
        desapropriacao1.setId(1L);
        Desapropriacao desapropriacao2 = new Desapropriacao();
        desapropriacao2.setId(desapropriacao1.getId());
        assertThat(desapropriacao1).isEqualTo(desapropriacao2);
        desapropriacao2.setId(2L);
        assertThat(desapropriacao1).isNotEqualTo(desapropriacao2);
        desapropriacao1.setId(null);
        assertThat(desapropriacao1).isNotEqualTo(desapropriacao2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DesapropriacaoDTO.class);
        DesapropriacaoDTO desapropriacaoDTO1 = new DesapropriacaoDTO();
        desapropriacaoDTO1.setId(1L);
        DesapropriacaoDTO desapropriacaoDTO2 = new DesapropriacaoDTO();
        assertThat(desapropriacaoDTO1).isNotEqualTo(desapropriacaoDTO2);
        desapropriacaoDTO2.setId(desapropriacaoDTO1.getId());
        assertThat(desapropriacaoDTO1).isEqualTo(desapropriacaoDTO2);
        desapropriacaoDTO2.setId(2L);
        assertThat(desapropriacaoDTO1).isNotEqualTo(desapropriacaoDTO2);
        desapropriacaoDTO1.setId(null);
        assertThat(desapropriacaoDTO1).isNotEqualTo(desapropriacaoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(desapropriacaoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(desapropriacaoMapper.fromId(null)).isNull();
    }
}
