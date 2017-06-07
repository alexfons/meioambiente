package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A Referenciacontrato.
 */
@Entity
@Table(name = "referenciacontrato")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Referenciacontrato implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "aporte")
    private String aporte;

    @Column(name = "moeda")
    private String moeda;

    @Column(name = "idreferenciacontrato")
    private Integer idreferenciacontrato;

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

    public Referenciacontrato aporte(String aporte) {
        this.aporte = aporte;
        return this;
    }

    public void setAporte(String aporte) {
        this.aporte = aporte;
    }

    public String getMoeda() {
        return moeda;
    }

    public Referenciacontrato moeda(String moeda) {
        this.moeda = moeda;
        return this;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public Integer getIdreferenciacontrato() {
        return idreferenciacontrato;
    }

    public Referenciacontrato idreferenciacontrato(Integer idreferenciacontrato) {
        this.idreferenciacontrato = idreferenciacontrato;
        return this;
    }

    public void setIdreferenciacontrato(Integer idreferenciacontrato) {
        this.idreferenciacontrato = idreferenciacontrato;
    }

    public Integer getNreferencia() {
        return nreferencia;
    }

    public Referenciacontrato nreferencia(Integer nreferencia) {
        this.nreferencia = nreferencia;
        return this;
    }

    public void setNreferencia(Integer nreferencia) {
        this.nreferencia = nreferencia;
    }

    public BigDecimal getValorreferencia() {
        return valorreferencia;
    }

    public Referenciacontrato valorreferencia(BigDecimal valorreferencia) {
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
        Referenciacontrato referenciacontrato = (Referenciacontrato) o;
        if (referenciacontrato.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), referenciacontrato.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Referenciacontrato{" +
            "id=" + getId() +
            ", aporte='" + getAporte() + "'" +
            ", moeda='" + getMoeda() + "'" +
            ", idreferenciacontrato='" + getIdreferenciacontrato() + "'" +
            ", nreferencia='" + getNreferencia() + "'" +
            ", valorreferencia='" + getValorreferencia() + "'" +
            "}";
    }
}
