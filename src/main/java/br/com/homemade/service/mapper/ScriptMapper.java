package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.ScriptDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Script and its DTO ScriptDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ScriptMapper extends EntityMapper <ScriptDTO, Script> {
    
    
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Script fromId(Long id) {
        if (id == null) {
            return null;
        }
        Script script = new Script();
        script.setId(id);
        return script;
    }
}
