package edu.unimag.sgpm.control.service;

import edu.unimag.sgpm.control.dto.UsuarioDto;
import edu.unimag.sgpm.model.entity.Role;

import java.util.List;
import java.util.Set;

public interface UsuarioService {

    UsuarioDto createUsuario(UsuarioDto request);

    UsuarioDto findUsuarioById(Integer id);

    UsuarioDto findUsuarioByRoles(Set<Role> roles);

    List<UsuarioDto> findAllUsuarios();

    UsuarioDto updateUsuarioById(Integer id, UsuarioDto request);

    void deleteUsuarioById(Integer id);
}
