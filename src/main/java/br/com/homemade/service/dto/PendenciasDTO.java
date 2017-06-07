package br.com.homemade.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Pendencias entity.
 */
public class PendenciasDTO implements Serializable {

    private Long id;

    private ZonedDateTime data;

    private ZonedDateTime datainspecao;

    private Boolean notificacao;

    private Integer numero;

    private String obs;

    private Long obraId;

    private Set<FotoDTO> fotos = new HashSet<>();

    private Set<OcorrenciapendenciasDTO> ocorrenciaspendencias = new HashSet<>();

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

    public Set<OcorrenciapendenciasDTO> getOcorrenciaspendencias() {
        return ocorrenciaspendencias;
    }

    public void setOcorrenciaspendencias(Set<OcorrenciapendenciasDTO> ocorrenciapendencias) {
        this.ocorrenciaspendencias = ocorrenciapendencias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PendenciasDTO pendenciasDTO = (PendenciasDTO) o;
        if(pendenciasDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pendenciasDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PendenciasDTO{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            ", datainspecao='" + getDatainspecao() + "'" +
            ", notificacao='" + isNotificacao() + "'" +
            ", numero='" + getNumero() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
