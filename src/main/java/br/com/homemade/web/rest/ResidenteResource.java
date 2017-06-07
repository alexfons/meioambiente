package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Residente;

import br.com.homemade.repository.ResidenteRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.ResidenteDTO;
import br.com.homemade.service.mapper.ResidenteMapper;
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
 * REST controller for managing Residente.
 */
@RestController
@RequestMapping("/api")
public class ResidenteResource {

    private final Logger log = LoggerFactory.getLogger(ResidenteResource.class);

    private static final String ENTITY_NAME = "residente";
        
    private final ResidenteRepository residenteRepository;

    private final ResidenteMapper residenteMapper;

    public ResidenteResource(ResidenteRepository residenteRepository, ResidenteMapper residenteMapper) {
        this.residenteRepository = residenteRepository;
        this.residenteMapper = residenteMapper;
    }

    /**
     * POST  /residentes : Create a new residente.
     *
     * @param residenteDTO the residenteDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new residenteDTO, or with status 400 (Bad Request) if the residente has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/residentes")
    @Timed
    public ResponseEntity<ResidenteDTO> createResidente(@RequestBody ResidenteDTO residenteDTO) throws URISyntaxException {
        log.debug("REST request to save Residente : {}", residenteDTO);
        if (residenteDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new residente cannot already have an ID")).body(null);
        }
        Residente residente = residenteMapper.toEntity(residenteDTO);
        residente = residenteRepository.save(residente);
        ResidenteDTO result = residenteMapper.toDto(residente);
        return ResponseEntity.created(new URI("/api/residentes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /residentes : Updates an existing residente.
     *
     * @param residenteDTO the residenteDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated residenteDTO,
     * or with status 400 (Bad Request) if the residenteDTO is not valid,
     * or with status 500 (Internal Server Error) if the residenteDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/residentes")
    @Timed
    public ResponseEntity<ResidenteDTO> updateResidente(@RequestBody ResidenteDTO residenteDTO) throws URISyntaxException {
        log.debug("REST request to update Residente : {}", residenteDTO);
        if (residenteDTO.getId() == null) {
            return createResidente(residenteDTO);
        }
        Residente residente = residenteMapper.toEntity(residenteDTO);
        residente = residenteRepository.save(residente);
        ResidenteDTO result = residenteMapper.toDto(residente);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, residenteDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /residentes : get all the residentes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of residentes in body
     */
    @GetMapping("/residentes")
    @Timed
    public List<ResidenteDTO> getAllResidentes() {
        log.debug("REST request to get all Residentes");
        List<Residente> residentes = residenteRepository.findAll();
        return residenteMapper.toDto(residentes);
    }

    /**
     * GET  /residentes/:id : get the "id" residente.
     *
     * @param id the id of the residenteDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the residenteDTO, or with status 404 (Not Found)
     */
    @GetMapping("/residentes/{id}")
    @Timed
    public ResponseEntity<ResidenteDTO> getResidente(@PathVariable Long id) {
        log.debug("REST request to get Residente : {}", id);
        Residente residente = residenteRepository.findOne(id);
        ResidenteDTO residenteDTO = residenteMapper.toDto(residente);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(residenteDTO));
    }

    /**
     * DELETE  /residentes/:id : delete the "id" residente.
     *
     * @param id the id of the residenteDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/residentes/{id}")
    @Timed
    public ResponseEntity<Void> deleteResidente(@PathVariable Long id) {
        log.debug("REST request to delete Residente : {}", id);
        residenteRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
