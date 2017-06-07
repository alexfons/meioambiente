package br.com.homemade.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Valoresdesembolso entity.
 */
public class ValoresdesembolsoDTO implements Serializable {

    private Long id;

    private ZonedDateTime datainternalizacao;

    private Integer idvaloresdesembolso;

    private Integer nsolicitacao;

    private String tipodesembolso;

    private BigDecimal valoreais;

    private BigDecimal valorus;

    private ZonedDateTime valuedata;

    private Long idcontabancariaId;

    private Long idreferenciaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDatainternalizacao() {
        return datainternalizacao;
    }

    public void setDatainternalizacao(ZonedDateTime datainternalizacao) {
        this.datainternalizacao = datainternalizacao;
    }

    public Integer getIdvaloresdesembolso() {
        return idvaloresdesembolso;
    }

    public void setIdvaloresdesembolso(Integer idvaloresdesembolso) {
        this.idvaloresdesembolso = idvaloresdesembolso;
    }

    public Integer getNsolicitacao() {
        return nsolicitacao;
    }

    public void setNsolicitacao(Integer nsolicitacao) {
        this.nsolicitacao = nsolicitacao;
    }

    public String getTipodesembolso() {
        return tipodesembolso;
    }

    public void setTipodesembolso(String tipodesembolso) {
        this.tipodesembolso = tipodesembolso;
    }

    public BigDecimal getValoreais() {
        return valoreais;
    }

    public void setValoreais(BigDecimal valoreais) {
        this.valoreais = valoreais;
    }

    public BigDecimal getValorus() {
        return valorus;
    }

    public void setValorus(BigDecimal valorus) {
        this.valorus = valorus;
    }

    public ZonedDateTime getValuedata() {
        return valuedata;
    }

    public void setValuedata(ZonedDateTime valuedata) {
        this.valuedata = valuedata;
    }

    public Long getIdcontabancariaId() {
        return idcontabancariaId;
    }

    public void setIdcontabancariaId(Long contabancariaId) {
        this.idcontabancariaId = contabancariaId;
    }

    public Long getIdreferenciaId() {
        return idreferenciaId;
    }

    public void setIdreferenciaId(Long referenciaId) {
        this.idreferenciaId = referenciaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ValoresdesembolsoDTO valoresdesembolsoDTO = (ValoresdesembolsoDTO) o;
        if(valoresdesembolsoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), valoresdesembolsoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ValoresdesembolsoDTO{" +
            "id=" + getId() +
            ", datainternalizacao='" + getDatainternalizacao() + "'" +
            ", idvaloresdesembolso='" + getIdvaloresdesembolso() + "'" +
            ", nsolicitacao='" + getNsolicitacao() + "'" +
            ", tipodesembolso='" + getTipodesembolso() + "'" +
            ", valoreais='" + getValoreais() + "'" +
            ", valorus='" + getValorus() + "'" +
            ", valuedata='" + getValuedata() + "'" +
            "}";
    }
}
