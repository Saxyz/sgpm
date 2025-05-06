package edu.unimag.sgpm.control.service.impl;

import edu.unimag.sgpm.control.dto.estadoEspacio.EstadoEspacioDto;
import edu.unimag.sgpm.control.mapper.EstadoEspacioMapper;
import edu.unimag.sgpm.control.service.EstadoEspacioService;
import edu.unimag.sgpm.model.entity.EstadoDeEspacio;
import edu.unimag.sgpm.model.repository.EstadoEspacioRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoEspacioServiceImpl implements EstadoEspacioService {
    private final EstadoEspacioMapper estadoEspacioMapper;
    private final EstadoEspacioRepository estadoEspacioRepository;

    @Override
    public EstadoEspacioDto createEspacio(@NotNull EstadoEspacioDto estadoEspacioDto) {
        EstadoDeEspacio estado = estadoEspacioMapper.toEntity(estadoEspacioDto);
        estado.setNombre(estadoEspacioDto.nombre());
        return estadoEspacioMapper.toDto(estadoEspacioRepository.save(estado));
    }

    @Override
    public EstadoEspacioDto findEstadoEspacioById(Integer id) {
        return estadoEspacioMapper.toDto(estadoEspacioRepository.findById(id).orElseThrow(() -> new RuntimeException("Estado no encontrado")));
    }

    @Override
    public List<EstadoEspacioDto> findAllEstadoEspacios() {
        return estadoEspacioMapper.toDtos(estadoEspacioRepository.findAll());
    }

    @Override
    public EstadoEspacioDto updateEstadoEspacio(Integer id, EstadoEspacioDto estadoEspacioDto) {
        EstadoDeEspacio estado = estadoEspacioRepository.findById(id).orElseThrow(() -> new RuntimeException("Estado no encontrado"));
        estado.setNombre(estadoEspacioDto.nombre());
        return estadoEspacioMapper.toDto(estadoEspacioRepository.save(estado));
    }

    @Override
    public void deleteEstadoEspacio(Integer id) {
        estadoEspacioRepository.deleteById(id);
    }
}
