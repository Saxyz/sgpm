package edu.unimag.sgpm.control.controller;
import edu.unimag.sgpm.control.dto.SancionDto;
import edu.unimag.sgpm.control.exceptions.EspacioNotFoundException;
import edu.unimag.sgpm.control.service.SancionService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/controller/v1/sanciones")
public class SancionController {
    private final SancionService sancionService;
    public SancionController(SancionService sancionService) {
        this.sancionService = sancionService;
    }
    @GetMapping()
    public ResponseEntity<List<SancionDto>> getAllSanciones() {
        return ResponseEntity.ok(sancionService.findAllSancion());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SancionDto> getSancionById(@PathVariable Integer id) {
        SancionDto sancion = sancionService.findSancionById(id);
        if (sancion == null) {
            throw new EspacioNotFoundException("sancion no encontrado: " + id);
        }
        return ResponseEntity.ok(sancion);
    }

    @PostMapping()
    public ResponseEntity<SancionDto> createSancion(@RequestBody SancionDto sancion) {
        return createNewSancion(sancion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SancionDto> updateReserva(@PathVariable Integer id, @RequestBody SancionDto sancion) {
        try {
            SancionDto updatedSancion = sancionService.updateSancionById(id,sancion);
            return ResponseEntity.ok(updatedSancion);
        } catch (RuntimeException e) {
            return createNewSancion(sancion);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSancion(@PathVariable Integer id) {
        sancionService.deleteSancionById(id);
        return ResponseEntity.noContent().build();
    }

    @NotNull
    private static ResponseEntity<SancionDto> createNewSancion(SancionDto c) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(c.id())
                .toUri();
        return ResponseEntity.created(location).body(c);
    }
}
