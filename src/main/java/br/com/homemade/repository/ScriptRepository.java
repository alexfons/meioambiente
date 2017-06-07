package br.com.homemade.repository;

import br.com.homemade.domain.Script;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Script entity.
 */
@SuppressWarnings("unused")
public interface ScriptRepository extends JpaRepository<Script,Long> {

}
