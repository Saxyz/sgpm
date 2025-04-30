package edu.unimag.sgpm.control.service;

import edu.unimag.sgpm.control.dto.usuario.RequestUsuarioDTO;
import edu.unimag.sgpm.control.dto.usuario.ResponseUsuarioDTO;
import edu.unimag.sgpm.control.dto.usuario.UpdateUsuarioDTO;

import java.util.List;

public interface UsuarioService {

    ResponseUsuarioDTO createUsuario(RequestUsuarioDTO request);

    ResponseUsuarioDTO findUsuarioById(Integer id);

    List<ResponseUsuarioDTO> findAllUsuarios();

    ResponseUsuarioDTO updateUsuarioById(Integer id, UpdateUsuarioDTO request);

    void deleteUsuarioById(Integer id);
}
