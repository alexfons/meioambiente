package br.com.homemade.repository;

import br.com.homemade.domain.Participante;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Participante entity.
 */
@SuppressWarnings("unused")
public interface ParticipanteRepository extends JpaRepository<Participante,Long> {

}
