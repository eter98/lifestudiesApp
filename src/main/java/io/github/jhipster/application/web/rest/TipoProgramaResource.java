package io.github.jhipster.application.web.rest;
import io.github.jhipster.application.domain.TipoPrograma;
import io.github.jhipster.application.repository.TipoProgramaRepository;
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
 * REST controller for managing TipoPrograma.
 */
@RestController
@RequestMapping("/api")
public class TipoProgramaResource {

    private final Logger log = LoggerFactory.getLogger(TipoProgramaResource.class);

    private static final String ENTITY_NAME = "tipoPrograma";

    private final TipoProgramaRepository tipoProgramaRepository;

    public TipoProgramaResource(TipoProgramaRepository tipoProgramaRepository) {
        this.tipoProgramaRepository = tipoProgramaRepository;
    }

    /**
     * POST  /tipo-programas : Create a new tipoPrograma.
     *
     * @param tipoPrograma the tipoPrograma to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipoPrograma, or with status 400 (Bad Request) if the tipoPrograma has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tipo-programas")
    public ResponseEntity<TipoPrograma> createTipoPrograma(@RequestBody TipoPrograma tipoPrograma) throws URISyntaxException {
        log.debug("REST request to save TipoPrograma : {}", tipoPrograma);
        if (tipoPrograma.getId() != null) {
            throw new BadRequestAlertException("A new tipoPrograma cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoPrograma result = tipoProgramaRepository.save(tipoPrograma);
        return ResponseEntity.created(new URI("/api/tipo-programas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tipo-programas : Updates an existing tipoPrograma.
     *
     * @param tipoPrograma the tipoPrograma to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipoPrograma,
     * or with status 400 (Bad Request) if the tipoPrograma is not valid,
     * or with status 500 (Internal Server Error) if the tipoPrograma couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tipo-programas")
    public ResponseEntity<TipoPrograma> updateTipoPrograma(@RequestBody TipoPrograma tipoPrograma) throws URISyntaxException {
        log.debug("REST request to update TipoPrograma : {}", tipoPrograma);
        if (tipoPrograma.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoPrograma result = tipoProgramaRepository.save(tipoPrograma);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipoPrograma.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tipo-programas : get all the tipoProgramas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tipoProgramas in body
     */
    @GetMapping("/tipo-programas")
    public List<TipoPrograma> getAllTipoProgramas() {
        log.debug("REST request to get all TipoProgramas");
        return tipoProgramaRepository.findAll();
    }

    /**
     * GET  /tipo-programas/:id : get the "id" tipoPrograma.
     *
     * @param id the id of the tipoPrograma to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipoPrograma, or with status 404 (Not Found)
     */
    @GetMapping("/tipo-programas/{id}")
    public ResponseEntity<TipoPrograma> getTipoPrograma(@PathVariable Long id) {
        log.debug("REST request to get TipoPrograma : {}", id);
        Optional<TipoPrograma> tipoPrograma = tipoProgramaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tipoPrograma);
    }

    /**
     * DELETE  /tipo-programas/:id : delete the "id" tipoPrograma.
     *
     * @param id the id of the tipoPrograma to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tipo-programas/{id}")
    public ResponseEntity<Void> deleteTipoPrograma(@PathVariable Long id) {
        log.debug("REST request to delete TipoPrograma : {}", id);
        tipoProgramaRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
