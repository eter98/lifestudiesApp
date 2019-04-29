package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.BlogContenido;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BlogContenido entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BlogContenidoRepository extends JpaRepository<BlogContenido, Long> {

}
