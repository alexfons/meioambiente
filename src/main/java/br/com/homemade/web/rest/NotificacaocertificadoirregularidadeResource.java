package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Notificacaocertificadoirregularidade;

import br.com.homemade.repository.NotificacaocertificadoirregularidadeRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.NotificacaocertificadoirregularidadeDTO;
import br.com.homemade.service.mapper.NotificacaocertificadoirregularidadeMapper;
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
 * REST controller for managing Notificacaocertificadoirregularidade.
 */
@RestController
@RequestMapping("/api")
public class NotificacaocertificadoirregularidadeResource {

    private final Logger log = LoggerFactory.getLogger(NotificacaocertificadoirregularidadeResource.class);

    private static final String ENTITY_NAME = "notificacaocertificadoirregularidade";
        
    private final NotificacaocertificadoirregularidadeRepository notificacaocertificadoirregularidadeRepository;

    private final NotificacaocertificadoirregularidadeMapper notificacaocertificadoirregularidadeMapper;

    public NotificacaocertificadoirregularidadeResource(NotificacaocertificadoirregularidadeRepository notificacaocertificadoirregularidadeRepository, NotificacaocertificadoirregularidadeMapper notificacaocertificadoirregularidadeMapper) {
        this.notificacaocertificadoirregularidadeRepository = notificacaocertificadoirregularidadeRepository;
        this.notificacaocertificadoirregularidadeMapper = notificacaocertificadoirregularidadeMapper;
    }

    /**
     * POST  /notificacaocertificadoirregularidades : Create a new notificacaocertificadoirregularidade.
     *
     * @param notificacaocertificadoirregularidadeDTO the notificacaocertificadoirregularidadeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new notificacaocertificadoirregularidadeDTO, or with status 400 (Bad Request) if the notificacaocertificadoirregularidade has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/notificacaocertificadoirregularidades")
    @Timed
    public ResponseEntity<NotificacaocertificadoirregularidadeDTO> createNotificacaocertificadoirregularidade(@RequestBody NotificacaocertificadoirregularidadeDTO notificacaocertificadoirregularidadeDTO) throws URISyntaxException {
        log.debug("REST request to save Notificacaocertificadoirregularidade : {}", notificacaocertificadoirregularidadeDTO);
        if (notificacaocertificadoirregularidadeDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new notificacaocertificadoirregularidade cannot already have an ID")).body(null);
        }
        Notificacaocertificadoirregularidade notificacaocertificadoirregularidade = notificacaocertificadoirregularidadeMapper.toEntity(notificacaocertificadoirregularidadeDTO);
        notificacaocertificadoirregularidade = notificacaocertificadoirregularidadeRepository.save(notificacaocertificadoirregularidade);
        NotificacaocertificadoirregularidadeDTO result = notificacaocertificadoirregularidadeMapper.toDto(notificacaocertificadoirregularidade);
        return ResponseEntity.created(new URI("/api/notificacaocertificadoirregularidades/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /notificacaocertificadoirregularidades : Updates an existing notificacaocertificadoirregularidade.
     *
     * @param notificacaocertificadoirregularidadeDTO the notificacaocertificadoirregularidadeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated notificacaocertificadoirregularidadeDTO,
     * or with status 400 (Bad Request) if the notificacaocertificadoirregularidadeDTO is not valid,
     * or with status 500 (Internal Server Error) if the notificacaocertificadoirregularidadeDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/notificacaocertificadoirregularidades")
    @Timed
    public ResponseEntity<NotificacaocertificadoirregularidadeDTO> updateNotificacaocertificadoirregularidade(@RequestBody NotificacaocertificadoirregularidadeDTO notificacaocertificadoirregularidadeDTO) throws URISyntaxException {
        log.debug("REST request to update Notificacaocertificadoirregularidade : {}", notificacaocertificadoirregularidadeDTO);
        if (notificacaocertificadoirregularidadeDTO.getId() == null) {
            return createNotificacaocertificadoirregularidade(notificacaocertificadoirregularidadeDTO);
        }
        Notificacaocertificadoirregularidade notificacaocertificadoirregularidade = notificacaocertificadoirregularidadeMapper.toEntity(notificacaocertificadoirregularidadeDTO);
        notificacaocertificadoirregularidade = notificacaocertificadoirregularidadeRepository.save(notificacaocertificadoirregularidade);
        NotificacaocertificadoirregularidadeDTO result = notificacaocertificadoirregularidadeMapper.toDto(notificacaocertificadoirregularidade);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, notificacaocertificadoirregularidadeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /notificacaocertificadoirregularidades : get all the notificacaocertificadoirregularidades.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of notificacaocertificadoirregularidades in body
     */
    @GetMapping("/notificacaocertificadoirregularidades")
    @Timed
    public List<NotificacaocertificadoirregularidadeDTO> getAllNotificacaocertificadoirregularidades() {
        log.debug("REST request to get all Notificacaocertificadoirregularidades");
        List<Notificacaocertificadoirregularidade> notificacaocertificadoirregularidades = notificacaocertificadoirregularidadeRepository.findAll();
        return notificacaocertificadoirregularidadeMapper.toDto(notificacaocertificadoirregularidades);
    }

    /**
     * GET  /notificacaocertificadoirregularidades/:id : get the "id" notificacaocertificadoirregularidade.
     *
     * @param id the id of the notificacaocertificadoirregularidadeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the notificacaocertificadoirregularidadeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/notificacaocertificadoirregularidades/{id}")
    @Timed
    public ResponseEntity<NotificacaocertificadoirregularidadeDTO> getNotificacaocertificadoirregularidade(@PathVariable Long id) {
        log.debug("REST request to get Notificacaocertificadoirregularidade : {}", id);
        Notificacaocertificadoirregularidade notificacaocertificadoirregularidade = notificacaocertificadoirregularidadeRepository.findOne(id);
        NotificacaocertificadoirregularidadeDTO notificacaocertificadoirregularidadeDTO = notificacaocertificadoirregularidadeMapper.toDto(notificacaocertificadoirregularidade);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(notificacaocertificadoirregularidadeDTO));
    }

    /**
     * DELETE  /notificacaocertificadoirregularidades/:id : delete the "id" notificacaocertificadoirregularidade.
     *
     * @param id the id of the notificacaocertificadoirregularidadeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/notificacaocertificadoirregularidades/{id}")
    @Timed
    public ResponseEntity<Void> deleteNotificacaocertificadoirregularidade(@PathVariable Long id) {
        log.debug("REST request to delete Notificacaocertificadoirregularidade : {}", id);
        notificacaocertificadoirregularidadeRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
