package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Obrafisicomensal;
import br.com.homemade.repository.ObrafisicomensalRepository;
import br.com.homemade.service.dto.ObrafisicomensalDTO;
import br.com.homemade.service.mapper.ObrafisicomensalMapper;
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
 * Test class for the ObrafisicomensalResource REST controller.
 *
 * @see ObrafisicomensalResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class ObrafisicomensalResourceIntTest {

    private static final String DEFAULT_COMENTARIO = "AAAAAAAAAA";
    private static final String UPDATED_COMENTARIO = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATAINSPECAO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATAINSPECAO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATARELATORIO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATARELATORIO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_PESSOAL = "AAAAAAAAAA";
    private static final String UPDATED_PESSOAL = "BBBBBBBBBB";

    private static final String DEFAULT_EQUIPAMENTO = "AAAAAAAAAA";
    private static final String UPDATED_EQUIPAMENTO = "BBBBBBBBBB";

    private static final String DEFAULT_INSTALACAOAPOIO = "AAAAAAAAAA";
    private static final String UPDATED_INSTALACAOAPOIO = "BBBBBBBBBB";

    private static final String DEFAULT_RITMO = "AAAAAAAAAA";
    private static final String UPDATED_RITMO = "BBBBBBBBBB";

    private static final String DEFAULT_APRESENTACAO = "AAAAAAAAAA";
    private static final String UPDATED_APRESENTACAO = "BBBBBBBBBB";

    private static final String DEFAULT_QUALIDADESERVICOS = "AAAAAAAAAA";
    private static final String UPDATED_QUALIDADESERVICOS = "BBBBBBBBBB";

    private static final String DEFAULT_CRONOGRAMA = "AAAAAAAAAA";
    private static final String UPDATED_CRONOGRAMA = "BBBBBBBBBB";

    private static final Integer DEFAULT_PRAZODECORRIDO = 1;
    private static final Integer UPDATED_PRAZODECORRIDO = 2;

    private static final Integer DEFAULT_AVANCOFISICO_OAE = 1;
    private static final Integer UPDATED_AVANCOFISICO_OAE = 2;

    private static final Integer DEFAULT_AVANCOFISICOPONDERADO = 1;
    private static final Integer UPDATED_AVANCOFISICOPONDERADO = 2;

    private static final ZonedDateTime DEFAULT_PREVISAOATUAL = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_PREVISAOATUAL = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private ObrafisicomensalRepository obrafisicomensalRepository;

    @Autowired
    private ObrafisicomensalMapper obrafisicomensalMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restObrafisicomensalMockMvc;

    private Obrafisicomensal obrafisicomensal;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ObrafisicomensalResource obrafisicomensalResource = new ObrafisicomensalResource(obrafisicomensalRepository, obrafisicomensalMapper);
        this.restObrafisicomensalMockMvc = MockMvcBuilders.standaloneSetup(obrafisicomensalResource)
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
    public static Obrafisicomensal createEntity(EntityManager em) {
        Obrafisicomensal obrafisicomensal = new Obrafisicomensal()
            .comentario(DEFAULT_COMENTARIO)
            .datainspecao(DEFAULT_DATAINSPECAO)
            .datarelatorio(DEFAULT_DATARELATORIO)
            .pessoal(DEFAULT_PESSOAL)
            .equipamento(DEFAULT_EQUIPAMENTO)
            .instalacaoapoio(DEFAULT_INSTALACAOAPOIO)
            .ritmo(DEFAULT_RITMO)
            .apresentacao(DEFAULT_APRESENTACAO)
            .qualidadeservicos(DEFAULT_QUALIDADESERVICOS)
            .cronograma(DEFAULT_CRONOGRAMA)
            .prazodecorrido(DEFAULT_PRAZODECORRIDO)
            .avancofisicoOAE(DEFAULT_AVANCOFISICO_OAE)
            .avancofisicoponderado(DEFAULT_AVANCOFISICOPONDERADO)
            .previsaoatual(DEFAULT_PREVISAOATUAL);
        return obrafisicomensal;
    }

    @Before
    public void initTest() {
        obrafisicomensal = createEntity(em);
    }

    @Test
    @Transactional
    public void createObrafisicomensal() throws Exception {
        int databaseSizeBeforeCreate = obrafisicomensalRepository.findAll().size();

        // Create the Obrafisicomensal
        ObrafisicomensalDTO obrafisicomensalDTO = obrafisicomensalMapper.toDto(obrafisicomensal);
        restObrafisicomensalMockMvc.perform(post("/api/obrafisicomensals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(obrafisicomensalDTO)))
            .andExpect(status().isCreated());

        // Validate the Obrafisicomensal in the database
        List<Obrafisicomensal> obrafisicomensalList = obrafisicomensalRepository.findAll();
        assertThat(obrafisicomensalList).hasSize(databaseSizeBeforeCreate + 1);
        Obrafisicomensal testObrafisicomensal = obrafisicomensalList.get(obrafisicomensalList.size() - 1);
        assertThat(testObrafisicomensal.getComentario()).isEqualTo(DEFAULT_COMENTARIO);
        assertThat(testObrafisicomensal.getDatainspecao()).isEqualTo(DEFAULT_DATAINSPECAO);
        assertThat(testObrafisicomensal.getDatarelatorio()).isEqualTo(DEFAULT_DATARELATORIO);
        assertThat(testObrafisicomensal.getPessoal()).isEqualTo(DEFAULT_PESSOAL);
        assertThat(testObrafisicomensal.getEquipamento()).isEqualTo(DEFAULT_EQUIPAMENTO);
        assertThat(testObrafisicomensal.getInstalacaoapoio()).isEqualTo(DEFAULT_INSTALACAOAPOIO);
        assertThat(testObrafisicomensal.getRitmo()).isEqualTo(DEFAULT_RITMO);
        assertThat(testObrafisicomensal.getApresentacao()).isEqualTo(DEFAULT_APRESENTACAO);
        assertThat(testObrafisicomensal.getQualidadeservicos()).isEqualTo(DEFAULT_QUALIDADESERVICOS);
        assertThat(testObrafisicomensal.getCronograma()).isEqualTo(DEFAULT_CRONOGRAMA);
        assertThat(testObrafisicomensal.getPrazodecorrido()).isEqualTo(DEFAULT_PRAZODECORRIDO);
        assertThat(testObrafisicomensal.getAvancofisicoOAE()).isEqualTo(DEFAULT_AVANCOFISICO_OAE);
        assertThat(testObrafisicomensal.getAvancofisicoponderado()).isEqualTo(DEFAULT_AVANCOFISICOPONDERADO);
        assertThat(testObrafisicomensal.getPrevisaoatual()).isEqualTo(DEFAULT_PREVISAOATUAL);
    }

    @Test
    @Transactional
    public void createObrafisicomensalWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = obrafisicomensalRepository.findAll().size();

        // Create the Obrafisicomensal with an existing ID
        obrafisicomensal.setId(1L);
        ObrafisicomensalDTO obrafisicomensalDTO = obrafisicomensalMapper.toDto(obrafisicomensal);

        // An entity with an existing ID cannot be created, so this API call must fail
        restObrafisicomensalMockMvc.perform(post("/api/obrafisicomensals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(obrafisicomensalDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Obrafisicomensal> obrafisicomensalList = obrafisicomensalRepository.findAll();
        assertThat(obrafisicomensalList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllObrafisicomensals() throws Exception {
        // Initialize the database
        obrafisicomensalRepository.saveAndFlush(obrafisicomensal);

        // Get all the obrafisicomensalList
        restObrafisicomensalMockMvc.perform(get("/api/obrafisicomensals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(obrafisicomensal.getId().intValue())))
            .andExpect(jsonPath("$.[*].comentario").value(hasItem(DEFAULT_COMENTARIO.toString())))
            .andExpect(jsonPath("$.[*].datainspecao").value(hasItem(sameInstant(DEFAULT_DATAINSPECAO))))
            .andExpect(jsonPath("$.[*].datarelatorio").value(hasItem(sameInstant(DEFAULT_DATARELATORIO))))
            .andExpect(jsonPath("$.[*].pessoal").value(hasItem(DEFAULT_PESSOAL.toString())))
            .andExpect(jsonPath("$.[*].equipamento").value(hasItem(DEFAULT_EQUIPAMENTO.toString())))
            .andExpect(jsonPath("$.[*].instalacaoapoio").value(hasItem(DEFAULT_INSTALACAOAPOIO.toString())))
            .andExpect(jsonPath("$.[*].ritmo").value(hasItem(DEFAULT_RITMO.toString())))
            .andExpect(jsonPath("$.[*].apresentacao").value(hasItem(DEFAULT_APRESENTACAO.toString())))
            .andExpect(jsonPath("$.[*].qualidadeservicos").value(hasItem(DEFAULT_QUALIDADESERVICOS.toString())))
            .andExpect(jsonPath("$.[*].cronograma").value(hasItem(DEFAULT_CRONOGRAMA.toString())))
            .andExpect(jsonPath("$.[*].prazodecorrido").value(hasItem(DEFAULT_PRAZODECORRIDO)))
            .andExpect(jsonPath("$.[*].avancofisicoOAE").value(hasItem(DEFAULT_AVANCOFISICO_OAE)))
            .andExpect(jsonPath("$.[*].avancofisicoponderado").value(hasItem(DEFAULT_AVANCOFISICOPONDERADO)))
            .andExpect(jsonPath("$.[*].previsaoatual").value(hasItem(sameInstant(DEFAULT_PREVISAOATUAL))));
    }

    @Test
    @Transactional
    public void getObrafisicomensal() throws Exception {
        // Initialize the database
        obrafisicomensalRepository.saveAndFlush(obrafisicomensal);

        // Get the obrafisicomensal
        restObrafisicomensalMockMvc.perform(get("/api/obrafisicomensals/{id}", obrafisicomensal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(obrafisicomensal.getId().intValue()))
            .andExpect(jsonPath("$.comentario").value(DEFAULT_COMENTARIO.toString()))
            .andExpect(jsonPath("$.datainspecao").value(sameInstant(DEFAULT_DATAINSPECAO)))
            .andExpect(jsonPath("$.datarelatorio").value(sameInstant(DEFAULT_DATARELATORIO)))
            .andExpect(jsonPath("$.pessoal").value(DEFAULT_PESSOAL.toString()))
            .andExpect(jsonPath("$.equipamento").value(DEFAULT_EQUIPAMENTO.toString()))
            .andExpect(jsonPath("$.instalacaoapoio").value(DEFAULT_INSTALACAOAPOIO.toString()))
            .andExpect(jsonPath("$.ritmo").value(DEFAULT_RITMO.toString()))
            .andExpect(jsonPath("$.apresentacao").value(DEFAULT_APRESENTACAO.toString()))
            .andExpect(jsonPath("$.qualidadeservicos").value(DEFAULT_QUALIDADESERVICOS.toString()))
            .andExpect(jsonPath("$.cronograma").value(DEFAULT_CRONOGRAMA.toString()))
            .andExpect(jsonPath("$.prazodecorrido").value(DEFAULT_PRAZODECORRIDO))
            .andExpect(jsonPath("$.avancofisicoOAE").value(DEFAULT_AVANCOFISICO_OAE))
            .andExpect(jsonPath("$.avancofisicoponderado").value(DEFAULT_AVANCOFISICOPONDERADO))
            .andExpect(jsonPath("$.previsaoatual").value(sameInstant(DEFAULT_PREVISAOATUAL)));
    }

    @Test
    @Transactional
    public void getNonExistingObrafisicomensal() throws Exception {
        // Get the obrafisicomensal
        restObrafisicomensalMockMvc.perform(get("/api/obrafisicomensals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateObrafisicomensal() throws Exception {
        // Initialize the database
        obrafisicomensalRepository.saveAndFlush(obrafisicomensal);
        int databaseSizeBeforeUpdate = obrafisicomensalRepository.findAll().size();

        // Update the obrafisicomensal
        Obrafisicomensal updatedObrafisicomensal = obrafisicomensalRepository.findOne(obrafisicomensal.getId());
        updatedObrafisicomensal
            .comentario(UPDATED_COMENTARIO)
            .datainspecao(UPDATED_DATAINSPECAO)
            .datarelatorio(UPDATED_DATARELATORIO)
            .pessoal(UPDATED_PESSOAL)
            .equipamento(UPDATED_EQUIPAMENTO)
            .instalacaoapoio(UPDATED_INSTALACAOAPOIO)
            .ritmo(UPDATED_RITMO)
            .apresentacao(UPDATED_APRESENTACAO)
            .qualidadeservicos(UPDATED_QUALIDADESERVICOS)
            .cronograma(UPDATED_CRONOGRAMA)
            .prazodecorrido(UPDATED_PRAZODECORRIDO)
            .avancofisicoOAE(UPDATED_AVANCOFISICO_OAE)
            .avancofisicoponderado(UPDATED_AVANCOFISICOPONDERADO)
            .previsaoatual(UPDATED_PREVISAOATUAL);
        ObrafisicomensalDTO obrafisicomensalDTO = obrafisicomensalMapper.toDto(updatedObrafisicomensal);

        restObrafisicomensalMockMvc.perform(put("/api/obrafisicomensals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(obrafisicomensalDTO)))
            .andExpect(status().isOk());

        // Validate the Obrafisicomensal in the database
        List<Obrafisicomensal> obrafisicomensalList = obrafisicomensalRepository.findAll();
        assertThat(obrafisicomensalList).hasSize(databaseSizeBeforeUpdate);
        Obrafisicomensal testObrafisicomensal = obrafisicomensalList.get(obrafisicomensalList.size() - 1);
        assertThat(testObrafisicomensal.getComentario()).isEqualTo(UPDATED_COMENTARIO);
        assertThat(testObrafisicomensal.getDatainspecao()).isEqualTo(UPDATED_DATAINSPECAO);
        assertThat(testObrafisicomensal.getDatarelatorio()).isEqualTo(UPDATED_DATARELATORIO);
        assertThat(testObrafisicomensal.getPessoal()).isEqualTo(UPDATED_PESSOAL);
        assertThat(testObrafisicomensal.getEquipamento()).isEqualTo(UPDATED_EQUIPAMENTO);
        assertThat(testObrafisicomensal.getInstalacaoapoio()).isEqualTo(UPDATED_INSTALACAOAPOIO);
        assertThat(testObrafisicomensal.getRitmo()).isEqualTo(UPDATED_RITMO);
        assertThat(testObrafisicomensal.getApresentacao()).isEqualTo(UPDATED_APRESENTACAO);
        assertThat(testObrafisicomensal.getQualidadeservicos()).isEqualTo(UPDATED_QUALIDADESERVICOS);
        assertThat(testObrafisicomensal.getCronograma()).isEqualTo(UPDATED_CRONOGRAMA);
        assertThat(testObrafisicomensal.getPrazodecorrido()).isEqualTo(UPDATED_PRAZODECORRIDO);
        assertThat(testObrafisicomensal.getAvancofisicoOAE()).isEqualTo(UPDATED_AVANCOFISICO_OAE);
        assertThat(testObrafisicomensal.getAvancofisicoponderado()).isEqualTo(UPDATED_AVANCOFISICOPONDERADO);
        assertThat(testObrafisicomensal.getPrevisaoatual()).isEqualTo(UPDATED_PREVISAOATUAL);
    }

    @Test
    @Transactional
    public void updateNonExistingObrafisicomensal() throws Exception {
        int databaseSizeBeforeUpdate = obrafisicomensalRepository.findAll().size();

        // Create the Obrafisicomensal
        ObrafisicomensalDTO obrafisicomensalDTO = obrafisicomensalMapper.toDto(obrafisicomensal);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restObrafisicomensalMockMvc.perform(put("/api/obrafisicomensals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(obrafisicomensalDTO)))
            .andExpect(status().isCreated());

        // Validate the Obrafisicomensal in the database
        List<Obrafisicomensal> obrafisicomensalList = obrafisicomensalRepository.findAll();
        assertThat(obrafisicomensalList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteObrafisicomensal() throws Exception {
        // Initialize the database
        obrafisicomensalRepository.saveAndFlush(obrafisicomensal);
        int databaseSizeBeforeDelete = obrafisicomensalRepository.findAll().size();

        // Get the obrafisicomensal
        restObrafisicomensalMockMvc.perform(delete("/api/obrafisicomensals/{id}", obrafisicomensal.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Obrafisicomensal> obrafisicomensalList = obrafisicomensalRepository.findAll();
        assertThat(obrafisicomensalList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Obrafisicomensal.class);
        Obrafisicomensal obrafisicomensal1 = new Obrafisicomensal();
        obrafisicomensal1.setId(1L);
        Obrafisicomensal obrafisicomensal2 = new Obrafisicomensal();
        obrafisicomensal2.setId(obrafisicomensal1.getId());
        assertThat(obrafisicomensal1).isEqualTo(obrafisicomensal2);
        obrafisicomensal2.setId(2L);
        assertThat(obrafisicomensal1).isNotEqualTo(obrafisicomensal2);
        obrafisicomensal1.setId(null);
        assertThat(obrafisicomensal1).isNotEqualTo(obrafisicomensal2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ObrafisicomensalDTO.class);
        ObrafisicomensalDTO obrafisicomensalDTO1 = new ObrafisicomensalDTO();
        obrafisicomensalDTO1.setId(1L);
        ObrafisicomensalDTO obrafisicomensalDTO2 = new ObrafisicomensalDTO();
        assertThat(obrafisicomensalDTO1).isNotEqualTo(obrafisicomensalDTO2);
        obrafisicomensalDTO2.setId(obrafisicomensalDTO1.getId());
        assertThat(obrafisicomensalDTO1).isEqualTo(obrafisicomensalDTO2);
        obrafisicomensalDTO2.setId(2L);
        assertThat(obrafisicomensalDTO1).isNotEqualTo(obrafisicomensalDTO2);
        obrafisicomensalDTO1.setId(null);
        assertThat(obrafisicomensalDTO1).isNotEqualTo(obrafisicomensalDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(obrafisicomensalMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(obrafisicomensalMapper.fromId(null)).isNull();
    }
}
