package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.InformeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Informe and its DTO InformeDTO.
 */
@Mapper(componentModel = "spring", uses = {ObraMapper.class, FotoMapper.class, OcorrenciainformeMapper.class, })
public interface InformeMapper extends EntityMapper <InformeDTO, Informe> {
    @Mapping(source = "obra.id", target = "obraId")
    InformeDTO toDto(Informe informe); 
    @Mapping(source = "obraId", target = "obra")
    Informe toEntity(InformeDTO informeDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Informe fromId(Long id) {
        if (id == null) {
            return null;
        }
        Informe informe = new Informe();
        informe.setId(id);
        return informe;
    }
}
