package edu.unimag.sgpm.repository;

import edu.unimag.sgpm.model.Registro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroRepository extends JpaRepository<Registro, Integer> {
}
