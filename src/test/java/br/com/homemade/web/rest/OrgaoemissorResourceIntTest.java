package br.com.homemade.web.rest;

import br.com.homemade.MeioambienteApp;

import br.com.homemade.domain.Orgaoemissor;
import br.com.homemade.repository.OrgaoemissorRepository;
import br.com.homemade.service.dto.OrgaoemissorDTO;
import br.com.homemade.service.mapper.OrgaoemissorMapper;
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
 * Test class for the OrgaoemissorResource REST controller.
 *
 * @see OrgaoemissorResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeioambienteApp.class)
public class OrgaoemissorResourceIntTest {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    @Autowired
    private OrgaoemissorRepository orgaoemissorRepository;

    @Autowired
    private OrgaoemissorMapper orgaoemissorMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOrgaoemissorMockMvc;

    private Orgaoemissor orgaoemissor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        OrgaoemissorResource orgaoemissorResource = new OrgaoemissorResource(orgaoemissorRepository, orgaoemissorMapper);
        this.restOrgaoemissorMockMvc = MockMvcBuilders.standaloneSetup(orgaoemissorResource)
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
    public static Orgaoemissor createEntity(EntityManager em) {
        Orgaoemissor orgaoemissor = new Orgaoemissor()
            .descricao(DEFAULT_DESCRICAO);
        return orgaoemissor;
    }

    @Before
    public void initTest() {
        orgaoemissor = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrgaoemissor() throws Exception {
        int databaseSizeBeforeCreate = orgaoemissorRepository.findAll().size();

        // Create the Orgaoemissor
        OrgaoemissorDTO orgaoemissorDTO = orgaoemissorMapper.toDto(orgaoemissor);
        restOrgaoemissorMockMvc.perform(post("/api/orgaoemissors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orgaoemissorDTO)))
            .andExpect(status().isCreated());

        // Validate the Orgaoemissor in the database
        List<Orgaoemissor> orgaoemissorList = orgaoemissorRepository.findAll();
        assertThat(orgaoemissorList).hasSize(databaseSizeBeforeCreate + 1);
        Orgaoemissor testOrgaoemissor = orgaoemissorList.get(orgaoemissorList.size() - 1);
        assertThat(testOrgaoemissor.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
    }

    @Test
    @Transactional
    public void createOrgaoemissorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orgaoemissorRepository.findAll().size();

        // Create the Orgaoemissor with an existing ID
        orgaoemissor.setId(1L);
        OrgaoemissorDTO orgaoemissorDTO = orgaoemissorMapper.toDto(orgaoemissor);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrgaoemissorMockMvc.perform(post("/api/orgaoemissors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orgaoemissorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Orgaoemissor> orgaoemissorList = orgaoemissorRepository.findAll();
        assertThat(orgaoemissorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOrgaoemissors() throws Exception {
        // Initialize the database
        orgaoemissorRepository.saveAndFlush(orgaoemissor);

        // Get all the orgaoemissorList
        restOrgaoemissorMockMvc.perform(get("/api/orgaoemissors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orgaoemissor.getId().intValue())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())));
    }

    @Test
    @Transactional
    public void getOrgaoemissor() throws Exception {
        // Initialize the database
        orgaoemissorRepository.saveAndFlush(orgaoemissor);

        // Get the orgaoemissor
        restOrgaoemissorMockMvc.perform(get("/api/orgaoemissors/{id}", orgaoemissor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(orgaoemissor.getId().intValue()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOrgaoemissor() throws Exception {
        // Get the orgaoemissor
        restOrgaoemissorMockMvc.perform(get("/api/orgaoemissors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrgaoemissor() throws Exception {
        // Initialize the database
        orgaoemissorRepository.saveAndFlush(orgaoemissor);
        int databaseSizeBeforeUpdate = orgaoemissorRepository.findAll().size();

        // Update the orgaoemissor
        Orgaoemissor updatedOrgaoemissor = orgaoemissorRepository.findOne(orgaoemissor.getId());
        updatedOrgaoemissor
            .descricao(UPDATED_DESCRICAO);
        OrgaoemissorDTO orgaoemissorDTO = orgaoemissorMapper.toDto(updatedOrgaoemissor);

        restOrgaoemissorMockMvc.perform(put("/api/orgaoemissors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orgaoemissorDTO)))
            .andExpect(status().isOk());

        // Validate the Orgaoemissor in the database
        List<Orgaoemissor> orgaoemissorList = orgaoemissorRepository.findAll();
        assertThat(orgaoemissorList).hasSize(databaseSizeBeforeUpdate);
        Orgaoemissor testOrgaoemissor = orgaoemissorList.get(orgaoemissorList.size() - 1);
        assertThat(testOrgaoemissor.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
    }

    @Test
    @Transactional
    public void updateNonExistingOrgaoemissor() throws Exception {
        int databaseSizeBeforeUpdate = orgaoemissorRepository.findAll().size();

        // Create the Orgaoemissor
        OrgaoemissorDTO orgaoemissorDTO = orgaoemissorMapper.toDto(orgaoemissor);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restOrgaoemissorMockMvc.perform(put("/api/orgaoemissors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orgaoemissorDTO)))
            .andExpect(status().isCreated());

        // Validate the Orgaoemissor in the database
        List<Orgaoemissor> orgaoemissorList = orgaoemissorRepository.findAll();
        assertThat(orgaoemissorList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteOrgaoemissor() throws Exception {
        // Initialize the database
        orgaoemissorRepository.saveAndFlush(orgaoemissor);
        int databaseSizeBeforeDelete = orgaoemissorRepository.findAll().size();

        // Get the orgaoemissor
        restOrgaoemissorMockMvc.perform(delete("/api/orgaoemissors/{id}", orgaoemissor.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Orgaoemissor> orgaoemissorList = orgaoemissorRepository.findAll();
        assertThat(orgaoemissorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Orgaoemissor.class);
        Orgaoemissor orgaoemissor1 = new Orgaoemissor();
        orgaoemissor1.setId(1L);
        Orgaoemissor orgaoemissor2 = new Orgaoemissor();
        orgaoemissor2.setId(orgaoemissor1.getId());
        assertThat(orgaoemissor1).isEqualTo(orgaoemissor2);
        orgaoemissor2.setId(2L);
        assertThat(orgaoemissor1).isNotEqualTo(orgaoemissor2);
        orgaoemissor1.setId(null);
        assertThat(orgaoemissor1).isNotEqualTo(orgaoemissor2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrgaoemissorDTO.class);
        OrgaoemissorDTO orgaoemissorDTO1 = new OrgaoemissorDTO();
        orgaoemissorDTO1.setId(1L);
        OrgaoemissorDTO orgaoemissorDTO2 = new OrgaoemissorDTO();
        assertThat(orgaoemissorDTO1).isNotEqualTo(orgaoemissorDTO2);
        orgaoemissorDTO2.setId(orgaoemissorDTO1.getId());
        assertThat(orgaoemissorDTO1).isEqualTo(orgaoemissorDTO2);
        orgaoemissorDTO2.setId(2L);
        assertThat(orgaoemissorDTO1).isNotEqualTo(orgaoemissorDTO2);
        orgaoemissorDTO1.setId(null);
        assertThat(orgaoemissorDTO1).isNotEqualTo(orgaoemissorDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(orgaoemissorMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(orgaoemissorMapper.fromId(null)).isNull();
    }
}
