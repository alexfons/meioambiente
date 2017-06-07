package br.com.homemade.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Coordenada entity.
 */
public class CoordenadaDTO implements Serializable {

    private Long id;

    private Float a;

    private Float km;

    private Float n;

    private Float s;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getA() {
        return a;
    }

    public void setA(Float a) {
        this.a = a;
    }

    public Float getKm() {
        return km;
    }

    public void setKm(Float km) {
        this.km = km;
    }

    public Float getN() {
        return n;
    }

    public void setN(Float n) {
        this.n = n;
    }

    public Float getS() {
        return s;
    }

    public void setS(Float s) {
        this.s = s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CoordenadaDTO coordenadaDTO = (CoordenadaDTO) o;
        if(coordenadaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), coordenadaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CoordenadaDTO{" +
            "id=" + getId() +
            ", a='" + getA() + "'" +
            ", km='" + getKm() + "'" +
            ", n='" + getN() + "'" +
            ", s='" + getS() + "'" +
            "}";
    }
}
