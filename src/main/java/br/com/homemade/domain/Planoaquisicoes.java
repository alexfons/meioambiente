package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Planoaquisicoes.
 */
@Entity
@Table(name = "planoaquisicoes")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Planoaquisicoes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "avisolicitacao")
    private ZonedDateTime avisolicitacao;

    @Column(name = "custoestimado", precision=10, scale=2)
    private BigDecimal custoestimado;

    @Column(name = "aportelocal", precision=10, scale=2)
    private BigDecimal aportelocal;

    @Column(name = "aporteagente", precision=10, scale=2)
    private BigDecimal aporteagente;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "metodo")
    private String metodo;

    @Column(name = "revisao")
    private String revisao;

    @Column(name = "prequalificado")
    private String prequalificado;

    @Column(name = "situacao")
    private String situacao;

    @Column(name = "idplanoaquisicoes")
    private Integer idplanoaquisicoes;

    @ManyToOne
    private Fonte fonte;

    @ManyToOne
    private Contratoagente idcontratoagente;

    @ManyToOne
    private Edital idedital;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getAvisolicitacao() {
        return avisolicitacao;
    }

    public Planoaquisicoes avisolicitacao(ZonedDateTime avisolicitacao) {
        this.avisolicitacao = avisolicitacao;
        return this;
    }

    public void setAvisolicitacao(ZonedDateTime avisolicitacao) {
        this.avisolicitacao = avisolicitacao;
    }

    public BigDecimal getCustoestimado() {
        return custoestimado;
    }

    public Planoaquisicoes custoestimado(BigDecimal custoestimado) {
        this.custoestimado = custoestimado;
        return this;
    }

    public void setCustoestimado(BigDecimal custoestimado) {
        this.custoestimado = custoestimado;
    }

    public BigDecimal getAportelocal() {
        return aportelocal;
    }

    public Planoaquisicoes aportelocal(BigDecimal aportelocal) {
        this.aportelocal = aportelocal;
        return this;
    }

    public void setAportelocal(BigDecimal aportelocal) {
        this.aportelocal = aportelocal;
    }

    public BigDecimal getAporteagente() {
        return aporteagente;
    }

    public Planoaquisicoes aporteagente(BigDecimal aporteagente) {
        this.aporteagente = aporteagente;
        return this;
    }

    public void setAporteagente(BigDecimal aporteagente) {
        this.aporteagente = aporteagente;
    }

    public String getDescricao() {
        return descricao;
    }

    public Planoaquisicoes descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMetodo() {
        return metodo;
    }

    public Planoaquisicoes metodo(String metodo) {
        this.metodo = metodo;
        return this;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getRevisao() {
        return revisao;
    }

    public Planoaquisicoes revisao(String revisao) {
        this.revisao = revisao;
        return this;
    }

    public void setRevisao(String revisao) {
        this.revisao = revisao;
    }

    public String getPrequalificado() {
        return prequalificado;
    }

    public Planoaquisicoes prequalificado(String prequalificado) {
        this.prequalificado = prequalificado;
        return this;
    }

    public void setPrequalificado(String prequalificado) {
        this.prequalificado = prequalificado;
    }

    public String getSituacao() {
        return situacao;
    }

    public Planoaquisicoes situacao(String situacao) {
        this.situacao = situacao;
        return this;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Integer getIdplanoaquisicoes() {
        return idplanoaquisicoes;
    }

    public Planoaquisicoes idplanoaquisicoes(Integer idplanoaquisicoes) {
        this.idplanoaquisicoes = idplanoaquisicoes;
        return this;
    }

    public void setIdplanoaquisicoes(Integer idplanoaquisicoes) {
        this.idplanoaquisicoes = idplanoaquisicoes;
    }

    public Fonte getFonte() {
        return fonte;
    }

    public Planoaquisicoes fonte(Fonte fonte) {
        this.fonte = fonte;
        return this;
    }

    public void setFonte(Fonte fonte) {
        this.fonte = fonte;
    }

    public Contratoagente getIdcontratoagente() {
        return idcontratoagente;
    }

    public Planoaquisicoes idcontratoagente(Contratoagente contratoagente) {
        this.idcontratoagente = contratoagente;
        return this;
    }

    public void setIdcontratoagente(Contratoagente contratoagente) {
        this.idcontratoagente = contratoagente;
    }

    public Edital getIdedital() {
        return idedital;
    }

    public Planoaquisicoes idedital(Edital edital) {
        this.idedital = edital;
        return this;
    }

    public void setIdedital(Edital edital) {
        this.idedital = edital;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Planoaquisicoes planoaquisicoes = (Planoaquisicoes) o;
        if (planoaquisicoes.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), planoaquisicoes.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Planoaquisicoes{" +
            "id=" + getId() +
            ", avisolicitacao='" + getAvisolicitacao() + "'" +
            ", custoestimado='" + getCustoestimado() + "'" +
            ", aportelocal='" + getAportelocal() + "'" +
            ", aporteagente='" + getAporteagente() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", metodo='" + getMetodo() + "'" +
            ", revisao='" + getRevisao() + "'" +
            ", prequalificado='" + getPrequalificado() + "'" +
            ", situacao='" + getSituacao() + "'" +
            ", idplanoaquisicoes='" + getIdplanoaquisicoes() + "'" +
            "}";
    }
}
