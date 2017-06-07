package br.com.homemade.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Banco entity.
 */
public class BancoDTO implements Serializable {

    private Long id;

    private String cidadedep;

    private String cidadeint;

    private String codswifdep;

    private String codswifint;

    private Boolean contafeddep;

    private Boolean contafedint;

    private String enderecodep;

    private String enderecoint;

    private Integer idbanco;

    private String instrucoesespeciaisdep;

    private String instrucoesespeciaisint;

    private Integer nabadep;

    private Integer nabaint;

    private Integer ncontadep;

    private Integer ncontaint;

    private String nomebancodep;

    private String nomebancoint;

    private String paisdep;

    private String paisint;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCidadedep() {
        return cidadedep;
    }

    public void setCidadedep(String cidadedep) {
        this.cidadedep = cidadedep;
    }

    public String getCidadeint() {
        return cidadeint;
    }

    public void setCidadeint(String cidadeint) {
        this.cidadeint = cidadeint;
    }

    public String getCodswifdep() {
        return codswifdep;
    }

    public void setCodswifdep(String codswifdep) {
        this.codswifdep = codswifdep;
    }

    public String getCodswifint() {
        return codswifint;
    }

    public void setCodswifint(String codswifint) {
        this.codswifint = codswifint;
    }

    public Boolean isContafeddep() {
        return contafeddep;
    }

    public void setContafeddep(Boolean contafeddep) {
        this.contafeddep = contafeddep;
    }

    public Boolean isContafedint() {
        return contafedint;
    }

    public void setContafedint(Boolean contafedint) {
        this.contafedint = contafedint;
    }

    public String getEnderecodep() {
        return enderecodep;
    }

    public void setEnderecodep(String enderecodep) {
        this.enderecodep = enderecodep;
    }

    public String getEnderecoint() {
        return enderecoint;
    }

    public void setEnderecoint(String enderecoint) {
        this.enderecoint = enderecoint;
    }

    public Integer getIdbanco() {
        return idbanco;
    }

    public void setIdbanco(Integer idbanco) {
        this.idbanco = idbanco;
    }

    public String getInstrucoesespeciaisdep() {
        return instrucoesespeciaisdep;
    }

    public void setInstrucoesespeciaisdep(String instrucoesespeciaisdep) {
        this.instrucoesespeciaisdep = instrucoesespeciaisdep;
    }

    public String getInstrucoesespeciaisint() {
        return instrucoesespeciaisint;
    }

    public void setInstrucoesespeciaisint(String instrucoesespeciaisint) {
        this.instrucoesespeciaisint = instrucoesespeciaisint;
    }

    public Integer getNabadep() {
        return nabadep;
    }

    public void setNabadep(Integer nabadep) {
        this.nabadep = nabadep;
    }

    public Integer getNabaint() {
        return nabaint;
    }

    public void setNabaint(Integer nabaint) {
        this.nabaint = nabaint;
    }

    public Integer getNcontadep() {
        return ncontadep;
    }

    public void setNcontadep(Integer ncontadep) {
        this.ncontadep = ncontadep;
    }

    public Integer getNcontaint() {
        return ncontaint;
    }

    public void setNcontaint(Integer ncontaint) {
        this.ncontaint = ncontaint;
    }

    public String getNomebancodep() {
        return nomebancodep;
    }

    public void setNomebancodep(String nomebancodep) {
        this.nomebancodep = nomebancodep;
    }

    public String getNomebancoint() {
        return nomebancoint;
    }

    public void setNomebancoint(String nomebancoint) {
        this.nomebancoint = nomebancoint;
    }

    public String getPaisdep() {
        return paisdep;
    }

    public void setPaisdep(String paisdep) {
        this.paisdep = paisdep;
    }

    public String getPaisint() {
        return paisint;
    }

    public void setPaisint(String paisint) {
        this.paisint = paisint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BancoDTO bancoDTO = (BancoDTO) o;
        if(bancoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bancoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BancoDTO{" +
            "id=" + getId() +
            ", cidadedep='" + getCidadedep() + "'" +
            ", cidadeint='" + getCidadeint() + "'" +
            ", codswifdep='" + getCodswifdep() + "'" +
            ", codswifint='" + getCodswifint() + "'" +
            ", contafeddep='" + isContafeddep() + "'" +
            ", contafedint='" + isContafedint() + "'" +
            ", enderecodep='" + getEnderecodep() + "'" +
            ", enderecoint='" + getEnderecoint() + "'" +
            ", idbanco='" + getIdbanco() + "'" +
            ", instrucoesespeciaisdep='" + getInstrucoesespeciaisdep() + "'" +
            ", instrucoesespeciaisint='" + getInstrucoesespeciaisint() + "'" +
            ", nabadep='" + getNabadep() + "'" +
            ", nabaint='" + getNabaint() + "'" +
            ", ncontadep='" + getNcontadep() + "'" +
            ", ncontaint='" + getNcontaint() + "'" +
            ", nomebancodep='" + getNomebancodep() + "'" +
            ", nomebancoint='" + getNomebancoint() + "'" +
            ", paisdep='" + getPaisdep() + "'" +
            ", paisint='" + getPaisint() + "'" +
            "}";
    }
}
