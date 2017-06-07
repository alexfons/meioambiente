package br.com.homemade.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Trecho entity.
 */
public class TrechoDTO implements Serializable {

    private Long id;

    private String defim;

    private String deinicio;

    private String fim;

    private String inicio;

    private String jurisdicao;

    private String kml;

    private Double nuextensao;

    private Double nukmfinal;

    private Double nukminicia;

    private String responsavel;

    private String sgpre;

    private String sgsituacao;

    private String tprevest;

    private Long rodoviaId;

    private Long supreId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDefim() {
        return defim;
    }

    public void setDefim(String defim) {
        this.defim = defim;
    }

    public String getDeinicio() {
        return deinicio;
    }

    public void setDeinicio(String deinicio) {
        this.deinicio = deinicio;
    }

    public String getFim() {
        return fim;
    }

    public void setFim(String fim) {
        this.fim = fim;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getJurisdicao() {
        return jurisdicao;
    }

    public void setJurisdicao(String jurisdicao) {
        this.jurisdicao = jurisdicao;
    }

    public String getKml() {
        return kml;
    }

    public void setKml(String kml) {
        this.kml = kml;
    }

    public Double getNuextensao() {
        return nuextensao;
    }

    public void setNuextensao(Double nuextensao) {
        this.nuextensao = nuextensao;
    }

    public Double getNukmfinal() {
        return nukmfinal;
    }

    public void setNukmfinal(Double nukmfinal) {
        this.nukmfinal = nukmfinal;
    }

    public Double getNukminicia() {
        return nukminicia;
    }

    public void setNukminicia(Double nukminicia) {
        this.nukminicia = nukminicia;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getSgpre() {
        return sgpre;
    }

    public void setSgpre(String sgpre) {
        this.sgpre = sgpre;
    }

    public String getSgsituacao() {
        return sgsituacao;
    }

    public void setSgsituacao(String sgsituacao) {
        this.sgsituacao = sgsituacao;
    }

    public String getTprevest() {
        return tprevest;
    }

    public void setTprevest(String tprevest) {
        this.tprevest = tprevest;
    }

    public Long getRodoviaId() {
        return rodoviaId;
    }

    public void setRodoviaId(Long rodoviaId) {
        this.rodoviaId = rodoviaId;
    }

    public Long getSupreId() {
        return supreId;
    }

    public void setSupreId(Long supreId) {
        this.supreId = supreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TrechoDTO trechoDTO = (TrechoDTO) o;
        if(trechoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), trechoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TrechoDTO{" +
            "id=" + getId() +
            ", defim='" + getDefim() + "'" +
            ", deinicio='" + getDeinicio() + "'" +
            ", fim='" + getFim() + "'" +
            ", inicio='" + getInicio() + "'" +
            ", jurisdicao='" + getJurisdicao() + "'" +
            ", kml='" + getKml() + "'" +
            ", nuextensao='" + getNuextensao() + "'" +
            ", nukmfinal='" + getNukmfinal() + "'" +
            ", nukminicia='" + getNukminicia() + "'" +
            ", responsavel='" + getResponsavel() + "'" +
            ", sgpre='" + getSgpre() + "'" +
            ", sgsituacao='" + getSgsituacao() + "'" +
            ", tprevest='" + getTprevest() + "'" +
            "}";
    }
}
