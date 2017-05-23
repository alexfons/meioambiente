package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.CondicionanteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Condicionante and its DTO CondicionanteDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CondicionanteMapper extends EntityMapper <CondicionanteDTO, Condicionante> {
    
    
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Condicionante fromId(Long id) {
        if (id == null) {
            return null;
        }
        Condicionante condicionante = new Condicionante();
        condicionante.setId(id);
        return condicionante;
    }
}
