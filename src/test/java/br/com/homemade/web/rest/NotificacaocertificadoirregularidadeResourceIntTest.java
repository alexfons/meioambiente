package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Notificacaocertificadoirregularidade;
import br.com.homemade.repository.NotificacaocertificadoirregularidadeRepository;
import br.com.homemade.service.dto.NotificacaocertificadoirregularidadeDTO;
import br.com.homemade.service.mapper.NotificacaocertificadoirregularidadeMapper;
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
 * Test class for the NotificacaocertificadoirregularidadeResource REST controller.
 *
 * @see NotificacaocertificadoirregularidadeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class NotificacaocertificadoirregularidadeResourceIntTest {

    @Autowired
    private NotificacaocertificadoirregularidadeRepository notificacaocertificadoirregularidadeRepository;

    @Autowired
    private NotificacaocertificadoirregularidadeMapper notificacaocertificadoirregularidadeMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restNotificacaocertificadoirregularidadeMockMvc;

    private Notificacaocertificadoirregularidade notificacaocertificadoirregularidade;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        NotificacaocertificadoirregularidadeResource notificacaocertificadoirregularidadeResource = new NotificacaocertificadoirregularidadeResource(notificacaocertificadoirregularidadeRepository, notificacaocertificadoirregularidadeMapper);
        this.restNotificacaocertificadoirregularidadeMockMvc = MockMvcBuilders.standaloneSetup(notificacaocertificadoirregularidadeResource)
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
    public static Notificacaocertificadoirregularidade createEntity(EntityManager em) {
        Notificacaocertificadoirregularidade notificacaocertificadoirregularidade = new Notificacaocertificadoirregularidade();
        return notificacaocertificadoirregularidade;
    }

    @Before
    public void initTest() {
        notificacaocertificadoirregularidade = createEntity(em);
    }

    @Test
    @Transactional
    public void createNotificacaocertificadoirregularidade() throws Exception {
        int databaseSizeBeforeCreate = notificacaocertificadoirregularidadeRepository.findAll().size();

        // Create the Notificacaocertificadoirregularidade
        NotificacaocertificadoirregularidadeDTO notificacaocertificadoirregularidadeDTO = notificacaocertificadoirregularidadeMapper.toDto(notificacaocertificadoirregularidade);
        restNotificacaocertificadoirregularidadeMockMvc.perform(post("/api/notificacaocertificadoirregularidades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificacaocertificadoirregularidadeDTO)))
            .andExpect(status().isCreated());

        // Validate the Notificacaocertificadoirregularidade in the database
        List<Notificacaocertificadoirregularidade> notificacaocertificadoirregularidadeList = notificacaocertificadoirregularidadeRepository.findAll();
        assertThat(notificacaocertificadoirregularidadeList).hasSize(databaseSizeBeforeCreate + 1);
        Notificacaocertificadoirregularidade testNotificacaocertificadoirregularidade = notificacaocertificadoirregularidadeList.get(notificacaocertificadoirregularidadeList.size() - 1);
    }

    @Test
    @Transactional
    public void createNotificacaocertificadoirregularidadeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = notificacaocertificadoirregularidadeRepository.findAll().size();

        // Create the Notificacaocertificadoirregularidade with an existing ID
        notificacaocertificadoirregularidade.setId(1L);
        NotificacaocertificadoirregularidadeDTO notificacaocertificadoirregularidadeDTO = notificacaocertificadoirregularidadeMapper.toDto(notificacaocertificadoirregularidade);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNotificacaocertificadoirregularidadeMockMvc.perform(post("/api/notificacaocertificadoirregularidades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificacaocertificadoirregularidadeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Notificacaocertificadoirregularidade> notificacaocertificadoirregularidadeList = notificacaocertificadoirregularidadeRepository.findAll();
        assertThat(notificacaocertificadoirregularidadeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllNotificacaocertificadoirregularidades() throws Exception {
        // Initialize the database
        notificacaocertificadoirregularidadeRepository.saveAndFlush(notificacaocertificadoirregularidade);

        // Get all the notificacaocertificadoirregularidadeList
        restNotificacaocertificadoirregularidadeMockMvc.perform(get("/api/notificacaocertificadoirregularidades?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notificacaocertificadoirregularidade.getId().intValue())));
    }

    @Test
    @Transactional
    public void getNotificacaocertificadoirregularidade() throws Exception {
        // Initialize the database
        notificacaocertificadoirregularidadeRepository.saveAndFlush(notificacaocertificadoirregularidade);

        // Get the notificacaocertificadoirregularidade
        restNotificacaocertificadoirregularidadeMockMvc.perform(get("/api/notificacaocertificadoirregularidades/{id}", notificacaocertificadoirregularidade.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(notificacaocertificadoirregularidade.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingNotificacaocertificadoirregularidade() throws Exception {
        // Get the notificacaocertificadoirregularidade
        restNotificacaocertificadoirregularidadeMockMvc.perform(get("/api/notificacaocertificadoirregularidades/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNotificacaocertificadoirregularidade() throws Exception {
        // Initialize the database
        notificacaocertificadoirregularidadeRepository.saveAndFlush(notificacaocertificadoirregularidade);
        int databaseSizeBeforeUpdate = notificacaocertificadoirregularidadeRepository.findAll().size();

        // Update the notificacaocertificadoirregularidade
        Notificacaocertificadoirregularidade updatedNotificacaocertificadoirregularidade = notificacaocertificadoirregularidadeRepository.findOne(notificacaocertificadoirregularidade.getId());
        NotificacaocertificadoirregularidadeDTO notificacaocertificadoirregularidadeDTO = notificacaocertificadoirregularidadeMapper.toDto(updatedNotificacaocertificadoirregularidade);

        restNotificacaocertificadoirregularidadeMockMvc.perform(put("/api/notificacaocertificadoirregularidades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificacaocertificadoirregularidadeDTO)))
            .andExpect(status().isOk());

        // Validate the Notificacaocertificadoirregularidade in the database
        List<Notificacaocertificadoirregularidade> notificacaocertificadoirregularidadeList = notificacaocertificadoirregularidadeRepository.findAll();
        assertThat(notificacaocertificadoirregularidadeList).hasSize(databaseSizeBeforeUpdate);
        Notificacaocertificadoirregularidade testNotificacaocertificadoirregularidade = notificacaocertificadoirregularidadeList.get(notificacaocertificadoirregularidadeList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingNotificacaocertificadoirregularidade() throws Exception {
        int databaseSizeBeforeUpdate = notificacaocertificadoirregularidadeRepository.findAll().size();

        // Create the Notificacaocertificadoirregularidade
        NotificacaocertificadoirregularidadeDTO notificacaocertificadoirregularidadeDTO = notificacaocertificadoirregularidadeMapper.toDto(notificacaocertificadoirregularidade);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restNotificacaocertificadoirregularidadeMockMvc.perform(put("/api/notificacaocertificadoirregularidades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificacaocertificadoirregularidadeDTO)))
            .andExpect(status().isCreated());

        // Validate the Notificacaocertificadoirregularidade in the database
        List<Notificacaocertificadoirregularidade> notificacaocertificadoirregularidadeList = notificacaocertificadoirregularidadeRepository.findAll();
        assertThat(notificacaocertificadoirregularidadeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteNotificacaocertificadoirregularidade() throws Exception {
        // Initialize the database
        notificacaocertificadoirregularidadeRepository.saveAndFlush(notificacaocertificadoirregularidade);
        int databaseSizeBeforeDelete = notificacaocertificadoirregularidadeRepository.findAll().size();

        // Get the notificacaocertificadoirregularidade
        restNotificacaocertificadoirregularidadeMockMvc.perform(delete("/api/notificacaocertificadoirregularidades/{id}", notificacaocertificadoirregularidade.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Notificacaocertificadoirregularidade> notificacaocertificadoirregularidadeList = notificacaocertificadoirregularidadeRepository.findAll();
        assertThat(notificacaocertificadoirregularidadeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Notificacaocertificadoirregularidade.class);
        Notificacaocertificadoirregularidade notificacaocertificadoirregularidade1 = new Notificacaocertificadoirregularidade();
        notificacaocertificadoirregularidade1.setId(1L);
        Notificacaocertificadoirregularidade notificacaocertificadoirregularidade2 = new Notificacaocertificadoirregularidade();
        notificacaocertificadoirregularidade2.setId(notificacaocertificadoirregularidade1.getId());
        assertThat(notificacaocertificadoirregularidade1).isEqualTo(notificacaocertificadoirregularidade2);
        notificacaocertificadoirregularidade2.setId(2L);
        assertThat(notificacaocertificadoirregularidade1).isNotEqualTo(notificacaocertificadoirregularidade2);
        notificacaocertificadoirregularidade1.setId(null);
        assertThat(notificacaocertificadoirregularidade1).isNotEqualTo(notificacaocertificadoirregularidade2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotificacaocertificadoirregularidadeDTO.class);
        NotificacaocertificadoirregularidadeDTO notificacaocertificadoirregularidadeDTO1 = new NotificacaocertificadoirregularidadeDTO();
        notificacaocertificadoirregularidadeDTO1.setId(1L);
        NotificacaocertificadoirregularidadeDTO notificacaocertificadoirregularidadeDTO2 = new NotificacaocertificadoirregularidadeDTO();
        assertThat(notificacaocertificadoirregularidadeDTO1).isNotEqualTo(notificacaocertificadoirregularidadeDTO2);
        notificacaocertificadoirregularidadeDTO2.setId(notificacaocertificadoirregularidadeDTO1.getId());
        assertThat(notificacaocertificadoirregularidadeDTO1).isEqualTo(notificacaocertificadoirregularidadeDTO2);
        notificacaocertificadoirregularidadeDTO2.setId(2L);
        assertThat(notificacaocertificadoirregularidadeDTO1).isNotEqualTo(notificacaocertificadoirregularidadeDTO2);
        notificacaocertificadoirregularidadeDTO1.setId(null);
        assertThat(notificacaocertificadoirregularidadeDTO1).isNotEqualTo(notificacaocertificadoirregularidadeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(notificacaocertificadoirregularidadeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(notificacaocertificadoirregularidadeMapper.fromId(null)).isNull();
    }
}
