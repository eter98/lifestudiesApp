package io.github.jhipster.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

import io.github.jhipster.application.domain.enumeration.Duraciond;

import io.github.jhipster.application.domain.enumeration.TipoProgramad;

/**
 * A Programas.
 */
@Entity
@Table(name = "programas")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Programas implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "programa_nombre")
    private String programaNombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "duracion")
    private Duraciond duracion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoProgramad tipo;

    @OneToOne
    @JoinColumn(unique = true)
    private Institucion institucion;

    @OneToOne(mappedBy = "programas")
    @JsonIgnore
    private Cotizacion cotizacion;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProgramaNombre() {
        return programaNombre;
    }

    public Programas programaNombre(String programaNombre) {
        this.programaNombre = programaNombre;
        return this;
    }

    public void setProgramaNombre(String programaNombre) {
        this.programaNombre = programaNombre;
    }

    public Duraciond getDuracion() {
        return duracion;
    }

    public Programas duracion(Duraciond duracion) {
        this.duracion = duracion;
        return this;
    }

    public void setDuracion(Duraciond duracion) {
        this.duracion = duracion;
    }

    public TipoProgramad getTipo() {
        return tipo;
    }

    public Programas tipo(TipoProgramad tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(TipoProgramad tipo) {
        this.tipo = tipo;
    }

    public Institucion getInstitucion() {
        return institucion;
    }

    public Programas institucion(Institucion institucion) {
        this.institucion = institucion;
        return this;
    }

    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public Programas cotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
        return this;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
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
        Programas programas = (Programas) o;
        if (programas.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), programas.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Programas{" +
            "id=" + getId() +
            ", programaNombre='" + getProgramaNombre() + "'" +
            ", duracion='" + getDuracion() + "'" +
            ", tipo='" + getTipo() + "'" +
            "}";
    }
}
