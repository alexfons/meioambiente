package br.com.homemade.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Tabela entity.
 */
public class TabelaDTO implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TabelaDTO tabelaDTO = (TabelaDTO) o;
        if(tabelaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tabelaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TabelaDTO{" +
            "id=" + getId() +
            "}";
    }
}
