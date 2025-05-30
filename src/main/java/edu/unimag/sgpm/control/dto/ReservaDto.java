package edu.unimag.sgpm.control.dto;

import java.time.LocalDateTime;

public record ReservaDto(
        Integer id,
        Integer estado,
        Integer usuario,
        Integer espacio,
        LocalDateTime fecha
) {
}
