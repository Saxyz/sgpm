package edu.unimag.sgpm.control.controller;
import edu.unimag.sgpm.control.dto.ParqueaderoDto;
import edu.unimag.sgpm.control.exceptions.EspacioNotFoundException;
import edu.unimag.sgpm.control.service.ParqueaderoService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/api/v1/parqueaderos")
public class ParqueaderoController {
    private final ParqueaderoService parqueaderoService;
    public ParqueaderoController(ParqueaderoService parqueaderoService) {
        this.parqueaderoService = parqueaderoService;
    }

    @GetMapping()
    public ResponseEntity<List<ParqueaderoDto>> getAllParqueaderos() {
        return ResponseEntity.ok(parqueaderoService.findAllParqueaderos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParqueaderoDto> getParqueaderoById(@PathVariable Integer id) {
        ParqueaderoDto parqueadero = parqueaderoService.findParqueaderoById(id);
        if (parqueadero == null) {
            throw new EspacioNotFoundException("parqueadero no encontrado: " + id);
        }
        return ResponseEntity.ok(parqueadero);
    }

    @PostMapping()
    public ResponseEntity<ParqueaderoDto> createParqueadero(@RequestBody ParqueaderoDto parqueadero) {
        return createNewParqueadero(parqueadero);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParqueaderoDto> updatespacio(@PathVariable Integer id, @RequestBody ParqueaderoDto parqueadero) {
        try {
            ParqueaderoDto updatedParqueadero = parqueaderoService.updateParqueaderoById(id, parqueadero);
            return ResponseEntity.ok(updatedParqueadero);
        } catch (RuntimeException e) {
            ParqueaderoDto nuevoParqueadero = new ParqueaderoDto(id, parqueadero.nombre(),parqueadero.espacios());
            return createNewParqueadero(nuevoParqueadero);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParqueadero(@PathVariable Integer id) {
        parqueaderoService.deleteParqueaderoById(id);
        return ResponseEntity.noContent().build();
    }

    @NotNull
    private static ResponseEntity<ParqueaderoDto> createNewParqueadero(ParqueaderoDto c) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(c.id())
                .toUri();
        return ResponseEntity.created(location).body(c);
    }
}
