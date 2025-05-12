package edu.unimag.sgpm.control.controller;
import edu.unimag.sgpm.control.dto.parqueadero.ResponseParqueaderoDTO;
import edu.unimag.sgpm.control.dto.parqueadero.UpdateParqueaderoDTO;
import edu.unimag.sgpm.control.exceptions.EspacioNotFoundException;
import edu.unimag.sgpm.control.service.ParqueaderoService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/controller/v1/parqueaderos")
public class ParqueaderoController {
    private final ParqueaderoService parqueaderoService;
    public ParqueaderoController(ParqueaderoService parqueaderoService) {
        this.parqueaderoService = parqueaderoService;
    }

    @GetMapping()
    public ResponseEntity<List<ResponseParqueaderoDTO>> getAllParqueaderos() {
        return ResponseEntity.ok(parqueaderoService.findAllParqueaderos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseParqueaderoDTO> getParqueaderoById(@PathVariable Integer id) {
        ResponseParqueaderoDTO parqueadero = parqueaderoService.findParqueaderoById(id);
        if (parqueadero == null) {
            throw new EspacioNotFoundException("parqueadero no encontrado: " + id);
        }
        return ResponseEntity.ok(parqueadero);
    }

    @PostMapping()
    public ResponseEntity<ResponseParqueaderoDTO> createParqueadero(@RequestBody ResponseParqueaderoDTO parqueadero) {
        return createNewParqueadero(parqueadero);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseParqueaderoDTO> updatespacio(@PathVariable Integer id, @RequestBody UpdateParqueaderoDTO parqueadero) {
        try {
            ResponseParqueaderoDTO updatedParqueadero = parqueaderoService.updateParqueaderoById(id, parqueadero);
            return ResponseEntity.ok(updatedParqueadero);
        } catch (RuntimeException e) {
            ResponseParqueaderoDTO nuevoParqueadero = new ResponseParqueaderoDTO(id, parqueadero.nombre());
            return createNewParqueadero(nuevoParqueadero);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParqueadero(@PathVariable Integer id) {
        parqueaderoService.deleteParqueaderoById(id);
        return ResponseEntity.noContent().build();
    }

    @NotNull
    private static ResponseEntity<ResponseParqueaderoDTO> createNewParqueadero(ResponseParqueaderoDTO c) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(c.idParqueadero())
                .toUri();
        return ResponseEntity.created(location).body(c);
    }
}
