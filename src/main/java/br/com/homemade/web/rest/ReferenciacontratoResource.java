package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Referenciacontrato;

import br.com.homemade.repository.ReferenciacontratoRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.ReferenciacontratoDTO;
import br.com.homemade.service.mapper.ReferenciacontratoMapper;
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
 * REST controller for managing Referenciacontrato.
 */
@RestController
@RequestMapping("/api")
public class ReferenciacontratoResource {

    private final Logger log = LoggerFactory.getLogger(ReferenciacontratoResource.class);

    private static final String ENTITY_NAME = "referenciacontrato";
        
    private final ReferenciacontratoRepository referenciacontratoRepository;

    private final ReferenciacontratoMapper referenciacontratoMapper;

    public ReferenciacontratoResource(ReferenciacontratoRepository referenciacontratoRepository, ReferenciacontratoMapper referenciacontratoMapper) {
        this.referenciacontratoRepository = referenciacontratoRepository;
        this.referenciacontratoMapper = referenciacontratoMapper;
    }

    /**
     * POST  /referenciacontratoes : Create a new referenciacontrato.
     *
     * @param referenciacontratoDTO the referenciacontratoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new referenciacontratoDTO, or with status 400 (Bad Request) if the referenciacontrato has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/referenciacontratoes")
    @Timed
    public ResponseEntity<ReferenciacontratoDTO> createReferenciacontrato(@RequestBody ReferenciacontratoDTO referenciacontratoDTO) throws URISyntaxException {
        log.debug("REST request to save Referenciacontrato : {}", referenciacontratoDTO);
        if (referenciacontratoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new referenciacontrato cannot already have an ID")).body(null);
        }
        Referenciacontrato referenciacontrato = referenciacontratoMapper.toEntity(referenciacontratoDTO);
        referenciacontrato = referenciacontratoRepository.save(referenciacontrato);
        ReferenciacontratoDTO result = referenciacontratoMapper.toDto(referenciacontrato);
        return ResponseEntity.created(new URI("/api/referenciacontratoes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /referenciacontratoes : Updates an existing referenciacontrato.
     *
     * @param referenciacontratoDTO the referenciacontratoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated referenciacontratoDTO,
     * or with status 400 (Bad Request) if the referenciacontratoDTO is not valid,
     * or with status 500 (Internal Server Error) if the referenciacontratoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/referenciacontratoes")
    @Timed
    public ResponseEntity<ReferenciacontratoDTO> updateReferenciacontrato(@RequestBody ReferenciacontratoDTO referenciacontratoDTO) throws URISyntaxException {
        log.debug("REST request to update Referenciacontrato : {}", referenciacontratoDTO);
        if (referenciacontratoDTO.getId() == null) {
            return createReferenciacontrato(referenciacontratoDTO);
        }
        Referenciacontrato referenciacontrato = referenciacontratoMapper.toEntity(referenciacontratoDTO);
        referenciacontrato = referenciacontratoRepository.save(referenciacontrato);
        ReferenciacontratoDTO result = referenciacontratoMapper.toDto(referenciacontrato);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, referenciacontratoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /referenciacontratoes : get all the referenciacontratoes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of referenciacontratoes in body
     */
    @GetMapping("/referenciacontratoes")
    @Timed
    public List<ReferenciacontratoDTO> getAllReferenciacontratoes() {
        log.debug("REST request to get all Referenciacontratoes");
        List<Referenciacontrato> referenciacontratoes = referenciacontratoRepository.findAll();
        return referenciacontratoMapper.toDto(referenciacontratoes);
    }

    /**
     * GET  /referenciacontratoes/:id : get the "id" referenciacontrato.
     *
     * @param id the id of the referenciacontratoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the referenciacontratoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/referenciacontratoes/{id}")
    @Timed
    public ResponseEntity<ReferenciacontratoDTO> getReferenciacontrato(@PathVariable Long id) {
        log.debug("REST request to get Referenciacontrato : {}", id);
        Referenciacontrato referenciacontrato = referenciacontratoRepository.findOne(id);
        ReferenciacontratoDTO referenciacontratoDTO = referenciacontratoMapper.toDto(referenciacontrato);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(referenciacontratoDTO));
    }

    /**
     * DELETE  /referenciacontratoes/:id : delete the "id" referenciacontrato.
     *
     * @param id the id of the referenciacontratoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/referenciacontratoes/{id}")
    @Timed
    public ResponseEntity<Void> deleteReferenciacontrato(@PathVariable Long id) {
        log.debug("REST request to delete Referenciacontrato : {}", id);
        referenciacontratoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
