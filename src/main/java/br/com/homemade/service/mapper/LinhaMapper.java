package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.LinhaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Linha and its DTO LinhaDTO.
 */
@Mapper(componentModel = "spring", uses = {ColunaMapper.class, })
public interface LinhaMapper extends EntityMapper <LinhaDTO, Linha> {
    @Mapping(source = "coluna.id", target = "colunaId")
    LinhaDTO toDto(Linha linha); 
    @Mapping(source = "colunaId", target = "coluna")
    Linha toEntity(LinhaDTO linhaDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Linha fromId(Long id) {
        if (id == null) {
            return null;
        }
        Linha linha = new Linha();
        linha.setId(id);
        return linha;
    }
}
