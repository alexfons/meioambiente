package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.ProjetoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Projeto and its DTO ProjetoDTO.
 */
@Mapper(componentModel = "spring", uses = {InspetorMapper.class, MunicipioMapper.class, TipoobraMapper.class, TrechoMapper.class, FiscalMapper.class, ContratoprojetoMapper.class, HistoricoMapper.class, })
public interface ProjetoMapper extends EntityMapper <ProjetoDTO, Projeto> {
    @Mapping(source = "inspetor.id", target = "inspetorId")
    @Mapping(source = "municipio.id", target = "municipioId")
    @Mapping(source = "tipo.id", target = "tipoId")
    @Mapping(source = "tipoobra.id", target = "tipoobraId")
    @Mapping(source = "trecho.id", target = "trechoId")
    @Mapping(source = "fiscal.id", target = "fiscalId")
    ProjetoDTO toDto(Projeto projeto); 
    @Mapping(source = "inspetorId", target = "inspetor")
    @Mapping(source = "municipioId", target = "municipio")
    @Mapping(source = "tipoId", target = "tipo")
    @Mapping(source = "tipoobraId", target = "tipoobra")
    @Mapping(source = "trechoId", target = "trecho")
    @Mapping(source = "fiscalId", target = "fiscal")
    Projeto toEntity(ProjetoDTO projetoDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Projeto fromId(Long id) {
        if (id == null) {
            return null;
        }
        Projeto projeto = new Projeto();
        projeto.setId(id);
        return projeto;
    }
}
