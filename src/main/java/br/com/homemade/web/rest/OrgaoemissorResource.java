package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Orgaoemissor;

import br.com.homemade.repository.OrgaoemissorRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.OrgaoemissorDTO;
import br.com.homemade.service.mapper.OrgaoemissorMapper;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Orgaoemissor.
 */
@RestController
@RequestMapping("/api")
public class OrgaoemissorResource {

    private final Logger log = LoggerFactory.getLogger(OrgaoemissorResource.class);

    private static final String ENTITY_NAME = "orgaoemissor";
        
    private final OrgaoemissorRepository orgaoemissorRepository;

    private final OrgaoemissorMapper orgaoemissorMapper;

    public OrgaoemissorResource(OrgaoemissorRepository orgaoemissorRepository, OrgaoemissorMapper orgaoemissorMapper) {
        this.orgaoemissorRepository = orgaoemissorRepository;
        this.orgaoemissorMapper = orgaoemissorMapper;
    }

    /**
     * POST  /orgaoemissors : Create a new orgaoemissor.
     *
     * @param orgaoemissorDTO the orgaoemissorDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new orgaoemissorDTO, or with status 400 (Bad Request) if the orgaoemissor has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/orgaoemissors")
    @Timed
    public ResponseEntity<OrgaoemissorDTO> createOrgaoemissor(@RequestBody OrgaoemissorDTO orgaoemissorDTO) throws URISyntaxException {
        log.debug("REST request to save Orgaoemissor : {}", orgaoemissorDTO);
        if (orgaoemissorDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new orgaoemissor cannot already have an ID")).body(null);
        }
        Orgaoemissor orgaoemissor = orgaoemissorMapper.toEntity(orgaoemissorDTO);
        orgaoemissor = orgaoemissorRepository.save(orgaoemissor);
        OrgaoemissorDTO result = orgaoemissorMapper.toDto(orgaoemissor);
        return ResponseEntity.created(new URI("/api/orgaoemissors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /orgaoemissors : Updates an existing orgaoemissor.
     *
     * @param orgaoemissorDTO the orgaoemissorDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated orgaoemissorDTO,
     * or with status 400 (Bad Request) if the orgaoemissorDTO is not valid,
     * or with status 500 (Internal Server Error) if the orgaoemissorDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/orgaoemissors")
    @Timed
    public ResponseEntity<OrgaoemissorDTO> updateOrgaoemissor(@RequestBody OrgaoemissorDTO orgaoemissorDTO) throws URISyntaxException {
        log.debug("REST request to update Orgaoemissor : {}", orgaoemissorDTO);
        if (orgaoemissorDTO.getId() == null) {
            return createOrgaoemissor(orgaoemissorDTO);
        }
        Orgaoemissor orgaoemissor = orgaoemissorMapper.toEntity(orgaoemissorDTO);
        orgaoemissor = orgaoemissorRepository.save(orgaoemissor);
        OrgaoemissorDTO result = orgaoemissorMapper.toDto(orgaoemissor);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, orgaoemissorDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /orgaoemissors : get all the orgaoemissors.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of orgaoemissors in body
     */
    @GetMapping("/orgaoemissors")
    @Timed
    public List<OrgaoemissorDTO> getAllOrgaoemissors() {
        log.debug("REST request to get all Orgaoemissors");
        List<Orgaoemissor> orgaoemissors = orgaoemissorRepository.findAll();
        return orgaoemissorMapper.toDto(orgaoemissors);
    }

    /**
     * GET  /orgaoemissors/:id : get the "id" orgaoemissor.
     *
     * @param id the id of the orgaoemissorDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the orgaoemissorDTO, or with status 404 (Not Found)
     */
    @GetMapping("/orgaoemissors/{id}")
    @Timed
    public ResponseEntity<OrgaoemissorDTO> getOrgaoemissor(@PathVariable Long id) {
        log.debug("REST request to get Orgaoemissor : {}", id);
        Orgaoemissor orgaoemissor = orgaoemissorRepository.findOne(id);
        OrgaoemissorDTO orgaoemissorDTO = orgaoemissorMapper.toDto(orgaoemissor);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(orgaoemissorDTO));
    }

    /**
     * DELETE  /orgaoemissors/:id : delete the "id" orgaoemissor.
     *
     * @param id the id of the orgaoemissorDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/orgaoemissors/{id}")
    @Timed
    public ResponseEntity<Void> deleteOrgaoemissor(@PathVariable Long id) {
        log.debug("REST request to delete Orgaoemissor : {}", id);
        orgaoemissorRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
