package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Responsavel;
import br.com.homemade.repository.ResponsavelRepository;
import br.com.homemade.service.dto.ResponsavelDTO;
import br.com.homemade.service.mapper.ResponsavelMapper;
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
 * Test class for the ResponsavelResource REST controller.
 *
 * @see ResponsavelResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class ResponsavelResourceIntTest {

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_FUNCAO = "AAAAAAAAAA";
    private static final String UPDATED_FUNCAO = "BBBBBBBBBB";

    private static final String DEFAULT_LOCALTRABALHO = "AAAAAAAAAA";
    private static final String UPDATED_LOCALTRABALHO = "BBBBBBBBBB";

    private static final String DEFAULT_MATRICULA = "AAAAAAAAAA";
    private static final String UPDATED_MATRICULA = "BBBBBBBBBB";

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_SUPERINTENDENCIA = "AAAAAAAAAA";
    private static final String UPDATED_SUPERINTENDENCIA = "BBBBBBBBBB";

    private static final Long DEFAULT_TELEFONE = 1L;
    private static final Long UPDATED_TELEFONE = 2L;

    private static final Long DEFAULT_TELEFONECOMERCIAL = 1L;
    private static final Long UPDATED_TELEFONECOMERCIAL = 2L;

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Autowired
    private ResponsavelMapper responsavelMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restResponsavelMockMvc;

    private Responsavel responsavel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ResponsavelResource responsavelResource = new ResponsavelResource(responsavelRepository, responsavelMapper);
        this.restResponsavelMockMvc = MockMvcBuilders.standaloneSetup(responsavelResource)
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
    public static Responsavel createEntity(EntityManager em) {
        Responsavel responsavel = new Responsavel()
            .email(DEFAULT_EMAIL)
            .funcao(DEFAULT_FUNCAO)
            .localtrabalho(DEFAULT_LOCALTRABALHO)
            .matricula(DEFAULT_MATRICULA)
            .nome(DEFAULT_NOME)
            .superintendencia(DEFAULT_SUPERINTENDENCIA)
            .telefone(DEFAULT_TELEFONE)
            .telefonecomercial(DEFAULT_TELEFONECOMERCIAL);
        return responsavel;
    }

    @Before
    public void initTest() {
        responsavel = createEntity(em);
    }

    @Test
    @Transactional
    public void createResponsavel() throws Exception {
        int databaseSizeBeforeCreate = responsavelRepository.findAll().size();

        // Create the Responsavel
        ResponsavelDTO responsavelDTO = responsavelMapper.toDto(responsavel);
        restResponsavelMockMvc.perform(post("/api/responsavels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(responsavelDTO)))
            .andExpect(status().isCreated());

        // Validate the Responsavel in the database
        List<Responsavel> responsavelList = responsavelRepository.findAll();
        assertThat(responsavelList).hasSize(databaseSizeBeforeCreate + 1);
        Responsavel testResponsavel = responsavelList.get(responsavelList.size() - 1);
        assertThat(testResponsavel.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testResponsavel.getFuncao()).isEqualTo(DEFAULT_FUNCAO);
        assertThat(testResponsavel.getLocaltrabalho()).isEqualTo(DEFAULT_LOCALTRABALHO);
        assertThat(testResponsavel.getMatricula()).isEqualTo(DEFAULT_MATRICULA);
        assertThat(testResponsavel.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testResponsavel.getSuperintendencia()).isEqualTo(DEFAULT_SUPERINTENDENCIA);
        assertThat(testResponsavel.getTelefone()).isEqualTo(DEFAULT_TELEFONE);
        assertThat(testResponsavel.getTelefonecomercial()).isEqualTo(DEFAULT_TELEFONECOMERCIAL);
    }

    @Test
    @Transactional
    public void createResponsavelWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = responsavelRepository.findAll().size();

        // Create the Responsavel with an existing ID
        responsavel.setId(1L);
        ResponsavelDTO responsavelDTO = responsavelMapper.toDto(responsavel);

        // An entity with an existing ID cannot be created, so this API call must fail
        restResponsavelMockMvc.perform(post("/api/responsavels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(responsavelDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Responsavel> responsavelList = responsavelRepository.findAll();
        assertThat(responsavelList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllResponsavels() throws Exception {
        // Initialize the database
        responsavelRepository.saveAndFlush(responsavel);

        // Get all the responsavelList
        restResponsavelMockMvc.perform(get("/api/responsavels?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(responsavel.getId().intValue())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].funcao").value(hasItem(DEFAULT_FUNCAO.toString())))
            .andExpect(jsonPath("$.[*].localtrabalho").value(hasItem(DEFAULT_LOCALTRABALHO.toString())))
            .andExpect(jsonPath("$.[*].matricula").value(hasItem(DEFAULT_MATRICULA.toString())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].superintendencia").value(hasItem(DEFAULT_SUPERINTENDENCIA.toString())))
            .andExpect(jsonPath("$.[*].telefone").value(hasItem(DEFAULT_TELEFONE.intValue())))
            .andExpect(jsonPath("$.[*].telefonecomercial").value(hasItem(DEFAULT_TELEFONECOMERCIAL.intValue())));
    }

    @Test
    @Transactional
    public void getResponsavel() throws Exception {
        // Initialize the database
        responsavelRepository.saveAndFlush(responsavel);

        // Get the responsavel
        restResponsavelMockMvc.perform(get("/api/responsavels/{id}", responsavel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(responsavel.getId().intValue()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.funcao").value(DEFAULT_FUNCAO.toString()))
            .andExpect(jsonPath("$.localtrabalho").value(DEFAULT_LOCALTRABALHO.toString()))
            .andExpect(jsonPath("$.matricula").value(DEFAULT_MATRICULA.toString()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.superintendencia").value(DEFAULT_SUPERINTENDENCIA.toString()))
            .andExpect(jsonPath("$.telefone").value(DEFAULT_TELEFONE.intValue()))
            .andExpect(jsonPath("$.telefonecomercial").value(DEFAULT_TELEFONECOMERCIAL.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingResponsavel() throws Exception {
        // Get the responsavel
        restResponsavelMockMvc.perform(get("/api/responsavels/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateResponsavel() throws Exception {
        // Initialize the database
        responsavelRepository.saveAndFlush(responsavel);
        int databaseSizeBeforeUpdate = responsavelRepository.findAll().size();

        // Update the responsavel
        Responsavel updatedResponsavel = responsavelRepository.findOne(responsavel.getId());
        updatedResponsavel
            .email(UPDATED_EMAIL)
            .funcao(UPDATED_FUNCAO)
            .localtrabalho(UPDATED_LOCALTRABALHO)
            .matricula(UPDATED_MATRICULA)
            .nome(UPDATED_NOME)
            .superintendencia(UPDATED_SUPERINTENDENCIA)
            .telefone(UPDATED_TELEFONE)
            .telefonecomercial(UPDATED_TELEFONECOMERCIAL);
        ResponsavelDTO responsavelDTO = responsavelMapper.toDto(updatedResponsavel);

        restResponsavelMockMvc.perform(put("/api/responsavels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(responsavelDTO)))
            .andExpect(status().isOk());

        // Validate the Responsavel in the database
        List<Responsavel> responsavelList = responsavelRepository.findAll();
        assertThat(responsavelList).hasSize(databaseSizeBeforeUpdate);
        Responsavel testResponsavel = responsavelList.get(responsavelList.size() - 1);
        assertThat(testResponsavel.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testResponsavel.getFuncao()).isEqualTo(UPDATED_FUNCAO);
        assertThat(testResponsavel.getLocaltrabalho()).isEqualTo(UPDATED_LOCALTRABALHO);
        assertThat(testResponsavel.getMatricula()).isEqualTo(UPDATED_MATRICULA);
        assertThat(testResponsavel.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testResponsavel.getSuperintendencia()).isEqualTo(UPDATED_SUPERINTENDENCIA);
        assertThat(testResponsavel.getTelefone()).isEqualTo(UPDATED_TELEFONE);
        assertThat(testResponsavel.getTelefonecomercial()).isEqualTo(UPDATED_TELEFONECOMERCIAL);
    }

    @Test
    @Transactional
    public void updateNonExistingResponsavel() throws Exception {
        int databaseSizeBeforeUpdate = responsavelRepository.findAll().size();

        // Create the Responsavel
        ResponsavelDTO responsavelDTO = responsavelMapper.toDto(responsavel);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restResponsavelMockMvc.perform(put("/api/responsavels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(responsavelDTO)))
            .andExpect(status().isCreated());

        // Validate the Responsavel in the database
        List<Responsavel> responsavelList = responsavelRepository.findAll();
        assertThat(responsavelList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteResponsavel() throws Exception {
        // Initialize the database
        responsavelRepository.saveAndFlush(responsavel);
        int databaseSizeBeforeDelete = responsavelRepository.findAll().size();

        // Get the responsavel
        restResponsavelMockMvc.perform(delete("/api/responsavels/{id}", responsavel.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Responsavel> responsavelList = responsavelRepository.findAll();
        assertThat(responsavelList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Responsavel.class);
        Responsavel responsavel1 = new Responsavel();
        responsavel1.setId(1L);
        Responsavel responsavel2 = new Responsavel();
        responsavel2.setId(responsavel1.getId());
        assertThat(responsavel1).isEqualTo(responsavel2);
        responsavel2.setId(2L);
        assertThat(responsavel1).isNotEqualTo(responsavel2);
        responsavel1.setId(null);
        assertThat(responsavel1).isNotEqualTo(responsavel2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResponsavelDTO.class);
        ResponsavelDTO responsavelDTO1 = new ResponsavelDTO();
        responsavelDTO1.setId(1L);
        ResponsavelDTO responsavelDTO2 = new ResponsavelDTO();
        assertThat(responsavelDTO1).isNotEqualTo(responsavelDTO2);
        responsavelDTO2.setId(responsavelDTO1.getId());
        assertThat(responsavelDTO1).isEqualTo(responsavelDTO2);
        responsavelDTO2.setId(2L);
        assertThat(responsavelDTO1).isNotEqualTo(responsavelDTO2);
        responsavelDTO1.setId(null);
        assertThat(responsavelDTO1).isNotEqualTo(responsavelDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(responsavelMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(responsavelMapper.fromId(null)).isNull();
    }
}
