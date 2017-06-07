package br.com.homemade.service.dto;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Proposta entity.
 */
public class PropostaDTO implements Serializable {

    private Long id;

    private String classificacao;

    private String nota;

    private String tipoproposta;

    private String obs;

    private String contrato;

    private String habilitada;

    private String arqlink;

    private Integer idproposta;

    private Integer numeroedital;

    private BigDecimal valorproposta;

    private BigDecimal valorrenegociado;

    private Long idempresaId;

    private Long idloteId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getTipoproposta() {
        return tipoproposta;
    }

    public void setTipoproposta(String tipoproposta) {
        this.tipoproposta = tipoproposta;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getHabilitada() {
        return habilitada;
    }

    public void setHabilitada(String habilitada) {
        this.habilitada = habilitada;
    }

    public String getArqlink() {
        return arqlink;
    }

    public void setArqlink(String arqlink) {
        this.arqlink = arqlink;
    }

    public Integer getIdproposta() {
        return idproposta;
    }

    public void setIdproposta(Integer idproposta) {
        this.idproposta = idproposta;
    }

    public Integer getNumeroedital() {
        return numeroedital;
    }

    public void setNumeroedital(Integer numeroedital) {
        this.numeroedital = numeroedital;
    }

    public BigDecimal getValorproposta() {
        return valorproposta;
    }

    public void setValorproposta(BigDecimal valorproposta) {
        this.valorproposta = valorproposta;
    }

    public BigDecimal getValorrenegociado() {
        return valorrenegociado;
    }

    public void setValorrenegociado(BigDecimal valorrenegociado) {
        this.valorrenegociado = valorrenegociado;
    }

    public Long getIdempresaId() {
        return idempresaId;
    }

    public void setIdempresaId(Long empresaId) {
        this.idempresaId = empresaId;
    }

    public Long getIdloteId() {
        return idloteId;
    }

    public void setIdloteId(Long editalloteId) {
        this.idloteId = editalloteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PropostaDTO propostaDTO = (PropostaDTO) o;
        if(propostaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), propostaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PropostaDTO{" +
            "id=" + getId() +
            ", classificacao='" + getClassificacao() + "'" +
            ", nota='" + getNota() + "'" +
            ", tipoproposta='" + getTipoproposta() + "'" +
            ", obs='" + getObs() + "'" +
            ", contrato='" + getContrato() + "'" +
            ", habilitada='" + getHabilitada() + "'" +
            ", arqlink='" + getArqlink() + "'" +
            ", idproposta='" + getIdproposta() + "'" +
            ", numeroedital='" + getNumeroedital() + "'" +
            ", valorproposta='" + getValorproposta() + "'" +
            ", valorrenegociado='" + getValorrenegociado() + "'" +
            "}";
    }
}
