package br.com.homemade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Administrativo.
 */
@Entity
@Table(name = "administrativo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Administrativo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "assunto")
    private String assunto;

    @Column(name = "jhi_local")
    private String local;

    @Column(name = "album")
    private String album;

    @Column(name = "folder")
    private String folder;

    @Column(name = "consideracao")
    private String consideracao;

    @Column(name = "data")
    private ZonedDateTime data;

    @OneToOne
    @JoinColumn(unique = true)
    private Tipoadministrativo tipo;

    @ManyToOne
    private Obra obra;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "administrativo_fotos",
               joinColumns = @JoinColumn(name="administrativos_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="fotos_id", referencedColumnName="id"))
    private Set<Foto> fotos = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "administrativo_docs",
               joinColumns = @JoinColumn(name="administrativos_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="docs_id", referencedColumnName="id"))
    private Set<Documento> docs = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "administrativo_participantes",
               joinColumns = @JoinColumn(name="administrativos_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="participantes_id", referencedColumnName="id"))
    private Set<Participante> participantes = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Administrativo descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAssunto() {
        return assunto;
    }

    public Administrativo assunto(String assunto) {
        this.assunto = assunto;
        return this;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getLocal() {
        return local;
    }

    public Administrativo local(String local) {
        this.local = local;
        return this;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getAlbum() {
        return album;
    }

    public Administrativo album(String album) {
        this.album = album;
        return this;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getFolder() {
        return folder;
    }

    public Administrativo folder(String folder) {
        this.folder = folder;
        return this;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getConsideracao() {
        return consideracao;
    }

    public Administrativo consideracao(String consideracao) {
        this.consideracao = consideracao;
        return this;
    }

    public void setConsideracao(String consideracao) {
        this.consideracao = consideracao;
    }

    public ZonedDateTime getData() {
        return data;
    }

    public Administrativo data(ZonedDateTime data) {
        this.data = data;
        return this;
    }

    public void setData(ZonedDateTime data) {
        this.data = data;
    }

    public Tipoadministrativo getTipo() {
        return tipo;
    }

    public Administrativo tipo(Tipoadministrativo tipoadministrativo) {
        this.tipo = tipoadministrativo;
        return this;
    }

    public void setTipo(Tipoadministrativo tipoadministrativo) {
        this.tipo = tipoadministrativo;
    }

    public Obra getObra() {
        return obra;
    }

    public Administrativo obra(Obra obra) {
        this.obra = obra;
        return this;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public Set<Foto> getFotos() {
        return fotos;
    }

    public Administrativo fotos(Set<Foto> fotos) {
        this.fotos = fotos;
        return this;
    }

    public Administrativo addFotos(Foto foto) {
        this.fotos.add(foto);
        return this;
    }

    public Administrativo removeFotos(Foto foto) {
        this.fotos.remove(foto);
        return this;
    }

    public void setFotos(Set<Foto> fotos) {
        this.fotos = fotos;
    }

    public Set<Documento> getDocs() {
        return docs;
    }

    public Administrativo docs(Set<Documento> documentos) {
        this.docs = documentos;
        return this;
    }

    public Administrativo addDocs(Documento documento) {
        this.docs.add(documento);
        return this;
    }

    public Administrativo removeDocs(Documento documento) {
        this.docs.remove(documento);
        return this;
    }

    public void setDocs(Set<Documento> documentos) {
        this.docs = documentos;
    }

    public Set<Participante> getParticipantes() {
        return participantes;
    }

    public Administrativo participantes(Set<Participante> participantes) {
        this.participantes = participantes;
        return this;
    }

    public Administrativo addParticipantes(Participante participante) {
        this.participantes.add(participante);
        return this;
    }

    public Administrativo removeParticipantes(Participante participante) {
        this.participantes.remove(participante);
        return this;
    }

    public void setParticipantes(Set<Participante> participantes) {
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
        Administrativo administrativo = (Administrativo) o;
        if (administrativo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), administrativo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Administrativo{" +
            "id=" + getId() +
            ", descricao='" + getDescricao() + "'" +
            ", assunto='" + getAssunto() + "'" +
            ", local='" + getLocal() + "'" +
            ", album='" + getAlbum() + "'" +
            ", folder='" + getFolder() + "'" +
            ", consideracao='" + getConsideracao() + "'" +
            ", data='" + getData() + "'" +
            "}";
    }
}
