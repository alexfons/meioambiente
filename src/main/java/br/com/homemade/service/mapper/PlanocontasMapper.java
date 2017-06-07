package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.PlanocontasDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Planocontas and its DTO PlanocontasDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PlanocontasMapper extends EntityMapper <PlanocontasDTO, Planocontas> {
    
    
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Planocontas fromId(Long id) {
        if (id == null) {
            return null;
        }
        Planocontas planocontas = new Planocontas();
        planocontas.setId(id);
        return planocontas;
    }
}
