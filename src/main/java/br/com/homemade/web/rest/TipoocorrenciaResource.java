package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Tipoocorrencia;

import br.com.homemade.repository.TipoocorrenciaRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.TipoocorrenciaDTO;
import br.com.homemade.service.mapper.TipoocorrenciaMapper;
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
 * REST controller for managing Tipoocorrencia.
 */
@RestController
@RequestMapping("/api")
public class TipoocorrenciaResource {

    private final Logger log = LoggerFactory.getLogger(TipoocorrenciaResource.class);

    private static final String ENTITY_NAME = "tipoocorrencia";
        
    private final TipoocorrenciaRepository tipoocorrenciaRepository;

    private final TipoocorrenciaMapper tipoocorrenciaMapper;

    public TipoocorrenciaResource(TipoocorrenciaRepository tipoocorrenciaRepository, TipoocorrenciaMapper tipoocorrenciaMapper) {
        this.tipoocorrenciaRepository = tipoocorrenciaRepository;
        this.tipoocorrenciaMapper = tipoocorrenciaMapper;
    }

    /**
     * POST  /tipoocorrencias : Create a new tipoocorrencia.
     *
     * @param tipoocorrenciaDTO the tipoocorrenciaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipoocorrenciaDTO, or with status 400 (Bad Request) if the tipoocorrencia has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tipoocorrencias")
    @Timed
    public ResponseEntity<TipoocorrenciaDTO> createTipoocorrencia(@RequestBody TipoocorrenciaDTO tipoocorrenciaDTO) throws URISyntaxException {
        log.debug("REST request to save Tipoocorrencia : {}", tipoocorrenciaDTO);
        if (tipoocorrenciaDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new tipoocorrencia cannot already have an ID")).body(null);
        }
        Tipoocorrencia tipoocorrencia = tipoocorrenciaMapper.toEntity(tipoocorrenciaDTO);
        tipoocorrencia = tipoocorrenciaRepository.save(tipoocorrencia);
        TipoocorrenciaDTO result = tipoocorrenciaMapper.toDto(tipoocorrencia);
        return ResponseEntity.created(new URI("/api/tipoocorrencias/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tipoocorrencias : Updates an existing tipoocorrencia.
     *
     * @param tipoocorrenciaDTO the tipoocorrenciaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipoocorrenciaDTO,
     * or with status 400 (Bad Request) if the tipoocorrenciaDTO is not valid,
     * or with status 500 (Internal Server Error) if the tipoocorrenciaDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tipoocorrencias")
    @Timed
    public ResponseEntity<TipoocorrenciaDTO> updateTipoocorrencia(@RequestBody TipoocorrenciaDTO tipoocorrenciaDTO) throws URISyntaxException {
        log.debug("REST request to update Tipoocorrencia : {}", tipoocorrenciaDTO);
        if (tipoocorrenciaDTO.getId() == null) {
            return createTipoocorrencia(tipoocorrenciaDTO);
        }
        Tipoocorrencia tipoocorrencia = tipoocorrenciaMapper.toEntity(tipoocorrenciaDTO);
        tipoocorrencia = tipoocorrenciaRepository.save(tipoocorrencia);
        TipoocorrenciaDTO result = tipoocorrenciaMapper.toDto(tipoocorrencia);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipoocorrenciaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tipoocorrencias : get all the tipoocorrencias.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tipoocorrencias in body
     */
    @GetMapping("/tipoocorrencias")
    @Timed
    public List<TipoocorrenciaDTO> getAllTipoocorrencias() {
        log.debug("REST request to get all Tipoocorrencias");
        List<Tipoocorrencia> tipoocorrencias = tipoocorrenciaRepository.findAll();
        return tipoocorrenciaMapper.toDto(tipoocorrencias);
    }

    /**
     * GET  /tipoocorrencias/:id : get the "id" tipoocorrencia.
     *
     * @param id the id of the tipoocorrenciaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipoocorrenciaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tipoocorrencias/{id}")
    @Timed
    public ResponseEntity<TipoocorrenciaDTO> getTipoocorrencia(@PathVariable Long id) {
        log.debug("REST request to get Tipoocorrencia : {}", id);
        Tipoocorrencia tipoocorrencia = tipoocorrenciaRepository.findOne(id);
        TipoocorrenciaDTO tipoocorrenciaDTO = tipoocorrenciaMapper.toDto(tipoocorrencia);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tipoocorrenciaDTO));
    }

    /**
     * DELETE  /tipoocorrencias/:id : delete the "id" tipoocorrencia.
     *
     * @param id the id of the tipoocorrenciaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tipoocorrencias/{id}")
    @Timed
    public ResponseEntity<Void> deleteTipoocorrencia(@PathVariable Long id) {
        log.debug("REST request to delete Tipoocorrencia : {}", id);
        tipoocorrenciaRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
