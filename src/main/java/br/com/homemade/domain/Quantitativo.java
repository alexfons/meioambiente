package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A Quantitativo.
 */
@Entity
@Table(name = "quantitativo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Quantitativo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "idquantitativo")
    private Integer idquantitativo;

    @Column(name = "quantidade", precision=10, scale=2)
    private BigDecimal quantidade;

    @Column(name = "total", precision=10, scale=2)
    private BigDecimal total;

    @Column(name = "unidade")
    private String unidade;

    @Column(name = "unitario")
    private Double unitario;

    @ManyToOne
    private Medicao nmedicao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Quantitativo descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIdquantitativo() {
        return idquantitativo;
    }

    public Quantitativo idquantitativo(Integer idquantitativo) {
        this.idquantitativo = idquantitativo;
        return this;
    }

    public void setIdquantitativo(Integer idquantitativo) {
        this.idquantitativo = idquantitativo;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public Quantitativo quantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Quantitativo total(BigDecimal total) {
        this.total = total;
        return this;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getUnidade() {
        return unidade;
    }

    public Quantitativo unidade(String unidade) {
        this.unidade = unidade;
        return this;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Double getUnitario() {
        return unitario;
    }

    public Quantitativo unitario(Double unitario) {
        this.unitario = unitario;
        return this;
    }

    public void setUnitario(Double unitario) {
        this.unitario = unitario;
    }

    public Medicao getNmedicao() {
        return nmedicao;
    }

    public Quantitativo nmedicao(Medicao medicao) {
        this.nmedicao = medicao;
        return this;
    }

    public void setNmedicao(Medicao medicao) {
        this.nmedicao = medicao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Quantitativo quantitativo = (Quantitativo) o;
        if (quantitativo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quantitativo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Quantitativo{" +
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
