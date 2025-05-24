package edu.unimag.sgpm.control.dto;

import edu.unimag.sgpm.model.entity.Role;

public record SignUpRequest(String nombre,
                            String apellido,
                            String dirección,
                            String cel,
                            String correo,
                            String contrasenia,
                            Role rol) {
}
