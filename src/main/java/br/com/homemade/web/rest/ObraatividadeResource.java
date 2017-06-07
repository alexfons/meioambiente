package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Obraatividade;

import br.com.homemade.repository.ObraatividadeRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.ObraatividadeDTO;
import br.com.homemade.service.mapper.ObraatividadeMapper;
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
 * REST controller for managing Obraatividade.
 */
@RestController
@RequestMapping("/api")
public class ObraatividadeResource {

    private final Logger log = LoggerFactory.getLogger(ObraatividadeResource.class);

    private static final String ENTITY_NAME = "obraatividade";
        
    private final ObraatividadeRepository obraatividadeRepository;

    private final ObraatividadeMapper obraatividadeMapper;

    public ObraatividadeResource(ObraatividadeRepository obraatividadeRepository, ObraatividadeMapper obraatividadeMapper) {
        this.obraatividadeRepository = obraatividadeRepository;
        this.obraatividadeMapper = obraatividadeMapper;
    }

    /**
     * POST  /obraatividades : Create a new obraatividade.
     *
     * @param obraatividadeDTO the obraatividadeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new obraatividadeDTO, or with status 400 (Bad Request) if the obraatividade has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/obraatividades")
    @Timed
    public ResponseEntity<ObraatividadeDTO> createObraatividade(@RequestBody ObraatividadeDTO obraatividadeDTO) throws URISyntaxException {
        log.debug("REST request to save Obraatividade : {}", obraatividadeDTO);
        if (obraatividadeDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new obraatividade cannot already have an ID")).body(null);
        }
        Obraatividade obraatividade = obraatividadeMapper.toEntity(obraatividadeDTO);
        obraatividade = obraatividadeRepository.save(obraatividade);
        ObraatividadeDTO result = obraatividadeMapper.toDto(obraatividade);
        return ResponseEntity.created(new URI("/api/obraatividades/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /obraatividades : Updates an existing obraatividade.
     *
     * @param obraatividadeDTO the obraatividadeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated obraatividadeDTO,
     * or with status 400 (Bad Request) if the obraatividadeDTO is not valid,
     * or with status 500 (Internal Server Error) if the obraatividadeDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/obraatividades")
    @Timed
    public ResponseEntity<ObraatividadeDTO> updateObraatividade(@RequestBody ObraatividadeDTO obraatividadeDTO) throws URISyntaxException {
        log.debug("REST request to update Obraatividade : {}", obraatividadeDTO);
        if (obraatividadeDTO.getId() == null) {
            return createObraatividade(obraatividadeDTO);
        }
        Obraatividade obraatividade = obraatividadeMapper.toEntity(obraatividadeDTO);
        obraatividade = obraatividadeRepository.save(obraatividade);
        ObraatividadeDTO result = obraatividadeMapper.toDto(obraatividade);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, obraatividadeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /obraatividades : get all the obraatividades.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of obraatividades in body
     */
    @GetMapping("/obraatividades")
    @Timed
    public List<ObraatividadeDTO> getAllObraatividades() {
        log.debug("REST request to get all Obraatividades");
        List<Obraatividade> obraatividades = obraatividadeRepository.findAll();
        return obraatividadeMapper.toDto(obraatividades);
    }

    /**
     * GET  /obraatividades/:id : get the "id" obraatividade.
     *
     * @param id the id of the obraatividadeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the obraatividadeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/obraatividades/{id}")
    @Timed
    public ResponseEntity<ObraatividadeDTO> getObraatividade(@PathVariable Long id) {
        log.debug("REST request to get Obraatividade : {}", id);
        Obraatividade obraatividade = obraatividadeRepository.findOne(id);
        ObraatividadeDTO obraatividadeDTO = obraatividadeMapper.toDto(obraatividade);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(obraatividadeDTO));
    }

    /**
     * DELETE  /obraatividades/:id : delete the "id" obraatividade.
     *
     * @param id the id of the obraatividadeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/obraatividades/{id}")
    @Timed
    public ResponseEntity<Void> deleteObraatividade(@PathVariable Long id) {
        log.debug("REST request to delete Obraatividade : {}", id);
        obraatividadeRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
