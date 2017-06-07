package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Manifestacao;

import br.com.homemade.repository.ManifestacaoRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.ManifestacaoDTO;
import br.com.homemade.service.mapper.ManifestacaoMapper;
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
 * REST controller for managing Manifestacao.
 */
@RestController
@RequestMapping("/api")
public class ManifestacaoResource {

    private final Logger log = LoggerFactory.getLogger(ManifestacaoResource.class);

    private static final String ENTITY_NAME = "manifestacao";
        
    private final ManifestacaoRepository manifestacaoRepository;

    private final ManifestacaoMapper manifestacaoMapper;

    public ManifestacaoResource(ManifestacaoRepository manifestacaoRepository, ManifestacaoMapper manifestacaoMapper) {
        this.manifestacaoRepository = manifestacaoRepository;
        this.manifestacaoMapper = manifestacaoMapper;
    }

    /**
     * POST  /manifestacaos : Create a new manifestacao.
     *
     * @param manifestacaoDTO the manifestacaoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new manifestacaoDTO, or with status 400 (Bad Request) if the manifestacao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/manifestacaos")
    @Timed
    public ResponseEntity<ManifestacaoDTO> createManifestacao(@RequestBody ManifestacaoDTO manifestacaoDTO) throws URISyntaxException {
        log.debug("REST request to save Manifestacao : {}", manifestacaoDTO);
        if (manifestacaoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new manifestacao cannot already have an ID")).body(null);
        }
        Manifestacao manifestacao = manifestacaoMapper.toEntity(manifestacaoDTO);
        manifestacao = manifestacaoRepository.save(manifestacao);
        ManifestacaoDTO result = manifestacaoMapper.toDto(manifestacao);
        return ResponseEntity.created(new URI("/api/manifestacaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /manifestacaos : Updates an existing manifestacao.
     *
     * @param manifestacaoDTO the manifestacaoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated manifestacaoDTO,
     * or with status 400 (Bad Request) if the manifestacaoDTO is not valid,
     * or with status 500 (Internal Server Error) if the manifestacaoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/manifestacaos")
    @Timed
    public ResponseEntity<ManifestacaoDTO> updateManifestacao(@RequestBody ManifestacaoDTO manifestacaoDTO) throws URISyntaxException {
        log.debug("REST request to update Manifestacao : {}", manifestacaoDTO);
        if (manifestacaoDTO.getId() == null) {
            return createManifestacao(manifestacaoDTO);
        }
        Manifestacao manifestacao = manifestacaoMapper.toEntity(manifestacaoDTO);
        manifestacao = manifestacaoRepository.save(manifestacao);
        ManifestacaoDTO result = manifestacaoMapper.toDto(manifestacao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, manifestacaoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /manifestacaos : get all the manifestacaos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of manifestacaos in body
     */
    @GetMapping("/manifestacaos")
    @Timed
    public List<ManifestacaoDTO> getAllManifestacaos() {
        log.debug("REST request to get all Manifestacaos");
        List<Manifestacao> manifestacaos = manifestacaoRepository.findAll();
        return manifestacaoMapper.toDto(manifestacaos);
    }

    /**
     * GET  /manifestacaos/:id : get the "id" manifestacao.
     *
     * @param id the id of the manifestacaoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the manifestacaoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/manifestacaos/{id}")
    @Timed
    public ResponseEntity<ManifestacaoDTO> getManifestacao(@PathVariable Long id) {
        log.debug("REST request to get Manifestacao : {}", id);
        Manifestacao manifestacao = manifestacaoRepository.findOne(id);
        ManifestacaoDTO manifestacaoDTO = manifestacaoMapper.toDto(manifestacao);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(manifestacaoDTO));
    }

    /**
     * DELETE  /manifestacaos/:id : delete the "id" manifestacao.
     *
     * @param id the id of the manifestacaoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/manifestacaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteManifestacao(@PathVariable Long id) {
        log.debug("REST request to delete Manifestacao : {}", id);
        manifestacaoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
