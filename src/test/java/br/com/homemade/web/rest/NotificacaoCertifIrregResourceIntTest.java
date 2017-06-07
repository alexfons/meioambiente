package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.NotificacaoCertifIrreg;
import br.com.homemade.repository.NotificacaoCertifIrregRepository;
import br.com.homemade.service.dto.NotificacaoCertifIrregDTO;
import br.com.homemade.service.mapper.NotificacaoCertifIrregMapper;
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
 * Test class for the NotificacaoCertifIrregResource REST controller.
 *
 * @see NotificacaoCertifIrregResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class NotificacaoCertifIrregResourceIntTest {

    @Autowired
    private NotificacaoCertifIrregRepository notificacaoCertifIrregRepository;

    @Autowired
    private NotificacaoCertifIrregMapper notificacaoCertifIrregMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restNotificacaoCertifIrregMockMvc;

    private NotificacaoCertifIrreg notificacaoCertifIrreg;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        NotificacaoCertifIrregResource notificacaoCertifIrregResource = new NotificacaoCertifIrregResource(notificacaoCertifIrregRepository, notificacaoCertifIrregMapper);
        this.restNotificacaoCertifIrregMockMvc = MockMvcBuilders.standaloneSetup(notificacaoCertifIrregResource)
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
    public static NotificacaoCertifIrreg createEntity(EntityManager em) {
        NotificacaoCertifIrreg notificacaoCertifIrreg = new NotificacaoCertifIrreg();
        return notificacaoCertifIrreg;
    }

    @Before
    public void initTest() {
        notificacaoCertifIrreg = createEntity(em);
    }

    @Test
    @Transactional
    public void createNotificacaoCertifIrreg() throws Exception {
        int databaseSizeBeforeCreate = notificacaoCertifIrregRepository.findAll().size();

        // Create the NotificacaoCertifIrreg
        NotificacaoCertifIrregDTO notificacaoCertifIrregDTO = notificacaoCertifIrregMapper.toDto(notificacaoCertifIrreg);
        restNotificacaoCertifIrregMockMvc.perform(post("/api/notificacao-certif-irregs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificacaoCertifIrregDTO)))
            .andExpect(status().isCreated());

        // Validate the NotificacaoCertifIrreg in the database
        List<NotificacaoCertifIrreg> notificacaoCertifIrregList = notificacaoCertifIrregRepository.findAll();
        assertThat(notificacaoCertifIrregList).hasSize(databaseSizeBeforeCreate + 1);
        NotificacaoCertifIrreg testNotificacaoCertifIrreg = notificacaoCertifIrregList.get(notificacaoCertifIrregList.size() - 1);
    }

    @Test
    @Transactional
    public void createNotificacaoCertifIrregWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = notificacaoCertifIrregRepository.findAll().size();

        // Create the NotificacaoCertifIrreg with an existing ID
        notificacaoCertifIrreg.setId(1L);
        NotificacaoCertifIrregDTO notificacaoCertifIrregDTO = notificacaoCertifIrregMapper.toDto(notificacaoCertifIrreg);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNotificacaoCertifIrregMockMvc.perform(post("/api/notificacao-certif-irregs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificacaoCertifIrregDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<NotificacaoCertifIrreg> notificacaoCertifIrregList = notificacaoCertifIrregRepository.findAll();
        assertThat(notificacaoCertifIrregList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllNotificacaoCertifIrregs() throws Exception {
        // Initialize the database
        notificacaoCertifIrregRepository.saveAndFlush(notificacaoCertifIrreg);

        // Get all the notificacaoCertifIrregList
        restNotificacaoCertifIrregMockMvc.perform(get("/api/notificacao-certif-irregs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notificacaoCertifIrreg.getId().intValue())));
    }

    @Test
    @Transactional
    public void getNotificacaoCertifIrreg() throws Exception {
        // Initialize the database
        notificacaoCertifIrregRepository.saveAndFlush(notificacaoCertifIrreg);

        // Get the notificacaoCertifIrreg
        restNotificacaoCertifIrregMockMvc.perform(get("/api/notificacao-certif-irregs/{id}", notificacaoCertifIrreg.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(notificacaoCertifIrreg.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingNotificacaoCertifIrreg() throws Exception {
        // Get the notificacaoCertifIrreg
        restNotificacaoCertifIrregMockMvc.perform(get("/api/notificacao-certif-irregs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNotificacaoCertifIrreg() throws Exception {
        // Initialize the database
        notificacaoCertifIrregRepository.saveAndFlush(notificacaoCertifIrreg);
        int databaseSizeBeforeUpdate = notificacaoCertifIrregRepository.findAll().size();

        // Update the notificacaoCertifIrreg
        NotificacaoCertifIrreg updatedNotificacaoCertifIrreg = notificacaoCertifIrregRepository.findOne(notificacaoCertifIrreg.getId());
        NotificacaoCertifIrregDTO notificacaoCertifIrregDTO = notificacaoCertifIrregMapper.toDto(updatedNotificacaoCertifIrreg);

        restNotificacaoCertifIrregMockMvc.perform(put("/api/notificacao-certif-irregs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificacaoCertifIrregDTO)))
            .andExpect(status().isOk());

        // Validate the NotificacaoCertifIrreg in the database
        List<NotificacaoCertifIrreg> notificacaoCertifIrregList = notificacaoCertifIrregRepository.findAll();
        assertThat(notificacaoCertifIrregList).hasSize(databaseSizeBeforeUpdate);
        NotificacaoCertifIrreg testNotificacaoCertifIrreg = notificacaoCertifIrregList.get(notificacaoCertifIrregList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingNotificacaoCertifIrreg() throws Exception {
        int databaseSizeBeforeUpdate = notificacaoCertifIrregRepository.findAll().size();

        // Create the NotificacaoCertifIrreg
        NotificacaoCertifIrregDTO notificacaoCertifIrregDTO = notificacaoCertifIrregMapper.toDto(notificacaoCertifIrreg);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restNotificacaoCertifIrregMockMvc.perform(put("/api/notificacao-certif-irregs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificacaoCertifIrregDTO)))
            .andExpect(status().isCreated());

        // Validate the NotificacaoCertifIrreg in the database
        List<NotificacaoCertifIrreg> notificacaoCertifIrregList = notificacaoCertifIrregRepository.findAll();
        assertThat(notificacaoCertifIrregList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteNotificacaoCertifIrreg() throws Exception {
        // Initialize the database
        notificacaoCertifIrregRepository.saveAndFlush(notificacaoCertifIrreg);
        int databaseSizeBeforeDelete = notificacaoCertifIrregRepository.findAll().size();

        // Get the notificacaoCertifIrreg
        restNotificacaoCertifIrregMockMvc.perform(delete("/api/notificacao-certif-irregs/{id}", notificacaoCertifIrreg.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<NotificacaoCertifIrreg> notificacaoCertifIrregList = notificacaoCertifIrregRepository.findAll();
        assertThat(notificacaoCertifIrregList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotificacaoCertifIrreg.class);
        NotificacaoCertifIrreg notificacaoCertifIrreg1 = new NotificacaoCertifIrreg();
        notificacaoCertifIrreg1.setId(1L);
        NotificacaoCertifIrreg notificacaoCertifIrreg2 = new NotificacaoCertifIrreg();
        notificacaoCertifIrreg2.setId(notificacaoCertifIrreg1.getId());
        assertThat(notificacaoCertifIrreg1).isEqualTo(notificacaoCertifIrreg2);
        notificacaoCertifIrreg2.setId(2L);
        assertThat(notificacaoCertifIrreg1).isNotEqualTo(notificacaoCertifIrreg2);
        notificacaoCertifIrreg1.setId(null);
        assertThat(notificacaoCertifIrreg1).isNotEqualTo(notificacaoCertifIrreg2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotificacaoCertifIrregDTO.class);
        NotificacaoCertifIrregDTO notificacaoCertifIrregDTO1 = new NotificacaoCertifIrregDTO();
        notificacaoCertifIrregDTO1.setId(1L);
        NotificacaoCertifIrregDTO notificacaoCertifIrregDTO2 = new NotificacaoCertifIrregDTO();
        assertThat(notificacaoCertifIrregDTO1).isNotEqualTo(notificacaoCertifIrregDTO2);
        notificacaoCertifIrregDTO2.setId(notificacaoCertifIrregDTO1.getId());
        assertThat(notificacaoCertifIrregDTO1).isEqualTo(notificacaoCertifIrregDTO2);
        notificacaoCertifIrregDTO2.setId(2L);
        assertThat(notificacaoCertifIrregDTO1).isNotEqualTo(notificacaoCertifIrregDTO2);
        notificacaoCertifIrregDTO1.setId(null);
        assertThat(notificacaoCertifIrregDTO1).isNotEqualTo(notificacaoCertifIrregDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(notificacaoCertifIrregMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(notificacaoCertifIrregMapper.fromId(null)).isNull();
    }
}
