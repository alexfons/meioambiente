package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.TipolicencaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Tipolicenca and its DTO TipolicencaDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TipolicencaMapper extends EntityMapper <TipolicencaDTO, Tipolicenca> {
    
    
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Tipolicenca fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tipolicenca tipolicenca = new Tipolicenca();
        tipolicenca.setId(id);
        return tipolicenca;
    }
}
