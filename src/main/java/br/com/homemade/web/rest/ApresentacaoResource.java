package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Apresentacao;

import br.com.homemade.repository.ApresentacaoRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.ApresentacaoDTO;
import br.com.homemade.service.mapper.ApresentacaoMapper;
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
 * REST controller for managing Apresentacao.
 */
@RestController
@RequestMapping("/api")
public class ApresentacaoResource {

    private final Logger log = LoggerFactory.getLogger(ApresentacaoResource.class);

    private static final String ENTITY_NAME = "apresentacao";
        
    private final ApresentacaoRepository apresentacaoRepository;

    private final ApresentacaoMapper apresentacaoMapper;

    public ApresentacaoResource(ApresentacaoRepository apresentacaoRepository, ApresentacaoMapper apresentacaoMapper) {
        this.apresentacaoRepository = apresentacaoRepository;
        this.apresentacaoMapper = apresentacaoMapper;
    }

    /**
     * POST  /apresentacaos : Create a new apresentacao.
     *
     * @param apresentacaoDTO the apresentacaoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new apresentacaoDTO, or with status 400 (Bad Request) if the apresentacao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/apresentacaos")
    @Timed
    public ResponseEntity<ApresentacaoDTO> createApresentacao(@RequestBody ApresentacaoDTO apresentacaoDTO) throws URISyntaxException {
        log.debug("REST request to save Apresentacao : {}", apresentacaoDTO);
        if (apresentacaoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new apresentacao cannot already have an ID")).body(null);
        }
        Apresentacao apresentacao = apresentacaoMapper.toEntity(apresentacaoDTO);
        apresentacao = apresentacaoRepository.save(apresentacao);
        ApresentacaoDTO result = apresentacaoMapper.toDto(apresentacao);
        return ResponseEntity.created(new URI("/api/apresentacaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /apresentacaos : Updates an existing apresentacao.
     *
     * @param apresentacaoDTO the apresentacaoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated apresentacaoDTO,
     * or with status 400 (Bad Request) if the apresentacaoDTO is not valid,
     * or with status 500 (Internal Server Error) if the apresentacaoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/apresentacaos")
    @Timed
    public ResponseEntity<ApresentacaoDTO> updateApresentacao(@RequestBody ApresentacaoDTO apresentacaoDTO) throws URISyntaxException {
        log.debug("REST request to update Apresentacao : {}", apresentacaoDTO);
        if (apresentacaoDTO.getId() == null) {
            return createApresentacao(apresentacaoDTO);
        }
        Apresentacao apresentacao = apresentacaoMapper.toEntity(apresentacaoDTO);
        apresentacao = apresentacaoRepository.save(apresentacao);
        ApresentacaoDTO result = apresentacaoMapper.toDto(apresentacao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, apresentacaoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /apresentacaos : get all the apresentacaos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of apresentacaos in body
     */
    @GetMapping("/apresentacaos")
    @Timed
    public List<ApresentacaoDTO> getAllApresentacaos() {
        log.debug("REST request to get all Apresentacaos");
        List<Apresentacao> apresentacaos = apresentacaoRepository.findAllWithEagerRelationships();
        return apresentacaoMapper.toDto(apresentacaos);
    }

    /**
     * GET  /apresentacaos/:id : get the "id" apresentacao.
     *
     * @param id the id of the apresentacaoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the apresentacaoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/apresentacaos/{id}")
    @Timed
    public ResponseEntity<ApresentacaoDTO> getApresentacao(@PathVariable Long id) {
        log.debug("REST request to get Apresentacao : {}", id);
        Apresentacao apresentacao = apresentacaoRepository.findOneWithEagerRelationships(id);
        ApresentacaoDTO apresentacaoDTO = apresentacaoMapper.toDto(apresentacao);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(apresentacaoDTO));
    }

    /**
     * DELETE  /apresentacaos/:id : delete the "id" apresentacao.
     *
     * @param id the id of the apresentacaoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/apresentacaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteApresentacao(@PathVariable Long id) {
        log.debug("REST request to delete Apresentacao : {}", id);
        apresentacaoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
