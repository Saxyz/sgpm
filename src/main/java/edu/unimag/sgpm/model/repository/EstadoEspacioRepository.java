package edu.unimag.sgpm.model.repository;

import edu.unimag.sgpm.model.entity.EstadoDeEspacio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoEspacioRepository extends JpaRepository<EstadoDeEspacio,Integer> {
}
