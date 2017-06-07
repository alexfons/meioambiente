package br.com.homemade.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Desapropriacao entity.
 */
public class DesapropriacaoDTO implements Serializable {

    private Long id;

    private ZonedDateTime dataop;

    private Integer iddesapropria;

    private String nprocesso;

    private String nomedesapropriado;

    private String local;

    private Integer nsolicitacao;

    private BigDecimal valorpago;

    private BigDecimal valorus;

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

    private Long idcontabancariaId;

    private Long idreferenciaId;

    private Long rodoviaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDataop() {
        return dataop;
    }

    public void setDataop(ZonedDateTime dataop) {
        this.dataop = dataop;
    }

    public Integer getIddesapropria() {
        return iddesapropria;
    }

    public void setIddesapropria(Integer iddesapropria) {
        this.iddesapropria = iddesapropria;
    }

    public String getNprocesso() {
        return nprocesso;
    }

    public void setNprocesso(String nprocesso) {
        this.nprocesso = nprocesso;
    }

    public String getNomedesapropriado() {
        return nomedesapropriado;
    }

    public void setNomedesapropriado(String nomedesapropriado) {
        this.nomedesapropriado = nomedesapropriado;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Integer getNsolicitacao() {
        return nsolicitacao;
    }

    public void setNsolicitacao(Integer nsolicitacao) {
        this.nsolicitacao = nsolicitacao;
    }

    public BigDecimal getValorpago() {
        return valorpago;
    }

    public void setValorpago(BigDecimal valorpago) {
        this.valorpago = valorpago;
    }

    public BigDecimal getValorus() {
        return valorus;
    }

    public void setValorus(BigDecimal valorus) {
        this.valorus = valorus;
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

    public Long getRodoviaId() {
        return rodoviaId;
    }

    public void setRodoviaId(Long rodoviaId) {
        this.rodoviaId = rodoviaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DesapropriacaoDTO desapropriacaoDTO = (DesapropriacaoDTO) o;
        if(desapropriacaoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), desapropriacaoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DesapropriacaoDTO{" +
            "id=" + getId() +
            ", dataop='" + getDataop() + "'" +
            ", iddesapropria='" + getIddesapropria() + "'" +
            ", nprocesso='" + getNprocesso() + "'" +
            ", nomedesapropriado='" + getNomedesapropriado() + "'" +
            ", local='" + getLocal() + "'" +
            ", nsolicitacao='" + getNsolicitacao() + "'" +
            ", valorpago='" + getValorpago() + "'" +
            ", valorus='" + getValorus() + "'" +
            "}";
    }
}
