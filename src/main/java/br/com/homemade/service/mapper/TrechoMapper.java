package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.TrechoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Trecho and its DTO TrechoDTO.
 */
@Mapper(componentModel = "spring", uses = {RodoviaMapper.class, SupreMapper.class, })
public interface TrechoMapper extends EntityMapper <TrechoDTO, Trecho> {
    @Mapping(source = "rodovia.id", target = "rodoviaId")
    @Mapping(source = "supre.id", target = "supreId")
    TrechoDTO toDto(Trecho trecho); 
    @Mapping(source = "rodoviaId", target = "rodovia")
    @Mapping(source = "supreId", target = "supre")
    Trecho toEntity(TrechoDTO trechoDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Trecho fromId(Long id) {
        if (id == null) {
            return null;
        }
        Trecho trecho = new Trecho();
        trecho.setId(id);
        return trecho;
    }
}
