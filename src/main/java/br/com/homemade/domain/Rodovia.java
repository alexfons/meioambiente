package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Rodovia.
 */
@Entity
@Table(name = "rodovia")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Rodovia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "sgrodovia")
    private String sgrodovia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSgrodovia() {
        return sgrodovia;
    }

    public Rodovia sgrodovia(String sgrodovia) {
        this.sgrodovia = sgrodovia;
        return this;
    }

    public void setSgrodovia(String sgrodovia) {
        this.sgrodovia = sgrodovia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rodovia rodovia = (Rodovia) o;
        if (rodovia.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rodovia.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Rodovia{" +
            "id=" + getId() +
            ", sgrodovia='" + getSgrodovia() + "'" +
            "}";
    }
}
