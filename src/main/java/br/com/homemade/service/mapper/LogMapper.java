package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.LogDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Log and its DTO LogDTO.
 */
@Mapper(componentModel = "spring", uses = {UsuarioMapper.class, })
public interface LogMapper extends EntityMapper <LogDTO, Log> {
    @Mapping(source = "usuario.id", target = "usuarioId")
    LogDTO toDto(Log log); 
    @Mapping(source = "usuarioId", target = "usuario")
    Log toEntity(LogDTO logDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Log fromId(Long id) {
        if (id == null) {
            return null;
        }
        Log log = new Log();
        log.setId(id);
        return log;
    }
}
