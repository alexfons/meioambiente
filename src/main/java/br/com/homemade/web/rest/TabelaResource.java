package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Tabela;

import br.com.homemade.repository.TabelaRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.TabelaDTO;
import br.com.homemade.service.mapper.TabelaMapper;
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
 * REST controller for managing Tabela.
 */
@RestController
@RequestMapping("/api")
public class TabelaResource {

    private final Logger log = LoggerFactory.getLogger(TabelaResource.class);

    private static final String ENTITY_NAME = "tabela";
        
    private final TabelaRepository tabelaRepository;

    private final TabelaMapper tabelaMapper;

    public TabelaResource(TabelaRepository tabelaRepository, TabelaMapper tabelaMapper) {
        this.tabelaRepository = tabelaRepository;
        this.tabelaMapper = tabelaMapper;
    }

    /**
     * POST  /tabelas : Create a new tabela.
     *
     * @param tabelaDTO the tabelaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tabelaDTO, or with status 400 (Bad Request) if the tabela has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tabelas")
    @Timed
    public ResponseEntity<TabelaDTO> createTabela(@RequestBody TabelaDTO tabelaDTO) throws URISyntaxException {
        log.debug("REST request to save Tabela : {}", tabelaDTO);
        if (tabelaDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new tabela cannot already have an ID")).body(null);
        }
        Tabela tabela = tabelaMapper.toEntity(tabelaDTO);
        tabela = tabelaRepository.save(tabela);
        TabelaDTO result = tabelaMapper.toDto(tabela);
        return ResponseEntity.created(new URI("/api/tabelas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tabelas : Updates an existing tabela.
     *
     * @param tabelaDTO the tabelaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tabelaDTO,
     * or with status 400 (Bad Request) if the tabelaDTO is not valid,
     * or with status 500 (Internal Server Error) if the tabelaDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tabelas")
    @Timed
    public ResponseEntity<TabelaDTO> updateTabela(@RequestBody TabelaDTO tabelaDTO) throws URISyntaxException {
        log.debug("REST request to update Tabela : {}", tabelaDTO);
        if (tabelaDTO.getId() == null) {
            return createTabela(tabelaDTO);
        }
        Tabela tabela = tabelaMapper.toEntity(tabelaDTO);
        tabela = tabelaRepository.save(tabela);
        TabelaDTO result = tabelaMapper.toDto(tabela);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tabelaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tabelas : get all the tabelas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tabelas in body
     */
    @GetMapping("/tabelas")
    @Timed
    public List<TabelaDTO> getAllTabelas() {
        log.debug("REST request to get all Tabelas");
        List<Tabela> tabelas = tabelaRepository.findAll();
        return tabelaMapper.toDto(tabelas);
    }

    /**
     * GET  /tabelas/:id : get the "id" tabela.
     *
     * @param id the id of the tabelaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tabelaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tabelas/{id}")
    @Timed
    public ResponseEntity<TabelaDTO> getTabela(@PathVariable Long id) {
        log.debug("REST request to get Tabela : {}", id);
        Tabela tabela = tabelaRepository.findOne(id);
        TabelaDTO tabelaDTO = tabelaMapper.toDto(tabela);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tabelaDTO));
    }

    /**
     * DELETE  /tabelas/:id : delete the "id" tabela.
     *
     * @param id the id of the tabelaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tabelas/{id}")
    @Timed
    public ResponseEntity<Void> deleteTabela(@PathVariable Long id) {
        log.debug("REST request to delete Tabela : {}", id);
        tabelaRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
