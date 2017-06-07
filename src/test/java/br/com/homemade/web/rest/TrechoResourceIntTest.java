package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Trecho;
import br.com.homemade.repository.TrechoRepository;
import br.com.homemade.service.dto.TrechoDTO;
import br.com.homemade.service.mapper.TrechoMapper;
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
 * Test class for the TrechoResource REST controller.
 *
 * @see TrechoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class TrechoResourceIntTest {

    private static final String DEFAULT_DEFIM = "AAAAAAAAAA";
    private static final String UPDATED_DEFIM = "BBBBBBBBBB";

    private static final String DEFAULT_DEINICIO = "AAAAAAAAAA";
    private static final String UPDATED_DEINICIO = "BBBBBBBBBB";

    private static final String DEFAULT_FIM = "AAAAAAAAAA";
    private static final String UPDATED_FIM = "BBBBBBBBBB";

    private static final String DEFAULT_INICIO = "AAAAAAAAAA";
    private static final String UPDATED_INICIO = "BBBBBBBBBB";

    private static final String DEFAULT_JURISDICAO = "AAAAAAAAAA";
    private static final String UPDATED_JURISDICAO = "BBBBBBBBBB";

    private static final String DEFAULT_KML = "AAAAAAAAAA";
    private static final String UPDATED_KML = "BBBBBBBBBB";

    private static final Double DEFAULT_NUEXTENSAO = 1D;
    private static final Double UPDATED_NUEXTENSAO = 2D;

    private static final Double DEFAULT_NUKMFINAL = 1D;
    private static final Double UPDATED_NUKMFINAL = 2D;

    private static final Double DEFAULT_NUKMINICIA = 1D;
    private static final Double UPDATED_NUKMINICIA = 2D;

    private static final String DEFAULT_RESPONSAVEL = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSAVEL = "BBBBBBBBBB";

    private static final String DEFAULT_SGPRE = "AAAAAAAAAA";
    private static final String UPDATED_SGPRE = "BBBBBBBBBB";

    private static final String DEFAULT_SGSITUACAO = "AAAAAAAAAA";
    private static final String UPDATED_SGSITUACAO = "BBBBBBBBBB";

    private static final String DEFAULT_TPREVEST = "AAAAAAAAAA";
    private static final String UPDATED_TPREVEST = "BBBBBBBBBB";

    @Autowired
    private TrechoRepository trechoRepository;

    @Autowired
    private TrechoMapper trechoMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTrechoMockMvc;

    private Trecho trecho;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TrechoResource trechoResource = new TrechoResource(trechoRepository, trechoMapper);
        this.restTrechoMockMvc = MockMvcBuilders.standaloneSetup(trechoResource)
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
    public static Trecho createEntity(EntityManager em) {
        Trecho trecho = new Trecho()
            .defim(DEFAULT_DEFIM)
            .deinicio(DEFAULT_DEINICIO)
            .fim(DEFAULT_FIM)
            .inicio(DEFAULT_INICIO)
            .jurisdicao(DEFAULT_JURISDICAO)
            .kml(DEFAULT_KML)
            .nuextensao(DEFAULT_NUEXTENSAO)
            .nukmfinal(DEFAULT_NUKMFINAL)
            .nukminicia(DEFAULT_NUKMINICIA)
            .responsavel(DEFAULT_RESPONSAVEL)
            .sgpre(DEFAULT_SGPRE)
            .sgsituacao(DEFAULT_SGSITUACAO)
            .tprevest(DEFAULT_TPREVEST);
        return trecho;
    }

    @Before
    public void initTest() {
        trecho = createEntity(em);
    }

    @Test
    @Transactional
    public void createTrecho() throws Exception {
        int databaseSizeBeforeCreate = trechoRepository.findAll().size();

        // Create the Trecho
        TrechoDTO trechoDTO = trechoMapper.toDto(trecho);
        restTrechoMockMvc.perform(post("/api/trechos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trechoDTO)))
            .andExpect(status().isCreated());

        // Validate the Trecho in the database
        List<Trecho> trechoList = trechoRepository.findAll();
        assertThat(trechoList).hasSize(databaseSizeBeforeCreate + 1);
        Trecho testTrecho = trechoList.get(trechoList.size() - 1);
        assertThat(testTrecho.getDefim()).isEqualTo(DEFAULT_DEFIM);
        assertThat(testTrecho.getDeinicio()).isEqualTo(DEFAULT_DEINICIO);
        assertThat(testTrecho.getFim()).isEqualTo(DEFAULT_FIM);
        assertThat(testTrecho.getInicio()).isEqualTo(DEFAULT_INICIO);
        assertThat(testTrecho.getJurisdicao()).isEqualTo(DEFAULT_JURISDICAO);
        assertThat(testTrecho.getKml()).isEqualTo(DEFAULT_KML);
        assertThat(testTrecho.getNuextensao()).isEqualTo(DEFAULT_NUEXTENSAO);
        assertThat(testTrecho.getNukmfinal()).isEqualTo(DEFAULT_NUKMFINAL);
        assertThat(testTrecho.getNukminicia()).isEqualTo(DEFAULT_NUKMINICIA);
        assertThat(testTrecho.getResponsavel()).isEqualTo(DEFAULT_RESPONSAVEL);
        assertThat(testTrecho.getSgpre()).isEqualTo(DEFAULT_SGPRE);
        assertThat(testTrecho.getSgsituacao()).isEqualTo(DEFAULT_SGSITUACAO);
        assertThat(testTrecho.getTprevest()).isEqualTo(DEFAULT_TPREVEST);
    }

    @Test
    @Transactional
    public void createTrechoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = trechoRepository.findAll().size();

        // Create the Trecho with an existing ID
        trecho.setId(1L);
        TrechoDTO trechoDTO = trechoMapper.toDto(trecho);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTrechoMockMvc.perform(post("/api/trechos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trechoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Trecho> trechoList = trechoRepository.findAll();
        assertThat(trechoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTrechos() throws Exception {
        // Initialize the database
        trechoRepository.saveAndFlush(trecho);

        // Get all the trechoList
        restTrechoMockMvc.perform(get("/api/trechos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(trecho.getId().intValue())))
            .andExpect(jsonPath("$.[*].defim").value(hasItem(DEFAULT_DEFIM.toString())))
            .andExpect(jsonPath("$.[*].deinicio").value(hasItem(DEFAULT_DEINICIO.toString())))
            .andExpect(jsonPath("$.[*].fim").value(hasItem(DEFAULT_FIM.toString())))
            .andExpect(jsonPath("$.[*].inicio").value(hasItem(DEFAULT_INICIO.toString())))
            .andExpect(jsonPath("$.[*].jurisdicao").value(hasItem(DEFAULT_JURISDICAO.toString())))
            .andExpect(jsonPath("$.[*].kml").value(hasItem(DEFAULT_KML.toString())))
            .andExpect(jsonPath("$.[*].nuextensao").value(hasItem(DEFAULT_NUEXTENSAO.doubleValue())))
            .andExpect(jsonPath("$.[*].nukmfinal").value(hasItem(DEFAULT_NUKMFINAL.doubleValue())))
            .andExpect(jsonPath("$.[*].nukminicia").value(hasItem(DEFAULT_NUKMINICIA.doubleValue())))
            .andExpect(jsonPath("$.[*].responsavel").value(hasItem(DEFAULT_RESPONSAVEL.toString())))
            .andExpect(jsonPath("$.[*].sgpre").value(hasItem(DEFAULT_SGPRE.toString())))
            .andExpect(jsonPath("$.[*].sgsituacao").value(hasItem(DEFAULT_SGSITUACAO.toString())))
            .andExpect(jsonPath("$.[*].tprevest").value(hasItem(DEFAULT_TPREVEST.toString())));
    }

    @Test
    @Transactional
    public void getTrecho() throws Exception {
        // Initialize the database
        trechoRepository.saveAndFlush(trecho);

        // Get the trecho
        restTrechoMockMvc.perform(get("/api/trechos/{id}", trecho.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(trecho.getId().intValue()))
            .andExpect(jsonPath("$.defim").value(DEFAULT_DEFIM.toString()))
            .andExpect(jsonPath("$.deinicio").value(DEFAULT_DEINICIO.toString()))
            .andExpect(jsonPath("$.fim").value(DEFAULT_FIM.toString()))
            .andExpect(jsonPath("$.inicio").value(DEFAULT_INICIO.toString()))
            .andExpect(jsonPath("$.jurisdicao").value(DEFAULT_JURISDICAO.toString()))
            .andExpect(jsonPath("$.kml").value(DEFAULT_KML.toString()))
            .andExpect(jsonPath("$.nuextensao").value(DEFAULT_NUEXTENSAO.doubleValue()))
            .andExpect(jsonPath("$.nukmfinal").value(DEFAULT_NUKMFINAL.doubleValue()))
            .andExpect(jsonPath("$.nukminicia").value(DEFAULT_NUKMINICIA.doubleValue()))
            .andExpect(jsonPath("$.responsavel").value(DEFAULT_RESPONSAVEL.toString()))
            .andExpect(jsonPath("$.sgpre").value(DEFAULT_SGPRE.toString()))
            .andExpect(jsonPath("$.sgsituacao").value(DEFAULT_SGSITUACAO.toString()))
            .andExpect(jsonPath("$.tprevest").value(DEFAULT_TPREVEST.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTrecho() throws Exception {
        // Get the trecho
        restTrechoMockMvc.perform(get("/api/trechos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTrecho() throws Exception {
        // Initialize the database
        trechoRepository.saveAndFlush(trecho);
        int databaseSizeBeforeUpdate = trechoRepository.findAll().size();

        // Update the trecho
        Trecho updatedTrecho = trechoRepository.findOne(trecho.getId());
        updatedTrecho
            .defim(UPDATED_DEFIM)
            .deinicio(UPDATED_DEINICIO)
            .fim(UPDATED_FIM)
            .inicio(UPDATED_INICIO)
            .jurisdicao(UPDATED_JURISDICAO)
            .kml(UPDATED_KML)
            .nuextensao(UPDATED_NUEXTENSAO)
            .nukmfinal(UPDATED_NUKMFINAL)
            .nukminicia(UPDATED_NUKMINICIA)
            .responsavel(UPDATED_RESPONSAVEL)
            .sgpre(UPDATED_SGPRE)
            .sgsituacao(UPDATED_SGSITUACAO)
            .tprevest(UPDATED_TPREVEST);
        TrechoDTO trechoDTO = trechoMapper.toDto(updatedTrecho);

        restTrechoMockMvc.perform(put("/api/trechos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trechoDTO)))
            .andExpect(status().isOk());

        // Validate the Trecho in the database
        List<Trecho> trechoList = trechoRepository.findAll();
        assertThat(trechoList).hasSize(databaseSizeBeforeUpdate);
        Trecho testTrecho = trechoList.get(trechoList.size() - 1);
        assertThat(testTrecho.getDefim()).isEqualTo(UPDATED_DEFIM);
        assertThat(testTrecho.getDeinicio()).isEqualTo(UPDATED_DEINICIO);
        assertThat(testTrecho.getFim()).isEqualTo(UPDATED_FIM);
        assertThat(testTrecho.getInicio()).isEqualTo(UPDATED_INICIO);
        assertThat(testTrecho.getJurisdicao()).isEqualTo(UPDATED_JURISDICAO);
        assertThat(testTrecho.getKml()).isEqualTo(UPDATED_KML);
        assertThat(testTrecho.getNuextensao()).isEqualTo(UPDATED_NUEXTENSAO);
        assertThat(testTrecho.getNukmfinal()).isEqualTo(UPDATED_NUKMFINAL);
        assertThat(testTrecho.getNukminicia()).isEqualTo(UPDATED_NUKMINICIA);
        assertThat(testTrecho.getResponsavel()).isEqualTo(UPDATED_RESPONSAVEL);
        assertThat(testTrecho.getSgpre()).isEqualTo(UPDATED_SGPRE);
        assertThat(testTrecho.getSgsituacao()).isEqualTo(UPDATED_SGSITUACAO);
        assertThat(testTrecho.getTprevest()).isEqualTo(UPDATED_TPREVEST);
    }

    @Test
    @Transactional
    public void updateNonExistingTrecho() throws Exception {
        int databaseSizeBeforeUpdate = trechoRepository.findAll().size();

        // Create the Trecho
        TrechoDTO trechoDTO = trechoMapper.toDto(trecho);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTrechoMockMvc.perform(put("/api/trechos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trechoDTO)))
            .andExpect(status().isCreated());

        // Validate the Trecho in the database
        List<Trecho> trechoList = trechoRepository.findAll();
        assertThat(trechoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTrecho() throws Exception {
        // Initialize the database
        trechoRepository.saveAndFlush(trecho);
        int databaseSizeBeforeDelete = trechoRepository.findAll().size();

        // Get the trecho
        restTrechoMockMvc.perform(delete("/api/trechos/{id}", trecho.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Trecho> trechoList = trechoRepository.findAll();
        assertThat(trechoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Trecho.class);
        Trecho trecho1 = new Trecho();
        trecho1.setId(1L);
        Trecho trecho2 = new Trecho();
        trecho2.setId(trecho1.getId());
        assertThat(trecho1).isEqualTo(trecho2);
        trecho2.setId(2L);
        assertThat(trecho1).isNotEqualTo(trecho2);
        trecho1.setId(null);
        assertThat(trecho1).isNotEqualTo(trecho2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TrechoDTO.class);
        TrechoDTO trechoDTO1 = new TrechoDTO();
        trechoDTO1.setId(1L);
        TrechoDTO trechoDTO2 = new TrechoDTO();
        assertThat(trechoDTO1).isNotEqualTo(trechoDTO2);
        trechoDTO2.setId(trechoDTO1.getId());
        assertThat(trechoDTO1).isEqualTo(trechoDTO2);
        trechoDTO2.setId(2L);
        assertThat(trechoDTO1).isNotEqualTo(trechoDTO2);
        trechoDTO1.setId(null);
        assertThat(trechoDTO1).isNotEqualTo(trechoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(trechoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(trechoMapper.fromId(null)).isNull();
    }
}
