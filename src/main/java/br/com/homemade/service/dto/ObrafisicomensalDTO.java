package br.com.homemade.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Obrafisicomensal entity.
 */
public class ObrafisicomensalDTO implements Serializable {

    private Long id;

    private String comentario;

    private ZonedDateTime datainspecao;

    private ZonedDateTime datarelatorio;

    private String pessoal;

    private String equipamento;

    private String instalacaoapoio;

    private String ritmo;

    private String apresentacao;

    private String qualidadeservicos;

    private String cronograma;

    private Integer prazodecorrido;

    private Integer avancofisicoOAE;

    private Integer avancofisicoponderado;

    private ZonedDateTime previsaoatual;

    private Long fisicoId;

    private Set<AtividadeexecutadamensalDTO> atividadeexecutadamensals = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public ZonedDateTime getDatainspecao() {
        return datainspecao;
    }

    public void setDatainspecao(ZonedDateTime datainspecao) {
        this.datainspecao = datainspecao;
    }

    public ZonedDateTime getDatarelatorio() {
        return datarelatorio;
    }

    public void setDatarelatorio(ZonedDateTime datarelatorio) {
        this.datarelatorio = datarelatorio;
    }

    public String getPessoal() {
        return pessoal;
    }

    public void setPessoal(String pessoal) {
        this.pessoal = pessoal;
    }

    public String getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }

    public String getInstalacaoapoio() {
        return instalacaoapoio;
    }

    public void setInstalacaoapoio(String instalacaoapoio) {
        this.instalacaoapoio = instalacaoapoio;
    }

    public String getRitmo() {
        return ritmo;
    }

    public void setRitmo(String ritmo) {
        this.ritmo = ritmo;
    }

    public String getApresentacao() {
        return apresentacao;
    }

    public void setApresentacao(String apresentacao) {
        this.apresentacao = apresentacao;
    }

    public String getQualidadeservicos() {
        return qualidadeservicos;
    }

    public void setQualidadeservicos(String qualidadeservicos) {
        this.qualidadeservicos = qualidadeservicos;
    }

    public String getCronograma() {
        return cronograma;
    }

    public void setCronograma(String cronograma) {
        this.cronograma = cronograma;
    }

    public Integer getPrazodecorrido() {
        return prazodecorrido;
    }

    public void setPrazodecorrido(Integer prazodecorrido) {
        this.prazodecorrido = prazodecorrido;
    }

    public Integer getAvancofisicoOAE() {
        return avancofisicoOAE;
    }

    public void setAvancofisicoOAE(Integer avancofisicoOAE) {
        this.avancofisicoOAE = avancofisicoOAE;
    }

    public Integer getAvancofisicoponderado() {
        return avancofisicoponderado;
    }

    public void setAvancofisicoponderado(Integer avancofisicoponderado) {
        this.avancofisicoponderado = avancofisicoponderado;
    }

    public ZonedDateTime getPrevisaoatual() {
        return previsaoatual;
    }

    public void setPrevisaoatual(ZonedDateTime previsaoatual) {
        this.previsaoatual = previsaoatual;
    }

    public Long getFisicoId() {
        return fisicoId;
    }

    public void setFisicoId(Long fisicoId) {
        this.fisicoId = fisicoId;
    }

    public Set<AtividadeexecutadamensalDTO> getAtividadeexecutadamensals() {
        return atividadeexecutadamensals;
    }

    public void setAtividadeexecutadamensals(Set<AtividadeexecutadamensalDTO> atividadeexecutadamensals) {
        this.atividadeexecutadamensals = atividadeexecutadamensals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ObrafisicomensalDTO obrafisicomensalDTO = (ObrafisicomensalDTO) o;
        if(obrafisicomensalDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), obrafisicomensalDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ObrafisicomensalDTO{" +
            "id=" + getId() +
            ", comentario='" + getComentario() + "'" +
            ", datainspecao='" + getDatainspecao() + "'" +
            ", datarelatorio='" + getDatarelatorio() + "'" +
            ", pessoal='" + getPessoal() + "'" +
            ", equipamento='" + getEquipamento() + "'" +
            ", instalacaoapoio='" + getInstalacaoapoio() + "'" +
            ", ritmo='" + getRitmo() + "'" +
            ", apresentacao='" + getApresentacao() + "'" +
            ", qualidadeservicos='" + getQualidadeservicos() + "'" +
            ", cronograma='" + getCronograma() + "'" +
            ", prazodecorrido='" + getPrazodecorrido() + "'" +
            ", avancofisicoOAE='" + getAvancofisicoOAE() + "'" +
            ", avancofisicoponderado='" + getAvancofisicoponderado() + "'" +
            ", previsaoatual='" + getPrevisaoatual() + "'" +
            "}";
    }
}
