package edu.unimag.sgpm.dto.Usuario;

public record ResponseUsuarioDTO(
        Integer idUsuario,
        String nombre,
        String apellido,
        Integer idParqueadero
) {
}
