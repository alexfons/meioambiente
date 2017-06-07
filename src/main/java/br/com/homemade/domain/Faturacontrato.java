package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Faturacontrato.
 */
@Entity
@Table(name = "faturacontrato")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Faturacontrato implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "dataop")
    private ZonedDateTime dataop;

    @Column(name = "despesasexeanteriores")
    private String despesasexeanteriores;

    @Column(name = "idfaturacontrato")
    private Integer idfaturacontrato;

    @Column(name = "nfatura")
    private Integer nfatura;

    @Column(name = "nprocesso")
    private Integer nprocesso;

    @Column(name = "nsolicitacao")
    private Integer nsolicitacao;

    @Column(name = "parcela")
    private Integer parcela;

    @Column(name = "nobancaria")
    private Integer nobancaria;

    @Column(name = "nop")
    private Integer nop;

    @Column(name = "nummedicao")
    private Integer nummedicao;

    @Column(name = "restosapagar")
    private String restosapagar;

    @Column(name = "tipomedicao")
    private String tipomedicao;

    @Column(name = "valorpi", precision=10, scale=2)
    private BigDecimal valorpi;

    @Column(name = "valorreajuste", precision=10, scale=2)
    private BigDecimal valorreajuste;

    @Column(name = "valorus", precision=10, scale=2)
    private BigDecimal valorus;

    @Column(name = "vreajuste", precision=10, scale=2)
    private BigDecimal vreajuste;

    @Column(name = "aportelocal", precision=10, scale=2)
    private BigDecimal aportelocal;

    @Column(name = "aporteagente", precision=10, scale=2)
    private BigDecimal aporteagente;

    @ManyToOne
    private Fonte fonte;

    @ManyToOne
    private Contrato idcontrato;

    @ManyToOne
    private Referenciacontrato idreferenciacontrato;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDataop() {
        return dataop;
    }

    public Faturacontrato dataop(ZonedDateTime dataop) {
        this.dataop = dataop;
        return this;
    }

    public void setDataop(ZonedDateTime dataop) {
        this.dataop = dataop;
    }

    public String getDespesasexeanteriores() {
        return despesasexeanteriores;
    }

    public Faturacontrato despesasexeanteriores(String despesasexeanteriores) {
        this.despesasexeanteriores = despesasexeanteriores;
        return this;
    }

    public void setDespesasexeanteriores(String despesasexeanteriores) {
        this.despesasexeanteriores = despesasexeanteriores;
    }

    public Integer getIdfaturacontrato() {
        return idfaturacontrato;
    }

    public Faturacontrato idfaturacontrato(Integer idfaturacontrato) {
        this.idfaturacontrato = idfaturacontrato;
        return this;
    }

    public void setIdfaturacontrato(Integer idfaturacontrato) {
        this.idfaturacontrato = idfaturacontrato;
    }

    public Integer getNfatura() {
        return nfatura;
    }

    public Faturacontrato nfatura(Integer nfatura) {
        this.nfatura = nfatura;
        return this;
    }

    public void setNfatura(Integer nfatura) {
        this.nfatura = nfatura;
    }

    public Integer getNprocesso() {
        return nprocesso;
    }

    public Faturacontrato nprocesso(Integer nprocesso) {
        this.nprocesso = nprocesso;
        return this;
    }

    public void setNprocesso(Integer nprocesso) {
        this.nprocesso = nprocesso;
    }

    public Integer getNsolicitacao() {
        return nsolicitacao;
    }

    public Faturacontrato nsolicitacao(Integer nsolicitacao) {
        this.nsolicitacao = nsolicitacao;
        return this;
    }

    public void setNsolicitacao(Integer nsolicitacao) {
        this.nsolicitacao = nsolicitacao;
    }

    public Integer getParcela() {
        return parcela;
    }

    public Faturacontrato parcela(Integer parcela) {
        this.parcela = parcela;
        return this;
    }

    public void setParcela(Integer parcela) {
        this.parcela = parcela;
    }

    public Integer getNobancaria() {
        return nobancaria;
    }

    public Faturacontrato nobancaria(Integer nobancaria) {
        this.nobancaria = nobancaria;
        return this;
    }

    public void setNobancaria(Integer nobancaria) {
        this.nobancaria = nobancaria;
    }

    public Integer getNop() {
        return nop;
    }

    public Faturacontrato nop(Integer nop) {
        this.nop = nop;
        return this;
    }

    public void setNop(Integer nop) {
        this.nop = nop;
    }

    public Integer getNummedicao() {
        return nummedicao;
    }

    public Faturacontrato nummedicao(Integer nummedicao) {
        this.nummedicao = nummedicao;
        return this;
    }

    public void setNummedicao(Integer nummedicao) {
        this.nummedicao = nummedicao;
    }

    public String getRestosapagar() {
        return restosapagar;
    }

    public Faturacontrato restosapagar(String restosapagar) {
        this.restosapagar = restosapagar;
        return this;
    }

    public void setRestosapagar(String restosapagar) {
        this.restosapagar = restosapagar;
    }

    public String getTipomedicao() {
        return tipomedicao;
    }

    public Faturacontrato tipomedicao(String tipomedicao) {
        this.tipomedicao = tipomedicao;
        return this;
    }

    public void setTipomedicao(String tipomedicao) {
        this.tipomedicao = tipomedicao;
    }

    public BigDecimal getValorpi() {
        return valorpi;
    }

    public Faturacontrato valorpi(BigDecimal valorpi) {
        this.valorpi = valorpi;
        return this;
    }

    public void setValorpi(BigDecimal valorpi) {
        this.valorpi = valorpi;
    }

    public BigDecimal getValorreajuste() {
        return valorreajuste;
    }

    public Faturacontrato valorreajuste(BigDecimal valorreajuste) {
        this.valorreajuste = valorreajuste;
        return this;
    }

    public void setValorreajuste(BigDecimal valorreajuste) {
        this.valorreajuste = valorreajuste;
    }

    public BigDecimal getValorus() {
        return valorus;
    }

    public Faturacontrato valorus(BigDecimal valorus) {
        this.valorus = valorus;
        return this;
    }

    public void setValorus(BigDecimal valorus) {
        this.valorus = valorus;
    }

    public BigDecimal getVreajuste() {
        return vreajuste;
    }

    public Faturacontrato vreajuste(BigDecimal vreajuste) {
        this.vreajuste = vreajuste;
        return this;
    }

    public void setVreajuste(BigDecimal vreajuste) {
        this.vreajuste = vreajuste;
    }

    public BigDecimal getAportelocal() {
        return aportelocal;
    }

    public Faturacontrato aportelocal(BigDecimal aportelocal) {
        this.aportelocal = aportelocal;
        return this;
    }

    public void setAportelocal(BigDecimal aportelocal) {
        this.aportelocal = aportelocal;
    }

    public BigDecimal getAporteagente() {
        return aporteagente;
    }

    public Faturacontrato aporteagente(BigDecimal aporteagente) {
        this.aporteagente = aporteagente;
        return this;
    }

    public void setAporteagente(BigDecimal aporteagente) {
        this.aporteagente = aporteagente;
    }

    public Fonte getFonte() {
        return fonte;
    }

    public Faturacontrato fonte(Fonte fonte) {
        this.fonte = fonte;
        return this;
    }

    public void setFonte(Fonte fonte) {
        this.fonte = fonte;
    }

    public Contrato getIdcontrato() {
        return idcontrato;
    }

    public Faturacontrato idcontrato(Contrato contrato) {
        this.idcontrato = contrato;
        return this;
    }

    public void setIdcontrato(Contrato contrato) {
        this.idcontrato = contrato;
    }

    public Referenciacontrato getIdreferenciacontrato() {
        return idreferenciacontrato;
    }

    public Faturacontrato idreferenciacontrato(Referenciacontrato referenciacontrato) {
        this.idreferenciacontrato = referenciacontrato;
        return this;
    }

    public void setIdreferenciacontrato(Referenciacontrato referenciacontrato) {
        this.idreferenciacontrato = referenciacontrato;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Faturacontrato faturacontrato = (Faturacontrato) o;
        if (faturacontrato.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), faturacontrato.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Faturacontrato{" +
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
