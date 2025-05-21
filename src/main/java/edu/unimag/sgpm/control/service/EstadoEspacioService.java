package edu.unimag.sgpm.control.service;

import edu.unimag.sgpm.control.dto.EstadoEspacioDto;

import java.util.List;

public interface EstadoEspacioService {
    EstadoEspacioDto createEspacio(EstadoEspacioDto estadoEspacioDto);
    EstadoEspacioDto findEstadoEspacioById(Integer id);
    List<EstadoEspacioDto> findAllEstadoEspacios();
    EstadoEspacioDto updateEstadoEspacio(Integer id, EstadoEspacioDto estadoEspacioDto);
    void deleteEstadoEspacio(Integer id);
}
