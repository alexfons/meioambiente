package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Projeto;

import br.com.homemade.repository.ProjetoRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.ProjetoDTO;
import br.com.homemade.service.mapper.ProjetoMapper;
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
 * REST controller for managing Projeto.
 */
@RestController
@RequestMapping("/api")
public class ProjetoResource {

    private final Logger log = LoggerFactory.getLogger(ProjetoResource.class);

    private static final String ENTITY_NAME = "projeto";
        
    private final ProjetoRepository projetoRepository;

    private final ProjetoMapper projetoMapper;

    public ProjetoResource(ProjetoRepository projetoRepository, ProjetoMapper projetoMapper) {
        this.projetoRepository = projetoRepository;
        this.projetoMapper = projetoMapper;
    }

    /**
     * POST  /projetos : Create a new projeto.
     *
     * @param projetoDTO the projetoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new projetoDTO, or with status 400 (Bad Request) if the projeto has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/projetos")
    @Timed
    public ResponseEntity<ProjetoDTO> createProjeto(@RequestBody ProjetoDTO projetoDTO) throws URISyntaxException {
        log.debug("REST request to save Projeto : {}", projetoDTO);
        if (projetoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new projeto cannot already have an ID")).body(null);
        }
        Projeto projeto = projetoMapper.toEntity(projetoDTO);
        projeto = projetoRepository.save(projeto);
        ProjetoDTO result = projetoMapper.toDto(projeto);
        return ResponseEntity.created(new URI("/api/projetos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /projetos : Updates an existing projeto.
     *
     * @param projetoDTO the projetoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated projetoDTO,
     * or with status 400 (Bad Request) if the projetoDTO is not valid,
     * or with status 500 (Internal Server Error) if the projetoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/projetos")
    @Timed
    public ResponseEntity<ProjetoDTO> updateProjeto(@RequestBody ProjetoDTO projetoDTO) throws URISyntaxException {
        log.debug("REST request to update Projeto : {}", projetoDTO);
        if (projetoDTO.getId() == null) {
            return createProjeto(projetoDTO);
        }
        Projeto projeto = projetoMapper.toEntity(projetoDTO);
        projeto = projetoRepository.save(projeto);
        ProjetoDTO result = projetoMapper.toDto(projeto);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, projetoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /projetos : get all the projetos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of projetos in body
     */
    @GetMapping("/projetos")
    @Timed
    public List<ProjetoDTO> getAllProjetos() {
        log.debug("REST request to get all Projetos");
        List<Projeto> projetos = projetoRepository.findAllWithEagerRelationships();
        return projetoMapper.toDto(projetos);
    }

    /**
     * GET  /projetos/:id : get the "id" projeto.
     *
     * @param id the id of the projetoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the projetoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/projetos/{id}")
    @Timed
    public ResponseEntity<ProjetoDTO> getProjeto(@PathVariable Long id) {
        log.debug("REST request to get Projeto : {}", id);
        Projeto projeto = projetoRepository.findOneWithEagerRelationships(id);
        ProjetoDTO projetoDTO = projetoMapper.toDto(projeto);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(projetoDTO));
    }

    /**
     * DELETE  /projetos/:id : delete the "id" projeto.
     *
     * @param id the id of the projetoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/projetos/{id}")
    @Timed
    public ResponseEntity<Void> deleteProjeto(@PathVariable Long id) {
        log.debug("REST request to delete Projeto : {}", id);
        projetoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
