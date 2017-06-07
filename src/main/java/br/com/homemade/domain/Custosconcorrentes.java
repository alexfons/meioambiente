package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Custosconcorrentes.
 */
@Entity
@Table(name = "custosconcorrentes")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Custosconcorrentes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "datainicio")
    private ZonedDateTime datainicio;

    @Column(name = "nsolicitacao")
    private Integer nsolicitacao;

    @Column(name = "valorus", precision=10, scale=2)
    private BigDecimal valorus;

    @Column(name = "valorpagoreais", precision=10, scale=2)
    private BigDecimal valorpagoreais;

    @Column(name = "taxa", precision=10, scale=2)
    private BigDecimal taxa;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDatainicio() {
        return datainicio;
    }

    public Custosconcorrentes datainicio(ZonedDateTime datainicio) {
        this.datainicio = datainicio;
        return this;
    }

    public void setDatainicio(ZonedDateTime datainicio) {
        this.datainicio = datainicio;
    }

    public Integer getNsolicitacao() {
        return nsolicitacao;
    }

    public Custosconcorrentes nsolicitacao(Integer nsolicitacao) {
        this.nsolicitacao = nsolicitacao;
        return this;
    }

    public void setNsolicitacao(Integer nsolicitacao) {
        this.nsolicitacao = nsolicitacao;
    }

    public BigDecimal getValorus() {
        return valorus;
    }

    public Custosconcorrentes valorus(BigDecimal valorus) {
        this.valorus = valorus;
        return this;
    }

    public void setValorus(BigDecimal valorus) {
        this.valorus = valorus;
    }

    public BigDecimal getValorpagoreais() {
        return valorpagoreais;
    }

    public Custosconcorrentes valorpagoreais(BigDecimal valorpagoreais) {
        this.valorpagoreais = valorpagoreais;
        return this;
    }

    public void setValorpagoreais(BigDecimal valorpagoreais) {
        this.valorpagoreais = valorpagoreais;
    }

    public BigDecimal getTaxa() {
        return taxa;
    }

    public Custosconcorrentes taxa(BigDecimal taxa) {
        this.taxa = taxa;
        return this;
    }

    public void setTaxa(BigDecimal taxa) {
        this.taxa = taxa;
    }

    public Planocontas getContacontabilC1() {
        return contacontabilC1;
    }

    public Custosconcorrentes contacontabilC1(Planocontas planocontas) {
        this.contacontabilC1 = planocontas;
        return this;
    }

    public void setContacontabilC1(Planocontas planocontas) {
        this.contacontabilC1 = planocontas;
    }

    public Planocontas getContacontabilC2() {
        return contacontabilC2;
    }

    public Custosconcorrentes contacontabilC2(Planocontas planocontas) {
        this.contacontabilC2 = planocontas;
        return this;
    }

    public void setContacontabilC2(Planocontas planocontas) {
        this.contacontabilC2 = planocontas;
    }

    public Planocontas getContacontabilD1() {
        return contacontabilD1;
    }

    public Custosconcorrentes contacontabilD1(Planocontas planocontas) {
        this.contacontabilD1 = planocontas;
        return this;
    }

    public void setContacontabilD1(Planocontas planocontas) {
        this.contacontabilD1 = planocontas;
    }

    public Planocontas getContacontabilD2() {
        return contacontabilD2;
    }

    public Custosconcorrentes contacontabilD2(Planocontas planocontas) {
        this.contacontabilD2 = planocontas;
        return this;
    }

    public void setContacontabilD2(Planocontas planocontas) {
        this.contacontabilD2 = planocontas;
    }

    public Planocontas getDivcontacontabilC1() {
        return divcontacontabilC1;
    }

    public Custosconcorrentes divcontacontabilC1(Planocontas planocontas) {
        this.divcontacontabilC1 = planocontas;
        return this;
    }

    public void setDivcontacontabilC1(Planocontas planocontas) {
        this.divcontacontabilC1 = planocontas;
    }

    public Planocontas getDivcontacontabilD1() {
        return divcontacontabilD1;
    }

    public Custosconcorrentes divcontacontabilD1(Planocontas planocontas) {
        this.divcontacontabilD1 = planocontas;
        return this;
    }

    public void setDivcontacontabilD1(Planocontas planocontas) {
        this.divcontacontabilD1 = planocontas;
    }

    public Planocontas getJuscontacontabilC1() {
        return juscontacontabilC1;
    }

    public Custosconcorrentes juscontacontabilC1(Planocontas planocontas) {
        this.juscontacontabilC1 = planocontas;
        return this;
    }

    public void setJuscontacontabilC1(Planocontas planocontas) {
        this.juscontacontabilC1 = planocontas;
    }

    public Planocontas getJuscontacontabilD1() {
        return juscontacontabilD1;
    }

    public Custosconcorrentes juscontacontabilD1(Planocontas planocontas) {
        this.juscontacontabilD1 = planocontas;
        return this;
    }

    public void setJuscontacontabilD1(Planocontas planocontas) {
        this.juscontacontabilD1 = planocontas;
    }

    public Fonte getFonte() {
        return fonte;
    }

    public Custosconcorrentes fonte(Fonte fonte) {
        this.fonte = fonte;
        return this;
    }

    public void setFonte(Fonte fonte) {
        this.fonte = fonte;
    }

    public Categoriainversao getIdcategoriainversao() {
        return idcategoriainversao;
    }

    public Custosconcorrentes idcategoriainversao(Categoriainversao categoriainversao) {
        this.idcategoriainversao = categoriainversao;
        return this;
    }

    public void setIdcategoriainversao(Categoriainversao categoriainversao) {
        this.idcategoriainversao = categoriainversao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Custosconcorrentes custosconcorrentes = (Custosconcorrentes) o;
        if (custosconcorrentes.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), custosconcorrentes.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Custosconcorrentes{" +
            "id=" + getId() +
            ", datainicio='" + getDatainicio() + "'" +
            ", nsolicitacao='" + getNsolicitacao() + "'" +
            ", valorus='" + getValorus() + "'" +
            ", valorpagoreais='" + getValorpagoreais() + "'" +
            ", taxa='" + getTaxa() + "'" +
            "}";
    }
}
