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
 * A Auc.
 */
@Entity
@Table(name = "auc")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Auc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "fceivalor", precision=10, scale=2)
    private BigDecimal fceivalor;

    @Column(name = "inativo")
    private Boolean inativo;

    @Column(name = "prazomes")
    private Boolean prazomes;

    @Column(name = "reposicaoflorestal")
    private Boolean reposicaoflorestal;

    @Column(name = "compensacaoambiental")
    private Boolean compensacaoambiental;

    @Column(name = "prazovalidade")
    private Integer prazovalidade;

    @Column(name = "fcei")
    private String fcei;

    @Column(name = "reciboentregadocs")
    private String reciboentregadocs;

    @Column(name = "numero")
    private String numero;

    @Column(name = "numeroprocesso")
    private String numeroprocesso;

    @Column(name = "numerooficiolocalpedido")
    private String numerooficiolocalpedido;

    @Column(name = "numerooficiooficialpedido")
    private String numerooficiooficialpedido;

    @Column(name = "numerooficiolocalrecebimento")
    private String numerooficiolocalrecebimento;

    @Column(name = "numerooficiooficialrecebimento")
    private String numerooficiooficialrecebimento;

    @Column(name = "numeroparecertecnico")
    private String numeroparecertecnico;

    @Column(name = "numerooficioprorrogacao_1")
    private String numerooficioprorrogacao1;

    @Column(name = "numerooficioconcessaoprorrogacao_1")
    private String numerooficioconcessaoprorrogacao1;

    @Column(name = "numerooficioprorrogacao_2")
    private String numerooficioprorrogacao2;

    @Column(name = "numerooficioconcessaoprorrogacao_2")
    private String numerooficioconcessaoprorrogacao2;

    @Column(name = "numerooficioprorrogacao_3")
    private String numerooficioprorrogacao3;

    @Column(name = "numerooficioconcessaoprorrogacao_3")
    private String numerooficioconcessaoprorrogacao3;

    @Column(name = "album")
    private String album;

    @Column(name = "folder")
    private String folder;

    @Column(name = "pendente")
    private String pendente;

    @Column(name = "andamento")
    private String andamento;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "observacao")
    private String observacao;

    @Column(name = "docsentregues")
    private String docsentregues;

    @Column(name = "reposicaoflorestalobs")
    private String reposicaoflorestalobs;

    @Column(name = "compensacaoambientalobs")
    private String compensacaoambientalobs;

    @Column(name = "fceidataprotocolo")
    private ZonedDateTime fceidataprotocolo;

    @Column(name = "fceidatapagamento")
    private ZonedDateTime fceidatapagamento;

    @Column(name = "dataentregadocs")
    private ZonedDateTime dataentregadocs;

    @Column(name = "dataoficiolocalpedido")
    private ZonedDateTime dataoficiolocalpedido;

    @Column(name = "dataoficioreoficialpedido")
    private ZonedDateTime dataoficioreoficialpedido;

    @Column(name = "dataoficiolocalrecebimento")
    private ZonedDateTime dataoficiolocalrecebimento;

    @Column(name = "dataoficioreoficialrecebimento")
    private ZonedDateTime dataoficioreoficialrecebimento;

    @Column(name = "dataemissao")
    private ZonedDateTime dataemissao;

    @Column(name = "datapedidoprorrogacao_1")
    private ZonedDateTime datapedidoprorrogacao1;

    @Column(name = "dataexpedicaoprorrogacao_1")
    private ZonedDateTime dataexpedicaoprorrogacao1;

    @Column(name = "datavalidadeprorrogacao_1")
    private ZonedDateTime datavalidadeprorrogacao1;

    @Column(name = "datapedidoprorrogacao_2")
    private ZonedDateTime datapedidoprorrogacao2;

    @Column(name = "dataexpedicaoprorrogacao_2")
    private ZonedDateTime dataexpedicaoprorrogacao2;

    @Column(name = "datavalidadeprorrogacao_2")
    private ZonedDateTime datavalidadeprorrogacao2;

    @Column(name = "datapedidoprorrogacao_3")
    private ZonedDateTime datapedidoprorrogacao3;

    @Column(name = "dataexpedicaoprorrogacao_3")
    private ZonedDateTime dataexpedicaoprorrogacao3;

    @Column(name = "datavalidadeprorrogacao_3")
    private ZonedDateTime datavalidadeprorrogacao3;

    @Column(name = "datavencimento")
    private ZonedDateTime datavencimento;

    @Column(name = "datavencimentoatual")
    private ZonedDateTime datavencimentoatual;

    @ManyToOne
    private Atividadelicenca atividadelicenca;

    @ManyToOne
    private Orgaoemissor orgaoemissor;

    @ManyToOne
    private Obra obra;

    @ManyToOne
    private Projeto projeto;

    @ManyToOne
    private Empresa empresa;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "auc_condicionantes",
               joinColumns = @JoinColumn(name="aucs_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="condicionantes_id", referencedColumnName="id"))
    private Set<Condicionante> condicionantes = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "auc_fotos",
               joinColumns = @JoinColumn(name="aucs_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="fotos_id", referencedColumnName="id"))
    private Set<Foto> fotos = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "auc_docs",
               joinColumns = @JoinColumn(name="aucs_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="docs_id", referencedColumnName="id"))
    private Set<Documento> docs = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getFceivalor() {
        return fceivalor;
    }

    public Auc fceivalor(BigDecimal fceivalor) {
        this.fceivalor = fceivalor;
        return this;
    }

    public void setFceivalor(BigDecimal fceivalor) {
        this.fceivalor = fceivalor;
    }

    public Boolean isInativo() {
        return inativo;
    }

    public Auc inativo(Boolean inativo) {
        this.inativo = inativo;
        return this;
    }

    public void setInativo(Boolean inativo) {
        this.inativo = inativo;
    }

    public Boolean isPrazomes() {
        return prazomes;
    }

    public Auc prazomes(Boolean prazomes) {
        this.prazomes = prazomes;
        return this;
    }

    public void setPrazomes(Boolean prazomes) {
        this.prazomes = prazomes;
    }

    public Boolean isReposicaoflorestal() {
        return reposicaoflorestal;
    }

    public Auc reposicaoflorestal(Boolean reposicaoflorestal) {
        this.reposicaoflorestal = reposicaoflorestal;
        return this;
    }

    public void setReposicaoflorestal(Boolean reposicaoflorestal) {
        this.reposicaoflorestal = reposicaoflorestal;
    }

    public Boolean isCompensacaoambiental() {
        return compensacaoambiental;
    }

    public Auc compensacaoambiental(Boolean compensacaoambiental) {
        this.compensacaoambiental = compensacaoambiental;
        return this;
    }

    public void setCompensacaoambiental(Boolean compensacaoambiental) {
        this.compensacaoambiental = compensacaoambiental;
    }

    public Integer getPrazovalidade() {
        return prazovalidade;
    }

    public Auc prazovalidade(Integer prazovalidade) {
        this.prazovalidade = prazovalidade;
        return this;
    }

    public void setPrazovalidade(Integer prazovalidade) {
        this.prazovalidade = prazovalidade;
    }

    public String getFcei() {
        return fcei;
    }

    public Auc fcei(String fcei) {
        this.fcei = fcei;
        return this;
    }

    public void setFcei(String fcei) {
        this.fcei = fcei;
    }

    public String getReciboentregadocs() {
        return reciboentregadocs;
    }

    public Auc reciboentregadocs(String reciboentregadocs) {
        this.reciboentregadocs = reciboentregadocs;
        return this;
    }

    public void setReciboentregadocs(String reciboentregadocs) {
        this.reciboentregadocs = reciboentregadocs;
    }

    public String getNumero() {
        return numero;
    }

    public Auc numero(String numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNumeroprocesso() {
        return numeroprocesso;
    }

    public Auc numeroprocesso(String numeroprocesso) {
        this.numeroprocesso = numeroprocesso;
        return this;
    }

    public void setNumeroprocesso(String numeroprocesso) {
        this.numeroprocesso = numeroprocesso;
    }

    public String getNumerooficiolocalpedido() {
        return numerooficiolocalpedido;
    }

    public Auc numerooficiolocalpedido(String numerooficiolocalpedido) {
        this.numerooficiolocalpedido = numerooficiolocalpedido;
        return this;
    }

    public void setNumerooficiolocalpedido(String numerooficiolocalpedido) {
        this.numerooficiolocalpedido = numerooficiolocalpedido;
    }

    public String getNumerooficiooficialpedido() {
        return numerooficiooficialpedido;
    }

    public Auc numerooficiooficialpedido(String numerooficiooficialpedido) {
        this.numerooficiooficialpedido = numerooficiooficialpedido;
        return this;
    }

    public void setNumerooficiooficialpedido(String numerooficiooficialpedido) {
        this.numerooficiooficialpedido = numerooficiooficialpedido;
    }

    public String getNumerooficiolocalrecebimento() {
        return numerooficiolocalrecebimento;
    }

    public Auc numerooficiolocalrecebimento(String numerooficiolocalrecebimento) {
        this.numerooficiolocalrecebimento = numerooficiolocalrecebimento;
        return this;
    }

    public void setNumerooficiolocalrecebimento(String numerooficiolocalrecebimento) {
        this.numerooficiolocalrecebimento = numerooficiolocalrecebimento;
    }

    public String getNumerooficiooficialrecebimento() {
        return numerooficiooficialrecebimento;
    }

    public Auc numerooficiooficialrecebimento(String numerooficiooficialrecebimento) {
        this.numerooficiooficialrecebimento = numerooficiooficialrecebimento;
        return this;
    }

    public void setNumerooficiooficialrecebimento(String numerooficiooficialrecebimento) {
        this.numerooficiooficialrecebimento = numerooficiooficialrecebimento;
    }

    public String getNumeroparecertecnico() {
        return numeroparecertecnico;
    }

    public Auc numeroparecertecnico(String numeroparecertecnico) {
        this.numeroparecertecnico = numeroparecertecnico;
        return this;
    }

    public void setNumeroparecertecnico(String numeroparecertecnico) {
        this.numeroparecertecnico = numeroparecertecnico;
    }

    public String getNumerooficioprorrogacao1() {
        return numerooficioprorrogacao1;
    }

    public Auc numerooficioprorrogacao1(String numerooficioprorrogacao1) {
        this.numerooficioprorrogacao1 = numerooficioprorrogacao1;
        return this;
    }

    public void setNumerooficioprorrogacao1(String numerooficioprorrogacao1) {
        this.numerooficioprorrogacao1 = numerooficioprorrogacao1;
    }

    public String getNumerooficioconcessaoprorrogacao1() {
        return numerooficioconcessaoprorrogacao1;
    }

    public Auc numerooficioconcessaoprorrogacao1(String numerooficioconcessaoprorrogacao1) {
        this.numerooficioconcessaoprorrogacao1 = numerooficioconcessaoprorrogacao1;
        return this;
    }

    public void setNumerooficioconcessaoprorrogacao1(String numerooficioconcessaoprorrogacao1) {
        this.numerooficioconcessaoprorrogacao1 = numerooficioconcessaoprorrogacao1;
    }

    public String getNumerooficioprorrogacao2() {
        return numerooficioprorrogacao2;
    }

    public Auc numerooficioprorrogacao2(String numerooficioprorrogacao2) {
        this.numerooficioprorrogacao2 = numerooficioprorrogacao2;
        return this;
    }

    public void setNumerooficioprorrogacao2(String numerooficioprorrogacao2) {
        this.numerooficioprorrogacao2 = numerooficioprorrogacao2;
    }

    public String getNumerooficioconcessaoprorrogacao2() {
        return numerooficioconcessaoprorrogacao2;
    }

    public Auc numerooficioconcessaoprorrogacao2(String numerooficioconcessaoprorrogacao2) {
        this.numerooficioconcessaoprorrogacao2 = numerooficioconcessaoprorrogacao2;
        return this;
    }

    public void setNumerooficioconcessaoprorrogacao2(String numerooficioconcessaoprorrogacao2) {
        this.numerooficioconcessaoprorrogacao2 = numerooficioconcessaoprorrogacao2;
    }

    public String getNumerooficioprorrogacao3() {
        return numerooficioprorrogacao3;
    }

    public Auc numerooficioprorrogacao3(String numerooficioprorrogacao3) {
        this.numerooficioprorrogacao3 = numerooficioprorrogacao3;
        return this;
    }

    public void setNumerooficioprorrogacao3(String numerooficioprorrogacao3) {
        this.numerooficioprorrogacao3 = numerooficioprorrogacao3;
    }

    public String getNumerooficioconcessaoprorrogacao3() {
        return numerooficioconcessaoprorrogacao3;
    }

    public Auc numerooficioconcessaoprorrogacao3(String numerooficioconcessaoprorrogacao3) {
        this.numerooficioconcessaoprorrogacao3 = numerooficioconcessaoprorrogacao3;
        return this;
    }

    public void setNumerooficioconcessaoprorrogacao3(String numerooficioconcessaoprorrogacao3) {
        this.numerooficioconcessaoprorrogacao3 = numerooficioconcessaoprorrogacao3;
    }

    public String getAlbum() {
        return album;
    }

    public Auc album(String album) {
        this.album = album;
        return this;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getFolder() {
        return folder;
    }

    public Auc folder(String folder) {
        this.folder = folder;
        return this;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getPendente() {
        return pendente;
    }

    public Auc pendente(String pendente) {
        this.pendente = pendente;
        return this;
    }

    public void setPendente(String pendente) {
        this.pendente = pendente;
    }

    public String getAndamento() {
        return andamento;
    }

    public Auc andamento(String andamento) {
        this.andamento = andamento;
        return this;
    }

    public void setAndamento(String andamento) {
        this.andamento = andamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public Auc descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObservacao() {
        return observacao;
    }

    public Auc observacao(String observacao) {
        this.observacao = observacao;
        return this;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getDocsentregues() {
        return docsentregues;
    }

    public Auc docsentregues(String docsentregues) {
        this.docsentregues = docsentregues;
        return this;
    }

    public void setDocsentregues(String docsentregues) {
        this.docsentregues = docsentregues;
    }

    public String getReposicaoflorestalobs() {
        return reposicaoflorestalobs;
    }

    public Auc reposicaoflorestalobs(String reposicaoflorestalobs) {
        this.reposicaoflorestalobs = reposicaoflorestalobs;
        return this;
    }

    public void setReposicaoflorestalobs(String reposicaoflorestalobs) {
        this.reposicaoflorestalobs = reposicaoflorestalobs;
    }

    public String getCompensacaoambientalobs() {
        return compensacaoambientalobs;
    }

    public Auc compensacaoambientalobs(String compensacaoambientalobs) {
        this.compensacaoambientalobs = compensacaoambientalobs;
        return this;
    }

    public void setCompensacaoambientalobs(String compensacaoambientalobs) {
        this.compensacaoambientalobs = compensacaoambientalobs;
    }

    public ZonedDateTime getFceidataprotocolo() {
        return fceidataprotocolo;
    }

    public Auc fceidataprotocolo(ZonedDateTime fceidataprotocolo) {
        this.fceidataprotocolo = fceidataprotocolo;
        return this;
    }

    public void setFceidataprotocolo(ZonedDateTime fceidataprotocolo) {
        this.fceidataprotocolo = fceidataprotocolo;
    }

    public ZonedDateTime getFceidatapagamento() {
        return fceidatapagamento;
    }

    public Auc fceidatapagamento(ZonedDateTime fceidatapagamento) {
        this.fceidatapagamento = fceidatapagamento;
        return this;
    }

    public void setFceidatapagamento(ZonedDateTime fceidatapagamento) {
        this.fceidatapagamento = fceidatapagamento;
    }

    public ZonedDateTime getDataentregadocs() {
        return dataentregadocs;
    }

    public Auc dataentregadocs(ZonedDateTime dataentregadocs) {
        this.dataentregadocs = dataentregadocs;
        return this;
    }

    public void setDataentregadocs(ZonedDateTime dataentregadocs) {
        this.dataentregadocs = dataentregadocs;
    }

    public ZonedDateTime getDataoficiolocalpedido() {
        return dataoficiolocalpedido;
    }

    public Auc dataoficiolocalpedido(ZonedDateTime dataoficiolocalpedido) {
        this.dataoficiolocalpedido = dataoficiolocalpedido;
        return this;
    }

    public void setDataoficiolocalpedido(ZonedDateTime dataoficiolocalpedido) {
        this.dataoficiolocalpedido = dataoficiolocalpedido;
    }

    public ZonedDateTime getDataoficioreoficialpedido() {
        return dataoficioreoficialpedido;
    }

    public Auc dataoficioreoficialpedido(ZonedDateTime dataoficioreoficialpedido) {
        this.dataoficioreoficialpedido = dataoficioreoficialpedido;
        return this;
    }

    public void setDataoficioreoficialpedido(ZonedDateTime dataoficioreoficialpedido) {
        this.dataoficioreoficialpedido = dataoficioreoficialpedido;
    }

    public ZonedDateTime getDataoficiolocalrecebimento() {
        return dataoficiolocalrecebimento;
    }

    public Auc dataoficiolocalrecebimento(ZonedDateTime dataoficiolocalrecebimento) {
        this.dataoficiolocalrecebimento = dataoficiolocalrecebimento;
        return this;
    }

    public void setDataoficiolocalrecebimento(ZonedDateTime dataoficiolocalrecebimento) {
        this.dataoficiolocalrecebimento = dataoficiolocalrecebimento;
    }

    public ZonedDateTime getDataoficioreoficialrecebimento() {
        return dataoficioreoficialrecebimento;
    }

    public Auc dataoficioreoficialrecebimento(ZonedDateTime dataoficioreoficialrecebimento) {
        this.dataoficioreoficialrecebimento = dataoficioreoficialrecebimento;
        return this;
    }

    public void setDataoficioreoficialrecebimento(ZonedDateTime dataoficioreoficialrecebimento) {
        this.dataoficioreoficialrecebimento = dataoficioreoficialrecebimento;
    }

    public ZonedDateTime getDataemissao() {
        return dataemissao;
    }

    public Auc dataemissao(ZonedDateTime dataemissao) {
        this.dataemissao = dataemissao;
        return this;
    }

    public void setDataemissao(ZonedDateTime dataemissao) {
        this.dataemissao = dataemissao;
    }

    public ZonedDateTime getDatapedidoprorrogacao1() {
        return datapedidoprorrogacao1;
    }

    public Auc datapedidoprorrogacao1(ZonedDateTime datapedidoprorrogacao1) {
        this.datapedidoprorrogacao1 = datapedidoprorrogacao1;
        return this;
    }

    public void setDatapedidoprorrogacao1(ZonedDateTime datapedidoprorrogacao1) {
        this.datapedidoprorrogacao1 = datapedidoprorrogacao1;
    }

    public ZonedDateTime getDataexpedicaoprorrogacao1() {
        return dataexpedicaoprorrogacao1;
    }

    public Auc dataexpedicaoprorrogacao1(ZonedDateTime dataexpedicaoprorrogacao1) {
        this.dataexpedicaoprorrogacao1 = dataexpedicaoprorrogacao1;
        return this;
    }

    public void setDataexpedicaoprorrogacao1(ZonedDateTime dataexpedicaoprorrogacao1) {
        this.dataexpedicaoprorrogacao1 = dataexpedicaoprorrogacao1;
    }

    public ZonedDateTime getDatavalidadeprorrogacao1() {
        return datavalidadeprorrogacao1;
    }

    public Auc datavalidadeprorrogacao1(ZonedDateTime datavalidadeprorrogacao1) {
        this.datavalidadeprorrogacao1 = datavalidadeprorrogacao1;
        return this;
    }

    public void setDatavalidadeprorrogacao1(ZonedDateTime datavalidadeprorrogacao1) {
        this.datavalidadeprorrogacao1 = datavalidadeprorrogacao1;
    }

    public ZonedDateTime getDatapedidoprorrogacao2() {
        return datapedidoprorrogacao2;
    }

    public Auc datapedidoprorrogacao2(ZonedDateTime datapedidoprorrogacao2) {
        this.datapedidoprorrogacao2 = datapedidoprorrogacao2;
        return this;
    }

    public void setDatapedidoprorrogacao2(ZonedDateTime datapedidoprorrogacao2) {
        this.datapedidoprorrogacao2 = datapedidoprorrogacao2;
    }

    public ZonedDateTime getDataexpedicaoprorrogacao2() {
        return dataexpedicaoprorrogacao2;
    }

    public Auc dataexpedicaoprorrogacao2(ZonedDateTime dataexpedicaoprorrogacao2) {
        this.dataexpedicaoprorrogacao2 = dataexpedicaoprorrogacao2;
        return this;
    }

    public void setDataexpedicaoprorrogacao2(ZonedDateTime dataexpedicaoprorrogacao2) {
        this.dataexpedicaoprorrogacao2 = dataexpedicaoprorrogacao2;
    }

    public ZonedDateTime getDatavalidadeprorrogacao2() {
        return datavalidadeprorrogacao2;
    }

    public Auc datavalidadeprorrogacao2(ZonedDateTime datavalidadeprorrogacao2) {
        this.datavalidadeprorrogacao2 = datavalidadeprorrogacao2;
        return this;
    }

    public void setDatavalidadeprorrogacao2(ZonedDateTime datavalidadeprorrogacao2) {
        this.datavalidadeprorrogacao2 = datavalidadeprorrogacao2;
    }

    public ZonedDateTime getDatapedidoprorrogacao3() {
        return datapedidoprorrogacao3;
    }

    public Auc datapedidoprorrogacao3(ZonedDateTime datapedidoprorrogacao3) {
        this.datapedidoprorrogacao3 = datapedidoprorrogacao3;
        return this;
    }

    public void setDatapedidoprorrogacao3(ZonedDateTime datapedidoprorrogacao3) {
        this.datapedidoprorrogacao3 = datapedidoprorrogacao3;
    }

    public ZonedDateTime getDataexpedicaoprorrogacao3() {
        return dataexpedicaoprorrogacao3;
    }

    public Auc dataexpedicaoprorrogacao3(ZonedDateTime dataexpedicaoprorrogacao3) {
        this.dataexpedicaoprorrogacao3 = dataexpedicaoprorrogacao3;
        return this;
    }

    public void setDataexpedicaoprorrogacao3(ZonedDateTime dataexpedicaoprorrogacao3) {
        this.dataexpedicaoprorrogacao3 = dataexpedicaoprorrogacao3;
    }

    public ZonedDateTime getDatavalidadeprorrogacao3() {
        return datavalidadeprorrogacao3;
    }

    public Auc datavalidadeprorrogacao3(ZonedDateTime datavalidadeprorrogacao3) {
        this.datavalidadeprorrogacao3 = datavalidadeprorrogacao3;
        return this;
    }

    public void setDatavalidadeprorrogacao3(ZonedDateTime datavalidadeprorrogacao3) {
        this.datavalidadeprorrogacao3 = datavalidadeprorrogacao3;
    }

    public ZonedDateTime getDatavencimento() {
        return datavencimento;
    }

    public Auc datavencimento(ZonedDateTime datavencimento) {
        this.datavencimento = datavencimento;
        return this;
    }

    public void setDatavencimento(ZonedDateTime datavencimento) {
        this.datavencimento = datavencimento;
    }

    public ZonedDateTime getDatavencimentoatual() {
        return datavencimentoatual;
    }

    public Auc datavencimentoatual(ZonedDateTime datavencimentoatual) {
        this.datavencimentoatual = datavencimentoatual;
        return this;
    }

    public void setDatavencimentoatual(ZonedDateTime datavencimentoatual) {
        this.datavencimentoatual = datavencimentoatual;
    }

    public Atividadelicenca getAtividadelicenca() {
        return atividadelicenca;
    }

    public Auc atividadelicenca(Atividadelicenca atividadelicenca) {
        this.atividadelicenca = atividadelicenca;
        return this;
    }

    public void setAtividadelicenca(Atividadelicenca atividadelicenca) {
        this.atividadelicenca = atividadelicenca;
    }

    public Orgaoemissor getOrgaoemissor() {
        return orgaoemissor;
    }

    public Auc orgaoemissor(Orgaoemissor orgaoemissor) {
        this.orgaoemissor = orgaoemissor;
        return this;
    }

    public void setOrgaoemissor(Orgaoemissor orgaoemissor) {
        this.orgaoemissor = orgaoemissor;
    }

    public Obra getObra() {
        return obra;
    }

    public Auc obra(Obra obra) {
        this.obra = obra;
        return this;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public Auc projeto(Projeto projeto) {
        this.projeto = projeto;
        return this;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public Auc empresa(Empresa empresa) {
        this.empresa = empresa;
        return this;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Set<Condicionante> getCondicionantes() {
        return condicionantes;
    }

    public Auc condicionantes(Set<Condicionante> condicionantes) {
        this.condicionantes = condicionantes;
        return this;
    }

    public Auc addCondicionantes(Condicionante condicionante) {
        this.condicionantes.add(condicionante);
        return this;
    }

    public Auc removeCondicionantes(Condicionante condicionante) {
        this.condicionantes.remove(condicionante);
        return this;
    }

    public void setCondicionantes(Set<Condicionante> condicionantes) {
        this.condicionantes = condicionantes;
    }

    public Set<Foto> getFotos() {
        return fotos;
    }

    public Auc fotos(Set<Foto> fotos) {
        this.fotos = fotos;
        return this;
    }

    public Auc addFotos(Foto foto) {
        this.fotos.add(foto);
        return this;
    }

    public Auc removeFotos(Foto foto) {
        this.fotos.remove(foto);
        return this;
    }

    public void setFotos(Set<Foto> fotos) {
        this.fotos = fotos;
    }

    public Set<Documento> getDocs() {
        return docs;
    }

    public Auc docs(Set<Documento> documentos) {
        this.docs = documentos;
        return this;
    }

    public Auc addDocs(Documento documento) {
        this.docs.add(documento);
        return this;
    }

    public Auc removeDocs(Documento documento) {
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
        Auc auc = (Auc) o;
        if (auc.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), auc.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Auc{" +
            "id=" + getId() +
            ", fceivalor='" + getFceivalor() + "'" +
            ", inativo='" + isInativo() + "'" +
            ", prazomes='" + isPrazomes() + "'" +
            ", reposicaoflorestal='" + isReposicaoflorestal() + "'" +
            ", compensacaoambiental='" + isCompensacaoambiental() + "'" +
            ", prazovalidade='" + getPrazovalidade() + "'" +
            ", fcei='" + getFcei() + "'" +
            ", reciboentregadocs='" + getReciboentregadocs() + "'" +
            ", numero='" + getNumero() + "'" +
            ", numeroprocesso='" + getNumeroprocesso() + "'" +
            ", numerooficiolocalpedido='" + getNumerooficiolocalpedido() + "'" +
            ", numerooficiooficialpedido='" + getNumerooficiooficialpedido() + "'" +
            ", numerooficiolocalrecebimento='" + getNumerooficiolocalrecebimento() + "'" +
            ", numerooficiooficialrecebimento='" + getNumerooficiooficialrecebimento() + "'" +
            ", numeroparecertecnico='" + getNumeroparecertecnico() + "'" +
            ", numerooficioprorrogacao1='" + getNumerooficioprorrogacao1() + "'" +
            ", numerooficioconcessaoprorrogacao1='" + getNumerooficioconcessaoprorrogacao1() + "'" +
            ", numerooficioprorrogacao2='" + getNumerooficioprorrogacao2() + "'" +
            ", numerooficioconcessaoprorrogacao2='" + getNumerooficioconcessaoprorrogacao2() + "'" +
            ", numerooficioprorrogacao3='" + getNumerooficioprorrogacao3() + "'" +
            ", numerooficioconcessaoprorrogacao3='" + getNumerooficioconcessaoprorrogacao3() + "'" +
            ", album='" + getAlbum() + "'" +
            ", folder='" + getFolder() + "'" +
            ", pendente='" + getPendente() + "'" +
            ", andamento='" + getAndamento() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", observacao='" + getObservacao() + "'" +
            ", docsentregues='" + getDocsentregues() + "'" +
            ", reposicaoflorestalobs='" + getReposicaoflorestalobs() + "'" +
            ", compensacaoambientalobs='" + getCompensacaoambientalobs() + "'" +
            ", fceidataprotocolo='" + getFceidataprotocolo() + "'" +
            ", fceidatapagamento='" + getFceidatapagamento() + "'" +
            ", dataentregadocs='" + getDataentregadocs() + "'" +
            ", dataoficiolocalpedido='" + getDataoficiolocalpedido() + "'" +
            ", dataoficioreoficialpedido='" + getDataoficioreoficialpedido() + "'" +
            ", dataoficiolocalrecebimento='" + getDataoficiolocalrecebimento() + "'" +
            ", dataoficioreoficialrecebimento='" + getDataoficioreoficialrecebimento() + "'" +
            ", dataemissao='" + getDataemissao() + "'" +
            ", datapedidoprorrogacao1='" + getDatapedidoprorrogacao1() + "'" +
            ", dataexpedicaoprorrogacao1='" + getDataexpedicaoprorrogacao1() + "'" +
            ", datavalidadeprorrogacao1='" + getDatavalidadeprorrogacao1() + "'" +
            ", datapedidoprorrogacao2='" + getDatapedidoprorrogacao2() + "'" +
            ", dataexpedicaoprorrogacao2='" + getDataexpedicaoprorrogacao2() + "'" +
            ", datavalidadeprorrogacao2='" + getDatavalidadeprorrogacao2() + "'" +
            ", datapedidoprorrogacao3='" + getDatapedidoprorrogacao3() + "'" +
            ", dataexpedicaoprorrogacao3='" + getDataexpedicaoprorrogacao3() + "'" +
            ", datavalidadeprorrogacao3='" + getDatavalidadeprorrogacao3() + "'" +
            ", datavencimento='" + getDatavencimento() + "'" +
            ", datavencimentoatual='" + getDatavencimentoatual() + "'" +
            "}";
    }
}
