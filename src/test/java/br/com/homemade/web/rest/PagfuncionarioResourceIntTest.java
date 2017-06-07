package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Pagfuncionario;
import br.com.homemade.repository.PagfuncionarioRepository;
import br.com.homemade.service.dto.PagfuncionarioDTO;
import br.com.homemade.service.mapper.PagfuncionarioMapper;
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
 * Test class for the PagfuncionarioResource REST controller.
 *
 * @see PagfuncionarioResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class PagfuncionarioResourceIntTest {

    private static final ZonedDateTime DEFAULT_DATAPAGAMENTO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAPAGAMENTO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_IDPAGFUNCIONARIOS = 1;
    private static final Integer UPDATED_IDPAGFUNCIONARIOS = 2;

    private static final Integer DEFAULT_NSOLICITACAO = 1;
    private static final Integer UPDATED_NSOLICITACAO = 2;

    private static final BigDecimal DEFAULT_PERCENTUAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_PERCENTUAL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SALARIO = new BigDecimal(1);
    private static final BigDecimal UPDATED_SALARIO = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SALARIOCONTRIBUICAO = new BigDecimal(1);
    private static final BigDecimal UPDATED_SALARIOCONTRIBUICAO = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SALARIOTOTAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_SALARIOTOTAL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VALORUS = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALORUS = new BigDecimal(2);

    @Autowired
    private PagfuncionarioRepository pagfuncionarioRepository;

    @Autowired
    private PagfuncionarioMapper pagfuncionarioMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPagfuncionarioMockMvc;

    private Pagfuncionario pagfuncionario;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PagfuncionarioResource pagfuncionarioResource = new PagfuncionarioResource(pagfuncionarioRepository, pagfuncionarioMapper);
        this.restPagfuncionarioMockMvc = MockMvcBuilders.standaloneSetup(pagfuncionarioResource)
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
    public static Pagfuncionario createEntity(EntityManager em) {
        Pagfuncionario pagfuncionario = new Pagfuncionario()
            .datapagamento(DEFAULT_DATAPAGAMENTO)
            .idpagfuncionarios(DEFAULT_IDPAGFUNCIONARIOS)
            .nsolicitacao(DEFAULT_NSOLICITACAO)
            .percentual(DEFAULT_PERCENTUAL)
            .salario(DEFAULT_SALARIO)
            .salariocontribuicao(DEFAULT_SALARIOCONTRIBUICAO)
            .salariototal(DEFAULT_SALARIOTOTAL)
            .valorus(DEFAULT_VALORUS);
        return pagfuncionario;
    }

    @Before
    public void initTest() {
        pagfuncionario = createEntity(em);
    }

    @Test
    @Transactional
    public void createPagfuncionario() throws Exception {
        int databaseSizeBeforeCreate = pagfuncionarioRepository.findAll().size();

        // Create the Pagfuncionario
        PagfuncionarioDTO pagfuncionarioDTO = pagfuncionarioMapper.toDto(pagfuncionario);
        restPagfuncionarioMockMvc.perform(post("/api/pagfuncionarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pagfuncionarioDTO)))
            .andExpect(status().isCreated());

        // Validate the Pagfuncionario in the database
        List<Pagfuncionario> pagfuncionarioList = pagfuncionarioRepository.findAll();
        assertThat(pagfuncionarioList).hasSize(databaseSizeBeforeCreate + 1);
        Pagfuncionario testPagfuncionario = pagfuncionarioList.get(pagfuncionarioList.size() - 1);
        assertThat(testPagfuncionario.getDatapagamento()).isEqualTo(DEFAULT_DATAPAGAMENTO);
        assertThat(testPagfuncionario.getIdpagfuncionarios()).isEqualTo(DEFAULT_IDPAGFUNCIONARIOS);
        assertThat(testPagfuncionario.getNsolicitacao()).isEqualTo(DEFAULT_NSOLICITACAO);
        assertThat(testPagfuncionario.getPercentual()).isEqualTo(DEFAULT_PERCENTUAL);
        assertThat(testPagfuncionario.getSalario()).isEqualTo(DEFAULT_SALARIO);
        assertThat(testPagfuncionario.getSalariocontribuicao()).isEqualTo(DEFAULT_SALARIOCONTRIBUICAO);
        assertThat(testPagfuncionario.getSalariototal()).isEqualTo(DEFAULT_SALARIOTOTAL);
        assertThat(testPagfuncionario.getValorus()).isEqualTo(DEFAULT_VALORUS);
    }

    @Test
    @Transactional
    public void createPagfuncionarioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pagfuncionarioRepository.findAll().size();

        // Create the Pagfuncionario with an existing ID
        pagfuncionario.setId(1L);
        PagfuncionarioDTO pagfuncionarioDTO = pagfuncionarioMapper.toDto(pagfuncionario);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPagfuncionarioMockMvc.perform(post("/api/pagfuncionarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pagfuncionarioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Pagfuncionario> pagfuncionarioList = pagfuncionarioRepository.findAll();
        assertThat(pagfuncionarioList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPagfuncionarios() throws Exception {
        // Initialize the database
        pagfuncionarioRepository.saveAndFlush(pagfuncionario);

        // Get all the pagfuncionarioList
        restPagfuncionarioMockMvc.perform(get("/api/pagfuncionarios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pagfuncionario.getId().intValue())))
            .andExpect(jsonPath("$.[*].datapagamento").value(hasItem(sameInstant(DEFAULT_DATAPAGAMENTO))))
            .andExpect(jsonPath("$.[*].idpagfuncionarios").value(hasItem(DEFAULT_IDPAGFUNCIONARIOS)))
            .andExpect(jsonPath("$.[*].nsolicitacao").value(hasItem(DEFAULT_NSOLICITACAO)))
            .andExpect(jsonPath("$.[*].percentual").value(hasItem(DEFAULT_PERCENTUAL.intValue())))
            .andExpect(jsonPath("$.[*].salario").value(hasItem(DEFAULT_SALARIO.intValue())))
            .andExpect(jsonPath("$.[*].salariocontribuicao").value(hasItem(DEFAULT_SALARIOCONTRIBUICAO.intValue())))
            .andExpect(jsonPath("$.[*].salariototal").value(hasItem(DEFAULT_SALARIOTOTAL.intValue())))
            .andExpect(jsonPath("$.[*].valorus").value(hasItem(DEFAULT_VALORUS.intValue())));
    }

    @Test
    @Transactional
    public void getPagfuncionario() throws Exception {
        // Initialize the database
        pagfuncionarioRepository.saveAndFlush(pagfuncionario);

        // Get the pagfuncionario
        restPagfuncionarioMockMvc.perform(get("/api/pagfuncionarios/{id}", pagfuncionario.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pagfuncionario.getId().intValue()))
            .andExpect(jsonPath("$.datapagamento").value(sameInstant(DEFAULT_DATAPAGAMENTO)))
            .andExpect(jsonPath("$.idpagfuncionarios").value(DEFAULT_IDPAGFUNCIONARIOS))
            .andExpect(jsonPath("$.nsolicitacao").value(DEFAULT_NSOLICITACAO))
            .andExpect(jsonPath("$.percentual").value(DEFAULT_PERCENTUAL.intValue()))
            .andExpect(jsonPath("$.salario").value(DEFAULT_SALARIO.intValue()))
            .andExpect(jsonPath("$.salariocontribuicao").value(DEFAULT_SALARIOCONTRIBUICAO.intValue()))
            .andExpect(jsonPath("$.salariototal").value(DEFAULT_SALARIOTOTAL.intValue()))
            .andExpect(jsonPath("$.valorus").value(DEFAULT_VALORUS.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPagfuncionario() throws Exception {
        // Get the pagfuncionario
        restPagfuncionarioMockMvc.perform(get("/api/pagfuncionarios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePagfuncionario() throws Exception {
        // Initialize the database
        pagfuncionarioRepository.saveAndFlush(pagfuncionario);
        int databaseSizeBeforeUpdate = pagfuncionarioRepository.findAll().size();

        // Update the pagfuncionario
        Pagfuncionario updatedPagfuncionario = pagfuncionarioRepository.findOne(pagfuncionario.getId());
        updatedPagfuncionario
            .datapagamento(UPDATED_DATAPAGAMENTO)
            .idpagfuncionarios(UPDATED_IDPAGFUNCIONARIOS)
            .nsolicitacao(UPDATED_NSOLICITACAO)
            .percentual(UPDATED_PERCENTUAL)
            .salario(UPDATED_SALARIO)
            .salariocontribuicao(UPDATED_SALARIOCONTRIBUICAO)
            .salariototal(UPDATED_SALARIOTOTAL)
            .valorus(UPDATED_VALORUS);
        PagfuncionarioDTO pagfuncionarioDTO = pagfuncionarioMapper.toDto(updatedPagfuncionario);

        restPagfuncionarioMockMvc.perform(put("/api/pagfuncionarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pagfuncionarioDTO)))
            .andExpect(status().isOk());

        // Validate the Pagfuncionario in the database
        List<Pagfuncionario> pagfuncionarioList = pagfuncionarioRepository.findAll();
        assertThat(pagfuncionarioList).hasSize(databaseSizeBeforeUpdate);
        Pagfuncionario testPagfuncionario = pagfuncionarioList.get(pagfuncionarioList.size() - 1);
        assertThat(testPagfuncionario.getDatapagamento()).isEqualTo(UPDATED_DATAPAGAMENTO);
        assertThat(testPagfuncionario.getIdpagfuncionarios()).isEqualTo(UPDATED_IDPAGFUNCIONARIOS);
        assertThat(testPagfuncionario.getNsolicitacao()).isEqualTo(UPDATED_NSOLICITACAO);
        assertThat(testPagfuncionario.getPercentual()).isEqualTo(UPDATED_PERCENTUAL);
        assertThat(testPagfuncionario.getSalario()).isEqualTo(UPDATED_SALARIO);
        assertThat(testPagfuncionario.getSalariocontribuicao()).isEqualTo(UPDATED_SALARIOCONTRIBUICAO);
        assertThat(testPagfuncionario.getSalariototal()).isEqualTo(UPDATED_SALARIOTOTAL);
        assertThat(testPagfuncionario.getValorus()).isEqualTo(UPDATED_VALORUS);
    }

    @Test
    @Transactional
    public void updateNonExistingPagfuncionario() throws Exception {
        int databaseSizeBeforeUpdate = pagfuncionarioRepository.findAll().size();

        // Create the Pagfuncionario
        PagfuncionarioDTO pagfuncionarioDTO = pagfuncionarioMapper.toDto(pagfuncionario);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPagfuncionarioMockMvc.perform(put("/api/pagfuncionarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pagfuncionarioDTO)))
            .andExpect(status().isCreated());

        // Validate the Pagfuncionario in the database
        List<Pagfuncionario> pagfuncionarioList = pagfuncionarioRepository.findAll();
        assertThat(pagfuncionarioList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePagfuncionario() throws Exception {
        // Initialize the database
        pagfuncionarioRepository.saveAndFlush(pagfuncionario);
        int databaseSizeBeforeDelete = pagfuncionarioRepository.findAll().size();

        // Get the pagfuncionario
        restPagfuncionarioMockMvc.perform(delete("/api/pagfuncionarios/{id}", pagfuncionario.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Pagfuncionario> pagfuncionarioList = pagfuncionarioRepository.findAll();
        assertThat(pagfuncionarioList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Pagfuncionario.class);
        Pagfuncionario pagfuncionario1 = new Pagfuncionario();
        pagfuncionario1.setId(1L);
        Pagfuncionario pagfuncionario2 = new Pagfuncionario();
        pagfuncionario2.setId(pagfuncionario1.getId());
        assertThat(pagfuncionario1).isEqualTo(pagfuncionario2);
        pagfuncionario2.setId(2L);
        assertThat(pagfuncionario1).isNotEqualTo(pagfuncionario2);
        pagfuncionario1.setId(null);
        assertThat(pagfuncionario1).isNotEqualTo(pagfuncionario2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PagfuncionarioDTO.class);
        PagfuncionarioDTO pagfuncionarioDTO1 = new PagfuncionarioDTO();
        pagfuncionarioDTO1.setId(1L);
        PagfuncionarioDTO pagfuncionarioDTO2 = new PagfuncionarioDTO();
        assertThat(pagfuncionarioDTO1).isNotEqualTo(pagfuncionarioDTO2);
        pagfuncionarioDTO2.setId(pagfuncionarioDTO1.getId());
        assertThat(pagfuncionarioDTO1).isEqualTo(pagfuncionarioDTO2);
        pagfuncionarioDTO2.setId(2L);
        assertThat(pagfuncionarioDTO1).isNotEqualTo(pagfuncionarioDTO2);
        pagfuncionarioDTO1.setId(null);
        assertThat(pagfuncionarioDTO1).isNotEqualTo(pagfuncionarioDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(pagfuncionarioMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(pagfuncionarioMapper.fromId(null)).isNull();
    }
}
