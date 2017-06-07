package br.com.homemade.repository;

import br.com.homemade.domain.Projeto;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Projeto entity.
 */
@SuppressWarnings("unused")
public interface ProjetoRepository extends JpaRepository<Projeto,Long> {

    @Query("select distinct projeto from Projeto projeto left join fetch projeto.contratosprojetos left join fetch projeto.historicos left join fetch projeto.municipios")
    List<Projeto> findAllWithEagerRelationships();

    @Query("select projeto from Projeto projeto left join fetch projeto.contratosprojetos left join fetch projeto.historicos left join fetch projeto.municipios where projeto.id =:id")
    Projeto findOneWithEagerRelationships(@Param("id") Long id);

}
