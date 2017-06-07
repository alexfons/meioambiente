package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.ResponsavelTipo;
import br.com.homemade.repository.ResponsavelTipoRepository;
import br.com.homemade.service.dto.ResponsavelTipoDTO;
import br.com.homemade.service.mapper.ResponsavelTipoMapper;
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
 * Test class for the ResponsavelTipoResource REST controller.
 *
 * @see ResponsavelTipoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class ResponsavelTipoResourceIntTest {

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
    private ResponsavelTipoRepository responsavelTipoRepository;

    @Autowired
    private ResponsavelTipoMapper responsavelTipoMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restResponsavelTipoMockMvc;

    private ResponsavelTipo responsavelTipo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ResponsavelTipoResource responsavelTipoResource = new ResponsavelTipoResource(responsavelTipoRepository, responsavelTipoMapper);
        this.restResponsavelTipoMockMvc = MockMvcBuilders.standaloneSetup(responsavelTipoResource)
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
    public static ResponsavelTipo createEntity(EntityManager em) {
        ResponsavelTipo responsavelTipo = new ResponsavelTipo()
            .email(DEFAULT_EMAIL)
            .funcao(DEFAULT_FUNCAO)
            .localtrabalho(DEFAULT_LOCALTRABALHO)
            .matricula(DEFAULT_MATRICULA)
            .nome(DEFAULT_NOME)
            .superintendencia(DEFAULT_SUPERINTENDENCIA)
            .telefone(DEFAULT_TELEFONE)
            .telefonecomercial(DEFAULT_TELEFONECOMERCIAL);
        return responsavelTipo;
    }

    @Before
    public void initTest() {
        responsavelTipo = createEntity(em);
    }

    @Test
    @Transactional
    public void createResponsavelTipo() throws Exception {
        int databaseSizeBeforeCreate = responsavelTipoRepository.findAll().size();

        // Create the ResponsavelTipo
        ResponsavelTipoDTO responsavelTipoDTO = responsavelTipoMapper.toDto(responsavelTipo);
        restResponsavelTipoMockMvc.perform(post("/api/responsavel-tipos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(responsavelTipoDTO)))
            .andExpect(status().isCreated());

        // Validate the ResponsavelTipo in the database
        List<ResponsavelTipo> responsavelTipoList = responsavelTipoRepository.findAll();
        assertThat(responsavelTipoList).hasSize(databaseSizeBeforeCreate + 1);
        ResponsavelTipo testResponsavelTipo = responsavelTipoList.get(responsavelTipoList.size() - 1);
        assertThat(testResponsavelTipo.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testResponsavelTipo.getFuncao()).isEqualTo(DEFAULT_FUNCAO);
        assertThat(testResponsavelTipo.getLocaltrabalho()).isEqualTo(DEFAULT_LOCALTRABALHO);
        assertThat(testResponsavelTipo.getMatricula()).isEqualTo(DEFAULT_MATRICULA);
        assertThat(testResponsavelTipo.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testResponsavelTipo.getSuperintendencia()).isEqualTo(DEFAULT_SUPERINTENDENCIA);
        assertThat(testResponsavelTipo.getTelefone()).isEqualTo(DEFAULT_TELEFONE);
        assertThat(testResponsavelTipo.getTelefonecomercial()).isEqualTo(DEFAULT_TELEFONECOMERCIAL);
    }

    @Test
    @Transactional
    public void createResponsavelTipoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = responsavelTipoRepository.findAll().size();

        // Create the ResponsavelTipo with an existing ID
        responsavelTipo.setId(1L);
        ResponsavelTipoDTO responsavelTipoDTO = responsavelTipoMapper.toDto(responsavelTipo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restResponsavelTipoMockMvc.perform(post("/api/responsavel-tipos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(responsavelTipoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<ResponsavelTipo> responsavelTipoList = responsavelTipoRepository.findAll();
        assertThat(responsavelTipoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllResponsavelTipos() throws Exception {
        // Initialize the database
        responsavelTipoRepository.saveAndFlush(responsavelTipo);

        // Get all the responsavelTipoList
        restResponsavelTipoMockMvc.perform(get("/api/responsavel-tipos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(responsavelTipo.getId().intValue())))
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
    public void getResponsavelTipo() throws Exception {
        // Initialize the database
        responsavelTipoRepository.saveAndFlush(responsavelTipo);

        // Get the responsavelTipo
        restResponsavelTipoMockMvc.perform(get("/api/responsavel-tipos/{id}", responsavelTipo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(responsavelTipo.getId().intValue()))
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
    public void getNonExistingResponsavelTipo() throws Exception {
        // Get the responsavelTipo
        restResponsavelTipoMockMvc.perform(get("/api/responsavel-tipos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateResponsavelTipo() throws Exception {
        // Initialize the database
        responsavelTipoRepository.saveAndFlush(responsavelTipo);
        int databaseSizeBeforeUpdate = responsavelTipoRepository.findAll().size();

        // Update the responsavelTipo
        ResponsavelTipo updatedResponsavelTipo = responsavelTipoRepository.findOne(responsavelTipo.getId());
        updatedResponsavelTipo
            .email(UPDATED_EMAIL)
            .funcao(UPDATED_FUNCAO)
            .localtrabalho(UPDATED_LOCALTRABALHO)
            .matricula(UPDATED_MATRICULA)
            .nome(UPDATED_NOME)
            .superintendencia(UPDATED_SUPERINTENDENCIA)
            .telefone(UPDATED_TELEFONE)
            .telefonecomercial(UPDATED_TELEFONECOMERCIAL);
        ResponsavelTipoDTO responsavelTipoDTO = responsavelTipoMapper.toDto(updatedResponsavelTipo);

        restResponsavelTipoMockMvc.perform(put("/api/responsavel-tipos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(responsavelTipoDTO)))
            .andExpect(status().isOk());

        // Validate the ResponsavelTipo in the database
        List<ResponsavelTipo> responsavelTipoList = responsavelTipoRepository.findAll();
        assertThat(responsavelTipoList).hasSize(databaseSizeBeforeUpdate);
        ResponsavelTipo testResponsavelTipo = responsavelTipoList.get(responsavelTipoList.size() - 1);
        assertThat(testResponsavelTipo.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testResponsavelTipo.getFuncao()).isEqualTo(UPDATED_FUNCAO);
        assertThat(testResponsavelTipo.getLocaltrabalho()).isEqualTo(UPDATED_LOCALTRABALHO);
        assertThat(testResponsavelTipo.getMatricula()).isEqualTo(UPDATED_MATRICULA);
        assertThat(testResponsavelTipo.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testResponsavelTipo.getSuperintendencia()).isEqualTo(UPDATED_SUPERINTENDENCIA);
        assertThat(testResponsavelTipo.getTelefone()).isEqualTo(UPDATED_TELEFONE);
        assertThat(testResponsavelTipo.getTelefonecomercial()).isEqualTo(UPDATED_TELEFONECOMERCIAL);
    }

    @Test
    @Transactional
    public void updateNonExistingResponsavelTipo() throws Exception {
        int databaseSizeBeforeUpdate = responsavelTipoRepository.findAll().size();

        // Create the ResponsavelTipo
        ResponsavelTipoDTO responsavelTipoDTO = responsavelTipoMapper.toDto(responsavelTipo);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restResponsavelTipoMockMvc.perform(put("/api/responsavel-tipos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(responsavelTipoDTO)))
            .andExpect(status().isCreated());

        // Validate the ResponsavelTipo in the database
        List<ResponsavelTipo> responsavelTipoList = responsavelTipoRepository.findAll();
        assertThat(responsavelTipoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteResponsavelTipo() throws Exception {
        // Initialize the database
        responsavelTipoRepository.saveAndFlush(responsavelTipo);
        int databaseSizeBeforeDelete = responsavelTipoRepository.findAll().size();

        // Get the responsavelTipo
        restResponsavelTipoMockMvc.perform(delete("/api/responsavel-tipos/{id}", responsavelTipo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ResponsavelTipo> responsavelTipoList = responsavelTipoRepository.findAll();
        assertThat(responsavelTipoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResponsavelTipo.class);
        ResponsavelTipo responsavelTipo1 = new ResponsavelTipo();
        responsavelTipo1.setId(1L);
        ResponsavelTipo responsavelTipo2 = new ResponsavelTipo();
        responsavelTipo2.setId(responsavelTipo1.getId());
        assertThat(responsavelTipo1).isEqualTo(responsavelTipo2);
        responsavelTipo2.setId(2L);
        assertThat(responsavelTipo1).isNotEqualTo(responsavelTipo2);
        responsavelTipo1.setId(null);
        assertThat(responsavelTipo1).isNotEqualTo(responsavelTipo2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResponsavelTipoDTO.class);
        ResponsavelTipoDTO responsavelTipoDTO1 = new ResponsavelTipoDTO();
        responsavelTipoDTO1.setId(1L);
        ResponsavelTipoDTO responsavelTipoDTO2 = new ResponsavelTipoDTO();
        assertThat(responsavelTipoDTO1).isNotEqualTo(responsavelTipoDTO2);
        responsavelTipoDTO2.setId(responsavelTipoDTO1.getId());
        assertThat(responsavelTipoDTO1).isEqualTo(responsavelTipoDTO2);
        responsavelTipoDTO2.setId(2L);
        assertThat(responsavelTipoDTO1).isNotEqualTo(responsavelTipoDTO2);
        responsavelTipoDTO1.setId(null);
        assertThat(responsavelTipoDTO1).isNotEqualTo(responsavelTipoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(responsavelTipoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(responsavelTipoMapper.fromId(null)).isNull();
    }
}
