package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.NotificacaoCertifIrreg;

import br.com.homemade.repository.NotificacaoCertifIrregRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.NotificacaoCertifIrregDTO;
import br.com.homemade.service.mapper.NotificacaoCertifIrregMapper;
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
 * REST controller for managing NotificacaoCertifIrreg.
 */
@RestController
@RequestMapping("/api")
public class NotificacaoCertifIrregResource {

    private final Logger log = LoggerFactory.getLogger(NotificacaoCertifIrregResource.class);

    private static final String ENTITY_NAME = "notificacaoCertifIrreg";
        
    private final NotificacaoCertifIrregRepository notificacaoCertifIrregRepository;

    private final NotificacaoCertifIrregMapper notificacaoCertifIrregMapper;

    public NotificacaoCertifIrregResource(NotificacaoCertifIrregRepository notificacaoCertifIrregRepository, NotificacaoCertifIrregMapper notificacaoCertifIrregMapper) {
        this.notificacaoCertifIrregRepository = notificacaoCertifIrregRepository;
        this.notificacaoCertifIrregMapper = notificacaoCertifIrregMapper;
    }

    /**
     * POST  /notificacao-certif-irregs : Create a new notificacaoCertifIrreg.
     *
     * @param notificacaoCertifIrregDTO the notificacaoCertifIrregDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new notificacaoCertifIrregDTO, or with status 400 (Bad Request) if the notificacaoCertifIrreg has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/notificacao-certif-irregs")
    @Timed
    public ResponseEntity<NotificacaoCertifIrregDTO> createNotificacaoCertifIrreg(@RequestBody NotificacaoCertifIrregDTO notificacaoCertifIrregDTO) throws URISyntaxException {
        log.debug("REST request to save NotificacaoCertifIrreg : {}", notificacaoCertifIrregDTO);
        if (notificacaoCertifIrregDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new notificacaoCertifIrreg cannot already have an ID")).body(null);
        }
        NotificacaoCertifIrreg notificacaoCertifIrreg = notificacaoCertifIrregMapper.toEntity(notificacaoCertifIrregDTO);
        notificacaoCertifIrreg = notificacaoCertifIrregRepository.save(notificacaoCertifIrreg);
        NotificacaoCertifIrregDTO result = notificacaoCertifIrregMapper.toDto(notificacaoCertifIrreg);
        return ResponseEntity.created(new URI("/api/notificacao-certif-irregs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /notificacao-certif-irregs : Updates an existing notificacaoCertifIrreg.
     *
     * @param notificacaoCertifIrregDTO the notificacaoCertifIrregDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated notificacaoCertifIrregDTO,
     * or with status 400 (Bad Request) if the notificacaoCertifIrregDTO is not valid,
     * or with status 500 (Internal Server Error) if the notificacaoCertifIrregDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/notificacao-certif-irregs")
    @Timed
    public ResponseEntity<NotificacaoCertifIrregDTO> updateNotificacaoCertifIrreg(@RequestBody NotificacaoCertifIrregDTO notificacaoCertifIrregDTO) throws URISyntaxException {
        log.debug("REST request to update NotificacaoCertifIrreg : {}", notificacaoCertifIrregDTO);
        if (notificacaoCertifIrregDTO.getId() == null) {
            return createNotificacaoCertifIrreg(notificacaoCertifIrregDTO);
        }
        NotificacaoCertifIrreg notificacaoCertifIrreg = notificacaoCertifIrregMapper.toEntity(notificacaoCertifIrregDTO);
        notificacaoCertifIrreg = notificacaoCertifIrregRepository.save(notificacaoCertifIrreg);
        NotificacaoCertifIrregDTO result = notificacaoCertifIrregMapper.toDto(notificacaoCertifIrreg);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, notificacaoCertifIrregDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /notificacao-certif-irregs : get all the notificacaoCertifIrregs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of notificacaoCertifIrregs in body
     */
    @GetMapping("/notificacao-certif-irregs")
    @Timed
    public List<NotificacaoCertifIrregDTO> getAllNotificacaoCertifIrregs() {
        log.debug("REST request to get all NotificacaoCertifIrregs");
        List<NotificacaoCertifIrreg> notificacaoCertifIrregs = notificacaoCertifIrregRepository.findAll();
        return notificacaoCertifIrregMapper.toDto(notificacaoCertifIrregs);
    }

    /**
     * GET  /notificacao-certif-irregs/:id : get the "id" notificacaoCertifIrreg.
     *
     * @param id the id of the notificacaoCertifIrregDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the notificacaoCertifIrregDTO, or with status 404 (Not Found)
     */
    @GetMapping("/notificacao-certif-irregs/{id}")
    @Timed
    public ResponseEntity<NotificacaoCertifIrregDTO> getNotificacaoCertifIrreg(@PathVariable Long id) {
        log.debug("REST request to get NotificacaoCertifIrreg : {}", id);
        NotificacaoCertifIrreg notificacaoCertifIrreg = notificacaoCertifIrregRepository.findOne(id);
        NotificacaoCertifIrregDTO notificacaoCertifIrregDTO = notificacaoCertifIrregMapper.toDto(notificacaoCertifIrreg);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(notificacaoCertifIrregDTO));
    }

    /**
     * DELETE  /notificacao-certif-irregs/:id : delete the "id" notificacaoCertifIrreg.
     *
     * @param id the id of the notificacaoCertifIrregDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/notificacao-certif-irregs/{id}")
    @Timed
    public ResponseEntity<Void> deleteNotificacaoCertifIrreg(@PathVariable Long id) {
        log.debug("REST request to delete NotificacaoCertifIrreg : {}", id);
        notificacaoCertifIrregRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
