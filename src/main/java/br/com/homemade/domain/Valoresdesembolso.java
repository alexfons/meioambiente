package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Valoresdesembolso.
 */
@Entity
@Table(name = "valoresdesembolso")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Valoresdesembolso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "datainternalizacao")
    private ZonedDateTime datainternalizacao;

    @Column(name = "idvaloresdesembolso")
    private Integer idvaloresdesembolso;

    @Column(name = "nsolicitacao")
    private Integer nsolicitacao;

    @Column(name = "tipodesembolso")
    private String tipodesembolso;

    @Column(name = "valoreais", precision=10, scale=2)
    private BigDecimal valoreais;

    @Column(name = "valorus", precision=10, scale=2)
    private BigDecimal valorus;

    @Column(name = "valuedata")
    private ZonedDateTime valuedata;

    @ManyToOne
    private Contabancaria idcontabancaria;

    @ManyToOne
    private Referencia idreferencia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDatainternalizacao() {
        return datainternalizacao;
    }

    public Valoresdesembolso datainternalizacao(ZonedDateTime datainternalizacao) {
        this.datainternalizacao = datainternalizacao;
        return this;
    }

    public void setDatainternalizacao(ZonedDateTime datainternalizacao) {
        this.datainternalizacao = datainternalizacao;
    }

    public Integer getIdvaloresdesembolso() {
        return idvaloresdesembolso;
    }

    public Valoresdesembolso idvaloresdesembolso(Integer idvaloresdesembolso) {
        this.idvaloresdesembolso = idvaloresdesembolso;
        return this;
    }

    public void setIdvaloresdesembolso(Integer idvaloresdesembolso) {
        this.idvaloresdesembolso = idvaloresdesembolso;
    }

    public Integer getNsolicitacao() {
        return nsolicitacao;
    }

    public Valoresdesembolso nsolicitacao(Integer nsolicitacao) {
        this.nsolicitacao = nsolicitacao;
        return this;
    }

    public void setNsolicitacao(Integer nsolicitacao) {
        this.nsolicitacao = nsolicitacao;
    }

    public String getTipodesembolso() {
        return tipodesembolso;
    }

    public Valoresdesembolso tipodesembolso(String tipodesembolso) {
        this.tipodesembolso = tipodesembolso;
        return this;
    }

    public void setTipodesembolso(String tipodesembolso) {
        this.tipodesembolso = tipodesembolso;
    }

    public BigDecimal getValoreais() {
        return valoreais;
    }

    public Valoresdesembolso valoreais(BigDecimal valoreais) {
        this.valoreais = valoreais;
        return this;
    }

    public void setValoreais(BigDecimal valoreais) {
        this.valoreais = valoreais;
    }

    public BigDecimal getValorus() {
        return valorus;
    }

    public Valoresdesembolso valorus(BigDecimal valorus) {
        this.valorus = valorus;
        return this;
    }

    public void setValorus(BigDecimal valorus) {
        this.valorus = valorus;
    }

    public ZonedDateTime getValuedata() {
        return valuedata;
    }

    public Valoresdesembolso valuedata(ZonedDateTime valuedata) {
        this.valuedata = valuedata;
        return this;
    }

    public void setValuedata(ZonedDateTime valuedata) {
        this.valuedata = valuedata;
    }

    public Contabancaria getIdcontabancaria() {
        return idcontabancaria;
    }

    public Valoresdesembolso idcontabancaria(Contabancaria contabancaria) {
        this.idcontabancaria = contabancaria;
        return this;
    }

    public void setIdcontabancaria(Contabancaria contabancaria) {
        this.idcontabancaria = contabancaria;
    }

    public Referencia getIdreferencia() {
        return idreferencia;
    }

    public Valoresdesembolso idreferencia(Referencia referencia) {
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
        Valoresdesembolso valoresdesembolso = (Valoresdesembolso) o;
        if (valoresdesembolso.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), valoresdesembolso.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Valoresdesembolso{" +
            "id=" + getId() +
            ", datainternalizacao='" + getDatainternalizacao() + "'" +
            ", idvaloresdesembolso='" + getIdvaloresdesembolso() + "'" +
            ", nsolicitacao='" + getNsolicitacao() + "'" +
            ", tipodesembolso='" + getTipodesembolso() + "'" +
            ", valoreais='" + getValoreais() + "'" +
            ", valorus='" + getValorus() + "'" +
            ", valuedata='" + getValuedata() + "'" +
            "}";
    }
}
