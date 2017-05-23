package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Trecho;

import br.com.homemade.repository.TrechoRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.TrechoDTO;
import br.com.homemade.service.mapper.TrechoMapper;
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
 * REST controller for managing Trecho.
 */
@RestController
@RequestMapping("/api")
public class TrechoResource {

    private final Logger log = LoggerFactory.getLogger(TrechoResource.class);

    private static final String ENTITY_NAME = "trecho";
        
    private final TrechoRepository trechoRepository;

    private final TrechoMapper trechoMapper;

    public TrechoResource(TrechoRepository trechoRepository, TrechoMapper trechoMapper) {
        this.trechoRepository = trechoRepository;
        this.trechoMapper = trechoMapper;
    }

    /**
     * POST  /trechos : Create a new trecho.
     *
     * @param trechoDTO the trechoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new trechoDTO, or with status 400 (Bad Request) if the trecho has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/trechos")
    @Timed
    public ResponseEntity<TrechoDTO> createTrecho(@RequestBody TrechoDTO trechoDTO) throws URISyntaxException {
        log.debug("REST request to save Trecho : {}", trechoDTO);
        if (trechoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new trecho cannot already have an ID")).body(null);
        }
        Trecho trecho = trechoMapper.toEntity(trechoDTO);
        trecho = trechoRepository.save(trecho);
        TrechoDTO result = trechoMapper.toDto(trecho);
        return ResponseEntity.created(new URI("/api/trechos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /trechos : Updates an existing trecho.
     *
     * @param trechoDTO the trechoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated trechoDTO,
     * or with status 400 (Bad Request) if the trechoDTO is not valid,
     * or with status 500 (Internal Server Error) if the trechoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/trechos")
    @Timed
    public ResponseEntity<TrechoDTO> updateTrecho(@RequestBody TrechoDTO trechoDTO) throws URISyntaxException {
        log.debug("REST request to update Trecho : {}", trechoDTO);
        if (trechoDTO.getId() == null) {
            return createTrecho(trechoDTO);
        }
        Trecho trecho = trechoMapper.toEntity(trechoDTO);
        trecho = trechoRepository.save(trecho);
        TrechoDTO result = trechoMapper.toDto(trecho);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, trechoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /trechos : get all the trechos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of trechos in body
     */
    @GetMapping("/trechos")
    @Timed
    public List<TrechoDTO> getAllTrechos() {
        log.debug("REST request to get all Trechos");
        List<Trecho> trechos = trechoRepository.findAll();
        return trechoMapper.toDto(trechos);
    }

    /**
     * GET  /trechos/:id : get the "id" trecho.
     *
     * @param id the id of the trechoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the trechoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/trechos/{id}")
    @Timed
    public ResponseEntity<TrechoDTO> getTrecho(@PathVariable Long id) {
        log.debug("REST request to get Trecho : {}", id);
        Trecho trecho = trechoRepository.findOne(id);
        TrechoDTO trechoDTO = trechoMapper.toDto(trecho);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(trechoDTO));
    }

    /**
     * DELETE  /trechos/:id : delete the "id" trecho.
     *
     * @param id the id of the trechoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/trechos/{id}")
    @Timed
    public ResponseEntity<Void> deleteTrecho(@PathVariable Long id) {
        log.debug("REST request to delete Trecho : {}", id);
        trechoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
