package edu.unimag.sgpm.dto.sancion;

import java.time.LocalDate;

public record SancionDto(
        Integer id,
        Integer sancionado,
        Integer sancionador,
        LocalDate fecha,
        String description
) {
}
