package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Clausulascontratuais;

import br.com.homemade.repository.ClausulascontratuaisRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.ClausulascontratuaisDTO;
import br.com.homemade.service.mapper.ClausulascontratuaisMapper;
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
 * REST controller for managing Clausulascontratuais.
 */
@RestController
@RequestMapping("/api")
public class ClausulascontratuaisResource {

    private final Logger log = LoggerFactory.getLogger(ClausulascontratuaisResource.class);

    private static final String ENTITY_NAME = "clausulascontratuais";
        
    private final ClausulascontratuaisRepository clausulascontratuaisRepository;

    private final ClausulascontratuaisMapper clausulascontratuaisMapper;

    public ClausulascontratuaisResource(ClausulascontratuaisRepository clausulascontratuaisRepository, ClausulascontratuaisMapper clausulascontratuaisMapper) {
        this.clausulascontratuaisRepository = clausulascontratuaisRepository;
        this.clausulascontratuaisMapper = clausulascontratuaisMapper;
    }

    /**
     * POST  /clausulascontratuais : Create a new clausulascontratuais.
     *
     * @param clausulascontratuaisDTO the clausulascontratuaisDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new clausulascontratuaisDTO, or with status 400 (Bad Request) if the clausulascontratuais has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/clausulascontratuais")
    @Timed
    public ResponseEntity<ClausulascontratuaisDTO> createClausulascontratuais(@RequestBody ClausulascontratuaisDTO clausulascontratuaisDTO) throws URISyntaxException {
        log.debug("REST request to save Clausulascontratuais : {}", clausulascontratuaisDTO);
        if (clausulascontratuaisDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new clausulascontratuais cannot already have an ID")).body(null);
        }
        Clausulascontratuais clausulascontratuais = clausulascontratuaisMapper.toEntity(clausulascontratuaisDTO);
        clausulascontratuais = clausulascontratuaisRepository.save(clausulascontratuais);
        ClausulascontratuaisDTO result = clausulascontratuaisMapper.toDto(clausulascontratuais);
        return ResponseEntity.created(new URI("/api/clausulascontratuais/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /clausulascontratuais : Updates an existing clausulascontratuais.
     *
     * @param clausulascontratuaisDTO the clausulascontratuaisDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated clausulascontratuaisDTO,
     * or with status 400 (Bad Request) if the clausulascontratuaisDTO is not valid,
     * or with status 500 (Internal Server Error) if the clausulascontratuaisDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/clausulascontratuais")
    @Timed
    public ResponseEntity<ClausulascontratuaisDTO> updateClausulascontratuais(@RequestBody ClausulascontratuaisDTO clausulascontratuaisDTO) throws URISyntaxException {
        log.debug("REST request to update Clausulascontratuais : {}", clausulascontratuaisDTO);
        if (clausulascontratuaisDTO.getId() == null) {
            return createClausulascontratuais(clausulascontratuaisDTO);
        }
        Clausulascontratuais clausulascontratuais = clausulascontratuaisMapper.toEntity(clausulascontratuaisDTO);
        clausulascontratuais = clausulascontratuaisRepository.save(clausulascontratuais);
        ClausulascontratuaisDTO result = clausulascontratuaisMapper.toDto(clausulascontratuais);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, clausulascontratuaisDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /clausulascontratuais : get all the clausulascontratuais.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of clausulascontratuais in body
     */
    @GetMapping("/clausulascontratuais")
    @Timed
    public List<ClausulascontratuaisDTO> getAllClausulascontratuais() {
        log.debug("REST request to get all Clausulascontratuais");
        List<Clausulascontratuais> clausulascontratuais = clausulascontratuaisRepository.findAll();
        return clausulascontratuaisMapper.toDto(clausulascontratuais);
    }

    /**
     * GET  /clausulascontratuais/:id : get the "id" clausulascontratuais.
     *
     * @param id the id of the clausulascontratuaisDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the clausulascontratuaisDTO, or with status 404 (Not Found)
     */
    @GetMapping("/clausulascontratuais/{id}")
    @Timed
    public ResponseEntity<ClausulascontratuaisDTO> getClausulascontratuais(@PathVariable Long id) {
        log.debug("REST request to get Clausulascontratuais : {}", id);
        Clausulascontratuais clausulascontratuais = clausulascontratuaisRepository.findOne(id);
        ClausulascontratuaisDTO clausulascontratuaisDTO = clausulascontratuaisMapper.toDto(clausulascontratuais);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(clausulascontratuaisDTO));
    }

    /**
     * DELETE  /clausulascontratuais/:id : delete the "id" clausulascontratuais.
     *
     * @param id the id of the clausulascontratuaisDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/clausulascontratuais/{id}")
    @Timed
    public ResponseEntity<Void> deleteClausulascontratuais(@PathVariable Long id) {
        log.debug("REST request to delete Clausulascontratuais : {}", id);
        clausulascontratuaisRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
