package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.InformeCertifIrregDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity InformeCertifIrreg and its DTO InformeCertifIrregDTO.
 */
@Mapper(componentModel = "spring", uses = {InformeMapper.class, })
public interface InformeCertifIrregMapper extends EntityMapper <InformeCertifIrregDTO, InformeCertifIrreg> {
    @Mapping(source = "informe.id", target = "informeId")
    InformeCertifIrregDTO toDto(InformeCertifIrreg informeCertifIrreg); 
    @Mapping(source = "informeId", target = "informe")
    InformeCertifIrreg toEntity(InformeCertifIrregDTO informeCertifIrregDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default InformeCertifIrreg fromId(Long id) {
        if (id == null) {
            return null;
        }
        InformeCertifIrreg informeCertifIrreg = new InformeCertifIrreg();
        informeCertifIrreg.setId(id);
        return informeCertifIrreg;
    }
}
