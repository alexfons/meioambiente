package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Obrafisicomensal.
 */
@Entity
@Table(name = "obrafisicomensal")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Obrafisicomensal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "datainspecao")
    private ZonedDateTime datainspecao;

    @Column(name = "datarelatorio")
    private ZonedDateTime datarelatorio;

    @Column(name = "pessoal")
    private String pessoal;

    @Column(name = "equipamento")
    private String equipamento;

    @Column(name = "instalacaoapoio")
    private String instalacaoapoio;

    @Column(name = "ritmo")
    private String ritmo;

    @Column(name = "apresentacao")
    private String apresentacao;

    @Column(name = "qualidadeservicos")
    private String qualidadeservicos;

    @Column(name = "cronograma")
    private String cronograma;

    @Column(name = "prazodecorrido")
    private Integer prazodecorrido;

    @Column(name = "avancofisico_oae")
    private Integer avancofisicoOAE;

    @Column(name = "avancofisicoponderado")
    private Integer avancofisicoponderado;

    @Column(name = "previsaoatual")
    private ZonedDateTime previsaoatual;

    @ManyToOne
    private Fisico fisico;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "obrafisicomensal_atividadeexecutadamensals",
               joinColumns = @JoinColumn(name="obrafisicomensals_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="atividadeexecutadamensals_id", referencedColumnName="id"))
    private Set<Atividadeexecutadamensal> atividadeexecutadamensals = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public Obrafisicomensal comentario(String comentario) {
        this.comentario = comentario;
        return this;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public ZonedDateTime getDatainspecao() {
        return datainspecao;
    }

    public Obrafisicomensal datainspecao(ZonedDateTime datainspecao) {
        this.datainspecao = datainspecao;
        return this;
    }

    public void setDatainspecao(ZonedDateTime datainspecao) {
        this.datainspecao = datainspecao;
    }

    public ZonedDateTime getDatarelatorio() {
        return datarelatorio;
    }

    public Obrafisicomensal datarelatorio(ZonedDateTime datarelatorio) {
        this.datarelatorio = datarelatorio;
        return this;
    }

    public void setDatarelatorio(ZonedDateTime datarelatorio) {
        this.datarelatorio = datarelatorio;
    }

    public String getPessoal() {
        return pessoal;
    }

    public Obrafisicomensal pessoal(String pessoal) {
        this.pessoal = pessoal;
        return this;
    }

    public void setPessoal(String pessoal) {
        this.pessoal = pessoal;
    }

    public String getEquipamento() {
        return equipamento;
    }

    public Obrafisicomensal equipamento(String equipamento) {
        this.equipamento = equipamento;
        return this;
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }

    public String getInstalacaoapoio() {
        return instalacaoapoio;
    }

    public Obrafisicomensal instalacaoapoio(String instalacaoapoio) {
        this.instalacaoapoio = instalacaoapoio;
        return this;
    }

    public void setInstalacaoapoio(String instalacaoapoio) {
        this.instalacaoapoio = instalacaoapoio;
    }

    public String getRitmo() {
        return ritmo;
    }

    public Obrafisicomensal ritmo(String ritmo) {
        this.ritmo = ritmo;
        return this;
    }

    public void setRitmo(String ritmo) {
        this.ritmo = ritmo;
    }

    public String getApresentacao() {
        return apresentacao;
    }

    public Obrafisicomensal apresentacao(String apresentacao) {
        this.apresentacao = apresentacao;
        return this;
    }

    public void setApresentacao(String apresentacao) {
        this.apresentacao = apresentacao;
    }

    public String getQualidadeservicos() {
        return qualidadeservicos;
    }

    public Obrafisicomensal qualidadeservicos(String qualidadeservicos) {
        this.qualidadeservicos = qualidadeservicos;
        return this;
    }

    public void setQualidadeservicos(String qualidadeservicos) {
        this.qualidadeservicos = qualidadeservicos;
    }

    public String getCronograma() {
        return cronograma;
    }

    public Obrafisicomensal cronograma(String cronograma) {
        this.cronograma = cronograma;
        return this;
    }

    public void setCronograma(String cronograma) {
        this.cronograma = cronograma;
    }

    public Integer getPrazodecorrido() {
        return prazodecorrido;
    }

    public Obrafisicomensal prazodecorrido(Integer prazodecorrido) {
        this.prazodecorrido = prazodecorrido;
        return this;
    }

    public void setPrazodecorrido(Integer prazodecorrido) {
        this.prazodecorrido = prazodecorrido;
    }

    public Integer getAvancofisicoOAE() {
        return avancofisicoOAE;
    }

    public Obrafisicomensal avancofisicoOAE(Integer avancofisicoOAE) {
        this.avancofisicoOAE = avancofisicoOAE;
        return this;
    }

    public void setAvancofisicoOAE(Integer avancofisicoOAE) {
        this.avancofisicoOAE = avancofisicoOAE;
    }

    public Integer getAvancofisicoponderado() {
        return avancofisicoponderado;
    }

    public Obrafisicomensal avancofisicoponderado(Integer avancofisicoponderado) {
        this.avancofisicoponderado = avancofisicoponderado;
        return this;
    }

    public void setAvancofisicoponderado(Integer avancofisicoponderado) {
        this.avancofisicoponderado = avancofisicoponderado;
    }

    public ZonedDateTime getPrevisaoatual() {
        return previsaoatual;
    }

    public Obrafisicomensal previsaoatual(ZonedDateTime previsaoatual) {
        this.previsaoatual = previsaoatual;
        return this;
    }

    public void setPrevisaoatual(ZonedDateTime previsaoatual) {
        this.previsaoatual = previsaoatual;
    }

    public Fisico getFisico() {
        return fisico;
    }

    public Obrafisicomensal fisico(Fisico fisico) {
        this.fisico = fisico;
        return this;
    }

    public void setFisico(Fisico fisico) {
        this.fisico = fisico;
    }

    public Set<Atividadeexecutadamensal> getAtividadeexecutadamensals() {
        return atividadeexecutadamensals;
    }

    public Obrafisicomensal atividadeexecutadamensals(Set<Atividadeexecutadamensal> atividadeexecutadamensals) {
        this.atividadeexecutadamensals = atividadeexecutadamensals;
        return this;
    }

    public Obrafisicomensal addAtividadeexecutadamensals(Atividadeexecutadamensal atividadeexecutadamensal) {
        this.atividadeexecutadamensals.add(atividadeexecutadamensal);
        return this;
    }

    public Obrafisicomensal removeAtividadeexecutadamensals(Atividadeexecutadamensal atividadeexecutadamensal) {
        this.atividadeexecutadamensals.remove(atividadeexecutadamensal);
        return this;
    }

    public void setAtividadeexecutadamensals(Set<Atividadeexecutadamensal> atividadeexecutadamensals) {
        this.atividadeexecutadamensals = atividadeexecutadamensals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Obrafisicomensal obrafisicomensal = (Obrafisicomensal) o;
        if (obrafisicomensal.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), obrafisicomensal.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Obrafisicomensal{" +
            "id=" + getId() +
            ", comentario='" + getComentario() + "'" +
            ", datainspecao='" + getDatainspecao() + "'" +
            ", datarelatorio='" + getDatarelatorio() + "'" +
            ", pessoal='" + getPessoal() + "'" +
            ", equipamento='" + getEquipamento() + "'" +
            ", instalacaoapoio='" + getInstalacaoapoio() + "'" +
            ", ritmo='" + getRitmo() + "'" +
            ", apresentacao='" + getApresentacao() + "'" +
            ", qualidadeservicos='" + getQualidadeservicos() + "'" +
            ", cronograma='" + getCronograma() + "'" +
            ", prazodecorrido='" + getPrazodecorrido() + "'" +
            ", avancofisicoOAE='" + getAvancofisicoOAE() + "'" +
            ", avancofisicoponderado='" + getAvancofisicoponderado() + "'" +
            ", previsaoatual='" + getPrevisaoatual() + "'" +
            "}";
    }
}
