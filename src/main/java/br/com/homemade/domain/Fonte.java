package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A Fonte.
 */
@Entity
@Table(name = "fonte")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Fonte implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "fonte")
    private Integer fonte;

    @Column(name = "indiceagente", precision=10, scale=2)
    private BigDecimal indiceagente;

    @Column(name = "indicelocal", precision=10, scale=2)
    private BigDecimal indicelocal;

    @ManyToOne
    private Contabancaria idcontabancaria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Fonte descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getFonte() {
        return fonte;
    }

    public Fonte fonte(Integer fonte) {
        this.fonte = fonte;
        return this;
    }

    public void setFonte(Integer fonte) {
        this.fonte = fonte;
    }

    public BigDecimal getIndiceagente() {
        return indiceagente;
    }

    public Fonte indiceagente(BigDecimal indiceagente) {
        this.indiceagente = indiceagente;
        return this;
    }

    public void setIndiceagente(BigDecimal indiceagente) {
        this.indiceagente = indiceagente;
    }

    public BigDecimal getIndicelocal() {
        return indicelocal;
    }

    public Fonte indicelocal(BigDecimal indicelocal) {
        this.indicelocal = indicelocal;
        return this;
    }

    public void setIndicelocal(BigDecimal indicelocal) {
        this.indicelocal = indicelocal;
    }

    public Contabancaria getIdcontabancaria() {
        return idcontabancaria;
    }

    public Fonte idcontabancaria(Contabancaria contabancaria) {
        this.idcontabancaria = contabancaria;
        return this;
    }

    public void setIdcontabancaria(Contabancaria contabancaria) {
        this.idcontabancaria = contabancaria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Fonte fonte = (Fonte) o;
        if (fonte.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fonte.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Fonte{" +
            "id=" + getId() +
            ", descricao='" + getDescricao() + "'" +
            ", fonte='" + getFonte() + "'" +
            ", indiceagente='" + getIndiceagente() + "'" +
            ", indicelocal='" + getIndicelocal() + "'" +
            "}";
    }
}
