package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Atividadelicenca;

import br.com.homemade.repository.AtividadelicencaRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.AtividadelicencaDTO;
import br.com.homemade.service.mapper.AtividadelicencaMapper;
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
 * REST controller for managing Atividadelicenca.
 */
@RestController
@RequestMapping("/api")
public class AtividadelicencaResource {

    private final Logger log = LoggerFactory.getLogger(AtividadelicencaResource.class);

    private static final String ENTITY_NAME = "atividadelicenca";
        
    private final AtividadelicencaRepository atividadelicencaRepository;

    private final AtividadelicencaMapper atividadelicencaMapper;

    public AtividadelicencaResource(AtividadelicencaRepository atividadelicencaRepository, AtividadelicencaMapper atividadelicencaMapper) {
        this.atividadelicencaRepository = atividadelicencaRepository;
        this.atividadelicencaMapper = atividadelicencaMapper;
    }

    /**
     * POST  /atividadelicencas : Create a new atividadelicenca.
     *
     * @param atividadelicencaDTO the atividadelicencaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new atividadelicencaDTO, or with status 400 (Bad Request) if the atividadelicenca has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/atividadelicencas")
    @Timed
    public ResponseEntity<AtividadelicencaDTO> createAtividadelicenca(@RequestBody AtividadelicencaDTO atividadelicencaDTO) throws URISyntaxException {
        log.debug("REST request to save Atividadelicenca : {}", atividadelicencaDTO);
        if (atividadelicencaDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new atividadelicenca cannot already have an ID")).body(null);
        }
        Atividadelicenca atividadelicenca = atividadelicencaMapper.toEntity(atividadelicencaDTO);
        atividadelicenca = atividadelicencaRepository.save(atividadelicenca);
        AtividadelicencaDTO result = atividadelicencaMapper.toDto(atividadelicenca);
        return ResponseEntity.created(new URI("/api/atividadelicencas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /atividadelicencas : Updates an existing atividadelicenca.
     *
     * @param atividadelicencaDTO the atividadelicencaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated atividadelicencaDTO,
     * or with status 400 (Bad Request) if the atividadelicencaDTO is not valid,
     * or with status 500 (Internal Server Error) if the atividadelicencaDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/atividadelicencas")
    @Timed
    public ResponseEntity<AtividadelicencaDTO> updateAtividadelicenca(@RequestBody AtividadelicencaDTO atividadelicencaDTO) throws URISyntaxException {
        log.debug("REST request to update Atividadelicenca : {}", atividadelicencaDTO);
        if (atividadelicencaDTO.getId() == null) {
            return createAtividadelicenca(atividadelicencaDTO);
        }
        Atividadelicenca atividadelicenca = atividadelicencaMapper.toEntity(atividadelicencaDTO);
        atividadelicenca = atividadelicencaRepository.save(atividadelicenca);
        AtividadelicencaDTO result = atividadelicencaMapper.toDto(atividadelicenca);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, atividadelicencaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /atividadelicencas : get all the atividadelicencas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of atividadelicencas in body
     */
    @GetMapping("/atividadelicencas")
    @Timed
    public List<AtividadelicencaDTO> getAllAtividadelicencas() {
        log.debug("REST request to get all Atividadelicencas");
        List<Atividadelicenca> atividadelicencas = atividadelicencaRepository.findAll();
        return atividadelicencaMapper.toDto(atividadelicencas);
    }

    /**
     * GET  /atividadelicencas/:id : get the "id" atividadelicenca.
     *
     * @param id the id of the atividadelicencaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the atividadelicencaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/atividadelicencas/{id}")
    @Timed
    public ResponseEntity<AtividadelicencaDTO> getAtividadelicenca(@PathVariable Long id) {
        log.debug("REST request to get Atividadelicenca : {}", id);
        Atividadelicenca atividadelicenca = atividadelicencaRepository.findOne(id);
        AtividadelicencaDTO atividadelicencaDTO = atividadelicencaMapper.toDto(atividadelicenca);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(atividadelicencaDTO));
    }

    /**
     * DELETE  /atividadelicencas/:id : delete the "id" atividadelicenca.
     *
     * @param id the id of the atividadelicencaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/atividadelicencas/{id}")
    @Timed
    public ResponseEntity<Void> deleteAtividadelicenca(@PathVariable Long id) {
        log.debug("REST request to delete Atividadelicenca : {}", id);
        atividadelicencaRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
