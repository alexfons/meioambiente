package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.AucDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Auc and its DTO AucDTO.
 */
@Mapper(componentModel = "spring", uses = {AtividadelicencaMapper.class, OrgaoemissorMapper.class, ObraMapper.class, ProjetoMapper.class, EmpresaMapper.class, CondicionanteMapper.class, FotoMapper.class, DocumentoMapper.class, })
public interface AucMapper extends EntityMapper <AucDTO, Auc> {
    @Mapping(source = "atividadelicenca.id", target = "atividadelicencaId")
    @Mapping(source = "orgaoemissor.id", target = "orgaoemissorId")
    @Mapping(source = "obra.id", target = "obraId")
    @Mapping(source = "projeto.id", target = "projetoId")
    @Mapping(source = "empresa.id", target = "empresaId")
    AucDTO toDto(Auc auc); 
    @Mapping(source = "atividadelicencaId", target = "atividadelicenca")
    @Mapping(source = "orgaoemissorId", target = "orgaoemissor")
    @Mapping(source = "obraId", target = "obra")
    @Mapping(source = "projetoId", target = "projeto")
    @Mapping(source = "empresaId", target = "empresa")
    Auc toEntity(AucDTO aucDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Auc fromId(Long id) {
        if (id == null) {
            return null;
        }
        Auc auc = new Auc();
        auc.setId(id);
        return auc;
    }
}
