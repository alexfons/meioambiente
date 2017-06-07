package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Log;

import br.com.homemade.repository.LogRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.LogDTO;
import br.com.homemade.service.mapper.LogMapper;
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
 * REST controller for managing Log.
 */
@RestController
@RequestMapping("/api")
public class LogResource {

    private final Logger log = LoggerFactory.getLogger(LogResource.class);

    private static final String ENTITY_NAME = "log";
        
    private final LogRepository logRepository;

    private final LogMapper logMapper;

    public LogResource(LogRepository logRepository, LogMapper logMapper) {
        this.logRepository = logRepository;
        this.logMapper = logMapper;
    }

    /**
     * POST  /logs : Create a new log.
     *
     * @param logDTO the logDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new logDTO, or with status 400 (Bad Request) if the log has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/logs")
    @Timed
    public ResponseEntity<LogDTO> createLog(@RequestBody LogDTO logDTO) throws URISyntaxException {
        log.debug("REST request to save Log : {}", logDTO);
        if (logDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new log cannot already have an ID")).body(null);
        }
        Log log = logMapper.toEntity(logDTO);
        log = logRepository.save(log);
        LogDTO result = logMapper.toDto(log);
        return ResponseEntity.created(new URI("/api/logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /logs : Updates an existing log.
     *
     * @param logDTO the logDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated logDTO,
     * or with status 400 (Bad Request) if the logDTO is not valid,
     * or with status 500 (Internal Server Error) if the logDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/logs")
    @Timed
    public ResponseEntity<LogDTO> updateLog(@RequestBody LogDTO logDTO) throws URISyntaxException {
        log.debug("REST request to update Log : {}", logDTO);
        if (logDTO.getId() == null) {
            return createLog(logDTO);
        }
        Log log = logMapper.toEntity(logDTO);
        log = logRepository.save(log);
        LogDTO result = logMapper.toDto(log);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, logDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /logs : get all the logs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of logs in body
     */
    @GetMapping("/logs")
    @Timed
    public List<LogDTO> getAllLogs() {
        log.debug("REST request to get all Logs");
        List<Log> logs = logRepository.findAll();
        return logMapper.toDto(logs);
    }

    /**
     * GET  /logs/:id : get the "id" log.
     *
     * @param id the id of the logDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the logDTO, or with status 404 (Not Found)
     */
    @GetMapping("/logs/{id}")
    @Timed
    public ResponseEntity<LogDTO> getLog(@PathVariable Long id) {
        log.debug("REST request to get Log : {}", id);
        Log log = logRepository.findOne(id);
        LogDTO logDTO = logMapper.toDto(log);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(logDTO));
    }

    /**
     * DELETE  /logs/:id : delete the "id" log.
     *
     * @param id the id of the logDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/logs/{id}")
    @Timed
    public ResponseEntity<Void> deleteLog(@PathVariable Long id) {
        log.debug("REST request to delete Log : {}", id);
        logRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
