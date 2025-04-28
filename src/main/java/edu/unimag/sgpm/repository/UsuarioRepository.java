package edu.unimag.sgpm.repository;

import edu.unimag.sgpm.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findUsuarioByIdUsuario(Integer id);

    List<Usuario> findByParqueaderoIdParqueadero(Integer id);
}
