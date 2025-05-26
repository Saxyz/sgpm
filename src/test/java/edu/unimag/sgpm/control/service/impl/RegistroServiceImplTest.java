package edu.unimag.sgpm.control.service.impl;

import edu.unimag.sgpm.control.dto.RegistroDto;
import edu.unimag.sgpm.control.mapper.RegistroMapper;
import edu.unimag.sgpm.model.entity.Espacio;
import edu.unimag.sgpm.model.entity.Moto;
import edu.unimag.sgpm.model.entity.Registro;
import edu.unimag.sgpm.model.entity.Vigilante;
import edu.unimag.sgpm.model.repository.EspacioRepository;
import edu.unimag.sgpm.model.repository.MotoRepository;
import edu.unimag.sgpm.model.repository.RegistroRepository;
import edu.unimag.sgpm.model.repository.VigilanteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegistroServiceImplTest {

    @Mock
    private EspacioRepository espacioRepository;

    @Mock
    private MotoRepository motoRepository;

    @Mock
    private VigilanteRepository vigilanteRepository;

    @Mock
    private RegistroMapper registroMapper;

    @Mock
    private RegistroRepository registroRepository;

    @InjectMocks
    private RegistroServiceImpl registroService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createRegistro_Success() {
        RegistroDto dto = new RegistroDto(1, "ESP001", "MOTO123", 5, LocalDate.now(), LocalTime.of(8, 0), null);
        Espacio espacio = new Espacio();
        Moto moto = new Moto();
        Vigilante vigilante = new Vigilante();
        Registro entity = new Registro();
        Registro saved = new Registro();
        RegistroDto expected = new RegistroDto(1, "ESP001", "MOTO123", 5, LocalDate.now(), LocalTime.of(8, 0), null);

        when(espacioRepository.findById("ESP001")).thenReturn(Optional.of(espacio));
        when(motoRepository.findById("MOTO123")).thenReturn(Optional.of(moto));
        when(vigilanteRepository.findById(5)).thenReturn(Optional.of(vigilante));
        when(registroMapper.toRegistro(dto)).thenReturn(entity);
        when(registroRepository.save(entity)).thenReturn(saved);
        when(registroMapper.toDto(saved)).thenReturn(expected);

        RegistroDto result = registroService.createRegistro(dto);

        assertEquals(expected, result);
        verify(registroRepository).save(entity);
    }

    @Test
    void findRegistroById_Success() {
        Registro entity = new Registro();
        RegistroDto dto = new RegistroDto(1, "ESP001", "MOTO123", 5, LocalDate.now(), LocalTime.of(8, 0), null);

        when(registroRepository.findById(1)).thenReturn(Optional.of(entity));
        when(registroMapper.toDto(entity)).thenReturn(dto);

        RegistroDto result = registroService.findRegistroById(1);

        assertEquals(dto, result);
    }

    @Test
    void findRegistroById_NotFound_Throws() {
        when(registroRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> registroService.findRegistroById(1));

        assertEquals("Registro no encontrado", ex.getMessage());
    }

    @Test
    void findAllRegistros_Success() {
        Registro r1 = new Registro();
        Registro r2 = new Registro();
        RegistroDto dto1 = new RegistroDto(1, "E1", "M1", 1, LocalDate.now(), LocalTime.of(9, 0), null);
        RegistroDto dto2 = new RegistroDto(2, "E2", "M2", 2, LocalDate.now(), LocalTime.of(10, 0), null);

        when(registroRepository.findAll()).thenReturn(List.of(r1, r2));
        when(registroMapper.toDtos(List.of(r1, r2))).thenReturn(List.of(dto1, dto2));

        List<RegistroDto> result = registroService.findAllRegistros();

        assertEquals(2, result.size());
        assertTrue(result.contains(dto1));
        assertTrue(result.contains(dto2));
    }

    @Test
    void updateRegistroById_Success() {
        RegistroDto dto = new RegistroDto(1, "ESP001", "MOTO123", 5, LocalDate.now(), LocalTime.of(8, 0), LocalTime.of(17, 0));
        Registro entity = new Registro();
        Moto moto = new Moto();
        Espacio espacio = new Espacio();
        Vigilante vigilante = new Vigilante();
        Registro saved = new Registro();
        RegistroDto expected = new RegistroDto(1, "ESP001", "MOTO123", 5, LocalDate.now(), LocalTime.of(8, 0), LocalTime.of(17, 0));

        when(registroRepository.findById(1)).thenReturn(Optional.of(entity));
        when(motoRepository.findById("MOTO123")).thenReturn(Optional.of(moto));
        when(espacioRepository.findById("ESP001")).thenReturn(Optional.of(espacio));
        when(vigilanteRepository.findById(5)).thenReturn(Optional.of(vigilante));
        when(registroRepository.save(entity)).thenReturn(saved);
        when(registroMapper.toDto(saved)).thenReturn(expected);

        RegistroDto result = registroService.updateRegistroById(1, dto);

        assertEquals(expected, result);
        verify(registroRepository).save(entity);
    }

    @Test
    void updateRegistroById_NotFound_Throws() {
        RegistroDto dto = new RegistroDto(1, "ESP001", "MOTO123", 5, LocalDate.now(), LocalTime.of(8, 0), null);
        when(registroRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                registroService.updateRegistroById(1, dto));

        assertEquals("Registro no encontrado", ex.getMessage());
    }

    @Test
    void deleteRegistroById_Success() {
        doNothing().when(registroRepository).deleteById(1);

        registroService.deleteRegistroById(1);

        verify(registroRepository).deleteById(1);
    }
}
