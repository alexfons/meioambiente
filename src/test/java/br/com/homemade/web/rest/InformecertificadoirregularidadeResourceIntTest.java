package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Informecertificadoirregularidade;
import br.com.homemade.repository.InformecertificadoirregularidadeRepository;
import br.com.homemade.service.dto.InformecertificadoirregularidadeDTO;
import br.com.homemade.service.mapper.InformecertificadoirregularidadeMapper;
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
 * Test class for the InformecertificadoirregularidadeResource REST controller.
 *
 * @see InformecertificadoirregularidadeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class InformecertificadoirregularidadeResourceIntTest {

    @Autowired
    private InformecertificadoirregularidadeRepository informecertificadoirregularidadeRepository;

    @Autowired
    private InformecertificadoirregularidadeMapper informecertificadoirregularidadeMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restInformecertificadoirregularidadeMockMvc;

    private Informecertificadoirregularidade informecertificadoirregularidade;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        InformecertificadoirregularidadeResource informecertificadoirregularidadeResource = new InformecertificadoirregularidadeResource(informecertificadoirregularidadeRepository, informecertificadoirregularidadeMapper);
        this.restInformecertificadoirregularidadeMockMvc = MockMvcBuilders.standaloneSetup(informecertificadoirregularidadeResource)
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
    public static Informecertificadoirregularidade createEntity(EntityManager em) {
        Informecertificadoirregularidade informecertificadoirregularidade = new Informecertificadoirregularidade();
        return informecertificadoirregularidade;
    }

    @Before
    public void initTest() {
        informecertificadoirregularidade = createEntity(em);
    }

    @Test
    @Transactional
    public void createInformecertificadoirregularidade() throws Exception {
        int databaseSizeBeforeCreate = informecertificadoirregularidadeRepository.findAll().size();

        // Create the Informecertificadoirregularidade
        InformecertificadoirregularidadeDTO informecertificadoirregularidadeDTO = informecertificadoirregularidadeMapper.toDto(informecertificadoirregularidade);
        restInformecertificadoirregularidadeMockMvc.perform(post("/api/informecertificadoirregularidades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(informecertificadoirregularidadeDTO)))
            .andExpect(status().isCreated());

        // Validate the Informecertificadoirregularidade in the database
        List<Informecertificadoirregularidade> informecertificadoirregularidadeList = informecertificadoirregularidadeRepository.findAll();
        assertThat(informecertificadoirregularidadeList).hasSize(databaseSizeBeforeCreate + 1);
        Informecertificadoirregularidade testInformecertificadoirregularidade = informecertificadoirregularidadeList.get(informecertificadoirregularidadeList.size() - 1);
    }

    @Test
    @Transactional
    public void createInformecertificadoirregularidadeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = informecertificadoirregularidadeRepository.findAll().size();

        // Create the Informecertificadoirregularidade with an existing ID
        informecertificadoirregularidade.setId(1L);
        InformecertificadoirregularidadeDTO informecertificadoirregularidadeDTO = informecertificadoirregularidadeMapper.toDto(informecertificadoirregularidade);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInformecertificadoirregularidadeMockMvc.perform(post("/api/informecertificadoirregularidades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(informecertificadoirregularidadeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Informecertificadoirregularidade> informecertificadoirregularidadeList = informecertificadoirregularidadeRepository.findAll();
        assertThat(informecertificadoirregularidadeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllInformecertificadoirregularidades() throws Exception {
        // Initialize the database
        informecertificadoirregularidadeRepository.saveAndFlush(informecertificadoirregularidade);

        // Get all the informecertificadoirregularidadeList
        restInformecertificadoirregularidadeMockMvc.perform(get("/api/informecertificadoirregularidades?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(informecertificadoirregularidade.getId().intValue())));
    }

    @Test
    @Transactional
    public void getInformecertificadoirregularidade() throws Exception {
        // Initialize the database
        informecertificadoirregularidadeRepository.saveAndFlush(informecertificadoirregularidade);

        // Get the informecertificadoirregularidade
        restInformecertificadoirregularidadeMockMvc.perform(get("/api/informecertificadoirregularidades/{id}", informecertificadoirregularidade.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(informecertificadoirregularidade.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingInformecertificadoirregularidade() throws Exception {
        // Get the informecertificadoirregularidade
        restInformecertificadoirregularidadeMockMvc.perform(get("/api/informecertificadoirregularidades/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInformecertificadoirregularidade() throws Exception {
        // Initialize the database
        informecertificadoirregularidadeRepository.saveAndFlush(informecertificadoirregularidade);
        int databaseSizeBeforeUpdate = informecertificadoirregularidadeRepository.findAll().size();

        // Update the informecertificadoirregularidade
        Informecertificadoirregularidade updatedInformecertificadoirregularidade = informecertificadoirregularidadeRepository.findOne(informecertificadoirregularidade.getId());
        InformecertificadoirregularidadeDTO informecertificadoirregularidadeDTO = informecertificadoirregularidadeMapper.toDto(updatedInformecertificadoirregularidade);

        restInformecertificadoirregularidadeMockMvc.perform(put("/api/informecertificadoirregularidades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(informecertificadoirregularidadeDTO)))
            .andExpect(status().isOk());

        // Validate the Informecertificadoirregularidade in the database
        List<Informecertificadoirregularidade> informecertificadoirregularidadeList = informecertificadoirregularidadeRepository.findAll();
        assertThat(informecertificadoirregularidadeList).hasSize(databaseSizeBeforeUpdate);
        Informecertificadoirregularidade testInformecertificadoirregularidade = informecertificadoirregularidadeList.get(informecertificadoirregularidadeList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingInformecertificadoirregularidade() throws Exception {
        int databaseSizeBeforeUpdate = informecertificadoirregularidadeRepository.findAll().size();

        // Create the Informecertificadoirregularidade
        InformecertificadoirregularidadeDTO informecertificadoirregularidadeDTO = informecertificadoirregularidadeMapper.toDto(informecertificadoirregularidade);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restInformecertificadoirregularidadeMockMvc.perform(put("/api/informecertificadoirregularidades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(informecertificadoirregularidadeDTO)))
            .andExpect(status().isCreated());

        // Validate the Informecertificadoirregularidade in the database
        List<Informecertificadoirregularidade> informecertificadoirregularidadeList = informecertificadoirregularidadeRepository.findAll();
        assertThat(informecertificadoirregularidadeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteInformecertificadoirregularidade() throws Exception {
        // Initialize the database
        informecertificadoirregularidadeRepository.saveAndFlush(informecertificadoirregularidade);
        int databaseSizeBeforeDelete = informecertificadoirregularidadeRepository.findAll().size();

        // Get the informecertificadoirregularidade
        restInformecertificadoirregularidadeMockMvc.perform(delete("/api/informecertificadoirregularidades/{id}", informecertificadoirregularidade.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Informecertificadoirregularidade> informecertificadoirregularidadeList = informecertificadoirregularidadeRepository.findAll();
        assertThat(informecertificadoirregularidadeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Informecertificadoirregularidade.class);
        Informecertificadoirregularidade informecertificadoirregularidade1 = new Informecertificadoirregularidade();
        informecertificadoirregularidade1.setId(1L);
        Informecertificadoirregularidade informecertificadoirregularidade2 = new Informecertificadoirregularidade();
        informecertificadoirregularidade2.setId(informecertificadoirregularidade1.getId());
        assertThat(informecertificadoirregularidade1).isEqualTo(informecertificadoirregularidade2);
        informecertificadoirregularidade2.setId(2L);
        assertThat(informecertificadoirregularidade1).isNotEqualTo(informecertificadoirregularidade2);
        informecertificadoirregularidade1.setId(null);
        assertThat(informecertificadoirregularidade1).isNotEqualTo(informecertificadoirregularidade2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InformecertificadoirregularidadeDTO.class);
        InformecertificadoirregularidadeDTO informecertificadoirregularidadeDTO1 = new InformecertificadoirregularidadeDTO();
        informecertificadoirregularidadeDTO1.setId(1L);
        InformecertificadoirregularidadeDTO informecertificadoirregularidadeDTO2 = new InformecertificadoirregularidadeDTO();
        assertThat(informecertificadoirregularidadeDTO1).isNotEqualTo(informecertificadoirregularidadeDTO2);
        informecertificadoirregularidadeDTO2.setId(informecertificadoirregularidadeDTO1.getId());
        assertThat(informecertificadoirregularidadeDTO1).isEqualTo(informecertificadoirregularidadeDTO2);
        informecertificadoirregularidadeDTO2.setId(2L);
        assertThat(informecertificadoirregularidadeDTO1).isNotEqualTo(informecertificadoirregularidadeDTO2);
        informecertificadoirregularidadeDTO1.setId(null);
        assertThat(informecertificadoirregularidadeDTO1).isNotEqualTo(informecertificadoirregularidadeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(informecertificadoirregularidadeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(informecertificadoirregularidadeMapper.fromId(null)).isNull();
    }
}
