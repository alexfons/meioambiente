package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.TipocertifConfor;

import br.com.homemade.repository.TipocertifConforRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.TipocertifConforDTO;
import br.com.homemade.service.mapper.TipocertifConforMapper;
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
 * REST controller for managing TipocertifConfor.
 */
@RestController
@RequestMapping("/api")
public class TipocertifConforResource {

    private final Logger log = LoggerFactory.getLogger(TipocertifConforResource.class);

    private static final String ENTITY_NAME = "tipocertifConfor";
        
    private final TipocertifConforRepository tipocertifConforRepository;

    private final TipocertifConforMapper tipocertifConforMapper;

    public TipocertifConforResource(TipocertifConforRepository tipocertifConforRepository, TipocertifConforMapper tipocertifConforMapper) {
        this.tipocertifConforRepository = tipocertifConforRepository;
        this.tipocertifConforMapper = tipocertifConforMapper;
    }

    /**
     * POST  /tipocertif-confors : Create a new tipocertifConfor.
     *
     * @param tipocertifConforDTO the tipocertifConforDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipocertifConforDTO, or with status 400 (Bad Request) if the tipocertifConfor has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tipocertif-confors")
    @Timed
    public ResponseEntity<TipocertifConforDTO> createTipocertifConfor(@RequestBody TipocertifConforDTO tipocertifConforDTO) throws URISyntaxException {
        log.debug("REST request to save TipocertifConfor : {}", tipocertifConforDTO);
        if (tipocertifConforDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new tipocertifConfor cannot already have an ID")).body(null);
        }
        TipocertifConfor tipocertifConfor = tipocertifConforMapper.toEntity(tipocertifConforDTO);
        tipocertifConfor = tipocertifConforRepository.save(tipocertifConfor);
        TipocertifConforDTO result = tipocertifConforMapper.toDto(tipocertifConfor);
        return ResponseEntity.created(new URI("/api/tipocertif-confors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tipocertif-confors : Updates an existing tipocertifConfor.
     *
     * @param tipocertifConforDTO the tipocertifConforDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipocertifConforDTO,
     * or with status 400 (Bad Request) if the tipocertifConforDTO is not valid,
     * or with status 500 (Internal Server Error) if the tipocertifConforDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tipocertif-confors")
    @Timed
    public ResponseEntity<TipocertifConforDTO> updateTipocertifConfor(@RequestBody TipocertifConforDTO tipocertifConforDTO) throws URISyntaxException {
        log.debug("REST request to update TipocertifConfor : {}", tipocertifConforDTO);
        if (tipocertifConforDTO.getId() == null) {
            return createTipocertifConfor(tipocertifConforDTO);
        }
        TipocertifConfor tipocertifConfor = tipocertifConforMapper.toEntity(tipocertifConforDTO);
        tipocertifConfor = tipocertifConforRepository.save(tipocertifConfor);
        TipocertifConforDTO result = tipocertifConforMapper.toDto(tipocertifConfor);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipocertifConforDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tipocertif-confors : get all the tipocertifConfors.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tipocertifConfors in body
     */
    @GetMapping("/tipocertif-confors")
    @Timed
    public List<TipocertifConforDTO> getAllTipocertifConfors() {
        log.debug("REST request to get all TipocertifConfors");
        List<TipocertifConfor> tipocertifConfors = tipocertifConforRepository.findAll();
        return tipocertifConforMapper.toDto(tipocertifConfors);
    }

    /**
     * GET  /tipocertif-confors/:id : get the "id" tipocertifConfor.
     *
     * @param id the id of the tipocertifConforDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipocertifConforDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tipocertif-confors/{id}")
    @Timed
    public ResponseEntity<TipocertifConforDTO> getTipocertifConfor(@PathVariable Long id) {
        log.debug("REST request to get TipocertifConfor : {}", id);
        TipocertifConfor tipocertifConfor = tipocertifConforRepository.findOne(id);
        TipocertifConforDTO tipocertifConforDTO = tipocertifConforMapper.toDto(tipocertifConfor);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tipocertifConforDTO));
    }

    /**
     * DELETE  /tipocertif-confors/:id : delete the "id" tipocertifConfor.
     *
     * @param id the id of the tipocertifConforDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tipocertif-confors/{id}")
    @Timed
    public ResponseEntity<Void> deleteTipocertifConfor(@PathVariable Long id) {
        log.debug("REST request to delete TipocertifConfor : {}", id);
        tipocertifConforRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
