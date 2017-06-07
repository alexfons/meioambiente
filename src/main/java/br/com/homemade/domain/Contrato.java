package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Contrato.
 */
@Entity
@Table(name = "contrato")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Contrato implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "codigobid")
    private String codigobid;

    @Column(name = "dataatual")
    private ZonedDateTime dataatual;

    @Column(name = "databasecontrato")
    private ZonedDateTime databasecontrato;

    @Column(name = "datacontratacao")
    private ZonedDateTime datacontratacao;

    @Column(name = "dataterminocaucao")
    private ZonedDateTime dataterminocaucao;

    @Column(name = "extatualcontrato", precision=10, scale=2)
    private BigDecimal extatualcontrato;

    @Column(name = "extinicialcontrato", precision=10, scale=2)
    private BigDecimal extinicialcontrato;

    @Column(name = "ncontrato")
    private String ncontrato;

    @Column(name = "ordemservico")
    private String ordemservico;

    @Column(name = "prazoatual")
    private Integer prazoatual;

    @Column(name = "prazoinicial")
    private Integer prazoinicial;

    @Column(name = "rodoviacontrato")
    private String rodoviacontrato;

    @Column(name = "saldocontratual", precision=10, scale=2)
    private BigDecimal saldocontratual;

    @Column(name = "situacao")
    private String situacao;

    @Column(name = "taxaatual", precision=10, scale=2)
    private BigDecimal taxaatual;

    @Column(name = "taxaoriginal", precision=10, scale=2)
    private BigDecimal taxaoriginal;

    @Column(name = "tipocontrato")
    private String tipocontrato;

    @Column(name = "titulocontrato")
    private String titulocontrato;

    @Column(name = "trechocontrato")
    private String trechocontrato;

    @Column(name = "valoratual", precision=10, scale=2)
    private BigDecimal valoratual;

    @Column(name = "valorcaucao", precision=10, scale=2)
    private BigDecimal valorcaucao;

    @Column(name = "valorpi", precision=10, scale=2)
    private BigDecimal valorpi;

    @Column(name = "valorreajuste", precision=10, scale=2)
    private BigDecimal valorreajuste;

    @OneToOne
    @JoinColumn(unique = true)
    private Empresa idempresa;

    @ManyToOne
    private Planocontas contacontabilC2;

    @ManyToOne
    private Planocontas contacontabilD1;

    @ManyToOne
    private Planocontas contacontabilD2;

    @ManyToOne
    private Planocontas divcontacontabilC1;

    @ManyToOne
    private Planocontas divcontacontabilD1;

    @ManyToOne
    private Planocontas juscontacontabilC1;

    @ManyToOne
    private Planocontas juscontacontabilD1;

    @ManyToOne
    private Natureza idnatureza;

    @ManyToOne
    private Proposta proposta;

    @ManyToOne
    private Categoriainversao idcategoriainversao;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "contrato_aditivocontratos",
               joinColumns = @JoinColumn(name="contratoes_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="aditivocontratos_id", referencedColumnName="id"))
    private Set<Aditivocontrato> aditivocontratos = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "contrato_contratosobra",
               joinColumns = @JoinColumn(name="contratoes_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="contratosobras_id", referencedColumnName="id"))
    private Set<Contratoobra> contratosobras = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "contrato_empresascontrato",
               joinColumns = @JoinColumn(name="contratoes_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="empresascontratoes_id", referencedColumnName="id"))
    private Set<Empresacontrato> empresascontratoes = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigobid() {
        return codigobid;
    }

    public Contrato codigobid(String codigobid) {
        this.codigobid = codigobid;
        return this;
    }

    public void setCodigobid(String codigobid) {
        this.codigobid = codigobid;
    }

    public ZonedDateTime getDataatual() {
        return dataatual;
    }

    public Contrato dataatual(ZonedDateTime dataatual) {
        this.dataatual = dataatual;
        return this;
    }

    public void setDataatual(ZonedDateTime dataatual) {
        this.dataatual = dataatual;
    }

    public ZonedDateTime getDatabasecontrato() {
        return databasecontrato;
    }

    public Contrato databasecontrato(ZonedDateTime databasecontrato) {
        this.databasecontrato = databasecontrato;
        return this;
    }

    public void setDatabasecontrato(ZonedDateTime databasecontrato) {
        this.databasecontrato = databasecontrato;
    }

    public ZonedDateTime getDatacontratacao() {
        return datacontratacao;
    }

    public Contrato datacontratacao(ZonedDateTime datacontratacao) {
        this.datacontratacao = datacontratacao;
        return this;
    }

    public void setDatacontratacao(ZonedDateTime datacontratacao) {
        this.datacontratacao = datacontratacao;
    }

    public ZonedDateTime getDataterminocaucao() {
        return dataterminocaucao;
    }

    public Contrato dataterminocaucao(ZonedDateTime dataterminocaucao) {
        this.dataterminocaucao = dataterminocaucao;
        return this;
    }

    public void setDataterminocaucao(ZonedDateTime dataterminocaucao) {
        this.dataterminocaucao = dataterminocaucao;
    }

    public BigDecimal getExtatualcontrato() {
        return extatualcontrato;
    }

    public Contrato extatualcontrato(BigDecimal extatualcontrato) {
        this.extatualcontrato = extatualcontrato;
        return this;
    }

    public void setExtatualcontrato(BigDecimal extatualcontrato) {
        this.extatualcontrato = extatualcontrato;
    }

    public BigDecimal getExtinicialcontrato() {
        return extinicialcontrato;
    }

    public Contrato extinicialcontrato(BigDecimal extinicialcontrato) {
        this.extinicialcontrato = extinicialcontrato;
        return this;
    }

    public void setExtinicialcontrato(BigDecimal extinicialcontrato) {
        this.extinicialcontrato = extinicialcontrato;
    }

    public String getNcontrato() {
        return ncontrato;
    }

    public Contrato ncontrato(String ncontrato) {
        this.ncontrato = ncontrato;
        return this;
    }

    public void setNcontrato(String ncontrato) {
        this.ncontrato = ncontrato;
    }

    public String getOrdemservico() {
        return ordemservico;
    }

    public Contrato ordemservico(String ordemservico) {
        this.ordemservico = ordemservico;
        return this;
    }

    public void setOrdemservico(String ordemservico) {
        this.ordemservico = ordemservico;
    }

    public Integer getPrazoatual() {
        return prazoatual;
    }

    public Contrato prazoatual(Integer prazoatual) {
        this.prazoatual = prazoatual;
        return this;
    }

    public void setPrazoatual(Integer prazoatual) {
        this.prazoatual = prazoatual;
    }

    public Integer getPrazoinicial() {
        return prazoinicial;
    }

    public Contrato prazoinicial(Integer prazoinicial) {
        this.prazoinicial = prazoinicial;
        return this;
    }

    public void setPrazoinicial(Integer prazoinicial) {
        this.prazoinicial = prazoinicial;
    }

    public String getRodoviacontrato() {
        return rodoviacontrato;
    }

    public Contrato rodoviacontrato(String rodoviacontrato) {
        this.rodoviacontrato = rodoviacontrato;
        return this;
    }

    public void setRodoviacontrato(String rodoviacontrato) {
        this.rodoviacontrato = rodoviacontrato;
    }

    public BigDecimal getSaldocontratual() {
        return saldocontratual;
    }

    public Contrato saldocontratual(BigDecimal saldocontratual) {
        this.saldocontratual = saldocontratual;
        return this;
    }

    public void setSaldocontratual(BigDecimal saldocontratual) {
        this.saldocontratual = saldocontratual;
    }

    public String getSituacao() {
        return situacao;
    }

    public Contrato situacao(String situacao) {
        this.situacao = situacao;
        return this;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public BigDecimal getTaxaatual() {
        return taxaatual;
    }

    public Contrato taxaatual(BigDecimal taxaatual) {
        this.taxaatual = taxaatual;
        return this;
    }

    public void setTaxaatual(BigDecimal taxaatual) {
        this.taxaatual = taxaatual;
    }

    public BigDecimal getTaxaoriginal() {
        return taxaoriginal;
    }

    public Contrato taxaoriginal(BigDecimal taxaoriginal) {
        this.taxaoriginal = taxaoriginal;
        return this;
    }

    public void setTaxaoriginal(BigDecimal taxaoriginal) {
        this.taxaoriginal = taxaoriginal;
    }

    public String getTipocontrato() {
        return tipocontrato;
    }

    public Contrato tipocontrato(String tipocontrato) {
        this.tipocontrato = tipocontrato;
        return this;
    }

    public void setTipocontrato(String tipocontrato) {
        this.tipocontrato = tipocontrato;
    }

    public String getTitulocontrato() {
        return titulocontrato;
    }

    public Contrato titulocontrato(String titulocontrato) {
        this.titulocontrato = titulocontrato;
        return this;
    }

    public void setTitulocontrato(String titulocontrato) {
        this.titulocontrato = titulocontrato;
    }

    public String getTrechocontrato() {
        return trechocontrato;
    }

    public Contrato trechocontrato(String trechocontrato) {
        this.trechocontrato = trechocontrato;
        return this;
    }

    public void setTrechocontrato(String trechocontrato) {
        this.trechocontrato = trechocontrato;
    }

    public BigDecimal getValoratual() {
        return valoratual;
    }

    public Contrato valoratual(BigDecimal valoratual) {
        this.valoratual = valoratual;
        return this;
    }

    public void setValoratual(BigDecimal valoratual) {
        this.valoratual = valoratual;
    }

    public BigDecimal getValorcaucao() {
        return valorcaucao;
    }

    public Contrato valorcaucao(BigDecimal valorcaucao) {
        this.valorcaucao = valorcaucao;
        return this;
    }

    public void setValorcaucao(BigDecimal valorcaucao) {
        this.valorcaucao = valorcaucao;
    }

    public BigDecimal getValorpi() {
        return valorpi;
    }

    public Contrato valorpi(BigDecimal valorpi) {
        this.valorpi = valorpi;
        return this;
    }

    public void setValorpi(BigDecimal valorpi) {
        this.valorpi = valorpi;
    }

    public BigDecimal getValorreajuste() {
        return valorreajuste;
    }

    public Contrato valorreajuste(BigDecimal valorreajuste) {
        this.valorreajuste = valorreajuste;
        return this;
    }

    public void setValorreajuste(BigDecimal valorreajuste) {
        this.valorreajuste = valorreajuste;
    }

    public Empresa getIdempresa() {
        return idempresa;
    }

    public Contrato idempresa(Empresa empresa) {
        this.idempresa = empresa;
        return this;
    }

    public void setIdempresa(Empresa empresa) {
        this.idempresa = empresa;
    }

    public Planocontas getContacontabilC2() {
        return contacontabilC2;
    }

    public Contrato contacontabilC2(Planocontas planocontas) {
        this.contacontabilC2 = planocontas;
        return this;
    }

    public void setContacontabilC2(Planocontas planocontas) {
        this.contacontabilC2 = planocontas;
    }

    public Planocontas getContacontabilD1() {
        return contacontabilD1;
    }

    public Contrato contacontabilD1(Planocontas planocontas) {
        this.contacontabilD1 = planocontas;
        return this;
    }

    public void setContacontabilD1(Planocontas planocontas) {
        this.contacontabilD1 = planocontas;
    }

    public Planocontas getContacontabilD2() {
        return contacontabilD2;
    }

    public Contrato contacontabilD2(Planocontas planocontas) {
        this.contacontabilD2 = planocontas;
        return this;
    }

    public void setContacontabilD2(Planocontas planocontas) {
        this.contacontabilD2 = planocontas;
    }

    public Planocontas getDivcontacontabilC1() {
        return divcontacontabilC1;
    }

    public Contrato divcontacontabilC1(Planocontas planocontas) {
        this.divcontacontabilC1 = planocontas;
        return this;
    }

    public void setDivcontacontabilC1(Planocontas planocontas) {
        this.divcontacontabilC1 = planocontas;
    }

    public Planocontas getDivcontacontabilD1() {
        return divcontacontabilD1;
    }

    public Contrato divcontacontabilD1(Planocontas planocontas) {
        this.divcontacontabilD1 = planocontas;
        return this;
    }

    public void setDivcontacontabilD1(Planocontas planocontas) {
        this.divcontacontabilD1 = planocontas;
    }

    public Planocontas getJuscontacontabilC1() {
        return juscontacontabilC1;
    }

    public Contrato juscontacontabilC1(Planocontas planocontas) {
        this.juscontacontabilC1 = planocontas;
        return this;
    }

    public void setJuscontacontabilC1(Planocontas planocontas) {
        this.juscontacontabilC1 = planocontas;
    }

    public Planocontas getJuscontacontabilD1() {
        return juscontacontabilD1;
    }

    public Contrato juscontacontabilD1(Planocontas planocontas) {
        this.juscontacontabilD1 = planocontas;
        return this;
    }

    public void setJuscontacontabilD1(Planocontas planocontas) {
        this.juscontacontabilD1 = planocontas;
    }

    public Natureza getIdnatureza() {
        return idnatureza;
    }

    public Contrato idnatureza(Natureza natureza) {
        this.idnatureza = natureza;
        return this;
    }

    public void setIdnatureza(Natureza natureza) {
        this.idnatureza = natureza;
    }

    public Proposta getProposta() {
        return proposta;
    }

    public Contrato proposta(Proposta proposta) {
        this.proposta = proposta;
        return this;
    }

    public void setProposta(Proposta proposta) {
        this.proposta = proposta;
    }

    public Categoriainversao getIdcategoriainversao() {
        return idcategoriainversao;
    }

    public Contrato idcategoriainversao(Categoriainversao categoriainversao) {
        this.idcategoriainversao = categoriainversao;
        return this;
    }

    public void setIdcategoriainversao(Categoriainversao categoriainversao) {
        this.idcategoriainversao = categoriainversao;
    }

    public Set<Aditivocontrato> getAditivocontratos() {
        return aditivocontratos;
    }

    public Contrato aditivocontratos(Set<Aditivocontrato> aditivocontratoes) {
        this.aditivocontratos = aditivocontratoes;
        return this;
    }

    public Contrato addAditivocontratos(Aditivocontrato aditivocontrato) {
        this.aditivocontratos.add(aditivocontrato);
        return this;
    }

    public Contrato removeAditivocontratos(Aditivocontrato aditivocontrato) {
        this.aditivocontratos.remove(aditivocontrato);
        return this;
    }

    public void setAditivocontratos(Set<Aditivocontrato> aditivocontratoes) {
        this.aditivocontratos = aditivocontratoes;
    }

    public Set<Contratoobra> getContratosobras() {
        return contratosobras;
    }

    public Contrato contratosobras(Set<Contratoobra> contratoobras) {
        this.contratosobras = contratoobras;
        return this;
    }

    public Contrato addContratosobra(Contratoobra contratoobra) {
        this.contratosobras.add(contratoobra);
        return this;
    }

    public Contrato removeContratosobra(Contratoobra contratoobra) {
        this.contratosobras.remove(contratoobra);
        return this;
    }

    public void setContratosobras(Set<Contratoobra> contratoobras) {
        this.contratosobras = contratoobras;
    }

    public Set<Empresacontrato> getEmpresascontratoes() {
        return empresascontratoes;
    }

    public Contrato empresascontratoes(Set<Empresacontrato> empresacontratoes) {
        this.empresascontratoes = empresacontratoes;
        return this;
    }

    public Contrato addEmpresascontrato(Empresacontrato empresacontrato) {
        this.empresascontratoes.add(empresacontrato);
        return this;
    }

    public Contrato removeEmpresascontrato(Empresacontrato empresacontrato) {
        this.empresascontratoes.remove(empresacontrato);
        return this;
    }

    public void setEmpresascontratoes(Set<Empresacontrato> empresacontratoes) {
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
        Contrato contrato = (Contrato) o;
        if (contrato.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contrato.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Contrato{" +
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
