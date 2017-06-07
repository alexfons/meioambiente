package br.com.homemade.repository;

import br.com.homemade.domain.Certificadoconformidade;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Certificadoconformidade entity.
 */
@SuppressWarnings("unused")
public interface CertificadoconformidadeRepository extends JpaRepository<Certificadoconformidade,Long> {

    @Query("select distinct certificadoconformidade from Certificadoconformidade certificadoconformidade left join fetch certificadoconformidade.informescertificadoirregularidades left join fetch certificadoconformidade.notificacaoscertificadoirregularidades left join fetch certificadoconformidade.ocorrenciascertificadoirregularidades")
    List<Certificadoconformidade> findAllWithEagerRelationships();

    @Query("select certificadoconformidade from Certificadoconformidade certificadoconformidade left join fetch certificadoconformidade.informescertificadoirregularidades left join fetch certificadoconformidade.notificacaoscertificadoirregularidades left join fetch certificadoconformidade.ocorrenciascertificadoirregularidades where certificadoconformidade.id =:id")
    Certificadoconformidade findOneWithEagerRelationships(@Param("id") Long id);

}
