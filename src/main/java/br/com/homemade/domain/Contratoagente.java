package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Contratoagente.
 */
@Entity
@Table(name = "contratoagente")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Contratoagente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "dataaprovacao")
    private ZonedDateTime dataaprovacao;

    @Column(name = "dataassinatura")
    private ZonedDateTime dataassinatura;

    @Column(name = "dataconclusao")
    private ZonedDateTime dataconclusao;

    @Column(name = "datainicio")
    private ZonedDateTime datainicio;

    @Column(name = "idcontratoagente")
    private Integer idcontratoagente;

    @Column(name = "nomecontratoagente")
    private String nomecontratoagente;

    @Column(name = "referenciabid")
    private String referenciabid;

    @Column(name = "acordocredito")
    private String acordocredito;

    @Column(name = "mutuario")
    private String mutuario;

    @Column(name = "executor")
    private String executor;

    @Column(name = "clausulascontratuais")
    private String clausulascontratuais;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "moeda")
    private String moeda;

    @Column(name = "percentuallocal")
    private Double percentuallocal;

    @Column(name = "percentualagente")
    private Double percentualagente;

    @Column(name = "valorcontrato")
    private Double valorcontrato;

    @Column(name = "valor")
    private Double valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDataaprovacao() {
        return dataaprovacao;
    }

    public Contratoagente dataaprovacao(ZonedDateTime dataaprovacao) {
        this.dataaprovacao = dataaprovacao;
        return this;
    }

    public void setDataaprovacao(ZonedDateTime dataaprovacao) {
        this.dataaprovacao = dataaprovacao;
    }

    public ZonedDateTime getDataassinatura() {
        return dataassinatura;
    }

    public Contratoagente dataassinatura(ZonedDateTime dataassinatura) {
        this.dataassinatura = dataassinatura;
        return this;
    }

    public void setDataassinatura(ZonedDateTime dataassinatura) {
        this.dataassinatura = dataassinatura;
    }

    public ZonedDateTime getDataconclusao() {
        return dataconclusao;
    }

    public Contratoagente dataconclusao(ZonedDateTime dataconclusao) {
        this.dataconclusao = dataconclusao;
        return this;
    }

    public void setDataconclusao(ZonedDateTime dataconclusao) {
        this.dataconclusao = dataconclusao;
    }

    public ZonedDateTime getDatainicio() {
        return datainicio;
    }

    public Contratoagente datainicio(ZonedDateTime datainicio) {
        this.datainicio = datainicio;
        return this;
    }

    public void setDatainicio(ZonedDateTime datainicio) {
        this.datainicio = datainicio;
    }

    public Integer getIdcontratoagente() {
        return idcontratoagente;
    }

    public Contratoagente idcontratoagente(Integer idcontratoagente) {
        this.idcontratoagente = idcontratoagente;
        return this;
    }

    public void setIdcontratoagente(Integer idcontratoagente) {
        this.idcontratoagente = idcontratoagente;
    }

    public String getNomecontratoagente() {
        return nomecontratoagente;
    }

    public Contratoagente nomecontratoagente(String nomecontratoagente) {
        this.nomecontratoagente = nomecontratoagente;
        return this;
    }

    public void setNomecontratoagente(String nomecontratoagente) {
        this.nomecontratoagente = nomecontratoagente;
    }

    public String getReferenciabid() {
        return referenciabid;
    }

    public Contratoagente referenciabid(String referenciabid) {
        this.referenciabid = referenciabid;
        return this;
    }

    public void setReferenciabid(String referenciabid) {
        this.referenciabid = referenciabid;
    }

    public String getAcordocredito() {
        return acordocredito;
    }

    public Contratoagente acordocredito(String acordocredito) {
        this.acordocredito = acordocredito;
        return this;
    }

    public void setAcordocredito(String acordocredito) {
        this.acordocredito = acordocredito;
    }

    public String getMutuario() {
        return mutuario;
    }

    public Contratoagente mutuario(String mutuario) {
        this.mutuario = mutuario;
        return this;
    }

    public void setMutuario(String mutuario) {
        this.mutuario = mutuario;
    }

    public String getExecutor() {
        return executor;
    }

    public Contratoagente executor(String executor) {
        this.executor = executor;
        return this;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getClausulascontratuais() {
        return clausulascontratuais;
    }

    public Contratoagente clausulascontratuais(String clausulascontratuais) {
        this.clausulascontratuais = clausulascontratuais;
        return this;
    }

    public void setClausulascontratuais(String clausulascontratuais) {
        this.clausulascontratuais = clausulascontratuais;
    }

    public String getDescricao() {
        return descricao;
    }

    public Contratoagente descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMoeda() {
        return moeda;
    }

    public Contratoagente moeda(String moeda) {
        this.moeda = moeda;
        return this;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public Double getPercentuallocal() {
        return percentuallocal;
    }

    public Contratoagente percentuallocal(Double percentuallocal) {
        this.percentuallocal = percentuallocal;
        return this;
    }

    public void setPercentuallocal(Double percentuallocal) {
        this.percentuallocal = percentuallocal;
    }

    public Double getPercentualagente() {
        return percentualagente;
    }

    public Contratoagente percentualagente(Double percentualagente) {
        this.percentualagente = percentualagente;
        return this;
    }

    public void setPercentualagente(Double percentualagente) {
        this.percentualagente = percentualagente;
    }

    public Double getValorcontrato() {
        return valorcontrato;
    }

    public Contratoagente valorcontrato(Double valorcontrato) {
        this.valorcontrato = valorcontrato;
        return this;
    }

    public void setValorcontrato(Double valorcontrato) {
        this.valorcontrato = valorcontrato;
    }

    public Double getValor() {
        return valor;
    }

    public Contratoagente valor(Double valor) {
        this.valor = valor;
        return this;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contratoagente contratoagente = (Contratoagente) o;
        if (contratoagente.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contratoagente.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Contratoagente{" +
            "id=" + getId() +
            ", dataaprovacao='" + getDataaprovacao() + "'" +
            ", dataassinatura='" + getDataassinatura() + "'" +
            ", dataconclusao='" + getDataconclusao() + "'" +
            ", datainicio='" + getDatainicio() + "'" +
            ", idcontratoagente='" + getIdcontratoagente() + "'" +
            ", nomecontratoagente='" + getNomecontratoagente() + "'" +
            ", referenciabid='" + getReferenciabid() + "'" +
            ", acordocredito='" + getAcordocredito() + "'" +
            ", mutuario='" + getMutuario() + "'" +
            ", executor='" + getExecutor() + "'" +
            ", clausulascontratuais='" + getClausulascontratuais() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", moeda='" + getMoeda() + "'" +
            ", percentuallocal='" + getPercentuallocal() + "'" +
            ", percentualagente='" + getPercentualagente() + "'" +
            ", valorcontrato='" + getValorcontrato() + "'" +
            ", valor='" + getValor() + "'" +
            "}";
    }
}
