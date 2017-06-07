package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Log.
 */
@Entity
@Table(name = "log")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "data")
    private ZonedDateTime data;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "metodo")
    private String metodo;

    @ManyToOne
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getData() {
        return data;
    }

    public Log data(ZonedDateTime data) {
        this.data = data;
        return this;
    }

    public void setData(ZonedDateTime data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public Log descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMetodo() {
        return metodo;
    }

    public Log metodo(String metodo) {
        this.metodo = metodo;
        return this;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Log usuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Log log = (Log) o;
        if (log.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), log.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Log{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", metodo='" + getMetodo() + "'" +
            "}";
    }
}
