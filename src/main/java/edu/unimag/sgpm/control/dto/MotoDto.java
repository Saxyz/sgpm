package edu.unimag.sgpm.control.dto;

public record MotoDto(
        String idMoto,
        Integer idUsuario,
        Integer idParqueadero,
        String modelo,
        String descripcion
) {
}
