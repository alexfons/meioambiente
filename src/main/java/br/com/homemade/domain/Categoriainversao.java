package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Categoriainversao.
 */
@Entity
@Table(name = "categoriainversao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Categoriainversao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "codcategoria")
    private String codcategoria;

    @Column(name = "descricaocategoria")
    private String descricaocategoria;

    @Column(name = "descricaoitem")
    private String descricaoitem;

    @Column(name = "descricaosubcategoria")
    private String descricaosubcategoria;

    @Column(name = "descricaosubitem")
    private String descricaosubitem;

    @Column(name = "idcategoriainversao")
    private Integer idcategoriainversao;

    @Column(name = "item")
    private String item;

    @Column(name = "percentualcatagente")
    private Double percentualcatagente;

    @Column(name = "percentualcatlocal")
    private Double percentualcatlocal;

    @Column(name = "subcategoria")
    private String subcategoria;

    @Column(name = "subitem")
    private String subitem;

    @Column(name = "valorcatagente")
    private Double valorcatagente;

    @Column(name = "valorcatlocal")
    private Double valorcatlocal;

    @Column(name = "valorporcategoria")
    private Double valorporcategoria;

    @ManyToOne
    private Contratoagente idcontratoagente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodcategoria() {
        return codcategoria;
    }

    public Categoriainversao codcategoria(String codcategoria) {
        this.codcategoria = codcategoria;
        return this;
    }

    public void setCodcategoria(String codcategoria) {
        this.codcategoria = codcategoria;
    }

    public String getDescricaocategoria() {
        return descricaocategoria;
    }

    public Categoriainversao descricaocategoria(String descricaocategoria) {
        this.descricaocategoria = descricaocategoria;
        return this;
    }

    public void setDescricaocategoria(String descricaocategoria) {
        this.descricaocategoria = descricaocategoria;
    }

    public String getDescricaoitem() {
        return descricaoitem;
    }

    public Categoriainversao descricaoitem(String descricaoitem) {
        this.descricaoitem = descricaoitem;
        return this;
    }

    public void setDescricaoitem(String descricaoitem) {
        this.descricaoitem = descricaoitem;
    }

    public String getDescricaosubcategoria() {
        return descricaosubcategoria;
    }

    public Categoriainversao descricaosubcategoria(String descricaosubcategoria) {
        this.descricaosubcategoria = descricaosubcategoria;
        return this;
    }

    public void setDescricaosubcategoria(String descricaosubcategoria) {
        this.descricaosubcategoria = descricaosubcategoria;
    }

    public String getDescricaosubitem() {
        return descricaosubitem;
    }

    public Categoriainversao descricaosubitem(String descricaosubitem) {
        this.descricaosubitem = descricaosubitem;
        return this;
    }

    public void setDescricaosubitem(String descricaosubitem) {
        this.descricaosubitem = descricaosubitem;
    }

    public Integer getIdcategoriainversao() {
        return idcategoriainversao;
    }

    public Categoriainversao idcategoriainversao(Integer idcategoriainversao) {
        this.idcategoriainversao = idcategoriainversao;
        return this;
    }

    public void setIdcategoriainversao(Integer idcategoriainversao) {
        this.idcategoriainversao = idcategoriainversao;
    }

    public String getItem() {
        return item;
    }

    public Categoriainversao item(String item) {
        this.item = item;
        return this;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Double getPercentualcatagente() {
        return percentualcatagente;
    }

    public Categoriainversao percentualcatagente(Double percentualcatagente) {
        this.percentualcatagente = percentualcatagente;
        return this;
    }

    public void setPercentualcatagente(Double percentualcatagente) {
        this.percentualcatagente = percentualcatagente;
    }

    public Double getPercentualcatlocal() {
        return percentualcatlocal;
    }

    public Categoriainversao percentualcatlocal(Double percentualcatlocal) {
        this.percentualcatlocal = percentualcatlocal;
        return this;
    }

    public void setPercentualcatlocal(Double percentualcatlocal) {
        this.percentualcatlocal = percentualcatlocal;
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public Categoriainversao subcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
        return this;
    }

    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }

    public String getSubitem() {
        return subitem;
    }

    public Categoriainversao subitem(String subitem) {
        this.subitem = subitem;
        return this;
    }

    public void setSubitem(String subitem) {
        this.subitem = subitem;
    }

    public Double getValorcatagente() {
        return valorcatagente;
    }

    public Categoriainversao valorcatagente(Double valorcatagente) {
        this.valorcatagente = valorcatagente;
        return this;
    }

    public void setValorcatagente(Double valorcatagente) {
        this.valorcatagente = valorcatagente;
    }

    public Double getValorcatlocal() {
        return valorcatlocal;
    }

    public Categoriainversao valorcatlocal(Double valorcatlocal) {
        this.valorcatlocal = valorcatlocal;
        return this;
    }

    public void setValorcatlocal(Double valorcatlocal) {
        this.valorcatlocal = valorcatlocal;
    }

    public Double getValorporcategoria() {
        return valorporcategoria;
    }

    public Categoriainversao valorporcategoria(Double valorporcategoria) {
        this.valorporcategoria = valorporcategoria;
        return this;
    }

    public void setValorporcategoria(Double valorporcategoria) {
        this.valorporcategoria = valorporcategoria;
    }

    public Contratoagente getIdcontratoagente() {
        return idcontratoagente;
    }

    public Categoriainversao idcontratoagente(Contratoagente contratoagente) {
        this.idcontratoagente = contratoagente;
        return this;
    }

    public void setIdcontratoagente(Contratoagente contratoagente) {
        this.idcontratoagente = contratoagente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Categoriainversao categoriainversao = (Categoriainversao) o;
        if (categoriainversao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), categoriainversao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Categoriainversao{" +
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
