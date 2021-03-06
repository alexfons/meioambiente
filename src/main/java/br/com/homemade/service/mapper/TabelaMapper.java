package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.TabelaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Tabela and its DTO TabelaDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TabelaMapper extends EntityMapper <TabelaDTO, Tabela> {
    
    @Mapping(target = "campos", ignore = true)
    Tabela toEntity(TabelaDTO tabelaDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Tabela fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tabela tabela = new Tabela();
        tabela.setId(id);
        return tabela;
    }
}
