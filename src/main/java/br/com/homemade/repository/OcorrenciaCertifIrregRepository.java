package br.com.homemade.repository;

import br.com.homemade.domain.OcorrenciaCertifIrreg;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the OcorrenciaCertifIrreg entity.
 */
@SuppressWarnings("unused")
public interface OcorrenciaCertifIrregRepository extends JpaRepository<OcorrenciaCertifIrreg,Long> {

}
