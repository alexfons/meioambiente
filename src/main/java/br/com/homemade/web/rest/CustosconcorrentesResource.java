package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Custosconcorrentes;

import br.com.homemade.repository.CustosconcorrentesRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.CustosconcorrentesDTO;
import br.com.homemade.service.mapper.CustosconcorrentesMapper;
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
 * REST controller for managing Custosconcorrentes.
 */
@RestController
@RequestMapping("/api")
public class CustosconcorrentesResource {

    private final Logger log = LoggerFactory.getLogger(CustosconcorrentesResource.class);

    private static final String ENTITY_NAME = "custosconcorrentes";
        
    private final CustosconcorrentesRepository custosconcorrentesRepository;

    private final CustosconcorrentesMapper custosconcorrentesMapper;

    public CustosconcorrentesResource(CustosconcorrentesRepository custosconcorrentesRepository, CustosconcorrentesMapper custosconcorrentesMapper) {
        this.custosconcorrentesRepository = custosconcorrentesRepository;
        this.custosconcorrentesMapper = custosconcorrentesMapper;
    }

    /**
     * POST  /custosconcorrentes : Create a new custosconcorrentes.
     *
     * @param custosconcorrentesDTO the custosconcorrentesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new custosconcorrentesDTO, or with status 400 (Bad Request) if the custosconcorrentes has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/custosconcorrentes")
    @Timed
    public ResponseEntity<CustosconcorrentesDTO> createCustosconcorrentes(@RequestBody CustosconcorrentesDTO custosconcorrentesDTO) throws URISyntaxException {
        log.debug("REST request to save Custosconcorrentes : {}", custosconcorrentesDTO);
        if (custosconcorrentesDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new custosconcorrentes cannot already have an ID")).body(null);
        }
        Custosconcorrentes custosconcorrentes = custosconcorrentesMapper.toEntity(custosconcorrentesDTO);
        custosconcorrentes = custosconcorrentesRepository.save(custosconcorrentes);
        CustosconcorrentesDTO result = custosconcorrentesMapper.toDto(custosconcorrentes);
        return ResponseEntity.created(new URI("/api/custosconcorrentes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /custosconcorrentes : Updates an existing custosconcorrentes.
     *
     * @param custosconcorrentesDTO the custosconcorrentesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated custosconcorrentesDTO,
     * or with status 400 (Bad Request) if the custosconcorrentesDTO is not valid,
     * or with status 500 (Internal Server Error) if the custosconcorrentesDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/custosconcorrentes")
    @Timed
    public ResponseEntity<CustosconcorrentesDTO> updateCustosconcorrentes(@RequestBody CustosconcorrentesDTO custosconcorrentesDTO) throws URISyntaxException {
        log.debug("REST request to update Custosconcorrentes : {}", custosconcorrentesDTO);
        if (custosconcorrentesDTO.getId() == null) {
            return createCustosconcorrentes(custosconcorrentesDTO);
        }
        Custosconcorrentes custosconcorrentes = custosconcorrentesMapper.toEntity(custosconcorrentesDTO);
        custosconcorrentes = custosconcorrentesRepository.save(custosconcorrentes);
        CustosconcorrentesDTO result = custosconcorrentesMapper.toDto(custosconcorrentes);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, custosconcorrentesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /custosconcorrentes : get all the custosconcorrentes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of custosconcorrentes in body
     */
    @GetMapping("/custosconcorrentes")
    @Timed
    public List<CustosconcorrentesDTO> getAllCustosconcorrentes() {
        log.debug("REST request to get all Custosconcorrentes");
        List<Custosconcorrentes> custosconcorrentes = custosconcorrentesRepository.findAll();
        return custosconcorrentesMapper.toDto(custosconcorrentes);
    }

    /**
     * GET  /custosconcorrentes/:id : get the "id" custosconcorrentes.
     *
     * @param id the id of the custosconcorrentesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the custosconcorrentesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/custosconcorrentes/{id}")
    @Timed
    public ResponseEntity<CustosconcorrentesDTO> getCustosconcorrentes(@PathVariable Long id) {
        log.debug("REST request to get Custosconcorrentes : {}", id);
        Custosconcorrentes custosconcorrentes = custosconcorrentesRepository.findOne(id);
        CustosconcorrentesDTO custosconcorrentesDTO = custosconcorrentesMapper.toDto(custosconcorrentes);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(custosconcorrentesDTO));
    }

    /**
     * DELETE  /custosconcorrentes/:id : delete the "id" custosconcorrentes.
     *
     * @param id the id of the custosconcorrentesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/custosconcorrentes/{id}")
    @Timed
    public ResponseEntity<Void> deleteCustosconcorrentes(@PathVariable Long id) {
        log.debug("REST request to delete Custosconcorrentes : {}", id);
        custosconcorrentesRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
