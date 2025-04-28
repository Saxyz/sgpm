package edu.unimag.sgpm.dto.Moto;

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
