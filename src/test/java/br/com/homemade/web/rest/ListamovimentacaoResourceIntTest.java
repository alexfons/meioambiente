package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Listamovimentacao;
import br.com.homemade.repository.ListamovimentacaoRepository;
import br.com.homemade.service.dto.ListamovimentacaoDTO;
import br.com.homemade.service.mapper.ListamovimentacaoMapper;
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
 * Test class for the ListamovimentacaoResource REST controller.
 *
 * @see ListamovimentacaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class ListamovimentacaoResourceIntTest {

    private static final String DEFAULT_TIPOLANCAMENTO = "AAAAAAAAAA";
    private static final String UPDATED_TIPOLANCAMENTO = "BBBBBBBBBB";

    private static final String DEFAULT_HISTORICO = "AAAAAAAAAA";
    private static final String UPDATED_HISTORICO = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_VALORUSCREDITO = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALORUSCREDITO = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VALORRCREDITO = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALORRCREDITO = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VALORUSDEBITO = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALORUSDEBITO = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VALORRDEBITO = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALORRDEBITO = new BigDecimal(2);

    @Autowired
    private ListamovimentacaoRepository listamovimentacaoRepository;

    @Autowired
    private ListamovimentacaoMapper listamovimentacaoMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restListamovimentacaoMockMvc;

    private Listamovimentacao listamovimentacao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ListamovimentacaoResource listamovimentacaoResource = new ListamovimentacaoResource(listamovimentacaoRepository, listamovimentacaoMapper);
        this.restListamovimentacaoMockMvc = MockMvcBuilders.standaloneSetup(listamovimentacaoResource)
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
    public static Listamovimentacao createEntity(EntityManager em) {
        Listamovimentacao listamovimentacao = new Listamovimentacao()
            .tipolancamento(DEFAULT_TIPOLANCAMENTO)
            .historico(DEFAULT_HISTORICO)
            .valoruscredito(DEFAULT_VALORUSCREDITO)
            .valorrcredito(DEFAULT_VALORRCREDITO)
            .valorusdebito(DEFAULT_VALORUSDEBITO)
            .valorrdebito(DEFAULT_VALORRDEBITO);
        return listamovimentacao;
    }

    @Before
    public void initTest() {
        listamovimentacao = createEntity(em);
    }

    @Test
    @Transactional
    public void createListamovimentacao() throws Exception {
        int databaseSizeBeforeCreate = listamovimentacaoRepository.findAll().size();

        // Create the Listamovimentacao
        ListamovimentacaoDTO listamovimentacaoDTO = listamovimentacaoMapper.toDto(listamovimentacao);
        restListamovimentacaoMockMvc.perform(post("/api/listamovimentacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listamovimentacaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Listamovimentacao in the database
        List<Listamovimentacao> listamovimentacaoList = listamovimentacaoRepository.findAll();
        assertThat(listamovimentacaoList).hasSize(databaseSizeBeforeCreate + 1);
        Listamovimentacao testListamovimentacao = listamovimentacaoList.get(listamovimentacaoList.size() - 1);
        assertThat(testListamovimentacao.getTipolancamento()).isEqualTo(DEFAULT_TIPOLANCAMENTO);
        assertThat(testListamovimentacao.getHistorico()).isEqualTo(DEFAULT_HISTORICO);
        assertThat(testListamovimentacao.getValoruscredito()).isEqualTo(DEFAULT_VALORUSCREDITO);
        assertThat(testListamovimentacao.getValorrcredito()).isEqualTo(DEFAULT_VALORRCREDITO);
        assertThat(testListamovimentacao.getValorusdebito()).isEqualTo(DEFAULT_VALORUSDEBITO);
        assertThat(testListamovimentacao.getValorrdebito()).isEqualTo(DEFAULT_VALORRDEBITO);
    }

    @Test
    @Transactional
    public void createListamovimentacaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listamovimentacaoRepository.findAll().size();

        // Create the Listamovimentacao with an existing ID
        listamovimentacao.setId(1L);
        ListamovimentacaoDTO listamovimentacaoDTO = listamovimentacaoMapper.toDto(listamovimentacao);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListamovimentacaoMockMvc.perform(post("/api/listamovimentacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listamovimentacaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Listamovimentacao> listamovimentacaoList = listamovimentacaoRepository.findAll();
        assertThat(listamovimentacaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllListamovimentacaos() throws Exception {
        // Initialize the database
        listamovimentacaoRepository.saveAndFlush(listamovimentacao);

        // Get all the listamovimentacaoList
        restListamovimentacaoMockMvc.perform(get("/api/listamovimentacaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listamovimentacao.getId().intValue())))
            .andExpect(jsonPath("$.[*].tipolancamento").value(hasItem(DEFAULT_TIPOLANCAMENTO.toString())))
            .andExpect(jsonPath("$.[*].historico").value(hasItem(DEFAULT_HISTORICO.toString())))
            .andExpect(jsonPath("$.[*].valoruscredito").value(hasItem(DEFAULT_VALORUSCREDITO.intValue())))
            .andExpect(jsonPath("$.[*].valorrcredito").value(hasItem(DEFAULT_VALORRCREDITO.intValue())))
            .andExpect(jsonPath("$.[*].valorusdebito").value(hasItem(DEFAULT_VALORUSDEBITO.intValue())))
            .andExpect(jsonPath("$.[*].valorrdebito").value(hasItem(DEFAULT_VALORRDEBITO.intValue())));
    }

    @Test
    @Transactional
    public void getListamovimentacao() throws Exception {
        // Initialize the database
        listamovimentacaoRepository.saveAndFlush(listamovimentacao);

        // Get the listamovimentacao
        restListamovimentacaoMockMvc.perform(get("/api/listamovimentacaos/{id}", listamovimentacao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listamovimentacao.getId().intValue()))
            .andExpect(jsonPath("$.tipolancamento").value(DEFAULT_TIPOLANCAMENTO.toString()))
            .andExpect(jsonPath("$.historico").value(DEFAULT_HISTORICO.toString()))
            .andExpect(jsonPath("$.valoruscredito").value(DEFAULT_VALORUSCREDITO.intValue()))
            .andExpect(jsonPath("$.valorrcredito").value(DEFAULT_VALORRCREDITO.intValue()))
            .andExpect(jsonPath("$.valorusdebito").value(DEFAULT_VALORUSDEBITO.intValue()))
            .andExpect(jsonPath("$.valorrdebito").value(DEFAULT_VALORRDEBITO.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingListamovimentacao() throws Exception {
        // Get the listamovimentacao
        restListamovimentacaoMockMvc.perform(get("/api/listamovimentacaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListamovimentacao() throws Exception {
        // Initialize the database
        listamovimentacaoRepository.saveAndFlush(listamovimentacao);
        int databaseSizeBeforeUpdate = listamovimentacaoRepository.findAll().size();

        // Update the listamovimentacao
        Listamovimentacao updatedListamovimentacao = listamovimentacaoRepository.findOne(listamovimentacao.getId());
        updatedListamovimentacao
            .tipolancamento(UPDATED_TIPOLANCAMENTO)
            .historico(UPDATED_HISTORICO)
            .valoruscredito(UPDATED_VALORUSCREDITO)
            .valorrcredito(UPDATED_VALORRCREDITO)
            .valorusdebito(UPDATED_VALORUSDEBITO)
            .valorrdebito(UPDATED_VALORRDEBITO);
        ListamovimentacaoDTO listamovimentacaoDTO = listamovimentacaoMapper.toDto(updatedListamovimentacao);

        restListamovimentacaoMockMvc.perform(put("/api/listamovimentacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listamovimentacaoDTO)))
            .andExpect(status().isOk());

        // Validate the Listamovimentacao in the database
        List<Listamovimentacao> listamovimentacaoList = listamovimentacaoRepository.findAll();
        assertThat(listamovimentacaoList).hasSize(databaseSizeBeforeUpdate);
        Listamovimentacao testListamovimentacao = listamovimentacaoList.get(listamovimentacaoList.size() - 1);
        assertThat(testListamovimentacao.getTipolancamento()).isEqualTo(UPDATED_TIPOLANCAMENTO);
        assertThat(testListamovimentacao.getHistorico()).isEqualTo(UPDATED_HISTORICO);
        assertThat(testListamovimentacao.getValoruscredito()).isEqualTo(UPDATED_VALORUSCREDITO);
        assertThat(testListamovimentacao.getValorrcredito()).isEqualTo(UPDATED_VALORRCREDITO);
        assertThat(testListamovimentacao.getValorusdebito()).isEqualTo(UPDATED_VALORUSDEBITO);
        assertThat(testListamovimentacao.getValorrdebito()).isEqualTo(UPDATED_VALORRDEBITO);
    }

    @Test
    @Transactional
    public void updateNonExistingListamovimentacao() throws Exception {
        int databaseSizeBeforeUpdate = listamovimentacaoRepository.findAll().size();

        // Create the Listamovimentacao
        ListamovimentacaoDTO listamovimentacaoDTO = listamovimentacaoMapper.toDto(listamovimentacao);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restListamovimentacaoMockMvc.perform(put("/api/listamovimentacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listamovimentacaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Listamovimentacao in the database
        List<Listamovimentacao> listamovimentacaoList = listamovimentacaoRepository.findAll();
        assertThat(listamovimentacaoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteListamovimentacao() throws Exception {
        // Initialize the database
        listamovimentacaoRepository.saveAndFlush(listamovimentacao);
        int databaseSizeBeforeDelete = listamovimentacaoRepository.findAll().size();

        // Get the listamovimentacao
        restListamovimentacaoMockMvc.perform(delete("/api/listamovimentacaos/{id}", listamovimentacao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Listamovimentacao> listamovimentacaoList = listamovimentacaoRepository.findAll();
        assertThat(listamovimentacaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Listamovimentacao.class);
        Listamovimentacao listamovimentacao1 = new Listamovimentacao();
        listamovimentacao1.setId(1L);
        Listamovimentacao listamovimentacao2 = new Listamovimentacao();
        listamovimentacao2.setId(listamovimentacao1.getId());
        assertThat(listamovimentacao1).isEqualTo(listamovimentacao2);
        listamovimentacao2.setId(2L);
        assertThat(listamovimentacao1).isNotEqualTo(listamovimentacao2);
        listamovimentacao1.setId(null);
        assertThat(listamovimentacao1).isNotEqualTo(listamovimentacao2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListamovimentacaoDTO.class);
        ListamovimentacaoDTO listamovimentacaoDTO1 = new ListamovimentacaoDTO();
        listamovimentacaoDTO1.setId(1L);
        ListamovimentacaoDTO listamovimentacaoDTO2 = new ListamovimentacaoDTO();
        assertThat(listamovimentacaoDTO1).isNotEqualTo(listamovimentacaoDTO2);
        listamovimentacaoDTO2.setId(listamovimentacaoDTO1.getId());
        assertThat(listamovimentacaoDTO1).isEqualTo(listamovimentacaoDTO2);
        listamovimentacaoDTO2.setId(2L);
        assertThat(listamovimentacaoDTO1).isNotEqualTo(listamovimentacaoDTO2);
        listamovimentacaoDTO1.setId(null);
        assertThat(listamovimentacaoDTO1).isNotEqualTo(listamovimentacaoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listamovimentacaoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listamovimentacaoMapper.fromId(null)).isNull();
    }
}
