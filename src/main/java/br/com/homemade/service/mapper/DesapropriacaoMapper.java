package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.DesapropriacaoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Desapropriacao and its DTO DesapropriacaoDTO.
 */
@Mapper(componentModel = "spring", uses = {PlanocontasMapper.class, FonteMapper.class, CategoriainversaoMapper.class, ContabancariaMapper.class, ReferenciaMapper.class, RodoviaMapper.class, })
public interface DesapropriacaoMapper extends EntityMapper <DesapropriacaoDTO, Desapropriacao> {
    @Mapping(source = "contacontabilC1.id", target = "contacontabilC1Id")
    @Mapping(source = "contacontabilC2.id", target = "contacontabilC2Id")
    @Mapping(source = "contacontabilD1.id", target = "contacontabilD1Id")
    @Mapping(source = "contacontabilD2.id", target = "contacontabilD2Id")
    @Mapping(source = "divcontacontabilC1.id", target = "divcontacontabilC1Id")
    @Mapping(source = "divcontacontabilD1.id", target = "divcontacontabilD1Id")
    @Mapping(source = "juscontacontabilC1.id", target = "juscontacontabilC1Id")
    @Mapping(source = "juscontacontabilD1.id", target = "juscontacontabilD1Id")
    @Mapping(source = "fonte.id", target = "fonteId")
    @Mapping(source = "idcategoriainversao.id", target = "idcategoriainversaoId")
    @Mapping(source = "idcontabancaria.id", target = "idcontabancariaId")
    @Mapping(source = "idreferencia.id", target = "idreferenciaId")
    @Mapping(source = "rodovia.id", target = "rodoviaId")
    DesapropriacaoDTO toDto(Desapropriacao desapropriacao); 
    @Mapping(source = "contacontabilC1Id", target = "contacontabilC1")
    @Mapping(source = "contacontabilC2Id", target = "contacontabilC2")
    @Mapping(source = "contacontabilD1Id", target = "contacontabilD1")
    @Mapping(source = "contacontabilD2Id", target = "contacontabilD2")
    @Mapping(source = "divcontacontabilC1Id", target = "divcontacontabilC1")
    @Mapping(source = "divcontacontabilD1Id", target = "divcontacontabilD1")
    @Mapping(source = "juscontacontabilC1Id", target = "juscontacontabilC1")
    @Mapping(source = "juscontacontabilD1Id", target = "juscontacontabilD1")
    @Mapping(source = "fonteId", target = "fonte")
    @Mapping(source = "idcategoriainversaoId", target = "idcategoriainversao")
    @Mapping(source = "idcontabancariaId", target = "idcontabancaria")
    @Mapping(source = "idreferenciaId", target = "idreferencia")
    @Mapping(source = "rodoviaId", target = "rodovia")
    Desapropriacao toEntity(DesapropriacaoDTO desapropriacaoDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Desapropriacao fromId(Long id) {
        if (id == null) {
            return null;
        }
        Desapropriacao desapropriacao = new Desapropriacao();
        desapropriacao.setId(id);
        return desapropriacao;
    }
}
