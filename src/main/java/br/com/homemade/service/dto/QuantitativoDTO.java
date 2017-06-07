package br.com.homemade.service.dto;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Quantitativo entity.
 */
public class QuantitativoDTO implements Serializable {

    private Long id;

    private String descricao;

    private Integer idquantitativo;

    private BigDecimal quantidade;

    private BigDecimal total;

    private String unidade;

    private Double unitario;

    private Long nmedicaoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIdquantitativo() {
        return idquantitativo;
    }

    public void setIdquantitativo(Integer idquantitativo) {
        this.idquantitativo = idquantitativo;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Double getUnitario() {
        return unitario;
    }

    public void setUnitario(Double unitario) {
        this.unitario = unitario;
    }

    public Long getNmedicaoId() {
        return nmedicaoId;
    }

    public void setNmedicaoId(Long medicaoId) {
        this.nmedicaoId = medicaoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QuantitativoDTO quantitativoDTO = (QuantitativoDTO) o;
        if(quantitativoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quantitativoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuantitativoDTO{" +
            "id=" + getId() +
            ", descricao='" + getDescricao() + "'" +
            ", idquantitativo='" + getIdquantitativo() + "'" +
            ", quantidade='" + getQuantidade() + "'" +
            ", total='" + getTotal() + "'" +
            ", unidade='" + getUnidade() + "'" +
            ", unitario='" + getUnitario() + "'" +
            "}";
    }
}
