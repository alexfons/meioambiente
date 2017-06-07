package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A TipocertifConfor.
 */
@Entity
@Table(name = "tipocertif_confor")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TipocertifConfor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "subcategoria")
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

    public TipocertifConfor categoria(String categoria) {
        this.categoria = categoria;
        return this;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public TipocertifConfor descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public TipocertifConfor subcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
        return this;
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
        TipocertifConfor tipocertifConfor = (TipocertifConfor) o;
        if (tipocertifConfor.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tipocertifConfor.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TipocertifConfor{" +
            "id=" + getId() +
            ", categoria='" + getCategoria() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", subcategoria='" + getSubcategoria() + "'" +
            "}";
    }
}
