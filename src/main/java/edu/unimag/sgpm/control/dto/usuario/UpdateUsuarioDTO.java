package edu.unimag.sgpm.control.dto.usuario;

public record UpdateUsuarioDTO(
        String nombre,
        String apellido,
        Integer idParqueadero
) {
}
