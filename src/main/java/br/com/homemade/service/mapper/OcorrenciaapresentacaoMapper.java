package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.OcorrenciaapresentacaoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Ocorrenciaapresentacao and its DTO OcorrenciaapresentacaoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OcorrenciaapresentacaoMapper extends EntityMapper <OcorrenciaapresentacaoDTO, Ocorrenciaapresentacao> {
    
    
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Ocorrenciaapresentacao fromId(Long id) {
        if (id == null) {
            return null;
        }
        Ocorrenciaapresentacao ocorrenciaapresentacao = new Ocorrenciaapresentacao();
        ocorrenciaapresentacao.setId(id);
        return ocorrenciaapresentacao;
    }
}
