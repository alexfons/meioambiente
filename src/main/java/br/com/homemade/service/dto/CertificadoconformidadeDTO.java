package br.com.homemade.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Certificadoconformidade entity.
 */
public class CertificadoconformidadeDTO implements Serializable {

    private Long id;

    private ZonedDateTime data;

    private ZonedDateTime dataliberacao;

    private ZonedDateTime dataparalisacao;

    private ZonedDateTime datareinicio;

    private String edital;

    private String item;

    private String liberacao;

    private Boolean liberacaoadministrativamente;

    private String periodo;

    private String texto;

    private String texto2;

    private Long obraId;

    private Long tipoId;

    private Long tipocertificadoconformidadeId;

    private Set<InformecertificadoirregularidadeDTO> informescertificadoirregularidades = new HashSet<>();

    private Set<NotificacaocertificadoirregularidadeDTO> notificacaoscertificadoirregularidades = new HashSet<>();

    private Set<OcorrenciacertificadoirregularidadeDTO> ocorrenciascertificadoirregularidades = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getData() {
        return data;
    }

    public void setData(ZonedDateTime data) {
        this.data = data;
    }

    public ZonedDateTime getDataliberacao() {
        return dataliberacao;
    }

    public void setDataliberacao(ZonedDateTime dataliberacao) {
        this.dataliberacao = dataliberacao;
    }

    public ZonedDateTime getDataparalisacao() {
        return dataparalisacao;
    }

    public void setDataparalisacao(ZonedDateTime dataparalisacao) {
        this.dataparalisacao = dataparalisacao;
    }

    public ZonedDateTime getDatareinicio() {
        return datareinicio;
    }

    public void setDatareinicio(ZonedDateTime datareinicio) {
        this.datareinicio = datareinicio;
    }

    public String getEdital() {
        return edital;
    }

    public void setEdital(String edital) {
        this.edital = edital;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getLiberacao() {
        return liberacao;
    }

    public void setLiberacao(String liberacao) {
        this.liberacao = liberacao;
    }

    public Boolean isLiberacaoadministrativamente() {
        return liberacaoadministrativamente;
    }

    public void setLiberacaoadministrativamente(Boolean liberacaoadministrativamente) {
        this.liberacaoadministrativamente = liberacaoadministrativamente;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTexto2() {
        return texto2;
    }

    public void setTexto2(String texto2) {
        this.texto2 = texto2;
    }

    public Long getObraId() {
        return obraId;
    }

    public void setObraId(Long obraId) {
        this.obraId = obraId;
    }

    public Long getTipoId() {
        return tipoId;
    }

    public void setTipoId(Long tipocertificadoconformidadeId) {
        this.tipoId = tipocertificadoconformidadeId;
    }

    public Long getTipocertificadoconformidadeId() {
        return tipocertificadoconformidadeId;
    }

    public void setTipocertificadoconformidadeId(Long tipocertificadoconformidadeId) {
        this.tipocertificadoconformidadeId = tipocertificadoconformidadeId;
    }

    public Set<InformecertificadoirregularidadeDTO> getInformescertificadoirregularidades() {
        return informescertificadoirregularidades;
    }

    public void setInformescertificadoirregularidades(Set<InformecertificadoirregularidadeDTO> informecertificadoirregularidades) {
        this.informescertificadoirregularidades = informecertificadoirregularidades;
    }

    public Set<NotificacaocertificadoirregularidadeDTO> getNotificacaoscertificadoirregularidades() {
        return notificacaoscertificadoirregularidades;
    }

    public void setNotificacaoscertificadoirregularidades(Set<NotificacaocertificadoirregularidadeDTO> notificacaocertificadoirregularidades) {
        this.notificacaoscertificadoirregularidades = notificacaocertificadoirregularidades;
    }

    public Set<OcorrenciacertificadoirregularidadeDTO> getOcorrenciascertificadoirregularidades() {
        return ocorrenciascertificadoirregularidades;
    }

    public void setOcorrenciascertificadoirregularidades(Set<OcorrenciacertificadoirregularidadeDTO> ocorrenciacertificadoirregularidades) {
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

        CertificadoconformidadeDTO certificadoconformidadeDTO = (CertificadoconformidadeDTO) o;
        if(certificadoconformidadeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), certificadoconformidadeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CertificadoconformidadeDTO{" +
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
