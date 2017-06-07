package br.com.homemade.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Pagfuncionario entity.
 */
public class PagfuncionarioDTO implements Serializable {

    private Long id;

    private ZonedDateTime datapagamento;

    private Integer idpagfuncionarios;

    private Integer nsolicitacao;

    private BigDecimal percentual;

    private BigDecimal salario;

    private BigDecimal salariocontribuicao;

    private BigDecimal salariototal;

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

    private Long idfuncionariosId;

    private Long idreferenciaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDatapagamento() {
        return datapagamento;
    }

    public void setDatapagamento(ZonedDateTime datapagamento) {
        this.datapagamento = datapagamento;
    }

    public Integer getIdpagfuncionarios() {
        return idpagfuncionarios;
    }

    public void setIdpagfuncionarios(Integer idpagfuncionarios) {
        this.idpagfuncionarios = idpagfuncionarios;
    }

    public Integer getNsolicitacao() {
        return nsolicitacao;
    }

    public void setNsolicitacao(Integer nsolicitacao) {
        this.nsolicitacao = nsolicitacao;
    }

    public BigDecimal getPercentual() {
        return percentual;
    }

    public void setPercentual(BigDecimal percentual) {
        this.percentual = percentual;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public BigDecimal getSalariocontribuicao() {
        return salariocontribuicao;
    }

    public void setSalariocontribuicao(BigDecimal salariocontribuicao) {
        this.salariocontribuicao = salariocontribuicao;
    }

    public BigDecimal getSalariototal() {
        return salariototal;
    }

    public void setSalariototal(BigDecimal salariototal) {
        this.salariototal = salariototal;
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

    public Long getIdfuncionariosId() {
        return idfuncionariosId;
    }

    public void setIdfuncionariosId(Long funcionarioId) {
        this.idfuncionariosId = funcionarioId;
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

        PagfuncionarioDTO pagfuncionarioDTO = (PagfuncionarioDTO) o;
        if(pagfuncionarioDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pagfuncionarioDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PagfuncionarioDTO{" +
            "id=" + getId() +
            ", datapagamento='" + getDatapagamento() + "'" +
            ", idpagfuncionarios='" + getIdpagfuncionarios() + "'" +
            ", nsolicitacao='" + getNsolicitacao() + "'" +
            ", percentual='" + getPercentual() + "'" +
            ", salario='" + getSalario() + "'" +
            ", salariocontribuicao='" + getSalariocontribuicao() + "'" +
            ", salariototal='" + getSalariototal() + "'" +
            ", valorus='" + getValorus() + "'" +
            "}";
    }
}
