package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.OcorrenciaCertifIrreg;

import br.com.homemade.repository.OcorrenciaCertifIrregRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.OcorrenciaCertifIrregDTO;
import br.com.homemade.service.mapper.OcorrenciaCertifIrregMapper;
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
 * REST controller for managing OcorrenciaCertifIrreg.
 */
@RestController
@RequestMapping("/api")
public class OcorrenciaCertifIrregResource {

    private final Logger log = LoggerFactory.getLogger(OcorrenciaCertifIrregResource.class);

    private static final String ENTITY_NAME = "ocorrenciaCertifIrreg";
        
    private final OcorrenciaCertifIrregRepository ocorrenciaCertifIrregRepository;

    private final OcorrenciaCertifIrregMapper ocorrenciaCertifIrregMapper;

    public OcorrenciaCertifIrregResource(OcorrenciaCertifIrregRepository ocorrenciaCertifIrregRepository, OcorrenciaCertifIrregMapper ocorrenciaCertifIrregMapper) {
        this.ocorrenciaCertifIrregRepository = ocorrenciaCertifIrregRepository;
        this.ocorrenciaCertifIrregMapper = ocorrenciaCertifIrregMapper;
    }

    /**
     * POST  /ocorrencia-certif-irregs : Create a new ocorrenciaCertifIrreg.
     *
     * @param ocorrenciaCertifIrregDTO the ocorrenciaCertifIrregDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ocorrenciaCertifIrregDTO, or with status 400 (Bad Request) if the ocorrenciaCertifIrreg has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ocorrencia-certif-irregs")
    @Timed
    public ResponseEntity<OcorrenciaCertifIrregDTO> createOcorrenciaCertifIrreg(@RequestBody OcorrenciaCertifIrregDTO ocorrenciaCertifIrregDTO) throws URISyntaxException {
        log.debug("REST request to save OcorrenciaCertifIrreg : {}", ocorrenciaCertifIrregDTO);
        if (ocorrenciaCertifIrregDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new ocorrenciaCertifIrreg cannot already have an ID")).body(null);
        }
        OcorrenciaCertifIrreg ocorrenciaCertifIrreg = ocorrenciaCertifIrregMapper.toEntity(ocorrenciaCertifIrregDTO);
        ocorrenciaCertifIrreg = ocorrenciaCertifIrregRepository.save(ocorrenciaCertifIrreg);
        OcorrenciaCertifIrregDTO result = ocorrenciaCertifIrregMapper.toDto(ocorrenciaCertifIrreg);
        return ResponseEntity.created(new URI("/api/ocorrencia-certif-irregs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ocorrencia-certif-irregs : Updates an existing ocorrenciaCertifIrreg.
     *
     * @param ocorrenciaCertifIrregDTO the ocorrenciaCertifIrregDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ocorrenciaCertifIrregDTO,
     * or with status 400 (Bad Request) if the ocorrenciaCertifIrregDTO is not valid,
     * or with status 500 (Internal Server Error) if the ocorrenciaCertifIrregDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ocorrencia-certif-irregs")
    @Timed
    public ResponseEntity<OcorrenciaCertifIrregDTO> updateOcorrenciaCertifIrreg(@RequestBody OcorrenciaCertifIrregDTO ocorrenciaCertifIrregDTO) throws URISyntaxException {
        log.debug("REST request to update OcorrenciaCertifIrreg : {}", ocorrenciaCertifIrregDTO);
        if (ocorrenciaCertifIrregDTO.getId() == null) {
            return createOcorrenciaCertifIrreg(ocorrenciaCertifIrregDTO);
        }
        OcorrenciaCertifIrreg ocorrenciaCertifIrreg = ocorrenciaCertifIrregMapper.toEntity(ocorrenciaCertifIrregDTO);
        ocorrenciaCertifIrreg = ocorrenciaCertifIrregRepository.save(ocorrenciaCertifIrreg);
        OcorrenciaCertifIrregDTO result = ocorrenciaCertifIrregMapper.toDto(ocorrenciaCertifIrreg);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ocorrenciaCertifIrregDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ocorrencia-certif-irregs : get all the ocorrenciaCertifIrregs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of ocorrenciaCertifIrregs in body
     */
    @GetMapping("/ocorrencia-certif-irregs")
    @Timed
    public List<OcorrenciaCertifIrregDTO> getAllOcorrenciaCertifIrregs() {
        log.debug("REST request to get all OcorrenciaCertifIrregs");
        List<OcorrenciaCertifIrreg> ocorrenciaCertifIrregs = ocorrenciaCertifIrregRepository.findAll();
        return ocorrenciaCertifIrregMapper.toDto(ocorrenciaCertifIrregs);
    }

    /**
     * GET  /ocorrencia-certif-irregs/:id : get the "id" ocorrenciaCertifIrreg.
     *
     * @param id the id of the ocorrenciaCertifIrregDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ocorrenciaCertifIrregDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ocorrencia-certif-irregs/{id}")
    @Timed
    public ResponseEntity<OcorrenciaCertifIrregDTO> getOcorrenciaCertifIrreg(@PathVariable Long id) {
        log.debug("REST request to get OcorrenciaCertifIrreg : {}", id);
        OcorrenciaCertifIrreg ocorrenciaCertifIrreg = ocorrenciaCertifIrregRepository.findOne(id);
        OcorrenciaCertifIrregDTO ocorrenciaCertifIrregDTO = ocorrenciaCertifIrregMapper.toDto(ocorrenciaCertifIrreg);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(ocorrenciaCertifIrregDTO));
    }

    /**
     * DELETE  /ocorrencia-certif-irregs/:id : delete the "id" ocorrenciaCertifIrreg.
     *
     * @param id the id of the ocorrenciaCertifIrregDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ocorrencia-certif-irregs/{id}")
    @Timed
    public ResponseEntity<Void> deleteOcorrenciaCertifIrreg(@PathVariable Long id) {
        log.debug("REST request to delete OcorrenciaCertifIrreg : {}", id);
        ocorrenciaCertifIrregRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
