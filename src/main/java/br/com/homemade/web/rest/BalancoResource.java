package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Balanco;

import br.com.homemade.repository.BalancoRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.BalancoDTO;
import br.com.homemade.service.mapper.BalancoMapper;
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
 * REST controller for managing Balanco.
 */
@RestController
@RequestMapping("/api")
public class BalancoResource {

    private final Logger log = LoggerFactory.getLogger(BalancoResource.class);

    private static final String ENTITY_NAME = "balanco";
        
    private final BalancoRepository balancoRepository;

    private final BalancoMapper balancoMapper;

    public BalancoResource(BalancoRepository balancoRepository, BalancoMapper balancoMapper) {
        this.balancoRepository = balancoRepository;
        this.balancoMapper = balancoMapper;
    }

    /**
     * POST  /balancos : Create a new balanco.
     *
     * @param balancoDTO the balancoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new balancoDTO, or with status 400 (Bad Request) if the balanco has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/balancos")
    @Timed
    public ResponseEntity<BalancoDTO> createBalanco(@RequestBody BalancoDTO balancoDTO) throws URISyntaxException {
        log.debug("REST request to save Balanco : {}", balancoDTO);
        if (balancoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new balanco cannot already have an ID")).body(null);
        }
        Balanco balanco = balancoMapper.toEntity(balancoDTO);
        balanco = balancoRepository.save(balanco);
        BalancoDTO result = balancoMapper.toDto(balanco);
        return ResponseEntity.created(new URI("/api/balancos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /balancos : Updates an existing balanco.
     *
     * @param balancoDTO the balancoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated balancoDTO,
     * or with status 400 (Bad Request) if the balancoDTO is not valid,
     * or with status 500 (Internal Server Error) if the balancoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/balancos")
    @Timed
    public ResponseEntity<BalancoDTO> updateBalanco(@RequestBody BalancoDTO balancoDTO) throws URISyntaxException {
        log.debug("REST request to update Balanco : {}", balancoDTO);
        if (balancoDTO.getId() == null) {
            return createBalanco(balancoDTO);
        }
        Balanco balanco = balancoMapper.toEntity(balancoDTO);
        balanco = balancoRepository.save(balanco);
        BalancoDTO result = balancoMapper.toDto(balanco);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, balancoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /balancos : get all the balancos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of balancos in body
     */
    @GetMapping("/balancos")
    @Timed
    public List<BalancoDTO> getAllBalancos() {
        log.debug("REST request to get all Balancos");
        List<Balanco> balancos = balancoRepository.findAll();
        return balancoMapper.toDto(balancos);
    }

    /**
     * GET  /balancos/:id : get the "id" balanco.
     *
     * @param id the id of the balancoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the balancoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/balancos/{id}")
    @Timed
    public ResponseEntity<BalancoDTO> getBalanco(@PathVariable Long id) {
        log.debug("REST request to get Balanco : {}", id);
        Balanco balanco = balancoRepository.findOne(id);
        BalancoDTO balancoDTO = balancoMapper.toDto(balanco);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(balancoDTO));
    }

    /**
     * DELETE  /balancos/:id : delete the "id" balanco.
     *
     * @param id the id of the balancoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/balancos/{id}")
    @Timed
    public ResponseEntity<Void> deleteBalanco(@PathVariable Long id) {
        log.debug("REST request to delete Balanco : {}", id);
        balancoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
