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
 * A CertifConfor.
 */
@Entity
@Table(name = "certif_confor")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CertifConfor implements Serializable {

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
    private TipocertifConfor tipo;

    @ManyToOne
    private TipocertifConfor tipocertifConfor;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "certif_confor_informes_certif_irreg",
               joinColumns = @JoinColumn(name="certif_confors_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="informes_certif_irregs_id", referencedColumnName="id"))
    private Set<InformeCertifIrreg> informesCertifIrregs = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "certif_confor_notificacaos_certif_irreg",
               joinColumns = @JoinColumn(name="certif_confors_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="notificacaos_certif_irregs_id", referencedColumnName="id"))
    private Set<NotificacaoCertifIrreg> notificacaosCertifIrregs = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "certif_confor_ocorrencias_certif_irreg",
               joinColumns = @JoinColumn(name="certif_confors_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="ocorrencias_certif_irregs_id", referencedColumnName="id"))
    private Set<OcorrenciaCertifIrreg> ocorrenciasCertifIrregs = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getData() {
        return data;
    }

    public CertifConfor data(ZonedDateTime data) {
        this.data = data;
        return this;
    }

    public void setData(ZonedDateTime data) {
        this.data = data;
    }

    public ZonedDateTime getDataliberacao() {
        return dataliberacao;
    }

    public CertifConfor dataliberacao(ZonedDateTime dataliberacao) {
        this.dataliberacao = dataliberacao;
        return this;
    }

    public void setDataliberacao(ZonedDateTime dataliberacao) {
        this.dataliberacao = dataliberacao;
    }

    public ZonedDateTime getDataparalisacao() {
        return dataparalisacao;
    }

    public CertifConfor dataparalisacao(ZonedDateTime dataparalisacao) {
        this.dataparalisacao = dataparalisacao;
        return this;
    }

    public void setDataparalisacao(ZonedDateTime dataparalisacao) {
        this.dataparalisacao = dataparalisacao;
    }

    public ZonedDateTime getDatareinicio() {
        return datareinicio;
    }

    public CertifConfor datareinicio(ZonedDateTime datareinicio) {
        this.datareinicio = datareinicio;
        return this;
    }

    public void setDatareinicio(ZonedDateTime datareinicio) {
        this.datareinicio = datareinicio;
    }

    public String getEdital() {
        return edital;
    }

    public CertifConfor edital(String edital) {
        this.edital = edital;
        return this;
    }

    public void setEdital(String edital) {
        this.edital = edital;
    }

    public String getItem() {
        return item;
    }

    public CertifConfor item(String item) {
        this.item = item;
        return this;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getLiberacao() {
        return liberacao;
    }

    public CertifConfor liberacao(String liberacao) {
        this.liberacao = liberacao;
        return this;
    }

    public void setLiberacao(String liberacao) {
        this.liberacao = liberacao;
    }

    public Boolean isLiberacaoadministrativamente() {
        return liberacaoadministrativamente;
    }

    public CertifConfor liberacaoadministrativamente(Boolean liberacaoadministrativamente) {
        this.liberacaoadministrativamente = liberacaoadministrativamente;
        return this;
    }

    public void setLiberacaoadministrativamente(Boolean liberacaoadministrativamente) {
        this.liberacaoadministrativamente = liberacaoadministrativamente;
    }

    public String getPeriodo() {
        return periodo;
    }

    public CertifConfor periodo(String periodo) {
        this.periodo = periodo;
        return this;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getTexto() {
        return texto;
    }

    public CertifConfor texto(String texto) {
        this.texto = texto;
        return this;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTexto2() {
        return texto2;
    }

    public CertifConfor texto2(String texto2) {
        this.texto2 = texto2;
        return this;
    }

    public void setTexto2(String texto2) {
        this.texto2 = texto2;
    }

    public Obra getObra() {
        return obra;
    }

    public CertifConfor obra(Obra obra) {
        this.obra = obra;
        return this;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public TipocertifConfor getTipo() {
        return tipo;
    }

    public CertifConfor tipo(TipocertifConfor tipocertifConfor) {
        this.tipo = tipocertifConfor;
        return this;
    }

    public void setTipo(TipocertifConfor tipocertifConfor) {
        this.tipo = tipocertifConfor;
    }

    public TipocertifConfor getTipocertifConfor() {
        return tipocertifConfor;
    }

    public CertifConfor tipocertifConfor(TipocertifConfor tipocertifConfor) {
        this.tipocertifConfor = tipocertifConfor;
        return this;
    }

    public void setTipocertifConfor(TipocertifConfor tipocertifConfor) {
        this.tipocertifConfor = tipocertifConfor;
    }

    public Set<InformeCertifIrreg> getInformesCertifIrregs() {
        return informesCertifIrregs;
    }

    public CertifConfor informesCertifIrregs(Set<InformeCertifIrreg> informeCertifIrregs) {
        this.informesCertifIrregs = informeCertifIrregs;
        return this;
    }

    public CertifConfor addInformesCertifIrreg(InformeCertifIrreg informeCertifIrreg) {
        this.informesCertifIrregs.add(informeCertifIrreg);
        return this;
    }

    public CertifConfor removeInformesCertifIrreg(InformeCertifIrreg informeCertifIrreg) {
        this.informesCertifIrregs.remove(informeCertifIrreg);
        return this;
    }

    public void setInformesCertifIrregs(Set<InformeCertifIrreg> informeCertifIrregs) {
        this.informesCertifIrregs = informeCertifIrregs;
    }

    public Set<NotificacaoCertifIrreg> getNotificacaosCertifIrregs() {
        return notificacaosCertifIrregs;
    }

    public CertifConfor notificacaosCertifIrregs(Set<NotificacaoCertifIrreg> notificacaoCertifIrregs) {
        this.notificacaosCertifIrregs = notificacaoCertifIrregs;
        return this;
    }

    public CertifConfor addNotificacaosCertifIrreg(NotificacaoCertifIrreg notificacaoCertifIrreg) {
        this.notificacaosCertifIrregs.add(notificacaoCertifIrreg);
        return this;
    }

    public CertifConfor removeNotificacaosCertifIrreg(NotificacaoCertifIrreg notificacaoCertifIrreg) {
        this.notificacaosCertifIrregs.remove(notificacaoCertifIrreg);
        return this;
    }

    public void setNotificacaosCertifIrregs(Set<NotificacaoCertifIrreg> notificacaoCertifIrregs) {
        this.notificacaosCertifIrregs = notificacaoCertifIrregs;
    }

    public Set<OcorrenciaCertifIrreg> getOcorrenciasCertifIrregs() {
        return ocorrenciasCertifIrregs;
    }

    public CertifConfor ocorrenciasCertifIrregs(Set<OcorrenciaCertifIrreg> ocorrenciaCertifIrregs) {
        this.ocorrenciasCertifIrregs = ocorrenciaCertifIrregs;
        return this;
    }

    public CertifConfor addOcorrenciasCertifIrreg(OcorrenciaCertifIrreg ocorrenciaCertifIrreg) {
        this.ocorrenciasCertifIrregs.add(ocorrenciaCertifIrreg);
        return this;
    }

    public CertifConfor removeOcorrenciasCertifIrreg(OcorrenciaCertifIrreg ocorrenciaCertifIrreg) {
        this.ocorrenciasCertifIrregs.remove(ocorrenciaCertifIrreg);
        return this;
    }

    public void setOcorrenciasCertifIrregs(Set<OcorrenciaCertifIrreg> ocorrenciaCertifIrregs) {
        this.ocorrenciasCertifIrregs = ocorrenciaCertifIrregs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CertifConfor certifConfor = (CertifConfor) o;
        if (certifConfor.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), certifConfor.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CertifConfor{" +
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
