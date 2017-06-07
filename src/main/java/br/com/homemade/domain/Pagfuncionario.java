package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Pagfuncionario.
 */
@Entity
@Table(name = "pagfuncionario")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Pagfuncionario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "datapagamento")
    private ZonedDateTime datapagamento;

    @Column(name = "idpagfuncionarios")
    private Integer idpagfuncionarios;

    @Column(name = "nsolicitacao")
    private Integer nsolicitacao;

    @Column(name = "percentual", precision=10, scale=2)
    private BigDecimal percentual;

    @Column(name = "salario", precision=10, scale=2)
    private BigDecimal salario;

    @Column(name = "salariocontribuicao", precision=10, scale=2)
    private BigDecimal salariocontribuicao;

    @Column(name = "salariototal", precision=10, scale=2)
    private BigDecimal salariototal;

    @Column(name = "valorus", precision=10, scale=2)
    private BigDecimal valorus;

    @ManyToOne
    private Planocontas contacontabilC1;

    @ManyToOne
    private Planocontas contacontabilC2;

    @ManyToOne
    private Planocontas contacontabilD1;

    @ManyToOne
    private Planocontas contacontabilD2;

    @ManyToOne
    private Planocontas divcontacontabilC1;

    @ManyToOne
    private Planocontas divcontacontabilD1;

    @ManyToOne
    private Planocontas juscontacontabilC1;

    @ManyToOne
    private Planocontas juscontacontabilD1;

    @ManyToOne
    private Fonte fonte;

    @ManyToOne
    private Categoriainversao idcategoriainversao;

    @ManyToOne
    private Funcionario idfuncionarios;

    @ManyToOne
    private Referencia idreferencia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDatapagamento() {
        return datapagamento;
    }

    public Pagfuncionario datapagamento(ZonedDateTime datapagamento) {
        this.datapagamento = datapagamento;
        return this;
    }

    public void setDatapagamento(ZonedDateTime datapagamento) {
        this.datapagamento = datapagamento;
    }

    public Integer getIdpagfuncionarios() {
        return idpagfuncionarios;
    }

    public Pagfuncionario idpagfuncionarios(Integer idpagfuncionarios) {
        this.idpagfuncionarios = idpagfuncionarios;
        return this;
    }

    public void setIdpagfuncionarios(Integer idpagfuncionarios) {
        this.idpagfuncionarios = idpagfuncionarios;
    }

    public Integer getNsolicitacao() {
        return nsolicitacao;
    }

    public Pagfuncionario nsolicitacao(Integer nsolicitacao) {
        this.nsolicitacao = nsolicitacao;
        return this;
    }

    public void setNsolicitacao(Integer nsolicitacao) {
        this.nsolicitacao = nsolicitacao;
    }

    public BigDecimal getPercentual() {
        return percentual;
    }

    public Pagfuncionario percentual(BigDecimal percentual) {
        this.percentual = percentual;
        return this;
    }

    public void setPercentual(BigDecimal percentual) {
        this.percentual = percentual;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public Pagfuncionario salario(BigDecimal salario) {
        this.salario = salario;
        return this;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public BigDecimal getSalariocontribuicao() {
        return salariocontribuicao;
    }

    public Pagfuncionario salariocontribuicao(BigDecimal salariocontribuicao) {
        this.salariocontribuicao = salariocontribuicao;
        return this;
    }

    public void setSalariocontribuicao(BigDecimal salariocontribuicao) {
        this.salariocontribuicao = salariocontribuicao;
    }

    public BigDecimal getSalariototal() {
        return salariototal;
    }

    public Pagfuncionario salariototal(BigDecimal salariototal) {
        this.salariototal = salariototal;
        return this;
    }

    public void setSalariototal(BigDecimal salariototal) {
        this.salariototal = salariototal;
    }

    public BigDecimal getValorus() {
        return valorus;
    }

    public Pagfuncionario valorus(BigDecimal valorus) {
        this.valorus = valorus;
        return this;
    }

    public void setValorus(BigDecimal valorus) {
        this.valorus = valorus;
    }

    public Planocontas getContacontabilC1() {
        return contacontabilC1;
    }

    public Pagfuncionario contacontabilC1(Planocontas planocontas) {
        this.contacontabilC1 = planocontas;
        return this;
    }

    public void setContacontabilC1(Planocontas planocontas) {
        this.contacontabilC1 = planocontas;
    }

    public Planocontas getContacontabilC2() {
        return contacontabilC2;
    }

    public Pagfuncionario contacontabilC2(Planocontas planocontas) {
        this.contacontabilC2 = planocontas;
        return this;
    }

    public void setContacontabilC2(Planocontas planocontas) {
        this.contacontabilC2 = planocontas;
    }

    public Planocontas getContacontabilD1() {
        return contacontabilD1;
    }

    public Pagfuncionario contacontabilD1(Planocontas planocontas) {
        this.contacontabilD1 = planocontas;
        return this;
    }

    public void setContacontabilD1(Planocontas planocontas) {
        this.contacontabilD1 = planocontas;
    }

    public Planocontas getContacontabilD2() {
        return contacontabilD2;
    }

    public Pagfuncionario contacontabilD2(Planocontas planocontas) {
        this.contacontabilD2 = planocontas;
        return this;
    }

    public void setContacontabilD2(Planocontas planocontas) {
        this.contacontabilD2 = planocontas;
    }

    public Planocontas getDivcontacontabilC1() {
        return divcontacontabilC1;
    }

    public Pagfuncionario divcontacontabilC1(Planocontas planocontas) {
        this.divcontacontabilC1 = planocontas;
        return this;
    }

    public void setDivcontacontabilC1(Planocontas planocontas) {
        this.divcontacontabilC1 = planocontas;
    }

    public Planocontas getDivcontacontabilD1() {
        return divcontacontabilD1;
    }

    public Pagfuncionario divcontacontabilD1(Planocontas planocontas) {
        this.divcontacontabilD1 = planocontas;
        return this;
    }

    public void setDivcontacontabilD1(Planocontas planocontas) {
        this.divcontacontabilD1 = planocontas;
    }

    public Planocontas getJuscontacontabilC1() {
        return juscontacontabilC1;
    }

    public Pagfuncionario juscontacontabilC1(Planocontas planocontas) {
        this.juscontacontabilC1 = planocontas;
        return this;
    }

    public void setJuscontacontabilC1(Planocontas planocontas) {
        this.juscontacontabilC1 = planocontas;
    }

    public Planocontas getJuscontacontabilD1() {
        return juscontacontabilD1;
    }

    public Pagfuncionario juscontacontabilD1(Planocontas planocontas) {
        this.juscontacontabilD1 = planocontas;
        return this;
    }

    public void setJuscontacontabilD1(Planocontas planocontas) {
        this.juscontacontabilD1 = planocontas;
    }

    public Fonte getFonte() {
        return fonte;
    }

    public Pagfuncionario fonte(Fonte fonte) {
        this.fonte = fonte;
        return this;
    }

    public void setFonte(Fonte fonte) {
        this.fonte = fonte;
    }

    public Categoriainversao getIdcategoriainversao() {
        return idcategoriainversao;
    }

    public Pagfuncionario idcategoriainversao(Categoriainversao categoriainversao) {
        this.idcategoriainversao = categoriainversao;
        return this;
    }

    public void setIdcategoriainversao(Categoriainversao categoriainversao) {
        this.idcategoriainversao = categoriainversao;
    }

    public Funcionario getIdfuncionarios() {
        return idfuncionarios;
    }

    public Pagfuncionario idfuncionarios(Funcionario funcionario) {
        this.idfuncionarios = funcionario;
        return this;
    }

    public void setIdfuncionarios(Funcionario funcionario) {
        this.idfuncionarios = funcionario;
    }

    public Referencia getIdreferencia() {
        return idreferencia;
    }

    public Pagfuncionario idreferencia(Referencia referencia) {
        this.idreferencia = referencia;
        return this;
    }

    public void setIdreferencia(Referencia referencia) {
        this.idreferencia = referencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pagfuncionario pagfuncionario = (Pagfuncionario) o;
        if (pagfuncionario.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pagfuncionario.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Pagfuncionario{" +
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
