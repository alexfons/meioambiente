package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.LancamentosDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Lancamentos and its DTO LancamentosDTO.
 */
@Mapper(componentModel = "spring", uses = {FonteMapper.class, PlanocontasMapper.class, })
public interface LancamentosMapper extends EntityMapper <LancamentosDTO, Lancamentos> {
    @Mapping(source = "fonte.id", target = "fonteId")
    @Mapping(source = "idplanocontas.id", target = "idplanocontasId")
    LancamentosDTO toDto(Lancamentos lancamentos); 
    @Mapping(source = "fonteId", target = "fonte")
    @Mapping(source = "idplanocontasId", target = "idplanocontas")
    Lancamentos toEntity(LancamentosDTO lancamentosDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Lancamentos fromId(Long id) {
        if (id == null) {
            return null;
        }
        Lancamentos lancamentos = new Lancamentos();
        lancamentos.setId(id);
        return lancamentos;
    }
}
