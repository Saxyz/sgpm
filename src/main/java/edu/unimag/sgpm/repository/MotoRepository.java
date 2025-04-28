package edu.unimag.sgpm.repository;

import edu.unimag.sgpm.model.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MotoRepository extends JpaRepository<Moto, String> {


    Optional<Moto> findByIdMoto(String placa);

    List<Moto> findByUsuarioIdUsuario(Integer id);
}
