package br.com.homemade.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Planoaquisicoes entity.
 */
public class PlanoaquisicoesDTO implements Serializable {

    private Long id;

    private ZonedDateTime avisolicitacao;

    private BigDecimal custoestimado;

    private BigDecimal aportelocal;

    private BigDecimal aporteagente;

    private String descricao;

    private String metodo;

    private String revisao;

    private String prequalificado;

    private String situacao;

    private Integer idplanoaquisicoes;

    private Long fonteId;

    private Long idcontratoagenteId;

    private Long ideditalId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getAvisolicitacao() {
        return avisolicitacao;
    }

    public void setAvisolicitacao(ZonedDateTime avisolicitacao) {
        this.avisolicitacao = avisolicitacao;
    }

    public BigDecimal getCustoestimado() {
        return custoestimado;
    }

    public void setCustoestimado(BigDecimal custoestimado) {
        this.custoestimado = custoestimado;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getRevisao() {
        return revisao;
    }

    public void setRevisao(String revisao) {
        this.revisao = revisao;
    }

    public String getPrequalificado() {
        return prequalificado;
    }

    public void setPrequalificado(String prequalificado) {
        this.prequalificado = prequalificado;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Integer getIdplanoaquisicoes() {
        return idplanoaquisicoes;
    }

    public void setIdplanoaquisicoes(Integer idplanoaquisicoes) {
        this.idplanoaquisicoes = idplanoaquisicoes;
    }

    public Long getFonteId() {
        return fonteId;
    }

    public void setFonteId(Long fonteId) {
        this.fonteId = fonteId;
    }

    public Long getIdcontratoagenteId() {
        return idcontratoagenteId;
    }

    public void setIdcontratoagenteId(Long contratoagenteId) {
        this.idcontratoagenteId = contratoagenteId;
    }

    public Long getIdeditalId() {
        return ideditalId;
    }

    public void setIdeditalId(Long editalId) {
        this.ideditalId = editalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PlanoaquisicoesDTO planoaquisicoesDTO = (PlanoaquisicoesDTO) o;
        if(planoaquisicoesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), planoaquisicoesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PlanoaquisicoesDTO{" +
            "id=" + getId() +
            ", avisolicitacao='" + getAvisolicitacao() + "'" +
            ", custoestimado='" + getCustoestimado() + "'" +
            ", aportelocal='" + getAportelocal() + "'" +
            ", aporteagente='" + getAporteagente() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", metodo='" + getMetodo() + "'" +
            ", revisao='" + getRevisao() + "'" +
            ", prequalificado='" + getPrequalificado() + "'" +
            ", situacao='" + getSituacao() + "'" +
            ", idplanoaquisicoes='" + getIdplanoaquisicoes() + "'" +
            "}";
    }
}
