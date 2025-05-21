package edu.unimag.sgpm.model.repository;

import edu.unimag.sgpm.model.entity.EstadoDeReserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoReservaRepository extends JpaRepository<EstadoDeReserva,Integer> {
}
