package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.EmpresacontratoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Empresacontrato and its DTO EmpresacontratoDTO.
 */
@Mapper(componentModel = "spring", uses = {EmpresaMapper.class, })
public interface EmpresacontratoMapper extends EntityMapper <EmpresacontratoDTO, Empresacontrato> {
    @Mapping(source = "empresa.id", target = "empresaId")
    EmpresacontratoDTO toDto(Empresacontrato empresacontrato); 
    @Mapping(source = "empresaId", target = "empresa")
    Empresacontrato toEntity(EmpresacontratoDTO empresacontratoDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Empresacontrato fromId(Long id) {
        if (id == null) {
            return null;
        }
        Empresacontrato empresacontrato = new Empresacontrato();
        empresacontrato.setId(id);
        return empresacontrato;
    }
}
