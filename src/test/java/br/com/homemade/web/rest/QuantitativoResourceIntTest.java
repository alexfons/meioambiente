package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Quantitativo;
import br.com.homemade.repository.QuantitativoRepository;
import br.com.homemade.service.dto.QuantitativoDTO;
import br.com.homemade.service.mapper.QuantitativoMapper;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the QuantitativoResource REST controller.
 *
 * @see QuantitativoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class QuantitativoResourceIntTest {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final Integer DEFAULT_IDQUANTITATIVO = 1;
    private static final Integer UPDATED_IDQUANTITATIVO = 2;

    private static final BigDecimal DEFAULT_QUANTIDADE = new BigDecimal(1);
    private static final BigDecimal UPDATED_QUANTIDADE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TOTAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_TOTAL = new BigDecimal(2);

    private static final String DEFAULT_UNIDADE = "AAAAAAAAAA";
    private static final String UPDATED_UNIDADE = "BBBBBBBBBB";

    private static final Double DEFAULT_UNITARIO = 1D;
    private static final Double UPDATED_UNITARIO = 2D;

    @Autowired
    private QuantitativoRepository quantitativoRepository;

    @Autowired
    private QuantitativoMapper quantitativoMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restQuantitativoMockMvc;

    private Quantitativo quantitativo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        QuantitativoResource quantitativoResource = new QuantitativoResource(quantitativoRepository, quantitativoMapper);
        this.restQuantitativoMockMvc = MockMvcBuilders.standaloneSetup(quantitativoResource)
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
    public static Quantitativo createEntity(EntityManager em) {
        Quantitativo quantitativo = new Quantitativo()
            .descricao(DEFAULT_DESCRICAO)
            .idquantitativo(DEFAULT_IDQUANTITATIVO)
            .quantidade(DEFAULT_QUANTIDADE)
            .total(DEFAULT_TOTAL)
            .unidade(DEFAULT_UNIDADE)
            .unitario(DEFAULT_UNITARIO);
        return quantitativo;
    }

    @Before
    public void initTest() {
        quantitativo = createEntity(em);
    }

    @Test
    @Transactional
    public void createQuantitativo() throws Exception {
        int databaseSizeBeforeCreate = quantitativoRepository.findAll().size();

        // Create the Quantitativo
        QuantitativoDTO quantitativoDTO = quantitativoMapper.toDto(quantitativo);
        restQuantitativoMockMvc.perform(post("/api/quantitativos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quantitativoDTO)))
            .andExpect(status().isCreated());

        // Validate the Quantitativo in the database
        List<Quantitativo> quantitativoList = quantitativoRepository.findAll();
        assertThat(quantitativoList).hasSize(databaseSizeBeforeCreate + 1);
        Quantitativo testQuantitativo = quantitativoList.get(quantitativoList.size() - 1);
        assertThat(testQuantitativo.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testQuantitativo.getIdquantitativo()).isEqualTo(DEFAULT_IDQUANTITATIVO);
        assertThat(testQuantitativo.getQuantidade()).isEqualTo(DEFAULT_QUANTIDADE);
        assertThat(testQuantitativo.getTotal()).isEqualTo(DEFAULT_TOTAL);
        assertThat(testQuantitativo.getUnidade()).isEqualTo(DEFAULT_UNIDADE);
        assertThat(testQuantitativo.getUnitario()).isEqualTo(DEFAULT_UNITARIO);
    }

    @Test
    @Transactional
    public void createQuantitativoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = quantitativoRepository.findAll().size();

        // Create the Quantitativo with an existing ID
        quantitativo.setId(1L);
        QuantitativoDTO quantitativoDTO = quantitativoMapper.toDto(quantitativo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuantitativoMockMvc.perform(post("/api/quantitativos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quantitativoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Quantitativo> quantitativoList = quantitativoRepository.findAll();
        assertThat(quantitativoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllQuantitativos() throws Exception {
        // Initialize the database
        quantitativoRepository.saveAndFlush(quantitativo);

        // Get all the quantitativoList
        restQuantitativoMockMvc.perform(get("/api/quantitativos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(quantitativo.getId().intValue())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].idquantitativo").value(hasItem(DEFAULT_IDQUANTITATIVO)))
            .andExpect(jsonPath("$.[*].quantidade").value(hasItem(DEFAULT_QUANTIDADE.intValue())))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL.intValue())))
            .andExpect(jsonPath("$.[*].unidade").value(hasItem(DEFAULT_UNIDADE.toString())))
            .andExpect(jsonPath("$.[*].unitario").value(hasItem(DEFAULT_UNITARIO.doubleValue())));
    }

    @Test
    @Transactional
    public void getQuantitativo() throws Exception {
        // Initialize the database
        quantitativoRepository.saveAndFlush(quantitativo);

        // Get the quantitativo
        restQuantitativoMockMvc.perform(get("/api/quantitativos/{id}", quantitativo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(quantitativo.getId().intValue()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.idquantitativo").value(DEFAULT_IDQUANTITATIVO))
            .andExpect(jsonPath("$.quantidade").value(DEFAULT_QUANTIDADE.intValue()))
            .andExpect(jsonPath("$.total").value(DEFAULT_TOTAL.intValue()))
            .andExpect(jsonPath("$.unidade").value(DEFAULT_UNIDADE.toString()))
            .andExpect(jsonPath("$.unitario").value(DEFAULT_UNITARIO.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingQuantitativo() throws Exception {
        // Get the quantitativo
        restQuantitativoMockMvc.perform(get("/api/quantitativos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQuantitativo() throws Exception {
        // Initialize the database
        quantitativoRepository.saveAndFlush(quantitativo);
        int databaseSizeBeforeUpdate = quantitativoRepository.findAll().size();

        // Update the quantitativo
        Quantitativo updatedQuantitativo = quantitativoRepository.findOne(quantitativo.getId());
        updatedQuantitativo
            .descricao(UPDATED_DESCRICAO)
            .idquantitativo(UPDATED_IDQUANTITATIVO)
            .quantidade(UPDATED_QUANTIDADE)
            .total(UPDATED_TOTAL)
            .unidade(UPDATED_UNIDADE)
            .unitario(UPDATED_UNITARIO);
        QuantitativoDTO quantitativoDTO = quantitativoMapper.toDto(updatedQuantitativo);

        restQuantitativoMockMvc.perform(put("/api/quantitativos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quantitativoDTO)))
            .andExpect(status().isOk());

        // Validate the Quantitativo in the database
        List<Quantitativo> quantitativoList = quantitativoRepository.findAll();
        assertThat(quantitativoList).hasSize(databaseSizeBeforeUpdate);
        Quantitativo testQuantitativo = quantitativoList.get(quantitativoList.size() - 1);
        assertThat(testQuantitativo.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testQuantitativo.getIdquantitativo()).isEqualTo(UPDATED_IDQUANTITATIVO);
        assertThat(testQuantitativo.getQuantidade()).isEqualTo(UPDATED_QUANTIDADE);
        assertThat(testQuantitativo.getTotal()).isEqualTo(UPDATED_TOTAL);
        assertThat(testQuantitativo.getUnidade()).isEqualTo(UPDATED_UNIDADE);
        assertThat(testQuantitativo.getUnitario()).isEqualTo(UPDATED_UNITARIO);
    }

    @Test
    @Transactional
    public void updateNonExistingQuantitativo() throws Exception {
        int databaseSizeBeforeUpdate = quantitativoRepository.findAll().size();

        // Create the Quantitativo
        QuantitativoDTO quantitativoDTO = quantitativoMapper.toDto(quantitativo);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restQuantitativoMockMvc.perform(put("/api/quantitativos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quantitativoDTO)))
            .andExpect(status().isCreated());

        // Validate the Quantitativo in the database
        List<Quantitativo> quantitativoList = quantitativoRepository.findAll();
        assertThat(quantitativoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteQuantitativo() throws Exception {
        // Initialize the database
        quantitativoRepository.saveAndFlush(quantitativo);
        int databaseSizeBeforeDelete = quantitativoRepository.findAll().size();

        // Get the quantitativo
        restQuantitativoMockMvc.perform(delete("/api/quantitativos/{id}", quantitativo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Quantitativo> quantitativoList = quantitativoRepository.findAll();
        assertThat(quantitativoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Quantitativo.class);
        Quantitativo quantitativo1 = new Quantitativo();
        quantitativo1.setId(1L);
        Quantitativo quantitativo2 = new Quantitativo();
        quantitativo2.setId(quantitativo1.getId());
        assertThat(quantitativo1).isEqualTo(quantitativo2);
        quantitativo2.setId(2L);
        assertThat(quantitativo1).isNotEqualTo(quantitativo2);
        quantitativo1.setId(null);
        assertThat(quantitativo1).isNotEqualTo(quantitativo2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuantitativoDTO.class);
        QuantitativoDTO quantitativoDTO1 = new QuantitativoDTO();
        quantitativoDTO1.setId(1L);
        QuantitativoDTO quantitativoDTO2 = new QuantitativoDTO();
        assertThat(quantitativoDTO1).isNotEqualTo(quantitativoDTO2);
        quantitativoDTO2.setId(quantitativoDTO1.getId());
        assertThat(quantitativoDTO1).isEqualTo(quantitativoDTO2);
        quantitativoDTO2.setId(2L);
        assertThat(quantitativoDTO1).isNotEqualTo(quantitativoDTO2);
        quantitativoDTO1.setId(null);
        assertThat(quantitativoDTO1).isNotEqualTo(quantitativoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(quantitativoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(quantitativoMapper.fromId(null)).isNull();
    }
}
