package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Clausulascontratuais.
 */
@Entity
@Table(name = "clausulascontratuais")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Clausulascontratuais implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "artigo")
    private String artigo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "noficioenviado")
    private String noficioenviado;

    @Column(name = "noficioaprovado")
    private String noficioaprovado;

    @Column(name = "dataaprovacao")
    private ZonedDateTime dataaprovacao;

    @Column(name = "dataenvio")
    private ZonedDateTime dataenvio;

    @Column(name = "datavigente")
    private ZonedDateTime datavigente;

    @Column(name = "idclausulascontratuais")
    private Integer idclausulascontratuais;

    @ManyToOne
    private Contratoagente idcontratoagente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArtigo() {
        return artigo;
    }

    public Clausulascontratuais artigo(String artigo) {
        this.artigo = artigo;
        return this;
    }

    public void setArtigo(String artigo) {
        this.artigo = artigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Clausulascontratuais descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNoficioenviado() {
        return noficioenviado;
    }

    public Clausulascontratuais noficioenviado(String noficioenviado) {
        this.noficioenviado = noficioenviado;
        return this;
    }

    public void setNoficioenviado(String noficioenviado) {
        this.noficioenviado = noficioenviado;
    }

    public String getNoficioaprovado() {
        return noficioaprovado;
    }

    public Clausulascontratuais noficioaprovado(String noficioaprovado) {
        this.noficioaprovado = noficioaprovado;
        return this;
    }

    public void setNoficioaprovado(String noficioaprovado) {
        this.noficioaprovado = noficioaprovado;
    }

    public ZonedDateTime getDataaprovacao() {
        return dataaprovacao;
    }

    public Clausulascontratuais dataaprovacao(ZonedDateTime dataaprovacao) {
        this.dataaprovacao = dataaprovacao;
        return this;
    }

    public void setDataaprovacao(ZonedDateTime dataaprovacao) {
        this.dataaprovacao = dataaprovacao;
    }

    public ZonedDateTime getDataenvio() {
        return dataenvio;
    }

    public Clausulascontratuais dataenvio(ZonedDateTime dataenvio) {
        this.dataenvio = dataenvio;
        return this;
    }

    public void setDataenvio(ZonedDateTime dataenvio) {
        this.dataenvio = dataenvio;
    }

    public ZonedDateTime getDatavigente() {
        return datavigente;
    }

    public Clausulascontratuais datavigente(ZonedDateTime datavigente) {
        this.datavigente = datavigente;
        return this;
    }

    public void setDatavigente(ZonedDateTime datavigente) {
        this.datavigente = datavigente;
    }

    public Integer getIdclausulascontratuais() {
        return idclausulascontratuais;
    }

    public Clausulascontratuais idclausulascontratuais(Integer idclausulascontratuais) {
        this.idclausulascontratuais = idclausulascontratuais;
        return this;
    }

    public void setIdclausulascontratuais(Integer idclausulascontratuais) {
        this.idclausulascontratuais = idclausulascontratuais;
    }

    public Contratoagente getIdcontratoagente() {
        return idcontratoagente;
    }

    public Clausulascontratuais idcontratoagente(Contratoagente contratoagente) {
        this.idcontratoagente = contratoagente;
        return this;
    }

    public void setIdcontratoagente(Contratoagente contratoagente) {
        this.idcontratoagente = contratoagente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Clausulascontratuais clausulascontratuais = (Clausulascontratuais) o;
        if (clausulascontratuais.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), clausulascontratuais.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Clausulascontratuais{" +
            "id=" + getId() +
            ", artigo='" + getArtigo() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", noficioenviado='" + getNoficioenviado() + "'" +
            ", noficioaprovado='" + getNoficioaprovado() + "'" +
            ", dataaprovacao='" + getDataaprovacao() + "'" +
            ", dataenvio='" + getDataenvio() + "'" +
            ", datavigente='" + getDatavigente() + "'" +
            ", idclausulascontratuais='" + getIdclausulascontratuais() + "'" +
            "}";
    }
}
