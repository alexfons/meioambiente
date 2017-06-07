package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Certificadoconformidade;

import br.com.homemade.repository.CertificadoconformidadeRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.CertificadoconformidadeDTO;
import br.com.homemade.service.mapper.CertificadoconformidadeMapper;
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
 * REST controller for managing Certificadoconformidade.
 */
@RestController
@RequestMapping("/api")
public class CertificadoconformidadeResource {

    private final Logger log = LoggerFactory.getLogger(CertificadoconformidadeResource.class);

    private static final String ENTITY_NAME = "certificadoconformidade";
        
    private final CertificadoconformidadeRepository certificadoconformidadeRepository;

    private final CertificadoconformidadeMapper certificadoconformidadeMapper;

    public CertificadoconformidadeResource(CertificadoconformidadeRepository certificadoconformidadeRepository, CertificadoconformidadeMapper certificadoconformidadeMapper) {
        this.certificadoconformidadeRepository = certificadoconformidadeRepository;
        this.certificadoconformidadeMapper = certificadoconformidadeMapper;
    }

    /**
     * POST  /certificadoconformidades : Create a new certificadoconformidade.
     *
     * @param certificadoconformidadeDTO the certificadoconformidadeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new certificadoconformidadeDTO, or with status 400 (Bad Request) if the certificadoconformidade has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/certificadoconformidades")
    @Timed
    public ResponseEntity<CertificadoconformidadeDTO> createCertificadoconformidade(@RequestBody CertificadoconformidadeDTO certificadoconformidadeDTO) throws URISyntaxException {
        log.debug("REST request to save Certificadoconformidade : {}", certificadoconformidadeDTO);
        if (certificadoconformidadeDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new certificadoconformidade cannot already have an ID")).body(null);
        }
        Certificadoconformidade certificadoconformidade = certificadoconformidadeMapper.toEntity(certificadoconformidadeDTO);
        certificadoconformidade = certificadoconformidadeRepository.save(certificadoconformidade);
        CertificadoconformidadeDTO result = certificadoconformidadeMapper.toDto(certificadoconformidade);
        return ResponseEntity.created(new URI("/api/certificadoconformidades/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /certificadoconformidades : Updates an existing certificadoconformidade.
     *
     * @param certificadoconformidadeDTO the certificadoconformidadeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated certificadoconformidadeDTO,
     * or with status 400 (Bad Request) if the certificadoconformidadeDTO is not valid,
     * or with status 500 (Internal Server Error) if the certificadoconformidadeDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/certificadoconformidades")
    @Timed
    public ResponseEntity<CertificadoconformidadeDTO> updateCertificadoconformidade(@RequestBody CertificadoconformidadeDTO certificadoconformidadeDTO) throws URISyntaxException {
        log.debug("REST request to update Certificadoconformidade : {}", certificadoconformidadeDTO);
        if (certificadoconformidadeDTO.getId() == null) {
            return createCertificadoconformidade(certificadoconformidadeDTO);
        }
        Certificadoconformidade certificadoconformidade = certificadoconformidadeMapper.toEntity(certificadoconformidadeDTO);
        certificadoconformidade = certificadoconformidadeRepository.save(certificadoconformidade);
        CertificadoconformidadeDTO result = certificadoconformidadeMapper.toDto(certificadoconformidade);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, certificadoconformidadeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /certificadoconformidades : get all the certificadoconformidades.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of certificadoconformidades in body
     */
    @GetMapping("/certificadoconformidades")
    @Timed
    public List<CertificadoconformidadeDTO> getAllCertificadoconformidades() {
        log.debug("REST request to get all Certificadoconformidades");
        List<Certificadoconformidade> certificadoconformidades = certificadoconformidadeRepository.findAllWithEagerRelationships();
        return certificadoconformidadeMapper.toDto(certificadoconformidades);
    }

    /**
     * GET  /certificadoconformidades/:id : get the "id" certificadoconformidade.
     *
     * @param id the id of the certificadoconformidadeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the certificadoconformidadeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/certificadoconformidades/{id}")
    @Timed
    public ResponseEntity<CertificadoconformidadeDTO> getCertificadoconformidade(@PathVariable Long id) {
        log.debug("REST request to get Certificadoconformidade : {}", id);
        Certificadoconformidade certificadoconformidade = certificadoconformidadeRepository.findOneWithEagerRelationships(id);
        CertificadoconformidadeDTO certificadoconformidadeDTO = certificadoconformidadeMapper.toDto(certificadoconformidade);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(certificadoconformidadeDTO));
    }

    /**
     * DELETE  /certificadoconformidades/:id : delete the "id" certificadoconformidade.
     *
     * @param id the id of the certificadoconformidadeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/certificadoconformidades/{id}")
    @Timed
    public ResponseEntity<Void> deleteCertificadoconformidade(@PathVariable Long id) {
        log.debug("REST request to delete Certificadoconformidade : {}", id);
        certificadoconformidadeRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
