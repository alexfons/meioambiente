package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Script;

import br.com.homemade.repository.ScriptRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.ScriptDTO;
import br.com.homemade.service.mapper.ScriptMapper;
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
 * REST controller for managing Script.
 */
@RestController
@RequestMapping("/api")
public class ScriptResource {

    private final Logger log = LoggerFactory.getLogger(ScriptResource.class);

    private static final String ENTITY_NAME = "script";
        
    private final ScriptRepository scriptRepository;

    private final ScriptMapper scriptMapper;

    public ScriptResource(ScriptRepository scriptRepository, ScriptMapper scriptMapper) {
        this.scriptRepository = scriptRepository;
        this.scriptMapper = scriptMapper;
    }

    /**
     * POST  /scripts : Create a new script.
     *
     * @param scriptDTO the scriptDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new scriptDTO, or with status 400 (Bad Request) if the script has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/scripts")
    @Timed
    public ResponseEntity<ScriptDTO> createScript(@RequestBody ScriptDTO scriptDTO) throws URISyntaxException {
        log.debug("REST request to save Script : {}", scriptDTO);
        if (scriptDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new script cannot already have an ID")).body(null);
        }
        Script script = scriptMapper.toEntity(scriptDTO);
        script = scriptRepository.save(script);
        ScriptDTO result = scriptMapper.toDto(script);
        return ResponseEntity.created(new URI("/api/scripts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /scripts : Updates an existing script.
     *
     * @param scriptDTO the scriptDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated scriptDTO,
     * or with status 400 (Bad Request) if the scriptDTO is not valid,
     * or with status 500 (Internal Server Error) if the scriptDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/scripts")
    @Timed
    public ResponseEntity<ScriptDTO> updateScript(@RequestBody ScriptDTO scriptDTO) throws URISyntaxException {
        log.debug("REST request to update Script : {}", scriptDTO);
        if (scriptDTO.getId() == null) {
            return createScript(scriptDTO);
        }
        Script script = scriptMapper.toEntity(scriptDTO);
        script = scriptRepository.save(script);
        ScriptDTO result = scriptMapper.toDto(script);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, scriptDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /scripts : get all the scripts.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of scripts in body
     */
    @GetMapping("/scripts")
    @Timed
    public List<ScriptDTO> getAllScripts() {
        log.debug("REST request to get all Scripts");
        List<Script> scripts = scriptRepository.findAll();
        return scriptMapper.toDto(scripts);
    }

    /**
     * GET  /scripts/:id : get the "id" script.
     *
     * @param id the id of the scriptDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the scriptDTO, or with status 404 (Not Found)
     */
    @GetMapping("/scripts/{id}")
    @Timed
    public ResponseEntity<ScriptDTO> getScript(@PathVariable Long id) {
        log.debug("REST request to get Script : {}", id);
        Script script = scriptRepository.findOne(id);
        ScriptDTO scriptDTO = scriptMapper.toDto(script);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(scriptDTO));
    }

    /**
     * DELETE  /scripts/:id : delete the "id" script.
     *
     * @param id the id of the scriptDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/scripts/{id}")
    @Timed
    public ResponseEntity<Void> deleteScript(@PathVariable Long id) {
        log.debug("REST request to delete Script : {}", id);
        scriptRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
