package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Categoriainversao;
import br.com.homemade.repository.CategoriainversaoRepository;
import br.com.homemade.service.dto.CategoriainversaoDTO;
import br.com.homemade.service.mapper.CategoriainversaoMapper;
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
 * Test class for the CategoriainversaoResource REST controller.
 *
 * @see CategoriainversaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class CategoriainversaoResourceIntTest {

    private static final String DEFAULT_CODCATEGORIA = "AAAAAAAAAA";
    private static final String UPDATED_CODCATEGORIA = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRICAOCATEGORIA = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAOCATEGORIA = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRICAOITEM = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAOITEM = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRICAOSUBCATEGORIA = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAOSUBCATEGORIA = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRICAOSUBITEM = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAOSUBITEM = "BBBBBBBBBB";

    private static final Integer DEFAULT_IDCATEGORIAINVERSAO = 1;
    private static final Integer UPDATED_IDCATEGORIAINVERSAO = 2;

    private static final String DEFAULT_ITEM = "AAAAAAAAAA";
    private static final String UPDATED_ITEM = "BBBBBBBBBB";

    private static final Double DEFAULT_PERCENTUALCATAGENTE = 1D;
    private static final Double UPDATED_PERCENTUALCATAGENTE = 2D;

    private static final Double DEFAULT_PERCENTUALCATLOCAL = 1D;
    private static final Double UPDATED_PERCENTUALCATLOCAL = 2D;

    private static final String DEFAULT_SUBCATEGORIA = "AAAAAAAAAA";
    private static final String UPDATED_SUBCATEGORIA = "BBBBBBBBBB";

    private static final String DEFAULT_SUBITEM = "AAAAAAAAAA";
    private static final String UPDATED_SUBITEM = "BBBBBBBBBB";

    private static final Double DEFAULT_VALORCATAGENTE = 1D;
    private static final Double UPDATED_VALORCATAGENTE = 2D;

    private static final Double DEFAULT_VALORCATLOCAL = 1D;
    private static final Double UPDATED_VALORCATLOCAL = 2D;

    private static final Double DEFAULT_VALORPORCATEGORIA = 1D;
    private static final Double UPDATED_VALORPORCATEGORIA = 2D;

    @Autowired
    private CategoriainversaoRepository categoriainversaoRepository;

    @Autowired
    private CategoriainversaoMapper categoriainversaoMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCategoriainversaoMockMvc;

    private Categoriainversao categoriainversao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CategoriainversaoResource categoriainversaoResource = new CategoriainversaoResource(categoriainversaoRepository, categoriainversaoMapper);
        this.restCategoriainversaoMockMvc = MockMvcBuilders.standaloneSetup(categoriainversaoResource)
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
    public static Categoriainversao createEntity(EntityManager em) {
        Categoriainversao categoriainversao = new Categoriainversao()
            .codcategoria(DEFAULT_CODCATEGORIA)
            .descricaocategoria(DEFAULT_DESCRICAOCATEGORIA)
            .descricaoitem(DEFAULT_DESCRICAOITEM)
            .descricaosubcategoria(DEFAULT_DESCRICAOSUBCATEGORIA)
            .descricaosubitem(DEFAULT_DESCRICAOSUBITEM)
            .idcategoriainversao(DEFAULT_IDCATEGORIAINVERSAO)
            .item(DEFAULT_ITEM)
            .percentualcatagente(DEFAULT_PERCENTUALCATAGENTE)
            .percentualcatlocal(DEFAULT_PERCENTUALCATLOCAL)
            .subcategoria(DEFAULT_SUBCATEGORIA)
            .subitem(DEFAULT_SUBITEM)
            .valorcatagente(DEFAULT_VALORCATAGENTE)
            .valorcatlocal(DEFAULT_VALORCATLOCAL)
            .valorporcategoria(DEFAULT_VALORPORCATEGORIA);
        return categoriainversao;
    }

    @Before
    public void initTest() {
        categoriainversao = createEntity(em);
    }

    @Test
    @Transactional
    public void createCategoriainversao() throws Exception {
        int databaseSizeBeforeCreate = categoriainversaoRepository.findAll().size();

        // Create the Categoriainversao
        CategoriainversaoDTO categoriainversaoDTO = categoriainversaoMapper.toDto(categoriainversao);
        restCategoriainversaoMockMvc.perform(post("/api/categoriainversaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categoriainversaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Categoriainversao in the database
        List<Categoriainversao> categoriainversaoList = categoriainversaoRepository.findAll();
        assertThat(categoriainversaoList).hasSize(databaseSizeBeforeCreate + 1);
        Categoriainversao testCategoriainversao = categoriainversaoList.get(categoriainversaoList.size() - 1);
        assertThat(testCategoriainversao.getCodcategoria()).isEqualTo(DEFAULT_CODCATEGORIA);
        assertThat(testCategoriainversao.getDescricaocategoria()).isEqualTo(DEFAULT_DESCRICAOCATEGORIA);
        assertThat(testCategoriainversao.getDescricaoitem()).isEqualTo(DEFAULT_DESCRICAOITEM);
        assertThat(testCategoriainversao.getDescricaosubcategoria()).isEqualTo(DEFAULT_DESCRICAOSUBCATEGORIA);
        assertThat(testCategoriainversao.getDescricaosubitem()).isEqualTo(DEFAULT_DESCRICAOSUBITEM);
        assertThat(testCategoriainversao.getIdcategoriainversao()).isEqualTo(DEFAULT_IDCATEGORIAINVERSAO);
        assertThat(testCategoriainversao.getItem()).isEqualTo(DEFAULT_ITEM);
        assertThat(testCategoriainversao.getPercentualcatagente()).isEqualTo(DEFAULT_PERCENTUALCATAGENTE);
        assertThat(testCategoriainversao.getPercentualcatlocal()).isEqualTo(DEFAULT_PERCENTUALCATLOCAL);
        assertThat(testCategoriainversao.getSubcategoria()).isEqualTo(DEFAULT_SUBCATEGORIA);
        assertThat(testCategoriainversao.getSubitem()).isEqualTo(DEFAULT_SUBITEM);
        assertThat(testCategoriainversao.getValorcatagente()).isEqualTo(DEFAULT_VALORCATAGENTE);
        assertThat(testCategoriainversao.getValorcatlocal()).isEqualTo(DEFAULT_VALORCATLOCAL);
        assertThat(testCategoriainversao.getValorporcategoria()).isEqualTo(DEFAULT_VALORPORCATEGORIA);
    }

    @Test
    @Transactional
    public void createCategoriainversaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = categoriainversaoRepository.findAll().size();

        // Create the Categoriainversao with an existing ID
        categoriainversao.setId(1L);
        CategoriainversaoDTO categoriainversaoDTO = categoriainversaoMapper.toDto(categoriainversao);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCategoriainversaoMockMvc.perform(post("/api/categoriainversaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categoriainversaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Categoriainversao> categoriainversaoList = categoriainversaoRepository.findAll();
        assertThat(categoriainversaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCategoriainversaos() throws Exception {
        // Initialize the database
        categoriainversaoRepository.saveAndFlush(categoriainversao);

        // Get all the categoriainversaoList
        restCategoriainversaoMockMvc.perform(get("/api/categoriainversaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(categoriainversao.getId().intValue())))
            .andExpect(jsonPath("$.[*].codcategoria").value(hasItem(DEFAULT_CODCATEGORIA.toString())))
            .andExpect(jsonPath("$.[*].descricaocategoria").value(hasItem(DEFAULT_DESCRICAOCATEGORIA.toString())))
            .andExpect(jsonPath("$.[*].descricaoitem").value(hasItem(DEFAULT_DESCRICAOITEM.toString())))
            .andExpect(jsonPath("$.[*].descricaosubcategoria").value(hasItem(DEFAULT_DESCRICAOSUBCATEGORIA.toString())))
            .andExpect(jsonPath("$.[*].descricaosubitem").value(hasItem(DEFAULT_DESCRICAOSUBITEM.toString())))
            .andExpect(jsonPath("$.[*].idcategoriainversao").value(hasItem(DEFAULT_IDCATEGORIAINVERSAO)))
            .andExpect(jsonPath("$.[*].item").value(hasItem(DEFAULT_ITEM.toString())))
            .andExpect(jsonPath("$.[*].percentualcatagente").value(hasItem(DEFAULT_PERCENTUALCATAGENTE.doubleValue())))
            .andExpect(jsonPath("$.[*].percentualcatlocal").value(hasItem(DEFAULT_PERCENTUALCATLOCAL.doubleValue())))
            .andExpect(jsonPath("$.[*].subcategoria").value(hasItem(DEFAULT_SUBCATEGORIA.toString())))
            .andExpect(jsonPath("$.[*].subitem").value(hasItem(DEFAULT_SUBITEM.toString())))
            .andExpect(jsonPath("$.[*].valorcatagente").value(hasItem(DEFAULT_VALORCATAGENTE.doubleValue())))
            .andExpect(jsonPath("$.[*].valorcatlocal").value(hasItem(DEFAULT_VALORCATLOCAL.doubleValue())))
            .andExpect(jsonPath("$.[*].valorporcategoria").value(hasItem(DEFAULT_VALORPORCATEGORIA.doubleValue())));
    }

    @Test
    @Transactional
    public void getCategoriainversao() throws Exception {
        // Initialize the database
        categoriainversaoRepository.saveAndFlush(categoriainversao);

        // Get the categoriainversao
        restCategoriainversaoMockMvc.perform(get("/api/categoriainversaos/{id}", categoriainversao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(categoriainversao.getId().intValue()))
            .andExpect(jsonPath("$.codcategoria").value(DEFAULT_CODCATEGORIA.toString()))
            .andExpect(jsonPath("$.descricaocategoria").value(DEFAULT_DESCRICAOCATEGORIA.toString()))
            .andExpect(jsonPath("$.descricaoitem").value(DEFAULT_DESCRICAOITEM.toString()))
            .andExpect(jsonPath("$.descricaosubcategoria").value(DEFAULT_DESCRICAOSUBCATEGORIA.toString()))
            .andExpect(jsonPath("$.descricaosubitem").value(DEFAULT_DESCRICAOSUBITEM.toString()))
            .andExpect(jsonPath("$.idcategoriainversao").value(DEFAULT_IDCATEGORIAINVERSAO))
            .andExpect(jsonPath("$.item").value(DEFAULT_ITEM.toString()))
            .andExpect(jsonPath("$.percentualcatagente").value(DEFAULT_PERCENTUALCATAGENTE.doubleValue()))
            .andExpect(jsonPath("$.percentualcatlocal").value(DEFAULT_PERCENTUALCATLOCAL.doubleValue()))
            .andExpect(jsonPath("$.subcategoria").value(DEFAULT_SUBCATEGORIA.toString()))
            .andExpect(jsonPath("$.subitem").value(DEFAULT_SUBITEM.toString()))
            .andExpect(jsonPath("$.valorcatagente").value(DEFAULT_VALORCATAGENTE.doubleValue()))
            .andExpect(jsonPath("$.valorcatlocal").value(DEFAULT_VALORCATLOCAL.doubleValue()))
            .andExpect(jsonPath("$.valorporcategoria").value(DEFAULT_VALORPORCATEGORIA.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCategoriainversao() throws Exception {
        // Get the categoriainversao
        restCategoriainversaoMockMvc.perform(get("/api/categoriainversaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCategoriainversao() throws Exception {
        // Initialize the database
        categoriainversaoRepository.saveAndFlush(categoriainversao);
        int databaseSizeBeforeUpdate = categoriainversaoRepository.findAll().size();

        // Update the categoriainversao
        Categoriainversao updatedCategoriainversao = categoriainversaoRepository.findOne(categoriainversao.getId());
        updatedCategoriainversao
            .codcategoria(UPDATED_CODCATEGORIA)
            .descricaocategoria(UPDATED_DESCRICAOCATEGORIA)
            .descricaoitem(UPDATED_DESCRICAOITEM)
            .descricaosubcategoria(UPDATED_DESCRICAOSUBCATEGORIA)
            .descricaosubitem(UPDATED_DESCRICAOSUBITEM)
            .idcategoriainversao(UPDATED_IDCATEGORIAINVERSAO)
            .item(UPDATED_ITEM)
            .percentualcatagente(UPDATED_PERCENTUALCATAGENTE)
            .percentualcatlocal(UPDATED_PERCENTUALCATLOCAL)
            .subcategoria(UPDATED_SUBCATEGORIA)
            .subitem(UPDATED_SUBITEM)
            .valorcatagente(UPDATED_VALORCATAGENTE)
            .valorcatlocal(UPDATED_VALORCATLOCAL)
            .valorporcategoria(UPDATED_VALORPORCATEGORIA);
        CategoriainversaoDTO categoriainversaoDTO = categoriainversaoMapper.toDto(updatedCategoriainversao);

        restCategoriainversaoMockMvc.perform(put("/api/categoriainversaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categoriainversaoDTO)))
            .andExpect(status().isOk());

        // Validate the Categoriainversao in the database
        List<Categoriainversao> categoriainversaoList = categoriainversaoRepository.findAll();
        assertThat(categoriainversaoList).hasSize(databaseSizeBeforeUpdate);
        Categoriainversao testCategoriainversao = categoriainversaoList.get(categoriainversaoList.size() - 1);
        assertThat(testCategoriainversao.getCodcategoria()).isEqualTo(UPDATED_CODCATEGORIA);
        assertThat(testCategoriainversao.getDescricaocategoria()).isEqualTo(UPDATED_DESCRICAOCATEGORIA);
        assertThat(testCategoriainversao.getDescricaoitem()).isEqualTo(UPDATED_DESCRICAOITEM);
        assertThat(testCategoriainversao.getDescricaosubcategoria()).isEqualTo(UPDATED_DESCRICAOSUBCATEGORIA);
        assertThat(testCategoriainversao.getDescricaosubitem()).isEqualTo(UPDATED_DESCRICAOSUBITEM);
        assertThat(testCategoriainversao.getIdcategoriainversao()).isEqualTo(UPDATED_IDCATEGORIAINVERSAO);
        assertThat(testCategoriainversao.getItem()).isEqualTo(UPDATED_ITEM);
        assertThat(testCategoriainversao.getPercentualcatagente()).isEqualTo(UPDATED_PERCENTUALCATAGENTE);
        assertThat(testCategoriainversao.getPercentualcatlocal()).isEqualTo(UPDATED_PERCENTUALCATLOCAL);
        assertThat(testCategoriainversao.getSubcategoria()).isEqualTo(UPDATED_SUBCATEGORIA);
        assertThat(testCategoriainversao.getSubitem()).isEqualTo(UPDATED_SUBITEM);
        assertThat(testCategoriainversao.getValorcatagente()).isEqualTo(UPDATED_VALORCATAGENTE);
        assertThat(testCategoriainversao.getValorcatlocal()).isEqualTo(UPDATED_VALORCATLOCAL);
        assertThat(testCategoriainversao.getValorporcategoria()).isEqualTo(UPDATED_VALORPORCATEGORIA);
    }

    @Test
    @Transactional
    public void updateNonExistingCategoriainversao() throws Exception {
        int databaseSizeBeforeUpdate = categoriainversaoRepository.findAll().size();

        // Create the Categoriainversao
        CategoriainversaoDTO categoriainversaoDTO = categoriainversaoMapper.toDto(categoriainversao);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCategoriainversaoMockMvc.perform(put("/api/categoriainversaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categoriainversaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Categoriainversao in the database
        List<Categoriainversao> categoriainversaoList = categoriainversaoRepository.findAll();
        assertThat(categoriainversaoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCategoriainversao() throws Exception {
        // Initialize the database
        categoriainversaoRepository.saveAndFlush(categoriainversao);
        int databaseSizeBeforeDelete = categoriainversaoRepository.findAll().size();

        // Get the categoriainversao
        restCategoriainversaoMockMvc.perform(delete("/api/categoriainversaos/{id}", categoriainversao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Categoriainversao> categoriainversaoList = categoriainversaoRepository.findAll();
        assertThat(categoriainversaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Categoriainversao.class);
        Categoriainversao categoriainversao1 = new Categoriainversao();
        categoriainversao1.setId(1L);
        Categoriainversao categoriainversao2 = new Categoriainversao();
        categoriainversao2.setId(categoriainversao1.getId());
        assertThat(categoriainversao1).isEqualTo(categoriainversao2);
        categoriainversao2.setId(2L);
        assertThat(categoriainversao1).isNotEqualTo(categoriainversao2);
        categoriainversao1.setId(null);
        assertThat(categoriainversao1).isNotEqualTo(categoriainversao2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategoriainversaoDTO.class);
        CategoriainversaoDTO categoriainversaoDTO1 = new CategoriainversaoDTO();
        categoriainversaoDTO1.setId(1L);
        CategoriainversaoDTO categoriainversaoDTO2 = new CategoriainversaoDTO();
        assertThat(categoriainversaoDTO1).isNotEqualTo(categoriainversaoDTO2);
        categoriainversaoDTO2.setId(categoriainversaoDTO1.getId());
        assertThat(categoriainversaoDTO1).isEqualTo(categoriainversaoDTO2);
        categoriainversaoDTO2.setId(2L);
        assertThat(categoriainversaoDTO1).isNotEqualTo(categoriainversaoDTO2);
        categoriainversaoDTO1.setId(null);
        assertThat(categoriainversaoDTO1).isNotEqualTo(categoriainversaoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(categoriainversaoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(categoriainversaoMapper.fromId(null)).isNull();
    }
}
