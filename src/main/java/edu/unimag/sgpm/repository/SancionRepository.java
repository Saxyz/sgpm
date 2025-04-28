package edu.unimag.sgpm.repository;

import edu.unimag.sgpm.model.Sancion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SancionRepository extends JpaRepository<Sancion, Integer> {
}
