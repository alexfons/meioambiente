package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.ReferenciacontratoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Referenciacontrato and its DTO ReferenciacontratoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ReferenciacontratoMapper extends EntityMapper <ReferenciacontratoDTO, Referenciacontrato> {
    
    
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Referenciacontrato fromId(Long id) {
        if (id == null) {
            return null;
        }
        Referenciacontrato referenciacontrato = new Referenciacontrato();
        referenciacontrato.setId(id);
        return referenciacontrato;
    }
}
