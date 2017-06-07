package br.com.homemade.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Balanco entity.
 */
public class BalancoDTO implements Serializable {

    private Long id;

    private ZonedDateTime datafim;

    private ZonedDateTime datainicio;

    private String descricao;

    private Double taxa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDatafim() {
        return datafim;
    }

    public void setDatafim(ZonedDateTime datafim) {
        this.datafim = datafim;
    }

    public ZonedDateTime getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(ZonedDateTime datainicio) {
        this.datainicio = datainicio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getTaxa() {
        return taxa;
    }

    public void setTaxa(Double taxa) {
        this.taxa = taxa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BalancoDTO balancoDTO = (BalancoDTO) o;
        if(balancoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), balancoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BalancoDTO{" +
            "id=" + getId() +
            ", datafim='" + getDatafim() + "'" +
            ", datainicio='" + getDatainicio() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", taxa='" + getTaxa() + "'" +
            "}";
    }
}
