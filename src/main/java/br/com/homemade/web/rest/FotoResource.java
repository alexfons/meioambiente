package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Foto;

import br.com.homemade.repository.FotoRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.web.rest.util.PaginationUtil;
import br.com.homemade.service.dto.FotoDTO;
import br.com.homemade.service.mapper.FotoMapper;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Foto.
 */
@RestController
@RequestMapping("/api")
public class FotoResource {

    private final Logger log = LoggerFactory.getLogger(FotoResource.class);

    private static final String ENTITY_NAME = "foto";
        
    private final FotoRepository fotoRepository;

    private final FotoMapper fotoMapper;

    public FotoResource(FotoRepository fotoRepository, FotoMapper fotoMapper) {
        this.fotoRepository = fotoRepository;
        this.fotoMapper = fotoMapper;
    }

    /**
     * POST  /fotos : Create a new foto.
     *
     * @param fotoDTO the fotoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fotoDTO, or with status 400 (Bad Request) if the foto has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/fotos")
    @Timed
    public ResponseEntity<FotoDTO> createFoto(@RequestBody FotoDTO fotoDTO) throws URISyntaxException {
        log.debug("REST request to save Foto : {}", fotoDTO);
        if (fotoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new foto cannot already have an ID")).body(null);
        }
        Foto foto = fotoMapper.toEntity(fotoDTO);
        foto = fotoRepository.save(foto);
        FotoDTO result = fotoMapper.toDto(foto);
        return ResponseEntity.created(new URI("/api/fotos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /fotos : Updates an existing foto.
     *
     * @param fotoDTO the fotoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fotoDTO,
     * or with status 400 (Bad Request) if the fotoDTO is not valid,
     * or with status 500 (Internal Server Error) if the fotoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/fotos")
    @Timed
    public ResponseEntity<FotoDTO> updateFoto(@RequestBody FotoDTO fotoDTO) throws URISyntaxException {
        log.debug("REST request to update Foto : {}", fotoDTO);
        if (fotoDTO.getId() == null) {
            return createFoto(fotoDTO);
        }
        Foto foto = fotoMapper.toEntity(fotoDTO);
        foto = fotoRepository.save(foto);
        FotoDTO result = fotoMapper.toDto(foto);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fotoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /fotos : get all the fotos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of fotos in body
     */
    @GetMapping("/fotos")
    @Timed
    public ResponseEntity<List<FotoDTO>> getAllFotos(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Fotos");
        Page<Foto> page = fotoRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/fotos");
        return new ResponseEntity<>(fotoMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /fotos/:id : get the "id" foto.
     *
     * @param id the id of the fotoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fotoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/fotos/{id}")
    @Timed
    public ResponseEntity<FotoDTO> getFoto(@PathVariable Long id) {
        log.debug("REST request to get Foto : {}", id);
        Foto foto = fotoRepository.findOne(id);
        FotoDTO fotoDTO = fotoMapper.toDto(foto);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(fotoDTO));
    }

    /**
     * DELETE  /fotos/:id : delete the "id" foto.
     *
     * @param id the id of the fotoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/fotos/{id}")
    @Timed
    public ResponseEntity<Void> deleteFoto(@PathVariable Long id) {
        log.debug("REST request to delete Foto : {}", id);
        fotoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
