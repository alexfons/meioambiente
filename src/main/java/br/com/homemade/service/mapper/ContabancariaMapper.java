package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.ContabancariaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Contabancaria and its DTO ContabancariaDTO.
 */
@Mapper(componentModel = "spring", uses = {PlanocontasMapper.class, })
public interface ContabancariaMapper extends EntityMapper <ContabancariaDTO, Contabancaria> {
    @Mapping(source = "idplanocontas.id", target = "idplanocontasId")
    ContabancariaDTO toDto(Contabancaria contabancaria); 
    @Mapping(source = "idplanocontasId", target = "idplanocontas")
    Contabancaria toEntity(ContabancariaDTO contabancariaDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Contabancaria fromId(Long id) {
        if (id == null) {
            return null;
        }
        Contabancaria contabancaria = new Contabancaria();
        contabancaria.setId(id);
        return contabancaria;
    }
}
