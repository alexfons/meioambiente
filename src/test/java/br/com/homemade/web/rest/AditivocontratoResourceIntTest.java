package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Aditivocontrato;
import br.com.homemade.repository.AditivocontratoRepository;
import br.com.homemade.service.dto.AditivocontratoDTO;
import br.com.homemade.service.mapper.AditivocontratoMapper;
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
 * Test class for the AditivocontratoResource REST controller.
 *
 * @see AditivocontratoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class AditivocontratoResourceIntTest {

    private static final Integer DEFAULT_NUMADITIVO = 1;
    private static final Integer UPDATED_NUMADITIVO = 2;

    private static final String DEFAULT_TIPOADITIVO = "AAAAAAAAAA";
    private static final String UPDATED_TIPOADITIVO = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_PRAZOADITIVO = 1;
    private static final Integer UPDATED_PRAZOADITIVO = 2;

    private static final BigDecimal DEFAULT_VALOR = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALOR = new BigDecimal(2);

    @Autowired
    private AditivocontratoRepository aditivocontratoRepository;

    @Autowired
    private AditivocontratoMapper aditivocontratoMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAditivocontratoMockMvc;

    private Aditivocontrato aditivocontrato;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AditivocontratoResource aditivocontratoResource = new AditivocontratoResource(aditivocontratoRepository, aditivocontratoMapper);
        this.restAditivocontratoMockMvc = MockMvcBuilders.standaloneSetup(aditivocontratoResource)
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
    public static Aditivocontrato createEntity(EntityManager em) {
        Aditivocontrato aditivocontrato = new Aditivocontrato()
            .numaditivo(DEFAULT_NUMADITIVO)
            .tipoaditivo(DEFAULT_TIPOADITIVO)
            .data(DEFAULT_DATA)
            .prazoaditivo(DEFAULT_PRAZOADITIVO)
            .valor(DEFAULT_VALOR);
        return aditivocontrato;
    }

    @Before
    public void initTest() {
        aditivocontrato = createEntity(em);
    }

    @Test
    @Transactional
    public void createAditivocontrato() throws Exception {
        int databaseSizeBeforeCreate = aditivocontratoRepository.findAll().size();

        // Create the Aditivocontrato
        AditivocontratoDTO aditivocontratoDTO = aditivocontratoMapper.toDto(aditivocontrato);
        restAditivocontratoMockMvc.perform(post("/api/aditivocontratoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aditivocontratoDTO)))
            .andExpect(status().isCreated());

        // Validate the Aditivocontrato in the database
        List<Aditivocontrato> aditivocontratoList = aditivocontratoRepository.findAll();
        assertThat(aditivocontratoList).hasSize(databaseSizeBeforeCreate + 1);
        Aditivocontrato testAditivocontrato = aditivocontratoList.get(aditivocontratoList.size() - 1);
        assertThat(testAditivocontrato.getNumaditivo()).isEqualTo(DEFAULT_NUMADITIVO);
        assertThat(testAditivocontrato.getTipoaditivo()).isEqualTo(DEFAULT_TIPOADITIVO);
        assertThat(testAditivocontrato.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testAditivocontrato.getPrazoaditivo()).isEqualTo(DEFAULT_PRAZOADITIVO);
        assertThat(testAditivocontrato.getValor()).isEqualTo(DEFAULT_VALOR);
    }

    @Test
    @Transactional
    public void createAditivocontratoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = aditivocontratoRepository.findAll().size();

        // Create the Aditivocontrato with an existing ID
        aditivocontrato.setId(1L);
        AditivocontratoDTO aditivocontratoDTO = aditivocontratoMapper.toDto(aditivocontrato);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAditivocontratoMockMvc.perform(post("/api/aditivocontratoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aditivocontratoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Aditivocontrato> aditivocontratoList = aditivocontratoRepository.findAll();
        assertThat(aditivocontratoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAditivocontratoes() throws Exception {
        // Initialize the database
        aditivocontratoRepository.saveAndFlush(aditivocontrato);

        // Get all the aditivocontratoList
        restAditivocontratoMockMvc.perform(get("/api/aditivocontratoes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(aditivocontrato.getId().intValue())))
            .andExpect(jsonPath("$.[*].numaditivo").value(hasItem(DEFAULT_NUMADITIVO)))
            .andExpect(jsonPath("$.[*].tipoaditivo").value(hasItem(DEFAULT_TIPOADITIVO.toString())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(sameInstant(DEFAULT_DATA))))
            .andExpect(jsonPath("$.[*].prazoaditivo").value(hasItem(DEFAULT_PRAZOADITIVO)))
            .andExpect(jsonPath("$.[*].valor").value(hasItem(DEFAULT_VALOR.intValue())));
    }

    @Test
    @Transactional
    public void getAditivocontrato() throws Exception {
        // Initialize the database
        aditivocontratoRepository.saveAndFlush(aditivocontrato);

        // Get the aditivocontrato
        restAditivocontratoMockMvc.perform(get("/api/aditivocontratoes/{id}", aditivocontrato.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(aditivocontrato.getId().intValue()))
            .andExpect(jsonPath("$.numaditivo").value(DEFAULT_NUMADITIVO))
            .andExpect(jsonPath("$.tipoaditivo").value(DEFAULT_TIPOADITIVO.toString()))
            .andExpect(jsonPath("$.data").value(sameInstant(DEFAULT_DATA)))
            .andExpect(jsonPath("$.prazoaditivo").value(DEFAULT_PRAZOADITIVO))
            .andExpect(jsonPath("$.valor").value(DEFAULT_VALOR.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAditivocontrato() throws Exception {
        // Get the aditivocontrato
        restAditivocontratoMockMvc.perform(get("/api/aditivocontratoes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAditivocontrato() throws Exception {
        // Initialize the database
        aditivocontratoRepository.saveAndFlush(aditivocontrato);
        int databaseSizeBeforeUpdate = aditivocontratoRepository.findAll().size();

        // Update the aditivocontrato
        Aditivocontrato updatedAditivocontrato = aditivocontratoRepository.findOne(aditivocontrato.getId());
        updatedAditivocontrato
            .numaditivo(UPDATED_NUMADITIVO)
            .tipoaditivo(UPDATED_TIPOADITIVO)
            .data(UPDATED_DATA)
            .prazoaditivo(UPDATED_PRAZOADITIVO)
            .valor(UPDATED_VALOR);
        AditivocontratoDTO aditivocontratoDTO = aditivocontratoMapper.toDto(updatedAditivocontrato);

        restAditivocontratoMockMvc.perform(put("/api/aditivocontratoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aditivocontratoDTO)))
            .andExpect(status().isOk());

        // Validate the Aditivocontrato in the database
        List<Aditivocontrato> aditivocontratoList = aditivocontratoRepository.findAll();
        assertThat(aditivocontratoList).hasSize(databaseSizeBeforeUpdate);
        Aditivocontrato testAditivocontrato = aditivocontratoList.get(aditivocontratoList.size() - 1);
        assertThat(testAditivocontrato.getNumaditivo()).isEqualTo(UPDATED_NUMADITIVO);
        assertThat(testAditivocontrato.getTipoaditivo()).isEqualTo(UPDATED_TIPOADITIVO);
        assertThat(testAditivocontrato.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testAditivocontrato.getPrazoaditivo()).isEqualTo(UPDATED_PRAZOADITIVO);
        assertThat(testAditivocontrato.getValor()).isEqualTo(UPDATED_VALOR);
    }

    @Test
    @Transactional
    public void updateNonExistingAditivocontrato() throws Exception {
        int databaseSizeBeforeUpdate = aditivocontratoRepository.findAll().size();

        // Create the Aditivocontrato
        AditivocontratoDTO aditivocontratoDTO = aditivocontratoMapper.toDto(aditivocontrato);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAditivocontratoMockMvc.perform(put("/api/aditivocontratoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aditivocontratoDTO)))
            .andExpect(status().isCreated());

        // Validate the Aditivocontrato in the database
        List<Aditivocontrato> aditivocontratoList = aditivocontratoRepository.findAll();
        assertThat(aditivocontratoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAditivocontrato() throws Exception {
        // Initialize the database
        aditivocontratoRepository.saveAndFlush(aditivocontrato);
        int databaseSizeBeforeDelete = aditivocontratoRepository.findAll().size();

        // Get the aditivocontrato
        restAditivocontratoMockMvc.perform(delete("/api/aditivocontratoes/{id}", aditivocontrato.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Aditivocontrato> aditivocontratoList = aditivocontratoRepository.findAll();
        assertThat(aditivocontratoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Aditivocontrato.class);
        Aditivocontrato aditivocontrato1 = new Aditivocontrato();
        aditivocontrato1.setId(1L);
        Aditivocontrato aditivocontrato2 = new Aditivocontrato();
        aditivocontrato2.setId(aditivocontrato1.getId());
        assertThat(aditivocontrato1).isEqualTo(aditivocontrato2);
        aditivocontrato2.setId(2L);
        assertThat(aditivocontrato1).isNotEqualTo(aditivocontrato2);
        aditivocontrato1.setId(null);
        assertThat(aditivocontrato1).isNotEqualTo(aditivocontrato2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AditivocontratoDTO.class);
        AditivocontratoDTO aditivocontratoDTO1 = new AditivocontratoDTO();
        aditivocontratoDTO1.setId(1L);
        AditivocontratoDTO aditivocontratoDTO2 = new AditivocontratoDTO();
        assertThat(aditivocontratoDTO1).isNotEqualTo(aditivocontratoDTO2);
        aditivocontratoDTO2.setId(aditivocontratoDTO1.getId());
        assertThat(aditivocontratoDTO1).isEqualTo(aditivocontratoDTO2);
        aditivocontratoDTO2.setId(2L);
        assertThat(aditivocontratoDTO1).isNotEqualTo(aditivocontratoDTO2);
        aditivocontratoDTO1.setId(null);
        assertThat(aditivocontratoDTO1).isNotEqualTo(aditivocontratoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(aditivocontratoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(aditivocontratoMapper.fromId(null)).isNull();
    }
}
