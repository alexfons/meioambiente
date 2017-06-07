package br.com.homemade.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Funcionario entity.
 */
public class FuncionarioDTO implements Serializable {

    private Long id;

    private String funcao;

    private Integer idfuncionarios;

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

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public Integer getIdfuncionarios() {
        return idfuncionarios;
    }

    public void setIdfuncionarios(Integer idfuncionarios) {
        this.idfuncionarios = idfuncionarios;
    }

    public String getNomefuncionario() {
        return nomefuncionario;
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

        FuncionarioDTO funcionarioDTO = (FuncionarioDTO) o;
        if(funcionarioDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), funcionarioDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FuncionarioDTO{" +
            "id=" + getId() +
            ", funcao='" + getFuncao() + "'" +
            ", idfuncionarios='" + getIdfuncionarios() + "'" +
            ", nomefuncionario='" + getNomefuncionario() + "'" +
            "}";
    }
}
