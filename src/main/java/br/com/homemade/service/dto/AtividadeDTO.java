package br.com.homemade.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Atividade entity.
 */
public class AtividadeDTO implements Serializable {

    private Long id;

    private String codigo;

    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AtividadeDTO atividadeDTO = (AtividadeDTO) o;
        if(atividadeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), atividadeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AtividadeDTO{" +
            "id=" + getId() +
            ", codigo='" + getCodigo() + "'" +
            ", descricao='" + getDescricao() + "'" +
            "}";
    }
}
