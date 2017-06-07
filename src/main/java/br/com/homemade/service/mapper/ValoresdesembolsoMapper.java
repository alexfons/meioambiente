package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.ValoresdesembolsoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Valoresdesembolso and its DTO ValoresdesembolsoDTO.
 */
@Mapper(componentModel = "spring", uses = {ContabancariaMapper.class, ReferenciaMapper.class, })
public interface ValoresdesembolsoMapper extends EntityMapper <ValoresdesembolsoDTO, Valoresdesembolso> {
    @Mapping(source = "idcontabancaria.id", target = "idcontabancariaId")
    @Mapping(source = "idreferencia.id", target = "idreferenciaId")
    ValoresdesembolsoDTO toDto(Valoresdesembolso valoresdesembolso); 
    @Mapping(source = "idcontabancariaId", target = "idcontabancaria")
    @Mapping(source = "idreferenciaId", target = "idreferencia")
    Valoresdesembolso toEntity(ValoresdesembolsoDTO valoresdesembolsoDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Valoresdesembolso fromId(Long id) {
        if (id == null) {
            return null;
        }
        Valoresdesembolso valoresdesembolso = new Valoresdesembolso();
        valoresdesembolso.setId(id);
        return valoresdesembolso;
    }
}
