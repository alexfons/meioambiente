package br.com.homemade.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ResponsavelTipo entity.
 */
public class ResponsavelTipoDTO implements Serializable {

    private Long id;

    private String email;

    private String funcao;

    private String localtrabalho;

    private String matricula;

    private String nome;

    private String superintendencia;

    private Long telefone;

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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getLocaltrabalho() {
        return localtrabalho;
    }

    public void setLocaltrabalho(String localtrabalho) {
        this.localtrabalho = localtrabalho;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSuperintendencia() {
        return superintendencia;
    }

    public void setSuperintendencia(String superintendencia) {
        this.superintendencia = superintendencia;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }

    public Long getTelefonecomercial() {
        return telefonecomercial;
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

        ResponsavelTipoDTO responsavelTipoDTO = (ResponsavelTipoDTO) o;
        if(responsavelTipoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), responsavelTipoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ResponsavelTipoDTO{" +
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
