package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.HistoricoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Historico and its DTO HistoricoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface HistoricoMapper extends EntityMapper <HistoricoDTO, Historico> {
    
    
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Historico fromId(Long id) {
        if (id == null) {
            return null;
        }
        Historico historico = new Historico();
        historico.setId(id);
        return historico;
    }
}
