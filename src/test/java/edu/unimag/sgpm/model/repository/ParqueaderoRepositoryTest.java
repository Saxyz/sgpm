package edu.unimag.sgpm.model.repository;

import edu.unimag.sgpm.ContainerConfig;
import edu.unimag.sgpm.model.entity.Parqueadero;
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
class ParqueaderoRepositoryTest {


    @Autowired
    private ParqueaderoRepository parqueaderoRepository;

    private Parqueadero parqueadero1;

    @BeforeEach
    void setup() {
        parqueaderoRepository.deleteAll();

        parqueadero1 = Parqueadero.builder()
                .nombre("Parqueadero Central")
                .imagen("central.jpg")
                .build();

        parqueadero1 = parqueaderoRepository.save(parqueadero1);
    }

    @Test
    void findByIdParqueadero() {
        Integer id = parqueadero1.getIdParqueadero();

        Optional<Parqueadero> parqueaderoOpt = parqueaderoRepository.findById(id);

        assertTrue(parqueaderoOpt.isPresent(), "El parqueadero debería existir");
        Parqueadero parqueadero = parqueaderoOpt.get();
        assertEquals("Parqueadero Central", parqueadero.getNombre());
        assertEquals("central.jpg", parqueadero.getImagen());
    }
}