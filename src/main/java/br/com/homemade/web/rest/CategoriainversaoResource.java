package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Categoriainversao;

import br.com.homemade.repository.CategoriainversaoRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.CategoriainversaoDTO;
import br.com.homemade.service.mapper.CategoriainversaoMapper;
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
 * REST controller for managing Categoriainversao.
 */
@RestController
@RequestMapping("/api")
public class CategoriainversaoResource {

    private final Logger log = LoggerFactory.getLogger(CategoriainversaoResource.class);

    private static final String ENTITY_NAME = "categoriainversao";
        
    private final CategoriainversaoRepository categoriainversaoRepository;

    private final CategoriainversaoMapper categoriainversaoMapper;

    public CategoriainversaoResource(CategoriainversaoRepository categoriainversaoRepository, CategoriainversaoMapper categoriainversaoMapper) {
        this.categoriainversaoRepository = categoriainversaoRepository;
        this.categoriainversaoMapper = categoriainversaoMapper;
    }

    /**
     * POST  /categoriainversaos : Create a new categoriainversao.
     *
     * @param categoriainversaoDTO the categoriainversaoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new categoriainversaoDTO, or with status 400 (Bad Request) if the categoriainversao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/categoriainversaos")
    @Timed
    public ResponseEntity<CategoriainversaoDTO> createCategoriainversao(@RequestBody CategoriainversaoDTO categoriainversaoDTO) throws URISyntaxException {
        log.debug("REST request to save Categoriainversao : {}", categoriainversaoDTO);
        if (categoriainversaoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new categoriainversao cannot already have an ID")).body(null);
        }
        Categoriainversao categoriainversao = categoriainversaoMapper.toEntity(categoriainversaoDTO);
        categoriainversao = categoriainversaoRepository.save(categoriainversao);
        CategoriainversaoDTO result = categoriainversaoMapper.toDto(categoriainversao);
        return ResponseEntity.created(new URI("/api/categoriainversaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /categoriainversaos : Updates an existing categoriainversao.
     *
     * @param categoriainversaoDTO the categoriainversaoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated categoriainversaoDTO,
     * or with status 400 (Bad Request) if the categoriainversaoDTO is not valid,
     * or with status 500 (Internal Server Error) if the categoriainversaoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/categoriainversaos")
    @Timed
    public ResponseEntity<CategoriainversaoDTO> updateCategoriainversao(@RequestBody CategoriainversaoDTO categoriainversaoDTO) throws URISyntaxException {
        log.debug("REST request to update Categoriainversao : {}", categoriainversaoDTO);
        if (categoriainversaoDTO.getId() == null) {
            return createCategoriainversao(categoriainversaoDTO);
        }
        Categoriainversao categoriainversao = categoriainversaoMapper.toEntity(categoriainversaoDTO);
        categoriainversao = categoriainversaoRepository.save(categoriainversao);
        CategoriainversaoDTO result = categoriainversaoMapper.toDto(categoriainversao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, categoriainversaoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /categoriainversaos : get all the categoriainversaos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of categoriainversaos in body
     */
    @GetMapping("/categoriainversaos")
    @Timed
    public List<CategoriainversaoDTO> getAllCategoriainversaos() {
        log.debug("REST request to get all Categoriainversaos");
        List<Categoriainversao> categoriainversaos = categoriainversaoRepository.findAll();
        return categoriainversaoMapper.toDto(categoriainversaos);
    }

    /**
     * GET  /categoriainversaos/:id : get the "id" categoriainversao.
     *
     * @param id the id of the categoriainversaoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the categoriainversaoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/categoriainversaos/{id}")
    @Timed
    public ResponseEntity<CategoriainversaoDTO> getCategoriainversao(@PathVariable Long id) {
        log.debug("REST request to get Categoriainversao : {}", id);
        Categoriainversao categoriainversao = categoriainversaoRepository.findOne(id);
        CategoriainversaoDTO categoriainversaoDTO = categoriainversaoMapper.toDto(categoriainversao);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(categoriainversaoDTO));
    }

    /**
     * DELETE  /categoriainversaos/:id : delete the "id" categoriainversao.
     *
     * @param id the id of the categoriainversaoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/categoriainversaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteCategoriainversao(@PathVariable Long id) {
        log.debug("REST request to delete Categoriainversao : {}", id);
        categoriainversaoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
