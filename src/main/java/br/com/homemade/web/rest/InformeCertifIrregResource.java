package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.InformeCertifIrreg;

import br.com.homemade.repository.InformeCertifIrregRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.InformeCertifIrregDTO;
import br.com.homemade.service.mapper.InformeCertifIrregMapper;
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
 * REST controller for managing InformeCertifIrreg.
 */
@RestController
@RequestMapping("/api")
public class InformeCertifIrregResource {

    private final Logger log = LoggerFactory.getLogger(InformeCertifIrregResource.class);

    private static final String ENTITY_NAME = "informeCertifIrreg";
        
    private final InformeCertifIrregRepository informeCertifIrregRepository;

    private final InformeCertifIrregMapper informeCertifIrregMapper;

    public InformeCertifIrregResource(InformeCertifIrregRepository informeCertifIrregRepository, InformeCertifIrregMapper informeCertifIrregMapper) {
        this.informeCertifIrregRepository = informeCertifIrregRepository;
        this.informeCertifIrregMapper = informeCertifIrregMapper;
    }

    /**
     * POST  /informe-certif-irregs : Create a new informeCertifIrreg.
     *
     * @param informeCertifIrregDTO the informeCertifIrregDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new informeCertifIrregDTO, or with status 400 (Bad Request) if the informeCertifIrreg has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/informe-certif-irregs")
    @Timed
    public ResponseEntity<InformeCertifIrregDTO> createInformeCertifIrreg(@RequestBody InformeCertifIrregDTO informeCertifIrregDTO) throws URISyntaxException {
        log.debug("REST request to save InformeCertifIrreg : {}", informeCertifIrregDTO);
        if (informeCertifIrregDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new informeCertifIrreg cannot already have an ID")).body(null);
        }
        InformeCertifIrreg informeCertifIrreg = informeCertifIrregMapper.toEntity(informeCertifIrregDTO);
        informeCertifIrreg = informeCertifIrregRepository.save(informeCertifIrreg);
        InformeCertifIrregDTO result = informeCertifIrregMapper.toDto(informeCertifIrreg);
        return ResponseEntity.created(new URI("/api/informe-certif-irregs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /informe-certif-irregs : Updates an existing informeCertifIrreg.
     *
     * @param informeCertifIrregDTO the informeCertifIrregDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated informeCertifIrregDTO,
     * or with status 400 (Bad Request) if the informeCertifIrregDTO is not valid,
     * or with status 500 (Internal Server Error) if the informeCertifIrregDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/informe-certif-irregs")
    @Timed
    public ResponseEntity<InformeCertifIrregDTO> updateInformeCertifIrreg(@RequestBody InformeCertifIrregDTO informeCertifIrregDTO) throws URISyntaxException {
        log.debug("REST request to update InformeCertifIrreg : {}", informeCertifIrregDTO);
        if (informeCertifIrregDTO.getId() == null) {
            return createInformeCertifIrreg(informeCertifIrregDTO);
        }
        InformeCertifIrreg informeCertifIrreg = informeCertifIrregMapper.toEntity(informeCertifIrregDTO);
        informeCertifIrreg = informeCertifIrregRepository.save(informeCertifIrreg);
        InformeCertifIrregDTO result = informeCertifIrregMapper.toDto(informeCertifIrreg);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, informeCertifIrregDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /informe-certif-irregs : get all the informeCertifIrregs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of informeCertifIrregs in body
     */
    @GetMapping("/informe-certif-irregs")
    @Timed
    public List<InformeCertifIrregDTO> getAllInformeCertifIrregs() {
        log.debug("REST request to get all InformeCertifIrregs");
        List<InformeCertifIrreg> informeCertifIrregs = informeCertifIrregRepository.findAll();
        return informeCertifIrregMapper.toDto(informeCertifIrregs);
    }

    /**
     * GET  /informe-certif-irregs/:id : get the "id" informeCertifIrreg.
     *
     * @param id the id of the informeCertifIrregDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the informeCertifIrregDTO, or with status 404 (Not Found)
     */
    @GetMapping("/informe-certif-irregs/{id}")
    @Timed
    public ResponseEntity<InformeCertifIrregDTO> getInformeCertifIrreg(@PathVariable Long id) {
        log.debug("REST request to get InformeCertifIrreg : {}", id);
        InformeCertifIrreg informeCertifIrreg = informeCertifIrregRepository.findOne(id);
        InformeCertifIrregDTO informeCertifIrregDTO = informeCertifIrregMapper.toDto(informeCertifIrreg);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(informeCertifIrregDTO));
    }

    /**
     * DELETE  /informe-certif-irregs/:id : delete the "id" informeCertifIrreg.
     *
     * @param id the id of the informeCertifIrregDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/informe-certif-irregs/{id}")
    @Timed
    public ResponseEntity<Void> deleteInformeCertifIrreg(@PathVariable Long id) {
        log.debug("REST request to delete InformeCertifIrreg : {}", id);
        informeCertifIrregRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
