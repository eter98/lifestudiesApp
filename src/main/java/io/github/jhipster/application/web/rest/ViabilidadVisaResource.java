package io.github.jhipster.application.web.rest;
import io.github.jhipster.application.domain.ViabilidadVisa;
import io.github.jhipster.application.repository.ViabilidadVisaRepository;
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

/**
 * REST controller for managing ViabilidadVisa.
 */
@RestController
@RequestMapping("/api")
public class ViabilidadVisaResource {

    private final Logger log = LoggerFactory.getLogger(ViabilidadVisaResource.class);

    private static final String ENTITY_NAME = "viabilidadVisa";

    private final ViabilidadVisaRepository viabilidadVisaRepository;

    public ViabilidadVisaResource(ViabilidadVisaRepository viabilidadVisaRepository) {
        this.viabilidadVisaRepository = viabilidadVisaRepository;
    }

    /**
     * POST  /viabilidad-visas : Create a new viabilidadVisa.
     *
     * @param viabilidadVisa the viabilidadVisa to create
     * @return the ResponseEntity with status 201 (Created) and with body the new viabilidadVisa, or with status 400 (Bad Request) if the viabilidadVisa has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/viabilidad-visas")
    public ResponseEntity<ViabilidadVisa> createViabilidadVisa(@RequestBody ViabilidadVisa viabilidadVisa) throws URISyntaxException {
        log.debug("REST request to save ViabilidadVisa : {}", viabilidadVisa);
        if (viabilidadVisa.getId() != null) {
            throw new BadRequestAlertException("A new viabilidadVisa cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ViabilidadVisa result = viabilidadVisaRepository.save(viabilidadVisa);
        return ResponseEntity.created(new URI("/api/viabilidad-visas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /viabilidad-visas : Updates an existing viabilidadVisa.
     *
     * @param viabilidadVisa the viabilidadVisa to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated viabilidadVisa,
     * or with status 400 (Bad Request) if the viabilidadVisa is not valid,
     * or with status 500 (Internal Server Error) if the viabilidadVisa couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/viabilidad-visas")
    public ResponseEntity<ViabilidadVisa> updateViabilidadVisa(@RequestBody ViabilidadVisa viabilidadVisa) throws URISyntaxException {
        log.debug("REST request to update ViabilidadVisa : {}", viabilidadVisa);
        if (viabilidadVisa.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ViabilidadVisa result = viabilidadVisaRepository.save(viabilidadVisa);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, viabilidadVisa.getId().toString()))
            .body(result);
    }

    /**
     * GET  /viabilidad-visas : get all the viabilidadVisas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of viabilidadVisas in body
     */
    @GetMapping("/viabilidad-visas")
    public List<ViabilidadVisa> getAllViabilidadVisas() {
        log.debug("REST request to get all ViabilidadVisas");
        return viabilidadVisaRepository.findAll();
    }

    /**
     * GET  /viabilidad-visas/:id : get the "id" viabilidadVisa.
     *
     * @param id the id of the viabilidadVisa to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the viabilidadVisa, or with status 404 (Not Found)
     */
    @GetMapping("/viabilidad-visas/{id}")
    public ResponseEntity<ViabilidadVisa> getViabilidadVisa(@PathVariable Long id) {
        log.debug("REST request to get ViabilidadVisa : {}", id);
        Optional<ViabilidadVisa> viabilidadVisa = viabilidadVisaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(viabilidadVisa);
    }

    /**
     * DELETE  /viabilidad-visas/:id : delete the "id" viabilidadVisa.
     *
     * @param id the id of the viabilidadVisa to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/viabilidad-visas/{id}")
    public ResponseEntity<Void> deleteViabilidadVisa(@PathVariable Long id) {
        log.debug("REST request to delete ViabilidadVisa : {}", id);
        viabilidadVisaRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
