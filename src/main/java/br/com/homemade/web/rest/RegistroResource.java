package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Registro;

import br.com.homemade.repository.RegistroRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.RegistroDTO;
import br.com.homemade.service.mapper.RegistroMapper;
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
 * REST controller for managing Registro.
 */
@RestController
@RequestMapping("/api")
public class RegistroResource {

    private final Logger log = LoggerFactory.getLogger(RegistroResource.class);

    private static final String ENTITY_NAME = "registro";
        
    private final RegistroRepository registroRepository;

    private final RegistroMapper registroMapper;

    public RegistroResource(RegistroRepository registroRepository, RegistroMapper registroMapper) {
        this.registroRepository = registroRepository;
        this.registroMapper = registroMapper;
    }

    /**
     * POST  /registros : Create a new registro.
     *
     * @param registroDTO the registroDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new registroDTO, or with status 400 (Bad Request) if the registro has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/registros")
    @Timed
    public ResponseEntity<RegistroDTO> createRegistro(@RequestBody RegistroDTO registroDTO) throws URISyntaxException {
        log.debug("REST request to save Registro : {}", registroDTO);
        if (registroDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new registro cannot already have an ID")).body(null);
        }
        Registro registro = registroMapper.toEntity(registroDTO);
        registro = registroRepository.save(registro);
        RegistroDTO result = registroMapper.toDto(registro);
        return ResponseEntity.created(new URI("/api/registros/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /registros : Updates an existing registro.
     *
     * @param registroDTO the registroDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated registroDTO,
     * or with status 400 (Bad Request) if the registroDTO is not valid,
     * or with status 500 (Internal Server Error) if the registroDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/registros")
    @Timed
    public ResponseEntity<RegistroDTO> updateRegistro(@RequestBody RegistroDTO registroDTO) throws URISyntaxException {
        log.debug("REST request to update Registro : {}", registroDTO);
        if (registroDTO.getId() == null) {
            return createRegistro(registroDTO);
        }
        Registro registro = registroMapper.toEntity(registroDTO);
        registro = registroRepository.save(registro);
        RegistroDTO result = registroMapper.toDto(registro);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, registroDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /registros : get all the registros.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of registros in body
     */
    @GetMapping("/registros")
    @Timed
    public List<RegistroDTO> getAllRegistros() {
        log.debug("REST request to get all Registros");
        List<Registro> registros = registroRepository.findAll();
        return registroMapper.toDto(registros);
    }

    /**
     * GET  /registros/:id : get the "id" registro.
     *
     * @param id the id of the registroDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the registroDTO, or with status 404 (Not Found)
     */
    @GetMapping("/registros/{id}")
    @Timed
    public ResponseEntity<RegistroDTO> getRegistro(@PathVariable Long id) {
        log.debug("REST request to get Registro : {}", id);
        Registro registro = registroRepository.findOne(id);
        RegistroDTO registroDTO = registroMapper.toDto(registro);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(registroDTO));
    }

    /**
     * DELETE  /registros/:id : delete the "id" registro.
     *
     * @param id the id of the registroDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/registros/{id}")
    @Timed
    public ResponseEntity<Void> deleteRegistro(@PathVariable Long id) {
        log.debug("REST request to delete Registro : {}", id);
        registroRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
