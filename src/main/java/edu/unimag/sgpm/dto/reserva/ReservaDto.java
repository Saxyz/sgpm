package edu.unimag.sgpm.dto.reserva;

import java.time.LocalDateTime;

public record ReservaDto(
        Integer id,
        String estado,
        Integer usuario,
        String espacio,
        LocalDateTime fecha
) {
}
