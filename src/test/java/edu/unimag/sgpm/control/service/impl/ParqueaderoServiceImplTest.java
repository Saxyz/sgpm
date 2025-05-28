package edu.unimag.sgpm.control.service.impl;

import edu.unimag.sgpm.control.dto.ParqueaderoDto;
import edu.unimag.sgpm.control.mapper.ParqueaderoMapper;
import edu.unimag.sgpm.model.entity.Parqueadero;
import edu.unimag.sgpm.model.repository.ParqueaderoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParqueaderoServiceImplTest {

    @Mock
    private ParqueaderoRepository parqueaderoRepository;

    @Mock
    private ParqueaderoMapper parqueaderoMapper;

    @InjectMocks
    private ParqueaderoServiceImpl parqueaderoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    MultipartFile mockImage = mock(MultipartFile.class);
    String placeholderRuta = "/default/path/to/image.jpg";


    @Test
    void createParqueadero_Success() {
        ParqueaderoDto request = new ParqueaderoDto(null, "Central", mockImage, placeholderRuta, List.of("A1", "A2"));
        Parqueadero entity = mock(Parqueadero.class);
        Parqueadero savedEntity = mock(Parqueadero.class);
        ParqueaderoDto expected = new ParqueaderoDto(1, "Central", mockImage, placeholderRuta, List.of("A1", "A2"));

        when(parqueaderoMapper.toEntity(request)).thenReturn(entity);
        when(parqueaderoRepository.save(entity)).thenReturn(savedEntity);
        when(parqueaderoMapper.toDto(savedEntity)).thenReturn(expected);

        ParqueaderoDto result = parqueaderoService.createParqueadero(request);

        assertEquals(expected, result);
        verify(parqueaderoRepository).save(entity);
    }

    @Test
    void findParqueaderoById_Success() {
        Parqueadero entity = mock(Parqueadero.class);
        ParqueaderoDto expected = new ParqueaderoDto(1, "Central", mockImage, placeholderRuta, List.of("A1", "A2"));

        when(parqueaderoRepository.findById(1)).thenReturn(Optional.of(entity));
        when(parqueaderoMapper.toDto(entity)).thenReturn(expected);

        ParqueaderoDto result = parqueaderoService.findParqueaderoById(1);

        assertEquals(expected, result);
    }

    @Test
    void findParqueaderoById_NotFound_Throws() {
        when(parqueaderoRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> parqueaderoService.findParqueaderoById(1));

        assertEquals("Parqueadero no encontrado", ex.getMessage());
    }

    @Test
    void findAllParqueaderos_ReturnsList() {
        Parqueadero p1 = mock(Parqueadero.class);
        Parqueadero p2 = mock(Parqueadero.class);
        ParqueaderoDto dto1 = new ParqueaderoDto(1, "Central", mockImage, placeholderRuta, List.of("A1"));
        ParqueaderoDto dto2 = new ParqueaderoDto(2, "Norte" , mockImage, placeholderRuta, List.of("B1"));

        when(parqueaderoRepository.findAll()).thenReturn(List.of(p1, p2));
        when(parqueaderoMapper.toDto(p1)).thenReturn(dto1);
        when(parqueaderoMapper.toDto(p2)).thenReturn(dto2);

        List<ParqueaderoDto> result = parqueaderoService.findAllParqueaderos();

        assertEquals(2, result.size());
        assertTrue(result.contains(dto1));
        assertTrue(result.contains(dto2));
    }

    @Test
    void updateParqueaderoById_Success() {
        ParqueaderoDto request = new ParqueaderoDto(1, "Central" , mockImage, placeholderRuta, List.of("A1", "A2"));
        Parqueadero entity = mock(Parqueadero.class);
        Parqueadero updatedEntity = mock(Parqueadero.class);
        ParqueaderoDto expected = new ParqueaderoDto(1, "Central" , mockImage, placeholderRuta, List.of("A1", "A2"));

        when(parqueaderoRepository.findById(1)).thenReturn(Optional.of(mock(Parqueadero.class)));
        when(parqueaderoMapper.toEntity(request)).thenReturn(entity);
        when(parqueaderoRepository.save(entity)).thenReturn(updatedEntity);
        when(parqueaderoMapper.toDto(updatedEntity)).thenReturn(expected);

        ParqueaderoDto result = parqueaderoService.updateParqueaderoById(1, request);

        assertEquals(expected, result);
    }

    @Test
    void updateParqueaderoById_NotFound_Throws() {
        ParqueaderoDto request = new ParqueaderoDto(1, "Central" , mockImage, placeholderRuta, List.of("A1", "A2"));
        when(parqueaderoRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> parqueaderoService.updateParqueaderoById(1, request));

        assertEquals("Parqueadero no encontrado", ex.getMessage());
    }

    @Test
    void deleteParqueaderoById_Success() {
        when(parqueaderoRepository.existsById(1)).thenReturn(true);
        doNothing().when(parqueaderoRepository).deleteById(1);

        parqueaderoService.deleteParqueaderoById(1);

        verify(parqueaderoRepository).deleteById(1);
    }

    @Test
    void deleteParqueaderoById_NotFound_Throws() {
        when(parqueaderoRepository.existsById(1)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> parqueaderoService.deleteParqueaderoById(1));

        assertEquals("Parqueadero no encontrado para eliminar", ex.getMessage());
    }
}
