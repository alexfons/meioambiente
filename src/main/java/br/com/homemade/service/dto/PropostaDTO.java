package br.com.homemade.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Proposta entity.
 */
public class PropostaDTO implements Serializable {

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

        PropostaDTO propostaDTO = (PropostaDTO) o;
        if(propostaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), propostaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PropostaDTO{" +
            "id=" + getId() +
            "}";
    }
}
