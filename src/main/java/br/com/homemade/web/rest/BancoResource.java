package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Banco;

import br.com.homemade.repository.BancoRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.BancoDTO;
import br.com.homemade.service.mapper.BancoMapper;
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
 * REST controller for managing Banco.
 */
@RestController
@RequestMapping("/api")
public class BancoResource {

    private final Logger log = LoggerFactory.getLogger(BancoResource.class);

    private static final String ENTITY_NAME = "banco";
        
    private final BancoRepository bancoRepository;

    private final BancoMapper bancoMapper;

    public BancoResource(BancoRepository bancoRepository, BancoMapper bancoMapper) {
        this.bancoRepository = bancoRepository;
        this.bancoMapper = bancoMapper;
    }

    /**
     * POST  /bancos : Create a new banco.
     *
     * @param bancoDTO the bancoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bancoDTO, or with status 400 (Bad Request) if the banco has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/bancos")
    @Timed
    public ResponseEntity<BancoDTO> createBanco(@RequestBody BancoDTO bancoDTO) throws URISyntaxException {
        log.debug("REST request to save Banco : {}", bancoDTO);
        if (bancoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new banco cannot already have an ID")).body(null);
        }
        Banco banco = bancoMapper.toEntity(bancoDTO);
        banco = bancoRepository.save(banco);
        BancoDTO result = bancoMapper.toDto(banco);
        return ResponseEntity.created(new URI("/api/bancos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /bancos : Updates an existing banco.
     *
     * @param bancoDTO the bancoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bancoDTO,
     * or with status 400 (Bad Request) if the bancoDTO is not valid,
     * or with status 500 (Internal Server Error) if the bancoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/bancos")
    @Timed
    public ResponseEntity<BancoDTO> updateBanco(@RequestBody BancoDTO bancoDTO) throws URISyntaxException {
        log.debug("REST request to update Banco : {}", bancoDTO);
        if (bancoDTO.getId() == null) {
            return createBanco(bancoDTO);
        }
        Banco banco = bancoMapper.toEntity(bancoDTO);
        banco = bancoRepository.save(banco);
        BancoDTO result = bancoMapper.toDto(banco);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, bancoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /bancos : get all the bancos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of bancos in body
     */
    @GetMapping("/bancos")
    @Timed
    public List<BancoDTO> getAllBancos() {
        log.debug("REST request to get all Bancos");
        List<Banco> bancos = bancoRepository.findAll();
        return bancoMapper.toDto(bancos);
    }

    /**
     * GET  /bancos/:id : get the "id" banco.
     *
     * @param id the id of the bancoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bancoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/bancos/{id}")
    @Timed
    public ResponseEntity<BancoDTO> getBanco(@PathVariable Long id) {
        log.debug("REST request to get Banco : {}", id);
        Banco banco = bancoRepository.findOne(id);
        BancoDTO bancoDTO = bancoMapper.toDto(banco);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(bancoDTO));
    }

    /**
     * DELETE  /bancos/:id : delete the "id" banco.
     *
     * @param id the id of the bancoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/bancos/{id}")
    @Timed
    public ResponseEntity<Void> deleteBanco(@PathVariable Long id) {
        log.debug("REST request to delete Banco : {}", id);
        bancoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
