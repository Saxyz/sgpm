package edu.unimag.sgpm.control.service.impl;

import edu.unimag.sgpm.control.dto.MotoDto;
import edu.unimag.sgpm.control.mapper.MotoMapper;
import edu.unimag.sgpm.model.entity.Moto;
import edu.unimag.sgpm.model.entity.Parqueadero;
import edu.unimag.sgpm.model.entity.Usuario;
import edu.unimag.sgpm.model.repository.MotoRepository;
import edu.unimag.sgpm.model.repository.ParqueaderoRepository;
import edu.unimag.sgpm.model.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MotoServiceImplTest {

    @Mock
    private MotoRepository motoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ParqueaderoRepository parqueaderoRepository;

    @Mock
    private MotoMapper motoMapper;

    @InjectMocks
    private MotoServiceImpl motoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createMoto_Success() {
        MotoDto request = new MotoDto(1,"M001", 1, 100, "Pulsar", "Negra",  "");
        Usuario usuario = new Usuario();
        Parqueadero parqueadero = new Parqueadero();
        Moto motoEntity = new Moto();
        Moto savedMoto = new Moto();
        MotoDto expectedDto = new MotoDto(1,"M001", 1, 100, "Pulsar", "Negra",  "");

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        when(parqueaderoRepository.findById(100)).thenReturn(Optional.of(parqueadero));
        when(motoMapper.toEntity(request)).thenReturn(motoEntity);
        when(motoRepository.save(motoEntity)).thenReturn(savedMoto);
        when(motoMapper.toDto(savedMoto)).thenReturn(expectedDto);

        MotoDto result = motoService.createMoto(request);

        assertEquals(expectedDto, result);
        verify(motoRepository).save(motoEntity);
    }

    @Test
    void findMotoById_Success() {
        Moto moto = new Moto();
        MotoDto dto = new MotoDto(1,"M001", 1, 100, "Pulsar", "Negra",  "");

        when(motoRepository.findById(1)).thenReturn(Optional.of(moto));
        when(motoMapper.toDto(moto)).thenReturn(dto);

        MotoDto result = motoService.findMotoById(1);

        assertEquals(dto, result);
    }

    @Test
    void findMotoById_NotFound_Throws() {
        when(motoRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                motoService.findMotoById(1));

        assertEquals("Moto no encontrada", ex.getMessage());
    }

    @Test
    void findAllMotos_Success() {
        Moto moto1 = new Moto();
        Moto moto2 = new Moto();
        MotoDto dto1 = new MotoDto(1,"M001", 1, 100, "Pulsar", "Negra",  "");
        MotoDto dto2 = new MotoDto(1,"M002", 2, 101, "Yamaha", "Azul",  "");

        when(motoRepository.findAll()).thenReturn(List.of(moto1, moto2));
        when(motoMapper.toDto(any())).thenReturn(dto1, dto2);

        List<MotoDto> result = motoService.findAllMotos();

        assertEquals(2, result.size());
        result.forEach(System.out::println);
        assertTrue(result.contains(dto1));
        assertTrue(result.contains(dto2));
    }

    @Test
    void updateMotoById_Success() {
        MotoDto request = new MotoDto(1,"M001", 1, 100, "Pulsar", "Negra actualizada",  "");
        Usuario usuario = new Usuario();
        Parqueadero parqueadero = new Parqueadero();
        Moto motoEntity = new Moto();
        Moto updatedMoto = new Moto();
        MotoDto expectedDto = new MotoDto(1,"M001", 1, 100, "Pulsar", "Negra actualizada",  "");

        when(motoRepository.findById(1)).thenReturn(Optional.of(new Moto()));
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        when(parqueaderoRepository.findById(100)).thenReturn(Optional.of(parqueadero));
        when(motoMapper.toEntity(request)).thenReturn(motoEntity);
        when(motoRepository.save(motoEntity)).thenReturn(updatedMoto);
        when(motoMapper.toDto(updatedMoto)).thenReturn(expectedDto);

        MotoDto result = motoService.updateMotoById(1, request);

        assertEquals(expectedDto, result);
        verify(motoRepository).save(motoEntity);
    }

    @Test
    void updateMotoById_NotFound_Throws() {
        MotoDto request = new MotoDto(1,"M001", 1, 100, "Pulsar", "Negra actualizada",  "");

        when(motoRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                motoService.updateMotoById(1, request));

        assertEquals("Moto no encontrada", ex.getMessage());
    }

    @Test
    void deleteMotoById_Success() {
        when(motoRepository.existsById(1)).thenReturn(true);
        doNothing().when(motoRepository).deleteById(1);

        motoService.deleteMotoById(1);

        verify(motoRepository).deleteById(1);
    }

    @Test
    void deleteMotoById_NotFound_Throws() {
        when(motoRepository.existsById(1)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                motoService.deleteMotoById(1));

        assertEquals("Moto no encontrada para eliminar", ex.getMessage());
    }
}
