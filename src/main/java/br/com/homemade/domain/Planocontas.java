package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Planocontas.
 */
@Entity
@Table(name = "planocontas")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Planocontas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "idplanocontas")
    private Integer idplanocontas;

    @Column(name = "ncontabil")
    private String ncontabil;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "tipoconta")
    private String tipoconta;

    @Column(name = "tipolancamento")
    private String tipolancamento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdplanocontas() {
        return idplanocontas;
    }

    public Planocontas idplanocontas(Integer idplanocontas) {
        this.idplanocontas = idplanocontas;
        return this;
    }

    public void setIdplanocontas(Integer idplanocontas) {
        this.idplanocontas = idplanocontas;
    }

    public String getNcontabil() {
        return ncontabil;
    }

    public Planocontas ncontabil(String ncontabil) {
        this.ncontabil = ncontabil;
        return this;
    }

    public void setNcontabil(String ncontabil) {
        this.ncontabil = ncontabil;
    }

    public String getDescricao() {
        return descricao;
    }

    public Planocontas descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipoconta() {
        return tipoconta;
    }

    public Planocontas tipoconta(String tipoconta) {
        this.tipoconta = tipoconta;
        return this;
    }

    public void setTipoconta(String tipoconta) {
        this.tipoconta = tipoconta;
    }

    public String getTipolancamento() {
        return tipolancamento;
    }

    public Planocontas tipolancamento(String tipolancamento) {
        this.tipolancamento = tipolancamento;
        return this;
    }

    public void setTipolancamento(String tipolancamento) {
        this.tipolancamento = tipolancamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Planocontas planocontas = (Planocontas) o;
        if (planocontas.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), planocontas.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Planocontas{" +
            "id=" + getId() +
            ", idplanocontas='" + getIdplanocontas() + "'" +
            ", ncontabil='" + getNcontabil() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", tipoconta='" + getTipoconta() + "'" +
            ", tipolancamento='" + getTipolancamento() + "'" +
            "}";
    }
}
