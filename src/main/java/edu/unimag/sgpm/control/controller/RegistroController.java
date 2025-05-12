package edu.unimag.sgpm.control.controller;
import edu.unimag.sgpm.control.dto.registro.RegistroDto;
import edu.unimag.sgpm.control.exceptions.EspacioNotFoundException;
import edu.unimag.sgpm.control.service.RegistroService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/controller/v1/registros")
public class RegistroController {
    private final RegistroService registroService;
    public RegistroController(RegistroService registroService) {
        this.registroService = registroService;
    }

    @GetMapping()
    public ResponseEntity<List<RegistroDto>> getAllRegistros() {
        return ResponseEntity.ok(registroService.findAllRegistros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroDto> getRegistroById(@PathVariable Integer id) {
        RegistroDto registro = registroService.findRegistroById(id);
        if (registro == null) {
            throw new EspacioNotFoundException("registro no encontrado: " + id);
        }
        return ResponseEntity.ok(registro);
    }

    @PostMapping()
    public ResponseEntity<RegistroDto> createRegistro(@RequestBody RegistroDto registro) {
        return createNewRegistro(registro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroDto> updateRegistro(@PathVariable Integer id, @RequestBody RegistroDto registro) {
        try {
            RegistroDto updatedParqueadero = registroService.updateRegistroById(id, registro);
            return ResponseEntity.ok(updatedParqueadero);
        } catch (RuntimeException e) {
            RegistroDto nuevoRegistro = new RegistroDto(registro.id(),registro.espacio(), registro.moto(), registro.vigilante(),registro.fecha(),registro.horaEntrada(),registro.horaSalida());
            return createNewRegistro(nuevoRegistro);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistro(@PathVariable Integer id) {
        registroService.deleteRegistroById(id);
        return ResponseEntity.noContent().build();
    }

    @NotNull
    private static ResponseEntity<RegistroDto> createNewRegistro(RegistroDto c) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(c.id())
                .toUri();
        return ResponseEntity.created(location).body(c);
    }
}
