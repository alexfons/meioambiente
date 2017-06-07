package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.CertifConforDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CertifConfor and its DTO CertifConforDTO.
 */
@Mapper(componentModel = "spring", uses = {ObraMapper.class, TipocertifConforMapper.class, InformeCertifIrregMapper.class, NotificacaoCertifIrregMapper.class, OcorrenciaCertifIrregMapper.class, })
public interface CertifConforMapper extends EntityMapper <CertifConforDTO, CertifConfor> {
    @Mapping(source = "obra.id", target = "obraId")
    @Mapping(source = "tipo.id", target = "tipoId")
    @Mapping(source = "tipocertifConfor.id", target = "tipocertifConforId")
    CertifConforDTO toDto(CertifConfor certifConfor); 
    @Mapping(source = "obraId", target = "obra")
    @Mapping(source = "tipoId", target = "tipo")
    @Mapping(source = "tipocertifConforId", target = "tipocertifConfor")
    CertifConfor toEntity(CertifConforDTO certifConforDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default CertifConfor fromId(Long id) {
        if (id == null) {
            return null;
        }
        CertifConfor certifConfor = new CertifConfor();
        certifConfor.setId(id);
        return certifConfor;
    }
}
