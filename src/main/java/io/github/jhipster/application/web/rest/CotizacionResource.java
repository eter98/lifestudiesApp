package io.github.jhipster.application.web.rest;
import io.github.jhipster.application.domain.Cotizacion;
import io.github.jhipster.application.repository.CotizacionRepository;
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
 * REST controller for managing Cotizacion.
 */
@RestController
@RequestMapping("/api")
public class CotizacionResource {

    private final Logger log = LoggerFactory.getLogger(CotizacionResource.class);

    private static final String ENTITY_NAME = "cotizacion";

    private final CotizacionRepository cotizacionRepository;

    public CotizacionResource(CotizacionRepository cotizacionRepository) {
        this.cotizacionRepository = cotizacionRepository;
    }

    /**
     * POST  /cotizacions : Create a new cotizacion.
     *
     * @param cotizacion the cotizacion to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cotizacion, or with status 400 (Bad Request) if the cotizacion has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cotizacions")
    public ResponseEntity<Cotizacion> createCotizacion(@RequestBody Cotizacion cotizacion) throws URISyntaxException {
        log.debug("REST request to save Cotizacion : {}", cotizacion);
        if (cotizacion.getId() != null) {
            throw new BadRequestAlertException("A new cotizacion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Cotizacion result = cotizacionRepository.save(cotizacion);
        return ResponseEntity.created(new URI("/api/cotizacions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cotizacions : Updates an existing cotizacion.
     *
     * @param cotizacion the cotizacion to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated cotizacion,
     * or with status 400 (Bad Request) if the cotizacion is not valid,
     * or with status 500 (Internal Server Error) if the cotizacion couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cotizacions")
    public ResponseEntity<Cotizacion> updateCotizacion(@RequestBody Cotizacion cotizacion) throws URISyntaxException {
        log.debug("REST request to update Cotizacion : {}", cotizacion);
        if (cotizacion.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Cotizacion result = cotizacionRepository.save(cotizacion);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cotizacion.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cotizacions : get all the cotizacions.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of cotizacions in body
     */
    @GetMapping("/cotizacions")
    public List<Cotizacion> getAllCotizacions() {
        log.debug("REST request to get all Cotizacions");
        return cotizacionRepository.findAll();
    }

    /**
     * GET  /cotizacions/:id : get the "id" cotizacion.
     *
     * @param id the id of the cotizacion to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cotizacion, or with status 404 (Not Found)
     */
    @GetMapping("/cotizacions/{id}")
    public ResponseEntity<Cotizacion> getCotizacion(@PathVariable Long id) {
        log.debug("REST request to get Cotizacion : {}", id);
        Optional<Cotizacion> cotizacion = cotizacionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(cotizacion);
    }

    /**
     * DELETE  /cotizacions/:id : delete the "id" cotizacion.
     *
     * @param id the id of the cotizacion to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cotizacions/{id}")
    public ResponseEntity<Void> deleteCotizacion(@PathVariable Long id) {
        log.debug("REST request to delete Cotizacion : {}", id);
        cotizacionRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
