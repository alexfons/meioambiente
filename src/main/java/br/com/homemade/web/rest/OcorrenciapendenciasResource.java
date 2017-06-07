package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Ocorrenciapendencias;

import br.com.homemade.repository.OcorrenciapendenciasRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.OcorrenciapendenciasDTO;
import br.com.homemade.service.mapper.OcorrenciapendenciasMapper;
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
 * REST controller for managing Ocorrenciapendencias.
 */
@RestController
@RequestMapping("/api")
public class OcorrenciapendenciasResource {

    private final Logger log = LoggerFactory.getLogger(OcorrenciapendenciasResource.class);

    private static final String ENTITY_NAME = "ocorrenciapendencias";
        
    private final OcorrenciapendenciasRepository ocorrenciapendenciasRepository;

    private final OcorrenciapendenciasMapper ocorrenciapendenciasMapper;

    public OcorrenciapendenciasResource(OcorrenciapendenciasRepository ocorrenciapendenciasRepository, OcorrenciapendenciasMapper ocorrenciapendenciasMapper) {
        this.ocorrenciapendenciasRepository = ocorrenciapendenciasRepository;
        this.ocorrenciapendenciasMapper = ocorrenciapendenciasMapper;
    }

    /**
     * POST  /ocorrenciapendencias : Create a new ocorrenciapendencias.
     *
     * @param ocorrenciapendenciasDTO the ocorrenciapendenciasDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ocorrenciapendenciasDTO, or with status 400 (Bad Request) if the ocorrenciapendencias has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ocorrenciapendencias")
    @Timed
    public ResponseEntity<OcorrenciapendenciasDTO> createOcorrenciapendencias(@RequestBody OcorrenciapendenciasDTO ocorrenciapendenciasDTO) throws URISyntaxException {
        log.debug("REST request to save Ocorrenciapendencias : {}", ocorrenciapendenciasDTO);
        if (ocorrenciapendenciasDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new ocorrenciapendencias cannot already have an ID")).body(null);
        }
        Ocorrenciapendencias ocorrenciapendencias = ocorrenciapendenciasMapper.toEntity(ocorrenciapendenciasDTO);
        ocorrenciapendencias = ocorrenciapendenciasRepository.save(ocorrenciapendencias);
        OcorrenciapendenciasDTO result = ocorrenciapendenciasMapper.toDto(ocorrenciapendencias);
        return ResponseEntity.created(new URI("/api/ocorrenciapendencias/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ocorrenciapendencias : Updates an existing ocorrenciapendencias.
     *
     * @param ocorrenciapendenciasDTO the ocorrenciapendenciasDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ocorrenciapendenciasDTO,
     * or with status 400 (Bad Request) if the ocorrenciapendenciasDTO is not valid,
     * or with status 500 (Internal Server Error) if the ocorrenciapendenciasDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ocorrenciapendencias")
    @Timed
    public ResponseEntity<OcorrenciapendenciasDTO> updateOcorrenciapendencias(@RequestBody OcorrenciapendenciasDTO ocorrenciapendenciasDTO) throws URISyntaxException {
        log.debug("REST request to update Ocorrenciapendencias : {}", ocorrenciapendenciasDTO);
        if (ocorrenciapendenciasDTO.getId() == null) {
            return createOcorrenciapendencias(ocorrenciapendenciasDTO);
        }
        Ocorrenciapendencias ocorrenciapendencias = ocorrenciapendenciasMapper.toEntity(ocorrenciapendenciasDTO);
        ocorrenciapendencias = ocorrenciapendenciasRepository.save(ocorrenciapendencias);
        OcorrenciapendenciasDTO result = ocorrenciapendenciasMapper.toDto(ocorrenciapendencias);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ocorrenciapendenciasDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ocorrenciapendencias : get all the ocorrenciapendencias.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of ocorrenciapendencias in body
     */
    @GetMapping("/ocorrenciapendencias")
    @Timed
    public List<OcorrenciapendenciasDTO> getAllOcorrenciapendencias() {
        log.debug("REST request to get all Ocorrenciapendencias");
        List<Ocorrenciapendencias> ocorrenciapendencias = ocorrenciapendenciasRepository.findAll();
        return ocorrenciapendenciasMapper.toDto(ocorrenciapendencias);
    }

    /**
     * GET  /ocorrenciapendencias/:id : get the "id" ocorrenciapendencias.
     *
     * @param id the id of the ocorrenciapendenciasDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ocorrenciapendenciasDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ocorrenciapendencias/{id}")
    @Timed
    public ResponseEntity<OcorrenciapendenciasDTO> getOcorrenciapendencias(@PathVariable Long id) {
        log.debug("REST request to get Ocorrenciapendencias : {}", id);
        Ocorrenciapendencias ocorrenciapendencias = ocorrenciapendenciasRepository.findOne(id);
        OcorrenciapendenciasDTO ocorrenciapendenciasDTO = ocorrenciapendenciasMapper.toDto(ocorrenciapendencias);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(ocorrenciapendenciasDTO));
    }

    /**
     * DELETE  /ocorrenciapendencias/:id : delete the "id" ocorrenciapendencias.
     *
     * @param id the id of the ocorrenciapendenciasDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ocorrenciapendencias/{id}")
    @Timed
    public ResponseEntity<Void> deleteOcorrenciapendencias(@PathVariable Long id) {
        log.debug("REST request to delete Ocorrenciapendencias : {}", id);
        ocorrenciapendenciasRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
