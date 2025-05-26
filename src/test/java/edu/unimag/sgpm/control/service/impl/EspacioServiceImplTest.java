package edu.unimag.sgpm.control.service.impl;

import edu.unimag.sgpm.control.dto.EspacioDto;
import edu.unimag.sgpm.control.mapper.EspacioMapper;
import edu.unimag.sgpm.model.entity.Espacio;
import edu.unimag.sgpm.model.entity.EstadoDeEspacio;
import edu.unimag.sgpm.model.entity.Parqueadero;
import edu.unimag.sgpm.model.repository.EspacioRepository;
import edu.unimag.sgpm.model.repository.EstadoEspacioRepository;
import edu.unimag.sgpm.model.repository.ParqueaderoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EspacioServiceImplTest {

    @Mock
    private ParqueaderoRepository parqueaderoRepository;

    @Mock
    private EstadoEspacioRepository estadoEspacioRepository;

    @Mock
    private EspacioRepository espacioRepository;

    @Mock
    private EspacioMapper espacioMapper;

    @InjectMocks
    private EspacioServiceImpl espacioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createEspacio_Success() {
        EspacioDto dto = new EspacioDto("A1", 1, 2);
        Parqueadero parqueadero = new Parqueadero();
        EstadoDeEspacio estado = new EstadoDeEspacio();
        Espacio entity = new Espacio();
        Espacio saved = new Espacio();
        EspacioDto expectedDto = new EspacioDto("A1", 1, 2);

        when(parqueaderoRepository.findById(1)).thenReturn(Optional.of(parqueadero));
        when(estadoEspacioRepository.findById(2)).thenReturn(Optional.of(estado));
        when(espacioMapper.toEntity(dto)).thenReturn(entity);
        when(espacioRepository.save(entity)).thenReturn(saved);
        when(espacioMapper.toDto(saved)).thenReturn(expectedDto);

        EspacioDto result = espacioService.createEspacio(dto);

        assertEquals(expectedDto, result);
        verify(espacioRepository).save(entity);
    }

    @Test
    void createEspacio_ParqueaderoNotFound_Throws() {
        EspacioDto dto = new EspacioDto("A1", 1, 2);
        when(parqueaderoRepository.findById(1)).thenReturn(Optional.empty());

        Exception ex = assertThrows(RuntimeException.class, () -> espacioService.createEspacio(dto));
        assertEquals("Parqueadero no encontrado", ex.getMessage());
    }

    @Test
    void createEspacio_EstadoNotFound_Throws() {
        EspacioDto dto = new EspacioDto("A1", 1, 2);
        when(parqueaderoRepository.findById(1)).thenReturn(Optional.of(new Parqueadero()));
        when(estadoEspacioRepository.findById(2)).thenReturn(Optional.empty());

        Exception ex = assertThrows(RuntimeException.class, () -> espacioService.createEspacio(dto));
        assertEquals("Estado no encontrado", ex.getMessage());
    }

    @Test
    void findEspacioById_Success() {
        Espacio espacio = new Espacio();
        EspacioDto expectedDto = new EspacioDto("A1", 1, 2);

        when(espacioRepository.findById("A1")).thenReturn(Optional.of(espacio));
        when(espacioMapper.toDto(espacio)).thenReturn(expectedDto);

        EspacioDto result = espacioService.findEspacioById("A1");

        assertEquals(expectedDto, result);
    }

    @Test
    void findEspacioById_NotFound_Throws() {
        when(espacioRepository.findById("A1")).thenReturn(Optional.empty());

        Exception ex = assertThrows(RuntimeException.class,
                () -> espacioService.findEspacioById("A1"));

        assertEquals("Espacio no encontrado", ex.getMessage());
    }

    @Test
    void findAllEspacios_Success() {
        Espacio e1 = new Espacio();
        Espacio e2 = new Espacio();
        EspacioDto dto1 = new EspacioDto("A1", 1, 1);
        EspacioDto dto2 = new EspacioDto("A2", 1, 2);

        when(espacioRepository.findAll()).thenReturn(List.of(e1, e2));
        when(espacioMapper.toDtos(List.of(e1, e2))).thenReturn(List.of(dto1, dto2));

        List<EspacioDto> result = espacioService.findAllEspacios();

        assertEquals(2, result.size());
        assertTrue(result.contains(dto1));
        assertTrue(result.contains(dto2));
    }

    @Test
    void updateEspacio_Success() {
        EspacioDto dto = new EspacioDto("A1", 1, 2);
        Espacio entity = new Espacio();
        EstadoDeEspacio estado = new EstadoDeEspacio();
        Parqueadero parqueadero = new Parqueadero();
        EspacioDto expectedDto = new EspacioDto("A1", 1, 2);

        when(espacioRepository.findById("A1")).thenReturn(Optional.of(entity));
        when(estadoEspacioRepository.findById(2)).thenReturn(Optional.of(estado));
        when(parqueaderoRepository.findById(1)).thenReturn(Optional.of(parqueadero));
        when(espacioRepository.save(entity)).thenReturn(entity);
        when(espacioMapper.toDto(entity)).thenReturn(expectedDto);

        EspacioDto result = espacioService.updateEspacio("A1", dto);

        assertEquals(expectedDto, result);
        assertSame(estado, entity.getEstado());
        assertSame(parqueadero, entity.getParqueadero());
    }

    @Test
    void updateEspacio_NotFound_Throws() {
        EspacioDto dto = new EspacioDto("A1", 1, 2);

        when(espacioRepository.findById("A1")).thenReturn(Optional.empty());

        Exception ex = assertThrows(RuntimeException.class, () -> espacioService.updateEspacio("A1", dto));
        assertEquals("Espacio no encontrado", ex.getMessage());
    }

    @Test
    void deleteEspacioById_Success() {
        doNothing().when(espacioRepository).deleteById("A1");

        espacioService.deleteEspacioById("A1");

        verify(espacioRepository).deleteById("A1");
    }
}
