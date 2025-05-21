package edu.unimag.sgpm.model.repository;

import edu.unimag.sgpm.model.entity.Role;
import edu.unimag.sgpm.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByRoles(Set<Role> roles);
    Optional<Usuario> findByCorreo(String correo);
    boolean existsUserByCorreo(String email);
}
