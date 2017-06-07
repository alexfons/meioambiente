package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.PlanoaquisicoesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Planoaquisicoes and its DTO PlanoaquisicoesDTO.
 */
@Mapper(componentModel = "spring", uses = {FonteMapper.class, ContratoagenteMapper.class, EditalMapper.class, })
public interface PlanoaquisicoesMapper extends EntityMapper <PlanoaquisicoesDTO, Planoaquisicoes> {
    @Mapping(source = "fonte.id", target = "fonteId")
    @Mapping(source = "idcontratoagente.id", target = "idcontratoagenteId")
    @Mapping(source = "idedital.id", target = "ideditalId")
    PlanoaquisicoesDTO toDto(Planoaquisicoes planoaquisicoes); 
    @Mapping(source = "fonteId", target = "fonte")
    @Mapping(source = "idcontratoagenteId", target = "idcontratoagente")
    @Mapping(source = "ideditalId", target = "idedital")
    Planoaquisicoes toEntity(PlanoaquisicoesDTO planoaquisicoesDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Planoaquisicoes fromId(Long id) {
        if (id == null) {
            return null;
        }
        Planoaquisicoes planoaquisicoes = new Planoaquisicoes();
        planoaquisicoes.setId(id);
        return planoaquisicoes;
    }
}
