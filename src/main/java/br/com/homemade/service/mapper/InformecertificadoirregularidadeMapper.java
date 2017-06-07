package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.InformecertificadoirregularidadeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Informecertificadoirregularidade and its DTO InformecertificadoirregularidadeDTO.
 */
@Mapper(componentModel = "spring", uses = {InformeMapper.class, })
public interface InformecertificadoirregularidadeMapper extends EntityMapper <InformecertificadoirregularidadeDTO, Informecertificadoirregularidade> {
    @Mapping(source = "informe.id", target = "informeId")
    InformecertificadoirregularidadeDTO toDto(Informecertificadoirregularidade informecertificadoirregularidade); 
    @Mapping(source = "informeId", target = "informe")
    Informecertificadoirregularidade toEntity(InformecertificadoirregularidadeDTO informecertificadoirregularidadeDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Informecertificadoirregularidade fromId(Long id) {
        if (id == null) {
            return null;
        }
        Informecertificadoirregularidade informecertificadoirregularidade = new Informecertificadoirregularidade();
        informecertificadoirregularidade.setId(id);
        return informecertificadoirregularidade;
    }
}
