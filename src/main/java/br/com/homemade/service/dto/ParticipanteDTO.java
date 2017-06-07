package br.com.homemade.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Participante entity.
 */
public class ParticipanteDTO implements Serializable {

    private Long id;

    private String formacao;

    private String nome;

    private Long empresaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ParticipanteDTO participanteDTO = (ParticipanteDTO) o;
        if(participanteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), participanteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ParticipanteDTO{" +
            "id=" + getId() +
            ", formacao='" + getFormacao() + "'" +
            ", nome='" + getNome() + "'" +
            "}";
    }
}
