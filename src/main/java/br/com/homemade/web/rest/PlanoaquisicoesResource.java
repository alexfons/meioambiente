package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Planoaquisicoes;

import br.com.homemade.repository.PlanoaquisicoesRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.PlanoaquisicoesDTO;
import br.com.homemade.service.mapper.PlanoaquisicoesMapper;
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
 * REST controller for managing Planoaquisicoes.
 */
@RestController
@RequestMapping("/api")
public class PlanoaquisicoesResource {

    private final Logger log = LoggerFactory.getLogger(PlanoaquisicoesResource.class);

    private static final String ENTITY_NAME = "planoaquisicoes";
        
    private final PlanoaquisicoesRepository planoaquisicoesRepository;

    private final PlanoaquisicoesMapper planoaquisicoesMapper;

    public PlanoaquisicoesResource(PlanoaquisicoesRepository planoaquisicoesRepository, PlanoaquisicoesMapper planoaquisicoesMapper) {
        this.planoaquisicoesRepository = planoaquisicoesRepository;
        this.planoaquisicoesMapper = planoaquisicoesMapper;
    }

    /**
     * POST  /planoaquisicoes : Create a new planoaquisicoes.
     *
     * @param planoaquisicoesDTO the planoaquisicoesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new planoaquisicoesDTO, or with status 400 (Bad Request) if the planoaquisicoes has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/planoaquisicoes")
    @Timed
    public ResponseEntity<PlanoaquisicoesDTO> createPlanoaquisicoes(@RequestBody PlanoaquisicoesDTO planoaquisicoesDTO) throws URISyntaxException {
        log.debug("REST request to save Planoaquisicoes : {}", planoaquisicoesDTO);
        if (planoaquisicoesDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new planoaquisicoes cannot already have an ID")).body(null);
        }
        Planoaquisicoes planoaquisicoes = planoaquisicoesMapper.toEntity(planoaquisicoesDTO);
        planoaquisicoes = planoaquisicoesRepository.save(planoaquisicoes);
        PlanoaquisicoesDTO result = planoaquisicoesMapper.toDto(planoaquisicoes);
        return ResponseEntity.created(new URI("/api/planoaquisicoes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /planoaquisicoes : Updates an existing planoaquisicoes.
     *
     * @param planoaquisicoesDTO the planoaquisicoesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated planoaquisicoesDTO,
     * or with status 400 (Bad Request) if the planoaquisicoesDTO is not valid,
     * or with status 500 (Internal Server Error) if the planoaquisicoesDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/planoaquisicoes")
    @Timed
    public ResponseEntity<PlanoaquisicoesDTO> updatePlanoaquisicoes(@RequestBody PlanoaquisicoesDTO planoaquisicoesDTO) throws URISyntaxException {
        log.debug("REST request to update Planoaquisicoes : {}", planoaquisicoesDTO);
        if (planoaquisicoesDTO.getId() == null) {
            return createPlanoaquisicoes(planoaquisicoesDTO);
        }
        Planoaquisicoes planoaquisicoes = planoaquisicoesMapper.toEntity(planoaquisicoesDTO);
        planoaquisicoes = planoaquisicoesRepository.save(planoaquisicoes);
        PlanoaquisicoesDTO result = planoaquisicoesMapper.toDto(planoaquisicoes);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, planoaquisicoesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /planoaquisicoes : get all the planoaquisicoes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of planoaquisicoes in body
     */
    @GetMapping("/planoaquisicoes")
    @Timed
    public List<PlanoaquisicoesDTO> getAllPlanoaquisicoes() {
        log.debug("REST request to get all Planoaquisicoes");
        List<Planoaquisicoes> planoaquisicoes = planoaquisicoesRepository.findAll();
        return planoaquisicoesMapper.toDto(planoaquisicoes);
    }

    /**
     * GET  /planoaquisicoes/:id : get the "id" planoaquisicoes.
     *
     * @param id the id of the planoaquisicoesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the planoaquisicoesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/planoaquisicoes/{id}")
    @Timed
    public ResponseEntity<PlanoaquisicoesDTO> getPlanoaquisicoes(@PathVariable Long id) {
        log.debug("REST request to get Planoaquisicoes : {}", id);
        Planoaquisicoes planoaquisicoes = planoaquisicoesRepository.findOne(id);
        PlanoaquisicoesDTO planoaquisicoesDTO = planoaquisicoesMapper.toDto(planoaquisicoes);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(planoaquisicoesDTO));
    }

    /**
     * DELETE  /planoaquisicoes/:id : delete the "id" planoaquisicoes.
     *
     * @param id the id of the planoaquisicoesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/planoaquisicoes/{id}")
    @Timed
    public ResponseEntity<Void> deletePlanoaquisicoes(@PathVariable Long id) {
        log.debug("REST request to delete Planoaquisicoes : {}", id);
        planoaquisicoesRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
