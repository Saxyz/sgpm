package edu.unimag.sgpm.dto.usuario;

public record UpdateUsuarioDTO(
        String nombre,
        String apellido,
        Integer idParqueadero
) {
}
