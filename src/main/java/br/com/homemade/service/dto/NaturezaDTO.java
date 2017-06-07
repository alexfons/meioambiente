package br.com.homemade.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Natureza entity.
 */
public class NaturezaDTO implements Serializable {

    private Long id;

    private String descnatureza;

    private String descsubacao;

    private Integer idnatureza;

    private Integer numnatureza;

    private Integer subacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescnatureza() {
        return descnatureza;
    }

    public void setDescnatureza(String descnatureza) {
        this.descnatureza = descnatureza;
    }

    public String getDescsubacao() {
        return descsubacao;
    }

    public void setDescsubacao(String descsubacao) {
        this.descsubacao = descsubacao;
    }

    public Integer getIdnatureza() {
        return idnatureza;
    }

    public void setIdnatureza(Integer idnatureza) {
        this.idnatureza = idnatureza;
    }

    public Integer getNumnatureza() {
        return numnatureza;
    }

    public void setNumnatureza(Integer numnatureza) {
        this.numnatureza = numnatureza;
    }

    public Integer getSubacao() {
        return subacao;
    }

    public void setSubacao(Integer subacao) {
        this.subacao = subacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NaturezaDTO naturezaDTO = (NaturezaDTO) o;
        if(naturezaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), naturezaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NaturezaDTO{" +
            "id=" + getId() +
            ", descnatureza='" + getDescnatureza() + "'" +
            ", descsubacao='" + getDescsubacao() + "'" +
            ", idnatureza='" + getIdnatureza() + "'" +
            ", numnatureza='" + getNumnatureza() + "'" +
            ", subacao='" + getSubacao() + "'" +
            "}";
    }
}
