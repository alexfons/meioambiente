package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.OcorrenciapendenciasDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Ocorrenciapendencias and its DTO OcorrenciapendenciasDTO.
 */
@Mapper(componentModel = "spring", uses = {OcorrenciaMapper.class, })
public interface OcorrenciapendenciasMapper extends EntityMapper <OcorrenciapendenciasDTO, Ocorrenciapendencias> {
    @Mapping(source = "ocorrencia.id", target = "ocorrenciaId")
    OcorrenciapendenciasDTO toDto(Ocorrenciapendencias ocorrenciapendencias); 
    @Mapping(source = "ocorrenciaId", target = "ocorrencia")
    Ocorrenciapendencias toEntity(OcorrenciapendenciasDTO ocorrenciapendenciasDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Ocorrenciapendencias fromId(Long id) {
        if (id == null) {
            return null;
        }
        Ocorrenciapendencias ocorrenciapendencias = new Ocorrenciapendencias();
        ocorrenciapendencias.setId(id);
        return ocorrenciapendencias;
    }
}
