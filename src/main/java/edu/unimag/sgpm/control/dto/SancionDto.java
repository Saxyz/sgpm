package edu.unimag.sgpm.control.dto;

import java.time.LocalDate;

public record SancionDto(
        Integer id,
        Integer sancionado,
        Integer sancionador,
        LocalDate fecha,
        String descripcion
) {
}
