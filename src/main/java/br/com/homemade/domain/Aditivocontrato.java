package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Aditivocontrato.
 */
@Entity
@Table(name = "aditivocontrato")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Aditivocontrato implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "numaditivo")
    private Integer numaditivo;

    @Column(name = "tipoaditivo")
    private String tipoaditivo;

    @Column(name = "data")
    private ZonedDateTime data;

    @Column(name = "prazoaditivo")
    private Integer prazoaditivo;

    @Column(name = "valor", precision=10, scale=2)
    private BigDecimal valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumaditivo() {
        return numaditivo;
    }

    public Aditivocontrato numaditivo(Integer numaditivo) {
        this.numaditivo = numaditivo;
        return this;
    }

    public void setNumaditivo(Integer numaditivo) {
        this.numaditivo = numaditivo;
    }

    public String getTipoaditivo() {
        return tipoaditivo;
    }

    public Aditivocontrato tipoaditivo(String tipoaditivo) {
        this.tipoaditivo = tipoaditivo;
        return this;
    }

    public void setTipoaditivo(String tipoaditivo) {
        this.tipoaditivo = tipoaditivo;
    }

    public ZonedDateTime getData() {
        return data;
    }

    public Aditivocontrato data(ZonedDateTime data) {
        this.data = data;
        return this;
    }

    public void setData(ZonedDateTime data) {
        this.data = data;
    }

    public Integer getPrazoaditivo() {
        return prazoaditivo;
    }

    public Aditivocontrato prazoaditivo(Integer prazoaditivo) {
        this.prazoaditivo = prazoaditivo;
        return this;
    }

    public void setPrazoaditivo(Integer prazoaditivo) {
        this.prazoaditivo = prazoaditivo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Aditivocontrato valor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Aditivocontrato aditivocontrato = (Aditivocontrato) o;
        if (aditivocontrato.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), aditivocontrato.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Aditivocontrato{" +
            "id=" + getId() +
            ", numaditivo='" + getNumaditivo() + "'" +
            ", tipoaditivo='" + getTipoaditivo() + "'" +
            ", data='" + getData() + "'" +
            ", prazoaditivo='" + getPrazoaditivo() + "'" +
            ", valor='" + getValor() + "'" +
            "}";
    }
}
