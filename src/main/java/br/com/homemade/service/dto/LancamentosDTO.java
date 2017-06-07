package br.com.homemade.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Lancamentos entity.
 */
public class LancamentosDTO implements Serializable {

    private Long id;

    private ZonedDateTime datalan;

    private String historico;

    private String recurso;

    private String tipomedicao;

    private Integer idlancamentos;

    private Integer nummedicao;

    private BigDecimal valorrdebito;

    private BigDecimal valorusdebito;

    private BigDecimal valorrcredito;

    private BigDecimal valoruscredito;

    private Long fonteId;

    private Long idplanocontasId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDatalan() {
        return datalan;
    }

    public void setDatalan(ZonedDateTime datalan) {
        this.datalan = datalan;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public String getTipomedicao() {
        return tipomedicao;
    }

    public void setTipomedicao(String tipomedicao) {
        this.tipomedicao = tipomedicao;
    }

    public Integer getIdlancamentos() {
        return idlancamentos;
    }

    public void setIdlancamentos(Integer idlancamentos) {
        this.idlancamentos = idlancamentos;
    }

    public Integer getNummedicao() {
        return nummedicao;
    }

    public void setNummedicao(Integer nummedicao) {
        this.nummedicao = nummedicao;
    }

    public BigDecimal getValorrdebito() {
        return valorrdebito;
    }

    public void setValorrdebito(BigDecimal valorrdebito) {
        this.valorrdebito = valorrdebito;
    }

    public BigDecimal getValorusdebito() {
        return valorusdebito;
    }

    public void setValorusdebito(BigDecimal valorusdebito) {
        this.valorusdebito = valorusdebito;
    }

    public BigDecimal getValorrcredito() {
        return valorrcredito;
    }

    public void setValorrcredito(BigDecimal valorrcredito) {
        this.valorrcredito = valorrcredito;
    }

    public BigDecimal getValoruscredito() {
        return valoruscredito;
    }

    public void setValoruscredito(BigDecimal valoruscredito) {
        this.valoruscredito = valoruscredito;
    }

    public Long getFonteId() {
        return fonteId;
    }

    public void setFonteId(Long fonteId) {
        this.fonteId = fonteId;
    }

    public Long getIdplanocontasId() {
        return idplanocontasId;
    }

    public void setIdplanocontasId(Long planocontasId) {
        this.idplanocontasId = planocontasId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LancamentosDTO lancamentosDTO = (LancamentosDTO) o;
        if(lancamentosDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), lancamentosDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LancamentosDTO{" +
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
