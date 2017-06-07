package br.com.homemade.repository;

import br.com.homemade.domain.CertifConfor;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the CertifConfor entity.
 */
@SuppressWarnings("unused")
public interface CertifConforRepository extends JpaRepository<CertifConfor,Long> {

    @Query("select distinct certifConfor from CertifConfor certifConfor left join fetch certifConfor.informesCertifIrregs left join fetch certifConfor.notificacaosCertifIrregs left join fetch certifConfor.ocorrenciasCertifIrregs")
    List<CertifConfor> findAllWithEagerRelationships();

    @Query("select certifConfor from CertifConfor certifConfor left join fetch certifConfor.informesCertifIrregs left join fetch certifConfor.notificacaosCertifIrregs left join fetch certifConfor.ocorrenciasCertifIrregs where certifConfor.id =:id")
    CertifConfor findOneWithEagerRelationships(@Param("id") Long id);

}
