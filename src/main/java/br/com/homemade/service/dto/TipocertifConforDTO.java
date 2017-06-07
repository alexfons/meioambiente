package br.com.homemade.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the TipocertifConfor entity.
 */
public class TipocertifConforDTO implements Serializable {

    private Long id;

    private String categoria;

    private String descricao;

    private String subcategoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TipocertifConforDTO tipocertifConforDTO = (TipocertifConforDTO) o;
        if(tipocertifConforDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tipocertifConforDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TipocertifConforDTO{" +
            "id=" + getId() +
            ", categoria='" + getCategoria() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", subcategoria='" + getSubcategoria() + "'" +
            "}";
    }
}
