package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.AtividadeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Atividade and its DTO AtividadeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AtividadeMapper extends EntityMapper <AtividadeDTO, Atividade> {
    
    
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Atividade fromId(Long id) {
        if (id == null) {
            return null;
        }
        Atividade atividade = new Atividade();
        atividade.setId(id);
        return atividade;
    }
}
