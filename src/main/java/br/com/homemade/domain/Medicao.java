package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Medicao.
 */
@Entity
@Table(name = "medicao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Medicao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ajustecambio")
    private Boolean ajustecambio;

    @Column(name = "conferido")
    private String conferido;

    @Column(name = "idmedicao")
    private Integer idmedicao;

    @Column(name = "mes")
    private ZonedDateTime mes;

    @Column(name = "nummedicao")
    private Integer nummedicao;

    @Column(name = "tipomedicao")
    private String tipomedicao;

    @Column(name = "valormedicao", precision=10, scale=2)
    private BigDecimal valormedicao;

    @Column(name = "valormedicaoreajuste", precision=10, scale=2)
    private BigDecimal valormedicaoreajuste;

    @Column(name = "valorusmedicao", precision=10, scale=2)
    private BigDecimal valorusmedicao;

    @ManyToOne
    private Contrato idcontrato;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isAjustecambio() {
        return ajustecambio;
    }

    public Medicao ajustecambio(Boolean ajustecambio) {
        this.ajustecambio = ajustecambio;
        return this;
    }

    public void setAjustecambio(Boolean ajustecambio) {
        this.ajustecambio = ajustecambio;
    }

    public String getConferido() {
        return conferido;
    }

    public Medicao conferido(String conferido) {
        this.conferido = conferido;
        return this;
    }

    public void setConferido(String conferido) {
        this.conferido = conferido;
    }

    public Integer getIdmedicao() {
        return idmedicao;
    }

    public Medicao idmedicao(Integer idmedicao) {
        this.idmedicao = idmedicao;
        return this;
    }

    public void setIdmedicao(Integer idmedicao) {
        this.idmedicao = idmedicao;
    }

    public ZonedDateTime getMes() {
        return mes;
    }

    public Medicao mes(ZonedDateTime mes) {
        this.mes = mes;
        return this;
    }

    public void setMes(ZonedDateTime mes) {
        this.mes = mes;
    }

    public Integer getNummedicao() {
        return nummedicao;
    }

    public Medicao nummedicao(Integer nummedicao) {
        this.nummedicao = nummedicao;
        return this;
    }

    public void setNummedicao(Integer nummedicao) {
        this.nummedicao = nummedicao;
    }

    public String getTipomedicao() {
        return tipomedicao;
    }

    public Medicao tipomedicao(String tipomedicao) {
        this.tipomedicao = tipomedicao;
        return this;
    }

    public void setTipomedicao(String tipomedicao) {
        this.tipomedicao = tipomedicao;
    }

    public BigDecimal getValormedicao() {
        return valormedicao;
    }

    public Medicao valormedicao(BigDecimal valormedicao) {
        this.valormedicao = valormedicao;
        return this;
    }

    public void setValormedicao(BigDecimal valormedicao) {
        this.valormedicao = valormedicao;
    }

    public BigDecimal getValormedicaoreajuste() {
        return valormedicaoreajuste;
    }

    public Medicao valormedicaoreajuste(BigDecimal valormedicaoreajuste) {
        this.valormedicaoreajuste = valormedicaoreajuste;
        return this;
    }

    public void setValormedicaoreajuste(BigDecimal valormedicaoreajuste) {
        this.valormedicaoreajuste = valormedicaoreajuste;
    }

    public BigDecimal getValorusmedicao() {
        return valorusmedicao;
    }

    public Medicao valorusmedicao(BigDecimal valorusmedicao) {
        this.valorusmedicao = valorusmedicao;
        return this;
    }

    public void setValorusmedicao(BigDecimal valorusmedicao) {
        this.valorusmedicao = valorusmedicao;
    }

    public Contrato getIdcontrato() {
        return idcontrato;
    }

    public Medicao idcontrato(Contrato contrato) {
        this.idcontrato = contrato;
        return this;
    }

    public void setIdcontrato(Contrato contrato) {
        this.idcontrato = contrato;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Medicao medicao = (Medicao) o;
        if (medicao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), medicao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Medicao{" +
            "id=" + getId() +
            ", ajustecambio='" + isAjustecambio() + "'" +
            ", conferido='" + getConferido() + "'" +
            ", idmedicao='" + getIdmedicao() + "'" +
            ", mes='" + getMes() + "'" +
            ", nummedicao='" + getNummedicao() + "'" +
            ", tipomedicao='" + getTipomedicao() + "'" +
            ", valormedicao='" + getValormedicao() + "'" +
            ", valormedicaoreajuste='" + getValormedicaoreajuste() + "'" +
            ", valorusmedicao='" + getValorusmedicao() + "'" +
            "}";
    }
}
