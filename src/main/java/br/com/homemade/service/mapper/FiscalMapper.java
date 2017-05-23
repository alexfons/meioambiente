package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.FiscalDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Fiscal and its DTO FiscalDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FiscalMapper extends EntityMapper <FiscalDTO, Fiscal> {
    
    
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Fiscal fromId(Long id) {
        if (id == null) {
            return null;
        }
        Fiscal fiscal = new Fiscal();
        fiscal.setId(id);
        return fiscal;
    }
}
