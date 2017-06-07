package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Listamovimentacao;

import br.com.homemade.repository.ListamovimentacaoRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.ListamovimentacaoDTO;
import br.com.homemade.service.mapper.ListamovimentacaoMapper;
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
 * REST controller for managing Listamovimentacao.
 */
@RestController
@RequestMapping("/api")
public class ListamovimentacaoResource {

    private final Logger log = LoggerFactory.getLogger(ListamovimentacaoResource.class);

    private static final String ENTITY_NAME = "listamovimentacao";
        
    private final ListamovimentacaoRepository listamovimentacaoRepository;

    private final ListamovimentacaoMapper listamovimentacaoMapper;

    public ListamovimentacaoResource(ListamovimentacaoRepository listamovimentacaoRepository, ListamovimentacaoMapper listamovimentacaoMapper) {
        this.listamovimentacaoRepository = listamovimentacaoRepository;
        this.listamovimentacaoMapper = listamovimentacaoMapper;
    }

    /**
     * POST  /listamovimentacaos : Create a new listamovimentacao.
     *
     * @param listamovimentacaoDTO the listamovimentacaoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new listamovimentacaoDTO, or with status 400 (Bad Request) if the listamovimentacao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/listamovimentacaos")
    @Timed
    public ResponseEntity<ListamovimentacaoDTO> createListamovimentacao(@RequestBody ListamovimentacaoDTO listamovimentacaoDTO) throws URISyntaxException {
        log.debug("REST request to save Listamovimentacao : {}", listamovimentacaoDTO);
        if (listamovimentacaoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new listamovimentacao cannot already have an ID")).body(null);
        }
        Listamovimentacao listamovimentacao = listamovimentacaoMapper.toEntity(listamovimentacaoDTO);
        listamovimentacao = listamovimentacaoRepository.save(listamovimentacao);
        ListamovimentacaoDTO result = listamovimentacaoMapper.toDto(listamovimentacao);
        return ResponseEntity.created(new URI("/api/listamovimentacaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /listamovimentacaos : Updates an existing listamovimentacao.
     *
     * @param listamovimentacaoDTO the listamovimentacaoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated listamovimentacaoDTO,
     * or with status 400 (Bad Request) if the listamovimentacaoDTO is not valid,
     * or with status 500 (Internal Server Error) if the listamovimentacaoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/listamovimentacaos")
    @Timed
    public ResponseEntity<ListamovimentacaoDTO> updateListamovimentacao(@RequestBody ListamovimentacaoDTO listamovimentacaoDTO) throws URISyntaxException {
        log.debug("REST request to update Listamovimentacao : {}", listamovimentacaoDTO);
        if (listamovimentacaoDTO.getId() == null) {
            return createListamovimentacao(listamovimentacaoDTO);
        }
        Listamovimentacao listamovimentacao = listamovimentacaoMapper.toEntity(listamovimentacaoDTO);
        listamovimentacao = listamovimentacaoRepository.save(listamovimentacao);
        ListamovimentacaoDTO result = listamovimentacaoMapper.toDto(listamovimentacao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, listamovimentacaoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /listamovimentacaos : get all the listamovimentacaos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of listamovimentacaos in body
     */
    @GetMapping("/listamovimentacaos")
    @Timed
    public List<ListamovimentacaoDTO> getAllListamovimentacaos() {
        log.debug("REST request to get all Listamovimentacaos");
        List<Listamovimentacao> listamovimentacaos = listamovimentacaoRepository.findAll();
        return listamovimentacaoMapper.toDto(listamovimentacaos);
    }

    /**
     * GET  /listamovimentacaos/:id : get the "id" listamovimentacao.
     *
     * @param id the id of the listamovimentacaoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the listamovimentacaoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/listamovimentacaos/{id}")
    @Timed
    public ResponseEntity<ListamovimentacaoDTO> getListamovimentacao(@PathVariable Long id) {
        log.debug("REST request to get Listamovimentacao : {}", id);
        Listamovimentacao listamovimentacao = listamovimentacaoRepository.findOne(id);
        ListamovimentacaoDTO listamovimentacaoDTO = listamovimentacaoMapper.toDto(listamovimentacao);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(listamovimentacaoDTO));
    }

    /**
     * DELETE  /listamovimentacaos/:id : delete the "id" listamovimentacao.
     *
     * @param id the id of the listamovimentacaoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/listamovimentacaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteListamovimentacao(@PathVariable Long id) {
        log.debug("REST request to delete Listamovimentacao : {}", id);
        listamovimentacaoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
