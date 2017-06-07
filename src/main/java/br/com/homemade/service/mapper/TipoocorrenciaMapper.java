package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.TipoocorrenciaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Tipoocorrencia and its DTO TipoocorrenciaDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TipoocorrenciaMapper extends EntityMapper <TipoocorrenciaDTO, Tipoocorrencia> {
    
    
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Tipoocorrencia fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tipoocorrencia tipoocorrencia = new Tipoocorrencia();
        tipoocorrencia.setId(id);
        return tipoocorrencia;
    }
}
