package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Tipocertificadoconformidade;

import br.com.homemade.repository.TipocertificadoconformidadeRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.TipocertificadoconformidadeDTO;
import br.com.homemade.service.mapper.TipocertificadoconformidadeMapper;
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
 * REST controller for managing Tipocertificadoconformidade.
 */
@RestController
@RequestMapping("/api")
public class TipocertificadoconformidadeResource {

    private final Logger log = LoggerFactory.getLogger(TipocertificadoconformidadeResource.class);

    private static final String ENTITY_NAME = "tipocertificadoconformidade";
        
    private final TipocertificadoconformidadeRepository tipocertificadoconformidadeRepository;

    private final TipocertificadoconformidadeMapper tipocertificadoconformidadeMapper;

    public TipocertificadoconformidadeResource(TipocertificadoconformidadeRepository tipocertificadoconformidadeRepository, TipocertificadoconformidadeMapper tipocertificadoconformidadeMapper) {
        this.tipocertificadoconformidadeRepository = tipocertificadoconformidadeRepository;
        this.tipocertificadoconformidadeMapper = tipocertificadoconformidadeMapper;
    }

    /**
     * POST  /tipocertificadoconformidades : Create a new tipocertificadoconformidade.
     *
     * @param tipocertificadoconformidadeDTO the tipocertificadoconformidadeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipocertificadoconformidadeDTO, or with status 400 (Bad Request) if the tipocertificadoconformidade has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tipocertificadoconformidades")
    @Timed
    public ResponseEntity<TipocertificadoconformidadeDTO> createTipocertificadoconformidade(@RequestBody TipocertificadoconformidadeDTO tipocertificadoconformidadeDTO) throws URISyntaxException {
        log.debug("REST request to save Tipocertificadoconformidade : {}", tipocertificadoconformidadeDTO);
        if (tipocertificadoconformidadeDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new tipocertificadoconformidade cannot already have an ID")).body(null);
        }
        Tipocertificadoconformidade tipocertificadoconformidade = tipocertificadoconformidadeMapper.toEntity(tipocertificadoconformidadeDTO);
        tipocertificadoconformidade = tipocertificadoconformidadeRepository.save(tipocertificadoconformidade);
        TipocertificadoconformidadeDTO result = tipocertificadoconformidadeMapper.toDto(tipocertificadoconformidade);
        return ResponseEntity.created(new URI("/api/tipocertificadoconformidades/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tipocertificadoconformidades : Updates an existing tipocertificadoconformidade.
     *
     * @param tipocertificadoconformidadeDTO the tipocertificadoconformidadeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipocertificadoconformidadeDTO,
     * or with status 400 (Bad Request) if the tipocertificadoconformidadeDTO is not valid,
     * or with status 500 (Internal Server Error) if the tipocertificadoconformidadeDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tipocertificadoconformidades")
    @Timed
    public ResponseEntity<TipocertificadoconformidadeDTO> updateTipocertificadoconformidade(@RequestBody TipocertificadoconformidadeDTO tipocertificadoconformidadeDTO) throws URISyntaxException {
        log.debug("REST request to update Tipocertificadoconformidade : {}", tipocertificadoconformidadeDTO);
        if (tipocertificadoconformidadeDTO.getId() == null) {
            return createTipocertificadoconformidade(tipocertificadoconformidadeDTO);
        }
        Tipocertificadoconformidade tipocertificadoconformidade = tipocertificadoconformidadeMapper.toEntity(tipocertificadoconformidadeDTO);
        tipocertificadoconformidade = tipocertificadoconformidadeRepository.save(tipocertificadoconformidade);
        TipocertificadoconformidadeDTO result = tipocertificadoconformidadeMapper.toDto(tipocertificadoconformidade);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipocertificadoconformidadeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tipocertificadoconformidades : get all the tipocertificadoconformidades.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tipocertificadoconformidades in body
     */
    @GetMapping("/tipocertificadoconformidades")
    @Timed
    public List<TipocertificadoconformidadeDTO> getAllTipocertificadoconformidades() {
        log.debug("REST request to get all Tipocertificadoconformidades");
        List<Tipocertificadoconformidade> tipocertificadoconformidades = tipocertificadoconformidadeRepository.findAll();
        return tipocertificadoconformidadeMapper.toDto(tipocertificadoconformidades);
    }

    /**
     * GET  /tipocertificadoconformidades/:id : get the "id" tipocertificadoconformidade.
     *
     * @param id the id of the tipocertificadoconformidadeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipocertificadoconformidadeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tipocertificadoconformidades/{id}")
    @Timed
    public ResponseEntity<TipocertificadoconformidadeDTO> getTipocertificadoconformidade(@PathVariable Long id) {
        log.debug("REST request to get Tipocertificadoconformidade : {}", id);
        Tipocertificadoconformidade tipocertificadoconformidade = tipocertificadoconformidadeRepository.findOne(id);
        TipocertificadoconformidadeDTO tipocertificadoconformidadeDTO = tipocertificadoconformidadeMapper.toDto(tipocertificadoconformidade);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tipocertificadoconformidadeDTO));
    }

    /**
     * DELETE  /tipocertificadoconformidades/:id : delete the "id" tipocertificadoconformidade.
     *
     * @param id the id of the tipocertificadoconformidadeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tipocertificadoconformidades/{id}")
    @Timed
    public ResponseEntity<Void> deleteTipocertificadoconformidade(@PathVariable Long id) {
        log.debug("REST request to delete Tipocertificadoconformidade : {}", id);
        tipocertificadoconformidadeRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
