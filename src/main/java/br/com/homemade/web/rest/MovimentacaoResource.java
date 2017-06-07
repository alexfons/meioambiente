package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Movimentacao;

import br.com.homemade.repository.MovimentacaoRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.MovimentacaoDTO;
import br.com.homemade.service.mapper.MovimentacaoMapper;
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
 * REST controller for managing Movimentacao.
 */
@RestController
@RequestMapping("/api")
public class MovimentacaoResource {

    private final Logger log = LoggerFactory.getLogger(MovimentacaoResource.class);

    private static final String ENTITY_NAME = "movimentacao";
        
    private final MovimentacaoRepository movimentacaoRepository;

    private final MovimentacaoMapper movimentacaoMapper;

    public MovimentacaoResource(MovimentacaoRepository movimentacaoRepository, MovimentacaoMapper movimentacaoMapper) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.movimentacaoMapper = movimentacaoMapper;
    }

    /**
     * POST  /movimentacaos : Create a new movimentacao.
     *
     * @param movimentacaoDTO the movimentacaoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new movimentacaoDTO, or with status 400 (Bad Request) if the movimentacao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/movimentacaos")
    @Timed
    public ResponseEntity<MovimentacaoDTO> createMovimentacao(@RequestBody MovimentacaoDTO movimentacaoDTO) throws URISyntaxException {
        log.debug("REST request to save Movimentacao : {}", movimentacaoDTO);
        if (movimentacaoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new movimentacao cannot already have an ID")).body(null);
        }
        Movimentacao movimentacao = movimentacaoMapper.toEntity(movimentacaoDTO);
        movimentacao = movimentacaoRepository.save(movimentacao);
        MovimentacaoDTO result = movimentacaoMapper.toDto(movimentacao);
        return ResponseEntity.created(new URI("/api/movimentacaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /movimentacaos : Updates an existing movimentacao.
     *
     * @param movimentacaoDTO the movimentacaoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated movimentacaoDTO,
     * or with status 400 (Bad Request) if the movimentacaoDTO is not valid,
     * or with status 500 (Internal Server Error) if the movimentacaoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/movimentacaos")
    @Timed
    public ResponseEntity<MovimentacaoDTO> updateMovimentacao(@RequestBody MovimentacaoDTO movimentacaoDTO) throws URISyntaxException {
        log.debug("REST request to update Movimentacao : {}", movimentacaoDTO);
        if (movimentacaoDTO.getId() == null) {
            return createMovimentacao(movimentacaoDTO);
        }
        Movimentacao movimentacao = movimentacaoMapper.toEntity(movimentacaoDTO);
        movimentacao = movimentacaoRepository.save(movimentacao);
        MovimentacaoDTO result = movimentacaoMapper.toDto(movimentacao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, movimentacaoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /movimentacaos : get all the movimentacaos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of movimentacaos in body
     */
    @GetMapping("/movimentacaos")
    @Timed
    public List<MovimentacaoDTO> getAllMovimentacaos() {
        log.debug("REST request to get all Movimentacaos");
        List<Movimentacao> movimentacaos = movimentacaoRepository.findAll();
        return movimentacaoMapper.toDto(movimentacaos);
    }

    /**
     * GET  /movimentacaos/:id : get the "id" movimentacao.
     *
     * @param id the id of the movimentacaoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the movimentacaoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/movimentacaos/{id}")
    @Timed
    public ResponseEntity<MovimentacaoDTO> getMovimentacao(@PathVariable Long id) {
        log.debug("REST request to get Movimentacao : {}", id);
        Movimentacao movimentacao = movimentacaoRepository.findOne(id);
        MovimentacaoDTO movimentacaoDTO = movimentacaoMapper.toDto(movimentacao);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(movimentacaoDTO));
    }

    /**
     * DELETE  /movimentacaos/:id : delete the "id" movimentacao.
     *
     * @param id the id of the movimentacaoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/movimentacaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteMovimentacao(@PathVariable Long id) {
        log.debug("REST request to delete Movimentacao : {}", id);
        movimentacaoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
