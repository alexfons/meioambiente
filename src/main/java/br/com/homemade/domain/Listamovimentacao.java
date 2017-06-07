package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A Listamovimentacao.
 */
@Entity
@Table(name = "listamovimentacao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Listamovimentacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "tipolancamento")
    private String tipolancamento;

    @Column(name = "historico")
    private String historico;

    @Column(name = "valoruscredito", precision=10, scale=2)
    private BigDecimal valoruscredito;

    @Column(name = "valorrcredito", precision=10, scale=2)
    private BigDecimal valorrcredito;

    @Column(name = "valorusdebito", precision=10, scale=2)
    private BigDecimal valorusdebito;

    @Column(name = "valorrdebito", precision=10, scale=2)
    private BigDecimal valorrdebito;

    @ManyToOne
    private Planocontas idplanocontas;

    @ManyToOne
    private Movimentacao movimentacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipolancamento() {
        return tipolancamento;
    }

    public Listamovimentacao tipolancamento(String tipolancamento) {
        this.tipolancamento = tipolancamento;
        return this;
    }

    public void setTipolancamento(String tipolancamento) {
        this.tipolancamento = tipolancamento;
    }

    public String getHistorico() {
        return historico;
    }

    public Listamovimentacao historico(String historico) {
        this.historico = historico;
        return this;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public BigDecimal getValoruscredito() {
        return valoruscredito;
    }

    public Listamovimentacao valoruscredito(BigDecimal valoruscredito) {
        this.valoruscredito = valoruscredito;
        return this;
    }

    public void setValoruscredito(BigDecimal valoruscredito) {
        this.valoruscredito = valoruscredito;
    }

    public BigDecimal getValorrcredito() {
        return valorrcredito;
    }

    public Listamovimentacao valorrcredito(BigDecimal valorrcredito) {
        this.valorrcredito = valorrcredito;
        return this;
    }

    public void setValorrcredito(BigDecimal valorrcredito) {
        this.valorrcredito = valorrcredito;
    }

    public BigDecimal getValorusdebito() {
        return valorusdebito;
    }

    public Listamovimentacao valorusdebito(BigDecimal valorusdebito) {
        this.valorusdebito = valorusdebito;
        return this;
    }

    public void setValorusdebito(BigDecimal valorusdebito) {
        this.valorusdebito = valorusdebito;
    }

    public BigDecimal getValorrdebito() {
        return valorrdebito;
    }

    public Listamovimentacao valorrdebito(BigDecimal valorrdebito) {
        this.valorrdebito = valorrdebito;
        return this;
    }

    public void setValorrdebito(BigDecimal valorrdebito) {
        this.valorrdebito = valorrdebito;
    }

    public Planocontas getIdplanocontas() {
        return idplanocontas;
    }

    public Listamovimentacao idplanocontas(Planocontas planocontas) {
        this.idplanocontas = planocontas;
        return this;
    }

    public void setIdplanocontas(Planocontas planocontas) {
        this.idplanocontas = planocontas;
    }

    public Movimentacao getMovimentacao() {
        return movimentacao;
    }

    public Listamovimentacao movimentacao(Movimentacao movimentacao) {
        this.movimentacao = movimentacao;
        return this;
    }

    public void setMovimentacao(Movimentacao movimentacao) {
        this.movimentacao = movimentacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Listamovimentacao listamovimentacao = (Listamovimentacao) o;
        if (listamovimentacao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), listamovimentacao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Listamovimentacao{" +
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
