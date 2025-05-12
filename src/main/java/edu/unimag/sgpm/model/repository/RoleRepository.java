package edu.unimag.sgpm.model.repository;

import edu.unimag.sgpm.model.entity.ERole;
import edu.unimag.sgpm.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRole(ERole name);
    Optional<Role> findByName(ERole ERol);
}
