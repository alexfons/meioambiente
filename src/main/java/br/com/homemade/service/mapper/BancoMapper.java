package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.BancoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Banco and its DTO BancoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BancoMapper extends EntityMapper <BancoDTO, Banco> {
    
    
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Banco fromId(Long id) {
        if (id == null) {
            return null;
        }
        Banco banco = new Banco();
        banco.setId(id);
        return banco;
    }
}
