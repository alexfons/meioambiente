package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.OcorrencianotificacaoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Ocorrencianotificacao and its DTO OcorrencianotificacaoDTO.
 */
@Mapper(componentModel = "spring", uses = {OcorrenciaMapper.class, })
public interface OcorrencianotificacaoMapper extends EntityMapper <OcorrencianotificacaoDTO, Ocorrencianotificacao> {
    @Mapping(source = "ocorrencia.id", target = "ocorrenciaId")
    OcorrencianotificacaoDTO toDto(Ocorrencianotificacao ocorrencianotificacao); 
    @Mapping(source = "ocorrenciaId", target = "ocorrencia")
    Ocorrencianotificacao toEntity(OcorrencianotificacaoDTO ocorrencianotificacaoDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Ocorrencianotificacao fromId(Long id) {
        if (id == null) {
            return null;
        }
        Ocorrencianotificacao ocorrencianotificacao = new Ocorrencianotificacao();
        ocorrencianotificacao.setId(id);
        return ocorrencianotificacao;
    }
}
