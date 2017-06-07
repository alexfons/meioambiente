package br.com.homemade.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Manifestacao entity.
 */
public class ManifestacaoDTO implements Serializable {

    private Long id;

    private ZonedDateTime dataaviso;

    private ZonedDateTime dataentrega;

    private Integer numero;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDataaviso() {
        return dataaviso;
    }

    public void setDataaviso(ZonedDateTime dataaviso) {
        this.dataaviso = dataaviso;
    }

    public ZonedDateTime getDataentrega() {
        return dataentrega;
    }

    public void setDataentrega(ZonedDateTime dataentrega) {
        this.dataentrega = dataentrega;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ManifestacaoDTO manifestacaoDTO = (ManifestacaoDTO) o;
        if(manifestacaoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), manifestacaoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ManifestacaoDTO{" +
            "id=" + getId() +
            ", dataaviso='" + getDataaviso() + "'" +
            ", dataentrega='" + getDataentrega() + "'" +
            ", numero='" + getNumero() + "'" +
            "}";
    }
}
