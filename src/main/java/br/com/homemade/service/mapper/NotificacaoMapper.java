package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.NotificacaoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Notificacao and its DTO NotificacaoDTO.
 */
@Mapper(componentModel = "spring", uses = {ObraMapper.class, FotoMapper.class, OcorrencianotificacaoMapper.class, })
public interface NotificacaoMapper extends EntityMapper <NotificacaoDTO, Notificacao> {
    @Mapping(source = "obra.id", target = "obraId")
    NotificacaoDTO toDto(Notificacao notificacao); 
    @Mapping(source = "obraId", target = "obra")
    Notificacao toEntity(NotificacaoDTO notificacaoDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Notificacao fromId(Long id) {
        if (id == null) {
            return null;
        }
        Notificacao notificacao = new Notificacao();
        notificacao.setId(id);
        return notificacao;
    }
}
