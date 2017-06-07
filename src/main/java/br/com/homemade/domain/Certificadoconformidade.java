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
 * A Certificadoconformidade.
 */
@Entity
@Table(name = "certificadoconformidade")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Certificadoconformidade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "data")
    private ZonedDateTime data;

    @Column(name = "dataliberacao")
    private ZonedDateTime dataliberacao;

    @Column(name = "dataparalisacao")
    private ZonedDateTime dataparalisacao;

    @Column(name = "datareinicio")
    private ZonedDateTime datareinicio;

    @Column(name = "edital")
    private String edital;

    @Column(name = "item")
    private String item;

    @Column(name = "liberacao")
    private String liberacao;

    @Column(name = "liberacaoadministrativamente")
    private Boolean liberacaoadministrativamente;

    @Column(name = "periodo")
    private String periodo;

    @Column(name = "texto")
    private String texto;

    @Column(name = "texto_2")
    private String texto2;

    @ManyToOne
    private Obra obra;

    @ManyToOne
    private Tipocertificadoconformidade tipo;

    @ManyToOne
    private Tipocertificadoconformidade tipocertificadoconformidade;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "certificadoconformidade_informescertificadoirregularidade",
               joinColumns = @JoinColumn(name="certificadoconformidades_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="informescertificadoirregularidades_id", referencedColumnName="id"))
    private Set<Informecertificadoirregularidade> informescertificadoirregularidades = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "certificadoconformidade_notificacaoscertificadoirregularidade",
               joinColumns = @JoinColumn(name="certificadoconformidades_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="notificacaoscertificadoirregularidades_id", referencedColumnName="id"))
    private Set<Notificacaocertificadoirregularidade> notificacaoscertificadoirregularidades = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "certificadoconformidade_ocorrenciascertificadoirregularidade",
               joinColumns = @JoinColumn(name="certificadoconformidades_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="ocorrenciascertificadoirregularidades_id", referencedColumnName="id"))
    private Set<Ocorrenciacertificadoirregularidade> ocorrenciascertificadoirregularidades = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getData() {
        return data;
    }

    public Certificadoconformidade data(ZonedDateTime data) {
        this.data = data;
        return this;
    }

    public void setData(ZonedDateTime data) {
        this.data = data;
    }

    public ZonedDateTime getDataliberacao() {
        return dataliberacao;
    }

    public Certificadoconformidade dataliberacao(ZonedDateTime dataliberacao) {
        this.dataliberacao = dataliberacao;
        return this;
    }

    public void setDataliberacao(ZonedDateTime dataliberacao) {
        this.dataliberacao = dataliberacao;
    }

    public ZonedDateTime getDataparalisacao() {
        return dataparalisacao;
    }

    public Certificadoconformidade dataparalisacao(ZonedDateTime dataparalisacao) {
        this.dataparalisacao = dataparalisacao;
        return this;
    }

    public void setDataparalisacao(ZonedDateTime dataparalisacao) {
        this.dataparalisacao = dataparalisacao;
    }

    public ZonedDateTime getDatareinicio() {
        return datareinicio;
    }

    public Certificadoconformidade datareinicio(ZonedDateTime datareinicio) {
        this.datareinicio = datareinicio;
        return this;
    }

    public void setDatareinicio(ZonedDateTime datareinicio) {
        this.datareinicio = datareinicio;
    }

    public String getEdital() {
        return edital;
    }

    public Certificadoconformidade edital(String edital) {
        this.edital = edital;
        return this;
    }

    public void setEdital(String edital) {
        this.edital = edital;
    }

    public String getItem() {
        return item;
    }

    public Certificadoconformidade item(String item) {
        this.item = item;
        return this;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getLiberacao() {
        return liberacao;
    }

    public Certificadoconformidade liberacao(String liberacao) {
        this.liberacao = liberacao;
        return this;
    }

    public void setLiberacao(String liberacao) {
        this.liberacao = liberacao;
    }

    public Boolean isLiberacaoadministrativamente() {
        return liberacaoadministrativamente;
    }

    public Certificadoconformidade liberacaoadministrativamente(Boolean liberacaoadministrativamente) {
        this.liberacaoadministrativamente = liberacaoadministrativamente;
        return this;
    }

    public void setLiberacaoadministrativamente(Boolean liberacaoadministrativamente) {
        this.liberacaoadministrativamente = liberacaoadministrativamente;
    }

    public String getPeriodo() {
        return periodo;
    }

    public Certificadoconformidade periodo(String periodo) {
        this.periodo = periodo;
        return this;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getTexto() {
        return texto;
    }

    public Certificadoconformidade texto(String texto) {
        this.texto = texto;
        return this;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTexto2() {
        return texto2;
    }

    public Certificadoconformidade texto2(String texto2) {
        this.texto2 = texto2;
        return this;
    }

    public void setTexto2(String texto2) {
        this.texto2 = texto2;
    }

    public Obra getObra() {
        return obra;
    }

    public Certificadoconformidade obra(Obra obra) {
        this.obra = obra;
        return this;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public Tipocertificadoconformidade getTipo() {
        return tipo;
    }

    public Certificadoconformidade tipo(Tipocertificadoconformidade tipocertificadoconformidade) {
        this.tipo = tipocertificadoconformidade;
        return this;
    }

    public void setTipo(Tipocertificadoconformidade tipocertificadoconformidade) {
        this.tipo = tipocertificadoconformidade;
    }

    public Tipocertificadoconformidade getTipocertificadoconformidade() {
        return tipocertificadoconformidade;
    }

    public Certificadoconformidade tipocertificadoconformidade(Tipocertificadoconformidade tipocertificadoconformidade) {
        this.tipocertificadoconformidade = tipocertificadoconformidade;
        return this;
    }

    public void setTipocertificadoconformidade(Tipocertificadoconformidade tipocertificadoconformidade) {
        this.tipocertificadoconformidade = tipocertificadoconformidade;
    }

    public Set<Informecertificadoirregularidade> getInformescertificadoirregularidades() {
        return informescertificadoirregularidades;
    }

    public Certificadoconformidade informescertificadoirregularidades(Set<Informecertificadoirregularidade> informecertificadoirregularidades) {
        this.informescertificadoirregularidades = informecertificadoirregularidades;
        return this;
    }

    public Certificadoconformidade addInformescertificadoirregularidade(Informecertificadoirregularidade informecertificadoirregularidade) {
        this.informescertificadoirregularidades.add(informecertificadoirregularidade);
        return this;
    }

    public Certificadoconformidade removeInformescertificadoirregularidade(Informecertificadoirregularidade informecertificadoirregularidade) {
        this.informescertificadoirregularidades.remove(informecertificadoirregularidade);
        return this;
    }

    public void setInformescertificadoirregularidades(Set<Informecertificadoirregularidade> informecertificadoirregularidades) {
        this.informescertificadoirregularidades = informecertificadoirregularidades;
    }

    public Set<Notificacaocertificadoirregularidade> getNotificacaoscertificadoirregularidades() {
        return notificacaoscertificadoirregularidades;
    }

    public Certificadoconformidade notificacaoscertificadoirregularidades(Set<Notificacaocertificadoirregularidade> notificacaocertificadoirregularidades) {
        this.notificacaoscertificadoirregularidades = notificacaocertificadoirregularidades;
        return this;
    }

    public Certificadoconformidade addNotificacaoscertificadoirregularidade(Notificacaocertificadoirregularidade notificacaocertificadoirregularidade) {
        this.notificacaoscertificadoirregularidades.add(notificacaocertificadoirregularidade);
        return this;
    }

    public Certificadoconformidade removeNotificacaoscertificadoirregularidade(Notificacaocertificadoirregularidade notificacaocertificadoirregularidade) {
        this.notificacaoscertificadoirregularidades.remove(notificacaocertificadoirregularidade);
        return this;
    }

    public void setNotificacaoscertificadoirregularidades(Set<Notificacaocertificadoirregularidade> notificacaocertificadoirregularidades) {
        this.notificacaoscertificadoirregularidades = notificacaocertificadoirregularidades;
    }

    public Set<Ocorrenciacertificadoirregularidade> getOcorrenciascertificadoirregularidades() {
        return ocorrenciascertificadoirregularidades;
    }

    public Certificadoconformidade ocorrenciascertificadoirregularidades(Set<Ocorrenciacertificadoirregularidade> ocorrenciacertificadoirregularidades) {
        this.ocorrenciascertificadoirregularidades = ocorrenciacertificadoirregularidades;
        return this;
    }

    public Certificadoconformidade addOcorrenciascertificadoirregularidade(Ocorrenciacertificadoirregularidade ocorrenciacertificadoirregularidade) {
        this.ocorrenciascertificadoirregularidades.add(ocorrenciacertificadoirregularidade);
        return this;
    }

    public Certificadoconformidade removeOcorrenciascertificadoirregularidade(Ocorrenciacertificadoirregularidade ocorrenciacertificadoirregularidade) {
        this.ocorrenciascertificadoirregularidades.remove(ocorrenciacertificadoirregularidade);
        return this;
    }

    public void setOcorrenciascertificadoirregularidades(Set<Ocorrenciacertificadoirregularidade> ocorrenciacertificadoirregularidades) {
        this.ocorrenciascertificadoirregularidades = ocorrenciacertificadoirregularidades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Certificadoconformidade certificadoconformidade = (Certificadoconformidade) o;
        if (certificadoconformidade.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), certificadoconformidade.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Certificadoconformidade{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            ", dataliberacao='" + getDataliberacao() + "'" +
            ", dataparalisacao='" + getDataparalisacao() + "'" +
            ", datareinicio='" + getDatareinicio() + "'" +
            ", edital='" + getEdital() + "'" +
            ", item='" + getItem() + "'" +
            ", liberacao='" + getLiberacao() + "'" +
            ", liberacaoadministrativamente='" + isLiberacaoadministrativamente() + "'" +
            ", periodo='" + getPeriodo() + "'" +
            ", texto='" + getTexto() + "'" +
            ", texto2='" + getTexto2() + "'" +
            "}";
    }
}
