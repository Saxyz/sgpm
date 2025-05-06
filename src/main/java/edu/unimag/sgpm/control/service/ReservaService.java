package edu.unimag.sgpm.control.service;

import edu.unimag.sgpm.control.dto.reserva.ReservaDto;

import java.util.List;

public interface ReservaService {
    ReservaDto createReserva(ReservaDto reserva);
    ReservaDto findReservaById(Integer id);
    List<ReservaDto> findAllReservas();
    ReservaDto updateReservaById(Integer id, ReservaDto reserva);
    void deleteReservaById(Integer id);
}
