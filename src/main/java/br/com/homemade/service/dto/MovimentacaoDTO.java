package br.com.homemade.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Movimentacao entity.
 */
public class MovimentacaoDTO implements Serializable {

    private Long id;

    private ZonedDateTime data;

    private String tipomovimentacao;

    private Long contabancariaId;

    private Long fonteId;

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

    public String getTipomovimentacao() {
        return tipomovimentacao;
    }

    public void setTipomovimentacao(String tipomovimentacao) {
        this.tipomovimentacao = tipomovimentacao;
    }

    public Long getContabancariaId() {
        return contabancariaId;
    }

    public void setContabancariaId(Long contabancariaId) {
        this.contabancariaId = contabancariaId;
    }

    public Long getFonteId() {
        return fonteId;
    }

    public void setFonteId(Long fonteId) {
        this.fonteId = fonteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MovimentacaoDTO movimentacaoDTO = (MovimentacaoDTO) o;
        if(movimentacaoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), movimentacaoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MovimentacaoDTO{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            ", tipomovimentacao='" + getTipomovimentacao() + "'" +
            "}";
    }
}
