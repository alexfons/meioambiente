package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.ContratoagenteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Contratoagente and its DTO ContratoagenteDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ContratoagenteMapper extends EntityMapper <ContratoagenteDTO, Contratoagente> {
    
    
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Contratoagente fromId(Long id) {
        if (id == null) {
            return null;
        }
        Contratoagente contratoagente = new Contratoagente();
        contratoagente.setId(id);
        return contratoagente;
    }
}
