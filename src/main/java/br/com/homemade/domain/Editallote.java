package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Editallote.
 */
@Entity
@Table(name = "editallote")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Editallote implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "dataprovacaolote")
    private ZonedDateTime dataprovacaolote;

    @Column(name = "datarelatoriolote")
    private ZonedDateTime datarelatoriolote;

    @Column(name = "idlote")
    private Integer idlote;

    @Column(name = "lote")
    private String lote;

    @Column(name = "objeto")
    private String objeto;

    @Column(name = "prazo")
    private String prazo;

    @Column(name = "valororcado", precision=10, scale=2)
    private BigDecimal valororcado;

    @ManyToOne
    private Edital numeroedital;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDataprovacaolote() {
        return dataprovacaolote;
    }

    public Editallote dataprovacaolote(ZonedDateTime dataprovacaolote) {
        this.dataprovacaolote = dataprovacaolote;
        return this;
    }

    public void setDataprovacaolote(ZonedDateTime dataprovacaolote) {
        this.dataprovacaolote = dataprovacaolote;
    }

    public ZonedDateTime getDatarelatoriolote() {
        return datarelatoriolote;
    }

    public Editallote datarelatoriolote(ZonedDateTime datarelatoriolote) {
        this.datarelatoriolote = datarelatoriolote;
        return this;
    }

    public void setDatarelatoriolote(ZonedDateTime datarelatoriolote) {
        this.datarelatoriolote = datarelatoriolote;
    }

    public Integer getIdlote() {
        return idlote;
    }

    public Editallote idlote(Integer idlote) {
        this.idlote = idlote;
        return this;
    }

    public void setIdlote(Integer idlote) {
        this.idlote = idlote;
    }

    public String getLote() {
        return lote;
    }

    public Editallote lote(String lote) {
        this.lote = lote;
        return this;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getObjeto() {
        return objeto;
    }

    public Editallote objeto(String objeto) {
        this.objeto = objeto;
        return this;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getPrazo() {
        return prazo;
    }

    public Editallote prazo(String prazo) {
        this.prazo = prazo;
        return this;
    }

    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }

    public BigDecimal getValororcado() {
        return valororcado;
    }

    public Editallote valororcado(BigDecimal valororcado) {
        this.valororcado = valororcado;
        return this;
    }

    public void setValororcado(BigDecimal valororcado) {
        this.valororcado = valororcado;
    }

    public Edital getNumeroedital() {
        return numeroedital;
    }

    public Editallote numeroedital(Edital edital) {
        this.numeroedital = edital;
        return this;
    }

    public void setNumeroedital(Edital edital) {
        this.numeroedital = edital;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Editallote editallote = (Editallote) o;
        if (editallote.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), editallote.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Editallote{" +
            "id=" + getId() +
            ", dataprovacaolote='" + getDataprovacaolote() + "'" +
            ", datarelatoriolote='" + getDatarelatoriolote() + "'" +
            ", idlote='" + getIdlote() + "'" +
            ", lote='" + getLote() + "'" +
            ", objeto='" + getObjeto() + "'" +
            ", prazo='" + getPrazo() + "'" +
            ", valororcado='" + getValororcado() + "'" +
            "}";
    }
}
