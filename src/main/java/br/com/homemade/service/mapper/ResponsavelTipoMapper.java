package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.ResponsavelTipoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ResponsavelTipo and its DTO ResponsavelTipoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ResponsavelTipoMapper extends EntityMapper <ResponsavelTipoDTO, ResponsavelTipo> {
    
    
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default ResponsavelTipo fromId(Long id) {
        if (id == null) {
            return null;
        }
        ResponsavelTipo responsavelTipo = new ResponsavelTipo();
        responsavelTipo.setId(id);
        return responsavelTipo;
    }
}
