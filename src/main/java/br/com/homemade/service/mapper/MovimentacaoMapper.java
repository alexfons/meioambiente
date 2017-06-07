package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.MovimentacaoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Movimentacao and its DTO MovimentacaoDTO.
 */
@Mapper(componentModel = "spring", uses = {ContabancariaMapper.class, FonteMapper.class, })
public interface MovimentacaoMapper extends EntityMapper <MovimentacaoDTO, Movimentacao> {
    @Mapping(source = "contabancaria.id", target = "contabancariaId")
    @Mapping(source = "fonte.id", target = "fonteId")
    MovimentacaoDTO toDto(Movimentacao movimentacao); 
    @Mapping(target = "listamovimentacaos", ignore = true)
    @Mapping(source = "contabancariaId", target = "contabancaria")
    @Mapping(source = "fonteId", target = "fonte")
    Movimentacao toEntity(MovimentacaoDTO movimentacaoDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Movimentacao fromId(Long id) {
        if (id == null) {
            return null;
        }
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setId(id);
        return movimentacao;
    }
}
