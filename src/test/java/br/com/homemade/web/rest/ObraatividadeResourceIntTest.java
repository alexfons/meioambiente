package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Obraatividade;
import br.com.homemade.repository.ObraatividadeRepository;
import br.com.homemade.service.dto.ObraatividadeDTO;
import br.com.homemade.service.mapper.ObraatividadeMapper;
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
 * Test class for the ObraatividadeResource REST controller.
 *
 * @see ObraatividadeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class ObraatividadeResourceIntTest {

    @Autowired
    private ObraatividadeRepository obraatividadeRepository;

    @Autowired
    private ObraatividadeMapper obraatividadeMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restObraatividadeMockMvc;

    private Obraatividade obraatividade;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ObraatividadeResource obraatividadeResource = new ObraatividadeResource(obraatividadeRepository, obraatividadeMapper);
        this.restObraatividadeMockMvc = MockMvcBuilders.standaloneSetup(obraatividadeResource)
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
    public static Obraatividade createEntity(EntityManager em) {
        Obraatividade obraatividade = new Obraatividade();
        return obraatividade;
    }

    @Before
    public void initTest() {
        obraatividade = createEntity(em);
    }

    @Test
    @Transactional
    public void createObraatividade() throws Exception {
        int databaseSizeBeforeCreate = obraatividadeRepository.findAll().size();

        // Create the Obraatividade
        ObraatividadeDTO obraatividadeDTO = obraatividadeMapper.toDto(obraatividade);
        restObraatividadeMockMvc.perform(post("/api/obraatividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(obraatividadeDTO)))
            .andExpect(status().isCreated());

        // Validate the Obraatividade in the database
        List<Obraatividade> obraatividadeList = obraatividadeRepository.findAll();
        assertThat(obraatividadeList).hasSize(databaseSizeBeforeCreate + 1);
        Obraatividade testObraatividade = obraatividadeList.get(obraatividadeList.size() - 1);
    }

    @Test
    @Transactional
    public void createObraatividadeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = obraatividadeRepository.findAll().size();

        // Create the Obraatividade with an existing ID
        obraatividade.setId(1L);
        ObraatividadeDTO obraatividadeDTO = obraatividadeMapper.toDto(obraatividade);

        // An entity with an existing ID cannot be created, so this API call must fail
        restObraatividadeMockMvc.perform(post("/api/obraatividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(obraatividadeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Obraatividade> obraatividadeList = obraatividadeRepository.findAll();
        assertThat(obraatividadeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllObraatividades() throws Exception {
        // Initialize the database
        obraatividadeRepository.saveAndFlush(obraatividade);

        // Get all the obraatividadeList
        restObraatividadeMockMvc.perform(get("/api/obraatividades?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(obraatividade.getId().intValue())));
    }

    @Test
    @Transactional
    public void getObraatividade() throws Exception {
        // Initialize the database
        obraatividadeRepository.saveAndFlush(obraatividade);

        // Get the obraatividade
        restObraatividadeMockMvc.perform(get("/api/obraatividades/{id}", obraatividade.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(obraatividade.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingObraatividade() throws Exception {
        // Get the obraatividade
        restObraatividadeMockMvc.perform(get("/api/obraatividades/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateObraatividade() throws Exception {
        // Initialize the database
        obraatividadeRepository.saveAndFlush(obraatividade);
        int databaseSizeBeforeUpdate = obraatividadeRepository.findAll().size();

        // Update the obraatividade
        Obraatividade updatedObraatividade = obraatividadeRepository.findOne(obraatividade.getId());
        ObraatividadeDTO obraatividadeDTO = obraatividadeMapper.toDto(updatedObraatividade);

        restObraatividadeMockMvc.perform(put("/api/obraatividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(obraatividadeDTO)))
            .andExpect(status().isOk());

        // Validate the Obraatividade in the database
        List<Obraatividade> obraatividadeList = obraatividadeRepository.findAll();
        assertThat(obraatividadeList).hasSize(databaseSizeBeforeUpdate);
        Obraatividade testObraatividade = obraatividadeList.get(obraatividadeList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingObraatividade() throws Exception {
        int databaseSizeBeforeUpdate = obraatividadeRepository.findAll().size();

        // Create the Obraatividade
        ObraatividadeDTO obraatividadeDTO = obraatividadeMapper.toDto(obraatividade);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restObraatividadeMockMvc.perform(put("/api/obraatividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(obraatividadeDTO)))
            .andExpect(status().isCreated());

        // Validate the Obraatividade in the database
        List<Obraatividade> obraatividadeList = obraatividadeRepository.findAll();
        assertThat(obraatividadeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteObraatividade() throws Exception {
        // Initialize the database
        obraatividadeRepository.saveAndFlush(obraatividade);
        int databaseSizeBeforeDelete = obraatividadeRepository.findAll().size();

        // Get the obraatividade
        restObraatividadeMockMvc.perform(delete("/api/obraatividades/{id}", obraatividade.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Obraatividade> obraatividadeList = obraatividadeRepository.findAll();
        assertThat(obraatividadeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Obraatividade.class);
        Obraatividade obraatividade1 = new Obraatividade();
        obraatividade1.setId(1L);
        Obraatividade obraatividade2 = new Obraatividade();
        obraatividade2.setId(obraatividade1.getId());
        assertThat(obraatividade1).isEqualTo(obraatividade2);
        obraatividade2.setId(2L);
        assertThat(obraatividade1).isNotEqualTo(obraatividade2);
        obraatividade1.setId(null);
        assertThat(obraatividade1).isNotEqualTo(obraatividade2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ObraatividadeDTO.class);
        ObraatividadeDTO obraatividadeDTO1 = new ObraatividadeDTO();
        obraatividadeDTO1.setId(1L);
        ObraatividadeDTO obraatividadeDTO2 = new ObraatividadeDTO();
        assertThat(obraatividadeDTO1).isNotEqualTo(obraatividadeDTO2);
        obraatividadeDTO2.setId(obraatividadeDTO1.getId());
        assertThat(obraatividadeDTO1).isEqualTo(obraatividadeDTO2);
        obraatividadeDTO2.setId(2L);
        assertThat(obraatividadeDTO1).isNotEqualTo(obraatividadeDTO2);
        obraatividadeDTO1.setId(null);
        assertThat(obraatividadeDTO1).isNotEqualTo(obraatividadeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(obraatividadeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(obraatividadeMapper.fromId(null)).isNull();
    }
}
