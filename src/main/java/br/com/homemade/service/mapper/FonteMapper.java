package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.FonteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Fonte and its DTO FonteDTO.
 */
@Mapper(componentModel = "spring", uses = {ContabancariaMapper.class, })
public interface FonteMapper extends EntityMapper <FonteDTO, Fonte> {
    @Mapping(source = "idcontabancaria.id", target = "idcontabancariaId")
    FonteDTO toDto(Fonte fonte); 
    @Mapping(source = "idcontabancariaId", target = "idcontabancaria")
    Fonte toEntity(FonteDTO fonteDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Fonte fromId(Long id) {
        if (id == null) {
            return null;
        }
        Fonte fonte = new Fonte();
        fonte.setId(id);
        return fonte;
    }
}
