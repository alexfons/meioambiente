package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Tipolicenca;
import br.com.homemade.repository.TipolicencaRepository;
import br.com.homemade.service.dto.TipolicencaDTO;
import br.com.homemade.service.mapper.TipolicencaMapper;
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
 * Test class for the TipolicencaResource REST controller.
 *
 * @see TipolicencaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class TipolicencaResourceIntTest {

    @Autowired
    private TipolicencaRepository tipolicencaRepository;

    @Autowired
    private TipolicencaMapper tipolicencaMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTipolicencaMockMvc;

    private Tipolicenca tipolicenca;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TipolicencaResource tipolicencaResource = new TipolicencaResource(tipolicencaRepository, tipolicencaMapper);
        this.restTipolicencaMockMvc = MockMvcBuilders.standaloneSetup(tipolicencaResource)
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
    public static Tipolicenca createEntity(EntityManager em) {
        Tipolicenca tipolicenca = new Tipolicenca();
        return tipolicenca;
    }

    @Before
    public void initTest() {
        tipolicenca = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipolicenca() throws Exception {
        int databaseSizeBeforeCreate = tipolicencaRepository.findAll().size();

        // Create the Tipolicenca
        TipolicencaDTO tipolicencaDTO = tipolicencaMapper.toDto(tipolicenca);
        restTipolicencaMockMvc.perform(post("/api/tipolicencas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipolicencaDTO)))
            .andExpect(status().isCreated());

        // Validate the Tipolicenca in the database
        List<Tipolicenca> tipolicencaList = tipolicencaRepository.findAll();
        assertThat(tipolicencaList).hasSize(databaseSizeBeforeCreate + 1);
        Tipolicenca testTipolicenca = tipolicencaList.get(tipolicencaList.size() - 1);
    }

    @Test
    @Transactional
    public void createTipolicencaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipolicencaRepository.findAll().size();

        // Create the Tipolicenca with an existing ID
        tipolicenca.setId(1L);
        TipolicencaDTO tipolicencaDTO = tipolicencaMapper.toDto(tipolicenca);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipolicencaMockMvc.perform(post("/api/tipolicencas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipolicencaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Tipolicenca> tipolicencaList = tipolicencaRepository.findAll();
        assertThat(tipolicencaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTipolicencas() throws Exception {
        // Initialize the database
        tipolicencaRepository.saveAndFlush(tipolicenca);

        // Get all the tipolicencaList
        restTipolicencaMockMvc.perform(get("/api/tipolicencas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipolicenca.getId().intValue())));
    }

    @Test
    @Transactional
    public void getTipolicenca() throws Exception {
        // Initialize the database
        tipolicencaRepository.saveAndFlush(tipolicenca);

        // Get the tipolicenca
        restTipolicencaMockMvc.perform(get("/api/tipolicencas/{id}", tipolicenca.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipolicenca.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTipolicenca() throws Exception {
        // Get the tipolicenca
        restTipolicencaMockMvc.perform(get("/api/tipolicencas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipolicenca() throws Exception {
        // Initialize the database
        tipolicencaRepository.saveAndFlush(tipolicenca);
        int databaseSizeBeforeUpdate = tipolicencaRepository.findAll().size();

        // Update the tipolicenca
        Tipolicenca updatedTipolicenca = tipolicencaRepository.findOne(tipolicenca.getId());
        TipolicencaDTO tipolicencaDTO = tipolicencaMapper.toDto(updatedTipolicenca);

        restTipolicencaMockMvc.perform(put("/api/tipolicencas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipolicencaDTO)))
            .andExpect(status().isOk());

        // Validate the Tipolicenca in the database
        List<Tipolicenca> tipolicencaList = tipolicencaRepository.findAll();
        assertThat(tipolicencaList).hasSize(databaseSizeBeforeUpdate);
        Tipolicenca testTipolicenca = tipolicencaList.get(tipolicencaList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingTipolicenca() throws Exception {
        int databaseSizeBeforeUpdate = tipolicencaRepository.findAll().size();

        // Create the Tipolicenca
        TipolicencaDTO tipolicencaDTO = tipolicencaMapper.toDto(tipolicenca);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTipolicencaMockMvc.perform(put("/api/tipolicencas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipolicencaDTO)))
            .andExpect(status().isCreated());

        // Validate the Tipolicenca in the database
        List<Tipolicenca> tipolicencaList = tipolicencaRepository.findAll();
        assertThat(tipolicencaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTipolicenca() throws Exception {
        // Initialize the database
        tipolicencaRepository.saveAndFlush(tipolicenca);
        int databaseSizeBeforeDelete = tipolicencaRepository.findAll().size();

        // Get the tipolicenca
        restTipolicencaMockMvc.perform(delete("/api/tipolicencas/{id}", tipolicenca.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Tipolicenca> tipolicencaList = tipolicencaRepository.findAll();
        assertThat(tipolicencaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tipolicenca.class);
        Tipolicenca tipolicenca1 = new Tipolicenca();
        tipolicenca1.setId(1L);
        Tipolicenca tipolicenca2 = new Tipolicenca();
        tipolicenca2.setId(tipolicenca1.getId());
        assertThat(tipolicenca1).isEqualTo(tipolicenca2);
        tipolicenca2.setId(2L);
        assertThat(tipolicenca1).isNotEqualTo(tipolicenca2);
        tipolicenca1.setId(null);
        assertThat(tipolicenca1).isNotEqualTo(tipolicenca2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipolicencaDTO.class);
        TipolicencaDTO tipolicencaDTO1 = new TipolicencaDTO();
        tipolicencaDTO1.setId(1L);
        TipolicencaDTO tipolicencaDTO2 = new TipolicencaDTO();
        assertThat(tipolicencaDTO1).isNotEqualTo(tipolicencaDTO2);
        tipolicencaDTO2.setId(tipolicencaDTO1.getId());
        assertThat(tipolicencaDTO1).isEqualTo(tipolicencaDTO2);
        tipolicencaDTO2.setId(2L);
        assertThat(tipolicencaDTO1).isNotEqualTo(tipolicencaDTO2);
        tipolicencaDTO1.setId(null);
        assertThat(tipolicencaDTO1).isNotEqualTo(tipolicencaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(tipolicencaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(tipolicencaMapper.fromId(null)).isNull();
    }
}
