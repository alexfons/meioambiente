package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.ContratoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Contrato and its DTO ContratoDTO.
 */
@Mapper(componentModel = "spring", uses = {EmpresaMapper.class, PlanocontasMapper.class, NaturezaMapper.class, PropostaMapper.class, CategoriainversaoMapper.class, AditivocontratoMapper.class, ContratoobraMapper.class, EmpresacontratoMapper.class, })
public interface ContratoMapper extends EntityMapper <ContratoDTO, Contrato> {
    @Mapping(source = "idempresa.id", target = "idempresaId")
    @Mapping(source = "contacontabilC2.id", target = "contacontabilC2Id")
    @Mapping(source = "contacontabilD1.id", target = "contacontabilD1Id")
    @Mapping(source = "contacontabilD2.id", target = "contacontabilD2Id")
    @Mapping(source = "divcontacontabilC1.id", target = "divcontacontabilC1Id")
    @Mapping(source = "divcontacontabilD1.id", target = "divcontacontabilD1Id")
    @Mapping(source = "juscontacontabilC1.id", target = "juscontacontabilC1Id")
    @Mapping(source = "juscontacontabilD1.id", target = "juscontacontabilD1Id")
    @Mapping(source = "idnatureza.id", target = "idnaturezaId")
    @Mapping(source = "proposta.id", target = "propostaId")
    @Mapping(source = "idcategoriainversao.id", target = "idcategoriainversaoId")
    ContratoDTO toDto(Contrato contrato); 
    @Mapping(source = "idempresaId", target = "idempresa")
    @Mapping(source = "contacontabilC2Id", target = "contacontabilC2")
    @Mapping(source = "contacontabilD1Id", target = "contacontabilD1")
    @Mapping(source = "contacontabilD2Id", target = "contacontabilD2")
    @Mapping(source = "divcontacontabilC1Id", target = "divcontacontabilC1")
    @Mapping(source = "divcontacontabilD1Id", target = "divcontacontabilD1")
    @Mapping(source = "juscontacontabilC1Id", target = "juscontacontabilC1")
    @Mapping(source = "juscontacontabilD1Id", target = "juscontacontabilD1")
    @Mapping(source = "idnaturezaId", target = "idnatureza")
    @Mapping(source = "propostaId", target = "proposta")
    @Mapping(source = "idcategoriainversaoId", target = "idcategoriainversao")
    Contrato toEntity(ContratoDTO contratoDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Contrato fromId(Long id) {
        if (id == null) {
            return null;
        }
        Contrato contrato = new Contrato();
        contrato.setId(id);
        return contrato;
    }
}
