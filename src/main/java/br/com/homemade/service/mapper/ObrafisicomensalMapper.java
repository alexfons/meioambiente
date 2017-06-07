package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.ObrafisicomensalDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Obrafisicomensal and its DTO ObrafisicomensalDTO.
 */
@Mapper(componentModel = "spring", uses = {FisicoMapper.class, AtividadeexecutadamensalMapper.class, })
public interface ObrafisicomensalMapper extends EntityMapper <ObrafisicomensalDTO, Obrafisicomensal> {
    @Mapping(source = "fisico.id", target = "fisicoId")
    ObrafisicomensalDTO toDto(Obrafisicomensal obrafisicomensal); 
    @Mapping(source = "fisicoId", target = "fisico")
    Obrafisicomensal toEntity(ObrafisicomensalDTO obrafisicomensalDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Obrafisicomensal fromId(Long id) {
        if (id == null) {
            return null;
        }
        Obrafisicomensal obrafisicomensal = new Obrafisicomensal();
        obrafisicomensal.setId(id);
        return obrafisicomensal;
    }
}
