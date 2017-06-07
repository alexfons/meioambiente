package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.CertificadoconformidadeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Certificadoconformidade and its DTO CertificadoconformidadeDTO.
 */
@Mapper(componentModel = "spring", uses = {ObraMapper.class, TipocertificadoconformidadeMapper.class, InformecertificadoirregularidadeMapper.class, NotificacaocertificadoirregularidadeMapper.class, OcorrenciacertificadoirregularidadeMapper.class, })
public interface CertificadoconformidadeMapper extends EntityMapper <CertificadoconformidadeDTO, Certificadoconformidade> {
    @Mapping(source = "obra.id", target = "obraId")
    @Mapping(source = "tipo.id", target = "tipoId")
    @Mapping(source = "tipocertificadoconformidade.id", target = "tipocertificadoconformidadeId")
    CertificadoconformidadeDTO toDto(Certificadoconformidade certificadoconformidade); 
    @Mapping(source = "obraId", target = "obra")
    @Mapping(source = "tipoId", target = "tipo")
    @Mapping(source = "tipocertificadoconformidadeId", target = "tipocertificadoconformidade")
    Certificadoconformidade toEntity(CertificadoconformidadeDTO certificadoconformidadeDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Certificadoconformidade fromId(Long id) {
        if (id == null) {
            return null;
        }
        Certificadoconformidade certificadoconformidade = new Certificadoconformidade();
        certificadoconformidade.setId(id);
        return certificadoconformidade;
    }
}
