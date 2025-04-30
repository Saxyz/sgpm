package edu.unimag.sgpm.control.dto.usuario;

public record ResponseUsuarioDTO(
        Integer idUsuario,
        String nombre,
        String apellido,
        Integer idParqueadero
) {
}
