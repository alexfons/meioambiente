package br.com.homemade.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Apresentacao entity.
 */
public class ApresentacaoDTO implements Serializable {

    private Long id;

    private Integer numero;

    private String obs;

    private Boolean notificacao;

    private ZonedDateTime data;

    private Long obraId;

    private Set<OcorrenciaapresentacaoDTO> ocorrenciasapresentacaos = new HashSet<>();

    private Set<FotoDTO> fotos = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Boolean isNotificacao() {
        return notificacao;
    }

    public void setNotificacao(Boolean notificacao) {
        this.notificacao = notificacao;
    }

    public ZonedDateTime getData() {
        return data;
    }

    public void setData(ZonedDateTime data) {
        this.data = data;
    }

    public Long getObraId() {
        return obraId;
    }

    public void setObraId(Long obraId) {
        this.obraId = obraId;
    }

    public Set<OcorrenciaapresentacaoDTO> getOcorrenciasapresentacaos() {
        return ocorrenciasapresentacaos;
    }

    public void setOcorrenciasapresentacaos(Set<OcorrenciaapresentacaoDTO> ocorrenciaapresentacaos) {
        this.ocorrenciasapresentacaos = ocorrenciaapresentacaos;
    }

    public Set<FotoDTO> getFotos() {
        return fotos;
    }

    public void setFotos(Set<FotoDTO> fotos) {
        this.fotos = fotos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ApresentacaoDTO apresentacaoDTO = (ApresentacaoDTO) o;
        if(apresentacaoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), apresentacaoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ApresentacaoDTO{" +
            "id=" + getId() +
            ", numero='" + getNumero() + "'" +
            ", obs='" + getObs() + "'" +
            ", notificacao='" + isNotificacao() + "'" +
            ", data='" + getData() + "'" +
            "}";
    }
}
