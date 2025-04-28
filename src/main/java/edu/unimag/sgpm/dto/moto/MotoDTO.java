package edu.unimag.sgpm.dto.moto;

public record MotoDTO(
        String idMoto,
        Integer idUsuario,
        Integer idParqueadero,
        String modelo,
        String descripcion
) {
}
