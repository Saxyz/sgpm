package edu.unimag.sgpm.control.service.impl;

import edu.unimag.sgpm.control.dto.ReservaDto;
import edu.unimag.sgpm.control.mapper.ReservaMapper;
import edu.unimag.sgpm.model.entity.Espacio;
import edu.unimag.sgpm.model.entity.EstadoDeReserva;
import edu.unimag.sgpm.model.entity.Reserva;
import edu.unimag.sgpm.model.entity.Usuario;
import edu.unimag.sgpm.model.repository.EstadoReservaRepository;
import edu.unimag.sgpm.model.repository.UsuarioRepository;
import edu.unimag.sgpm.model.repository.EspacioRepository;
import edu.unimag.sgpm.model.repository.ReservaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservaServiceImplTest {

    @Mock
    private EstadoReservaRepository estadoReservaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private EspacioRepository espacioRepository;

    @Mock
    private ReservaMapper reservaMapper;

    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private ReservaServiceImpl reservaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createReserva_Success() {
        ReservaDto dto = new ReservaDto(1, 2, 3, 1, LocalDateTime.now());
        EstadoDeReserva estado = new EstadoDeReserva();
        Usuario usuario = new Usuario();
        Espacio espacio = new Espacio();
        Reserva reserva = new Reserva();
        Reserva saved = new Reserva();
        ReservaDto expected = new ReservaDto(1, 2, 3, 1, dto.fecha());

        when(estadoReservaRepository.findById(2)).thenReturn(Optional.of(estado));
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario)); // id() devuelve el ID del DTO (posiblemente incorrecto en tu código)
        when(espacioRepository.findById(1)).thenReturn(Optional.of(espacio));
        when(reservaMapper.reservaDtoToReserva(dto)).thenReturn(reserva);
        when(reservaRepository.save(reserva)).thenReturn(saved);
        when(reservaMapper.reservaToDto(saved)).thenReturn(expected);

        ReservaDto result = reservaService.createReserva(dto);

        assertEquals(expected, result);
        verify(reservaRepository).save(reserva);
    }

    @Test
    void findReservaById_Success() {
        Reserva reserva = new Reserva();
        ReservaDto dto = new ReservaDto(1, 2, 3, 1, LocalDateTime.now());

        when(reservaRepository.findById(1)).thenReturn(Optional.of(reserva));
        when(reservaMapper.reservaToDto(reserva)).thenReturn(dto);

        ReservaDto result = reservaService.findReservaById(1);

        assertEquals(dto, result);
    }

    @Test
    void findReservaById_NotFound_Throws() {
        when(reservaRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> reservaService.findReservaById(1));

        assertEquals("Reserva no encontrada", ex.getMessage());
    }

    @Test
    void findAllReservas_Success() {
        Reserva r1 = new Reserva();
        Reserva r2 = new Reserva();
        ReservaDto dto1 = new ReservaDto(1, 1, 1, 1, LocalDateTime.now());
        ReservaDto dto2 = new ReservaDto(2, 2, 2, 1, LocalDateTime.now());

        when(reservaRepository.findAll()).thenReturn(List.of(r1, r2));
        when(reservaMapper.reservaListToDtoList(List.of(r1, r2))).thenReturn(List.of(dto1, dto2));

        List<ReservaDto> result = reservaService.findAllReservas();

        assertEquals(2, result.size());
        assertTrue(result.contains(dto1));
        assertTrue(result.contains(dto2));
    }

    @Test
    void updateReservaById_Success() {
        ReservaDto dto = new ReservaDto(1, 2, 3, 1, LocalDateTime.now());
        Reserva reserva = new Reserva();
        EstadoDeReserva estado = new EstadoDeReserva();
        Usuario usuario = new Usuario();
        Espacio espacio = new Espacio();
        Reserva saved = new Reserva();
        ReservaDto expected = new ReservaDto(1, 2, 3, 1, dto.fecha());

        when(reservaRepository.findById(1)).thenReturn(Optional.of(reserva));
        when(espacioRepository.findById(1)).thenReturn(Optional.of(espacio));
        when(usuarioRepository.findById(3)).thenReturn(Optional.of(usuario));
        when(estadoReservaRepository.findById(2)).thenReturn(Optional.of(estado));
        when(reservaRepository.save(reserva)).thenReturn(saved);
        when(reservaMapper.reservaToDto(saved)).thenReturn(expected);

        ReservaDto result = reservaService.updateReservaById(1, dto);

        assertEquals(expected, result);
        verify(reservaRepository).save(reserva);
    }

    @Test
    void updateReservaById_NotFound_Throws() {
        ReservaDto dto = new ReservaDto(1, 2, 3, 1, LocalDateTime.now());

        when(reservaRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> reservaService.updateReservaById(1, dto));

        assertEquals("Reserva no encontrada", ex.getMessage());
    }

    @Test
    void deleteReservaById_Success() {
        doNothing().when(reservaRepository).deleteById(1);

        reservaService.deleteReservaById(1);

        verify(reservaRepository).deleteById(1);
    }
}
