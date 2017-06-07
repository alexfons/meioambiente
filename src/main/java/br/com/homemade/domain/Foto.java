package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Foto.
 */
@Entity
@Table(name = "foto")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Foto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "coordenadaa")
    private String coordenadaa;

    @Column(name = "coordenadae")
    private String coordenadae;

    @Column(name = "coordenadan")
    private String coordenadan;

    @Column(name = "data")
    private ZonedDateTime data;

    @Column(name = "descricao_string")
    private String descricaoString;

    @Column(name = "foto")
    private String foto;

    @Column(name = "lado")
    private String lado;

    @Column(name = "jhi_link")
    private String link;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "picasa_id")
    private String picasaId;

    @Column(name = "ponto")
    private Integer ponto;

    @Column(name = "thumb")
    private String thumb;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoordenadaa() {
        return coordenadaa;
    }

    public Foto coordenadaa(String coordenadaa) {
        this.coordenadaa = coordenadaa;
        return this;
    }

    public void setCoordenadaa(String coordenadaa) {
        this.coordenadaa = coordenadaa;
    }

    public String getCoordenadae() {
        return coordenadae;
    }

    public Foto coordenadae(String coordenadae) {
        this.coordenadae = coordenadae;
        return this;
    }

    public void setCoordenadae(String coordenadae) {
        this.coordenadae = coordenadae;
    }

    public String getCoordenadan() {
        return coordenadan;
    }

    public Foto coordenadan(String coordenadan) {
        this.coordenadan = coordenadan;
        return this;
    }

    public void setCoordenadan(String coordenadan) {
        this.coordenadan = coordenadan;
    }

    public ZonedDateTime getData() {
        return data;
    }

    public Foto data(ZonedDateTime data) {
        this.data = data;
        return this;
    }

    public void setData(ZonedDateTime data) {
        this.data = data;
    }

    public String getDescricaoString() {
        return descricaoString;
    }

    public Foto descricaoString(String descricaoString) {
        this.descricaoString = descricaoString;
        return this;
    }

    public void setDescricaoString(String descricaoString) {
        this.descricaoString = descricaoString;
    }

    public String getFoto() {
        return foto;
    }

    public Foto foto(String foto) {
        this.foto = foto;
        return this;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getLado() {
        return lado;
    }

    public Foto lado(String lado) {
        this.lado = lado;
        return this;
    }

    public void setLado(String lado) {
        this.lado = lado;
    }

    public String getLink() {
        return link;
    }

    public Foto link(String link) {
        this.link = link;
        return this;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getNumero() {
        return numero;
    }

    public Foto numero(Integer numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getPicasaId() {
        return picasaId;
    }

    public Foto picasaId(String picasaId) {
        this.picasaId = picasaId;
        return this;
    }

    public void setPicasaId(String picasaId) {
        this.picasaId = picasaId;
    }

    public Integer getPonto() {
        return ponto;
    }

    public Foto ponto(Integer ponto) {
        this.ponto = ponto;
        return this;
    }

    public void setPonto(Integer ponto) {
        this.ponto = ponto;
    }

    public String getThumb() {
        return thumb;
    }

    public Foto thumb(String thumb) {
        this.thumb = thumb;
        return this;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Foto foto = (Foto) o;
        if (foto.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), foto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Foto{" +
            "id=" + getId() +
            ", coordenadaa='" + getCoordenadaa() + "'" +
            ", coordenadae='" + getCoordenadae() + "'" +
            ", coordenadan='" + getCoordenadan() + "'" +
            ", data='" + getData() + "'" +
            ", descricaoString='" + getDescricaoString() + "'" +
            ", foto='" + getFoto() + "'" +
            ", lado='" + getLado() + "'" +
            ", link='" + getLink() + "'" +
            ", numero='" + getNumero() + "'" +
            ", picasaId='" + getPicasaId() + "'" +
            ", ponto='" + getPonto() + "'" +
            ", thumb='" + getThumb() + "'" +
            "}";
    }
}
