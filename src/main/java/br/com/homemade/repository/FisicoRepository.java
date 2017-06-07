package br.com.homemade.repository;

import br.com.homemade.domain.Fisico;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Fisico entity.
 */
@SuppressWarnings("unused")
public interface FisicoRepository extends JpaRepository<Fisico,Long> {

    @Query("select distinct fisico from Fisico fisico left join fetch fisico.obraatividades")
    List<Fisico> findAllWithEagerRelationships();

    @Query("select fisico from Fisico fisico left join fetch fisico.obraatividades where fisico.id =:id")
    Fisico findOneWithEagerRelationships(@Param("id") Long id);

}
