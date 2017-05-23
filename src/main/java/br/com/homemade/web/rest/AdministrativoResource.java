package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Administrativo;

import br.com.homemade.repository.AdministrativoRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.AdministrativoDTO;
import br.com.homemade.service.mapper.AdministrativoMapper;
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
 * REST controller for managing Administrativo.
 */
@RestController
@RequestMapping("/api")
public class AdministrativoResource {

    private final Logger log = LoggerFactory.getLogger(AdministrativoResource.class);

    private static final String ENTITY_NAME = "administrativo";
        
    private final AdministrativoRepository administrativoRepository;

    private final AdministrativoMapper administrativoMapper;

    public AdministrativoResource(AdministrativoRepository administrativoRepository, AdministrativoMapper administrativoMapper) {
        this.administrativoRepository = administrativoRepository;
        this.administrativoMapper = administrativoMapper;
    }

    /**
     * POST  /administrativos : Create a new administrativo.
     *
     * @param administrativoDTO the administrativoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new administrativoDTO, or with status 400 (Bad Request) if the administrativo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/administrativos")
    @Timed
    public ResponseEntity<AdministrativoDTO> createAdministrativo(@RequestBody AdministrativoDTO administrativoDTO) throws URISyntaxException {
        log.debug("REST request to save Administrativo : {}", administrativoDTO);
        if (administrativoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new administrativo cannot already have an ID")).body(null);
        }
        Administrativo administrativo = administrativoMapper.toEntity(administrativoDTO);
        administrativo = administrativoRepository.save(administrativo);
        AdministrativoDTO result = administrativoMapper.toDto(administrativo);
        return ResponseEntity.created(new URI("/api/administrativos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /administrativos : Updates an existing administrativo.
     *
     * @param administrativoDTO the administrativoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated administrativoDTO,
     * or with status 400 (Bad Request) if the administrativoDTO is not valid,
     * or with status 500 (Internal Server Error) if the administrativoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/administrativos")
    @Timed
    public ResponseEntity<AdministrativoDTO> updateAdministrativo(@RequestBody AdministrativoDTO administrativoDTO) throws URISyntaxException {
        log.debug("REST request to update Administrativo : {}", administrativoDTO);
        if (administrativoDTO.getId() == null) {
            return createAdministrativo(administrativoDTO);
        }
        Administrativo administrativo = administrativoMapper.toEntity(administrativoDTO);
        administrativo = administrativoRepository.save(administrativo);
        AdministrativoDTO result = administrativoMapper.toDto(administrativo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, administrativoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /administrativos : get all the administrativos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of administrativos in body
     */
    @GetMapping("/administrativos")
    @Timed
    public List<AdministrativoDTO> getAllAdministrativos() {
        log.debug("REST request to get all Administrativos");
        List<Administrativo> administrativos = administrativoRepository.findAllWithEagerRelationships();
        return administrativoMapper.toDto(administrativos);
    }

    /**
     * GET  /administrativos/:id : get the "id" administrativo.
     *
     * @param id the id of the administrativoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the administrativoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/administrativos/{id}")
    @Timed
    public ResponseEntity<AdministrativoDTO> getAdministrativo(@PathVariable Long id) {
        log.debug("REST request to get Administrativo : {}", id);
        Administrativo administrativo = administrativoRepository.findOneWithEagerRelationships(id);
        AdministrativoDTO administrativoDTO = administrativoMapper.toDto(administrativo);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(administrativoDTO));
    }

    /**
     * DELETE  /administrativos/:id : delete the "id" administrativo.
     *
     * @param id the id of the administrativoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/administrativos/{id}")
    @Timed
    public ResponseEntity<Void> deleteAdministrativo(@PathVariable Long id) {
        log.debug("REST request to delete Administrativo : {}", id);
        administrativoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
