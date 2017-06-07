package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Residente.
 */
@Entity
@Table(name = "residente")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Residente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "funcao")
    private String funcao;

    @Column(name = "localtrabalho")
    private String localtrabalho;

    @Column(name = "matricula")
    private String matricula;

    @Column(name = "nome")
    private String nome;

    @Column(name = "superintendencia")
    private String superintendencia;

    @Column(name = "telefone")
    private Long telefone;

    @Column(name = "telefonecomercial")
    private Long telefonecomercial;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public Residente email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFuncao() {
        return funcao;
    }

    public Residente funcao(String funcao) {
        this.funcao = funcao;
        return this;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getLocaltrabalho() {
        return localtrabalho;
    }

    public Residente localtrabalho(String localtrabalho) {
        this.localtrabalho = localtrabalho;
        return this;
    }

    public void setLocaltrabalho(String localtrabalho) {
        this.localtrabalho = localtrabalho;
    }

    public String getMatricula() {
        return matricula;
    }

    public Residente matricula(String matricula) {
        this.matricula = matricula;
        return this;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public Residente nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSuperintendencia() {
        return superintendencia;
    }

    public Residente superintendencia(String superintendencia) {
        this.superintendencia = superintendencia;
        return this;
    }

    public void setSuperintendencia(String superintendencia) {
        this.superintendencia = superintendencia;
    }

    public Long getTelefone() {
        return telefone;
    }

    public Residente telefone(Long telefone) {
        this.telefone = telefone;
        return this;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }

    public Long getTelefonecomercial() {
        return telefonecomercial;
    }

    public Residente telefonecomercial(Long telefonecomercial) {
        this.telefonecomercial = telefonecomercial;
        return this;
    }

    public void setTelefonecomercial(Long telefonecomercial) {
        this.telefonecomercial = telefonecomercial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Residente residente = (Residente) o;
        if (residente.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), residente.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Residente{" +
            "id=" + getId() +
            ", email='" + getEmail() + "'" +
            ", funcao='" + getFuncao() + "'" +
            ", localtrabalho='" + getLocaltrabalho() + "'" +
            ", matricula='" + getMatricula() + "'" +
            ", nome='" + getNome() + "'" +
            ", superintendencia='" + getSuperintendencia() + "'" +
            ", telefone='" + getTelefone() + "'" +
            ", telefonecomercial='" + getTelefonecomercial() + "'" +
            "}";
    }
}
