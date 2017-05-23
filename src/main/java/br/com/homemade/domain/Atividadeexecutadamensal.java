package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Atividadeexecutadamensal.
 */
@Entity
@Table(name = "atividadeexecutadamensal")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Atividadeexecutadamensal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "percentualatacado")
    private Float percentualatacado;

    @Column(name = "fim")
    private Float fim;

    @Column(name = "inicio")
    private Double inicio;

    @Column(name = "lado")
    private String lado;

    @Column(name = "percentualconcluido")
    private Float percentualconcluido;

    @ManyToOne
    private Atividade atividade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getPercentualatacado() {
        return percentualatacado;
    }

    public Atividadeexecutadamensal percentualatacado(Float percentualatacado) {
        this.percentualatacado = percentualatacado;
        return this;
    }

    public void setPercentualatacado(Float percentualatacado) {
        this.percentualatacado = percentualatacado;
    }

    public Float getFim() {
        return fim;
    }

    public Atividadeexecutadamensal fim(Float fim) {
        this.fim = fim;
        return this;
    }

    public void setFim(Float fim) {
        this.fim = fim;
    }

    public Double getInicio() {
        return inicio;
    }

    public Atividadeexecutadamensal inicio(Double inicio) {
        this.inicio = inicio;
        return this;
    }

    public void setInicio(Double inicio) {
        this.inicio = inicio;
    }

    public String getLado() {
        return lado;
    }

    public Atividadeexecutadamensal lado(String lado) {
        this.lado = lado;
        return this;
    }

    public void setLado(String lado) {
        this.lado = lado;
    }

    public Float getPercentualconcluido() {
        return percentualconcluido;
    }

    public Atividadeexecutadamensal percentualconcluido(Float percentualconcluido) {
        this.percentualconcluido = percentualconcluido;
        return this;
    }

    public void setPercentualconcluido(Float percentualconcluido) {
        this.percentualconcluido = percentualconcluido;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public Atividadeexecutadamensal atividade(Atividade atividade) {
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
        Atividadeexecutadamensal atividadeexecutadamensal = (Atividadeexecutadamensal) o;
        if (atividadeexecutadamensal.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), atividadeexecutadamensal.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Atividadeexecutadamensal{" +
            "id=" + getId() +
            ", percentualatacado='" + getPercentualatacado() + "'" +
            ", fim='" + getFim() + "'" +
            ", inicio='" + getInicio() + "'" +
            ", lado='" + getLado() + "'" +
            ", percentualconcluido='" + getPercentualconcluido() + "'" +
            "}";
    }
}
