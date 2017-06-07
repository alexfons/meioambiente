package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Valoresdesembolso;

import br.com.homemade.repository.ValoresdesembolsoRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.ValoresdesembolsoDTO;
import br.com.homemade.service.mapper.ValoresdesembolsoMapper;
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
 * REST controller for managing Valoresdesembolso.
 */
@RestController
@RequestMapping("/api")
public class ValoresdesembolsoResource {

    private final Logger log = LoggerFactory.getLogger(ValoresdesembolsoResource.class);

    private static final String ENTITY_NAME = "valoresdesembolso";
        
    private final ValoresdesembolsoRepository valoresdesembolsoRepository;

    private final ValoresdesembolsoMapper valoresdesembolsoMapper;

    public ValoresdesembolsoResource(ValoresdesembolsoRepository valoresdesembolsoRepository, ValoresdesembolsoMapper valoresdesembolsoMapper) {
        this.valoresdesembolsoRepository = valoresdesembolsoRepository;
        this.valoresdesembolsoMapper = valoresdesembolsoMapper;
    }

    /**
     * POST  /valoresdesembolsos : Create a new valoresdesembolso.
     *
     * @param valoresdesembolsoDTO the valoresdesembolsoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new valoresdesembolsoDTO, or with status 400 (Bad Request) if the valoresdesembolso has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/valoresdesembolsos")
    @Timed
    public ResponseEntity<ValoresdesembolsoDTO> createValoresdesembolso(@RequestBody ValoresdesembolsoDTO valoresdesembolsoDTO) throws URISyntaxException {
        log.debug("REST request to save Valoresdesembolso : {}", valoresdesembolsoDTO);
        if (valoresdesembolsoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new valoresdesembolso cannot already have an ID")).body(null);
        }
        Valoresdesembolso valoresdesembolso = valoresdesembolsoMapper.toEntity(valoresdesembolsoDTO);
        valoresdesembolso = valoresdesembolsoRepository.save(valoresdesembolso);
        ValoresdesembolsoDTO result = valoresdesembolsoMapper.toDto(valoresdesembolso);
        return ResponseEntity.created(new URI("/api/valoresdesembolsos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /valoresdesembolsos : Updates an existing valoresdesembolso.
     *
     * @param valoresdesembolsoDTO the valoresdesembolsoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated valoresdesembolsoDTO,
     * or with status 400 (Bad Request) if the valoresdesembolsoDTO is not valid,
     * or with status 500 (Internal Server Error) if the valoresdesembolsoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/valoresdesembolsos")
    @Timed
    public ResponseEntity<ValoresdesembolsoDTO> updateValoresdesembolso(@RequestBody ValoresdesembolsoDTO valoresdesembolsoDTO) throws URISyntaxException {
        log.debug("REST request to update Valoresdesembolso : {}", valoresdesembolsoDTO);
        if (valoresdesembolsoDTO.getId() == null) {
            return createValoresdesembolso(valoresdesembolsoDTO);
        }
        Valoresdesembolso valoresdesembolso = valoresdesembolsoMapper.toEntity(valoresdesembolsoDTO);
        valoresdesembolso = valoresdesembolsoRepository.save(valoresdesembolso);
        ValoresdesembolsoDTO result = valoresdesembolsoMapper.toDto(valoresdesembolso);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, valoresdesembolsoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /valoresdesembolsos : get all the valoresdesembolsos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of valoresdesembolsos in body
     */
    @GetMapping("/valoresdesembolsos")
    @Timed
    public List<ValoresdesembolsoDTO> getAllValoresdesembolsos() {
        log.debug("REST request to get all Valoresdesembolsos");
        List<Valoresdesembolso> valoresdesembolsos = valoresdesembolsoRepository.findAll();
        return valoresdesembolsoMapper.toDto(valoresdesembolsos);
    }

    /**
     * GET  /valoresdesembolsos/:id : get the "id" valoresdesembolso.
     *
     * @param id the id of the valoresdesembolsoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the valoresdesembolsoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/valoresdesembolsos/{id}")
    @Timed
    public ResponseEntity<ValoresdesembolsoDTO> getValoresdesembolso(@PathVariable Long id) {
        log.debug("REST request to get Valoresdesembolso : {}", id);
        Valoresdesembolso valoresdesembolso = valoresdesembolsoRepository.findOne(id);
        ValoresdesembolsoDTO valoresdesembolsoDTO = valoresdesembolsoMapper.toDto(valoresdesembolso);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(valoresdesembolsoDTO));
    }

    /**
     * DELETE  /valoresdesembolsos/:id : delete the "id" valoresdesembolso.
     *
     * @param id the id of the valoresdesembolsoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/valoresdesembolsos/{id}")
    @Timed
    public ResponseEntity<Void> deleteValoresdesembolso(@PathVariable Long id) {
        log.debug("REST request to delete Valoresdesembolso : {}", id);
        valoresdesembolsoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
