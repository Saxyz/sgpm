package edu.unimag.sgpm.model.repository;

import edu.unimag.sgpm.ContainerConfig;
import edu.unimag.sgpm.model.entity.ERole;
import edu.unimag.sgpm.model.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Import(ContainerConfig.class)
@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RoleRepositoryTest {
    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    void setup() {
        roleRepository.deleteAll();

        roleRepository.save(new Role(null, ERole.ROLE_ADMINISTRADOR));
        roleRepository.save(new Role(null, ERole.ROLE_ESTUDIANTE));
        roleRepository.save(new Role(null, ERole.ROLE_PROFESOR));
    }

    @Test
    void findByRole() {
        Optional<Role> roleOpt = roleRepository.findByRole(ERole.ROLE_ADMINISTRADOR);

        assertTrue(roleOpt.isPresent(), "El rol debería existir");
        Role role = roleOpt.get();
        assertEquals(ERole.ROLE_ADMINISTRADOR, role.getRole());
    }
}