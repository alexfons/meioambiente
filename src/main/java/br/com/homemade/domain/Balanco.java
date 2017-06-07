package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Balanco.
 */
@Entity
@Table(name = "balanco")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Balanco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "datafim")
    private ZonedDateTime datafim;

    @Column(name = "datainicio")
    private ZonedDateTime datainicio;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "taxa")
    private Double taxa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDatafim() {
        return datafim;
    }

    public Balanco datafim(ZonedDateTime datafim) {
        this.datafim = datafim;
        return this;
    }

    public void setDatafim(ZonedDateTime datafim) {
        this.datafim = datafim;
    }

    public ZonedDateTime getDatainicio() {
        return datainicio;
    }

    public Balanco datainicio(ZonedDateTime datainicio) {
        this.datainicio = datainicio;
        return this;
    }

    public void setDatainicio(ZonedDateTime datainicio) {
        this.datainicio = datainicio;
    }

    public String getDescricao() {
        return descricao;
    }

    public Balanco descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getTaxa() {
        return taxa;
    }

    public Balanco taxa(Double taxa) {
        this.taxa = taxa;
        return this;
    }

    public void setTaxa(Double taxa) {
        this.taxa = taxa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Balanco balanco = (Balanco) o;
        if (balanco.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), balanco.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Balanco{" +
            "id=" + getId() +
            ", datafim='" + getDatafim() + "'" +
            ", datainicio='" + getDatainicio() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", taxa='" + getTaxa() + "'" +
            "}";
    }
}
