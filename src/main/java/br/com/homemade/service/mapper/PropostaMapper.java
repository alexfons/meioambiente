package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.PropostaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Proposta and its DTO PropostaDTO.
 */
@Mapper(componentModel = "spring", uses = {EmpresaMapper.class, EditalloteMapper.class, })
public interface PropostaMapper extends EntityMapper <PropostaDTO, Proposta> {
    @Mapping(source = "idempresa.id", target = "idempresaId")
    @Mapping(source = "idlote.id", target = "idloteId")
    PropostaDTO toDto(Proposta proposta); 
    @Mapping(source = "idempresaId", target = "idempresa")
    @Mapping(source = "idloteId", target = "idlote")
    Proposta toEntity(PropostaDTO propostaDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Proposta fromId(Long id) {
        if (id == null) {
            return null;
        }
        Proposta proposta = new Proposta();
        proposta.setId(id);
        return proposta;
    }
}
