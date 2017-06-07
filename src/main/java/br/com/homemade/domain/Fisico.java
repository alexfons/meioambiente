package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Fisico.
 */
@Entity
@Table(name = "fisico")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Fisico implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "extensao")
    private Double extensao;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "titulo_fim")
    private String tituloFim;

    @Column(name = "titulo_inicio")
    private String tituloInicio;

    @ManyToOne
    private Obra obra;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "fisico_obraatividades",
               joinColumns = @JoinColumn(name="fisicos_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="obraatividades_id", referencedColumnName="id"))
    private Set<Obraatividade> obraatividades = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getExtensao() {
        return extensao;
    }

    public Fisico extensao(Double extensao) {
        this.extensao = extensao;
        return this;
    }

    public void setExtensao(Double extensao) {
        this.extensao = extensao;
    }

    public String getTipo() {
        return tipo;
    }

    public Fisico tipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTituloFim() {
        return tituloFim;
    }

    public Fisico tituloFim(String tituloFim) {
        this.tituloFim = tituloFim;
        return this;
    }

    public void setTituloFim(String tituloFim) {
        this.tituloFim = tituloFim;
    }

    public String getTituloInicio() {
        return tituloInicio;
    }

    public Fisico tituloInicio(String tituloInicio) {
        this.tituloInicio = tituloInicio;
        return this;
    }

    public void setTituloInicio(String tituloInicio) {
        this.tituloInicio = tituloInicio;
    }

    public Obra getObra() {
        return obra;
    }

    public Fisico obra(Obra obra) {
        this.obra = obra;
        return this;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public Set<Obraatividade> getObraatividades() {
        return obraatividades;
    }

    public Fisico obraatividades(Set<Obraatividade> obraatividades) {
        this.obraatividades = obraatividades;
        return this;
    }

    public Fisico addObraatividades(Obraatividade obraatividade) {
        this.obraatividades.add(obraatividade);
        return this;
    }

    public Fisico removeObraatividades(Obraatividade obraatividade) {
        this.obraatividades.remove(obraatividade);
        return this;
    }

    public void setObraatividades(Set<Obraatividade> obraatividades) {
        this.obraatividades = obraatividades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Fisico fisico = (Fisico) o;
        if (fisico.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fisico.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Fisico{" +
            "id=" + getId() +
            ", extensao='" + getExtensao() + "'" +
            ", tipo='" + getTipo() + "'" +
            ", tituloFim='" + getTituloFim() + "'" +
            ", tituloInicio='" + getTituloInicio() + "'" +
            "}";
    }
}
