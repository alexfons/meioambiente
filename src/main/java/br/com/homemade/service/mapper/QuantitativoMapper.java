package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.QuantitativoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Quantitativo and its DTO QuantitativoDTO.
 */
@Mapper(componentModel = "spring", uses = {MedicaoMapper.class, })
public interface QuantitativoMapper extends EntityMapper <QuantitativoDTO, Quantitativo> {
    @Mapping(source = "nmedicao.id", target = "nmedicaoId")
    QuantitativoDTO toDto(Quantitativo quantitativo); 
    @Mapping(source = "nmedicaoId", target = "nmedicao")
    Quantitativo toEntity(QuantitativoDTO quantitativoDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Quantitativo fromId(Long id) {
        if (id == null) {
            return null;
        }
        Quantitativo quantitativo = new Quantitativo();
        quantitativo.setId(id);
        return quantitativo;
    }
}
