package br.com.homemade.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Foto entity.
 */
public class FotoDTO implements Serializable {

    private Long id;

    private String coordenadaa;

    private String coordenadae;

    private String coordenadan;

    private ZonedDateTime data;

    private String descricaoString;

    private String foto;

    private String lado;

    private String link;

    private Integer numero;

    private String picasaId;

    private Integer ponto;

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

    public void setCoordenadaa(String coordenadaa) {
        this.coordenadaa = coordenadaa;
    }

    public String getCoordenadae() {
        return coordenadae;
    }

    public void setCoordenadae(String coordenadae) {
        this.coordenadae = coordenadae;
    }

    public String getCoordenadan() {
        return coordenadan;
    }

    public void setCoordenadan(String coordenadan) {
        this.coordenadan = coordenadan;
    }

    public ZonedDateTime getData() {
        return data;
    }

    public void setData(ZonedDateTime data) {
        this.data = data;
    }

    public String getDescricaoString() {
        return descricaoString;
    }

    public void setDescricaoString(String descricaoString) {
        this.descricaoString = descricaoString;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getLado() {
        return lado;
    }

    public void setLado(String lado) {
        this.lado = lado;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getPicasaId() {
        return picasaId;
    }

    public void setPicasaId(String picasaId) {
        this.picasaId = picasaId;
    }

    public Integer getPonto() {
        return ponto;
    }

    public void setPonto(Integer ponto) {
        this.ponto = ponto;
    }

    public String getThumb() {
        return thumb;
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

        FotoDTO fotoDTO = (FotoDTO) o;
        if(fotoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fotoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FotoDTO{" +
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
