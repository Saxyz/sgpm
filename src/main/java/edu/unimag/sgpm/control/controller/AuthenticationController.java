package edu.unimag.sgpm.control.controller;

import edu.unimag.sgpm.control.dto.JwtResponse.JwtResponse;
import edu.unimag.sgpm.control.dto.LoginRequest.LoginRequest;
import edu.unimag.sgpm.control.dto.SignUpRequest.SignUpRequest;
import edu.unimag.sgpm.control.security.jwt.JwtUtil;
import edu.unimag.sgpm.control.security.service.UserDetailsImpl;
import edu.unimag.sgpm.model.entity.ERole;
import edu.unimag.sgpm.model.entity.Role;
import edu.unimag.sgpm.model.entity.Usuario;
import edu.unimag.sgpm.model.repository.RoleRepository;
import edu.unimag.sgpm.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.username(),
                            loginRequest.password()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwtToken = jwtUtil.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(
                    new JwtResponse(
                            jwtToken,
                            "Bearer",
                            userDetails.getUsername(),
                            roles
                    )
            );
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Error de autenticación: " + e.getMessage());
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest sRequest) {
        try {
            // Validaciones de usuario/email existente
            if (userRepository.existsUserByUsername(sRequest.username())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Error: El nombre de usuario ya está en uso.");
            }

            if (userRepository.existsUserByEmail(sRequest.email())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Error: El correo electrónico ya está en uso.");
            }

            // Obtener el rol solicitado
            ERole requestedRole = sRequest.rol().getRole();
            Role role = roleRepository.findByName(requestedRole)
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + requestedRole));

            // Crear y guardar el usuario
            Usuario user = Usuario.builder()
                    .nombre(sRequest.username())
                    .correo(sRequest.email())
                    .contrasenia(passwordEncoder.encode(sRequest.password()))
                    .roles(new HashSet<>(Set.of(role)))
                    .build();

            Usuario savedUser = userRepository.save(user);
            return ResponseEntity.ok(savedUser);

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error en el registro: " + e.getMessage());
        }
    }
}