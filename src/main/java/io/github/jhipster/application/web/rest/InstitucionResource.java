package io.github.jhipster.application.web.rest;
import io.github.jhipster.application.domain.Institucion;
import io.github.jhipster.application.repository.InstitucionRepository;
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
 * REST controller for managing Institucion.
 */
@RestController
@RequestMapping("/api")
public class InstitucionResource {

    private final Logger log = LoggerFactory.getLogger(InstitucionResource.class);

    private static final String ENTITY_NAME = "institucion";

    private final InstitucionRepository institucionRepository;

    public InstitucionResource(InstitucionRepository institucionRepository) {
        this.institucionRepository = institucionRepository;
    }

    /**
     * POST  /institucions : Create a new institucion.
     *
     * @param institucion the institucion to create
     * @return the ResponseEntity with status 201 (Created) and with body the new institucion, or with status 400 (Bad Request) if the institucion has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/institucions")
    public ResponseEntity<Institucion> createInstitucion(@RequestBody Institucion institucion) throws URISyntaxException {
        log.debug("REST request to save Institucion : {}", institucion);
        if (institucion.getId() != null) {
            throw new BadRequestAlertException("A new institucion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Institucion result = institucionRepository.save(institucion);
        return ResponseEntity.created(new URI("/api/institucions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /institucions : Updates an existing institucion.
     *
     * @param institucion the institucion to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated institucion,
     * or with status 400 (Bad Request) if the institucion is not valid,
     * or with status 500 (Internal Server Error) if the institucion couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/institucions")
    public ResponseEntity<Institucion> updateInstitucion(@RequestBody Institucion institucion) throws URISyntaxException {
        log.debug("REST request to update Institucion : {}", institucion);
        if (institucion.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Institucion result = institucionRepository.save(institucion);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, institucion.getId().toString()))
            .body(result);
    }

    /**
     * GET  /institucions : get all the institucions.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of institucions in body
     */
    @GetMapping("/institucions")
    public List<Institucion> getAllInstitucions(@RequestParam(required = false) String filter) {
        if ("programas-is-null".equals(filter)) {
            log.debug("REST request to get all Institucions where programas is null");
            return StreamSupport
                .stream(institucionRepository.findAll().spliterator(), false)
                .filter(institucion -> institucion.getProgramas() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all Institucions");
        return institucionRepository.findAll();
    }

    /**
     * GET  /institucions/:id : get the "id" institucion.
     *
     * @param id the id of the institucion to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the institucion, or with status 404 (Not Found)
     */
    @GetMapping("/institucions/{id}")
    public ResponseEntity<Institucion> getInstitucion(@PathVariable Long id) {
        log.debug("REST request to get Institucion : {}", id);
        Optional<Institucion> institucion = institucionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(institucion);
    }

    /**
     * DELETE  /institucions/:id : delete the "id" institucion.
     *
     * @param id the id of the institucion to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/institucions/{id}")
    public ResponseEntity<Void> deleteInstitucion(@PathVariable Long id) {
        log.debug("REST request to delete Institucion : {}", id);
        institucionRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
