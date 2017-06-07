package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Planocontas;

import br.com.homemade.repository.PlanocontasRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.PlanocontasDTO;
import br.com.homemade.service.mapper.PlanocontasMapper;
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
 * REST controller for managing Planocontas.
 */
@RestController
@RequestMapping("/api")
public class PlanocontasResource {

    private final Logger log = LoggerFactory.getLogger(PlanocontasResource.class);

    private static final String ENTITY_NAME = "planocontas";
        
    private final PlanocontasRepository planocontasRepository;

    private final PlanocontasMapper planocontasMapper;

    public PlanocontasResource(PlanocontasRepository planocontasRepository, PlanocontasMapper planocontasMapper) {
        this.planocontasRepository = planocontasRepository;
        this.planocontasMapper = planocontasMapper;
    }

    /**
     * POST  /planocontas : Create a new planocontas.
     *
     * @param planocontasDTO the planocontasDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new planocontasDTO, or with status 400 (Bad Request) if the planocontas has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/planocontas")
    @Timed
    public ResponseEntity<PlanocontasDTO> createPlanocontas(@RequestBody PlanocontasDTO planocontasDTO) throws URISyntaxException {
        log.debug("REST request to save Planocontas : {}", planocontasDTO);
        if (planocontasDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new planocontas cannot already have an ID")).body(null);
        }
        Planocontas planocontas = planocontasMapper.toEntity(planocontasDTO);
        planocontas = planocontasRepository.save(planocontas);
        PlanocontasDTO result = planocontasMapper.toDto(planocontas);
        return ResponseEntity.created(new URI("/api/planocontas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /planocontas : Updates an existing planocontas.
     *
     * @param planocontasDTO the planocontasDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated planocontasDTO,
     * or with status 400 (Bad Request) if the planocontasDTO is not valid,
     * or with status 500 (Internal Server Error) if the planocontasDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/planocontas")
    @Timed
    public ResponseEntity<PlanocontasDTO> updatePlanocontas(@RequestBody PlanocontasDTO planocontasDTO) throws URISyntaxException {
        log.debug("REST request to update Planocontas : {}", planocontasDTO);
        if (planocontasDTO.getId() == null) {
            return createPlanocontas(planocontasDTO);
        }
        Planocontas planocontas = planocontasMapper.toEntity(planocontasDTO);
        planocontas = planocontasRepository.save(planocontas);
        PlanocontasDTO result = planocontasMapper.toDto(planocontas);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, planocontasDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /planocontas : get all the planocontas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of planocontas in body
     */
    @GetMapping("/planocontas")
    @Timed
    public List<PlanocontasDTO> getAllPlanocontas() {
        log.debug("REST request to get all Planocontas");
        List<Planocontas> planocontas = planocontasRepository.findAll();
        return planocontasMapper.toDto(planocontas);
    }

    /**
     * GET  /planocontas/:id : get the "id" planocontas.
     *
     * @param id the id of the planocontasDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the planocontasDTO, or with status 404 (Not Found)
     */
    @GetMapping("/planocontas/{id}")
    @Timed
    public ResponseEntity<PlanocontasDTO> getPlanocontas(@PathVariable Long id) {
        log.debug("REST request to get Planocontas : {}", id);
        Planocontas planocontas = planocontasRepository.findOne(id);
        PlanocontasDTO planocontasDTO = planocontasMapper.toDto(planocontas);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(planocontasDTO));
    }

    /**
     * DELETE  /planocontas/:id : delete the "id" planocontas.
     *
     * @param id the id of the planocontasDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/planocontas/{id}")
    @Timed
    public ResponseEntity<Void> deletePlanocontas(@PathVariable Long id) {
        log.debug("REST request to delete Planocontas : {}", id);
        planocontasRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
