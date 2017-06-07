package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Tipo;

import br.com.homemade.repository.TipoRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.TipoDTO;
import br.com.homemade.service.mapper.TipoMapper;
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
 * REST controller for managing Tipo.
 */
@RestController
@RequestMapping("/api")
public class TipoResource {

    private final Logger log = LoggerFactory.getLogger(TipoResource.class);

    private static final String ENTITY_NAME = "tipo";
        
    private final TipoRepository tipoRepository;

    private final TipoMapper tipoMapper;

    public TipoResource(TipoRepository tipoRepository, TipoMapper tipoMapper) {
        this.tipoRepository = tipoRepository;
        this.tipoMapper = tipoMapper;
    }

    /**
     * POST  /tipos : Create a new tipo.
     *
     * @param tipoDTO the tipoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipoDTO, or with status 400 (Bad Request) if the tipo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tipos")
    @Timed
    public ResponseEntity<TipoDTO> createTipo(@RequestBody TipoDTO tipoDTO) throws URISyntaxException {
        log.debug("REST request to save Tipo : {}", tipoDTO);
        if (tipoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new tipo cannot already have an ID")).body(null);
        }
        Tipo tipo = tipoMapper.toEntity(tipoDTO);
        tipo = tipoRepository.save(tipo);
        TipoDTO result = tipoMapper.toDto(tipo);
        return ResponseEntity.created(new URI("/api/tipos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tipos : Updates an existing tipo.
     *
     * @param tipoDTO the tipoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipoDTO,
     * or with status 400 (Bad Request) if the tipoDTO is not valid,
     * or with status 500 (Internal Server Error) if the tipoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tipos")
    @Timed
    public ResponseEntity<TipoDTO> updateTipo(@RequestBody TipoDTO tipoDTO) throws URISyntaxException {
        log.debug("REST request to update Tipo : {}", tipoDTO);
        if (tipoDTO.getId() == null) {
            return createTipo(tipoDTO);
        }
        Tipo tipo = tipoMapper.toEntity(tipoDTO);
        tipo = tipoRepository.save(tipo);
        TipoDTO result = tipoMapper.toDto(tipo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tipos : get all the tipos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tipos in body
     */
    @GetMapping("/tipos")
    @Timed
    public List<TipoDTO> getAllTipos() {
        log.debug("REST request to get all Tipos");
        List<Tipo> tipos = tipoRepository.findAll();
        return tipoMapper.toDto(tipos);
    }

    /**
     * GET  /tipos/:id : get the "id" tipo.
     *
     * @param id the id of the tipoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tipos/{id}")
    @Timed
    public ResponseEntity<TipoDTO> getTipo(@PathVariable Long id) {
        log.debug("REST request to get Tipo : {}", id);
        Tipo tipo = tipoRepository.findOne(id);
        TipoDTO tipoDTO = tipoMapper.toDto(tipo);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tipoDTO));
    }

    /**
     * DELETE  /tipos/:id : delete the "id" tipo.
     *
     * @param id the id of the tipoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tipos/{id}")
    @Timed
    public ResponseEntity<Void> deleteTipo(@PathVariable Long id) {
        log.debug("REST request to delete Tipo : {}", id);
        tipoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
