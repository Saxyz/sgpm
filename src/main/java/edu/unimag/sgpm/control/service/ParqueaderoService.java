package edu.unimag.sgpm.control.service;

import edu.unimag.sgpm.control.dto.ParqueaderoDto;

import java.util.List;

public interface ParqueaderoService {

    ParqueaderoDto createParqueadero(ParqueaderoDto request);

    ParqueaderoDto findParqueaderoById(Integer id);

    List<ParqueaderoDto> findAllParqueaderos();

    ParqueaderoDto updateParqueaderoById(Integer id, ParqueaderoDto request);

    void deleteParqueaderoById(Integer id);
}
