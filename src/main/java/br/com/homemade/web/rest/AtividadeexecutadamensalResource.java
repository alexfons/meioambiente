package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Atividadeexecutadamensal;

import br.com.homemade.repository.AtividadeexecutadamensalRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.AtividadeexecutadamensalDTO;
import br.com.homemade.service.mapper.AtividadeexecutadamensalMapper;
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
 * REST controller for managing Atividadeexecutadamensal.
 */
@RestController
@RequestMapping("/api")
public class AtividadeexecutadamensalResource {

    private final Logger log = LoggerFactory.getLogger(AtividadeexecutadamensalResource.class);

    private static final String ENTITY_NAME = "atividadeexecutadamensal";
        
    private final AtividadeexecutadamensalRepository atividadeexecutadamensalRepository;

    private final AtividadeexecutadamensalMapper atividadeexecutadamensalMapper;

    public AtividadeexecutadamensalResource(AtividadeexecutadamensalRepository atividadeexecutadamensalRepository, AtividadeexecutadamensalMapper atividadeexecutadamensalMapper) {
        this.atividadeexecutadamensalRepository = atividadeexecutadamensalRepository;
        this.atividadeexecutadamensalMapper = atividadeexecutadamensalMapper;
    }

    /**
     * POST  /atividadeexecutadamensals : Create a new atividadeexecutadamensal.
     *
     * @param atividadeexecutadamensalDTO the atividadeexecutadamensalDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new atividadeexecutadamensalDTO, or with status 400 (Bad Request) if the atividadeexecutadamensal has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/atividadeexecutadamensals")
    @Timed
    public ResponseEntity<AtividadeexecutadamensalDTO> createAtividadeexecutadamensal(@RequestBody AtividadeexecutadamensalDTO atividadeexecutadamensalDTO) throws URISyntaxException {
        log.debug("REST request to save Atividadeexecutadamensal : {}", atividadeexecutadamensalDTO);
        if (atividadeexecutadamensalDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new atividadeexecutadamensal cannot already have an ID")).body(null);
        }
        Atividadeexecutadamensal atividadeexecutadamensal = atividadeexecutadamensalMapper.toEntity(atividadeexecutadamensalDTO);
        atividadeexecutadamensal = atividadeexecutadamensalRepository.save(atividadeexecutadamensal);
        AtividadeexecutadamensalDTO result = atividadeexecutadamensalMapper.toDto(atividadeexecutadamensal);
        return ResponseEntity.created(new URI("/api/atividadeexecutadamensals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /atividadeexecutadamensals : Updates an existing atividadeexecutadamensal.
     *
     * @param atividadeexecutadamensalDTO the atividadeexecutadamensalDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated atividadeexecutadamensalDTO,
     * or with status 400 (Bad Request) if the atividadeexecutadamensalDTO is not valid,
     * or with status 500 (Internal Server Error) if the atividadeexecutadamensalDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/atividadeexecutadamensals")
    @Timed
    public ResponseEntity<AtividadeexecutadamensalDTO> updateAtividadeexecutadamensal(@RequestBody AtividadeexecutadamensalDTO atividadeexecutadamensalDTO) throws URISyntaxException {
        log.debug("REST request to update Atividadeexecutadamensal : {}", atividadeexecutadamensalDTO);
        if (atividadeexecutadamensalDTO.getId() == null) {
            return createAtividadeexecutadamensal(atividadeexecutadamensalDTO);
        }
        Atividadeexecutadamensal atividadeexecutadamensal = atividadeexecutadamensalMapper.toEntity(atividadeexecutadamensalDTO);
        atividadeexecutadamensal = atividadeexecutadamensalRepository.save(atividadeexecutadamensal);
        AtividadeexecutadamensalDTO result = atividadeexecutadamensalMapper.toDto(atividadeexecutadamensal);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, atividadeexecutadamensalDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /atividadeexecutadamensals : get all the atividadeexecutadamensals.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of atividadeexecutadamensals in body
     */
    @GetMapping("/atividadeexecutadamensals")
    @Timed
    public List<AtividadeexecutadamensalDTO> getAllAtividadeexecutadamensals() {
        log.debug("REST request to get all Atividadeexecutadamensals");
        List<Atividadeexecutadamensal> atividadeexecutadamensals = atividadeexecutadamensalRepository.findAll();
        return atividadeexecutadamensalMapper.toDto(atividadeexecutadamensals);
    }

    /**
     * GET  /atividadeexecutadamensals/:id : get the "id" atividadeexecutadamensal.
     *
     * @param id the id of the atividadeexecutadamensalDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the atividadeexecutadamensalDTO, or with status 404 (Not Found)
     */
    @GetMapping("/atividadeexecutadamensals/{id}")
    @Timed
    public ResponseEntity<AtividadeexecutadamensalDTO> getAtividadeexecutadamensal(@PathVariable Long id) {
        log.debug("REST request to get Atividadeexecutadamensal : {}", id);
        Atividadeexecutadamensal atividadeexecutadamensal = atividadeexecutadamensalRepository.findOne(id);
        AtividadeexecutadamensalDTO atividadeexecutadamensalDTO = atividadeexecutadamensalMapper.toDto(atividadeexecutadamensal);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(atividadeexecutadamensalDTO));
    }

    /**
     * DELETE  /atividadeexecutadamensals/:id : delete the "id" atividadeexecutadamensal.
     *
     * @param id the id of the atividadeexecutadamensalDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/atividadeexecutadamensals/{id}")
    @Timed
    public ResponseEntity<Void> deleteAtividadeexecutadamensal(@PathVariable Long id) {
        log.debug("REST request to delete Atividadeexecutadamensal : {}", id);
        atividadeexecutadamensalRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
