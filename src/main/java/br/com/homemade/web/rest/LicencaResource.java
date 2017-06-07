package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Licenca;

import br.com.homemade.repository.LicencaRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.LicencaDTO;
import br.com.homemade.service.mapper.LicencaMapper;
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
 * REST controller for managing Licenca.
 */
@RestController
@RequestMapping("/api")
public class LicencaResource {

    private final Logger log = LoggerFactory.getLogger(LicencaResource.class);

    private static final String ENTITY_NAME = "licenca";
        
    private final LicencaRepository licencaRepository;

    private final LicencaMapper licencaMapper;

    public LicencaResource(LicencaRepository licencaRepository, LicencaMapper licencaMapper) {
        this.licencaRepository = licencaRepository;
        this.licencaMapper = licencaMapper;
    }

    /**
     * POST  /licencas : Create a new licenca.
     *
     * @param licencaDTO the licencaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new licencaDTO, or with status 400 (Bad Request) if the licenca has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/licencas")
    @Timed
    public ResponseEntity<LicencaDTO> createLicenca(@RequestBody LicencaDTO licencaDTO) throws URISyntaxException {
        log.debug("REST request to save Licenca : {}", licencaDTO);
        if (licencaDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new licenca cannot already have an ID")).body(null);
        }
        Licenca licenca = licencaMapper.toEntity(licencaDTO);
        licenca = licencaRepository.save(licenca);
        LicencaDTO result = licencaMapper.toDto(licenca);
        return ResponseEntity.created(new URI("/api/licencas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /licencas : Updates an existing licenca.
     *
     * @param licencaDTO the licencaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated licencaDTO,
     * or with status 400 (Bad Request) if the licencaDTO is not valid,
     * or with status 500 (Internal Server Error) if the licencaDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/licencas")
    @Timed
    public ResponseEntity<LicencaDTO> updateLicenca(@RequestBody LicencaDTO licencaDTO) throws URISyntaxException {
        log.debug("REST request to update Licenca : {}", licencaDTO);
        if (licencaDTO.getId() == null) {
            return createLicenca(licencaDTO);
        }
        Licenca licenca = licencaMapper.toEntity(licencaDTO);
        licenca = licencaRepository.save(licenca);
        LicencaDTO result = licencaMapper.toDto(licenca);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, licencaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /licencas : get all the licencas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of licencas in body
     */
    @GetMapping("/licencas")
    @Timed
    public List<LicencaDTO> getAllLicencas() {
        log.debug("REST request to get all Licencas");
        List<Licenca> licencas = licencaRepository.findAllWithEagerRelationships();
        return licencaMapper.toDto(licencas);
    }

    /**
     * GET  /licencas/:id : get the "id" licenca.
     *
     * @param id the id of the licencaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the licencaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/licencas/{id}")
    @Timed
    public ResponseEntity<LicencaDTO> getLicenca(@PathVariable Long id) {
        log.debug("REST request to get Licenca : {}", id);
        Licenca licenca = licencaRepository.findOneWithEagerRelationships(id);
        LicencaDTO licencaDTO = licencaMapper.toDto(licenca);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(licencaDTO));
    }

    /**
     * DELETE  /licencas/:id : delete the "id" licenca.
     *
     * @param id the id of the licencaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/licencas/{id}")
    @Timed
    public ResponseEntity<Void> deleteLicenca(@PathVariable Long id) {
        log.debug("REST request to delete Licenca : {}", id);
        licencaRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
