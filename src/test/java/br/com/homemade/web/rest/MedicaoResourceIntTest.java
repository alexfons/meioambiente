package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Medicao;
import br.com.homemade.repository.MedicaoRepository;
import br.com.homemade.service.dto.MedicaoDTO;
import br.com.homemade.service.mapper.MedicaoMapper;
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
 * Test class for the MedicaoResource REST controller.
 *
 * @see MedicaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class MedicaoResourceIntTest {

    private static final Boolean DEFAULT_AJUSTECAMBIO = false;
    private static final Boolean UPDATED_AJUSTECAMBIO = true;

    private static final String DEFAULT_CONFERIDO = "AAAAAAAAAA";
    private static final String UPDATED_CONFERIDO = "BBBBBBBBBB";

    private static final Integer DEFAULT_IDMEDICAO = 1;
    private static final Integer UPDATED_IDMEDICAO = 2;

    private static final ZonedDateTime DEFAULT_MES = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_MES = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_NUMMEDICAO = 1;
    private static final Integer UPDATED_NUMMEDICAO = 2;

    private static final String DEFAULT_TIPOMEDICAO = "AAAAAAAAAA";
    private static final String UPDATED_TIPOMEDICAO = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_VALORMEDICAO = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALORMEDICAO = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VALORMEDICAOREAJUSTE = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALORMEDICAOREAJUSTE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VALORUSMEDICAO = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALORUSMEDICAO = new BigDecimal(2);

    @Autowired
    private MedicaoRepository medicaoRepository;

    @Autowired
    private MedicaoMapper medicaoMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMedicaoMockMvc;

    private Medicao medicao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MedicaoResource medicaoResource = new MedicaoResource(medicaoRepository, medicaoMapper);
        this.restMedicaoMockMvc = MockMvcBuilders.standaloneSetup(medicaoResource)
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
    public static Medicao createEntity(EntityManager em) {
        Medicao medicao = new Medicao()
            .ajustecambio(DEFAULT_AJUSTECAMBIO)
            .conferido(DEFAULT_CONFERIDO)
            .idmedicao(DEFAULT_IDMEDICAO)
            .mes(DEFAULT_MES)
            .nummedicao(DEFAULT_NUMMEDICAO)
            .tipomedicao(DEFAULT_TIPOMEDICAO)
            .valormedicao(DEFAULT_VALORMEDICAO)
            .valormedicaoreajuste(DEFAULT_VALORMEDICAOREAJUSTE)
            .valorusmedicao(DEFAULT_VALORUSMEDICAO);
        return medicao;
    }

    @Before
    public void initTest() {
        medicao = createEntity(em);
    }

    @Test
    @Transactional
    public void createMedicao() throws Exception {
        int databaseSizeBeforeCreate = medicaoRepository.findAll().size();

        // Create the Medicao
        MedicaoDTO medicaoDTO = medicaoMapper.toDto(medicao);
        restMedicaoMockMvc.perform(post("/api/medicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medicaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Medicao in the database
        List<Medicao> medicaoList = medicaoRepository.findAll();
        assertThat(medicaoList).hasSize(databaseSizeBeforeCreate + 1);
        Medicao testMedicao = medicaoList.get(medicaoList.size() - 1);
        assertThat(testMedicao.isAjustecambio()).isEqualTo(DEFAULT_AJUSTECAMBIO);
        assertThat(testMedicao.getConferido()).isEqualTo(DEFAULT_CONFERIDO);
        assertThat(testMedicao.getIdmedicao()).isEqualTo(DEFAULT_IDMEDICAO);
        assertThat(testMedicao.getMes()).isEqualTo(DEFAULT_MES);
        assertThat(testMedicao.getNummedicao()).isEqualTo(DEFAULT_NUMMEDICAO);
        assertThat(testMedicao.getTipomedicao()).isEqualTo(DEFAULT_TIPOMEDICAO);
        assertThat(testMedicao.getValormedicao()).isEqualTo(DEFAULT_VALORMEDICAO);
        assertThat(testMedicao.getValormedicaoreajuste()).isEqualTo(DEFAULT_VALORMEDICAOREAJUSTE);
        assertThat(testMedicao.getValorusmedicao()).isEqualTo(DEFAULT_VALORUSMEDICAO);
    }

    @Test
    @Transactional
    public void createMedicaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = medicaoRepository.findAll().size();

        // Create the Medicao with an existing ID
        medicao.setId(1L);
        MedicaoDTO medicaoDTO = medicaoMapper.toDto(medicao);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMedicaoMockMvc.perform(post("/api/medicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medicaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Medicao> medicaoList = medicaoRepository.findAll();
        assertThat(medicaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMedicaos() throws Exception {
        // Initialize the database
        medicaoRepository.saveAndFlush(medicao);

        // Get all the medicaoList
        restMedicaoMockMvc.perform(get("/api/medicaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(medicao.getId().intValue())))
            .andExpect(jsonPath("$.[*].ajustecambio").value(hasItem(DEFAULT_AJUSTECAMBIO.booleanValue())))
            .andExpect(jsonPath("$.[*].conferido").value(hasItem(DEFAULT_CONFERIDO.toString())))
            .andExpect(jsonPath("$.[*].idmedicao").value(hasItem(DEFAULT_IDMEDICAO)))
            .andExpect(jsonPath("$.[*].mes").value(hasItem(sameInstant(DEFAULT_MES))))
            .andExpect(jsonPath("$.[*].nummedicao").value(hasItem(DEFAULT_NUMMEDICAO)))
            .andExpect(jsonPath("$.[*].tipomedicao").value(hasItem(DEFAULT_TIPOMEDICAO.toString())))
            .andExpect(jsonPath("$.[*].valormedicao").value(hasItem(DEFAULT_VALORMEDICAO.intValue())))
            .andExpect(jsonPath("$.[*].valormedicaoreajuste").value(hasItem(DEFAULT_VALORMEDICAOREAJUSTE.intValue())))
            .andExpect(jsonPath("$.[*].valorusmedicao").value(hasItem(DEFAULT_VALORUSMEDICAO.intValue())));
    }

    @Test
    @Transactional
    public void getMedicao() throws Exception {
        // Initialize the database
        medicaoRepository.saveAndFlush(medicao);

        // Get the medicao
        restMedicaoMockMvc.perform(get("/api/medicaos/{id}", medicao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(medicao.getId().intValue()))
            .andExpect(jsonPath("$.ajustecambio").value(DEFAULT_AJUSTECAMBIO.booleanValue()))
            .andExpect(jsonPath("$.conferido").value(DEFAULT_CONFERIDO.toString()))
            .andExpect(jsonPath("$.idmedicao").value(DEFAULT_IDMEDICAO))
            .andExpect(jsonPath("$.mes").value(sameInstant(DEFAULT_MES)))
            .andExpect(jsonPath("$.nummedicao").value(DEFAULT_NUMMEDICAO))
            .andExpect(jsonPath("$.tipomedicao").value(DEFAULT_TIPOMEDICAO.toString()))
            .andExpect(jsonPath("$.valormedicao").value(DEFAULT_VALORMEDICAO.intValue()))
            .andExpect(jsonPath("$.valormedicaoreajuste").value(DEFAULT_VALORMEDICAOREAJUSTE.intValue()))
            .andExpect(jsonPath("$.valorusmedicao").value(DEFAULT_VALORUSMEDICAO.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingMedicao() throws Exception {
        // Get the medicao
        restMedicaoMockMvc.perform(get("/api/medicaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMedicao() throws Exception {
        // Initialize the database
        medicaoRepository.saveAndFlush(medicao);
        int databaseSizeBeforeUpdate = medicaoRepository.findAll().size();

        // Update the medicao
        Medicao updatedMedicao = medicaoRepository.findOne(medicao.getId());
        updatedMedicao
            .ajustecambio(UPDATED_AJUSTECAMBIO)
            .conferido(UPDATED_CONFERIDO)
            .idmedicao(UPDATED_IDMEDICAO)
            .mes(UPDATED_MES)
            .nummedicao(UPDATED_NUMMEDICAO)
            .tipomedicao(UPDATED_TIPOMEDICAO)
            .valormedicao(UPDATED_VALORMEDICAO)
            .valormedicaoreajuste(UPDATED_VALORMEDICAOREAJUSTE)
            .valorusmedicao(UPDATED_VALORUSMEDICAO);
        MedicaoDTO medicaoDTO = medicaoMapper.toDto(updatedMedicao);

        restMedicaoMockMvc.perform(put("/api/medicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medicaoDTO)))
            .andExpect(status().isOk());

        // Validate the Medicao in the database
        List<Medicao> medicaoList = medicaoRepository.findAll();
        assertThat(medicaoList).hasSize(databaseSizeBeforeUpdate);
        Medicao testMedicao = medicaoList.get(medicaoList.size() - 1);
        assertThat(testMedicao.isAjustecambio()).isEqualTo(UPDATED_AJUSTECAMBIO);
        assertThat(testMedicao.getConferido()).isEqualTo(UPDATED_CONFERIDO);
        assertThat(testMedicao.getIdmedicao()).isEqualTo(UPDATED_IDMEDICAO);
        assertThat(testMedicao.getMes()).isEqualTo(UPDATED_MES);
        assertThat(testMedicao.getNummedicao()).isEqualTo(UPDATED_NUMMEDICAO);
        assertThat(testMedicao.getTipomedicao()).isEqualTo(UPDATED_TIPOMEDICAO);
        assertThat(testMedicao.getValormedicao()).isEqualTo(UPDATED_VALORMEDICAO);
        assertThat(testMedicao.getValormedicaoreajuste()).isEqualTo(UPDATED_VALORMEDICAOREAJUSTE);
        assertThat(testMedicao.getValorusmedicao()).isEqualTo(UPDATED_VALORUSMEDICAO);
    }

    @Test
    @Transactional
    public void updateNonExistingMedicao() throws Exception {
        int databaseSizeBeforeUpdate = medicaoRepository.findAll().size();

        // Create the Medicao
        MedicaoDTO medicaoDTO = medicaoMapper.toDto(medicao);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMedicaoMockMvc.perform(put("/api/medicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medicaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Medicao in the database
        List<Medicao> medicaoList = medicaoRepository.findAll();
        assertThat(medicaoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMedicao() throws Exception {
        // Initialize the database
        medicaoRepository.saveAndFlush(medicao);
        int databaseSizeBeforeDelete = medicaoRepository.findAll().size();

        // Get the medicao
        restMedicaoMockMvc.perform(delete("/api/medicaos/{id}", medicao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Medicao> medicaoList = medicaoRepository.findAll();
        assertThat(medicaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Medicao.class);
        Medicao medicao1 = new Medicao();
        medicao1.setId(1L);
        Medicao medicao2 = new Medicao();
        medicao2.setId(medicao1.getId());
        assertThat(medicao1).isEqualTo(medicao2);
        medicao2.setId(2L);
        assertThat(medicao1).isNotEqualTo(medicao2);
        medicao1.setId(null);
        assertThat(medicao1).isNotEqualTo(medicao2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MedicaoDTO.class);
        MedicaoDTO medicaoDTO1 = new MedicaoDTO();
        medicaoDTO1.setId(1L);
        MedicaoDTO medicaoDTO2 = new MedicaoDTO();
        assertThat(medicaoDTO1).isNotEqualTo(medicaoDTO2);
        medicaoDTO2.setId(medicaoDTO1.getId());
        assertThat(medicaoDTO1).isEqualTo(medicaoDTO2);
        medicaoDTO2.setId(2L);
        assertThat(medicaoDTO1).isNotEqualTo(medicaoDTO2);
        medicaoDTO1.setId(null);
        assertThat(medicaoDTO1).isNotEqualTo(medicaoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(medicaoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(medicaoMapper.fromId(null)).isNull();
    }
}
