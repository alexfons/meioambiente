package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Tipoobra;

import br.com.homemade.repository.TipoobraRepository2;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.TipoobraDTO;
import br.com.homemade.service.mapper.TipoobraMapper;
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
 * REST controller for managing Tipoobra.
 */
@RestController
@RequestMapping("/api")
public class TipoobraResource2 {

    private final Logger log = LoggerFactory.getLogger(TipoobraResource.class);

    private static final String ENTITY_NAME = "tipoobra";
        
    private final TipoobraRepository2 tipoobraRepository;

    private final TipoobraMapper tipoobraMapper;

    public TipoobraResource2(TipoobraRepository2 tipoobraRepository, TipoobraMapper tipoobraMapper) {
        this.tipoobraRepository = tipoobraRepository;
        this.tipoobraMapper = tipoobraMapper;
    }

    /**
     * POST  /tipoobras : Create a new tipoobra.
     *
     * @param tipoobraDTO the tipoobraDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipoobraDTO, or with status 400 (Bad Request) if the tipoobra has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tipoobras")
    @Timed
    public ResponseEntity<TipoobraDTO> createTipoobra(@RequestBody TipoobraDTO tipoobraDTO) throws URISyntaxException {
        log.debug("REST request to save Tipoobra : {}", tipoobraDTO);
        if (tipoobraDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new tipoobra cannot already have an ID")).body(null);
        }
        Tipoobra tipoobra = tipoobraMapper.toEntity(tipoobraDTO);
        tipoobra = tipoobraRepository.save(tipoobra);
        TipoobraDTO result = tipoobraMapper.toDto(tipoobra);
        return ResponseEntity.created(new URI("/api/tipoobras/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tipoobras : Updates an existing tipoobra.
     *
     * @param tipoobraDTO the tipoobraDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipoobraDTO,
     * or with status 400 (Bad Request) if the tipoobraDTO is not valid,
     * or with status 500 (Internal Server Error) if the tipoobraDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tipoobras")
    @Timed
    public ResponseEntity<TipoobraDTO> updateTipoobra(@RequestBody TipoobraDTO tipoobraDTO) throws URISyntaxException {
        log.debug("REST request to update Tipoobra : {}", tipoobraDTO);
        if (tipoobraDTO.getId() == null) {
            return createTipoobra(tipoobraDTO);
        }
        Tipoobra tipoobra = tipoobraMapper.toEntity(tipoobraDTO);
        tipoobra = tipoobraRepository.save(tipoobra);
        TipoobraDTO result = tipoobraMapper.toDto(tipoobra);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipoobraDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tipoobras : get all the tipoobras.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tipoobras in body
     */
    @GetMapping("/tipoobras")
    @Timed
    public List<TipoobraDTO> getAllTipoobras() {
        log.debug("REST request to get all Tipoobras");
        List<Tipoobra> tipoobras = tipoobraRepository.findAll();
        return tipoobraMapper.toDto(tipoobras);
    }

    /**
     * GET  /tipoobras/:id : get the "id" tipoobra.
     *
     * @param id the id of the tipoobraDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipoobraDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tipoobras/{id}")
    @Timed
    public ResponseEntity<TipoobraDTO> getTipoobra(@PathVariable Long id) {
        log.debug("REST request to get Tipoobra : {}", id);
        Tipoobra tipoobra = tipoobraRepository.findOne(id);
        TipoobraDTO tipoobraDTO = tipoobraMapper.toDto(tipoobra);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tipoobraDTO));
    }

    /**
     * DELETE  /tipoobras/:id : delete the "id" tipoobra.
     *
     * @param id the id of the tipoobraDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tipoobras/{id}")
    @Timed
    public ResponseEntity<Void> deleteTipoobra(@PathVariable Long id) {
        log.debug("REST request to delete Tipoobra : {}", id);
        tipoobraRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
