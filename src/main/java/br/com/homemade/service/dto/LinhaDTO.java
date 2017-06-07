package br.com.homemade.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Linha entity.
 */
public class LinhaDTO implements Serializable {

    private Long id;

    private Integer sequencia;

    private String valor;

    private Long colunaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSequencia() {
        return sequencia;
    }

    public void setSequencia(Integer sequencia) {
        this.sequencia = sequencia;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Long getColunaId() {
        return colunaId;
    }

    public void setColunaId(Long colunaId) {
        this.colunaId = colunaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LinhaDTO linhaDTO = (LinhaDTO) o;
        if(linhaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), linhaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LinhaDTO{" +
            "id=" + getId() +
            ", sequencia='" + getSequencia() + "'" +
            ", valor='" + getValor() + "'" +
            "}";
    }
}
