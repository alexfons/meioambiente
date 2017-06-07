package br.com.homemade.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Coluna entity.
 */
public class ColunaDTO implements Serializable {

    private Long id;

    private String descricao;

    private Boolean lista;

    private Integer sequencia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean isLista() {
        return lista;
    }

    public void setLista(Boolean lista) {
        this.lista = lista;
    }

    public Integer getSequencia() {
        return sequencia;
    }

    public void setSequencia(Integer sequencia) {
        this.sequencia = sequencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ColunaDTO colunaDTO = (ColunaDTO) o;
        if(colunaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), colunaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ColunaDTO{" +
            "id=" + getId() +
            ", descricao='" + getDescricao() + "'" +
            ", lista='" + isLista() + "'" +
            ", sequencia='" + getSequencia() + "'" +
            "}";
    }
}
