package br.com.homemade.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Informe entity.
 */
public class InformeDTO implements Serializable {

    private Long id;

    private ZonedDateTime data;

    private ZonedDateTime datainspecao;

    private Boolean notificacao;

    private Integer numero;

    private String obs;

    private Long obraId;

    private Set<FotoDTO> fotos = new HashSet<>();

    private Set<OcorrenciainformeDTO> ocorrenciasinformes = new HashSet<>();

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

    public ZonedDateTime getDatainspecao() {
        return datainspecao;
    }

    public void setDatainspecao(ZonedDateTime datainspecao) {
        this.datainspecao = datainspecao;
    }

    public Boolean isNotificacao() {
        return notificacao;
    }

    public void setNotificacao(Boolean notificacao) {
        this.notificacao = notificacao;
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

    public Long getObraId() {
        return obraId;
    }

    public void setObraId(Long obraId) {
        this.obraId = obraId;
    }

    public Set<FotoDTO> getFotos() {
        return fotos;
    }

    public void setFotos(Set<FotoDTO> fotos) {
        this.fotos = fotos;
    }

    public Set<OcorrenciainformeDTO> getOcorrenciasinformes() {
        return ocorrenciasinformes;
    }

    public void setOcorrenciasinformes(Set<OcorrenciainformeDTO> ocorrenciainformes) {
        this.ocorrenciasinformes = ocorrenciainformes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InformeDTO informeDTO = (InformeDTO) o;
        if(informeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), informeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InformeDTO{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            ", datainspecao='" + getDatainspecao() + "'" +
            ", notificacao='" + isNotificacao() + "'" +
            ", numero='" + getNumero() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
