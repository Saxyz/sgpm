package edu.unimag.sgpm.control.dto;

import java.util.Set;

public record UsuarioDto (Integer id, Integer parqueadero, String nombre, String apellido, String correo, String contrasenia, Set<Integer> roles){
}
