package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.AdministrativoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Administrativo and its DTO AdministrativoDTO.
 */
@Mapper(componentModel = "spring", uses = {TipoadministrativoMapper.class, ObraMapper.class, FotoMapper.class, DocumentoMapper.class, ParticipanteMapper.class, })
public interface AdministrativoMapper extends EntityMapper <AdministrativoDTO, Administrativo> {
    @Mapping(source = "tipo.id", target = "tipoId")
    @Mapping(source = "obra.id", target = "obraId")
    AdministrativoDTO toDto(Administrativo administrativo); 
    @Mapping(source = "tipoId", target = "tipo")
    @Mapping(source = "obraId", target = "obra")
    Administrativo toEntity(AdministrativoDTO administrativoDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Administrativo fromId(Long id) {
        if (id == null) {
            return null;
        }
        Administrativo administrativo = new Administrativo();
        administrativo.setId(id);
        return administrativo;
    }
}
