package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A Proposta.
 */
@Entity
@Table(name = "proposta")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Proposta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "classificacao")
    private String classificacao;

    @Column(name = "nota")
    private String nota;

    @Column(name = "tipoproposta")
    private String tipoproposta;

    @Column(name = "obs")
    private String obs;

    @Column(name = "contrato")
    private String contrato;

    @Column(name = "habilitada")
    private String habilitada;

    @Column(name = "arqlink")
    private String arqlink;

    @Column(name = "idproposta")
    private Integer idproposta;

    @Column(name = "numeroedital")
    private Integer numeroedital;

    @Column(name = "valorproposta", precision=10, scale=2)
    private BigDecimal valorproposta;

    @Column(name = "valorrenegociado", precision=10, scale=2)
    private BigDecimal valorrenegociado;

    @ManyToOne
    private Empresa idempresa;

    @ManyToOne
    private Editallote idlote;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public Proposta classificacao(String classificacao) {
        this.classificacao = classificacao;
        return this;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getNota() {
        return nota;
    }

    public Proposta nota(String nota) {
        this.nota = nota;
        return this;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getTipoproposta() {
        return tipoproposta;
    }

    public Proposta tipoproposta(String tipoproposta) {
        this.tipoproposta = tipoproposta;
        return this;
    }

    public void setTipoproposta(String tipoproposta) {
        this.tipoproposta = tipoproposta;
    }

    public String getObs() {
        return obs;
    }

    public Proposta obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getContrato() {
        return contrato;
    }

    public Proposta contrato(String contrato) {
        this.contrato = contrato;
        return this;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getHabilitada() {
        return habilitada;
    }

    public Proposta habilitada(String habilitada) {
        this.habilitada = habilitada;
        return this;
    }

    public void setHabilitada(String habilitada) {
        this.habilitada = habilitada;
    }

    public String getArqlink() {
        return arqlink;
    }

    public Proposta arqlink(String arqlink) {
        this.arqlink = arqlink;
        return this;
    }

    public void setArqlink(String arqlink) {
        this.arqlink = arqlink;
    }

    public Integer getIdproposta() {
        return idproposta;
    }

    public Proposta idproposta(Integer idproposta) {
        this.idproposta = idproposta;
        return this;
    }

    public void setIdproposta(Integer idproposta) {
        this.idproposta = idproposta;
    }

    public Integer getNumeroedital() {
        return numeroedital;
    }

    public Proposta numeroedital(Integer numeroedital) {
        this.numeroedital = numeroedital;
        return this;
    }

    public void setNumeroedital(Integer numeroedital) {
        this.numeroedital = numeroedital;
    }

    public BigDecimal getValorproposta() {
        return valorproposta;
    }

    public Proposta valorproposta(BigDecimal valorproposta) {
        this.valorproposta = valorproposta;
        return this;
    }

    public void setValorproposta(BigDecimal valorproposta) {
        this.valorproposta = valorproposta;
    }

    public BigDecimal getValorrenegociado() {
        return valorrenegociado;
    }

    public Proposta valorrenegociado(BigDecimal valorrenegociado) {
        this.valorrenegociado = valorrenegociado;
        return this;
    }

    public void setValorrenegociado(BigDecimal valorrenegociado) {
        this.valorrenegociado = valorrenegociado;
    }

    public Empresa getIdempresa() {
        return idempresa;
    }

    public Proposta idempresa(Empresa empresa) {
        this.idempresa = empresa;
        return this;
    }

    public void setIdempresa(Empresa empresa) {
        this.idempresa = empresa;
    }

    public Editallote getIdlote() {
        return idlote;
    }

    public Proposta idlote(Editallote editallote) {
        this.idlote = editallote;
        return this;
    }

    public void setIdlote(Editallote editallote) {
        this.idlote = editallote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Proposta proposta = (Proposta) o;
        if (proposta.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proposta.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Proposta{" +
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
