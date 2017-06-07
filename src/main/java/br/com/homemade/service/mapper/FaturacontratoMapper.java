package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.FaturacontratoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Faturacontrato and its DTO FaturacontratoDTO.
 */
@Mapper(componentModel = "spring", uses = {FonteMapper.class, ContratoMapper.class, ReferenciacontratoMapper.class, })
public interface FaturacontratoMapper extends EntityMapper <FaturacontratoDTO, Faturacontrato> {
    @Mapping(source = "fonte.id", target = "fonteId")
    @Mapping(source = "idcontrato.id", target = "idcontratoId")
    @Mapping(source = "idreferenciacontrato.id", target = "idreferenciacontratoId")
    FaturacontratoDTO toDto(Faturacontrato faturacontrato); 
    @Mapping(source = "fonteId", target = "fonte")
    @Mapping(source = "idcontratoId", target = "idcontrato")
    @Mapping(source = "idreferenciacontratoId", target = "idreferenciacontrato")
    Faturacontrato toEntity(FaturacontratoDTO faturacontratoDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Faturacontrato fromId(Long id) {
        if (id == null) {
            return null;
        }
        Faturacontrato faturacontrato = new Faturacontrato();
        faturacontrato.setId(id);
        return faturacontrato;
    }
}
