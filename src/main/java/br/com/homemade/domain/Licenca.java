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
 * A Licenca.
 */
@Entity
@Table(name = "licenca")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Licenca implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "album")
    private String album;

    @Column(name = "andamento")
    private String andamento;

    @Column(name = "compensacaoambiental")
    private Boolean compensacaoambiental;

    @Column(name = "dataemissao")
    private ZonedDateTime dataemissao;

    @Column(name = "dataentregadocs")
    private ZonedDateTime dataentregadocs;

    @Column(name = "dataexpedicaoprorrogacao_1")
    private ZonedDateTime dataexpedicaoprorrogacao1;

    @Column(name = "dataexpedicaoprorrogacao_2")
    private ZonedDateTime dataexpedicaoprorrogacao2;

    @Column(name = "dataexpedicaoprorrogacao_3")
    private ZonedDateTime dataexpedicaoprorrogacao3;

    @Column(name = "dataoficiolocalpedido")
    private ZonedDateTime dataoficiolocalpedido;

    @Column(name = "dataoficiolocalrecebimento")
    private ZonedDateTime dataoficiolocalrecebimento;

    @Column(name = "dataoficioreoficialpedido")
    private ZonedDateTime dataoficioreoficialpedido;

    @Column(name = "dataoficioreoficialrecebimento")
    private ZonedDateTime dataoficioreoficialrecebimento;

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

    @Column(name = "dispensalai")
    private Boolean dispensalai;

    @Column(name = "docsentregues")
    private String docsentregues;

    @Column(name = "eiarima")
    private Boolean eiarima;

    @Column(name = "fcei")
    private String fcei;

    @Column(name = "fceidatapagamento")
    private ZonedDateTime fceidatapagamento;

    @Column(name = "fceidataprotocolo")
    private ZonedDateTime fceidataprotocolo;

    @Column(name = "fceivalor", precision=10, scale=2)
    private BigDecimal fceivalor;

    @Column(name = "folder")
    private String folder;

    @Column(name = "inativo")
    private Boolean inativo;

    @Column(name = "inventarioflorestal")
    private Boolean inventarioflorestal;

    @Column(name = "numero")
    private String numero;

    @Column(name = "numerolap")
    private String numerolap;

    @Column(name = "numerooficioconcessaoprorrogacao_1")
    private String numerooficioconcessaoprorrogacao1;

    @Column(name = "numerooficioconcessaoprorrogacao_2")
    private String numerooficioconcessaoprorrogacao2;

    @Column(name = "numerooficioconcessaoprorrogacao_3")
    private String numerooficioconcessaoprorrogacao3;

    @Column(name = "numerooficiolocalpedido")
    private String numerooficiolocalpedido;

    @Column(name = "numerooficiolocalrecebimento")
    private String numerooficiolocalrecebimento;

    @Column(name = "numerooficiooficialpedido")
    private String numerooficiooficialpedido;

    @Column(name = "numerooficiooficialrecebimento")
    private String numerooficiooficialrecebimento;

    @Column(name = "numerooficioprorrogacao_1")
    private String numerooficioprorrogacao1;

    @Column(name = "numerooficioprorrogacao_2")
    private String numerooficioprorrogacao2;

    @Column(name = "numerooficioprorrogacao_3")
    private String numerooficioprorrogacao3;

    @Column(name = "numeroparecertecnico")
    private String numeroparecertecnico;

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

    @Column(name = "reciboentregadocs")
    private String reciboentregadocs;

    @Column(name = "usosolo")
    private Boolean usosolo;

    @Column(name = "usosoloobs")
    private String usosoloobs;

    @ManyToOne
    private Atividadelicenca atividadelicenca;

    @ManyToOne
    private Empresa empresa;

    @ManyToOne
    private Projeto projeto;

    @ManyToOne
    private Tipolicenca tipo;

    @ManyToOne
    private Obra obra;

    @ManyToOne
    private Orgaoemissor orgaoemissor;

    @ManyToOne
    private Tipolicenca tipolicenca;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "licenca_condicionantes",
               joinColumns = @JoinColumn(name="licencas_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="condicionantes_id", referencedColumnName="id"))
    private Set<Condicionante> condicionantes = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "licenca_docs",
               joinColumns = @JoinColumn(name="licencas_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="docs_id", referencedColumnName="id"))
    private Set<Documento> docs = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "licenca_fotos",
               joinColumns = @JoinColumn(name="licencas_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="fotos_id", referencedColumnName="id"))
    private Set<Foto> fotos = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlbum() {
        return album;
    }

    public Licenca album(String album) {
        this.album = album;
        return this;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getAndamento() {
        return andamento;
    }

    public Licenca andamento(String andamento) {
        this.andamento = andamento;
        return this;
    }

    public void setAndamento(String andamento) {
        this.andamento = andamento;
    }

    public Boolean isCompensacaoambiental() {
        return compensacaoambiental;
    }

    public Licenca compensacaoambiental(Boolean compensacaoambiental) {
        this.compensacaoambiental = compensacaoambiental;
        return this;
    }

    public void setCompensacaoambiental(Boolean compensacaoambiental) {
        this.compensacaoambiental = compensacaoambiental;
    }

    public ZonedDateTime getDataemissao() {
        return dataemissao;
    }

    public Licenca dataemissao(ZonedDateTime dataemissao) {
        this.dataemissao = dataemissao;
        return this;
    }

    public void setDataemissao(ZonedDateTime dataemissao) {
        this.dataemissao = dataemissao;
    }

    public ZonedDateTime getDataentregadocs() {
        return dataentregadocs;
    }

    public Licenca dataentregadocs(ZonedDateTime dataentregadocs) {
        this.dataentregadocs = dataentregadocs;
        return this;
    }

    public void setDataentregadocs(ZonedDateTime dataentregadocs) {
        this.dataentregadocs = dataentregadocs;
    }

    public ZonedDateTime getDataexpedicaoprorrogacao1() {
        return dataexpedicaoprorrogacao1;
    }

    public Licenca dataexpedicaoprorrogacao1(ZonedDateTime dataexpedicaoprorrogacao1) {
        this.dataexpedicaoprorrogacao1 = dataexpedicaoprorrogacao1;
        return this;
    }

    public void setDataexpedicaoprorrogacao1(ZonedDateTime dataexpedicaoprorrogacao1) {
        this.dataexpedicaoprorrogacao1 = dataexpedicaoprorrogacao1;
    }

    public ZonedDateTime getDataexpedicaoprorrogacao2() {
        return dataexpedicaoprorrogacao2;
    }

    public Licenca dataexpedicaoprorrogacao2(ZonedDateTime dataexpedicaoprorrogacao2) {
        this.dataexpedicaoprorrogacao2 = dataexpedicaoprorrogacao2;
        return this;
    }

    public void setDataexpedicaoprorrogacao2(ZonedDateTime dataexpedicaoprorrogacao2) {
        this.dataexpedicaoprorrogacao2 = dataexpedicaoprorrogacao2;
    }

    public ZonedDateTime getDataexpedicaoprorrogacao3() {
        return dataexpedicaoprorrogacao3;
    }

    public Licenca dataexpedicaoprorrogacao3(ZonedDateTime dataexpedicaoprorrogacao3) {
        this.dataexpedicaoprorrogacao3 = dataexpedicaoprorrogacao3;
        return this;
    }

    public void setDataexpedicaoprorrogacao3(ZonedDateTime dataexpedicaoprorrogacao3) {
        this.dataexpedicaoprorrogacao3 = dataexpedicaoprorrogacao3;
    }

    public ZonedDateTime getDataoficiolocalpedido() {
        return dataoficiolocalpedido;
    }

    public Licenca dataoficiolocalpedido(ZonedDateTime dataoficiolocalpedido) {
        this.dataoficiolocalpedido = dataoficiolocalpedido;
        return this;
    }

    public void setDataoficiolocalpedido(ZonedDateTime dataoficiolocalpedido) {
        this.dataoficiolocalpedido = dataoficiolocalpedido;
    }

    public ZonedDateTime getDataoficiolocalrecebimento() {
        return dataoficiolocalrecebimento;
    }

    public Licenca dataoficiolocalrecebimento(ZonedDateTime dataoficiolocalrecebimento) {
        this.dataoficiolocalrecebimento = dataoficiolocalrecebimento;
        return this;
    }

    public void setDataoficiolocalrecebimento(ZonedDateTime dataoficiolocalrecebimento) {
        this.dataoficiolocalrecebimento = dataoficiolocalrecebimento;
    }

    public ZonedDateTime getDataoficioreoficialpedido() {
        return dataoficioreoficialpedido;
    }

    public Licenca dataoficioreoficialpedido(ZonedDateTime dataoficioreoficialpedido) {
        this.dataoficioreoficialpedido = dataoficioreoficialpedido;
        return this;
    }

    public void setDataoficioreoficialpedido(ZonedDateTime dataoficioreoficialpedido) {
        this.dataoficioreoficialpedido = dataoficioreoficialpedido;
    }

    public ZonedDateTime getDataoficioreoficialrecebimento() {
        return dataoficioreoficialrecebimento;
    }

    public Licenca dataoficioreoficialrecebimento(ZonedDateTime dataoficioreoficialrecebimento) {
        this.dataoficioreoficialrecebimento = dataoficioreoficialrecebimento;
        return this;
    }

    public void setDataoficioreoficialrecebimento(ZonedDateTime dataoficioreoficialrecebimento) {
        this.dataoficioreoficialrecebimento = dataoficioreoficialrecebimento;
    }

    public ZonedDateTime getDatapedidoprorrogacao1() {
        return datapedidoprorrogacao1;
    }

    public Licenca datapedidoprorrogacao1(ZonedDateTime datapedidoprorrogacao1) {
        this.datapedidoprorrogacao1 = datapedidoprorrogacao1;
        return this;
    }

    public void setDatapedidoprorrogacao1(ZonedDateTime datapedidoprorrogacao1) {
        this.datapedidoprorrogacao1 = datapedidoprorrogacao1;
    }

    public ZonedDateTime getDatapedidoprorrogacao2() {
        return datapedidoprorrogacao2;
    }

    public Licenca datapedidoprorrogacao2(ZonedDateTime datapedidoprorrogacao2) {
        this.datapedidoprorrogacao2 = datapedidoprorrogacao2;
        return this;
    }

    public void setDatapedidoprorrogacao2(ZonedDateTime datapedidoprorrogacao2) {
        this.datapedidoprorrogacao2 = datapedidoprorrogacao2;
    }

    public ZonedDateTime getDatapedidoprorrogacao3() {
        return datapedidoprorrogacao3;
    }

    public Licenca datapedidoprorrogacao3(ZonedDateTime datapedidoprorrogacao3) {
        this.datapedidoprorrogacao3 = datapedidoprorrogacao3;
        return this;
    }

    public void setDatapedidoprorrogacao3(ZonedDateTime datapedidoprorrogacao3) {
        this.datapedidoprorrogacao3 = datapedidoprorrogacao3;
    }

    public ZonedDateTime getDatavalidadeprorrogacao1() {
        return datavalidadeprorrogacao1;
    }

    public Licenca datavalidadeprorrogacao1(ZonedDateTime datavalidadeprorrogacao1) {
        this.datavalidadeprorrogacao1 = datavalidadeprorrogacao1;
        return this;
    }

    public void setDatavalidadeprorrogacao1(ZonedDateTime datavalidadeprorrogacao1) {
        this.datavalidadeprorrogacao1 = datavalidadeprorrogacao1;
    }

    public ZonedDateTime getDatavalidadeprorrogacao2() {
        return datavalidadeprorrogacao2;
    }

    public Licenca datavalidadeprorrogacao2(ZonedDateTime datavalidadeprorrogacao2) {
        this.datavalidadeprorrogacao2 = datavalidadeprorrogacao2;
        return this;
    }

    public void setDatavalidadeprorrogacao2(ZonedDateTime datavalidadeprorrogacao2) {
        this.datavalidadeprorrogacao2 = datavalidadeprorrogacao2;
    }

    public ZonedDateTime getDatavalidadeprorrogacao3() {
        return datavalidadeprorrogacao3;
    }

    public Licenca datavalidadeprorrogacao3(ZonedDateTime datavalidadeprorrogacao3) {
        this.datavalidadeprorrogacao3 = datavalidadeprorrogacao3;
        return this;
    }

    public void setDatavalidadeprorrogacao3(ZonedDateTime datavalidadeprorrogacao3) {
        this.datavalidadeprorrogacao3 = datavalidadeprorrogacao3;
    }

    public ZonedDateTime getDatavencimento() {
        return datavencimento;
    }

    public Licenca datavencimento(ZonedDateTime datavencimento) {
        this.datavencimento = datavencimento;
        return this;
    }

    public void setDatavencimento(ZonedDateTime datavencimento) {
        this.datavencimento = datavencimento;
    }

    public ZonedDateTime getDatavencimentoatual() {
        return datavencimentoatual;
    }

    public Licenca datavencimentoatual(ZonedDateTime datavencimentoatual) {
        this.datavencimentoatual = datavencimentoatual;
        return this;
    }

    public void setDatavencimentoatual(ZonedDateTime datavencimentoatual) {
        this.datavencimentoatual = datavencimentoatual;
    }

    public String getDescricao() {
        return descricao;
    }

    public Licenca descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean isDispensalai() {
        return dispensalai;
    }

    public Licenca dispensalai(Boolean dispensalai) {
        this.dispensalai = dispensalai;
        return this;
    }

    public void setDispensalai(Boolean dispensalai) {
        this.dispensalai = dispensalai;
    }

    public String getDocsentregues() {
        return docsentregues;
    }

    public Licenca docsentregues(String docsentregues) {
        this.docsentregues = docsentregues;
        return this;
    }

    public void setDocsentregues(String docsentregues) {
        this.docsentregues = docsentregues;
    }

    public Boolean isEiarima() {
        return eiarima;
    }

    public Licenca eiarima(Boolean eiarima) {
        this.eiarima = eiarima;
        return this;
    }

    public void setEiarima(Boolean eiarima) {
        this.eiarima = eiarima;
    }

    public String getFcei() {
        return fcei;
    }

    public Licenca fcei(String fcei) {
        this.fcei = fcei;
        return this;
    }

    public void setFcei(String fcei) {
        this.fcei = fcei;
    }

    public ZonedDateTime getFceidatapagamento() {
        return fceidatapagamento;
    }

    public Licenca fceidatapagamento(ZonedDateTime fceidatapagamento) {
        this.fceidatapagamento = fceidatapagamento;
        return this;
    }

    public void setFceidatapagamento(ZonedDateTime fceidatapagamento) {
        this.fceidatapagamento = fceidatapagamento;
    }

    public ZonedDateTime getFceidataprotocolo() {
        return fceidataprotocolo;
    }

    public Licenca fceidataprotocolo(ZonedDateTime fceidataprotocolo) {
        this.fceidataprotocolo = fceidataprotocolo;
        return this;
    }

    public void setFceidataprotocolo(ZonedDateTime fceidataprotocolo) {
        this.fceidataprotocolo = fceidataprotocolo;
    }

    public BigDecimal getFceivalor() {
        return fceivalor;
    }

    public Licenca fceivalor(BigDecimal fceivalor) {
        this.fceivalor = fceivalor;
        return this;
    }

    public void setFceivalor(BigDecimal fceivalor) {
        this.fceivalor = fceivalor;
    }

    public String getFolder() {
        return folder;
    }

    public Licenca folder(String folder) {
        this.folder = folder;
        return this;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public Boolean isInativo() {
        return inativo;
    }

    public Licenca inativo(Boolean inativo) {
        this.inativo = inativo;
        return this;
    }

    public void setInativo(Boolean inativo) {
        this.inativo = inativo;
    }

    public Boolean isInventarioflorestal() {
        return inventarioflorestal;
    }

    public Licenca inventarioflorestal(Boolean inventarioflorestal) {
        this.inventarioflorestal = inventarioflorestal;
        return this;
    }

    public void setInventarioflorestal(Boolean inventarioflorestal) {
        this.inventarioflorestal = inventarioflorestal;
    }

    public String getNumero() {
        return numero;
    }

    public Licenca numero(String numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNumerolap() {
        return numerolap;
    }

    public Licenca numerolap(String numerolap) {
        this.numerolap = numerolap;
        return this;
    }

    public void setNumerolap(String numerolap) {
        this.numerolap = numerolap;
    }

    public String getNumerooficioconcessaoprorrogacao1() {
        return numerooficioconcessaoprorrogacao1;
    }

    public Licenca numerooficioconcessaoprorrogacao1(String numerooficioconcessaoprorrogacao1) {
        this.numerooficioconcessaoprorrogacao1 = numerooficioconcessaoprorrogacao1;
        return this;
    }

    public void setNumerooficioconcessaoprorrogacao1(String numerooficioconcessaoprorrogacao1) {
        this.numerooficioconcessaoprorrogacao1 = numerooficioconcessaoprorrogacao1;
    }

    public String getNumerooficioconcessaoprorrogacao2() {
        return numerooficioconcessaoprorrogacao2;
    }

    public Licenca numerooficioconcessaoprorrogacao2(String numerooficioconcessaoprorrogacao2) {
        this.numerooficioconcessaoprorrogacao2 = numerooficioconcessaoprorrogacao2;
        return this;
    }

    public void setNumerooficioconcessaoprorrogacao2(String numerooficioconcessaoprorrogacao2) {
        this.numerooficioconcessaoprorrogacao2 = numerooficioconcessaoprorrogacao2;
    }

    public String getNumerooficioconcessaoprorrogacao3() {
        return numerooficioconcessaoprorrogacao3;
    }

    public Licenca numerooficioconcessaoprorrogacao3(String numerooficioconcessaoprorrogacao3) {
        this.numerooficioconcessaoprorrogacao3 = numerooficioconcessaoprorrogacao3;
        return this;
    }

    public void setNumerooficioconcessaoprorrogacao3(String numerooficioconcessaoprorrogacao3) {
        this.numerooficioconcessaoprorrogacao3 = numerooficioconcessaoprorrogacao3;
    }

    public String getNumerooficiolocalpedido() {
        return numerooficiolocalpedido;
    }

    public Licenca numerooficiolocalpedido(String numerooficiolocalpedido) {
        this.numerooficiolocalpedido = numerooficiolocalpedido;
        return this;
    }

    public void setNumerooficiolocalpedido(String numerooficiolocalpedido) {
        this.numerooficiolocalpedido = numerooficiolocalpedido;
    }

    public String getNumerooficiolocalrecebimento() {
        return numerooficiolocalrecebimento;
    }

    public Licenca numerooficiolocalrecebimento(String numerooficiolocalrecebimento) {
        this.numerooficiolocalrecebimento = numerooficiolocalrecebimento;
        return this;
    }

    public void setNumerooficiolocalrecebimento(String numerooficiolocalrecebimento) {
        this.numerooficiolocalrecebimento = numerooficiolocalrecebimento;
    }

    public String getNumerooficiooficialpedido() {
        return numerooficiooficialpedido;
    }

    public Licenca numerooficiooficialpedido(String numerooficiooficialpedido) {
        this.numerooficiooficialpedido = numerooficiooficialpedido;
        return this;
    }

    public void setNumerooficiooficialpedido(String numerooficiooficialpedido) {
        this.numerooficiooficialpedido = numerooficiooficialpedido;
    }

    public String getNumerooficiooficialrecebimento() {
        return numerooficiooficialrecebimento;
    }

    public Licenca numerooficiooficialrecebimento(String numerooficiooficialrecebimento) {
        this.numerooficiooficialrecebimento = numerooficiooficialrecebimento;
        return this;
    }

    public void setNumerooficiooficialrecebimento(String numerooficiooficialrecebimento) {
        this.numerooficiooficialrecebimento = numerooficiooficialrecebimento;
    }

    public String getNumerooficioprorrogacao1() {
        return numerooficioprorrogacao1;
    }

    public Licenca numerooficioprorrogacao1(String numerooficioprorrogacao1) {
        this.numerooficioprorrogacao1 = numerooficioprorrogacao1;
        return this;
    }

    public void setNumerooficioprorrogacao1(String numerooficioprorrogacao1) {
        this.numerooficioprorrogacao1 = numerooficioprorrogacao1;
    }

    public String getNumerooficioprorrogacao2() {
        return numerooficioprorrogacao2;
    }

    public Licenca numerooficioprorrogacao2(String numerooficioprorrogacao2) {
        this.numerooficioprorrogacao2 = numerooficioprorrogacao2;
        return this;
    }

    public void setNumerooficioprorrogacao2(String numerooficioprorrogacao2) {
        this.numerooficioprorrogacao2 = numerooficioprorrogacao2;
    }

    public String getNumerooficioprorrogacao3() {
        return numerooficioprorrogacao3;
    }

    public Licenca numerooficioprorrogacao3(String numerooficioprorrogacao3) {
        this.numerooficioprorrogacao3 = numerooficioprorrogacao3;
        return this;
    }

    public void setNumerooficioprorrogacao3(String numerooficioprorrogacao3) {
        this.numerooficioprorrogacao3 = numerooficioprorrogacao3;
    }

    public String getNumeroparecertecnico() {
        return numeroparecertecnico;
    }

    public Licenca numeroparecertecnico(String numeroparecertecnico) {
        this.numeroparecertecnico = numeroparecertecnico;
        return this;
    }

    public void setNumeroparecertecnico(String numeroparecertecnico) {
        this.numeroparecertecnico = numeroparecertecnico;
    }

    public String getNumeroprocesso() {
        return numeroprocesso;
    }

    public Licenca numeroprocesso(String numeroprocesso) {
        this.numeroprocesso = numeroprocesso;
        return this;
    }

    public void setNumeroprocesso(String numeroprocesso) {
        this.numeroprocesso = numeroprocesso;
    }

    public String getObservacao() {
        return observacao;
    }

    public Licenca observacao(String observacao) {
        this.observacao = observacao;
        return this;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getPendente() {
        return pendente;
    }

    public Licenca pendente(String pendente) {
        this.pendente = pendente;
        return this;
    }

    public void setPendente(String pendente) {
        this.pendente = pendente;
    }

    public Boolean isPrazomes() {
        return prazomes;
    }

    public Licenca prazomes(Boolean prazomes) {
        this.prazomes = prazomes;
        return this;
    }

    public void setPrazomes(Boolean prazomes) {
        this.prazomes = prazomes;
    }

    public Integer getPrazovalidade() {
        return prazovalidade;
    }

    public Licenca prazovalidade(Integer prazovalidade) {
        this.prazovalidade = prazovalidade;
        return this;
    }

    public void setPrazovalidade(Integer prazovalidade) {
        this.prazovalidade = prazovalidade;
    }

    public String getReciboentregadocs() {
        return reciboentregadocs;
    }

    public Licenca reciboentregadocs(String reciboentregadocs) {
        this.reciboentregadocs = reciboentregadocs;
        return this;
    }

    public void setReciboentregadocs(String reciboentregadocs) {
        this.reciboentregadocs = reciboentregadocs;
    }

    public Boolean isUsosolo() {
        return usosolo;
    }

    public Licenca usosolo(Boolean usosolo) {
        this.usosolo = usosolo;
        return this;
    }

    public void setUsosolo(Boolean usosolo) {
        this.usosolo = usosolo;
    }

    public String getUsosoloobs() {
        return usosoloobs;
    }

    public Licenca usosoloobs(String usosoloobs) {
        this.usosoloobs = usosoloobs;
        return this;
    }

    public void setUsosoloobs(String usosoloobs) {
        this.usosoloobs = usosoloobs;
    }

    public Atividadelicenca getAtividadelicenca() {
        return atividadelicenca;
    }

    public Licenca atividadelicenca(Atividadelicenca atividadelicenca) {
        this.atividadelicenca = atividadelicenca;
        return this;
    }

    public void setAtividadelicenca(Atividadelicenca atividadelicenca) {
        this.atividadelicenca = atividadelicenca;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public Licenca empresa(Empresa empresa) {
        this.empresa = empresa;
        return this;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public Licenca projeto(Projeto projeto) {
        this.projeto = projeto;
        return this;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public Tipolicenca getTipo() {
        return tipo;
    }

    public Licenca tipo(Tipolicenca tipolicenca) {
        this.tipo = tipolicenca;
        return this;
    }

    public void setTipo(Tipolicenca tipolicenca) {
        this.tipo = tipolicenca;
    }

    public Obra getObra() {
        return obra;
    }

    public Licenca obra(Obra obra) {
        this.obra = obra;
        return this;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public Orgaoemissor getOrgaoemissor() {
        return orgaoemissor;
    }

    public Licenca orgaoemissor(Orgaoemissor orgaoemissor) {
        this.orgaoemissor = orgaoemissor;
        return this;
    }

    public void setOrgaoemissor(Orgaoemissor orgaoemissor) {
        this.orgaoemissor = orgaoemissor;
    }

    public Tipolicenca getTipolicenca() {
        return tipolicenca;
    }

    public Licenca tipolicenca(Tipolicenca tipolicenca) {
        this.tipolicenca = tipolicenca;
        return this;
    }

    public void setTipolicenca(Tipolicenca tipolicenca) {
        this.tipolicenca = tipolicenca;
    }

    public Set<Condicionante> getCondicionantes() {
        return condicionantes;
    }

    public Licenca condicionantes(Set<Condicionante> condicionantes) {
        this.condicionantes = condicionantes;
        return this;
    }

    public Licenca addCondicionantes(Condicionante condicionante) {
        this.condicionantes.add(condicionante);
        return this;
    }

    public Licenca removeCondicionantes(Condicionante condicionante) {
        this.condicionantes.remove(condicionante);
        return this;
    }

    public void setCondicionantes(Set<Condicionante> condicionantes) {
        this.condicionantes = condicionantes;
    }

    public Set<Documento> getDocs() {
        return docs;
    }

    public Licenca docs(Set<Documento> documentos) {
        this.docs = documentos;
        return this;
    }

    public Licenca addDocs(Documento documento) {
        this.docs.add(documento);
        return this;
    }

    public Licenca removeDocs(Documento documento) {
        this.docs.remove(documento);
        return this;
    }

    public void setDocs(Set<Documento> documentos) {
        this.docs = documentos;
    }

    public Set<Foto> getFotos() {
        return fotos;
    }

    public Licenca fotos(Set<Foto> fotos) {
        this.fotos = fotos;
        return this;
    }

    public Licenca addFotos(Foto foto) {
        this.fotos.add(foto);
        return this;
    }

    public Licenca removeFotos(Foto foto) {
        this.fotos.remove(foto);
        return this;
    }

    public void setFotos(Set<Foto> fotos) {
        this.fotos = fotos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Licenca licenca = (Licenca) o;
        if (licenca.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), licenca.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Licenca{" +
            "id=" + getId() +
            ", album='" + getAlbum() + "'" +
            ", andamento='" + getAndamento() + "'" +
            ", compensacaoambiental='" + isCompensacaoambiental() + "'" +
            ", dataemissao='" + getDataemissao() + "'" +
            ", dataentregadocs='" + getDataentregadocs() + "'" +
            ", dataexpedicaoprorrogacao1='" + getDataexpedicaoprorrogacao1() + "'" +
            ", dataexpedicaoprorrogacao2='" + getDataexpedicaoprorrogacao2() + "'" +
            ", dataexpedicaoprorrogacao3='" + getDataexpedicaoprorrogacao3() + "'" +
            ", dataoficiolocalpedido='" + getDataoficiolocalpedido() + "'" +
            ", dataoficiolocalrecebimento='" + getDataoficiolocalrecebimento() + "'" +
            ", dataoficioreoficialpedido='" + getDataoficioreoficialpedido() + "'" +
            ", dataoficioreoficialrecebimento='" + getDataoficioreoficialrecebimento() + "'" +
            ", datapedidoprorrogacao1='" + getDatapedidoprorrogacao1() + "'" +
            ", datapedidoprorrogacao2='" + getDatapedidoprorrogacao2() + "'" +
            ", datapedidoprorrogacao3='" + getDatapedidoprorrogacao3() + "'" +
            ", datavalidadeprorrogacao1='" + getDatavalidadeprorrogacao1() + "'" +
            ", datavalidadeprorrogacao2='" + getDatavalidadeprorrogacao2() + "'" +
            ", datavalidadeprorrogacao3='" + getDatavalidadeprorrogacao3() + "'" +
            ", datavencimento='" + getDatavencimento() + "'" +
            ", datavencimentoatual='" + getDatavencimentoatual() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", dispensalai='" + isDispensalai() + "'" +
            ", docsentregues='" + getDocsentregues() + "'" +
            ", eiarima='" + isEiarima() + "'" +
            ", fcei='" + getFcei() + "'" +
            ", fceidatapagamento='" + getFceidatapagamento() + "'" +
            ", fceidataprotocolo='" + getFceidataprotocolo() + "'" +
            ", fceivalor='" + getFceivalor() + "'" +
            ", folder='" + getFolder() + "'" +
            ", inativo='" + isInativo() + "'" +
            ", inventarioflorestal='" + isInventarioflorestal() + "'" +
            ", numero='" + getNumero() + "'" +
            ", numerolap='" + getNumerolap() + "'" +
            ", numerooficioconcessaoprorrogacao1='" + getNumerooficioconcessaoprorrogacao1() + "'" +
            ", numerooficioconcessaoprorrogacao2='" + getNumerooficioconcessaoprorrogacao2() + "'" +
            ", numerooficioconcessaoprorrogacao3='" + getNumerooficioconcessaoprorrogacao3() + "'" +
            ", numerooficiolocalpedido='" + getNumerooficiolocalpedido() + "'" +
            ", numerooficiolocalrecebimento='" + getNumerooficiolocalrecebimento() + "'" +
            ", numerooficiooficialpedido='" + getNumerooficiooficialpedido() + "'" +
            ", numerooficiooficialrecebimento='" + getNumerooficiooficialrecebimento() + "'" +
            ", numerooficioprorrogacao1='" + getNumerooficioprorrogacao1() + "'" +
            ", numerooficioprorrogacao2='" + getNumerooficioprorrogacao2() + "'" +
            ", numerooficioprorrogacao3='" + getNumerooficioprorrogacao3() + "'" +
            ", numeroparecertecnico='" + getNumeroparecertecnico() + "'" +
            ", numeroprocesso='" + getNumeroprocesso() + "'" +
            ", observacao='" + getObservacao() + "'" +
            ", pendente='" + getPendente() + "'" +
            ", prazomes='" + isPrazomes() + "'" +
            ", prazovalidade='" + getPrazovalidade() + "'" +
            ", reciboentregadocs='" + getReciboentregadocs() + "'" +
            ", usosolo='" + isUsosolo() + "'" +
            ", usosoloobs='" + getUsosoloobs() + "'" +
            "}";
    }
}
