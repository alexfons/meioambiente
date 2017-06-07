package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Obrafisicomensal;

import br.com.homemade.repository.ObrafisicomensalRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.ObrafisicomensalDTO;
import br.com.homemade.service.mapper.ObrafisicomensalMapper;
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
 * REST controller for managing Obrafisicomensal.
 */
@RestController
@RequestMapping("/api")
public class ObrafisicomensalResource {

    private final Logger log = LoggerFactory.getLogger(ObrafisicomensalResource.class);

    private static final String ENTITY_NAME = "obrafisicomensal";
        
    private final ObrafisicomensalRepository obrafisicomensalRepository;

    private final ObrafisicomensalMapper obrafisicomensalMapper;

    public ObrafisicomensalResource(ObrafisicomensalRepository obrafisicomensalRepository, ObrafisicomensalMapper obrafisicomensalMapper) {
        this.obrafisicomensalRepository = obrafisicomensalRepository;
        this.obrafisicomensalMapper = obrafisicomensalMapper;
    }

    /**
     * POST  /obrafisicomensals : Create a new obrafisicomensal.
     *
     * @param obrafisicomensalDTO the obrafisicomensalDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new obrafisicomensalDTO, or with status 400 (Bad Request) if the obrafisicomensal has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/obrafisicomensals")
    @Timed
    public ResponseEntity<ObrafisicomensalDTO> createObrafisicomensal(@RequestBody ObrafisicomensalDTO obrafisicomensalDTO) throws URISyntaxException {
        log.debug("REST request to save Obrafisicomensal : {}", obrafisicomensalDTO);
        if (obrafisicomensalDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new obrafisicomensal cannot already have an ID")).body(null);
        }
        Obrafisicomensal obrafisicomensal = obrafisicomensalMapper.toEntity(obrafisicomensalDTO);
        obrafisicomensal = obrafisicomensalRepository.save(obrafisicomensal);
        ObrafisicomensalDTO result = obrafisicomensalMapper.toDto(obrafisicomensal);
        return ResponseEntity.created(new URI("/api/obrafisicomensals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /obrafisicomensals : Updates an existing obrafisicomensal.
     *
     * @param obrafisicomensalDTO the obrafisicomensalDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated obrafisicomensalDTO,
     * or with status 400 (Bad Request) if the obrafisicomensalDTO is not valid,
     * or with status 500 (Internal Server Error) if the obrafisicomensalDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/obrafisicomensals")
    @Timed
    public ResponseEntity<ObrafisicomensalDTO> updateObrafisicomensal(@RequestBody ObrafisicomensalDTO obrafisicomensalDTO) throws URISyntaxException {
        log.debug("REST request to update Obrafisicomensal : {}", obrafisicomensalDTO);
        if (obrafisicomensalDTO.getId() == null) {
            return createObrafisicomensal(obrafisicomensalDTO);
        }
        Obrafisicomensal obrafisicomensal = obrafisicomensalMapper.toEntity(obrafisicomensalDTO);
        obrafisicomensal = obrafisicomensalRepository.save(obrafisicomensal);
        ObrafisicomensalDTO result = obrafisicomensalMapper.toDto(obrafisicomensal);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, obrafisicomensalDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /obrafisicomensals : get all the obrafisicomensals.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of obrafisicomensals in body
     */
    @GetMapping("/obrafisicomensals")
    @Timed
    public List<ObrafisicomensalDTO> getAllObrafisicomensals() {
        log.debug("REST request to get all Obrafisicomensals");
        List<Obrafisicomensal> obrafisicomensals = obrafisicomensalRepository.findAllWithEagerRelationships();
        return obrafisicomensalMapper.toDto(obrafisicomensals);
    }

    /**
     * GET  /obrafisicomensals/:id : get the "id" obrafisicomensal.
     *
     * @param id the id of the obrafisicomensalDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the obrafisicomensalDTO, or with status 404 (Not Found)
     */
    @GetMapping("/obrafisicomensals/{id}")
    @Timed
    public ResponseEntity<ObrafisicomensalDTO> getObrafisicomensal(@PathVariable Long id) {
        log.debug("REST request to get Obrafisicomensal : {}", id);
        Obrafisicomensal obrafisicomensal = obrafisicomensalRepository.findOneWithEagerRelationships(id);
        ObrafisicomensalDTO obrafisicomensalDTO = obrafisicomensalMapper.toDto(obrafisicomensal);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(obrafisicomensalDTO));
    }

    /**
     * DELETE  /obrafisicomensals/:id : delete the "id" obrafisicomensal.
     *
     * @param id the id of the obrafisicomensalDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/obrafisicomensals/{id}")
    @Timed
    public ResponseEntity<Void> deleteObrafisicomensal(@PathVariable Long id) {
        log.debug("REST request to delete Obrafisicomensal : {}", id);
        obrafisicomensalRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
