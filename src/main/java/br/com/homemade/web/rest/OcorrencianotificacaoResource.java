package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Ocorrencianotificacao;

import br.com.homemade.repository.OcorrencianotificacaoRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.OcorrencianotificacaoDTO;
import br.com.homemade.service.mapper.OcorrencianotificacaoMapper;
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
 * REST controller for managing Ocorrencianotificacao.
 */
@RestController
@RequestMapping("/api")
public class OcorrencianotificacaoResource {

    private final Logger log = LoggerFactory.getLogger(OcorrencianotificacaoResource.class);

    private static final String ENTITY_NAME = "ocorrencianotificacao";
        
    private final OcorrencianotificacaoRepository ocorrencianotificacaoRepository;

    private final OcorrencianotificacaoMapper ocorrencianotificacaoMapper;

    public OcorrencianotificacaoResource(OcorrencianotificacaoRepository ocorrencianotificacaoRepository, OcorrencianotificacaoMapper ocorrencianotificacaoMapper) {
        this.ocorrencianotificacaoRepository = ocorrencianotificacaoRepository;
        this.ocorrencianotificacaoMapper = ocorrencianotificacaoMapper;
    }

    /**
     * POST  /ocorrencianotificacaos : Create a new ocorrencianotificacao.
     *
     * @param ocorrencianotificacaoDTO the ocorrencianotificacaoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ocorrencianotificacaoDTO, or with status 400 (Bad Request) if the ocorrencianotificacao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ocorrencianotificacaos")
    @Timed
    public ResponseEntity<OcorrencianotificacaoDTO> createOcorrencianotificacao(@RequestBody OcorrencianotificacaoDTO ocorrencianotificacaoDTO) throws URISyntaxException {
        log.debug("REST request to save Ocorrencianotificacao : {}", ocorrencianotificacaoDTO);
        if (ocorrencianotificacaoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new ocorrencianotificacao cannot already have an ID")).body(null);
        }
        Ocorrencianotificacao ocorrencianotificacao = ocorrencianotificacaoMapper.toEntity(ocorrencianotificacaoDTO);
        ocorrencianotificacao = ocorrencianotificacaoRepository.save(ocorrencianotificacao);
        OcorrencianotificacaoDTO result = ocorrencianotificacaoMapper.toDto(ocorrencianotificacao);
        return ResponseEntity.created(new URI("/api/ocorrencianotificacaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ocorrencianotificacaos : Updates an existing ocorrencianotificacao.
     *
     * @param ocorrencianotificacaoDTO the ocorrencianotificacaoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ocorrencianotificacaoDTO,
     * or with status 400 (Bad Request) if the ocorrencianotificacaoDTO is not valid,
     * or with status 500 (Internal Server Error) if the ocorrencianotificacaoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ocorrencianotificacaos")
    @Timed
    public ResponseEntity<OcorrencianotificacaoDTO> updateOcorrencianotificacao(@RequestBody OcorrencianotificacaoDTO ocorrencianotificacaoDTO) throws URISyntaxException {
        log.debug("REST request to update Ocorrencianotificacao : {}", ocorrencianotificacaoDTO);
        if (ocorrencianotificacaoDTO.getId() == null) {
            return createOcorrencianotificacao(ocorrencianotificacaoDTO);
        }
        Ocorrencianotificacao ocorrencianotificacao = ocorrencianotificacaoMapper.toEntity(ocorrencianotificacaoDTO);
        ocorrencianotificacao = ocorrencianotificacaoRepository.save(ocorrencianotificacao);
        OcorrencianotificacaoDTO result = ocorrencianotificacaoMapper.toDto(ocorrencianotificacao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ocorrencianotificacaoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ocorrencianotificacaos : get all the ocorrencianotificacaos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of ocorrencianotificacaos in body
     */
    @GetMapping("/ocorrencianotificacaos")
    @Timed
    public List<OcorrencianotificacaoDTO> getAllOcorrencianotificacaos() {
        log.debug("REST request to get all Ocorrencianotificacaos");
        List<Ocorrencianotificacao> ocorrencianotificacaos = ocorrencianotificacaoRepository.findAll();
        return ocorrencianotificacaoMapper.toDto(ocorrencianotificacaos);
    }

    /**
     * GET  /ocorrencianotificacaos/:id : get the "id" ocorrencianotificacao.
     *
     * @param id the id of the ocorrencianotificacaoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ocorrencianotificacaoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ocorrencianotificacaos/{id}")
    @Timed
    public ResponseEntity<OcorrencianotificacaoDTO> getOcorrencianotificacao(@PathVariable Long id) {
        log.debug("REST request to get Ocorrencianotificacao : {}", id);
        Ocorrencianotificacao ocorrencianotificacao = ocorrencianotificacaoRepository.findOne(id);
        OcorrencianotificacaoDTO ocorrencianotificacaoDTO = ocorrencianotificacaoMapper.toDto(ocorrencianotificacao);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(ocorrencianotificacaoDTO));
    }

    /**
     * DELETE  /ocorrencianotificacaos/:id : delete the "id" ocorrencianotificacao.
     *
     * @param id the id of the ocorrencianotificacaoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ocorrencianotificacaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteOcorrencianotificacao(@PathVariable Long id) {
        log.debug("REST request to delete Ocorrencianotificacao : {}", id);
        ocorrencianotificacaoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
