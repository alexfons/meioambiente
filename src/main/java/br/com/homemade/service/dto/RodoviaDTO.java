package br.com.homemade.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Rodovia entity.
 */
public class RodoviaDTO implements Serializable {

    private Long id;

    private String sgrodovia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSgrodovia() {
        return sgrodovia;
    }

    public void setSgrodovia(String sgrodovia) {
        this.sgrodovia = sgrodovia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RodoviaDTO rodoviaDTO = (RodoviaDTO) o;
        if(rodoviaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rodoviaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RodoviaDTO{" +
            "id=" + getId() +
            ", sgrodovia='" + getSgrodovia() + "'" +
            "}";
    }
}
