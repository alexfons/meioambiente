package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Trecho.
 */
@Entity
@Table(name = "trecho")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Trecho implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "defim")
    private String defim;

    @Column(name = "deinicio")
    private String deinicio;

    @Column(name = "fim")
    private String fim;

    @Column(name = "inicio")
    private String inicio;

    @Column(name = "jurisdicao")
    private String jurisdicao;

    @Column(name = "kml")
    private String kml;

    @Column(name = "nuextensao")
    private Double nuextensao;

    @Column(name = "nukmfinal")
    private Double nukmfinal;

    @Column(name = "nukminicia")
    private Double nukminicia;

    @Column(name = "responsavel")
    private String responsavel;

    @Column(name = "sgpre")
    private String sgpre;

    @Column(name = "sgsituacao")
    private String sgsituacao;

    @Column(name = "tprevest")
    private String tprevest;

    @ManyToOne
    private Rodovia rodovia;

    @ManyToOne
    private Supre supre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDefim() {
        return defim;
    }

    public Trecho defim(String defim) {
        this.defim = defim;
        return this;
    }

    public void setDefim(String defim) {
        this.defim = defim;
    }

    public String getDeinicio() {
        return deinicio;
    }

    public Trecho deinicio(String deinicio) {
        this.deinicio = deinicio;
        return this;
    }

    public void setDeinicio(String deinicio) {
        this.deinicio = deinicio;
    }

    public String getFim() {
        return fim;
    }

    public Trecho fim(String fim) {
        this.fim = fim;
        return this;
    }

    public void setFim(String fim) {
        this.fim = fim;
    }

    public String getInicio() {
        return inicio;
    }

    public Trecho inicio(String inicio) {
        this.inicio = inicio;
        return this;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getJurisdicao() {
        return jurisdicao;
    }

    public Trecho jurisdicao(String jurisdicao) {
        this.jurisdicao = jurisdicao;
        return this;
    }

    public void setJurisdicao(String jurisdicao) {
        this.jurisdicao = jurisdicao;
    }

    public String getKml() {
        return kml;
    }

    public Trecho kml(String kml) {
        this.kml = kml;
        return this;
    }

    public void setKml(String kml) {
        this.kml = kml;
    }

    public Double getNuextensao() {
        return nuextensao;
    }

    public Trecho nuextensao(Double nuextensao) {
        this.nuextensao = nuextensao;
        return this;
    }

    public void setNuextensao(Double nuextensao) {
        this.nuextensao = nuextensao;
    }

    public Double getNukmfinal() {
        return nukmfinal;
    }

    public Trecho nukmfinal(Double nukmfinal) {
        this.nukmfinal = nukmfinal;
        return this;
    }

    public void setNukmfinal(Double nukmfinal) {
        this.nukmfinal = nukmfinal;
    }

    public Double getNukminicia() {
        return nukminicia;
    }

    public Trecho nukminicia(Double nukminicia) {
        this.nukminicia = nukminicia;
        return this;
    }

    public void setNukminicia(Double nukminicia) {
        this.nukminicia = nukminicia;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public Trecho responsavel(String responsavel) {
        this.responsavel = responsavel;
        return this;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getSgpre() {
        return sgpre;
    }

    public Trecho sgpre(String sgpre) {
        this.sgpre = sgpre;
        return this;
    }

    public void setSgpre(String sgpre) {
        this.sgpre = sgpre;
    }

    public String getSgsituacao() {
        return sgsituacao;
    }

    public Trecho sgsituacao(String sgsituacao) {
        this.sgsituacao = sgsituacao;
        return this;
    }

    public void setSgsituacao(String sgsituacao) {
        this.sgsituacao = sgsituacao;
    }

    public String getTprevest() {
        return tprevest;
    }

    public Trecho tprevest(String tprevest) {
        this.tprevest = tprevest;
        return this;
    }

    public void setTprevest(String tprevest) {
        this.tprevest = tprevest;
    }

    public Rodovia getRodovia() {
        return rodovia;
    }

    public Trecho rodovia(Rodovia rodovia) {
        this.rodovia = rodovia;
        return this;
    }

    public void setRodovia(Rodovia rodovia) {
        this.rodovia = rodovia;
    }

    public Supre getSupre() {
        return supre;
    }

    public Trecho supre(Supre supre) {
        this.supre = supre;
        return this;
    }

    public void setSupre(Supre supre) {
        this.supre = supre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Trecho trecho = (Trecho) o;
        if (trecho.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), trecho.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Trecho{" +
            "id=" + getId() +
            ", defim='" + getDefim() + "'" +
            ", deinicio='" + getDeinicio() + "'" +
            ", fim='" + getFim() + "'" +
            ", inicio='" + getInicio() + "'" +
            ", jurisdicao='" + getJurisdicao() + "'" +
            ", kml='" + getKml() + "'" +
            ", nuextensao='" + getNuextensao() + "'" +
            ", nukmfinal='" + getNukmfinal() + "'" +
            ", nukminicia='" + getNukminicia() + "'" +
            ", responsavel='" + getResponsavel() + "'" +
            ", sgpre='" + getSgpre() + "'" +
            ", sgsituacao='" + getSgsituacao() + "'" +
            ", tprevest='" + getTprevest() + "'" +
            "}";
    }
}
