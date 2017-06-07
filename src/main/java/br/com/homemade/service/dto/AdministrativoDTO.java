package br.com.homemade.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Administrativo entity.
 */
public class AdministrativoDTO implements Serializable {

    private Long id;

    private String album;

    private String assunto;

    private String consideracao;

    private ZonedDateTime data;

    private String descricao;

    private String folder;

    private String local;

    private Long tipoId;

    private Long obraId;

    private Set<FotoDTO> fotos = new HashSet<>();

    private Set<DocumentoDTO> docs = new HashSet<>();

    private Set<ParticipanteDTO> participantes = new HashSet<>();

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

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getConsideracao() {
        return consideracao;
    }

    public void setConsideracao(String consideracao) {
        this.consideracao = consideracao;
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

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Long getTipoId() {
        return tipoId;
    }

    public void setTipoId(Long tipoadministrativoId) {
        this.tipoId = tipoadministrativoId;
    }

    public Long getObraId() {
        return obraId;
    }

    public void setObraId(Long obraId) {
        this.obraId = obraId;
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

    public Set<ParticipanteDTO> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(Set<ParticipanteDTO> participantes) {
        this.participantes = participantes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AdministrativoDTO administrativoDTO = (AdministrativoDTO) o;
        if(administrativoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), administrativoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdministrativoDTO{" +
            "id=" + getId() +
            ", album='" + getAlbum() + "'" +
            ", assunto='" + getAssunto() + "'" +
            ", consideracao='" + getConsideracao() + "'" +
            ", data='" + getData() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", folder='" + getFolder() + "'" +
            ", local='" + getLocal() + "'" +
            "}";
    }
}
