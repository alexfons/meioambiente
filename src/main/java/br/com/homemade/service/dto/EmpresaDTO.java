package br.com.homemade.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Empresa entity.
 */
public class EmpresaDTO implements Serializable {

    private Long id;

    private String bairro;

    private String cidade;

    private String cnpj;

    private String contato;

    private String email;

    private String endereco;

    private Integer idempresa;

    private String nomeempresa;

    private String numero;

    private String pais;

    private String telefonecontato;

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

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Integer idempresa) {
        this.idempresa = idempresa;
    }

    public String getNomeempresa() {
        return nomeempresa;
    }

    public void setNomeempresa(String nomeempresa) {
        this.nomeempresa = nomeempresa;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTelefonecontato() {
        return telefonecontato;
    }

    public void setTelefonecontato(String telefonecontato) {
        this.telefonecontato = telefonecontato;
    }

    public String getUf() {
        return uf;
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

        EmpresaDTO empresaDTO = (EmpresaDTO) o;
        if(empresaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), empresaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EmpresaDTO{" +
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
