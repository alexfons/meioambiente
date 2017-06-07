package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.CoordenadaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Coordenada and its DTO CoordenadaDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CoordenadaMapper extends EntityMapper <CoordenadaDTO, Coordenada> {
    
    
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Coordenada fromId(Long id) {
        if (id == null) {
            return null;
        }
        Coordenada coordenada = new Coordenada();
        coordenada.setId(id);
        return coordenada;
    }
}
