package edu.unimag.sgpm.model.repository;

import edu.unimag.sgpm.model.entity.Parqueadero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ParqueaderoRepository extends JpaRepository<Parqueadero, Integer> {

    Optional<Parqueadero> findByIdParqueadero(Integer id);

}
