package br.com.homemade.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Tipolicenca entity.
 */
public class TipolicencaDTO implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TipolicencaDTO tipolicencaDTO = (TipolicencaDTO) o;
        if(tipolicencaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tipolicencaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TipolicencaDTO{" +
            "id=" + getId() +
            "}";
    }
}
