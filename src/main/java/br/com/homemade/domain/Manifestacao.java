package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Manifestacao.
 */
@Entity
@Table(name = "manifestacao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Manifestacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "dataaviso")
    private ZonedDateTime dataaviso;

    @Column(name = "dataentrega")
    private ZonedDateTime dataentrega;

    @Column(name = "numero")
    private Integer numero;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDataaviso() {
        return dataaviso;
    }

    public Manifestacao dataaviso(ZonedDateTime dataaviso) {
        this.dataaviso = dataaviso;
        return this;
    }

    public void setDataaviso(ZonedDateTime dataaviso) {
        this.dataaviso = dataaviso;
    }

    public ZonedDateTime getDataentrega() {
        return dataentrega;
    }

    public Manifestacao dataentrega(ZonedDateTime dataentrega) {
        this.dataentrega = dataentrega;
        return this;
    }

    public void setDataentrega(ZonedDateTime dataentrega) {
        this.dataentrega = dataentrega;
    }

    public Integer getNumero() {
        return numero;
    }

    public Manifestacao numero(Integer numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Manifestacao manifestacao = (Manifestacao) o;
        if (manifestacao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), manifestacao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Manifestacao{" +
            "id=" + getId() +
            ", dataaviso='" + getDataaviso() + "'" +
            ", dataentrega='" + getDataentrega() + "'" +
            ", numero='" + getNumero() + "'" +
            "}";
    }
}
