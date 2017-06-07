package br.com.homemade.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Licenca entity.
 */
public class LicencaDTO implements Serializable {

    private Long id;

    private String album;

    private String andamento;

    private Boolean compensacaoambiental;

    private ZonedDateTime dataemissao;

    private ZonedDateTime dataentregadocs;

    private ZonedDateTime dataexpedicaoprorrogacao1;

    private ZonedDateTime dataexpedicaoprorrogacao2;

    private ZonedDateTime dataexpedicaoprorrogacao3;

    private ZonedDateTime dataoficiolocalpedido;

    private ZonedDateTime dataoficiolocalrecebimento;

    private ZonedDateTime dataoficioreoficialpedido;

    private ZonedDateTime dataoficioreoficialrecebimento;

    private ZonedDateTime datapedidoprorrogacao1;

    private ZonedDateTime datapedidoprorrogacao2;

    private ZonedDateTime datapedidoprorrogacao3;

    private ZonedDateTime datavalidadeprorrogacao1;

    private ZonedDateTime datavalidadeprorrogacao2;

    private ZonedDateTime datavalidadeprorrogacao3;

    private ZonedDateTime datavencimento;

    private ZonedDateTime datavencimentoatual;

    private String descricao;

    private Boolean dispensalai;

    private String docsentregues;

    private Boolean eiarima;

    private String fcei;

    private ZonedDateTime fceidatapagamento;

    private ZonedDateTime fceidataprotocolo;

    private BigDecimal fceivalor;

    private String folder;

    private Boolean inativo;

    private Boolean inventarioflorestal;

    private String numero;

    private String numerolap;

    private String numerooficioconcessaoprorrogacao1;

    private String numerooficioconcessaoprorrogacao2;

    private String numerooficioconcessaoprorrogacao3;

    private String numerooficiolocalpedido;

    private String numerooficiolocalrecebimento;

    private String numerooficiooficialpedido;

    private String numerooficiooficialrecebimento;

    private String numerooficioprorrogacao1;

    private String numerooficioprorrogacao2;

    private String numerooficioprorrogacao3;

    private String numeroparecertecnico;

    private String numeroprocesso;

    private String observacao;

    private String pendente;

    private Boolean prazomes;

    private Integer prazovalidade;

    private String reciboentregadocs;

    private Boolean usosolo;

    private String usosoloobs;

    private Long atividadelicencaId;

    private Long empresaId;

    private Long projetoId;

    private Long tipoId;

    private Long obraId;

    private Long orgaoemissorId;

    private Long tipolicencaId;

    private Set<CondicionanteDTO> condicionantes = new HashSet<>();

    private Set<DocumentoDTO> docs = new HashSet<>();

    private Set<FotoDTO> fotos = new HashSet<>();

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

    public Boolean isCompensacaoambiental() {
        return compensacaoambiental;
    }

    public void setCompensacaoambiental(Boolean compensacaoambiental) {
        this.compensacaoambiental = compensacaoambiental;
    }

    public ZonedDateTime getDataemissao() {
        return dataemissao;
    }

    public void setDataemissao(ZonedDateTime dataemissao) {
        this.dataemissao = dataemissao;
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

    public ZonedDateTime getDataoficiolocalpedido() {
        return dataoficiolocalpedido;
    }

    public void setDataoficiolocalpedido(ZonedDateTime dataoficiolocalpedido) {
        this.dataoficiolocalpedido = dataoficiolocalpedido;
    }

    public ZonedDateTime getDataoficiolocalrecebimento() {
        return dataoficiolocalrecebimento;
    }

    public void setDataoficiolocalrecebimento(ZonedDateTime dataoficiolocalrecebimento) {
        this.dataoficiolocalrecebimento = dataoficiolocalrecebimento;
    }

    public ZonedDateTime getDataoficioreoficialpedido() {
        return dataoficioreoficialpedido;
    }

    public void setDataoficioreoficialpedido(ZonedDateTime dataoficioreoficialpedido) {
        this.dataoficioreoficialpedido = dataoficioreoficialpedido;
    }

    public ZonedDateTime getDataoficioreoficialrecebimento() {
        return dataoficioreoficialrecebimento;
    }

    public void setDataoficioreoficialrecebimento(ZonedDateTime dataoficioreoficialrecebimento) {
        this.dataoficioreoficialrecebimento = dataoficioreoficialrecebimento;
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

    public Boolean isDispensalai() {
        return dispensalai;
    }

    public void setDispensalai(Boolean dispensalai) {
        this.dispensalai = dispensalai;
    }

    public String getDocsentregues() {
        return docsentregues;
    }

    public void setDocsentregues(String docsentregues) {
        this.docsentregues = docsentregues;
    }

    public Boolean isEiarima() {
        return eiarima;
    }

    public void setEiarima(Boolean eiarima) {
        this.eiarima = eiarima;
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

    public BigDecimal getFceivalor() {
        return fceivalor;
    }

    public void setFceivalor(BigDecimal fceivalor) {
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

    public Boolean isInventarioflorestal() {
        return inventarioflorestal;
    }

    public void setInventarioflorestal(Boolean inventarioflorestal) {
        this.inventarioflorestal = inventarioflorestal;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNumerolap() {
        return numerolap;
    }

    public void setNumerolap(String numerolap) {
        this.numerolap = numerolap;
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

    public String getNumerooficiolocalpedido() {
        return numerooficiolocalpedido;
    }

    public void setNumerooficiolocalpedido(String numerooficiolocalpedido) {
        this.numerooficiolocalpedido = numerooficiolocalpedido;
    }

    public String getNumerooficiolocalrecebimento() {
        return numerooficiolocalrecebimento;
    }

    public void setNumerooficiolocalrecebimento(String numerooficiolocalrecebimento) {
        this.numerooficiolocalrecebimento = numerooficiolocalrecebimento;
    }

    public String getNumerooficiooficialpedido() {
        return numerooficiooficialpedido;
    }

    public void setNumerooficiooficialpedido(String numerooficiooficialpedido) {
        this.numerooficiooficialpedido = numerooficiooficialpedido;
    }

    public String getNumerooficiooficialrecebimento() {
        return numerooficiooficialrecebimento;
    }

    public void setNumerooficiooficialrecebimento(String numerooficiooficialrecebimento) {
        this.numerooficiooficialrecebimento = numerooficiooficialrecebimento;
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

    public String getNumeroparecertecnico() {
        return numeroparecertecnico;
    }

    public void setNumeroparecertecnico(String numeroparecertecnico) {
        this.numeroparecertecnico = numeroparecertecnico;
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

    public String getReciboentregadocs() {
        return reciboentregadocs;
    }

    public void setReciboentregadocs(String reciboentregadocs) {
        this.reciboentregadocs = reciboentregadocs;
    }

    public Boolean isUsosolo() {
        return usosolo;
    }

    public void setUsosolo(Boolean usosolo) {
        this.usosolo = usosolo;
    }

    public String getUsosoloobs() {
        return usosoloobs;
    }

    public void setUsosoloobs(String usosoloobs) {
        this.usosoloobs = usosoloobs;
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

    public Long getProjetoId() {
        return projetoId;
    }

    public void setProjetoId(Long projetoId) {
        this.projetoId = projetoId;
    }

    public Long getTipoId() {
        return tipoId;
    }

    public void setTipoId(Long tipolicencaId) {
        this.tipoId = tipolicencaId;
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

    public Long getTipolicencaId() {
        return tipolicencaId;
    }

    public void setTipolicencaId(Long tipolicencaId) {
        this.tipolicencaId = tipolicencaId;
    }

    public Set<CondicionanteDTO> getCondicionantes() {
        return condicionantes;
    }

    public void setCondicionantes(Set<CondicionanteDTO> condicionantes) {
        this.condicionantes = condicionantes;
    }

    public Set<DocumentoDTO> getDocs() {
        return docs;
    }

    public void setDocs(Set<DocumentoDTO> documentos) {
        this.docs = documentos;
    }

    public Set<FotoDTO> getFotos() {
        return fotos;
    }

    public void setFotos(Set<FotoDTO> fotos) {
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

        LicencaDTO licencaDTO = (LicencaDTO) o;
        if(licencaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), licencaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LicencaDTO{" +
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
