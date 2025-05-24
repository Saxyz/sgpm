package edu.unimag.sgpm.control.controller;
import edu.unimag.sgpm.control.dto.EstadoReservaDto;
import edu.unimag.sgpm.control.exceptions.EspacioNotFoundException;
import edu.unimag.sgpm.control.service.EstadoReservaService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/api/v1/estadoReservas")
public class EstadoReservaController {
    private final EstadoReservaService estadoReservaservice;
    public EstadoReservaController(EstadoReservaService estadoReservaservice) {
        this.estadoReservaservice = estadoReservaservice;
    }

    @GetMapping()
    public ResponseEntity<List<EstadoReservaDto>> getAllEstadoReservas() {
        return ResponseEntity.ok(estadoReservaservice.findAllEstadoReserva());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoReservaDto> getEstadoReservaById(@PathVariable Integer id) {
        EstadoReservaDto estadoReserva = estadoReservaservice.findEstadoReservaById(id);
        if (estadoReserva == null) {
            throw new EspacioNotFoundException("estadoReserva no encontrado: " + id);
        }
        return ResponseEntity.ok(estadoReserva);
    }

    @PostMapping()
    public ResponseEntity<EstadoReservaDto> createEstadoReserva(@RequestBody EstadoReservaDto estadoReservaDto) {
        return createNewEstadoReserva(estadoReservaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoReservaDto> updatEstadoReserva(@PathVariable Integer id, @RequestBody EstadoReservaDto estadoReservaDto) {
        try {
            EstadoReservaDto updatedEspacio = estadoReservaservice.updateEstadoReserva(id,estadoReservaDto);
            return ResponseEntity.ok(updatedEspacio);
        } catch (RuntimeException e) {
            return createNewEstadoReserva(estadoReservaDto); // Si no existe, créalo
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstadoReserva(@PathVariable Integer id) {
        estadoReservaservice.deleteEstadoReserva(id);
        return ResponseEntity.noContent().build();
    }

    @NotNull
    private static ResponseEntity<EstadoReservaDto> createNewEstadoReserva(EstadoReservaDto c) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(c.idEstado())
                .toUri();
        return ResponseEntity.created(location).body(c);
    }
}
