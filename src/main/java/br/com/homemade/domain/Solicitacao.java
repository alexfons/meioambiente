package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Solicitacao.
 */
@Entity
@Table(name = "solicitacao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Solicitacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "apresentamos")
    private Boolean apresentamos;

    @Column(name = "data")
    private ZonedDateTime data;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "extenso")
    private String extenso;

    @Column(name = "extenso_1")
    private String extenso1;

    @Column(name = "idsolicitacao")
    private Integer idsolicitacao;

    @Column(name = "moeda")
    private String moeda;

    @Column(name = "montante", precision=10, scale=2)
    private BigDecimal montante;

    @Column(name = "nsolicitacao")
    private Integer nsolicitacao;

    @Column(name = "oficio")
    private String oficio;

    @Column(name = "solicitamos")
    private Boolean solicitamos;

    @OneToOne
    @JoinColumn(unique = true)
    private Banco idbanco;

    @ManyToOne
    private Contratoagente idContratoagente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isApresentamos() {
        return apresentamos;
    }

    public Solicitacao apresentamos(Boolean apresentamos) {
        this.apresentamos = apresentamos;
        return this;
    }

    public void setApresentamos(Boolean apresentamos) {
        this.apresentamos = apresentamos;
    }

    public ZonedDateTime getData() {
        return data;
    }

    public Solicitacao data(ZonedDateTime data) {
        this.data = data;
        return this;
    }

    public void setData(ZonedDateTime data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public Solicitacao descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getExtenso() {
        return extenso;
    }

    public Solicitacao extenso(String extenso) {
        this.extenso = extenso;
        return this;
    }

    public void setExtenso(String extenso) {
        this.extenso = extenso;
    }

    public String getExtenso1() {
        return extenso1;
    }

    public Solicitacao extenso1(String extenso1) {
        this.extenso1 = extenso1;
        return this;
    }

    public void setExtenso1(String extenso1) {
        this.extenso1 = extenso1;
    }

    public Integer getIdsolicitacao() {
        return idsolicitacao;
    }

    public Solicitacao idsolicitacao(Integer idsolicitacao) {
        this.idsolicitacao = idsolicitacao;
        return this;
    }

    public void setIdsolicitacao(Integer idsolicitacao) {
        this.idsolicitacao = idsolicitacao;
    }

    public String getMoeda() {
        return moeda;
    }

    public Solicitacao moeda(String moeda) {
        this.moeda = moeda;
        return this;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public BigDecimal getMontante() {
        return montante;
    }

    public Solicitacao montante(BigDecimal montante) {
        this.montante = montante;
        return this;
    }

    public void setMontante(BigDecimal montante) {
        this.montante = montante;
    }

    public Integer getNsolicitacao() {
        return nsolicitacao;
    }

    public Solicitacao nsolicitacao(Integer nsolicitacao) {
        this.nsolicitacao = nsolicitacao;
        return this;
    }

    public void setNsolicitacao(Integer nsolicitacao) {
        this.nsolicitacao = nsolicitacao;
    }

    public String getOficio() {
        return oficio;
    }

    public Solicitacao oficio(String oficio) {
        this.oficio = oficio;
        return this;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public Boolean isSolicitamos() {
        return solicitamos;
    }

    public Solicitacao solicitamos(Boolean solicitamos) {
        this.solicitamos = solicitamos;
        return this;
    }

    public void setSolicitamos(Boolean solicitamos) {
        this.solicitamos = solicitamos;
    }

    public Banco getIdbanco() {
        return idbanco;
    }

    public Solicitacao idbanco(Banco banco) {
        this.idbanco = banco;
        return this;
    }

    public void setIdbanco(Banco banco) {
        this.idbanco = banco;
    }

    public Contratoagente getIdContratoagente() {
        return idContratoagente;
    }

    public Solicitacao idContratoagente(Contratoagente contratoagente) {
        this.idContratoagente = contratoagente;
        return this;
    }

    public void setIdContratoagente(Contratoagente contratoagente) {
        this.idContratoagente = contratoagente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Solicitacao solicitacao = (Solicitacao) o;
        if (solicitacao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), solicitacao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Solicitacao{" +
            "id=" + getId() +
            ", apresentamos='" + isApresentamos() + "'" +
            ", data='" + getData() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", extenso='" + getExtenso() + "'" +
            ", extenso1='" + getExtenso1() + "'" +
            ", idsolicitacao='" + getIdsolicitacao() + "'" +
            ", moeda='" + getMoeda() + "'" +
            ", montante='" + getMontante() + "'" +
            ", nsolicitacao='" + getNsolicitacao() + "'" +
            ", oficio='" + getOficio() + "'" +
            ", solicitamos='" + isSolicitamos() + "'" +
            "}";
    }
}
