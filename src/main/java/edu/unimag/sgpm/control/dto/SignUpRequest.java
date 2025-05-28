package edu.unimag.sgpm.control.dto;

import edu.unimag.sgpm.model.entity.ERole;

public record SignUpRequest(String nombre,
                            String apellido,
                            String dirección,
                            String cel,
                            String correo,
                            String contrasenia,
                            String codigo,
                            ERole rol) {
}
