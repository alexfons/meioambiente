package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Tipoadministrativo;

import br.com.homemade.repository.TipoadministrativoRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.TipoadministrativoDTO;
import br.com.homemade.service.mapper.TipoadministrativoMapper;
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
 * REST controller for managing Tipoadministrativo.
 */
@RestController
@RequestMapping("/api")
public class TipoadministrativoResource {

    private final Logger log = LoggerFactory.getLogger(TipoadministrativoResource.class);

    private static final String ENTITY_NAME = "tipoadministrativo";
        
    private final TipoadministrativoRepository tipoadministrativoRepository;

    private final TipoadministrativoMapper tipoadministrativoMapper;

    public TipoadministrativoResource(TipoadministrativoRepository tipoadministrativoRepository, TipoadministrativoMapper tipoadministrativoMapper) {
        this.tipoadministrativoRepository = tipoadministrativoRepository;
        this.tipoadministrativoMapper = tipoadministrativoMapper;
    }

    /**
     * POST  /tipoadministrativos : Create a new tipoadministrativo.
     *
     * @param tipoadministrativoDTO the tipoadministrativoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipoadministrativoDTO, or with status 400 (Bad Request) if the tipoadministrativo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tipoadministrativos")
    @Timed
    public ResponseEntity<TipoadministrativoDTO> createTipoadministrativo(@RequestBody TipoadministrativoDTO tipoadministrativoDTO) throws URISyntaxException {
        log.debug("REST request to save Tipoadministrativo : {}", tipoadministrativoDTO);
        if (tipoadministrativoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new tipoadministrativo cannot already have an ID")).body(null);
        }
        Tipoadministrativo tipoadministrativo = tipoadministrativoMapper.toEntity(tipoadministrativoDTO);
        tipoadministrativo = tipoadministrativoRepository.save(tipoadministrativo);
        TipoadministrativoDTO result = tipoadministrativoMapper.toDto(tipoadministrativo);
        return ResponseEntity.created(new URI("/api/tipoadministrativos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tipoadministrativos : Updates an existing tipoadministrativo.
     *
     * @param tipoadministrativoDTO the tipoadministrativoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipoadministrativoDTO,
     * or with status 400 (Bad Request) if the tipoadministrativoDTO is not valid,
     * or with status 500 (Internal Server Error) if the tipoadministrativoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tipoadministrativos")
    @Timed
    public ResponseEntity<TipoadministrativoDTO> updateTipoadministrativo(@RequestBody TipoadministrativoDTO tipoadministrativoDTO) throws URISyntaxException {
        log.debug("REST request to update Tipoadministrativo : {}", tipoadministrativoDTO);
        if (tipoadministrativoDTO.getId() == null) {
            return createTipoadministrativo(tipoadministrativoDTO);
        }
        Tipoadministrativo tipoadministrativo = tipoadministrativoMapper.toEntity(tipoadministrativoDTO);
        tipoadministrativo = tipoadministrativoRepository.save(tipoadministrativo);
        TipoadministrativoDTO result = tipoadministrativoMapper.toDto(tipoadministrativo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipoadministrativoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tipoadministrativos : get all the tipoadministrativos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tipoadministrativos in body
     */
    @GetMapping("/tipoadministrativos")
    @Timed
    public List<TipoadministrativoDTO> getAllTipoadministrativos() {
        log.debug("REST request to get all Tipoadministrativos");
        List<Tipoadministrativo> tipoadministrativos = tipoadministrativoRepository.findAll();
        return tipoadministrativoMapper.toDto(tipoadministrativos);
    }

    /**
     * GET  /tipoadministrativos/:id : get the "id" tipoadministrativo.
     *
     * @param id the id of the tipoadministrativoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipoadministrativoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tipoadministrativos/{id}")
    @Timed
    public ResponseEntity<TipoadministrativoDTO> getTipoadministrativo(@PathVariable Long id) {
        log.debug("REST request to get Tipoadministrativo : {}", id);
        Tipoadministrativo tipoadministrativo = tipoadministrativoRepository.findOne(id);
        TipoadministrativoDTO tipoadministrativoDTO = tipoadministrativoMapper.toDto(tipoadministrativo);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tipoadministrativoDTO));
    }

    /**
     * DELETE  /tipoadministrativos/:id : delete the "id" tipoadministrativo.
     *
     * @param id the id of the tipoadministrativoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tipoadministrativos/{id}")
    @Timed
    public ResponseEntity<Void> deleteTipoadministrativo(@PathVariable Long id) {
        log.debug("REST request to delete Tipoadministrativo : {}", id);
        tipoadministrativoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
