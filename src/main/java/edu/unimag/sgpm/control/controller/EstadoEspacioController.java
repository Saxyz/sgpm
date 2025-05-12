package edu.unimag.sgpm.control.controller;
import edu.unimag.sgpm.control.dto.estadoEspacio.EstadoEspacioDto;
import edu.unimag.sgpm.control.exceptions.EspacioNotFoundException;
import edu.unimag.sgpm.control.service.EstadoEspacioService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/controller/v1/estadoEspacios")
public class EstadoEspacioController {
    private final EstadoEspacioService estadoEspacioService;
    public EstadoEspacioController(EstadoEspacioService estadoEspacioService) {
        this.estadoEspacioService = estadoEspacioService;
    }

    @GetMapping()
    public ResponseEntity<List<EstadoEspacioDto>> getAllEstadoE() {
        return ResponseEntity.ok(estadoEspacioService.findAllEstadoEspacios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoEspacioDto> getEspaceById(@PathVariable Integer id) {
        EstadoEspacioDto estadoEspacio = estadoEspacioService.findEstadoEspacioById(id);
        if (estadoEspacio == null) {
            throw new EspacioNotFoundException("estadoEspacio no encontrado: " + id);
        }
        return ResponseEntity.ok(estadoEspacio);
    }

    @PostMapping()
    public ResponseEntity<EstadoEspacioDto> createEspacio(@RequestBody EstadoEspacioDto estadoEspacio) {
        return createNewEstadoEspacio(estadoEspacio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoEspacioDto> updatespacio(@PathVariable Integer id, @RequestBody EstadoEspacioDto estadoEspacio) {
        try {
            EstadoEspacioDto updatedEspacio = estadoEspacioService.updateEstadoEspacio(id, estadoEspacio);
            return ResponseEntity.ok(updatedEspacio);
        } catch (RuntimeException e) {
            return createNewEstadoEspacio(estadoEspacio); // Si no existe, créalo
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstadoEpacio(@PathVariable Integer id) {
        estadoEspacioService.deleteEstadoEspacio(id);
        return ResponseEntity.noContent().build();
    }

    @NotNull
    private static ResponseEntity<EstadoEspacioDto> createNewEstadoEspacio(EstadoEspacioDto c) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(c.idEstado())
                .toUri();
        return ResponseEntity.created(location).body(c);
    }
}
