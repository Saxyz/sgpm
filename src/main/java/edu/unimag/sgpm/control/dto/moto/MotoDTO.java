package edu.unimag.sgpm.control.dto.moto;

public record MotoDTO(
        String idMoto,
        Integer idUsuario,
        Integer idParqueadero,
        String modelo,
        String descripcion
) {
}
