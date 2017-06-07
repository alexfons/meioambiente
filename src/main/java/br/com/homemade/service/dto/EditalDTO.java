package br.com.homemade.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the Edital entity.
 */
public class EditalDTO implements Serializable {

    private Long id;

    private String comissaojulgamento;

    private ZonedDateTime dataaprovacao;

    private ZonedDateTime dataenvio;

    private ZonedDateTime datahoraabertura;

    private ZonedDateTime datalicitacao;

    private ZonedDateTime datanumeromanifestacao;

    private ZonedDateTime datapublicacao;

    private ZonedDateTime datarelatorio;

    private String licitacao;

    private String localrelatorio;

    private String modalidade;

    private String naturezaservico;

    private String numeroconvite;

    private Integer numeroedital;

    private String numeromanifestacao;

    private String numeroprojeto;

    private BigDecimal precoglobal;

    private String tipoaquisicao;

    private String tipolicitacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComissaojulgamento() {
        return comissaojulgamento;
    }

    public void setComissaojulgamento(String comissaojulgamento) {
        this.comissaojulgamento = comissaojulgamento;
    }

    public ZonedDateTime getDataaprovacao() {
        return dataaprovacao;
    }

    public void setDataaprovacao(ZonedDateTime dataaprovacao) {
        this.dataaprovacao = dataaprovacao;
    }

    public ZonedDateTime getDataenvio() {
        return dataenvio;
    }

    public void setDataenvio(ZonedDateTime dataenvio) {
        this.dataenvio = dataenvio;
    }

    public ZonedDateTime getDatahoraabertura() {
        return datahoraabertura;
    }

    public void setDatahoraabertura(ZonedDateTime datahoraabertura) {
        this.datahoraabertura = datahoraabertura;
    }

    public ZonedDateTime getDatalicitacao() {
        return datalicitacao;
    }

    public void setDatalicitacao(ZonedDateTime datalicitacao) {
        this.datalicitacao = datalicitacao;
    }

    public ZonedDateTime getDatanumeromanifestacao() {
        return datanumeromanifestacao;
    }

    public void setDatanumeromanifestacao(ZonedDateTime datanumeromanifestacao) {
        this.datanumeromanifestacao = datanumeromanifestacao;
    }

    public ZonedDateTime getDatapublicacao() {
        return datapublicacao;
    }

    public void setDatapublicacao(ZonedDateTime datapublicacao) {
        this.datapublicacao = datapublicacao;
    }

    public ZonedDateTime getDatarelatorio() {
        return datarelatorio;
    }

    public void setDatarelatorio(ZonedDateTime datarelatorio) {
        this.datarelatorio = datarelatorio;
    }

    public String getLicitacao() {
        return licitacao;
    }

    public void setLicitacao(String licitacao) {
        this.licitacao = licitacao;
    }

    public String getLocalrelatorio() {
        return localrelatorio;
    }

    public void setLocalrelatorio(String localrelatorio) {
        this.localrelatorio = localrelatorio;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public String getNaturezaservico() {
        return naturezaservico;
    }

    public void setNaturezaservico(String naturezaservico) {
        this.naturezaservico = naturezaservico;
    }

    public String getNumeroconvite() {
        return numeroconvite;
    }

    public void setNumeroconvite(String numeroconvite) {
        this.numeroconvite = numeroconvite;
    }

    public Integer getNumeroedital() {
        return numeroedital;
    }

    public void setNumeroedital(Integer numeroedital) {
        this.numeroedital = numeroedital;
    }

    public String getNumeromanifestacao() {
        return numeromanifestacao;
    }

    public void setNumeromanifestacao(String numeromanifestacao) {
        this.numeromanifestacao = numeromanifestacao;
    }

    public String getNumeroprojeto() {
        return numeroprojeto;
    }

    public void setNumeroprojeto(String numeroprojeto) {
        this.numeroprojeto = numeroprojeto;
    }

    public BigDecimal getPrecoglobal() {
        return precoglobal;
    }

    public void setPrecoglobal(BigDecimal precoglobal) {
        this.precoglobal = precoglobal;
    }

    public String getTipoaquisicao() {
        return tipoaquisicao;
    }

    public void setTipoaquisicao(String tipoaquisicao) {
        this.tipoaquisicao = tipoaquisicao;
    }

    public String getTipolicitacao() {
        return tipolicitacao;
    }

    public void setTipolicitacao(String tipolicitacao) {
        this.tipolicitacao = tipolicitacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EditalDTO editalDTO = (EditalDTO) o;
        if(editalDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), editalDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EditalDTO{" +
            "id=" + getId() +
            ", comissaojulgamento='" + getComissaojulgamento() + "'" +
            ", dataaprovacao='" + getDataaprovacao() + "'" +
            ", dataenvio='" + getDataenvio() + "'" +
            ", datahoraabertura='" + getDatahoraabertura() + "'" +
            ", datalicitacao='" + getDatalicitacao() + "'" +
            ", datanumeromanifestacao='" + getDatanumeromanifestacao() + "'" +
            ", datapublicacao='" + getDatapublicacao() + "'" +
            ", datarelatorio='" + getDatarelatorio() + "'" +
            ", licitacao='" + getLicitacao() + "'" +
            ", localrelatorio='" + getLocalrelatorio() + "'" +
            ", modalidade='" + getModalidade() + "'" +
            ", naturezaservico='" + getNaturezaservico() + "'" +
            ", numeroconvite='" + getNumeroconvite() + "'" +
            ", numeroedital='" + getNumeroedital() + "'" +
            ", numeromanifestacao='" + getNumeromanifestacao() + "'" +
            ", numeroprojeto='" + getNumeroprojeto() + "'" +
            ", precoglobal='" + getPrecoglobal() + "'" +
            ", tipoaquisicao='" + getTipoaquisicao() + "'" +
            ", tipolicitacao='" + getTipolicitacao() + "'" +
            "}";
    }
}
