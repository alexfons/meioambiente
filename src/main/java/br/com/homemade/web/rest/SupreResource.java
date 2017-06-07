package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Supre;

import br.com.homemade.repository.SupreRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.SupreDTO;
import br.com.homemade.service.mapper.SupreMapper;
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
 * REST controller for managing Supre.
 */
@RestController
@RequestMapping("/api")
public class SupreResource {

    private final Logger log = LoggerFactory.getLogger(SupreResource.class);

    private static final String ENTITY_NAME = "supre";
        
    private final SupreRepository supreRepository;

    private final SupreMapper supreMapper;

    public SupreResource(SupreRepository supreRepository, SupreMapper supreMapper) {
        this.supreRepository = supreRepository;
        this.supreMapper = supreMapper;
    }

    /**
     * POST  /supres : Create a new supre.
     *
     * @param supreDTO the supreDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new supreDTO, or with status 400 (Bad Request) if the supre has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/supres")
    @Timed
    public ResponseEntity<SupreDTO> createSupre(@RequestBody SupreDTO supreDTO) throws URISyntaxException {
        log.debug("REST request to save Supre : {}", supreDTO);
        if (supreDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new supre cannot already have an ID")).body(null);
        }
        Supre supre = supreMapper.toEntity(supreDTO);
        supre = supreRepository.save(supre);
        SupreDTO result = supreMapper.toDto(supre);
        return ResponseEntity.created(new URI("/api/supres/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /supres : Updates an existing supre.
     *
     * @param supreDTO the supreDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated supreDTO,
     * or with status 400 (Bad Request) if the supreDTO is not valid,
     * or with status 500 (Internal Server Error) if the supreDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/supres")
    @Timed
    public ResponseEntity<SupreDTO> updateSupre(@RequestBody SupreDTO supreDTO) throws URISyntaxException {
        log.debug("REST request to update Supre : {}", supreDTO);
        if (supreDTO.getId() == null) {
            return createSupre(supreDTO);
        }
        Supre supre = supreMapper.toEntity(supreDTO);
        supre = supreRepository.save(supre);
        SupreDTO result = supreMapper.toDto(supre);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, supreDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /supres : get all the supres.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of supres in body
     */
    @GetMapping("/supres")
    @Timed
    public List<SupreDTO> getAllSupres() {
        log.debug("REST request to get all Supres");
        List<Supre> supres = supreRepository.findAll();
        return supreMapper.toDto(supres);
    }

    /**
     * GET  /supres/:id : get the "id" supre.
     *
     * @param id the id of the supreDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the supreDTO, or with status 404 (Not Found)
     */
    @GetMapping("/supres/{id}")
    @Timed
    public ResponseEntity<SupreDTO> getSupre(@PathVariable Long id) {
        log.debug("REST request to get Supre : {}", id);
        Supre supre = supreRepository.findOne(id);
        SupreDTO supreDTO = supreMapper.toDto(supre);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(supreDTO));
    }

    /**
     * DELETE  /supres/:id : delete the "id" supre.
     *
     * @param id the id of the supreDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/supres/{id}")
    @Timed
    public ResponseEntity<Void> deleteSupre(@PathVariable Long id) {
        log.debug("REST request to delete Supre : {}", id);
        supreRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
