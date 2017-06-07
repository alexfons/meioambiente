package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Quantitativo;

import br.com.homemade.repository.QuantitativoRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.QuantitativoDTO;
import br.com.homemade.service.mapper.QuantitativoMapper;
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
 * REST controller for managing Quantitativo.
 */
@RestController
@RequestMapping("/api")
public class QuantitativoResource {

    private final Logger log = LoggerFactory.getLogger(QuantitativoResource.class);

    private static final String ENTITY_NAME = "quantitativo";
        
    private final QuantitativoRepository quantitativoRepository;

    private final QuantitativoMapper quantitativoMapper;

    public QuantitativoResource(QuantitativoRepository quantitativoRepository, QuantitativoMapper quantitativoMapper) {
        this.quantitativoRepository = quantitativoRepository;
        this.quantitativoMapper = quantitativoMapper;
    }

    /**
     * POST  /quantitativos : Create a new quantitativo.
     *
     * @param quantitativoDTO the quantitativoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new quantitativoDTO, or with status 400 (Bad Request) if the quantitativo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/quantitativos")
    @Timed
    public ResponseEntity<QuantitativoDTO> createQuantitativo(@RequestBody QuantitativoDTO quantitativoDTO) throws URISyntaxException {
        log.debug("REST request to save Quantitativo : {}", quantitativoDTO);
        if (quantitativoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new quantitativo cannot already have an ID")).body(null);
        }
        Quantitativo quantitativo = quantitativoMapper.toEntity(quantitativoDTO);
        quantitativo = quantitativoRepository.save(quantitativo);
        QuantitativoDTO result = quantitativoMapper.toDto(quantitativo);
        return ResponseEntity.created(new URI("/api/quantitativos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /quantitativos : Updates an existing quantitativo.
     *
     * @param quantitativoDTO the quantitativoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated quantitativoDTO,
     * or with status 400 (Bad Request) if the quantitativoDTO is not valid,
     * or with status 500 (Internal Server Error) if the quantitativoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/quantitativos")
    @Timed
    public ResponseEntity<QuantitativoDTO> updateQuantitativo(@RequestBody QuantitativoDTO quantitativoDTO) throws URISyntaxException {
        log.debug("REST request to update Quantitativo : {}", quantitativoDTO);
        if (quantitativoDTO.getId() == null) {
            return createQuantitativo(quantitativoDTO);
        }
        Quantitativo quantitativo = quantitativoMapper.toEntity(quantitativoDTO);
        quantitativo = quantitativoRepository.save(quantitativo);
        QuantitativoDTO result = quantitativoMapper.toDto(quantitativo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, quantitativoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /quantitativos : get all the quantitativos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of quantitativos in body
     */
    @GetMapping("/quantitativos")
    @Timed
    public List<QuantitativoDTO> getAllQuantitativos() {
        log.debug("REST request to get all Quantitativos");
        List<Quantitativo> quantitativos = quantitativoRepository.findAll();
        return quantitativoMapper.toDto(quantitativos);
    }

    /**
     * GET  /quantitativos/:id : get the "id" quantitativo.
     *
     * @param id the id of the quantitativoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quantitativoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/quantitativos/{id}")
    @Timed
    public ResponseEntity<QuantitativoDTO> getQuantitativo(@PathVariable Long id) {
        log.debug("REST request to get Quantitativo : {}", id);
        Quantitativo quantitativo = quantitativoRepository.findOne(id);
        QuantitativoDTO quantitativoDTO = quantitativoMapper.toDto(quantitativo);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(quantitativoDTO));
    }

    /**
     * DELETE  /quantitativos/:id : delete the "id" quantitativo.
     *
     * @param id the id of the quantitativoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/quantitativos/{id}")
    @Timed
    public ResponseEntity<Void> deleteQuantitativo(@PathVariable Long id) {
        log.debug("REST request to delete Quantitativo : {}", id);
        quantitativoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
