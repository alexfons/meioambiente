package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Lancamentos.
 */
@Entity
@Table(name = "lancamentos")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Lancamentos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "datalan")
    private ZonedDateTime datalan;

    @Column(name = "historico")
    private String historico;

    @Column(name = "recurso")
    private String recurso;

    @Column(name = "tipomedicao")
    private String tipomedicao;

    @Column(name = "idlancamentos")
    private Integer idlancamentos;

    @Column(name = "nummedicao")
    private Integer nummedicao;

    @Column(name = "valorrdebito", precision=10, scale=2)
    private BigDecimal valorrdebito;

    @Column(name = "valorusdebito", precision=10, scale=2)
    private BigDecimal valorusdebito;

    @Column(name = "valorrcredito", precision=10, scale=2)
    private BigDecimal valorrcredito;

    @Column(name = "valoruscredito", precision=10, scale=2)
    private BigDecimal valoruscredito;

    @ManyToOne
    private Fonte fonte;

    @ManyToOne
    private Planocontas idplanocontas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDatalan() {
        return datalan;
    }

    public Lancamentos datalan(ZonedDateTime datalan) {
        this.datalan = datalan;
        return this;
    }

    public void setDatalan(ZonedDateTime datalan) {
        this.datalan = datalan;
    }

    public String getHistorico() {
        return historico;
    }

    public Lancamentos historico(String historico) {
        this.historico = historico;
        return this;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public String getRecurso() {
        return recurso;
    }

    public Lancamentos recurso(String recurso) {
        this.recurso = recurso;
        return this;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public String getTipomedicao() {
        return tipomedicao;
    }

    public Lancamentos tipomedicao(String tipomedicao) {
        this.tipomedicao = tipomedicao;
        return this;
    }

    public void setTipomedicao(String tipomedicao) {
        this.tipomedicao = tipomedicao;
    }

    public Integer getIdlancamentos() {
        return idlancamentos;
    }

    public Lancamentos idlancamentos(Integer idlancamentos) {
        this.idlancamentos = idlancamentos;
        return this;
    }

    public void setIdlancamentos(Integer idlancamentos) {
        this.idlancamentos = idlancamentos;
    }

    public Integer getNummedicao() {
        return nummedicao;
    }

    public Lancamentos nummedicao(Integer nummedicao) {
        this.nummedicao = nummedicao;
        return this;
    }

    public void setNummedicao(Integer nummedicao) {
        this.nummedicao = nummedicao;
    }

    public BigDecimal getValorrdebito() {
        return valorrdebito;
    }

    public Lancamentos valorrdebito(BigDecimal valorrdebito) {
        this.valorrdebito = valorrdebito;
        return this;
    }

    public void setValorrdebito(BigDecimal valorrdebito) {
        this.valorrdebito = valorrdebito;
    }

    public BigDecimal getValorusdebito() {
        return valorusdebito;
    }

    public Lancamentos valorusdebito(BigDecimal valorusdebito) {
        this.valorusdebito = valorusdebito;
        return this;
    }

    public void setValorusdebito(BigDecimal valorusdebito) {
        this.valorusdebito = valorusdebito;
    }

    public BigDecimal getValorrcredito() {
        return valorrcredito;
    }

    public Lancamentos valorrcredito(BigDecimal valorrcredito) {
        this.valorrcredito = valorrcredito;
        return this;
    }

    public void setValorrcredito(BigDecimal valorrcredito) {
        this.valorrcredito = valorrcredito;
    }

    public BigDecimal getValoruscredito() {
        return valoruscredito;
    }

    public Lancamentos valoruscredito(BigDecimal valoruscredito) {
        this.valoruscredito = valoruscredito;
        return this;
    }

    public void setValoruscredito(BigDecimal valoruscredito) {
        this.valoruscredito = valoruscredito;
    }

    public Fonte getFonte() {
        return fonte;
    }

    public Lancamentos fonte(Fonte fonte) {
        this.fonte = fonte;
        return this;
    }

    public void setFonte(Fonte fonte) {
        this.fonte = fonte;
    }

    public Planocontas getIdplanocontas() {
        return idplanocontas;
    }

    public Lancamentos idplanocontas(Planocontas planocontas) {
        this.idplanocontas = planocontas;
        return this;
    }

    public void setIdplanocontas(Planocontas planocontas) {
        this.idplanocontas = planocontas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lancamentos lancamentos = (Lancamentos) o;
        if (lancamentos.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), lancamentos.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Lancamentos{" +
            "id=" + getId() +
            ", datalan='" + getDatalan() + "'" +
            ", historico='" + getHistorico() + "'" +
            ", recurso='" + getRecurso() + "'" +
            ", tipomedicao='" + getTipomedicao() + "'" +
            ", idlancamentos='" + getIdlancamentos() + "'" +
            ", nummedicao='" + getNummedicao() + "'" +
            ", valorrdebito='" + getValorrdebito() + "'" +
            ", valorusdebito='" + getValorusdebito() + "'" +
            ", valorrcredito='" + getValorrcredito() + "'" +
            ", valoruscredito='" + getValoruscredito() + "'" +
            "}";
    }
}
