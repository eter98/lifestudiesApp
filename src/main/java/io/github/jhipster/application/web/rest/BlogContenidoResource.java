package io.github.jhipster.application.web.rest;
import io.github.jhipster.application.domain.BlogContenido;
import io.github.jhipster.application.repository.BlogContenidoRepository;
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
 * REST controller for managing BlogContenido.
 */
@RestController
@RequestMapping("/api")
public class BlogContenidoResource {

    private final Logger log = LoggerFactory.getLogger(BlogContenidoResource.class);

    private static final String ENTITY_NAME = "blogContenido";

    private final BlogContenidoRepository blogContenidoRepository;

    public BlogContenidoResource(BlogContenidoRepository blogContenidoRepository) {
        this.blogContenidoRepository = blogContenidoRepository;
    }

    /**
     * POST  /blog-contenidos : Create a new blogContenido.
     *
     * @param blogContenido the blogContenido to create
     * @return the ResponseEntity with status 201 (Created) and with body the new blogContenido, or with status 400 (Bad Request) if the blogContenido has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/blog-contenidos")
    public ResponseEntity<BlogContenido> createBlogContenido(@RequestBody BlogContenido blogContenido) throws URISyntaxException {
        log.debug("REST request to save BlogContenido : {}", blogContenido);
        if (blogContenido.getId() != null) {
            throw new BadRequestAlertException("A new blogContenido cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BlogContenido result = blogContenidoRepository.save(blogContenido);
        return ResponseEntity.created(new URI("/api/blog-contenidos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /blog-contenidos : Updates an existing blogContenido.
     *
     * @param blogContenido the blogContenido to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated blogContenido,
     * or with status 400 (Bad Request) if the blogContenido is not valid,
     * or with status 500 (Internal Server Error) if the blogContenido couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/blog-contenidos")
    public ResponseEntity<BlogContenido> updateBlogContenido(@RequestBody BlogContenido blogContenido) throws URISyntaxException {
        log.debug("REST request to update BlogContenido : {}", blogContenido);
        if (blogContenido.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BlogContenido result = blogContenidoRepository.save(blogContenido);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, blogContenido.getId().toString()))
            .body(result);
    }

    /**
     * GET  /blog-contenidos : get all the blogContenidos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of blogContenidos in body
     */
    @GetMapping("/blog-contenidos")
    public List<BlogContenido> getAllBlogContenidos() {
        log.debug("REST request to get all BlogContenidos");
        return blogContenidoRepository.findAll();
    }

    /**
     * GET  /blog-contenidos/:id : get the "id" blogContenido.
     *
     * @param id the id of the blogContenido to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the blogContenido, or with status 404 (Not Found)
     */
    @GetMapping("/blog-contenidos/{id}")
    public ResponseEntity<BlogContenido> getBlogContenido(@PathVariable Long id) {
        log.debug("REST request to get BlogContenido : {}", id);
        Optional<BlogContenido> blogContenido = blogContenidoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(blogContenido);
    }

    /**
     * DELETE  /blog-contenidos/:id : delete the "id" blogContenido.
     *
     * @param id the id of the blogContenido to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/blog-contenidos/{id}")
    public ResponseEntity<Void> deleteBlogContenido(@PathVariable Long id) {
        log.debug("REST request to delete BlogContenido : {}", id);
        blogContenidoRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
