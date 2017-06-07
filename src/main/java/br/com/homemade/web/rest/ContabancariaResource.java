package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Contabancaria;

import br.com.homemade.repository.ContabancariaRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.ContabancariaDTO;
import br.com.homemade.service.mapper.ContabancariaMapper;
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
 * REST controller for managing Contabancaria.
 */
@RestController
@RequestMapping("/api")
public class ContabancariaResource {

    private final Logger log = LoggerFactory.getLogger(ContabancariaResource.class);

    private static final String ENTITY_NAME = "contabancaria";
        
    private final ContabancariaRepository contabancariaRepository;

    private final ContabancariaMapper contabancariaMapper;

    public ContabancariaResource(ContabancariaRepository contabancariaRepository, ContabancariaMapper contabancariaMapper) {
        this.contabancariaRepository = contabancariaRepository;
        this.contabancariaMapper = contabancariaMapper;
    }

    /**
     * POST  /contabancarias : Create a new contabancaria.
     *
     * @param contabancariaDTO the contabancariaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new contabancariaDTO, or with status 400 (Bad Request) if the contabancaria has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/contabancarias")
    @Timed
    public ResponseEntity<ContabancariaDTO> createContabancaria(@RequestBody ContabancariaDTO contabancariaDTO) throws URISyntaxException {
        log.debug("REST request to save Contabancaria : {}", contabancariaDTO);
        if (contabancariaDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new contabancaria cannot already have an ID")).body(null);
        }
        Contabancaria contabancaria = contabancariaMapper.toEntity(contabancariaDTO);
        contabancaria = contabancariaRepository.save(contabancaria);
        ContabancariaDTO result = contabancariaMapper.toDto(contabancaria);
        return ResponseEntity.created(new URI("/api/contabancarias/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /contabancarias : Updates an existing contabancaria.
     *
     * @param contabancariaDTO the contabancariaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated contabancariaDTO,
     * or with status 400 (Bad Request) if the contabancariaDTO is not valid,
     * or with status 500 (Internal Server Error) if the contabancariaDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/contabancarias")
    @Timed
    public ResponseEntity<ContabancariaDTO> updateContabancaria(@RequestBody ContabancariaDTO contabancariaDTO) throws URISyntaxException {
        log.debug("REST request to update Contabancaria : {}", contabancariaDTO);
        if (contabancariaDTO.getId() == null) {
            return createContabancaria(contabancariaDTO);
        }
        Contabancaria contabancaria = contabancariaMapper.toEntity(contabancariaDTO);
        contabancaria = contabancariaRepository.save(contabancaria);
        ContabancariaDTO result = contabancariaMapper.toDto(contabancaria);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, contabancariaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /contabancarias : get all the contabancarias.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of contabancarias in body
     */
    @GetMapping("/contabancarias")
    @Timed
    public List<ContabancariaDTO> getAllContabancarias() {
        log.debug("REST request to get all Contabancarias");
        List<Contabancaria> contabancarias = contabancariaRepository.findAll();
        return contabancariaMapper.toDto(contabancarias);
    }

    /**
     * GET  /contabancarias/:id : get the "id" contabancaria.
     *
     * @param id the id of the contabancariaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the contabancariaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/contabancarias/{id}")
    @Timed
    public ResponseEntity<ContabancariaDTO> getContabancaria(@PathVariable Long id) {
        log.debug("REST request to get Contabancaria : {}", id);
        Contabancaria contabancaria = contabancariaRepository.findOne(id);
        ContabancariaDTO contabancariaDTO = contabancariaMapper.toDto(contabancaria);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(contabancariaDTO));
    }

    /**
     * DELETE  /contabancarias/:id : delete the "id" contabancaria.
     *
     * @param id the id of the contabancariaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/contabancarias/{id}")
    @Timed
    public ResponseEntity<Void> deleteContabancaria(@PathVariable Long id) {
        log.debug("REST request to delete Contabancaria : {}", id);
        contabancariaRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
