package edu.unimag.sgpm.control.controller;
import edu.unimag.sgpm.control.dto.usuario.ResponseUsuarioDTO;
import edu.unimag.sgpm.control.dto.usuario.UpdateUsuarioDTO;
import edu.unimag.sgpm.control.exceptions.EspacioNotFoundException;
import edu.unimag.sgpm.control.service.UsuarioService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/controller/v1/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @GetMapping()
    public ResponseEntity<List<ResponseUsuarioDTO>> getAllUsuarios() {
        return ResponseEntity.ok(usuarioService.findAllUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUsuarioDTO> getUsuarioById(@PathVariable Integer id) {
        ResponseUsuarioDTO usuario = usuarioService.findUsuarioById(id);
        if (usuario == null) {
            throw new EspacioNotFoundException("usuario no encontrado: " + id);
        }
        return ResponseEntity.ok(usuario);
    }

    @PostMapping()
    public ResponseEntity<ResponseUsuarioDTO> createUsuario(@RequestBody ResponseUsuarioDTO usuario) {
        return createNewUsuario(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseUsuarioDTO> updatespacio(@PathVariable Integer id, @RequestBody UpdateUsuarioDTO usuario) {
        try {
            ResponseUsuarioDTO updatedUsuario = usuarioService.updateUsuarioById(id,usuario);
            return ResponseEntity.ok(updatedUsuario);
        } catch (RuntimeException e) {
            ResponseUsuarioDTO nuevoUsuario = new ResponseUsuarioDTO(id,usuario.nombre(),usuario.apellido(),usuario.idParqueadero());
            return createNewUsuario(nuevoUsuario);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSancion(@PathVariable Integer id) {
        usuarioService.deleteUsuarioById(id);
        return ResponseEntity.noContent().build();
    }

    @NotNull
    private static ResponseEntity<ResponseUsuarioDTO> createNewUsuario(ResponseUsuarioDTO c) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(c.idUsuario())
                .toUri();
        return ResponseEntity.created(location).body(c);
    }
}
