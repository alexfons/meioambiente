package br.com.homemade.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Projeto entity.
 */
public class ProjetoDTO implements Serializable {

    private Long id;

    private String andamento;

    private String pendente;

    private Long inspetorId;

    private Long municipioId;

    private Long tipoId;

    private Long tipoobraId;

    private Long trechoId;

    private Long fiscalId;

    private Set<ContratoprojetoDTO> contratosprojetos = new HashSet<>();

    private Set<HistoricoDTO> historicos = new HashSet<>();

    private Set<MunicipioDTO> municipios = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAndamento() {
        return andamento;
    }

    public void setAndamento(String andamento) {
        this.andamento = andamento;
    }

    public String getPendente() {
        return pendente;
    }

    public void setPendente(String pendente) {
        this.pendente = pendente;
    }

    public Long getInspetorId() {
        return inspetorId;
    }

    public void setInspetorId(Long inspetorId) {
        this.inspetorId = inspetorId;
    }

    public Long getMunicipioId() {
        return municipioId;
    }

    public void setMunicipioId(Long municipioId) {
        this.municipioId = municipioId;
    }

    public Long getTipoId() {
        return tipoId;
    }

    public void setTipoId(Long tipoobraId) {
        this.tipoId = tipoobraId;
    }

    public Long getTipoobraId() {
        return tipoobraId;
    }

    public void setTipoobraId(Long tipoobraId) {
        this.tipoobraId = tipoobraId;
    }

    public Long getTrechoId() {
        return trechoId;
    }

    public void setTrechoId(Long trechoId) {
        this.trechoId = trechoId;
    }

    public Long getFiscalId() {
        return fiscalId;
    }

    public void setFiscalId(Long fiscalId) {
        this.fiscalId = fiscalId;
    }

    public Set<ContratoprojetoDTO> getContratosprojetos() {
        return contratosprojetos;
    }

    public void setContratosprojetos(Set<ContratoprojetoDTO> contratoprojetos) {
        this.contratosprojetos = contratoprojetos;
    }

    public Set<HistoricoDTO> getHistoricos() {
        return historicos;
    }

    public void setHistoricos(Set<HistoricoDTO> historicos) {
        this.historicos = historicos;
    }

    public Set<MunicipioDTO> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(Set<MunicipioDTO> municipios) {
        this.municipios = municipios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProjetoDTO projetoDTO = (ProjetoDTO) o;
        if(projetoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), projetoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProjetoDTO{" +
            "id=" + getId() +
            ", andamento='" + getAndamento() + "'" +
            ", pendente='" + getPendente() + "'" +
            "}";
    }
}
