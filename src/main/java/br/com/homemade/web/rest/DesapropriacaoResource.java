package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Desapropriacao;

import br.com.homemade.repository.DesapropriacaoRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.DesapropriacaoDTO;
import br.com.homemade.service.mapper.DesapropriacaoMapper;
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
 * REST controller for managing Desapropriacao.
 */
@RestController
@RequestMapping("/api")
public class DesapropriacaoResource {

    private final Logger log = LoggerFactory.getLogger(DesapropriacaoResource.class);

    private static final String ENTITY_NAME = "desapropriacao";
        
    private final DesapropriacaoRepository desapropriacaoRepository;

    private final DesapropriacaoMapper desapropriacaoMapper;

    public DesapropriacaoResource(DesapropriacaoRepository desapropriacaoRepository, DesapropriacaoMapper desapropriacaoMapper) {
        this.desapropriacaoRepository = desapropriacaoRepository;
        this.desapropriacaoMapper = desapropriacaoMapper;
    }

    /**
     * POST  /desapropriacaos : Create a new desapropriacao.
     *
     * @param desapropriacaoDTO the desapropriacaoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new desapropriacaoDTO, or with status 400 (Bad Request) if the desapropriacao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/desapropriacaos")
    @Timed
    public ResponseEntity<DesapropriacaoDTO> createDesapropriacao(@RequestBody DesapropriacaoDTO desapropriacaoDTO) throws URISyntaxException {
        log.debug("REST request to save Desapropriacao : {}", desapropriacaoDTO);
        if (desapropriacaoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new desapropriacao cannot already have an ID")).body(null);
        }
        Desapropriacao desapropriacao = desapropriacaoMapper.toEntity(desapropriacaoDTO);
        desapropriacao = desapropriacaoRepository.save(desapropriacao);
        DesapropriacaoDTO result = desapropriacaoMapper.toDto(desapropriacao);
        return ResponseEntity.created(new URI("/api/desapropriacaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /desapropriacaos : Updates an existing desapropriacao.
     *
     * @param desapropriacaoDTO the desapropriacaoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated desapropriacaoDTO,
     * or with status 400 (Bad Request) if the desapropriacaoDTO is not valid,
     * or with status 500 (Internal Server Error) if the desapropriacaoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/desapropriacaos")
    @Timed
    public ResponseEntity<DesapropriacaoDTO> updateDesapropriacao(@RequestBody DesapropriacaoDTO desapropriacaoDTO) throws URISyntaxException {
        log.debug("REST request to update Desapropriacao : {}", desapropriacaoDTO);
        if (desapropriacaoDTO.getId() == null) {
            return createDesapropriacao(desapropriacaoDTO);
        }
        Desapropriacao desapropriacao = desapropriacaoMapper.toEntity(desapropriacaoDTO);
        desapropriacao = desapropriacaoRepository.save(desapropriacao);
        DesapropriacaoDTO result = desapropriacaoMapper.toDto(desapropriacao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, desapropriacaoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /desapropriacaos : get all the desapropriacaos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of desapropriacaos in body
     */
    @GetMapping("/desapropriacaos")
    @Timed
    public List<DesapropriacaoDTO> getAllDesapropriacaos() {
        log.debug("REST request to get all Desapropriacaos");
        List<Desapropriacao> desapropriacaos = desapropriacaoRepository.findAll();
        return desapropriacaoMapper.toDto(desapropriacaos);
    }

    /**
     * GET  /desapropriacaos/:id : get the "id" desapropriacao.
     *
     * @param id the id of the desapropriacaoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the desapropriacaoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/desapropriacaos/{id}")
    @Timed
    public ResponseEntity<DesapropriacaoDTO> getDesapropriacao(@PathVariable Long id) {
        log.debug("REST request to get Desapropriacao : {}", id);
        Desapropriacao desapropriacao = desapropriacaoRepository.findOne(id);
        DesapropriacaoDTO desapropriacaoDTO = desapropriacaoMapper.toDto(desapropriacao);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(desapropriacaoDTO));
    }

    /**
     * DELETE  /desapropriacaos/:id : delete the "id" desapropriacao.
     *
     * @param id the id of the desapropriacaoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/desapropriacaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteDesapropriacao(@PathVariable Long id) {
        log.debug("REST request to delete Desapropriacao : {}", id);
        desapropriacaoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
