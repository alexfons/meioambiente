package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Empresa;
import br.com.homemade.repository.EmpresaRepository;
import br.com.homemade.service.dto.EmpresaDTO;
import br.com.homemade.service.mapper.EmpresaMapper;
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
 * Test class for the EmpresaResource REST controller.
 *
 * @see EmpresaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class EmpresaResourceIntTest {

    private static final String DEFAULT_BAIRRO = "AAAAAAAAAA";
    private static final String UPDATED_BAIRRO = "BBBBBBBBBB";

    private static final String DEFAULT_CIDADE = "AAAAAAAAAA";
    private static final String UPDATED_CIDADE = "BBBBBBBBBB";

    private static final String DEFAULT_CNPJ = "AAAAAAAAAA";
    private static final String UPDATED_CNPJ = "BBBBBBBBBB";

    private static final String DEFAULT_CONTATO = "AAAAAAAAAA";
    private static final String UPDATED_CONTATO = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_ENDERECO = "AAAAAAAAAA";
    private static final String UPDATED_ENDERECO = "BBBBBBBBBB";

    private static final Integer DEFAULT_IDEMPRESA = 1;
    private static final Integer UPDATED_IDEMPRESA = 2;

    private static final String DEFAULT_NOMEEMPRESA = "AAAAAAAAAA";
    private static final String UPDATED_NOMEEMPRESA = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO = "BBBBBBBBBB";

    private static final String DEFAULT_PAIS = "AAAAAAAAAA";
    private static final String UPDATED_PAIS = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONECONTATO = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONECONTATO = "BBBBBBBBBB";

    private static final String DEFAULT_UF = "AAAAAAAAAA";
    private static final String UPDATED_UF = "BBBBBBBBBB";

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EmpresaMapper empresaMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEmpresaMockMvc;

    private Empresa empresa;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        EmpresaResource empresaResource = new EmpresaResource(empresaRepository, empresaMapper);
        this.restEmpresaMockMvc = MockMvcBuilders.standaloneSetup(empresaResource)
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
    public static Empresa createEntity(EntityManager em) {
        Empresa empresa = new Empresa()
            .bairro(DEFAULT_BAIRRO)
            .cidade(DEFAULT_CIDADE)
            .cnpj(DEFAULT_CNPJ)
            .contato(DEFAULT_CONTATO)
            .email(DEFAULT_EMAIL)
            .endereco(DEFAULT_ENDERECO)
            .idempresa(DEFAULT_IDEMPRESA)
            .nomeempresa(DEFAULT_NOMEEMPRESA)
            .numero(DEFAULT_NUMERO)
            .pais(DEFAULT_PAIS)
            .telefonecontato(DEFAULT_TELEFONECONTATO)
            .uf(DEFAULT_UF);
        return empresa;
    }

    @Before
    public void initTest() {
        empresa = createEntity(em);
    }

    @Test
    @Transactional
    public void createEmpresa() throws Exception {
        int databaseSizeBeforeCreate = empresaRepository.findAll().size();

        // Create the Empresa
        EmpresaDTO empresaDTO = empresaMapper.toDto(empresa);
        restEmpresaMockMvc.perform(post("/api/empresas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empresaDTO)))
            .andExpect(status().isCreated());

        // Validate the Empresa in the database
        List<Empresa> empresaList = empresaRepository.findAll();
        assertThat(empresaList).hasSize(databaseSizeBeforeCreate + 1);
        Empresa testEmpresa = empresaList.get(empresaList.size() - 1);
        assertThat(testEmpresa.getBairro()).isEqualTo(DEFAULT_BAIRRO);
        assertThat(testEmpresa.getCidade()).isEqualTo(DEFAULT_CIDADE);
        assertThat(testEmpresa.getCnpj()).isEqualTo(DEFAULT_CNPJ);
        assertThat(testEmpresa.getContato()).isEqualTo(DEFAULT_CONTATO);
        assertThat(testEmpresa.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testEmpresa.getEndereco()).isEqualTo(DEFAULT_ENDERECO);
        assertThat(testEmpresa.getIdempresa()).isEqualTo(DEFAULT_IDEMPRESA);
        assertThat(testEmpresa.getNomeempresa()).isEqualTo(DEFAULT_NOMEEMPRESA);
        assertThat(testEmpresa.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testEmpresa.getPais()).isEqualTo(DEFAULT_PAIS);
        assertThat(testEmpresa.getTelefonecontato()).isEqualTo(DEFAULT_TELEFONECONTATO);
        assertThat(testEmpresa.getUf()).isEqualTo(DEFAULT_UF);
    }

    @Test
    @Transactional
    public void createEmpresaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = empresaRepository.findAll().size();

        // Create the Empresa with an existing ID
        empresa.setId(1L);
        EmpresaDTO empresaDTO = empresaMapper.toDto(empresa);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmpresaMockMvc.perform(post("/api/empresas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empresaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Empresa> empresaList = empresaRepository.findAll();
        assertThat(empresaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEmpresas() throws Exception {
        // Initialize the database
        empresaRepository.saveAndFlush(empresa);

        // Get all the empresaList
        restEmpresaMockMvc.perform(get("/api/empresas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(empresa.getId().intValue())))
            .andExpect(jsonPath("$.[*].bairro").value(hasItem(DEFAULT_BAIRRO.toString())))
            .andExpect(jsonPath("$.[*].cidade").value(hasItem(DEFAULT_CIDADE.toString())))
            .andExpect(jsonPath("$.[*].cnpj").value(hasItem(DEFAULT_CNPJ.toString())))
            .andExpect(jsonPath("$.[*].contato").value(hasItem(DEFAULT_CONTATO.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].endereco").value(hasItem(DEFAULT_ENDERECO.toString())))
            .andExpect(jsonPath("$.[*].idempresa").value(hasItem(DEFAULT_IDEMPRESA)))
            .andExpect(jsonPath("$.[*].nomeempresa").value(hasItem(DEFAULT_NOMEEMPRESA.toString())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO.toString())))
            .andExpect(jsonPath("$.[*].pais").value(hasItem(DEFAULT_PAIS.toString())))
            .andExpect(jsonPath("$.[*].telefonecontato").value(hasItem(DEFAULT_TELEFONECONTATO.toString())))
            .andExpect(jsonPath("$.[*].uf").value(hasItem(DEFAULT_UF.toString())));
    }

    @Test
    @Transactional
    public void getEmpresa() throws Exception {
        // Initialize the database
        empresaRepository.saveAndFlush(empresa);

        // Get the empresa
        restEmpresaMockMvc.perform(get("/api/empresas/{id}", empresa.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(empresa.getId().intValue()))
            .andExpect(jsonPath("$.bairro").value(DEFAULT_BAIRRO.toString()))
            .andExpect(jsonPath("$.cidade").value(DEFAULT_CIDADE.toString()))
            .andExpect(jsonPath("$.cnpj").value(DEFAULT_CNPJ.toString()))
            .andExpect(jsonPath("$.contato").value(DEFAULT_CONTATO.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.endereco").value(DEFAULT_ENDERECO.toString()))
            .andExpect(jsonPath("$.idempresa").value(DEFAULT_IDEMPRESA))
            .andExpect(jsonPath("$.nomeempresa").value(DEFAULT_NOMEEMPRESA.toString()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO.toString()))
            .andExpect(jsonPath("$.pais").value(DEFAULT_PAIS.toString()))
            .andExpect(jsonPath("$.telefonecontato").value(DEFAULT_TELEFONECONTATO.toString()))
            .andExpect(jsonPath("$.uf").value(DEFAULT_UF.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEmpresa() throws Exception {
        // Get the empresa
        restEmpresaMockMvc.perform(get("/api/empresas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEmpresa() throws Exception {
        // Initialize the database
        empresaRepository.saveAndFlush(empresa);
        int databaseSizeBeforeUpdate = empresaRepository.findAll().size();

        // Update the empresa
        Empresa updatedEmpresa = empresaRepository.findOne(empresa.getId());
        updatedEmpresa
            .bairro(UPDATED_BAIRRO)
            .cidade(UPDATED_CIDADE)
            .cnpj(UPDATED_CNPJ)
            .contato(UPDATED_CONTATO)
            .email(UPDATED_EMAIL)
            .endereco(UPDATED_ENDERECO)
            .idempresa(UPDATED_IDEMPRESA)
            .nomeempresa(UPDATED_NOMEEMPRESA)
            .numero(UPDATED_NUMERO)
            .pais(UPDATED_PAIS)
            .telefonecontato(UPDATED_TELEFONECONTATO)
            .uf(UPDATED_UF);
        EmpresaDTO empresaDTO = empresaMapper.toDto(updatedEmpresa);

        restEmpresaMockMvc.perform(put("/api/empresas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empresaDTO)))
            .andExpect(status().isOk());

        // Validate the Empresa in the database
        List<Empresa> empresaList = empresaRepository.findAll();
        assertThat(empresaList).hasSize(databaseSizeBeforeUpdate);
        Empresa testEmpresa = empresaList.get(empresaList.size() - 1);
        assertThat(testEmpresa.getBairro()).isEqualTo(UPDATED_BAIRRO);
        assertThat(testEmpresa.getCidade()).isEqualTo(UPDATED_CIDADE);
        assertThat(testEmpresa.getCnpj()).isEqualTo(UPDATED_CNPJ);
        assertThat(testEmpresa.getContato()).isEqualTo(UPDATED_CONTATO);
        assertThat(testEmpresa.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testEmpresa.getEndereco()).isEqualTo(UPDATED_ENDERECO);
        assertThat(testEmpresa.getIdempresa()).isEqualTo(UPDATED_IDEMPRESA);
        assertThat(testEmpresa.getNomeempresa()).isEqualTo(UPDATED_NOMEEMPRESA);
        assertThat(testEmpresa.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testEmpresa.getPais()).isEqualTo(UPDATED_PAIS);
        assertThat(testEmpresa.getTelefonecontato()).isEqualTo(UPDATED_TELEFONECONTATO);
        assertThat(testEmpresa.getUf()).isEqualTo(UPDATED_UF);
    }

    @Test
    @Transactional
    public void updateNonExistingEmpresa() throws Exception {
        int databaseSizeBeforeUpdate = empresaRepository.findAll().size();

        // Create the Empresa
        EmpresaDTO empresaDTO = empresaMapper.toDto(empresa);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEmpresaMockMvc.perform(put("/api/empresas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empresaDTO)))
            .andExpect(status().isCreated());

        // Validate the Empresa in the database
        List<Empresa> empresaList = empresaRepository.findAll();
        assertThat(empresaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteEmpresa() throws Exception {
        // Initialize the database
        empresaRepository.saveAndFlush(empresa);
        int databaseSizeBeforeDelete = empresaRepository.findAll().size();

        // Get the empresa
        restEmpresaMockMvc.perform(delete("/api/empresas/{id}", empresa.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Empresa> empresaList = empresaRepository.findAll();
        assertThat(empresaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Empresa.class);
        Empresa empresa1 = new Empresa();
        empresa1.setId(1L);
        Empresa empresa2 = new Empresa();
        empresa2.setId(empresa1.getId());
        assertThat(empresa1).isEqualTo(empresa2);
        empresa2.setId(2L);
        assertThat(empresa1).isNotEqualTo(empresa2);
        empresa1.setId(null);
        assertThat(empresa1).isNotEqualTo(empresa2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmpresaDTO.class);
        EmpresaDTO empresaDTO1 = new EmpresaDTO();
        empresaDTO1.setId(1L);
        EmpresaDTO empresaDTO2 = new EmpresaDTO();
        assertThat(empresaDTO1).isNotEqualTo(empresaDTO2);
        empresaDTO2.setId(empresaDTO1.getId());
        assertThat(empresaDTO1).isEqualTo(empresaDTO2);
        empresaDTO2.setId(2L);
        assertThat(empresaDTO1).isNotEqualTo(empresaDTO2);
        empresaDTO1.setId(null);
        assertThat(empresaDTO1).isNotEqualTo(empresaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(empresaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(empresaMapper.fromId(null)).isNull();
    }
}
