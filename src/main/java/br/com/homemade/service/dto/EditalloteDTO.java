package br.com.homemade.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Editallote entity.
 */
public class EditalloteDTO implements Serializable {

    private Long id;

    private ZonedDateTime dataprovacaolote;

    private ZonedDateTime datarelatoriolote;

    private Integer idlote;

    private String lote;

    private String objeto;

    private String prazo;

    private BigDecimal valororcado;

    private Long numeroeditalId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDataprovacaolote() {
        return dataprovacaolote;
    }

    public void setDataprovacaolote(ZonedDateTime dataprovacaolote) {
        this.dataprovacaolote = dataprovacaolote;
    }

    public ZonedDateTime getDatarelatoriolote() {
        return datarelatoriolote;
    }

    public void setDatarelatoriolote(ZonedDateTime datarelatoriolote) {
        this.datarelatoriolote = datarelatoriolote;
    }

    public Integer getIdlote() {
        return idlote;
    }

    public void setIdlote(Integer idlote) {
        this.idlote = idlote;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getPrazo() {
        return prazo;
    }

    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }

    public BigDecimal getValororcado() {
        return valororcado;
    }

    public void setValororcado(BigDecimal valororcado) {
        this.valororcado = valororcado;
    }

    public Long getNumeroeditalId() {
        return numeroeditalId;
    }

    public void setNumeroeditalId(Long editalId) {
        this.numeroeditalId = editalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EditalloteDTO editalloteDTO = (EditalloteDTO) o;
        if(editalloteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), editalloteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EditalloteDTO{" +
            "id=" + getId() +
            ", dataprovacaolote='" + getDataprovacaolote() + "'" +
            ", datarelatoriolote='" + getDatarelatoriolote() + "'" +
            ", idlote='" + getIdlote() + "'" +
            ", lote='" + getLote() + "'" +
            ", objeto='" + getObjeto() + "'" +
            ", prazo='" + getPrazo() + "'" +
            ", valororcado='" + getValororcado() + "'" +
            "}";
    }
}
