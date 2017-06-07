package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Editallote;
import br.com.homemade.repository.EditalloteRepository;
import br.com.homemade.service.dto.EditalloteDTO;
import br.com.homemade.service.mapper.EditalloteMapper;
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
 * Test class for the EditalloteResource REST controller.
 *
 * @see EditalloteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class EditalloteResourceIntTest {

    private static final ZonedDateTime DEFAULT_DATAPROVACAOLOTE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAPROVACAOLOTE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATARELATORIOLOTE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATARELATORIOLOTE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_IDLOTE = 1;
    private static final Integer UPDATED_IDLOTE = 2;

    private static final String DEFAULT_LOTE = "AAAAAAAAAA";
    private static final String UPDATED_LOTE = "BBBBBBBBBB";

    private static final String DEFAULT_OBJETO = "AAAAAAAAAA";
    private static final String UPDATED_OBJETO = "BBBBBBBBBB";

    private static final String DEFAULT_PRAZO = "AAAAAAAAAA";
    private static final String UPDATED_PRAZO = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_VALORORCADO = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALORORCADO = new BigDecimal(2);

    @Autowired
    private EditalloteRepository editalloteRepository;

    @Autowired
    private EditalloteMapper editalloteMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEditalloteMockMvc;

    private Editallote editallote;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        EditalloteResource editalloteResource = new EditalloteResource(editalloteRepository, editalloteMapper);
        this.restEditalloteMockMvc = MockMvcBuilders.standaloneSetup(editalloteResource)
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
    public static Editallote createEntity(EntityManager em) {
        Editallote editallote = new Editallote()
            .dataprovacaolote(DEFAULT_DATAPROVACAOLOTE)
            .datarelatoriolote(DEFAULT_DATARELATORIOLOTE)
            .idlote(DEFAULT_IDLOTE)
            .lote(DEFAULT_LOTE)
            .objeto(DEFAULT_OBJETO)
            .prazo(DEFAULT_PRAZO)
            .valororcado(DEFAULT_VALORORCADO);
        return editallote;
    }

    @Before
    public void initTest() {
        editallote = createEntity(em);
    }

    @Test
    @Transactional
    public void createEditallote() throws Exception {
        int databaseSizeBeforeCreate = editalloteRepository.findAll().size();

        // Create the Editallote
        EditalloteDTO editalloteDTO = editalloteMapper.toDto(editallote);
        restEditalloteMockMvc.perform(post("/api/editallotes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(editalloteDTO)))
            .andExpect(status().isCreated());

        // Validate the Editallote in the database
        List<Editallote> editalloteList = editalloteRepository.findAll();
        assertThat(editalloteList).hasSize(databaseSizeBeforeCreate + 1);
        Editallote testEditallote = editalloteList.get(editalloteList.size() - 1);
        assertThat(testEditallote.getDataprovacaolote()).isEqualTo(DEFAULT_DATAPROVACAOLOTE);
        assertThat(testEditallote.getDatarelatoriolote()).isEqualTo(DEFAULT_DATARELATORIOLOTE);
        assertThat(testEditallote.getIdlote()).isEqualTo(DEFAULT_IDLOTE);
        assertThat(testEditallote.getLote()).isEqualTo(DEFAULT_LOTE);
        assertThat(testEditallote.getObjeto()).isEqualTo(DEFAULT_OBJETO);
        assertThat(testEditallote.getPrazo()).isEqualTo(DEFAULT_PRAZO);
        assertThat(testEditallote.getValororcado()).isEqualTo(DEFAULT_VALORORCADO);
    }

    @Test
    @Transactional
    public void createEditalloteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = editalloteRepository.findAll().size();

        // Create the Editallote with an existing ID
        editallote.setId(1L);
        EditalloteDTO editalloteDTO = editalloteMapper.toDto(editallote);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEditalloteMockMvc.perform(post("/api/editallotes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(editalloteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Editallote> editalloteList = editalloteRepository.findAll();
        assertThat(editalloteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEditallotes() throws Exception {
        // Initialize the database
        editalloteRepository.saveAndFlush(editallote);

        // Get all the editalloteList
        restEditalloteMockMvc.perform(get("/api/editallotes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(editallote.getId().intValue())))
            .andExpect(jsonPath("$.[*].dataprovacaolote").value(hasItem(sameInstant(DEFAULT_DATAPROVACAOLOTE))))
            .andExpect(jsonPath("$.[*].datarelatoriolote").value(hasItem(sameInstant(DEFAULT_DATARELATORIOLOTE))))
            .andExpect(jsonPath("$.[*].idlote").value(hasItem(DEFAULT_IDLOTE)))
            .andExpect(jsonPath("$.[*].lote").value(hasItem(DEFAULT_LOTE.toString())))
            .andExpect(jsonPath("$.[*].objeto").value(hasItem(DEFAULT_OBJETO.toString())))
            .andExpect(jsonPath("$.[*].prazo").value(hasItem(DEFAULT_PRAZO.toString())))
            .andExpect(jsonPath("$.[*].valororcado").value(hasItem(DEFAULT_VALORORCADO.intValue())));
    }

    @Test
    @Transactional
    public void getEditallote() throws Exception {
        // Initialize the database
        editalloteRepository.saveAndFlush(editallote);

        // Get the editallote
        restEditalloteMockMvc.perform(get("/api/editallotes/{id}", editallote.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(editallote.getId().intValue()))
            .andExpect(jsonPath("$.dataprovacaolote").value(sameInstant(DEFAULT_DATAPROVACAOLOTE)))
            .andExpect(jsonPath("$.datarelatoriolote").value(sameInstant(DEFAULT_DATARELATORIOLOTE)))
            .andExpect(jsonPath("$.idlote").value(DEFAULT_IDLOTE))
            .andExpect(jsonPath("$.lote").value(DEFAULT_LOTE.toString()))
            .andExpect(jsonPath("$.objeto").value(DEFAULT_OBJETO.toString()))
            .andExpect(jsonPath("$.prazo").value(DEFAULT_PRAZO.toString()))
            .andExpect(jsonPath("$.valororcado").value(DEFAULT_VALORORCADO.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEditallote() throws Exception {
        // Get the editallote
        restEditalloteMockMvc.perform(get("/api/editallotes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEditallote() throws Exception {
        // Initialize the database
        editalloteRepository.saveAndFlush(editallote);
        int databaseSizeBeforeUpdate = editalloteRepository.findAll().size();

        // Update the editallote
        Editallote updatedEditallote = editalloteRepository.findOne(editallote.getId());
        updatedEditallote
            .dataprovacaolote(UPDATED_DATAPROVACAOLOTE)
            .datarelatoriolote(UPDATED_DATARELATORIOLOTE)
            .idlote(UPDATED_IDLOTE)
            .lote(UPDATED_LOTE)
            .objeto(UPDATED_OBJETO)
            .prazo(UPDATED_PRAZO)
            .valororcado(UPDATED_VALORORCADO);
        EditalloteDTO editalloteDTO = editalloteMapper.toDto(updatedEditallote);

        restEditalloteMockMvc.perform(put("/api/editallotes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(editalloteDTO)))
            .andExpect(status().isOk());

        // Validate the Editallote in the database
        List<Editallote> editalloteList = editalloteRepository.findAll();
        assertThat(editalloteList).hasSize(databaseSizeBeforeUpdate);
        Editallote testEditallote = editalloteList.get(editalloteList.size() - 1);
        assertThat(testEditallote.getDataprovacaolote()).isEqualTo(UPDATED_DATAPROVACAOLOTE);
        assertThat(testEditallote.getDatarelatoriolote()).isEqualTo(UPDATED_DATARELATORIOLOTE);
        assertThat(testEditallote.getIdlote()).isEqualTo(UPDATED_IDLOTE);
        assertThat(testEditallote.getLote()).isEqualTo(UPDATED_LOTE);
        assertThat(testEditallote.getObjeto()).isEqualTo(UPDATED_OBJETO);
        assertThat(testEditallote.getPrazo()).isEqualTo(UPDATED_PRAZO);
        assertThat(testEditallote.getValororcado()).isEqualTo(UPDATED_VALORORCADO);
    }

    @Test
    @Transactional
    public void updateNonExistingEditallote() throws Exception {
        int databaseSizeBeforeUpdate = editalloteRepository.findAll().size();

        // Create the Editallote
        EditalloteDTO editalloteDTO = editalloteMapper.toDto(editallote);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEditalloteMockMvc.perform(put("/api/editallotes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(editalloteDTO)))
            .andExpect(status().isCreated());

        // Validate the Editallote in the database
        List<Editallote> editalloteList = editalloteRepository.findAll();
        assertThat(editalloteList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteEditallote() throws Exception {
        // Initialize the database
        editalloteRepository.saveAndFlush(editallote);
        int databaseSizeBeforeDelete = editalloteRepository.findAll().size();

        // Get the editallote
        restEditalloteMockMvc.perform(delete("/api/editallotes/{id}", editallote.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Editallote> editalloteList = editalloteRepository.findAll();
        assertThat(editalloteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Editallote.class);
        Editallote editallote1 = new Editallote();
        editallote1.setId(1L);
        Editallote editallote2 = new Editallote();
        editallote2.setId(editallote1.getId());
        assertThat(editallote1).isEqualTo(editallote2);
        editallote2.setId(2L);
        assertThat(editallote1).isNotEqualTo(editallote2);
        editallote1.setId(null);
        assertThat(editallote1).isNotEqualTo(editallote2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EditalloteDTO.class);
        EditalloteDTO editalloteDTO1 = new EditalloteDTO();
        editalloteDTO1.setId(1L);
        EditalloteDTO editalloteDTO2 = new EditalloteDTO();
        assertThat(editalloteDTO1).isNotEqualTo(editalloteDTO2);
        editalloteDTO2.setId(editalloteDTO1.getId());
        assertThat(editalloteDTO1).isEqualTo(editalloteDTO2);
        editalloteDTO2.setId(2L);
        assertThat(editalloteDTO1).isNotEqualTo(editalloteDTO2);
        editalloteDTO1.setId(null);
        assertThat(editalloteDTO1).isNotEqualTo(editalloteDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(editalloteMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(editalloteMapper.fromId(null)).isNull();
    }
}
