package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Responsavel;

import br.com.homemade.repository.ResponsavelRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.ResponsavelDTO;
import br.com.homemade.service.mapper.ResponsavelMapper;
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
 * REST controller for managing Responsavel.
 */
@RestController
@RequestMapping("/api")
public class ResponsavelResource {

    private final Logger log = LoggerFactory.getLogger(ResponsavelResource.class);

    private static final String ENTITY_NAME = "responsavel";
        
    private final ResponsavelRepository responsavelRepository;

    private final ResponsavelMapper responsavelMapper;

    public ResponsavelResource(ResponsavelRepository responsavelRepository, ResponsavelMapper responsavelMapper) {
        this.responsavelRepository = responsavelRepository;
        this.responsavelMapper = responsavelMapper;
    }

    /**
     * POST  /responsavels : Create a new responsavel.
     *
     * @param responsavelDTO the responsavelDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new responsavelDTO, or with status 400 (Bad Request) if the responsavel has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/responsavels")
    @Timed
    public ResponseEntity<ResponsavelDTO> createResponsavel(@RequestBody ResponsavelDTO responsavelDTO) throws URISyntaxException {
        log.debug("REST request to save Responsavel : {}", responsavelDTO);
        if (responsavelDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new responsavel cannot already have an ID")).body(null);
        }
        Responsavel responsavel = responsavelMapper.toEntity(responsavelDTO);
        responsavel = responsavelRepository.save(responsavel);
        ResponsavelDTO result = responsavelMapper.toDto(responsavel);
        return ResponseEntity.created(new URI("/api/responsavels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /responsavels : Updates an existing responsavel.
     *
     * @param responsavelDTO the responsavelDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated responsavelDTO,
     * or with status 400 (Bad Request) if the responsavelDTO is not valid,
     * or with status 500 (Internal Server Error) if the responsavelDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/responsavels")
    @Timed
    public ResponseEntity<ResponsavelDTO> updateResponsavel(@RequestBody ResponsavelDTO responsavelDTO) throws URISyntaxException {
        log.debug("REST request to update Responsavel : {}", responsavelDTO);
        if (responsavelDTO.getId() == null) {
            return createResponsavel(responsavelDTO);
        }
        Responsavel responsavel = responsavelMapper.toEntity(responsavelDTO);
        responsavel = responsavelRepository.save(responsavel);
        ResponsavelDTO result = responsavelMapper.toDto(responsavel);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, responsavelDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /responsavels : get all the responsavels.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of responsavels in body
     */
    @GetMapping("/responsavels")
    @Timed
    public List<ResponsavelDTO> getAllResponsavels() {
        log.debug("REST request to get all Responsavels");
        List<Responsavel> responsavels = responsavelRepository.findAll();
        return responsavelMapper.toDto(responsavels);
    }

    /**
     * GET  /responsavels/:id : get the "id" responsavel.
     *
     * @param id the id of the responsavelDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the responsavelDTO, or with status 404 (Not Found)
     */
    @GetMapping("/responsavels/{id}")
    @Timed
    public ResponseEntity<ResponsavelDTO> getResponsavel(@PathVariable Long id) {
        log.debug("REST request to get Responsavel : {}", id);
        Responsavel responsavel = responsavelRepository.findOne(id);
        ResponsavelDTO responsavelDTO = responsavelMapper.toDto(responsavel);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(responsavelDTO));
    }

    /**
     * DELETE  /responsavels/:id : delete the "id" responsavel.
     *
     * @param id the id of the responsavelDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/responsavels/{id}")
    @Timed
    public ResponseEntity<Void> deleteResponsavel(@PathVariable Long id) {
        log.debug("REST request to delete Responsavel : {}", id);
        responsavelRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
