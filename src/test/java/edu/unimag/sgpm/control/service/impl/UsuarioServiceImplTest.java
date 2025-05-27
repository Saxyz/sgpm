package edu.unimag.sgpm.control.service.impl;

import edu.unimag.sgpm.control.dto.UsuarioDto;
import edu.unimag.sgpm.control.mapper.UsuarioMapper;
import edu.unimag.sgpm.model.entity.Parqueadero;
import edu.unimag.sgpm.model.entity.Role;
import edu.unimag.sgpm.model.entity.Usuario;
import edu.unimag.sgpm.model.repository.ParqueaderoRepository;
import edu.unimag.sgpm.model.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ParqueaderoRepository parqueaderoRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @BeforeEach
    void setUp() {
            MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUsuario_Success() {
        UsuarioDto request = new UsuarioDto(null, 1, "John", "Doe", "john@example.com", Set.of(1));
        Usuario usuarioEntity = mock(Usuario.class);
        Parqueadero parqueadero = new Parqueadero();
        Usuario savedUsuario = mock(Usuario.class);
        UsuarioDto expectedDto = new UsuarioDto(1, 1, "John", "Doe", "john@example.com", Set.of(1));

        when(usuarioMapper.toEntity(request)).thenReturn(usuarioEntity);
        when(parqueaderoRepository.findById(1)).thenReturn(Optional.of(parqueadero));
        doNothing().when(usuarioEntity).setParqueadero(parqueadero);
        when(usuarioRepository.save(usuarioEntity)).thenReturn(savedUsuario);
        when(usuarioMapper.toDto(savedUsuario)).thenReturn(expectedDto);

        UsuarioDto result = usuarioService.createUsuario(request);

        assertEquals(expectedDto, result);
        verify(usuarioRepository).save(usuarioEntity);
    }

    @Test
    void createUsuario_ParqueaderoNotFound_Throws() {
        UsuarioDto request = new UsuarioDto(null, 1, "John", "Doe", "john@example.com",  Set.of(1));

        when(parqueaderoRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> usuarioService.createUsuario(request));
        assertEquals("Parqueadero no encontrado", ex.getMessage());
    }

    @Test
    void findUsuarioById_Success() {
        Usuario usuario = mock(Usuario.class);
        UsuarioDto expectedDto = new UsuarioDto(1, 1, "John", "Doe", "john@example.com",  Set.of(1));

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        when(usuarioMapper.toDto(usuario)).thenReturn(expectedDto);

        UsuarioDto result = usuarioService.findUsuarioById(1);

        assertEquals(expectedDto, result);
    }

    @Test
    void findUsuarioById_NotFound_Throws() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> usuarioService.findUsuarioById(1));
        assertEquals("Usuario no encontrado", ex.getMessage());
    }

    @Test
    void findUsuarioByRoles_Success() {
        Usuario usuario = mock(Usuario.class);
        UsuarioDto expectedDto = new UsuarioDto(1, 1, "John", "Doe", "john@example.com",  Set.of(1));
        Set<Role> roles = Set.of(new Role());

        when(usuarioRepository.findByRoles(roles)).thenReturn(Optional.of(usuario));
        when(usuarioMapper.toDto(usuario)).thenReturn(expectedDto);

        UsuarioDto result = usuarioService.findUsuarioByRoles(roles);

        assertEquals(expectedDto, result);
    }

    @Test
    void findUsuarioByRoles_NotFound_Throws() {
        Set<Role> roles = Set.of(new Role());
        when(usuarioRepository.findByRoles(roles)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> usuarioService.findUsuarioByRoles(roles));
        assertEquals("Usuario no encontrado", ex.getMessage());
    }

    @Test
    void findAllUsuarios_ReturnsList() {
        Usuario usuario1 = mock(Usuario.class);
        Usuario usuario2 = mock(Usuario.class);
        UsuarioDto dto1 = new UsuarioDto(1, 1, "John", "Doe", "john@example.com",  Set.of(1));
        UsuarioDto dto2 = new UsuarioDto(2, 2, "Jane", "Doe", "jane@example.com",  Set.of(2));

        when(usuarioRepository.findAll()).thenReturn(List.of(usuario1, usuario2));
        when(usuarioMapper.toDto(usuario1)).thenReturn(dto1);
        when(usuarioMapper.toDto(usuario2)).thenReturn(dto2);

        List<UsuarioDto> result = usuarioService.findAllUsuarios();

        assertEquals(2, result.size());
        assertTrue(result.contains(dto1));
        assertTrue(result.contains(dto2));
    }

    @Test
    void updateUsuarioById_Success() {
        UsuarioDto request = new UsuarioDto(1, 1, "John", "Doe", "john@example.com",  Set.of(1));
        Usuario usuarioEntity = mock(Usuario.class);
        Parqueadero parqueadero = new Parqueadero();
        Usuario updatedUsuario = mock(Usuario.class);
        UsuarioDto expectedDto = new UsuarioDto(1, 1, "John", "Doe", "john@example.com",  Set.of(1));

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuarioEntity));
        when(usuarioMapper.toEntity(request)).thenReturn(usuarioEntity);
        when(parqueaderoRepository.findById(1)).thenReturn(Optional.of(parqueadero));
        doNothing().when(usuarioEntity).setParqueadero(parqueadero);
        when(usuarioRepository.save(usuarioEntity)).thenReturn(updatedUsuario);
        when(usuarioMapper.toDto(updatedUsuario)).thenReturn(expectedDto);

        UsuarioDto result = usuarioService.updateUsuarioById(1, request);

        assertEquals(expectedDto, result);
    }

    @Test
    void updateUsuarioById_ParqueaderoNull_Success() {
        UsuarioDto request = new UsuarioDto(1, null, "John", "Doe", "john@example.com",  Set.of(1));
        Usuario usuarioEntity = mock(Usuario.class);
        Parqueadero parqueadero = new Parqueadero();
        Usuario updatedUsuario = mock(Usuario.class);
        UsuarioDto expectedDto = new UsuarioDto(1, 1, "John", "Doe", "john@example.com", Set.of(1));

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuarioEntity));
        when(usuarioMapper.toEntity(request)).thenReturn(usuarioEntity);
        when(parqueaderoRepository.findById(1)).thenReturn(Optional.of(parqueadero));
        doNothing().when(usuarioEntity).setParqueadero(parqueadero);
        when(usuarioRepository.save(usuarioEntity)).thenReturn(updatedUsuario);
        when(usuarioMapper.toDto(updatedUsuario)).thenReturn(expectedDto);

        UsuarioDto result = usuarioService.updateUsuarioById(1, request);

        assertEquals(expectedDto, result);
    }

    @Test
    void updateUsuarioById_ParqueaderoNotFound_Throws() {
        Usuario usuarioEntity = mock(Usuario.class);
        UsuarioDto request = new UsuarioDto(1, 1, "John", "Doe", "john@example.com",  Set.of(1));

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuarioEntity));
        when(usuarioMapper.toEntity(request)).thenReturn(usuarioEntity);
        when(parqueaderoRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> usuarioService.updateUsuarioById(1, request));
        assertEquals("Parqueadero no encontrado", ex.getMessage());
    }

    @Test
    void updateUsuarioById_UsuarioNotFound_Throws() {
        UsuarioDto request = new UsuarioDto(1, 1, "John", "Doe", "john@example.com",  Set.of(1));
        when(usuarioRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> usuarioService.updateUsuarioById(1, request));
        assertEquals("Usuario no encontrado", ex.getMessage());
    }

    @Test
    void deleteUsuarioById_Success() {
        Usuario usuario = mock(Usuario.class);

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        doNothing().when(usuarioRepository).delete(usuario);

        usuarioService.deleteUsuarioById(1);

        verify(usuarioRepository).delete(usuario);
    }

    @Test
    void deleteUsuarioById_NotFound_Throws() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> usuarioService.deleteUsuarioById(1));
        assertEquals("Usuario no encontrado", ex.getMessage());
    }
}
