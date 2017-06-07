package br.com.homemade.repository;

import br.com.homemade.domain.NotificacaoCertifIrreg;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the NotificacaoCertifIrreg entity.
 */
@SuppressWarnings("unused")
public interface NotificacaoCertifIrregRepository extends JpaRepository<NotificacaoCertifIrreg,Long> {

}
