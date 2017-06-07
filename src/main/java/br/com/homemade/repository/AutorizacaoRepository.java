package br.com.homemade.repository;

import br.com.homemade.domain.Autorizacao;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Autorizacao entity.
 */
@SuppressWarnings("unused")
public interface AutorizacaoRepository extends JpaRepository<Autorizacao,Long> {

    @Query("select distinct autorizacao from Autorizacao autorizacao left join fetch autorizacao.fotos left join fetch autorizacao.docs")
    List<Autorizacao> findAllWithEagerRelationships();

    @Query("select autorizacao from Autorizacao autorizacao left join fetch autorizacao.fotos left join fetch autorizacao.docs where autorizacao.id =:id")
    Autorizacao findOneWithEagerRelationships(@Param("id") Long id);

}
