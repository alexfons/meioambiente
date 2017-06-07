package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Fatura;

import br.com.homemade.repository.FaturaRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.FaturaDTO;
import br.com.homemade.service.mapper.FaturaMapper;
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
 * REST controller for managing Fatura.
 */
@RestController
@RequestMapping("/api")
public class FaturaResource {

    private final Logger log = LoggerFactory.getLogger(FaturaResource.class);

    private static final String ENTITY_NAME = "fatura";
        
    private final FaturaRepository faturaRepository;

    private final FaturaMapper faturaMapper;

    public FaturaResource(FaturaRepository faturaRepository, FaturaMapper faturaMapper) {
        this.faturaRepository = faturaRepository;
        this.faturaMapper = faturaMapper;
    }

    /**
     * POST  /faturas : Create a new fatura.
     *
     * @param faturaDTO the faturaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new faturaDTO, or with status 400 (Bad Request) if the fatura has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/faturas")
    @Timed
    public ResponseEntity<FaturaDTO> createFatura(@RequestBody FaturaDTO faturaDTO) throws URISyntaxException {
        log.debug("REST request to save Fatura : {}", faturaDTO);
        if (faturaDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new fatura cannot already have an ID")).body(null);
        }
        Fatura fatura = faturaMapper.toEntity(faturaDTO);
        fatura = faturaRepository.save(fatura);
        FaturaDTO result = faturaMapper.toDto(fatura);
        return ResponseEntity.created(new URI("/api/faturas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /faturas : Updates an existing fatura.
     *
     * @param faturaDTO the faturaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated faturaDTO,
     * or with status 400 (Bad Request) if the faturaDTO is not valid,
     * or with status 500 (Internal Server Error) if the faturaDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/faturas")
    @Timed
    public ResponseEntity<FaturaDTO> updateFatura(@RequestBody FaturaDTO faturaDTO) throws URISyntaxException {
        log.debug("REST request to update Fatura : {}", faturaDTO);
        if (faturaDTO.getId() == null) {
            return createFatura(faturaDTO);
        }
        Fatura fatura = faturaMapper.toEntity(faturaDTO);
        fatura = faturaRepository.save(fatura);
        FaturaDTO result = faturaMapper.toDto(fatura);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, faturaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /faturas : get all the faturas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of faturas in body
     */
    @GetMapping("/faturas")
    @Timed
    public List<FaturaDTO> getAllFaturas() {
        log.debug("REST request to get all Faturas");
        List<Fatura> faturas = faturaRepository.findAll();
        return faturaMapper.toDto(faturas);
    }

    /**
     * GET  /faturas/:id : get the "id" fatura.
     *
     * @param id the id of the faturaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the faturaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/faturas/{id}")
    @Timed
    public ResponseEntity<FaturaDTO> getFatura(@PathVariable Long id) {
        log.debug("REST request to get Fatura : {}", id);
        Fatura fatura = faturaRepository.findOne(id);
        FaturaDTO faturaDTO = faturaMapper.toDto(fatura);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(faturaDTO));
    }

    /**
     * DELETE  /faturas/:id : delete the "id" fatura.
     *
     * @param id the id of the faturaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/faturas/{id}")
    @Timed
    public ResponseEntity<Void> deleteFatura(@PathVariable Long id) {
        log.debug("REST request to delete Fatura : {}", id);
        faturaRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
