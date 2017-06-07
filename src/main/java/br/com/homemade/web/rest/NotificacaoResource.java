package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Notificacao;

import br.com.homemade.repository.NotificacaoRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.NotificacaoDTO;
import br.com.homemade.service.mapper.NotificacaoMapper;
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
 * REST controller for managing Notificacao.
 */
@RestController
@RequestMapping("/api")
public class NotificacaoResource {

    private final Logger log = LoggerFactory.getLogger(NotificacaoResource.class);

    private static final String ENTITY_NAME = "notificacao";
        
    private final NotificacaoRepository notificacaoRepository;

    private final NotificacaoMapper notificacaoMapper;

    public NotificacaoResource(NotificacaoRepository notificacaoRepository, NotificacaoMapper notificacaoMapper) {
        this.notificacaoRepository = notificacaoRepository;
        this.notificacaoMapper = notificacaoMapper;
    }

    /**
     * POST  /notificacaos : Create a new notificacao.
     *
     * @param notificacaoDTO the notificacaoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new notificacaoDTO, or with status 400 (Bad Request) if the notificacao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/notificacaos")
    @Timed
    public ResponseEntity<NotificacaoDTO> createNotificacao(@RequestBody NotificacaoDTO notificacaoDTO) throws URISyntaxException {
        log.debug("REST request to save Notificacao : {}", notificacaoDTO);
        if (notificacaoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new notificacao cannot already have an ID")).body(null);
        }
        Notificacao notificacao = notificacaoMapper.toEntity(notificacaoDTO);
        notificacao = notificacaoRepository.save(notificacao);
        NotificacaoDTO result = notificacaoMapper.toDto(notificacao);
        return ResponseEntity.created(new URI("/api/notificacaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /notificacaos : Updates an existing notificacao.
     *
     * @param notificacaoDTO the notificacaoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated notificacaoDTO,
     * or with status 400 (Bad Request) if the notificacaoDTO is not valid,
     * or with status 500 (Internal Server Error) if the notificacaoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/notificacaos")
    @Timed
    public ResponseEntity<NotificacaoDTO> updateNotificacao(@RequestBody NotificacaoDTO notificacaoDTO) throws URISyntaxException {
        log.debug("REST request to update Notificacao : {}", notificacaoDTO);
        if (notificacaoDTO.getId() == null) {
            return createNotificacao(notificacaoDTO);
        }
        Notificacao notificacao = notificacaoMapper.toEntity(notificacaoDTO);
        notificacao = notificacaoRepository.save(notificacao);
        NotificacaoDTO result = notificacaoMapper.toDto(notificacao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, notificacaoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /notificacaos : get all the notificacaos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of notificacaos in body
     */
    @GetMapping("/notificacaos")
    @Timed
    public List<NotificacaoDTO> getAllNotificacaos() {
        log.debug("REST request to get all Notificacaos");
        List<Notificacao> notificacaos = notificacaoRepository.findAllWithEagerRelationships();
        return notificacaoMapper.toDto(notificacaos);
    }

    /**
     * GET  /notificacaos/:id : get the "id" notificacao.
     *
     * @param id the id of the notificacaoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the notificacaoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/notificacaos/{id}")
    @Timed
    public ResponseEntity<NotificacaoDTO> getNotificacao(@PathVariable Long id) {
        log.debug("REST request to get Notificacao : {}", id);
        Notificacao notificacao = notificacaoRepository.findOneWithEagerRelationships(id);
        NotificacaoDTO notificacaoDTO = notificacaoMapper.toDto(notificacao);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(notificacaoDTO));
    }

    /**
     * DELETE  /notificacaos/:id : delete the "id" notificacao.
     *
     * @param id the id of the notificacaoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/notificacaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteNotificacao(@PathVariable Long id) {
        log.debug("REST request to delete Notificacao : {}", id);
        notificacaoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
