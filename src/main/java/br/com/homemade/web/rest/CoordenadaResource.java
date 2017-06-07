package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Coordenada;

import br.com.homemade.repository.CoordenadaRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.CoordenadaDTO;
import br.com.homemade.service.mapper.CoordenadaMapper;
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
 * REST controller for managing Coordenada.
 */
@RestController
@RequestMapping("/api")
public class CoordenadaResource {

    private final Logger log = LoggerFactory.getLogger(CoordenadaResource.class);

    private static final String ENTITY_NAME = "coordenada";
        
    private final CoordenadaRepository coordenadaRepository;

    private final CoordenadaMapper coordenadaMapper;

    public CoordenadaResource(CoordenadaRepository coordenadaRepository, CoordenadaMapper coordenadaMapper) {
        this.coordenadaRepository = coordenadaRepository;
        this.coordenadaMapper = coordenadaMapper;
    }

    /**
     * POST  /coordenadas : Create a new coordenada.
     *
     * @param coordenadaDTO the coordenadaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new coordenadaDTO, or with status 400 (Bad Request) if the coordenada has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/coordenadas")
    @Timed
    public ResponseEntity<CoordenadaDTO> createCoordenada(@RequestBody CoordenadaDTO coordenadaDTO) throws URISyntaxException {
        log.debug("REST request to save Coordenada : {}", coordenadaDTO);
        if (coordenadaDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new coordenada cannot already have an ID")).body(null);
        }
        Coordenada coordenada = coordenadaMapper.toEntity(coordenadaDTO);
        coordenada = coordenadaRepository.save(coordenada);
        CoordenadaDTO result = coordenadaMapper.toDto(coordenada);
        return ResponseEntity.created(new URI("/api/coordenadas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /coordenadas : Updates an existing coordenada.
     *
     * @param coordenadaDTO the coordenadaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated coordenadaDTO,
     * or with status 400 (Bad Request) if the coordenadaDTO is not valid,
     * or with status 500 (Internal Server Error) if the coordenadaDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/coordenadas")
    @Timed
    public ResponseEntity<CoordenadaDTO> updateCoordenada(@RequestBody CoordenadaDTO coordenadaDTO) throws URISyntaxException {
        log.debug("REST request to update Coordenada : {}", coordenadaDTO);
        if (coordenadaDTO.getId() == null) {
            return createCoordenada(coordenadaDTO);
        }
        Coordenada coordenada = coordenadaMapper.toEntity(coordenadaDTO);
        coordenada = coordenadaRepository.save(coordenada);
        CoordenadaDTO result = coordenadaMapper.toDto(coordenada);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, coordenadaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /coordenadas : get all the coordenadas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of coordenadas in body
     */
    @GetMapping("/coordenadas")
    @Timed
    public List<CoordenadaDTO> getAllCoordenadas() {
        log.debug("REST request to get all Coordenadas");
        List<Coordenada> coordenadas = coordenadaRepository.findAll();
        return coordenadaMapper.toDto(coordenadas);
    }

    /**
     * GET  /coordenadas/:id : get the "id" coordenada.
     *
     * @param id the id of the coordenadaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the coordenadaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/coordenadas/{id}")
    @Timed
    public ResponseEntity<CoordenadaDTO> getCoordenada(@PathVariable Long id) {
        log.debug("REST request to get Coordenada : {}", id);
        Coordenada coordenada = coordenadaRepository.findOne(id);
        CoordenadaDTO coordenadaDTO = coordenadaMapper.toDto(coordenada);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(coordenadaDTO));
    }

    /**
     * DELETE  /coordenadas/:id : delete the "id" coordenada.
     *
     * @param id the id of the coordenadaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/coordenadas/{id}")
    @Timed
    public ResponseEntity<Void> deleteCoordenada(@PathVariable Long id) {
        log.debug("REST request to delete Coordenada : {}", id);
        coordenadaRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
