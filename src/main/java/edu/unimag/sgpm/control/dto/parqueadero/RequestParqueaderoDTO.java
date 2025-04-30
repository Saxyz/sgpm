package edu.unimag.sgpm.control.dto.parqueadero;

import jakarta.validation.constraints.NotBlank;

public record RequestParqueaderoDTO(
        @NotBlank String nombre
) {
}
