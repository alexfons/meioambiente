package br.com.homemade.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Obra entity.
 */
public class ObraDTO implements Serializable {

    private Long id;

    private String pendente;

    private String map;

    private String user;

    private String track;

    private String certificadoMes;

    private Long tipoobraId;

    private Long inspetorId;

    private Long fiscalId;

    private Long trechoId;

    private Set<ContratoobraDTO> contratosobras = new HashSet<>();

    private Set<HistoricoDTO> historicos = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPendente() {
        return pendente;
    }

    public void setPendente(String pendente) {
        this.pendente = pendente;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getCertificadoMes() {
        return certificadoMes;
    }

    public void setCertificadoMes(String certificadoMes) {
        this.certificadoMes = certificadoMes;
    }

    public Long getTipoobraId() {
        return tipoobraId;
    }

    public void setTipoobraId(Long tipoobraId) {
        this.tipoobraId = tipoobraId;
    }

    public Long getInspetorId() {
        return inspetorId;
    }

    public void setInspetorId(Long inspetorId) {
        this.inspetorId = inspetorId;
    }

    public Long getFiscalId() {
        return fiscalId;
    }

    public void setFiscalId(Long fiscalId) {
        this.fiscalId = fiscalId;
    }

    public Long getTrechoId() {
        return trechoId;
    }

    public void setTrechoId(Long trechoId) {
        this.trechoId = trechoId;
    }

    public Set<ContratoobraDTO> getContratosobras() {
        return contratosobras;
    }

    public void setContratosobras(Set<ContratoobraDTO> contratoobras) {
        this.contratosobras = contratoobras;
    }

    public Set<HistoricoDTO> getHistoricos() {
        return historicos;
    }

    public void setHistoricos(Set<HistoricoDTO> historicos) {
        this.historicos = historicos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ObraDTO obraDTO = (ObraDTO) o;
        if(obraDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), obraDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ObraDTO{" +
            "id=" + getId() +
            ", pendente='" + getPendente() + "'" +
            ", map='" + getMap() + "'" +
            ", user='" + getUser() + "'" +
            ", track='" + getTrack() + "'" +
            ", certificadoMes='" + getCertificadoMes() + "'" +
            "}";
    }
}
