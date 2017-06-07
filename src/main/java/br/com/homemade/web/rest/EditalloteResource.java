package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Editallote;

import br.com.homemade.repository.EditalloteRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.EditalloteDTO;
import br.com.homemade.service.mapper.EditalloteMapper;
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
 * REST controller for managing Editallote.
 */
@RestController
@RequestMapping("/api")
public class EditalloteResource {

    private final Logger log = LoggerFactory.getLogger(EditalloteResource.class);

    private static final String ENTITY_NAME = "editallote";
        
    private final EditalloteRepository editalloteRepository;

    private final EditalloteMapper editalloteMapper;

    public EditalloteResource(EditalloteRepository editalloteRepository, EditalloteMapper editalloteMapper) {
        this.editalloteRepository = editalloteRepository;
        this.editalloteMapper = editalloteMapper;
    }

    /**
     * POST  /editallotes : Create a new editallote.
     *
     * @param editalloteDTO the editalloteDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new editalloteDTO, or with status 400 (Bad Request) if the editallote has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/editallotes")
    @Timed
    public ResponseEntity<EditalloteDTO> createEditallote(@RequestBody EditalloteDTO editalloteDTO) throws URISyntaxException {
        log.debug("REST request to save Editallote : {}", editalloteDTO);
        if (editalloteDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new editallote cannot already have an ID")).body(null);
        }
        Editallote editallote = editalloteMapper.toEntity(editalloteDTO);
        editallote = editalloteRepository.save(editallote);
        EditalloteDTO result = editalloteMapper.toDto(editallote);
        return ResponseEntity.created(new URI("/api/editallotes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /editallotes : Updates an existing editallote.
     *
     * @param editalloteDTO the editalloteDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated editalloteDTO,
     * or with status 400 (Bad Request) if the editalloteDTO is not valid,
     * or with status 500 (Internal Server Error) if the editalloteDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/editallotes")
    @Timed
    public ResponseEntity<EditalloteDTO> updateEditallote(@RequestBody EditalloteDTO editalloteDTO) throws URISyntaxException {
        log.debug("REST request to update Editallote : {}", editalloteDTO);
        if (editalloteDTO.getId() == null) {
            return createEditallote(editalloteDTO);
        }
        Editallote editallote = editalloteMapper.toEntity(editalloteDTO);
        editallote = editalloteRepository.save(editallote);
        EditalloteDTO result = editalloteMapper.toDto(editallote);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, editalloteDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /editallotes : get all the editallotes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of editallotes in body
     */
    @GetMapping("/editallotes")
    @Timed
    public List<EditalloteDTO> getAllEditallotes() {
        log.debug("REST request to get all Editallotes");
        List<Editallote> editallotes = editalloteRepository.findAll();
        return editalloteMapper.toDto(editallotes);
    }

    /**
     * GET  /editallotes/:id : get the "id" editallote.
     *
     * @param id the id of the editalloteDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the editalloteDTO, or with status 404 (Not Found)
     */
    @GetMapping("/editallotes/{id}")
    @Timed
    public ResponseEntity<EditalloteDTO> getEditallote(@PathVariable Long id) {
        log.debug("REST request to get Editallote : {}", id);
        Editallote editallote = editalloteRepository.findOne(id);
        EditalloteDTO editalloteDTO = editalloteMapper.toDto(editallote);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(editalloteDTO));
    }

    /**
     * DELETE  /editallotes/:id : delete the "id" editallote.
     *
     * @param id the id of the editalloteDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/editallotes/{id}")
    @Timed
    public ResponseEntity<Void> deleteEditallote(@PathVariable Long id) {
        log.debug("REST request to delete Editallote : {}", id);
        editalloteRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
