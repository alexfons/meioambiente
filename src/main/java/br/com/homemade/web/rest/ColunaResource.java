package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Coluna;

import br.com.homemade.repository.ColunaRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.ColunaDTO;
import br.com.homemade.service.mapper.ColunaMapper;
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
 * REST controller for managing Coluna.
 */
@RestController
@RequestMapping("/api")
public class ColunaResource {

    private final Logger log = LoggerFactory.getLogger(ColunaResource.class);

    private static final String ENTITY_NAME = "coluna";
        
    private final ColunaRepository colunaRepository;

    private final ColunaMapper colunaMapper;

    public ColunaResource(ColunaRepository colunaRepository, ColunaMapper colunaMapper) {
        this.colunaRepository = colunaRepository;
        this.colunaMapper = colunaMapper;
    }

    /**
     * POST  /colunas : Create a new coluna.
     *
     * @param colunaDTO the colunaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new colunaDTO, or with status 400 (Bad Request) if the coluna has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/colunas")
    @Timed
    public ResponseEntity<ColunaDTO> createColuna(@RequestBody ColunaDTO colunaDTO) throws URISyntaxException {
        log.debug("REST request to save Coluna : {}", colunaDTO);
        if (colunaDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new coluna cannot already have an ID")).body(null);
        }
        Coluna coluna = colunaMapper.toEntity(colunaDTO);
        coluna = colunaRepository.save(coluna);
        ColunaDTO result = colunaMapper.toDto(coluna);
        return ResponseEntity.created(new URI("/api/colunas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /colunas : Updates an existing coluna.
     *
     * @param colunaDTO the colunaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated colunaDTO,
     * or with status 400 (Bad Request) if the colunaDTO is not valid,
     * or with status 500 (Internal Server Error) if the colunaDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/colunas")
    @Timed
    public ResponseEntity<ColunaDTO> updateColuna(@RequestBody ColunaDTO colunaDTO) throws URISyntaxException {
        log.debug("REST request to update Coluna : {}", colunaDTO);
        if (colunaDTO.getId() == null) {
            return createColuna(colunaDTO);
        }
        Coluna coluna = colunaMapper.toEntity(colunaDTO);
        coluna = colunaRepository.save(coluna);
        ColunaDTO result = colunaMapper.toDto(coluna);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, colunaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /colunas : get all the colunas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of colunas in body
     */
    @GetMapping("/colunas")
    @Timed
    public List<ColunaDTO> getAllColunas() {
        log.debug("REST request to get all Colunas");
        List<Coluna> colunas = colunaRepository.findAll();
        return colunaMapper.toDto(colunas);
    }

    /**
     * GET  /colunas/:id : get the "id" coluna.
     *
     * @param id the id of the colunaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the colunaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/colunas/{id}")
    @Timed
    public ResponseEntity<ColunaDTO> getColuna(@PathVariable Long id) {
        log.debug("REST request to get Coluna : {}", id);
        Coluna coluna = colunaRepository.findOne(id);
        ColunaDTO colunaDTO = colunaMapper.toDto(coluna);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(colunaDTO));
    }

    /**
     * DELETE  /colunas/:id : delete the "id" coluna.
     *
     * @param id the id of the colunaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/colunas/{id}")
    @Timed
    public ResponseEntity<Void> deleteColuna(@PathVariable Long id) {
        log.debug("REST request to delete Coluna : {}", id);
        colunaRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
