package br.com.homemade.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Fisicografico entity.
 */
public class FisicograficoDTO implements Serializable {

    private Long id;

    private Boolean atacada;

    private Double extensao;

    private Double fim;

    private Double inicio;

    private Integer npontos;

    private Integer pontofim;

    private Integer pontoinicio;

    private Long atividadeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isAtacada() {
        return atacada;
    }

    public void setAtacada(Boolean atacada) {
        this.atacada = atacada;
    }

    public Double getExtensao() {
        return extensao;
    }

    public void setExtensao(Double extensao) {
        this.extensao = extensao;
    }

    public Double getFim() {
        return fim;
    }

    public void setFim(Double fim) {
        this.fim = fim;
    }

    public Double getInicio() {
        return inicio;
    }

    public void setInicio(Double inicio) {
        this.inicio = inicio;
    }

    public Integer getNpontos() {
        return npontos;
    }

    public void setNpontos(Integer npontos) {
        this.npontos = npontos;
    }

    public Integer getPontofim() {
        return pontofim;
    }

    public void setPontofim(Integer pontofim) {
        this.pontofim = pontofim;
    }

    public Integer getPontoinicio() {
        return pontoinicio;
    }

    public void setPontoinicio(Integer pontoinicio) {
        this.pontoinicio = pontoinicio;
    }

    public Long getAtividadeId() {
        return atividadeId;
    }

    public void setAtividadeId(Long atividadeId) {
        this.atividadeId = atividadeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FisicograficoDTO fisicograficoDTO = (FisicograficoDTO) o;
        if(fisicograficoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fisicograficoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FisicograficoDTO{" +
            "id=" + getId() +
            ", atacada='" + isAtacada() + "'" +
            ", extensao='" + getExtensao() + "'" +
            ", fim='" + getFim() + "'" +
            ", inicio='" + getInicio() + "'" +
            ", npontos='" + getNpontos() + "'" +
            ", pontofim='" + getPontofim() + "'" +
            ", pontoinicio='" + getPontoinicio() + "'" +
            "}";
    }
}
