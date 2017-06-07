package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Administrativo;
import br.com.homemade.repository.AdministrativoRepository;
import br.com.homemade.service.dto.AdministrativoDTO;
import br.com.homemade.service.mapper.AdministrativoMapper;
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
 * Test class for the AdministrativoResource REST controller.
 *
 * @see AdministrativoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class AdministrativoResourceIntTest {

    private static final String DEFAULT_ALBUM = "AAAAAAAAAA";
    private static final String UPDATED_ALBUM = "BBBBBBBBBB";

    private static final String DEFAULT_ASSUNTO = "AAAAAAAAAA";
    private static final String UPDATED_ASSUNTO = "BBBBBBBBBB";

    private static final String DEFAULT_CONSIDERACAO = "AAAAAAAAAA";
    private static final String UPDATED_CONSIDERACAO = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final String DEFAULT_FOLDER = "AAAAAAAAAA";
    private static final String UPDATED_FOLDER = "BBBBBBBBBB";

    private static final String DEFAULT_LOCAL = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL = "BBBBBBBBBB";

    @Autowired
    private AdministrativoRepository administrativoRepository;

    @Autowired
    private AdministrativoMapper administrativoMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAdministrativoMockMvc;

    private Administrativo administrativo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AdministrativoResource administrativoResource = new AdministrativoResource(administrativoRepository, administrativoMapper);
        this.restAdministrativoMockMvc = MockMvcBuilders.standaloneSetup(administrativoResource)
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
    public static Administrativo createEntity(EntityManager em) {
        Administrativo administrativo = new Administrativo()
            .album(DEFAULT_ALBUM)
            .assunto(DEFAULT_ASSUNTO)
            .consideracao(DEFAULT_CONSIDERACAO)
            .data(DEFAULT_DATA)
            .descricao(DEFAULT_DESCRICAO)
            .folder(DEFAULT_FOLDER)
            .local(DEFAULT_LOCAL);
        return administrativo;
    }

    @Before
    public void initTest() {
        administrativo = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdministrativo() throws Exception {
        int databaseSizeBeforeCreate = administrativoRepository.findAll().size();

        // Create the Administrativo
        AdministrativoDTO administrativoDTO = administrativoMapper.toDto(administrativo);
        restAdministrativoMockMvc.perform(post("/api/administrativos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(administrativoDTO)))
            .andExpect(status().isCreated());

        // Validate the Administrativo in the database
        List<Administrativo> administrativoList = administrativoRepository.findAll();
        assertThat(administrativoList).hasSize(databaseSizeBeforeCreate + 1);
        Administrativo testAdministrativo = administrativoList.get(administrativoList.size() - 1);
        assertThat(testAdministrativo.getAlbum()).isEqualTo(DEFAULT_ALBUM);
        assertThat(testAdministrativo.getAssunto()).isEqualTo(DEFAULT_ASSUNTO);
        assertThat(testAdministrativo.getConsideracao()).isEqualTo(DEFAULT_CONSIDERACAO);
        assertThat(testAdministrativo.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testAdministrativo.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testAdministrativo.getFolder()).isEqualTo(DEFAULT_FOLDER);
        assertThat(testAdministrativo.getLocal()).isEqualTo(DEFAULT_LOCAL);
    }

    @Test
    @Transactional
    public void createAdministrativoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = administrativoRepository.findAll().size();

        // Create the Administrativo with an existing ID
        administrativo.setId(1L);
        AdministrativoDTO administrativoDTO = administrativoMapper.toDto(administrativo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdministrativoMockMvc.perform(post("/api/administrativos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(administrativoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Administrativo> administrativoList = administrativoRepository.findAll();
        assertThat(administrativoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAdministrativos() throws Exception {
        // Initialize the database
        administrativoRepository.saveAndFlush(administrativo);

        // Get all the administrativoList
        restAdministrativoMockMvc.perform(get("/api/administrativos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(administrativo.getId().intValue())))
            .andExpect(jsonPath("$.[*].album").value(hasItem(DEFAULT_ALBUM.toString())))
            .andExpect(jsonPath("$.[*].assunto").value(hasItem(DEFAULT_ASSUNTO.toString())))
            .andExpect(jsonPath("$.[*].consideracao").value(hasItem(DEFAULT_CONSIDERACAO.toString())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(sameInstant(DEFAULT_DATA))))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].folder").value(hasItem(DEFAULT_FOLDER.toString())))
            .andExpect(jsonPath("$.[*].local").value(hasItem(DEFAULT_LOCAL.toString())));
    }

    @Test
    @Transactional
    public void getAdministrativo() throws Exception {
        // Initialize the database
        administrativoRepository.saveAndFlush(administrativo);

        // Get the administrativo
        restAdministrativoMockMvc.perform(get("/api/administrativos/{id}", administrativo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(administrativo.getId().intValue()))
            .andExpect(jsonPath("$.album").value(DEFAULT_ALBUM.toString()))
            .andExpect(jsonPath("$.assunto").value(DEFAULT_ASSUNTO.toString()))
            .andExpect(jsonPath("$.consideracao").value(DEFAULT_CONSIDERACAO.toString()))
            .andExpect(jsonPath("$.data").value(sameInstant(DEFAULT_DATA)))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.folder").value(DEFAULT_FOLDER.toString()))
            .andExpect(jsonPath("$.local").value(DEFAULT_LOCAL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAdministrativo() throws Exception {
        // Get the administrativo
        restAdministrativoMockMvc.perform(get("/api/administrativos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdministrativo() throws Exception {
        // Initialize the database
        administrativoRepository.saveAndFlush(administrativo);
        int databaseSizeBeforeUpdate = administrativoRepository.findAll().size();

        // Update the administrativo
        Administrativo updatedAdministrativo = administrativoRepository.findOne(administrativo.getId());
        updatedAdministrativo
            .album(UPDATED_ALBUM)
            .assunto(UPDATED_ASSUNTO)
            .consideracao(UPDATED_CONSIDERACAO)
            .data(UPDATED_DATA)
            .descricao(UPDATED_DESCRICAO)
            .folder(UPDATED_FOLDER)
            .local(UPDATED_LOCAL);
        AdministrativoDTO administrativoDTO = administrativoMapper.toDto(updatedAdministrativo);

        restAdministrativoMockMvc.perform(put("/api/administrativos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(administrativoDTO)))
            .andExpect(status().isOk());

        // Validate the Administrativo in the database
        List<Administrativo> administrativoList = administrativoRepository.findAll();
        assertThat(administrativoList).hasSize(databaseSizeBeforeUpdate);
        Administrativo testAdministrativo = administrativoList.get(administrativoList.size() - 1);
        assertThat(testAdministrativo.getAlbum()).isEqualTo(UPDATED_ALBUM);
        assertThat(testAdministrativo.getAssunto()).isEqualTo(UPDATED_ASSUNTO);
        assertThat(testAdministrativo.getConsideracao()).isEqualTo(UPDATED_CONSIDERACAO);
        assertThat(testAdministrativo.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testAdministrativo.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testAdministrativo.getFolder()).isEqualTo(UPDATED_FOLDER);
        assertThat(testAdministrativo.getLocal()).isEqualTo(UPDATED_LOCAL);
    }

    @Test
    @Transactional
    public void updateNonExistingAdministrativo() throws Exception {
        int databaseSizeBeforeUpdate = administrativoRepository.findAll().size();

        // Create the Administrativo
        AdministrativoDTO administrativoDTO = administrativoMapper.toDto(administrativo);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAdministrativoMockMvc.perform(put("/api/administrativos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(administrativoDTO)))
            .andExpect(status().isCreated());

        // Validate the Administrativo in the database
        List<Administrativo> administrativoList = administrativoRepository.findAll();
        assertThat(administrativoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAdministrativo() throws Exception {
        // Initialize the database
        administrativoRepository.saveAndFlush(administrativo);
        int databaseSizeBeforeDelete = administrativoRepository.findAll().size();

        // Get the administrativo
        restAdministrativoMockMvc.perform(delete("/api/administrativos/{id}", administrativo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Administrativo> administrativoList = administrativoRepository.findAll();
        assertThat(administrativoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Administrativo.class);
        Administrativo administrativo1 = new Administrativo();
        administrativo1.setId(1L);
        Administrativo administrativo2 = new Administrativo();
        administrativo2.setId(administrativo1.getId());
        assertThat(administrativo1).isEqualTo(administrativo2);
        administrativo2.setId(2L);
        assertThat(administrativo1).isNotEqualTo(administrativo2);
        administrativo1.setId(null);
        assertThat(administrativo1).isNotEqualTo(administrativo2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdministrativoDTO.class);
        AdministrativoDTO administrativoDTO1 = new AdministrativoDTO();
        administrativoDTO1.setId(1L);
        AdministrativoDTO administrativoDTO2 = new AdministrativoDTO();
        assertThat(administrativoDTO1).isNotEqualTo(administrativoDTO2);
        administrativoDTO2.setId(administrativoDTO1.getId());
        assertThat(administrativoDTO1).isEqualTo(administrativoDTO2);
        administrativoDTO2.setId(2L);
        assertThat(administrativoDTO1).isNotEqualTo(administrativoDTO2);
        administrativoDTO1.setId(null);
        assertThat(administrativoDTO1).isNotEqualTo(administrativoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(administrativoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(administrativoMapper.fromId(null)).isNull();
    }
}
