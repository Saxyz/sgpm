package edu.unimag.sgpm.model.repository;

import edu.unimag.sgpm.model.entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepository extends JpaRepository<Profesor, Integer> {
}
