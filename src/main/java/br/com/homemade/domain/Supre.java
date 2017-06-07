package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Supre.
 */
@Entity
@Table(name = "supre")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Supre implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "cdorgaoset")
    private Integer cdorgaoset;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "nome")
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCdorgaoset() {
        return cdorgaoset;
    }

    public Supre cdorgaoset(Integer cdorgaoset) {
        this.cdorgaoset = cdorgaoset;
        return this;
    }

    public void setCdorgaoset(Integer cdorgaoset) {
        this.cdorgaoset = cdorgaoset;
    }

    public String getDescricao() {
        return descricao;
    }

    public Supre descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public Supre nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Supre supre = (Supre) o;
        if (supre.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), supre.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Supre{" +
            "id=" + getId() +
            ", cdorgaoset='" + getCdorgaoset() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", nome='" + getNome() + "'" +
            "}";
    }
}
