package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Edital.
 */
@Entity
@Table(name = "edital")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Edital implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "comissaojulgamento")
    private String comissaojulgamento;

    @Column(name = "dataaprovacao")
    private ZonedDateTime dataaprovacao;

    @Column(name = "dataenvio")
    private ZonedDateTime dataenvio;

    @Column(name = "datahoraabertura")
    private ZonedDateTime datahoraabertura;

    @Column(name = "datalicitacao")
    private ZonedDateTime datalicitacao;

    @Column(name = "datanumeromanifestacao")
    private ZonedDateTime datanumeromanifestacao;

    @Column(name = "datapublicacao")
    private ZonedDateTime datapublicacao;

    @Column(name = "datarelatorio")
    private ZonedDateTime datarelatorio;

    @Column(name = "licitacao")
    private String licitacao;

    @Column(name = "localrelatorio")
    private String localrelatorio;

    @Column(name = "modalidade")
    private String modalidade;

    @Column(name = "naturezaservico")
    private String naturezaservico;

    @Column(name = "numeroconvite")
    private String numeroconvite;

    @Column(name = "numeroedital")
    private Integer numeroedital;

    @Column(name = "numeromanifestacao")
    private String numeromanifestacao;

    @Column(name = "numeroprojeto")
    private String numeroprojeto;

    @Column(name = "precoglobal", precision=10, scale=2)
    private BigDecimal precoglobal;

    @Column(name = "tipoaquisicao")
    private String tipoaquisicao;

    @Column(name = "tipolicitacao")
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

    public Edital comissaojulgamento(String comissaojulgamento) {
        this.comissaojulgamento = comissaojulgamento;
        return this;
    }

    public void setComissaojulgamento(String comissaojulgamento) {
        this.comissaojulgamento = comissaojulgamento;
    }

    public ZonedDateTime getDataaprovacao() {
        return dataaprovacao;
    }

    public Edital dataaprovacao(ZonedDateTime dataaprovacao) {
        this.dataaprovacao = dataaprovacao;
        return this;
    }

    public void setDataaprovacao(ZonedDateTime dataaprovacao) {
        this.dataaprovacao = dataaprovacao;
    }

    public ZonedDateTime getDataenvio() {
        return dataenvio;
    }

    public Edital dataenvio(ZonedDateTime dataenvio) {
        this.dataenvio = dataenvio;
        return this;
    }

    public void setDataenvio(ZonedDateTime dataenvio) {
        this.dataenvio = dataenvio;
    }

    public ZonedDateTime getDatahoraabertura() {
        return datahoraabertura;
    }

    public Edital datahoraabertura(ZonedDateTime datahoraabertura) {
        this.datahoraabertura = datahoraabertura;
        return this;
    }

    public void setDatahoraabertura(ZonedDateTime datahoraabertura) {
        this.datahoraabertura = datahoraabertura;
    }

    public ZonedDateTime getDatalicitacao() {
        return datalicitacao;
    }

    public Edital datalicitacao(ZonedDateTime datalicitacao) {
        this.datalicitacao = datalicitacao;
        return this;
    }

    public void setDatalicitacao(ZonedDateTime datalicitacao) {
        this.datalicitacao = datalicitacao;
    }

    public ZonedDateTime getDatanumeromanifestacao() {
        return datanumeromanifestacao;
    }

    public Edital datanumeromanifestacao(ZonedDateTime datanumeromanifestacao) {
        this.datanumeromanifestacao = datanumeromanifestacao;
        return this;
    }

    public void setDatanumeromanifestacao(ZonedDateTime datanumeromanifestacao) {
        this.datanumeromanifestacao = datanumeromanifestacao;
    }

    public ZonedDateTime getDatapublicacao() {
        return datapublicacao;
    }

    public Edital datapublicacao(ZonedDateTime datapublicacao) {
        this.datapublicacao = datapublicacao;
        return this;
    }

    public void setDatapublicacao(ZonedDateTime datapublicacao) {
        this.datapublicacao = datapublicacao;
    }

    public ZonedDateTime getDatarelatorio() {
        return datarelatorio;
    }

    public Edital datarelatorio(ZonedDateTime datarelatorio) {
        this.datarelatorio = datarelatorio;
        return this;
    }

    public void setDatarelatorio(ZonedDateTime datarelatorio) {
        this.datarelatorio = datarelatorio;
    }

    public String getLicitacao() {
        return licitacao;
    }

    public Edital licitacao(String licitacao) {
        this.licitacao = licitacao;
        return this;
    }

    public void setLicitacao(String licitacao) {
        this.licitacao = licitacao;
    }

    public String getLocalrelatorio() {
        return localrelatorio;
    }

    public Edital localrelatorio(String localrelatorio) {
        this.localrelatorio = localrelatorio;
        return this;
    }

    public void setLocalrelatorio(String localrelatorio) {
        this.localrelatorio = localrelatorio;
    }

    public String getModalidade() {
        return modalidade;
    }

    public Edital modalidade(String modalidade) {
        this.modalidade = modalidade;
        return this;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public String getNaturezaservico() {
        return naturezaservico;
    }

    public Edital naturezaservico(String naturezaservico) {
        this.naturezaservico = naturezaservico;
        return this;
    }

    public void setNaturezaservico(String naturezaservico) {
        this.naturezaservico = naturezaservico;
    }

    public String getNumeroconvite() {
        return numeroconvite;
    }

    public Edital numeroconvite(String numeroconvite) {
        this.numeroconvite = numeroconvite;
        return this;
    }

    public void setNumeroconvite(String numeroconvite) {
        this.numeroconvite = numeroconvite;
    }

    public Integer getNumeroedital() {
        return numeroedital;
    }

    public Edital numeroedital(Integer numeroedital) {
        this.numeroedital = numeroedital;
        return this;
    }

    public void setNumeroedital(Integer numeroedital) {
        this.numeroedital = numeroedital;
    }

    public String getNumeromanifestacao() {
        return numeromanifestacao;
    }

    public Edital numeromanifestacao(String numeromanifestacao) {
        this.numeromanifestacao = numeromanifestacao;
        return this;
    }

    public void setNumeromanifestacao(String numeromanifestacao) {
        this.numeromanifestacao = numeromanifestacao;
    }

    public String getNumeroprojeto() {
        return numeroprojeto;
    }

    public Edital numeroprojeto(String numeroprojeto) {
        this.numeroprojeto = numeroprojeto;
        return this;
    }

    public void setNumeroprojeto(String numeroprojeto) {
        this.numeroprojeto = numeroprojeto;
    }

    public BigDecimal getPrecoglobal() {
        return precoglobal;
    }

    public Edital precoglobal(BigDecimal precoglobal) {
        this.precoglobal = precoglobal;
        return this;
    }

    public void setPrecoglobal(BigDecimal precoglobal) {
        this.precoglobal = precoglobal;
    }

    public String getTipoaquisicao() {
        return tipoaquisicao;
    }

    public Edital tipoaquisicao(String tipoaquisicao) {
        this.tipoaquisicao = tipoaquisicao;
        return this;
    }

    public void setTipoaquisicao(String tipoaquisicao) {
        this.tipoaquisicao = tipoaquisicao;
    }

    public String getTipolicitacao() {
        return tipolicitacao;
    }

    public Edital tipolicitacao(String tipolicitacao) {
        this.tipolicitacao = tipolicitacao;
        return this;
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
        Edital edital = (Edital) o;
        if (edital.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), edital.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Edital{" +
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
