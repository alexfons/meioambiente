package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.TipoadministrativoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Tipoadministrativo and its DTO TipoadministrativoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TipoadministrativoMapper extends EntityMapper <TipoadministrativoDTO, Tipoadministrativo> {
    
    
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Tipoadministrativo fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tipoadministrativo tipoadministrativo = new Tipoadministrativo();
        tipoadministrativo.setId(id);
        return tipoadministrativo;
    }
}
