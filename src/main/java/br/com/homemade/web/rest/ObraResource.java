package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Obra;

import br.com.homemade.repository.ObraRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.web.rest.util.PaginationUtil;
import br.com.homemade.service.dto.ObraDTO;
import br.com.homemade.service.mapper.ObraMapper;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Obra.
 */
@RestController
@RequestMapping("/api")
public class ObraResource {

    private final Logger log = LoggerFactory.getLogger(ObraResource.class);

    private static final String ENTITY_NAME = "obra";
        
    private final ObraRepository obraRepository;

    private final ObraMapper obraMapper;

    public ObraResource(ObraRepository obraRepository, ObraMapper obraMapper) {
        this.obraRepository = obraRepository;
        this.obraMapper = obraMapper;
    }

    /**
     * POST  /obras : Create a new obra.
     *
     * @param obraDTO the obraDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new obraDTO, or with status 400 (Bad Request) if the obra has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/obras")
    @Timed
    public ResponseEntity<ObraDTO> createObra(@RequestBody ObraDTO obraDTO) throws URISyntaxException {
        log.debug("REST request to save Obra : {}", obraDTO);
        if (obraDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new obra cannot already have an ID")).body(null);
        }
        Obra obra = obraMapper.toEntity(obraDTO);
        obra = obraRepository.save(obra);
        ObraDTO result = obraMapper.toDto(obra);
        return ResponseEntity.created(new URI("/api/obras/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /obras : Updates an existing obra.
     *
     * @param obraDTO the obraDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated obraDTO,
     * or with status 400 (Bad Request) if the obraDTO is not valid,
     * or with status 500 (Internal Server Error) if the obraDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/obras")
    @Timed
    public ResponseEntity<ObraDTO> updateObra(@RequestBody ObraDTO obraDTO) throws URISyntaxException {
        log.debug("REST request to update Obra : {}", obraDTO);
        if (obraDTO.getId() == null) {
            return createObra(obraDTO);
        }
        Obra obra = obraMapper.toEntity(obraDTO);
        obra = obraRepository.save(obra);
        ObraDTO result = obraMapper.toDto(obra);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, obraDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /obras : get all the obras.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of obras in body
     */
    @GetMapping("/obras")
    @Timed
    public ResponseEntity<List<ObraDTO>> getAllObras(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Obras");
        Page<Obra> page = obraRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/obras");
        return new ResponseEntity<>(obraMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /obras/:id : get the "id" obra.
     *
     * @param id the id of the obraDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the obraDTO, or with status 404 (Not Found)
     */
    @GetMapping("/obras/{id}")
    @Timed
    public ResponseEntity<ObraDTO> getObra(@PathVariable Long id) {
        log.debug("REST request to get Obra : {}", id);
        Obra obra = obraRepository.findOneWithEagerRelationships(id);
        ObraDTO obraDTO = obraMapper.toDto(obra);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(obraDTO));
    }

    /**
     * DELETE  /obras/:id : delete the "id" obra.
     *
     * @param id the id of the obraDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/obras/{id}")
    @Timed
    public ResponseEntity<Void> deleteObra(@PathVariable Long id) {
        log.debug("REST request to delete Obra : {}", id);
        obraRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
