package br.com.homemade.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Participante entity.
 */
public class ParticipanteDTO implements Serializable {

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

        ParticipanteDTO participanteDTO = (ParticipanteDTO) o;
        if(participanteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), participanteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ParticipanteDTO{" +
            "id=" + getId() +
            "}";
    }
}
