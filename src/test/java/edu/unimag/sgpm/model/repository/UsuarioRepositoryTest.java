package edu.unimag.sgpm.model.repository;

import edu.unimag.sgpm.ContainerConfig;
import edu.unimag.sgpm.model.entity.ERole;
import edu.unimag.sgpm.model.entity.Parqueadero;
import edu.unimag.sgpm.model.entity.Role;
import edu.unimag.sgpm.model.entity.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@Import(ContainerConfig.class)
@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UsuarioRepositoryTest {


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ParqueaderoRepository parqueaderoRepository;

    private Role roleAdmin;

    private Usuario usuario2;

    @BeforeEach
    void setup() {
        usuarioRepository.deleteAll();
        roleRepository.deleteAll();
        parqueaderoRepository.deleteAll();

        roleAdmin = roleRepository.save(new Role(null, ERole.ROLE_ADMINISTRADOR));
        Role roleEstudiante = roleRepository.save(new Role(null, ERole.ROLE_ESTUDIANTE));

        Parqueadero parqueadero = parqueaderoRepository.save(Parqueadero.builder()
                .nombre("Parqueadero Central")
                .imagen("imagen.png")
                .build());

        Usuario usuario1 = usuarioRepository.save(Usuario.builder()
                .nombre("Juan")
                .apellido("Perez")
                .correo("juan.perez@example.com")
                .contrasenia("password123")
                .parqueadero(parqueadero)
                .roles(Set.of(roleAdmin))
                .build());

        usuario2 = usuarioRepository.save(Usuario.builder()
                .nombre("Maria")
                .apellido("Lopez")
                .correo("maria.lopez@example.com")
                .contrasenia("password456")
                .parqueadero(parqueadero)
                .roles(Set.of(roleEstudiante))
                .build());
    }

    @Test
    void findByRoles() {
        Optional<Usuario> result = usuarioRepository.findByRoles(Set.of(roleAdmin));
        assertTrue(result.isPresent());
        assertEquals("juan.perez@example.com", result.get().getCorreo());
    }

    @Test
    void findByCorreo() {
        Optional<Usuario> result = usuarioRepository.findByCorreo("maria.lopez@example.com");
        assertTrue(result.isPresent());
        assertEquals("Maria", result.get().getNombre());
    }

    @Test
    void existsUserByCorreo() {
        boolean exists = usuarioRepository.existsUserByCorreo("juan.perez@example.com");
        assertTrue(exists);

        boolean notExists = usuarioRepository.existsUserByCorreo("noexiste@example.com");
        assertFalse(notExists);
    }
}