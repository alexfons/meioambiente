package br.com.homemade.repository;

import br.com.homemade.domain.Contrato;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Contrato entity.
 */
@SuppressWarnings("unused")
public interface ContratoRepository extends JpaRepository<Contrato,Long> {

    @Query("select distinct contrato from Contrato contrato left join fetch contrato.aditivocontratos left join fetch contrato.contratosobras left join fetch contrato.empresascontratoes")
    List<Contrato> findAllWithEagerRelationships();

    @Query("select contrato from Contrato contrato left join fetch contrato.aditivocontratos left join fetch contrato.contratosobras left join fetch contrato.empresascontratoes where contrato.id =:id")
    Contrato findOneWithEagerRelationships(@Param("id") Long id);

}
