package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Contabancaria.
 */
@Entity
@Table(name = "contabancaria")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Contabancaria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "idcontabancaria")
    private Integer idcontabancaria;

    @Column(name = "nconta")
    private String nconta;

    @Column(name = "nbanco")
    private String nbanco;

    @Column(name = "descricao")
    private String descricao;

    @ManyToOne
    private Planocontas idplanocontas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdcontabancaria() {
        return idcontabancaria;
    }

    public Contabancaria idcontabancaria(Integer idcontabancaria) {
        this.idcontabancaria = idcontabancaria;
        return this;
    }

    public void setIdcontabancaria(Integer idcontabancaria) {
        this.idcontabancaria = idcontabancaria;
    }

    public String getNconta() {
        return nconta;
    }

    public Contabancaria nconta(String nconta) {
        this.nconta = nconta;
        return this;
    }

    public void setNconta(String nconta) {
        this.nconta = nconta;
    }

    public String getNbanco() {
        return nbanco;
    }

    public Contabancaria nbanco(String nbanco) {
        this.nbanco = nbanco;
        return this;
    }

    public void setNbanco(String nbanco) {
        this.nbanco = nbanco;
    }

    public String getDescricao() {
        return descricao;
    }

    public Contabancaria descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Planocontas getIdplanocontas() {
        return idplanocontas;
    }

    public Contabancaria idplanocontas(Planocontas planocontas) {
        this.idplanocontas = planocontas;
        return this;
    }

    public void setIdplanocontas(Planocontas planocontas) {
        this.idplanocontas = planocontas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contabancaria contabancaria = (Contabancaria) o;
        if (contabancaria.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contabancaria.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Contabancaria{" +
            "id=" + getId() +
            ", idcontabancaria='" + getIdcontabancaria() + "'" +
            ", nconta='" + getNconta() + "'" +
            ", nbanco='" + getNbanco() + "'" +
            ", descricao='" + getDescricao() + "'" +
            "}";
    }
}
