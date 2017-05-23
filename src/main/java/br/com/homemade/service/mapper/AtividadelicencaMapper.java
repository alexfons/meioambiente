package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.AtividadelicencaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Atividadelicenca and its DTO AtividadelicencaDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AtividadelicencaMapper extends EntityMapper <AtividadelicencaDTO, Atividadelicenca> {
    
    
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Atividadelicenca fromId(Long id) {
        if (id == null) {
            return null;
        }
        Atividadelicenca atividadelicenca = new Atividadelicenca();
        atividadelicenca.setId(id);
        return atividadelicenca;
    }
}
