package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.BalancoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Balanco and its DTO BalancoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BalancoMapper extends EntityMapper <BalancoDTO, Balanco> {
    
    
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Balanco fromId(Long id) {
        if (id == null) {
            return null;
        }
        Balanco balanco = new Balanco();
        balanco.setId(id);
        return balanco;
    }
}
