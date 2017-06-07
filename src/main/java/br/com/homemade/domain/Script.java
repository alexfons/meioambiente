package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Script.
 */
@Entity
@Table(name = "script")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Script implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "data_verificacao_licencas")
    private ZonedDateTime dataVerificacaoLicencas;

    @Column(name = "descricao")
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

    public Script codigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public ZonedDateTime getDataVerificacaoLicencas() {
        return dataVerificacaoLicencas;
    }

    public Script dataVerificacaoLicencas(ZonedDateTime dataVerificacaoLicencas) {
        this.dataVerificacaoLicencas = dataVerificacaoLicencas;
        return this;
    }

    public void setDataVerificacaoLicencas(ZonedDateTime dataVerificacaoLicencas) {
        this.dataVerificacaoLicencas = dataVerificacaoLicencas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Script descricao(String descricao) {
        this.descricao = descricao;
        return this;
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
        Script script = (Script) o;
        if (script.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), script.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Script{" +
            "id=" + getId() +
            ", codigo='" + getCodigo() + "'" +
            ", dataVerificacaoLicencas='" + getDataVerificacaoLicencas() + "'" +
            ", descricao='" + getDescricao() + "'" +
            "}";
    }
}
