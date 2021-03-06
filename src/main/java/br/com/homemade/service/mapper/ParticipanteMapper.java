package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.ParticipanteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Participante and its DTO ParticipanteDTO.
 */
@Mapper(componentModel = "spring", uses = {EmpresaMapper.class, })
public interface ParticipanteMapper extends EntityMapper <ParticipanteDTO, Participante> {
    @Mapping(source = "empresa.id", target = "empresaId")
    ParticipanteDTO toDto(Participante participante); 
    @Mapping(source = "empresaId", target = "empresa")
    Participante toEntity(ParticipanteDTO participanteDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Participante fromId(Long id) {
        if (id == null) {
            return null;
        }
        Participante participante = new Participante();
        participante.setId(id);
        return participante;
    }
}
