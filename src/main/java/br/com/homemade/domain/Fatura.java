package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Fatura.
 */
@Entity
@Table(name = "fatura")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Fatura implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ajustecambio")
    private Boolean ajustecambio;

    @Column(name = "dataop")
    private ZonedDateTime dataop;

    @Column(name = "despesasexeanteriores")
    private String despesasexeanteriores;

    @Column(name = "nfatura")
    private Integer nfatura;

    @Column(name = "nprocesso")
    private Integer nprocesso;

    @Column(name = "nsolicitacao")
    private Integer nsolicitacao;

    @Column(name = "parcela")
    private Integer parcela;

    @Column(name = "nob")
    private Integer nob;

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

    @ManyToOne
    private Fonte fonte;

    @ManyToOne
    private Contabancaria idcontabancaria;

    @ManyToOne
    private Contrato idcontrato;

    @ManyToOne
    private Referencia idreferencia;

    @ManyToOne
    private Medicao medicao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isAjustecambio() {
        return ajustecambio;
    }

    public Fatura ajustecambio(Boolean ajustecambio) {
        this.ajustecambio = ajustecambio;
        return this;
    }

    public void setAjustecambio(Boolean ajustecambio) {
        this.ajustecambio = ajustecambio;
    }

    public ZonedDateTime getDataop() {
        return dataop;
    }

    public Fatura dataop(ZonedDateTime dataop) {
        this.dataop = dataop;
        return this;
    }

    public void setDataop(ZonedDateTime dataop) {
        this.dataop = dataop;
    }

    public String getDespesasexeanteriores() {
        return despesasexeanteriores;
    }

    public Fatura despesasexeanteriores(String despesasexeanteriores) {
        this.despesasexeanteriores = despesasexeanteriores;
        return this;
    }

    public void setDespesasexeanteriores(String despesasexeanteriores) {
        this.despesasexeanteriores = despesasexeanteriores;
    }

    public Integer getNfatura() {
        return nfatura;
    }

    public Fatura nfatura(Integer nfatura) {
        this.nfatura = nfatura;
        return this;
    }

    public void setNfatura(Integer nfatura) {
        this.nfatura = nfatura;
    }

    public Integer getNprocesso() {
        return nprocesso;
    }

    public Fatura nprocesso(Integer nprocesso) {
        this.nprocesso = nprocesso;
        return this;
    }

    public void setNprocesso(Integer nprocesso) {
        this.nprocesso = nprocesso;
    }

    public Integer getNsolicitacao() {
        return nsolicitacao;
    }

    public Fatura nsolicitacao(Integer nsolicitacao) {
        this.nsolicitacao = nsolicitacao;
        return this;
    }

    public void setNsolicitacao(Integer nsolicitacao) {
        this.nsolicitacao = nsolicitacao;
    }

    public Integer getParcela() {
        return parcela;
    }

    public Fatura parcela(Integer parcela) {
        this.parcela = parcela;
        return this;
    }

    public void setParcela(Integer parcela) {
        this.parcela = parcela;
    }

    public Integer getNob() {
        return nob;
    }

    public Fatura nob(Integer nob) {
        this.nob = nob;
        return this;
    }

    public void setNob(Integer nob) {
        this.nob = nob;
    }

    public Integer getNop() {
        return nop;
    }

    public Fatura nop(Integer nop) {
        this.nop = nop;
        return this;
    }

    public void setNop(Integer nop) {
        this.nop = nop;
    }

    public Integer getNummedicao() {
        return nummedicao;
    }

    public Fatura nummedicao(Integer nummedicao) {
        this.nummedicao = nummedicao;
        return this;
    }

    public void setNummedicao(Integer nummedicao) {
        this.nummedicao = nummedicao;
    }

    public String getRestosapagar() {
        return restosapagar;
    }

    public Fatura restosapagar(String restosapagar) {
        this.restosapagar = restosapagar;
        return this;
    }

    public void setRestosapagar(String restosapagar) {
        this.restosapagar = restosapagar;
    }

    public String getTipomedicao() {
        return tipomedicao;
    }

    public Fatura tipomedicao(String tipomedicao) {
        this.tipomedicao = tipomedicao;
        return this;
    }

    public void setTipomedicao(String tipomedicao) {
        this.tipomedicao = tipomedicao;
    }

    public BigDecimal getValorpi() {
        return valorpi;
    }

    public Fatura valorpi(BigDecimal valorpi) {
        this.valorpi = valorpi;
        return this;
    }

    public void setValorpi(BigDecimal valorpi) {
        this.valorpi = valorpi;
    }

    public BigDecimal getValorreajuste() {
        return valorreajuste;
    }

    public Fatura valorreajuste(BigDecimal valorreajuste) {
        this.valorreajuste = valorreajuste;
        return this;
    }

    public void setValorreajuste(BigDecimal valorreajuste) {
        this.valorreajuste = valorreajuste;
    }

    public BigDecimal getValorus() {
        return valorus;
    }

    public Fatura valorus(BigDecimal valorus) {
        this.valorus = valorus;
        return this;
    }

    public void setValorus(BigDecimal valorus) {
        this.valorus = valorus;
    }

    public BigDecimal getVreajuste() {
        return vreajuste;
    }

    public Fatura vreajuste(BigDecimal vreajuste) {
        this.vreajuste = vreajuste;
        return this;
    }

    public void setVreajuste(BigDecimal vreajuste) {
        this.vreajuste = vreajuste;
    }

    public Fonte getFonte() {
        return fonte;
    }

    public Fatura fonte(Fonte fonte) {
        this.fonte = fonte;
        return this;
    }

    public void setFonte(Fonte fonte) {
        this.fonte = fonte;
    }

    public Contabancaria getIdcontabancaria() {
        return idcontabancaria;
    }

    public Fatura idcontabancaria(Contabancaria contabancaria) {
        this.idcontabancaria = contabancaria;
        return this;
    }

    public void setIdcontabancaria(Contabancaria contabancaria) {
        this.idcontabancaria = contabancaria;
    }

    public Contrato getIdcontrato() {
        return idcontrato;
    }

    public Fatura idcontrato(Contrato contrato) {
        this.idcontrato = contrato;
        return this;
    }

    public void setIdcontrato(Contrato contrato) {
        this.idcontrato = contrato;
    }

    public Referencia getIdreferencia() {
        return idreferencia;
    }

    public Fatura idreferencia(Referencia referencia) {
        this.idreferencia = referencia;
        return this;
    }

    public void setIdreferencia(Referencia referencia) {
        this.idreferencia = referencia;
    }

    public Medicao getMedicao() {
        return medicao;
    }

    public Fatura medicao(Medicao medicao) {
        this.medicao = medicao;
        return this;
    }

    public void setMedicao(Medicao medicao) {
        this.medicao = medicao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Fatura fatura = (Fatura) o;
        if (fatura.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fatura.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Fatura{" +
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
