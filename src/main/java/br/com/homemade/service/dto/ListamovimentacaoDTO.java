package br.com.homemade.service.dto;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Listamovimentacao entity.
 */
public class ListamovimentacaoDTO implements Serializable {

    private Long id;

    private String tipolancamento;

    private String historico;

    private BigDecimal valoruscredito;

    private BigDecimal valorrcredito;

    private BigDecimal valorusdebito;

    private BigDecimal valorrdebito;

    private Long idplanocontasId;

    private Long movimentacaoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipolancamento() {
        return tipolancamento;
    }

    public void setTipolancamento(String tipolancamento) {
        this.tipolancamento = tipolancamento;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public BigDecimal getValoruscredito() {
        return valoruscredito;
    }

    public void setValoruscredito(BigDecimal valoruscredito) {
        this.valoruscredito = valoruscredito;
    }

    public BigDecimal getValorrcredito() {
        return valorrcredito;
    }

    public void setValorrcredito(BigDecimal valorrcredito) {
        this.valorrcredito = valorrcredito;
    }

    public BigDecimal getValorusdebito() {
        return valorusdebito;
    }

    public void setValorusdebito(BigDecimal valorusdebito) {
        this.valorusdebito = valorusdebito;
    }

    public BigDecimal getValorrdebito() {
        return valorrdebito;
    }

    public void setValorrdebito(BigDecimal valorrdebito) {
        this.valorrdebito = valorrdebito;
    }

    public Long getIdplanocontasId() {
        return idplanocontasId;
    }

    public void setIdplanocontasId(Long planocontasId) {
        this.idplanocontasId = planocontasId;
    }

    public Long getMovimentacaoId() {
        return movimentacaoId;
    }

    public void setMovimentacaoId(Long movimentacaoId) {
        this.movimentacaoId = movimentacaoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ListamovimentacaoDTO listamovimentacaoDTO = (ListamovimentacaoDTO) o;
        if(listamovimentacaoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), listamovimentacaoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ListamovimentacaoDTO{" +
            "id=" + getId() +
            ", tipolancamento='" + getTipolancamento() + "'" +
            ", historico='" + getHistorico() + "'" +
            ", valoruscredito='" + getValoruscredito() + "'" +
            ", valorrcredito='" + getValorrcredito() + "'" +
            ", valorusdebito='" + getValorusdebito() + "'" +
            ", valorrdebito='" + getValorrdebito() + "'" +
            "}";
    }
}
