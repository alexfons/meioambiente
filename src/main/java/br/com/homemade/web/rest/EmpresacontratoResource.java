package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Empresacontrato;

import br.com.homemade.repository.EmpresacontratoRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.EmpresacontratoDTO;
import br.com.homemade.service.mapper.EmpresacontratoMapper;
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
 * REST controller for managing Empresacontrato.
 */
@RestController
@RequestMapping("/api")
public class EmpresacontratoResource {

    private final Logger log = LoggerFactory.getLogger(EmpresacontratoResource.class);

    private static final String ENTITY_NAME = "empresacontrato";
        
    private final EmpresacontratoRepository empresacontratoRepository;

    private final EmpresacontratoMapper empresacontratoMapper;

    public EmpresacontratoResource(EmpresacontratoRepository empresacontratoRepository, EmpresacontratoMapper empresacontratoMapper) {
        this.empresacontratoRepository = empresacontratoRepository;
        this.empresacontratoMapper = empresacontratoMapper;
    }

    /**
     * POST  /empresacontratoes : Create a new empresacontrato.
     *
     * @param empresacontratoDTO the empresacontratoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new empresacontratoDTO, or with status 400 (Bad Request) if the empresacontrato has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/empresacontratoes")
    @Timed
    public ResponseEntity<EmpresacontratoDTO> createEmpresacontrato(@RequestBody EmpresacontratoDTO empresacontratoDTO) throws URISyntaxException {
        log.debug("REST request to save Empresacontrato : {}", empresacontratoDTO);
        if (empresacontratoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new empresacontrato cannot already have an ID")).body(null);
        }
        Empresacontrato empresacontrato = empresacontratoMapper.toEntity(empresacontratoDTO);
        empresacontrato = empresacontratoRepository.save(empresacontrato);
        EmpresacontratoDTO result = empresacontratoMapper.toDto(empresacontrato);
        return ResponseEntity.created(new URI("/api/empresacontratoes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /empresacontratoes : Updates an existing empresacontrato.
     *
     * @param empresacontratoDTO the empresacontratoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated empresacontratoDTO,
     * or with status 400 (Bad Request) if the empresacontratoDTO is not valid,
     * or with status 500 (Internal Server Error) if the empresacontratoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/empresacontratoes")
    @Timed
    public ResponseEntity<EmpresacontratoDTO> updateEmpresacontrato(@RequestBody EmpresacontratoDTO empresacontratoDTO) throws URISyntaxException {
        log.debug("REST request to update Empresacontrato : {}", empresacontratoDTO);
        if (empresacontratoDTO.getId() == null) {
            return createEmpresacontrato(empresacontratoDTO);
        }
        Empresacontrato empresacontrato = empresacontratoMapper.toEntity(empresacontratoDTO);
        empresacontrato = empresacontratoRepository.save(empresacontrato);
        EmpresacontratoDTO result = empresacontratoMapper.toDto(empresacontrato);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, empresacontratoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /empresacontratoes : get all the empresacontratoes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of empresacontratoes in body
     */
    @GetMapping("/empresacontratoes")
    @Timed
    public List<EmpresacontratoDTO> getAllEmpresacontratoes() {
        log.debug("REST request to get all Empresacontratoes");
        List<Empresacontrato> empresacontratoes = empresacontratoRepository.findAll();
        return empresacontratoMapper.toDto(empresacontratoes);
    }

    /**
     * GET  /empresacontratoes/:id : get the "id" empresacontrato.
     *
     * @param id the id of the empresacontratoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the empresacontratoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/empresacontratoes/{id}")
    @Timed
    public ResponseEntity<EmpresacontratoDTO> getEmpresacontrato(@PathVariable Long id) {
        log.debug("REST request to get Empresacontrato : {}", id);
        Empresacontrato empresacontrato = empresacontratoRepository.findOne(id);
        EmpresacontratoDTO empresacontratoDTO = empresacontratoMapper.toDto(empresacontrato);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(empresacontratoDTO));
    }

    /**
     * DELETE  /empresacontratoes/:id : delete the "id" empresacontrato.
     *
     * @param id the id of the empresacontratoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/empresacontratoes/{id}")
    @Timed
    public ResponseEntity<Void> deleteEmpresacontrato(@PathVariable Long id) {
        log.debug("REST request to delete Empresacontrato : {}", id);
        empresacontratoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
