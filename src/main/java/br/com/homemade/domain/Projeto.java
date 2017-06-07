package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Projeto.
 */
@Entity
@Table(name = "projeto")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Projeto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "andamento")
    private String andamento;

    @Column(name = "pendente")
    private String pendente;

    @ManyToOne
    private Inspetor inspetor;

    @ManyToOne
    private Municipio municipio;

    @ManyToOne
    private Tipoobra tipo;

    @ManyToOne
    private Tipoobra tipoobra;

    @ManyToOne
    private Trecho trecho;

    @ManyToOne
    private Fiscal fiscal;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "projeto_contratosprojeto",
               joinColumns = @JoinColumn(name="projetos_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="contratosprojetos_id", referencedColumnName="id"))
    private Set<Contratoprojeto> contratosprojetos = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "projeto_historicos",
               joinColumns = @JoinColumn(name="projetos_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="historicos_id", referencedColumnName="id"))
    private Set<Historico> historicos = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "projeto_municipios",
               joinColumns = @JoinColumn(name="projetos_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="municipios_id", referencedColumnName="id"))
    private Set<Municipio> municipios = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAndamento() {
        return andamento;
    }

    public Projeto andamento(String andamento) {
        this.andamento = andamento;
        return this;
    }

    public void setAndamento(String andamento) {
        this.andamento = andamento;
    }

    public String getPendente() {
        return pendente;
    }

    public Projeto pendente(String pendente) {
        this.pendente = pendente;
        return this;
    }

    public void setPendente(String pendente) {
        this.pendente = pendente;
    }

    public Inspetor getInspetor() {
        return inspetor;
    }

    public Projeto inspetor(Inspetor inspetor) {
        this.inspetor = inspetor;
        return this;
    }

    public void setInspetor(Inspetor inspetor) {
        this.inspetor = inspetor;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public Projeto municipio(Municipio municipio) {
        this.municipio = municipio;
        return this;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Tipoobra getTipo() {
        return tipo;
    }

    public Projeto tipo(Tipoobra tipoobra) {
        this.tipo = tipoobra;
        return this;
    }

    public void setTipo(Tipoobra tipoobra) {
        this.tipo = tipoobra;
    }

    public Tipoobra getTipoobra() {
        return tipoobra;
    }

    public Projeto tipoobra(Tipoobra tipoobra) {
        this.tipoobra = tipoobra;
        return this;
    }

    public void setTipoobra(Tipoobra tipoobra) {
        this.tipoobra = tipoobra;
    }

    public Trecho getTrecho() {
        return trecho;
    }

    public Projeto trecho(Trecho trecho) {
        this.trecho = trecho;
        return this;
    }

    public void setTrecho(Trecho trecho) {
        this.trecho = trecho;
    }

    public Fiscal getFiscal() {
        return fiscal;
    }

    public Projeto fiscal(Fiscal fiscal) {
        this.fiscal = fiscal;
        return this;
    }

    public void setFiscal(Fiscal fiscal) {
        this.fiscal = fiscal;
    }

    public Set<Contratoprojeto> getContratosprojetos() {
        return contratosprojetos;
    }

    public Projeto contratosprojetos(Set<Contratoprojeto> contratoprojetos) {
        this.contratosprojetos = contratoprojetos;
        return this;
    }

    public Projeto addContratosprojeto(Contratoprojeto contratoprojeto) {
        this.contratosprojetos.add(contratoprojeto);
        return this;
    }

    public Projeto removeContratosprojeto(Contratoprojeto contratoprojeto) {
        this.contratosprojetos.remove(contratoprojeto);
        return this;
    }

    public void setContratosprojetos(Set<Contratoprojeto> contratoprojetos) {
        this.contratosprojetos = contratoprojetos;
    }

    public Set<Historico> getHistoricos() {
        return historicos;
    }

    public Projeto historicos(Set<Historico> historicos) {
        this.historicos = historicos;
        return this;
    }

    public Projeto addHistoricos(Historico historico) {
        this.historicos.add(historico);
        return this;
    }

    public Projeto removeHistoricos(Historico historico) {
        this.historicos.remove(historico);
        return this;
    }

    public void setHistoricos(Set<Historico> historicos) {
        this.historicos = historicos;
    }

    public Set<Municipio> getMunicipios() {
        return municipios;
    }

    public Projeto municipios(Set<Municipio> municipios) {
        this.municipios = municipios;
        return this;
    }

    public Projeto addMunicipios(Municipio municipio) {
        this.municipios.add(municipio);
        return this;
    }

    public Projeto removeMunicipios(Municipio municipio) {
        this.municipios.remove(municipio);
        return this;
    }

    public void setMunicipios(Set<Municipio> municipios) {
        this.municipios = municipios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Projeto projeto = (Projeto) o;
        if (projeto.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), projeto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Projeto{" +
            "id=" + getId() +
            ", andamento='" + getAndamento() + "'" +
            ", pendente='" + getPendente() + "'" +
            "}";
    }
}
