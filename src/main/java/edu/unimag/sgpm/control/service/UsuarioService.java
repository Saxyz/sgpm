package edu.unimag.sgpm.control.service;

import edu.unimag.sgpm.control.dto.usuario.RequestUsuarioDTO;
import edu.unimag.sgpm.control.dto.usuario.ResponseUsuarioDTO;
import edu.unimag.sgpm.control.dto.usuario.UpdateUsuarioDTO;
import edu.unimag.sgpm.model.entity.Role;

import java.util.List;
import java.util.Set;

public interface UsuarioService {

    ResponseUsuarioDTO createUsuario(RequestUsuarioDTO request);

    ResponseUsuarioDTO findUsuarioById(Integer id);

    ResponseUsuarioDTO findUsuarioByRoles(Set<Role> roles);

    List<ResponseUsuarioDTO> findAllUsuarios();

    ResponseUsuarioDTO updateUsuarioById(Integer id, UpdateUsuarioDTO request);

    void deleteUsuarioById(Integer id);
}
