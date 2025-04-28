package edu.unimag.sgpm.dto.Parqueadero;

import jakarta.validation.constraints.NotBlank;

public record RequestParqueaderoDTO(
        @NotBlank String nombre
) {
}
