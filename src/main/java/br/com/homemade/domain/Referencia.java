package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A Referencia.
 */
@Entity
@Table(name = "referencia")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Referencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "aporte")
    private String aporte;

    @Column(name = "idreferencia")
    private Integer idreferencia;

    @Column(name = "moeda")
    private String moeda;

    @Column(name = "nreferencia")
    private Integer nreferencia;

    @Column(name = "valorreferencia", precision=10, scale=2)
    private BigDecimal valorreferencia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAporte() {
        return aporte;
    }

    public Referencia aporte(String aporte) {
        this.aporte = aporte;
        return this;
    }

    public void setAporte(String aporte) {
        this.aporte = aporte;
    }

    public Integer getIdreferencia() {
        return idreferencia;
    }

    public Referencia idreferencia(Integer idreferencia) {
        this.idreferencia = idreferencia;
        return this;
    }

    public void setIdreferencia(Integer idreferencia) {
        this.idreferencia = idreferencia;
    }

    public String getMoeda() {
        return moeda;
    }

    public Referencia moeda(String moeda) {
        this.moeda = moeda;
        return this;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public Integer getNreferencia() {
        return nreferencia;
    }

    public Referencia nreferencia(Integer nreferencia) {
        this.nreferencia = nreferencia;
        return this;
    }

    public void setNreferencia(Integer nreferencia) {
        this.nreferencia = nreferencia;
    }

    public BigDecimal getValorreferencia() {
        return valorreferencia;
    }

    public Referencia valorreferencia(BigDecimal valorreferencia) {
        this.valorreferencia = valorreferencia;
        return this;
    }

    public void setValorreferencia(BigDecimal valorreferencia) {
        this.valorreferencia = valorreferencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Referencia referencia = (Referencia) o;
        if (referencia.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), referencia.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Referencia{" +
            "id=" + getId() +
            ", aporte='" + getAporte() + "'" +
            ", idreferencia='" + getIdreferencia() + "'" +
            ", moeda='" + getMoeda() + "'" +
            ", nreferencia='" + getNreferencia() + "'" +
            ", valorreferencia='" + getValorreferencia() + "'" +
            "}";
    }
}
