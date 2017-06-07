package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Faturacontrato;

import br.com.homemade.repository.FaturacontratoRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.FaturacontratoDTO;
import br.com.homemade.service.mapper.FaturacontratoMapper;
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
 * REST controller for managing Faturacontrato.
 */
@RestController
@RequestMapping("/api")
public class FaturacontratoResource {

    private final Logger log = LoggerFactory.getLogger(FaturacontratoResource.class);

    private static final String ENTITY_NAME = "faturacontrato";
        
    private final FaturacontratoRepository faturacontratoRepository;

    private final FaturacontratoMapper faturacontratoMapper;

    public FaturacontratoResource(FaturacontratoRepository faturacontratoRepository, FaturacontratoMapper faturacontratoMapper) {
        this.faturacontratoRepository = faturacontratoRepository;
        this.faturacontratoMapper = faturacontratoMapper;
    }

    /**
     * POST  /faturacontratoes : Create a new faturacontrato.
     *
     * @param faturacontratoDTO the faturacontratoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new faturacontratoDTO, or with status 400 (Bad Request) if the faturacontrato has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/faturacontratoes")
    @Timed
    public ResponseEntity<FaturacontratoDTO> createFaturacontrato(@RequestBody FaturacontratoDTO faturacontratoDTO) throws URISyntaxException {
        log.debug("REST request to save Faturacontrato : {}", faturacontratoDTO);
        if (faturacontratoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new faturacontrato cannot already have an ID")).body(null);
        }
        Faturacontrato faturacontrato = faturacontratoMapper.toEntity(faturacontratoDTO);
        faturacontrato = faturacontratoRepository.save(faturacontrato);
        FaturacontratoDTO result = faturacontratoMapper.toDto(faturacontrato);
        return ResponseEntity.created(new URI("/api/faturacontratoes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /faturacontratoes : Updates an existing faturacontrato.
     *
     * @param faturacontratoDTO the faturacontratoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated faturacontratoDTO,
     * or with status 400 (Bad Request) if the faturacontratoDTO is not valid,
     * or with status 500 (Internal Server Error) if the faturacontratoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/faturacontratoes")
    @Timed
    public ResponseEntity<FaturacontratoDTO> updateFaturacontrato(@RequestBody FaturacontratoDTO faturacontratoDTO) throws URISyntaxException {
        log.debug("REST request to update Faturacontrato : {}", faturacontratoDTO);
        if (faturacontratoDTO.getId() == null) {
            return createFaturacontrato(faturacontratoDTO);
        }
        Faturacontrato faturacontrato = faturacontratoMapper.toEntity(faturacontratoDTO);
        faturacontrato = faturacontratoRepository.save(faturacontrato);
        FaturacontratoDTO result = faturacontratoMapper.toDto(faturacontrato);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, faturacontratoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /faturacontratoes : get all the faturacontratoes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of faturacontratoes in body
     */
    @GetMapping("/faturacontratoes")
    @Timed
    public List<FaturacontratoDTO> getAllFaturacontratoes() {
        log.debug("REST request to get all Faturacontratoes");
        List<Faturacontrato> faturacontratoes = faturacontratoRepository.findAll();
        return faturacontratoMapper.toDto(faturacontratoes);
    }

    /**
     * GET  /faturacontratoes/:id : get the "id" faturacontrato.
     *
     * @param id the id of the faturacontratoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the faturacontratoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/faturacontratoes/{id}")
    @Timed
    public ResponseEntity<FaturacontratoDTO> getFaturacontrato(@PathVariable Long id) {
        log.debug("REST request to get Faturacontrato : {}", id);
        Faturacontrato faturacontrato = faturacontratoRepository.findOne(id);
        FaturacontratoDTO faturacontratoDTO = faturacontratoMapper.toDto(faturacontrato);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(faturacontratoDTO));
    }

    /**
     * DELETE  /faturacontratoes/:id : delete the "id" faturacontrato.
     *
     * @param id the id of the faturacontratoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/faturacontratoes/{id}")
    @Timed
    public ResponseEntity<Void> deleteFaturacontrato(@PathVariable Long id) {
        log.debug("REST request to delete Faturacontrato : {}", id);
        faturacontratoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
