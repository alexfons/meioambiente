package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Banco;
import br.com.homemade.repository.BancoRepository;
import br.com.homemade.service.dto.BancoDTO;
import br.com.homemade.service.mapper.BancoMapper;
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
 * Test class for the BancoResource REST controller.
 *
 * @see BancoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class BancoResourceIntTest {

    private static final String DEFAULT_CIDADEDEP = "AAAAAAAAAA";
    private static final String UPDATED_CIDADEDEP = "BBBBBBBBBB";

    private static final String DEFAULT_CIDADEINT = "AAAAAAAAAA";
    private static final String UPDATED_CIDADEINT = "BBBBBBBBBB";

    private static final String DEFAULT_CODSWIFDEP = "AAAAAAAAAA";
    private static final String UPDATED_CODSWIFDEP = "BBBBBBBBBB";

    private static final String DEFAULT_CODSWIFINT = "AAAAAAAAAA";
    private static final String UPDATED_CODSWIFINT = "BBBBBBBBBB";

    private static final Boolean DEFAULT_CONTAFEDDEP = false;
    private static final Boolean UPDATED_CONTAFEDDEP = true;

    private static final Boolean DEFAULT_CONTAFEDINT = false;
    private static final Boolean UPDATED_CONTAFEDINT = true;

    private static final String DEFAULT_ENDERECODEP = "AAAAAAAAAA";
    private static final String UPDATED_ENDERECODEP = "BBBBBBBBBB";

    private static final String DEFAULT_ENDERECOINT = "AAAAAAAAAA";
    private static final String UPDATED_ENDERECOINT = "BBBBBBBBBB";

    private static final Integer DEFAULT_IDBANCO = 1;
    private static final Integer UPDATED_IDBANCO = 2;

    private static final String DEFAULT_INSTRUCOESESPECIAISDEP = "AAAAAAAAAA";
    private static final String UPDATED_INSTRUCOESESPECIAISDEP = "BBBBBBBBBB";

    private static final String DEFAULT_INSTRUCOESESPECIAISINT = "AAAAAAAAAA";
    private static final String UPDATED_INSTRUCOESESPECIAISINT = "BBBBBBBBBB";

    private static final Integer DEFAULT_NABADEP = 1;
    private static final Integer UPDATED_NABADEP = 2;

    private static final Integer DEFAULT_NABAINT = 1;
    private static final Integer UPDATED_NABAINT = 2;

    private static final Integer DEFAULT_NCONTADEP = 1;
    private static final Integer UPDATED_NCONTADEP = 2;

    private static final Integer DEFAULT_NCONTAINT = 1;
    private static final Integer UPDATED_NCONTAINT = 2;

    private static final String DEFAULT_NOMEBANCODEP = "AAAAAAAAAA";
    private static final String UPDATED_NOMEBANCODEP = "BBBBBBBBBB";

    private static final String DEFAULT_NOMEBANCOINT = "AAAAAAAAAA";
    private static final String UPDATED_NOMEBANCOINT = "BBBBBBBBBB";

    private static final String DEFAULT_PAISDEP = "AAAAAAAAAA";
    private static final String UPDATED_PAISDEP = "BBBBBBBBBB";

    private static final String DEFAULT_PAISINT = "AAAAAAAAAA";
    private static final String UPDATED_PAISINT = "BBBBBBBBBB";

    @Autowired
    private BancoRepository bancoRepository;

    @Autowired
    private BancoMapper bancoMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBancoMockMvc;

    private Banco banco;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        BancoResource bancoResource = new BancoResource(bancoRepository, bancoMapper);
        this.restBancoMockMvc = MockMvcBuilders.standaloneSetup(bancoResource)
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
    public static Banco createEntity(EntityManager em) {
        Banco banco = new Banco()
            .cidadedep(DEFAULT_CIDADEDEP)
            .cidadeint(DEFAULT_CIDADEINT)
            .codswifdep(DEFAULT_CODSWIFDEP)
            .codswifint(DEFAULT_CODSWIFINT)
            .contafeddep(DEFAULT_CONTAFEDDEP)
            .contafedint(DEFAULT_CONTAFEDINT)
            .enderecodep(DEFAULT_ENDERECODEP)
            .enderecoint(DEFAULT_ENDERECOINT)
            .idbanco(DEFAULT_IDBANCO)
            .instrucoesespeciaisdep(DEFAULT_INSTRUCOESESPECIAISDEP)
            .instrucoesespeciaisint(DEFAULT_INSTRUCOESESPECIAISINT)
            .nabadep(DEFAULT_NABADEP)
            .nabaint(DEFAULT_NABAINT)
            .ncontadep(DEFAULT_NCONTADEP)
            .ncontaint(DEFAULT_NCONTAINT)
            .nomebancodep(DEFAULT_NOMEBANCODEP)
            .nomebancoint(DEFAULT_NOMEBANCOINT)
            .paisdep(DEFAULT_PAISDEP)
            .paisint(DEFAULT_PAISINT);
        return banco;
    }

    @Before
    public void initTest() {
        banco = createEntity(em);
    }

    @Test
    @Transactional
    public void createBanco() throws Exception {
        int databaseSizeBeforeCreate = bancoRepository.findAll().size();

        // Create the Banco
        BancoDTO bancoDTO = bancoMapper.toDto(banco);
        restBancoMockMvc.perform(post("/api/bancos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bancoDTO)))
            .andExpect(status().isCreated());

        // Validate the Banco in the database
        List<Banco> bancoList = bancoRepository.findAll();
        assertThat(bancoList).hasSize(databaseSizeBeforeCreate + 1);
        Banco testBanco = bancoList.get(bancoList.size() - 1);
        assertThat(testBanco.getCidadedep()).isEqualTo(DEFAULT_CIDADEDEP);
        assertThat(testBanco.getCidadeint()).isEqualTo(DEFAULT_CIDADEINT);
        assertThat(testBanco.getCodswifdep()).isEqualTo(DEFAULT_CODSWIFDEP);
        assertThat(testBanco.getCodswifint()).isEqualTo(DEFAULT_CODSWIFINT);
        assertThat(testBanco.isContafeddep()).isEqualTo(DEFAULT_CONTAFEDDEP);
        assertThat(testBanco.isContafedint()).isEqualTo(DEFAULT_CONTAFEDINT);
        assertThat(testBanco.getEnderecodep()).isEqualTo(DEFAULT_ENDERECODEP);
        assertThat(testBanco.getEnderecoint()).isEqualTo(DEFAULT_ENDERECOINT);
        assertThat(testBanco.getIdbanco()).isEqualTo(DEFAULT_IDBANCO);
        assertThat(testBanco.getInstrucoesespeciaisdep()).isEqualTo(DEFAULT_INSTRUCOESESPECIAISDEP);
        assertThat(testBanco.getInstrucoesespeciaisint()).isEqualTo(DEFAULT_INSTRUCOESESPECIAISINT);
        assertThat(testBanco.getNabadep()).isEqualTo(DEFAULT_NABADEP);
        assertThat(testBanco.getNabaint()).isEqualTo(DEFAULT_NABAINT);
        assertThat(testBanco.getNcontadep()).isEqualTo(DEFAULT_NCONTADEP);
        assertThat(testBanco.getNcontaint()).isEqualTo(DEFAULT_NCONTAINT);
        assertThat(testBanco.getNomebancodep()).isEqualTo(DEFAULT_NOMEBANCODEP);
        assertThat(testBanco.getNomebancoint()).isEqualTo(DEFAULT_NOMEBANCOINT);
        assertThat(testBanco.getPaisdep()).isEqualTo(DEFAULT_PAISDEP);
        assertThat(testBanco.getPaisint()).isEqualTo(DEFAULT_PAISINT);
    }

    @Test
    @Transactional
    public void createBancoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bancoRepository.findAll().size();

        // Create the Banco with an existing ID
        banco.setId(1L);
        BancoDTO bancoDTO = bancoMapper.toDto(banco);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBancoMockMvc.perform(post("/api/bancos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bancoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Banco> bancoList = bancoRepository.findAll();
        assertThat(bancoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBancos() throws Exception {
        // Initialize the database
        bancoRepository.saveAndFlush(banco);

        // Get all the bancoList
        restBancoMockMvc.perform(get("/api/bancos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(banco.getId().intValue())))
            .andExpect(jsonPath("$.[*].cidadedep").value(hasItem(DEFAULT_CIDADEDEP.toString())))
            .andExpect(jsonPath("$.[*].cidadeint").value(hasItem(DEFAULT_CIDADEINT.toString())))
            .andExpect(jsonPath("$.[*].codswifdep").value(hasItem(DEFAULT_CODSWIFDEP.toString())))
            .andExpect(jsonPath("$.[*].codswifint").value(hasItem(DEFAULT_CODSWIFINT.toString())))
            .andExpect(jsonPath("$.[*].contafeddep").value(hasItem(DEFAULT_CONTAFEDDEP.booleanValue())))
            .andExpect(jsonPath("$.[*].contafedint").value(hasItem(DEFAULT_CONTAFEDINT.booleanValue())))
            .andExpect(jsonPath("$.[*].enderecodep").value(hasItem(DEFAULT_ENDERECODEP.toString())))
            .andExpect(jsonPath("$.[*].enderecoint").value(hasItem(DEFAULT_ENDERECOINT.toString())))
            .andExpect(jsonPath("$.[*].idbanco").value(hasItem(DEFAULT_IDBANCO)))
            .andExpect(jsonPath("$.[*].instrucoesespeciaisdep").value(hasItem(DEFAULT_INSTRUCOESESPECIAISDEP.toString())))
            .andExpect(jsonPath("$.[*].instrucoesespeciaisint").value(hasItem(DEFAULT_INSTRUCOESESPECIAISINT.toString())))
            .andExpect(jsonPath("$.[*].nabadep").value(hasItem(DEFAULT_NABADEP)))
            .andExpect(jsonPath("$.[*].nabaint").value(hasItem(DEFAULT_NABAINT)))
            .andExpect(jsonPath("$.[*].ncontadep").value(hasItem(DEFAULT_NCONTADEP)))
            .andExpect(jsonPath("$.[*].ncontaint").value(hasItem(DEFAULT_NCONTAINT)))
            .andExpect(jsonPath("$.[*].nomebancodep").value(hasItem(DEFAULT_NOMEBANCODEP.toString())))
            .andExpect(jsonPath("$.[*].nomebancoint").value(hasItem(DEFAULT_NOMEBANCOINT.toString())))
            .andExpect(jsonPath("$.[*].paisdep").value(hasItem(DEFAULT_PAISDEP.toString())))
            .andExpect(jsonPath("$.[*].paisint").value(hasItem(DEFAULT_PAISINT.toString())));
    }

    @Test
    @Transactional
    public void getBanco() throws Exception {
        // Initialize the database
        bancoRepository.saveAndFlush(banco);

        // Get the banco
        restBancoMockMvc.perform(get("/api/bancos/{id}", banco.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(banco.getId().intValue()))
            .andExpect(jsonPath("$.cidadedep").value(DEFAULT_CIDADEDEP.toString()))
            .andExpect(jsonPath("$.cidadeint").value(DEFAULT_CIDADEINT.toString()))
            .andExpect(jsonPath("$.codswifdep").value(DEFAULT_CODSWIFDEP.toString()))
            .andExpect(jsonPath("$.codswifint").value(DEFAULT_CODSWIFINT.toString()))
            .andExpect(jsonPath("$.contafeddep").value(DEFAULT_CONTAFEDDEP.booleanValue()))
            .andExpect(jsonPath("$.contafedint").value(DEFAULT_CONTAFEDINT.booleanValue()))
            .andExpect(jsonPath("$.enderecodep").value(DEFAULT_ENDERECODEP.toString()))
            .andExpect(jsonPath("$.enderecoint").value(DEFAULT_ENDERECOINT.toString()))
            .andExpect(jsonPath("$.idbanco").value(DEFAULT_IDBANCO))
            .andExpect(jsonPath("$.instrucoesespeciaisdep").value(DEFAULT_INSTRUCOESESPECIAISDEP.toString()))
            .andExpect(jsonPath("$.instrucoesespeciaisint").value(DEFAULT_INSTRUCOESESPECIAISINT.toString()))
            .andExpect(jsonPath("$.nabadep").value(DEFAULT_NABADEP))
            .andExpect(jsonPath("$.nabaint").value(DEFAULT_NABAINT))
            .andExpect(jsonPath("$.ncontadep").value(DEFAULT_NCONTADEP))
            .andExpect(jsonPath("$.ncontaint").value(DEFAULT_NCONTAINT))
            .andExpect(jsonPath("$.nomebancodep").value(DEFAULT_NOMEBANCODEP.toString()))
            .andExpect(jsonPath("$.nomebancoint").value(DEFAULT_NOMEBANCOINT.toString()))
            .andExpect(jsonPath("$.paisdep").value(DEFAULT_PAISDEP.toString()))
            .andExpect(jsonPath("$.paisint").value(DEFAULT_PAISINT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBanco() throws Exception {
        // Get the banco
        restBancoMockMvc.perform(get("/api/bancos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBanco() throws Exception {
        // Initialize the database
        bancoRepository.saveAndFlush(banco);
        int databaseSizeBeforeUpdate = bancoRepository.findAll().size();

        // Update the banco
        Banco updatedBanco = bancoRepository.findOne(banco.getId());
        updatedBanco
            .cidadedep(UPDATED_CIDADEDEP)
            .cidadeint(UPDATED_CIDADEINT)
            .codswifdep(UPDATED_CODSWIFDEP)
            .codswifint(UPDATED_CODSWIFINT)
            .contafeddep(UPDATED_CONTAFEDDEP)
            .contafedint(UPDATED_CONTAFEDINT)
            .enderecodep(UPDATED_ENDERECODEP)
            .enderecoint(UPDATED_ENDERECOINT)
            .idbanco(UPDATED_IDBANCO)
            .instrucoesespeciaisdep(UPDATED_INSTRUCOESESPECIAISDEP)
            .instrucoesespeciaisint(UPDATED_INSTRUCOESESPECIAISINT)
            .nabadep(UPDATED_NABADEP)
            .nabaint(UPDATED_NABAINT)
            .ncontadep(UPDATED_NCONTADEP)
            .ncontaint(UPDATED_NCONTAINT)
            .nomebancodep(UPDATED_NOMEBANCODEP)
            .nomebancoint(UPDATED_NOMEBANCOINT)
            .paisdep(UPDATED_PAISDEP)
            .paisint(UPDATED_PAISINT);
        BancoDTO bancoDTO = bancoMapper.toDto(updatedBanco);

        restBancoMockMvc.perform(put("/api/bancos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bancoDTO)))
            .andExpect(status().isOk());

        // Validate the Banco in the database
        List<Banco> bancoList = bancoRepository.findAll();
        assertThat(bancoList).hasSize(databaseSizeBeforeUpdate);
        Banco testBanco = bancoList.get(bancoList.size() - 1);
        assertThat(testBanco.getCidadedep()).isEqualTo(UPDATED_CIDADEDEP);
        assertThat(testBanco.getCidadeint()).isEqualTo(UPDATED_CIDADEINT);
        assertThat(testBanco.getCodswifdep()).isEqualTo(UPDATED_CODSWIFDEP);
        assertThat(testBanco.getCodswifint()).isEqualTo(UPDATED_CODSWIFINT);
        assertThat(testBanco.isContafeddep()).isEqualTo(UPDATED_CONTAFEDDEP);
        assertThat(testBanco.isContafedint()).isEqualTo(UPDATED_CONTAFEDINT);
        assertThat(testBanco.getEnderecodep()).isEqualTo(UPDATED_ENDERECODEP);
        assertThat(testBanco.getEnderecoint()).isEqualTo(UPDATED_ENDERECOINT);
        assertThat(testBanco.getIdbanco()).isEqualTo(UPDATED_IDBANCO);
        assertThat(testBanco.getInstrucoesespeciaisdep()).isEqualTo(UPDATED_INSTRUCOESESPECIAISDEP);
        assertThat(testBanco.getInstrucoesespeciaisint()).isEqualTo(UPDATED_INSTRUCOESESPECIAISINT);
        assertThat(testBanco.getNabadep()).isEqualTo(UPDATED_NABADEP);
        assertThat(testBanco.getNabaint()).isEqualTo(UPDATED_NABAINT);
        assertThat(testBanco.getNcontadep()).isEqualTo(UPDATED_NCONTADEP);
        assertThat(testBanco.getNcontaint()).isEqualTo(UPDATED_NCONTAINT);
        assertThat(testBanco.getNomebancodep()).isEqualTo(UPDATED_NOMEBANCODEP);
        assertThat(testBanco.getNomebancoint()).isEqualTo(UPDATED_NOMEBANCOINT);
        assertThat(testBanco.getPaisdep()).isEqualTo(UPDATED_PAISDEP);
        assertThat(testBanco.getPaisint()).isEqualTo(UPDATED_PAISINT);
    }

    @Test
    @Transactional
    public void updateNonExistingBanco() throws Exception {
        int databaseSizeBeforeUpdate = bancoRepository.findAll().size();

        // Create the Banco
        BancoDTO bancoDTO = bancoMapper.toDto(banco);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBancoMockMvc.perform(put("/api/bancos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bancoDTO)))
            .andExpect(status().isCreated());

        // Validate the Banco in the database
        List<Banco> bancoList = bancoRepository.findAll();
        assertThat(bancoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteBanco() throws Exception {
        // Initialize the database
        bancoRepository.saveAndFlush(banco);
        int databaseSizeBeforeDelete = bancoRepository.findAll().size();

        // Get the banco
        restBancoMockMvc.perform(delete("/api/bancos/{id}", banco.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Banco> bancoList = bancoRepository.findAll();
        assertThat(bancoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Banco.class);
        Banco banco1 = new Banco();
        banco1.setId(1L);
        Banco banco2 = new Banco();
        banco2.setId(banco1.getId());
        assertThat(banco1).isEqualTo(banco2);
        banco2.setId(2L);
        assertThat(banco1).isNotEqualTo(banco2);
        banco1.setId(null);
        assertThat(banco1).isNotEqualTo(banco2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BancoDTO.class);
        BancoDTO bancoDTO1 = new BancoDTO();
        bancoDTO1.setId(1L);
        BancoDTO bancoDTO2 = new BancoDTO();
        assertThat(bancoDTO1).isNotEqualTo(bancoDTO2);
        bancoDTO2.setId(bancoDTO1.getId());
        assertThat(bancoDTO1).isEqualTo(bancoDTO2);
        bancoDTO2.setId(2L);
        assertThat(bancoDTO1).isNotEqualTo(bancoDTO2);
        bancoDTO1.setId(null);
        assertThat(bancoDTO1).isNotEqualTo(bancoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(bancoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(bancoMapper.fromId(null)).isNull();
    }
}
