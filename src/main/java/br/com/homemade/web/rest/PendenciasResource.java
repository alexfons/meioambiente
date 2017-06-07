package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Pendencias;

import br.com.homemade.repository.PendenciasRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.PendenciasDTO;
import br.com.homemade.service.mapper.PendenciasMapper;
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
 * REST controller for managing Pendencias.
 */
@RestController
@RequestMapping("/api")
public class PendenciasResource {

    private final Logger log = LoggerFactory.getLogger(PendenciasResource.class);

    private static final String ENTITY_NAME = "pendencias";
        
    private final PendenciasRepository pendenciasRepository;

    private final PendenciasMapper pendenciasMapper;

    public PendenciasResource(PendenciasRepository pendenciasRepository, PendenciasMapper pendenciasMapper) {
        this.pendenciasRepository = pendenciasRepository;
        this.pendenciasMapper = pendenciasMapper;
    }

    /**
     * POST  /pendencias : Create a new pendencias.
     *
     * @param pendenciasDTO the pendenciasDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pendenciasDTO, or with status 400 (Bad Request) if the pendencias has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pendencias")
    @Timed
    public ResponseEntity<PendenciasDTO> createPendencias(@RequestBody PendenciasDTO pendenciasDTO) throws URISyntaxException {
        log.debug("REST request to save Pendencias : {}", pendenciasDTO);
        if (pendenciasDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new pendencias cannot already have an ID")).body(null);
        }
        Pendencias pendencias = pendenciasMapper.toEntity(pendenciasDTO);
        pendencias = pendenciasRepository.save(pendencias);
        PendenciasDTO result = pendenciasMapper.toDto(pendencias);
        return ResponseEntity.created(new URI("/api/pendencias/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pendencias : Updates an existing pendencias.
     *
     * @param pendenciasDTO the pendenciasDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pendenciasDTO,
     * or with status 400 (Bad Request) if the pendenciasDTO is not valid,
     * or with status 500 (Internal Server Error) if the pendenciasDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pendencias")
    @Timed
    public ResponseEntity<PendenciasDTO> updatePendencias(@RequestBody PendenciasDTO pendenciasDTO) throws URISyntaxException {
        log.debug("REST request to update Pendencias : {}", pendenciasDTO);
        if (pendenciasDTO.getId() == null) {
            return createPendencias(pendenciasDTO);
        }
        Pendencias pendencias = pendenciasMapper.toEntity(pendenciasDTO);
        pendencias = pendenciasRepository.save(pendencias);
        PendenciasDTO result = pendenciasMapper.toDto(pendencias);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pendenciasDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pendencias : get all the pendencias.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of pendencias in body
     */
    @GetMapping("/pendencias")
    @Timed
    public List<PendenciasDTO> getAllPendencias() {
        log.debug("REST request to get all Pendencias");
        List<Pendencias> pendencias = pendenciasRepository.findAllWithEagerRelationships();
        return pendenciasMapper.toDto(pendencias);
    }

    /**
     * GET  /pendencias/:id : get the "id" pendencias.
     *
     * @param id the id of the pendenciasDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pendenciasDTO, or with status 404 (Not Found)
     */
    @GetMapping("/pendencias/{id}")
    @Timed
    public ResponseEntity<PendenciasDTO> getPendencias(@PathVariable Long id) {
        log.debug("REST request to get Pendencias : {}", id);
        Pendencias pendencias = pendenciasRepository.findOneWithEagerRelationships(id);
        PendenciasDTO pendenciasDTO = pendenciasMapper.toDto(pendencias);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(pendenciasDTO));
    }

    /**
     * DELETE  /pendencias/:id : delete the "id" pendencias.
     *
     * @param id the id of the pendenciasDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pendencias/{id}")
    @Timed
    public ResponseEntity<Void> deletePendencias(@PathVariable Long id) {
        log.debug("REST request to delete Pendencias : {}", id);
        pendenciasRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
