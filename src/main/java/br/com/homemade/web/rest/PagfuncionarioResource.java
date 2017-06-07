package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Pagfuncionario;

import br.com.homemade.repository.PagfuncionarioRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.PagfuncionarioDTO;
import br.com.homemade.service.mapper.PagfuncionarioMapper;
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
 * REST controller for managing Pagfuncionario.
 */
@RestController
@RequestMapping("/api")
public class PagfuncionarioResource {

    private final Logger log = LoggerFactory.getLogger(PagfuncionarioResource.class);

    private static final String ENTITY_NAME = "pagfuncionario";
        
    private final PagfuncionarioRepository pagfuncionarioRepository;

    private final PagfuncionarioMapper pagfuncionarioMapper;

    public PagfuncionarioResource(PagfuncionarioRepository pagfuncionarioRepository, PagfuncionarioMapper pagfuncionarioMapper) {
        this.pagfuncionarioRepository = pagfuncionarioRepository;
        this.pagfuncionarioMapper = pagfuncionarioMapper;
    }

    /**
     * POST  /pagfuncionarios : Create a new pagfuncionario.
     *
     * @param pagfuncionarioDTO the pagfuncionarioDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pagfuncionarioDTO, or with status 400 (Bad Request) if the pagfuncionario has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pagfuncionarios")
    @Timed
    public ResponseEntity<PagfuncionarioDTO> createPagfuncionario(@RequestBody PagfuncionarioDTO pagfuncionarioDTO) throws URISyntaxException {
        log.debug("REST request to save Pagfuncionario : {}", pagfuncionarioDTO);
        if (pagfuncionarioDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new pagfuncionario cannot already have an ID")).body(null);
        }
        Pagfuncionario pagfuncionario = pagfuncionarioMapper.toEntity(pagfuncionarioDTO);
        pagfuncionario = pagfuncionarioRepository.save(pagfuncionario);
        PagfuncionarioDTO result = pagfuncionarioMapper.toDto(pagfuncionario);
        return ResponseEntity.created(new URI("/api/pagfuncionarios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pagfuncionarios : Updates an existing pagfuncionario.
     *
     * @param pagfuncionarioDTO the pagfuncionarioDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pagfuncionarioDTO,
     * or with status 400 (Bad Request) if the pagfuncionarioDTO is not valid,
     * or with status 500 (Internal Server Error) if the pagfuncionarioDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pagfuncionarios")
    @Timed
    public ResponseEntity<PagfuncionarioDTO> updatePagfuncionario(@RequestBody PagfuncionarioDTO pagfuncionarioDTO) throws URISyntaxException {
        log.debug("REST request to update Pagfuncionario : {}", pagfuncionarioDTO);
        if (pagfuncionarioDTO.getId() == null) {
            return createPagfuncionario(pagfuncionarioDTO);
        }
        Pagfuncionario pagfuncionario = pagfuncionarioMapper.toEntity(pagfuncionarioDTO);
        pagfuncionario = pagfuncionarioRepository.save(pagfuncionario);
        PagfuncionarioDTO result = pagfuncionarioMapper.toDto(pagfuncionario);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pagfuncionarioDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pagfuncionarios : get all the pagfuncionarios.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of pagfuncionarios in body
     */
    @GetMapping("/pagfuncionarios")
    @Timed
    public List<PagfuncionarioDTO> getAllPagfuncionarios() {
        log.debug("REST request to get all Pagfuncionarios");
        List<Pagfuncionario> pagfuncionarios = pagfuncionarioRepository.findAll();
        return pagfuncionarioMapper.toDto(pagfuncionarios);
    }

    /**
     * GET  /pagfuncionarios/:id : get the "id" pagfuncionario.
     *
     * @param id the id of the pagfuncionarioDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pagfuncionarioDTO, or with status 404 (Not Found)
     */
    @GetMapping("/pagfuncionarios/{id}")
    @Timed
    public ResponseEntity<PagfuncionarioDTO> getPagfuncionario(@PathVariable Long id) {
        log.debug("REST request to get Pagfuncionario : {}", id);
        Pagfuncionario pagfuncionario = pagfuncionarioRepository.findOne(id);
        PagfuncionarioDTO pagfuncionarioDTO = pagfuncionarioMapper.toDto(pagfuncionario);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(pagfuncionarioDTO));
    }

    /**
     * DELETE  /pagfuncionarios/:id : delete the "id" pagfuncionario.
     *
     * @param id the id of the pagfuncionarioDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pagfuncionarios/{id}")
    @Timed
    public ResponseEntity<Void> deletePagfuncionario(@PathVariable Long id) {
        log.debug("REST request to delete Pagfuncionario : {}", id);
        pagfuncionarioRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
