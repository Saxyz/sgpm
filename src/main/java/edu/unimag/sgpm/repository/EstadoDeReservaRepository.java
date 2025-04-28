package edu.unimag.sgpm.repository;

import edu.unimag.sgpm.model.EstadoDeReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadoDeReservaRepository extends JpaRepository<EstadoDeReserva, Integer> {

    Optional<EstadoDeReserva> findByIdEstado(Integer id);

    Optional<EstadoDeReserva> findByNombre(String nombre);
}
