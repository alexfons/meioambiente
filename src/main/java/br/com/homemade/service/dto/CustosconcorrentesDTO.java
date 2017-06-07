package br.com.homemade.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Custosconcorrentes entity.
 */
public class CustosconcorrentesDTO implements Serializable {

    private Long id;

    private ZonedDateTime datainicio;

    private Integer nsolicitacao;

    private BigDecimal valorus;

    private BigDecimal valorpagoreais;

    private BigDecimal taxa;

    private Long contacontabilC1Id;

    private Long contacontabilC2Id;

    private Long contacontabilD1Id;

    private Long contacontabilD2Id;

    private Long divcontacontabilC1Id;

    private Long divcontacontabilD1Id;

    private Long juscontacontabilC1Id;

    private Long juscontacontabilD1Id;

    private Long fonteId;

    private Long idcategoriainversaoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(ZonedDateTime datainicio) {
        this.datainicio = datainicio;
    }

    public Integer getNsolicitacao() {
        return nsolicitacao;
    }

    public void setNsolicitacao(Integer nsolicitacao) {
        this.nsolicitacao = nsolicitacao;
    }

    public BigDecimal getValorus() {
        return valorus;
    }

    public void setValorus(BigDecimal valorus) {
        this.valorus = valorus;
    }

    public BigDecimal getValorpagoreais() {
        return valorpagoreais;
    }

    public void setValorpagoreais(BigDecimal valorpagoreais) {
        this.valorpagoreais = valorpagoreais;
    }

    public BigDecimal getTaxa() {
        return taxa;
    }

    public void setTaxa(BigDecimal taxa) {
        this.taxa = taxa;
    }

    public Long getContacontabilC1Id() {
        return contacontabilC1Id;
    }

    public void setContacontabilC1Id(Long planocontasId) {
        this.contacontabilC1Id = planocontasId;
    }

    public Long getContacontabilC2Id() {
        return contacontabilC2Id;
    }

    public void setContacontabilC2Id(Long planocontasId) {
        this.contacontabilC2Id = planocontasId;
    }

    public Long getContacontabilD1Id() {
        return contacontabilD1Id;
    }

    public void setContacontabilD1Id(Long planocontasId) {
        this.contacontabilD1Id = planocontasId;
    }

    public Long getContacontabilD2Id() {
        return contacontabilD2Id;
    }

    public void setContacontabilD2Id(Long planocontasId) {
        this.contacontabilD2Id = planocontasId;
    }

    public Long getDivcontacontabilC1Id() {
        return divcontacontabilC1Id;
    }

    public void setDivcontacontabilC1Id(Long planocontasId) {
        this.divcontacontabilC1Id = planocontasId;
    }

    public Long getDivcontacontabilD1Id() {
        return divcontacontabilD1Id;
    }

    public void setDivcontacontabilD1Id(Long planocontasId) {
        this.divcontacontabilD1Id = planocontasId;
    }

    public Long getJuscontacontabilC1Id() {
        return juscontacontabilC1Id;
    }

    public void setJuscontacontabilC1Id(Long planocontasId) {
        this.juscontacontabilC1Id = planocontasId;
    }

    public Long getJuscontacontabilD1Id() {
        return juscontacontabilD1Id;
    }

    public void setJuscontacontabilD1Id(Long planocontasId) {
        this.juscontacontabilD1Id = planocontasId;
    }

    public Long getFonteId() {
        return fonteId;
    }

    public void setFonteId(Long fonteId) {
        this.fonteId = fonteId;
    }

    public Long getIdcategoriainversaoId() {
        return idcategoriainversaoId;
    }

    public void setIdcategoriainversaoId(Long categoriainversaoId) {
        this.idcategoriainversaoId = categoriainversaoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CustosconcorrentesDTO custosconcorrentesDTO = (CustosconcorrentesDTO) o;
        if(custosconcorrentesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), custosconcorrentesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CustosconcorrentesDTO{" +
            "id=" + getId() +
            ", datainicio='" + getDatainicio() + "'" +
            ", nsolicitacao='" + getNsolicitacao() + "'" +
            ", valorus='" + getValorus() + "'" +
            ", valorpagoreais='" + getValorpagoreais() + "'" +
            ", taxa='" + getTaxa() + "'" +
            "}";
    }
}
