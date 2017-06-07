package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.TipocontratoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Tipocontrato and its DTO TipocontratoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TipocontratoMapper extends EntityMapper <TipocontratoDTO, Tipocontrato> {
    
    
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Tipocontrato fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tipocontrato tipocontrato = new Tipocontrato();
        tipocontrato.setId(id);
        return tipocontrato;
    }
}
