package io.github.jhipster.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Ciudad.
 */
@Entity
@Table(name = "ciudad")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Ciudad implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ciudad_nombre")
    private String ciudadNombre;

    @OneToOne
    @JoinColumn(unique = true)
    private Pais pais;

    @OneToOne(mappedBy = "ciudad")
    @JsonIgnore
    private Institucion institucion;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCiudadNombre() {
        return ciudadNombre;
    }

    public Ciudad ciudadNombre(String ciudadNombre) {
        this.ciudadNombre = ciudadNombre;
        return this;
    }

    public void setCiudadNombre(String ciudadNombre) {
        this.ciudadNombre = ciudadNombre;
    }

    public Pais getPais() {
        return pais;
    }

    public Ciudad pais(Pais pais) {
        this.pais = pais;
        return this;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Institucion getInstitucion() {
        return institucion;
    }

    public Ciudad institucion(Institucion institucion) {
        this.institucion = institucion;
        return this;
    }

    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ciudad ciudad = (Ciudad) o;
        if (ciudad.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ciudad.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Ciudad{" +
            "id=" + getId() +
            ", ciudadNombre='" + getCiudadNombre() + "'" +
            "}";
    }
}
