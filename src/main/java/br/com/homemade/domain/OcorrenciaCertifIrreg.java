package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A OcorrenciaCertifIrreg.
 */
@Entity
@Table(name = "ocorrencia_certif_irreg")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OcorrenciaCertifIrreg implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne
    private Ocorrencia ocorrencia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ocorrencia getOcorrencia() {
        return ocorrencia;
    }

    public OcorrenciaCertifIrreg ocorrencia(Ocorrencia ocorrencia) {
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
        OcorrenciaCertifIrreg ocorrenciaCertifIrreg = (OcorrenciaCertifIrreg) o;
        if (ocorrenciaCertifIrreg.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ocorrenciaCertifIrreg.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OcorrenciaCertifIrreg{" +
            "id=" + getId() +
            "}";
    }
}
