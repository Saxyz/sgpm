package edu.unimag.sgpm.service;

import edu.unimag.sgpm.dto.Parqueadero.RequestParqueaderoDTO;
import edu.unimag.sgpm.dto.Parqueadero.ResponseParqueaderoDTO;
import edu.unimag.sgpm.dto.Parqueadero.UpdateParqueaderoDTO;

import java.util.List;

public interface ParqueaderoService {

    ResponseParqueaderoDTO createParqueadero(RequestParqueaderoDTO request);

    ResponseParqueaderoDTO findParqueaderoById(Integer id);

    List<ResponseParqueaderoDTO> findAllParqueaderos();

    ResponseParqueaderoDTO updateParqueaderoById(Integer id, UpdateParqueaderoDTO request);

    void deleteParqueaderoById(Integer id);
}
