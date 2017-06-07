package br.com.homemade.repository;

import br.com.homemade.domain.Licenca;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Licenca entity.
 */
@SuppressWarnings("unused")
public interface LicencaRepository extends JpaRepository<Licenca,Long> {

    @Query("select distinct licenca from Licenca licenca left join fetch licenca.condicionantes left join fetch licenca.docs left join fetch licenca.fotos")
    List<Licenca> findAllWithEagerRelationships();

    @Query("select licenca from Licenca licenca left join fetch licenca.condicionantes left join fetch licenca.docs left join fetch licenca.fotos where licenca.id =:id")
    Licenca findOneWithEagerRelationships(@Param("id") Long id);

}
