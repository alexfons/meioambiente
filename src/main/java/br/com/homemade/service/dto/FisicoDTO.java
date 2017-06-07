package br.com.homemade.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Fisico entity.
 */
public class FisicoDTO implements Serializable {

    private Long id;

    private Double extensao;

    private String tipo;

    private String tituloFim;

    private String tituloInicio;

    private Long obraId;

    private Set<ObraatividadeDTO> obraatividades = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getExtensao() {
        return extensao;
    }

    public void setExtensao(Double extensao) {
        this.extensao = extensao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTituloFim() {
        return tituloFim;
    }

    public void setTituloFim(String tituloFim) {
        this.tituloFim = tituloFim;
    }

    public String getTituloInicio() {
        return tituloInicio;
    }

    public void setTituloInicio(String tituloInicio) {
        this.tituloInicio = tituloInicio;
    }

    public Long getObraId() {
        return obraId;
    }

    public void setObraId(Long obraId) {
        this.obraId = obraId;
    }

    public Set<ObraatividadeDTO> getObraatividades() {
        return obraatividades;
    }

    public void setObraatividades(Set<ObraatividadeDTO> obraatividades) {
        this.obraatividades = obraatividades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FisicoDTO fisicoDTO = (FisicoDTO) o;
        if(fisicoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fisicoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FisicoDTO{" +
            "id=" + getId() +
            ", extensao='" + getExtensao() + "'" +
            ", tipo='" + getTipo() + "'" +
            ", tituloFim='" + getTituloFim() + "'" +
            ", tituloInicio='" + getTituloInicio() + "'" +
            "}";
    }
}
