package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Obraatividade.
 */
@Entity
@Table(name = "obraatividade")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Obraatividade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "peso")
    private Double peso;

    @Column(name = "revestimento")
    private String revestimento;

    @ManyToOne
    private Atividade atividade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPeso() {
        return peso;
    }

    public Obraatividade peso(Double peso) {
        this.peso = peso;
        return this;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getRevestimento() {
        return revestimento;
    }

    public Obraatividade revestimento(String revestimento) {
        this.revestimento = revestimento;
        return this;
    }

    public void setRevestimento(String revestimento) {
        this.revestimento = revestimento;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public Obraatividade atividade(Atividade atividade) {
        this.atividade = atividade;
        return this;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Obraatividade obraatividade = (Obraatividade) o;
        if (obraatividade.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), obraatividade.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Obraatividade{" +
            "id=" + getId() +
            ", peso='" + getPeso() + "'" +
            ", revestimento='" + getRevestimento() + "'" +
            "}";
    }
}
