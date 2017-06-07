package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Informe;

import br.com.homemade.repository.InformeRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.InformeDTO;
import br.com.homemade.service.mapper.InformeMapper;
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
 * REST controller for managing Informe.
 */
@RestController
@RequestMapping("/api")
public class InformeResource {

    private final Logger log = LoggerFactory.getLogger(InformeResource.class);

    private static final String ENTITY_NAME = "informe";
        
    private final InformeRepository informeRepository;

    private final InformeMapper informeMapper;

    public InformeResource(InformeRepository informeRepository, InformeMapper informeMapper) {
        this.informeRepository = informeRepository;
        this.informeMapper = informeMapper;
    }

    /**
     * POST  /informes : Create a new informe.
     *
     * @param informeDTO the informeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new informeDTO, or with status 400 (Bad Request) if the informe has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/informes")
    @Timed
    public ResponseEntity<InformeDTO> createInforme(@RequestBody InformeDTO informeDTO) throws URISyntaxException {
        log.debug("REST request to save Informe : {}", informeDTO);
        if (informeDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new informe cannot already have an ID")).body(null);
        }
        Informe informe = informeMapper.toEntity(informeDTO);
        informe = informeRepository.save(informe);
        InformeDTO result = informeMapper.toDto(informe);
        return ResponseEntity.created(new URI("/api/informes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /informes : Updates an existing informe.
     *
     * @param informeDTO the informeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated informeDTO,
     * or with status 400 (Bad Request) if the informeDTO is not valid,
     * or with status 500 (Internal Server Error) if the informeDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/informes")
    @Timed
    public ResponseEntity<InformeDTO> updateInforme(@RequestBody InformeDTO informeDTO) throws URISyntaxException {
        log.debug("REST request to update Informe : {}", informeDTO);
        if (informeDTO.getId() == null) {
            return createInforme(informeDTO);
        }
        Informe informe = informeMapper.toEntity(informeDTO);
        informe = informeRepository.save(informe);
        InformeDTO result = informeMapper.toDto(informe);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, informeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /informes : get all the informes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of informes in body
     */
    @GetMapping("/informes")
    @Timed
    public List<InformeDTO> getAllInformes() {
        log.debug("REST request to get all Informes");
        List<Informe> informes = informeRepository.findAllWithEagerRelationships();
        return informeMapper.toDto(informes);
    }

    /**
     * GET  /informes/:id : get the "id" informe.
     *
     * @param id the id of the informeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the informeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/informes/{id}")
    @Timed
    public ResponseEntity<InformeDTO> getInforme(@PathVariable Long id) {
        log.debug("REST request to get Informe : {}", id);
        Informe informe = informeRepository.findOneWithEagerRelationships(id);
        InformeDTO informeDTO = informeMapper.toDto(informe);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(informeDTO));
    }

    /**
     * DELETE  /informes/:id : delete the "id" informe.
     *
     * @param id the id of the informeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/informes/{id}")
    @Timed
    public ResponseEntity<Void> deleteInforme(@PathVariable Long id) {
        log.debug("REST request to delete Informe : {}", id);
        informeRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
