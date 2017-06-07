package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Linha;

import br.com.homemade.repository.LinhaRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.LinhaDTO;
import br.com.homemade.service.mapper.LinhaMapper;
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
 * REST controller for managing Linha.
 */
@RestController
@RequestMapping("/api")
public class LinhaResource {

    private final Logger log = LoggerFactory.getLogger(LinhaResource.class);

    private static final String ENTITY_NAME = "linha";
        
    private final LinhaRepository linhaRepository;

    private final LinhaMapper linhaMapper;

    public LinhaResource(LinhaRepository linhaRepository, LinhaMapper linhaMapper) {
        this.linhaRepository = linhaRepository;
        this.linhaMapper = linhaMapper;
    }

    /**
     * POST  /linhas : Create a new linha.
     *
     * @param linhaDTO the linhaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new linhaDTO, or with status 400 (Bad Request) if the linha has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/linhas")
    @Timed
    public ResponseEntity<LinhaDTO> createLinha(@RequestBody LinhaDTO linhaDTO) throws URISyntaxException {
        log.debug("REST request to save Linha : {}", linhaDTO);
        if (linhaDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new linha cannot already have an ID")).body(null);
        }
        Linha linha = linhaMapper.toEntity(linhaDTO);
        linha = linhaRepository.save(linha);
        LinhaDTO result = linhaMapper.toDto(linha);
        return ResponseEntity.created(new URI("/api/linhas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /linhas : Updates an existing linha.
     *
     * @param linhaDTO the linhaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated linhaDTO,
     * or with status 400 (Bad Request) if the linhaDTO is not valid,
     * or with status 500 (Internal Server Error) if the linhaDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/linhas")
    @Timed
    public ResponseEntity<LinhaDTO> updateLinha(@RequestBody LinhaDTO linhaDTO) throws URISyntaxException {
        log.debug("REST request to update Linha : {}", linhaDTO);
        if (linhaDTO.getId() == null) {
            return createLinha(linhaDTO);
        }
        Linha linha = linhaMapper.toEntity(linhaDTO);
        linha = linhaRepository.save(linha);
        LinhaDTO result = linhaMapper.toDto(linha);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, linhaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /linhas : get all the linhas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of linhas in body
     */
    @GetMapping("/linhas")
    @Timed
    public List<LinhaDTO> getAllLinhas() {
        log.debug("REST request to get all Linhas");
        List<Linha> linhas = linhaRepository.findAll();
        return linhaMapper.toDto(linhas);
    }

    /**
     * GET  /linhas/:id : get the "id" linha.
     *
     * @param id the id of the linhaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the linhaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/linhas/{id}")
    @Timed
    public ResponseEntity<LinhaDTO> getLinha(@PathVariable Long id) {
        log.debug("REST request to get Linha : {}", id);
        Linha linha = linhaRepository.findOne(id);
        LinhaDTO linhaDTO = linhaMapper.toDto(linha);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(linhaDTO));
    }

    /**
     * DELETE  /linhas/:id : delete the "id" linha.
     *
     * @param id the id of the linhaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/linhas/{id}")
    @Timed
    public ResponseEntity<Void> deleteLinha(@PathVariable Long id) {
        log.debug("REST request to delete Linha : {}", id);
        linhaRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
