package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Obra;
import br.com.homemade.repository.ObraRepository;
import br.com.homemade.service.dto.ObraDTO;
import br.com.homemade.service.mapper.ObraMapper;
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
 * Test class for the ObraResource REST controller.
 *
 * @see ObraResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class ObraResourceIntTest {

    private static final String DEFAULT_PENDENTE = "AAAAAAAAAA";
    private static final String UPDATED_PENDENTE = "BBBBBBBBBB";

    private static final String DEFAULT_MAP = "AAAAAAAAAA";
    private static final String UPDATED_MAP = "BBBBBBBBBB";

    private static final String DEFAULT_USER = "AAAAAAAAAA";
    private static final String UPDATED_USER = "BBBBBBBBBB";

    private static final String DEFAULT_TRACK = "AAAAAAAAAA";
    private static final String UPDATED_TRACK = "BBBBBBBBBB";

    private static final String DEFAULT_CERTIFICADO_MES = "AAAAAAAAAA";
    private static final String UPDATED_CERTIFICADO_MES = "BBBBBBBBBB";

    @Autowired
    private ObraRepository obraRepository;

    @Autowired
    private ObraMapper obraMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restObraMockMvc;

    private Obra obra;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ObraResource obraResource = new ObraResource(obraRepository, obraMapper);
        this.restObraMockMvc = MockMvcBuilders.standaloneSetup(obraResource)
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
    public static Obra createEntity(EntityManager em) {
        Obra obra = new Obra()
            .pendente(DEFAULT_PENDENTE)
            .map(DEFAULT_MAP)
            .user(DEFAULT_USER)
            .track(DEFAULT_TRACK)
            .certificadoMes(DEFAULT_CERTIFICADO_MES);
        return obra;
    }

    @Before
    public void initTest() {
        obra = createEntity(em);
    }

    @Test
    @Transactional
    public void createObra() throws Exception {
        int databaseSizeBeforeCreate = obraRepository.findAll().size();

        // Create the Obra
        ObraDTO obraDTO = obraMapper.toDto(obra);
        restObraMockMvc.perform(post("/api/obras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(obraDTO)))
            .andExpect(status().isCreated());

        // Validate the Obra in the database
        List<Obra> obraList = obraRepository.findAll();
        assertThat(obraList).hasSize(databaseSizeBeforeCreate + 1);
        Obra testObra = obraList.get(obraList.size() - 1);
        assertThat(testObra.getPendente()).isEqualTo(DEFAULT_PENDENTE);
        assertThat(testObra.getMap()).isEqualTo(DEFAULT_MAP);
        assertThat(testObra.getUser()).isEqualTo(DEFAULT_USER);
        assertThat(testObra.getTrack()).isEqualTo(DEFAULT_TRACK);
        assertThat(testObra.getCertificadoMes()).isEqualTo(DEFAULT_CERTIFICADO_MES);
    }

    @Test
    @Transactional
    public void createObraWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = obraRepository.findAll().size();

        // Create the Obra with an existing ID
        obra.setId(1L);
        ObraDTO obraDTO = obraMapper.toDto(obra);

        // An entity with an existing ID cannot be created, so this API call must fail
        restObraMockMvc.perform(post("/api/obras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(obraDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Obra> obraList = obraRepository.findAll();
        assertThat(obraList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllObras() throws Exception {
        // Initialize the database
        obraRepository.saveAndFlush(obra);

        // Get all the obraList
        restObraMockMvc.perform(get("/api/obras?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(obra.getId().intValue())))
            .andExpect(jsonPath("$.[*].pendente").value(hasItem(DEFAULT_PENDENTE.toString())))
            .andExpect(jsonPath("$.[*].map").value(hasItem(DEFAULT_MAP.toString())))
            .andExpect(jsonPath("$.[*].user").value(hasItem(DEFAULT_USER.toString())))
            .andExpect(jsonPath("$.[*].track").value(hasItem(DEFAULT_TRACK.toString())))
            .andExpect(jsonPath("$.[*].certificadoMes").value(hasItem(DEFAULT_CERTIFICADO_MES.toString())));
    }

    @Test
    @Transactional
    public void getObra() throws Exception {
        // Initialize the database
        obraRepository.saveAndFlush(obra);

        // Get the obra
        restObraMockMvc.perform(get("/api/obras/{id}", obra.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(obra.getId().intValue()))
            .andExpect(jsonPath("$.pendente").value(DEFAULT_PENDENTE.toString()))
            .andExpect(jsonPath("$.map").value(DEFAULT_MAP.toString()))
            .andExpect(jsonPath("$.user").value(DEFAULT_USER.toString()))
            .andExpect(jsonPath("$.track").value(DEFAULT_TRACK.toString()))
            .andExpect(jsonPath("$.certificadoMes").value(DEFAULT_CERTIFICADO_MES.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingObra() throws Exception {
        // Get the obra
        restObraMockMvc.perform(get("/api/obras/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateObra() throws Exception {
        // Initialize the database
        obraRepository.saveAndFlush(obra);
        int databaseSizeBeforeUpdate = obraRepository.findAll().size();

        // Update the obra
        Obra updatedObra = obraRepository.findOne(obra.getId());
        updatedObra
            .pendente(UPDATED_PENDENTE)
            .map(UPDATED_MAP)
            .user(UPDATED_USER)
            .track(UPDATED_TRACK)
            .certificadoMes(UPDATED_CERTIFICADO_MES);
        ObraDTO obraDTO = obraMapper.toDto(updatedObra);

        restObraMockMvc.perform(put("/api/obras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(obraDTO)))
            .andExpect(status().isOk());

        // Validate the Obra in the database
        List<Obra> obraList = obraRepository.findAll();
        assertThat(obraList).hasSize(databaseSizeBeforeUpdate);
        Obra testObra = obraList.get(obraList.size() - 1);
        assertThat(testObra.getPendente()).isEqualTo(UPDATED_PENDENTE);
        assertThat(testObra.getMap()).isEqualTo(UPDATED_MAP);
        assertThat(testObra.getUser()).isEqualTo(UPDATED_USER);
        assertThat(testObra.getTrack()).isEqualTo(UPDATED_TRACK);
        assertThat(testObra.getCertificadoMes()).isEqualTo(UPDATED_CERTIFICADO_MES);
    }

    @Test
    @Transactional
    public void updateNonExistingObra() throws Exception {
        int databaseSizeBeforeUpdate = obraRepository.findAll().size();

        // Create the Obra
        ObraDTO obraDTO = obraMapper.toDto(obra);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restObraMockMvc.perform(put("/api/obras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(obraDTO)))
            .andExpect(status().isCreated());

        // Validate the Obra in the database
        List<Obra> obraList = obraRepository.findAll();
        assertThat(obraList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteObra() throws Exception {
        // Initialize the database
        obraRepository.saveAndFlush(obra);
        int databaseSizeBeforeDelete = obraRepository.findAll().size();

        // Get the obra
        restObraMockMvc.perform(delete("/api/obras/{id}", obra.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Obra> obraList = obraRepository.findAll();
        assertThat(obraList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Obra.class);
        Obra obra1 = new Obra();
        obra1.setId(1L);
        Obra obra2 = new Obra();
        obra2.setId(obra1.getId());
        assertThat(obra1).isEqualTo(obra2);
        obra2.setId(2L);
        assertThat(obra1).isNotEqualTo(obra2);
        obra1.setId(null);
        assertThat(obra1).isNotEqualTo(obra2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ObraDTO.class);
        ObraDTO obraDTO1 = new ObraDTO();
        obraDTO1.setId(1L);
        ObraDTO obraDTO2 = new ObraDTO();
        assertThat(obraDTO1).isNotEqualTo(obraDTO2);
        obraDTO2.setId(obraDTO1.getId());
        assertThat(obraDTO1).isEqualTo(obraDTO2);
        obraDTO2.setId(2L);
        assertThat(obraDTO1).isNotEqualTo(obraDTO2);
        obraDTO1.setId(null);
        assertThat(obraDTO1).isNotEqualTo(obraDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(obraMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(obraMapper.fromId(null)).isNull();
    }
}
