package br.com.homemade.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Fatura entity.
 */
public class FaturaDTO implements Serializable {

    private Long id;

    private Boolean ajustecambio;

    private ZonedDateTime dataop;

    private String despesasexeanteriores;

    private Integer nfatura;

    private Integer nprocesso;

    private Integer nsolicitacao;

    private Integer parcela;

    private Integer nob;

    private Integer nop;

    private Integer nummedicao;

    private String restosapagar;

    private String tipomedicao;

    private BigDecimal valorpi;

    private BigDecimal valorreajuste;

    private BigDecimal valorus;

    private BigDecimal vreajuste;

    private Long fonteId;

    private Long idcontabancariaId;

    private Long idcontratoId;

    private Long idreferenciaId;

    private Long medicaoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isAjustecambio() {
        return ajustecambio;
    }

    public void setAjustecambio(Boolean ajustecambio) {
        this.ajustecambio = ajustecambio;
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

    public Integer getNob() {
        return nob;
    }

    public void setNob(Integer nob) {
        this.nob = nob;
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

    public Long getFonteId() {
        return fonteId;
    }

    public void setFonteId(Long fonteId) {
        this.fonteId = fonteId;
    }

    public Long getIdcontabancariaId() {
        return idcontabancariaId;
    }

    public void setIdcontabancariaId(Long contabancariaId) {
        this.idcontabancariaId = contabancariaId;
    }

    public Long getIdcontratoId() {
        return idcontratoId;
    }

    public void setIdcontratoId(Long contratoId) {
        this.idcontratoId = contratoId;
    }

    public Long getIdreferenciaId() {
        return idreferenciaId;
    }

    public void setIdreferenciaId(Long referenciaId) {
        this.idreferenciaId = referenciaId;
    }

    public Long getMedicaoId() {
        return medicaoId;
    }

    public void setMedicaoId(Long medicaoId) {
        this.medicaoId = medicaoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FaturaDTO faturaDTO = (FaturaDTO) o;
        if(faturaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), faturaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FaturaDTO{" +
            "id=" + getId() +
            ", ajustecambio='" + isAjustecambio() + "'" +
            ", dataop='" + getDataop() + "'" +
            ", despesasexeanteriores='" + getDespesasexeanteriores() + "'" +
            ", nfatura='" + getNfatura() + "'" +
            ", nprocesso='" + getNprocesso() + "'" +
            ", nsolicitacao='" + getNsolicitacao() + "'" +
            ", parcela='" + getParcela() + "'" +
            ", nob='" + getNob() + "'" +
            ", nop='" + getNop() + "'" +
            ", nummedicao='" + getNummedicao() + "'" +
            ", restosapagar='" + getRestosapagar() + "'" +
            ", tipomedicao='" + getTipomedicao() + "'" +
            ", valorpi='" + getValorpi() + "'" +
            ", valorreajuste='" + getValorreajuste() + "'" +
            ", valorus='" + getValorus() + "'" +
            ", vreajuste='" + getVreajuste() + "'" +
            "}";
    }
}
