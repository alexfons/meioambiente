package br.com.homemade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.homemade.domain.Medicao;

import br.com.homemade.repository.MedicaoRepository;
import br.com.homemade.web.rest.util.HeaderUtil;
import br.com.homemade.service.dto.MedicaoDTO;
import br.com.homemade.service.mapper.MedicaoMapper;
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
 * REST controller for managing Medicao.
 */
@RestController
@RequestMapping("/api")
public class MedicaoResource {

    private final Logger log = LoggerFactory.getLogger(MedicaoResource.class);

    private static final String ENTITY_NAME = "medicao";
        
    private final MedicaoRepository medicaoRepository;

    private final MedicaoMapper medicaoMapper;

    public MedicaoResource(MedicaoRepository medicaoRepository, MedicaoMapper medicaoMapper) {
        this.medicaoRepository = medicaoRepository;
        this.medicaoMapper = medicaoMapper;
    }

    /**
     * POST  /medicaos : Create a new medicao.
     *
     * @param medicaoDTO the medicaoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new medicaoDTO, or with status 400 (Bad Request) if the medicao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/medicaos")
    @Timed
    public ResponseEntity<MedicaoDTO> createMedicao(@RequestBody MedicaoDTO medicaoDTO) throws URISyntaxException {
        log.debug("REST request to save Medicao : {}", medicaoDTO);
        if (medicaoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new medicao cannot already have an ID")).body(null);
        }
        Medicao medicao = medicaoMapper.toEntity(medicaoDTO);
        medicao = medicaoRepository.save(medicao);
        MedicaoDTO result = medicaoMapper.toDto(medicao);
        return ResponseEntity.created(new URI("/api/medicaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /medicaos : Updates an existing medicao.
     *
     * @param medicaoDTO the medicaoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated medicaoDTO,
     * or with status 400 (Bad Request) if the medicaoDTO is not valid,
     * or with status 500 (Internal Server Error) if the medicaoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/medicaos")
    @Timed
    public ResponseEntity<MedicaoDTO> updateMedicao(@RequestBody MedicaoDTO medicaoDTO) throws URISyntaxException {
        log.debug("REST request to update Medicao : {}", medicaoDTO);
        if (medicaoDTO.getId() == null) {
            return createMedicao(medicaoDTO);
        }
        Medicao medicao = medicaoMapper.toEntity(medicaoDTO);
        medicao = medicaoRepository.save(medicao);
        MedicaoDTO result = medicaoMapper.toDto(medicao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, medicaoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /medicaos : get all the medicaos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of medicaos in body
     */
    @GetMapping("/medicaos")
    @Timed
    public List<MedicaoDTO> getAllMedicaos() {
        log.debug("REST request to get all Medicaos");
        List<Medicao> medicaos = medicaoRepository.findAll();
        return medicaoMapper.toDto(medicaos);
    }

    /**
     * GET  /medicaos/:id : get the "id" medicao.
     *
     * @param id the id of the medicaoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the medicaoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/medicaos/{id}")
    @Timed
    public ResponseEntity<MedicaoDTO> getMedicao(@PathVariable Long id) {
        log.debug("REST request to get Medicao : {}", id);
        Medicao medicao = medicaoRepository.findOne(id);
        MedicaoDTO medicaoDTO = medicaoMapper.toDto(medicao);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(medicaoDTO));
    }

    /**
     * DELETE  /medicaos/:id : delete the "id" medicao.
     *
     * @param id the id of the medicaoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/medicaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteMedicao(@PathVariable Long id) {
        log.debug("REST request to delete Medicao : {}", id);
        medicaoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
