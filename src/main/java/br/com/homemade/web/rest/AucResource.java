package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Auc;

import br.com.homemade.repository.AucRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.web.rest.util.PaginationUtil;
import br.com.homemade.service.dto.AucDTO;
import br.com.homemade.service.mapper.AucMapper;
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
 * REST controller for managing Auc.
 */
@RestController
@RequestMapping("/api")
public class AucResource {

    private final Logger log = LoggerFactory.getLogger(AucResource.class);

    private static final String ENTITY_NAME = "auc";
        
    private final AucRepository aucRepository;

    private final AucMapper aucMapper;

    public AucResource(AucRepository aucRepository, AucMapper aucMapper) {
        this.aucRepository = aucRepository;
        this.aucMapper = aucMapper;
    }

    /**
     * POST  /aucs : Create a new auc.
     *
     * @param aucDTO the aucDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new aucDTO, or with status 400 (Bad Request) if the auc has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/aucs")
    @Timed
    public ResponseEntity<AucDTO> createAuc(@RequestBody AucDTO aucDTO) throws URISyntaxException {
        log.debug("REST request to save Auc : {}", aucDTO);
        if (aucDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new auc cannot already have an ID")).body(null);
        }
        Auc auc = aucMapper.toEntity(aucDTO);
        auc = aucRepository.save(auc);
        AucDTO result = aucMapper.toDto(auc);
        return ResponseEntity.created(new URI("/api/aucs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /aucs : Updates an existing auc.
     *
     * @param aucDTO the aucDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated aucDTO,
     * or with status 400 (Bad Request) if the aucDTO is not valid,
     * or with status 500 (Internal Server Error) if the aucDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/aucs")
    @Timed
    public ResponseEntity<AucDTO> updateAuc(@RequestBody AucDTO aucDTO) throws URISyntaxException {
        log.debug("REST request to update Auc : {}", aucDTO);
        if (aucDTO.getId() == null) {
            return createAuc(aucDTO);
        }
        Auc auc = aucMapper.toEntity(aucDTO);
        auc = aucRepository.save(auc);
        AucDTO result = aucMapper.toDto(auc);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, aucDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /aucs : get all the aucs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of aucs in body
     */
    @GetMapping("/aucs")
    @Timed
    public ResponseEntity<List<AucDTO>> getAllAucs(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Aucs");
        Page<Auc> page = aucRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/aucs");
        return new ResponseEntity<>(aucMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /aucs/:id : get the "id" auc.
     *
     * @param id the id of the aucDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the aucDTO, or with status 404 (Not Found)
     */
    @GetMapping("/aucs/{id}")
    @Timed
    public ResponseEntity<AucDTO> getAuc(@PathVariable Long id) {
        log.debug("REST request to get Auc : {}", id);
        Auc auc = aucRepository.findOneWithEagerRelationships(id);
        AucDTO aucDTO = aucMapper.toDto(auc);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(aucDTO));
    }

    /**
     * DELETE  /aucs/:id : delete the "id" auc.
     *
     * @param id the id of the aucDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/aucs/{id}")
    @Timed
    public ResponseEntity<Void> deleteAuc(@PathVariable Long id) {
        log.debug("REST request to delete Auc : {}", id);
        aucRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
