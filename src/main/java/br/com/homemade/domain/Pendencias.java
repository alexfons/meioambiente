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
 * A Pendencias.
 */
@Entity
@Table(name = "pendencias")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Pendencias implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "data")
    private ZonedDateTime data;

    @Column(name = "datainspecao")
    private ZonedDateTime datainspecao;

    @Column(name = "notificacao")
    private Boolean notificacao;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "obs")
    private String obs;

    @ManyToOne
    private Obra obra;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "pendencias_fotos",
               joinColumns = @JoinColumn(name="pendencias_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="fotos_id", referencedColumnName="id"))
    private Set<Foto> fotos = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "pendencias_ocorrenciaspendencias",
               joinColumns = @JoinColumn(name="pendencias_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="ocorrenciaspendencias_id", referencedColumnName="id"))
    private Set<Ocorrenciapendencias> ocorrenciaspendencias = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getData() {
        return data;
    }

    public Pendencias data(ZonedDateTime data) {
        this.data = data;
        return this;
    }

    public void setData(ZonedDateTime data) {
        this.data = data;
    }

    public ZonedDateTime getDatainspecao() {
        return datainspecao;
    }

    public Pendencias datainspecao(ZonedDateTime datainspecao) {
        this.datainspecao = datainspecao;
        return this;
    }

    public void setDatainspecao(ZonedDateTime datainspecao) {
        this.datainspecao = datainspecao;
    }

    public Boolean isNotificacao() {
        return notificacao;
    }

    public Pendencias notificacao(Boolean notificacao) {
        this.notificacao = notificacao;
        return this;
    }

    public void setNotificacao(Boolean notificacao) {
        this.notificacao = notificacao;
    }

    public Integer getNumero() {
        return numero;
    }

    public Pendencias numero(Integer numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getObs() {
        return obs;
    }

    public Pendencias obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Obra getObra() {
        return obra;
    }

    public Pendencias obra(Obra obra) {
        this.obra = obra;
        return this;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public Set<Foto> getFotos() {
        return fotos;
    }

    public Pendencias fotos(Set<Foto> fotos) {
        this.fotos = fotos;
        return this;
    }

    public Pendencias addFotos(Foto foto) {
        this.fotos.add(foto);
        return this;
    }

    public Pendencias removeFotos(Foto foto) {
        this.fotos.remove(foto);
        return this;
    }

    public void setFotos(Set<Foto> fotos) {
        this.fotos = fotos;
    }

    public Set<Ocorrenciapendencias> getOcorrenciaspendencias() {
        return ocorrenciaspendencias;
    }

    public Pendencias ocorrenciaspendencias(Set<Ocorrenciapendencias> ocorrenciapendencias) {
        this.ocorrenciaspendencias = ocorrenciapendencias;
        return this;
    }

    public Pendencias addOcorrenciaspendencias(Ocorrenciapendencias ocorrenciapendencias) {
        this.ocorrenciaspendencias.add(ocorrenciapendencias);
        return this;
    }

    public Pendencias removeOcorrenciaspendencias(Ocorrenciapendencias ocorrenciapendencias) {
        this.ocorrenciaspendencias.remove(ocorrenciapendencias);
        return this;
    }

    public void setOcorrenciaspendencias(Set<Ocorrenciapendencias> ocorrenciapendencias) {
        this.ocorrenciaspendencias = ocorrenciapendencias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pendencias pendencias = (Pendencias) o;
        if (pendencias.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pendencias.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Pendencias{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            ", datainspecao='" + getDatainspecao() + "'" +
            ", notificacao='" + isNotificacao() + "'" +
            ", numero='" + getNumero() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
