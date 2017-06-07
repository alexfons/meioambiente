package br.com.homemade.repository;

import br.com.homemade.domain.InformeCertifIrreg;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the InformeCertifIrreg entity.
 */
@SuppressWarnings("unused")
public interface InformeCertifIrregRepository extends JpaRepository<InformeCertifIrreg,Long> {

}
