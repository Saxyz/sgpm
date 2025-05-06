package edu.unimag.sgpm.control.service.impl;

import edu.unimag.sgpm.control.dto.estadoReserva.EstadoReservaDto;
import edu.unimag.sgpm.control.mapper.EstadoReservaMapper;
import edu.unimag.sgpm.control.service.EstadoReservaService;
import edu.unimag.sgpm.model.entity.EstadoDeReserva;
import edu.unimag.sgpm.model.repository.EstadoReservaRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoReservaServiceImpl implements EstadoReservaService {
    private final EstadoReservaMapper estadoReservaMapper;
    private final EstadoReservaRepository estadoReservaRepository;

    @Override
    public EstadoReservaDto createEstadoReserva(EstadoReservaDto estado) {
        EstadoDeReserva estadoDeReserva = estadoReservaMapper.toEntity(estado);
        estadoDeReserva.setNombre(estado.nombre());
        return estadoReservaMapper.toDto(estadoDeReserva);
    }

    @Override
    public EstadoReservaDto findEstadoReservaById(Integer id) {
        return estadoReservaMapper.toDto(estadoReservaRepository.findById(id).orElseThrow(() -> new RuntimeException("Estado no encontrado")));
    }

    @Override
    public List<EstadoReservaDto> findAllEstadoReserva() {
        return estadoReservaMapper.toDtos(estadoReservaRepository.findAll());
    }

    @Override
    public EstadoReservaDto updateEstadoReserva(Integer id, @NotNull EstadoReservaDto estado) {
        EstadoDeReserva estadoDeReserva = estadoReservaRepository.findById(id).orElseThrow(() -> new RuntimeException("Estado no encontrado"));
        estadoDeReserva.setNombre(estado.nombre());
        return estadoReservaMapper.toDto(estadoReservaRepository.save(estadoDeReserva));
    }

    @Override
    public void deleteEstadoReserva(Integer id) {
        estadoReservaRepository.deleteById(id);
    }
}
