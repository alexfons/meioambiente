package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Edital;

import br.com.homemade.repository.EditalRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.EditalDTO;
import br.com.homemade.service.mapper.EditalMapper;
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
 * REST controller for managing Edital.
 */
@RestController
@RequestMapping("/api")
public class EditalResource {

    private final Logger log = LoggerFactory.getLogger(EditalResource.class);

    private static final String ENTITY_NAME = "edital";
        
    private final EditalRepository editalRepository;

    private final EditalMapper editalMapper;

    public EditalResource(EditalRepository editalRepository, EditalMapper editalMapper) {
        this.editalRepository = editalRepository;
        this.editalMapper = editalMapper;
    }

    /**
     * POST  /editals : Create a new edital.
     *
     * @param editalDTO the editalDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new editalDTO, or with status 400 (Bad Request) if the edital has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/editals")
    @Timed
    public ResponseEntity<EditalDTO> createEdital(@RequestBody EditalDTO editalDTO) throws URISyntaxException {
        log.debug("REST request to save Edital : {}", editalDTO);
        if (editalDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new edital cannot already have an ID")).body(null);
        }
        Edital edital = editalMapper.toEntity(editalDTO);
        edital = editalRepository.save(edital);
        EditalDTO result = editalMapper.toDto(edital);
        return ResponseEntity.created(new URI("/api/editals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /editals : Updates an existing edital.
     *
     * @param editalDTO the editalDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated editalDTO,
     * or with status 400 (Bad Request) if the editalDTO is not valid,
     * or with status 500 (Internal Server Error) if the editalDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/editals")
    @Timed
    public ResponseEntity<EditalDTO> updateEdital(@RequestBody EditalDTO editalDTO) throws URISyntaxException {
        log.debug("REST request to update Edital : {}", editalDTO);
        if (editalDTO.getId() == null) {
            return createEdital(editalDTO);
        }
        Edital edital = editalMapper.toEntity(editalDTO);
        edital = editalRepository.save(edital);
        EditalDTO result = editalMapper.toDto(edital);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, editalDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /editals : get all the editals.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of editals in body
     */
    @GetMapping("/editals")
    @Timed
    public List<EditalDTO> getAllEditals() {
        log.debug("REST request to get all Editals");
        List<Edital> editals = editalRepository.findAll();
        return editalMapper.toDto(editals);
    }

    /**
     * GET  /editals/:id : get the "id" edital.
     *
     * @param id the id of the editalDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the editalDTO, or with status 404 (Not Found)
     */
    @GetMapping("/editals/{id}")
    @Timed
    public ResponseEntity<EditalDTO> getEdital(@PathVariable Long id) {
        log.debug("REST request to get Edital : {}", id);
        Edital edital = editalRepository.findOne(id);
        EditalDTO editalDTO = editalMapper.toDto(edital);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(editalDTO));
    }

    /**
     * DELETE  /editals/:id : delete the "id" edital.
     *
     * @param id the id of the editalDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/editals/{id}")
    @Timed
    public ResponseEntity<Void> deleteEdital(@PathVariable Long id) {
        log.debug("REST request to delete Edital : {}", id);
        editalRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
