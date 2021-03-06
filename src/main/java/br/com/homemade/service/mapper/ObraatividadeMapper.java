package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.ObraatividadeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Obraatividade and its DTO ObraatividadeDTO.
 */
@Mapper(componentModel = "spring", uses = {AtividadeMapper.class, })
public interface ObraatividadeMapper extends EntityMapper <ObraatividadeDTO, Obraatividade> {
    @Mapping(source = "atividade.id", target = "atividadeId")
    ObraatividadeDTO toDto(Obraatividade obraatividade); 
    @Mapping(source = "atividadeId", target = "atividade")
    Obraatividade toEntity(ObraatividadeDTO obraatividadeDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Obraatividade fromId(Long id) {
        if (id == null) {
            return null;
        }
        Obraatividade obraatividade = new Obraatividade();
        obraatividade.setId(id);
        return obraatividade;
    }
}
