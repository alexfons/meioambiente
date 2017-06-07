package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Autorizacao;

import br.com.homemade.repository.AutorizacaoRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.AutorizacaoDTO;
import br.com.homemade.service.mapper.AutorizacaoMapper;
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
 * REST controller for managing Autorizacao.
 */
@RestController
@RequestMapping("/api")
public class AutorizacaoResource {

    private final Logger log = LoggerFactory.getLogger(AutorizacaoResource.class);

    private static final String ENTITY_NAME = "autorizacao";
        
    private final AutorizacaoRepository autorizacaoRepository;

    private final AutorizacaoMapper autorizacaoMapper;

    public AutorizacaoResource(AutorizacaoRepository autorizacaoRepository, AutorizacaoMapper autorizacaoMapper) {
        this.autorizacaoRepository = autorizacaoRepository;
        this.autorizacaoMapper = autorizacaoMapper;
    }

    /**
     * POST  /autorizacaos : Create a new autorizacao.
     *
     * @param autorizacaoDTO the autorizacaoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new autorizacaoDTO, or with status 400 (Bad Request) if the autorizacao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/autorizacaos")
    @Timed
    public ResponseEntity<AutorizacaoDTO> createAutorizacao(@RequestBody AutorizacaoDTO autorizacaoDTO) throws URISyntaxException {
        log.debug("REST request to save Autorizacao : {}", autorizacaoDTO);
        if (autorizacaoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new autorizacao cannot already have an ID")).body(null);
        }
        Autorizacao autorizacao = autorizacaoMapper.toEntity(autorizacaoDTO);
        autorizacao = autorizacaoRepository.save(autorizacao);
        AutorizacaoDTO result = autorizacaoMapper.toDto(autorizacao);
        return ResponseEntity.created(new URI("/api/autorizacaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /autorizacaos : Updates an existing autorizacao.
     *
     * @param autorizacaoDTO the autorizacaoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated autorizacaoDTO,
     * or with status 400 (Bad Request) if the autorizacaoDTO is not valid,
     * or with status 500 (Internal Server Error) if the autorizacaoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/autorizacaos")
    @Timed
    public ResponseEntity<AutorizacaoDTO> updateAutorizacao(@RequestBody AutorizacaoDTO autorizacaoDTO) throws URISyntaxException {
        log.debug("REST request to update Autorizacao : {}", autorizacaoDTO);
        if (autorizacaoDTO.getId() == null) {
            return createAutorizacao(autorizacaoDTO);
        }
        Autorizacao autorizacao = autorizacaoMapper.toEntity(autorizacaoDTO);
        autorizacao = autorizacaoRepository.save(autorizacao);
        AutorizacaoDTO result = autorizacaoMapper.toDto(autorizacao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, autorizacaoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /autorizacaos : get all the autorizacaos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of autorizacaos in body
     */
    @GetMapping("/autorizacaos")
    @Timed
    public List<AutorizacaoDTO> getAllAutorizacaos() {
        log.debug("REST request to get all Autorizacaos");
        List<Autorizacao> autorizacaos = autorizacaoRepository.findAllWithEagerRelationships();
        return autorizacaoMapper.toDto(autorizacaos);
    }

    /**
     * GET  /autorizacaos/:id : get the "id" autorizacao.
     *
     * @param id the id of the autorizacaoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the autorizacaoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/autorizacaos/{id}")
    @Timed
    public ResponseEntity<AutorizacaoDTO> getAutorizacao(@PathVariable Long id) {
        log.debug("REST request to get Autorizacao : {}", id);
        Autorizacao autorizacao = autorizacaoRepository.findOneWithEagerRelationships(id);
        AutorizacaoDTO autorizacaoDTO = autorizacaoMapper.toDto(autorizacao);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(autorizacaoDTO));
    }

    /**
     * DELETE  /autorizacaos/:id : delete the "id" autorizacao.
     *
     * @param id the id of the autorizacaoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/autorizacaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteAutorizacao(@PathVariable Long id) {
        log.debug("REST request to delete Autorizacao : {}", id);
        autorizacaoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
