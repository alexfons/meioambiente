package br.com.homemade.repository;

import br.com.homemade.domain.Auc;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Auc entity.
 */
@SuppressWarnings("unused")
public interface AucRepository extends JpaRepository<Auc,Long> {

    @Query("select distinct auc from Auc auc left join fetch auc.condicionantes left join fetch auc.fotos left join fetch auc.docs")
    List<Auc> findAllWithEagerRelationships();

    @Query("select auc from Auc auc left join fetch auc.condicionantes left join fetch auc.fotos left join fetch auc.docs where auc.id =:id")
    Auc findOneWithEagerRelationships(@Param("id") Long id);

}
