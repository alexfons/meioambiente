package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Tipocontrato;

import br.com.homemade.repository.TipocontratoRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.TipocontratoDTO;
import br.com.homemade.service.mapper.TipocontratoMapper;
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
 * REST controller for managing Tipocontrato.
 */
@RestController
@RequestMapping("/api")
public class TipocontratoResource {

    private final Logger log = LoggerFactory.getLogger(TipocontratoResource.class);

    private static final String ENTITY_NAME = "tipocontrato";
        
    private final TipocontratoRepository tipocontratoRepository;

    private final TipocontratoMapper tipocontratoMapper;

    public TipocontratoResource(TipocontratoRepository tipocontratoRepository, TipocontratoMapper tipocontratoMapper) {
        this.tipocontratoRepository = tipocontratoRepository;
        this.tipocontratoMapper = tipocontratoMapper;
    }

    /**
     * POST  /tipocontratoes : Create a new tipocontrato.
     *
     * @param tipocontratoDTO the tipocontratoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipocontratoDTO, or with status 400 (Bad Request) if the tipocontrato has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tipocontratoes")
    @Timed
    public ResponseEntity<TipocontratoDTO> createTipocontrato(@RequestBody TipocontratoDTO tipocontratoDTO) throws URISyntaxException {
        log.debug("REST request to save Tipocontrato : {}", tipocontratoDTO);
        if (tipocontratoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new tipocontrato cannot already have an ID")).body(null);
        }
        Tipocontrato tipocontrato = tipocontratoMapper.toEntity(tipocontratoDTO);
        tipocontrato = tipocontratoRepository.save(tipocontrato);
        TipocontratoDTO result = tipocontratoMapper.toDto(tipocontrato);
        return ResponseEntity.created(new URI("/api/tipocontratoes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tipocontratoes : Updates an existing tipocontrato.
     *
     * @param tipocontratoDTO the tipocontratoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipocontratoDTO,
     * or with status 400 (Bad Request) if the tipocontratoDTO is not valid,
     * or with status 500 (Internal Server Error) if the tipocontratoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tipocontratoes")
    @Timed
    public ResponseEntity<TipocontratoDTO> updateTipocontrato(@RequestBody TipocontratoDTO tipocontratoDTO) throws URISyntaxException {
        log.debug("REST request to update Tipocontrato : {}", tipocontratoDTO);
        if (tipocontratoDTO.getId() == null) {
            return createTipocontrato(tipocontratoDTO);
        }
        Tipocontrato tipocontrato = tipocontratoMapper.toEntity(tipocontratoDTO);
        tipocontrato = tipocontratoRepository.save(tipocontrato);
        TipocontratoDTO result = tipocontratoMapper.toDto(tipocontrato);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipocontratoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tipocontratoes : get all the tipocontratoes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tipocontratoes in body
     */
    @GetMapping("/tipocontratoes")
    @Timed
    public List<TipocontratoDTO> getAllTipocontratoes() {
        log.debug("REST request to get all Tipocontratoes");
        List<Tipocontrato> tipocontratoes = tipocontratoRepository.findAll();
        return tipocontratoMapper.toDto(tipocontratoes);
    }

    /**
     * GET  /tipocontratoes/:id : get the "id" tipocontrato.
     *
     * @param id the id of the tipocontratoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipocontratoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tipocontratoes/{id}")
    @Timed
    public ResponseEntity<TipocontratoDTO> getTipocontrato(@PathVariable Long id) {
        log.debug("REST request to get Tipocontrato : {}", id);
        Tipocontrato tipocontrato = tipocontratoRepository.findOne(id);
        TipocontratoDTO tipocontratoDTO = tipocontratoMapper.toDto(tipocontrato);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tipocontratoDTO));
    }

    /**
     * DELETE  /tipocontratoes/:id : delete the "id" tipocontrato.
     *
     * @param id the id of the tipocontratoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tipocontratoes/{id}")
    @Timed
    public ResponseEntity<Void> deleteTipocontrato(@PathVariable Long id) {
        log.debug("REST request to delete Tipocontrato : {}", id);
        tipocontratoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
