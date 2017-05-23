package br.com.homemade.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Orgaoemissor entity.
 */
public class OrgaoemissorDTO implements Serializable {

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

        OrgaoemissorDTO orgaoemissorDTO = (OrgaoemissorDTO) o;
        if(orgaoemissorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orgaoemissorDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrgaoemissorDTO{" +
            "id=" + getId() +
            "}";
    }
}
