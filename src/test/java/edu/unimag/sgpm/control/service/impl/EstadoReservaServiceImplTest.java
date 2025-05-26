package edu.unimag.sgpm.control.service.impl;

import edu.unimag.sgpm.control.dto.EstadoReservaDto;
import edu.unimag.sgpm.control.mapper.EstadoReservaMapper;
import edu.unimag.sgpm.model.entity.EstadoDeReserva;
import edu.unimag.sgpm.model.repository.EstadoReservaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EstadoReservaServiceImplTest {

    @Mock
    private EstadoReservaMapper estadoReservaMapper;

    @Mock
    private EstadoReservaRepository estadoReservaRepository;

    @InjectMocks
    private EstadoReservaServiceImpl estadoReservaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createEstadoReserva_Success() {
        EstadoReservaDto dto = new EstadoReservaDto(1, "Pendiente");
        EstadoDeReserva entity = new EstadoDeReserva();
        EstadoReservaDto expectedDto = new EstadoReservaDto(1, "Pendiente");

        when(estadoReservaMapper.toEntity(dto)).thenReturn(entity);
        when(estadoReservaMapper.toDto(entity)).thenReturn(expectedDto);

        EstadoReservaDto result = estadoReservaService.createEstadoReserva(dto);

        assertEquals(expectedDto, result);
        assertEquals("Pendiente", entity.getNombre());
    }

    @Test
    void findEstadoReservaById_Success() {
        EstadoDeReserva entity = new EstadoDeReserva();
        EstadoReservaDto expectedDto = new EstadoReservaDto(1, "Aprobado");

        when(estadoReservaRepository.findById(1)).thenReturn(Optional.of(entity));
        when(estadoReservaMapper.toDto(entity)).thenReturn(expectedDto);

        EstadoReservaDto result = estadoReservaService.findEstadoReservaById(1);

        assertEquals(expectedDto, result);
    }

    @Test
    void findEstadoReservaById_NotFound_Throws() {
        when(estadoReservaRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                estadoReservaService.findEstadoReservaById(1));

        assertEquals("Estado no encontrado", ex.getMessage());
    }

    @Test
    void findAllEstadoReserva_Success() {
        EstadoDeReserva e1 = new EstadoDeReserva();
        EstadoDeReserva e2 = new EstadoDeReserva();
        EstadoReservaDto dto1 = new EstadoReservaDto(1, "Pendiente");
        EstadoReservaDto dto2 = new EstadoReservaDto(2, "Cancelada");

        when(estadoReservaRepository.findAll()).thenReturn(List.of(e1, e2));
        when(estadoReservaMapper.toDtos(List.of(e1, e2))).thenReturn(List.of(dto1, dto2));

        List<EstadoReservaDto> result = estadoReservaService.findAllEstadoReserva();

        assertEquals(2, result.size());
        assertTrue(result.contains(dto1));
        assertTrue(result.contains(dto2));
    }

    @Test
    void updateEstadoReserva_Success() {
        EstadoReservaDto inputDto = new EstadoReservaDto(1, "Finalizada");
        EstadoDeReserva entity = new EstadoDeReserva();
        EstadoReservaDto expectedDto = new EstadoReservaDto(1, "Finalizada");

        when(estadoReservaRepository.findById(1)).thenReturn(Optional.of(entity));
        when(estadoReservaRepository.save(entity)).thenReturn(entity);
        when(estadoReservaMapper.toDto(entity)).thenReturn(expectedDto);

        EstadoReservaDto result = estadoReservaService.updateEstadoReserva(1, inputDto);

        assertEquals(expectedDto, result);
        assertEquals("Finalizada", entity.getNombre());
    }

    @Test
    void updateEstadoReserva_NotFound_Throws() {
        EstadoReservaDto dto = new EstadoReservaDto(1, "Inexistente");

        when(estadoReservaRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                estadoReservaService.updateEstadoReserva(1, dto));

        assertEquals("Estado no encontrado", ex.getMessage());
    }

    @Test
    void deleteEstadoReserva_Success() {
        doNothing().when(estadoReservaRepository).deleteById(1);

        estadoReservaService.deleteEstadoReserva(1);

        verify(estadoReservaRepository).deleteById(1);
    }
}
