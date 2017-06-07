package br.com.homemade.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Solicitacao entity.
 */
public class SolicitacaoDTO implements Serializable {

    private Long id;

    private Boolean apresentamos;

    private ZonedDateTime data;

    private String descricao;

    private String extenso;

    private String extenso1;

    private Integer idsolicitacao;

    private String moeda;

    private BigDecimal montante;

    private Integer nsolicitacao;

    private String oficio;

    private Boolean solicitamos;

    private Long idbancoId;

    private Long idContratoagenteId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isApresentamos() {
        return apresentamos;
    }

    public void setApresentamos(Boolean apresentamos) {
        this.apresentamos = apresentamos;
    }

    public ZonedDateTime getData() {
        return data;
    }

    public void setData(ZonedDateTime data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getExtenso() {
        return extenso;
    }

    public void setExtenso(String extenso) {
        this.extenso = extenso;
    }

    public String getExtenso1() {
        return extenso1;
    }

    public void setExtenso1(String extenso1) {
        this.extenso1 = extenso1;
    }

    public Integer getIdsolicitacao() {
        return idsolicitacao;
    }

    public void setIdsolicitacao(Integer idsolicitacao) {
        this.idsolicitacao = idsolicitacao;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public BigDecimal getMontante() {
        return montante;
    }

    public void setMontante(BigDecimal montante) {
        this.montante = montante;
    }

    public Integer getNsolicitacao() {
        return nsolicitacao;
    }

    public void setNsolicitacao(Integer nsolicitacao) {
        this.nsolicitacao = nsolicitacao;
    }

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public Boolean isSolicitamos() {
        return solicitamos;
    }

    public void setSolicitamos(Boolean solicitamos) {
        this.solicitamos = solicitamos;
    }

    public Long getIdbancoId() {
        return idbancoId;
    }

    public void setIdbancoId(Long bancoId) {
        this.idbancoId = bancoId;
    }

    public Long getIdContratoagenteId() {
        return idContratoagenteId;
    }

    public void setIdContratoagenteId(Long contratoagenteId) {
        this.idContratoagenteId = contratoagenteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SolicitacaoDTO solicitacaoDTO = (SolicitacaoDTO) o;
        if(solicitacaoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), solicitacaoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SolicitacaoDTO{" +
            "id=" + getId() +
            ", apresentamos='" + isApresentamos() + "'" +
            ", data='" + getData() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", extenso='" + getExtenso() + "'" +
            ", extenso1='" + getExtenso1() + "'" +
            ", idsolicitacao='" + getIdsolicitacao() + "'" +
            ", moeda='" + getMoeda() + "'" +
            ", montante='" + getMontante() + "'" +
            ", nsolicitacao='" + getNsolicitacao() + "'" +
            ", oficio='" + getOficio() + "'" +
            ", solicitamos='" + isSolicitamos() + "'" +
            "}";
    }
}
