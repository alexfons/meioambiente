package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Informecertificadoirregularidade;

import br.com.homemade.repository.InformecertificadoirregularidadeRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.InformecertificadoirregularidadeDTO;
import br.com.homemade.service.mapper.InformecertificadoirregularidadeMapper;
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
 * REST controller for managing Informecertificadoirregularidade.
 */
@RestController
@RequestMapping("/api")
public class InformecertificadoirregularidadeResource {

    private final Logger log = LoggerFactory.getLogger(InformecertificadoirregularidadeResource.class);

    private static final String ENTITY_NAME = "informecertificadoirregularidade";
        
    private final InformecertificadoirregularidadeRepository informecertificadoirregularidadeRepository;

    private final InformecertificadoirregularidadeMapper informecertificadoirregularidadeMapper;

    public InformecertificadoirregularidadeResource(InformecertificadoirregularidadeRepository informecertificadoirregularidadeRepository, InformecertificadoirregularidadeMapper informecertificadoirregularidadeMapper) {
        this.informecertificadoirregularidadeRepository = informecertificadoirregularidadeRepository;
        this.informecertificadoirregularidadeMapper = informecertificadoirregularidadeMapper;
    }

    /**
     * POST  /informecertificadoirregularidades : Create a new informecertificadoirregularidade.
     *
     * @param informecertificadoirregularidadeDTO the informecertificadoirregularidadeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new informecertificadoirregularidadeDTO, or with status 400 (Bad Request) if the informecertificadoirregularidade has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/informecertificadoirregularidades")
    @Timed
    public ResponseEntity<InformecertificadoirregularidadeDTO> createInformecertificadoirregularidade(@RequestBody InformecertificadoirregularidadeDTO informecertificadoirregularidadeDTO) throws URISyntaxException {
        log.debug("REST request to save Informecertificadoirregularidade : {}", informecertificadoirregularidadeDTO);
        if (informecertificadoirregularidadeDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new informecertificadoirregularidade cannot already have an ID")).body(null);
        }
        Informecertificadoirregularidade informecertificadoirregularidade = informecertificadoirregularidadeMapper.toEntity(informecertificadoirregularidadeDTO);
        informecertificadoirregularidade = informecertificadoirregularidadeRepository.save(informecertificadoirregularidade);
        InformecertificadoirregularidadeDTO result = informecertificadoirregularidadeMapper.toDto(informecertificadoirregularidade);
        return ResponseEntity.created(new URI("/api/informecertificadoirregularidades/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /informecertificadoirregularidades : Updates an existing informecertificadoirregularidade.
     *
     * @param informecertificadoirregularidadeDTO the informecertificadoirregularidadeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated informecertificadoirregularidadeDTO,
     * or with status 400 (Bad Request) if the informecertificadoirregularidadeDTO is not valid,
     * or with status 500 (Internal Server Error) if the informecertificadoirregularidadeDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/informecertificadoirregularidades")
    @Timed
    public ResponseEntity<InformecertificadoirregularidadeDTO> updateInformecertificadoirregularidade(@RequestBody InformecertificadoirregularidadeDTO informecertificadoirregularidadeDTO) throws URISyntaxException {
        log.debug("REST request to update Informecertificadoirregularidade : {}", informecertificadoirregularidadeDTO);
        if (informecertificadoirregularidadeDTO.getId() == null) {
            return createInformecertificadoirregularidade(informecertificadoirregularidadeDTO);
        }
        Informecertificadoirregularidade informecertificadoirregularidade = informecertificadoirregularidadeMapper.toEntity(informecertificadoirregularidadeDTO);
        informecertificadoirregularidade = informecertificadoirregularidadeRepository.save(informecertificadoirregularidade);
        InformecertificadoirregularidadeDTO result = informecertificadoirregularidadeMapper.toDto(informecertificadoirregularidade);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, informecertificadoirregularidadeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /informecertificadoirregularidades : get all the informecertificadoirregularidades.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of informecertificadoirregularidades in body
     */
    @GetMapping("/informecertificadoirregularidades")
    @Timed
    public List<InformecertificadoirregularidadeDTO> getAllInformecertificadoirregularidades() {
        log.debug("REST request to get all Informecertificadoirregularidades");
        List<Informecertificadoirregularidade> informecertificadoirregularidades = informecertificadoirregularidadeRepository.findAll();
        return informecertificadoirregularidadeMapper.toDto(informecertificadoirregularidades);
    }

    /**
     * GET  /informecertificadoirregularidades/:id : get the "id" informecertificadoirregularidade.
     *
     * @param id the id of the informecertificadoirregularidadeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the informecertificadoirregularidadeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/informecertificadoirregularidades/{id}")
    @Timed
    public ResponseEntity<InformecertificadoirregularidadeDTO> getInformecertificadoirregularidade(@PathVariable Long id) {
        log.debug("REST request to get Informecertificadoirregularidade : {}", id);
        Informecertificadoirregularidade informecertificadoirregularidade = informecertificadoirregularidadeRepository.findOne(id);
        InformecertificadoirregularidadeDTO informecertificadoirregularidadeDTO = informecertificadoirregularidadeMapper.toDto(informecertificadoirregularidade);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(informecertificadoirregularidadeDTO));
    }

    /**
     * DELETE  /informecertificadoirregularidades/:id : delete the "id" informecertificadoirregularidade.
     *
     * @param id the id of the informecertificadoirregularidadeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/informecertificadoirregularidades/{id}")
    @Timed
    public ResponseEntity<Void> deleteInformecertificadoirregularidade(@PathVariable Long id) {
        log.debug("REST request to delete Informecertificadoirregularidade : {}", id);
        informecertificadoirregularidadeRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
