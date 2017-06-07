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
 * A Notificacao.
 */
@Entity
@Table(name = "notificacao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Notificacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "data")
    private ZonedDateTime data;

    @Column(name = "datainspecao")
    private ZonedDateTime datainspecao;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "obs")
    private String obs;

    @ManyToOne
    private Obra obra;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "notificacao_fotos",
               joinColumns = @JoinColumn(name="notificacaos_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="fotos_id", referencedColumnName="id"))
    private Set<Foto> fotos = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "notificacao_ocorrenciasnotificacao",
               joinColumns = @JoinColumn(name="notificacaos_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="ocorrenciasnotificacaos_id", referencedColumnName="id"))
    private Set<Ocorrencianotificacao> ocorrenciasnotificacaos = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getData() {
        return data;
    }

    public Notificacao data(ZonedDateTime data) {
        this.data = data;
        return this;
    }

    public void setData(ZonedDateTime data) {
        this.data = data;
    }

    public ZonedDateTime getDatainspecao() {
        return datainspecao;
    }

    public Notificacao datainspecao(ZonedDateTime datainspecao) {
        this.datainspecao = datainspecao;
        return this;
    }

    public void setDatainspecao(ZonedDateTime datainspecao) {
        this.datainspecao = datainspecao;
    }

    public Integer getNumero() {
        return numero;
    }

    public Notificacao numero(Integer numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getObs() {
        return obs;
    }

    public Notificacao obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Obra getObra() {
        return obra;
    }

    public Notificacao obra(Obra obra) {
        this.obra = obra;
        return this;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public Set<Foto> getFotos() {
        return fotos;
    }

    public Notificacao fotos(Set<Foto> fotos) {
        this.fotos = fotos;
        return this;
    }

    public Notificacao addFotos(Foto foto) {
        this.fotos.add(foto);
        return this;
    }

    public Notificacao removeFotos(Foto foto) {
        this.fotos.remove(foto);
        return this;
    }

    public void setFotos(Set<Foto> fotos) {
        this.fotos = fotos;
    }

    public Set<Ocorrencianotificacao> getOcorrenciasnotificacaos() {
        return ocorrenciasnotificacaos;
    }

    public Notificacao ocorrenciasnotificacaos(Set<Ocorrencianotificacao> ocorrencianotificacaos) {
        this.ocorrenciasnotificacaos = ocorrencianotificacaos;
        return this;
    }

    public Notificacao addOcorrenciasnotificacao(Ocorrencianotificacao ocorrencianotificacao) {
        this.ocorrenciasnotificacaos.add(ocorrencianotificacao);
        return this;
    }

    public Notificacao removeOcorrenciasnotificacao(Ocorrencianotificacao ocorrencianotificacao) {
        this.ocorrenciasnotificacaos.remove(ocorrencianotificacao);
        return this;
    }

    public void setOcorrenciasnotificacaos(Set<Ocorrencianotificacao> ocorrencianotificacaos) {
        this.ocorrenciasnotificacaos = ocorrencianotificacaos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Notificacao notificacao = (Notificacao) o;
        if (notificacao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), notificacao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Notificacao{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            ", datainspecao='" + getDatainspecao() + "'" +
            ", numero='" + getNumero() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
