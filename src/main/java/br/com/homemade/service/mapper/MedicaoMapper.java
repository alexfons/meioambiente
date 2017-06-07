package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.MedicaoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Medicao and its DTO MedicaoDTO.
 */
@Mapper(componentModel = "spring", uses = {ContratoMapper.class, })
public interface MedicaoMapper extends EntityMapper <MedicaoDTO, Medicao> {
    @Mapping(source = "idcontrato.id", target = "idcontratoId")
    MedicaoDTO toDto(Medicao medicao); 
    @Mapping(source = "idcontratoId", target = "idcontrato")
    Medicao toEntity(MedicaoDTO medicaoDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Medicao fromId(Long id) {
        if (id == null) {
            return null;
        }
        Medicao medicao = new Medicao();
        medicao.setId(id);
        return medicao;
    }
}
