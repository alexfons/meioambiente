package br.com.homemade.repository;

import br.com.homemade.domain.Administrativo;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Administrativo entity.
 */
@SuppressWarnings("unused")
public interface AdministrativoRepository extends JpaRepository<Administrativo,Long> {

    @Query("select distinct administrativo from Administrativo administrativo left join fetch administrativo.fotos left join fetch administrativo.docs left join fetch administrativo.participantes")
    List<Administrativo> findAllWithEagerRelationships();

    @Query("select administrativo from Administrativo administrativo left join fetch administrativo.fotos left join fetch administrativo.docs left join fetch administrativo.participantes where administrativo.id =:id")
    Administrativo findOneWithEagerRelationships(@Param("id") Long id);

}
