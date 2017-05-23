package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.ObraDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Obra and its DTO ObraDTO.
 */
@Mapper(componentModel = "spring", uses = {TipoObraMapper.class, InspetorMapper.class, FiscalMapper.class, TrechoMapper.class, ContratoobraMapper.class, HistoricoMapper.class, })
public interface ObraMapper extends EntityMapper <ObraDTO, Obra> {
    @Mapping(source = "tipoobra.id", target = "tipoobraId")
    @Mapping(source = "inspetor.id", target = "inspetorId")
    @Mapping(source = "fiscal.id", target = "fiscalId")
    @Mapping(source = "trecho.id", target = "trechoId")
    ObraDTO toDto(Obra obra); 
    @Mapping(source = "tipoobraId", target = "tipoobra")
    @Mapping(source = "inspetorId", target = "inspetor")
    @Mapping(source = "fiscalId", target = "fiscal")
    @Mapping(source = "trechoId", target = "trecho")
    Obra toEntity(ObraDTO obraDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Obra fromId(Long id) {
        if (id == null) {
            return null;
        }
        Obra obra = new Obra();
        obra.setId(id);
        return obra;
    }
}
