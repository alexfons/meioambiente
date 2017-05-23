package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Aditivocontrato;

import br.com.homemade.repository.AditivocontratoRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.AditivocontratoDTO;
import br.com.homemade.service.mapper.AditivocontratoMapper;
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
 * REST controller for managing Aditivocontrato.
 */
@RestController
@RequestMapping("/api")
public class AditivocontratoResource {

    private final Logger log = LoggerFactory.getLogger(AditivocontratoResource.class);

    private static final String ENTITY_NAME = "aditivocontrato";
        
    private final AditivocontratoRepository aditivocontratoRepository;

    private final AditivocontratoMapper aditivocontratoMapper;

    public AditivocontratoResource(AditivocontratoRepository aditivocontratoRepository, AditivocontratoMapper aditivocontratoMapper) {
        this.aditivocontratoRepository = aditivocontratoRepository;
        this.aditivocontratoMapper = aditivocontratoMapper;
    }

    /**
     * POST  /aditivocontratoes : Create a new aditivocontrato.
     *
     * @param aditivocontratoDTO the aditivocontratoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new aditivocontratoDTO, or with status 400 (Bad Request) if the aditivocontrato has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/aditivocontratoes")
    @Timed
    public ResponseEntity<AditivocontratoDTO> createAditivocontrato(@RequestBody AditivocontratoDTO aditivocontratoDTO) throws URISyntaxException {
        log.debug("REST request to save Aditivocontrato : {}", aditivocontratoDTO);
        if (aditivocontratoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new aditivocontrato cannot already have an ID")).body(null);
        }
        Aditivocontrato aditivocontrato = aditivocontratoMapper.toEntity(aditivocontratoDTO);
        aditivocontrato = aditivocontratoRepository.save(aditivocontrato);
        AditivocontratoDTO result = aditivocontratoMapper.toDto(aditivocontrato);
        return ResponseEntity.created(new URI("/api/aditivocontratoes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /aditivocontratoes : Updates an existing aditivocontrato.
     *
     * @param aditivocontratoDTO the aditivocontratoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated aditivocontratoDTO,
     * or with status 400 (Bad Request) if the aditivocontratoDTO is not valid,
     * or with status 500 (Internal Server Error) if the aditivocontratoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/aditivocontratoes")
    @Timed
    public ResponseEntity<AditivocontratoDTO> updateAditivocontrato(@RequestBody AditivocontratoDTO aditivocontratoDTO) throws URISyntaxException {
        log.debug("REST request to update Aditivocontrato : {}", aditivocontratoDTO);
        if (aditivocontratoDTO.getId() == null) {
            return createAditivocontrato(aditivocontratoDTO);
        }
        Aditivocontrato aditivocontrato = aditivocontratoMapper.toEntity(aditivocontratoDTO);
        aditivocontrato = aditivocontratoRepository.save(aditivocontrato);
        AditivocontratoDTO result = aditivocontratoMapper.toDto(aditivocontrato);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, aditivocontratoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /aditivocontratoes : get all the aditivocontratoes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of aditivocontratoes in body
     */
    @GetMapping("/aditivocontratoes")
    @Timed
    public List<AditivocontratoDTO> getAllAditivocontratoes() {
        log.debug("REST request to get all Aditivocontratoes");
        List<Aditivocontrato> aditivocontratoes = aditivocontratoRepository.findAll();
        return aditivocontratoMapper.toDto(aditivocontratoes);
    }

    /**
     * GET  /aditivocontratoes/:id : get the "id" aditivocontrato.
     *
     * @param id the id of the aditivocontratoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the aditivocontratoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/aditivocontratoes/{id}")
    @Timed
    public ResponseEntity<AditivocontratoDTO> getAditivocontrato(@PathVariable Long id) {
        log.debug("REST request to get Aditivocontrato : {}", id);
        Aditivocontrato aditivocontrato = aditivocontratoRepository.findOne(id);
        AditivocontratoDTO aditivocontratoDTO = aditivocontratoMapper.toDto(aditivocontrato);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(aditivocontratoDTO));
    }

    /**
     * DELETE  /aditivocontratoes/:id : delete the "id" aditivocontrato.
     *
     * @param id the id of the aditivocontratoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/aditivocontratoes/{id}")
    @Timed
    public ResponseEntity<Void> deleteAditivocontrato(@PathVariable Long id) {
        log.debug("REST request to delete Aditivocontrato : {}", id);
        aditivocontratoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
