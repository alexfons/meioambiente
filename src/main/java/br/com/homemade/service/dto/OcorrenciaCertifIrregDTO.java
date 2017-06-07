package br.com.homemade.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the OcorrenciaCertifIrreg entity.
 */
public class OcorrenciaCertifIrregDTO implements Serializable {

    private Long id;

    private Long ocorrenciaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOcorrenciaId() {
        return ocorrenciaId;
    }

    public void setOcorrenciaId(Long ocorrenciaId) {
        this.ocorrenciaId = ocorrenciaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OcorrenciaCertifIrregDTO ocorrenciaCertifIrregDTO = (OcorrenciaCertifIrregDTO) o;
        if(ocorrenciaCertifIrregDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ocorrenciaCertifIrregDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OcorrenciaCertifIrregDTO{" +
            "id=" + getId() +
            "}";
    }
}
