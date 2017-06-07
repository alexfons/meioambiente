package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Ocorrencia;

import br.com.homemade.repository.OcorrenciaRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.OcorrenciaDTO;
import br.com.homemade.service.mapper.OcorrenciaMapper;
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
 * REST controller for managing Ocorrencia.
 */
@RestController
@RequestMapping("/api")
public class OcorrenciaResource {

    private final Logger log = LoggerFactory.getLogger(OcorrenciaResource.class);

    private static final String ENTITY_NAME = "ocorrencia";
        
    private final OcorrenciaRepository ocorrenciaRepository;

    private final OcorrenciaMapper ocorrenciaMapper;

    public OcorrenciaResource(OcorrenciaRepository ocorrenciaRepository, OcorrenciaMapper ocorrenciaMapper) {
        this.ocorrenciaRepository = ocorrenciaRepository;
        this.ocorrenciaMapper = ocorrenciaMapper;
    }

    /**
     * POST  /ocorrencias : Create a new ocorrencia.
     *
     * @param ocorrenciaDTO the ocorrenciaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ocorrenciaDTO, or with status 400 (Bad Request) if the ocorrencia has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ocorrencias")
    @Timed
    public ResponseEntity<OcorrenciaDTO> createOcorrencia(@RequestBody OcorrenciaDTO ocorrenciaDTO) throws URISyntaxException {
        log.debug("REST request to save Ocorrencia : {}", ocorrenciaDTO);
        if (ocorrenciaDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new ocorrencia cannot already have an ID")).body(null);
        }
        Ocorrencia ocorrencia = ocorrenciaMapper.toEntity(ocorrenciaDTO);
        ocorrencia = ocorrenciaRepository.save(ocorrencia);
        OcorrenciaDTO result = ocorrenciaMapper.toDto(ocorrencia);
        return ResponseEntity.created(new URI("/api/ocorrencias/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ocorrencias : Updates an existing ocorrencia.
     *
     * @param ocorrenciaDTO the ocorrenciaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ocorrenciaDTO,
     * or with status 400 (Bad Request) if the ocorrenciaDTO is not valid,
     * or with status 500 (Internal Server Error) if the ocorrenciaDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ocorrencias")
    @Timed
    public ResponseEntity<OcorrenciaDTO> updateOcorrencia(@RequestBody OcorrenciaDTO ocorrenciaDTO) throws URISyntaxException {
        log.debug("REST request to update Ocorrencia : {}", ocorrenciaDTO);
        if (ocorrenciaDTO.getId() == null) {
            return createOcorrencia(ocorrenciaDTO);
        }
        Ocorrencia ocorrencia = ocorrenciaMapper.toEntity(ocorrenciaDTO);
        ocorrencia = ocorrenciaRepository.save(ocorrencia);
        OcorrenciaDTO result = ocorrenciaMapper.toDto(ocorrencia);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ocorrenciaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ocorrencias : get all the ocorrencias.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of ocorrencias in body
     */
    @GetMapping("/ocorrencias")
    @Timed
    public List<OcorrenciaDTO> getAllOcorrencias() {
        log.debug("REST request to get all Ocorrencias");
        List<Ocorrencia> ocorrencias = ocorrenciaRepository.findAllWithEagerRelationships();
        return ocorrenciaMapper.toDto(ocorrencias);
    }

    /**
     * GET  /ocorrencias/:id : get the "id" ocorrencia.
     *
     * @param id the id of the ocorrenciaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ocorrenciaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ocorrencias/{id}")
    @Timed
    public ResponseEntity<OcorrenciaDTO> getOcorrencia(@PathVariable Long id) {
        log.debug("REST request to get Ocorrencia : {}", id);
        Ocorrencia ocorrencia = ocorrenciaRepository.findOneWithEagerRelationships(id);
        OcorrenciaDTO ocorrenciaDTO = ocorrenciaMapper.toDto(ocorrencia);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(ocorrenciaDTO));
    }

    /**
     * DELETE  /ocorrencias/:id : delete the "id" ocorrencia.
     *
     * @param id the id of the ocorrenciaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ocorrencias/{id}")
    @Timed
    public ResponseEntity<Void> deleteOcorrencia(@PathVariable Long id) {
        log.debug("REST request to delete Ocorrencia : {}", id);
        ocorrenciaRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
