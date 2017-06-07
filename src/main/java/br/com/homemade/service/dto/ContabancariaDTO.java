package br.com.homemade.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Contabancaria entity.
 */
public class ContabancariaDTO implements Serializable {

    private Long id;

    private Integer idcontabancaria;

    private String nconta;

    private String nbanco;

    private String descricao;

    private Long idplanocontasId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdcontabancaria() {
        return idcontabancaria;
    }

    public void setIdcontabancaria(Integer idcontabancaria) {
        this.idcontabancaria = idcontabancaria;
    }

    public String getNconta() {
        return nconta;
    }

    public void setNconta(String nconta) {
        this.nconta = nconta;
    }

    public String getNbanco() {
        return nbanco;
    }

    public void setNbanco(String nbanco) {
        this.nbanco = nbanco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

        ContabancariaDTO contabancariaDTO = (ContabancariaDTO) o;
        if(contabancariaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contabancariaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ContabancariaDTO{" +
            "id=" + getId() +
            ", idcontabancaria='" + getIdcontabancaria() + "'" +
            ", nconta='" + getNconta() + "'" +
            ", nbanco='" + getNbanco() + "'" +
            ", descricao='" + getDescricao() + "'" +
            "}";
    }
}
