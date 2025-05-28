package edu.unimag.sgpm.model.repository;

import edu.unimag.sgpm.ContainerConfig;
import edu.unimag.sgpm.model.entity.Moto;
import edu.unimag.sgpm.model.entity.Parqueadero;
import edu.unimag.sgpm.model.entity.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Import(ContainerConfig.class)
@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MotoRepositoryTest {

    @Autowired
    private MotoRepository motoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ParqueaderoRepository parqueaderoRepository;

    private Usuario usuario1;
    private Parqueadero parqueadero1;

    @BeforeEach
    void setup() {
        motoRepository.deleteAll();
        usuarioRepository.deleteAll();
        parqueaderoRepository.deleteAll();

        parqueadero1 = Parqueadero.builder()
                .nombre("Parqueadero Central")
                .imagen("central.jpg")
                .build();
        parqueadero1 = parqueaderoRepository.save(parqueadero1);

        usuario1 = Usuario.builder()
                .nombre("Ana")
                .apellido("Gómez")
                .correo("ana@correo.com")
                .contrasenia("1234")
                .parqueadero(parqueadero1)
                .build();
        usuario1 = usuarioRepository.save(usuario1);

        Moto moto1 = Moto.builder()
                .idMoto(1)
                .modelo("Honda CB190")
                .placa("ATX-106")
                .descripcion("Moto deportiva")
                .imagen("honda.jpg")
                .usuario(usuario1)
                .parqueadero(parqueadero1)
                .build();

        Moto moto2 = Moto.builder()
                .idMoto(2)
                .modelo("Yamaha FZ")
                .placa("ATX-107")
                .descripcion("Moto urbana")
                .imagen("yamaha.jpg")
                .usuario(usuario1)
                .parqueadero(parqueadero1)
                .build();

        motoRepository.save(moto1);
        motoRepository.save(moto2);
    }

    @Test
    void findByIdMoto() {
        Integer idMoto = 1;

        Optional<Moto> motoOpt = motoRepository.findById(idMoto);

        assertTrue(motoOpt.isPresent(), "La moto debería existir");
        Moto moto = motoOpt.get();
        assertEquals("Honda CB190", moto.getModelo());
        assertEquals("Moto deportiva", moto.getDescripcion());
        assertEquals("honda.jpg", moto.getImagen());
        assertEquals(usuario1.getIdUsuario(), moto.getUsuario().getIdUsuario());
    }

    @Test
    void findByUsuarioIdUsuario() {
        Integer idUsuario = usuario1.getIdUsuario();

        List<Moto> motos = motoRepository.findByUsuarioIdUsuario(idUsuario);

        assertEquals(2, motos.size(), "El usuario 1 debería tener 2 motos");

        List<String> modelosEsperados = List.of("Honda CB190", "Yamaha FZ");
        List<String> modelosEncontrados = motos.stream().map(Moto::getModelo).toList();

        assertTrue(modelosEncontrados.containsAll(modelosEsperados));
    }
}
