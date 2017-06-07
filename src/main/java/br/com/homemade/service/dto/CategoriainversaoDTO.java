package br.com.homemade.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Categoriainversao entity.
 */
public class CategoriainversaoDTO implements Serializable {

    private Long id;

    private String codcategoria;

    private String descricaocategoria;

    private String descricaoitem;

    private String descricaosubcategoria;

    private String descricaosubitem;

    private Integer idcategoriainversao;

    private String item;

    private Double percentualcatagente;

    private Double percentualcatlocal;

    private String subcategoria;

    private String subitem;

    private Double valorcatagente;

    private Double valorcatlocal;

    private Double valorporcategoria;

    private Long idcontratoagenteId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodcategoria() {
        return codcategoria;
    }

    public void setCodcategoria(String codcategoria) {
        this.codcategoria = codcategoria;
    }

    public String getDescricaocategoria() {
        return descricaocategoria;
    }

    public void setDescricaocategoria(String descricaocategoria) {
        this.descricaocategoria = descricaocategoria;
    }

    public String getDescricaoitem() {
        return descricaoitem;
    }

    public void setDescricaoitem(String descricaoitem) {
        this.descricaoitem = descricaoitem;
    }

    public String getDescricaosubcategoria() {
        return descricaosubcategoria;
    }

    public void setDescricaosubcategoria(String descricaosubcategoria) {
        this.descricaosubcategoria = descricaosubcategoria;
    }

    public String getDescricaosubitem() {
        return descricaosubitem;
    }

    public void setDescricaosubitem(String descricaosubitem) {
        this.descricaosubitem = descricaosubitem;
    }

    public Integer getIdcategoriainversao() {
        return idcategoriainversao;
    }

    public void setIdcategoriainversao(Integer idcategoriainversao) {
        this.idcategoriainversao = idcategoriainversao;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Double getPercentualcatagente() {
        return percentualcatagente;
    }

    public void setPercentualcatagente(Double percentualcatagente) {
        this.percentualcatagente = percentualcatagente;
    }

    public Double getPercentualcatlocal() {
        return percentualcatlocal;
    }

    public void setPercentualcatlocal(Double percentualcatlocal) {
        this.percentualcatlocal = percentualcatlocal;
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }

    public String getSubitem() {
        return subitem;
    }

    public void setSubitem(String subitem) {
        this.subitem = subitem;
    }

    public Double getValorcatagente() {
        return valorcatagente;
    }

    public void setValorcatagente(Double valorcatagente) {
        this.valorcatagente = valorcatagente;
    }

    public Double getValorcatlocal() {
        return valorcatlocal;
    }

    public void setValorcatlocal(Double valorcatlocal) {
        this.valorcatlocal = valorcatlocal;
    }

    public Double getValorporcategoria() {
        return valorporcategoria;
    }

    public void setValorporcategoria(Double valorporcategoria) {
        this.valorporcategoria = valorporcategoria;
    }

    public Long getIdcontratoagenteId() {
        return idcontratoagenteId;
    }

    public void setIdcontratoagenteId(Long contratoagenteId) {
        this.idcontratoagenteId = contratoagenteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CategoriainversaoDTO categoriainversaoDTO = (CategoriainversaoDTO) o;
        if(categoriainversaoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), categoriainversaoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CategoriainversaoDTO{" +
            "id=" + getId() +
            ", codcategoria='" + getCodcategoria() + "'" +
            ", descricaocategoria='" + getDescricaocategoria() + "'" +
            ", descricaoitem='" + getDescricaoitem() + "'" +
            ", descricaosubcategoria='" + getDescricaosubcategoria() + "'" +
            ", descricaosubitem='" + getDescricaosubitem() + "'" +
            ", idcategoriainversao='" + getIdcategoriainversao() + "'" +
            ", item='" + getItem() + "'" +
            ", percentualcatagente='" + getPercentualcatagente() + "'" +
            ", percentualcatlocal='" + getPercentualcatlocal() + "'" +
            ", subcategoria='" + getSubcategoria() + "'" +
            ", subitem='" + getSubitem() + "'" +
            ", valorcatagente='" + getValorcatagente() + "'" +
            ", valorcatlocal='" + getValorcatlocal() + "'" +
            ", valorporcategoria='" + getValorporcategoria() + "'" +
            "}";
    }
}
