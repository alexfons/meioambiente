package br.com.homemade.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Ocorrencia entity.
 */
public class OcorrenciaDTO implements Serializable {

    private Long id;

    private String album;

    private String caracterizacao;

    private String coordenadaa;

    private String coordenadae;

    private String coordenadan;

    private ZonedDateTime data;

    private Float distanciaeixo;

    private String feature;

    private Integer kmfim;

    private Integer kminicio;

    private String lado;

    private Integer numero;

    private Long atividadeId;

    private Long obraId;

    private Long servicoId;

    private Long tabelaId;

    private Long tipoId;

    private Long tipoocorrenciaId;

    private Set<FotoDTO> fotos = new HashSet<>();

    private Set<HistoricoDTO> historicos = new HashSet<>();

    private Set<RegistroDTO> registros = new HashSet<>();

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

    public String getCaracterizacao() {
        return caracterizacao;
    }

    public void setCaracterizacao(String caracterizacao) {
        this.caracterizacao = caracterizacao;
    }

    public String getCoordenadaa() {
        return coordenadaa;
    }

    public void setCoordenadaa(String coordenadaa) {
        this.coordenadaa = coordenadaa;
    }

    public String getCoordenadae() {
        return coordenadae;
    }

    public void setCoordenadae(String coordenadae) {
        this.coordenadae = coordenadae;
    }

    public String getCoordenadan() {
        return coordenadan;
    }

    public void setCoordenadan(String coordenadan) {
        this.coordenadan = coordenadan;
    }

    public ZonedDateTime getData() {
        return data;
    }

    public void setData(ZonedDateTime data) {
        this.data = data;
    }

    public Float getDistanciaeixo() {
        return distanciaeixo;
    }

    public void setDistanciaeixo(Float distanciaeixo) {
        this.distanciaeixo = distanciaeixo;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
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

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Long getAtividadeId() {
        return atividadeId;
    }

    public void setAtividadeId(Long atividadeId) {
        this.atividadeId = atividadeId;
    }

    public Long getObraId() {
        return obraId;
    }

    public void setObraId(Long obraId) {
        this.obraId = obraId;
    }

    public Long getServicoId() {
        return servicoId;
    }

    public void setServicoId(Long tabelaId) {
        this.servicoId = tabelaId;
    }

    public Long getTabelaId() {
        return tabelaId;
    }

    public void setTabelaId(Long tabelaId) {
        this.tabelaId = tabelaId;
    }

    public Long getTipoId() {
        return tipoId;
    }

    public void setTipoId(Long tipoocorrenciaId) {
        this.tipoId = tipoocorrenciaId;
    }

    public Long getTipoocorrenciaId() {
        return tipoocorrenciaId;
    }

    public void setTipoocorrenciaId(Long tipoocorrenciaId) {
        this.tipoocorrenciaId = tipoocorrenciaId;
    }

    public Set<FotoDTO> getFotos() {
        return fotos;
    }

    public void setFotos(Set<FotoDTO> fotos) {
        this.fotos = fotos;
    }

    public Set<HistoricoDTO> getHistoricos() {
        return historicos;
    }

    public void setHistoricos(Set<HistoricoDTO> historicos) {
        this.historicos = historicos;
    }

    public Set<RegistroDTO> getRegistros() {
        return registros;
    }

    public void setRegistros(Set<RegistroDTO> registros) {
        this.registros = registros;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OcorrenciaDTO ocorrenciaDTO = (OcorrenciaDTO) o;
        if(ocorrenciaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ocorrenciaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OcorrenciaDTO{" +
            "id=" + getId() +
            ", album='" + getAlbum() + "'" +
            ", caracterizacao='" + getCaracterizacao() + "'" +
            ", coordenadaa='" + getCoordenadaa() + "'" +
            ", coordenadae='" + getCoordenadae() + "'" +
            ", coordenadan='" + getCoordenadan() + "'" +
            ", data='" + getData() + "'" +
            ", distanciaeixo='" + getDistanciaeixo() + "'" +
            ", feature='" + getFeature() + "'" +
            ", kmfim='" + getKmfim() + "'" +
            ", kminicio='" + getKminicio() + "'" +
            ", lado='" + getLado() + "'" +
            ", numero='" + getNumero() + "'" +
            "}";
    }
}
