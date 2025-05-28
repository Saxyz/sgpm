package edu.unimag.sgpm.model.repository;

import edu.unimag.sgpm.model.entity.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Integer> {

    List<Moto> findByUsuarioIdUsuario(Integer id);
}
