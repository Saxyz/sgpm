package edu.unimag.sgpm.dto.Usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestUsuarioDTO(
        @NotBlank String nombre,
        @NotBlank String apellido,
        @NotNull Integer idParqueadero
) {
}
