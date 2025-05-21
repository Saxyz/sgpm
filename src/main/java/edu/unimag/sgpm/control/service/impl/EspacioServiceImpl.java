package edu.unimag.sgpm.control.service.impl;

import edu.unimag.sgpm.control.dto.EspacioDto;
import edu.unimag.sgpm.control.mapper.EspacioMapperImpl;
import edu.unimag.sgpm.control.service.EspacioService;
import edu.unimag.sgpm.model.entity.Espacio;
import edu.unimag.sgpm.model.entity.EstadoDeEspacio;
import edu.unimag.sgpm.model.entity.Parqueadero;
import edu.unimag.sgpm.model.repository.EspacioRepository;
import edu.unimag.sgpm.model.repository.EstadoEspacioRepository;
import edu.unimag.sgpm.model.repository.ParqueaderoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EspacioServiceImpl implements EspacioService {
    private final ParqueaderoRepository parqueaderoRepository;
    private final EstadoEspacioRepository estadoEspacioRepository;
    private final EspacioMapperImpl espacioMapperImpl;
    private final EspacioRepository espacioRepository;

    @Override
    public EspacioDto createEspacio(EspacioDto espacioDto) {
        Parqueadero parqueadero = parqueaderoRepository.findById(espacioDto.parqueadero())
                .orElseThrow(() -> new RuntimeException("Parqueadero no encontrado"));
        EstadoDeEspacio estado = estadoEspacioRepository.findById(espacioDto.estado())
                .orElseThrow(() ->new RuntimeException("Estado no encontrado"));
        Espacio espacio = espacioMapperImpl.toEntity(espacioDto);
        espacio.setEstado(estado);
        espacio.setParqueadero(parqueadero);
        return espacioMapperImpl.toDto(espacioRepository.save(espacio));
    }
    @Override
    public EspacioDto findEspacioById(String id) {
        return espacioMapperImpl.toDto(espacioRepository.findById(id).orElseThrow(() -> new RuntimeException("Espacio no encontrado")));
    }

    @Override
    public List<EspacioDto> findAllEspacios() {
        return espacioMapperImpl.toDtos(espacioRepository.findAll());
    }

    @Override
    public EspacioDto updateEspacio(String id, EspacioDto espacio) {
        Espacio antiguo = espacioRepository.findById(id).orElseThrow(() -> new RuntimeException("Espacio no encontrado"));
        antiguo.setEstado(estadoEspacioRepository.findById(espacio.estado()).orElseThrow(() -> new RuntimeException("Estado no encontrado")));
        antiguo.setParqueadero(parqueaderoRepository.findById(espacio.parqueadero()).orElseThrow(() -> new RuntimeException("Parqueadero no encontrado")));
        return espacioMapperImpl.toDto(espacioRepository.save(antiguo));
    }

    @Override
    public void deleteEspacioById(String id) {
        espacioRepository.deleteById(id);
    }
}
