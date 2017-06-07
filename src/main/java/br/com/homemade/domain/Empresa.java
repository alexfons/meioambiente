package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Empresa.
 */
@Entity
@Table(name = "empresa")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "contato")
    private String contato;

    @Column(name = "email")
    private String email;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "idempresa")
    private Integer idempresa;

    @Column(name = "nomeempresa")
    private String nomeempresa;

    @Column(name = "numero")
    private String numero;

    @Column(name = "pais")
    private String pais;

    @Column(name = "telefonecontato")
    private String telefonecontato;

    @Column(name = "uf")
    private String uf;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBairro() {
        return bairro;
    }

    public Empresa bairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public Empresa cidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCnpj() {
        return cnpj;
    }

    public Empresa cnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getContato() {
        return contato;
    }

    public Empresa contato(String contato) {
        this.contato = contato;
        return this;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getEmail() {
        return email;
    }

    public Empresa email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public Empresa endereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getIdempresa() {
        return idempresa;
    }

    public Empresa idempresa(Integer idempresa) {
        this.idempresa = idempresa;
        return this;
    }

    public void setIdempresa(Integer idempresa) {
        this.idempresa = idempresa;
    }

    public String getNomeempresa() {
        return nomeempresa;
    }

    public Empresa nomeempresa(String nomeempresa) {
        this.nomeempresa = nomeempresa;
        return this;
    }

    public void setNomeempresa(String nomeempresa) {
        this.nomeempresa = nomeempresa;
    }

    public String getNumero() {
        return numero;
    }

    public Empresa numero(String numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPais() {
        return pais;
    }

    public Empresa pais(String pais) {
        this.pais = pais;
        return this;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTelefonecontato() {
        return telefonecontato;
    }

    public Empresa telefonecontato(String telefonecontato) {
        this.telefonecontato = telefonecontato;
        return this;
    }

    public void setTelefonecontato(String telefonecontato) {
        this.telefonecontato = telefonecontato;
    }

    public String getUf() {
        return uf;
    }

    public Empresa uf(String uf) {
        this.uf = uf;
        return this;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Empresa empresa = (Empresa) o;
        if (empresa.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), empresa.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Empresa{" +
            "id=" + getId() +
            ", bairro='" + getBairro() + "'" +
            ", cidade='" + getCidade() + "'" +
            ", cnpj='" + getCnpj() + "'" +
            ", contato='" + getContato() + "'" +
            ", email='" + getEmail() + "'" +
            ", endereco='" + getEndereco() + "'" +
            ", idempresa='" + getIdempresa() + "'" +
            ", nomeempresa='" + getNomeempresa() + "'" +
            ", numero='" + getNumero() + "'" +
            ", pais='" + getPais() + "'" +
            ", telefonecontato='" + getTelefonecontato() + "'" +
            ", uf='" + getUf() + "'" +
            "}";
    }
}
