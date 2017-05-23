package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.ApresentacaoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Apresentacao and its DTO ApresentacaoDTO.
 */
@Mapper(componentModel = "spring", uses = {ObraMapper.class, OcorrenciaapresentacaoMapper.class, FotoMapper.class, })
public interface ApresentacaoMapper extends EntityMapper <ApresentacaoDTO, Apresentacao> {
    @Mapping(source = "obra.id", target = "obraId")
    ApresentacaoDTO toDto(Apresentacao apresentacao); 
    @Mapping(source = "obraId", target = "obra")
    Apresentacao toEntity(ApresentacaoDTO apresentacaoDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Apresentacao fromId(Long id) {
        if (id == null) {
            return null;
        }
        Apresentacao apresentacao = new Apresentacao();
        apresentacao.setId(id);
        return apresentacao;
    }
}
