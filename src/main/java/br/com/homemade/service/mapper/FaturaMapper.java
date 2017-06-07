package br.com.homemade.service.mapper;

import br.com.homemade.domain.*;
import br.com.homemade.service.dto.FaturaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Fatura and its DTO FaturaDTO.
 */
@Mapper(componentModel = "spring", uses = {FonteMapper.class, ContabancariaMapper.class, ContratoMapper.class, ReferenciaMapper.class, MedicaoMapper.class, })
public interface FaturaMapper extends EntityMapper <FaturaDTO, Fatura> {
    @Mapping(source = "fonte.id", target = "fonteId")
    @Mapping(source = "idcontabancaria.id", target = "idcontabancariaId")
    @Mapping(source = "idcontrato.id", target = "idcontratoId")
    @Mapping(source = "idreferencia.id", target = "idreferenciaId")
    @Mapping(source = "medicao.id", target = "medicaoId")
    FaturaDTO toDto(Fatura fatura); 
    @Mapping(source = "fonteId", target = "fonte")
    @Mapping(source = "idcontabancariaId", target = "idcontabancaria")
    @Mapping(source = "idcontratoId", target = "idcontrato")
    @Mapping(source = "idreferenciaId", target = "idreferencia")
    @Mapping(source = "medicaoId", target = "medicao")
    Fatura toEntity(FaturaDTO faturaDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Fatura fromId(Long id) {
        if (id == null) {
            return null;
        }
        Fatura fatura = new Fatura();
        fatura.setId(id);
        return fatura;
    }
}
