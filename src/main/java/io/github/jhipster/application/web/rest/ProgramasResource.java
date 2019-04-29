package io.github.jhipster.application.web.rest;
import io.github.jhipster.application.domain.Programas;
import io.github.jhipster.application.repository.ProgramasRepository;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing Programas.
 */
@RestController
@RequestMapping("/api")
public class ProgramasResource {

    private final Logger log = LoggerFactory.getLogger(ProgramasResource.class);

    private static final String ENTITY_NAME = "programas";

    private final ProgramasRepository programasRepository;

    public ProgramasResource(ProgramasRepository programasRepository) {
        this.programasRepository = programasRepository;
    }

    /**
     * POST  /programas : Create a new programas.
     *
     * @param programas the programas to create
     * @return the ResponseEntity with status 201 (Created) and with body the new programas, or with status 400 (Bad Request) if the programas has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/programas")
    public ResponseEntity<Programas> createProgramas(@RequestBody Programas programas) throws URISyntaxException {
        log.debug("REST request to save Programas : {}", programas);
        if (programas.getId() != null) {
            throw new BadRequestAlertException("A new programas cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Programas result = programasRepository.save(programas);
        return ResponseEntity.created(new URI("/api/programas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /programas : Updates an existing programas.
     *
     * @param programas the programas to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated programas,
     * or with status 400 (Bad Request) if the programas is not valid,
     * or with status 500 (Internal Server Error) if the programas couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/programas")
    public ResponseEntity<Programas> updateProgramas(@RequestBody Programas programas) throws URISyntaxException {
        log.debug("REST request to update Programas : {}", programas);
        if (programas.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Programas result = programasRepository.save(programas);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, programas.getId().toString()))
            .body(result);
    }

    /**
     * GET  /programas : get all the programas.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of programas in body
     */
    @GetMapping("/programas")
    public List<Programas> getAllProgramas(@RequestParam(required = false) String filter) {
        if ("cotizacion-is-null".equals(filter)) {
            log.debug("REST request to get all Programass where cotizacion is null");
            return StreamSupport
                .stream(programasRepository.findAll().spliterator(), false)
                .filter(programas -> programas.getCotizacion() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all Programas");
        return programasRepository.findAll();
    }

    /**
     * GET  /programas/:id : get the "id" programas.
     *
     * @param id the id of the programas to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the programas, or with status 404 (Not Found)
     */
    @GetMapping("/programas/{id}")
    public ResponseEntity<Programas> getProgramas(@PathVariable Long id) {
        log.debug("REST request to get Programas : {}", id);
        Optional<Programas> programas = programasRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(programas);
    }

    /**
     * DELETE  /programas/:id : delete the "id" programas.
     *
     * @param id the id of the programas to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/programas/{id}")
    public ResponseEntity<Void> deleteProgramas(@PathVariable Long id) {
        log.debug("REST request to delete Programas : {}", id);
        programasRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
