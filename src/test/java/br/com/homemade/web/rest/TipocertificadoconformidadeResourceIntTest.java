package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Tipocertificadoconformidade;
import br.com.homemade.repository.TipocertificadoconformidadeRepository;
import br.com.homemade.service.dto.TipocertificadoconformidadeDTO;
import br.com.homemade.service.mapper.TipocertificadoconformidadeMapper;
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
 * Test class for the TipocertificadoconformidadeResource REST controller.
 *
 * @see TipocertificadoconformidadeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class TipocertificadoconformidadeResourceIntTest {

    @Autowired
    private TipocertificadoconformidadeRepository tipocertificadoconformidadeRepository;

    @Autowired
    private TipocertificadoconformidadeMapper tipocertificadoconformidadeMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTipocertificadoconformidadeMockMvc;

    private Tipocertificadoconformidade tipocertificadoconformidade;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TipocertificadoconformidadeResource tipocertificadoconformidadeResource = new TipocertificadoconformidadeResource(tipocertificadoconformidadeRepository, tipocertificadoconformidadeMapper);
        this.restTipocertificadoconformidadeMockMvc = MockMvcBuilders.standaloneSetup(tipocertificadoconformidadeResource)
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
    public static Tipocertificadoconformidade createEntity(EntityManager em) {
        Tipocertificadoconformidade tipocertificadoconformidade = new Tipocertificadoconformidade();
        return tipocertificadoconformidade;
    }

    @Before
    public void initTest() {
        tipocertificadoconformidade = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipocertificadoconformidade() throws Exception {
        int databaseSizeBeforeCreate = tipocertificadoconformidadeRepository.findAll().size();

        // Create the Tipocertificadoconformidade
        TipocertificadoconformidadeDTO tipocertificadoconformidadeDTO = tipocertificadoconformidadeMapper.toDto(tipocertificadoconformidade);
        restTipocertificadoconformidadeMockMvc.perform(post("/api/tipocertificadoconformidades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipocertificadoconformidadeDTO)))
            .andExpect(status().isCreated());

        // Validate the Tipocertificadoconformidade in the database
        List<Tipocertificadoconformidade> tipocertificadoconformidadeList = tipocertificadoconformidadeRepository.findAll();
        assertThat(tipocertificadoconformidadeList).hasSize(databaseSizeBeforeCreate + 1);
        Tipocertificadoconformidade testTipocertificadoconformidade = tipocertificadoconformidadeList.get(tipocertificadoconformidadeList.size() - 1);
    }

    @Test
    @Transactional
    public void createTipocertificadoconformidadeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipocertificadoconformidadeRepository.findAll().size();

        // Create the Tipocertificadoconformidade with an existing ID
        tipocertificadoconformidade.setId(1L);
        TipocertificadoconformidadeDTO tipocertificadoconformidadeDTO = tipocertificadoconformidadeMapper.toDto(tipocertificadoconformidade);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipocertificadoconformidadeMockMvc.perform(post("/api/tipocertificadoconformidades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipocertificadoconformidadeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Tipocertificadoconformidade> tipocertificadoconformidadeList = tipocertificadoconformidadeRepository.findAll();
        assertThat(tipocertificadoconformidadeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTipocertificadoconformidades() throws Exception {
        // Initialize the database
        tipocertificadoconformidadeRepository.saveAndFlush(tipocertificadoconformidade);

        // Get all the tipocertificadoconformidadeList
        restTipocertificadoconformidadeMockMvc.perform(get("/api/tipocertificadoconformidades?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipocertificadoconformidade.getId().intValue())));
    }

    @Test
    @Transactional
    public void getTipocertificadoconformidade() throws Exception {
        // Initialize the database
        tipocertificadoconformidadeRepository.saveAndFlush(tipocertificadoconformidade);

        // Get the tipocertificadoconformidade
        restTipocertificadoconformidadeMockMvc.perform(get("/api/tipocertificadoconformidades/{id}", tipocertificadoconformidade.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipocertificadoconformidade.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTipocertificadoconformidade() throws Exception {
        // Get the tipocertificadoconformidade
        restTipocertificadoconformidadeMockMvc.perform(get("/api/tipocertificadoconformidades/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipocertificadoconformidade() throws Exception {
        // Initialize the database
        tipocertificadoconformidadeRepository.saveAndFlush(tipocertificadoconformidade);
        int databaseSizeBeforeUpdate = tipocertificadoconformidadeRepository.findAll().size();

        // Update the tipocertificadoconformidade
        Tipocertificadoconformidade updatedTipocertificadoconformidade = tipocertificadoconformidadeRepository.findOne(tipocertificadoconformidade.getId());
        TipocertificadoconformidadeDTO tipocertificadoconformidadeDTO = tipocertificadoconformidadeMapper.toDto(updatedTipocertificadoconformidade);

        restTipocertificadoconformidadeMockMvc.perform(put("/api/tipocertificadoconformidades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipocertificadoconformidadeDTO)))
            .andExpect(status().isOk());

        // Validate the Tipocertificadoconformidade in the database
        List<Tipocertificadoconformidade> tipocertificadoconformidadeList = tipocertificadoconformidadeRepository.findAll();
        assertThat(tipocertificadoconformidadeList).hasSize(databaseSizeBeforeUpdate);
        Tipocertificadoconformidade testTipocertificadoconformidade = tipocertificadoconformidadeList.get(tipocertificadoconformidadeList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingTipocertificadoconformidade() throws Exception {
        int databaseSizeBeforeUpdate = tipocertificadoconformidadeRepository.findAll().size();

        // Create the Tipocertificadoconformidade
        TipocertificadoconformidadeDTO tipocertificadoconformidadeDTO = tipocertificadoconformidadeMapper.toDto(tipocertificadoconformidade);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTipocertificadoconformidadeMockMvc.perform(put("/api/tipocertificadoconformidades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipocertificadoconformidadeDTO)))
            .andExpect(status().isCreated());

        // Validate the Tipocertificadoconformidade in the database
        List<Tipocertificadoconformidade> tipocertificadoconformidadeList = tipocertificadoconformidadeRepository.findAll();
        assertThat(tipocertificadoconformidadeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTipocertificadoconformidade() throws Exception {
        // Initialize the database
        tipocertificadoconformidadeRepository.saveAndFlush(tipocertificadoconformidade);
        int databaseSizeBeforeDelete = tipocertificadoconformidadeRepository.findAll().size();

        // Get the tipocertificadoconformidade
        restTipocertificadoconformidadeMockMvc.perform(delete("/api/tipocertificadoconformidades/{id}", tipocertificadoconformidade.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Tipocertificadoconformidade> tipocertificadoconformidadeList = tipocertificadoconformidadeRepository.findAll();
        assertThat(tipocertificadoconformidadeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tipocertificadoconformidade.class);
        Tipocertificadoconformidade tipocertificadoconformidade1 = new Tipocertificadoconformidade();
        tipocertificadoconformidade1.setId(1L);
        Tipocertificadoconformidade tipocertificadoconformidade2 = new Tipocertificadoconformidade();
        tipocertificadoconformidade2.setId(tipocertificadoconformidade1.getId());
        assertThat(tipocertificadoconformidade1).isEqualTo(tipocertificadoconformidade2);
        tipocertificadoconformidade2.setId(2L);
        assertThat(tipocertificadoconformidade1).isNotEqualTo(tipocertificadoconformidade2);
        tipocertificadoconformidade1.setId(null);
        assertThat(tipocertificadoconformidade1).isNotEqualTo(tipocertificadoconformidade2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipocertificadoconformidadeDTO.class);
        TipocertificadoconformidadeDTO tipocertificadoconformidadeDTO1 = new TipocertificadoconformidadeDTO();
        tipocertificadoconformidadeDTO1.setId(1L);
        TipocertificadoconformidadeDTO tipocertificadoconformidadeDTO2 = new TipocertificadoconformidadeDTO();
        assertThat(tipocertificadoconformidadeDTO1).isNotEqualTo(tipocertificadoconformidadeDTO2);
        tipocertificadoconformidadeDTO2.setId(tipocertificadoconformidadeDTO1.getId());
        assertThat(tipocertificadoconformidadeDTO1).isEqualTo(tipocertificadoconformidadeDTO2);
        tipocertificadoconformidadeDTO2.setId(2L);
        assertThat(tipocertificadoconformidadeDTO1).isNotEqualTo(tipocertificadoconformidadeDTO2);
        tipocertificadoconformidadeDTO1.setId(null);
        assertThat(tipocertificadoconformidadeDTO1).isNotEqualTo(tipocertificadoconformidadeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(tipocertificadoconformidadeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(tipocertificadoconformidadeMapper.fromId(null)).isNull();
    }
}
