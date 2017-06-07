package br.com.homemade.service.dto;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the Referencia entity.
 */
public class ReferenciaDTO implements Serializable {

    private Long id;

    private String aporte;

    private Integer idreferencia;

    private String moeda;

    private Integer nreferencia;

    private BigDecimal valorreferencia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAporte() {
        return aporte;
    }

    public void setAporte(String aporte) {
        this.aporte = aporte;
    }

    public Integer getIdreferencia() {
        return idreferencia;
    }

    public void setIdreferencia(Integer idreferencia) {
        this.idreferencia = idreferencia;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public Integer getNreferencia() {
        return nreferencia;
    }

    public void setNreferencia(Integer nreferencia) {
        this.nreferencia = nreferencia;
    }

    public BigDecimal getValorreferencia() {
        return valorreferencia;
    }

    public void setValorreferencia(BigDecimal valorreferencia) {
        this.valorreferencia = valorreferencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReferenciaDTO referenciaDTO = (ReferenciaDTO) o;
        if(referenciaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), referenciaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReferenciaDTO{" +
            "id=" + getId() +
            ", aporte='" + getAporte() + "'" +
            ", idreferencia='" + getIdreferencia() + "'" +
            ", moeda='" + getMoeda() + "'" +
            ", nreferencia='" + getNreferencia() + "'" +
            ", valorreferencia='" + getValorreferencia() + "'" +
            "}";
    }
}
