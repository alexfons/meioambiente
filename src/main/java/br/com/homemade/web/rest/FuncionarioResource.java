package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Funcionario;

import br.com.homemade.repository.FuncionarioRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.FuncionarioDTO;
import br.com.homemade.service.mapper.FuncionarioMapper;
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
 * REST controller for managing Funcionario.
 */
@RestController
@RequestMapping("/api")
public class FuncionarioResource {

    private final Logger log = LoggerFactory.getLogger(FuncionarioResource.class);

    private static final String ENTITY_NAME = "funcionario";
        
    private final FuncionarioRepository funcionarioRepository;

    private final FuncionarioMapper funcionarioMapper;

    public FuncionarioResource(FuncionarioRepository funcionarioRepository, FuncionarioMapper funcionarioMapper) {
        this.funcionarioRepository = funcionarioRepository;
        this.funcionarioMapper = funcionarioMapper;
    }

    /**
     * POST  /funcionarios : Create a new funcionario.
     *
     * @param funcionarioDTO the funcionarioDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new funcionarioDTO, or with status 400 (Bad Request) if the funcionario has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/funcionarios")
    @Timed
    public ResponseEntity<FuncionarioDTO> createFuncionario(@RequestBody FuncionarioDTO funcionarioDTO) throws URISyntaxException {
        log.debug("REST request to save Funcionario : {}", funcionarioDTO);
        if (funcionarioDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new funcionario cannot already have an ID")).body(null);
        }
        Funcionario funcionario = funcionarioMapper.toEntity(funcionarioDTO);
        funcionario = funcionarioRepository.save(funcionario);
        FuncionarioDTO result = funcionarioMapper.toDto(funcionario);
        return ResponseEntity.created(new URI("/api/funcionarios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /funcionarios : Updates an existing funcionario.
     *
     * @param funcionarioDTO the funcionarioDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated funcionarioDTO,
     * or with status 400 (Bad Request) if the funcionarioDTO is not valid,
     * or with status 500 (Internal Server Error) if the funcionarioDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/funcionarios")
    @Timed
    public ResponseEntity<FuncionarioDTO> updateFuncionario(@RequestBody FuncionarioDTO funcionarioDTO) throws URISyntaxException {
        log.debug("REST request to update Funcionario : {}", funcionarioDTO);
        if (funcionarioDTO.getId() == null) {
            return createFuncionario(funcionarioDTO);
        }
        Funcionario funcionario = funcionarioMapper.toEntity(funcionarioDTO);
        funcionario = funcionarioRepository.save(funcionario);
        FuncionarioDTO result = funcionarioMapper.toDto(funcionario);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, funcionarioDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /funcionarios : get all the funcionarios.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of funcionarios in body
     */
    @GetMapping("/funcionarios")
    @Timed
    public List<FuncionarioDTO> getAllFuncionarios() {
        log.debug("REST request to get all Funcionarios");
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        return funcionarioMapper.toDto(funcionarios);
    }

    /**
     * GET  /funcionarios/:id : get the "id" funcionario.
     *
     * @param id the id of the funcionarioDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the funcionarioDTO, or with status 404 (Not Found)
     */
    @GetMapping("/funcionarios/{id}")
    @Timed
    public ResponseEntity<FuncionarioDTO> getFuncionario(@PathVariable Long id) {
        log.debug("REST request to get Funcionario : {}", id);
        Funcionario funcionario = funcionarioRepository.findOne(id);
        FuncionarioDTO funcionarioDTO = funcionarioMapper.toDto(funcionario);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(funcionarioDTO));
    }

    /**
     * DELETE  /funcionarios/:id : delete the "id" funcionario.
     *
     * @param id the id of the funcionarioDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/funcionarios/{id}")
    @Timed
    public ResponseEntity<Void> deleteFuncionario(@PathVariable Long id) {
        log.debug("REST request to delete Funcionario : {}", id);
        funcionarioRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
