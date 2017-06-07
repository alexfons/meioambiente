package br.com.homemade.service.dto;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Fonte entity.
 */
public class FonteDTO implements Serializable {

    private Long id;

    private String descricao;

    private Integer fonte;

    private BigDecimal indiceagente;

    private BigDecimal indicelocal;

    private Long idcontabancariaId;

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

    public Integer getFonte() {
        return fonte;
    }

    public void setFonte(Integer fonte) {
        this.fonte = fonte;
    }

    public BigDecimal getIndiceagente() {
        return indiceagente;
    }

    public void setIndiceagente(BigDecimal indiceagente) {
        this.indiceagente = indiceagente;
    }

    public BigDecimal getIndicelocal() {
        return indicelocal;
    }

    public void setIndicelocal(BigDecimal indicelocal) {
        this.indicelocal = indicelocal;
    }

    public Long getIdcontabancariaId() {
        return idcontabancariaId;
    }

    public void setIdcontabancariaId(Long contabancariaId) {
        this.idcontabancariaId = contabancariaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FonteDTO fonteDTO = (FonteDTO) o;
        if(fonteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fonteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FonteDTO{" +
            "id=" + getId() +
            ", descricao='" + getDescricao() + "'" +
            ", fonte='" + getFonte() + "'" +
            ", indiceagente='" + getIndiceagente() + "'" +
            ", indicelocal='" + getIndicelocal() + "'" +
            "}";
    }
}
