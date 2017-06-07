package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.ResidenteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Residente and its DTO ResidenteDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ResidenteMapper extends EntityMapper <ResidenteDTO, Residente> {
    
    
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Residente fromId(Long id) {
        if (id == null) {
            return null;
        }
        Residente residente = new Residente();
        residente.setId(id);
        return residente;
    }
}
