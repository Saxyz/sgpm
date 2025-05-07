package edu.unimag.sgpm.control.security.service;

import edu.unimag.sgpm.model.entity.Usuario;
import edu.unimag.sgpm.model.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario =usuarioRepository.findByCorreo(username)
                .orElseThrow(() -> new UsernameNotFoundException(username+" not found"));
        return UserDetailsImpl.build(usuario);
    }
}
