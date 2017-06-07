package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A InformeCertifIrreg.
 */
@Entity
@Table(name = "informe_certif_irreg")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class InformeCertifIrreg implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne
    private Informe informe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Informe getInforme() {
        return informe;
    }

    public InformeCertifIrreg informe(Informe informe) {
        this.informe = informe;
        return this;
    }

    public void setInforme(Informe informe) {
        this.informe = informe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InformeCertifIrreg informeCertifIrreg = (InformeCertifIrreg) o;
        if (informeCertifIrreg.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), informeCertifIrreg.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InformeCertifIrreg{" +
            "id=" + getId() +
            "}";
    }
}
