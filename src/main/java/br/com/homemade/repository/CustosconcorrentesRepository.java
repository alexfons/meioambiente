package br.com.homemade.repository;

import br.com.homemade.domain.Custosconcorrentes;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Custosconcorrentes entity.
 */
@SuppressWarnings("unused")
public interface CustosconcorrentesRepository extends JpaRepository<Custosconcorrentes,Long> {

}
