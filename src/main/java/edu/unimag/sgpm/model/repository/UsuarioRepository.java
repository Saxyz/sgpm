package edu.unimag.sgpm.model.repository;

import edu.unimag.sgpm.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findUsuarioByIdUsuario(Integer id);

    List<Usuario> findByParqueaderoIdParqueadero(Integer id);
}
