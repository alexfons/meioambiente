package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Desapropriacao.
 */
@Entity
@Table(name = "desapropriacao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Desapropriacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "dataop")
    private ZonedDateTime dataop;

    @Column(name = "iddesapropria")
    private Integer iddesapropria;

    @Column(name = "nprocesso")
    private String nprocesso;

    @Column(name = "nomedesapropriado")
    private String nomedesapropriado;

    @Column(name = "jhi_local")
    private String local;

    @Column(name = "nsolicitacao")
    private Integer nsolicitacao;

    @Column(name = "valorpago", precision=10, scale=2)
    private BigDecimal valorpago;

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
    private Contabancaria idcontabancaria;

    @ManyToOne
    private Referencia idreferencia;

    @ManyToOne
    private Rodovia rodovia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDataop() {
        return dataop;
    }

    public Desapropriacao dataop(ZonedDateTime dataop) {
        this.dataop = dataop;
        return this;
    }

    public void setDataop(ZonedDateTime dataop) {
        this.dataop = dataop;
    }

    public Integer getIddesapropria() {
        return iddesapropria;
    }

    public Desapropriacao iddesapropria(Integer iddesapropria) {
        this.iddesapropria = iddesapropria;
        return this;
    }

    public void setIddesapropria(Integer iddesapropria) {
        this.iddesapropria = iddesapropria;
    }

    public String getNprocesso() {
        return nprocesso;
    }

    public Desapropriacao nprocesso(String nprocesso) {
        this.nprocesso = nprocesso;
        return this;
    }

    public void setNprocesso(String nprocesso) {
        this.nprocesso = nprocesso;
    }

    public String getNomedesapropriado() {
        return nomedesapropriado;
    }

    public Desapropriacao nomedesapropriado(String nomedesapropriado) {
        this.nomedesapropriado = nomedesapropriado;
        return this;
    }

    public void setNomedesapropriado(String nomedesapropriado) {
        this.nomedesapropriado = nomedesapropriado;
    }

    public String getLocal() {
        return local;
    }

    public Desapropriacao local(String local) {
        this.local = local;
        return this;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Integer getNsolicitacao() {
        return nsolicitacao;
    }

    public Desapropriacao nsolicitacao(Integer nsolicitacao) {
        this.nsolicitacao = nsolicitacao;
        return this;
    }

    public void setNsolicitacao(Integer nsolicitacao) {
        this.nsolicitacao = nsolicitacao;
    }

    public BigDecimal getValorpago() {
        return valorpago;
    }

    public Desapropriacao valorpago(BigDecimal valorpago) {
        this.valorpago = valorpago;
        return this;
    }

    public void setValorpago(BigDecimal valorpago) {
        this.valorpago = valorpago;
    }

    public BigDecimal getValorus() {
        return valorus;
    }

    public Desapropriacao valorus(BigDecimal valorus) {
        this.valorus = valorus;
        return this;
    }

    public void setValorus(BigDecimal valorus) {
        this.valorus = valorus;
    }

    public Planocontas getContacontabilC1() {
        return contacontabilC1;
    }

    public Desapropriacao contacontabilC1(Planocontas planocontas) {
        this.contacontabilC1 = planocontas;
        return this;
    }

    public void setContacontabilC1(Planocontas planocontas) {
        this.contacontabilC1 = planocontas;
    }

    public Planocontas getContacontabilC2() {
        return contacontabilC2;
    }

    public Desapropriacao contacontabilC2(Planocontas planocontas) {
        this.contacontabilC2 = planocontas;
        return this;
    }

    public void setContacontabilC2(Planocontas planocontas) {
        this.contacontabilC2 = planocontas;
    }

    public Planocontas getContacontabilD1() {
        return contacontabilD1;
    }

    public Desapropriacao contacontabilD1(Planocontas planocontas) {
        this.contacontabilD1 = planocontas;
        return this;
    }

    public void setContacontabilD1(Planocontas planocontas) {
        this.contacontabilD1 = planocontas;
    }

    public Planocontas getContacontabilD2() {
        return contacontabilD2;
    }

    public Desapropriacao contacontabilD2(Planocontas planocontas) {
        this.contacontabilD2 = planocontas;
        return this;
    }

    public void setContacontabilD2(Planocontas planocontas) {
        this.contacontabilD2 = planocontas;
    }

    public Planocontas getDivcontacontabilC1() {
        return divcontacontabilC1;
    }

    public Desapropriacao divcontacontabilC1(Planocontas planocontas) {
        this.divcontacontabilC1 = planocontas;
        return this;
    }

    public void setDivcontacontabilC1(Planocontas planocontas) {
        this.divcontacontabilC1 = planocontas;
    }

    public Planocontas getDivcontacontabilD1() {
        return divcontacontabilD1;
    }

    public Desapropriacao divcontacontabilD1(Planocontas planocontas) {
        this.divcontacontabilD1 = planocontas;
        return this;
    }

    public void setDivcontacontabilD1(Planocontas planocontas) {
        this.divcontacontabilD1 = planocontas;
    }

    public Planocontas getJuscontacontabilC1() {
        return juscontacontabilC1;
    }

    public Desapropriacao juscontacontabilC1(Planocontas planocontas) {
        this.juscontacontabilC1 = planocontas;
        return this;
    }

    public void setJuscontacontabilC1(Planocontas planocontas) {
        this.juscontacontabilC1 = planocontas;
    }

    public Planocontas getJuscontacontabilD1() {
        return juscontacontabilD1;
    }

    public Desapropriacao juscontacontabilD1(Planocontas planocontas) {
        this.juscontacontabilD1 = planocontas;
        return this;
    }

    public void setJuscontacontabilD1(Planocontas planocontas) {
        this.juscontacontabilD1 = planocontas;
    }

    public Fonte getFonte() {
        return fonte;
    }

    public Desapropriacao fonte(Fonte fonte) {
        this.fonte = fonte;
        return this;
    }

    public void setFonte(Fonte fonte) {
        this.fonte = fonte;
    }

    public Categoriainversao getIdcategoriainversao() {
        return idcategoriainversao;
    }

    public Desapropriacao idcategoriainversao(Categoriainversao categoriainversao) {
        this.idcategoriainversao = categoriainversao;
        return this;
    }

    public void setIdcategoriainversao(Categoriainversao categoriainversao) {
        this.idcategoriainversao = categoriainversao;
    }

    public Contabancaria getIdcontabancaria() {
        return idcontabancaria;
    }

    public Desapropriacao idcontabancaria(Contabancaria contabancaria) {
        this.idcontabancaria = contabancaria;
        return this;
    }

    public void setIdcontabancaria(Contabancaria contabancaria) {
        this.idcontabancaria = contabancaria;
    }

    public Referencia getIdreferencia() {
        return idreferencia;
    }

    public Desapropriacao idreferencia(Referencia referencia) {
        this.idreferencia = referencia;
        return this;
    }

    public void setIdreferencia(Referencia referencia) {
        this.idreferencia = referencia;
    }

    public Rodovia getRodovia() {
        return rodovia;
    }

    public Desapropriacao rodovia(Rodovia rodovia) {
        this.rodovia = rodovia;
        return this;
    }

    public void setRodovia(Rodovia rodovia) {
        this.rodovia = rodovia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Desapropriacao desapropriacao = (Desapropriacao) o;
        if (desapropriacao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), desapropriacao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Desapropriacao{" +
            "id=" + getId() +
            ", dataop='" + getDataop() + "'" +
            ", iddesapropria='" + getIddesapropria() + "'" +
            ", nprocesso='" + getNprocesso() + "'" +
            ", nomedesapropriado='" + getNomedesapropriado() + "'" +
            ", local='" + getLocal() + "'" +
            ", nsolicitacao='" + getNsolicitacao() + "'" +
            ", valorpago='" + getValorpago() + "'" +
            ", valorus='" + getValorus() + "'" +
            "}";
    }
}
