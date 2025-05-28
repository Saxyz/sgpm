package edu.unimag.sgpm.control.dto;

public record MotoDto(
        Integer idMoto,
        String placa,
        Integer idUsuario,
        Integer idParqueadero,
        String modelo,
        String descripcion,
        String ruta
) {
}
