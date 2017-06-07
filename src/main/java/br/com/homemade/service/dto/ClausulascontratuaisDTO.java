package br.com.homemade.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Clausulascontratuais entity.
 */
public class ClausulascontratuaisDTO implements Serializable {

    private Long id;

    private String artigo;

    private String descricao;

    private String noficioenviado;

    private String noficioaprovado;

    private ZonedDateTime dataaprovacao;

    private ZonedDateTime dataenvio;

    private ZonedDateTime datavigente;

    private Integer idclausulascontratuais;

    private Long idcontratoagenteId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArtigo() {
        return artigo;
    }

    public void setArtigo(String artigo) {
        this.artigo = artigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNoficioenviado() {
        return noficioenviado;
    }

    public void setNoficioenviado(String noficioenviado) {
        this.noficioenviado = noficioenviado;
    }

    public String getNoficioaprovado() {
        return noficioaprovado;
    }

    public void setNoficioaprovado(String noficioaprovado) {
        this.noficioaprovado = noficioaprovado;
    }

    public ZonedDateTime getDataaprovacao() {
        return dataaprovacao;
    }

    public void setDataaprovacao(ZonedDateTime dataaprovacao) {
        this.dataaprovacao = dataaprovacao;
    }

    public ZonedDateTime getDataenvio() {
        return dataenvio;
    }

    public void setDataenvio(ZonedDateTime dataenvio) {
        this.dataenvio = dataenvio;
    }

    public ZonedDateTime getDatavigente() {
        return datavigente;
    }

    public void setDatavigente(ZonedDateTime datavigente) {
        this.datavigente = datavigente;
    }

    public Integer getIdclausulascontratuais() {
        return idclausulascontratuais;
    }

    public void setIdclausulascontratuais(Integer idclausulascontratuais) {
        this.idclausulascontratuais = idclausulascontratuais;
    }

    public Long getIdcontratoagenteId() {
        return idcontratoagenteId;
    }

    public void setIdcontratoagenteId(Long contratoagenteId) {
        this.idcontratoagenteId = contratoagenteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ClausulascontratuaisDTO clausulascontratuaisDTO = (ClausulascontratuaisDTO) o;
        if(clausulascontratuaisDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), clausulascontratuaisDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ClausulascontratuaisDTO{" +
            "id=" + getId() +
            ", artigo='" + getArtigo() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", noficioenviado='" + getNoficioenviado() + "'" +
            ", noficioaprovado='" + getNoficioaprovado() + "'" +
            ", dataaprovacao='" + getDataaprovacao() + "'" +
            ", dataenvio='" + getDataenvio() + "'" +
            ", datavigente='" + getDatavigente() + "'" +
            ", idclausulascontratuais='" + getIdclausulascontratuais() + "'" +
            "}";
    }
}
