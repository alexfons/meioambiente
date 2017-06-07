package br.com.homemade.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Informecertificadoirregularidade entity.
 */
public class InformecertificadoirregularidadeDTO implements Serializable {

    private Long id;

    private Long informeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInformeId() {
        return informeId;
    }

    public void setInformeId(Long informeId) {
        this.informeId = informeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InformecertificadoirregularidadeDTO informecertificadoirregularidadeDTO = (InformecertificadoirregularidadeDTO) o;
        if(informecertificadoirregularidadeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), informecertificadoirregularidadeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InformecertificadoirregularidadeDTO{" +
            "id=" + getId() +
            "}";
    }
}
