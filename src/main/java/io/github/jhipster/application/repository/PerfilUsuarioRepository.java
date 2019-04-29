package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.PerfilUsuario;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PerfilUsuario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PerfilUsuarioRepository extends JpaRepository<PerfilUsuario, Long> {

}
