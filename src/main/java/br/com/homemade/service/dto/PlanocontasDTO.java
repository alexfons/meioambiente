package br.com.homemade.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Planocontas entity.
 */
public class PlanocontasDTO implements Serializable {

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

        PlanocontasDTO planocontasDTO = (PlanocontasDTO) o;
        if(planocontasDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), planocontasDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PlanocontasDTO{" +
            "id=" + getId() +
            "}";
    }
}
