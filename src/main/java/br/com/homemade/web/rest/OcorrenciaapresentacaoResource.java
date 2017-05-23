package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Ocorrenciaapresentacao;

import br.com.homemade.repository.OcorrenciaapresentacaoRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.OcorrenciaapresentacaoDTO;
import br.com.homemade.service.mapper.OcorrenciaapresentacaoMapper;
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
 * REST controller for managing Ocorrenciaapresentacao.
 */
@RestController
@RequestMapping("/api")
public class OcorrenciaapresentacaoResource {

    private final Logger log = LoggerFactory.getLogger(OcorrenciaapresentacaoResource.class);

    private static final String ENTITY_NAME = "ocorrenciaapresentacao";
        
    private final OcorrenciaapresentacaoRepository ocorrenciaapresentacaoRepository;

    private final OcorrenciaapresentacaoMapper ocorrenciaapresentacaoMapper;

    public OcorrenciaapresentacaoResource(OcorrenciaapresentacaoRepository ocorrenciaapresentacaoRepository, OcorrenciaapresentacaoMapper ocorrenciaapresentacaoMapper) {
        this.ocorrenciaapresentacaoRepository = ocorrenciaapresentacaoRepository;
        this.ocorrenciaapresentacaoMapper = ocorrenciaapresentacaoMapper;
    }

    /**
     * POST  /ocorrenciaapresentacaos : Create a new ocorrenciaapresentacao.
     *
     * @param ocorrenciaapresentacaoDTO the ocorrenciaapresentacaoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ocorrenciaapresentacaoDTO, or with status 400 (Bad Request) if the ocorrenciaapresentacao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ocorrenciaapresentacaos")
    @Timed
    public ResponseEntity<OcorrenciaapresentacaoDTO> createOcorrenciaapresentacao(@RequestBody OcorrenciaapresentacaoDTO ocorrenciaapresentacaoDTO) throws URISyntaxException {
        log.debug("REST request to save Ocorrenciaapresentacao : {}", ocorrenciaapresentacaoDTO);
        if (ocorrenciaapresentacaoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new ocorrenciaapresentacao cannot already have an ID")).body(null);
        }
        Ocorrenciaapresentacao ocorrenciaapresentacao = ocorrenciaapresentacaoMapper.toEntity(ocorrenciaapresentacaoDTO);
        ocorrenciaapresentacao = ocorrenciaapresentacaoRepository.save(ocorrenciaapresentacao);
        OcorrenciaapresentacaoDTO result = ocorrenciaapresentacaoMapper.toDto(ocorrenciaapresentacao);
        return ResponseEntity.created(new URI("/api/ocorrenciaapresentacaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ocorrenciaapresentacaos : Updates an existing ocorrenciaapresentacao.
     *
     * @param ocorrenciaapresentacaoDTO the ocorrenciaapresentacaoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ocorrenciaapresentacaoDTO,
     * or with status 400 (Bad Request) if the ocorrenciaapresentacaoDTO is not valid,
     * or with status 500 (Internal Server Error) if the ocorrenciaapresentacaoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ocorrenciaapresentacaos")
    @Timed
    public ResponseEntity<OcorrenciaapresentacaoDTO> updateOcorrenciaapresentacao(@RequestBody OcorrenciaapresentacaoDTO ocorrenciaapresentacaoDTO) throws URISyntaxException {
        log.debug("REST request to update Ocorrenciaapresentacao : {}", ocorrenciaapresentacaoDTO);
        if (ocorrenciaapresentacaoDTO.getId() == null) {
            return createOcorrenciaapresentacao(ocorrenciaapresentacaoDTO);
        }
        Ocorrenciaapresentacao ocorrenciaapresentacao = ocorrenciaapresentacaoMapper.toEntity(ocorrenciaapresentacaoDTO);
        ocorrenciaapresentacao = ocorrenciaapresentacaoRepository.save(ocorrenciaapresentacao);
        OcorrenciaapresentacaoDTO result = ocorrenciaapresentacaoMapper.toDto(ocorrenciaapresentacao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ocorrenciaapresentacaoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ocorrenciaapresentacaos : get all the ocorrenciaapresentacaos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of ocorrenciaapresentacaos in body
     */
    @GetMapping("/ocorrenciaapresentacaos")
    @Timed
    public List<OcorrenciaapresentacaoDTO> getAllOcorrenciaapresentacaos() {
        log.debug("REST request to get all Ocorrenciaapresentacaos");
        List<Ocorrenciaapresentacao> ocorrenciaapresentacaos = ocorrenciaapresentacaoRepository.findAll();
        return ocorrenciaapresentacaoMapper.toDto(ocorrenciaapresentacaos);
    }

    /**
     * GET  /ocorrenciaapresentacaos/:id : get the "id" ocorrenciaapresentacao.
     *
     * @param id the id of the ocorrenciaapresentacaoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ocorrenciaapresentacaoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ocorrenciaapresentacaos/{id}")
    @Timed
    public ResponseEntity<OcorrenciaapresentacaoDTO> getOcorrenciaapresentacao(@PathVariable Long id) {
        log.debug("REST request to get Ocorrenciaapresentacao : {}", id);
        Ocorrenciaapresentacao ocorrenciaapresentacao = ocorrenciaapresentacaoRepository.findOne(id);
        OcorrenciaapresentacaoDTO ocorrenciaapresentacaoDTO = ocorrenciaapresentacaoMapper.toDto(ocorrenciaapresentacao);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(ocorrenciaapresentacaoDTO));
    }

    /**
     * DELETE  /ocorrenciaapresentacaos/:id : delete the "id" ocorrenciaapresentacao.
     *
     * @param id the id of the ocorrenciaapresentacaoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ocorrenciaapresentacaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteOcorrenciaapresentacao(@PathVariable Long id) {
        log.debug("REST request to delete Ocorrenciaapresentacao : {}", id);
        ocorrenciaapresentacaoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
