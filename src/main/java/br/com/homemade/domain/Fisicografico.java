package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Fisicografico.
 */
@Entity
@Table(name = "fisicografico")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Fisicografico implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "atacada")
    private Boolean atacada;

    @Column(name = "extensao")
    private Double extensao;

    @Column(name = "fim")
    private Double fim;

    @Column(name = "inicio")
    private Double inicio;

    @Column(name = "npontos")
    private Integer npontos;

    @Column(name = "pontofim")
    private Integer pontofim;

    @Column(name = "pontoinicio")
    private Integer pontoinicio;

    @ManyToOne
    private Atividade atividade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isAtacada() {
        return atacada;
    }

    public Fisicografico atacada(Boolean atacada) {
        this.atacada = atacada;
        return this;
    }

    public void setAtacada(Boolean atacada) {
        this.atacada = atacada;
    }

    public Double getExtensao() {
        return extensao;
    }

    public Fisicografico extensao(Double extensao) {
        this.extensao = extensao;
        return this;
    }

    public void setExtensao(Double extensao) {
        this.extensao = extensao;
    }

    public Double getFim() {
        return fim;
    }

    public Fisicografico fim(Double fim) {
        this.fim = fim;
        return this;
    }

    public void setFim(Double fim) {
        this.fim = fim;
    }

    public Double getInicio() {
        return inicio;
    }

    public Fisicografico inicio(Double inicio) {
        this.inicio = inicio;
        return this;
    }

    public void setInicio(Double inicio) {
        this.inicio = inicio;
    }

    public Integer getNpontos() {
        return npontos;
    }

    public Fisicografico npontos(Integer npontos) {
        this.npontos = npontos;
        return this;
    }

    public void setNpontos(Integer npontos) {
        this.npontos = npontos;
    }

    public Integer getPontofim() {
        return pontofim;
    }

    public Fisicografico pontofim(Integer pontofim) {
        this.pontofim = pontofim;
        return this;
    }

    public void setPontofim(Integer pontofim) {
        this.pontofim = pontofim;
    }

    public Integer getPontoinicio() {
        return pontoinicio;
    }

    public Fisicografico pontoinicio(Integer pontoinicio) {
        this.pontoinicio = pontoinicio;
        return this;
    }

    public void setPontoinicio(Integer pontoinicio) {
        this.pontoinicio = pontoinicio;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public Fisicografico atividade(Atividade atividade) {
        this.atividade = atividade;
        return this;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Fisicografico fisicografico = (Fisicografico) o;
        if (fisicografico.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fisicografico.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Fisicografico{" +
            "id=" + getId() +
            ", atacada='" + isAtacada() + "'" +
            ", extensao='" + getExtensao() + "'" +
            ", fim='" + getFim() + "'" +
            ", inicio='" + getInicio() + "'" +
            ", npontos='" + getNpontos() + "'" +
            ", pontofim='" + getPontofim() + "'" +
            ", pontoinicio='" + getPontoinicio() + "'" +
            "}";
    }
}
