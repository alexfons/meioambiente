package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.PendenciasDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Pendencias and its DTO PendenciasDTO.
 */
@Mapper(componentModel = "spring", uses = {ObraMapper.class, FotoMapper.class, OcorrenciapendenciasMapper.class, })
public interface PendenciasMapper extends EntityMapper <PendenciasDTO, Pendencias> {
    @Mapping(source = "obra.id", target = "obraId")
    PendenciasDTO toDto(Pendencias pendencias); 
    @Mapping(source = "obraId", target = "obra")
    Pendencias toEntity(PendenciasDTO pendenciasDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Pendencias fromId(Long id) {
        if (id == null) {
            return null;
        }
        Pendencias pendencias = new Pendencias();
        pendencias.setId(id);
        return pendencias;
    }
}
