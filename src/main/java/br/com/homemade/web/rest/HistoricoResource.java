package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Historico;

import br.com.homemade.repository.HistoricoRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.HistoricoDTO;
import br.com.homemade.service.mapper.HistoricoMapper;
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
 * REST controller for managing Historico.
 */
@RestController
@RequestMapping("/api")
public class HistoricoResource {

    private final Logger log = LoggerFactory.getLogger(HistoricoResource.class);

    private static final String ENTITY_NAME = "historico";
        
    private final HistoricoRepository historicoRepository;

    private final HistoricoMapper historicoMapper;

    public HistoricoResource(HistoricoRepository historicoRepository, HistoricoMapper historicoMapper) {
        this.historicoRepository = historicoRepository;
        this.historicoMapper = historicoMapper;
    }

    /**
     * POST  /historicos : Create a new historico.
     *
     * @param historicoDTO the historicoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new historicoDTO, or with status 400 (Bad Request) if the historico has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/historicos")
    @Timed
    public ResponseEntity<HistoricoDTO> createHistorico(@RequestBody HistoricoDTO historicoDTO) throws URISyntaxException {
        log.debug("REST request to save Historico : {}", historicoDTO);
        if (historicoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new historico cannot already have an ID")).body(null);
        }
        Historico historico = historicoMapper.toEntity(historicoDTO);
        historico = historicoRepository.save(historico);
        HistoricoDTO result = historicoMapper.toDto(historico);
        return ResponseEntity.created(new URI("/api/historicos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /historicos : Updates an existing historico.
     *
     * @param historicoDTO the historicoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated historicoDTO,
     * or with status 400 (Bad Request) if the historicoDTO is not valid,
     * or with status 500 (Internal Server Error) if the historicoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/historicos")
    @Timed
    public ResponseEntity<HistoricoDTO> updateHistorico(@RequestBody HistoricoDTO historicoDTO) throws URISyntaxException {
        log.debug("REST request to update Historico : {}", historicoDTO);
        if (historicoDTO.getId() == null) {
            return createHistorico(historicoDTO);
        }
        Historico historico = historicoMapper.toEntity(historicoDTO);
        historico = historicoRepository.save(historico);
        HistoricoDTO result = historicoMapper.toDto(historico);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, historicoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /historicos : get all the historicos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of historicos in body
     */
    @GetMapping("/historicos")
    @Timed
    public List<HistoricoDTO> getAllHistoricos() {
        log.debug("REST request to get all Historicos");
        List<Historico> historicos = historicoRepository.findAll();
        return historicoMapper.toDto(historicos);
    }

    /**
     * GET  /historicos/:id : get the "id" historico.
     *
     * @param id the id of the historicoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the historicoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/historicos/{id}")
    @Timed
    public ResponseEntity<HistoricoDTO> getHistorico(@PathVariable Long id) {
        log.debug("REST request to get Historico : {}", id);
        Historico historico = historicoRepository.findOne(id);
        HistoricoDTO historicoDTO = historicoMapper.toDto(historico);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(historicoDTO));
    }

    /**
     * DELETE  /historicos/:id : delete the "id" historico.
     *
     * @param id the id of the historicoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/historicos/{id}")
    @Timed
    public ResponseEntity<Void> deleteHistorico(@PathVariable Long id) {
        log.debug("REST request to delete Historico : {}", id);
        historicoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
