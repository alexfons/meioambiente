package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Proposta;

import br.com.homemade.repository.PropostaRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.PropostaDTO;
import br.com.homemade.service.mapper.PropostaMapper;
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
 * REST controller for managing Proposta.
 */
@RestController
@RequestMapping("/api")
public class PropostaResource {

    private final Logger log = LoggerFactory.getLogger(PropostaResource.class);

    private static final String ENTITY_NAME = "proposta";
        
    private final PropostaRepository propostaRepository;

    private final PropostaMapper propostaMapper;

    public PropostaResource(PropostaRepository propostaRepository, PropostaMapper propostaMapper) {
        this.propostaRepository = propostaRepository;
        this.propostaMapper = propostaMapper;
    }

    /**
     * POST  /propostas : Create a new proposta.
     *
     * @param propostaDTO the propostaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new propostaDTO, or with status 400 (Bad Request) if the proposta has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/propostas")
    @Timed
    public ResponseEntity<PropostaDTO> createProposta(@RequestBody PropostaDTO propostaDTO) throws URISyntaxException {
        log.debug("REST request to save Proposta : {}", propostaDTO);
        if (propostaDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new proposta cannot already have an ID")).body(null);
        }
        Proposta proposta = propostaMapper.toEntity(propostaDTO);
        proposta = propostaRepository.save(proposta);
        PropostaDTO result = propostaMapper.toDto(proposta);
        return ResponseEntity.created(new URI("/api/propostas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /propostas : Updates an existing proposta.
     *
     * @param propostaDTO the propostaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated propostaDTO,
     * or with status 400 (Bad Request) if the propostaDTO is not valid,
     * or with status 500 (Internal Server Error) if the propostaDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/propostas")
    @Timed
    public ResponseEntity<PropostaDTO> updateProposta(@RequestBody PropostaDTO propostaDTO) throws URISyntaxException {
        log.debug("REST request to update Proposta : {}", propostaDTO);
        if (propostaDTO.getId() == null) {
            return createProposta(propostaDTO);
        }
        Proposta proposta = propostaMapper.toEntity(propostaDTO);
        proposta = propostaRepository.save(proposta);
        PropostaDTO result = propostaMapper.toDto(proposta);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, propostaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /propostas : get all the propostas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of propostas in body
     */
    @GetMapping("/propostas")
    @Timed
    public List<PropostaDTO> getAllPropostas() {
        log.debug("REST request to get all Propostas");
        List<Proposta> propostas = propostaRepository.findAll();
        return propostaMapper.toDto(propostas);
    }

    /**
     * GET  /propostas/:id : get the "id" proposta.
     *
     * @param id the id of the propostaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the propostaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/propostas/{id}")
    @Timed
    public ResponseEntity<PropostaDTO> getProposta(@PathVariable Long id) {
        log.debug("REST request to get Proposta : {}", id);
        Proposta proposta = propostaRepository.findOne(id);
        PropostaDTO propostaDTO = propostaMapper.toDto(proposta);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(propostaDTO));
    }

    /**
     * DELETE  /propostas/:id : delete the "id" proposta.
     *
     * @param id the id of the propostaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/propostas/{id}")
    @Timed
    public ResponseEntity<Void> deleteProposta(@PathVariable Long id) {
        log.debug("REST request to delete Proposta : {}", id);
        propostaRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
