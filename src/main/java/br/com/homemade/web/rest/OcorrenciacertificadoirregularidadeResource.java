package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Ocorrenciacertificadoirregularidade;

import br.com.homemade.repository.OcorrenciacertificadoirregularidadeRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.OcorrenciacertificadoirregularidadeDTO;
import br.com.homemade.service.mapper.OcorrenciacertificadoirregularidadeMapper;
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
 * REST controller for managing Ocorrenciacertificadoirregularidade.
 */
@RestController
@RequestMapping("/api")
public class OcorrenciacertificadoirregularidadeResource {

    private final Logger log = LoggerFactory.getLogger(OcorrenciacertificadoirregularidadeResource.class);

    private static final String ENTITY_NAME = "ocorrenciacertificadoirregularidade";
        
    private final OcorrenciacertificadoirregularidadeRepository ocorrenciacertificadoirregularidadeRepository;

    private final OcorrenciacertificadoirregularidadeMapper ocorrenciacertificadoirregularidadeMapper;

    public OcorrenciacertificadoirregularidadeResource(OcorrenciacertificadoirregularidadeRepository ocorrenciacertificadoirregularidadeRepository, OcorrenciacertificadoirregularidadeMapper ocorrenciacertificadoirregularidadeMapper) {
        this.ocorrenciacertificadoirregularidadeRepository = ocorrenciacertificadoirregularidadeRepository;
        this.ocorrenciacertificadoirregularidadeMapper = ocorrenciacertificadoirregularidadeMapper;
    }

    /**
     * POST  /ocorrenciacertificadoirregularidades : Create a new ocorrenciacertificadoirregularidade.
     *
     * @param ocorrenciacertificadoirregularidadeDTO the ocorrenciacertificadoirregularidadeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ocorrenciacertificadoirregularidadeDTO, or with status 400 (Bad Request) if the ocorrenciacertificadoirregularidade has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ocorrenciacertificadoirregularidades")
    @Timed
    public ResponseEntity<OcorrenciacertificadoirregularidadeDTO> createOcorrenciacertificadoirregularidade(@RequestBody OcorrenciacertificadoirregularidadeDTO ocorrenciacertificadoirregularidadeDTO) throws URISyntaxException {
        log.debug("REST request to save Ocorrenciacertificadoirregularidade : {}", ocorrenciacertificadoirregularidadeDTO);
        if (ocorrenciacertificadoirregularidadeDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new ocorrenciacertificadoirregularidade cannot already have an ID")).body(null);
        }
        Ocorrenciacertificadoirregularidade ocorrenciacertificadoirregularidade = ocorrenciacertificadoirregularidadeMapper.toEntity(ocorrenciacertificadoirregularidadeDTO);
        ocorrenciacertificadoirregularidade = ocorrenciacertificadoirregularidadeRepository.save(ocorrenciacertificadoirregularidade);
        OcorrenciacertificadoirregularidadeDTO result = ocorrenciacertificadoirregularidadeMapper.toDto(ocorrenciacertificadoirregularidade);
        return ResponseEntity.created(new URI("/api/ocorrenciacertificadoirregularidades/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ocorrenciacertificadoirregularidades : Updates an existing ocorrenciacertificadoirregularidade.
     *
     * @param ocorrenciacertificadoirregularidadeDTO the ocorrenciacertificadoirregularidadeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ocorrenciacertificadoirregularidadeDTO,
     * or with status 400 (Bad Request) if the ocorrenciacertificadoirregularidadeDTO is not valid,
     * or with status 500 (Internal Server Error) if the ocorrenciacertificadoirregularidadeDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ocorrenciacertificadoirregularidades")
    @Timed
    public ResponseEntity<OcorrenciacertificadoirregularidadeDTO> updateOcorrenciacertificadoirregularidade(@RequestBody OcorrenciacertificadoirregularidadeDTO ocorrenciacertificadoirregularidadeDTO) throws URISyntaxException {
        log.debug("REST request to update Ocorrenciacertificadoirregularidade : {}", ocorrenciacertificadoirregularidadeDTO);
        if (ocorrenciacertificadoirregularidadeDTO.getId() == null) {
            return createOcorrenciacertificadoirregularidade(ocorrenciacertificadoirregularidadeDTO);
        }
        Ocorrenciacertificadoirregularidade ocorrenciacertificadoirregularidade = ocorrenciacertificadoirregularidadeMapper.toEntity(ocorrenciacertificadoirregularidadeDTO);
        ocorrenciacertificadoirregularidade = ocorrenciacertificadoirregularidadeRepository.save(ocorrenciacertificadoirregularidade);
        OcorrenciacertificadoirregularidadeDTO result = ocorrenciacertificadoirregularidadeMapper.toDto(ocorrenciacertificadoirregularidade);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ocorrenciacertificadoirregularidadeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ocorrenciacertificadoirregularidades : get all the ocorrenciacertificadoirregularidades.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of ocorrenciacertificadoirregularidades in body
     */
    @GetMapping("/ocorrenciacertificadoirregularidades")
    @Timed
    public List<OcorrenciacertificadoirregularidadeDTO> getAllOcorrenciacertificadoirregularidades() {
        log.debug("REST request to get all Ocorrenciacertificadoirregularidades");
        List<Ocorrenciacertificadoirregularidade> ocorrenciacertificadoirregularidades = ocorrenciacertificadoirregularidadeRepository.findAll();
        return ocorrenciacertificadoirregularidadeMapper.toDto(ocorrenciacertificadoirregularidades);
    }

    /**
     * GET  /ocorrenciacertificadoirregularidades/:id : get the "id" ocorrenciacertificadoirregularidade.
     *
     * @param id the id of the ocorrenciacertificadoirregularidadeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ocorrenciacertificadoirregularidadeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ocorrenciacertificadoirregularidades/{id}")
    @Timed
    public ResponseEntity<OcorrenciacertificadoirregularidadeDTO> getOcorrenciacertificadoirregularidade(@PathVariable Long id) {
        log.debug("REST request to get Ocorrenciacertificadoirregularidade : {}", id);
        Ocorrenciacertificadoirregularidade ocorrenciacertificadoirregularidade = ocorrenciacertificadoirregularidadeRepository.findOne(id);
        OcorrenciacertificadoirregularidadeDTO ocorrenciacertificadoirregularidadeDTO = ocorrenciacertificadoirregularidadeMapper.toDto(ocorrenciacertificadoirregularidade);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(ocorrenciacertificadoirregularidadeDTO));
    }

    /**
     * DELETE  /ocorrenciacertificadoirregularidades/:id : delete the "id" ocorrenciacertificadoirregularidade.
     *
     * @param id the id of the ocorrenciacertificadoirregularidadeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ocorrenciacertificadoirregularidades/{id}")
    @Timed
    public ResponseEntity<Void> deleteOcorrenciacertificadoirregularidade(@PathVariable Long id) {
        log.debug("REST request to delete Ocorrenciacertificadoirregularidade : {}", id);
        ocorrenciacertificadoirregularidadeRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
