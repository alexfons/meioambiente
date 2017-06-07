package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Contratoagente;

import br.com.homemade.repository.ContratoagenteRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.ContratoagenteDTO;
import br.com.homemade.service.mapper.ContratoagenteMapper;
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
 * REST controller for managing Contratoagente.
 */
@RestController
@RequestMapping("/api")
public class ContratoagenteResource {

    private final Logger log = LoggerFactory.getLogger(ContratoagenteResource.class);

    private static final String ENTITY_NAME = "contratoagente";
        
    private final ContratoagenteRepository contratoagenteRepository;

    private final ContratoagenteMapper contratoagenteMapper;

    public ContratoagenteResource(ContratoagenteRepository contratoagenteRepository, ContratoagenteMapper contratoagenteMapper) {
        this.contratoagenteRepository = contratoagenteRepository;
        this.contratoagenteMapper = contratoagenteMapper;
    }

    /**
     * POST  /contratoagentes : Create a new contratoagente.
     *
     * @param contratoagenteDTO the contratoagenteDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new contratoagenteDTO, or with status 400 (Bad Request) if the contratoagente has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/contratoagentes")
    @Timed
    public ResponseEntity<ContratoagenteDTO> createContratoagente(@RequestBody ContratoagenteDTO contratoagenteDTO) throws URISyntaxException {
        log.debug("REST request to save Contratoagente : {}", contratoagenteDTO);
        if (contratoagenteDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new contratoagente cannot already have an ID")).body(null);
        }
        Contratoagente contratoagente = contratoagenteMapper.toEntity(contratoagenteDTO);
        contratoagente = contratoagenteRepository.save(contratoagente);
        ContratoagenteDTO result = contratoagenteMapper.toDto(contratoagente);
        return ResponseEntity.created(new URI("/api/contratoagentes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /contratoagentes : Updates an existing contratoagente.
     *
     * @param contratoagenteDTO the contratoagenteDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated contratoagenteDTO,
     * or with status 400 (Bad Request) if the contratoagenteDTO is not valid,
     * or with status 500 (Internal Server Error) if the contratoagenteDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/contratoagentes")
    @Timed
    public ResponseEntity<ContratoagenteDTO> updateContratoagente(@RequestBody ContratoagenteDTO contratoagenteDTO) throws URISyntaxException {
        log.debug("REST request to update Contratoagente : {}", contratoagenteDTO);
        if (contratoagenteDTO.getId() == null) {
            return createContratoagente(contratoagenteDTO);
        }
        Contratoagente contratoagente = contratoagenteMapper.toEntity(contratoagenteDTO);
        contratoagente = contratoagenteRepository.save(contratoagente);
        ContratoagenteDTO result = contratoagenteMapper.toDto(contratoagente);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, contratoagenteDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /contratoagentes : get all the contratoagentes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of contratoagentes in body
     */
    @GetMapping("/contratoagentes")
    @Timed
    public List<ContratoagenteDTO> getAllContratoagentes() {
        log.debug("REST request to get all Contratoagentes");
        List<Contratoagente> contratoagentes = contratoagenteRepository.findAll();
        return contratoagenteMapper.toDto(contratoagentes);
    }

    /**
     * GET  /contratoagentes/:id : get the "id" contratoagente.
     *
     * @param id the id of the contratoagenteDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the contratoagenteDTO, or with status 404 (Not Found)
     */
    @GetMapping("/contratoagentes/{id}")
    @Timed
    public ResponseEntity<ContratoagenteDTO> getContratoagente(@PathVariable Long id) {
        log.debug("REST request to get Contratoagente : {}", id);
        Contratoagente contratoagente = contratoagenteRepository.findOne(id);
        ContratoagenteDTO contratoagenteDTO = contratoagenteMapper.toDto(contratoagente);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(contratoagenteDTO));
    }

    /**
     * DELETE  /contratoagentes/:id : delete the "id" contratoagente.
     *
     * @param id the id of the contratoagenteDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/contratoagentes/{id}")
    @Timed
    public ResponseEntity<Void> deleteContratoagente(@PathVariable Long id) {
        log.debug("REST request to delete Contratoagente : {}", id);
        contratoagenteRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
