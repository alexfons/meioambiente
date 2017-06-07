package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.OcorrenciaCertifIrregDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity OcorrenciaCertifIrreg and its DTO OcorrenciaCertifIrregDTO.
 */
@Mapper(componentModel = "spring", uses = {OcorrenciaMapper.class, })
public interface OcorrenciaCertifIrregMapper extends EntityMapper <OcorrenciaCertifIrregDTO, OcorrenciaCertifIrreg> {
    @Mapping(source = "ocorrencia.id", target = "ocorrenciaId")
    OcorrenciaCertifIrregDTO toDto(OcorrenciaCertifIrreg ocorrenciaCertifIrreg); 
    @Mapping(source = "ocorrenciaId", target = "ocorrencia")
    OcorrenciaCertifIrreg toEntity(OcorrenciaCertifIrregDTO ocorrenciaCertifIrregDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default OcorrenciaCertifIrreg fromId(Long id) {
        if (id == null) {
            return null;
        }
        OcorrenciaCertifIrreg ocorrenciaCertifIrreg = new OcorrenciaCertifIrreg();
        ocorrenciaCertifIrreg.setId(id);
        return ocorrenciaCertifIrreg;
    }
}
