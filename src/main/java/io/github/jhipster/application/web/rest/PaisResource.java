package io.github.jhipster.application.web.rest;
import io.github.jhipster.application.domain.Pais;
import io.github.jhipster.application.repository.PaisRepository;
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
 * REST controller for managing Pais.
 */
@RestController
@RequestMapping("/api")
public class PaisResource {

    private final Logger log = LoggerFactory.getLogger(PaisResource.class);

    private static final String ENTITY_NAME = "pais";

    private final PaisRepository paisRepository;

    public PaisResource(PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
    }

    /**
     * POST  /pais : Create a new pais.
     *
     * @param pais the pais to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pais, or with status 400 (Bad Request) if the pais has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pais")
    public ResponseEntity<Pais> createPais(@RequestBody Pais pais) throws URISyntaxException {
        log.debug("REST request to save Pais : {}", pais);
        if (pais.getId() != null) {
            throw new BadRequestAlertException("A new pais cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Pais result = paisRepository.save(pais);
        return ResponseEntity.created(new URI("/api/pais/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pais : Updates an existing pais.
     *
     * @param pais the pais to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pais,
     * or with status 400 (Bad Request) if the pais is not valid,
     * or with status 500 (Internal Server Error) if the pais couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pais")
    public ResponseEntity<Pais> updatePais(@RequestBody Pais pais) throws URISyntaxException {
        log.debug("REST request to update Pais : {}", pais);
        if (pais.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Pais result = paisRepository.save(pais);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pais.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pais : get all the pais.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of pais in body
     */
    @GetMapping("/pais")
    public List<Pais> getAllPais(@RequestParam(required = false) String filter) {
        if ("ciudad-is-null".equals(filter)) {
            log.debug("REST request to get all Paiss where ciudad is null");
            return StreamSupport
                .stream(paisRepository.findAll().spliterator(), false)
                .filter(pais -> pais.getCiudad() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all Pais");
        return paisRepository.findAll();
    }

    /**
     * GET  /pais/:id : get the "id" pais.
     *
     * @param id the id of the pais to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pais, or with status 404 (Not Found)
     */
    @GetMapping("/pais/{id}")
    public ResponseEntity<Pais> getPais(@PathVariable Long id) {
        log.debug("REST request to get Pais : {}", id);
        Optional<Pais> pais = paisRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pais);
    }

    /**
     * DELETE  /pais/:id : delete the "id" pais.
     *
     * @param id the id of the pais to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pais/{id}")
    public ResponseEntity<Void> deletePais(@PathVariable Long id) {
        log.debug("REST request to delete Pais : {}", id);
        paisRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
