package edu.unimag.sgpm.control.service;

import edu.unimag.sgpm.control.dto.EstadoReservaDto;

import java.util.List;

public interface EstadoReservaService {
    EstadoReservaDto createEstadoReserva(EstadoReservaDto estado);
    EstadoReservaDto findEstadoReservaById(Integer id);
    List<EstadoReservaDto> findAllEstadoReserva();
    EstadoReservaDto updateEstadoReserva(Integer id, EstadoReservaDto estado);
    void deleteEstadoReserva(Integer id);
}
