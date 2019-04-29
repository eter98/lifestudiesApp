package io.github.jhipster.application.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Cotizacion.
 */
@Entity
@Table(name = "cotizacion")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Cotizacion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "mail")
    private String mail;

    @Column(name = "area_code")
    private String areaCode;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "movil")
    private String movil;

    @Column(name = "nacionalidad")
    private Integer nacionalidad;

    @Column(name = "area")
    private String area;

    @Column(name = "fecha_viaje")
    private LocalDate fechaViaje;

    @OneToOne
    @JoinColumn(unique = true)
    private Programas programas;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Cotizacion nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Cotizacion apellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public Cotizacion mail(String mail) {
        this.mail = mail;
        return this;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public Cotizacion areaCode(String areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getTelefono() {
        return telefono;
    }

    public Cotizacion telefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMovil() {
        return movil;
    }

    public Cotizacion movil(String movil) {
        this.movil = movil;
        return this;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public Integer getNacionalidad() {
        return nacionalidad;
    }

    public Cotizacion nacionalidad(Integer nacionalidad) {
        this.nacionalidad = nacionalidad;
        return this;
    }

    public void setNacionalidad(Integer nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getArea() {
        return area;
    }

    public Cotizacion area(String area) {
        this.area = area;
        return this;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public LocalDate getFechaViaje() {
        return fechaViaje;
    }

    public Cotizacion fechaViaje(LocalDate fechaViaje) {
        this.fechaViaje = fechaViaje;
        return this;
    }

    public void setFechaViaje(LocalDate fechaViaje) {
        this.fechaViaje = fechaViaje;
    }

    public Programas getProgramas() {
        return programas;
    }

    public Cotizacion programas(Programas programas) {
        this.programas = programas;
        return this;
    }

    public void setProgramas(Programas programas) {
        this.programas = programas;
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
        Cotizacion cotizacion = (Cotizacion) o;
        if (cotizacion.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cotizacion.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Cotizacion{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", apellido='" + getApellido() + "'" +
            ", mail='" + getMail() + "'" +
            ", areaCode='" + getAreaCode() + "'" +
            ", telefono='" + getTelefono() + "'" +
            ", movil='" + getMovil() + "'" +
            ", nacionalidad=" + getNacionalidad() +
            ", area='" + getArea() + "'" +
            ", fechaViaje='" + getFechaViaje() + "'" +
            "}";
    }
}
