package br.com.homemade.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Contratoagente entity.
 */
public class ContratoagenteDTO implements Serializable {

    private Long id;

    private ZonedDateTime dataaprovacao;

    private ZonedDateTime dataassinatura;

    private ZonedDateTime dataconclusao;

    private ZonedDateTime datainicio;

    private Integer idcontratoagente;

    private String nomecontratoagente;

    private String referenciabid;

    private String acordocredito;

    private String mutuario;

    private String executor;

    private String clausulascontratuais;

    private String descricao;

    private String moeda;

    private Double percentuallocal;

    private Double percentualagente;

    private Double valorcontrato;

    private Double valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDataaprovacao() {
        return dataaprovacao;
    }

    public void setDataaprovacao(ZonedDateTime dataaprovacao) {
        this.dataaprovacao = dataaprovacao;
    }

    public ZonedDateTime getDataassinatura() {
        return dataassinatura;
    }

    public void setDataassinatura(ZonedDateTime dataassinatura) {
        this.dataassinatura = dataassinatura;
    }

    public ZonedDateTime getDataconclusao() {
        return dataconclusao;
    }

    public void setDataconclusao(ZonedDateTime dataconclusao) {
        this.dataconclusao = dataconclusao;
    }

    public ZonedDateTime getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(ZonedDateTime datainicio) {
        this.datainicio = datainicio;
    }

    public Integer getIdcontratoagente() {
        return idcontratoagente;
    }

    public void setIdcontratoagente(Integer idcontratoagente) {
        this.idcontratoagente = idcontratoagente;
    }

    public String getNomecontratoagente() {
        return nomecontratoagente;
    }

    public void setNomecontratoagente(String nomecontratoagente) {
        this.nomecontratoagente = nomecontratoagente;
    }

    public String getReferenciabid() {
        return referenciabid;
    }

    public void setReferenciabid(String referenciabid) {
        this.referenciabid = referenciabid;
    }

    public String getAcordocredito() {
        return acordocredito;
    }

    public void setAcordocredito(String acordocredito) {
        this.acordocredito = acordocredito;
    }

    public String getMutuario() {
        return mutuario;
    }

    public void setMutuario(String mutuario) {
        this.mutuario = mutuario;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getClausulascontratuais() {
        return clausulascontratuais;
    }

    public void setClausulascontratuais(String clausulascontratuais) {
        this.clausulascontratuais = clausulascontratuais;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public Double getPercentuallocal() {
        return percentuallocal;
    }

    public void setPercentuallocal(Double percentuallocal) {
        this.percentuallocal = percentuallocal;
    }

    public Double getPercentualagente() {
        return percentualagente;
    }

    public void setPercentualagente(Double percentualagente) {
        this.percentualagente = percentualagente;
    }

    public Double getValorcontrato() {
        return valorcontrato;
    }

    public void setValorcontrato(Double valorcontrato) {
        this.valorcontrato = valorcontrato;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ContratoagenteDTO contratoagenteDTO = (ContratoagenteDTO) o;
        if(contratoagenteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contratoagenteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ContratoagenteDTO{" +
            "id=" + getId() +
            ", dataaprovacao='" + getDataaprovacao() + "'" +
            ", dataassinatura='" + getDataassinatura() + "'" +
            ", dataconclusao='" + getDataconclusao() + "'" +
            ", datainicio='" + getDatainicio() + "'" +
            ", idcontratoagente='" + getIdcontratoagente() + "'" +
            ", nomecontratoagente='" + getNomecontratoagente() + "'" +
            ", referenciabid='" + getReferenciabid() + "'" +
            ", acordocredito='" + getAcordocredito() + "'" +
            ", mutuario='" + getMutuario() + "'" +
            ", executor='" + getExecutor() + "'" +
            ", clausulascontratuais='" + getClausulascontratuais() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", moeda='" + getMoeda() + "'" +
            ", percentuallocal='" + getPercentuallocal() + "'" +
            ", percentualagente='" + getPercentualagente() + "'" +
            ", valorcontrato='" + getValorcontrato() + "'" +
            ", valor='" + getValor() + "'" +
            "}";
    }
}
