package br.com.homemade.repository;

import br.com.homemade.domain.Empresacontrato;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Empresacontrato entity.
 */
@SuppressWarnings("unused")
public interface EmpresacontratoRepository extends JpaRepository<Empresacontrato,Long> {

}
