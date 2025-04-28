package edu.unimag.sgpm.repository;

import edu.unimag.sgpm.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {
}
