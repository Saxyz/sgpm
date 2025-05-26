package edu.unimag.sgpm.control.service.impl;

import edu.unimag.sgpm.control.dto.SancionDto;
import edu.unimag.sgpm.control.mapper.SancionMapper;
import edu.unimag.sgpm.model.entity.Administrador;
import edu.unimag.sgpm.model.entity.Sancion;
import edu.unimag.sgpm.model.entity.Usuario;
import edu.unimag.sgpm.model.repository.AdministradorRepository;
import edu.unimag.sgpm.model.repository.SancionRepository;
import edu.unimag.sgpm.model.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SancionServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private AdministradorRepository administradorRepository;

    @Mock
    private SancionMapper sancionMapper;

    @Mock
    private SancionRepository sancionRepository;

    @InjectMocks
    private SancionServiceImpl sancionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createSancion_Success() {
        SancionDto dto = new SancionDto(1, 2, 3, LocalDate.now(), "Descripción de prueba");
        Usuario usuario = new Usuario();
        Administrador admin = new Administrador();
        Sancion sancion = new Sancion();
        Sancion saved = new Sancion();
        SancionDto expected = dto;

        when(usuarioRepository.findById(2)).thenReturn(Optional.of(usuario));
        when(administradorRepository.findById(3)).thenReturn(Optional.of(admin));
        when(sancionMapper.toSancion(dto)).thenReturn(sancion);
        when(sancionRepository.save(sancion)).thenReturn(saved);
        when(sancionMapper.toSancionDto(saved)).thenReturn(expected);

        SancionDto result = sancionService.createSancion(dto);

        assertEquals(expected, result);
        verify(sancionRepository).save(sancion);
    }

    @Test
    void findSancionById_Success() {
        Sancion sancion = new Sancion();
        SancionDto dto = new SancionDto(1, 2, 3, LocalDate.now(), "Motivo");

        when(sancionRepository.findById(1)).thenReturn(Optional.of(sancion));
        when(sancionMapper.toSancionDto(sancion)).thenReturn(dto);

        SancionDto result = sancionService.findSancionById(1);

        assertEquals(dto, result);
    }

    @Test
    void findSancionById_NotFound_Throws() {
        when(sancionRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> sancionService.findSancionById(1));

        assertEquals("Sancion no encontrada", ex.getMessage());
    }

    @Test
    void findAllSancion_Success() {
        Sancion s1 = new Sancion();
        Sancion s2 = new Sancion();
        SancionDto d1 = new SancionDto(1, 2, 3, LocalDate.now(), "desc1");
        SancionDto d2 = new SancionDto(2, 3, 4, LocalDate.now(), "desc2");

        when(sancionRepository.findAll()).thenReturn(List.of(s1, s2));
        when(sancionMapper.toSancionDtos(List.of(s1, s2))).thenReturn(List.of(d1, d2));

        List<SancionDto> result = sancionService.findAllSancion();

        assertEquals(2, result.size());
        assertTrue(result.contains(d1));
        assertTrue(result.contains(d2));
    }

    @Test
    void updateSancionById_Success() {
        SancionDto dto = new SancionDto(1, 2, 3, LocalDate.now(), "Actualizada");
        Sancion sancion = new Sancion();
        Usuario usuario = new Usuario();
        Administrador admin = new Administrador();
        Sancion saved = new Sancion();
        SancionDto expected = dto;

        when(sancionRepository.findById(1)).thenReturn(Optional.of(sancion));
        when(usuarioRepository.findById(2)).thenReturn(Optional.of(usuario));
        when(administradorRepository.findById(3)).thenReturn(Optional.of(admin));
        when(sancionRepository.save(sancion)).thenReturn(saved);
        when(sancionMapper.toSancionDto(saved)).thenReturn(expected);

        SancionDto result = sancionService.updateSancionById(1, dto);

        assertEquals(expected, result);
        verify(sancionRepository).save(sancion);
    }

    @Test
    void updateSancionById_NotFound_Throws() {
        SancionDto dto = new SancionDto(1, 2, 3, LocalDate.now(), "Intento fallido");

        when(sancionRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> sancionService.updateSancionById(1, dto));

        assertEquals("Sancion no encontrado", ex.getMessage());
    }

    @Test
    void deleteSancionById_Success() {
        doNothing().when(sancionRepository).deleteById(1);

        sancionService.deleteSancionById(1);

        verify(sancionRepository).deleteById(1);
    }
}
