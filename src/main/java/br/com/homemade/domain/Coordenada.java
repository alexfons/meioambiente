package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Coordenada.
 */
@Entity
@Table(name = "coordenada")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Coordenada implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "a")
    private Float a;

    @Column(name = "km")
    private Float km;

    @Column(name = "n")
    private Float n;

    @Column(name = "s")
    private Float s;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getA() {
        return a;
    }

    public Coordenada a(Float a) {
        this.a = a;
        return this;
    }

    public void setA(Float a) {
        this.a = a;
    }

    public Float getKm() {
        return km;
    }

    public Coordenada km(Float km) {
        this.km = km;
        return this;
    }

    public void setKm(Float km) {
        this.km = km;
    }

    public Float getN() {
        return n;
    }

    public Coordenada n(Float n) {
        this.n = n;
        return this;
    }

    public void setN(Float n) {
        this.n = n;
    }

    public Float getS() {
        return s;
    }

    public Coordenada s(Float s) {
        this.s = s;
        return this;
    }

    public void setS(Float s) {
        this.s = s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordenada coordenada = (Coordenada) o;
        if (coordenada.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), coordenada.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Coordenada{" +
            "id=" + getId() +
            ", a='" + getA() + "'" +
            ", km='" + getKm() + "'" +
            ", n='" + getN() + "'" +
            ", s='" + getS() + "'" +
            "}";
    }
}
