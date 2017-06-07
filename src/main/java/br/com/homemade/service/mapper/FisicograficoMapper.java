package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.FisicograficoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Fisicografico and its DTO FisicograficoDTO.
 */
@Mapper(componentModel = "spring", uses = {AtividadeMapper.class, })
public interface FisicograficoMapper extends EntityMapper <FisicograficoDTO, Fisicografico> {
    @Mapping(source = "atividade.id", target = "atividadeId")
    FisicograficoDTO toDto(Fisicografico fisicografico); 
    @Mapping(source = "atividadeId", target = "atividade")
    Fisicografico toEntity(FisicograficoDTO fisicograficoDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Fisicografico fromId(Long id) {
        if (id == null) {
            return null;
        }
        Fisicografico fisicografico = new Fisicografico();
        fisicografico.setId(id);
        return fisicografico;
    }
}
