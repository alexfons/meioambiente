package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Referencia;

import br.com.homemade.repository.ReferenciaRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.ReferenciaDTO;
import br.com.homemade.service.mapper.ReferenciaMapper;
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
 * REST controller for managing Referencia.
 */
@RestController
@RequestMapping("/api")
public class ReferenciaResource {

    private final Logger log = LoggerFactory.getLogger(ReferenciaResource.class);

    private static final String ENTITY_NAME = "referencia";
        
    private final ReferenciaRepository referenciaRepository;

    private final ReferenciaMapper referenciaMapper;

    public ReferenciaResource(ReferenciaRepository referenciaRepository, ReferenciaMapper referenciaMapper) {
        this.referenciaRepository = referenciaRepository;
        this.referenciaMapper = referenciaMapper;
    }

    /**
     * POST  /referencias : Create a new referencia.
     *
     * @param referenciaDTO the referenciaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new referenciaDTO, or with status 400 (Bad Request) if the referencia has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/referencias")
    @Timed
    public ResponseEntity<ReferenciaDTO> createReferencia(@RequestBody ReferenciaDTO referenciaDTO) throws URISyntaxException {
        log.debug("REST request to save Referencia : {}", referenciaDTO);
        if (referenciaDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new referencia cannot already have an ID")).body(null);
        }
        Referencia referencia = referenciaMapper.toEntity(referenciaDTO);
        referencia = referenciaRepository.save(referencia);
        ReferenciaDTO result = referenciaMapper.toDto(referencia);
        return ResponseEntity.created(new URI("/api/referencias/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /referencias : Updates an existing referencia.
     *
     * @param referenciaDTO the referenciaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated referenciaDTO,
     * or with status 400 (Bad Request) if the referenciaDTO is not valid,
     * or with status 500 (Internal Server Error) if the referenciaDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/referencias")
    @Timed
    public ResponseEntity<ReferenciaDTO> updateReferencia(@RequestBody ReferenciaDTO referenciaDTO) throws URISyntaxException {
        log.debug("REST request to update Referencia : {}", referenciaDTO);
        if (referenciaDTO.getId() == null) {
            return createReferencia(referenciaDTO);
        }
        Referencia referencia = referenciaMapper.toEntity(referenciaDTO);
        referencia = referenciaRepository.save(referencia);
        ReferenciaDTO result = referenciaMapper.toDto(referencia);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, referenciaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /referencias : get all the referencias.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of referencias in body
     */
    @GetMapping("/referencias")
    @Timed
    public List<ReferenciaDTO> getAllReferencias() {
        log.debug("REST request to get all Referencias");
        List<Referencia> referencias = referenciaRepository.findAll();
        return referenciaMapper.toDto(referencias);
    }

    /**
     * GET  /referencias/:id : get the "id" referencia.
     *
     * @param id the id of the referenciaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the referenciaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/referencias/{id}")
    @Timed
    public ResponseEntity<ReferenciaDTO> getReferencia(@PathVariable Long id) {
        log.debug("REST request to get Referencia : {}", id);
        Referencia referencia = referenciaRepository.findOne(id);
        ReferenciaDTO referenciaDTO = referenciaMapper.toDto(referencia);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(referenciaDTO));
    }

    /**
     * DELETE  /referencias/:id : delete the "id" referencia.
     *
     * @param id the id of the referenciaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/referencias/{id}")
    @Timed
    public ResponseEntity<Void> deleteReferencia(@PathVariable Long id) {
        log.debug("REST request to delete Referencia : {}", id);
        referenciaRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
