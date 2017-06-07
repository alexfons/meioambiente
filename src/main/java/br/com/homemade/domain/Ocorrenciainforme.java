package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Ocorrenciainforme.
 */
@Entity
@Table(name = "ocorrenciainforme")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Ocorrenciainforme implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "enquadramento")
    private String enquadramento;

    @ManyToOne
    private Ocorrencia ocorrencia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnquadramento() {
        return enquadramento;
    }

    public Ocorrenciainforme enquadramento(String enquadramento) {
        this.enquadramento = enquadramento;
        return this;
    }

    public void setEnquadramento(String enquadramento) {
        this.enquadramento = enquadramento;
    }

    public Ocorrencia getOcorrencia() {
        return ocorrencia;
    }

    public Ocorrenciainforme ocorrencia(Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
        return this;
    }

    public void setOcorrencia(Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ocorrenciainforme ocorrenciainforme = (Ocorrenciainforme) o;
        if (ocorrenciainforme.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ocorrenciainforme.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Ocorrenciainforme{" +
            "id=" + getId() +
            ", enquadramento='" + getEnquadramento() + "'" +
            "}";
    }
}
