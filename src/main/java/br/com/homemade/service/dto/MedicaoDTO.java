package br.com.homemade.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Medicao entity.
 */
public class MedicaoDTO implements Serializable {

    private Long id;

    private Boolean ajustecambio;

    private String conferido;

    private Integer idmedicao;

    private ZonedDateTime mes;

    private Integer nummedicao;

    private String tipomedicao;

    private BigDecimal valormedicao;

    private BigDecimal valormedicaoreajuste;

    private BigDecimal valorusmedicao;

    private Long idcontratoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isAjustecambio() {
        return ajustecambio;
    }

    public void setAjustecambio(Boolean ajustecambio) {
        this.ajustecambio = ajustecambio;
    }

    public String getConferido() {
        return conferido;
    }

    public void setConferido(String conferido) {
        this.conferido = conferido;
    }

    public Integer getIdmedicao() {
        return idmedicao;
    }

    public void setIdmedicao(Integer idmedicao) {
        this.idmedicao = idmedicao;
    }

    public ZonedDateTime getMes() {
        return mes;
    }

    public void setMes(ZonedDateTime mes) {
        this.mes = mes;
    }

    public Integer getNummedicao() {
        return nummedicao;
    }

    public void setNummedicao(Integer nummedicao) {
        this.nummedicao = nummedicao;
    }

    public String getTipomedicao() {
        return tipomedicao;
    }

    public void setTipomedicao(String tipomedicao) {
        this.tipomedicao = tipomedicao;
    }

    public BigDecimal getValormedicao() {
        return valormedicao;
    }

    public void setValormedicao(BigDecimal valormedicao) {
        this.valormedicao = valormedicao;
    }

    public BigDecimal getValormedicaoreajuste() {
        return valormedicaoreajuste;
    }

    public void setValormedicaoreajuste(BigDecimal valormedicaoreajuste) {
        this.valormedicaoreajuste = valormedicaoreajuste;
    }

    public BigDecimal getValorusmedicao() {
        return valorusmedicao;
    }

    public void setValorusmedicao(BigDecimal valorusmedicao) {
        this.valorusmedicao = valorusmedicao;
    }

    public Long getIdcontratoId() {
        return idcontratoId;
    }

    public void setIdcontratoId(Long contratoId) {
        this.idcontratoId = contratoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MedicaoDTO medicaoDTO = (MedicaoDTO) o;
        if(medicaoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), medicaoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MedicaoDTO{" +
            "id=" + getId() +
            ", ajustecambio='" + isAjustecambio() + "'" +
            ", conferido='" + getConferido() + "'" +
            ", idmedicao='" + getIdmedicao() + "'" +
            ", mes='" + getMes() + "'" +
            ", nummedicao='" + getNummedicao() + "'" +
            ", tipomedicao='" + getTipomedicao() + "'" +
            ", valormedicao='" + getValormedicao() + "'" +
            ", valormedicaoreajuste='" + getValormedicaoreajuste() + "'" +
            ", valorusmedicao='" + getValorusmedicao() + "'" +
            "}";
    }
}
