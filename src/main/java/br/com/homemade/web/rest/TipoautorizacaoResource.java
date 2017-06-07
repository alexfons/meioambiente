package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Tipoautorizacao;

import br.com.homemade.repository.TipoautorizacaoRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.TipoautorizacaoDTO;
import br.com.homemade.service.mapper.TipoautorizacaoMapper;
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
 * REST controller for managing Tipoautorizacao.
 */
@RestController
@RequestMapping("/api")
public class TipoautorizacaoResource {

    private final Logger log = LoggerFactory.getLogger(TipoautorizacaoResource.class);

    private static final String ENTITY_NAME = "tipoautorizacao";
        
    private final TipoautorizacaoRepository tipoautorizacaoRepository;

    private final TipoautorizacaoMapper tipoautorizacaoMapper;

    public TipoautorizacaoResource(TipoautorizacaoRepository tipoautorizacaoRepository, TipoautorizacaoMapper tipoautorizacaoMapper) {
        this.tipoautorizacaoRepository = tipoautorizacaoRepository;
        this.tipoautorizacaoMapper = tipoautorizacaoMapper;
    }

    /**
     * POST  /tipoautorizacaos : Create a new tipoautorizacao.
     *
     * @param tipoautorizacaoDTO the tipoautorizacaoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipoautorizacaoDTO, or with status 400 (Bad Request) if the tipoautorizacao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tipoautorizacaos")
    @Timed
    public ResponseEntity<TipoautorizacaoDTO> createTipoautorizacao(@RequestBody TipoautorizacaoDTO tipoautorizacaoDTO) throws URISyntaxException {
        log.debug("REST request to save Tipoautorizacao : {}", tipoautorizacaoDTO);
        if (tipoautorizacaoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new tipoautorizacao cannot already have an ID")).body(null);
        }
        Tipoautorizacao tipoautorizacao = tipoautorizacaoMapper.toEntity(tipoautorizacaoDTO);
        tipoautorizacao = tipoautorizacaoRepository.save(tipoautorizacao);
        TipoautorizacaoDTO result = tipoautorizacaoMapper.toDto(tipoautorizacao);
        return ResponseEntity.created(new URI("/api/tipoautorizacaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tipoautorizacaos : Updates an existing tipoautorizacao.
     *
     * @param tipoautorizacaoDTO the tipoautorizacaoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipoautorizacaoDTO,
     * or with status 400 (Bad Request) if the tipoautorizacaoDTO is not valid,
     * or with status 500 (Internal Server Error) if the tipoautorizacaoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tipoautorizacaos")
    @Timed
    public ResponseEntity<TipoautorizacaoDTO> updateTipoautorizacao(@RequestBody TipoautorizacaoDTO tipoautorizacaoDTO) throws URISyntaxException {
        log.debug("REST request to update Tipoautorizacao : {}", tipoautorizacaoDTO);
        if (tipoautorizacaoDTO.getId() == null) {
            return createTipoautorizacao(tipoautorizacaoDTO);
        }
        Tipoautorizacao tipoautorizacao = tipoautorizacaoMapper.toEntity(tipoautorizacaoDTO);
        tipoautorizacao = tipoautorizacaoRepository.save(tipoautorizacao);
        TipoautorizacaoDTO result = tipoautorizacaoMapper.toDto(tipoautorizacao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipoautorizacaoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tipoautorizacaos : get all the tipoautorizacaos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tipoautorizacaos in body
     */
    @GetMapping("/tipoautorizacaos")
    @Timed
    public List<TipoautorizacaoDTO> getAllTipoautorizacaos() {
        log.debug("REST request to get all Tipoautorizacaos");
        List<Tipoautorizacao> tipoautorizacaos = tipoautorizacaoRepository.findAll();
        return tipoautorizacaoMapper.toDto(tipoautorizacaos);
    }

    /**
     * GET  /tipoautorizacaos/:id : get the "id" tipoautorizacao.
     *
     * @param id the id of the tipoautorizacaoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipoautorizacaoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tipoautorizacaos/{id}")
    @Timed
    public ResponseEntity<TipoautorizacaoDTO> getTipoautorizacao(@PathVariable Long id) {
        log.debug("REST request to get Tipoautorizacao : {}", id);
        Tipoautorizacao tipoautorizacao = tipoautorizacaoRepository.findOne(id);
        TipoautorizacaoDTO tipoautorizacaoDTO = tipoautorizacaoMapper.toDto(tipoautorizacao);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tipoautorizacaoDTO));
    }

    /**
     * DELETE  /tipoautorizacaos/:id : delete the "id" tipoautorizacao.
     *
     * @param id the id of the tipoautorizacaoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tipoautorizacaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteTipoautorizacao(@PathVariable Long id) {
        log.debug("REST request to delete Tipoautorizacao : {}", id);
        tipoautorizacaoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
