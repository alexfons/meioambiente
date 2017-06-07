package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Fisicografico;

import br.com.homemade.repository.FisicograficoRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.FisicograficoDTO;
import br.com.homemade.service.mapper.FisicograficoMapper;
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
 * REST controller for managing Fisicografico.
 */
@RestController
@RequestMapping("/api")
public class FisicograficoResource {

    private final Logger log = LoggerFactory.getLogger(FisicograficoResource.class);

    private static final String ENTITY_NAME = "fisicografico";
        
    private final FisicograficoRepository fisicograficoRepository;

    private final FisicograficoMapper fisicograficoMapper;

    public FisicograficoResource(FisicograficoRepository fisicograficoRepository, FisicograficoMapper fisicograficoMapper) {
        this.fisicograficoRepository = fisicograficoRepository;
        this.fisicograficoMapper = fisicograficoMapper;
    }

    /**
     * POST  /fisicograficos : Create a new fisicografico.
     *
     * @param fisicograficoDTO the fisicograficoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fisicograficoDTO, or with status 400 (Bad Request) if the fisicografico has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/fisicograficos")
    @Timed
    public ResponseEntity<FisicograficoDTO> createFisicografico(@RequestBody FisicograficoDTO fisicograficoDTO) throws URISyntaxException {
        log.debug("REST request to save Fisicografico : {}", fisicograficoDTO);
        if (fisicograficoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new fisicografico cannot already have an ID")).body(null);
        }
        Fisicografico fisicografico = fisicograficoMapper.toEntity(fisicograficoDTO);
        fisicografico = fisicograficoRepository.save(fisicografico);
        FisicograficoDTO result = fisicograficoMapper.toDto(fisicografico);
        return ResponseEntity.created(new URI("/api/fisicograficos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /fisicograficos : Updates an existing fisicografico.
     *
     * @param fisicograficoDTO the fisicograficoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fisicograficoDTO,
     * or with status 400 (Bad Request) if the fisicograficoDTO is not valid,
     * or with status 500 (Internal Server Error) if the fisicograficoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/fisicograficos")
    @Timed
    public ResponseEntity<FisicograficoDTO> updateFisicografico(@RequestBody FisicograficoDTO fisicograficoDTO) throws URISyntaxException {
        log.debug("REST request to update Fisicografico : {}", fisicograficoDTO);
        if (fisicograficoDTO.getId() == null) {
            return createFisicografico(fisicograficoDTO);
        }
        Fisicografico fisicografico = fisicograficoMapper.toEntity(fisicograficoDTO);
        fisicografico = fisicograficoRepository.save(fisicografico);
        FisicograficoDTO result = fisicograficoMapper.toDto(fisicografico);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fisicograficoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /fisicograficos : get all the fisicograficos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of fisicograficos in body
     */
    @GetMapping("/fisicograficos")
    @Timed
    public List<FisicograficoDTO> getAllFisicograficos() {
        log.debug("REST request to get all Fisicograficos");
        List<Fisicografico> fisicograficos = fisicograficoRepository.findAll();
        return fisicograficoMapper.toDto(fisicograficos);
    }

    /**
     * GET  /fisicograficos/:id : get the "id" fisicografico.
     *
     * @param id the id of the fisicograficoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fisicograficoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/fisicograficos/{id}")
    @Timed
    public ResponseEntity<FisicograficoDTO> getFisicografico(@PathVariable Long id) {
        log.debug("REST request to get Fisicografico : {}", id);
        Fisicografico fisicografico = fisicograficoRepository.findOne(id);
        FisicograficoDTO fisicograficoDTO = fisicograficoMapper.toDto(fisicografico);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(fisicograficoDTO));
    }

    /**
     * DELETE  /fisicograficos/:id : delete the "id" fisicografico.
     *
     * @param id the id of the fisicograficoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/fisicograficos/{id}")
    @Timed
    public ResponseEntity<Void> deleteFisicografico(@PathVariable Long id) {
        log.debug("REST request to delete Fisicografico : {}", id);
        fisicograficoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
