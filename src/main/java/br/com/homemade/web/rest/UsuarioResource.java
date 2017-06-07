package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Usuario;

import br.com.homemade.repository.UsuarioRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.UsuarioDTO;
import br.com.homemade.service.mapper.UsuarioMapper;
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
 * REST controller for managing Usuario.
 */
@RestController
@RequestMapping("/api")
public class UsuarioResource {

    private final Logger log = LoggerFactory.getLogger(UsuarioResource.class);

    private static final String ENTITY_NAME = "usuario";
        
    private final UsuarioRepository usuarioRepository;

    private final UsuarioMapper usuarioMapper;

    public UsuarioResource(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    /**
     * POST  /usuarios : Create a new usuario.
     *
     * @param usuarioDTO the usuarioDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new usuarioDTO, or with status 400 (Bad Request) if the usuario has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/usuarios")
    @Timed
    public ResponseEntity<UsuarioDTO> createUsuario(@RequestBody UsuarioDTO usuarioDTO) throws URISyntaxException {
        log.debug("REST request to save Usuario : {}", usuarioDTO);
        if (usuarioDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new usuario cannot already have an ID")).body(null);
        }
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        UsuarioDTO result = usuarioMapper.toDto(usuario);
        return ResponseEntity.created(new URI("/api/usuarios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /usuarios : Updates an existing usuario.
     *
     * @param usuarioDTO the usuarioDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated usuarioDTO,
     * or with status 400 (Bad Request) if the usuarioDTO is not valid,
     * or with status 500 (Internal Server Error) if the usuarioDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/usuarios")
    @Timed
    public ResponseEntity<UsuarioDTO> updateUsuario(@RequestBody UsuarioDTO usuarioDTO) throws URISyntaxException {
        log.debug("REST request to update Usuario : {}", usuarioDTO);
        if (usuarioDTO.getId() == null) {
            return createUsuario(usuarioDTO);
        }
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        UsuarioDTO result = usuarioMapper.toDto(usuario);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, usuarioDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /usuarios : get all the usuarios.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of usuarios in body
     */
    @GetMapping("/usuarios")
    @Timed
    public List<UsuarioDTO> getAllUsuarios() {
        log.debug("REST request to get all Usuarios");
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarioMapper.toDto(usuarios);
    }

    /**
     * GET  /usuarios/:id : get the "id" usuario.
     *
     * @param id the id of the usuarioDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the usuarioDTO, or with status 404 (Not Found)
     */
    @GetMapping("/usuarios/{id}")
    @Timed
    public ResponseEntity<UsuarioDTO> getUsuario(@PathVariable Long id) {
        log.debug("REST request to get Usuario : {}", id);
        Usuario usuario = usuarioRepository.findOne(id);
        UsuarioDTO usuarioDTO = usuarioMapper.toDto(usuario);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(usuarioDTO));
    }

    /**
     * DELETE  /usuarios/:id : delete the "id" usuario.
     *
     * @param id the id of the usuarioDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/usuarios/{id}")
    @Timed
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        log.debug("REST request to delete Usuario : {}", id);
        usuarioRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
