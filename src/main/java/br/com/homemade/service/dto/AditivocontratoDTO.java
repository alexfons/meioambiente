package br.com.homemade.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the Aditivocontrato entity.
 */
public class AditivocontratoDTO implements Serializable {

    private Long id;

    private ZonedDateTime data;

    private Integer numaditivo;

    private Integer prazoaditivo;

    private String tipoaditivo;

    private BigDecimal valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getData() {
        return data;
    }

    public void setData(ZonedDateTime data) {
        this.data = data;
    }

    public Integer getNumaditivo() {
        return numaditivo;
    }

    public void setNumaditivo(Integer numaditivo) {
        this.numaditivo = numaditivo;
    }

    public Integer getPrazoaditivo() {
        return prazoaditivo;
    }

    public void setPrazoaditivo(Integer prazoaditivo) {
        this.prazoaditivo = prazoaditivo;
    }

    public String getTipoaditivo() {
        return tipoaditivo;
    }

    public void setTipoaditivo(String tipoaditivo) {
        this.tipoaditivo = tipoaditivo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AditivocontratoDTO aditivocontratoDTO = (AditivocontratoDTO) o;
        if(aditivocontratoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), aditivocontratoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AditivocontratoDTO{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            ", numaditivo='" + getNumaditivo() + "'" +
            ", prazoaditivo='" + getPrazoaditivo() + "'" +
            ", tipoaditivo='" + getTipoaditivo() + "'" +
            ", valor='" + getValor() + "'" +
            "}";
    }
}
