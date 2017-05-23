package br.com.homemade.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Atividadeexecutadamensal entity.
 */
public class AtividadeexecutadamensalDTO implements Serializable {

    private Long id;

    private Float percentualatacado;

    private Float fim;

    private Double inicio;

    private String lado;

    private Float percentualconcluido;

    private Long atividadeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getPercentualatacado() {
        return percentualatacado;
    }

    public void setPercentualatacado(Float percentualatacado) {
        this.percentualatacado = percentualatacado;
    }

    public Float getFim() {
        return fim;
    }

    public void setFim(Float fim) {
        this.fim = fim;
    }

    public Double getInicio() {
        return inicio;
    }

    public void setInicio(Double inicio) {
        this.inicio = inicio;
    }

    public String getLado() {
        return lado;
    }

    public void setLado(String lado) {
        this.lado = lado;
    }

    public Float getPercentualconcluido() {
        return percentualconcluido;
    }

    public void setPercentualconcluido(Float percentualconcluido) {
        this.percentualconcluido = percentualconcluido;
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

        AtividadeexecutadamensalDTO atividadeexecutadamensalDTO = (AtividadeexecutadamensalDTO) o;
        if(atividadeexecutadamensalDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), atividadeexecutadamensalDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AtividadeexecutadamensalDTO{" +
            "id=" + getId() +
            ", percentualatacado='" + getPercentualatacado() + "'" +
            ", fim='" + getFim() + "'" +
            ", inicio='" + getInicio() + "'" +
            ", lado='" + getLado() + "'" +
            ", percentualconcluido='" + getPercentualconcluido() + "'" +
            "}";
    }
}
