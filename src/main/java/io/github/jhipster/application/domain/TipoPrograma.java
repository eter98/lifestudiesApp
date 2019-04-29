package io.github.jhipster.application.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A TipoPrograma.
 */
@Entity
@Table(name = "tipo_programa")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TipoPrograma implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "tipo_nombre")
    private String tipoNombre;

    @OneToOne
    @JoinColumn(unique = true)
    private Programas tipoCodigo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoNombre() {
        return tipoNombre;
    }

    public TipoPrograma tipoNombre(String tipoNombre) {
        this.tipoNombre = tipoNombre;
        return this;
    }

    public void setTipoNombre(String tipoNombre) {
        this.tipoNombre = tipoNombre;
    }

    public Programas getTipoCodigo() {
        return tipoCodigo;
    }

    public TipoPrograma tipoCodigo(Programas programas) {
        this.tipoCodigo = programas;
        return this;
    }

    public void setTipoCodigo(Programas programas) {
        this.tipoCodigo = programas;
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
        TipoPrograma tipoPrograma = (TipoPrograma) o;
        if (tipoPrograma.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tipoPrograma.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TipoPrograma{" +
            "id=" + getId() +
            ", tipoNombre='" + getTipoNombre() + "'" +
            "}";
    }
}
