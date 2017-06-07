package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Funcionario.
 */
@Entity
@Table(name = "funcionario")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "funcao")
    private String funcao;

    @Column(name = "idfuncionarios")
    private Integer idfuncionarios;

    @Column(name = "nomefuncionario")
    private String nomefuncionario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFuncao() {
        return funcao;
    }

    public Funcionario funcao(String funcao) {
        this.funcao = funcao;
        return this;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public Integer getIdfuncionarios() {
        return idfuncionarios;
    }

    public Funcionario idfuncionarios(Integer idfuncionarios) {
        this.idfuncionarios = idfuncionarios;
        return this;
    }

    public void setIdfuncionarios(Integer idfuncionarios) {
        this.idfuncionarios = idfuncionarios;
    }

    public String getNomefuncionario() {
        return nomefuncionario;
    }

    public Funcionario nomefuncionario(String nomefuncionario) {
        this.nomefuncionario = nomefuncionario;
        return this;
    }

    public void setNomefuncionario(String nomefuncionario) {
        this.nomefuncionario = nomefuncionario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Funcionario funcionario = (Funcionario) o;
        if (funcionario.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), funcionario.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Funcionario{" +
            "id=" + getId() +
            ", funcao='" + getFuncao() + "'" +
            ", idfuncionarios='" + getIdfuncionarios() + "'" +
            ", nomefuncionario='" + getNomefuncionario() + "'" +
            "}";
    }
}
