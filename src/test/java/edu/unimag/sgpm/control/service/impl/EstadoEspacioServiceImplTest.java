package edu.unimag.sgpm.control.service.impl;

import edu.unimag.sgpm.control.dto.EstadoEspacioDto;
import edu.unimag.sgpm.control.mapper.EstadoEspacioMapper;
import edu.unimag.sgpm.model.entity.EstadoDeEspacio;
import edu.unimag.sgpm.model.repository.EstadoEspacioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EstadoEspacioServiceImplTest {

    @Mock
    private EstadoEspacioRepository estadoEspacioRepository;

    @Mock
    private EstadoEspacioMapper estadoEspacioMapper;

    @InjectMocks
    private EstadoEspacioServiceImpl estadoEspacioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createEspacio_Success() {
        EstadoEspacioDto dto = new EstadoEspacioDto(null, "Disponible");
        EstadoDeEspacio entity = new EstadoDeEspacio();
        EstadoDeEspacio saved = new EstadoDeEspacio();
        saved.setNombre("Disponible");
        EstadoEspacioDto expectedDto = new EstadoEspacioDto(1, "Disponible");

        when(estadoEspacioMapper.toEntity(dto)).thenReturn(entity);
        when(estadoEspacioRepository.save(entity)).thenReturn(saved);
        when(estadoEspacioMapper.toDto(saved)).thenReturn(expectedDto);

        EstadoEspacioDto result = estadoEspacioService.createEspacio(dto);

        assertEquals(expectedDto, result);
        verify(estadoEspacioRepository).save(entity);
    }

    @Test
    void findEstadoEspacioById_Success() {
        EstadoDeEspacio entity = new EstadoDeEspacio();
        EstadoEspacioDto expectedDto = new EstadoEspacioDto(1, "Ocupado");

        when(estadoEspacioRepository.findById(1)).thenReturn(Optional.of(entity));
        when(estadoEspacioMapper.toDto(entity)).thenReturn(expectedDto);

        EstadoEspacioDto result = estadoEspacioService.findEstadoEspacioById(1);

        assertEquals(expectedDto, result);
    }

    @Test
    void findEstadoEspacioById_NotFound_Throws() {
        when(estadoEspacioRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> estadoEspacioService.findEstadoEspacioById(1));

        assertEquals("Estado no encontrado", ex.getMessage());
    }

    @Test
    void findAllEstadoEspacios_Success() {
        EstadoDeEspacio e1 = new EstadoDeEspacio();
        EstadoDeEspacio e2 = new EstadoDeEspacio();
        EstadoEspacioDto dto1 = new EstadoEspacioDto(1, "Libre");
        EstadoEspacioDto dto2 = new EstadoEspacioDto(2, "Reservado");

        when(estadoEspacioRepository.findAll()).thenReturn(List.of(e1, e2));
        when(estadoEspacioMapper.toDtos(List.of(e1, e2))).thenReturn(List.of(dto1, dto2));

        List<EstadoEspacioDto> result = estadoEspacioService.findAllEstadoEspacios();

        assertEquals(2, result.size());
        assertTrue(result.contains(dto1));
        assertTrue(result.contains(dto2));
    }

    @Test
    void updateEstadoEspacio_Success() {
        EstadoEspacioDto dto = new EstadoEspacioDto(1, "Reservado");
        EstadoDeEspacio existing = new EstadoDeEspacio();
        EstadoDeEspacio updated = new EstadoDeEspacio();
        updated.setNombre("Reservado");
        EstadoEspacioDto expectedDto = new EstadoEspacioDto(1, "Reservado");

        when(estadoEspacioRepository.findById(1)).thenReturn(Optional.of(existing));
        when(estadoEspacioRepository.save(existing)).thenReturn(updated);
        when(estadoEspacioMapper.toDto(updated)).thenReturn(expectedDto);

        EstadoEspacioDto result = estadoEspacioService.updateEstadoEspacio(1, dto);

        assertEquals(expectedDto, result);
        assertEquals("Reservado", existing.getNombre());
    }

    @Test
    void updateEstadoEspacio_NotFound_Throws() {
        EstadoEspacioDto dto = new EstadoEspacioDto(1, "Reservado");

        when(estadoEspacioRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> estadoEspacioService.updateEstadoEspacio(1, dto));

        assertEquals("Estado no encontrado", ex.getMessage());
    }

    @Test
    void deleteEstadoEspacio_Success() {
        doNothing().when(estadoEspacioRepository).deleteById(1);

        estadoEspacioService.deleteEstadoEspacio(1);

        verify(estadoEspacioRepository).deleteById(1);
    }
}
