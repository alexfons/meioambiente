package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.ContratoobraDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Contratoobra and its DTO ContratoobraDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ContratoobraMapper extends EntityMapper <ContratoobraDTO, Contratoobra> {
    
    
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Contratoobra fromId(Long id) {
        if (id == null) {
            return null;
        }
        Contratoobra contratoobra = new Contratoobra();
        contratoobra.setId(id);
        return contratoobra;
    }
}
