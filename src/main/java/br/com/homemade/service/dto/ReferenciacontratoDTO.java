package br.com.homemade.service.dto;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the Referenciacontrato entity.
 */
public class ReferenciacontratoDTO implements Serializable {

    private Long id;

    private String aporte;

    private String moeda;

    private Integer idreferenciacontrato;

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

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public Integer getIdreferenciacontrato() {
        return idreferenciacontrato;
    }

    public void setIdreferenciacontrato(Integer idreferenciacontrato) {
        this.idreferenciacontrato = idreferenciacontrato;
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

        ReferenciacontratoDTO referenciacontratoDTO = (ReferenciacontratoDTO) o;
        if(referenciacontratoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), referenciacontratoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReferenciacontratoDTO{" +
            "id=" + getId() +
            ", aporte='" + getAporte() + "'" +
            ", moeda='" + getMoeda() + "'" +
            ", idreferenciacontrato='" + getIdreferenciacontrato() + "'" +
            ", nreferencia='" + getNreferencia() + "'" +
            ", valorreferencia='" + getValorreferencia() + "'" +
            "}";
    }
}
