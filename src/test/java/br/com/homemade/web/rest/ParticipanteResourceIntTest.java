package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Participante;
import br.com.homemade.repository.ParticipanteRepository;
import br.com.homemade.service.dto.ParticipanteDTO;
import br.com.homemade.service.mapper.ParticipanteMapper;
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
 * Test class for the ParticipanteResource REST controller.
 *
 * @see ParticipanteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class ParticipanteResourceIntTest {

    private static final String DEFAULT_FORMACAO = "AAAAAAAAAA";
    private static final String UPDATED_FORMACAO = "BBBBBBBBBB";

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    @Autowired
    private ParticipanteRepository participanteRepository;

    @Autowired
    private ParticipanteMapper participanteMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restParticipanteMockMvc;

    private Participante participante;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ParticipanteResource participanteResource = new ParticipanteResource(participanteRepository, participanteMapper);
        this.restParticipanteMockMvc = MockMvcBuilders.standaloneSetup(participanteResource)
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
    public static Participante createEntity(EntityManager em) {
        Participante participante = new Participante()
            .formacao(DEFAULT_FORMACAO)
            .nome(DEFAULT_NOME);
        return participante;
    }

    @Before
    public void initTest() {
        participante = createEntity(em);
    }

    @Test
    @Transactional
    public void createParticipante() throws Exception {
        int databaseSizeBeforeCreate = participanteRepository.findAll().size();

        // Create the Participante
        ParticipanteDTO participanteDTO = participanteMapper.toDto(participante);
        restParticipanteMockMvc.perform(post("/api/participantes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(participanteDTO)))
            .andExpect(status().isCreated());

        // Validate the Participante in the database
        List<Participante> participanteList = participanteRepository.findAll();
        assertThat(participanteList).hasSize(databaseSizeBeforeCreate + 1);
        Participante testParticipante = participanteList.get(participanteList.size() - 1);
        assertThat(testParticipante.getFormacao()).isEqualTo(DEFAULT_FORMACAO);
        assertThat(testParticipante.getNome()).isEqualTo(DEFAULT_NOME);
    }

    @Test
    @Transactional
    public void createParticipanteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = participanteRepository.findAll().size();

        // Create the Participante with an existing ID
        participante.setId(1L);
        ParticipanteDTO participanteDTO = participanteMapper.toDto(participante);

        // An entity with an existing ID cannot be created, so this API call must fail
        restParticipanteMockMvc.perform(post("/api/participantes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(participanteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Participante> participanteList = participanteRepository.findAll();
        assertThat(participanteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllParticipantes() throws Exception {
        // Initialize the database
        participanteRepository.saveAndFlush(participante);

        // Get all the participanteList
        restParticipanteMockMvc.perform(get("/api/participantes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(participante.getId().intValue())))
            .andExpect(jsonPath("$.[*].formacao").value(hasItem(DEFAULT_FORMACAO.toString())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())));
    }

    @Test
    @Transactional
    public void getParticipante() throws Exception {
        // Initialize the database
        participanteRepository.saveAndFlush(participante);

        // Get the participante
        restParticipanteMockMvc.perform(get("/api/participantes/{id}", participante.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(participante.getId().intValue()))
            .andExpect(jsonPath("$.formacao").value(DEFAULT_FORMACAO.toString()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingParticipante() throws Exception {
        // Get the participante
        restParticipanteMockMvc.perform(get("/api/participantes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateParticipante() throws Exception {
        // Initialize the database
        participanteRepository.saveAndFlush(participante);
        int databaseSizeBeforeUpdate = participanteRepository.findAll().size();

        // Update the participante
        Participante updatedParticipante = participanteRepository.findOne(participante.getId());
        updatedParticipante
            .formacao(UPDATED_FORMACAO)
            .nome(UPDATED_NOME);
        ParticipanteDTO participanteDTO = participanteMapper.toDto(updatedParticipante);

        restParticipanteMockMvc.perform(put("/api/participantes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(participanteDTO)))
            .andExpect(status().isOk());

        // Validate the Participante in the database
        List<Participante> participanteList = participanteRepository.findAll();
        assertThat(participanteList).hasSize(databaseSizeBeforeUpdate);
        Participante testParticipante = participanteList.get(participanteList.size() - 1);
        assertThat(testParticipante.getFormacao()).isEqualTo(UPDATED_FORMACAO);
        assertThat(testParticipante.getNome()).isEqualTo(UPDATED_NOME);
    }

    @Test
    @Transactional
    public void updateNonExistingParticipante() throws Exception {
        int databaseSizeBeforeUpdate = participanteRepository.findAll().size();

        // Create the Participante
        ParticipanteDTO participanteDTO = participanteMapper.toDto(participante);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restParticipanteMockMvc.perform(put("/api/participantes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(participanteDTO)))
            .andExpect(status().isCreated());

        // Validate the Participante in the database
        List<Participante> participanteList = participanteRepository.findAll();
        assertThat(participanteList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteParticipante() throws Exception {
        // Initialize the database
        participanteRepository.saveAndFlush(participante);
        int databaseSizeBeforeDelete = participanteRepository.findAll().size();

        // Get the participante
        restParticipanteMockMvc.perform(delete("/api/participantes/{id}", participante.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Participante> participanteList = participanteRepository.findAll();
        assertThat(participanteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Participante.class);
        Participante participante1 = new Participante();
        participante1.setId(1L);
        Participante participante2 = new Participante();
        participante2.setId(participante1.getId());
        assertThat(participante1).isEqualTo(participante2);
        participante2.setId(2L);
        assertThat(participante1).isNotEqualTo(participante2);
        participante1.setId(null);
        assertThat(participante1).isNotEqualTo(participante2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ParticipanteDTO.class);
        ParticipanteDTO participanteDTO1 = new ParticipanteDTO();
        participanteDTO1.setId(1L);
        ParticipanteDTO participanteDTO2 = new ParticipanteDTO();
        assertThat(participanteDTO1).isNotEqualTo(participanteDTO2);
        participanteDTO2.setId(participanteDTO1.getId());
        assertThat(participanteDTO1).isEqualTo(participanteDTO2);
        participanteDTO2.setId(2L);
        assertThat(participanteDTO1).isNotEqualTo(participanteDTO2);
        participanteDTO1.setId(null);
        assertThat(participanteDTO1).isNotEqualTo(participanteDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(participanteMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(participanteMapper.fromId(null)).isNull();
    }
}
