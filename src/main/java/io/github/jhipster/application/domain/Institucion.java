package io.github.jhipster.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Institucion.
 */
@Entity
@Table(name = "institucion")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Institucion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "tipo")
    private Integer tipo;

    @Column(name = "web_site")
    private String webSite;

    @Column(name = "prefix_nombre")
    private String prefixNombre;

    @Column(name = "nombre_responsable")
    private String nombreResponsable;

    @Column(name = "apellido_responsable")
    private String apellidoResponsable;

    @Column(name = "mail")
    private String mail;

    @Column(name = "area_code")
    private String areaCode;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "movil")
    private String movil;

    @Column(name = "skype")
    private String skype;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "direccion_2")
    private String direccion2;

    @Column(name = "estado")
    private String estado;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "mail_corporativo")
    private String mailCorporativo;

    @Column(name = "movil_corporativo")
    private String movilCorporativo;

    @Column(name = "skype_corporativo")
    private String skypeCorporativo;

    @Lob
    @Column(name = "logo")
    private byte[] logo;

    @Column(name = "logo_content_type")
    private String logoContentType;

    @Lob
    @Column(name = "imagen")
    private byte[] imagen;

    @Column(name = "imagen_content_type")
    private String imagenContentType;

    @Lob
    @Column(name = "imagen_2")
    private byte[] imagen2;

    @Column(name = "imagen_2_content_type")
    private String imagen2ContentType;

    @Lob
    @Column(name = "imagen_3")
    private byte[] imagen3;

    @Column(name = "imagen_3_content_type")
    private String imagen3ContentType;

    @Lob
    @Column(name = "imagen_4")
    private byte[] imagen4;

    @Column(name = "imagen_4_content_type")
    private String imagen4ContentType;

    @Lob
    @Column(name = "imagen_5")
    private byte[] imagen5;

    @Column(name = "imagen_5_content_type")
    private String imagen5ContentType;

    @Lob
    @Column(name = "video")
    private byte[] video;

    @Column(name = "video_content_type")
    private String videoContentType;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @OneToOne
    @JoinColumn(unique = true)
    private Ciudad ciudad;

    @OneToOne(mappedBy = "institucion")
    @JsonIgnore
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

    public Institucion nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTipo() {
        return tipo;
    }

    public Institucion tipo(Integer tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getWebSite() {
        return webSite;
    }

    public Institucion webSite(String webSite) {
        this.webSite = webSite;
        return this;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getPrefixNombre() {
        return prefixNombre;
    }

    public Institucion prefixNombre(String prefixNombre) {
        this.prefixNombre = prefixNombre;
        return this;
    }

    public void setPrefixNombre(String prefixNombre) {
        this.prefixNombre = prefixNombre;
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public Institucion nombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
        return this;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    public String getApellidoResponsable() {
        return apellidoResponsable;
    }

    public Institucion apellidoResponsable(String apellidoResponsable) {
        this.apellidoResponsable = apellidoResponsable;
        return this;
    }

    public void setApellidoResponsable(String apellidoResponsable) {
        this.apellidoResponsable = apellidoResponsable;
    }

    public String getMail() {
        return mail;
    }

    public Institucion mail(String mail) {
        this.mail = mail;
        return this;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public Institucion areaCode(String areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getTelefono() {
        return telefono;
    }

    public Institucion telefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMovil() {
        return movil;
    }

    public Institucion movil(String movil) {
        this.movil = movil;
        return this;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getSkype() {
        return skype;
    }

    public Institucion skype(String skype) {
        this.skype = skype;
        return this;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getDireccion() {
        return direccion;
    }

    public Institucion direccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion2() {
        return direccion2;
    }

    public Institucion direccion2(String direccion2) {
        this.direccion2 = direccion2;
        return this;
    }

    public void setDireccion2(String direccion2) {
        this.direccion2 = direccion2;
    }

    public String getEstado() {
        return estado;
    }

    public Institucion estado(String estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Institucion zipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getMailCorporativo() {
        return mailCorporativo;
    }

    public Institucion mailCorporativo(String mailCorporativo) {
        this.mailCorporativo = mailCorporativo;
        return this;
    }

    public void setMailCorporativo(String mailCorporativo) {
        this.mailCorporativo = mailCorporativo;
    }

    public String getMovilCorporativo() {
        return movilCorporativo;
    }

    public Institucion movilCorporativo(String movilCorporativo) {
        this.movilCorporativo = movilCorporativo;
        return this;
    }

    public void setMovilCorporativo(String movilCorporativo) {
        this.movilCorporativo = movilCorporativo;
    }

    public String getSkypeCorporativo() {
        return skypeCorporativo;
    }

    public Institucion skypeCorporativo(String skypeCorporativo) {
        this.skypeCorporativo = skypeCorporativo;
        return this;
    }

    public void setSkypeCorporativo(String skypeCorporativo) {
        this.skypeCorporativo = skypeCorporativo;
    }

    public byte[] getLogo() {
        return logo;
    }

    public Institucion logo(byte[] logo) {
        this.logo = logo;
        return this;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getLogoContentType() {
        return logoContentType;
    }

    public Institucion logoContentType(String logoContentType) {
        this.logoContentType = logoContentType;
        return this;
    }

    public void setLogoContentType(String logoContentType) {
        this.logoContentType = logoContentType;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public Institucion imagen(byte[] imagen) {
        this.imagen = imagen;
        return this;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getImagenContentType() {
        return imagenContentType;
    }

    public Institucion imagenContentType(String imagenContentType) {
        this.imagenContentType = imagenContentType;
        return this;
    }

    public void setImagenContentType(String imagenContentType) {
        this.imagenContentType = imagenContentType;
    }

    public byte[] getImagen2() {
        return imagen2;
    }

    public Institucion imagen2(byte[] imagen2) {
        this.imagen2 = imagen2;
        return this;
    }

    public void setImagen2(byte[] imagen2) {
        this.imagen2 = imagen2;
    }

    public String getImagen2ContentType() {
        return imagen2ContentType;
    }

    public Institucion imagen2ContentType(String imagen2ContentType) {
        this.imagen2ContentType = imagen2ContentType;
        return this;
    }

    public void setImagen2ContentType(String imagen2ContentType) {
        this.imagen2ContentType = imagen2ContentType;
    }

    public byte[] getImagen3() {
        return imagen3;
    }

    public Institucion imagen3(byte[] imagen3) {
        this.imagen3 = imagen3;
        return this;
    }

    public void setImagen3(byte[] imagen3) {
        this.imagen3 = imagen3;
    }

    public String getImagen3ContentType() {
        return imagen3ContentType;
    }

    public Institucion imagen3ContentType(String imagen3ContentType) {
        this.imagen3ContentType = imagen3ContentType;
        return this;
    }

    public void setImagen3ContentType(String imagen3ContentType) {
        this.imagen3ContentType = imagen3ContentType;
    }

    public byte[] getImagen4() {
        return imagen4;
    }

    public Institucion imagen4(byte[] imagen4) {
        this.imagen4 = imagen4;
        return this;
    }

    public void setImagen4(byte[] imagen4) {
        this.imagen4 = imagen4;
    }

    public String getImagen4ContentType() {
        return imagen4ContentType;
    }

    public Institucion imagen4ContentType(String imagen4ContentType) {
        this.imagen4ContentType = imagen4ContentType;
        return this;
    }

    public void setImagen4ContentType(String imagen4ContentType) {
        this.imagen4ContentType = imagen4ContentType;
    }

    public byte[] getImagen5() {
        return imagen5;
    }

    public Institucion imagen5(byte[] imagen5) {
        this.imagen5 = imagen5;
        return this;
    }

    public void setImagen5(byte[] imagen5) {
        this.imagen5 = imagen5;
    }

    public String getImagen5ContentType() {
        return imagen5ContentType;
    }

    public Institucion imagen5ContentType(String imagen5ContentType) {
        this.imagen5ContentType = imagen5ContentType;
        return this;
    }

    public void setImagen5ContentType(String imagen5ContentType) {
        this.imagen5ContentType = imagen5ContentType;
    }

    public byte[] getVideo() {
        return video;
    }

    public Institucion video(byte[] video) {
        this.video = video;
        return this;
    }

    public void setVideo(byte[] video) {
        this.video = video;
    }

    public String getVideoContentType() {
        return videoContentType;
    }

    public Institucion videoContentType(String videoContentType) {
        this.videoContentType = videoContentType;
        return this;
    }

    public void setVideoContentType(String videoContentType) {
        this.videoContentType = videoContentType;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Institucion descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public Institucion ciudad(Ciudad ciudad) {
        this.ciudad = ciudad;
        return this;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Programas getProgramas() {
        return programas;
    }

    public Institucion programas(Programas programas) {
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
        Institucion institucion = (Institucion) o;
        if (institucion.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), institucion.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Institucion{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", tipo=" + getTipo() +
            ", webSite='" + getWebSite() + "'" +
            ", prefixNombre='" + getPrefixNombre() + "'" +
            ", nombreResponsable='" + getNombreResponsable() + "'" +
            ", apellidoResponsable='" + getApellidoResponsable() + "'" +
            ", mail='" + getMail() + "'" +
            ", areaCode='" + getAreaCode() + "'" +
            ", telefono='" + getTelefono() + "'" +
            ", movil='" + getMovil() + "'" +
            ", skype='" + getSkype() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", direccion2='" + getDireccion2() + "'" +
            ", estado='" + getEstado() + "'" +
            ", zipCode='" + getZipCode() + "'" +
            ", mailCorporativo='" + getMailCorporativo() + "'" +
            ", movilCorporativo='" + getMovilCorporativo() + "'" +
            ", skypeCorporativo='" + getSkypeCorporativo() + "'" +
            ", logo='" + getLogo() + "'" +
            ", logoContentType='" + getLogoContentType() + "'" +
            ", imagen='" + getImagen() + "'" +
            ", imagenContentType='" + getImagenContentType() + "'" +
            ", imagen2='" + getImagen2() + "'" +
            ", imagen2ContentType='" + getImagen2ContentType() + "'" +
            ", imagen3='" + getImagen3() + "'" +
            ", imagen3ContentType='" + getImagen3ContentType() + "'" +
            ", imagen4='" + getImagen4() + "'" +
            ", imagen4ContentType='" + getImagen4ContentType() + "'" +
            ", imagen5='" + getImagen5() + "'" +
            ", imagen5ContentType='" + getImagen5ContentType() + "'" +
            ", video='" + getVideo() + "'" +
            ", videoContentType='" + getVideoContentType() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            "}";
    }
}
