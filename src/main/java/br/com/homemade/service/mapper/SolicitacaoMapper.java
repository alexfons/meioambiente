package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.SolicitacaoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Solicitacao and its DTO SolicitacaoDTO.
 */
@Mapper(componentModel = "spring", uses = {BancoMapper.class, ContratoagenteMapper.class, })
public interface SolicitacaoMapper extends EntityMapper <SolicitacaoDTO, Solicitacao> {
    @Mapping(source = "idbanco.id", target = "idbancoId")
    @Mapping(source = "idContratoagente.id", target = "idContratoagenteId")
    SolicitacaoDTO toDto(Solicitacao solicitacao); 
    @Mapping(source = "idbancoId", target = "idbanco")
    @Mapping(source = "idContratoagenteId", target = "idContratoagente")
    Solicitacao toEntity(SolicitacaoDTO solicitacaoDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Solicitacao fromId(Long id) {
        if (id == null) {
            return null;
        }
        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setId(id);
        return solicitacao;
    }
}
