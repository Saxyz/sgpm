package edu.unimag.sgpm.model.repository;

import edu.unimag.sgpm.model.entity.Sancion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SancionRepository extends JpaRepository<Sancion, Integer> {
}
