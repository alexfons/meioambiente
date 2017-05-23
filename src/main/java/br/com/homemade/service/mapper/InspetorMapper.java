package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.InspetorDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Inspetor and its DTO InspetorDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InspetorMapper extends EntityMapper <InspetorDTO, Inspetor> {
    
    
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Inspetor fromId(Long id) {
        if (id == null) {
            return null;
        }
        Inspetor inspetor = new Inspetor();
        inspetor.setId(id);
        return inspetor;
    }
}
