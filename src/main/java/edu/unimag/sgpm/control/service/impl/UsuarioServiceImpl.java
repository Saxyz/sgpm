package edu.unimag.sgpm.control.service.impl;

import edu.unimag.sgpm.control.dto.SignUpRequest;
import edu.unimag.sgpm.control.dto.UsuarioDto;
import edu.unimag.sgpm.control.exceptions.UsuarioNotFoundException;
import edu.unimag.sgpm.control.mapper.UsuarioMapper;
import edu.unimag.sgpm.model.entity.Parqueaderos;
import edu.unimag.sgpm.model.entity.Parqueadero;
import edu.unimag.sgpm.model.entity.ERole;
import edu.unimag.sgpm.model.entity.Role;
import edu.unimag.sgpm.model.entity.Usuario;
import edu.unimag.sgpm.model.repository.ParqueaderoRepository;
import edu.unimag.sgpm.model.repository.RoleRepository;
import edu.unimag.sgpm.model.repository.UsuarioRepository;
import edu.unimag.sgpm.control.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final ParqueaderoRepository parqueaderoRepository;
    private final RoleRepository roleRepository;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Usuario registrarEstudiante(SignUpRequest request) {

        if (usuarioRepository.existsUserByCorreo(request.correo())) {
            throw new RuntimeException("El correo ya está registrado.");
        }

        Role role = roleRepository.findByRole(ERole.ROLE_ESTUDIANTE)
                .orElseThrow(() ->
                        new RuntimeException("Rol ROLE_ESTUDIANTE no encontrado."));

        Parqueadero parqueadero = parqueaderoRepository.findById(
                        Parqueaderos.Parqueadero1.getNumero())
                .orElseThrow(() ->
                        new RuntimeException("Parqueadero no encontrado."));

        Usuario usuario = Usuario.builder()
                .nombre(request.nombre())
                .apellido(request.apellido())
                .correo(request.correo())
                .codigo(request.codigo())
                .contrasenia(passwordEncoder.encode(request.contrasenia()))
                .roles(Set.of(role))
                .parqueadero(parqueadero)
                .build();

        return usuarioRepository.save(usuario);
    }


    @Override
    public UsuarioDto createUsuario(SignUpRequest request) {
        if (usuarioRepository.existsUserByCorreo(request.correo())) {
            throw new RuntimeException("El correo ya está registrado.");
        }

        Role role = roleRepository.findByRole(request.rol())
                .orElseThrow(() ->
                        new RuntimeException("Rol no encontrado: " + request.rol()));

        Parqueadero parqueadero = parqueaderoRepository.findById(
                        Parqueaderos.Parqueadero1.getNumero())
                .orElseThrow(() ->
                        new RuntimeException("Parqueadero no encontrado."));

        Usuario usuario = Usuario.builder()
                .nombre(request.nombre())
                .apellido(request.apellido())
                .correo(request.correo())
                .codigo(request.codigo())
                .contrasenia(passwordEncoder.encode(request.contrasenia()))
                .roles(Set.of(role))
                .parqueadero(parqueadero)
                .build();

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
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombre(request.nombre());
        usuario.setApellido(request.apellido());
        usuario.setCorreo(request.correo());

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

    @Override
    public boolean cambiarContrasenia(Integer id, String actual, String nueva) {
        String message = "Usuario no encontrado";
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(message));

        if (!passwordEncoder.matches(actual, usuario.getContrasenia())) {
            return false;
        }

        usuario.setContrasenia(passwordEncoder.encode(nueva));
        usuarioRepository.save(usuario);
        return true;
    }

}
