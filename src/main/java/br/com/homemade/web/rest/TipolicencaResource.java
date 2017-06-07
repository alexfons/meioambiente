package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Tipolicenca;

import br.com.homemade.repository.TipolicencaRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.TipolicencaDTO;
import br.com.homemade.service.mapper.TipolicencaMapper;
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
 * REST controller for managing Tipolicenca.
 */
@RestController
@RequestMapping("/api")
public class TipolicencaResource {

    private final Logger log = LoggerFactory.getLogger(TipolicencaResource.class);

    private static final String ENTITY_NAME = "tipolicenca";
        
    private final TipolicencaRepository tipolicencaRepository;

    private final TipolicencaMapper tipolicencaMapper;

    public TipolicencaResource(TipolicencaRepository tipolicencaRepository, TipolicencaMapper tipolicencaMapper) {
        this.tipolicencaRepository = tipolicencaRepository;
        this.tipolicencaMapper = tipolicencaMapper;
    }

    /**
     * POST  /tipolicencas : Create a new tipolicenca.
     *
     * @param tipolicencaDTO the tipolicencaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipolicencaDTO, or with status 400 (Bad Request) if the tipolicenca has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tipolicencas")
    @Timed
    public ResponseEntity<TipolicencaDTO> createTipolicenca(@RequestBody TipolicencaDTO tipolicencaDTO) throws URISyntaxException {
        log.debug("REST request to save Tipolicenca : {}", tipolicencaDTO);
        if (tipolicencaDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new tipolicenca cannot already have an ID")).body(null);
        }
        Tipolicenca tipolicenca = tipolicencaMapper.toEntity(tipolicencaDTO);
        tipolicenca = tipolicencaRepository.save(tipolicenca);
        TipolicencaDTO result = tipolicencaMapper.toDto(tipolicenca);
        return ResponseEntity.created(new URI("/api/tipolicencas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tipolicencas : Updates an existing tipolicenca.
     *
     * @param tipolicencaDTO the tipolicencaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipolicencaDTO,
     * or with status 400 (Bad Request) if the tipolicencaDTO is not valid,
     * or with status 500 (Internal Server Error) if the tipolicencaDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tipolicencas")
    @Timed
    public ResponseEntity<TipolicencaDTO> updateTipolicenca(@RequestBody TipolicencaDTO tipolicencaDTO) throws URISyntaxException {
        log.debug("REST request to update Tipolicenca : {}", tipolicencaDTO);
        if (tipolicencaDTO.getId() == null) {
            return createTipolicenca(tipolicencaDTO);
        }
        Tipolicenca tipolicenca = tipolicencaMapper.toEntity(tipolicencaDTO);
        tipolicenca = tipolicencaRepository.save(tipolicenca);
        TipolicencaDTO result = tipolicencaMapper.toDto(tipolicenca);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipolicencaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tipolicencas : get all the tipolicencas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tipolicencas in body
     */
    @GetMapping("/tipolicencas")
    @Timed
    public List<TipolicencaDTO> getAllTipolicencas() {
        log.debug("REST request to get all Tipolicencas");
        List<Tipolicenca> tipolicencas = tipolicencaRepository.findAll();
        return tipolicencaMapper.toDto(tipolicencas);
    }

    /**
     * GET  /tipolicencas/:id : get the "id" tipolicenca.
     *
     * @param id the id of the tipolicencaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipolicencaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tipolicencas/{id}")
    @Timed
    public ResponseEntity<TipolicencaDTO> getTipolicenca(@PathVariable Long id) {
        log.debug("REST request to get Tipolicenca : {}", id);
        Tipolicenca tipolicenca = tipolicencaRepository.findOne(id);
        TipolicencaDTO tipolicencaDTO = tipolicencaMapper.toDto(tipolicenca);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tipolicencaDTO));
    }

    /**
     * DELETE  /tipolicencas/:id : delete the "id" tipolicenca.
     *
     * @param id the id of the tipolicencaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tipolicencas/{id}")
    @Timed
    public ResponseEntity<Void> deleteTipolicenca(@PathVariable Long id) {
        log.debug("REST request to delete Tipolicenca : {}", id);
        tipolicencaRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
