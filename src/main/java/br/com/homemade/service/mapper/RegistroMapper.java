package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.RegistroDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Registro and its DTO RegistroDTO.
 */
@Mapper(componentModel = "spring", uses = {LinhaMapper.class, })
public interface RegistroMapper extends EntityMapper <RegistroDTO, Registro> {
    @Mapping(source = "opcoes.id", target = "opcoesId")
    RegistroDTO toDto(Registro registro); 
    @Mapping(source = "opcoesId", target = "opcoes")
    Registro toEntity(RegistroDTO registroDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Registro fromId(Long id) {
        if (id == null) {
            return null;
        }
        Registro registro = new Registro();
        registro.setId(id);
        return registro;
    }
}
