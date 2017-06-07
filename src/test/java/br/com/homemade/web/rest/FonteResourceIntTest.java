package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Fonte;
import br.com.homemade.repository.FonteRepository;
import br.com.homemade.service.dto.FonteDTO;
import br.com.homemade.service.mapper.FonteMapper;
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
 * Test class for the FonteResource REST controller.
 *
 * @see FonteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class FonteResourceIntTest {

    @Autowired
    private FonteRepository fonteRepository;

    @Autowired
    private FonteMapper fonteMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFonteMockMvc;

    private Fonte fonte;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        FonteResource fonteResource = new FonteResource(fonteRepository, fonteMapper);
        this.restFonteMockMvc = MockMvcBuilders.standaloneSetup(fonteResource)
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
    public static Fonte createEntity(EntityManager em) {
        Fonte fonte = new Fonte();
        return fonte;
    }

    @Before
    public void initTest() {
        fonte = createEntity(em);
    }

    @Test
    @Transactional
    public void createFonte() throws Exception {
        int databaseSizeBeforeCreate = fonteRepository.findAll().size();

        // Create the Fonte
        FonteDTO fonteDTO = fonteMapper.toDto(fonte);
        restFonteMockMvc.perform(post("/api/fontes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fonteDTO)))
            .andExpect(status().isCreated());

        // Validate the Fonte in the database
        List<Fonte> fonteList = fonteRepository.findAll();
        assertThat(fonteList).hasSize(databaseSizeBeforeCreate + 1);
        Fonte testFonte = fonteList.get(fonteList.size() - 1);
    }

    @Test
    @Transactional
    public void createFonteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fonteRepository.findAll().size();

        // Create the Fonte with an existing ID
        fonte.setId(1L);
        FonteDTO fonteDTO = fonteMapper.toDto(fonte);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFonteMockMvc.perform(post("/api/fontes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fonteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Fonte> fonteList = fonteRepository.findAll();
        assertThat(fonteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFontes() throws Exception {
        // Initialize the database
        fonteRepository.saveAndFlush(fonte);

        // Get all the fonteList
        restFonteMockMvc.perform(get("/api/fontes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fonte.getId().intValue())));
    }

    @Test
    @Transactional
    public void getFonte() throws Exception {
        // Initialize the database
        fonteRepository.saveAndFlush(fonte);

        // Get the fonte
        restFonteMockMvc.perform(get("/api/fontes/{id}", fonte.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fonte.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingFonte() throws Exception {
        // Get the fonte
        restFonteMockMvc.perform(get("/api/fontes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFonte() throws Exception {
        // Initialize the database
        fonteRepository.saveAndFlush(fonte);
        int databaseSizeBeforeUpdate = fonteRepository.findAll().size();

        // Update the fonte
        Fonte updatedFonte = fonteRepository.findOne(fonte.getId());
        FonteDTO fonteDTO = fonteMapper.toDto(updatedFonte);

        restFonteMockMvc.perform(put("/api/fontes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fonteDTO)))
            .andExpect(status().isOk());

        // Validate the Fonte in the database
        List<Fonte> fonteList = fonteRepository.findAll();
        assertThat(fonteList).hasSize(databaseSizeBeforeUpdate);
        Fonte testFonte = fonteList.get(fonteList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingFonte() throws Exception {
        int databaseSizeBeforeUpdate = fonteRepository.findAll().size();

        // Create the Fonte
        FonteDTO fonteDTO = fonteMapper.toDto(fonte);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFonteMockMvc.perform(put("/api/fontes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fonteDTO)))
            .andExpect(status().isCreated());

        // Validate the Fonte in the database
        List<Fonte> fonteList = fonteRepository.findAll();
        assertThat(fonteList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFonte() throws Exception {
        // Initialize the database
        fonteRepository.saveAndFlush(fonte);
        int databaseSizeBeforeDelete = fonteRepository.findAll().size();

        // Get the fonte
        restFonteMockMvc.perform(delete("/api/fontes/{id}", fonte.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Fonte> fonteList = fonteRepository.findAll();
        assertThat(fonteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Fonte.class);
        Fonte fonte1 = new Fonte();
        fonte1.setId(1L);
        Fonte fonte2 = new Fonte();
        fonte2.setId(fonte1.getId());
        assertThat(fonte1).isEqualTo(fonte2);
        fonte2.setId(2L);
        assertThat(fonte1).isNotEqualTo(fonte2);
        fonte1.setId(null);
        assertThat(fonte1).isNotEqualTo(fonte2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FonteDTO.class);
        FonteDTO fonteDTO1 = new FonteDTO();
        fonteDTO1.setId(1L);
        FonteDTO fonteDTO2 = new FonteDTO();
        assertThat(fonteDTO1).isNotEqualTo(fonteDTO2);
        fonteDTO2.setId(fonteDTO1.getId());
        assertThat(fonteDTO1).isEqualTo(fonteDTO2);
        fonteDTO2.setId(2L);
        assertThat(fonteDTO1).isNotEqualTo(fonteDTO2);
        fonteDTO1.setId(null);
        assertThat(fonteDTO1).isNotEqualTo(fonteDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(fonteMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(fonteMapper.fromId(null)).isNull();
    }
}
