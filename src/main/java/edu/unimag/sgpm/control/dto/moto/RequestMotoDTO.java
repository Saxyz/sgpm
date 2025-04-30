package edu.unimag.sgpm.control.dto.moto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestMotoDTO(
    @NotNull String idMoto,
    @NotNull Integer idUsuario,
    @NotNull Integer idParqueadero,
    @NotBlank String modelo,
    @NotBlank String descripcion
) {
}
