package edu.unimag.sgpm.repository;

import edu.unimag.sgpm.model.EstadoDeEspacio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadoDeEspacioRepository extends JpaRepository<EstadoDeEspacio, Integer> {

    Optional<EstadoDeEspacio> findByIdEstado(Integer id);

    Optional<EstadoDeEspacio> findByNombre(String nombre);
}
