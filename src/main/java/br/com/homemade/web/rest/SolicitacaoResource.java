package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Solicitacao;

import br.com.homemade.repository.SolicitacaoRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.SolicitacaoDTO;
import br.com.homemade.service.mapper.SolicitacaoMapper;
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
 * REST controller for managing Solicitacao.
 */
@RestController
@RequestMapping("/api")
public class SolicitacaoResource {

    private final Logger log = LoggerFactory.getLogger(SolicitacaoResource.class);

    private static final String ENTITY_NAME = "solicitacao";
        
    private final SolicitacaoRepository solicitacaoRepository;

    private final SolicitacaoMapper solicitacaoMapper;

    public SolicitacaoResource(SolicitacaoRepository solicitacaoRepository, SolicitacaoMapper solicitacaoMapper) {
        this.solicitacaoRepository = solicitacaoRepository;
        this.solicitacaoMapper = solicitacaoMapper;
    }

    /**
     * POST  /solicitacaos : Create a new solicitacao.
     *
     * @param solicitacaoDTO the solicitacaoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new solicitacaoDTO, or with status 400 (Bad Request) if the solicitacao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/solicitacaos")
    @Timed
    public ResponseEntity<SolicitacaoDTO> createSolicitacao(@RequestBody SolicitacaoDTO solicitacaoDTO) throws URISyntaxException {
        log.debug("REST request to save Solicitacao : {}", solicitacaoDTO);
        if (solicitacaoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new solicitacao cannot already have an ID")).body(null);
        }
        Solicitacao solicitacao = solicitacaoMapper.toEntity(solicitacaoDTO);
        solicitacao = solicitacaoRepository.save(solicitacao);
        SolicitacaoDTO result = solicitacaoMapper.toDto(solicitacao);
        return ResponseEntity.created(new URI("/api/solicitacaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /solicitacaos : Updates an existing solicitacao.
     *
     * @param solicitacaoDTO the solicitacaoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated solicitacaoDTO,
     * or with status 400 (Bad Request) if the solicitacaoDTO is not valid,
     * or with status 500 (Internal Server Error) if the solicitacaoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/solicitacaos")
    @Timed
    public ResponseEntity<SolicitacaoDTO> updateSolicitacao(@RequestBody SolicitacaoDTO solicitacaoDTO) throws URISyntaxException {
        log.debug("REST request to update Solicitacao : {}", solicitacaoDTO);
        if (solicitacaoDTO.getId() == null) {
            return createSolicitacao(solicitacaoDTO);
        }
        Solicitacao solicitacao = solicitacaoMapper.toEntity(solicitacaoDTO);
        solicitacao = solicitacaoRepository.save(solicitacao);
        SolicitacaoDTO result = solicitacaoMapper.toDto(solicitacao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, solicitacaoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /solicitacaos : get all the solicitacaos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of solicitacaos in body
     */
    @GetMapping("/solicitacaos")
    @Timed
    public List<SolicitacaoDTO> getAllSolicitacaos() {
        log.debug("REST request to get all Solicitacaos");
        List<Solicitacao> solicitacaos = solicitacaoRepository.findAll();
        return solicitacaoMapper.toDto(solicitacaos);
    }

    /**
     * GET  /solicitacaos/:id : get the "id" solicitacao.
     *
     * @param id the id of the solicitacaoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the solicitacaoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/solicitacaos/{id}")
    @Timed
    public ResponseEntity<SolicitacaoDTO> getSolicitacao(@PathVariable Long id) {
        log.debug("REST request to get Solicitacao : {}", id);
        Solicitacao solicitacao = solicitacaoRepository.findOne(id);
        SolicitacaoDTO solicitacaoDTO = solicitacaoMapper.toDto(solicitacao);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(solicitacaoDTO));
    }

    /**
     * DELETE  /solicitacaos/:id : delete the "id" solicitacao.
     *
     * @param id the id of the solicitacaoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/solicitacaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteSolicitacao(@PathVariable Long id) {
        log.debug("REST request to delete Solicitacao : {}", id);
        solicitacaoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
