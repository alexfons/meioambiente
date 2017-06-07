package br.com.homemade.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Coluna.
 */
@Entity
@Table(name = "coluna")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Coluna implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "lista")
    private Boolean lista;

    @Column(name = "sequencia")
    private Integer sequencia;

    @OneToMany(mappedBy = "coluna")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Linha> opcoes = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Coluna descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean isLista() {
        return lista;
    }

    public Coluna lista(Boolean lista) {
        this.lista = lista;
        return this;
    }

    public void setLista(Boolean lista) {
        this.lista = lista;
    }

    public Integer getSequencia() {
        return sequencia;
    }

    public Coluna sequencia(Integer sequencia) {
        this.sequencia = sequencia;
        return this;
    }

    public void setSequencia(Integer sequencia) {
        this.sequencia = sequencia;
    }

    public Set<Linha> getOpcoes() {
        return opcoes;
    }

    public Coluna opcoes(Set<Linha> linhas) {
        this.opcoes = linhas;
        return this;
    }

    public Coluna addOpcoes(Linha linha) {
        this.opcoes.add(linha);
        linha.setColuna(this);
        return this;
    }

    public Coluna removeOpcoes(Linha linha) {
        this.opcoes.remove(linha);
        linha.setColuna(null);
        return this;
    }

    public void setOpcoes(Set<Linha> linhas) {
        this.opcoes = linhas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coluna coluna = (Coluna) o;
        if (coluna.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), coluna.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Coluna{" +
            "id=" + getId() +
            ", descricao='" + getDescricao() + "'" +
            ", lista='" + isLista() + "'" +
            ", sequencia='" + getSequencia() + "'" +
            "}";
    }
}
