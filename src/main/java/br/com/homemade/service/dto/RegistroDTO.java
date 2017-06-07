package br.com.homemade.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Registro entity.
 */
public class RegistroDTO implements Serializable {

    private Long id;

    private Long opcoesId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOpcoesId() {
        return opcoesId;
    }

    public void setOpcoesId(Long linhaId) {
        this.opcoesId = linhaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RegistroDTO registroDTO = (RegistroDTO) o;
        if(registroDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), registroDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RegistroDTO{" +
            "id=" + getId() +
            "}";
    }
}
