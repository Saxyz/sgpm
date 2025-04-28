package edu.unimag.sgpm.dto.usuario;

public record ResponseUsuarioDTO(
        Integer idUsuario,
        String nombre,
        String apellido,
        Integer idParqueadero
) {
}
