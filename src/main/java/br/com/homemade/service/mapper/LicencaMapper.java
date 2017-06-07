package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.LicencaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Licenca and its DTO LicencaDTO.
 */
@Mapper(componentModel = "spring", uses = {AtividadelicencaMapper.class, EmpresaMapper.class, ProjetoMapper.class, TipolicencaMapper.class, ObraMapper.class, OrgaoemissorMapper.class, CondicionanteMapper.class, DocumentoMapper.class, FotoMapper.class, })
public interface LicencaMapper extends EntityMapper <LicencaDTO, Licenca> {
    @Mapping(source = "atividadelicenca.id", target = "atividadelicencaId")
    @Mapping(source = "empresa.id", target = "empresaId")
    @Mapping(source = "projeto.id", target = "projetoId")
    @Mapping(source = "tipo.id", target = "tipoId")
    @Mapping(source = "obra.id", target = "obraId")
    @Mapping(source = "orgaoemissor.id", target = "orgaoemissorId")
    @Mapping(source = "tipolicenca.id", target = "tipolicencaId")
    LicencaDTO toDto(Licenca licenca); 
    @Mapping(source = "atividadelicencaId", target = "atividadelicenca")
    @Mapping(source = "empresaId", target = "empresa")
    @Mapping(source = "projetoId", target = "projeto")
    @Mapping(source = "tipoId", target = "tipo")
    @Mapping(source = "obraId", target = "obra")
    @Mapping(source = "orgaoemissorId", target = "orgaoemissor")
    @Mapping(source = "tipolicencaId", target = "tipolicenca")
    Licenca toEntity(LicencaDTO licencaDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Licenca fromId(Long id) {
        if (id == null) {
            return null;
        }
        Licenca licenca = new Licenca();
        licenca.setId(id);
        return licenca;
    }
}
