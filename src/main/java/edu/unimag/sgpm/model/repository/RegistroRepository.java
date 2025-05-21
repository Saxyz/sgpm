package edu.unimag.sgpm.model.repository;

import edu.unimag.sgpm.model.entity.Registro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroRepository extends JpaRepository<Registro, Integer> {
}
