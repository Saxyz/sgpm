package edu.unimag.sgpm.control.service;

import edu.unimag.sgpm.control.dto.reserva.ReservaDto;

import java.util.List;

public interface ReservaService {
    ReservaDto createReserva(ReservaDto reserva);
    ReservaDto findReservaById(int id);
    List<ReservaDto> findAllReservas();
    ReservaDto updateReservaById(ReservaDto reserva);
    void deleteReservaById(int id);
}
