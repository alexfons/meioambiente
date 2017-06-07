package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Natureza.
 */
@Entity
@Table(name = "natureza")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Natureza implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "descnatureza")
    private String descnatureza;

    @Column(name = "descsubacao")
    private String descsubacao;

    @Column(name = "idnatureza")
    private Integer idnatureza;

    @Column(name = "numnatureza")
    private Integer numnatureza;

    @Column(name = "subacao")
    private Integer subacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescnatureza() {
        return descnatureza;
    }

    public Natureza descnatureza(String descnatureza) {
        this.descnatureza = descnatureza;
        return this;
    }

    public void setDescnatureza(String descnatureza) {
        this.descnatureza = descnatureza;
    }

    public String getDescsubacao() {
        return descsubacao;
    }

    public Natureza descsubacao(String descsubacao) {
        this.descsubacao = descsubacao;
        return this;
    }

    public void setDescsubacao(String descsubacao) {
        this.descsubacao = descsubacao;
    }

    public Integer getIdnatureza() {
        return idnatureza;
    }

    public Natureza idnatureza(Integer idnatureza) {
        this.idnatureza = idnatureza;
        return this;
    }

    public void setIdnatureza(Integer idnatureza) {
        this.idnatureza = idnatureza;
    }

    public Integer getNumnatureza() {
        return numnatureza;
    }

    public Natureza numnatureza(Integer numnatureza) {
        this.numnatureza = numnatureza;
        return this;
    }

    public void setNumnatureza(Integer numnatureza) {
        this.numnatureza = numnatureza;
    }

    public Integer getSubacao() {
        return subacao;
    }

    public Natureza subacao(Integer subacao) {
        this.subacao = subacao;
        return this;
    }

    public void setSubacao(Integer subacao) {
        this.subacao = subacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Natureza natureza = (Natureza) o;
        if (natureza.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), natureza.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Natureza{" +
            "id=" + getId() +
            ", descnatureza='" + getDescnatureza() + "'" +
            ", descsubacao='" + getDescsubacao() + "'" +
            ", idnatureza='" + getIdnatureza() + "'" +
            ", numnatureza='" + getNumnatureza() + "'" +
            ", subacao='" + getSubacao() + "'" +
            "}";
    }
}
