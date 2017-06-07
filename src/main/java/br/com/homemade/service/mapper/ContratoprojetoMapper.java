package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.ContratoprojetoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Contratoprojeto and its DTO ContratoprojetoDTO.
 */
@Mapper(componentModel = "spring", uses = {ContratoMapper.class, ResponsavelMapper.class, })
public interface ContratoprojetoMapper extends EntityMapper <ContratoprojetoDTO, Contratoprojeto> {
    @Mapping(source = "contrato.id", target = "contratoId")
    @Mapping(source = "responsavel.id", target = "responsavelId")
    ContratoprojetoDTO toDto(Contratoprojeto contratoprojeto); 
    @Mapping(source = "contratoId", target = "contrato")
    @Mapping(source = "responsavelId", target = "responsavel")
    Contratoprojeto toEntity(ContratoprojetoDTO contratoprojetoDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Contratoprojeto fromId(Long id) {
        if (id == null) {
            return null;
        }
        Contratoprojeto contratoprojeto = new Contratoprojeto();
        contratoprojeto.setId(id);
        return contratoprojeto;
    }
}
