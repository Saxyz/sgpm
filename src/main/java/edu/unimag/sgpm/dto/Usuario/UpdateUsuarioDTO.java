package edu.unimag.sgpm.dto.Usuario;

public record UpdateUsuarioDTO(
        String nombre,
        String apellido,
        Integer idParqueadero
) {
}
