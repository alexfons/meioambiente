package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.PagfuncionarioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Pagfuncionario and its DTO PagfuncionarioDTO.
 */
@Mapper(componentModel = "spring", uses = {PlanocontasMapper.class, FonteMapper.class, CategoriainversaoMapper.class, FuncionarioMapper.class, ReferenciaMapper.class, })
public interface PagfuncionarioMapper extends EntityMapper <PagfuncionarioDTO, Pagfuncionario> {
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
    @Mapping(source = "idfuncionarios.id", target = "idfuncionariosId")
    @Mapping(source = "idreferencia.id", target = "idreferenciaId")
    PagfuncionarioDTO toDto(Pagfuncionario pagfuncionario); 
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
    @Mapping(source = "idfuncionariosId", target = "idfuncionarios")
    @Mapping(source = "idreferenciaId", target = "idreferencia")
    Pagfuncionario toEntity(PagfuncionarioDTO pagfuncionarioDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Pagfuncionario fromId(Long id) {
        if (id == null) {
            return null;
        }
        Pagfuncionario pagfuncionario = new Pagfuncionario();
        pagfuncionario.setId(id);
        return pagfuncionario;
    }
}
