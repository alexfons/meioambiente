package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Condicionante;

import br.com.homemade.repository.CondicionanteRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.CondicionanteDTO;
import br.com.homemade.service.mapper.CondicionanteMapper;
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
 * REST controller for managing Condicionante.
 */
@RestController
@RequestMapping("/api")
public class CondicionanteResource {

    private final Logger log = LoggerFactory.getLogger(CondicionanteResource.class);

    private static final String ENTITY_NAME = "condicionante";
        
    private final CondicionanteRepository condicionanteRepository;

    private final CondicionanteMapper condicionanteMapper;

    public CondicionanteResource(CondicionanteRepository condicionanteRepository, CondicionanteMapper condicionanteMapper) {
        this.condicionanteRepository = condicionanteRepository;
        this.condicionanteMapper = condicionanteMapper;
    }

    /**
     * POST  /condicionantes : Create a new condicionante.
     *
     * @param condicionanteDTO the condicionanteDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new condicionanteDTO, or with status 400 (Bad Request) if the condicionante has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/condicionantes")
    @Timed
    public ResponseEntity<CondicionanteDTO> createCondicionante(@RequestBody CondicionanteDTO condicionanteDTO) throws URISyntaxException {
        log.debug("REST request to save Condicionante : {}", condicionanteDTO);
        if (condicionanteDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new condicionante cannot already have an ID")).body(null);
        }
        Condicionante condicionante = condicionanteMapper.toEntity(condicionanteDTO);
        condicionante = condicionanteRepository.save(condicionante);
        CondicionanteDTO result = condicionanteMapper.toDto(condicionante);
        return ResponseEntity.created(new URI("/api/condicionantes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /condicionantes : Updates an existing condicionante.
     *
     * @param condicionanteDTO the condicionanteDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated condicionanteDTO,
     * or with status 400 (Bad Request) if the condicionanteDTO is not valid,
     * or with status 500 (Internal Server Error) if the condicionanteDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/condicionantes")
    @Timed
    public ResponseEntity<CondicionanteDTO> updateCondicionante(@RequestBody CondicionanteDTO condicionanteDTO) throws URISyntaxException {
        log.debug("REST request to update Condicionante : {}", condicionanteDTO);
        if (condicionanteDTO.getId() == null) {
            return createCondicionante(condicionanteDTO);
        }
        Condicionante condicionante = condicionanteMapper.toEntity(condicionanteDTO);
        condicionante = condicionanteRepository.save(condicionante);
        CondicionanteDTO result = condicionanteMapper.toDto(condicionante);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, condicionanteDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /condicionantes : get all the condicionantes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of condicionantes in body
     */
    @GetMapping("/condicionantes")
    @Timed
    public List<CondicionanteDTO> getAllCondicionantes() {
        log.debug("REST request to get all Condicionantes");
        List<Condicionante> condicionantes = condicionanteRepository.findAll();
        return condicionanteMapper.toDto(condicionantes);
    }

    /**
     * GET  /condicionantes/:id : get the "id" condicionante.
     *
     * @param id the id of the condicionanteDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the condicionanteDTO, or with status 404 (Not Found)
     */
    @GetMapping("/condicionantes/{id}")
    @Timed
    public ResponseEntity<CondicionanteDTO> getCondicionante(@PathVariable Long id) {
        log.debug("REST request to get Condicionante : {}", id);
        Condicionante condicionante = condicionanteRepository.findOne(id);
        CondicionanteDTO condicionanteDTO = condicionanteMapper.toDto(condicionante);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(condicionanteDTO));
    }

    /**
     * DELETE  /condicionantes/:id : delete the "id" condicionante.
     *
     * @param id the id of the condicionanteDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/condicionantes/{id}")
    @Timed
    public ResponseEntity<Void> deleteCondicionante(@PathVariable Long id) {
        log.debug("REST request to delete Condicionante : {}", id);
        condicionanteRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
