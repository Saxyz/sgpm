package edu.unimag.sgpm.control.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record RegistroDto(
        Integer id,
        Integer espacio,
        Integer moto,
        Integer vigilante,
        LocalDate fecha,
        LocalTime horaEntrada,
        LocalTime horaSalida
) {
}
