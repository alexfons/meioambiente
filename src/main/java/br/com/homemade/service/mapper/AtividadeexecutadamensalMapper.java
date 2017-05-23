package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.AtividadeexecutadamensalDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Atividadeexecutadamensal and its DTO AtividadeexecutadamensalDTO.
 */
@Mapper(componentModel = "spring", uses = {AtividadeMapper.class, })
public interface AtividadeexecutadamensalMapper extends EntityMapper <AtividadeexecutadamensalDTO, Atividadeexecutadamensal> {
    @Mapping(source = "atividade.id", target = "atividadeId")
    AtividadeexecutadamensalDTO toDto(Atividadeexecutadamensal atividadeexecutadamensal); 
    @Mapping(source = "atividadeId", target = "atividade")
    Atividadeexecutadamensal toEntity(AtividadeexecutadamensalDTO atividadeexecutadamensalDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Atividadeexecutadamensal fromId(Long id) {
        if (id == null) {
            return null;
        }
        Atividadeexecutadamensal atividadeexecutadamensal = new Atividadeexecutadamensal();
        atividadeexecutadamensal.setId(id);
        return atividadeexecutadamensal;
    }
}
