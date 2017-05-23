package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Fiscal;

import br.com.homemade.repository.FiscalRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.FiscalDTO;
import br.com.homemade.service.mapper.FiscalMapper;
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
 * REST controller for managing Fiscal.
 */
@RestController
@RequestMapping("/api")
public class FiscalResource {

    private final Logger log = LoggerFactory.getLogger(FiscalResource.class);

    private static final String ENTITY_NAME = "fiscal";
        
    private final FiscalRepository fiscalRepository;

    private final FiscalMapper fiscalMapper;

    public FiscalResource(FiscalRepository fiscalRepository, FiscalMapper fiscalMapper) {
        this.fiscalRepository = fiscalRepository;
        this.fiscalMapper = fiscalMapper;
    }

    /**
     * POST  /fiscals : Create a new fiscal.
     *
     * @param fiscalDTO the fiscalDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fiscalDTO, or with status 400 (Bad Request) if the fiscal has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/fiscals")
    @Timed
    public ResponseEntity<FiscalDTO> createFiscal(@RequestBody FiscalDTO fiscalDTO) throws URISyntaxException {
        log.debug("REST request to save Fiscal : {}", fiscalDTO);
        if (fiscalDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new fiscal cannot already have an ID")).body(null);
        }
        Fiscal fiscal = fiscalMapper.toEntity(fiscalDTO);
        fiscal = fiscalRepository.save(fiscal);
        FiscalDTO result = fiscalMapper.toDto(fiscal);
        return ResponseEntity.created(new URI("/api/fiscals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /fiscals : Updates an existing fiscal.
     *
     * @param fiscalDTO the fiscalDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fiscalDTO,
     * or with status 400 (Bad Request) if the fiscalDTO is not valid,
     * or with status 500 (Internal Server Error) if the fiscalDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/fiscals")
    @Timed
    public ResponseEntity<FiscalDTO> updateFiscal(@RequestBody FiscalDTO fiscalDTO) throws URISyntaxException {
        log.debug("REST request to update Fiscal : {}", fiscalDTO);
        if (fiscalDTO.getId() == null) {
            return createFiscal(fiscalDTO);
        }
        Fiscal fiscal = fiscalMapper.toEntity(fiscalDTO);
        fiscal = fiscalRepository.save(fiscal);
        FiscalDTO result = fiscalMapper.toDto(fiscal);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fiscalDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /fiscals : get all the fiscals.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of fiscals in body
     */
    @GetMapping("/fiscals")
    @Timed
    public List<FiscalDTO> getAllFiscals() {
        log.debug("REST request to get all Fiscals");
        List<Fiscal> fiscals = fiscalRepository.findAll();
        return fiscalMapper.toDto(fiscals);
    }

    /**
     * GET  /fiscals/:id : get the "id" fiscal.
     *
     * @param id the id of the fiscalDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fiscalDTO, or with status 404 (Not Found)
     */
    @GetMapping("/fiscals/{id}")
    @Timed
    public ResponseEntity<FiscalDTO> getFiscal(@PathVariable Long id) {
        log.debug("REST request to get Fiscal : {}", id);
        Fiscal fiscal = fiscalRepository.findOne(id);
        FiscalDTO fiscalDTO = fiscalMapper.toDto(fiscal);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(fiscalDTO));
    }

    /**
     * DELETE  /fiscals/:id : delete the "id" fiscal.
     *
     * @param id the id of the fiscalDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/fiscals/{id}")
    @Timed
    public ResponseEntity<Void> deleteFiscal(@PathVariable Long id) {
        log.debug("REST request to delete Fiscal : {}", id);
        fiscalRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
