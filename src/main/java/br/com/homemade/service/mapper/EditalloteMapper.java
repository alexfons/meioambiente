package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.EditalloteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Editallote and its DTO EditalloteDTO.
 */
@Mapper(componentModel = "spring", uses = {EditalMapper.class, })
public interface EditalloteMapper extends EntityMapper <EditalloteDTO, Editallote> {
    @Mapping(source = "numeroedital.id", target = "numeroeditalId")
    EditalloteDTO toDto(Editallote editallote); 
    @Mapping(source = "numeroeditalId", target = "numeroedital")
    Editallote toEntity(EditalloteDTO editalloteDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Editallote fromId(Long id) {
        if (id == null) {
            return null;
        }
        Editallote editallote = new Editallote();
        editallote.setId(id);
        return editallote;
    }
}
