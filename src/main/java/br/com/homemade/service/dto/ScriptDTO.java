package br.com.homemade.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Script entity.
 */
public class ScriptDTO implements Serializable {

    private Long id;

    private String codigo;

    private ZonedDateTime dataVerificacaoLicencas;

    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public ZonedDateTime getDataVerificacaoLicencas() {
        return dataVerificacaoLicencas;
    }

    public void setDataVerificacaoLicencas(ZonedDateTime dataVerificacaoLicencas) {
        this.dataVerificacaoLicencas = dataVerificacaoLicencas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ScriptDTO scriptDTO = (ScriptDTO) o;
        if(scriptDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), scriptDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ScriptDTO{" +
            "id=" + getId() +
            ", codigo='" + getCodigo() + "'" +
            ", dataVerificacaoLicencas='" + getDataVerificacaoLicencas() + "'" +
            ", descricao='" + getDescricao() + "'" +
            "}";
    }
}
