package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Fisico;

import br.com.homemade.repository.FisicoRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.FisicoDTO;
import br.com.homemade.service.mapper.FisicoMapper;
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
 * REST controller for managing Fisico.
 */
@RestController
@RequestMapping("/api")
public class FisicoResource {

    private final Logger log = LoggerFactory.getLogger(FisicoResource.class);

    private static final String ENTITY_NAME = "fisico";
        
    private final FisicoRepository fisicoRepository;

    private final FisicoMapper fisicoMapper;

    public FisicoResource(FisicoRepository fisicoRepository, FisicoMapper fisicoMapper) {
        this.fisicoRepository = fisicoRepository;
        this.fisicoMapper = fisicoMapper;
    }

    /**
     * POST  /fisicos : Create a new fisico.
     *
     * @param fisicoDTO the fisicoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fisicoDTO, or with status 400 (Bad Request) if the fisico has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/fisicos")
    @Timed
    public ResponseEntity<FisicoDTO> createFisico(@RequestBody FisicoDTO fisicoDTO) throws URISyntaxException {
        log.debug("REST request to save Fisico : {}", fisicoDTO);
        if (fisicoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new fisico cannot already have an ID")).body(null);
        }
        Fisico fisico = fisicoMapper.toEntity(fisicoDTO);
        fisico = fisicoRepository.save(fisico);
        FisicoDTO result = fisicoMapper.toDto(fisico);
        return ResponseEntity.created(new URI("/api/fisicos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /fisicos : Updates an existing fisico.
     *
     * @param fisicoDTO the fisicoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fisicoDTO,
     * or with status 400 (Bad Request) if the fisicoDTO is not valid,
     * or with status 500 (Internal Server Error) if the fisicoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/fisicos")
    @Timed
    public ResponseEntity<FisicoDTO> updateFisico(@RequestBody FisicoDTO fisicoDTO) throws URISyntaxException {
        log.debug("REST request to update Fisico : {}", fisicoDTO);
        if (fisicoDTO.getId() == null) {
            return createFisico(fisicoDTO);
        }
        Fisico fisico = fisicoMapper.toEntity(fisicoDTO);
        fisico = fisicoRepository.save(fisico);
        FisicoDTO result = fisicoMapper.toDto(fisico);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fisicoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /fisicos : get all the fisicos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of fisicos in body
     */
    @GetMapping("/fisicos")
    @Timed
    public List<FisicoDTO> getAllFisicos() {
        log.debug("REST request to get all Fisicos");
        List<Fisico> fisicos = fisicoRepository.findAllWithEagerRelationships();
        return fisicoMapper.toDto(fisicos);
    }

    /**
     * GET  /fisicos/:id : get the "id" fisico.
     *
     * @param id the id of the fisicoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fisicoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/fisicos/{id}")
    @Timed
    public ResponseEntity<FisicoDTO> getFisico(@PathVariable Long id) {
        log.debug("REST request to get Fisico : {}", id);
        Fisico fisico = fisicoRepository.findOneWithEagerRelationships(id);
        FisicoDTO fisicoDTO = fisicoMapper.toDto(fisico);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(fisicoDTO));
    }

    /**
     * DELETE  /fisicos/:id : delete the "id" fisico.
     *
     * @param id the id of the fisicoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/fisicos/{id}")
    @Timed
    public ResponseEntity<Void> deleteFisico(@PathVariable Long id) {
        log.debug("REST request to delete Fisico : {}", id);
        fisicoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
