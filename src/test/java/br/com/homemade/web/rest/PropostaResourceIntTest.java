package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Proposta;
import br.com.homemade.repository.PropostaRepository;
import br.com.homemade.service.dto.PropostaDTO;
import br.com.homemade.service.mapper.PropostaMapper;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the PropostaResource REST controller.
 *
 * @see PropostaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class PropostaResourceIntTest {

    private static final String DEFAULT_CLASSIFICACAO = "AAAAAAAAAA";
    private static final String UPDATED_CLASSIFICACAO = "BBBBBBBBBB";

    private static final String DEFAULT_NOTA = "AAAAAAAAAA";
    private static final String UPDATED_NOTA = "BBBBBBBBBB";

    private static final String DEFAULT_TIPOPROPOSTA = "AAAAAAAAAA";
    private static final String UPDATED_TIPOPROPOSTA = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    private static final String DEFAULT_CONTRATO = "AAAAAAAAAA";
    private static final String UPDATED_CONTRATO = "BBBBBBBBBB";

    private static final String DEFAULT_HABILITADA = "AAAAAAAAAA";
    private static final String UPDATED_HABILITADA = "BBBBBBBBBB";

    private static final String DEFAULT_ARQLINK = "AAAAAAAAAA";
    private static final String UPDATED_ARQLINK = "BBBBBBBBBB";

    private static final Integer DEFAULT_IDPROPOSTA = 1;
    private static final Integer UPDATED_IDPROPOSTA = 2;

    private static final Integer DEFAULT_NUMEROEDITAL = 1;
    private static final Integer UPDATED_NUMEROEDITAL = 2;

    private static final BigDecimal DEFAULT_VALORPROPOSTA = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALORPROPOSTA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VALORRENEGOCIADO = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALORRENEGOCIADO = new BigDecimal(2);

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private PropostaMapper propostaMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPropostaMockMvc;

    private Proposta proposta;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PropostaResource propostaResource = new PropostaResource(propostaRepository, propostaMapper);
        this.restPropostaMockMvc = MockMvcBuilders.standaloneSetup(propostaResource)
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
    public static Proposta createEntity(EntityManager em) {
        Proposta proposta = new Proposta()
            .classificacao(DEFAULT_CLASSIFICACAO)
            .nota(DEFAULT_NOTA)
            .tipoproposta(DEFAULT_TIPOPROPOSTA)
            .obs(DEFAULT_OBS)
            .contrato(DEFAULT_CONTRATO)
            .habilitada(DEFAULT_HABILITADA)
            .arqlink(DEFAULT_ARQLINK)
            .idproposta(DEFAULT_IDPROPOSTA)
            .numeroedital(DEFAULT_NUMEROEDITAL)
            .valorproposta(DEFAULT_VALORPROPOSTA)
            .valorrenegociado(DEFAULT_VALORRENEGOCIADO);
        return proposta;
    }

    @Before
    public void initTest() {
        proposta = createEntity(em);
    }

    @Test
    @Transactional
    public void createProposta() throws Exception {
        int databaseSizeBeforeCreate = propostaRepository.findAll().size();

        // Create the Proposta
        PropostaDTO propostaDTO = propostaMapper.toDto(proposta);
        restPropostaMockMvc.perform(post("/api/propostas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propostaDTO)))
            .andExpect(status().isCreated());

        // Validate the Proposta in the database
        List<Proposta> propostaList = propostaRepository.findAll();
        assertThat(propostaList).hasSize(databaseSizeBeforeCreate + 1);
        Proposta testProposta = propostaList.get(propostaList.size() - 1);
        assertThat(testProposta.getClassificacao()).isEqualTo(DEFAULT_CLASSIFICACAO);
        assertThat(testProposta.getNota()).isEqualTo(DEFAULT_NOTA);
        assertThat(testProposta.getTipoproposta()).isEqualTo(DEFAULT_TIPOPROPOSTA);
        assertThat(testProposta.getObs()).isEqualTo(DEFAULT_OBS);
        assertThat(testProposta.getContrato()).isEqualTo(DEFAULT_CONTRATO);
        assertThat(testProposta.getHabilitada()).isEqualTo(DEFAULT_HABILITADA);
        assertThat(testProposta.getArqlink()).isEqualTo(DEFAULT_ARQLINK);
        assertThat(testProposta.getIdproposta()).isEqualTo(DEFAULT_IDPROPOSTA);
        assertThat(testProposta.getNumeroedital()).isEqualTo(DEFAULT_NUMEROEDITAL);
        assertThat(testProposta.getValorproposta()).isEqualTo(DEFAULT_VALORPROPOSTA);
        assertThat(testProposta.getValorrenegociado()).isEqualTo(DEFAULT_VALORRENEGOCIADO);
    }

    @Test
    @Transactional
    public void createPropostaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = propostaRepository.findAll().size();

        // Create the Proposta with an existing ID
        proposta.setId(1L);
        PropostaDTO propostaDTO = propostaMapper.toDto(proposta);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPropostaMockMvc.perform(post("/api/propostas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propostaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Proposta> propostaList = propostaRepository.findAll();
        assertThat(propostaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPropostas() throws Exception {
        // Initialize the database
        propostaRepository.saveAndFlush(proposta);

        // Get all the propostaList
        restPropostaMockMvc.perform(get("/api/propostas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proposta.getId().intValue())))
            .andExpect(jsonPath("$.[*].classificacao").value(hasItem(DEFAULT_CLASSIFICACAO.toString())))
            .andExpect(jsonPath("$.[*].nota").value(hasItem(DEFAULT_NOTA.toString())))
            .andExpect(jsonPath("$.[*].tipoproposta").value(hasItem(DEFAULT_TIPOPROPOSTA.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())))
            .andExpect(jsonPath("$.[*].contrato").value(hasItem(DEFAULT_CONTRATO.toString())))
            .andExpect(jsonPath("$.[*].habilitada").value(hasItem(DEFAULT_HABILITADA.toString())))
            .andExpect(jsonPath("$.[*].arqlink").value(hasItem(DEFAULT_ARQLINK.toString())))
            .andExpect(jsonPath("$.[*].idproposta").value(hasItem(DEFAULT_IDPROPOSTA)))
            .andExpect(jsonPath("$.[*].numeroedital").value(hasItem(DEFAULT_NUMEROEDITAL)))
            .andExpect(jsonPath("$.[*].valorproposta").value(hasItem(DEFAULT_VALORPROPOSTA.intValue())))
            .andExpect(jsonPath("$.[*].valorrenegociado").value(hasItem(DEFAULT_VALORRENEGOCIADO.intValue())));
    }

    @Test
    @Transactional
    public void getProposta() throws Exception {
        // Initialize the database
        propostaRepository.saveAndFlush(proposta);

        // Get the proposta
        restPropostaMockMvc.perform(get("/api/propostas/{id}", proposta.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(proposta.getId().intValue()))
            .andExpect(jsonPath("$.classificacao").value(DEFAULT_CLASSIFICACAO.toString()))
            .andExpect(jsonPath("$.nota").value(DEFAULT_NOTA.toString()))
            .andExpect(jsonPath("$.tipoproposta").value(DEFAULT_TIPOPROPOSTA.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()))
            .andExpect(jsonPath("$.contrato").value(DEFAULT_CONTRATO.toString()))
            .andExpect(jsonPath("$.habilitada").value(DEFAULT_HABILITADA.toString()))
            .andExpect(jsonPath("$.arqlink").value(DEFAULT_ARQLINK.toString()))
            .andExpect(jsonPath("$.idproposta").value(DEFAULT_IDPROPOSTA))
            .andExpect(jsonPath("$.numeroedital").value(DEFAULT_NUMEROEDITAL))
            .andExpect(jsonPath("$.valorproposta").value(DEFAULT_VALORPROPOSTA.intValue()))
            .andExpect(jsonPath("$.valorrenegociado").value(DEFAULT_VALORRENEGOCIADO.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingProposta() throws Exception {
        // Get the proposta
        restPropostaMockMvc.perform(get("/api/propostas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProposta() throws Exception {
        // Initialize the database
        propostaRepository.saveAndFlush(proposta);
        int databaseSizeBeforeUpdate = propostaRepository.findAll().size();

        // Update the proposta
        Proposta updatedProposta = propostaRepository.findOne(proposta.getId());
        updatedProposta
            .classificacao(UPDATED_CLASSIFICACAO)
            .nota(UPDATED_NOTA)
            .tipoproposta(UPDATED_TIPOPROPOSTA)
            .obs(UPDATED_OBS)
            .contrato(UPDATED_CONTRATO)
            .habilitada(UPDATED_HABILITADA)
            .arqlink(UPDATED_ARQLINK)
            .idproposta(UPDATED_IDPROPOSTA)
            .numeroedital(UPDATED_NUMEROEDITAL)
            .valorproposta(UPDATED_VALORPROPOSTA)
            .valorrenegociado(UPDATED_VALORRENEGOCIADO);
        PropostaDTO propostaDTO = propostaMapper.toDto(updatedProposta);

        restPropostaMockMvc.perform(put("/api/propostas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propostaDTO)))
            .andExpect(status().isOk());

        // Validate the Proposta in the database
        List<Proposta> propostaList = propostaRepository.findAll();
        assertThat(propostaList).hasSize(databaseSizeBeforeUpdate);
        Proposta testProposta = propostaList.get(propostaList.size() - 1);
        assertThat(testProposta.getClassificacao()).isEqualTo(UPDATED_CLASSIFICACAO);
        assertThat(testProposta.getNota()).isEqualTo(UPDATED_NOTA);
        assertThat(testProposta.getTipoproposta()).isEqualTo(UPDATED_TIPOPROPOSTA);
        assertThat(testProposta.getObs()).isEqualTo(UPDATED_OBS);
        assertThat(testProposta.getContrato()).isEqualTo(UPDATED_CONTRATO);
        assertThat(testProposta.getHabilitada()).isEqualTo(UPDATED_HABILITADA);
        assertThat(testProposta.getArqlink()).isEqualTo(UPDATED_ARQLINK);
        assertThat(testProposta.getIdproposta()).isEqualTo(UPDATED_IDPROPOSTA);
        assertThat(testProposta.getNumeroedital()).isEqualTo(UPDATED_NUMEROEDITAL);
        assertThat(testProposta.getValorproposta()).isEqualTo(UPDATED_VALORPROPOSTA);
        assertThat(testProposta.getValorrenegociado()).isEqualTo(UPDATED_VALORRENEGOCIADO);
    }

    @Test
    @Transactional
    public void updateNonExistingProposta() throws Exception {
        int databaseSizeBeforeUpdate = propostaRepository.findAll().size();

        // Create the Proposta
        PropostaDTO propostaDTO = propostaMapper.toDto(proposta);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPropostaMockMvc.perform(put("/api/propostas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propostaDTO)))
            .andExpect(status().isCreated());

        // Validate the Proposta in the database
        List<Proposta> propostaList = propostaRepository.findAll();
        assertThat(propostaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteProposta() throws Exception {
        // Initialize the database
        propostaRepository.saveAndFlush(proposta);
        int databaseSizeBeforeDelete = propostaRepository.findAll().size();

        // Get the proposta
        restPropostaMockMvc.perform(delete("/api/propostas/{id}", proposta.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Proposta> propostaList = propostaRepository.findAll();
        assertThat(propostaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Proposta.class);
        Proposta proposta1 = new Proposta();
        proposta1.setId(1L);
        Proposta proposta2 = new Proposta();
        proposta2.setId(proposta1.getId());
        assertThat(proposta1).isEqualTo(proposta2);
        proposta2.setId(2L);
        assertThat(proposta1).isNotEqualTo(proposta2);
        proposta1.setId(null);
        assertThat(proposta1).isNotEqualTo(proposta2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PropostaDTO.class);
        PropostaDTO propostaDTO1 = new PropostaDTO();
        propostaDTO1.setId(1L);
        PropostaDTO propostaDTO2 = new PropostaDTO();
        assertThat(propostaDTO1).isNotEqualTo(propostaDTO2);
        propostaDTO2.setId(propostaDTO1.getId());
        assertThat(propostaDTO1).isEqualTo(propostaDTO2);
        propostaDTO2.setId(2L);
        assertThat(propostaDTO1).isNotEqualTo(propostaDTO2);
        propostaDTO1.setId(null);
        assertThat(propostaDTO1).isNotEqualTo(propostaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(propostaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(propostaMapper.fromId(null)).isNull();
    }
}
