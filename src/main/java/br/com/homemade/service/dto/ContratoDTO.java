package br.com.homemade.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Contrato entity.
 */
public class ContratoDTO implements Serializable {

    private Long id;

    private String codigobid;

    private ZonedDateTime dataatual;

    private ZonedDateTime databasecontrato;

    private ZonedDateTime datacontratacao;

    private ZonedDateTime dataterminocaucao;

    private BigDecimal extatualcontrato;

    private BigDecimal extinicialcontrato;

    private String ncontrato;

    private String ordemservico;

    private Integer prazoatual;

    private Integer prazoinicial;

    private String rodoviacontrato;

    private BigDecimal saldocontratual;

    private String situacao;

    private BigDecimal taxaatual;

    private BigDecimal taxaoriginal;

    private String tipocontrato;

    private String titulocontrato;

    private String trechocontrato;

    private BigDecimal valoratual;

    private BigDecimal valorcaucao;

    private BigDecimal valorpi;

    private BigDecimal valorreajuste;

    private Long idempresaId;

    private Long contacontabilC2Id;

    private Long contacontabilD1Id;

    private Long contacontabilD2Id;

    private Long divcontacontabilC1Id;

    private Long divcontacontabilD1Id;

    private Long juscontacontabilC1Id;

    private Long juscontacontabilD1Id;

    private Long idnaturezaId;

    private Long propostaId;

    private Long idcategoriainversaoId;

    private Set<AditivocontratoDTO> aditivocontratos = new HashSet<>();

    private Set<ContratoobraDTO> contratosobras = new HashSet<>();

    private Set<EmpresacontratoDTO> empresascontratoes = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigobid() {
        return codigobid;
    }

    public void setCodigobid(String codigobid) {
        this.codigobid = codigobid;
    }

    public ZonedDateTime getDataatual() {
        return dataatual;
    }

    public void setDataatual(ZonedDateTime dataatual) {
        this.dataatual = dataatual;
    }

    public ZonedDateTime getDatabasecontrato() {
        return databasecontrato;
    }

    public void setDatabasecontrato(ZonedDateTime databasecontrato) {
        this.databasecontrato = databasecontrato;
    }

    public ZonedDateTime getDatacontratacao() {
        return datacontratacao;
    }

    public void setDatacontratacao(ZonedDateTime datacontratacao) {
        this.datacontratacao = datacontratacao;
    }

    public ZonedDateTime getDataterminocaucao() {
        return dataterminocaucao;
    }

    public void setDataterminocaucao(ZonedDateTime dataterminocaucao) {
        this.dataterminocaucao = dataterminocaucao;
    }

    public BigDecimal getExtatualcontrato() {
        return extatualcontrato;
    }

    public void setExtatualcontrato(BigDecimal extatualcontrato) {
        this.extatualcontrato = extatualcontrato;
    }

    public BigDecimal getExtinicialcontrato() {
        return extinicialcontrato;
    }

    public void setExtinicialcontrato(BigDecimal extinicialcontrato) {
        this.extinicialcontrato = extinicialcontrato;
    }

    public String getNcontrato() {
        return ncontrato;
    }

    public void setNcontrato(String ncontrato) {
        this.ncontrato = ncontrato;
    }

    public String getOrdemservico() {
        return ordemservico;
    }

    public void setOrdemservico(String ordemservico) {
        this.ordemservico = ordemservico;
    }

    public Integer getPrazoatual() {
        return prazoatual;
    }

    public void setPrazoatual(Integer prazoatual) {
        this.prazoatual = prazoatual;
    }

    public Integer getPrazoinicial() {
        return prazoinicial;
    }

    public void setPrazoinicial(Integer prazoinicial) {
        this.prazoinicial = prazoinicial;
    }

    public String getRodoviacontrato() {
        return rodoviacontrato;
    }

    public void setRodoviacontrato(String rodoviacontrato) {
        this.rodoviacontrato = rodoviacontrato;
    }

    public BigDecimal getSaldocontratual() {
        return saldocontratual;
    }

    public void setSaldocontratual(BigDecimal saldocontratual) {
        this.saldocontratual = saldocontratual;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public BigDecimal getTaxaatual() {
        return taxaatual;
    }

    public void setTaxaatual(BigDecimal taxaatual) {
        this.taxaatual = taxaatual;
    }

    public BigDecimal getTaxaoriginal() {
        return taxaoriginal;
    }

    public void setTaxaoriginal(BigDecimal taxaoriginal) {
        this.taxaoriginal = taxaoriginal;
    }

    public String getTipocontrato() {
        return tipocontrato;
    }

    public void setTipocontrato(String tipocontrato) {
        this.tipocontrato = tipocontrato;
    }

    public String getTitulocontrato() {
        return titulocontrato;
    }

    public void setTitulocontrato(String titulocontrato) {
        this.titulocontrato = titulocontrato;
    }

    public String getTrechocontrato() {
        return trechocontrato;
    }

    public void setTrechocontrato(String trechocontrato) {
        this.trechocontrato = trechocontrato;
    }

    public BigDecimal getValoratual() {
        return valoratual;
    }

    public void setValoratual(BigDecimal valoratual) {
        this.valoratual = valoratual;
    }

    public BigDecimal getValorcaucao() {
        return valorcaucao;
    }

    public void setValorcaucao(BigDecimal valorcaucao) {
        this.valorcaucao = valorcaucao;
    }

    public BigDecimal getValorpi() {
        return valorpi;
    }

    public void setValorpi(BigDecimal valorpi) {
        this.valorpi = valorpi;
    }

    public BigDecimal getValorreajuste() {
        return valorreajuste;
    }

    public void setValorreajuste(BigDecimal valorreajuste) {
        this.valorreajuste = valorreajuste;
    }

    public Long getIdempresaId() {
        return idempresaId;
    }

    public void setIdempresaId(Long empresaId) {
        this.idempresaId = empresaId;
    }

    public Long getContacontabilC2Id() {
        return contacontabilC2Id;
    }

    public void setContacontabilC2Id(Long planocontasId) {
        this.contacontabilC2Id = planocontasId;
    }

    public Long getContacontabilD1Id() {
        return contacontabilD1Id;
    }

    public void setContacontabilD1Id(Long planocontasId) {
        this.contacontabilD1Id = planocontasId;
    }

    public Long getContacontabilD2Id() {
        return contacontabilD2Id;
    }

    public void setContacontabilD2Id(Long planocontasId) {
        this.contacontabilD2Id = planocontasId;
    }

    public Long getDivcontacontabilC1Id() {
        return divcontacontabilC1Id;
    }

    public void setDivcontacontabilC1Id(Long planocontasId) {
        this.divcontacontabilC1Id = planocontasId;
    }

    public Long getDivcontacontabilD1Id() {
        return divcontacontabilD1Id;
    }

    public void setDivcontacontabilD1Id(Long planocontasId) {
        this.divcontacontabilD1Id = planocontasId;
    }

    public Long getJuscontacontabilC1Id() {
        return juscontacontabilC1Id;
    }

    public void setJuscontacontabilC1Id(Long planocontasId) {
        this.juscontacontabilC1Id = planocontasId;
    }

    public Long getJuscontacontabilD1Id() {
        return juscontacontabilD1Id;
    }

    public void setJuscontacontabilD1Id(Long planocontasId) {
        this.juscontacontabilD1Id = planocontasId;
    }

    public Long getIdnaturezaId() {
        return idnaturezaId;
    }

    public void setIdnaturezaId(Long naturezaId) {
        this.idnaturezaId = naturezaId;
    }

    public Long getPropostaId() {
        return propostaId;
    }

    public void setPropostaId(Long propostaId) {
        this.propostaId = propostaId;
    }

    public Long getIdcategoriainversaoId() {
        return idcategoriainversaoId;
    }

    public void setIdcategoriainversaoId(Long categoriainversaoId) {
        this.idcategoriainversaoId = categoriainversaoId;
    }

    public Set<AditivocontratoDTO> getAditivocontratos() {
        return aditivocontratos;
    }

    public void setAditivocontratos(Set<AditivocontratoDTO> aditivocontratoes) {
        this.aditivocontratos = aditivocontratoes;
    }

    public Set<ContratoobraDTO> getContratosobras() {
        return contratosobras;
    }

    public void setContratosobras(Set<ContratoobraDTO> contratoobras) {
        this.contratosobras = contratoobras;
    }

    public Set<EmpresacontratoDTO> getEmpresascontratoes() {
        return empresascontratoes;
    }

    public void setEmpresascontratoes(Set<EmpresacontratoDTO> empresacontratoes) {
        this.empresascontratoes = empresacontratoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ContratoDTO contratoDTO = (ContratoDTO) o;
        if(contratoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contratoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ContratoDTO{" +
            "id=" + getId() +
            ", codigobid='" + getCodigobid() + "'" +
            ", dataatual='" + getDataatual() + "'" +
            ", databasecontrato='" + getDatabasecontrato() + "'" +
            ", datacontratacao='" + getDatacontratacao() + "'" +
            ", dataterminocaucao='" + getDataterminocaucao() + "'" +
            ", extatualcontrato='" + getExtatualcontrato() + "'" +
            ", extinicialcontrato='" + getExtinicialcontrato() + "'" +
            ", ncontrato='" + getNcontrato() + "'" +
            ", ordemservico='" + getOrdemservico() + "'" +
            ", prazoatual='" + getPrazoatual() + "'" +
            ", prazoinicial='" + getPrazoinicial() + "'" +
            ", rodoviacontrato='" + getRodoviacontrato() + "'" +
            ", saldocontratual='" + getSaldocontratual() + "'" +
            ", situacao='" + getSituacao() + "'" +
            ", taxaatual='" + getTaxaatual() + "'" +
            ", taxaoriginal='" + getTaxaoriginal() + "'" +
            ", tipocontrato='" + getTipocontrato() + "'" +
            ", titulocontrato='" + getTitulocontrato() + "'" +
            ", trechocontrato='" + getTrechocontrato() + "'" +
            ", valoratual='" + getValoratual() + "'" +
            ", valorcaucao='" + getValorcaucao() + "'" +
            ", valorpi='" + getValorpi() + "'" +
            ", valorreajuste='" + getValorreajuste() + "'" +
            "}";
    }
}
