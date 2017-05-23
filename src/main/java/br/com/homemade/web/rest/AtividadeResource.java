package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Atividade;

import br.com.homemade.repository.AtividadeRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.AtividadeDTO;
import br.com.homemade.service.mapper.AtividadeMapper;
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
 * REST controller for managing Atividade.
 */
@RestController
@RequestMapping("/api")
public class AtividadeResource {

    private final Logger log = LoggerFactory.getLogger(AtividadeResource.class);

    private static final String ENTITY_NAME = "atividade";
        
    private final AtividadeRepository atividadeRepository;

    private final AtividadeMapper atividadeMapper;

    public AtividadeResource(AtividadeRepository atividadeRepository, AtividadeMapper atividadeMapper) {
        this.atividadeRepository = atividadeRepository;
        this.atividadeMapper = atividadeMapper;
    }

    /**
     * POST  /atividades : Create a new atividade.
     *
     * @param atividadeDTO the atividadeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new atividadeDTO, or with status 400 (Bad Request) if the atividade has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/atividades")
    @Timed
    public ResponseEntity<AtividadeDTO> createAtividade(@RequestBody AtividadeDTO atividadeDTO) throws URISyntaxException {
        log.debug("REST request to save Atividade : {}", atividadeDTO);
        if (atividadeDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new atividade cannot already have an ID")).body(null);
        }
        Atividade atividade = atividadeMapper.toEntity(atividadeDTO);
        atividade = atividadeRepository.save(atividade);
        AtividadeDTO result = atividadeMapper.toDto(atividade);
        return ResponseEntity.created(new URI("/api/atividades/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /atividades : Updates an existing atividade.
     *
     * @param atividadeDTO the atividadeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated atividadeDTO,
     * or with status 400 (Bad Request) if the atividadeDTO is not valid,
     * or with status 500 (Internal Server Error) if the atividadeDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/atividades")
    @Timed
    public ResponseEntity<AtividadeDTO> updateAtividade(@RequestBody AtividadeDTO atividadeDTO) throws URISyntaxException {
        log.debug("REST request to update Atividade : {}", atividadeDTO);
        if (atividadeDTO.getId() == null) {
            return createAtividade(atividadeDTO);
        }
        Atividade atividade = atividadeMapper.toEntity(atividadeDTO);
        atividade = atividadeRepository.save(atividade);
        AtividadeDTO result = atividadeMapper.toDto(atividade);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, atividadeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /atividades : get all the atividades.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of atividades in body
     */
    @GetMapping("/atividades")
    @Timed
    public List<AtividadeDTO> getAllAtividades() {
        log.debug("REST request to get all Atividades");
        List<Atividade> atividades = atividadeRepository.findAll();
        return atividadeMapper.toDto(atividades);
    }

    /**
     * GET  /atividades/:id : get the "id" atividade.
     *
     * @param id the id of the atividadeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the atividadeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/atividades/{id}")
    @Timed
    public ResponseEntity<AtividadeDTO> getAtividade(@PathVariable Long id) {
        log.debug("REST request to get Atividade : {}", id);
        Atividade atividade = atividadeRepository.findOne(id);
        AtividadeDTO atividadeDTO = atividadeMapper.toDto(atividade);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(atividadeDTO));
    }

    /**
     * DELETE  /atividades/:id : delete the "id" atividade.
     *
     * @param id the id of the atividadeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/atividades/{id}")
    @Timed
    public ResponseEntity<Void> deleteAtividade(@PathVariable Long id) {
        log.debug("REST request to delete Atividade : {}", id);
        atividadeRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
