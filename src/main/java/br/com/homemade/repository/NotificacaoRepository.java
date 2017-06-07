package br.com.homemade.repository;

import br.com.homemade.domain.Notificacao;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Notificacao entity.
 */
@SuppressWarnings("unused")
public interface NotificacaoRepository extends JpaRepository<Notificacao,Long> {

    @Query("select distinct notificacao from Notificacao notificacao left join fetch notificacao.fotos left join fetch notificacao.ocorrenciasnotificacaos")
    List<Notificacao> findAllWithEagerRelationships();

    @Query("select notificacao from Notificacao notificacao left join fetch notificacao.fotos left join fetch notificacao.ocorrenciasnotificacaos where notificacao.id =:id")
    Notificacao findOneWithEagerRelationships(@Param("id") Long id);

}
