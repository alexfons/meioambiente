package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.ResponsavelTipo;

import br.com.homemade.repository.ResponsavelTipoRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.ResponsavelTipoDTO;
import br.com.homemade.service.mapper.ResponsavelTipoMapper;
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
 * REST controller for managing ResponsavelTipo.
 */
@RestController
@RequestMapping("/api")
public class ResponsavelTipoResource {

    private final Logger log = LoggerFactory.getLogger(ResponsavelTipoResource.class);

    private static final String ENTITY_NAME = "responsavelTipo";
        
    private final ResponsavelTipoRepository responsavelTipoRepository;

    private final ResponsavelTipoMapper responsavelTipoMapper;

    public ResponsavelTipoResource(ResponsavelTipoRepository responsavelTipoRepository, ResponsavelTipoMapper responsavelTipoMapper) {
        this.responsavelTipoRepository = responsavelTipoRepository;
        this.responsavelTipoMapper = responsavelTipoMapper;
    }

    /**
     * POST  /responsavel-tipos : Create a new responsavelTipo.
     *
     * @param responsavelTipoDTO the responsavelTipoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new responsavelTipoDTO, or with status 400 (Bad Request) if the responsavelTipo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/responsavel-tipos")
    @Timed
    public ResponseEntity<ResponsavelTipoDTO> createResponsavelTipo(@RequestBody ResponsavelTipoDTO responsavelTipoDTO) throws URISyntaxException {
        log.debug("REST request to save ResponsavelTipo : {}", responsavelTipoDTO);
        if (responsavelTipoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new responsavelTipo cannot already have an ID")).body(null);
        }
        ResponsavelTipo responsavelTipo = responsavelTipoMapper.toEntity(responsavelTipoDTO);
        responsavelTipo = responsavelTipoRepository.save(responsavelTipo);
        ResponsavelTipoDTO result = responsavelTipoMapper.toDto(responsavelTipo);
        return ResponseEntity.created(new URI("/api/responsavel-tipos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /responsavel-tipos : Updates an existing responsavelTipo.
     *
     * @param responsavelTipoDTO the responsavelTipoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated responsavelTipoDTO,
     * or with status 400 (Bad Request) if the responsavelTipoDTO is not valid,
     * or with status 500 (Internal Server Error) if the responsavelTipoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/responsavel-tipos")
    @Timed
    public ResponseEntity<ResponsavelTipoDTO> updateResponsavelTipo(@RequestBody ResponsavelTipoDTO responsavelTipoDTO) throws URISyntaxException {
        log.debug("REST request to update ResponsavelTipo : {}", responsavelTipoDTO);
        if (responsavelTipoDTO.getId() == null) {
            return createResponsavelTipo(responsavelTipoDTO);
        }
        ResponsavelTipo responsavelTipo = responsavelTipoMapper.toEntity(responsavelTipoDTO);
        responsavelTipo = responsavelTipoRepository.save(responsavelTipo);
        ResponsavelTipoDTO result = responsavelTipoMapper.toDto(responsavelTipo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, responsavelTipoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /responsavel-tipos : get all the responsavelTipos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of responsavelTipos in body
     */
    @GetMapping("/responsavel-tipos")
    @Timed
    public List<ResponsavelTipoDTO> getAllResponsavelTipos() {
        log.debug("REST request to get all ResponsavelTipos");
        List<ResponsavelTipo> responsavelTipos = responsavelTipoRepository.findAll();
        return responsavelTipoMapper.toDto(responsavelTipos);
    }

    /**
     * GET  /responsavel-tipos/:id : get the "id" responsavelTipo.
     *
     * @param id the id of the responsavelTipoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the responsavelTipoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/responsavel-tipos/{id}")
    @Timed
    public ResponseEntity<ResponsavelTipoDTO> getResponsavelTipo(@PathVariable Long id) {
        log.debug("REST request to get ResponsavelTipo : {}", id);
        ResponsavelTipo responsavelTipo = responsavelTipoRepository.findOne(id);
        ResponsavelTipoDTO responsavelTipoDTO = responsavelTipoMapper.toDto(responsavelTipo);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(responsavelTipoDTO));
    }

    /**
     * DELETE  /responsavel-tipos/:id : delete the "id" responsavelTipo.
     *
     * @param id the id of the responsavelTipoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/responsavel-tipos/{id}")
    @Timed
    public ResponseEntity<Void> deleteResponsavelTipo(@PathVariable Long id) {
        log.debug("REST request to delete ResponsavelTipo : {}", id);
        responsavelTipoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
