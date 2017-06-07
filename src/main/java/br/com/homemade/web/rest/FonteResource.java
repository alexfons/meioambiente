package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Fonte;

import br.com.homemade.repository.FonteRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.FonteDTO;
import br.com.homemade.service.mapper.FonteMapper;
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
 * REST controller for managing Fonte.
 */
@RestController
@RequestMapping("/api")
public class FonteResource {

    private final Logger log = LoggerFactory.getLogger(FonteResource.class);

    private static final String ENTITY_NAME = "fonte";
        
    private final FonteRepository fonteRepository;

    private final FonteMapper fonteMapper;

    public FonteResource(FonteRepository fonteRepository, FonteMapper fonteMapper) {
        this.fonteRepository = fonteRepository;
        this.fonteMapper = fonteMapper;
    }

    /**
     * POST  /fontes : Create a new fonte.
     *
     * @param fonteDTO the fonteDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fonteDTO, or with status 400 (Bad Request) if the fonte has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/fontes")
    @Timed
    public ResponseEntity<FonteDTO> createFonte(@RequestBody FonteDTO fonteDTO) throws URISyntaxException {
        log.debug("REST request to save Fonte : {}", fonteDTO);
        if (fonteDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new fonte cannot already have an ID")).body(null);
        }
        Fonte fonte = fonteMapper.toEntity(fonteDTO);
        fonte = fonteRepository.save(fonte);
        FonteDTO result = fonteMapper.toDto(fonte);
        return ResponseEntity.created(new URI("/api/fontes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /fontes : Updates an existing fonte.
     *
     * @param fonteDTO the fonteDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fonteDTO,
     * or with status 400 (Bad Request) if the fonteDTO is not valid,
     * or with status 500 (Internal Server Error) if the fonteDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/fontes")
    @Timed
    public ResponseEntity<FonteDTO> updateFonte(@RequestBody FonteDTO fonteDTO) throws URISyntaxException {
        log.debug("REST request to update Fonte : {}", fonteDTO);
        if (fonteDTO.getId() == null) {
            return createFonte(fonteDTO);
        }
        Fonte fonte = fonteMapper.toEntity(fonteDTO);
        fonte = fonteRepository.save(fonte);
        FonteDTO result = fonteMapper.toDto(fonte);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fonteDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /fontes : get all the fontes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of fontes in body
     */
    @GetMapping("/fontes")
    @Timed
    public List<FonteDTO> getAllFontes() {
        log.debug("REST request to get all Fontes");
        List<Fonte> fontes = fonteRepository.findAll();
        return fonteMapper.toDto(fontes);
    }

    /**
     * GET  /fontes/:id : get the "id" fonte.
     *
     * @param id the id of the fonteDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fonteDTO, or with status 404 (Not Found)
     */
    @GetMapping("/fontes/{id}")
    @Timed
    public ResponseEntity<FonteDTO> getFonte(@PathVariable Long id) {
        log.debug("REST request to get Fonte : {}", id);
        Fonte fonte = fonteRepository.findOne(id);
        FonteDTO fonteDTO = fonteMapper.toDto(fonte);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(fonteDTO));
    }

    /**
     * DELETE  /fontes/:id : delete the "id" fonte.
     *
     * @param id the id of the fonteDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/fontes/{id}")
    @Timed
    public ResponseEntity<Void> deleteFonte(@PathVariable Long id) {
        log.debug("REST request to delete Fonte : {}", id);
        fonteRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
