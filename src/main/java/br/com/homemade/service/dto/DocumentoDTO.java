package br.com.homemade.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Documento entity.
 */
public class DocumentoDTO implements Serializable {

    private Long id;

    private ZonedDateTime data;

    private String descricao;

    private String documento;

    private String link;

    private String thumb;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getData() {
        return data;
    }

    public void setData(ZonedDateTime data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DocumentoDTO documentoDTO = (DocumentoDTO) o;
        if(documentoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), documentoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DocumentoDTO{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", documento='" + getDocumento() + "'" +
            ", link='" + getLink() + "'" +
            ", thumb='" + getThumb() + "'" +
            "}";
    }
}
