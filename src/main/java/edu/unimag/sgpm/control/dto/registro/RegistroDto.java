package edu.unimag.sgpm.control.dto.registro;

import java.time.LocalDate;
import java.time.LocalTime;

public record RegistroDto(
        Integer id,
        String espacio,
        String moto,
        Integer vigilante,
        LocalDate fecha,
        LocalTime horaEntrada,
        LocalTime horaSalida
) {
}
