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
 * A Informe.
 */
@Entity
@Table(name = "informe")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Informe implements Serializable {

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
    @JoinTable(name = "informe_fotos",
               joinColumns = @JoinColumn(name="informes_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="fotos_id", referencedColumnName="id"))
    private Set<Foto> fotos = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "informe_ocorrenciasinforme",
               joinColumns = @JoinColumn(name="informes_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="ocorrenciasinformes_id", referencedColumnName="id"))
    private Set<Ocorrenciainforme> ocorrenciasinformes = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getData() {
        return data;
    }

    public Informe data(ZonedDateTime data) {
        this.data = data;
        return this;
    }

    public void setData(ZonedDateTime data) {
        this.data = data;
    }

    public ZonedDateTime getDatainspecao() {
        return datainspecao;
    }

    public Informe datainspecao(ZonedDateTime datainspecao) {
        this.datainspecao = datainspecao;
        return this;
    }

    public void setDatainspecao(ZonedDateTime datainspecao) {
        this.datainspecao = datainspecao;
    }

    public Boolean isNotificacao() {
        return notificacao;
    }

    public Informe notificacao(Boolean notificacao) {
        this.notificacao = notificacao;
        return this;
    }

    public void setNotificacao(Boolean notificacao) {
        this.notificacao = notificacao;
    }

    public Integer getNumero() {
        return numero;
    }

    public Informe numero(Integer numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getObs() {
        return obs;
    }

    public Informe obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Obra getObra() {
        return obra;
    }

    public Informe obra(Obra obra) {
        this.obra = obra;
        return this;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public Set<Foto> getFotos() {
        return fotos;
    }

    public Informe fotos(Set<Foto> fotos) {
        this.fotos = fotos;
        return this;
    }

    public Informe addFotos(Foto foto) {
        this.fotos.add(foto);
        return this;
    }

    public Informe removeFotos(Foto foto) {
        this.fotos.remove(foto);
        return this;
    }

    public void setFotos(Set<Foto> fotos) {
        this.fotos = fotos;
    }

    public Set<Ocorrenciainforme> getOcorrenciasinformes() {
        return ocorrenciasinformes;
    }

    public Informe ocorrenciasinformes(Set<Ocorrenciainforme> ocorrenciainformes) {
        this.ocorrenciasinformes = ocorrenciainformes;
        return this;
    }

    public Informe addOcorrenciasinforme(Ocorrenciainforme ocorrenciainforme) {
        this.ocorrenciasinformes.add(ocorrenciainforme);
        return this;
    }

    public Informe removeOcorrenciasinforme(Ocorrenciainforme ocorrenciainforme) {
        this.ocorrenciasinformes.remove(ocorrenciainforme);
        return this;
    }

    public void setOcorrenciasinformes(Set<Ocorrenciainforme> ocorrenciainformes) {
        this.ocorrenciasinformes = ocorrenciainformes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Informe informe = (Informe) o;
        if (informe.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), informe.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Informe{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            ", datainspecao='" + getDatainspecao() + "'" +
            ", notificacao='" + isNotificacao() + "'" +
            ", numero='" + getNumero() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
