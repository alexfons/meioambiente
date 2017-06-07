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
 * A Ocorrencia.
 */
@Entity
@Table(name = "ocorrencia")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Ocorrencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "album")
    private String album;

    @Column(name = "caracterizacao")
    private String caracterizacao;

    @Column(name = "coordenadaa")
    private String coordenadaa;

    @Column(name = "coordenadae")
    private String coordenadae;

    @Column(name = "coordenadan")
    private String coordenadan;

    @Column(name = "data")
    private ZonedDateTime data;

    @Column(name = "distanciaeixo")
    private Float distanciaeixo;

    @Column(name = "feature")
    private String feature;

    @Column(name = "kmfim")
    private Integer kmfim;

    @Column(name = "kminicio")
    private Integer kminicio;

    @Column(name = "lado")
    private String lado;

    @Column(name = "numero")
    private Integer numero;

    @ManyToOne
    private Atividade atividade;

    @ManyToOne
    private Obra obra;

    @ManyToOne
    private Tabela servico;

    @ManyToOne
    private Tabela tabela;

    @ManyToOne
    private Tipoocorrencia tipo;

    @ManyToOne
    private Tipoocorrencia tipoocorrencia;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "ocorrencia_fotos",
               joinColumns = @JoinColumn(name="ocorrencias_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="fotos_id", referencedColumnName="id"))
    private Set<Foto> fotos = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "ocorrencia_historicos",
               joinColumns = @JoinColumn(name="ocorrencias_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="historicos_id", referencedColumnName="id"))
    private Set<Historico> historicos = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "ocorrencia_registros",
               joinColumns = @JoinColumn(name="ocorrencias_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="registros_id", referencedColumnName="id"))
    private Set<Registro> registros = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlbum() {
        return album;
    }

    public Ocorrencia album(String album) {
        this.album = album;
        return this;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getCaracterizacao() {
        return caracterizacao;
    }

    public Ocorrencia caracterizacao(String caracterizacao) {
        this.caracterizacao = caracterizacao;
        return this;
    }

    public void setCaracterizacao(String caracterizacao) {
        this.caracterizacao = caracterizacao;
    }

    public String getCoordenadaa() {
        return coordenadaa;
    }

    public Ocorrencia coordenadaa(String coordenadaa) {
        this.coordenadaa = coordenadaa;
        return this;
    }

    public void setCoordenadaa(String coordenadaa) {
        this.coordenadaa = coordenadaa;
    }

    public String getCoordenadae() {
        return coordenadae;
    }

    public Ocorrencia coordenadae(String coordenadae) {
        this.coordenadae = coordenadae;
        return this;
    }

    public void setCoordenadae(String coordenadae) {
        this.coordenadae = coordenadae;
    }

    public String getCoordenadan() {
        return coordenadan;
    }

    public Ocorrencia coordenadan(String coordenadan) {
        this.coordenadan = coordenadan;
        return this;
    }

    public void setCoordenadan(String coordenadan) {
        this.coordenadan = coordenadan;
    }

    public ZonedDateTime getData() {
        return data;
    }

    public Ocorrencia data(ZonedDateTime data) {
        this.data = data;
        return this;
    }

    public void setData(ZonedDateTime data) {
        this.data = data;
    }

    public Float getDistanciaeixo() {
        return distanciaeixo;
    }

    public Ocorrencia distanciaeixo(Float distanciaeixo) {
        this.distanciaeixo = distanciaeixo;
        return this;
    }

    public void setDistanciaeixo(Float distanciaeixo) {
        this.distanciaeixo = distanciaeixo;
    }

    public String getFeature() {
        return feature;
    }

    public Ocorrencia feature(String feature) {
        this.feature = feature;
        return this;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public Integer getKmfim() {
        return kmfim;
    }

    public Ocorrencia kmfim(Integer kmfim) {
        this.kmfim = kmfim;
        return this;
    }

    public void setKmfim(Integer kmfim) {
        this.kmfim = kmfim;
    }

    public Integer getKminicio() {
        return kminicio;
    }

    public Ocorrencia kminicio(Integer kminicio) {
        this.kminicio = kminicio;
        return this;
    }

    public void setKminicio(Integer kminicio) {
        this.kminicio = kminicio;
    }

    public String getLado() {
        return lado;
    }

    public Ocorrencia lado(String lado) {
        this.lado = lado;
        return this;
    }

    public void setLado(String lado) {
        this.lado = lado;
    }

    public Integer getNumero() {
        return numero;
    }

    public Ocorrencia numero(Integer numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public Ocorrencia atividade(Atividade atividade) {
        this.atividade = atividade;
        return this;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    public Obra getObra() {
        return obra;
    }

    public Ocorrencia obra(Obra obra) {
        this.obra = obra;
        return this;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public Tabela getServico() {
        return servico;
    }

    public Ocorrencia servico(Tabela tabela) {
        this.servico = tabela;
        return this;
    }

    public void setServico(Tabela tabela) {
        this.servico = tabela;
    }

    public Tabela getTabela() {
        return tabela;
    }

    public Ocorrencia tabela(Tabela tabela) {
        this.tabela = tabela;
        return this;
    }

    public void setTabela(Tabela tabela) {
        this.tabela = tabela;
    }

    public Tipoocorrencia getTipo() {
        return tipo;
    }

    public Ocorrencia tipo(Tipoocorrencia tipoocorrencia) {
        this.tipo = tipoocorrencia;
        return this;
    }

    public void setTipo(Tipoocorrencia tipoocorrencia) {
        this.tipo = tipoocorrencia;
    }

    public Tipoocorrencia getTipoocorrencia() {
        return tipoocorrencia;
    }

    public Ocorrencia tipoocorrencia(Tipoocorrencia tipoocorrencia) {
        this.tipoocorrencia = tipoocorrencia;
        return this;
    }

    public void setTipoocorrencia(Tipoocorrencia tipoocorrencia) {
        this.tipoocorrencia = tipoocorrencia;
    }

    public Set<Foto> getFotos() {
        return fotos;
    }

    public Ocorrencia fotos(Set<Foto> fotos) {
        this.fotos = fotos;
        return this;
    }

    public Ocorrencia addFotos(Foto foto) {
        this.fotos.add(foto);
        return this;
    }

    public Ocorrencia removeFotos(Foto foto) {
        this.fotos.remove(foto);
        return this;
    }

    public void setFotos(Set<Foto> fotos) {
        this.fotos = fotos;
    }

    public Set<Historico> getHistoricos() {
        return historicos;
    }

    public Ocorrencia historicos(Set<Historico> historicos) {
        this.historicos = historicos;
        return this;
    }

    public Ocorrencia addHistoricos(Historico historico) {
        this.historicos.add(historico);
        return this;
    }

    public Ocorrencia removeHistoricos(Historico historico) {
        this.historicos.remove(historico);
        return this;
    }

    public void setHistoricos(Set<Historico> historicos) {
        this.historicos = historicos;
    }

    public Set<Registro> getRegistros() {
        return registros;
    }

    public Ocorrencia registros(Set<Registro> registros) {
        this.registros = registros;
        return this;
    }

    public Ocorrencia addRegistros(Registro registro) {
        this.registros.add(registro);
        return this;
    }

    public Ocorrencia removeRegistros(Registro registro) {
        this.registros.remove(registro);
        return this;
    }

    public void setRegistros(Set<Registro> registros) {
        this.registros = registros;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ocorrencia ocorrencia = (Ocorrencia) o;
        if (ocorrencia.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ocorrencia.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Ocorrencia{" +
            "id=" + getId() +
            ", album='" + getAlbum() + "'" +
            ", caracterizacao='" + getCaracterizacao() + "'" +
            ", coordenadaa='" + getCoordenadaa() + "'" +
            ", coordenadae='" + getCoordenadae() + "'" +
            ", coordenadan='" + getCoordenadan() + "'" +
            ", data='" + getData() + "'" +
            ", distanciaeixo='" + getDistanciaeixo() + "'" +
            ", feature='" + getFeature() + "'" +
            ", kmfim='" + getKmfim() + "'" +
            ", kminicio='" + getKminicio() + "'" +
            ", lado='" + getLado() + "'" +
            ", numero='" + getNumero() + "'" +
            "}";
    }
}
