package br.com.homemade.repository;

import br.com.homemade.domain.Log;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Log entity.
 */
@SuppressWarnings("unused")
public interface LogRepository extends JpaRepository<Log,Long> {

}
