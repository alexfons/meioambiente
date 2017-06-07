package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Autorizacao.
 */
@Entity
@Table(name = "autorizacao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Autorizacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "album")
    private String album;

    @Column(name = "andamento")
    private String andamento;

    @Column(name = "data")
    private ZonedDateTime data;

    @Column(name = "dataentregadocs")
    private ZonedDateTime dataentregadocs;

    @Column(name = "dataexpedicaoprorrogacao_1")
    private ZonedDateTime dataexpedicaoprorrogacao1;

    @Column(name = "dataexpedicaoprorrogacao_2")
    private ZonedDateTime dataexpedicaoprorrogacao2;

    @Column(name = "dataexpedicaoprorrogacao_3")
    private ZonedDateTime dataexpedicaoprorrogacao3;

    @Column(name = "datapedidoprorrogacao_1")
    private ZonedDateTime datapedidoprorrogacao1;

    @Column(name = "datapedidoprorrogacao_2")
    private ZonedDateTime datapedidoprorrogacao2;

    @Column(name = "datapedidoprorrogacao_3")
    private ZonedDateTime datapedidoprorrogacao3;

    @Column(name = "datavalidadeprorrogacao_1")
    private ZonedDateTime datavalidadeprorrogacao1;

    @Column(name = "datavalidadeprorrogacao_2")
    private ZonedDateTime datavalidadeprorrogacao2;

    @Column(name = "datavalidadeprorrogacao_3")
    private ZonedDateTime datavalidadeprorrogacao3;

    @Column(name = "datavencimento")
    private ZonedDateTime datavencimento;

    @Column(name = "datavencimentoatual")
    private ZonedDateTime datavencimentoatual;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "docsentregues")
    private String docsentregues;

    @Column(name = "fcei")
    private String fcei;

    @Column(name = "fceidatapagamento")
    private ZonedDateTime fceidatapagamento;

    @Column(name = "fceidataprotocolo")
    private ZonedDateTime fceidataprotocolo;

    @Column(name = "fceivalor")
    private Double fceivalor;

    @Column(name = "folder")
    private String folder;

    @Column(name = "inativo")
    private Boolean inativo;

    @Column(name = "kmfim")
    private Integer kmfim;

    @Column(name = "kminicio")
    private Integer kminicio;

    @Column(name = "lado")
    private String lado;

    @Column(name = "naoria")
    private Boolean naoria;

    @Column(name = "numero")
    private String numero;

    @Column(name = "numerooficioconcessaoprorrogacao_1")
    private String numerooficioconcessaoprorrogacao1;

    @Column(name = "numerooficioconcessaoprorrogacao_2")
    private String numerooficioconcessaoprorrogacao2;

    @Column(name = "numerooficioconcessaoprorrogacao_3")
    private String numerooficioconcessaoprorrogacao3;

    @Column(name = "numerooficioprorrogacao_1")
    private String numerooficioprorrogacao1;

    @Column(name = "numerooficioprorrogacao_2")
    private String numerooficioprorrogacao2;

    @Column(name = "numerooficioprorrogacao_3")
    private String numerooficioprorrogacao3;

    @Column(name = "numeroprocesso")
    private String numeroprocesso;

    @Column(name = "observacao")
    private String observacao;

    @Column(name = "pendente")
    private String pendente;

    @Column(name = "prazomes")
    private Boolean prazomes;

    @Column(name = "prazovalidade")
    private Integer prazovalidade;

    @Column(name = "proprietario")
    private String proprietario;

    @Column(name = "reciboentregadocs")
    private String reciboentregadocs;

    @ManyToOne
    private Atividadelicenca atividadelicenca;

    @ManyToOne
    private Empresa empresa;

    @ManyToOne
    private Obra obra;

    @ManyToOne
    private Orgaoemissor orgaoemissor;

    @ManyToOne
    private Projeto projeto;

    @ManyToOne
    private Tipoautorizacao tipoautorizacao;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "autorizacao_fotos",
               joinColumns = @JoinColumn(name="autorizacaos_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="fotos_id", referencedColumnName="id"))
    private Set<Foto> fotos = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "autorizacao_docs",
               joinColumns = @JoinColumn(name="autorizacaos_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="docs_id", referencedColumnName="id"))
    private Set<Documento> docs = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlbum() {
        return album;
    }

    public Autorizacao album(String album) {
        this.album = album;
        return this;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getAndamento() {
        return andamento;
    }

    public Autorizacao andamento(String andamento) {
        this.andamento = andamento;
        return this;
    }

    public void setAndamento(String andamento) {
        this.andamento = andamento;
    }

    public ZonedDateTime getData() {
        return data;
    }

    public Autorizacao data(ZonedDateTime data) {
        this.data = data;
        return this;
    }

    public void setData(ZonedDateTime data) {
        this.data = data;
    }

    public ZonedDateTime getDataentregadocs() {
        return dataentregadocs;
    }

    public Autorizacao dataentregadocs(ZonedDateTime dataentregadocs) {
        this.dataentregadocs = dataentregadocs;
        return this;
    }

    public void setDataentregadocs(ZonedDateTime dataentregadocs) {
        this.dataentregadocs = dataentregadocs;
    }

    public ZonedDateTime getDataexpedicaoprorrogacao1() {
        return dataexpedicaoprorrogacao1;
    }

    public Autorizacao dataexpedicaoprorrogacao1(ZonedDateTime dataexpedicaoprorrogacao1) {
        this.dataexpedicaoprorrogacao1 = dataexpedicaoprorrogacao1;
        return this;
    }

    public void setDataexpedicaoprorrogacao1(ZonedDateTime dataexpedicaoprorrogacao1) {
        this.dataexpedicaoprorrogacao1 = dataexpedicaoprorrogacao1;
    }

    public ZonedDateTime getDataexpedicaoprorrogacao2() {
        return dataexpedicaoprorrogacao2;
    }

    public Autorizacao dataexpedicaoprorrogacao2(ZonedDateTime dataexpedicaoprorrogacao2) {
        this.dataexpedicaoprorrogacao2 = dataexpedicaoprorrogacao2;
        return this;
    }

    public void setDataexpedicaoprorrogacao2(ZonedDateTime dataexpedicaoprorrogacao2) {
        this.dataexpedicaoprorrogacao2 = dataexpedicaoprorrogacao2;
    }

    public ZonedDateTime getDataexpedicaoprorrogacao3() {
        return dataexpedicaoprorrogacao3;
    }

    public Autorizacao dataexpedicaoprorrogacao3(ZonedDateTime dataexpedicaoprorrogacao3) {
        this.dataexpedicaoprorrogacao3 = dataexpedicaoprorrogacao3;
        return this;
    }

    public void setDataexpedicaoprorrogacao3(ZonedDateTime dataexpedicaoprorrogacao3) {
        this.dataexpedicaoprorrogacao3 = dataexpedicaoprorrogacao3;
    }

    public ZonedDateTime getDatapedidoprorrogacao1() {
        return datapedidoprorrogacao1;
    }

    public Autorizacao datapedidoprorrogacao1(ZonedDateTime datapedidoprorrogacao1) {
        this.datapedidoprorrogacao1 = datapedidoprorrogacao1;
        return this;
    }

    public void setDatapedidoprorrogacao1(ZonedDateTime datapedidoprorrogacao1) {
        this.datapedidoprorrogacao1 = datapedidoprorrogacao1;
    }

    public ZonedDateTime getDatapedidoprorrogacao2() {
        return datapedidoprorrogacao2;
    }

    public Autorizacao datapedidoprorrogacao2(ZonedDateTime datapedidoprorrogacao2) {
        this.datapedidoprorrogacao2 = datapedidoprorrogacao2;
        return this;
    }

    public void setDatapedidoprorrogacao2(ZonedDateTime datapedidoprorrogacao2) {
        this.datapedidoprorrogacao2 = datapedidoprorrogacao2;
    }

    public ZonedDateTime getDatapedidoprorrogacao3() {
        return datapedidoprorrogacao3;
    }

    public Autorizacao datapedidoprorrogacao3(ZonedDateTime datapedidoprorrogacao3) {
        this.datapedidoprorrogacao3 = datapedidoprorrogacao3;
        return this;
    }

    public void setDatapedidoprorrogacao3(ZonedDateTime datapedidoprorrogacao3) {
        this.datapedidoprorrogacao3 = datapedidoprorrogacao3;
    }

    public ZonedDateTime getDatavalidadeprorrogacao1() {
        return datavalidadeprorrogacao1;
    }

    public Autorizacao datavalidadeprorrogacao1(ZonedDateTime datavalidadeprorrogacao1) {
        this.datavalidadeprorrogacao1 = datavalidadeprorrogacao1;
        return this;
    }

    public void setDatavalidadeprorrogacao1(ZonedDateTime datavalidadeprorrogacao1) {
        this.datavalidadeprorrogacao1 = datavalidadeprorrogacao1;
    }

    public ZonedDateTime getDatavalidadeprorrogacao2() {
        return datavalidadeprorrogacao2;
    }

    public Autorizacao datavalidadeprorrogacao2(ZonedDateTime datavalidadeprorrogacao2) {
        this.datavalidadeprorrogacao2 = datavalidadeprorrogacao2;
        return this;
    }

    public void setDatavalidadeprorrogacao2(ZonedDateTime datavalidadeprorrogacao2) {
        this.datavalidadeprorrogacao2 = datavalidadeprorrogacao2;
    }

    public ZonedDateTime getDatavalidadeprorrogacao3() {
        return datavalidadeprorrogacao3;
    }

    public Autorizacao datavalidadeprorrogacao3(ZonedDateTime datavalidadeprorrogacao3) {
        this.datavalidadeprorrogacao3 = datavalidadeprorrogacao3;
        return this;
    }

    public void setDatavalidadeprorrogacao3(ZonedDateTime datavalidadeprorrogacao3) {
        this.datavalidadeprorrogacao3 = datavalidadeprorrogacao3;
    }

    public ZonedDateTime getDatavencimento() {
        return datavencimento;
    }

    public Autorizacao datavencimento(ZonedDateTime datavencimento) {
        this.datavencimento = datavencimento;
        return this;
    }

    public void setDatavencimento(ZonedDateTime datavencimento) {
        this.datavencimento = datavencimento;
    }

    public ZonedDateTime getDatavencimentoatual() {
        return datavencimentoatual;
    }

    public Autorizacao datavencimentoatual(ZonedDateTime datavencimentoatual) {
        this.datavencimentoatual = datavencimentoatual;
        return this;
    }

    public void setDatavencimentoatual(ZonedDateTime datavencimentoatual) {
        this.datavencimentoatual = datavencimentoatual;
    }

    public String getDescricao() {
        return descricao;
    }

    public Autorizacao descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDocsentregues() {
        return docsentregues;
    }

    public Autorizacao docsentregues(String docsentregues) {
        this.docsentregues = docsentregues;
        return this;
    }

    public void setDocsentregues(String docsentregues) {
        this.docsentregues = docsentregues;
    }

    public String getFcei() {
        return fcei;
    }

    public Autorizacao fcei(String fcei) {
        this.fcei = fcei;
        return this;
    }

    public void setFcei(String fcei) {
        this.fcei = fcei;
    }

    public ZonedDateTime getFceidatapagamento() {
        return fceidatapagamento;
    }

    public Autorizacao fceidatapagamento(ZonedDateTime fceidatapagamento) {
        this.fceidatapagamento = fceidatapagamento;
        return this;
    }

    public void setFceidatapagamento(ZonedDateTime fceidatapagamento) {
        this.fceidatapagamento = fceidatapagamento;
    }

    public ZonedDateTime getFceidataprotocolo() {
        return fceidataprotocolo;
    }

    public Autorizacao fceidataprotocolo(ZonedDateTime fceidataprotocolo) {
        this.fceidataprotocolo = fceidataprotocolo;
        return this;
    }

    public void setFceidataprotocolo(ZonedDateTime fceidataprotocolo) {
        this.fceidataprotocolo = fceidataprotocolo;
    }

    public Double getFceivalor() {
        return fceivalor;
    }

    public Autorizacao fceivalor(Double fceivalor) {
        this.fceivalor = fceivalor;
        return this;
    }

    public void setFceivalor(Double fceivalor) {
        this.fceivalor = fceivalor;
    }

    public String getFolder() {
        return folder;
    }

    public Autorizacao folder(String folder) {
        this.folder = folder;
        return this;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public Boolean isInativo() {
        return inativo;
    }

    public Autorizacao inativo(Boolean inativo) {
        this.inativo = inativo;
        return this;
    }

    public void setInativo(Boolean inativo) {
        this.inativo = inativo;
    }

    public Integer getKmfim() {
        return kmfim;
    }

    public Autorizacao kmfim(Integer kmfim) {
        this.kmfim = kmfim;
        return this;
    }

    public void setKmfim(Integer kmfim) {
        this.kmfim = kmfim;
    }

    public Integer getKminicio() {
        return kminicio;
    }

    public Autorizacao kminicio(Integer kminicio) {
        this.kminicio = kminicio;
        return this;
    }

    public void setKminicio(Integer kminicio) {
        this.kminicio = kminicio;
    }

    public String getLado() {
        return lado;
    }

    public Autorizacao lado(String lado) {
        this.lado = lado;
        return this;
    }

    public void setLado(String lado) {
        this.lado = lado;
    }

    public Boolean isNaoria() {
        return naoria;
    }

    public Autorizacao naoria(Boolean naoria) {
        this.naoria = naoria;
        return this;
    }

    public void setNaoria(Boolean naoria) {
        this.naoria = naoria;
    }

    public String getNumero() {
        return numero;
    }

    public Autorizacao numero(String numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNumerooficioconcessaoprorrogacao1() {
        return numerooficioconcessaoprorrogacao1;
    }

    public Autorizacao numerooficioconcessaoprorrogacao1(String numerooficioconcessaoprorrogacao1) {
        this.numerooficioconcessaoprorrogacao1 = numerooficioconcessaoprorrogacao1;
        return this;
    }

    public void setNumerooficioconcessaoprorrogacao1(String numerooficioconcessaoprorrogacao1) {
        this.numerooficioconcessaoprorrogacao1 = numerooficioconcessaoprorrogacao1;
    }

    public String getNumerooficioconcessaoprorrogacao2() {
        return numerooficioconcessaoprorrogacao2;
    }

    public Autorizacao numerooficioconcessaoprorrogacao2(String numerooficioconcessaoprorrogacao2) {
        this.numerooficioconcessaoprorrogacao2 = numerooficioconcessaoprorrogacao2;
        return this;
    }

    public void setNumerooficioconcessaoprorrogacao2(String numerooficioconcessaoprorrogacao2) {
        this.numerooficioconcessaoprorrogacao2 = numerooficioconcessaoprorrogacao2;
    }

    public String getNumerooficioconcessaoprorrogacao3() {
        return numerooficioconcessaoprorrogacao3;
    }

    public Autorizacao numerooficioconcessaoprorrogacao3(String numerooficioconcessaoprorrogacao3) {
        this.numerooficioconcessaoprorrogacao3 = numerooficioconcessaoprorrogacao3;
        return this;
    }

    public void setNumerooficioconcessaoprorrogacao3(String numerooficioconcessaoprorrogacao3) {
        this.numerooficioconcessaoprorrogacao3 = numerooficioconcessaoprorrogacao3;
    }

    public String getNumerooficioprorrogacao1() {
        return numerooficioprorrogacao1;
    }

    public Autorizacao numerooficioprorrogacao1(String numerooficioprorrogacao1) {
        this.numerooficioprorrogacao1 = numerooficioprorrogacao1;
        return this;
    }

    public void setNumerooficioprorrogacao1(String numerooficioprorrogacao1) {
        this.numerooficioprorrogacao1 = numerooficioprorrogacao1;
    }

    public String getNumerooficioprorrogacao2() {
        return numerooficioprorrogacao2;
    }

    public Autorizacao numerooficioprorrogacao2(String numerooficioprorrogacao2) {
        this.numerooficioprorrogacao2 = numerooficioprorrogacao2;
        return this;
    }

    public void setNumerooficioprorrogacao2(String numerooficioprorrogacao2) {
        this.numerooficioprorrogacao2 = numerooficioprorrogacao2;
    }

    public String getNumerooficioprorrogacao3() {
        return numerooficioprorrogacao3;
    }

    public Autorizacao numerooficioprorrogacao3(String numerooficioprorrogacao3) {
        this.numerooficioprorrogacao3 = numerooficioprorrogacao3;
        return this;
    }

    public void setNumerooficioprorrogacao3(String numerooficioprorrogacao3) {
        this.numerooficioprorrogacao3 = numerooficioprorrogacao3;
    }

    public String getNumeroprocesso() {
        return numeroprocesso;
    }

    public Autorizacao numeroprocesso(String numeroprocesso) {
        this.numeroprocesso = numeroprocesso;
        return this;
    }

    public void setNumeroprocesso(String numeroprocesso) {
        this.numeroprocesso = numeroprocesso;
    }

    public String getObservacao() {
        return observacao;
    }

    public Autorizacao observacao(String observacao) {
        this.observacao = observacao;
        return this;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getPendente() {
        return pendente;
    }

    public Autorizacao pendente(String pendente) {
        this.pendente = pendente;
        return this;
    }

    public void setPendente(String pendente) {
        this.pendente = pendente;
    }

    public Boolean isPrazomes() {
        return prazomes;
    }

    public Autorizacao prazomes(Boolean prazomes) {
        this.prazomes = prazomes;
        return this;
    }

    public void setPrazomes(Boolean prazomes) {
        this.prazomes = prazomes;
    }

    public Integer getPrazovalidade() {
        return prazovalidade;
    }

    public Autorizacao prazovalidade(Integer prazovalidade) {
        this.prazovalidade = prazovalidade;
        return this;
    }

    public void setPrazovalidade(Integer prazovalidade) {
        this.prazovalidade = prazovalidade;
    }

    public String getProprietario() {
        return proprietario;
    }

    public Autorizacao proprietario(String proprietario) {
        this.proprietario = proprietario;
        return this;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public String getReciboentregadocs() {
        return reciboentregadocs;
    }

    public Autorizacao reciboentregadocs(String reciboentregadocs) {
        this.reciboentregadocs = reciboentregadocs;
        return this;
    }

    public void setReciboentregadocs(String reciboentregadocs) {
        this.reciboentregadocs = reciboentregadocs;
    }

    public Atividadelicenca getAtividadelicenca() {
        return atividadelicenca;
    }

    public Autorizacao atividadelicenca(Atividadelicenca atividadelicenca) {
        this.atividadelicenca = atividadelicenca;
        return this;
    }

    public void setAtividadelicenca(Atividadelicenca atividadelicenca) {
        this.atividadelicenca = atividadelicenca;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public Autorizacao empresa(Empresa empresa) {
        this.empresa = empresa;
        return this;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Obra getObra() {
        return obra;
    }

    public Autorizacao obra(Obra obra) {
        this.obra = obra;
        return this;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public Orgaoemissor getOrgaoemissor() {
        return orgaoemissor;
    }

    public Autorizacao orgaoemissor(Orgaoemissor orgaoemissor) {
        this.orgaoemissor = orgaoemissor;
        return this;
    }

    public void setOrgaoemissor(Orgaoemissor orgaoemissor) {
        this.orgaoemissor = orgaoemissor;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public Autorizacao projeto(Projeto projeto) {
        this.projeto = projeto;
        return this;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public Tipoautorizacao getTipoautorizacao() {
        return tipoautorizacao;
    }

    public Autorizacao tipoautorizacao(Tipoautorizacao tipoautorizacao) {
        this.tipoautorizacao = tipoautorizacao;
        return this;
    }

    public void setTipoautorizacao(Tipoautorizacao tipoautorizacao) {
        this.tipoautorizacao = tipoautorizacao;
    }

    public Set<Foto> getFotos() {
        return fotos;
    }

    public Autorizacao fotos(Set<Foto> fotos) {
        this.fotos = fotos;
        return this;
    }

    public Autorizacao addFotos(Foto foto) {
        this.fotos.add(foto);
        return this;
    }

    public Autorizacao removeFotos(Foto foto) {
        this.fotos.remove(foto);
        return this;
    }

    public void setFotos(Set<Foto> fotos) {
        this.fotos = fotos;
    }

    public Set<Documento> getDocs() {
        return docs;
    }

    public Autorizacao docs(Set<Documento> documentos) {
        this.docs = documentos;
        return this;
    }

    public Autorizacao addDocs(Documento documento) {
        this.docs.add(documento);
        return this;
    }

    public Autorizacao removeDocs(Documento documento) {
        this.docs.remove(documento);
        return this;
    }

    public void setDocs(Set<Documento> documentos) {
        this.docs = documentos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Autorizacao autorizacao = (Autorizacao) o;
        if (autorizacao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), autorizacao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Autorizacao{" +
            "id=" + getId() +
            ", album='" + getAlbum() + "'" +
            ", andamento='" + getAndamento() + "'" +
            ", data='" + getData() + "'" +
            ", dataentregadocs='" + getDataentregadocs() + "'" +
            ", dataexpedicaoprorrogacao1='" + getDataexpedicaoprorrogacao1() + "'" +
            ", dataexpedicaoprorrogacao2='" + getDataexpedicaoprorrogacao2() + "'" +
            ", dataexpedicaoprorrogacao3='" + getDataexpedicaoprorrogacao3() + "'" +
            ", datapedidoprorrogacao1='" + getDatapedidoprorrogacao1() + "'" +
            ", datapedidoprorrogacao2='" + getDatapedidoprorrogacao2() + "'" +
            ", datapedidoprorrogacao3='" + getDatapedidoprorrogacao3() + "'" +
            ", datavalidadeprorrogacao1='" + getDatavalidadeprorrogacao1() + "'" +
            ", datavalidadeprorrogacao2='" + getDatavalidadeprorrogacao2() + "'" +
            ", datavalidadeprorrogacao3='" + getDatavalidadeprorrogacao3() + "'" +
            ", datavencimento='" + getDatavencimento() + "'" +
            ", datavencimentoatual='" + getDatavencimentoatual() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", docsentregues='" + getDocsentregues() + "'" +
            ", fcei='" + getFcei() + "'" +
            ", fceidatapagamento='" + getFceidatapagamento() + "'" +
            ", fceidataprotocolo='" + getFceidataprotocolo() + "'" +
            ", fceivalor='" + getFceivalor() + "'" +
            ", folder='" + getFolder() + "'" +
            ", inativo='" + isInativo() + "'" +
            ", kmfim='" + getKmfim() + "'" +
            ", kminicio='" + getKminicio() + "'" +
            ", lado='" + getLado() + "'" +
            ", naoria='" + isNaoria() + "'" +
            ", numero='" + getNumero() + "'" +
            ", numerooficioconcessaoprorrogacao1='" + getNumerooficioconcessaoprorrogacao1() + "'" +
            ", numerooficioconcessaoprorrogacao2='" + getNumerooficioconcessaoprorrogacao2() + "'" +
            ", numerooficioconcessaoprorrogacao3='" + getNumerooficioconcessaoprorrogacao3() + "'" +
            ", numerooficioprorrogacao1='" + getNumerooficioprorrogacao1() + "'" +
            ", numerooficioprorrogacao2='" + getNumerooficioprorrogacao2() + "'" +
            ", numerooficioprorrogacao3='" + getNumerooficioprorrogacao3() + "'" +
            ", numeroprocesso='" + getNumeroprocesso() + "'" +
            ", observacao='" + getObservacao() + "'" +
            ", pendente='" + getPendente() + "'" +
            ", prazomes='" + isPrazomes() + "'" +
            ", prazovalidade='" + getPrazovalidade() + "'" +
            ", proprietario='" + getProprietario() + "'" +
            ", reciboentregadocs='" + getReciboentregadocs() + "'" +
            "}";
    }
}
