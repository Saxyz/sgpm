package edu.unimag.sgpm.control.service.impl;

import edu.unimag.sgpm.control.dto.UsuarioDto;
import edu.unimag.sgpm.control.mapper.UsuarioMapper;
import edu.unimag.sgpm.model.entity.Parqueadero;
import edu.unimag.sgpm.model.entity.Role;
import edu.unimag.sgpm.model.entity.Usuario;
import edu.unimag.sgpm.model.repository.ParqueaderoRepository;
import edu.unimag.sgpm.model.repository.UsuarioRepository;
import edu.unimag.sgpm.control.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ParqueaderoRepository parqueaderoRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    public UsuarioDto createUsuario(UsuarioDto request) {
        Usuario usuario = usuarioMapper.toEntity(request);

        Parqueadero parqueadero = parqueaderoRepository.findById(request.parqueadero())
                .orElseThrow(() -> new RuntimeException("Parqueadero no encontrado"));

        usuario.setParqueadero(parqueadero);

        Usuario savedUsuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDto(savedUsuario);
    }

    @Override
    public UsuarioDto findUsuarioById(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return usuarioMapper.toDto(usuario);
    }

    @Override
    public UsuarioDto findUsuarioByRoles(Set<Role> roles) {
        return usuarioMapper.toDto(usuarioRepository.findByRoles(roles).orElseThrow(() -> new RuntimeException("Usuario no encontrado")));
    }

    @Override
    public List<UsuarioDto> findAllUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDto updateUsuarioById(Integer id, UsuarioDto request) {
        usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Usuario usuario;

        usuario = usuarioMapper.toEntity(request);

        if (request.parqueadero() != null) {
            Parqueadero parqueadero = parqueaderoRepository.findById(request.parqueadero())
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
