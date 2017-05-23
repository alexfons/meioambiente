package br.com.homemade.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Auc entity.
 */
public class AucDTO implements Serializable {

    private Long id;

    private BigDecimal fceivalor;

    private Boolean inativo;

    private Boolean prazomes;

    private Boolean reposicaoflorestal;

    private Boolean compensacaoambiental;

    private Integer prazovalidade;

    private String fcei;

    private String reciboentregadocs;

    private String numero;

    private String numeroprocesso;

    private String numerooficiolocalpedido;

    private String numerooficiooficialpedido;

    private String numerooficiolocalrecebimento;

    private String numerooficiooficialrecebimento;

    private String numeroparecertecnico;

    private String numerooficioprorrogacao1;

    private String numerooficioconcessaoprorrogacao1;

    private String numerooficioprorrogacao2;

    private String numerooficioconcessaoprorrogacao2;

    private String numerooficioprorrogacao3;

    private String numerooficioconcessaoprorrogacao3;

    private String album;

    private String folder;

    private String pendente;

    private String andamento;

    private String descricao;

    private String observacao;

    private String docsentregues;

    private String reposicaoflorestalobs;

    private String compensacaoambientalobs;

    private ZonedDateTime fceidataprotocolo;

    private ZonedDateTime fceidatapagamento;

    private ZonedDateTime dataentregadocs;

    private ZonedDateTime dataoficiolocalpedido;

    private ZonedDateTime dataoficioreoficialpedido;

    private ZonedDateTime dataoficiolocalrecebimento;

    private ZonedDateTime dataoficioreoficialrecebimento;

    private ZonedDateTime dataemissao;

    private ZonedDateTime datapedidoprorrogacao1;

    private ZonedDateTime dataexpedicaoprorrogacao1;

    private ZonedDateTime datavalidadeprorrogacao1;

    private ZonedDateTime datapedidoprorrogacao2;

    private ZonedDateTime dataexpedicaoprorrogacao2;

    private ZonedDateTime datavalidadeprorrogacao2;

    private ZonedDateTime datapedidoprorrogacao3;

    private ZonedDateTime dataexpedicaoprorrogacao3;

    private ZonedDateTime datavalidadeprorrogacao3;

    private ZonedDateTime datavencimento;

    private ZonedDateTime datavencimentoatual;

    private Long atividadelicencaId;

    private Long orgaoemissorId;

    private Long obraId;

    private Long projetoId;

    private Long empresaId;

    private Set<CondicionanteDTO> condicionantes = new HashSet<>();

    private Set<FotoDTO> fotos = new HashSet<>();

    private Set<DocumentoDTO> docs = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getFceivalor() {
        return fceivalor;
    }

    public void setFceivalor(BigDecimal fceivalor) {
        this.fceivalor = fceivalor;
    }

    public Boolean isInativo() {
        return inativo;
    }

    public void setInativo(Boolean inativo) {
        this.inativo = inativo;
    }

    public Boolean isPrazomes() {
        return prazomes;
    }

    public void setPrazomes(Boolean prazomes) {
        this.prazomes = prazomes;
    }

    public Boolean isReposicaoflorestal() {
        return reposicaoflorestal;
    }

    public void setReposicaoflorestal(Boolean reposicaoflorestal) {
        this.reposicaoflorestal = reposicaoflorestal;
    }

    public Boolean isCompensacaoambiental() {
        return compensacaoambiental;
    }

    public void setCompensacaoambiental(Boolean compensacaoambiental) {
        this.compensacaoambiental = compensacaoambiental;
    }

    public Integer getPrazovalidade() {
        return prazovalidade;
    }

    public void setPrazovalidade(Integer prazovalidade) {
        this.prazovalidade = prazovalidade;
    }

    public String getFcei() {
        return fcei;
    }

    public void setFcei(String fcei) {
        this.fcei = fcei;
    }

    public String getReciboentregadocs() {
        return reciboentregadocs;
    }

    public void setReciboentregadocs(String reciboentregadocs) {
        this.reciboentregadocs = reciboentregadocs;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNumeroprocesso() {
        return numeroprocesso;
    }

    public void setNumeroprocesso(String numeroprocesso) {
        this.numeroprocesso = numeroprocesso;
    }

    public String getNumerooficiolocalpedido() {
        return numerooficiolocalpedido;
    }

    public void setNumerooficiolocalpedido(String numerooficiolocalpedido) {
        this.numerooficiolocalpedido = numerooficiolocalpedido;
    }

    public String getNumerooficiooficialpedido() {
        return numerooficiooficialpedido;
    }

    public void setNumerooficiooficialpedido(String numerooficiooficialpedido) {
        this.numerooficiooficialpedido = numerooficiooficialpedido;
    }

    public String getNumerooficiolocalrecebimento() {
        return numerooficiolocalrecebimento;
    }

    public void setNumerooficiolocalrecebimento(String numerooficiolocalrecebimento) {
        this.numerooficiolocalrecebimento = numerooficiolocalrecebimento;
    }

    public String getNumerooficiooficialrecebimento() {
        return numerooficiooficialrecebimento;
    }

    public void setNumerooficiooficialrecebimento(String numerooficiooficialrecebimento) {
        this.numerooficiooficialrecebimento = numerooficiooficialrecebimento;
    }

    public String getNumeroparecertecnico() {
        return numeroparecertecnico;
    }

    public void setNumeroparecertecnico(String numeroparecertecnico) {
        this.numeroparecertecnico = numeroparecertecnico;
    }

    public String getNumerooficioprorrogacao1() {
        return numerooficioprorrogacao1;
    }

    public void setNumerooficioprorrogacao1(String numerooficioprorrogacao1) {
        this.numerooficioprorrogacao1 = numerooficioprorrogacao1;
    }

    public String getNumerooficioconcessaoprorrogacao1() {
        return numerooficioconcessaoprorrogacao1;
    }

    public void setNumerooficioconcessaoprorrogacao1(String numerooficioconcessaoprorrogacao1) {
        this.numerooficioconcessaoprorrogacao1 = numerooficioconcessaoprorrogacao1;
    }

    public String getNumerooficioprorrogacao2() {
        return numerooficioprorrogacao2;
    }

    public void setNumerooficioprorrogacao2(String numerooficioprorrogacao2) {
        this.numerooficioprorrogacao2 = numerooficioprorrogacao2;
    }

    public String getNumerooficioconcessaoprorrogacao2() {
        return numerooficioconcessaoprorrogacao2;
    }

    public void setNumerooficioconcessaoprorrogacao2(String numerooficioconcessaoprorrogacao2) {
        this.numerooficioconcessaoprorrogacao2 = numerooficioconcessaoprorrogacao2;
    }

    public String getNumerooficioprorrogacao3() {
        return numerooficioprorrogacao3;
    }

    public void setNumerooficioprorrogacao3(String numerooficioprorrogacao3) {
        this.numerooficioprorrogacao3 = numerooficioprorrogacao3;
    }

    public String getNumerooficioconcessaoprorrogacao3() {
        return numerooficioconcessaoprorrogacao3;
    }

    public void setNumerooficioconcessaoprorrogacao3(String numerooficioconcessaoprorrogacao3) {
        this.numerooficioconcessaoprorrogacao3 = numerooficioconcessaoprorrogacao3;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getPendente() {
        return pendente;
    }

    public void setPendente(String pendente) {
        this.pendente = pendente;
    }

    public String getAndamento() {
        return andamento;
    }

    public void setAndamento(String andamento) {
        this.andamento = andamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getDocsentregues() {
        return docsentregues;
    }

    public void setDocsentregues(String docsentregues) {
        this.docsentregues = docsentregues;
    }

    public String getReposicaoflorestalobs() {
        return reposicaoflorestalobs;
    }

    public void setReposicaoflorestalobs(String reposicaoflorestalobs) {
        this.reposicaoflorestalobs = reposicaoflorestalobs;
    }

    public String getCompensacaoambientalobs() {
        return compensacaoambientalobs;
    }

    public void setCompensacaoambientalobs(String compensacaoambientalobs) {
        this.compensacaoambientalobs = compensacaoambientalobs;
    }

    public ZonedDateTime getFceidataprotocolo() {
        return fceidataprotocolo;
    }

    public void setFceidataprotocolo(ZonedDateTime fceidataprotocolo) {
        this.fceidataprotocolo = fceidataprotocolo;
    }

    public ZonedDateTime getFceidatapagamento() {
        return fceidatapagamento;
    }

    public void setFceidatapagamento(ZonedDateTime fceidatapagamento) {
        this.fceidatapagamento = fceidatapagamento;
    }

    public ZonedDateTime getDataentregadocs() {
        return dataentregadocs;
    }

    public void setDataentregadocs(ZonedDateTime dataentregadocs) {
        this.dataentregadocs = dataentregadocs;
    }

    public ZonedDateTime getDataoficiolocalpedido() {
        return dataoficiolocalpedido;
    }

    public void setDataoficiolocalpedido(ZonedDateTime dataoficiolocalpedido) {
        this.dataoficiolocalpedido = dataoficiolocalpedido;
    }

    public ZonedDateTime getDataoficioreoficialpedido() {
        return dataoficioreoficialpedido;
    }

    public void setDataoficioreoficialpedido(ZonedDateTime dataoficioreoficialpedido) {
        this.dataoficioreoficialpedido = dataoficioreoficialpedido;
    }

    public ZonedDateTime getDataoficiolocalrecebimento() {
        return dataoficiolocalrecebimento;
    }

    public void setDataoficiolocalrecebimento(ZonedDateTime dataoficiolocalrecebimento) {
        this.dataoficiolocalrecebimento = dataoficiolocalrecebimento;
    }

    public ZonedDateTime getDataoficioreoficialrecebimento() {
        return dataoficioreoficialrecebimento;
    }

    public void setDataoficioreoficialrecebimento(ZonedDateTime dataoficioreoficialrecebimento) {
        this.dataoficioreoficialrecebimento = dataoficioreoficialrecebimento;
    }

    public ZonedDateTime getDataemissao() {
        return dataemissao;
    }

    public void setDataemissao(ZonedDateTime dataemissao) {
        this.dataemissao = dataemissao;
    }

    public ZonedDateTime getDatapedidoprorrogacao1() {
        return datapedidoprorrogacao1;
    }

    public void setDatapedidoprorrogacao1(ZonedDateTime datapedidoprorrogacao1) {
        this.datapedidoprorrogacao1 = datapedidoprorrogacao1;
    }

    public ZonedDateTime getDataexpedicaoprorrogacao1() {
        return dataexpedicaoprorrogacao1;
    }

    public void setDataexpedicaoprorrogacao1(ZonedDateTime dataexpedicaoprorrogacao1) {
        this.dataexpedicaoprorrogacao1 = dataexpedicaoprorrogacao1;
    }

    public ZonedDateTime getDatavalidadeprorrogacao1() {
        return datavalidadeprorrogacao1;
    }

    public void setDatavalidadeprorrogacao1(ZonedDateTime datavalidadeprorrogacao1) {
        this.datavalidadeprorrogacao1 = datavalidadeprorrogacao1;
    }

    public ZonedDateTime getDatapedidoprorrogacao2() {
        return datapedidoprorrogacao2;
    }

    public void setDatapedidoprorrogacao2(ZonedDateTime datapedidoprorrogacao2) {
        this.datapedidoprorrogacao2 = datapedidoprorrogacao2;
    }

    public ZonedDateTime getDataexpedicaoprorrogacao2() {
        return dataexpedicaoprorrogacao2;
    }

    public void setDataexpedicaoprorrogacao2(ZonedDateTime dataexpedicaoprorrogacao2) {
        this.dataexpedicaoprorrogacao2 = dataexpedicaoprorrogacao2;
    }

    public ZonedDateTime getDatavalidadeprorrogacao2() {
        return datavalidadeprorrogacao2;
    }

    public void setDatavalidadeprorrogacao2(ZonedDateTime datavalidadeprorrogacao2) {
        this.datavalidadeprorrogacao2 = datavalidadeprorrogacao2;
    }

    public ZonedDateTime getDatapedidoprorrogacao3() {
        return datapedidoprorrogacao3;
    }

    public void setDatapedidoprorrogacao3(ZonedDateTime datapedidoprorrogacao3) {
        this.datapedidoprorrogacao3 = datapedidoprorrogacao3;
    }

    public ZonedDateTime getDataexpedicaoprorrogacao3() {
        return dataexpedicaoprorrogacao3;
    }

    public void setDataexpedicaoprorrogacao3(ZonedDateTime dataexpedicaoprorrogacao3) {
        this.dataexpedicaoprorrogacao3 = dataexpedicaoprorrogacao3;
    }

    public ZonedDateTime getDatavalidadeprorrogacao3() {
        return datavalidadeprorrogacao3;
    }

    public void setDatavalidadeprorrogacao3(ZonedDateTime datavalidadeprorrogacao3) {
        this.datavalidadeprorrogacao3 = datavalidadeprorrogacao3;
    }

    public ZonedDateTime getDatavencimento() {
        return datavencimento;
    }

    public void setDatavencimento(ZonedDateTime datavencimento) {
        this.datavencimento = datavencimento;
    }

    public ZonedDateTime getDatavencimentoatual() {
        return datavencimentoatual;
    }

    public void setDatavencimentoatual(ZonedDateTime datavencimentoatual) {
        this.datavencimentoatual = datavencimentoatual;
    }

    public Long getAtividadelicencaId() {
        return atividadelicencaId;
    }

    public void setAtividadelicencaId(Long atividadelicencaId) {
        this.atividadelicencaId = atividadelicencaId;
    }

    public Long getOrgaoemissorId() {
        return orgaoemissorId;
    }

    public void setOrgaoemissorId(Long orgaoemissorId) {
        this.orgaoemissorId = orgaoemissorId;
    }

    public Long getObraId() {
        return obraId;
    }

    public void setObraId(Long obraId) {
        this.obraId = obraId;
    }

    public Long getProjetoId() {
        return projetoId;
    }

    public void setProjetoId(Long projetoId) {
        this.projetoId = projetoId;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public Set<CondicionanteDTO> getCondicionantes() {
        return condicionantes;
    }

    public void setCondicionantes(Set<CondicionanteDTO> condicionantes) {
        this.condicionantes = condicionantes;
    }

    public Set<FotoDTO> getFotos() {
        return fotos;
    }

    public void setFotos(Set<FotoDTO> fotos) {
        this.fotos = fotos;
    }

    public Set<DocumentoDTO> getDocs() {
        return docs;
    }

    public void setDocs(Set<DocumentoDTO> documentos) {
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

        AucDTO aucDTO = (AucDTO) o;
        if(aucDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), aucDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AucDTO{" +
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
