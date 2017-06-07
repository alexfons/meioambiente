package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Natureza;

import br.com.homemade.repository.NaturezaRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.NaturezaDTO;
import br.com.homemade.service.mapper.NaturezaMapper;
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
 * REST controller for managing Natureza.
 */
@RestController
@RequestMapping("/api")
public class NaturezaResource {

    private final Logger log = LoggerFactory.getLogger(NaturezaResource.class);

    private static final String ENTITY_NAME = "natureza";
        
    private final NaturezaRepository naturezaRepository;

    private final NaturezaMapper naturezaMapper;

    public NaturezaResource(NaturezaRepository naturezaRepository, NaturezaMapper naturezaMapper) {
        this.naturezaRepository = naturezaRepository;
        this.naturezaMapper = naturezaMapper;
    }

    /**
     * POST  /naturezas : Create a new natureza.
     *
     * @param naturezaDTO the naturezaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new naturezaDTO, or with status 400 (Bad Request) if the natureza has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/naturezas")
    @Timed
    public ResponseEntity<NaturezaDTO> createNatureza(@RequestBody NaturezaDTO naturezaDTO) throws URISyntaxException {
        log.debug("REST request to save Natureza : {}", naturezaDTO);
        if (naturezaDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new natureza cannot already have an ID")).body(null);
        }
        Natureza natureza = naturezaMapper.toEntity(naturezaDTO);
        natureza = naturezaRepository.save(natureza);
        NaturezaDTO result = naturezaMapper.toDto(natureza);
        return ResponseEntity.created(new URI("/api/naturezas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /naturezas : Updates an existing natureza.
     *
     * @param naturezaDTO the naturezaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated naturezaDTO,
     * or with status 400 (Bad Request) if the naturezaDTO is not valid,
     * or with status 500 (Internal Server Error) if the naturezaDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/naturezas")
    @Timed
    public ResponseEntity<NaturezaDTO> updateNatureza(@RequestBody NaturezaDTO naturezaDTO) throws URISyntaxException {
        log.debug("REST request to update Natureza : {}", naturezaDTO);
        if (naturezaDTO.getId() == null) {
            return createNatureza(naturezaDTO);
        }
        Natureza natureza = naturezaMapper.toEntity(naturezaDTO);
        natureza = naturezaRepository.save(natureza);
        NaturezaDTO result = naturezaMapper.toDto(natureza);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, naturezaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /naturezas : get all the naturezas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of naturezas in body
     */
    @GetMapping("/naturezas")
    @Timed
    public List<NaturezaDTO> getAllNaturezas() {
        log.debug("REST request to get all Naturezas");
        List<Natureza> naturezas = naturezaRepository.findAll();
        return naturezaMapper.toDto(naturezas);
    }

    /**
     * GET  /naturezas/:id : get the "id" natureza.
     *
     * @param id the id of the naturezaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the naturezaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/naturezas/{id}")
    @Timed
    public ResponseEntity<NaturezaDTO> getNatureza(@PathVariable Long id) {
        log.debug("REST request to get Natureza : {}", id);
        Natureza natureza = naturezaRepository.findOne(id);
        NaturezaDTO naturezaDTO = naturezaMapper.toDto(natureza);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(naturezaDTO));
    }

    /**
     * DELETE  /naturezas/:id : delete the "id" natureza.
     *
     * @param id the id of the naturezaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/naturezas/{id}")
    @Timed
    public ResponseEntity<Void> deleteNatureza(@PathVariable Long id) {
        log.debug("REST request to delete Natureza : {}", id);
        naturezaRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
