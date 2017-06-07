package br.com.homemade.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Supre entity.
 */
public class SupreDTO implements Serializable {

    private Long id;

    private Integer cdorgaoset;

    private String descricao;

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

    public void setCdorgaoset(Integer cdorgaoset) {
        this.cdorgaoset = cdorgaoset;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
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

        SupreDTO supreDTO = (SupreDTO) o;
        if(supreDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), supreDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SupreDTO{" +
            "id=" + getId() +
            ", cdorgaoset='" + getCdorgaoset() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", nome='" + getNome() + "'" +
            "}";
    }
}
