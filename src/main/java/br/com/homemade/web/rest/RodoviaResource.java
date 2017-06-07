package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Rodovia;

import br.com.homemade.repository.RodoviaRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.RodoviaDTO;
import br.com.homemade.service.mapper.RodoviaMapper;
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
 * REST controller for managing Rodovia.
 */
@RestController
@RequestMapping("/api")
public class RodoviaResource {

    private final Logger log = LoggerFactory.getLogger(RodoviaResource.class);

    private static final String ENTITY_NAME = "rodovia";
        
    private final RodoviaRepository rodoviaRepository;

    private final RodoviaMapper rodoviaMapper;

    public RodoviaResource(RodoviaRepository rodoviaRepository, RodoviaMapper rodoviaMapper) {
        this.rodoviaRepository = rodoviaRepository;
        this.rodoviaMapper = rodoviaMapper;
    }

    /**
     * POST  /rodovias : Create a new rodovia.
     *
     * @param rodoviaDTO the rodoviaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new rodoviaDTO, or with status 400 (Bad Request) if the rodovia has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/rodovias")
    @Timed
    public ResponseEntity<RodoviaDTO> createRodovia(@RequestBody RodoviaDTO rodoviaDTO) throws URISyntaxException {
        log.debug("REST request to save Rodovia : {}", rodoviaDTO);
        if (rodoviaDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new rodovia cannot already have an ID")).body(null);
        }
        Rodovia rodovia = rodoviaMapper.toEntity(rodoviaDTO);
        rodovia = rodoviaRepository.save(rodovia);
        RodoviaDTO result = rodoviaMapper.toDto(rodovia);
        return ResponseEntity.created(new URI("/api/rodovias/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /rodovias : Updates an existing rodovia.
     *
     * @param rodoviaDTO the rodoviaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated rodoviaDTO,
     * or with status 400 (Bad Request) if the rodoviaDTO is not valid,
     * or with status 500 (Internal Server Error) if the rodoviaDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/rodovias")
    @Timed
    public ResponseEntity<RodoviaDTO> updateRodovia(@RequestBody RodoviaDTO rodoviaDTO) throws URISyntaxException {
        log.debug("REST request to update Rodovia : {}", rodoviaDTO);
        if (rodoviaDTO.getId() == null) {
            return createRodovia(rodoviaDTO);
        }
        Rodovia rodovia = rodoviaMapper.toEntity(rodoviaDTO);
        rodovia = rodoviaRepository.save(rodovia);
        RodoviaDTO result = rodoviaMapper.toDto(rodovia);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, rodoviaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /rodovias : get all the rodovias.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of rodovias in body
     */
    @GetMapping("/rodovias")
    @Timed
    public List<RodoviaDTO> getAllRodovias() {
        log.debug("REST request to get all Rodovias");
        List<Rodovia> rodovias = rodoviaRepository.findAll();
        return rodoviaMapper.toDto(rodovias);
    }

    /**
     * GET  /rodovias/:id : get the "id" rodovia.
     *
     * @param id the id of the rodoviaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the rodoviaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/rodovias/{id}")
    @Timed
    public ResponseEntity<RodoviaDTO> getRodovia(@PathVariable Long id) {
        log.debug("REST request to get Rodovia : {}", id);
        Rodovia rodovia = rodoviaRepository.findOne(id);
        RodoviaDTO rodoviaDTO = rodoviaMapper.toDto(rodovia);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(rodoviaDTO));
    }

    /**
     * DELETE  /rodovias/:id : delete the "id" rodovia.
     *
     * @param id the id of the rodoviaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/rodovias/{id}")
    @Timed
    public ResponseEntity<Void> deleteRodovia(@PathVariable Long id) {
        log.debug("REST request to delete Rodovia : {}", id);
        rodoviaRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
