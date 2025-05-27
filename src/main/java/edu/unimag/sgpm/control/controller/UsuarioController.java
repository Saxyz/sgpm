package edu.unimag.sgpm.control.controller;
import edu.unimag.sgpm.control.dto.CambioContraseniaDto;
import edu.unimag.sgpm.control.dto.UsuarioDto;
import edu.unimag.sgpm.control.exceptions.EspacioNotFoundException;
import edu.unimag.sgpm.control.service.UsuarioService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @GetMapping()
    public ResponseEntity<List<UsuarioDto>> getAllUsuarios() {
        return ResponseEntity.ok(usuarioService.findAllUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> getUsuarioById(@PathVariable Integer id) {
        UsuarioDto usuario = usuarioService.findUsuarioById(id);
        if (usuario == null) {
            throw new EspacioNotFoundException("usuario no encontrado: " + id);
        }
        return ResponseEntity.ok(usuario);
    }

    @PostMapping()
    public ResponseEntity<UsuarioDto> createUsuario(@RequestBody UsuarioDto usuario) {
        return createNewUsuario(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> updatespacio(@PathVariable Integer id, @RequestBody UsuarioDto usuario) {
        try {
            UsuarioDto updatedUsuario = usuarioService.updateUsuarioById(id,usuario);
            return ResponseEntity.ok(updatedUsuario);
        } catch (RuntimeException e) {
            UsuarioDto nuevoUsuario = new UsuarioDto(usuario.id(), usuario.parqueadero(), usuario.nombre(), usuario.apellido(), usuario.correo(), usuario.contrasenia(), usuario.roles());
            return createNewUsuario(nuevoUsuario);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSancion(@PathVariable Integer id) {
        usuarioService.deleteUsuarioById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/contrasenia")
    public ResponseEntity<Void> cambiarContrasenia(
            @PathVariable Integer id,
            @RequestBody CambioContraseniaDto cambio
    ) {
        boolean actualizado = usuarioService.cambiarContrasenia(id, cambio.contraseniaActual(), cambio.nuevaContrasenia());
        if (!actualizado) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.noContent().build();
    }


    @NotNull
    private static ResponseEntity<UsuarioDto> createNewUsuario(UsuarioDto c) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(c.id())
                .toUri();
        return ResponseEntity.created(location).body(c);
    }
}
