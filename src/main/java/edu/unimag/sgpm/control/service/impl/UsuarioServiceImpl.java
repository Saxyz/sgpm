package edu.unimag.sgpm.control.service.impl;

import edu.unimag.sgpm.control.dto.usuario.RequestUsuarioDTO;
import edu.unimag.sgpm.control.dto.usuario.ResponseUsuarioDTO;
import edu.unimag.sgpm.control.dto.usuario.UpdateUsuarioDTO;
import edu.unimag.sgpm.control.mapper.UsuarioMapper;
import edu.unimag.sgpm.model.entity.Parqueadero;
import edu.unimag.sgpm.model.entity.Usuario;
import edu.unimag.sgpm.model.repository.ParqueaderoRepository;
import edu.unimag.sgpm.model.repository.UsuarioRepository;
import edu.unimag.sgpm.control.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ParqueaderoRepository parqueaderoRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    public ResponseUsuarioDTO createUsuario(RequestUsuarioDTO request) {
        Usuario usuario = usuarioMapper.toEntity(request);

        Parqueadero parqueadero = parqueaderoRepository.findById(request.idParqueadero())
                .orElseThrow(() -> new RuntimeException("Parqueadero no encontrado"));

        usuario.setParqueadero(parqueadero);

        Usuario savedUsuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDto(savedUsuario);
    }

    @Override
    public ResponseUsuarioDTO findUsuarioById(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return usuarioMapper.toDto(usuario);
    }

    @Override
    public List<ResponseUsuarioDTO> findAllUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseUsuarioDTO updateUsuarioById(Integer id, UpdateUsuarioDTO request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuarioMapper.updateEntityFromDto(request, usuario);

        if (request.idParqueadero() != null) {
            Parqueadero parqueadero = parqueaderoRepository.findById(request.idParqueadero())
                    .orElseThrow(() -> new RuntimeException("Parqueadero no encontrado"));
            usuario.setParqueadero(parqueadero);
        }

        Usuario updatedUsuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDto(updatedUsuario);
    }

    @Override
    public void deleteUsuarioById(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuarioRepository.delete(usuario);
    }
}
