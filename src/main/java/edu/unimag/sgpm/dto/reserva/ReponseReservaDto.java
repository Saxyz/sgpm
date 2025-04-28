package edu.unimag.sgpm.dto.reserva;

import java.time.LocalDateTime;

public record ReponseReservaDto(
        Integer id,
        Integer estado,
        Integer usuario,
        String espacio,
        LocalDateTime fecha
) {
}
