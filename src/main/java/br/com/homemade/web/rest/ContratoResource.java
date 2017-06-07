package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Contrato;

import br.com.homemade.repository.ContratoRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.ContratoDTO;
import br.com.homemade.service.mapper.ContratoMapper;
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
 * REST controller for managing Contrato.
 */
@RestController
@RequestMapping("/api")
public class ContratoResource {

    private final Logger log = LoggerFactory.getLogger(ContratoResource.class);

    private static final String ENTITY_NAME = "contrato";
        
    private final ContratoRepository contratoRepository;

    private final ContratoMapper contratoMapper;

    public ContratoResource(ContratoRepository contratoRepository, ContratoMapper contratoMapper) {
        this.contratoRepository = contratoRepository;
        this.contratoMapper = contratoMapper;
    }

    /**
     * POST  /contratoes : Create a new contrato.
     *
     * @param contratoDTO the contratoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new contratoDTO, or with status 400 (Bad Request) if the contrato has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/contratoes")
    @Timed
    public ResponseEntity<ContratoDTO> createContrato(@RequestBody ContratoDTO contratoDTO) throws URISyntaxException {
        log.debug("REST request to save Contrato : {}", contratoDTO);
        if (contratoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new contrato cannot already have an ID")).body(null);
        }
        Contrato contrato = contratoMapper.toEntity(contratoDTO);
        contrato = contratoRepository.save(contrato);
        ContratoDTO result = contratoMapper.toDto(contrato);
        return ResponseEntity.created(new URI("/api/contratoes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /contratoes : Updates an existing contrato.
     *
     * @param contratoDTO the contratoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated contratoDTO,
     * or with status 400 (Bad Request) if the contratoDTO is not valid,
     * or with status 500 (Internal Server Error) if the contratoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/contratoes")
    @Timed
    public ResponseEntity<ContratoDTO> updateContrato(@RequestBody ContratoDTO contratoDTO) throws URISyntaxException {
        log.debug("REST request to update Contrato : {}", contratoDTO);
        if (contratoDTO.getId() == null) {
            return createContrato(contratoDTO);
        }
        Contrato contrato = contratoMapper.toEntity(contratoDTO);
        contrato = contratoRepository.save(contrato);
        ContratoDTO result = contratoMapper.toDto(contrato);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, contratoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /contratoes : get all the contratoes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of contratoes in body
     */
    @GetMapping("/contratoes")
    @Timed
    public List<ContratoDTO> getAllContratoes() {
        log.debug("REST request to get all Contratoes");
        List<Contrato> contratoes = contratoRepository.findAllWithEagerRelationships();
        return contratoMapper.toDto(contratoes);
    }

    /**
     * GET  /contratoes/:id : get the "id" contrato.
     *
     * @param id the id of the contratoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the contratoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/contratoes/{id}")
    @Timed
    public ResponseEntity<ContratoDTO> getContrato(@PathVariable Long id) {
        log.debug("REST request to get Contrato : {}", id);
        Contrato contrato = contratoRepository.findOneWithEagerRelationships(id);
        ContratoDTO contratoDTO = contratoMapper.toDto(contrato);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(contratoDTO));
    }

    /**
     * DELETE  /contratoes/:id : delete the "id" contrato.
     *
     * @param id the id of the contratoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/contratoes/{id}")
    @Timed
    public ResponseEntity<Void> deleteContrato(@PathVariable Long id) {
        log.debug("REST request to delete Contrato : {}", id);
        contratoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
