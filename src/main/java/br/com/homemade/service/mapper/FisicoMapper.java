package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.FisicoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Fisico and its DTO FisicoDTO.
 */
@Mapper(componentModel = "spring", uses = {ObraMapper.class, ObraatividadeMapper.class, })
public interface FisicoMapper extends EntityMapper <FisicoDTO, Fisico> {
    @Mapping(source = "obra.id", target = "obraId")
    FisicoDTO toDto(Fisico fisico); 
    @Mapping(source = "obraId", target = "obra")
    Fisico toEntity(FisicoDTO fisicoDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Fisico fromId(Long id) {
        if (id == null) {
            return null;
        }
        Fisico fisico = new Fisico();
        fisico.setId(id);
        return fisico;
    }
}
