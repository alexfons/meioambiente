package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Obra.
 */
@Entity
@Table(name = "obra")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Obra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "pendente")
    private String pendente;

    @Column(name = "map")
    private String map;

    @Column(name = "jhi_user")
    private String user;

    @Column(name = "track")
    private String track;

    @Column(name = "certificado_mes")
    private String certificadoMes;

    @ManyToOne
    private Tipoobra tipoobra;

    @ManyToOne
    private Inspetor inspetor;

    @ManyToOne
    private Fiscal fiscal;

    @ManyToOne
    private Trecho trecho;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "obra_contratosobra",
               joinColumns = @JoinColumn(name="obras_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="contratosobras_id", referencedColumnName="id"))
    private Set<Contratoobra> contratosobras = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "obra_historicos",
               joinColumns = @JoinColumn(name="obras_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="historicos_id", referencedColumnName="id"))
    private Set<Historico> historicos = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPendente() {
        return pendente;
    }

    public Obra pendente(String pendente) {
        this.pendente = pendente;
        return this;
    }

    public void setPendente(String pendente) {
        this.pendente = pendente;
    }

    public String getMap() {
        return map;
    }

    public Obra map(String map) {
        this.map = map;
        return this;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getUser() {
        return user;
    }

    public Obra user(String user) {
        this.user = user;
        return this;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTrack() {
        return track;
    }

    public Obra track(String track) {
        this.track = track;
        return this;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getCertificadoMes() {
        return certificadoMes;
    }

    public Obra certificadoMes(String certificadoMes) {
        this.certificadoMes = certificadoMes;
        return this;
    }

    public void setCertificadoMes(String certificadoMes) {
        this.certificadoMes = certificadoMes;
    }

    public Tipoobra getTipoobra() {
        return tipoobra;
    }

    public Obra tipoobra(Tipoobra tipoobra) {
        this.tipoobra = tipoobra;
        return this;
    }

    public void setTipoobra(Tipoobra tipoobra) {
        this.tipoobra = tipoobra;
    }

    public Inspetor getInspetor() {
        return inspetor;
    }

    public Obra inspetor(Inspetor inspetor) {
        this.inspetor = inspetor;
        return this;
    }

    public void setInspetor(Inspetor inspetor) {
        this.inspetor = inspetor;
    }

    public Fiscal getFiscal() {
        return fiscal;
    }

    public Obra fiscal(Fiscal fiscal) {
        this.fiscal = fiscal;
        return this;
    }

    public void setFiscal(Fiscal fiscal) {
        this.fiscal = fiscal;
    }

    public Trecho getTrecho() {
        return trecho;
    }

    public Obra trecho(Trecho trecho) {
        this.trecho = trecho;
        return this;
    }

    public void setTrecho(Trecho trecho) {
        this.trecho = trecho;
    }

    public Set<Contratoobra> getContratosobras() {
        return contratosobras;
    }

    public Obra contratosobras(Set<Contratoobra> contratoobras) {
        this.contratosobras = contratoobras;
        return this;
    }

    public Obra addContratosobra(Contratoobra contratoobra) {
        this.contratosobras.add(contratoobra);
        return this;
    }

    public Obra removeContratosobra(Contratoobra contratoobra) {
        this.contratosobras.remove(contratoobra);
        return this;
    }

    public void setContratosobras(Set<Contratoobra> contratoobras) {
        this.contratosobras = contratoobras;
    }

    public Set<Historico> getHistoricos() {
        return historicos;
    }

    public Obra historicos(Set<Historico> historicos) {
        this.historicos = historicos;
        return this;
    }

    public Obra addHistoricos(Historico historico) {
        this.historicos.add(historico);
        return this;
    }

    public Obra removeHistoricos(Historico historico) {
        this.historicos.remove(historico);
        return this;
    }

    public void setHistoricos(Set<Historico> historicos) {
        this.historicos = historicos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Obra obra = (Obra) o;
        if (obra.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), obra.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Obra{" +
            "id=" + getId() +
            ", pendente='" + getPendente() + "'" +
            ", map='" + getMap() + "'" +
            ", user='" + getUser() + "'" +
            ", track='" + getTrack() + "'" +
            ", certificadoMes='" + getCertificadoMes() + "'" +
            "}";
    }
}
