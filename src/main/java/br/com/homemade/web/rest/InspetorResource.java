package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Inspetor;

import br.com.homemade.repository.InspetorRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.InspetorDTO;
import br.com.homemade.service.mapper.InspetorMapper;
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
 * REST controller for managing Inspetor.
 */
@RestController
@RequestMapping("/api")
public class InspetorResource {

    private final Logger log = LoggerFactory.getLogger(InspetorResource.class);

    private static final String ENTITY_NAME = "inspetor";
        
    private final InspetorRepository inspetorRepository;

    private final InspetorMapper inspetorMapper;

    public InspetorResource(InspetorRepository inspetorRepository, InspetorMapper inspetorMapper) {
        this.inspetorRepository = inspetorRepository;
        this.inspetorMapper = inspetorMapper;
    }

    /**
     * POST  /inspetors : Create a new inspetor.
     *
     * @param inspetorDTO the inspetorDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new inspetorDTO, or with status 400 (Bad Request) if the inspetor has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/inspetors")
    @Timed
    public ResponseEntity<InspetorDTO> createInspetor(@RequestBody InspetorDTO inspetorDTO) throws URISyntaxException {
        log.debug("REST request to save Inspetor : {}", inspetorDTO);
        if (inspetorDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new inspetor cannot already have an ID")).body(null);
        }
        Inspetor inspetor = inspetorMapper.toEntity(inspetorDTO);
        inspetor = inspetorRepository.save(inspetor);
        InspetorDTO result = inspetorMapper.toDto(inspetor);
        return ResponseEntity.created(new URI("/api/inspetors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /inspetors : Updates an existing inspetor.
     *
     * @param inspetorDTO the inspetorDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated inspetorDTO,
     * or with status 400 (Bad Request) if the inspetorDTO is not valid,
     * or with status 500 (Internal Server Error) if the inspetorDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/inspetors")
    @Timed
    public ResponseEntity<InspetorDTO> updateInspetor(@RequestBody InspetorDTO inspetorDTO) throws URISyntaxException {
        log.debug("REST request to update Inspetor : {}", inspetorDTO);
        if (inspetorDTO.getId() == null) {
            return createInspetor(inspetorDTO);
        }
        Inspetor inspetor = inspetorMapper.toEntity(inspetorDTO);
        inspetor = inspetorRepository.save(inspetor);
        InspetorDTO result = inspetorMapper.toDto(inspetor);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, inspetorDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /inspetors : get all the inspetors.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of inspetors in body
     */
    @GetMapping("/inspetors")
    @Timed
    public List<InspetorDTO> getAllInspetors() {
        log.debug("REST request to get all Inspetors");
        List<Inspetor> inspetors = inspetorRepository.findAll();
        return inspetorMapper.toDto(inspetors);
    }

    /**
     * GET  /inspetors/:id : get the "id" inspetor.
     *
     * @param id the id of the inspetorDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the inspetorDTO, or with status 404 (Not Found)
     */
    @GetMapping("/inspetors/{id}")
    @Timed
    public ResponseEntity<InspetorDTO> getInspetor(@PathVariable Long id) {
        log.debug("REST request to get Inspetor : {}", id);
        Inspetor inspetor = inspetorRepository.findOne(id);
        InspetorDTO inspetorDTO = inspetorMapper.toDto(inspetor);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(inspetorDTO));
    }

    /**
     * DELETE  /inspetors/:id : delete the "id" inspetor.
     *
     * @param id the id of the inspetorDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/inspetors/{id}")
    @Timed
    public ResponseEntity<Void> deleteInspetor(@PathVariable Long id) {
        log.debug("REST request to delete Inspetor : {}", id);
        inspetorRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
