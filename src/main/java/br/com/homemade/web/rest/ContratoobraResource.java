package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Contratoobra;

import br.com.homemade.repository.ContratoobraRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.ContratoobraDTO;
import br.com.homemade.service.mapper.ContratoobraMapper;
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
 * REST controller for managing Contratoobra.
 */
@RestController
@RequestMapping("/api")
public class ContratoobraResource {

    private final Logger log = LoggerFactory.getLogger(ContratoobraResource.class);

    private static final String ENTITY_NAME = "contratoobra";
        
    private final ContratoobraRepository contratoobraRepository;

    private final ContratoobraMapper contratoobraMapper;

    public ContratoobraResource(ContratoobraRepository contratoobraRepository, ContratoobraMapper contratoobraMapper) {
        this.contratoobraRepository = contratoobraRepository;
        this.contratoobraMapper = contratoobraMapper;
    }

    /**
     * POST  /contratoobras : Create a new contratoobra.
     *
     * @param contratoobraDTO the contratoobraDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new contratoobraDTO, or with status 400 (Bad Request) if the contratoobra has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/contratoobras")
    @Timed
    public ResponseEntity<ContratoobraDTO> createContratoobra(@RequestBody ContratoobraDTO contratoobraDTO) throws URISyntaxException {
        log.debug("REST request to save Contratoobra : {}", contratoobraDTO);
        if (contratoobraDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new contratoobra cannot already have an ID")).body(null);
        }
        Contratoobra contratoobra = contratoobraMapper.toEntity(contratoobraDTO);
        contratoobra = contratoobraRepository.save(contratoobra);
        ContratoobraDTO result = contratoobraMapper.toDto(contratoobra);
        return ResponseEntity.created(new URI("/api/contratoobras/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /contratoobras : Updates an existing contratoobra.
     *
     * @param contratoobraDTO the contratoobraDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated contratoobraDTO,
     * or with status 400 (Bad Request) if the contratoobraDTO is not valid,
     * or with status 500 (Internal Server Error) if the contratoobraDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/contratoobras")
    @Timed
    public ResponseEntity<ContratoobraDTO> updateContratoobra(@RequestBody ContratoobraDTO contratoobraDTO) throws URISyntaxException {
        log.debug("REST request to update Contratoobra : {}", contratoobraDTO);
        if (contratoobraDTO.getId() == null) {
            return createContratoobra(contratoobraDTO);
        }
        Contratoobra contratoobra = contratoobraMapper.toEntity(contratoobraDTO);
        contratoobra = contratoobraRepository.save(contratoobra);
        ContratoobraDTO result = contratoobraMapper.toDto(contratoobra);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, contratoobraDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /contratoobras : get all the contratoobras.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of contratoobras in body
     */
    @GetMapping("/contratoobras")
    @Timed
    public List<ContratoobraDTO> getAllContratoobras() {
        log.debug("REST request to get all Contratoobras");
        List<Contratoobra> contratoobras = contratoobraRepository.findAll();
        return contratoobraMapper.toDto(contratoobras);
    }

    /**
     * GET  /contratoobras/:id : get the "id" contratoobra.
     *
     * @param id the id of the contratoobraDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the contratoobraDTO, or with status 404 (Not Found)
     */
    @GetMapping("/contratoobras/{id}")
    @Timed
    public ResponseEntity<ContratoobraDTO> getContratoobra(@PathVariable Long id) {
        log.debug("REST request to get Contratoobra : {}", id);
        Contratoobra contratoobra = contratoobraRepository.findOne(id);
        ContratoobraDTO contratoobraDTO = contratoobraMapper.toDto(contratoobra);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(contratoobraDTO));
    }

    /**
     * DELETE  /contratoobras/:id : delete the "id" contratoobra.
     *
     * @param id the id of the contratoobraDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/contratoobras/{id}")
    @Timed
    public ResponseEntity<Void> deleteContratoobra(@PathVariable Long id) {
        log.debug("REST request to delete Contratoobra : {}", id);
        contratoobraRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
