package edu.unimag.sgpm.control.service.impl;

import edu.unimag.sgpm.control.dto.parqueadero.RequestParqueaderoDTO;
import edu.unimag.sgpm.control.dto.parqueadero.ResponseParqueaderoDTO;
import edu.unimag.sgpm.control.dto.parqueadero.UpdateParqueaderoDTO;
import edu.unimag.sgpm.control.mapper.ParqueaderoMapper;
import edu.unimag.sgpm.model.entity.Parqueadero;
import edu.unimag.sgpm.model.repository.ParqueaderoRepository;
import edu.unimag.sgpm.control.service.ParqueaderoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ParqueaderoServiceImpl implements ParqueaderoService {

    private final ParqueaderoRepository parqueaderoRepository;
    private final ParqueaderoMapper parqueaderoMapper;

    @Override
    public ResponseParqueaderoDTO createParqueadero(RequestParqueaderoDTO request) {
        Parqueadero parqueadero = parqueaderoMapper.toEntity(request);
        Parqueadero savedParqueadero = parqueaderoRepository.save(parqueadero);
        return parqueaderoMapper.toDto(savedParqueadero);
    }

    @Override
    public ResponseParqueaderoDTO findParqueaderoById(Integer id) {
        Parqueadero parqueadero = parqueaderoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parqueadero no encontrado"));
        return parqueaderoMapper.toDto(parqueadero);
    }

    @Override
    public List<ResponseParqueaderoDTO> findAllParqueaderos() {
        List<Parqueadero> parqueaderos = parqueaderoRepository.findAll();
        return parqueaderos.stream()
                .map(parqueaderoMapper::toDto).toList();
    }

    @Override
    public ResponseParqueaderoDTO updateParqueaderoById(Integer id, UpdateParqueaderoDTO request) {
        Parqueadero parqueadero = parqueaderoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parqueadero no encontrado"));

        parqueaderoMapper.updateEntityFromDto(request, parqueadero);

        Parqueadero updatedParqueadero = parqueaderoRepository.save(parqueadero);
        return parqueaderoMapper.toDto(updatedParqueadero);
    }

    @Override
    public void deleteParqueaderoById(Integer id) {
        if (!parqueaderoRepository.existsById(id)) {
            throw new RuntimeException("Parqueadero no encontrado para eliminar");
        }
        parqueaderoRepository.deleteById(id);
    }
}
