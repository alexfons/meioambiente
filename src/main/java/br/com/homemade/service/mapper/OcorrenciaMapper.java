package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.OcorrenciaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Ocorrencia and its DTO OcorrenciaDTO.
 */
@Mapper(componentModel = "spring", uses = {AtividadeMapper.class, ObraMapper.class, TabelaMapper.class, TipoocorrenciaMapper.class, FotoMapper.class, HistoricoMapper.class, RegistroMapper.class, })
public interface OcorrenciaMapper extends EntityMapper <OcorrenciaDTO, Ocorrencia> {
    @Mapping(source = "atividade.id", target = "atividadeId")
    @Mapping(source = "obra.id", target = "obraId")
    @Mapping(source = "servico.id", target = "servicoId")
    @Mapping(source = "tabela.id", target = "tabelaId")
    @Mapping(source = "tipo.id", target = "tipoId")
    @Mapping(source = "tipoocorrencia.id", target = "tipoocorrenciaId")
    OcorrenciaDTO toDto(Ocorrencia ocorrencia); 
    @Mapping(source = "atividadeId", target = "atividade")
    @Mapping(source = "obraId", target = "obra")
    @Mapping(source = "servicoId", target = "servico")
    @Mapping(source = "tabelaId", target = "tabela")
    @Mapping(source = "tipoId", target = "tipo")
    @Mapping(source = "tipoocorrenciaId", target = "tipoocorrencia")
    Ocorrencia toEntity(OcorrenciaDTO ocorrenciaDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Ocorrencia fromId(Long id) {
        if (id == null) {
            return null;
        }
        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setId(id);
        return ocorrencia;
    }
}
