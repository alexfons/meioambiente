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
 * A Apresentacao.
 */
@Entity
@Table(name = "apresentacao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Apresentacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "obs")
    private String obs;

    @Column(name = "notificacao")
    private Boolean notificacao;

    @Column(name = "data")
    private ZonedDateTime data;

    @ManyToOne
    private Obra obra;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "apresentacao_ocorrenciasapresentacao",
               joinColumns = @JoinColumn(name="apresentacaos_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="ocorrenciasapresentacaos_id", referencedColumnName="id"))
    private Set<Ocorrenciaapresentacao> ocorrenciasapresentacaos = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "apresentacao_fotos",
               joinColumns = @JoinColumn(name="apresentacaos_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="fotos_id", referencedColumnName="id"))
    private Set<Foto> fotos = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public Apresentacao numero(Integer numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getObs() {
        return obs;
    }

    public Apresentacao obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Boolean isNotificacao() {
        return notificacao;
    }

    public Apresentacao notificacao(Boolean notificacao) {
        this.notificacao = notificacao;
        return this;
    }

    public void setNotificacao(Boolean notificacao) {
        this.notificacao = notificacao;
    }

    public ZonedDateTime getData() {
        return data;
    }

    public Apresentacao data(ZonedDateTime data) {
        this.data = data;
        return this;
    }

    public void setData(ZonedDateTime data) {
        this.data = data;
    }

    public Obra getObra() {
        return obra;
    }

    public Apresentacao obra(Obra obra) {
        this.obra = obra;
        return this;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public Set<Ocorrenciaapresentacao> getOcorrenciasapresentacaos() {
        return ocorrenciasapresentacaos;
    }

    public Apresentacao ocorrenciasapresentacaos(Set<Ocorrenciaapresentacao> ocorrenciaapresentacaos) {
        this.ocorrenciasapresentacaos = ocorrenciaapresentacaos;
        return this;
    }

    public Apresentacao addOcorrenciasapresentacao(Ocorrenciaapresentacao ocorrenciaapresentacao) {
        this.ocorrenciasapresentacaos.add(ocorrenciaapresentacao);
        return this;
    }

    public Apresentacao removeOcorrenciasapresentacao(Ocorrenciaapresentacao ocorrenciaapresentacao) {
        this.ocorrenciasapresentacaos.remove(ocorrenciaapresentacao);
        return this;
    }

    public void setOcorrenciasapresentacaos(Set<Ocorrenciaapresentacao> ocorrenciaapresentacaos) {
        this.ocorrenciasapresentacaos = ocorrenciaapresentacaos;
    }

    public Set<Foto> getFotos() {
        return fotos;
    }

    public Apresentacao fotos(Set<Foto> fotos) {
        this.fotos = fotos;
        return this;
    }

    public Apresentacao addFotos(Foto foto) {
        this.fotos.add(foto);
        return this;
    }

    public Apresentacao removeFotos(Foto foto) {
        this.fotos.remove(foto);
        return this;
    }

    public void setFotos(Set<Foto> fotos) {
        this.fotos = fotos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Apresentacao apresentacao = (Apresentacao) o;
        if (apresentacao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), apresentacao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Apresentacao{" +
            "id=" + getId() +
            ", numero='" + getNumero() + "'" +
            ", obs='" + getObs() + "'" +
            ", notificacao='" + isNotificacao() + "'" +
            ", data='" + getData() + "'" +
            "}";
    }
}
