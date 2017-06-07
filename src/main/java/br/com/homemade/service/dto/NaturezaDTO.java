package br.com.homemade.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Natureza entity.
 */
public class NaturezaDTO implements Serializable {

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

        NaturezaDTO naturezaDTO = (NaturezaDTO) o;
        if(naturezaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), naturezaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NaturezaDTO{" +
            "id=" + getId() +
            "}";
    }
}
