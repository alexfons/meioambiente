package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.TipocertifConforDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TipocertifConfor and its DTO TipocertifConforDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TipocertifConforMapper extends EntityMapper <TipocertifConforDTO, TipocertifConfor> {
    
    
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default TipocertifConfor fromId(Long id) {
        if (id == null) {
            return null;
        }
        TipocertifConfor tipocertifConfor = new TipocertifConfor();
        tipocertifConfor.setId(id);
        return tipocertifConfor;
    }
}
