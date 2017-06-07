package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.ClausulascontratuaisDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Clausulascontratuais and its DTO ClausulascontratuaisDTO.
 */
@Mapper(componentModel = "spring", uses = {ContratoagenteMapper.class, })
public interface ClausulascontratuaisMapper extends EntityMapper <ClausulascontratuaisDTO, Clausulascontratuais> {
    @Mapping(source = "idcontratoagente.id", target = "idcontratoagenteId")
    ClausulascontratuaisDTO toDto(Clausulascontratuais clausulascontratuais); 
    @Mapping(source = "idcontratoagenteId", target = "idcontratoagente")
    Clausulascontratuais toEntity(ClausulascontratuaisDTO clausulascontratuaisDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Clausulascontratuais fromId(Long id) {
        if (id == null) {
            return null;
        }
        Clausulascontratuais clausulascontratuais = new Clausulascontratuais();
        clausulascontratuais.setId(id);
        return clausulascontratuais;
    }
}
