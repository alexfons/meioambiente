package br.com.homemade.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Movimentacao.
 */
@Entity
@Table(name = "movimentacao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Movimentacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "data")
    private ZonedDateTime data;

    @Column(name = "tipomovimentacao")
    private String tipomovimentacao;

    @OneToMany(mappedBy = "movimentacao")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Listamovimentacao> listamovimentacaos = new HashSet<>();

    @ManyToOne
    private Contabancaria contabancaria;

    @ManyToOne
    private Fonte fonte;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getData() {
        return data;
    }

    public Movimentacao data(ZonedDateTime data) {
        this.data = data;
        return this;
    }

    public void setData(ZonedDateTime data) {
        this.data = data;
    }

    public String getTipomovimentacao() {
        return tipomovimentacao;
    }

    public Movimentacao tipomovimentacao(String tipomovimentacao) {
        this.tipomovimentacao = tipomovimentacao;
        return this;
    }

    public void setTipomovimentacao(String tipomovimentacao) {
        this.tipomovimentacao = tipomovimentacao;
    }

    public Set<Listamovimentacao> getListamovimentacaos() {
        return listamovimentacaos;
    }

    public Movimentacao listamovimentacaos(Set<Listamovimentacao> listamovimentacaos) {
        this.listamovimentacaos = listamovimentacaos;
        return this;
    }

    public Movimentacao addListamovimentacao(Listamovimentacao listamovimentacao) {
        this.listamovimentacaos.add(listamovimentacao);
        listamovimentacao.setMovimentacao(this);
        return this;
    }

    public Movimentacao removeListamovimentacao(Listamovimentacao listamovimentacao) {
        this.listamovimentacaos.remove(listamovimentacao);
        listamovimentacao.setMovimentacao(null);
        return this;
    }

    public void setListamovimentacaos(Set<Listamovimentacao> listamovimentacaos) {
        this.listamovimentacaos = listamovimentacaos;
    }

    public Contabancaria getContabancaria() {
        return contabancaria;
    }

    public Movimentacao contabancaria(Contabancaria contabancaria) {
        this.contabancaria = contabancaria;
        return this;
    }

    public void setContabancaria(Contabancaria contabancaria) {
        this.contabancaria = contabancaria;
    }

    public Fonte getFonte() {
        return fonte;
    }

    public Movimentacao fonte(Fonte fonte) {
        this.fonte = fonte;
        return this;
    }

    public void setFonte(Fonte fonte) {
        this.fonte = fonte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Movimentacao movimentacao = (Movimentacao) o;
        if (movimentacao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), movimentacao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Movimentacao{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            ", tipomovimentacao='" + getTipomovimentacao() + "'" +
            "}";
    }
}
