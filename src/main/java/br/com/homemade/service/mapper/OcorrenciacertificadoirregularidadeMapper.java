package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.OcorrenciacertificadoirregularidadeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Ocorrenciacertificadoirregularidade and its DTO OcorrenciacertificadoirregularidadeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OcorrenciacertificadoirregularidadeMapper extends EntityMapper <OcorrenciacertificadoirregularidadeDTO, Ocorrenciacertificadoirregularidade> {
    
    
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Ocorrenciacertificadoirregularidade fromId(Long id) {
        if (id == null) {
            return null;
        }
        Ocorrenciacertificadoirregularidade ocorrenciacertificadoirregularidade = new Ocorrenciacertificadoirregularidade();
        ocorrenciacertificadoirregularidade.setId(id);
        return ocorrenciacertificadoirregularidade;
    }
}
