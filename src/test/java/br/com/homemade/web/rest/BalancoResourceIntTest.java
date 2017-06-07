package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Balanco;
import br.com.homemade.repository.BalancoRepository;
import br.com.homemade.service.dto.BalancoDTO;
import br.com.homemade.service.mapper.BalancoMapper;
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
 * Test class for the BalancoResource REST controller.
 *
 * @see BalancoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class BalancoResourceIntTest {

    private static final ZonedDateTime DEFAULT_DATAFIM = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAFIM = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATAINICIO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAINICIO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final Double DEFAULT_TAXA = 1D;
    private static final Double UPDATED_TAXA = 2D;

    @Autowired
    private BalancoRepository balancoRepository;

    @Autowired
    private BalancoMapper balancoMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBalancoMockMvc;

    private Balanco balanco;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        BalancoResource balancoResource = new BalancoResource(balancoRepository, balancoMapper);
        this.restBalancoMockMvc = MockMvcBuilders.standaloneSetup(balancoResource)
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
    public static Balanco createEntity(EntityManager em) {
        Balanco balanco = new Balanco()
            .datafim(DEFAULT_DATAFIM)
            .datainicio(DEFAULT_DATAINICIO)
            .descricao(DEFAULT_DESCRICAO)
            .taxa(DEFAULT_TAXA);
        return balanco;
    }

    @Before
    public void initTest() {
        balanco = createEntity(em);
    }

    @Test
    @Transactional
    public void createBalanco() throws Exception {
        int databaseSizeBeforeCreate = balancoRepository.findAll().size();

        // Create the Balanco
        BalancoDTO balancoDTO = balancoMapper.toDto(balanco);
        restBalancoMockMvc.perform(post("/api/balancos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(balancoDTO)))
            .andExpect(status().isCreated());

        // Validate the Balanco in the database
        List<Balanco> balancoList = balancoRepository.findAll();
        assertThat(balancoList).hasSize(databaseSizeBeforeCreate + 1);
        Balanco testBalanco = balancoList.get(balancoList.size() - 1);
        assertThat(testBalanco.getDatafim()).isEqualTo(DEFAULT_DATAFIM);
        assertThat(testBalanco.getDatainicio()).isEqualTo(DEFAULT_DATAINICIO);
        assertThat(testBalanco.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testBalanco.getTaxa()).isEqualTo(DEFAULT_TAXA);
    }

    @Test
    @Transactional
    public void createBalancoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = balancoRepository.findAll().size();

        // Create the Balanco with an existing ID
        balanco.setId(1L);
        BalancoDTO balancoDTO = balancoMapper.toDto(balanco);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBalancoMockMvc.perform(post("/api/balancos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(balancoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Balanco> balancoList = balancoRepository.findAll();
        assertThat(balancoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBalancos() throws Exception {
        // Initialize the database
        balancoRepository.saveAndFlush(balanco);

        // Get all the balancoList
        restBalancoMockMvc.perform(get("/api/balancos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(balanco.getId().intValue())))
            .andExpect(jsonPath("$.[*].datafim").value(hasItem(sameInstant(DEFAULT_DATAFIM))))
            .andExpect(jsonPath("$.[*].datainicio").value(hasItem(sameInstant(DEFAULT_DATAINICIO))))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].taxa").value(hasItem(DEFAULT_TAXA.doubleValue())));
    }

    @Test
    @Transactional
    public void getBalanco() throws Exception {
        // Initialize the database
        balancoRepository.saveAndFlush(balanco);

        // Get the balanco
        restBalancoMockMvc.perform(get("/api/balancos/{id}", balanco.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(balanco.getId().intValue()))
            .andExpect(jsonPath("$.datafim").value(sameInstant(DEFAULT_DATAFIM)))
            .andExpect(jsonPath("$.datainicio").value(sameInstant(DEFAULT_DATAINICIO)))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.taxa").value(DEFAULT_TAXA.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingBalanco() throws Exception {
        // Get the balanco
        restBalancoMockMvc.perform(get("/api/balancos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBalanco() throws Exception {
        // Initialize the database
        balancoRepository.saveAndFlush(balanco);
        int databaseSizeBeforeUpdate = balancoRepository.findAll().size();

        // Update the balanco
        Balanco updatedBalanco = balancoRepository.findOne(balanco.getId());
        updatedBalanco
            .datafim(UPDATED_DATAFIM)
            .datainicio(UPDATED_DATAINICIO)
            .descricao(UPDATED_DESCRICAO)
            .taxa(UPDATED_TAXA);
        BalancoDTO balancoDTO = balancoMapper.toDto(updatedBalanco);

        restBalancoMockMvc.perform(put("/api/balancos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(balancoDTO)))
            .andExpect(status().isOk());

        // Validate the Balanco in the database
        List<Balanco> balancoList = balancoRepository.findAll();
        assertThat(balancoList).hasSize(databaseSizeBeforeUpdate);
        Balanco testBalanco = balancoList.get(balancoList.size() - 1);
        assertThat(testBalanco.getDatafim()).isEqualTo(UPDATED_DATAFIM);
        assertThat(testBalanco.getDatainicio()).isEqualTo(UPDATED_DATAINICIO);
        assertThat(testBalanco.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testBalanco.getTaxa()).isEqualTo(UPDATED_TAXA);
    }

    @Test
    @Transactional
    public void updateNonExistingBalanco() throws Exception {
        int databaseSizeBeforeUpdate = balancoRepository.findAll().size();

        // Create the Balanco
        BalancoDTO balancoDTO = balancoMapper.toDto(balanco);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBalancoMockMvc.perform(put("/api/balancos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(balancoDTO)))
            .andExpect(status().isCreated());

        // Validate the Balanco in the database
        List<Balanco> balancoList = balancoRepository.findAll();
        assertThat(balancoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteBalanco() throws Exception {
        // Initialize the database
        balancoRepository.saveAndFlush(balanco);
        int databaseSizeBeforeDelete = balancoRepository.findAll().size();

        // Get the balanco
        restBalancoMockMvc.perform(delete("/api/balancos/{id}", balanco.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Balanco> balancoList = balancoRepository.findAll();
        assertThat(balancoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Balanco.class);
        Balanco balanco1 = new Balanco();
        balanco1.setId(1L);
        Balanco balanco2 = new Balanco();
        balanco2.setId(balanco1.getId());
        assertThat(balanco1).isEqualTo(balanco2);
        balanco2.setId(2L);
        assertThat(balanco1).isNotEqualTo(balanco2);
        balanco1.setId(null);
        assertThat(balanco1).isNotEqualTo(balanco2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BalancoDTO.class);
        BalancoDTO balancoDTO1 = new BalancoDTO();
        balancoDTO1.setId(1L);
        BalancoDTO balancoDTO2 = new BalancoDTO();
        assertThat(balancoDTO1).isNotEqualTo(balancoDTO2);
        balancoDTO2.setId(balancoDTO1.getId());
        assertThat(balancoDTO1).isEqualTo(balancoDTO2);
        balancoDTO2.setId(2L);
        assertThat(balancoDTO1).isNotEqualTo(balancoDTO2);
        balancoDTO1.setId(null);
        assertThat(balancoDTO1).isNotEqualTo(balancoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(balancoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(balancoMapper.fromId(null)).isNull();
    }
}
