package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.MunicipioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Municipio and its DTO MunicipioDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MunicipioMapper extends EntityMapper <MunicipioDTO, Municipio> {
    
    
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Municipio fromId(Long id) {
        if (id == null) {
            return null;
        }
        Municipio municipio = new Municipio();
        municipio.setId(id);
        return municipio;
    }
}
