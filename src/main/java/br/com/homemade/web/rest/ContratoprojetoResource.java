package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Contratoprojeto;

import br.com.homemade.repository.ContratoprojetoRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.ContratoprojetoDTO;
import br.com.homemade.service.mapper.ContratoprojetoMapper;
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
 * REST controller for managing Contratoprojeto.
 */
@RestController
@RequestMapping("/api")
public class ContratoprojetoResource {

    private final Logger log = LoggerFactory.getLogger(ContratoprojetoResource.class);

    private static final String ENTITY_NAME = "contratoprojeto";
        
    private final ContratoprojetoRepository contratoprojetoRepository;

    private final ContratoprojetoMapper contratoprojetoMapper;

    public ContratoprojetoResource(ContratoprojetoRepository contratoprojetoRepository, ContratoprojetoMapper contratoprojetoMapper) {
        this.contratoprojetoRepository = contratoprojetoRepository;
        this.contratoprojetoMapper = contratoprojetoMapper;
    }

    /**
     * POST  /contratoprojetos : Create a new contratoprojeto.
     *
     * @param contratoprojetoDTO the contratoprojetoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new contratoprojetoDTO, or with status 400 (Bad Request) if the contratoprojeto has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/contratoprojetos")
    @Timed
    public ResponseEntity<ContratoprojetoDTO> createContratoprojeto(@RequestBody ContratoprojetoDTO contratoprojetoDTO) throws URISyntaxException {
        log.debug("REST request to save Contratoprojeto : {}", contratoprojetoDTO);
        if (contratoprojetoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new contratoprojeto cannot already have an ID")).body(null);
        }
        Contratoprojeto contratoprojeto = contratoprojetoMapper.toEntity(contratoprojetoDTO);
        contratoprojeto = contratoprojetoRepository.save(contratoprojeto);
        ContratoprojetoDTO result = contratoprojetoMapper.toDto(contratoprojeto);
        return ResponseEntity.created(new URI("/api/contratoprojetos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /contratoprojetos : Updates an existing contratoprojeto.
     *
     * @param contratoprojetoDTO the contratoprojetoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated contratoprojetoDTO,
     * or with status 400 (Bad Request) if the contratoprojetoDTO is not valid,
     * or with status 500 (Internal Server Error) if the contratoprojetoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/contratoprojetos")
    @Timed
    public ResponseEntity<ContratoprojetoDTO> updateContratoprojeto(@RequestBody ContratoprojetoDTO contratoprojetoDTO) throws URISyntaxException {
        log.debug("REST request to update Contratoprojeto : {}", contratoprojetoDTO);
        if (contratoprojetoDTO.getId() == null) {
            return createContratoprojeto(contratoprojetoDTO);
        }
        Contratoprojeto contratoprojeto = contratoprojetoMapper.toEntity(contratoprojetoDTO);
        contratoprojeto = contratoprojetoRepository.save(contratoprojeto);
        ContratoprojetoDTO result = contratoprojetoMapper.toDto(contratoprojeto);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, contratoprojetoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /contratoprojetos : get all the contratoprojetos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of contratoprojetos in body
     */
    @GetMapping("/contratoprojetos")
    @Timed
    public List<ContratoprojetoDTO> getAllContratoprojetos() {
        log.debug("REST request to get all Contratoprojetos");
        List<Contratoprojeto> contratoprojetos = contratoprojetoRepository.findAll();
        return contratoprojetoMapper.toDto(contratoprojetos);
    }

    /**
     * GET  /contratoprojetos/:id : get the "id" contratoprojeto.
     *
     * @param id the id of the contratoprojetoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the contratoprojetoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/contratoprojetos/{id}")
    @Timed
    public ResponseEntity<ContratoprojetoDTO> getContratoprojeto(@PathVariable Long id) {
        log.debug("REST request to get Contratoprojeto : {}", id);
        Contratoprojeto contratoprojeto = contratoprojetoRepository.findOne(id);
        ContratoprojetoDTO contratoprojetoDTO = contratoprojetoMapper.toDto(contratoprojeto);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(contratoprojetoDTO));
    }

    /**
     * DELETE  /contratoprojetos/:id : delete the "id" contratoprojeto.
     *
     * @param id the id of the contratoprojetoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/contratoprojetos/{id}")
    @Timed
    public ResponseEntity<Void> deleteContratoprojeto(@PathVariable Long id) {
        log.debug("REST request to delete Contratoprojeto : {}", id);
        contratoprojetoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
