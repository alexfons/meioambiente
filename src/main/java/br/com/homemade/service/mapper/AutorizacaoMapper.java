package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.AutorizacaoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Autorizacao and its DTO AutorizacaoDTO.
 */
@Mapper(componentModel = "spring", uses = {AtividadelicencaMapper.class, EmpresaMapper.class, ObraMapper.class, OrgaoemissorMapper.class, ProjetoMapper.class, TipoautorizacaoMapper.class, FotoMapper.class, DocumentoMapper.class, })
public interface AutorizacaoMapper extends EntityMapper <AutorizacaoDTO, Autorizacao> {
    @Mapping(source = "atividadelicenca.id", target = "atividadelicencaId")
    @Mapping(source = "empresa.id", target = "empresaId")
    @Mapping(source = "obra.id", target = "obraId")
    @Mapping(source = "orgaoemissor.id", target = "orgaoemissorId")
    @Mapping(source = "projeto.id", target = "projetoId")
    @Mapping(source = "tipoautorizacao.id", target = "tipoautorizacaoId")
    AutorizacaoDTO toDto(Autorizacao autorizacao); 
    @Mapping(source = "atividadelicencaId", target = "atividadelicenca")
    @Mapping(source = "empresaId", target = "empresa")
    @Mapping(source = "obraId", target = "obra")
    @Mapping(source = "orgaoemissorId", target = "orgaoemissor")
    @Mapping(source = "projetoId", target = "projeto")
    @Mapping(source = "tipoautorizacaoId", target = "tipoautorizacao")
    Autorizacao toEntity(AutorizacaoDTO autorizacaoDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Autorizacao fromId(Long id) {
        if (id == null) {
            return null;
        }
        Autorizacao autorizacao = new Autorizacao();
        autorizacao.setId(id);
        return autorizacao;
    }
}
