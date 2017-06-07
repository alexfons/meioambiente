package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.OcorrenciainformeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Ocorrenciainforme and its DTO OcorrenciainformeDTO.
 */
@Mapper(componentModel = "spring", uses = {OcorrenciaMapper.class, })
public interface OcorrenciainformeMapper extends EntityMapper <OcorrenciainformeDTO, Ocorrenciainforme> {
    @Mapping(source = "ocorrencia.id", target = "ocorrenciaId")
    OcorrenciainformeDTO toDto(Ocorrenciainforme ocorrenciainforme); 
    @Mapping(source = "ocorrenciaId", target = "ocorrencia")
    Ocorrenciainforme toEntity(OcorrenciainformeDTO ocorrenciainformeDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Ocorrenciainforme fromId(Long id) {
        if (id == null) {
            return null;
        }
        Ocorrenciainforme ocorrenciainforme = new Ocorrenciainforme();
        ocorrenciainforme.setId(id);
        return ocorrenciainforme;
    }
}
