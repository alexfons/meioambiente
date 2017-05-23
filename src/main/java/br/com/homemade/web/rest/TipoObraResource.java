package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.TipoObra;

import br.com.homemade.repository.TipoObraRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.TipoObraDTO;
import br.com.homemade.service.mapper.TipoObraMapper;
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
 * REST controller for managing TipoObra.
 */
@RestController
@RequestMapping("/api")
public class TipoObraResource {

    private final Logger log = LoggerFactory.getLogger(TipoObraResource.class);

    private static final String ENTITY_NAME = "tipoObra";
        
    private final TipoObraRepository tipoObraRepository;

    private final TipoObraMapper tipoObraMapper;

    public TipoObraResource(TipoObraRepository tipoObraRepository, TipoObraMapper tipoObraMapper) {
        this.tipoObraRepository = tipoObraRepository;
        this.tipoObraMapper = tipoObraMapper;
    }

    /**
     * POST  /tipo-obras : Create a new tipoObra.
     *
     * @param tipoObraDTO the tipoObraDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipoObraDTO, or with status 400 (Bad Request) if the tipoObra has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tipo-obras")
    @Timed
    public ResponseEntity<TipoObraDTO> createTipoObra(@RequestBody TipoObraDTO tipoObraDTO) throws URISyntaxException {
        log.debug("REST request to save TipoObra : {}", tipoObraDTO);
        if (tipoObraDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new tipoObra cannot already have an ID")).body(null);
        }
        TipoObra tipoObra = tipoObraMapper.toEntity(tipoObraDTO);
        tipoObra = tipoObraRepository.save(tipoObra);
        TipoObraDTO result = tipoObraMapper.toDto(tipoObra);
        return ResponseEntity.created(new URI("/api/tipo-obras/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tipo-obras : Updates an existing tipoObra.
     *
     * @param tipoObraDTO the tipoObraDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipoObraDTO,
     * or with status 400 (Bad Request) if the tipoObraDTO is not valid,
     * or with status 500 (Internal Server Error) if the tipoObraDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tipo-obras")
    @Timed
    public ResponseEntity<TipoObraDTO> updateTipoObra(@RequestBody TipoObraDTO tipoObraDTO) throws URISyntaxException {
        log.debug("REST request to update TipoObra : {}", tipoObraDTO);
        if (tipoObraDTO.getId() == null) {
            return createTipoObra(tipoObraDTO);
        }
        TipoObra tipoObra = tipoObraMapper.toEntity(tipoObraDTO);
        tipoObra = tipoObraRepository.save(tipoObra);
        TipoObraDTO result = tipoObraMapper.toDto(tipoObra);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipoObraDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tipo-obras : get all the tipoObras.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tipoObras in body
     */
    @GetMapping("/tipo-obras")
    @Timed
    public List<TipoObraDTO> getAllTipoObras() {
        log.debug("REST request to get all TipoObras");
        List<TipoObra> tipoObras = tipoObraRepository.findAll();
        return tipoObraMapper.toDto(tipoObras);
    }

    /**
     * GET  /tipo-obras/:id : get the "id" tipoObra.
     *
     * @param id the id of the tipoObraDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipoObraDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tipo-obras/{id}")
    @Timed
    public ResponseEntity<TipoObraDTO> getTipoObra(@PathVariable Long id) {
        log.debug("REST request to get TipoObra : {}", id);
        TipoObra tipoObra = tipoObraRepository.findOne(id);
        TipoObraDTO tipoObraDTO = tipoObraMapper.toDto(tipoObra);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tipoObraDTO));
    }

    /**
     * DELETE  /tipo-obras/:id : delete the "id" tipoObra.
     *
     * @param id the id of the tipoObraDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tipo-obras/{id}")
    @Timed
    public ResponseEntity<Void> deleteTipoObra(@PathVariable Long id) {
        log.debug("REST request to delete TipoObra : {}", id);
        tipoObraRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
