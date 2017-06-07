package br.com.homemade.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Planocontas entity.
 */
public class PlanocontasDTO implements Serializable {

    private Long id;

    private Integer idplanocontas;

    private String ncontabil;

    private String descricao;

    private String tipoconta;

    private String tipolancamento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdplanocontas() {
        return idplanocontas;
    }

    public void setIdplanocontas(Integer idplanocontas) {
        this.idplanocontas = idplanocontas;
    }

    public String getNcontabil() {
        return ncontabil;
    }

    public void setNcontabil(String ncontabil) {
        this.ncontabil = ncontabil;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipoconta() {
        return tipoconta;
    }

    public void setTipoconta(String tipoconta) {
        this.tipoconta = tipoconta;
    }

    public String getTipolancamento() {
        return tipolancamento;
    }

    public void setTipolancamento(String tipolancamento) {
        this.tipolancamento = tipolancamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PlanocontasDTO planocontasDTO = (PlanocontasDTO) o;
        if(planocontasDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), planocontasDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PlanocontasDTO{" +
            "id=" + getId() +
            ", idplanocontas='" + getIdplanocontas() + "'" +
            ", ncontabil='" + getNcontabil() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", tipoconta='" + getTipoconta() + "'" +
            ", tipolancamento='" + getTipolancamento() + "'" +
            "}";
    }
}
