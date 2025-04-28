package edu.unimag.sgpm.dto.Moto;

public record MotoDTO(
        String idMoto,
        Integer idUsuario,
        Integer idParqueadero,
        String modelo,
        String descripcion
) {
}
