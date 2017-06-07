package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Ocorrenciainforme;

import br.com.homemade.repository.OcorrenciainformeRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.OcorrenciainformeDTO;
import br.com.homemade.service.mapper.OcorrenciainformeMapper;
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
 * REST controller for managing Ocorrenciainforme.
 */
@RestController
@RequestMapping("/api")
public class OcorrenciainformeResource {

    private final Logger log = LoggerFactory.getLogger(OcorrenciainformeResource.class);

    private static final String ENTITY_NAME = "ocorrenciainforme";
        
    private final OcorrenciainformeRepository ocorrenciainformeRepository;

    private final OcorrenciainformeMapper ocorrenciainformeMapper;

    public OcorrenciainformeResource(OcorrenciainformeRepository ocorrenciainformeRepository, OcorrenciainformeMapper ocorrenciainformeMapper) {
        this.ocorrenciainformeRepository = ocorrenciainformeRepository;
        this.ocorrenciainformeMapper = ocorrenciainformeMapper;
    }

    /**
     * POST  /ocorrenciainformes : Create a new ocorrenciainforme.
     *
     * @param ocorrenciainformeDTO the ocorrenciainformeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ocorrenciainformeDTO, or with status 400 (Bad Request) if the ocorrenciainforme has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ocorrenciainformes")
    @Timed
    public ResponseEntity<OcorrenciainformeDTO> createOcorrenciainforme(@RequestBody OcorrenciainformeDTO ocorrenciainformeDTO) throws URISyntaxException {
        log.debug("REST request to save Ocorrenciainforme : {}", ocorrenciainformeDTO);
        if (ocorrenciainformeDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new ocorrenciainforme cannot already have an ID")).body(null);
        }
        Ocorrenciainforme ocorrenciainforme = ocorrenciainformeMapper.toEntity(ocorrenciainformeDTO);
        ocorrenciainforme = ocorrenciainformeRepository.save(ocorrenciainforme);
        OcorrenciainformeDTO result = ocorrenciainformeMapper.toDto(ocorrenciainforme);
        return ResponseEntity.created(new URI("/api/ocorrenciainformes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ocorrenciainformes : Updates an existing ocorrenciainforme.
     *
     * @param ocorrenciainformeDTO the ocorrenciainformeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ocorrenciainformeDTO,
     * or with status 400 (Bad Request) if the ocorrenciainformeDTO is not valid,
     * or with status 500 (Internal Server Error) if the ocorrenciainformeDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ocorrenciainformes")
    @Timed
    public ResponseEntity<OcorrenciainformeDTO> updateOcorrenciainforme(@RequestBody OcorrenciainformeDTO ocorrenciainformeDTO) throws URISyntaxException {
        log.debug("REST request to update Ocorrenciainforme : {}", ocorrenciainformeDTO);
        if (ocorrenciainformeDTO.getId() == null) {
            return createOcorrenciainforme(ocorrenciainformeDTO);
        }
        Ocorrenciainforme ocorrenciainforme = ocorrenciainformeMapper.toEntity(ocorrenciainformeDTO);
        ocorrenciainforme = ocorrenciainformeRepository.save(ocorrenciainforme);
        OcorrenciainformeDTO result = ocorrenciainformeMapper.toDto(ocorrenciainforme);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ocorrenciainformeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ocorrenciainformes : get all the ocorrenciainformes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of ocorrenciainformes in body
     */
    @GetMapping("/ocorrenciainformes")
    @Timed
    public List<OcorrenciainformeDTO> getAllOcorrenciainformes() {
        log.debug("REST request to get all Ocorrenciainformes");
        List<Ocorrenciainforme> ocorrenciainformes = ocorrenciainformeRepository.findAll();
        return ocorrenciainformeMapper.toDto(ocorrenciainformes);
    }

    /**
     * GET  /ocorrenciainformes/:id : get the "id" ocorrenciainforme.
     *
     * @param id the id of the ocorrenciainformeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ocorrenciainformeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ocorrenciainformes/{id}")
    @Timed
    public ResponseEntity<OcorrenciainformeDTO> getOcorrenciainforme(@PathVariable Long id) {
        log.debug("REST request to get Ocorrenciainforme : {}", id);
        Ocorrenciainforme ocorrenciainforme = ocorrenciainformeRepository.findOne(id);
        OcorrenciainformeDTO ocorrenciainformeDTO = ocorrenciainformeMapper.toDto(ocorrenciainforme);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(ocorrenciainformeDTO));
    }

    /**
     * DELETE  /ocorrenciainformes/:id : delete the "id" ocorrenciainforme.
     *
     * @param id the id of the ocorrenciainformeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ocorrenciainformes/{id}")
    @Timed
    public ResponseEntity<Void> deleteOcorrenciainforme(@PathVariable Long id) {
        log.debug("REST request to delete Ocorrenciainforme : {}", id);
        ocorrenciainformeRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
