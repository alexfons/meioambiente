package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Banco.
 */
@Entity
@Table(name = "banco")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Banco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "cidadedep")
    private String cidadedep;

    @Column(name = "cidadeint")
    private String cidadeint;

    @Column(name = "codswifdep")
    private String codswifdep;

    @Column(name = "codswifint")
    private String codswifint;

    @Column(name = "contafeddep")
    private Boolean contafeddep;

    @Column(name = "contafedint")
    private Boolean contafedint;

    @Column(name = "enderecodep")
    private String enderecodep;

    @Column(name = "enderecoint")
    private String enderecoint;

    @Column(name = "idbanco")
    private Integer idbanco;

    @Column(name = "instrucoesespeciaisdep")
    private String instrucoesespeciaisdep;

    @Column(name = "instrucoesespeciaisint")
    private String instrucoesespeciaisint;

    @Column(name = "nabadep")
    private Integer nabadep;

    @Column(name = "nabaint")
    private Integer nabaint;

    @Column(name = "ncontadep")
    private Integer ncontadep;

    @Column(name = "ncontaint")
    private Integer ncontaint;

    @Column(name = "nomebancodep")
    private String nomebancodep;

    @Column(name = "nomebancoint")
    private String nomebancoint;

    @Column(name = "paisdep")
    private String paisdep;

    @Column(name = "paisint")
    private String paisint;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCidadedep() {
        return cidadedep;
    }

    public Banco cidadedep(String cidadedep) {
        this.cidadedep = cidadedep;
        return this;
    }

    public void setCidadedep(String cidadedep) {
        this.cidadedep = cidadedep;
    }

    public String getCidadeint() {
        return cidadeint;
    }

    public Banco cidadeint(String cidadeint) {
        this.cidadeint = cidadeint;
        return this;
    }

    public void setCidadeint(String cidadeint) {
        this.cidadeint = cidadeint;
    }

    public String getCodswifdep() {
        return codswifdep;
    }

    public Banco codswifdep(String codswifdep) {
        this.codswifdep = codswifdep;
        return this;
    }

    public void setCodswifdep(String codswifdep) {
        this.codswifdep = codswifdep;
    }

    public String getCodswifint() {
        return codswifint;
    }

    public Banco codswifint(String codswifint) {
        this.codswifint = codswifint;
        return this;
    }

    public void setCodswifint(String codswifint) {
        this.codswifint = codswifint;
    }

    public Boolean isContafeddep() {
        return contafeddep;
    }

    public Banco contafeddep(Boolean contafeddep) {
        this.contafeddep = contafeddep;
        return this;
    }

    public void setContafeddep(Boolean contafeddep) {
        this.contafeddep = contafeddep;
    }

    public Boolean isContafedint() {
        return contafedint;
    }

    public Banco contafedint(Boolean contafedint) {
        this.contafedint = contafedint;
        return this;
    }

    public void setContafedint(Boolean contafedint) {
        this.contafedint = contafedint;
    }

    public String getEnderecodep() {
        return enderecodep;
    }

    public Banco enderecodep(String enderecodep) {
        this.enderecodep = enderecodep;
        return this;
    }

    public void setEnderecodep(String enderecodep) {
        this.enderecodep = enderecodep;
    }

    public String getEnderecoint() {
        return enderecoint;
    }

    public Banco enderecoint(String enderecoint) {
        this.enderecoint = enderecoint;
        return this;
    }

    public void setEnderecoint(String enderecoint) {
        this.enderecoint = enderecoint;
    }

    public Integer getIdbanco() {
        return idbanco;
    }

    public Banco idbanco(Integer idbanco) {
        this.idbanco = idbanco;
        return this;
    }

    public void setIdbanco(Integer idbanco) {
        this.idbanco = idbanco;
    }

    public String getInstrucoesespeciaisdep() {
        return instrucoesespeciaisdep;
    }

    public Banco instrucoesespeciaisdep(String instrucoesespeciaisdep) {
        this.instrucoesespeciaisdep = instrucoesespeciaisdep;
        return this;
    }

    public void setInstrucoesespeciaisdep(String instrucoesespeciaisdep) {
        this.instrucoesespeciaisdep = instrucoesespeciaisdep;
    }

    public String getInstrucoesespeciaisint() {
        return instrucoesespeciaisint;
    }

    public Banco instrucoesespeciaisint(String instrucoesespeciaisint) {
        this.instrucoesespeciaisint = instrucoesespeciaisint;
        return this;
    }

    public void setInstrucoesespeciaisint(String instrucoesespeciaisint) {
        this.instrucoesespeciaisint = instrucoesespeciaisint;
    }

    public Integer getNabadep() {
        return nabadep;
    }

    public Banco nabadep(Integer nabadep) {
        this.nabadep = nabadep;
        return this;
    }

    public void setNabadep(Integer nabadep) {
        this.nabadep = nabadep;
    }

    public Integer getNabaint() {
        return nabaint;
    }

    public Banco nabaint(Integer nabaint) {
        this.nabaint = nabaint;
        return this;
    }

    public void setNabaint(Integer nabaint) {
        this.nabaint = nabaint;
    }

    public Integer getNcontadep() {
        return ncontadep;
    }

    public Banco ncontadep(Integer ncontadep) {
        this.ncontadep = ncontadep;
        return this;
    }

    public void setNcontadep(Integer ncontadep) {
        this.ncontadep = ncontadep;
    }

    public Integer getNcontaint() {
        return ncontaint;
    }

    public Banco ncontaint(Integer ncontaint) {
        this.ncontaint = ncontaint;
        return this;
    }

    public void setNcontaint(Integer ncontaint) {
        this.ncontaint = ncontaint;
    }

    public String getNomebancodep() {
        return nomebancodep;
    }

    public Banco nomebancodep(String nomebancodep) {
        this.nomebancodep = nomebancodep;
        return this;
    }

    public void setNomebancodep(String nomebancodep) {
        this.nomebancodep = nomebancodep;
    }

    public String getNomebancoint() {
        return nomebancoint;
    }

    public Banco nomebancoint(String nomebancoint) {
        this.nomebancoint = nomebancoint;
        return this;
    }

    public void setNomebancoint(String nomebancoint) {
        this.nomebancoint = nomebancoint;
    }

    public String getPaisdep() {
        return paisdep;
    }

    public Banco paisdep(String paisdep) {
        this.paisdep = paisdep;
        return this;
    }

    public void setPaisdep(String paisdep) {
        this.paisdep = paisdep;
    }

    public String getPaisint() {
        return paisint;
    }

    public Banco paisint(String paisint) {
        this.paisint = paisint;
        return this;
    }

    public void setPaisint(String paisint) {
        this.paisint = paisint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Banco banco = (Banco) o;
        if (banco.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), banco.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Banco{" +
            "id=" + getId() +
            ", cidadedep='" + getCidadedep() + "'" +
            ", cidadeint='" + getCidadeint() + "'" +
            ", codswifdep='" + getCodswifdep() + "'" +
            ", codswifint='" + getCodswifint() + "'" +
            ", contafeddep='" + isContafeddep() + "'" +
            ", contafedint='" + isContafedint() + "'" +
            ", enderecodep='" + getEnderecodep() + "'" +
            ", enderecoint='" + getEnderecoint() + "'" +
            ", idbanco='" + getIdbanco() + "'" +
            ", instrucoesespeciaisdep='" + getInstrucoesespeciaisdep() + "'" +
            ", instrucoesespeciaisint='" + getInstrucoesespeciaisint() + "'" +
            ", nabadep='" + getNabadep() + "'" +
            ", nabaint='" + getNabaint() + "'" +
            ", ncontadep='" + getNcontadep() + "'" +
            ", ncontaint='" + getNcontaint() + "'" +
            ", nomebancodep='" + getNomebancodep() + "'" +
            ", nomebancoint='" + getNomebancoint() + "'" +
            ", paisdep='" + getPaisdep() + "'" +
            ", paisint='" + getPaisint() + "'" +
            "}";
    }
}
