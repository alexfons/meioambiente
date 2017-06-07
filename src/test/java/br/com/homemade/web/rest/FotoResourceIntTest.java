package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Foto;
import br.com.homemade.repository.FotoRepository;
import br.com.homemade.service.dto.FotoDTO;
import br.com.homemade.service.mapper.FotoMapper;
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
 * Test class for the FotoResource REST controller.
 *
 * @see FotoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class FotoResourceIntTest {

    private static final String DEFAULT_COORDENADAA = "AAAAAAAAAA";
    private static final String UPDATED_COORDENADAA = "BBBBBBBBBB";

    private static final String DEFAULT_COORDENADAE = "AAAAAAAAAA";
    private static final String UPDATED_COORDENADAE = "BBBBBBBBBB";

    private static final String DEFAULT_COORDENADAN = "AAAAAAAAAA";
    private static final String UPDATED_COORDENADAN = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_DESCRICAO_STRING = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO_STRING = "BBBBBBBBBB";

    private static final String DEFAULT_FOTO = "AAAAAAAAAA";
    private static final String UPDATED_FOTO = "BBBBBBBBBB";

    private static final String DEFAULT_LADO = "AAAAAAAAAA";
    private static final String UPDATED_LADO = "BBBBBBBBBB";

    private static final String DEFAULT_LINK = "AAAAAAAAAA";
    private static final String UPDATED_LINK = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMERO = 1;
    private static final Integer UPDATED_NUMERO = 2;

    private static final String DEFAULT_PICASA_ID = "AAAAAAAAAA";
    private static final String UPDATED_PICASA_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_PONTO = 1;
    private static final Integer UPDATED_PONTO = 2;

    private static final String DEFAULT_THUMB = "AAAAAAAAAA";
    private static final String UPDATED_THUMB = "BBBBBBBBBB";

    @Autowired
    private FotoRepository fotoRepository;

    @Autowired
    private FotoMapper fotoMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFotoMockMvc;

    private Foto foto;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        FotoResource fotoResource = new FotoResource(fotoRepository, fotoMapper);
        this.restFotoMockMvc = MockMvcBuilders.standaloneSetup(fotoResource)
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
    public static Foto createEntity(EntityManager em) {
        Foto foto = new Foto()
            .coordenadaa(DEFAULT_COORDENADAA)
            .coordenadae(DEFAULT_COORDENADAE)
            .coordenadan(DEFAULT_COORDENADAN)
            .data(DEFAULT_DATA)
            .descricaoString(DEFAULT_DESCRICAO_STRING)
            .foto(DEFAULT_FOTO)
            .lado(DEFAULT_LADO)
            .link(DEFAULT_LINK)
            .numero(DEFAULT_NUMERO)
            .picasaId(DEFAULT_PICASA_ID)
            .ponto(DEFAULT_PONTO)
            .thumb(DEFAULT_THUMB);
        return foto;
    }

    @Before
    public void initTest() {
        foto = createEntity(em);
    }

    @Test
    @Transactional
    public void createFoto() throws Exception {
        int databaseSizeBeforeCreate = fotoRepository.findAll().size();

        // Create the Foto
        FotoDTO fotoDTO = fotoMapper.toDto(foto);
        restFotoMockMvc.perform(post("/api/fotos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fotoDTO)))
            .andExpect(status().isCreated());

        // Validate the Foto in the database
        List<Foto> fotoList = fotoRepository.findAll();
        assertThat(fotoList).hasSize(databaseSizeBeforeCreate + 1);
        Foto testFoto = fotoList.get(fotoList.size() - 1);
        assertThat(testFoto.getCoordenadaa()).isEqualTo(DEFAULT_COORDENADAA);
        assertThat(testFoto.getCoordenadae()).isEqualTo(DEFAULT_COORDENADAE);
        assertThat(testFoto.getCoordenadan()).isEqualTo(DEFAULT_COORDENADAN);
        assertThat(testFoto.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testFoto.getDescricaoString()).isEqualTo(DEFAULT_DESCRICAO_STRING);
        assertThat(testFoto.getFoto()).isEqualTo(DEFAULT_FOTO);
        assertThat(testFoto.getLado()).isEqualTo(DEFAULT_LADO);
        assertThat(testFoto.getLink()).isEqualTo(DEFAULT_LINK);
        assertThat(testFoto.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testFoto.getPicasaId()).isEqualTo(DEFAULT_PICASA_ID);
        assertThat(testFoto.getPonto()).isEqualTo(DEFAULT_PONTO);
        assertThat(testFoto.getThumb()).isEqualTo(DEFAULT_THUMB);
    }

    @Test
    @Transactional
    public void createFotoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fotoRepository.findAll().size();

        // Create the Foto with an existing ID
        foto.setId(1L);
        FotoDTO fotoDTO = fotoMapper.toDto(foto);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFotoMockMvc.perform(post("/api/fotos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fotoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Foto> fotoList = fotoRepository.findAll();
        assertThat(fotoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFotos() throws Exception {
        // Initialize the database
        fotoRepository.saveAndFlush(foto);

        // Get all the fotoList
        restFotoMockMvc.perform(get("/api/fotos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(foto.getId().intValue())))
            .andExpect(jsonPath("$.[*].coordenadaa").value(hasItem(DEFAULT_COORDENADAA.toString())))
            .andExpect(jsonPath("$.[*].coordenadae").value(hasItem(DEFAULT_COORDENADAE.toString())))
            .andExpect(jsonPath("$.[*].coordenadan").value(hasItem(DEFAULT_COORDENADAN.toString())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(sameInstant(DEFAULT_DATA))))
            .andExpect(jsonPath("$.[*].descricaoString").value(hasItem(DEFAULT_DESCRICAO_STRING.toString())))
            .andExpect(jsonPath("$.[*].foto").value(hasItem(DEFAULT_FOTO.toString())))
            .andExpect(jsonPath("$.[*].lado").value(hasItem(DEFAULT_LADO.toString())))
            .andExpect(jsonPath("$.[*].link").value(hasItem(DEFAULT_LINK.toString())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO)))
            .andExpect(jsonPath("$.[*].picasaId").value(hasItem(DEFAULT_PICASA_ID.toString())))
            .andExpect(jsonPath("$.[*].ponto").value(hasItem(DEFAULT_PONTO)))
            .andExpect(jsonPath("$.[*].thumb").value(hasItem(DEFAULT_THUMB.toString())));
    }

    @Test
    @Transactional
    public void getFoto() throws Exception {
        // Initialize the database
        fotoRepository.saveAndFlush(foto);

        // Get the foto
        restFotoMockMvc.perform(get("/api/fotos/{id}", foto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(foto.getId().intValue()))
            .andExpect(jsonPath("$.coordenadaa").value(DEFAULT_COORDENADAA.toString()))
            .andExpect(jsonPath("$.coordenadae").value(DEFAULT_COORDENADAE.toString()))
            .andExpect(jsonPath("$.coordenadan").value(DEFAULT_COORDENADAN.toString()))
            .andExpect(jsonPath("$.data").value(sameInstant(DEFAULT_DATA)))
            .andExpect(jsonPath("$.descricaoString").value(DEFAULT_DESCRICAO_STRING.toString()))
            .andExpect(jsonPath("$.foto").value(DEFAULT_FOTO.toString()))
            .andExpect(jsonPath("$.lado").value(DEFAULT_LADO.toString()))
            .andExpect(jsonPath("$.link").value(DEFAULT_LINK.toString()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO))
            .andExpect(jsonPath("$.picasaId").value(DEFAULT_PICASA_ID.toString()))
            .andExpect(jsonPath("$.ponto").value(DEFAULT_PONTO))
            .andExpect(jsonPath("$.thumb").value(DEFAULT_THUMB.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFoto() throws Exception {
        // Get the foto
        restFotoMockMvc.perform(get("/api/fotos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFoto() throws Exception {
        // Initialize the database
        fotoRepository.saveAndFlush(foto);
        int databaseSizeBeforeUpdate = fotoRepository.findAll().size();

        // Update the foto
        Foto updatedFoto = fotoRepository.findOne(foto.getId());
        updatedFoto
            .coordenadaa(UPDATED_COORDENADAA)
            .coordenadae(UPDATED_COORDENADAE)
            .coordenadan(UPDATED_COORDENADAN)
            .data(UPDATED_DATA)
            .descricaoString(UPDATED_DESCRICAO_STRING)
            .foto(UPDATED_FOTO)
            .lado(UPDATED_LADO)
            .link(UPDATED_LINK)
            .numero(UPDATED_NUMERO)
            .picasaId(UPDATED_PICASA_ID)
            .ponto(UPDATED_PONTO)
            .thumb(UPDATED_THUMB);
        FotoDTO fotoDTO = fotoMapper.toDto(updatedFoto);

        restFotoMockMvc.perform(put("/api/fotos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fotoDTO)))
            .andExpect(status().isOk());

        // Validate the Foto in the database
        List<Foto> fotoList = fotoRepository.findAll();
        assertThat(fotoList).hasSize(databaseSizeBeforeUpdate);
        Foto testFoto = fotoList.get(fotoList.size() - 1);
        assertThat(testFoto.getCoordenadaa()).isEqualTo(UPDATED_COORDENADAA);
        assertThat(testFoto.getCoordenadae()).isEqualTo(UPDATED_COORDENADAE);
        assertThat(testFoto.getCoordenadan()).isEqualTo(UPDATED_COORDENADAN);
        assertThat(testFoto.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testFoto.getDescricaoString()).isEqualTo(UPDATED_DESCRICAO_STRING);
        assertThat(testFoto.getFoto()).isEqualTo(UPDATED_FOTO);
        assertThat(testFoto.getLado()).isEqualTo(UPDATED_LADO);
        assertThat(testFoto.getLink()).isEqualTo(UPDATED_LINK);
        assertThat(testFoto.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testFoto.getPicasaId()).isEqualTo(UPDATED_PICASA_ID);
        assertThat(testFoto.getPonto()).isEqualTo(UPDATED_PONTO);
        assertThat(testFoto.getThumb()).isEqualTo(UPDATED_THUMB);
    }

    @Test
    @Transactional
    public void updateNonExistingFoto() throws Exception {
        int databaseSizeBeforeUpdate = fotoRepository.findAll().size();

        // Create the Foto
        FotoDTO fotoDTO = fotoMapper.toDto(foto);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFotoMockMvc.perform(put("/api/fotos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fotoDTO)))
            .andExpect(status().isCreated());

        // Validate the Foto in the database
        List<Foto> fotoList = fotoRepository.findAll();
        assertThat(fotoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFoto() throws Exception {
        // Initialize the database
        fotoRepository.saveAndFlush(foto);
        int databaseSizeBeforeDelete = fotoRepository.findAll().size();

        // Get the foto
        restFotoMockMvc.perform(delete("/api/fotos/{id}", foto.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Foto> fotoList = fotoRepository.findAll();
        assertThat(fotoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Foto.class);
        Foto foto1 = new Foto();
        foto1.setId(1L);
        Foto foto2 = new Foto();
        foto2.setId(foto1.getId());
        assertThat(foto1).isEqualTo(foto2);
        foto2.setId(2L);
        assertThat(foto1).isNotEqualTo(foto2);
        foto1.setId(null);
        assertThat(foto1).isNotEqualTo(foto2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FotoDTO.class);
        FotoDTO fotoDTO1 = new FotoDTO();
        fotoDTO1.setId(1L);
        FotoDTO fotoDTO2 = new FotoDTO();
        assertThat(fotoDTO1).isNotEqualTo(fotoDTO2);
        fotoDTO2.setId(fotoDTO1.getId());
        assertThat(fotoDTO1).isEqualTo(fotoDTO2);
        fotoDTO2.setId(2L);
        assertThat(fotoDTO1).isNotEqualTo(fotoDTO2);
        fotoDTO1.setId(null);
        assertThat(fotoDTO1).isNotEqualTo(fotoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(fotoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(fotoMapper.fromId(null)).isNull();
    }
}
