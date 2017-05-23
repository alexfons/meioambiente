package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Participante;

import br.com.homemade.repository.ParticipanteRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.ParticipanteDTO;
import br.com.homemade.service.mapper.ParticipanteMapper;
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
 * REST controller for managing Participante.
 */
@RestController
@RequestMapping("/api")
public class ParticipanteResource {

    private final Logger log = LoggerFactory.getLogger(ParticipanteResource.class);

    private static final String ENTITY_NAME = "participante";
        
    private final ParticipanteRepository participanteRepository;

    private final ParticipanteMapper participanteMapper;

    public ParticipanteResource(ParticipanteRepository participanteRepository, ParticipanteMapper participanteMapper) {
        this.participanteRepository = participanteRepository;
        this.participanteMapper = participanteMapper;
    }

    /**
     * POST  /participantes : Create a new participante.
     *
     * @param participanteDTO the participanteDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new participanteDTO, or with status 400 (Bad Request) if the participante has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/participantes")
    @Timed
    public ResponseEntity<ParticipanteDTO> createParticipante(@RequestBody ParticipanteDTO participanteDTO) throws URISyntaxException {
        log.debug("REST request to save Participante : {}", participanteDTO);
        if (participanteDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new participante cannot already have an ID")).body(null);
        }
        Participante participante = participanteMapper.toEntity(participanteDTO);
        participante = participanteRepository.save(participante);
        ParticipanteDTO result = participanteMapper.toDto(participante);
        return ResponseEntity.created(new URI("/api/participantes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /participantes : Updates an existing participante.
     *
     * @param participanteDTO the participanteDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated participanteDTO,
     * or with status 400 (Bad Request) if the participanteDTO is not valid,
     * or with status 500 (Internal Server Error) if the participanteDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/participantes")
    @Timed
    public ResponseEntity<ParticipanteDTO> updateParticipante(@RequestBody ParticipanteDTO participanteDTO) throws URISyntaxException {
        log.debug("REST request to update Participante : {}", participanteDTO);
        if (participanteDTO.getId() == null) {
            return createParticipante(participanteDTO);
        }
        Participante participante = participanteMapper.toEntity(participanteDTO);
        participante = participanteRepository.save(participante);
        ParticipanteDTO result = participanteMapper.toDto(participante);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, participanteDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /participantes : get all the participantes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of participantes in body
     */
    @GetMapping("/participantes")
    @Timed
    public List<ParticipanteDTO> getAllParticipantes() {
        log.debug("REST request to get all Participantes");
        List<Participante> participantes = participanteRepository.findAll();
        return participanteMapper.toDto(participantes);
    }

    /**
     * GET  /participantes/:id : get the "id" participante.
     *
     * @param id the id of the participanteDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the participanteDTO, or with status 404 (Not Found)
     */
    @GetMapping("/participantes/{id}")
    @Timed
    public ResponseEntity<ParticipanteDTO> getParticipante(@PathVariable Long id) {
        log.debug("REST request to get Participante : {}", id);
        Participante participante = participanteRepository.findOne(id);
        ParticipanteDTO participanteDTO = participanteMapper.toDto(participante);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(participanteDTO));
    }

    /**
     * DELETE  /participantes/:id : delete the "id" participante.
     *
     * @param id the id of the participanteDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/participantes/{id}")
    @Timed
    public ResponseEntity<Void> deleteParticipante(@PathVariable Long id) {
        log.debug("REST request to delete Participante : {}", id);
        participanteRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
