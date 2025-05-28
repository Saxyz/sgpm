package edu.unimag.sgpm.control.controller;

import edu.unimag.sgpm.control.dto.JwtResponse;
import edu.unimag.sgpm.control.dto.LoginRequest;
import edu.unimag.sgpm.control.dto.SignUpRequest;
import edu.unimag.sgpm.control.security.jwt.JwtUtil;
import edu.unimag.sgpm.control.security.service.UserDetailsImpl;
import edu.unimag.sgpm.model.entity.*;
import edu.unimag.sgpm.model.repository.ParqueaderoRepository;
import edu.unimag.sgpm.model.repository.RoleRepository;
import edu.unimag.sgpm.model.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {
    private ParqueaderoRepository parqueaderoRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
    private UsuarioRepository userRepository;
    private RoleRepository roleRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            System.out.println("DATA USER:" + loginRequest.username() + " " + loginRequest.password());
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
            // Validaciones de usuario/correo existente

            if (userRepository.existsUserByCorreo(sRequest.correo())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Error: El correo electrónico ya está en uso.");
            }

            // Obtener el rol solicitado
            Role role = roleRepository.findByRole(sRequest.rol())
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + sRequest.rol()));

            Parqueadero parqueadero = parqueaderoRepository.findById(Parqueaderos.Parqueadero1.getNumero())
                    .orElseThrow(() -> new RuntimeException("Error parqueadero in user."));

            // Crear y guardar el usuario
            Usuario user = Usuario.builder()
                    .nombre(sRequest.nombre())
                    .apellido(sRequest.apellido())
                    .correo(sRequest.correo())
                    .contrasenia(passwordEncoder.encode(sRequest.contrasenia()))
                    .roles(new HashSet<>(Set.of(role))) 
                    .parqueadero(parqueadero)
                    .codigo(sRequest.codigo())
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