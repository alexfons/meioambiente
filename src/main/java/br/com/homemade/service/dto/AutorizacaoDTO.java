package br.com.homemade.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Autorizacao entity.
 */
public class AutorizacaoDTO implements Serializable {

    private Long id;

    private String album;

    private String andamento;

    private ZonedDateTime data;

    private ZonedDateTime dataentregadocs;

    private ZonedDateTime dataexpedicaoprorrogacao1;

    private ZonedDateTime dataexpedicaoprorrogacao2;

    private ZonedDateTime dataexpedicaoprorrogacao3;

    private ZonedDateTime datapedidoprorrogacao1;

    private ZonedDateTime datapedidoprorrogacao2;

    private ZonedDateTime datapedidoprorrogacao3;

    private ZonedDateTime datavalidadeprorrogacao1;

    private ZonedDateTime datavalidadeprorrogacao2;

    private ZonedDateTime datavalidadeprorrogacao3;

    private ZonedDateTime datavencimento;

    private ZonedDateTime datavencimentoatual;

    private String descricao;

    private String docsentregues;

    private String fcei;

    private ZonedDateTime fceidatapagamento;

    private ZonedDateTime fceidataprotocolo;

    private Double fceivalor;

    private String folder;

    private Boolean inativo;

    private Integer kmfim;

    private Integer kminicio;

    private String lado;

    private Boolean naoria;

    private String numero;

    private String numerooficioconcessaoprorrogacao1;

    private String numerooficioconcessaoprorrogacao2;

    private String numerooficioconcessaoprorrogacao3;

    private String numerooficioprorrogacao1;

    private String numerooficioprorrogacao2;

    private String numerooficioprorrogacao3;

    private String numeroprocesso;

    private String observacao;

    private String pendente;

    private Boolean prazomes;

    private Integer prazovalidade;

    private String proprietario;

    private String reciboentregadocs;

    private Long atividadelicencaId;

    private Long empresaId;

    private Long obraId;

    private Long orgaoemissorId;

    private Long projetoId;

    private Long tipoautorizacaoId;

    private Set<FotoDTO> fotos = new HashSet<>();

    private Set<DocumentoDTO> docs = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getAndamento() {
        return andamento;
    }

    public void setAndamento(String andamento) {
        this.andamento = andamento;
    }

    public ZonedDateTime getData() {
        return data;
    }

    public void setData(ZonedDateTime data) {
        this.data = data;
    }

    public ZonedDateTime getDataentregadocs() {
        return dataentregadocs;
    }

    public void setDataentregadocs(ZonedDateTime dataentregadocs) {
        this.dataentregadocs = dataentregadocs;
    }

    public ZonedDateTime getDataexpedicaoprorrogacao1() {
        return dataexpedicaoprorrogacao1;
    }

    public void setDataexpedicaoprorrogacao1(ZonedDateTime dataexpedicaoprorrogacao1) {
        this.dataexpedicaoprorrogacao1 = dataexpedicaoprorrogacao1;
    }

    public ZonedDateTime getDataexpedicaoprorrogacao2() {
        return dataexpedicaoprorrogacao2;
    }

    public void setDataexpedicaoprorrogacao2(ZonedDateTime dataexpedicaoprorrogacao2) {
        this.dataexpedicaoprorrogacao2 = dataexpedicaoprorrogacao2;
    }

    public ZonedDateTime getDataexpedicaoprorrogacao3() {
        return dataexpedicaoprorrogacao3;
    }

    public void setDataexpedicaoprorrogacao3(ZonedDateTime dataexpedicaoprorrogacao3) {
        this.dataexpedicaoprorrogacao3 = dataexpedicaoprorrogacao3;
    }

    public ZonedDateTime getDatapedidoprorrogacao1() {
        return datapedidoprorrogacao1;
    }

    public void setDatapedidoprorrogacao1(ZonedDateTime datapedidoprorrogacao1) {
        this.datapedidoprorrogacao1 = datapedidoprorrogacao1;
    }

    public ZonedDateTime getDatapedidoprorrogacao2() {
        return datapedidoprorrogacao2;
    }

    public void setDatapedidoprorrogacao2(ZonedDateTime datapedidoprorrogacao2) {
        this.datapedidoprorrogacao2 = datapedidoprorrogacao2;
    }

    public ZonedDateTime getDatapedidoprorrogacao3() {
        return datapedidoprorrogacao3;
    }

    public void setDatapedidoprorrogacao3(ZonedDateTime datapedidoprorrogacao3) {
        this.datapedidoprorrogacao3 = datapedidoprorrogacao3;
    }

    public ZonedDateTime getDatavalidadeprorrogacao1() {
        return datavalidadeprorrogacao1;
    }

    public void setDatavalidadeprorrogacao1(ZonedDateTime datavalidadeprorrogacao1) {
        this.datavalidadeprorrogacao1 = datavalidadeprorrogacao1;
    }

    public ZonedDateTime getDatavalidadeprorrogacao2() {
        return datavalidadeprorrogacao2;
    }

    public void setDatavalidadeprorrogacao2(ZonedDateTime datavalidadeprorrogacao2) {
        this.datavalidadeprorrogacao2 = datavalidadeprorrogacao2;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDocsentregues() {
        return docsentregues;
    }

    public void setDocsentregues(String docsentregues) {
        this.docsentregues = docsentregues;
    }

    public String getFcei() {
        return fcei;
    }

    public void setFcei(String fcei) {
        this.fcei = fcei;
    }

    public ZonedDateTime getFceidatapagamento() {
        return fceidatapagamento;
    }

    public void setFceidatapagamento(ZonedDateTime fceidatapagamento) {
        this.fceidatapagamento = fceidatapagamento;
    }

    public ZonedDateTime getFceidataprotocolo() {
        return fceidataprotocolo;
    }

    public void setFceidataprotocolo(ZonedDateTime fceidataprotocolo) {
        this.fceidataprotocolo = fceidataprotocolo;
    }

    public Double getFceivalor() {
        return fceivalor;
    }

    public void setFceivalor(Double fceivalor) {
        this.fceivalor = fceivalor;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public Boolean isInativo() {
        return inativo;
    }

    public void setInativo(Boolean inativo) {
        this.inativo = inativo;
    }

    public Integer getKmfim() {
        return kmfim;
    }

    public void setKmfim(Integer kmfim) {
        this.kmfim = kmfim;
    }

    public Integer getKminicio() {
        return kminicio;
    }

    public void setKminicio(Integer kminicio) {
        this.kminicio = kminicio;
    }

    public String getLado() {
        return lado;
    }

    public void setLado(String lado) {
        this.lado = lado;
    }

    public Boolean isNaoria() {
        return naoria;
    }

    public void setNaoria(Boolean naoria) {
        this.naoria = naoria;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNumerooficioconcessaoprorrogacao1() {
        return numerooficioconcessaoprorrogacao1;
    }

    public void setNumerooficioconcessaoprorrogacao1(String numerooficioconcessaoprorrogacao1) {
        this.numerooficioconcessaoprorrogacao1 = numerooficioconcessaoprorrogacao1;
    }

    public String getNumerooficioconcessaoprorrogacao2() {
        return numerooficioconcessaoprorrogacao2;
    }

    public void setNumerooficioconcessaoprorrogacao2(String numerooficioconcessaoprorrogacao2) {
        this.numerooficioconcessaoprorrogacao2 = numerooficioconcessaoprorrogacao2;
    }

    public String getNumerooficioconcessaoprorrogacao3() {
        return numerooficioconcessaoprorrogacao3;
    }

    public void setNumerooficioconcessaoprorrogacao3(String numerooficioconcessaoprorrogacao3) {
        this.numerooficioconcessaoprorrogacao3 = numerooficioconcessaoprorrogacao3;
    }

    public String getNumerooficioprorrogacao1() {
        return numerooficioprorrogacao1;
    }

    public void setNumerooficioprorrogacao1(String numerooficioprorrogacao1) {
        this.numerooficioprorrogacao1 = numerooficioprorrogacao1;
    }

    public String getNumerooficioprorrogacao2() {
        return numerooficioprorrogacao2;
    }

    public void setNumerooficioprorrogacao2(String numerooficioprorrogacao2) {
        this.numerooficioprorrogacao2 = numerooficioprorrogacao2;
    }

    public String getNumerooficioprorrogacao3() {
        return numerooficioprorrogacao3;
    }

    public void setNumerooficioprorrogacao3(String numerooficioprorrogacao3) {
        this.numerooficioprorrogacao3 = numerooficioprorrogacao3;
    }

    public String getNumeroprocesso() {
        return numeroprocesso;
    }

    public void setNumeroprocesso(String numeroprocesso) {
        this.numeroprocesso = numeroprocesso;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getPendente() {
        return pendente;
    }

    public void setPendente(String pendente) {
        this.pendente = pendente;
    }

    public Boolean isPrazomes() {
        return prazomes;
    }

    public void setPrazomes(Boolean prazomes) {
        this.prazomes = prazomes;
    }

    public Integer getPrazovalidade() {
        return prazovalidade;
    }

    public void setPrazovalidade(Integer prazovalidade) {
        this.prazovalidade = prazovalidade;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public String getReciboentregadocs() {
        return reciboentregadocs;
    }

    public void setReciboentregadocs(String reciboentregadocs) {
        this.reciboentregadocs = reciboentregadocs;
    }

    public Long getAtividadelicencaId() {
        return atividadelicencaId;
    }

    public void setAtividadelicencaId(Long atividadelicencaId) {
        this.atividadelicencaId = atividadelicencaId;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public Long getObraId() {
        return obraId;
    }

    public void setObraId(Long obraId) {
        this.obraId = obraId;
    }

    public Long getOrgaoemissorId() {
        return orgaoemissorId;
    }

    public void setOrgaoemissorId(Long orgaoemissorId) {
        this.orgaoemissorId = orgaoemissorId;
    }

    public Long getProjetoId() {
        return projetoId;
    }

    public void setProjetoId(Long projetoId) {
        this.projetoId = projetoId;
    }

    public Long getTipoautorizacaoId() {
        return tipoautorizacaoId;
    }

    public void setTipoautorizacaoId(Long tipoautorizacaoId) {
        this.tipoautorizacaoId = tipoautorizacaoId;
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

        AutorizacaoDTO autorizacaoDTO = (AutorizacaoDTO) o;
        if(autorizacaoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), autorizacaoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AutorizacaoDTO{" +
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
