package br.com.homemade.repository;

import br.com.homemade.domain.Apresentacao;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Apresentacao entity.
 */
@SuppressWarnings("unused")
public interface ApresentacaoRepository extends JpaRepository<Apresentacao,Long> {

    @Query("select distinct apresentacao from Apresentacao apresentacao left join fetch apresentacao.ocorrenciasapresentacaos left join fetch apresentacao.fotos")
    List<Apresentacao> findAllWithEagerRelationships();

    @Query("select apresentacao from Apresentacao apresentacao left join fetch apresentacao.ocorrenciasapresentacaos left join fetch apresentacao.fotos where apresentacao.id =:id")
    Apresentacao findOneWithEagerRelationships(@Param("id") Long id);

}
