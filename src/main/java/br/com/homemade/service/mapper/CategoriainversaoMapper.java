package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.CategoriainversaoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Categoriainversao and its DTO CategoriainversaoDTO.
 */
@Mapper(componentModel = "spring", uses = {ContratoagenteMapper.class, })
public interface CategoriainversaoMapper extends EntityMapper <CategoriainversaoDTO, Categoriainversao> {
    @Mapping(source = "idcontratoagente.id", target = "idcontratoagenteId")
    CategoriainversaoDTO toDto(Categoriainversao categoriainversao); 
    @Mapping(source = "idcontratoagenteId", target = "idcontratoagente")
    Categoriainversao toEntity(CategoriainversaoDTO categoriainversaoDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Categoriainversao fromId(Long id) {
        if (id == null) {
            return null;
        }
        Categoriainversao categoriainversao = new Categoriainversao();
        categoriainversao.setId(id);
        return categoriainversao;
    }
}
