package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Linha.
 */
@Entity
@Table(name = "linha")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Linha implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne
    private Coluna coluna;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Coluna getColuna() {
        return coluna;
    }

    public Linha coluna(Coluna coluna) {
        this.coluna = coluna;
        return this;
    }

    public void setColuna(Coluna coluna) {
        this.coluna = coluna;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Linha linha = (Linha) o;
        if (linha.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), linha.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Linha{" +
            "id=" + getId() +
            "}";
    }
}
