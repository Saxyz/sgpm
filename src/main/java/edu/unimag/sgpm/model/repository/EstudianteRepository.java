package edu.unimag.sgpm.model.repository;

import edu.unimag.sgpm.model.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {

    Optional<Estudiante> findById(Integer id);

}
