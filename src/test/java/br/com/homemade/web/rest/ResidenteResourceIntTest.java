package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Residente;
import br.com.homemade.repository.ResidenteRepository;
import br.com.homemade.service.dto.ResidenteDTO;
import br.com.homemade.service.mapper.ResidenteMapper;
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
 * Test class for the ResidenteResource REST controller.
 *
 * @see ResidenteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class ResidenteResourceIntTest {

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
    private ResidenteRepository residenteRepository;

    @Autowired
    private ResidenteMapper residenteMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restResidenteMockMvc;

    private Residente residente;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ResidenteResource residenteResource = new ResidenteResource(residenteRepository, residenteMapper);
        this.restResidenteMockMvc = MockMvcBuilders.standaloneSetup(residenteResource)
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
    public static Residente createEntity(EntityManager em) {
        Residente residente = new Residente()
            .email(DEFAULT_EMAIL)
            .funcao(DEFAULT_FUNCAO)
            .localtrabalho(DEFAULT_LOCALTRABALHO)
            .matricula(DEFAULT_MATRICULA)
            .nome(DEFAULT_NOME)
            .superintendencia(DEFAULT_SUPERINTENDENCIA)
            .telefone(DEFAULT_TELEFONE)
            .telefonecomercial(DEFAULT_TELEFONECOMERCIAL);
        return residente;
    }

    @Before
    public void initTest() {
        residente = createEntity(em);
    }

    @Test
    @Transactional
    public void createResidente() throws Exception {
        int databaseSizeBeforeCreate = residenteRepository.findAll().size();

        // Create the Residente
        ResidenteDTO residenteDTO = residenteMapper.toDto(residente);
        restResidenteMockMvc.perform(post("/api/residentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(residenteDTO)))
            .andExpect(status().isCreated());

        // Validate the Residente in the database
        List<Residente> residenteList = residenteRepository.findAll();
        assertThat(residenteList).hasSize(databaseSizeBeforeCreate + 1);
        Residente testResidente = residenteList.get(residenteList.size() - 1);
        assertThat(testResidente.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testResidente.getFuncao()).isEqualTo(DEFAULT_FUNCAO);
        assertThat(testResidente.getLocaltrabalho()).isEqualTo(DEFAULT_LOCALTRABALHO);
        assertThat(testResidente.getMatricula()).isEqualTo(DEFAULT_MATRICULA);
        assertThat(testResidente.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testResidente.getSuperintendencia()).isEqualTo(DEFAULT_SUPERINTENDENCIA);
        assertThat(testResidente.getTelefone()).isEqualTo(DEFAULT_TELEFONE);
        assertThat(testResidente.getTelefonecomercial()).isEqualTo(DEFAULT_TELEFONECOMERCIAL);
    }

    @Test
    @Transactional
    public void createResidenteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = residenteRepository.findAll().size();

        // Create the Residente with an existing ID
        residente.setId(1L);
        ResidenteDTO residenteDTO = residenteMapper.toDto(residente);

        // An entity with an existing ID cannot be created, so this API call must fail
        restResidenteMockMvc.perform(post("/api/residentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(residenteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Residente> residenteList = residenteRepository.findAll();
        assertThat(residenteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllResidentes() throws Exception {
        // Initialize the database
        residenteRepository.saveAndFlush(residente);

        // Get all the residenteList
        restResidenteMockMvc.perform(get("/api/residentes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(residente.getId().intValue())))
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
    public void getResidente() throws Exception {
        // Initialize the database
        residenteRepository.saveAndFlush(residente);

        // Get the residente
        restResidenteMockMvc.perform(get("/api/residentes/{id}", residente.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(residente.getId().intValue()))
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
    public void getNonExistingResidente() throws Exception {
        // Get the residente
        restResidenteMockMvc.perform(get("/api/residentes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateResidente() throws Exception {
        // Initialize the database
        residenteRepository.saveAndFlush(residente);
        int databaseSizeBeforeUpdate = residenteRepository.findAll().size();

        // Update the residente
        Residente updatedResidente = residenteRepository.findOne(residente.getId());
        updatedResidente
            .email(UPDATED_EMAIL)
            .funcao(UPDATED_FUNCAO)
            .localtrabalho(UPDATED_LOCALTRABALHO)
            .matricula(UPDATED_MATRICULA)
            .nome(UPDATED_NOME)
            .superintendencia(UPDATED_SUPERINTENDENCIA)
            .telefone(UPDATED_TELEFONE)
            .telefonecomercial(UPDATED_TELEFONECOMERCIAL);
        ResidenteDTO residenteDTO = residenteMapper.toDto(updatedResidente);

        restResidenteMockMvc.perform(put("/api/residentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(residenteDTO)))
            .andExpect(status().isOk());

        // Validate the Residente in the database
        List<Residente> residenteList = residenteRepository.findAll();
        assertThat(residenteList).hasSize(databaseSizeBeforeUpdate);
        Residente testResidente = residenteList.get(residenteList.size() - 1);
        assertThat(testResidente.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testResidente.getFuncao()).isEqualTo(UPDATED_FUNCAO);
        assertThat(testResidente.getLocaltrabalho()).isEqualTo(UPDATED_LOCALTRABALHO);
        assertThat(testResidente.getMatricula()).isEqualTo(UPDATED_MATRICULA);
        assertThat(testResidente.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testResidente.getSuperintendencia()).isEqualTo(UPDATED_SUPERINTENDENCIA);
        assertThat(testResidente.getTelefone()).isEqualTo(UPDATED_TELEFONE);
        assertThat(testResidente.getTelefonecomercial()).isEqualTo(UPDATED_TELEFONECOMERCIAL);
    }

    @Test
    @Transactional
    public void updateNonExistingResidente() throws Exception {
        int databaseSizeBeforeUpdate = residenteRepository.findAll().size();

        // Create the Residente
        ResidenteDTO residenteDTO = residenteMapper.toDto(residente);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restResidenteMockMvc.perform(put("/api/residentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(residenteDTO)))
            .andExpect(status().isCreated());

        // Validate the Residente in the database
        List<Residente> residenteList = residenteRepository.findAll();
        assertThat(residenteList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteResidente() throws Exception {
        // Initialize the database
        residenteRepository.saveAndFlush(residente);
        int databaseSizeBeforeDelete = residenteRepository.findAll().size();

        // Get the residente
        restResidenteMockMvc.perform(delete("/api/residentes/{id}", residente.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Residente> residenteList = residenteRepository.findAll();
        assertThat(residenteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Residente.class);
        Residente residente1 = new Residente();
        residente1.setId(1L);
        Residente residente2 = new Residente();
        residente2.setId(residente1.getId());
        assertThat(residente1).isEqualTo(residente2);
        residente2.setId(2L);
        assertThat(residente1).isNotEqualTo(residente2);
        residente1.setId(null);
        assertThat(residente1).isNotEqualTo(residente2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResidenteDTO.class);
        ResidenteDTO residenteDTO1 = new ResidenteDTO();
        residenteDTO1.setId(1L);
        ResidenteDTO residenteDTO2 = new ResidenteDTO();
        assertThat(residenteDTO1).isNotEqualTo(residenteDTO2);
        residenteDTO2.setId(residenteDTO1.getId());
        assertThat(residenteDTO1).isEqualTo(residenteDTO2);
        residenteDTO2.setId(2L);
        assertThat(residenteDTO1).isNotEqualTo(residenteDTO2);
        residenteDTO1.setId(null);
        assertThat(residenteDTO1).isNotEqualTo(residenteDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(residenteMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(residenteMapper.fromId(null)).isNull();
    }
}
