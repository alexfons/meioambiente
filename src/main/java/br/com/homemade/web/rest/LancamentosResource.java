package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Lancamentos;

import br.com.homemade.repository.LancamentosRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.LancamentosDTO;
import br.com.homemade.service.mapper.LancamentosMapper;
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
 * REST controller for managing Lancamentos.
 */
@RestController
@RequestMapping("/api")
public class LancamentosResource {

    private final Logger log = LoggerFactory.getLogger(LancamentosResource.class);

    private static final String ENTITY_NAME = "lancamentos";
        
    private final LancamentosRepository lancamentosRepository;

    private final LancamentosMapper lancamentosMapper;

    public LancamentosResource(LancamentosRepository lancamentosRepository, LancamentosMapper lancamentosMapper) {
        this.lancamentosRepository = lancamentosRepository;
        this.lancamentosMapper = lancamentosMapper;
    }

    /**
     * POST  /lancamentos : Create a new lancamentos.
     *
     * @param lancamentosDTO the lancamentosDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new lancamentosDTO, or with status 400 (Bad Request) if the lancamentos has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/lancamentos")
    @Timed
    public ResponseEntity<LancamentosDTO> createLancamentos(@RequestBody LancamentosDTO lancamentosDTO) throws URISyntaxException {
        log.debug("REST request to save Lancamentos : {}", lancamentosDTO);
        if (lancamentosDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new lancamentos cannot already have an ID")).body(null);
        }
        Lancamentos lancamentos = lancamentosMapper.toEntity(lancamentosDTO);
        lancamentos = lancamentosRepository.save(lancamentos);
        LancamentosDTO result = lancamentosMapper.toDto(lancamentos);
        return ResponseEntity.created(new URI("/api/lancamentos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /lancamentos : Updates an existing lancamentos.
     *
     * @param lancamentosDTO the lancamentosDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated lancamentosDTO,
     * or with status 400 (Bad Request) if the lancamentosDTO is not valid,
     * or with status 500 (Internal Server Error) if the lancamentosDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/lancamentos")
    @Timed
    public ResponseEntity<LancamentosDTO> updateLancamentos(@RequestBody LancamentosDTO lancamentosDTO) throws URISyntaxException {
        log.debug("REST request to update Lancamentos : {}", lancamentosDTO);
        if (lancamentosDTO.getId() == null) {
            return createLancamentos(lancamentosDTO);
        }
        Lancamentos lancamentos = lancamentosMapper.toEntity(lancamentosDTO);
        lancamentos = lancamentosRepository.save(lancamentos);
        LancamentosDTO result = lancamentosMapper.toDto(lancamentos);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, lancamentosDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /lancamentos : get all the lancamentos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of lancamentos in body
     */
    @GetMapping("/lancamentos")
    @Timed
    public List<LancamentosDTO> getAllLancamentos() {
        log.debug("REST request to get all Lancamentos");
        List<Lancamentos> lancamentos = lancamentosRepository.findAll();
        return lancamentosMapper.toDto(lancamentos);
    }

    /**
     * GET  /lancamentos/:id : get the "id" lancamentos.
     *
     * @param id the id of the lancamentosDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the lancamentosDTO, or with status 404 (Not Found)
     */
    @GetMapping("/lancamentos/{id}")
    @Timed
    public ResponseEntity<LancamentosDTO> getLancamentos(@PathVariable Long id) {
        log.debug("REST request to get Lancamentos : {}", id);
        Lancamentos lancamentos = lancamentosRepository.findOne(id);
        LancamentosDTO lancamentosDTO = lancamentosMapper.toDto(lancamentos);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(lancamentosDTO));
    }

    /**
     * DELETE  /lancamentos/:id : delete the "id" lancamentos.
     *
     * @param id the id of the lancamentosDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/lancamentos/{id}")
    @Timed
    public ResponseEntity<Void> deleteLancamentos(@PathVariable Long id) {
        log.debug("REST request to delete Lancamentos : {}", id);
        lancamentosRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
