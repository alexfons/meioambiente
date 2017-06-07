package br.com.homemade.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Faturacontrato entity.
 */
public class FaturacontratoDTO implements Serializable {

    private Long id;

    private ZonedDateTime dataop;

    private String despesasexeanteriores;

    private Integer idfaturacontrato;

    private Integer nfatura;

    private Integer nprocesso;

    private Integer nsolicitacao;

    private Integer parcela;

    private Integer nobancaria;

    private Integer nop;

    private Integer nummedicao;

    private String restosapagar;

    private String tipomedicao;

    private BigDecimal valorpi;

    private BigDecimal valorreajuste;

    private BigDecimal valorus;

    private BigDecimal vreajuste;

    private BigDecimal aportelocal;

    private BigDecimal aporteagente;

    private Long fonteId;

    private Long idcontratoId;

    private Long idreferenciacontratoId;

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

    public String getDespesasexeanteriores() {
        return despesasexeanteriores;
    }

    public void setDespesasexeanteriores(String despesasexeanteriores) {
        this.despesasexeanteriores = despesasexeanteriores;
    }

    public Integer getIdfaturacontrato() {
        return idfaturacontrato;
    }

    public void setIdfaturacontrato(Integer idfaturacontrato) {
        this.idfaturacontrato = idfaturacontrato;
    }

    public Integer getNfatura() {
        return nfatura;
    }

    public void setNfatura(Integer nfatura) {
        this.nfatura = nfatura;
    }

    public Integer getNprocesso() {
        return nprocesso;
    }

    public void setNprocesso(Integer nprocesso) {
        this.nprocesso = nprocesso;
    }

    public Integer getNsolicitacao() {
        return nsolicitacao;
    }

    public void setNsolicitacao(Integer nsolicitacao) {
        this.nsolicitacao = nsolicitacao;
    }

    public Integer getParcela() {
        return parcela;
    }

    public void setParcela(Integer parcela) {
        this.parcela = parcela;
    }

    public Integer getNobancaria() {
        return nobancaria;
    }

    public void setNobancaria(Integer nobancaria) {
        this.nobancaria = nobancaria;
    }

    public Integer getNop() {
        return nop;
    }

    public void setNop(Integer nop) {
        this.nop = nop;
    }

    public Integer getNummedicao() {
        return nummedicao;
    }

    public void setNummedicao(Integer nummedicao) {
        this.nummedicao = nummedicao;
    }

    public String getRestosapagar() {
        return restosapagar;
    }

    public void setRestosapagar(String restosapagar) {
        this.restosapagar = restosapagar;
    }

    public String getTipomedicao() {
        return tipomedicao;
    }

    public void setTipomedicao(String tipomedicao) {
        this.tipomedicao = tipomedicao;
    }

    public BigDecimal getValorpi() {
        return valorpi;
    }

    public void setValorpi(BigDecimal valorpi) {
        this.valorpi = valorpi;
    }

    public BigDecimal getValorreajuste() {
        return valorreajuste;
    }

    public void setValorreajuste(BigDecimal valorreajuste) {
        this.valorreajuste = valorreajuste;
    }

    public BigDecimal getValorus() {
        return valorus;
    }

    public void setValorus(BigDecimal valorus) {
        this.valorus = valorus;
    }

    public BigDecimal getVreajuste() {
        return vreajuste;
    }

    public void setVreajuste(BigDecimal vreajuste) {
        this.vreajuste = vreajuste;
    }

    public BigDecimal getAportelocal() {
        return aportelocal;
    }

    public void setAportelocal(BigDecimal aportelocal) {
        this.aportelocal = aportelocal;
    }

    public BigDecimal getAporteagente() {
        return aporteagente;
    }

    public void setAporteagente(BigDecimal aporteagente) {
        this.aporteagente = aporteagente;
    }

    public Long getFonteId() {
        return fonteId;
    }

    public void setFonteId(Long fonteId) {
        this.fonteId = fonteId;
    }

    public Long getIdcontratoId() {
        return idcontratoId;
    }

    public void setIdcontratoId(Long contratoId) {
        this.idcontratoId = contratoId;
    }

    public Long getIdreferenciacontratoId() {
        return idreferenciacontratoId;
    }

    public void setIdreferenciacontratoId(Long referenciacontratoId) {
        this.idreferenciacontratoId = referenciacontratoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FaturacontratoDTO faturacontratoDTO = (FaturacontratoDTO) o;
        if(faturacontratoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), faturacontratoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FaturacontratoDTO{" +
            "id=" + getId() +
            ", dataop='" + getDataop() + "'" +
            ", despesasexeanteriores='" + getDespesasexeanteriores() + "'" +
            ", idfaturacontrato='" + getIdfaturacontrato() + "'" +
            ", nfatura='" + getNfatura() + "'" +
            ", nprocesso='" + getNprocesso() + "'" +
            ", nsolicitacao='" + getNsolicitacao() + "'" +
            ", parcela='" + getParcela() + "'" +
            ", nobancaria='" + getNobancaria() + "'" +
            ", nop='" + getNop() + "'" +
            ", nummedicao='" + getNummedicao() + "'" +
            ", restosapagar='" + getRestosapagar() + "'" +
            ", tipomedicao='" + getTipomedicao() + "'" +
            ", valorpi='" + getValorpi() + "'" +
            ", valorreajuste='" + getValorreajuste() + "'" +
            ", valorus='" + getValorus() + "'" +
            ", vreajuste='" + getVreajuste() + "'" +
            ", aportelocal='" + getAportelocal() + "'" +
            ", aporteagente='" + getAporteagente() + "'" +
            "}";
    }
}
