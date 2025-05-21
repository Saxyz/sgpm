package edu.unimag.sgpm.control.dto;

import edu.unimag.sgpm.model.entity.Registro;
import edu.unimag.sgpm.model.entity.Role;

import java.util.List;

public record SignUpRequest(String firstName,
                            String lastName,
                            String address,
                            String cell,
                            String email,
                            List<Registro> reserves,
                            String username,
                            String password,
                            Role rol) {
}
