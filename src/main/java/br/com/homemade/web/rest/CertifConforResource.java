package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.CertifConfor;

import br.com.homemade.repository.CertifConforRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.CertifConforDTO;
import br.com.homemade.service.mapper.CertifConforMapper;
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
 * REST controller for managing CertifConfor.
 */
@RestController
@RequestMapping("/api")
public class CertifConforResource {

    private final Logger log = LoggerFactory.getLogger(CertifConforResource.class);

    private static final String ENTITY_NAME = "certifConfor";
        
    private final CertifConforRepository certifConforRepository;

    private final CertifConforMapper certifConforMapper;

    public CertifConforResource(CertifConforRepository certifConforRepository, CertifConforMapper certifConforMapper) {
        this.certifConforRepository = certifConforRepository;
        this.certifConforMapper = certifConforMapper;
    }

    /**
     * POST  /certif-confors : Create a new certifConfor.
     *
     * @param certifConforDTO the certifConforDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new certifConforDTO, or with status 400 (Bad Request) if the certifConfor has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/certif-confors")
    @Timed
    public ResponseEntity<CertifConforDTO> createCertifConfor(@RequestBody CertifConforDTO certifConforDTO) throws URISyntaxException {
        log.debug("REST request to save CertifConfor : {}", certifConforDTO);
        if (certifConforDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new certifConfor cannot already have an ID")).body(null);
        }
        CertifConfor certifConfor = certifConforMapper.toEntity(certifConforDTO);
        certifConfor = certifConforRepository.save(certifConfor);
        CertifConforDTO result = certifConforMapper.toDto(certifConfor);
        return ResponseEntity.created(new URI("/api/certif-confors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /certif-confors : Updates an existing certifConfor.
     *
     * @param certifConforDTO the certifConforDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated certifConforDTO,
     * or with status 400 (Bad Request) if the certifConforDTO is not valid,
     * or with status 500 (Internal Server Error) if the certifConforDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/certif-confors")
    @Timed
    public ResponseEntity<CertifConforDTO> updateCertifConfor(@RequestBody CertifConforDTO certifConforDTO) throws URISyntaxException {
        log.debug("REST request to update CertifConfor : {}", certifConforDTO);
        if (certifConforDTO.getId() == null) {
            return createCertifConfor(certifConforDTO);
        }
        CertifConfor certifConfor = certifConforMapper.toEntity(certifConforDTO);
        certifConfor = certifConforRepository.save(certifConfor);
        CertifConforDTO result = certifConforMapper.toDto(certifConfor);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, certifConforDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /certif-confors : get all the certifConfors.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of certifConfors in body
     */
    @GetMapping("/certif-confors")
    @Timed
    public List<CertifConforDTO> getAllCertifConfors() {
        log.debug("REST request to get all CertifConfors");
        List<CertifConfor> certifConfors = certifConforRepository.findAllWithEagerRelationships();
        return certifConforMapper.toDto(certifConfors);
    }

    /**
     * GET  /certif-confors/:id : get the "id" certifConfor.
     *
     * @param id the id of the certifConforDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the certifConforDTO, or with status 404 (Not Found)
     */
    @GetMapping("/certif-confors/{id}")
    @Timed
    public ResponseEntity<CertifConforDTO> getCertifConfor(@PathVariable Long id) {
        log.debug("REST request to get CertifConfor : {}", id);
        CertifConfor certifConfor = certifConforRepository.findOneWithEagerRelationships(id);
        CertifConforDTO certifConforDTO = certifConforMapper.toDto(certifConfor);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(certifConforDTO));
    }

    /**
     * DELETE  /certif-confors/:id : delete the "id" certifConfor.
     *
     * @param id the id of the certifConforDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/certif-confors/{id}")
    @Timed
    public ResponseEntity<Void> deleteCertifConfor(@PathVariable Long id) {
        log.debug("REST request to delete CertifConfor : {}", id);
        certifConforRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
